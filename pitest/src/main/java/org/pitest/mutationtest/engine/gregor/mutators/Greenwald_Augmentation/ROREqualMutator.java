/*
 * Kevin Greenwald
 *
 * ROR Equal Mutator -
 *    Mutator that replaces every relational operator with "=="
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

public enum ROREqualMutator implements MethodMutatorFactory {

    ROR_EQUAL_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new ROREqualMethodVisitor(this, context, methodVisitor);
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

class ROREqualMethodVisitor extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

    static {
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFNE, "ROR - Replaced \"IFEQ\" with \"IFNE\" (\"!=\" -> \"==\")"));
        //MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFNE, "ROR - Replaced \"IFNE\" with \"IFNE\" (\"==\" -> \"==\")"));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFNE, "ROR - Replaced \"IFLE\" with \"IFNE\" (\">\" -> \"==\")"));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFNE, "ROR - Replaced \"IFGE\" with \"IFNE\" (\"<\" -> \"==\")"));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFNE, "ROR - Replaced \"IFGT\" with \"IFNE\" (\"<=\" -> \"==\")"));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFNE, "ROR - Replaced \"IFLT\" with \"IFNE\" (\">=\" -> \"==\")"));

        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPNE, "ROR - Replaced \"IF_ICMPEQ\" with \"IF_ICMPNE\" (\"!=\" -> \"==\")"));
        //MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPNE, "ROR - Replaced \"IF_ICMPNE\" with \"IF_ICMPNE\" (\"==\" -> \"==\")"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPNE, "ROR - Replaced \"IF_ICMPLE\" with \"IF_ICMPNE\" (\">\" -> \"==\")"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPNE, "ROR - Replaced \"IF_ICMPGE\" with \"IF_ICMPNE\" (\"<\" -> \"==\")"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPNE, "ROR - Replaced \"IF_ICMPGT\" with \"IF_ICMPNE\" (\"<=\" -> \"==\")"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPNE, "ROR - Replaced \"IF_ICMPLT\" with \"IF_ICMPNE\" (\">=\" -> \"==\")"));
    }

    ROREqualMethodVisitor(final MethodMutatorFactory factory, final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}