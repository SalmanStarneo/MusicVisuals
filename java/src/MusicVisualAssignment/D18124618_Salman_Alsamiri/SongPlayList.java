package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import processing.data.TableRow;

public class SongPlayList
{
    ScreenSaver sS;

    private String songName;

    private String playtime;

    private String beatPerMin;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getBeatPerMin() {
        return beatPerMin;
    }

    public void setBeatPerMin(String beatPerMin) {
        this.beatPerMin = beatPerMin;
    }
    
    public SongPlayList(String songName, String playtime, String beatPerMin) {
        this.songName = songName;
        this.playtime = playtime;
        this.beatPerMin = beatPerMin;
    }

    public SongPlayList(TableRow tr) 
    {
        this(tr.getString("Songs"),tr.getString("playTime"),tr.getString("bpm"));
    }

    @Override
    public String toString() {
        return "Song: " + songName + "\nLength: " + playtime +"m:s"+"\nbeat/min: " +  beatPerMin+ "\n";
    }

    

}