import static processing.core.PApplet.*;

import com.jaysonh.dmx4artists.*;

import processing.*;
import processing.core.PApplet;

public class Light extends DMXFixture {
  public static final int NUM_CHANNELS_PER_LIGHT = 6;
  public static final int CHAN_BRIGHTNESS = 1;
  public static final int CHAN_WARM = 2;
  public static final int CHAN_COOL = 3;

  private final PApplet app;
  private final DMXControl dmx;

  public Light(final PApplet app, final DMXControl dmx, final int light /* 0-indexed */) {
    super(app, (light * Light.NUM_CHANNELS_PER_LIGHT) + 1, Light.NUM_CHANNELS_PER_LIGHT);
    this.app = app;
    this.dmx = dmx;
    dmx.addFixture(this);
  }

  public void setBrightness(final double val) {
    float c = map((float)val, 0, 1, 0, 255);
    this.sendValue(CHAN_BRIGHTNESS, c);
  }

  public void setWarm(final double val) {
    float c = map((float)val, 0, 1, 0, 255);
    this.sendValue(CHAN_WARM, c);
  }

  public void setCool(final double val) {
    float c = map((float)val, 0, 1, 0, 255);
    this.sendValue(CHAN_COOL, c);
  }

}
