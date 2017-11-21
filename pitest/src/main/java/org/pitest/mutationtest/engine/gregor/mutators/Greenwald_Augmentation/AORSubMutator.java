/*
 * Kevin Greenwald
 *
 * AOR Sub Mutator -
 *    Mutator to replace an arithmetic operation with substitution
 */
package org.pitest.mutationtest.engine.gregor.mutators.Greenwald_Augmentation;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum AORSubMutator implements MethodMutatorFactory {

    AOR_SUB_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new AORSubMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AORSubMethodVisitor extends AbstractInsnMutator {

    AORSubMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo, final MutationContext context, final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

    static {
        //Integers
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB, "AOR - Replaced Integer \"+\" with \"-\""));
        //MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.ISUB, "AOR - Replaced Integer \"-\" with \"-\""));
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.ISUB, "AOR - Replaced Integer \"*\" with \"-\""));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.ISUB, "AOR - Replaced Integer \"/\" with \"-\""));
        MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.ISUB, "AOR - Replaced Integer \"%\" with \"-\""));
        //Longs
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB, "AOR - Replaced Long \"+\" with \"-\""));
        //MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LSUB, "AOR - Replaced Long \"-\" with \"-\""));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LSUB, "AOR - Replaced Long \"*\" with \"-\""));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LSUB, "AOR - Replaced Long \"/\" with \"-\""));
        MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LSUB, "AOR - Replaced Long \"%\" with \"-\""));
        //Floats
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB, "AOR - Replaced Float \"+\" with \"-\""));
        //MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FSUB, "AOR - Replaced Float \"-\" with \"-\""));
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FSUB, "AOR - Replaced Float \"*\" with \"-\""));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FSUB, "AOR - Replaced Float \"/\" with \"-\""));
        MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FSUB, "AOR - Replaced Float \"%\" with \"-\""));
        //Doubles
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB, "AOR - Replaced Double \"+\" with \"-\""));
        //MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DSUB, "AOR - Replaced Double \"-\" with \"-\""));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DSUB, "AOR - Replaced Double \"*\" with \"-\""));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DSUB, "AOR - Replaced Double \"/\" with \"-\""));
        MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DSUB, "AOR - Replaced Double \"%\" with \"-\""));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }
}