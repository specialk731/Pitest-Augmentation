/**
 *
 * ROR Greater Than Mutator:
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

    private static final String                     DESCRIPTION = "ROR - Replaced Relational Operator with \">=\"";
    private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

    static {
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGE, DESCRIPTION)); // Compare to 0: replace "==" with ">="
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGE, DESCRIPTION));   // Compare to 0: replace "!=" with ">="
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGE, DESCRIPTION));   // Compare to 0: replace "<=" with ">="
        //MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFGE, DESCRIPTION));   // Compare to 0: replace ">=" with ">="
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFGE, DESCRIPTION));   // Compare to 0: replace ">" with ">="
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGE, DESCRIPTION));   // Compare to 0: replace "<" with ">="

        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPGE, DESCRIPTION)); //Compare 2: replace "==" with ">="
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPGE, DESCRIPTION));   //Compare 2: replace "!=" with ">="
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGE, DESCRIPTION));   //Compare 2: replace "<=" with ">="
        //MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPGE, DESCRIPTION));   //Compare 2: replace ">=" with ">="
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPGE, DESCRIPTION));   //Compare 2: replace ">" with ">="
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGE, DESCRIPTION));   //Compare 2: replace "<" with ">="
    }

    RORGreaterThanOrEqualMethodVisitor(final MethodMutatorFactory factory, final MutationContext context, final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}