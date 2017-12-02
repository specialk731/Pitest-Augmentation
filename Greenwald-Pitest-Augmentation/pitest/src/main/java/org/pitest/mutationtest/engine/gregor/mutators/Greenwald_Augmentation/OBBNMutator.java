/*
 * Kevin Greenwald
 *
 * OBBN Mutator -
 *    Mutator that switches AND with OR and OR with AND
 */
package org.pitest.mutationtest.engine.gregor.mutators.Greenwald_Augmentation;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum OBBNMutator implements MethodMutatorFactory {

    OBBN_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new OBBNMethodVisitor(this, methodInfo, context, methodVisitor);
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

class OBBNMethodVisitor extends AbstractInsnMutator {

    OBBNMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo, final MutationContext context, final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

    static {
        //Ints
        MUTATIONS.put(Opcodes.IOR, new InsnSubstitution(Opcodes.IAND,"OBBN - Replaced Integer OR with AND"));
        MUTATIONS.put(Opcodes.IAND, new InsnSubstitution(Opcodes.IOR,"OBBN - Replaced Integer AND with OR"));

        //Longs
        MUTATIONS.put(Opcodes.LOR, new InsnSubstitution(Opcodes.LAND,"OBBN - Replaced Long OR with AND"));
        MUTATIONS.put(Opcodes.LAND, new InsnSubstitution(Opcodes.LOR,"OBBN - Replaced Long AND with OR"));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }

}