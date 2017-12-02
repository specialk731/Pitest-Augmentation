/*
 * Kevin Greenwald
 *
 * ABS Mutator -
 *    Mutator that replaces a variable with its negation
 */
package org.pitest.mutationtest.engine.gregor.mutators.Greenwald_Augmentation;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum ABSMutator implements MethodMutatorFactory {

    ABS_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new ABSMethodVisitor(this, context, methodVisitor);
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

class ABSMethodVisitor extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext context;

    ABSMethodVisitor(final MethodMutatorFactory factory, final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM6, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }


    @Override
    public void visitVarInsn(final int opcode, final int var) {
        if (opcode == Opcodes.ILOAD) {
            final MutationIdentifier newId = this.context.registerMutation(this.factory, "ABS - Negated integer variable ");

            if (this.context.shouldMutate(newId)) {
                this.mv.visitIntInsn(Opcodes.ILOAD, var);
                //this.mv.visitIntInsn(Opcodes.BIPUSH, -1);
                this.mv.visitInsn(Opcodes.ICONST_M1);
                this.mv.visitInsn(Opcodes.IMUL);
                this.mv.visitVarInsn(Opcodes.ISTORE, var);
                super.visitVarInsn(opcode, var);

            } else {
                super.visitVarInsn(opcode, var);
            }
        } else if (opcode == Opcodes.DLOAD) {
            final MutationIdentifier newId = this.context.registerMutation(this.factory, "ABS - Negated double variable ");

            if (this.context.shouldMutate(newId)) {
                this.mv.visitVarInsn(Opcodes.DLOAD, var);
                this.mv.visitLdcInsn(new Double("-1.0"));
                this.mv.visitInsn(Opcodes.DMUL);
                this.mv.visitVarInsn(Opcodes.DSTORE, var);
                super.visitVarInsn(opcode, var);

            } else {
                super.visitVarInsn(opcode, var);
            }

        } else {
            super.visitVarInsn(opcode, var);
        }
    }
}