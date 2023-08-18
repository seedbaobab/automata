package automata.string;

import org.junit.jupiter.api.*;

/**
 * Unit test class for {@link StringAutomata} instance.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StringStateTest {

    /**
     * A state for test for automata 1.
     */
    private static StringStateComponent A1S0 = null;

    /**
     * A state for test for automata 1.
     */
    private static StringStateComponent A1S1 = null;

    /**
     * A state for test for automata 1.
     */
    private static StringStateComponent A1S2 = null;

    /**
     * A state for test for automata 2.
     */
    private static StringStateComponent A2S0 = null;

    /**
     * A state for test for automata 2.
     */
    private static StringStateComponent A2S1 = null;

    /**
     * A state for test for automata 2.
     */
    private static StringStateComponent A2S2 = null;

    /**
     * A state for test for automata 3.
     */
    private static StringStateComponent A3S0 = null;

    /**
     * A state for test for automata 3.
     */
    private static StringStateComponent A3S1 = null;

    /**
     * A state for test for automata 3.
     */
    private static StringStateComponent A3S2 = null;

    /**
     * A state for test for automata 3.
     */
    private static StringStateComponent A3S3 = null;

    /**
     * A state for test for automata 4.
     */
    private static StringStateComponent A4S0 = null;

    /**
     * A state for test for automata 4.
     */
    private static StringStateComponent A4S1 = null;

    /**
     * A state for test for automata 4.
     */
    private static StringStateComponent A4S2 = null;

    @BeforeAll
    public static void initialize() {
        A1S0 = new StringStateComponent(new StringAutomata(), true, false, 0);
        A1S1 = new StringStateComponent(new StringAutomata(), false, false, 1);
        A1S2 = new StringStateComponent(new StringAutomata(), false, true, 2);

        A2S0 = new StringStateComponent(new StringAutomata(), true, false, 0);
        A2S1 = new StringStateComponent(new StringAutomata(), false, true, 1);
        A2S2 = new StringStateComponent(new StringAutomata(), false, true, 2);

        A3S0 = new StringStateComponent(new StringAutomata(), true, false, 0);
        A3S1 = new StringStateComponent(new StringAutomata(), false, false, 1);
        A3S2 = new StringStateComponent(new StringAutomata(), false, false, 2);
        A3S3 = new StringStateComponent(new StringAutomata(), false, true, 3);

        A4S0 = new StringStateComponent(new StringAutomata(), true, false, 0);
        A4S1 = new StringStateComponent(new StringAutomata(), true, false, 1);
        A4S2 = new StringStateComponent(new StringAutomata(), false, true, 2);
    }

    @DisplayName("METHOD : STATE addTransition()")
    @Order(1)
    @Test
    public void addTransition() {
        A1S0.addTransition("a", A1S1);
        // Test to add the same transition to a state.
        A1S0.addTransition("a", A1S1);

        A1S1.addTransition("a", A1S1);
        A1S1.addTransition("c", A1S1);
        A1S1.addTransition("b", A1S2);

        Assertions.assertEquals(1, A1S0.transitions().size());
        Assertions.assertEquals(3, A1S1.transitions().size());
        Assertions.assertEquals(0, A1S2.transitions().size());

        A2S0.addTransition("a", A2S1);
        A2S0.addTransition("b", A2S2);

        A2S1.addTransition("a", A2S2);
        A2S1.addTransition("b", A2S0);

        A2S2.addTransition("a", A2S0);
        A2S2.addTransition("b", A2S1);

        Assertions.assertEquals(2, A2S0.transitions().size());
        Assertions.assertEquals(2, A2S1.transitions().size());
        Assertions.assertEquals(2, A2S2.transitions().size());

        A3S0.addTransition("a", A3S0);
        A3S0.addTransition("b", A3S0);
        A3S0.addTransition("a", A3S1);

        A3S1.addTransition("a", A3S2);
        A3S1.addTransition("b", A3S2);

        A3S2.addTransition("a", A3S3);

        Assertions.assertEquals(2, A3S0.transitions().size());
        Assertions.assertEquals(2, A3S1.transitions().size());
        Assertions.assertEquals(1, A3S2.transitions().size());
        Assertions.assertEquals(0, A3S3.transitions().size());

        // Automata 4
        A4S0.addTransition("a", A4S1);

        A4S1.addTransition("b", A4S1);
        A4S1.addTransition("b", A4S2);

        A4S2.addTransition("b", A4S2);
        A4S2.addTransition("a", A4S2);
        A4S2.addTransition("b", A4S1);

        Assertions.assertEquals(1, A4S0.transitions().size());
        Assertions.assertEquals(1, A4S1.transitions().size());
        Assertions.assertEquals(2, A4S2.transitions().size());
    }

    @DisplayName("METHOD : STATE successors()")
    @Order(2)
    @Test
    public void successors() {
        Assertions.assertNotNull(A1S0.successors("a"));
        Assertions.assertNull(A1S0.successors("b"));
        Assertions.assertNull(A1S0.successors("c"));

        Assertions.assertEquals(1, A1S0.successors().size());

        Assertions.assertNotNull(A1S1.successors("a"));
        Assertions.assertNotNull(A1S1.successors("b"));
        Assertions.assertNotNull(A1S1.successors("c"));

        Assertions.assertEquals(2, A1S1.successors().size());

        Assertions.assertNull(A1S2.successors("a"));
        Assertions.assertNull(A1S2.successors("b"));
        Assertions.assertNull(A1S2.successors("c"));
        Assertions.assertEquals(0, A1S2.successors().size());

        Assertions.assertNotNull(A2S0.successors("a"));
        Assertions.assertNotNull(A2S0.successors("b"));
        Assertions.assertNull(A2S0.successors("c"));

        Assertions.assertEquals(1, A2S0.successors("b").size());
        Assertions.assertEquals(1, A2S0.successors("a").size());
        Assertions.assertEquals(2, A2S0.successors().size());

        Assertions.assertNotNull(A2S1.successors("a"));
        Assertions.assertNotNull(A2S1.successors("b"));
        Assertions.assertNull(A2S1.successors("c"));

        Assertions.assertEquals(1, A2S1.successors("b").size());
        Assertions.assertEquals(1, A2S1.successors("a").size());
        Assertions.assertEquals(2, A2S1.successors().size());

        Assertions.assertNotNull(A2S2.successors("a"));
        Assertions.assertNotNull(A2S2.successors("b"));
        Assertions.assertNull(A2S2.successors("c"));

        Assertions.assertEquals(1, A2S1.successors("b").size());
        Assertions.assertEquals(1, A2S1.successors("a").size());
        Assertions.assertEquals(2, A2S1.successors().size());

        // Automata n°3 - State 0
        Assertions.assertNotNull(A3S0.successors("a"));
        Assertions.assertNotNull(A3S0.successors("b"));
        Assertions.assertNull(A3S0.successors("c"));

        Assertions.assertEquals(2, A3S0.successors("a").size());
        Assertions.assertEquals(1, A3S0.successors("b").size());
        Assertions.assertEquals(2, A3S0.successors().size());

        // Automata n°3 - State 1
        Assertions.assertNotNull(A3S1.successors("a"));
        Assertions.assertNotNull(A3S1.successors("b"));
        Assertions.assertNull(A3S1.successors("c"));

        Assertions.assertEquals(1, A3S1.successors("a").size());
        Assertions.assertEquals(1, A3S1.successors("b").size());
        Assertions.assertEquals(1, A3S1.successors().size());

        // Automata n°3 - State 2
        Assertions.assertNotNull(A3S2.successors("a"));
        Assertions.assertNull(A3S2.successors("b"));
        Assertions.assertNull(A3S2.successors("c"));

        Assertions.assertEquals(1, A3S2.successors("a").size());
        Assertions.assertEquals(1, A3S2.successors().size());

        // Automata n°3 - State 3
        Assertions.assertNull(A3S3.successors("a"));
        Assertions.assertNull(A3S3.successors("b"));
        Assertions.assertNull(A3S3.successors("c"));

        Assertions.assertEquals(0, A3S3.successors().size());

        // Automata n°4 - State 0
        Assertions.assertNotNull(A4S0.successors("a"));
        Assertions.assertNull(A4S0.successors("b"));
        Assertions.assertNull(A4S0.successors("c"));
        Assertions.assertNotNull(A4S0.successors());

        Assertions.assertEquals(1, A4S0.successors("a").size());
        Assertions.assertEquals(1, A4S0.successors().size());

        // Automata n°4 - State 1
        Assertions.assertNull(A4S1.successors("a"));
        Assertions.assertNotNull(A4S1.successors("b"));
        Assertions.assertNull(A4S1.successors("c"));
        Assertions.assertNotNull(A4S1.successors());

        Assertions.assertEquals(2, A4S1.successors("b").size());
        Assertions.assertEquals(2, A4S1.successors().size());

        // Automata n°4 - State 2
        Assertions.assertNotNull(A4S2.successors("a"));
        Assertions.assertNotNull(A4S2.successors("b"));
        Assertions.assertNull(A4S2.successors("c"));
        Assertions.assertNotNull(A4S2.successors());

        Assertions.assertEquals(1, A4S2.successors("a").size());
        Assertions.assertEquals(2, A4S2.successors("b").size());
        Assertions.assertEquals(2, A4S2.successors().size());
    }

    @DisplayName("METHOD : State.isDeterministic")
    @Order(3)
    @Test
    public void isDeterministic() {
        Assertions.assertTrue(A1S0.isDeterministic());
        Assertions.assertTrue(A2S0.isDeterministic());
        Assertions.assertFalse(A3S0.isDeterministic());
        Assertions.assertFalse(A4S0.isDeterministic());
    }

}
