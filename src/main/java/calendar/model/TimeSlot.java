package calendar.model;

import calendar.exception.WrongParameters;

public class TimeSlot implements Comparable<TimeSlot> {
    private Time start;
    private Time end;

    public TimeSlot(Time start, Time end) {
        this.start = start;
        this.end = end;
    }

    public TimeSlot(String start, String end) {
        this.start = new Time(start);
        this.end = new Time(end);
        //TODO: start should be less than end
    }

    public boolean overlap(TimeSlot other) {
        TimeSlot t1 = this;
        TimeSlot t2 = other;
        if(t1==null | t2==null) return false;
        if(t1.start.compareTo(t2.end)<=1 && t2.start.compareTo(t1.end)<=1)
            return true;
        return false;
    }

    @Override
    public int compareTo(TimeSlot o) {
        if (o == null) return 1;
        if(this.start.equals(o.start))
            return this.end.compareTo(o.end);
        return this.start.compareTo(o.start);
    }

    static class Time implements Comparable<Time> {
        int hour;
        int minutes;

        public Time(int hour, int minutes) {
            this.hour = hour;
            this.minutes = minutes;
        }

        public Time(String time) {
            String[] times = time.split(":");
            if(times.length != 2 )
                throw new WrongParameters("wrong end time");
            this.hour = Integer.parseInt(times[0]);
            this.minutes = Integer.parseInt(times[1]);

        }

        @Override
        public int compareTo(Time other) {
            if(other == null) return 1;
            if(this.hour>other.hour) return 1;
            if(this.hour<other.hour) return -1;
            return Integer.compare(this.minutes, other.minutes);
        }
    }
}
