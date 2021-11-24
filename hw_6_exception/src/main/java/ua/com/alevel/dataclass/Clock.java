package ua.com.alevel.dataclass;

import lombok.Data;

import static ua.com.alevel.constant.Const.*;

@Data
public class Clock implements Comparable<Clock> {
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    public Clock(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = STARTING_SECOND;
        this.milliseconds = STARTING_MILLISECOND;
    }

    public Clock() {
        this.hours = STARTING_HOUR;
        this.minutes = STARTING_MINUTE;
        this.seconds = STARTING_SECOND;
        this.milliseconds = STARTING_MILLISECOND;
    }

    public Clock(int hours, int minutes, int seconds, int milliseconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = STARTING_MILLISECOND;
    }

    @Override
    public String toString() {
        return " " + hours + ":" + minutes + ":" + seconds + ":" + milliseconds;
    }


    @Override
    public int compareTo(Clock clock) {
        if (clock.getHours() == this.getHours()
                && clock.getMinutes() == this.getMinutes()
                && clock.getSeconds() == this.getSeconds()
                && clock.getMilliseconds() == this.getMilliseconds()) return 0;
        else {
            if (this.hours > clock.getHours()) return 1;
            else {
                if (this.hours == clock.getHours() && this.minutes > clock.getMinutes()) return 1;
                else {
                    if (this.hours == clock.getHours() && this.minutes == clock.getMinutes() && this.seconds > clock.getSeconds())
                        return 1;
                    else {
                        if (this.hours == clock.getHours() && this.minutes == clock.getMinutes() && this.seconds == clock.getSeconds() && this.milliseconds > clock.getMilliseconds())
                            return 1;
                        else return -1;
                    }
                }
            }
        }
    }
}
