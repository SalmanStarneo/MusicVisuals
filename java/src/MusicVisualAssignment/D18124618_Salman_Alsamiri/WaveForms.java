package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForms
{
    screenSaver mv;
    float cx = 0;
    float cy = 0;

    public WaveForms(screenSaver mv)
    {
        this.mv = mv;
        cx = this.mv.width / 4;
        cy = mv.height / 4;
    }

    public void render(float X, float Y, float rSize)
    {
        // for(int i = 0 ; i <( mv.getAudioBuffer().size()) ; i ++ )
        // {
            mv.colorMode(PApplet.HSB);
            for(int j = 0 ; j <( mv.getAudioBuffer().size()) ; j ++)
            {
                mv.noFill();
                mv.stroke(
                    PApplet.map(j, 0, mv.getAudioBuffer().size(), 0, 255)
                    , 255
                    , 255
                );
                
                // mv.line(X, (j*0.25f), ,j*0.25f);
                mv.ellipse(X, Y , (rSize)+(rSize)* mv.getAudioBuffer().get((int)rSize), (rSize)+(rSize)* mv.getAudioBuffer().get((int)rSize));

                // mv.line(X, (j*0.25f), X + X * mv.getAudioBuffer().get(j),j*0.25f);
                mv.point((j), cx + cx * mv.getAudioBuffer().get(j));
                
            }
        // }
    }
}