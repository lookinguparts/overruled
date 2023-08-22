LightManager lightManager;
int numLights = 9;
float frequency = 2;  // Higher value for faster oscillations
float amplitude = 100;
float min_brightness = 100;
float offset = min_brightness + amplitude;

void setup() {
  size(800, 600);
  lightManager = new LightManager(this, numLights);
  lightManager.setup();
}

void draw() {
  background(0);

  // Change the brightness of each light over time
  for (int i = 0; i < lightManager.getNumLights(); i++) {
    Light light = lightManager.getLight(i);
    // Sine is negative so wave goes left -> right
    float brightness = amplitude * sin(millis() * -0.001 * frequency + i) + offset;
    //float brightness = amplitude * sin(second() * 0.001 * frequency + i) + offset;
    light.setBrightness(brightness);
    light.display();
  }
}
