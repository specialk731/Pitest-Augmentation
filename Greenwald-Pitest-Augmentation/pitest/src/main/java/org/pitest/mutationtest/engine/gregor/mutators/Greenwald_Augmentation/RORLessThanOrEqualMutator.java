/*
 * Kevin Greenwald
 *
 * ROR Less Than Or Equal Mutator -
 *    Mutator that replaces every relational operator with "<="
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

public enum RORLessThanOrEqualMutator implements MethodMutatorFactory {

    ROR_LESS_THAN_OR_EQUAL_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new RORLessThanOrEqualMethodVisitor(this, context, methodVisitor);
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

class RORLessThanOrEqualMethodVisitor extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

    static {
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGT, "ROR - Replaced \"IFEQ\" with \"IFGT\" (\"!=\" -> \"<=\")"));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGT, "ROR - Replaced \"IFNE\" with \"IFGT\" (\"==\" -> \"<=\")"));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGT, "ROR - Replaced \"IFLE\" with \"IFGT\" (\">\" -> \"<=\")"));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFGT, "ROR - Replaced \"IFGE\" with \"IFGT\" (\"<\" -> \"<=\")"));
        //MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFGT, "ROR - Replaced \"IFGT\" with \"IFGT\" (\"<=\" -> \"<=\")"));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGT, "ROR - Replaced \"IFLT\" with \"IFGT\" (\">=\" -> \"<=\")"));

        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPGT, "ROR - Replaced \"IF_ICMPEQ\" with \"IF_ICMPGT\" (\"!=\" -> \"<=\")"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPGT, "ROR - Replaced \"IF_ICMPNE\" with \"IF_ICMPGT\" (\"==\" -> \"<=\")"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGT, "ROR - Replaced \"IF_ICMPLE\" with \"IF_ICMPGT\" (\">\" -> \"<=\")"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPGT, "ROR - Replaced \"IF_ICMPGE\" with \"IF_ICMPGT\" (\"<\" -> \"<=\")"));
        //MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPGT, "ROR - Replaced \"IF_ICMPGT\" with \"IF_ICMPGT\" (\"<=\" -> \"<=\")"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGT, "ROR - Replaced \"IF_ICMPLT\" with \"IF_ICMPGT\" (\">=\" -> \"<=\")"));
    }

    RORLessThanOrEqualMethodVisitor(final MethodMutatorFactory factory, final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}