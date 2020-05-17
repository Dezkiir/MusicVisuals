package C18339746;

import processing.core.PImage;
import ddf.minim.analysis.*;
import ie.tudublin.Visual;
import ddf.minim.*;

public class MyVisual extends Visual {
	
	// Objects for Sound Visual
	Minim minim;
	AudioPlayer Music;
	AudioMetaData meta;
	BeatDetect beat;

	int r = 200;
	float rad = 70;
	PImage DB;

	// Configuring Screen Size and Background
	public void settings() {
		size(1350, 679);
		DB = loadImage("Pintmen.png");
	}


	public void setup() {
		// Creating Minin (An Audio Library) Object
		minim = new Minim(this);
		Music = minim.loadFile("Pintman.wav");
		// Getting the details of wav file
		meta = Music.getMetaData();
		beat = new BeatDetect();
		Music.loop();
		background(DB);
	}


	
	public void draw() {
		
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
		// Loop to draw lines based on amp and beat
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

		// Loop to draw dots and lines connecting the individual floating points
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
		
		//If moused pressed so time remaining
    	if (flag){
			showMeta();
		}
	}
		
	// Function to show details of the song (Remaining time)
	void showMeta() 
	{
		int time =  meta.length();
		// Meta Data of Remaining Time is within Circle
		textSize(50);
		textAlign(CENTER);
		fill(0);
		text( (int)(time/1000-millis()/1000)/60 + ":"+ (time/1000-millis()/1000)%60, -7, 21);	
	}
	
	boolean flag =false;

	public void mousePressed()
	{
		// If mouse is pressed, background disappears and you can see the meta data of remaining time
		if (dist(mouseX, mouseY, width/2, height/2)<150) flag =!flag;
	}

	public void keyPressed() 
	{
		if (key == 'e')
		{
			exit();
		}
	}
}
