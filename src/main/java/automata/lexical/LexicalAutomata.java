package automata.lexical;

import automata.core.statesofset.SetOfStates;
import automata.lexical.model.Symbol;
import automata.string.StringAutomata;

/**
 * Class automata for a recon lexical field.
 */
public class LexicalAutomata extends StringAutomata {

    /**
     * The lexical symbol code.
     */
    private final String code;

    /**
     * Initialize a new instance of {@link LexicalAutomata} class.
     * @param code The automata lexical symbol code.
     */
    public LexicalAutomata(String code) {
        super();
        this.code = code;
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
        if(successors == null) return this.hasTerminal();
        return successors.accept(value, index+1);
    }

    /**
     * Extract a symbol from a value.
      * @param value The value.
     * @return The {@link Symbol} extracted otherwise null.
     */
    public Symbol extract(String value) {
        return this.extract(value, 0);
    }


    /**
     * Extract a symbol from a value.
     * @param value The value.
     * @return The {@link Symbol} extracted otherwise null.
     */
    public Symbol extract(String value, int index) {
        if(!this.accept(value)) return null;
        String symbol = this.extract(value, index, null);
        if (symbol == null) return null;
        return new Symbol(this.code, symbol, index);
    }

    private String extract(String value, int index, String token) {
        if(value.length() == index) {
            if (this.hasTerminal()) return token;
            return null;
        }

        //  Get the character at the position.
        String character = Character.toString(value.charAt(index));
        // Get the character successors.
        LexicalAutomata successors = (LexicalAutomata) this.successors(character);
        // No successors: null
        if(successors == null) {
            if (this.hasTerminal()) return token;
            return null;
        }
        if (token == null) token = character;
        else token += character;

        return successors.extract(value, index+1, token);
    }

    @Override
    public SetOfStates<String> newSetOfState() {
        return new LexicalAutomata(this.code);
    }
}
