package automata.core.state;

import automata.core.statesofset.ISetOfStateFactory;
import automata.core.statesofset.SetOfStates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Automata state class.
 */
public class State<T> implements IState<T> {

    /**
     * The state identifier
     */
    private final int identifier;

    /**
     * A {@link SetOfStates} factory.
     */
    protected final ISetOfStateFactory<T> setOfStateFactory;

    /**
     * Indicate if the state is initial.
     */
    private final boolean isInitial;

    /**
     * Indicate if the state is terminal.
     */
    private final boolean isTerminal;

    /**
     * Indicate if the instance is calling a recursive method.
     */
    protected boolean isTreated;

    /**
     * The state's transitions.
     */
    protected final HashMap<T, SetOfStates<T>> transitions;

    /**
     * Initialise a new instance of {@link State} class.
     * @param setOfStateFactory The set of states factory.
     * @param isInitial         Indicate if the state is initial.
     * @param isTerminal        Indicate if the state is terminal.
     * @param identifier        The state identifier.
     */
    public State(ISetOfStateFactory<T> setOfStateFactory, boolean isInitial, boolean isTerminal, int identifier) {
        this.isTreated = false;
        this.isInitial = isInitial;
        this.isTerminal = isTerminal;
        this.identifier = identifier;
        this.transitions = new HashMap<>();
        this.setOfStateFactory = setOfStateFactory;
    }

    @Override
    public boolean isTreated() {
        return this.isTreated;
    }

    @Override
    public boolean isTerminal() {
        return this.isTerminal;
    }

    @Override
    public boolean isInitial() {
        return this.isInitial;
    }

    @Override
    public SetOfStates<T> successors(T value) {
        return this.transitions.get(value);
    }

    @Override
    public SetOfStates<T> successors() {
        this.isTreated = true;

        SetOfStates<T> result = this.setOfStateFactory.newSetOfState();
        for(T key : this.transitions.keySet())
            result.addAll(this.transitions.get(key));

        this.isTreated = false;
        return result;
    }

    @Override
    public void addTransition(T value, IState<T> destination) {
        if(!this.transitions.containsKey(value))
            this.transitions.put(value, this.setOfStateFactory.newSetOfState());
        this.transitions.get(value).add(destination);
    }

    @Override
    public boolean isDeterministic() {
        this.isTreated = true;

        boolean result = true;
        Iterator<T> keys = this.transitions.keySet().iterator();
        while (keys.hasNext() && result) {
            T key = keys.next();
            result = this.transitions.get(key).size() == 1;
            if (result) {
                Iterator<IState<T>> iterator = this.transitions.get(key).iterator();
                while (iterator.hasNext() && result) {
                    IState<T> state = iterator.next();
                    if(!state.isTreated())
                        result = state.isDeterministic();
                }
            }
        }

        this.isTreated = false;
        return result;
    }

    @Override
    public Set<T> alphabet() {
        this.isTreated = true;
        Set<T> result = new HashSet<>();
        for(T key : this.transitions.keySet()) {
            result.add(key);
            result.addAll(this.transitions.get(key).alphabet());
        }
        this.isTreated = false;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final State<T> other = (State<T>) obj;
        return (this.identifier == other.identifier);
    }
}
