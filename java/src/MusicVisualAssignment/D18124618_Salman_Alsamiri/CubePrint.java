package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import ie.tudublin.Visual;

public class CubePrint extends Visual
{
    boolean twocubes = false;

    public void settings()
    {
        size(1024, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        //fullScreen(P3D, SPAN);
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            
        }
        if (key == '1')
        {
            twocubes = ! twocubes;

        }
        if(keyCode == RIGHT)
        {
            skycolour = 10;
        }
        if(keyCode == LEFT)
        {
            skycolour = 110;
        }
    }

    int skycolour;

    public void setup()
    {
        skycolour=150;
        colorMode(HSB);
        noCursor();
        
        setFrameSize(256);

        startMinim();
        loadAudio("heroplanet.mp3");
        //getAp().play();
        //startListening(); 
        
    }

    float smoothedBoxSize = 0;

    public void grass()
    {
       
        rect(1-height, 1*-height, 100, 100);
    }

    public void CubeFloating()
    {
        calculateAverageAmplitude();
        background(200,255,255);
        noFill();
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        translate(0, 0, -250);
               
        float boxSize = 50 + (getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);        
        if (twocubes)
        {
            pushMatrix();
            translate(-100, 0, 0);
            rotateY(angle);
            rotateX(angle);
            box(smoothedBoxSize);
            //strokeWeight(1);
            //sphere(smoothedBoxSize);
            popMatrix();
            pushMatrix();
            translate(100, 0, 0);
            rotateY(angle);
            rotateX(angle);
            strokeWeight(5); 
            box(smoothedBoxSize);
            popMatrix();
        }
        else
        {
            rotateY(angle);
            rotateX(angle);
            //strokeWeight(1);
            //sphere(smoothedBoxSize/ 2);            
            strokeWeight(5);
            
            box(smoothedBoxSize);
        }
        angle += 0.01f;
    }
    float angle = 0;
    
    public void sphero()
    {
    //     background(0);
    //     noStroke();
    //     lights();
    //     fill(gray);
    //     translate(58, 48, 0);
        calculateAverageAmplitude();
        background(skycolour,255,255);
        // noFill();
        fill(20,255,255);
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        translate(0, 0, -250);
        float boxSize = 50 + (getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);        
        if (twocubes)
        {
            pushMatrix();
            translate(-100, 0, 0);
            rotateY(angle);
            rotateX(angle);
            sphere(smoothedBoxSize);
            //strokeWeight(1);
            //sphere(smoothedBoxSize);
            popMatrix();
            pushMatrix();
            translate(100, 0, 0);
            rotateY(angle);
            rotateX(angle);
            strokeWeight(5); 
            sphere(smoothedBoxSize);
            popMatrix();
        }
        else
        {
            rotateY(angle);
            rotateX(angle);
            //strokeWeight(1);
            //sphere(smoothedBoxSize/ 2);            
            strokeWeight(0);
            
            sphere(smoothedBoxSize);
        }
        angle += 0.01f;
    }

    public void draw()
    {
        sphero();
        grass();
    }
        
} 