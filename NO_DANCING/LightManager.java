import java.util.ArrayList;
import java.util.List;

import com.jaysonh.dmx4artists.*;

import processing.core.PApplet;

public class LightManager {
    private final PApplet app;
    private final DMXControl dmx;
    private final int numLights;
    private final int numLightChannels;
    private final List<Light> lights = new ArrayList<Light>();

    public LightManager(final PApplet app, final DMXControl dmx, final int numLights, final int numLightChannels) {
        this.app = app;
        this.dmx = dmx;
        this.numLights = numLights;
        this.numLightChannels = numLightChannels;
    }

    public List<Light> lights() {
        return this.lights;
    }

    public void setup() {

        // add all of the lights to an array for later use
        for (int idx = 0; idx < numLights; idx++) {
            final Light light = new Light(this.app, this.dmx, idx);

            light.setCool(1);
            light.setWarm(1);
            light.setBrightness(0.0);
            lights.add(light);
        }

    }
}
