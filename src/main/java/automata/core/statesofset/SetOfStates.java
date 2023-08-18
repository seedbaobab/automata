package automata.core.statesofset;

import automata.core.state.IState;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Class of a set of states.
 */
public abstract class SetOfStates<T> extends HashSet<IState<T>> implements ISetOfStates<T>, ISetOfStateFactory<T> {

    /**
     * Initialize a new instance of {@link SetOfStates} class.
     */
    public SetOfStates() {
        super();
    }

    @Override
    public SetOfStates<T> successors(T value) {
        SetOfStates<T> result = null;
        Iterator<IState<T>> iterator = this.iterator();

        while(iterator.hasNext() && result == null)
            result = iterator.next().successors(value);

        return result;
    }

    @Override
    public boolean hasTerminal() {
        boolean result = false;
        Iterator<IState<T>> iterator = this.iterator();
        while(iterator.hasNext() && !result)
            result = iterator.next().isTerminal();
        return result;
    }

    @Override
    public boolean hasInitial() {
        boolean result = false;
        Iterator<IState<T>> iterator = this.iterator();
        while(iterator.hasNext() && !result)
            result = iterator.next().isInitial();
        return result;
    }

    @Override
    public Set<T> alphabet() {
        Set<T> result = new HashSet<>();
        for (IState<T> state : this)
            if (!state.isTreated())
                result.addAll(state.alphabet());
        return result;
    }

    @Override
    public abstract boolean accept(T value, int index);
}
