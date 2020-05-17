package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import processing.data.Table;

import processing.data.TableRow;

public class ScreenSaver extends MyVisuals
{
    ArrayList <tuneCloud> cloudy = new ArrayList<tuneCloud>();

    ArrayList <Starsky> StarLine = new ArrayList<Starsky>();

    ArrayList <SongPlayList> tuneBox = new ArrayList<SongPlayList>();
    
    public void settings()
    {
        size(800,800);
    }   

    //Global objects & Variables 
    WaveForms wf;
    SongPlayList spl;
    AudioBandsVisualPrint abvp;
    FFT fft;
    float w ;
    float wHalf;
    float h;
    float hoursRadiusY;
    float hoursRadiusX;
    String musicFile; 
    int tuneOrder;
    public void setup()
    {   
        w = width*0.8f;
        wHalf = width*0.5f;
        h = height*0.5f;
        int elipseRadius = min(width, height) /2;
        hoursRadiusY = (elipseRadius*0.50f);
        hoursRadiusX = (elipseRadius *0.50f);
        tuneOrder = 0%12;
        musicFile="Morning Routine.mp3";
        colorMode(HSB);
        setFrameSize(200);
        setSampleRate(44100);
        setFrameSize(1024);
        fft = new FFT(1, getSampleRate());
        startMinim();
        loadAudio(musicFile);
        wf = new WaveForms(this);
        abv = new AudioBandsVisualPrint(this);
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

        Table tableSong = loadTable("SongsOfTheDay.csv","header");
        for(TableRow row:tableSong.rows())
        {
            SongPlayList spl = new SongPlayList(row);
            tuneBox.add(spl);
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
    //Add tuneBox keys control
    public void keyPressed()
    {
        if (key == ' ')
        {
            if((getAudioPlayer().isPlaying())==true)
            {
                getAudioPlayer().pause();
            }
            else
            {
                getAudioPlayer().play();
            }
            
        }
        if(keyCode == DOWN)
        {
            getAudioPlayer().pause();
            getAudioPlayer().cue(0);
        }
        //Left to go to start of the playList
        if(keyCode == RIGHT && tuneOrder==tuneBox.size()-1)
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().pause();
            tuneOrder=0;
            loadAudio(tuneBox.get(tuneOrder).getSongName());
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
           
        }
        else if(keyCode== RIGHT)
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().pause();
            tuneOrder++;
            loadAudio(tuneBox.get(tuneOrder).getSongName()); 
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            
        }
        if(keyCode == LEFT&& tuneOrder==0)
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().pause();
            tuneOrder=tuneBox.size()-1;
            loadAudio(tuneBox.get(tuneOrder).getSongName());
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
        else if(keyCode == LEFT)
        {
            getAudioPlayer().cue(0); 
            getAudioPlayer().pause();
            tuneOrder--;
            loadAudio(tuneBox.get(tuneOrder).getSongName());
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            
        }

    }

    public void sky()
    {
        float skyY;

        // int skyGrid=10;
        
        for(int gridCounter = 0 ; gridCounter < h;gridCounter++)
        {
           skyY = map(gridCounter , 0, 70, 0, h);
            noStroke();
            fill((150*getSmoothedAmplitude()+gridCounter*2)-gridCounter, (255*getSmoothedAmplitude()+gridCounter*2), (185));
            rect(0, skyY , width , 100);
            
        }
          
    }

    float elipseRX;
    float elipseRY;
    float ellipseRSize;
    public void SunAndMoon()
    {    
        float ellipseSize = 200 + (getSmoothedAmplitude() * 300);
        float hPos = map(hour() + norm(minute(), 0, 60), 0, 24, 0, TWO_PI * 2) - HALF_PI;
        float ellipseX = (wHalf+cos(hPos)*hoursRadiusX)+80;
        float ellipseY = (h+sin(hPos)*hoursRadiusY);
        noStroke();
        fill(20,255,255);
        ellipse(ellipseX, ellipseY, ellipseSize,ellipseSize);  
        elipseRX=ellipseX;
        elipseRY=ellipseY; 
        ellipseRSize=ellipseSize;
    
    }

    float skyObj=10;
    
    public void skyObjects(float movX)
    {
        float cloudO=(height/2.5f);
        float cloudE=((height/2.5f)-10);
        
        // for(tuneCloud c:cloudy)
        // {
        //      cloudO=cloudO+c.getTopCloud();
        //      cloudE=cloudE+c.getTopCloud();
        // }
        // int borderCloud=width-10;// float randMov = random(-2, 2);
        
        
        
        
        for(int No=0;No < width;No++)
        {
                int cloudH= (int)((cloudE+cloudO)*getSmoothedAmplitude())/2;
                float cloudX=lerp(1, (cloudH), getAudioBuffer().get(No));
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
       //Stars
        for(Starsky s:StarLine)
        {
            stroke(255,0,255);
            point(s.getStarX(),s.getStarY());
        }

    }


     public void moveflyObj()
     {
         skyObj++;

         if(skyObj>width-15)
         {
            skyObj=15;
         }

     }

     public void tunePlayer()
     {}

     public void tuneClock()
     {
         float wClk = w-(w*0.6f);
         float hClk = h+(h*0.25f);
         float clockX=map(200, 0, 200, 0, wClk);

         float clockY=map(200, 0, 200, 0, hClk);

         int clockCenter=1;

         int clockColor=2;

         int clockCounter=0;

         int midnightCheck=0;
         int hourText=(hour()%12)+midnightCheck;

         String APM[]={" AM"," PM"};

        for(int frameNo=1; frameNo<=2;frameNo++)
        {
            fill(15,255/frameNo+clockColor,70*clockColor);
            stroke(255,255,0);
            rect(clockX+clockCenter, clockY+(clockCenter/frameNo), (315+clockCenter)/frameNo, (210+clockCenter)/frameNo,5);
            clockCenter+=60;
            clockColor+=clockCenter;
            
        } 
        // for(int frameNo=1; frameNo<=2;frameNo++)
        // {
        //     fill(15,255/frameNo+clockColor,70*clockColor);
        //     stroke(255,255,0);
        //     rect(clockX+clockCenter, clockY+(clockCenter/frameNo), (305+clockCenter)/frameNo, (180+clockCenter)/frameNo,5);
        //     clockCenter+=60;
        //     clockColor+=clockCenter;
            
        // } 
        fill(255, 255, 0);
        textSize(20);
        strokeWeight(3);
        if(hour()>11||hour()==0)
        {
            clockCounter=1;
        }
        else
        {
            clockCounter=0;
        }

       while(hour()==0)
       {
         midnightCheck=12;
       }
        text(hourText+":"+minute()+":"+second()+APM[clockCounter], clockX+(clockCenter-50), (clockY+clockCenter-55));
        line(clockX+(clockCenter-50), (clockY+clockCenter-45), clockX+(clockCenter*2), (clockY+clockCenter-45));
        text(day()+"/"+month()+"/"+year(), clockX+(clockCenter-50), (clockY+clockCenter+35));
        textSize(13);
        text(tuneBox.get(tuneOrder).toString(), clockX+(clockCenter-55), (clockY+clockCenter-30));
        //clock mp3 player on/off 
        float buttonPlaceX =(clockX+(clockCenter))+clockCenter*1.3f;
        if(getAudioPlayer().isPlaying()==true)
        {
            text("ON !",buttonPlaceX-10, (clockY+clockCenter-5));
            fill(100, 255, 230);
            ellipse(buttonPlaceX, (clockY+clockCenter-45), 40, 40);
        }
        // else if(getAudioPlayer().isPlaying()==false)
        // {

        // }
        else
        {
         
            text("OFF",buttonPlaceX-10, (clockY+clockCenter-5));
            fill(0, 255, 230);
            ellipse(buttonPlaceX, (clockY+clockCenter-45), 40, 40);
        }
        
     }
   
    public void draw()
    {    
        background(165,155,255);
        sky();
        calculateAverageAmplitude();
       
        calculateFrequencyBands();
        wf.render(elipseRX,elipseRY,ellipseRSize);
        SunAndMoon();
        skyObjects(skyObj);
        moveflyObj();
        grass();
        flowers();
        tuneClock();
    }
}