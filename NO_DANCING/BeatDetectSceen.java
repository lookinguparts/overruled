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
  private final int numBands = 16;
  private final float levelScale = (float) 1.0;
  private final float minLevel = (float) 1.0;

  private BeatDetect beat;
  private AudioInput in;

  public BeatDetectSceen(final PApplet app, final LightManager mgr, final double intensity) {
    this.mgr = mgr;
    this.intensity = intensity;
    this.minim = new Minim(app);
  }

  protected int BPMToMs(int bpm) {
    float bps = bpm / 60;
    float freq = bps;
    float periodS = 1 / freq;
    float periodMs = periodS * 1000;
    return (int) periodMs;
  }

  @Override()
  public void setup() {
    this.in = minim.getLineIn(Minim.STEREO);
    this.beat = new BeatDetect(in.bufferSize(), in.sampleRate());
    this.beat.setSensitivity(this.BPMToMs(110)); // ms
    this.beat.detectMode(BeatDetect.FREQ_ENERGY);
    final BeatListener bl = new BeatListener(beat, in);

    this.mgr.setBrightness(this.intensity);
  }

  @Override()
  public void draw() {

    int size = this.beat.detectSize();
    for (int idx = 0; idx < size; idx++) {

      float freq = this.beat.getDetectCenterFrequency(idx);

      if (this.beat.isRange(0, 3, 2)) {
        this.mgr.getLight(0).setBrightness(this.intensity);
        this.mgr.getLight(1).setBrightness(this.intensity);
      } else {
        this.mgr.getLight(0).setBrightness(0);
        this.mgr.getLight(1).setBrightness(0);
      }
    }
  }

  @Override()
  public void teardown() {
    this.mgr.off();
  }
}
