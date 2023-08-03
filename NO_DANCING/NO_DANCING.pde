import com.jaysonh.dmx4artists.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import processing.sound.*;

// sound

FFT fft;
AudioIn in;
final int numBands = 8;
float[] spectrum = new float[numBands];

// lights

final List<DMXFixture> lights = new ArrayList<DMXFixture>();

final int numLights = 9;
final int numLightChannels = 6;
final int numDmxChannels = numLights * numLightChannels;  // total number of channels allocated for the dmx device, must not be more than 511

void setup()
{
  // display
  size( 200, 200, P3D );

  // sound

  fft = new FFT(this, numBands);
  in = new AudioIn(this, 0);

  // start the Audio Input
  in.start();

  // patch the AudioIn
  fft.input(in);

  // lights

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
    lights.add(light);
  }
}

void draw()
{
  // display
  background( 0 );

  // sound
  fft.analyze(spectrum);

  for (int i = 0; i < numBands; i++) {

    // The result of the FFT is normalized
    int power = (int)map(spectrum[i]*8, 0, 1, 0, 128);
    final DMXFixture light = lights.get(i);
    light.sendValue(1, power);

  }

  // lights
}
