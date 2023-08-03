import java.time.Duration;
import java.util.Date;
import java.util.Iterator;

public class OnAirScene implements Scene {

  private final LightManager mgr;
  private final Duration period;
  private final double intensity;

  private boolean isOn;
  private Date last;

  public OnAirScene(final LightManager mgr, final Duration period, final double intensity) {
    this.mgr = mgr;
    this.period = period;
    this.intensity = intensity;
  }

  @Override()
  public void setup() {
    this.isOn = false;
    this.last = new Date();

    int idx = 0;
    for (Iterator<Light> it = this.mgr.lights().iterator(); it.hasNext(); idx++) {
      Light l = it.next();
      if (idx >= 2) {
        l.setBrightness(this.intensity);
      }
    }
  }

  @Override()
  public void draw() {
    final Date now = new Date();

    long diffMs = now.getTime() - this.last.getTime();
    if (diffMs > this.period.toMillis()) {
      if (this.isOn) {
        this.mgr.getLight(0).setBrightness(0);
        this.mgr.getLight(1).setBrightness(0);
        this.isOn = false;
      } else {
        this.mgr.getLight(0).setBrightness(this.intensity);
        this.mgr.getLight(1).setBrightness(this.intensity);
        this.isOn = true;
      }
      this.last = now;
    }

  }

  @Override()
  public void teardown() {
    this.mgr.off();
  }

}
