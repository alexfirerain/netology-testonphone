import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class CallList {
    protected final TreeMap<Date, String> missedCalls;  // TODO: executedCalls, receivedCalls;
    protected Date virtualInternalTime;

    private final static long DEMO_TIME = (long) (39.9 * 365.25 * 24 * 60 * 60 * 1000);
    static long MAX_CALL_INTERVAL = 90_000_000L;
    private final SimpleDateFormat listDateFormat = new SimpleDateFormat("E d MMMM HH:mm");

    public CallList(long initialTime) {
        missedCalls = new TreeMap<>();
        virtualInternalTime = new Date(initialTime);
    }
    public CallList() {
        this(DEMO_TIME);
    }
    public CallList(boolean withDemoSequence) {
        this();
        if (withDemoSequence) generateDemoMissedCallsSequence();
    }

    void takeAMissedCall(String number) {
        missedCalls.put(new Date(virtualInternalTime.getTime()), number);
    }

    String[] giveMissedCalls(ContactBase processThrough) {
        String[] lines = new String[missedCalls.size()];
        Iterator<Map.Entry<Date, String>> it = missedCalls.entrySet().iterator();
        IntStream.iterate(0, i -> it.hasNext(), i -> i + 1).forEach(i -> {
            Map.Entry<Date, String> next = it.next();
            lines[i] = listDateFormat.format(next.getKey()) + " " +
                    processThrough.tryToGetNameFor(next.getValue());
        });
        return lines;
    }

    public int missedCallsCount() {
        return missedCalls.size();
    }

    void clear() {
        missedCalls.clear();
    }


    // функционал для имитации и тестирования
    void promoteVirtualTime(long period) {
        virtualInternalTime.setTime(virtualInternalTime.getTime() + period);
    }
    void promoteVirtualTime() {
        promoteVirtualTime((long) (Math.random() * MAX_CALL_INTERVAL));
    }
    public void generateAMissedCall(String number) {
        promoteVirtualTime();
        takeAMissedCall(number);
    }
    void generateDemoMissedCallsSequence() {
        String[] history = { "+7921-1001010", "+7951-8821316", "+7921-1002030", "+7921-1003090" };
        Arrays.stream(history).forEach(this::generateAMissedCall);
    }
}
