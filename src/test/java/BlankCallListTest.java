import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlankCallListTest {
    CallList callList;

    @BeforeEach
    void setUp() {
        callList = new CallList();
    }

    @Test
    void empty_at_start() {
        assertEquals(0,
                callList.missedCallsCount());
    }

    @Test
    void empty_list_at_start() {
        assertEquals(0,
                callList.giveMissedCalls(ContactBase.getBaseExample()).length);
    }

    @Test
    void promotion_changes_time() {
        long timeBefore = callList.virtualInternalTime.getTime();
        callList.promoteVirtualTime(1000L);
        long timeAfter = callList.virtualInternalTime.getTime();
        assertNotEquals(timeBefore, timeAfter);
    }

    @Test
    void time_correctly_promotes() {
        long timeBefore = callList.virtualInternalTime.getTime();
        callList.promoteVirtualTime(1000L);
        long timeAfter = callList.virtualInternalTime.getTime();
        assertEquals(timeBefore + 1000L, timeAfter);
    }

    @Test
    void taking_adds_aCall() {
        callList.takeAMissedCall("+7-000");
        assertEquals(1, callList.missedCallsCount());
    }

    @Test
    void generation_adds_aCall() {
        callList.generateAMissedCall("+7-000");
        assertEquals(1, callList.missedCallsCount());
    }

    @Test
    void generatedCall_adds_atTail() {
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
        assertTrue(delta <= CallList.MAX_CALL_INTERVAL + 1);
    }

    @Test
    void two_timePeriods_differ() {
        long timeBefore = callList.virtualInternalTime.getTime();
        callList.generateAMissedCall("+7-000");
        long delta1 = callList.missedCalls.lastKey().getTime() - timeBefore;
        callList.generateAMissedCall("+7-001");
        long delta2 = callList.missedCalls.lastKey().getTime() - timeBefore - delta1;
        assertTrue(delta1 != delta2);
    }




    @Test
    void demo_fills_up_with_History() {
        callList.generateDemoMissedCallsSequence();
        assertEquals(CallList.HISTORY_EXAMPLE.length,
                callList.missedCallsCount());
    }
}