package automata.lexical;

import automata.lexical.model.Symbol;
import org.junit.jupiter.api.*;

/**
 * Test class for {@link LexicalAutomata} instance.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LexicalAutomataStateTest {

    /**
     * Automata for recon an integer value.
     */
    private final static LexicalAutomata AUTOMATA_1 = new LexicalAutomata("INTEGER");

    /**
     * Automata for recon a string value.
     */
    private final static LexicalAutomata AUTOMATA_2 = new LexicalAutomata("IDENTIFIER");


    /**
     * Automata for recon a string value.
     */
    private final static LexicalAutomata AUTOMATA_3 = new LexicalAutomata("STRING");

    /**
     * Test the method "extract()" of LexicalAutomata instance.
     */
    @DisplayName("METHOD : LEXICAL AUTOMATA (INTEGER) extract()")
    @Order(1)
    @Test
    public void extractInteger() {
        String VALUE_001 = "100";
        String VALUE_002 = "a1b";

        LexicalState A1S0 = new LexicalState(AUTOMATA_1, true, true, 0);

        A1S0.addTransition("\\d", A1S0);

        Assertions.assertTrue(AUTOMATA_1.addState(A1S0));
        Assertions.assertFalse(AUTOMATA_1.addState(A1S0));

        Symbol symbol = (AUTOMATA_1.extract(VALUE_001));
        Assertions.assertNotNull(symbol);

        Assertions.assertEquals("INTEGER", symbol.code);
        Assertions.assertEquals("100", symbol.symbol);
        Assertions.assertEquals(0, symbol.start);
        Assertions.assertEquals(2, symbol.end);
        Assertions.assertEquals(3, symbol.length);

        symbol = (AUTOMATA_1.extract(VALUE_002));
        Assertions.assertNull(symbol);

        symbol = (AUTOMATA_1.extract(VALUE_002, 1));
        Assertions.assertEquals("INTEGER", symbol.code);
        Assertions.assertEquals("1", symbol.symbol);
        Assertions.assertEquals(1, symbol.start);
        Assertions.assertEquals(1, symbol.end);
    }

    /**
     * Test the method "extract()" of LexicalAutomata instance.
     */
    @DisplayName("METHOD : LEXICAL AUTOMATA (IDENTIFIER) extract()")
    @Order(3)
    @Test
    public void extractIdentifier() {
        String VALUE_001 = "identifier";

        LexicalState A1S0 = new LexicalState(AUTOMATA_2, true, true, 0);
        LexicalState A1S1 = new LexicalState(AUTOMATA_2, false, true, 1);

        A1S0.addTransition("[a-zA-Z]", A1S1);
        A1S1.addTransition("[a-zA-Z0-9-_]", A1S1);

        Assertions.assertTrue(AUTOMATA_2.addState(A1S0));
        Assertions.assertTrue(AUTOMATA_2.addState(A1S1));

        Symbol symbol = (AUTOMATA_2.extract(VALUE_001));
        Assertions.assertNotNull(symbol);

        Assertions.assertEquals("IDENTIFIER", symbol.code);
        Assertions.assertEquals(VALUE_001, symbol.symbol);
        Assertions.assertEquals(0, symbol.start);
        Assertions.assertEquals(VALUE_001.length(), symbol.length);
        Assertions.assertEquals(VALUE_001.length() - 1, symbol.end);
    }

    @DisplayName("METHOD : LEXICAL AUTOMATA (STRING) extract()")
    @Order(3)
    @Test
    public void extractString() {
        String VALUE_001 = "\"Lorem Ipsum\"";
        String VALUE_002 = "\"Lorem Ipsum";

        LexicalState A1S0 = new LexicalState(AUTOMATA_3, true, false, 0);
        LexicalState A1S1 = new LexicalState(AUTOMATA_3, false, false, 1);
        LexicalState A1S2 = new LexicalState(AUTOMATA_3, false, true, 2);

        A1S0.addTransition("[\"]", A1S1);
        A1S1.addTransition("[^\"]", A1S1);
        A1S1.addTransition("[\"]", A1S2);

        Assertions.assertTrue(AUTOMATA_3.addState(A1S0));
        Assertions.assertTrue(AUTOMATA_3.addState(A1S1));
        Assertions.assertTrue(AUTOMATA_3.addState(A1S2));

        Symbol symbol = (AUTOMATA_3.extract(VALUE_001));

        Assertions.assertEquals("STRING", symbol.code);
        Assertions.assertEquals(VALUE_001, symbol.symbol);
        Assertions.assertEquals(0, symbol.start);
        Assertions.assertEquals(12, symbol.end);
        Assertions.assertEquals(13, symbol.length);

        symbol = (AUTOMATA_3.extract(VALUE_002));

        Assertions.assertNull(symbol);
    }

}
