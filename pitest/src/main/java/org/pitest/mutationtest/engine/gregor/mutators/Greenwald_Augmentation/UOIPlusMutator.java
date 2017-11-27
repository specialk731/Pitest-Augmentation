/*
 * Kevin Greenwald
 *
 * UOI Mutator -
 *    Mutator that adds ++ operators and removes unary operators
 */
package org.pitest.mutationtest.engine.gregor.mutators.Greenwald_Augmentation;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum UOIPlusMutator implements MethodMutatorFactory {

    UOI_PLUS_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new UOIPlusMutatorMethodVisitor(this, context, methodVisitor);
    }

    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    @Override
    public String getName() {
        return name();
    }
}

class UOIPlusMutatorMethodVisitor extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext      context;

    UOIPlusMutatorMethodVisitor(final MethodMutatorFactory factory, final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM6, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitVarInsn(final int opcode, final int var) {

        if (opcode == Opcodes.ILOAD) {
            final MutationIdentifier newId = this.context.registerMutation( this.factory, "UOI - Incremented int variable");

            if (this.context.shouldMutate(newId)) {
                this.mv.visitIntInsn(Opcodes.ILOAD, var);
                //this.mv.visitIntInsn(Opcodes.BIPUSH, 1);
                this.mv.visitInsn(Opcodes.ICONST_1);
                this.mv.visitInsn(Opcodes.IADD);
                this.mv.visitVarInsn(Opcodes.ISTORE, var);
                super.visitVarInsn(opcode, var);

            } else {
                super.visitVarInsn(opcode, var);
            }
        } else if(opcode == Opcodes.DLOAD){
            final MutationIdentifier newId = this.context.registerMutation( this.factory, "UOI - Incremented double variable");

            if (this.context.shouldMutate(newId)) {
                this.mv.visitVarInsn(Opcodes.DLOAD, var);
                this.mv.visitLdcInsn(new Double("1.0"));
                this.mv.visitInsn(Opcodes.DADD);
                this.mv.visitVarInsn(Opcodes.DSTORE, var);
                super.visitVarInsn(opcode, var);
            } else {
                super.visitVarInsn(opcode, var);
            }
        }
        else {
            super.visitVarInsn(opcode, var);
        }
    }

    @Override
    public void visitIincInsn(final int var, final int increment) {
        final MutationIdentifier newIde = this.context.registerMutation(this.factory, "UOI - Removed unary operator");

        if (this.context.shouldMutate(newIde)) {
            this.mv.visitIincInsn(var, 0);
        } else {
            this.mv.visitIincInsn(var, increment);
        }
    }

}