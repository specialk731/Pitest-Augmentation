/*
 * Copyright 2010 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest.engine.gregor.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import org.pitest.functional.prelude.Prelude;
import org.pitest.help.Help;
import org.pitest.help.PitHelpError;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.mutators.ArgumentPropagationMutator;
import org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator;
import org.pitest.mutationtest.engine.gregor.mutators.ConstructorCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.Greenwald_Augmentation.*;
import org.pitest.mutationtest.engine.gregor.mutators.IncrementsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.InlineConstantMutator;
import org.pitest.mutationtest.engine.gregor.mutators.InvertNegsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.MathMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NonVoidMethodCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RemoveConditionalMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RemoveConditionalMutator.Choice;
import org.pitest.mutationtest.engine.gregor.mutators.ReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.NakedReceiverMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.RemoveIncrementsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.RemoveSwitchMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.SwitchMutator;

public final class Mutator {

  private static final Map<String, Iterable<MethodMutatorFactory>> MUTATORS = new LinkedHashMap<String, Iterable<MethodMutatorFactory>>();

  static {

    //START Greenwald_Augmentation Mutators

    /*
     * Greenwald_Augmentation Mutator that adds negations of integer and floating
     * point numbers
     */
    add("ABS_Mutator", ABSMutator.ABS_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces AND with OR and OR with AND
     */
    add("OBBN_Mutator", OBBNMutator.OBBN_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that removes the first operator of +, -, *, /, and %
     * on int, long, float, and double
     */
    add("AOD_FIRST_Mutator", AODFirstMutator.AOD_FIRST_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that removes the first operator of +, -, *, /, and %
     * on int, long, float, and double
     */
    add("AOD_SECOND_Mutator", AODSecondMutator.AOD_SECOND_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Relational Operators with "=="
     */
    add("ROR_EQUAL_Mutator", ROREqualMutator.ROR_EQUAL_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Relational Operators with "!="
     */
    add("ROR_NOT_EQUAL_Mutator", RORNotEqualMutator.ROR_NOT_EQUAL_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Relational Operators with "<="
     */
    add("ROR_LESS_THAN_OR_EQUAL_Mutator", RORLessThanOrEqualMutator.ROR_LESS_THAN_OR_EQUAL_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Relational Operators with ">="
     */
    add("ROR_GREATER_THAN_OR_EQUAL_Mutator", RORGreaterThanOrEqualMutator.ROR_GREATER_THAN_OR_EQUAL_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Relational Operators with ">"
     */
    add("ROR_GREATER_THAN_Mutator", RORGreaterThanMutator.ROR_GREATER_THAN_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Relational Operators with "<"
     */
    add("ROR_LESS_THAN_Mutator", RORLessThanMutator.ROR_LESS_THAN_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Arithmetic Operations with "+"
     */
    add("AOR_ADD_Mutator", AORAddMutator.AOR_ADD_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Arithmetic Operations with "-"
     */
    add("AOR_SUB_Mutator", AORSubMutator.AOR_SUB_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Arithmetic Operations with "*"
     */
    add("AOR_MUL_Mutator", AORMulMutator.AOR_MUL_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Arithmetic Operations with "/"
     */
    add("AOR_DIV_Mutator", AORDivMutator.AOR_DIV_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces all Arithmetic Operations with "%"
     */
    add("AOR_MOD_Mutator", AORModMutator.AOR_MOD_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that replaces variables (a) with either
     * 1, 0, -a, a+1, or a-1,
     */
    add("CRCR_RANDOM_Mutator", new CRCRRandomMutator());

    /*
     * Greenwald_Augmentation Mutator that removes unary operators and adds ++ operators
     */
    add("UOI_PLUS_Mutator", UOIPlusMutator.UOI_PLUS_MUTATOR);

    /*
     * Greenwald_Augmentation Mutator that does NOT remove unary operators but DOES add -- operators
     */
    add("UOI_MINUS_Mutator", UOIMinusMutator.UOI_MINUS_MUTATOR);

    //START Default Mutators

    /*
     * Default mutator that inverts the negation of integer and floating point
     * numbers.
     */
    add("INVERT_NEGS", InvertNegsMutator.INVERT_NEGS_MUTATOR);

    /*
     * Default mutator that mutates the return values of methods.
     */
    add("RETURN_VALS", ReturnValsMutator.RETURN_VALS_MUTATOR);

    /*
     * Optional mutator that mutates integer and floating point inline
     * constants.
     */
    add("INLINE_CONSTS", new InlineConstantMutator());

    /*
     * Default mutator that mutates binary arithmetic operations.
     */
    add("MATH", MathMutator.MATH_MUTATOR);

    /*
     * Default mutator that removes method calls to void methods.
     *
     */
    add("VOID_METHOD_CALLS", VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR);

    /*
     * Default mutator that negates conditionals.
     */
    add("NEGATE_CONDITIONALS",
        NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR);

    /*
     * Default mutator that replaces the relational operators with their
     * boundary counterpart.
     */
    add("CONDITIONALS_BOUNDARY",
        ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR);

    /*
     * Default mutator that mutates increments, decrements and assignment
     * increments and decrements of local variables.
     */
    add("INCREMENTS", IncrementsMutator.INCREMENTS_MUTATOR);

    /*
     * Optional mutator that removes local variable increments.
     */

    add("REMOVE_INCREMENTS", RemoveIncrementsMutator.REMOVE_INCREMENTS_MUTATOR);

    /*
     * Optional mutator that removes method calls to non void methods.
     */
    add("NON_VOID_METHOD_CALLS",
        NonVoidMethodCallMutator.NON_VOID_METHOD_CALL_MUTATOR);

    /*
     * Optional mutator that replaces constructor calls with null values.
     */
    add("CONSTRUCTOR_CALLS", ConstructorCallMutator.CONSTRUCTOR_CALL_MUTATOR);

    /*
     * Removes conditional statements so that guarded statements always execute
     * The EQUAL version ignores LT,LE,GT,GE, which is the default behavior,
     * ORDER version mutates only those.
     */

    add("REMOVE_CONDITIONALS_EQ_IF", new RemoveConditionalMutator(Choice.EQUAL,
        true));
    add("REMOVE_CONDITIONALS_EQ_ELSE", new RemoveConditionalMutator(
        Choice.EQUAL, false));
    add("REMOVE_CONDITIONALS_ORD_IF", new RemoveConditionalMutator(
        Choice.ORDER, true));
    add("REMOVE_CONDITIONALS_ORD_ELSE", new RemoveConditionalMutator(
        Choice.ORDER, false));
    addGroup("REMOVE_CONDITIONALS", RemoveConditionalMutator.makeMutators());

    /*
     * Experimental mutator that removed assignments to member variables.
     */
    add("EXPERIMENTAL_MEMBER_VARIABLE",
        new org.pitest.mutationtest.engine.gregor.mutators.experimental.MemberVariableMutator());

    /*
     * Experimental mutator that swaps labels in switch statements
     */
    add("EXPERIMENTAL_SWITCH",
        new org.pitest.mutationtest.engine.gregor.mutators.experimental.SwitchMutator());

    /*
     * Experimental mutator that replaces method call with one of its parameters
     * of matching type
     */
    add("EXPERIMENTAL_ARGUMENT_PROPAGATION",
        ArgumentPropagationMutator.ARGUMENT_PROPAGATION_MUTATOR);

    /*
     * Experimental mutator that replaces method call with this
     */
    add("EXPERIMENTAL_NAKED_RECEIVER", NakedReceiverMutator.NAKED_RECEIVER);

    addGroup("REMOVE_SWITCH", RemoveSwitchMutator.makeMutators());
    addGroup("DEFAULTS", defaults());
    addGroup("STRONGER", stronger());
    addGroup("ALL", all());
  }

  public static Collection<MethodMutatorFactory> all() {
    return fromStrings(MUTATORS.keySet());
  }

  private static Collection<MethodMutatorFactory> stronger() {
    return combine(
        defaults(),
        group(new RemoveConditionalMutator(Choice.EQUAL, false),
            new SwitchMutator()));
  }

  private static Collection<MethodMutatorFactory> combine(
      Collection<MethodMutatorFactory> a, Collection<MethodMutatorFactory> b) {
    List<MethodMutatorFactory> l = new ArrayList<MethodMutatorFactory>(a);
    l.addAll(b);
    return l;
  }

  /**
   * Default set of mutators - designed to provide balance between strength and
   * performance
   */
  public static Collection<MethodMutatorFactory> defaults() {
    return group(InvertNegsMutator.INVERT_NEGS_MUTATOR,
        ReturnValsMutator.RETURN_VALS_MUTATOR, MathMutator.MATH_MUTATOR,
        VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR,
        NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR,
        ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR,
        IncrementsMutator.INCREMENTS_MUTATOR);
  }

  private static Collection<MethodMutatorFactory> group(
      final MethodMutatorFactory... ms) {
    return Arrays.asList(ms);
  }

  public static Collection<MethodMutatorFactory> byName(final String name) {
    return FCollection.map(MUTATORS.get(name),
        Prelude.id(MethodMutatorFactory.class));
  }

  private static void add(final String key, final MethodMutatorFactory value) {
    MUTATORS.put(key, Collections.singleton(value));
  }

  private static void addGroup(final String key,
      final Iterable<MethodMutatorFactory> value) {
    MUTATORS.put(key, value);
  }

  public static Collection<MethodMutatorFactory> fromStrings(
      final Collection<String> names) {
    final Set<MethodMutatorFactory> unique = new TreeSet<MethodMutatorFactory>(
        compareId());

    FCollection.flatMapTo(names, fromString(), unique);
    return unique;
  }

  private static Comparator<? super MethodMutatorFactory> compareId() {
    return new Comparator<MethodMutatorFactory>() {
      @Override
      public int compare(final MethodMutatorFactory o1,
          final MethodMutatorFactory o2) {
        return o1.getGloballyUniqueId().compareTo(o2.getGloballyUniqueId());
      }
    };
  }

  private static F<String, Iterable<MethodMutatorFactory>> fromString() {
    return new F<String, Iterable<MethodMutatorFactory>>() {
      @Override
      public Iterable<MethodMutatorFactory> apply(final String a) {
        Iterable<MethodMutatorFactory> i = MUTATORS.get(a);
        if (i == null) {
          throw new PitHelpError(Help.UNKNOWN_MUTATOR, a);
        }
        return i;
      }
    };
  }

}
