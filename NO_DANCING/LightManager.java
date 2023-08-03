import java.util.ArrayList;
import java.util.List;

import com.jaysonh.dmx4artists.*;

import processing.core.PApplet;

public class LightManager {
    private final PApplet app;
    private final DMXControl dmx;
    private final int numLights;
    private final List<Light> lights = new ArrayList<Light>();

    public LightManager(final PApplet app, final DMXControl dmx, final int numLights) {
        this.app = app;
        this.dmx = dmx;
        this.numLights = numLights;
    }

    public void setup() {

        // add all of the lights to an array for later use
        for (int idx = 0; idx < numLights; idx++) {
            final Light light = new Light(this.app, this.dmx, idx);

            this.lights.add(light);
            light.setCool(1);
            light.setWarm(1);
            light.setBrightness(0.0);
        }

    }

    public Light getLight(final int idx) {
        return this.lights.get(idx);
    }

    public List<Light> lights() {
        return this.lights;
    }

    public void setBrightness(final double val) {
        for (final Light light : this.lights) {
            light.setBrightness(val);
        }
    }

    public void setWarm(final double val) {
        for (final Light light : this.lights) {
            light.setWarm(val);
        }
    }

    public void setCool(final double val) {
        for (final Light light : this.lights) {
            light.setCool(val);
        }
    }

    public void on() {
        for (final Light light : this.lights) {
            light.setBrightness(1);
        }
    }

    public void off() {
        for (final Light light : this.lights) {
            light.setBrightness(0);
        }
    }

}
