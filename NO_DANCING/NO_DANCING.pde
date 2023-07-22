import com.jaysonh.dmx4artists.*;
import java.util.List;
import java.util.ArrayList;

final List<DMXFixture> lights = new ArrayList<DMXFixture>();

final int numLights = 9;
final int numLightChannels = 6;
final int numDMXChannels = numLights * numLightChannels;

final int numDmxChannels = numLights * numLightChannels;  // total number of channels allocated for the dmx device, must not be more than 511

void setup()
{
  size( 600, 600 );

  // Connect to the first dmx usb device available
  final DMXControl dmx = new DMXControl( 0, numDmxChannels );

  // add all of the lights to an array for later use
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
