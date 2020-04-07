package ie.tudublin;

import example.*;
// import example.CubeVisual1;
// import example.MyVisual;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new CubeVisual());		
	}

	public void star()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new screenSaver());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.star();			
	}
}