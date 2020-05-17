// Happy with Test for finalisatioy5
package C18339746;

import processing.core.PImage;
import processing.core.PVector;
import ddf.minim.analysis.*;
import ie.tudublin.Visual;
import ddf.minim.*;

public class MyVisualTest extends Visual {

	//Variables for Sound Visual
	Minim minim;
	AudioPlayer Music;
	AudioMetaData meta;
	BeatDetect beat;
	int r = 200;
	float rad = 70;
	PImage DB;

	//Variables for Tunnel
	// Variable for Dist between circle
	float zDist = 10;
	// Minimum Value Circles can spawn
	float zMin = -150;
	// Maximum Value Circles can spawn
	float zMax = 250;
	float zStep = 2.8f;
	// Radius of Circles
	float Radius = 300;
	// Variable for Colour Calculation
	int nb = (int) ((zMax - zMin) / zDist);
	PVector[] circles = new PVector[nb];
	int[] colors = new int[nb];
	Boolean bnw = true, dots = false;

	public void settings() {
		size(1350, 679, P3D);
		DB = loadImage("Pintman.png");
	}

	public void setup() {
		minim = new Minim(this);
		Music = minim.loadFile("Pintman.wav");
		meta = Music.getMetaData();
		beat = new BeatDetect();
		Music.loop();
		background(DB);

		noFill();
        strokeWeight(2);
        colorMode(HSB);
        for (int i = 0; i < nb; i++) {
            circles[i] = new PVector(0, 0, map(i, 0, nb - 1, zMax, zMin));
            colors[i] = color(random(110, 255), 0, random(60, 150));
            colors[i] = color(random(220, 255), 255, 255);
        }
	}


	
	public void draw() {
        // Start of Visual
		beat.detect(Music.mix);
		image(DB,0,0);
		noStroke();
		rect(0, 0, width, height);
		translate(width/2, height/2);
		noFill();
		fill(-1, 10);
		if (beat.isOnset()) 
		{
			rad = rad*0.9f;
		}
		else 
		{
			rad = 70;
		}
		ellipse(0, 0, 2*rad, 2*rad);
		stroke(-1, 50);
		int bsize = Music.bufferSize();
		for (int i = 0; i < bsize - 1; i+=5)
		{
			float x = (r)*cos(i*2*PI/bsize);
			float y = (r)*sin(i*2*PI/bsize);
			float x2 = (r + Music.left.get(i)*100)*cos(i*2*PI/bsize);
			float y2 = (r + Music.left.get(i)*100)*sin(i*2*PI/bsize);
			stroke(random(0,255),random(0,255),random(0,255));
			line(x, y, x2, y2);
		}
	
		beginShape();
		noFill();
		stroke(-1, 50);

		for (int i = 0; i < bsize; i+=30)
		{
			float x2 = (r + Music.left.get(i)*100)*cos(i*2*PI/bsize);
			float y2 = (r + Music.left.get(i)*100)*sin(i*2*PI/bsize);

			vertex(x2, y2);
			pushStyle();
			stroke(-1);
			strokeWeight(12);
			point(x2, y2);
			popStyle();
		}
    	endShape();
    	if (flag){
			showMeta();
		}

		// Start of Tunnel
		background(20);
        translate(width/2, height/2);
        PVector pv;
        float fc = (float)frameCount, a;
        if (dots) beginShape(POINTS); 


        for (int i = 0; i < nb; i++) {
            pv = circles[i];
            pv.z += zStep;
            pv.x = (float) ((noise((fc * 2 + pv.z) / 550) - .5) * height * map(pv.z, zMin, zMax, 6, 0));
            pv.y = (float) ((noise((fc * 2 - 3000 - pv.z) / 550) - .5) * height * map(pv.z, zMin, zMax, 6, 0));

            a = map(pv.z, zMin, zMax, 0, 255);
            // If not in Black and White mode draw the colours
            if (!bnw)stroke(colors[i], a);
            else stroke(map(pv.z, zMin, zMax, 0, 255), a);
            float r = map(pv.z, zMin, zMax, (float) (Radius * .1), Radius);

            // If in Dot Mode
            if (dots) {
                float jmax = r;

                // Loop to draw dots at fixed intervals around the circle
                for (int j  = 0; j < jmax; j++)
                {
                    vertex(pv.x + r*cos(j*TWO_PI/jmax + fc/40)/2, pv.y + r*sin(j*TWO_PI/jmax + fc/40)/2, pv.z);
                }
            } 
            else {
                // Brings forward the circles as they spawn to give illusion of 3D
                pushMatrix();
                translate(pv.x, pv.y, pv.z);
                ellipse(0, 0, r, r);
                // Removes circles when they reach the centre
                popMatrix();
            }

            if (pv.z > zMax) {
                circles[i].z = zMin;
            }
        }
        if (dots) endShape();
	}
		
	void showMeta() 
	{
		int time =  meta.length();

		textSize(50);
		textAlign(CENTER);
		fill(0);
		text( (int)(time/1000-millis()/1000)/60 + ":"+ (time/1000-millis()/1000)%60, -7, 21);	
	}
	
	boolean flag =false;

	public void mousePressed()
	{
		bnw = !bnw;
	}

	public void keyPressed() 
	{
		dots = !dots;
		if (key == 'e')
		{
			exit();
		}
	}
}
