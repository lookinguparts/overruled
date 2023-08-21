import java.time.Duration;

import processing.core.PApplet;

public class SineWaveScene implements Scene {

    private LightManager mgr;
    private final Duration period;
    private final float frequency;
    private final float amplitude;
    private final float min_brightness;
    private final float offset;

    public SineWaveScene(final PApplet app, final LightManager mgr, final Duration period) {
        this.mgr = mgr;
        this.period = period;

        // Higher value for faster oscillations
        this.frequency = 2;
        // This controls the height of the wave oscillations, affecting how
        // much the brightness of the lights will change.
        this.amplitude = 80;
        // Set the minimum brightness level that the lights can reach during their
        // oscillation.
        this.min_brightness = 100;
        // Calculate the offset to vertically shift the sine wave. Minimum
        this.offset = min_brightness + amplitude;
    }

    @Override
    public void setup() {
        for (int i = 0; i < this.mgr.getNumLights(); i++) {
            Light light = this.mgr.getLight(i);
            // Set Cool and Warm Channels to 100%
            light.setCool(1);
            light.setWarm(1);
            // Set Brightness to 0
            light.setBrightness(0.0);
        }
    }

    @Override
    public void draw() {
        for (int i = 0; i < this.mgr.getNumLights(); i++) {
            Light light = this.mgr.getLight(i);
            // Brightness is a
            double brightness = amplitude * Math.sin(period.toMillis() * -0.001 * this.frequency + i) + offset;
            light.setBrightness(brightness);
        }
    }

    @Override
    public void teardown() {
        this.mgr.off();
    }
}
