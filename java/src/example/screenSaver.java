package example;

// import java.io.Console;
import java.util.ArrayList;

// import javax.swing.border.Border;

import ie.tudublin.Visual;

import processing.data.Table;
import processing.data.TableRow;

public class screenSaver extends Visual
{
    ArrayList <tuneCloud> cloudy = new ArrayList<tuneCloud>();

    ArrayList <dayTime> Timeday = new ArrayList<dayTime>();
    
    public void settings()
    {
        size(1024,800);
    }   
    
    public void setup()
    {
        // skycolour=150;
        colorMode(HSB);
        // noCursor();
        reset();
        setFrameSize(256);

        startMinim();
        loadAudio("Aurora_Currents.wav");
        loadData();
        smooth();
    }

    public void loadData()
    {
        Table table = loadTable("clouds.csv","header");
        for(TableRow row:table.rows())
        {
            tuneCloud c = new tuneCloud(row);
            cloudy.add(c);
        }


        Table tableday = loadTable("TimeofDay.csv","header");
        for(TableRow row:tableday.rows())
        {
            dayTime d = new dayTime(row);
            Timeday.add(d);
        }
        
    }

    public void grass()
    {   
        int cheight=10+(height/2);
        noStroke();
        fill(110,255,150);
        rect(0, 700, width, cheight);
        
    }

    public void flowers()
    {
        int w=width-50;
        int h = height-50;
        for(int i=0; i<width; i++)
        {
            float xt =  map(i, 0, 5, 30, w);
            noStroke();
            fill(0,255,255);
            ellipse(xt, h, 35, 50);
            noStroke();
            fill(0,255,255);
            ellipse(xt, h, 55, 30);
            stroke(110,255,0);
            fill(50,205,255);
            ellipse(xt, h, 20, 20);
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
        float sunSize = 200 + (getSmoothedAmplitude() * 300);
        noStroke();
        fill(20,255,255);
        ellipse(width/4, height/4, sunSize,sunSize);
        
    
    }
// add get and set 
    public void sunRays()
    {    
        // calculateAverageAmplitude();
        
        float sunRayradius = 200*(210 + (getSmoothedAmplitude() * 300));
        // noFill();
        
        for(int i=0 ; i < width ;i++)
        {
            fill(sunRayradius,255,255);
            float rayR=10;
            noFill();
            stroke(getSmoothedAmplitude(), 200, 255);
            ellipse(width/4, height/4, rayR, rayR);
        }
        // line(x1, y1, x2, y2);
        
    }
// turn this to cicle 

    float skyObj=10;
    
    public void clouds(float movX)
    {
        
        float cloudO=(height/2.5f);
        float cloudE=(height/2.5f)-10;
        // int borderCloud=width-10;
        float cloudX=lerp(10, cloudO*ADD, getSmoothedAmplitude());
        // float cloudMov = 0;
        
        // translate(5, 0);
        for(int No=0;No < width;No++)
        {
                // tuneCloud cNo = cloudy.get((int)No);
                pushMatrix();
                float xc = map(No, 0,9, 25, width-30);
                noStroke();
                float i = movX+xc;
                fill(255, 0, 255); 
                translate(5, cloudX);               
                ellipse(i, cloudO, 35, 30);
                ellipse(i, cloudE, 25, 30);
                popMatrix();
        }
        
    }

     public void resetflyObj()
     {
        skyObj=10;
     }

     public void reset()
     {
         resetflyObj();
     }

     public void moveflyObj()
     {
         skyObj++;

         if(skyObj>width-10)
         {
            skyObj=10;
         }

     }
    
    
   
    public void draw()
    {
        sky();
        sunDawn();
        sunRays();
        grass();
       flowers();
        clouds(skyObj);
        moveflyObj();
        calculateAverageAmplitude();
        
    }
}