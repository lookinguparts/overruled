import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;

public class LightManager {
    private final PApplet app;
    private final int numLights;
    private final List<Light> lights;

    public LightManager(final PApplet app, final int numLights) {
        this.app = app;
        this.numLights = numLights;
        this.lights = new ArrayList<Light>();
    }

    public void setup() {
        float spacing = app.width / (float)(numLights + 1);
        float y = app.height / 2;

        // Add all of the lights to an array for later use
        for (int idx = 0; idx < numLights; idx++) {
            float x = spacing * (idx + 1);
            final Light light = new Light(x, y, 50.0, color(255, 255, 255));
            this.lights.add(light);
        }
    }

    public int getNumLights() {
        return this.numLights;
    }

    public Light getLight(final int idx) {
        return this.lights.get(idx);
    }

    public List<Light> getLights() {
        return this.lights;
    }

    public void setBrightness(final double val) {
        for (final Light light : this.lights) {
            light.setBrightness((float)val);
        }
    }
}
