package automata.core.state;

import automata.core.statesofset.SetOfStates;

import java.util.Set;

public interface IState<T> {

    /**
     * Indicate if the instance is calling a recursive method.
     */
    boolean isTreated();

    /**
     * Indicate if the state is terminal.
     */
    boolean isTerminal();

    /**
     * Add a transition to the {@link IState}.
     * @param value The transition value.
     * @param destination The transition destination.
     */
    void addTransition(T value, IState<T> destination);

    /**
     * Get the set of states, successors of the value.
     * @param value The value to test.
     * @return An instance of {@link SetOfStates} containing the state successors of the value.
     */
    SetOfStates<T> successors(T value);

    /**
     * Get a set of states with all successors from this instance.
     * @return An instance of {@link SetOfStates} containing all states successors of this instance.
     */
    SetOfStates<T> successors();

    /**
     * Get transitions value available from this state.
     * @return The transition value available from this state.
     */
    Set<T> alphabet();

    /**
     * Indicate if the state is initial.
     */
    boolean isInitial();

    /**
     * Indicate if the state is deterministic and complete or not.
     * @return <c>true</c> if the state is deterministic otherwise <c>false</c>.
     */
    boolean isDeterministic();
}
