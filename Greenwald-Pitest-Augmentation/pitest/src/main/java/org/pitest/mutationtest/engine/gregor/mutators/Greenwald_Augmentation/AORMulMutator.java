/*
 * Kevin Greenwald
 *
 * AOR Mul Mutator -
 *    Mutator to replace an arithmetic operation with multiplication
 */
package org.pitest.mutationtest.engine.gregor.mutators.Greenwald_Augmentation;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum AORMulMutator implements MethodMutatorFactory {

    AOR_MUL_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new AORMulMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AORMulMethodVisitor extends AbstractInsnMutator {

    AORMulMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo, final MutationContext context, final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

    static {
        //Integers
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IMUL, "AOR - Replaced Integer \"+\" with \"*\""));
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IMUL, "AOR - Replaced Integer \"-\" with \"*\""));
        //MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IMUL, "AOR - Replaced Integer \"*\" with \"*\""));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL, "AOR - Replaced Integer \"/\" with \"*\""));
        MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL, "AOR - Replaced Integer \"%\" with \"*\""));
        //Longs
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LMUL, "AOR - Replaced Long \"+\" with \"*\""));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LMUL, "AOR - Replaced Long \"-\" with \"*\""));
        //MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LMUL, "AOR - Replaced Long \"*\" with \"*\""));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL, "AOR - Replaced Long \"/\" with \"*\""));
        MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL, "AOR - Replaced Long \"%\" with \"*\""));
        //Floats
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FMUL, "AOR - Replaced Float \"+\" with \"*\""));
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FMUL, "AOR - Replaced Float \"-\" with \"*\""));
        //MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FMUL, "AOR - Replaced Float \"*\" with \"*\""));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL, "AOR - Replaced Float \"/\" with \"*\""));
        MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL, "AOR - Replaced Float \"%\" with \"*\""));
        //Doubles
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DMUL, "AOR - Replaced Double \"+\" with \"*\""));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DMUL, "AOR - Replaced Double \"-\" with \"*\""));
        //MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DMUL, "AOR - Replaced Double \"*\" with \"*\""));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL, "AOR - Replaced Double \"/\" with \"*\""));
        MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DMUL, "AOR - Replaced Double \"%\" with \"*\""));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }
}