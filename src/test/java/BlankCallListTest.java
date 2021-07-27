import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void new_missedCall_adds() {
        callList.generateAMissedCall("+7-000");
        assertEquals(1, callList.missedCalls.size());
    }

    @Test
    void new_missedCall_adds_atTail() {
        callList.generateAMissedCall("+7-000");
        assertEquals("+7-000", callList.missedCalls.get(callList.missedCalls.lastKey()));
    }

    @Test
    void new_missedCall_gets_newDate() {
        long timeBefore = callList.virtualInternalTime.getTime();
        callList.generateAMissedCall("+7-000");
        assertTrue(timeBefore < callList.missedCalls.lastKey().getTime());
    }
    @Test
    void newDate_not_later_than_MAX() {
        long timeBefore = callList.virtualInternalTime.getTime();
        callList.generateAMissedCall("+7-000");
        long delta = callList.missedCalls.lastKey().getTime() - timeBefore;
        assertTrue(delta < CallList.MAX_CALL_INTERVAL);
    }



    @Test
    void demo_fills_up_with_4items() {
        callList.generateDemoMissedCallsSequence();
        assertEquals(4, callList.missedCallsCount());
    }
}