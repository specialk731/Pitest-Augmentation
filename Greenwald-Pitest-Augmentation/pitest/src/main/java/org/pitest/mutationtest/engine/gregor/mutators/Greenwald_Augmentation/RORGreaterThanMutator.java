/*
 * Kevin Greenwald
 *
 * ROR Greater Than Mutator -
 *    Mutator that replaces every relational operator with ">"
 */

package org.pitest.mutationtest.engine.gregor.mutators.Greenwald_Augmentation;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

import java.util.HashMap;
import java.util.Map;

public enum RORGreaterThanMutator implements MethodMutatorFactory {

    ROR_GREATER_THAN_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new RORGreaterThanMethodVisitor(this, context, methodVisitor);
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

class RORGreaterThanMethodVisitor extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

    static {
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLE, "ROR - Replaced \"IFEQ\" with \"IFLE\" (\"!=\" -> \">\")"));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLE, "ROR - Replaced \"IFNE\" with \"IFLE\" (\"==\" -> \">\")"));
        //MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFLE, "ROR - Replaced \"IFLE\" with \"IFLE\" (\">\" -> \">\")"));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLE, "ROR - Replaced \"IFGE\" with \"IFLE\" (\"<\" -> \">\")"));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, "ROR - Replaced \"IFGT\" with \"IFLE\" (\"<=\" -> \">\")"));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFLE, "ROR - Replaced \"IFLT\" with \"IFLE\" (\">=\" -> \">\")"));

        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPLE, "ROR - Replaced \"IF_ICMPEQ\" with \"IF_ICMPLE\" (\"==\" -> \">\")"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPLE, "ROR - Replaced \"IF_ICMPNE\" with \"IF_ICMPLE\" (\"==\" -> \">\")"));
        //MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPLE, "ROR - Replaced \"IF_ICMPLE\" with \"IF_ICMPLE\" (\">\" -> \">\")"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLE, "ROR - Replaced \"IF_ICMPGE\" with \"IF_ICMPLE\" (\"<\" -> \">\")"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLE, "ROR - Replaced \"IF_ICMPGT\" with \"IF_ICMPLE\" (\"<=\" -> \">\")"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPLE, "ROR - Replaced \"IF_ICMPLT\" with \"IF_ICMPLE\" (\">=\" -> \">\")"));
    }

    RORGreaterThanMethodVisitor(final MethodMutatorFactory factory, final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}