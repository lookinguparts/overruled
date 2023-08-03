import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SceneManager implements Scene {
    private final List<Scene> scenes = new ArrayList<Scene>();
    private Scene currentScene = null;

    public SceneManager register(final Scene s) {
        this.scenes.add(s);
        return this;
    }

    public Scene getCurrentScene() {
        if (this.currentScene == null) {
            if (this.scenes.isEmpty()) {
                throw new RuntimeException(
                        "scene manager has no registered scenes; please `register(…)` a scene before starting.");
            }

            final Scene nextScene = this.scenes.iterator().next();
            this.currentScene = nextScene;
        }

        return this.currentScene;
    }

    public void shuffle() {
        int randomIndex = new Random().nextInt(this.scenes.size());
        final Scene nextScene = this.scenes.get(randomIndex);
        this.currentScene = nextScene;
    }

    @Override
    public void setup() {
        this.getCurrentScene().setup();
    }

    @Override
    public void draw() {
        this.getCurrentScene().draw();
    }

    @Override
    public void teardown() {
        this.getCurrentScene().teardown();
    }

}
