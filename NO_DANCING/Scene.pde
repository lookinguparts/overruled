import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public interface Scene {
  public void setup();
  public void draw();
  public void teardown();
}; 

public class SceneRegistry {
  private final Map<String, Scene> scenes = new HashMap<String, Scene>();
  
  public SceneRegistry register(final String name, final Scene s){
    this.scenes.put(name, s);
    return this;
  }

}
