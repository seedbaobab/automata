package automata.lexical;

import automata.core.statesofset.ISetOfStateFactory;
import automata.core.statesofset.SetOfStates;
import automata.string.StringState;

import java.util.Iterator;
import java.util.Stack;

public class LexicalState extends StringState {
    /**
     * Initialise a new instance of {@link LexicalState} class.
     *
     * @param setOfStateFactory The set of states factory.
     * @param isInitial         Indicate if the state is initial.
     * @param isTerminal        Indicate if the state is terminal.
     * @param identifier        The state identifier.
     */
    public LexicalState(ISetOfStateFactory<String> setOfStateFactory, boolean isInitial, boolean isTerminal, int identifier) {
        super(setOfStateFactory, isInitial, isTerminal, identifier);
    }

    @Override
    public SetOfStates<String> successors(String value) {
        boolean found = false;
        String key = null;
        Iterator<String> iterator = this.transitions.keySet().iterator();
        while (iterator.hasNext() && !found) {
            key = iterator.next();
            found = value.matches(key);
        }

        if(!found) return null;
        return this.transitions.get(key);
    }

}
