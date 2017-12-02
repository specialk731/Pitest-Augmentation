/*
 * Kevin Greenwald
 *
 * ROR Greater Than Or Equal Mutator -
 *    Mutator that replaces every relational operator with ">="
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

public enum RORGreaterThanOrEqualMutator implements MethodMutatorFactory {

    ROR_GREATER_THAN_OR_EQUAL_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new RORGreaterThanOrEqualMethodVisitor(this, context, methodVisitor);
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

class RORGreaterThanOrEqualMethodVisitor extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

    static {
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLT, "ROR - Replaced \"IFEQ\" with \"IFLT\" (\"!=\" -> \">=\")"));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLT, "ROR - Replaced \"IFNE\" with \"IFLT\" (\"==\" -> \">=\")"));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFLT, "ROR - Replaced \"IFLE\" with \"IFLT\" (\">\" -> \">=\")"));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLT, "ROR - Replaced \"IFGE\" with \"IFLT\" (\"<\" -> \">=\")"));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLT, "ROR - Replaced \"IFGT\" with \"IFLT\" (\"<=\" -> \">=\")"));
        //MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFLT, "ROR - Replaced \"IFLT\" with \"IFLT\" (\">=\" -> \">=\")"));

        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPLT, "ROR - Replaced \"IF_ICMPEQ\" with \"IF_ICMPLT\" (\"!=\" -> \">=\")"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPLT, "ROR - Replaced \"IF_ICMPNE\" with \"IF_ICMPLT\" (\"==\" -> \">=\")"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPLT, "ROR - Replaced \"IF_ICMPLE\" with \"IF_ICMPLT\" (\">\" -> \">=\")"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLT, "ROR - Replaced \"IF_ICMPGE\" with \"IF_ICMPLT\" (\"<\" -> \">=\")"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLT, "ROR - Replaced \"IF_ICMPGT\" with \"IF_ICMPLT\" (\"<=\" -> \">=\")"));
        //MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPLT, "ROR - Replaced \"IF_ICMPLT\" with \"IF_ICMPLT\" (\">=\" -> \">=\")"));
    }

    RORGreaterThanOrEqualMethodVisitor(final MethodMutatorFactory factory, final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}