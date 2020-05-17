package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import processing.data.TableRow;

public class Starsky {

    private int starY;
    private int starX;
    private int starX2;
    private int starY2;

    public Starsky(int starX, int starY,int starX2, int starY2) 
    {
        this.starX = starX;
        this.starY = starY;
        this.starX2 = starX2;
        this.starY2 = starY2;

    }

    public Starsky(TableRow tr) 
    {
        this(tr.getInt("XS"),tr.getInt("YS"),tr.getInt("XP"),tr.getInt("YP"));
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

    public int getStarX2() {
        return starX2;
    }

    public void setStarX2(int starX2) {
        this.starX2 = starX2;
    }

    public int getStarY2() {
        return starY2;
    }

    public void setStarY2(int starY2) {
        this.starY2 = starY2;
    }



}