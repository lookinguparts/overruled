import com.jaysonh.dmx4artists.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

Scene s = new AllOnScene();

// lights
final List<DMXFixture> lights = new ArrayList<DMXFixture>();

final int numLights = 9;
final int numLightChannels = 6;
final int numDMXChannels = numLights * numLightChannels;

void setup()
{
  // display
  size( 600, 600 );


  // lights


  // Connect to the first dmx usb device available
  final DMXControl dmx = new DMXControl( 0, numDMXChannels );

  // add all of the lights to an array for later use
  for (int idx = 0; idx < numLights; idx++) {
    final int addr = idx * numLightChannels + 1;
    final DMXFixture light = new DMXFixture( this, addr, numLightChannels);
    dmx.addFixture( light );

    light.sendValue( 1, 255 ); // set the fader channel to 255
    light.sendValue( 2, 255 ); // set the warm channel to 255
    light.sendValue( 3, 255 );   // set the cool channel to 0
    lights.add(light);
  }
}

void draw()
{
  // display
  background( 0 );

  // lights
}
