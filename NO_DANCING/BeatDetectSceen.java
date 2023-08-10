import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;
import processing.*;
import processing.core.PApplet;
import processing.sound.*;

public class BeatDetectSceen implements Scene {

  private LightManager mgr;
  private final double intensity;
  private final Minim minim;
  private final BeatDetect beat;
  private final AudioInput in;
  private final int numBands = 16;
  private final float levelScale = (float) 1.0;
  private final float minLevel = (float) 1.0;
  

  public BeatDetectSceen(final PApplet app, final LightManager mgr, final double intensity) {
    this.mgr = mgr;
    this.intensity = intensity;
    this.minim = new Minim(app);
    this.in = minim.getLineIn(Minim.STEREO);
    this.beat = new BeatDetect(in.bufferSize(), in.sampleRate());
    this.beat.setSensitivity(50);
    final BeatListener bl = new BeatListener(beat, in);  
  }

  @Override()
  public void setup() {
    this.mgr.setBrightness(this.intensity);
  }

  @Override()
  public void draw() {

    int size = this.beat.detectSize();
    for (int idx = 0; idx < size; idx++) {

      if (this.beat.isOnset(idx)) {
        this.mgr.getLight(0).setBrightness(this.intensity);
      } else {
        this.mgr.getLight(0).setBrightness(0);
      }
    }
  }

  @Override()
  public void teardown() {
    this.mgr.off();
  }
}
