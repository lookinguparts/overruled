import com.jaysonh.dmx4artists.*;

import static processing.core.PApplet.*;
import processing.core.PApplet;

public class FlickerScene implements Scene {

    private PApplet app;
    private LightManager mgr;
    private final double intensity;

    public FlickerScene(final PApplet app, final LightManager mgr, final double intensity) {
        this.app = app;
        this.mgr = mgr;
        this.intensity = intensity;
    }

    @Override()
    public void setup() {
        this.mgr.setBrightness(this.intensity);
        this.mgr.getLight(0).setBrightness(0);
        this.mgr.getLight(1).setBrightness(0);
    }

    @Override()
    public void draw() {
        final float n = this.app.noise(this.app.millis() / 200.0f);
        final float val = map(n, 0.0f, 1.0f, 0.0f, (float)this.intensity);

        this.mgr.getLight(0).setBrightness(val);
        this.mgr.getLight(1).setBrightness(val);
    }

    @Override()
    public void teardown() {
        this.mgr.off();
    }

}
