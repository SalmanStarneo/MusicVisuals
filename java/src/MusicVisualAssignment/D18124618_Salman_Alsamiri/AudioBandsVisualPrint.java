package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import processing.core.*;

// This is an example of a visual that uses the audio bands
public class AudioBandsVisualPrint {
    ScreenSaver sS;

    public AudioBandsVisualPrint(ScreenSaver sS)
    {
        this.sS = sS; 
    }

    public void render()
    {
        float gap = sS.width / (float) sS.getBands().length;
        sS.noStroke();
        for(int i = 0 ; i < sS.getBands().length ; i ++)
        {
            sS.fill(PApplet.map(i, 0, sS.getBands().length, 255, 0), 255, 255);
            sS.rect(i * gap, sS.height, gap,-sS.getSmoothedBands()[i] * 0.2f); 
        }
    }
}