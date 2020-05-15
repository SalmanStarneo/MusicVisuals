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

    ArrayList <Starsky> StarLine = new ArrayList<Starsky>();
    
    public void settings()
    {
        size(1024,800);
    }   

    float w ;
    float h;
    float hoursRadiusY;
    float hoursRadiusX;
    
    public void setup()
    {   
        w = width*0.8f;
        h = height*0.5f;
        // int radius = min(width, height) / 10;
        hoursRadiusY = (height*0.50f);
        hoursRadiusX = (width *0.50f);
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


        Table tableday = loadTable("StarPostions.csv","header");
        for(TableRow row:tableday.rows())
        {
            Starsky s = new Starsky(row);
            StarLine.add(s);
        }
        
    }

    public void grass()
    {   
        int cheight=10+(int)h;
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
        
        for(int i = 0 ; i < 25;i+=5)
        {
            float skyY= map(i, 0, i, 0, h);
            // noStroke();
            fill(170+i, 255, 185-i);
            rect(0, skyY, width,skyY);
        }
          
    }

    public void SunAndMoon()
    {    
        calculateAverageAmplitude();
        float hPos = map(hour() + norm(minute(), 0, 60), 0, 24, 0, TWO_PI * 2) - HALF_PI;
        float sunSize = 200 + (getSmoothedAmplitude() * 300);
        noStroke();
        fill(20,255,255);
        ellipse((width/2)+cos(hPos)*hoursRadiusX, -(sin(hPos)*hoursRadiusY)-100, sunSize,sunSize);
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
    
    public void skyObjects(float movX)
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
        // Starsky s = new Starsky();
        for(Starsky s:StarLine)
        {
            stroke(255,0,255);
            point(s.getStarX(),s.getStarY());
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

         if(skyObj>width-15)
         {
            skyObj=15;
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

         int midnightCheck=12;
         int hourText=(hour()%12)+midnightCheck;

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

        if(hour()!=0)
        {
            midnightCheck=0;
        }
        else
        {
            midnightCheck=12;
        }
        text(hourText+":"+minute()+":"+second()+APM[clockCounter], clockX+(clockCenter-50), (clockY+clockCenter-30));
     }

    
    
   
    public void draw()
    {
        
        
        background(165,155,255);
        // sky();
        SunAndMoon();
        // sunRays();
        grass();
        flowers();
        skyObjects(skyObj);
        moveflyObj();
        clockFrame();
        
        calculateAverageAmplitude();
        
    }
}