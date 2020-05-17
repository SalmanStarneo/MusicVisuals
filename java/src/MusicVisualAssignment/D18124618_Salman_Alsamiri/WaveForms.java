package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForms
{
    ScreenSaver sS;
    float cx = 0;
    float cy = 0;
    
    public WaveForms(ScreenSaver sS)
    {
        this.sS = sS;
        cx = sS.width / 4;
        cy = sS.height / 4;
    }
    
    public void render(float X, float Y, float rSize)
    {
        // for(int i = 0 ; i <( mv.getAudioBuffer().size()) ; i ++ )
        // {
            sS.colorMode(PApplet.HSB);
            for(int j = 0 ; j <( sS.getAudioBuffer().size()) ; j ++)
            {
                sS.noFill();
                sS.stroke(
                    PApplet.map(j, 0, sS.getAudioBuffer().size(), 0, 255)
                    , 255
                    , 255
                );
                
                sS.ellipse(X, Y , (rSize)+(rSize)* sS.getAudioBuffer().get((int)rSize)/4, (rSize)+(rSize)* sS.getAudioBuffer().get((int)rSize)/4);

                sS.point((j), cy + cy * sS.getAudioBuffer().get(j)/4);
                
            }
        // }
    }
}