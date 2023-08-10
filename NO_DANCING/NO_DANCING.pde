import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import processing.sound.*;
import com.jaysonh.dmx4artists.*;
import ddf.minim.*;
import ddf.minim.analysis.*;

final int numLights = 3;
final int numDMXChannels = numLights * Light.NUM_CHANNELS_PER_LIGHT ;  // total number of channels allocated for the dmx device,
                                                          // must not be more than 511

// Connect to the first dmx usb device available
final DMXControl dmx = new DMXControl(0, 511);

// lights
final LightManager lm = new LightManager(this, dmx, numLights);

// scenes
final Scene ao = new AllOnScene(lm, 0.1);
final Scene rr = new RoundRobinScene(lm, Duration.ofMillis(500), 0.1);
final Scene oa = new OnAirScene(lm, Duration.ofSeconds(1), 0.1);
final Scene bd = new BeatDetectSceen(this, lm, 0.5);
final SceneManager sm = new SceneManager();

void setup()
{
  // display
  size( 200, 200);

  lm.setup();
  sm.register(ao);
  sm.register(rr);
  sm.register(oa);
  sm.register(bd);
  sm.play(BeatDetectSceen.class);
  sm.setup();

}

void draw()
{
  // display
  background( 0 );

  sm.draw();


  // lights
}
