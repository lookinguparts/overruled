import java.time.Duration;
import java.util.Date;

public class RoundRobinScene implements Scene {

  private LightManager mgr;
  private Duration period;
  private Date start;
  private int activeLightIndex = 0;
  private double intensity;

  public RoundRobinScene(final LightManager mgr, final Duration period, final double intensity) {
    this.mgr = mgr;
    this.period = period;
    this.intensity = intensity;
  }

  @Override()
  public void setup() {

    this.mgr.getLight(this.activeLightIndex).setBrightness(this.intensity);
    this.start = new Date();

  }

  @Override()
  public void draw() {
    final Date now = new Date();

    long diffMs = now.getTime() - this.start.getTime();
    if (diffMs > this.period.toMillis()) {
      this.mgr.getLight(this.activeLightIndex).setBrightness(0);
      this.activeLightIndex = (this.activeLightIndex + 1) % (this.mgr.lights().size());
      System.out.println(this.activeLightIndex);
      this.mgr.getLight(this.activeLightIndex).setBrightness(this.intensity);
      this.start = now;
    }

  }

  @Override()
  public void teardown() {
    this.mgr.off();
  }

}
