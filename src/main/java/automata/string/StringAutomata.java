package automata.string;

import automata.core.Automata;

import automata.core.state.IState;
import automata.core.statesofset.SetOfStates;

import java.util.Iterator;

/**
 * Automata for an automata that recognize String pattern.
 */
public class StringAutomata extends Automata<String> {

    /**
     * Initialize a new instance of {@link StringAutomata} class.
     */
    public StringAutomata() {
        super();
    }

    @Override
    public boolean accept(String value) {
        boolean result = false;
        Iterator<IState<String>> iterator = this.initials.iterator();
        String character = Character.toString(value.charAt(0));

        while(iterator.hasNext() && !result) {
            IState<String> state = iterator.next();
            SetOfStates<String> successors = state.successors(character);
            if(successors != null)
                result = successors.accept(value, 1);
        }
        return result;
    }

    @Override
    public Automata<String> newAutomata() {
        return new StringAutomata();
    }

    @Override
    public boolean accept(String value, int index) {
        if(value.length() == index)
            return this.hasTerminal();

        //  Get the character at the position.
        String character = Character.toString(value.charAt(index));
        // Get the character successors.
        SetOfStates<String> successors = this.successors(character);
        // No successors: false
        if(successors == null) return false;

        return successors.accept(value, index+1);
    }
}
