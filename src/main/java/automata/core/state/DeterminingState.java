package automata.core.state;


import automata.core.statesofset.ISetOfStateFactory;
import automata.core.statesofset.SetOfStates;

/**
 * State class for a determining state.
 * @param <T> The state type.
 */
public class DeterminingState<T> extends State<T> {

    /**
     * The state's set of states.
     */
    private final SetOfStates<T> set;

    /**
     * Initialise a new instance of {@link DeterminingState} class.
     *
     * @param setOfStateFactory The set of states factory.
     * @param isInitial         Indicate if the state is initial.
     * @param identifier        The state identifier.
     * @param set               The state set of state.
     */
    public DeterminingState(ISetOfStateFactory<T> setOfStateFactory, int identifier, boolean isInitial,
                            SetOfStates<T> set) {
        super(setOfStateFactory, isInitial, set.hasTerminal(), identifier);
        this.set = set;
    }

    @Override
    public SetOfStates<T> successors(T value) {
        return this.set.successors(value);
    }

    @Override
    public SetOfStates<T> successors() {
        this.isTreated = true;

        SetOfStates<T> result = this.set.newSetOfState();
        for(T key : this.transitions.keySet())
            result.addAll(this.transitions.get(key));

        this.isTreated = false;
        return result;
    }
}
