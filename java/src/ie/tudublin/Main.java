package ie.tudublin;

import MusicVisualAssignment.D18124618_Salman_Alsamiri.*;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new CubePrint());		
	}

	public void star()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new screenSaver());		
	}

	public void clock()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new clockTime());		
	}

	public void visualprint()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisuals());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.star();			
	}
}