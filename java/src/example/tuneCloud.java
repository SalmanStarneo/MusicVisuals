package example;

import processing.data.TableRow;

public class tuneCloud {

    private int topCloud;
    private int lowCloud;

    public tuneCloud(int topCloud, int lowCloud) 
    {
        this.topCloud = topCloud;
        this.lowCloud = lowCloud;

    }

    public tuneCloud(TableRow tr) 
    {
        this(tr.getInt("topRow"),tr.getInt("lowRow"));
    }

    
	

    public int getTopCloud() {
        return topCloud;
    }

    public void setTopCloud(int topCloud) {
        this.topCloud = topCloud;
    }

    public int getLowCloud() {
        return lowCloud;
    }

    public void setLowCloud(int lowCloud) {
        this.lowCloud = lowCloud;
    }

}