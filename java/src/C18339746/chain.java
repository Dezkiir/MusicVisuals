package C18339746;

import processing.core.PApplet;

public class chain extends PApplet {

	float offset = 0;
	
	public void settings() {
        size(500, 500);
	}
	
    public void loops2()
	{
		// To cast to float you can also say 2.0f or any Number.0f
		int numCircles  = 20;	
		
		float w = width / (float) numCircles;
		float radius = w / 2.0f;
		colorMode(HSB);
		float cGap = 255 / (float) (numCircles * numCircles);
		noStroke();

		for(int j = 0 ; j < numCircles ; j ++)
		{		
			for(int i = 0 ; i < numCircles ; i ++)
			{
				float x = radius + (i * w);
				float y = radius + (j * w);
				float c = (cGap * i * j + offset) % 255; 
				fill(c, 255, 255);
				ellipse(x, y, w, w);
			}
		}
		offset += mouseY / 250.0f;
    }
      
    public void setup() {

    }

    public void draw() {
		loops2();
    }
}