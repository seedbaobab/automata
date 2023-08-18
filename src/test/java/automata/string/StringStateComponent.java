package automata.string;

import automata.core.statesofset.ISetOfStateFactory;
import automata.core.statesofset.SetOfStates;

import java.util.HashMap;

public class StringStateComponent extends StringState {
    /**
     * Initialise a new instance of {@link StringStateComponent} class.
     *
     * @param setOfStateFactory The set of states factory.
     * @param isInitial         Indicate if the state is initial.
     * @param isTerminal        Indicate if the state is terminal.
     * @param identifier        The state identifier.
     */
    public StringStateComponent(ISetOfStateFactory<String> setOfStateFactory, boolean isInitial, boolean isTerminal, int identifier) {
        super(setOfStateFactory, isInitial, isTerminal, identifier);
    }

    public final HashMap<String, SetOfStates<String>> transitions() {
        return this.transitions;
    }
}
