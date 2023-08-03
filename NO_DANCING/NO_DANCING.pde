import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.jaysonh.dmx4artists.*;

final int numLights = 9;
final int numLightChannels = 6;
final int numDMXChannels = numLights * numLightChannels; // total number of channels allocated for the dmx device,
                                                          // must not be more than 511

// Connect to the first dmx usb device available
final DMXControl dmx = new DMXControl(0, numDMXChannels);

// lights
final LightManager lm = new LightManager(this, dmx, numLights, numLightChannels);

// scenes
final Scene s = new AllOnScene();
final SceneManager sm = new SceneManager();

void setup()
{
  // display
  size( 200, 200);

  lm.setup();
  sm.register(s);
  sm.setup(lm.lights());
  sm.shuffle();


}

void draw()
{
  // display
  background( 0 );

  sm.draw();


  // lights
}
