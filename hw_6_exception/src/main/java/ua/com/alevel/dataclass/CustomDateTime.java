package ua.com.alevel.dataclass;

import lombok.Data;

@Data
public class CustomDateTime implements Comparable<CustomDateTime> {
    private Calendar calendar;
    private Clock clock;

    public CustomDateTime(Calendar calendar) {
        this.calendar = calendar;
        this.clock = new Clock();
    }

    public CustomDateTime(Calendar calendar, Clock clock) {
        this.calendar = calendar;
        this.clock = clock;
    }

    public CustomDateTime() {
        this.calendar = new Calendar();
        this.clock = new Clock();
    }

    @Override
    public String toString() {
        return " " + calendar.toString() + " " + clock.toString();
    }

    @Override
    public int compareTo(CustomDateTime customDateTime) {
        if (this.calendar.compareTo(customDateTime.getCalendar()) == 1) return 1;
        else {
            if (this.calendar.compareTo(customDateTime.getCalendar()) == 0)
                return clock.compareTo(customDateTime.getClock());
            else return -1;
        }
    }
}
