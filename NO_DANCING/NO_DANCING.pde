import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.jaysonh.dmx4artists.*;

final int numLights = 3;
final int numDMXChannels = numLights * Light.NUM_CHANNELS_PER_LIGHT ;  // total number of channels allocated for the dmx device,
                                                          // must not be more than 511

// Connect to the first dmx usb device available
final DMXControl dmx = new DMXControl(0, 511);

// lights
final LightManager lm = new LightManager(this, dmx, numLights);

// scenes
final Scene ao = new AllOnScene(lm, 0.1);
final Scene rr = new RoundRobinScene(lm, Duration.ofSeconds(3, 0));
final SceneManager sm = new SceneManager();

void setup()
{
  // display
  size( 200, 200);

  lm.setup();
  sm.register(ao);
  sm.register(rr);
  sm.shuffle();
  sm.setup();

}

void draw()
{
  // display
  background( 0 );

  sm.draw();


  // lights
}
