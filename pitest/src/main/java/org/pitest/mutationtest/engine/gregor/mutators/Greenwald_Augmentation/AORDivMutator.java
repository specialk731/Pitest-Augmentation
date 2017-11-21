/*
 * Kevin Greenwald
 *
 * AOR Div Mutator -
 *    Mutator to replace an arithmetic operation with division
 */
package org.pitest.mutationtest.engine.gregor.mutators.Greenwald_Augmentation;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum AORDivMutator implements MethodMutatorFactory {

    AOR_DIV_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new AORDivMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AORDivMethodVisitor extends AbstractInsnMutator {

    AORDivMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo, final MutationContext context, final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

    static {
        //Integers
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IDIV, "AOR - Replaced Integer \"+\" with \"/\""));
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IDIV, "AOR - Replaced Integer \"-\" with \"/\""));
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV, "AOR - Replaced Integer \"*\" with \"/\""));
        //MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IDIV, "AOR - Replaced Integer \"/\" with \"/\""));
        MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IDIV, "AOR - Replaced Integer \"%\" with \"/\""));
        //Longs
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LDIV, "AOR - Replaced Long \"+\" with \"/\""));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LDIV, "AOR - Replaced Long \"-\" with \"/\""));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV, "AOR - Replaced Long \"*\" with \"/\""));
        //MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LDIV, "AOR - Replaced Long \"/\" with \"/\""));
        MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LDIV, "AOR - Replaced Long \"%\" with \"/\""));
        //Floats
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FDIV, "AOR - Replaced Float \"+\" with \"/\""));
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FDIV, "AOR - Replaced Float \"-\" with \"/\""));
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV, "AOR - Replaced Float \"*\" with \"/\""));
        //MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FDIV, "AOR - Replaced Float \"/\" with \"/\""));
        MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FDIV, "AOR - Replaced Float \"%\" with \"/\""));
        //Doubles
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DDIV, "AOR - Replaced Double \"+\" with \"/\""));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DDIV, "AOR - Replaced Double \"-\" with \"/\""));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV, "AOR - Replaced Double \"*\" with \"/\""));
        //MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DDIV, "AOR - Replaced Double \"/\" with \"/\""));
        MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DDIV, "AOR - Replaced Double \"%\" with \"/\""));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }
}