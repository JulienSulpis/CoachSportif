package com.model.IAcore;

import java.util.ArrayList;

/**
 * Artificial Intelligence A Modern Approach (3rd Ed.): Figure 6.5, Page 215.<br>
 * <br>
 * <p/>
 * <pre>
 * <code>
 * function BACKTRACKING-SEARCH(csp) returns a solution, or failure
 *    return BACKTRACK({ }, csp)
 *
 * function BACKTRACK(assignment, csp) returns a solution, or failure
 *    if assignment is complete then return assignment
 *    var = SELECT-UNASSIGNED-VARIABLE(csp)
 *    for each value in ORDER-DOMAIN-VALUES(var, assignment, csp) do
 *       if value is consistent with assignment then
 *          add {var = value} to assignment
 *          inferences = INFERENCE(csp, var, value)
 *          if inferences != failure then
 *             add inferences to assignment
 *             result = BACKTRACK(assignment, csp)
 *             if result != failure then
 *                return result
 *          remove {var = value} and inferences from assignment
 *    return failure
 * </code>
 * </pre>
 * <p/>
 * Figure 6.5 A simple backtracking algorithm for constraint satisfaction
 * problems. The algorithm is modeled on the recursive depth-first search of
 * Chapter 3. By varying the functions SELECT-UNASSIGNED-VARIABLE and
 * ORDER-DOMAIN-VALUES, we can implement the general-purpose variableHeuristic discussed
 * in the text. The function INFERENCE can optionally be used to impose arc-,
 * path-, or k-consistency, as desired. If a value choice leads to failure
 * (noticed wither by INFERENCE or by BACKTRACK), then value assignments
 * (including those made by INFERENCE) are removed from the current assignment
 * and a new value is tried.
 *
 * @author Ruediger Lunde
 */
public class BacktrackingStrategy extends SolutionStrategy {

    private String variableHeuristic;
    private String valueHeuristic;

    public void setValueHeuristic(String valueHeuristic) {
        this.valueHeuristic = valueHeuristic;
    }

    public void setVariableHeuristic(String variableHeuristic) {
        this.variableHeuristic = variableHeuristic;
    }

    public Assignment solve(CSP csp) {
        return recursiveBackTrackingSearch(csp, new Assignment());
    }

    /**
     * Template method, which can be configured by overriding the three
     * primitive operations below.
     */
    private Assignment recursiveBackTrackingSearch(CSP csp,
                                                   Assignment assignment) {
        Assignment result = null;
        if (assignment.isComplete(csp.getVariables())) {
            result = assignment;
        } else {
            Variable var;
            if (this.variableHeuristic == "MRV")
                var = selectVariableWithMRV(assignment, csp);
            else if (this.variableHeuristic == "Degree")
                var = selectVariableDegree(assignment, csp);
            else
                var = selectUnassignedVariable(assignment, csp);

            if (this.valueHeuristic == "leastConstraining") {
                ArrayList<Object> testedValues = new ArrayList<Object>();

                for (int i = 1; i < csp.getDomain(var).size(); i++) {
                    Object value = leastConstrainingValue(var, assignment, csp);
                    if (!testedValues.contains(value)) {
                        testedValues.add(value);
                        assignment.setAssignment(var, value);
                        fireStateChanged(assignment, csp);
                        if (assignment.isConsistent(csp.getConstraints(var))) {
                            DomainRestoreInfo info = inference(var, assignment, csp);
                            if (!info.isEmpty())
                                fireStateChanged(csp);
                            if (!info.isEmptyDomainFound())
                                result = recursiveBackTrackingSearch(csp, assignment);
                            info.restoreDomains(csp);
                        }
                        assignment.removeAssignment(var);
                    }
                }
            } else {
                for (Object value : orderDomainValues(var, assignment, csp)) {
                    assignment.setAssignment(var, value);
                    fireStateChanged(assignment, csp);
                    if (assignment.isConsistent(csp.getConstraints(var))) {
                        DomainRestoreInfo info = inference(var, assignment, csp);
                        if (!info.isEmpty())
                            fireStateChanged(csp);
                        if (!info.isEmptyDomainFound()) {
                            result = recursiveBackTrackingSearch(csp, assignment);
                            if (result != null)
                                break;
                        }
                        info.restoreDomains(csp);
                    }
                    assignment.removeAssignment(var);
                }
            }

        }
        return result;
    }

    /**
     * Primitive operation, selecting a not yet assigned variable. This default
     * implementation just selects the first in the ordered list of variables
     * provided by the CSP.
     */
    protected Variable selectUnassignedVariable(Assignment assignment, CSP csp) {
        for (Variable var : csp.getVariables()) {
            if (!(assignment.hasAssignmentFor(var)))
                return var;
        }
        return null;
    }

    protected Variable selectVariableWithMRV(Assignment assignment, CSP csp) {
        Variable chosenVar = selectUnassignedVariable(assignment, csp);
        int remainingValues;
        // on récupère le nombre de valeurs légales pour la première variable non assignée :
        int MRV = csp.getDomain(chosenVar).size();

        for (Variable var : csp.getVariables()) {
            remainingValues = csp.getDomain(var).size();
            if (!(assignment.hasAssignmentFor(var)) && remainingValues < MRV) {
                MRV = remainingValues;
                chosenVar = var;
            }
        }
        return chosenVar;
    }

    protected Variable selectVariableDegree(Assignment assignment, CSP csp) {
        Variable chosenVar = selectUnassignedVariable(assignment, csp);
        int maxConstraintNumber = Integer.MIN_VALUE;
        int constraintNumber;

        /* Idée :
        * On parcourt les variables, on leur assigne une valeur, puis on compte le nombre de contraintes
        * sur toutes les autres variables encore non assignées.
         */
        for (Variable var : csp.getVariables()) {
            assignment.setAssignment(var, csp.getDomain(var).get(0));
            for (Variable unAssignedVar : csp.getVariables()) {
                if (!(assignment.hasAssignmentFor(unAssignedVar))) {
                    constraintNumber = csp.getConstraints(unAssignedVar).size();
                    if (constraintNumber > maxConstraintNumber) {
                        maxConstraintNumber = constraintNumber;
                        chosenVar = var;
                    }
                }
            }
            assignment.removeAssignment(var);
        }
        return chosenVar;
    }

    /**
     * Primitive operation, ordering the domain values of the specified
     * variable. This default implementation just takes the default order
     * provided by the CSP.
     */
    protected Iterable<?> orderDomainValues(Variable var,
                                            Assignment assignment, CSP csp) {
        return csp.getDomain(var);
    }

    protected Object leastConstrainingValue(Variable var,
                                            Assignment assignment, CSP csp) {
        int minRuledOutValues = Integer.MAX_VALUE;
        int ruledOutValues = 0;
        Object chosenValue = null;
        ArrayList<Variable> assignedVariables = new ArrayList<>();
        ArrayList<Variable> unAssignedVariables = new ArrayList<>();

        /* Idée :
        * Pour toutes les valeurs légales de var, pour chaque variable encore non assignée, on compte le nombre de valeurs exclues.
        * On choisit pour var la valeur qui exclue le moins de valeurs pour les autres variables.
        */
        for (Variable v : csp.getVariables()) {
            if (assignment.hasAssignmentFor(v))
                assignedVariables.add(v);
            else
                unAssignedVariables.add(v);
        }

        for (Object value : csp.getDomain(var)) {
            assignment.setAssignment(var, value);
            for (Variable unAssignedVar : csp.getVariables()) {
                for (Object availableValue : csp.getDomain(unAssignedVar)) {
                    assignment.setAssignment(unAssignedVar, availableValue);
                    for (Variable assignedVar : assignedVariables) {
                        if (!(assignment.isConsistent(csp.getConstraints(assignedVar))))
                            ruledOutValues += 1;
                    }
                }
                assignment.removeAssignment(unAssignedVar);
            }
            if (ruledOutValues < minRuledOutValues) {
                minRuledOutValues = ruledOutValues;
                chosenValue = value;
            }
            assignment.removeAssignment(var);
            ruledOutValues = 0;
        }
        return chosenValue;
    }

    /**
     * Primitive operation, which tries to prune out values from the CSP which
     * are not possible anymore when extending the given assignment to a
     * solution. This default implementation just leaves the original CSP as it
     * is.
     *
     * @return An object which provides informations about (1) whether changes
     * have been performed, (2) possibly inferred empty domains , and
     * (3) how to restore the domains.
     */
    protected DomainRestoreInfo inference(Variable var, Assignment assignment,
                                          CSP csp) {
        return new DomainRestoreInfo().compactify();
    }
}
