package automata.core.statesofset;


/**
 * Contract interface for a set of states factory.
 */
public interface ISetOfStateFactory<T> {
    /**
     * Create a new instance of {@link ISetOfStateFactory} class.
     */
    SetOfStates<T> newSetOfState();
}
