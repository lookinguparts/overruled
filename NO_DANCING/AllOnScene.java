import java.util.List;

public class AllOnScene implements Scene {

  @Override()
  public void setup(final List<Light> lights) {
    System.out.println("all on scene setting up...");

    for (Light light : lights) {
      light.setBrightness(1);
    }
  }

  @Override()
  public void draw() {
    System.out.println("all on scene drawing");
  }

  @Override()
  public void teardown() {
    System.out.println("all on scene tearing down...");
  }

}
