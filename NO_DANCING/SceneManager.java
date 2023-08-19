import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SceneManager implements Scene {
    private final List<Scene> scenes = new ArrayList<Scene>();

    private Scene currentScene = null;
    private Timer timer = null;

    public SceneManager register(final Scene s) {
        this.scenes.add(s);
        return this;
    }

    public void startShuffle(final Duration period) {
        final SceneManager that = this;

        this.timer = new Timer("SceneManagerShuffleTimer", true);
        this.timer.schedule(new TimerTask() {
            public void run() {
                that.playRandom();
            }
        }, 0, period.toMillis());
    }

    public void stopShuffle() {
        if (this.timer != null) {
            this.timer.cancel();
        }
    }

    public <T> void play(Class<T> klass) {
        for (Scene scene : scenes) {
            if (scene.getClass() == klass) {
                try {
                    System.out.println("Tearing down '" + getCurrentScene().getClass().getName() + "'...");
                    this.getCurrentScene().teardown();
                } catch(final Exception e){
                    System.out.println("Error tearing down '" + getCurrentScene().getClass().getName() + "'; skipping");
                }
                this.currentScene = scene;
                try {
                    System.out.println("Setting up scene '" + klass.getName() + "'...");
                    this.getCurrentScene().setup();
                    System.out.println("Playing scene '" + klass.getName() + "'");
                    return;
                } catch(final Exception e){
                    System.out.println("Error setting up '" + klass.getName() + "'; skipping");
                }
            }
        }
        System.out.println("Could not start'" + klass.getName() + "'; playing random scene...");
        this.playRandom();
    }

    public void playRandom() {
        int randomIndex = new Random().nextInt(this.scenes.size());
        final Scene nextScene = this.scenes.get(randomIndex);
        this.play(nextScene.getClass());
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
