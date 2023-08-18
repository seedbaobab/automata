package automata.core.statesofset;

import java.util.Set;

/**
 * Contract interface for a set of states.
 */
public interface ISetOfStates<T> {

    /**
     * Get the set of state's successors corresponding to the value.
     * @param value The value of the state.
     * @return The set od states corresponding to the value.
     */
    ISetOfStates<T> successors(T value);

    /**
     * Indicate if one of the state in the set is terminal.
     * @return <c>true</c> if one of the state is terminal otherwise <c>false</c>.
     */
    boolean hasTerminal();

    /**
     * Indicate if one of the state in the set is initial.
     * @return <c>true</c> if one of the state is an initial otherwise <c>false</c>.
     */
    boolean hasInitial();

    /**
     * Get transitions value available from this instance.
     * @return The transition value available from this instance.
     */
    Set<T> alphabet();

    /**
     * Indicate of value at the index can be accepted by the set of states.
     * @param value The value to test.
     * @param index The index in the value.
     * @return <c>true</c> if the value can be accepted, otherwise false.
     */
    boolean accept(T value, int index);
}
