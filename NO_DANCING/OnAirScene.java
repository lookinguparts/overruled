import java.time.Duration;
import java.util.Date;
import java.util.Iterator;

public class OnAirScene implements Scene {

  public static final double DEFAULT_FADE_RATE = 1.0;
  private final LightManager mgr;
  private final Duration period;
  private final double intensity;
  private final double fadeRate;

  private boolean isOn;
  private Date last;

  public OnAirScene(final LightManager mgr, final Duration period, final double intensity, final double fadeRate) {
    this.mgr = mgr;
    this.period = period;
    this.intensity = intensity;
    this.fadeRate = fadeRate;
  }

  public OnAirScene(final LightManager mgr, final Duration period, final double intensity) {
    this.mgr = mgr;
    this.period = period;
    this.intensity = intensity;
    this.fadeRate = OnAirScene.DEFAULT_FADE_RATE;
  }

  @Override()
  public void setup() {
    this.isOn = false;
    this.last = new Date();

    // TODO(will): the visualizer cannot pick up the fade values because they are
    // stored in the DMXControl#dmxData array;
    // we could pull that data out of the DMXControl in the visualizer if we like.
    this.mgr.setFade(true);
    this.mgr.setFadeRate((float) this.fadeRate);

    int idx = 0;
    for (Iterator<Light> it = this.mgr.getLights().iterator(); it.hasNext(); idx++) {
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
    this.mgr.setFade(false);
    this.mgr.setFadeRate(1);
    this.mgr.off();
  }

}
