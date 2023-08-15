import java.time.Duration;
import java.util.Date;

import processing.core.PApplet;

public class AlternatingScene implements Scene {

    private LightManager mgr;
    private final double intensity;
    private final Duration period;

    private boolean isNo;
    private Date last;

    public AlternatingScene(final PApplet app, final LightManager mgr, final Duration period, final double intensity) {
        this.mgr = mgr;
        this.intensity = intensity;
        this.period = period;
    }

    @Override()
    public void setup() {

        isNo = true;
        last = new Date();
    }

    @Override()
    public void draw() {
        final Date now = new Date();

        long diffMs = now.getTime() - this.last.getTime();
        if (diffMs > this.period.toMillis()) {

            for (int idx = 0; idx < this.mgr.getNumLights(); idx++) {
                // NOTE(will): this is too tricky; the idea is that we're using an XOR
                // to make this a single if statement.
                if ((idx < 2) ^ this.isNo) {
                    this.mgr.getLight(idx).setBrightness(this.intensity);
                } else {
                    this.mgr.getLight(idx).setBrightness(0);
                }
            }
            this.isNo = !this.isNo;

            this.last = now;
        }
    }

    @Override()
    public void teardown() {
        this.mgr.off();
    }

}
