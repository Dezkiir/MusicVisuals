package C18339746;

import processing.core.PApplet;
import processing.core.PVector;

public class Tunnel extends PApplet {
    
    float zDist = 11;
    float zMin = -150;
    float zMax = 250;
    float zStep = 2.8f;
    float rad = 200;
    int nb = (int) ((zMax - zMin) / zDist);
    PVector[] circles = new PVector[nb];
    int[] colors = new int[nb];
    Boolean bnw = true, dots = false;

    public void settings() {
        size(600, 400, P3D);
    }
    
    public void setup() {
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
        if (!bnw)stroke(colors[i], a);
        else stroke(map(pv.z, zMin, zMax, 0, 255), a);
        float r = map(pv.z, zMin, zMax, (float) (rad * .1), rad);

        if (dots) {
        float jmax = r;
        for (int j  = 0; j < jmax; j++)
        {
            vertex(pv.x + r*cos(j*TWO_PI/jmax + fc/40)/2, pv.y + r*sin(j*TWO_PI/jmax + fc/40)/2, pv.z);
        }
        } else {
        pushMatrix();
        translate(pv.x, pv.y, pv.z);
        ellipse(0, 0, r, r);
        popMatrix();
        }

        if (pv.z > zMax) {
        circles[i].z = zMin;
        }
    }
    if (dots) endShape();
    }

    public void keyPressed(){
        dots = !dots;
    }

    public void mousePressed(){
        bnw = !bnw;
    }
}