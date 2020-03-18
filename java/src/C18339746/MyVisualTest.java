package C18339746;

import processing.core.PApplet;
import processing.core.PImage;
import ddf.minim.analysis.*;
import ddf.minim.*;

public class MyVisualTest extends PApplet
{	
	Minim minim;
	AudioPlayer Music;
	AudioMetaData meta;
	BeatDetect beat;
	int r = 200;
	float rad = 70;
	PImage DB;

	public void settings()
	{
		size(1559, 700);
		DB = loadImage("Silhouette.png");
	}

	public void setup() {
		minim = new Minim(this);
		Music = minim.loadFile("Day of Fate.wav");
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
