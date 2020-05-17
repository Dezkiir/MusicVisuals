package example;

import ie.tudublin.*;

public class MyVisual extends Visual
{    
    Tunnel TL;
    MyVisual MV;

    public void settings()
    {
        size(1400, 800);
        
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        startMinim();
                
        // Call loadAudio to load an audio file to process 
        loadAudio("heroplanet.mp3");        
        
        // Call this instead to read audio from the microphone
        //startListening(); 
        
        TL = new WaveForm(this);
        MV = new AudioBandsVisual(this);
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            ap.stop();
            ap.trigger();
        }
    }

    public void draw()
    {
        //background(0);
        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();        
        MV.render();
        TL.render();
    }
}