import com.jaysonh.dmx4artists.*;

import processing.core.PApplet;

public class FlashScene implements Scene {

    private LightManager mgr;
    private final double intensity;
    private final DMXParam flashParam;

    public FlashScene(final PApplet app, final LightManager mgr, final double intensity) {
        this.mgr = mgr;
        this.intensity = intensity;
        this.flashParam = new DMXParamOsc(app,
                (float) 5, // oscillate over 5 seconds
                0, // min dmx value
                (int) (255 * intensity), // max dmx value
                MoveBehaviour.OSC_LINEAR, // This is the type of movement, OSC_SINE means follow a sin function over
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
