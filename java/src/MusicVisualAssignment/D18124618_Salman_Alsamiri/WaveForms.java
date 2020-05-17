package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForms
{
    ScreenSaver sS;
    float cx = 0;
    float cy = 0;

    public WaveForms(ScreenSaver mv)
    {
        this.sS = mv;
        cx = this.sS.width / 4;
        cy = mv.height / 4;
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
                
                // mv.line(X, (j*0.25f), ,j*0.25f);
                sS.ellipse(X, Y , (rSize)+(rSize)* sS.getAudioBuffer().get((int)rSize)/4, (rSize)+(rSize)* sS.getAudioBuffer().get((int)rSize)/4);

                // mv.line(X, (j*0.25f), X + X * mv.getAudioBuffer().get(j),j*0.25f);
                sS.point((j), cx + cx * sS.getAudioBuffer().get(j));

                sS.point(X, Y);
                
            }
        // }
    }
}