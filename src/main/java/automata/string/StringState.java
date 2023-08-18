package automata.string;

import automata.core.state.State;
import automata.core.statesofset.ISetOfStateFactory;

/**
 * Class for a {@link String} state.
 */
public class StringState extends State<String> {

    /**
     * Initialise a new instance of {@link StringState} class.
     * @param setOfStateFactory The set of states factory.
     * @param isInitial         Indicate if the state is initial.
     * @param isTerminal        Indicate if the state is terminal.
     * @param identifier The state identifier.
     */
    public StringState(ISetOfStateFactory<String> setOfStateFactory, boolean isInitial, boolean isTerminal,
                          int identifier) {
        super(setOfStateFactory, isInitial, isTerminal, identifier);
    }

}
