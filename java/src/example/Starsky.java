package example;

import processing.data.TableRow;

public class Starsky {

    private int starY;
    private int starX;

    public Starsky(int starX, int starY) 
    {
        this.starX = starX;
        this.starY = starY;

    }

    public Starsky(TableRow tr) 
    {
        this(tr.getInt("XS"),tr.getInt("YS"));
    }

    public int getStarY() {
        return starY;
    }

    public void setStarY(int starY) {
        this.starY = starY;
    }

    public int getStarX() {
        return starX;
    }

    public void setStarX(int starX) {
        this.starX = starX;
    }

    @Override
    public String toString() {
        return "Starsky [starX=" + starX + ", starY=" + starY + "]";
    }



}