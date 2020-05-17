package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import processing.data.TableRow;

public class TuneControls {

    private String buttonValue;
 

    public TuneControls(String buttonValue) 
    {
        this.buttonValue = buttonValue;
    }

    public TuneControls(TableRow tr) 
    {
        this(tr.getString("buttonValue"));
    }

    public String getbuttonValue() {
        return buttonValue;
    }

    public void setbuttonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    @Override
    public String toString() {
        return "TuneControls [buttonValue=" + buttonValue + "]";
    }

}