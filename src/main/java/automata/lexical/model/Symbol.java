package automata.lexical.model;

/**
 * Model class for a symbol.
 */
public class Symbol {

    /**
     * The symbol.
     */
    public final String symbol;

    /**
     * The symbol code.
     */
    public final String code;

    /**
     * The symbole length.
     */
    public final int length;

    /**
     * The start position of the symbol.
     */
    public final int start;

    /**
     * The end position of the symbol.
     */
    public final int end;

    /**
     * Initialize a new instance of {@link Symbol} class.
     * @param code      The symbole code.
     * @param symbol    The symbole.
     * @param start     The symbole start position.
     */
    public Symbol(String code, String symbol, int start) {
        this.code = code;
        this.start = start;
        this.symbol = symbol;
        this.length = this.symbol.length();
        this.end = start + this.length - 1;
    }

}
