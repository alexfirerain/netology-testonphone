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
    void takeAMissedCall() {
    }

    @Test
    void giveMissedCalls() {
    }

    @Test
    void missedCallsCount() {
    }

    @Test
    void clear() {
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
    void generateDemoMissedCallsSequence() {
    }
}