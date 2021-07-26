import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlankCallListTest {
    CallList callList;

    @BeforeEach
    void setUp() {
        callList = new CallList();
    }

    @Test
    void takeAMissedCall() {
    }

    @Test
    void empty_list_at_start() {
        assertEquals(0, callList.giveMissedCalls(new ContactBase()).length);
    }

    @Test
    void empty_at_start() {
        assertEquals(0, callList.missedCallsCount());
    }

    @Test
    void time_really_promotes() {
        long timeBefore = callList.virtualInternalTime.getTime();
        callList.promoteVirtualTime(1000L);
        long timeAfter = callList.virtualInternalTime.getTime();
        assertEquals(timeBefore + 1000L, timeAfter);
    }

    @Test
    void generateAMissedCall() {

    }

    @Test
    void demo_fills_up_with_4items() {
        callList.generateDemoMissedCallsSequence();
        assertEquals(4, callList.missedCallsCount());
    }
}