import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import processing.sound.*;
import com.jaysonh.dmx4artists.*;
import ddf.minim.*;
import ddf.minim.analysis.*;

final int numLights = 9;
final int numDMXChannels = numLights * Light.NUM_CHANNELS_PER_LIGHT;  // total number of channels allocated for the dmx device,
                                                          // must not be more than 511

// Connect to the first dmx usb device available
final DMXControl dmx = new DMXControl(0, 511);

// lights
final LightManager lm = new LightManager(this, dmx, numLights);

// scenes
final Scene ao = new AllOnScene(lm, 0.2);
final Scene rr = new RoundRobinScene(lm, Duration.ofMillis(200), 0.2);
final Scene oa = new OnAirScene(lm, Duration.ofSeconds(1), 0.8);
final Scene bd = new BeatDetectSceen(this, lm, 0.5);
final Scene f = new FlashScene(this, lm, 0.5);
final SceneManager sm = new SceneManager();

// viz
final Visualiser viz = new Visualiser();

void setup()
{
  // display
  size( 440, 200);

  // set up lights and scenes
  lm.setup();
  sm.register(ao);
  sm.register(rr);
  sm.register(oa);
  sm.register(bd);
  sm.register(f);
  sm.play(BeatDetectSceen.class);

  // visualization
  IntStream.range(0, lm.getNumLights()).forEach(idx -> {
    int xPos = 40 + 40 * (idx >= 2 ? idx +1 : idx);
    int yPos = 100;
    int xSize = 40;
    int ySize = 40;
    viz.add(new LightDrawer(this, lm.getLight(idx), xPos, yPos, xSize, ySize));
  });

}

void draw()
{
  // display
  background( 0 );

  // lights
  sm.draw();

  // visualization
  viz.draw();
}
