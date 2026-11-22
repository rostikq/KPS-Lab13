package io.github.rostikq.logger;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventLogger {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final List<String> logs = new ArrayList<>();

    public static void log(String source, String event) {
        String time = LocalTime.now().format(FMT);
        String entry = "[" + time + "] [" + source + "] " + event;
        logs.add(entry);
        System.out.println(entry);
    }

    public static List<String> getLogs() {
        return List.copyOf(logs);
    }
}
