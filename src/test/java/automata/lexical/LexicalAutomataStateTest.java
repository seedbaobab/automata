package automata.lexical;

import automata.string.StringState;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LexicalAutomataStateTest {

    /**
     * Automata for recon an integer value.
     */
    private final static LexicalAutomata AUTOMATA_1 = new LexicalAutomata("INTEGER");

    /**
     * Automata for recon a string value.
     */
    private final static LexicalAutomata AUTOMATA_2 = new LexicalAutomata("String");

    /**
     * Test the method "extract()" of LexicalAutomata instance.
     */
    @DisplayName("METHOD : LEXICAL AUTOMATA extract()")
    @Order(1)
    @Test
    public void extract() {
        LexicalState A1S0 = new LexicalState(AUTOMATA_1, true, true, 0);

        A1S0.addTransition("\\d", A1S0);

        Assertions.assertTrue(AUTOMATA_1.addState(A1S0));
        Assertions.assertFalse(AUTOMATA_1.addState(A1S0));

        Assertions.assertNull(AUTOMATA_1.extract("a1"));
        Assertions.assertEquals("100", AUTOMATA_1.extract("100").symbol);
        Assertions.assertEquals("1", AUTOMATA_1.extract("1a").symbol);

        LexicalState A2S0 = new LexicalState(AUTOMATA_2, true, false, 0);
        LexicalState A2S1 = new LexicalState(AUTOMATA_2, false, false, 1);
        LexicalState A2S2 = new LexicalState(AUTOMATA_2, false, true, 2);

        A2S0.addTransition("\"", A2S1);
        A2S1.addTransition("[^\"]", A2S1);
        A2S1.addTransition("\"", A2S2);

        Assertions.assertTrue(AUTOMATA_2.addState(A2S0));
        Assertions.assertTrue(AUTOMATA_2.addState(A2S1));
        Assertions.assertTrue(AUTOMATA_2.addState(A2S2));

        Assertions.assertNull(AUTOMATA_1.extract("a1"));
        Assertions.assertEquals("100", AUTOMATA_1.extract("100").symbol);
        Assertions.assertEquals("1", AUTOMATA_1.extract("1a").symbol);
    }
}
