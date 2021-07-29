import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoCallListTest {
    CallList demoCallList;

    @BeforeEach
    void setUp() {
        demoCallList = new CallList(true);
    }

    @Test
    void filled_at_start() {
        assertEquals(CallList.HISTORY_EXAMPLE.length,
                demoCallList.giveMissedCalls(ContactBase.getBaseExample()).length);
    }

    @Test
    void right_demoNumbers_sequence() {
        String[] stringsOutput = demoCallList.giveMissedCalls(new ContactBase());
        for (int i = 0; i < CallList.HISTORY_EXAMPLE.length; i++)
            assertTrue(stringsOutput[i].endsWith(CallList.HISTORY_EXAMPLE[i]));
    }

    @Test
    void generating_adds_aCall() {
        int initialLoading = demoCallList.missedCallsCount();
        demoCallList.generateAMissedCall("+7-000");
        assertEquals(initialLoading + 1,
                demoCallList.missedCallsCount());
    }


    @Test
    void giveMissedCalls() {

    }

    @Test
    void really_clears() {
        demoCallList.clear();
        assertEquals(0, demoCallList.missedCallsCount());
    }

    @Test
    void promoteVirtualTime() {
    }

    @Test
    void testPromoteVirtualTime() {
    }

    @Test
    void generateAMissedCall() {
    }

    @Test
    void demo_adds_4calls() {
        demoCallList.generateDemoMissedCallsSequence();
        assertEquals(CallList.HISTORY_EXAMPLE.length * 2,
                demoCallList.missedCallsCount());
    }
}