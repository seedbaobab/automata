package automata.core;

import automata.core.state.DeterminingState;
import automata.core.state.IState;
import automata.core.statesofset.SetOfStates;

import java.util.*;

/**
 * Class defining an automata.
 * @param <T> The automata transition value type.
 */
public abstract class Automata<T> extends SetOfStates<T> {

    /**
     * List of initial state.
     */
    protected Set<IState<T>> initials;

    /**
     * Initialize a new instance of {@link Automata} class.
     */
    public Automata() {
        this.initials = new HashSet<>();
    }

    /**
     * Add a state.
     * @param state The state to add.
     */
    public boolean addState(IState<T> state) {
        boolean result = this.add(state);
        if(result && state.isInitial())
            this.initials.add(state);
        return result;
    }

    /**
     * Indicate if the automaton is deterministic and complete or not.
     * @return <c>true</c> if the automaton is deterministic and complete otherwise <c>false</c>.
     */
    public boolean isDeterministic() {
        if(this.initials.size() > 1) return false;
        Iterator<IState<T>> iterator = this.iterator();
        boolean result = true;
        while(iterator.hasNext() && result) {
            IState<T> state = iterator.next();
            if(!state.isTreated()) result = state.isDeterministic();
        }
        return result;
    }

    /**
     * Indicate of value can be accepted by the automata.
     * @param value The value to test.
     * @return <c>true</c> if the value can be accepted, otherwise false.
     */
    public abstract boolean accept(T value);

    /**
     * Create a new instance of the automata.
     * @return A new instance of the automata.
     */
    public abstract Automata<T> newAutomata();

    /**
     * Transform the finite automata to a deterministic automata.
     * @return The automata in its deterministic finite form.
     */
    public Automata<T> determine() {
        if(this.isDeterministic()) return this;

        Set<T> alphabet = this.alphabet();
        Automata<T> result = this.newAutomata();
        Stack<SetOfStates<T>> stack = new Stack<>();
        HashMap<SetOfStates<T>, IState<T>> map = new HashMap<>();

        SetOfStates<T> initialSet = this.newSetOfState();
        initialSet.addAll(this.initials);

        int id = 0;
        DeterminingState<T> initialState = new DeterminingState<>(this, id++, true, initialSet);

        map.put(initialSet, initialState);
        stack.push(initialSet);

        result.addState(initialState);
        stack.add(initialSet);

        while(!stack.isEmpty()) {
            SetOfStates<T> set = stack.pop();
            for(T letter : alphabet) {
                SetOfStates<T> successors = set.successors(letter);
                if(successors != null) {
                    if(!map.containsKey(successors)) {
                        stack.add(successors);
                        DeterminingState<T> state = new DeterminingState<>(this, id++, false,
                                successors);
                        map.put(successors, state);
                        result.addState(state);
                    }
                    map.get(set).addTransition(letter, map.get(successors));
                }
            }
        }

        return result;
    }

    @Override
    public SetOfStates<T> newSetOfState() {
        return this.newAutomata();
    }
}
