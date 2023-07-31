import com.jaysonh.dmx4artists.*;
import java.util.Collections;

public class Light extends DMXFixture {
  public static final int NUM_LIGHTS = 9; // one for each non-whitespace character
  public static final int NUM_CHANNELS_PER_LIGHT = 6;

  public Light(final PApplet app, final DMXControl dmx, final int light /* 0-indexed */) {
    super(app, (light * Light.NUM_CHANNELS_PER_LIGHT) + 1, Light.NUM_CHANNELS_PER_LIGHT);
    dmx.addFixture( this );
  }
  
  public void setBrightness(final char brightness){
    this.setValue((int)brightness);
  }
    
}
