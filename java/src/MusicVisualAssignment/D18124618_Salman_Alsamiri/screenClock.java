package MusicVisualAssignment.D18124618_Salman_Alsamiri;

// import ie.tudublin.Visual;

class screenClock
{
    private int hour;

    private int minute;

    private int second;  

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "screenClock [hour=" + hour + ", minute=" + minute + ", second=" + second + "]";
    }

}