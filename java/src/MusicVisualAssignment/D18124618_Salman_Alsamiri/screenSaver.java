package MusicVisualAssignment.D18124618_Salman_Alsamiri;

import java.util.ArrayList;
import ddf.minim.analysis.FFT;
import processing.data.Table;

import processing.data.TableRow;

public class ScreenSaver extends MyVisuals
{
    ArrayList <TuneControls>controlButtons = new ArrayList<TuneControls>();

    ArrayList <Starsky> StarLine = new ArrayList<Starsky>();

    ArrayList <SongPlayList> tuneBox = new ArrayList<SongPlayList>();
    
    //Global objects & Variables 
    WaveForms wf;
    SongPlayList spl;
    FFT fft;
    float w ;
    float wHalf;
    float h;
    float hoursRadiusY;
    float hoursRadiusX;
    String musicFile; 
    int tuneOrder;
    int tunerKey;
    
    public void settings()
    {
        size(800,800);
    }   

    public void setup()
    {   
        w = width*0.8f;
        wHalf = width*0.5f;
        h = height*0.5f;
        int elipseRadius = min(width, height) /2;
        hoursRadiusY = (elipseRadius*0.50f);
        hoursRadiusX = (elipseRadius *0.50f);
        tuneOrder = hour()%12;
        musicFile="Morning Routine.mp3";
        tunerKey=0;
        colorMode(HSB);
        setSampleRate(44100);
        setFrameSize(1024);
        fft = new FFT(1, getSampleRate());
        startMinim();
        loadAudio(musicFile);
        wf = new WaveForms(this);
        calculateFrequencyBands();
        loadData();
        smooth();
    }

    public void loadData()
    {
        Table table = loadTable("controlButtons.csv","header");
        for(TableRow row:table.rows())
        {
            TuneControls c = new TuneControls(row);
            controlButtons.add(c);
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
        int hG=10+(int)h;
        noStroke();
        fill(110,255,150);
        rect(0, 700, width, h-30);
        for(int i=0; i<width; i++)
        {
            fill(110,255,50);
            float xg =  map(i, 0, 30, 0, width+10);
            float yg =  map(i, 0, h, h+350, hG+350);
            square(xg, yg-50, 20);
            square(xg, yg-25, 20);
            square(xg, yg, 20);
            square(xg, yg+25, 20);
        }
        
    }

    public void flowers()
    {
        
        int hflower = height-50;
        for(int i=0; i<width; i++)
        {
            float xt =  map(i, 0, 5, 30, wHalf);
            noStroke();
            fill(getAudioBuffer().get(i)*minute()*10+30,255,225);
            ellipse(xt, hflower, 35, 50);
            noStroke();
            ellipse(xt, hflower, 55, 30);
            stroke(110,255,0);
            fill(50,205,255);
            ellipse(xt, hflower, 20, 20);
        }
        
    }
    
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
        if(keyCode == UP)
        {
            getAudioPlayer().pause();
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
        if(keyCode == RIGHT && tuneOrder==tuneBox.size()-1)
        {
            getAudioPlayer().pause();
            getAudioPlayer().cue(0);
            tuneOrder=0;
            loadAudio(tuneBox.get(tuneOrder).getSongName());
            getAudioPlayer().play();
           
        }
        else if(keyCode== RIGHT)
        {
            getAudioPlayer().pause();
            getAudioPlayer().cue(0);
            tuneOrder++;
            loadAudio(tuneBox.get(tuneOrder).getSongName()); 
            getAudioPlayer().play();
            
        }
        if(keyCode == LEFT&& tuneOrder==0)
        {
            getAudioPlayer().pause();
            getAudioPlayer().cue(0);
            tuneOrder=tuneBox.size()-1;
            loadAudio(tuneBox.get(tuneOrder).getSongName());
            getAudioPlayer().play();
        }
        else if(keyCode == LEFT)
        {
            getAudioPlayer().pause();
            getAudioPlayer().cue(0);
            tuneOrder--;
            loadAudio(tuneBox.get(tuneOrder).getSongName());
            getAudioPlayer().play();
            
        }

    }

    public void mousePressed()
    {
        
        if((mouseX>=ctrlMousX[0]-15&&mouseX<=ctrlMousX[0]+15)&&(mouseY>=ctrlMousY[0]-15&&mouseY<=ctrlMousY[0]+15))
        { 
            getAudioPlayer().pause();
            getAudioPlayer().cue(0);
            if(tuneOrder==0)
            {
                tuneOrder=tuneBox.size()-1;
                loadAudio(tuneBox.get(tuneOrder).getSongName());
            }
            else
            {
                tuneOrder--;
                loadAudio(tuneBox.get(tuneOrder).getSongName());
            }
            getAudioPlayer().play();
        }
        if((mouseX>=ctrlMousX[1]-15&&mouseX<=ctrlMousX[1]+15)&&(mouseY>=ctrlMousY[1]-15&&mouseY<=ctrlMousY[1]+15))
        {
            getAudioPlayer().pause();
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
        if((mouseX>=ctrlMousX[2]-15&&mouseX<=ctrlMousX[2]+15)&&(mouseY>=ctrlMousY[2]-15&&mouseY<=ctrlMousY[2]+15))
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
        if((mouseX>=ctrlMousX[3]-15&&mouseX<=ctrlMousX[3]+15)&&(mouseY>=ctrlMousY[3]-15&&mouseY<=ctrlMousY[3]+15))
        {
            getAudioPlayer().pause();
            getAudioPlayer().cue(0);
            if(tuneOrder==tuneBox.size()-1)
            {
                tuneOrder=0;
                loadAudio(tuneBox.get(tuneOrder).getSongName());
            }
            else
            {
                tuneOrder++;
                loadAudio(tuneBox.get(tuneOrder).getSongName());
            }
            getAudioPlayer().play();
        }
        if((mouseX>=ctrlMousX[4]-15&&mouseX<=ctrlMousX[4]+15)&&(mouseY>=ctrlMousY[4]-15&&mouseY<=ctrlMousY[4]+15))
        {
            if((getAudioPlayer().isPlaying())==true)
            {
                getAudioPlayer().pause();
                getAudioPlayer().cue(0);
            }
        }
        

    }

    public void sky()
    {
        float skyY;

        
        
        for(int gridCounter = 0 ; gridCounter < h;gridCounter++)
        {
           skyY = map(gridCounter , 0, 70, 0, h);
            noStroke();
            fill((150*(getSmoothedBands()[gridCounter%10])+gridCounter*2)-gridCounter, (255*getSmoothedAmplitude()+gridCounter*2), (185));
            rect(0, skyY , width , 100);
            
        }
        for(Starsky s:StarLine)
        {
            stroke(255,0,255);
            point((s.getStarX()*getSmoothedAmplitude()+s.getStarX()),s.getStarY()*getSmoothedAmplitude()+s.getStarY());
            point((s.getStarX2()*getSmoothedAmplitude()+s.getStarX2()),s.getStarY2()*getSmoothedAmplitude()+s.getStarY2());

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
        if(hour()>4&&hour()<19)
        {
            fill(28,205,255);
        }
        else
        {
            fill(160,45,155);
        }
        
        ellipse(ellipseX, ellipseY, ellipseSize,ellipseSize);  
        elipseRX=ellipseX;
        elipseRY=ellipseY; 
        ellipseRSize=ellipseSize;
    
    }

    float skyObj=10;
    
    public void clouds(float movX)
    {
        float cloudO=(height/2.5f);
        float cloudE=((height/2.5f)-10);
        
        for(int No=0;No < width;No++)
        {
                int cloudH= (int)((cloudE+cloudO)*getSmoothedAmplitude())/2;
                float cloudX=lerp(1, (cloudH), getAudioBuffer().get(No));
                pushMatrix();
                float xc = map(No, 0,7, 0, width-30);
                noStroke();
                float i = movX+xc;
                fill(255, 0, 255); 
                translate(5, (cloudX+No)+(cloudX+No)*getAudioBuffer().get(No));               
                ellipse(i, cloudO, 45, 40);
                ellipse(i, cloudE, 30, 40);
                popMatrix();
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

     
    int[] ctrlMousY = new int[5];
     int []ctrlMousX=new int[5];
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
        else
        {
         
            text("OFF",buttonPlaceX-10, (clockY+clockCenter-5));
            fill(0, 255, 230);
            ellipse(buttonPlaceX, (clockY+clockCenter-45), 40, 40);
        }
        float controlButtonsY = clockY+clockCenter*1.55f;
        for(int clcki=0;clcki<5;clcki++)
        {
            TuneControls tc = controlButtons.get(clcki);
            float controlButtonsX=map(clcki, 0, 5, clockX+50, (clockCenter-50)+clockX*2);
            stroke(255,255,0);
            fill(200,0,255);
            ellipse(controlButtonsX, controlButtonsY, 40, 40);
            fill(200,255,0);
            textSize(18);
            text(tc.getbuttonValue(), controlButtonsX-6, controlButtonsY+5);
            ctrlMousX[clcki]=(int)controlButtonsX;
            ctrlMousY[clcki]=(int)controlButtonsY;
            // println("X:"+ctrlMousX[clcki]+" Y:"+ctrlMousY[clcki]);

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
        clouds(skyObj); 
        moveflyObj();
        grass();
        flowers();
        tuneClock();
        
        while(tunerKey==0)
        { 
            tunerKey=1;
            getAudioPlayer().cue(0);
            musicFile=tuneBox.get(tuneOrder).getSongName();
            loadAudio(musicFile); 
        }
    }
}