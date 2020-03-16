package ie.tudublin;

import C18339746.CubeVisual;
import C18339746.MyVisual;
import C18339746.WaveForm;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new CubeVisual());
		
	}

	public void Wave()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new WaveForm());
		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.Wave();			
	}
}