package C18339746;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Lightning extends PApplet{

    ArrayList<Strike> s = new ArrayList<Strike>();

    public void setup() {
    }

    public void settings() {
        size(1080, 720);
    }

    public void draw() {
        background(150);
        for (int i = s.size() - 1; i >= 0; i--) {
            s.get(i).update(20);
            s.get(i).show();
            if (s.get(i).dead)
                s.remove(i);
        }
        if (s.size() < 5) {
            s.add(new Strike(new PVector(random(width), 50), 100, 0, 5));
        }
    }

    public void mouseReleased() {
        s.add(new Strike(new PVector(mouseX, mouseY), 100, 0, 5));
    }
}