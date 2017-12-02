/*
 * Kevin Greenwald
 *
 * AOD First Mutator -
 *    Mutator to replace an arithmetic operation by the first of the two operands
 */

package org.pitest.mutationtest.engine.gregor.mutators.Greenwald_Augmentation;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum AODFirstMutator implements MethodMutatorFactory {

    AOD_FIRST_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new AODFirstMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AODFirstMethodVisitor extends AbstractInsnMutator {

    AODFirstMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo, final MutationContext context, final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

    static {
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from int add"));
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from long add"));

        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from float add"));
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from double add"));

        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from int sub"));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from long sub"));

        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from float sub"));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from double sub"));

        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from int mul"));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from long mul"));

        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from float mul"));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from double mul"));

        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from int div"));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from long div"));

        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from float div"));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from double div"));

        MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from int mod"));
        MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from long mod"));

        MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.POP, "AOD - Removed second operator from float mod"));
        MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.POP2, "AOD - Removed second operator from double mod"));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }
}
