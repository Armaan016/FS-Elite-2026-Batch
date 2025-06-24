package Day77.Streams;

// Given a list of EventAttendance (eventId, attendeeName, durationMinutes), 
// Your task is to consider only attendees who stayed ≥ 60 minutes.
// For each event, display the Event ID (ascending order), List of qualified 
// attendee names (alphabetically sorted) and Count of such attendees.

// Example 1
// ---------
// Sample Input:
// 4
// E101 John 90
// E101 Alice 55
// E101 Zara 75
// E102 Mark 120

// Sample Output:
// E101 [John, Zara] Count=2
// E102 [Mark] Count=1

// Example 2
// ---------
// Sample Input:
// 11
// E502 Carl 90
// E502 Dan 45
// E501 Ana 100
// E502 Evan 75
// E501 Beth 61
// E502 Fred 20
// E301 Ron 30
// E301 Tony 60
// E302 Lily 75
// E302 Kevin 50
// E301 Maya 90

// Sample Output:
// E301 [Maya, Tony] Count=2
// E302 [Lily] Count=1
// E501 [Ana, Beth] Count=2
// E502 [Carl, Evan] Count=2

import java.util.*;
import java.util.stream.*;

public class Streams1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        List<EventAttendance> eventAttendees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");

            eventAttendees.add(new EventAttendance(line[0], line[1], Integer.parseInt(line[2])));
        }

        stream(eventAttendees);
        sc.close();
    }

    private static void stream(List<EventAttendance> list) {
        Map<String, List<String>> map1 = list.stream().filter(l -> l.duration >= 60)
                .collect(Collectors.groupingBy(c -> c.eventId,
                        Collectors.mapping(c -> c.attendeeName, Collectors.toList())));

        map1.entrySet().stream().sorted((a, b) -> a.getKey().compareTo(b.getKey()))
                .forEach(e -> {
                    List<String> names = e.getValue();
                    names.sort((n1, n2) -> n1.compareTo(n2));
                    System.out.println(e.getKey() + " " + names + " Count=" + names.size());
                });
        // System.out.println(map2);
    }
}

class EventAttendance {
    String eventId;
    String attendeeName;
    int duration;

    public EventAttendance(String eventId, String attendeeName, int duration) {
        this.eventId = eventId;
        this.attendeeName = attendeeName;
        this.duration = duration;
    }
}