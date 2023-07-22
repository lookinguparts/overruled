/**
 *
 * Example: Multiple Fixtures
 *
 * This is a simple example where we control two lights
 *
 * Assumes you have connected a typical RGB can light where channel 1 is master fader,
 * channel 2 is red, channel 3 is green, channel 4 is blue
 */

import com.jaysonh.dmx4artists.*;
import java.util.List;
import java.util.ArrayList;

final List<DMXFixture> lights = new ArrayList<DMXFixture>();

final int numLights = 9;
final int numLightChannels = 6;
final int numDMXChannels = numLights * numLightChannels;

DMXFixture light1;
DMXFixture light2;

int lightAddress1    = 1;
int lightAddress2    = 7;

int numDmxChannels   = 511;  // total number of channels allocated for the dmx device, must not be more than 511

void setup()
{
  size( 600, 600 );

  // Connect to the first dmx usb device available
  final DMXControl dmx = new DMXControl( 0, numDmxChannels );


  for (int idx = 0; idx < numLights; idx++) {
    final int addr = idx * numLightChannels + 1;
    final DMXFixture light = new DMXFixture( this, addr, numLightChannels);
    dmx.addFixture( light );

    light.sendValue( 1, 255 ); // set the fader channel to 255
    light.sendValue( 2, 255 ); // set the warm channel to 255
    light.sendValue( 3, 255 );   // set the cool channel to 0
  }
}

void draw()
{
  background( 0 );
}
