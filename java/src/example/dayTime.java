package example;

import processing.data.TableRow;

public class dayTime {

    private String daySky;
    private int skyTime;

    public dayTime(int skyTime, String daySky) 
    {
        this.skyTime = skyTime;
        this.daySky = daySky;

    }

    public dayTime(TableRow tr) 
    {
        this(tr.getInt("Hour"),tr.getString("Time"));
    }

    public String getDaySky() {
        return daySky;
    }

    public void setDaySky(String daySky) {
        this.daySky = daySky;
    }

    public int getSkyTime() {
        return skyTime;
    }

    public void setSkyTime(int skyTime) {
        this.skyTime = skyTime;
    }

    public dayTime(String daySky) {
        this.daySky = daySky;
    }

    @Override
    public String toString() {
        return "dayTime [daySky=" + daySky + "]";
    }



}