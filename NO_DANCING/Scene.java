import java.util.List;

public interface Scene {
  public void setup(final List<Light> lights);

  public void draw();

  public void teardown();
}
