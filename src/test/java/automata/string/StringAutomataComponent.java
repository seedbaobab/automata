package automata.string;

import automata.core.state.IState;

import java.util.Set;

public class StringAutomataComponent extends StringAutomata {

    public StringAutomataComponent() {
        super();
    }

    public Set<IState<String>> initials() {
        return this.initials;
    }
}
