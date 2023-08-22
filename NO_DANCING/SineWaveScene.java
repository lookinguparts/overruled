import static processing.core.PApplet.*;

import java.time.Duration;

import processing.core.PApplet;

public class SineWaveScene implements Scene {

    private final PApplet app;
    private final LightManager mgr;

    private final float intensity;
    private final float minIntensity;
    private final float frequency;

    public SineWaveScene(final PApplet app, final LightManager mgr, final Duration period, final float intensity) {
        this.app = app;
        this.mgr = mgr;

        this.intensity = intensity;
        // Set the minimum brightness level that the lights can reach during their
        // oscillation.
        this.minIntensity = .5f;

        this.frequency = 1.0f / period.toSeconds();
    }

    @Override
    public void setup() {
        this.mgr.setCool(1);
        this.mgr.setWarm(1);
        this.mgr.setBrightness(0);
    }

    @Override
    public void draw() {
        for (int i = 0; i < this.mgr.getNumLights(); i++) {
            final Light light = this.mgr.getLight(i);
            final float now = this.app.millis() / 200.0f;
            final double brightness = map(
                    (float) (Math.sin(now - i)),
                    0.0f,
                    1.0f,
                    this.minIntensity,
                    this.intensity);
            light.setBrightness(brightness);
        }
    }

    @Override
    public void teardown() {
        this.mgr.off();
    }
}
