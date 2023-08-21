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
final DMXControl dmx = new DMXControl( 0, 128 );

// lights
final float INTENSITY = 1.0;
final LightManager lm = new LightManager(this, dmx, numLights);

// scenes
final Scene ao = new AllOnScene(lm, INTENSITY);
final Scene bd = new BeatDetectSceen(this, lm, INTENSITY);
final Scene fl = new FlashScene(this, lm, INTENSITY);
final Scene fs = new FlickerScene(this, lm, INTENSITY);
final Scene ss = new StrobeScene(this, lm, Duration.ofSeconds(3), INTENSITY);
final Scene oa = new OnAirScene(lm, Duration.ofSeconds(2), INTENSITY);
// Turin each light on/off from left to right
final Scene rr = new RoundRobinScene(lm, Duration.ofMillis(200), INTENSITY);
// Alternate fliping the lights on the words "NO" and "DANCING" on/off
final Scene as = new AlternatingScene(this, lm, Duration.ofSeconds(1), INTENSITY);
// Oscillates lights brightness from left ro right in a sine wave
final Scene sw = new SineWaveScene(this, lm, Duration.ofSeconds(1));

// scence manager
final SceneManager sm = new SceneManager();

// viz
final Visualiser viz = new Visualiser();

void setup()
{
  // display
  size(440, 200);

  // set up lights and scenes
  lm.setup();
  sm.register(ao);
  sm.register(as);
  sm.register(bd);
  sm.register(fl);
  sm.register(fs);
  sm.register(oa);
  sm.register(rr);
  sm.register(ss);
  sm.register(sw);
  sm.startShuffle(SineWaveScene.class);

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
  try {
  sm.draw();

  } catch (final Exception e){
    System.out.println("Error executing '" + sm.getCurrentScene().getClass().getName() + "'; playing random scene...");
  }

  // visualization
  viz.draw();
}
