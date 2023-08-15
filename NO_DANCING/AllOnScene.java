public class AllOnScene implements Scene {

  private LightManager mgr;
  private final double intensity;

  public AllOnScene(final LightManager mgr, final double intensity) {
    this.mgr = mgr;
    this.intensity = intensity;
  }

  @Override()
  public void setup() {
    this.mgr.setBrightness(this.intensity);
    this.mgr.setCool(this.intensity);
    this.mgr.setWarm(intensity);
  }

  @Override()
  public void draw() {
  }

  @Override()
  public void teardown() {
    this.mgr.off();
  }

}
