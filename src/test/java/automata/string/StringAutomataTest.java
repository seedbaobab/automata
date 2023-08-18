package automata.string;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StringAutomataTest {
    /**
     * The n°1 automaton to test.
     */
    private final static StringAutomataComponent AUTOMATA_1 = new StringAutomataComponent();

    /**
     * The n°2 automaton to test.
     */
    private final static StringAutomataComponent AUTOMATA_2 = new StringAutomataComponent();

    /**
     * The n°three automata to test.
     */
    private final static StringAutomataComponent AUTOMATA_3 = new StringAutomataComponent();

    /**
     * The n°4 automaton to test.
     */
    private final static StringAutomataComponent AUTOMATA_4 = new StringAutomataComponent();


    /**
     * Test the method "addState()" of Automata instance.
     */
    @DisplayName("METHOD : AUTOMATA addState()")
    @Order(1)
    @Test
    public void addState() {
        StringState A1S0 = new StringState(new StringAutomataComponent(), true, false, 0);
        StringState A1S1 = new StringState(new StringAutomataComponent(), false, false, 1);
        StringState A1S2 = new StringState(new StringAutomataComponent(), false, true, 2);

        StringState A2S0 = new StringState(new StringAutomataComponent(), true, false, 0);
        StringState A2S1 = new StringState(new StringAutomataComponent(), false, true, 1);
        StringState A2S2 = new StringState(new StringAutomataComponent(), false, true, 2);

        StringState A3S0 = new StringState(new StringAutomataComponent(), true, false, 0);
        StringState A3S1 = new StringState(new StringAutomataComponent(), false, false, 1);
        StringState A3S2 = new StringState(new StringAutomataComponent(), false, false, 2);
        StringState A3S3 = new StringState(new StringAutomataComponent(), false, true, 3);

        StringState A4S0 = new StringState(new StringAutomataComponent(), true, false, 0);
        StringState A4S1 = new StringState(new StringAutomataComponent(), true, false, 1);
        StringState A4S2 = new StringState(new StringAutomataComponent(), true, true, 2);

        // States for automata n°1
        A1S0.addTransition("a", A1S1);
        A1S0.addTransition("a", A1S1);

        A1S1.addTransition("a", A1S1);
        A1S1.addTransition("c", A1S1);
        A1S1.addTransition("b", A1S2);

        Assertions.assertTrue(AUTOMATA_1.addState(A1S0));
        Assertions.assertTrue(AUTOMATA_1.addState(A1S1));
        Assertions.assertTrue(AUTOMATA_1.addState(A1S2));
        Assertions.assertFalse(AUTOMATA_1.addState(A1S0));

        Assertions.assertEquals(3, AUTOMATA_1.size());
        Assertions.assertEquals(1, AUTOMATA_1.initials().size());

        // States for automata n°2
        A2S0.addTransition("a", A2S1);
        A2S0.addTransition("b", A2S2);

        A2S1.addTransition("a", A2S2);
        A2S1.addTransition("b", A2S0);

        A2S2.addTransition("a", A2S0);
        A2S2.addTransition("b", A2S1);

        Assertions.assertTrue(AUTOMATA_2.addState(A2S0));
        Assertions.assertTrue(AUTOMATA_2.addState(A2S1));
        Assertions.assertTrue(AUTOMATA_2.addState(A2S2));
        Assertions.assertFalse(AUTOMATA_2.addState(A2S0));

        Assertions.assertEquals(3, AUTOMATA_2.size());
        Assertions.assertEquals(1, AUTOMATA_2.initials().size());

        // States for automata n°3
        A3S0.addTransition("a", A3S0);
        A3S0.addTransition("b", A3S0);
        A3S0.addTransition("a", A3S1);

        A3S1.addTransition("a", A3S2);
        A3S1.addTransition("b", A3S2);

        A3S2.addTransition("a", A3S3);

        Assertions.assertTrue(AUTOMATA_3.addState(A3S0));
        Assertions.assertTrue(AUTOMATA_3.addState(A3S1));
        Assertions.assertTrue(AUTOMATA_3.addState(A3S2));
        Assertions.assertTrue(AUTOMATA_3.addState(A3S3));
        Assertions.assertFalse(AUTOMATA_3.addState(A3S0));

        Assertions.assertEquals(4, AUTOMATA_3.size());
        Assertions.assertEquals(1, AUTOMATA_3.initials().size());

        // States for automata n°4
        A4S0.addTransition("a", A4S1);

        A4S1.addTransition("b", A4S1);
        A4S1.addTransition("b", A4S2);

        A4S2.addTransition("b", A4S2);
        A4S2.addTransition("a", A4S2);
        A4S2.addTransition("b", A4S1);

        Assertions.assertTrue(AUTOMATA_4.addState(A4S0));
        Assertions.assertTrue(AUTOMATA_4.addState(A4S1));
        Assertions.assertTrue(AUTOMATA_4.addState(A4S2));
        Assertions.assertFalse(AUTOMATA_4.addState(A4S0));

        Assertions.assertEquals(3, AUTOMATA_4.size());
        Assertions.assertEquals(3, AUTOMATA_4.initials().size());
    }

    /**
     * Test the method "isDeterministic()" of Automata instance.
     */
    @DisplayName("METHOD : AUTOMATA isDeterministic()")
    @Order(2)
    @Test
    public void isDeterministic() {
        Assertions.assertTrue(AUTOMATA_1.isDeterministic());
        Assertions.assertTrue(AUTOMATA_2.isDeterministic());
        Assertions.assertFalse(AUTOMATA_3.isDeterministic());
        Assertions.assertFalse(AUTOMATA_4.isDeterministic());
    }

    @DisplayName("METHOD : AUTOMATA hasTerminal()")
    @Order(3)
    @Test
    public void hasTerminal() {
        Assertions.assertTrue(AUTOMATA_1.hasTerminal());
        Assertions.assertTrue(AUTOMATA_2.hasTerminal());
        Assertions.assertTrue(AUTOMATA_3.hasTerminal());
        Assertions.assertTrue(AUTOMATA_4.hasTerminal());
    }

    @DisplayName("METHOD : AUTOMATA accept()")
    @Order(4)
    @Test
    public void accept() {
        Assertions.assertTrue(AUTOMATA_1.accept("aacb"));
        Assertions.assertFalse(AUTOMATA_1.accept("aaaa"));

        Assertions.assertTrue(AUTOMATA_2.accept("aaab"));
        Assertions.assertFalse(AUTOMATA_2.accept("aaabc"));

        Assertions.assertFalse(AUTOMATA_3.accept("babb"));

        Assertions.assertTrue(AUTOMATA_4.accept("abb"));
        Assertions.assertTrue(AUTOMATA_4.accept("aab"));
    }

    @DisplayName("METHOD : AUTOMATA alphabet()")
    @Order(5)
    @Test
    public void alphabet() {
        Assertions.assertEquals(3, AUTOMATA_1.alphabet().size());
        Assertions.assertEquals(2, AUTOMATA_2.alphabet().size());
        Assertions.assertEquals(2, AUTOMATA_3.alphabet().size());
        Assertions.assertEquals(2, AUTOMATA_4.alphabet().size());
    }


    @DisplayName("METHOD : AUTOMATA determine()")
    @Order(6)
    @Test
    public void determine() {
        StringAutomata AUTOMATA_1_1 = (StringAutomata) AUTOMATA_1.determine();
        StringAutomata AUTOMATA_2_1 = (StringAutomata) AUTOMATA_2.determine();
        StringAutomata AUTOMATA_3_1 = (StringAutomata) AUTOMATA_3.determine();
        StringAutomata AUTOMATA_4_1 = (StringAutomata) AUTOMATA_4.determine();

        Assertions.assertTrue(AUTOMATA_1_1.isDeterministic());
        Assertions.assertTrue(AUTOMATA_2_1.isDeterministic());
        Assertions.assertTrue(AUTOMATA_3_1.isDeterministic());
        Assertions.assertTrue(AUTOMATA_4_1.isDeterministic());

        Assertions.assertTrue(AUTOMATA_3_1.accept("baba"));
    }
}
