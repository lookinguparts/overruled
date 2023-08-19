import com.jaysonh.dmx4artists.*;
import java.time.Duration;

import processing.core.PApplet;

public class StrobeScene implements Scene {

    private LightManager mgr;
    private final double intensity;
    private final DMXParam flashParam;

    public StrobeScene(final PApplet app, final LightManager mgr, final Duration period, final double intensity) {
        this.mgr = mgr;
        this.intensity = intensity;
        this.flashParam = new DMXParamOsc(app,
                (float) period.toSeconds(), // oscillate over 10 seconds
                0, // min dmx value
                (int) (255 * intensity), // max dmx value
                MoveBehaviour.OSC_SINE, // This is the type of movement, OSC_SINE means follow a sin function over
                                          // time
                0, // num times to repeat 0 = infinite repeat
                true); // autostart
    }

    @Override()
    public void setup() {
        this.mgr.setBrightness(this.intensity);
        for (final Light light : this.mgr.getLights()) {
            light.sendValue(Light.CHAN_BRIGHTNESS, this.flashParam);
        }

    }

    @Override()
    public void draw() {
        // noop, done by DMXParam
    }

    @Override()
    public void teardown() {
        this.mgr.off();
    }

}
