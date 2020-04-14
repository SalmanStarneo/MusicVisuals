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
        setSampleRate(44100);
        setFrameSize(1024);

        startMinim();
        loadAudio("Aurora_Currents.wav");
        calculateFrequencyBands();
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
            fill(getSmoothedAmplitude()*i,255,255);
            ellipse(xt, h, 35, 50);
            noStroke();
            fill(getSmoothedAmplitude()*i,255,255);
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
        int colorgrid = -10;
        float skywidth=width-5;
        float skyX=map(1, 0, 1, 0, skywidth+5); 
        int skyhalf= height/2;
        for(int grid = 1; grid < height/2;grid++)
        {
            float skyY=map(grid, 0, grid, 0, height/2); 
            noStroke();
            fill(135,255,255-grid);
            rect(skyX, skyY, height/7, width);
          
            colorgrid*=grid;
        }
        
    }

    public void sunDawn()
    {    
        calculateAverageAmplitude();
        float sunSize = 200 + (getSmoothedAmplitude() * 300);
        noStroke();
        fill(20,255,255);
        ellipse(width/4, height/4, sunSize,sunSize);
        // rotate(30+second());
        
    
    }
// add get and set 
    public void sunRays()
    {    
        // calculateAverageAmplitude();
        
        float sunRayradius = (300*(250 + (getSmoothedAmplitude() * 250)))/255;
        // noFill();
        
        for(int i=0 ; i < width ;i++)
        {
            fill(sunRayradius,255,255);
            float rayR=sunRayradius/i;
            noFill();
            stroke(rayR*getAudioBuffer().get((int)sunRayradius), 200, 255);
            ellipse(width/4, height/4, rayR, rayR);
        }
        
    }
// turn this to cicle 

    float skyObj=10;
    
    public void clouds(float movX)
    {
        float cloudO=(height/2.5f);
        float cloudE=((height/2.5f)-10);
        int cloudH= (int)((cloudE+cloudO)*getSmoothedAmplitude());
        // for(tuneCloud c:cloudy)
        // {
        //      cloudO=cloudO+c.getTopCloud();
        //      cloudE=cloudE+c.getTopCloud();
        // }
        // int borderCloud=width-10;
        float cloudX=lerp(1, (cloudH), getSmoothedAmplitude());
        // float randMov = random(-2, 2);
        
        
        for(int No=0;No < width;No++)
        {
                // tuneCloud cNo = cloudy.get((int)No);
                pushMatrix();
                float xc = map(No, 0,7, 0, width-30);
                noStroke();
                float i = movX+xc;
                fill(255, 0, 255); 
                translate(5, (cloudX+No));               
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

     public void clockFrame()
     {
         float w = width*0.67f;
         float h = height*0.67f;
         float clockX=map(200, 0, 200, 0, w);

         float clockY=map(200, 0, 200, 0, h);

         int clockCenter=1;

         int clockColor=2;

         int clockCounter=0;

         String APM[]={" AM"," PM"};

        for(int frameNo=1; frameNo<=2;frameNo++)
        {
            fill(15,255/frameNo+clockColor,70*clockColor);
            stroke(255,255,0);
            rect(clockX+clockCenter, clockY+(clockCenter/frameNo), (305+clockCenter)/frameNo, (150+clockCenter)/frameNo,5);
            clockCenter+=60;
            clockColor+=clockCenter;
            
        } 
        fill(255, 255, 0);
        textSize(25);
        strokeWeight(3);
        if(hour()>12)
        {
            clockCounter=1;
        }
        else
        {
            clockCounter=0;
        }
        text(hour()%12+":"+minute()+":"+second()+APM[clockCounter], clockX+(clockCenter-50), (clockY+clockCenter-30));
     }

    
    
   
    public void draw()
    {
        background(170,100,255);
        // sky();
        sunDawn();
        sunRays();
        grass();
       flowers();
        clouds(skyObj);
        moveflyObj();
        clockFrame();
        calculateAverageAmplitude();
        
    }
}