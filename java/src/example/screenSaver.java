package example;

import javax.swing.border.Border;

import ie.tudublin.Visual;

public class screenSaver extends Visual
{
    public void settings()
    {
        size(1024,800);
    }   
    
    public void setup()
    {
        // skycolour=150;
        colorMode(HSB);
        // noCursor();
        
        setFrameSize(256);

        startMinim();
        loadAudio("heroplanet.mp3");
    }

    public void grass()
    {   
        noStroke();
        fill(110,255,150);
        rect(0, 700, width, height/2);
        
    }

    public void grassPrint()
    {
        int w=width;
        int h = height;
        int i=1;
        float xt =  map(25, 0, i, 0, w/2);
        float yt =  map(25, 0, i, 0, 600);
        for(i=1; i<width; i++)
        {
            stroke(110,255,20);
            fill(100,255,255);
            square((xt) ,(yt), 25);
        }
        
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            
        }
    }

    public void sky()
    {
        
        background(135,255,255);
    }

    public void sunDawn()
    {    
        calculateAverageAmplitude();
        float sunSize = 200 + (getAmplitude() * 300);
        stroke(35,255,255);
        fill(20,255,255);
        ellipse(width/4, height/4, sunSize,sunSize);
        
    
    }
// add get and set 
    public void sunRays()
    {    
        // calculateAverageAmplitude();
        // float sunbeam = 200 + (getAmplitude() * 300);
        // stroke(sunbeam,255,255);
        // fill(sunSize,255,255);
        // ellipse(width/4, height/4, sunSize,sunSize);
        // ellipse(width/4, height/4, sunbeam*2,sunbeam*2);
        // float circy;
        // float lerpedcircley;
        // float lerpedw = 0;
        // float circy = height / 2;
        // float lerpedcircley = circy;
        // float average = sum / ai.bufferSize();
		// float w  = average * 1000;
        // lerpedw = lerp(lerpedw, w, 0.1f);
        // float average = sum / ai.bufferSize();

		// float w  = average * 1000;
		// lerpedw = lerp(lerpedw, w, 0.1f);
        
    
    }
// turn this to cicle 
    public void clouds(int x)
    {
        float cloudO=(height/2);
        float cloudE=(height/2)-10;
        float No = (getAmplitude() * 300);
        int i;
        for(No < width)
        {
            
                noStroke();
                fill(255, 0, 255);
                ellipse(No, cloudO, 35, 30);
                ellipse(No, cloudE, 25, 30);
            
            // No++;
        }
    }

    // public void clockframe()
    // {}
    
   
    public void draw()
    {
        sky();
        sunDawn();
        // sunRays();
        grass();
        // grassPrint();
        for(int i=1; i<width ; i++)
        {
            clouds(i);
        }
    }
}