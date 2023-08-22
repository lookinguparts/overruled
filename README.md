# overruled

Code For Running the Over Ruled art project

## Hardware

- Lights: https://www.amazon.com/dp/B07ZYKRSH8
- Controller: https://www.amazon.com/gp/product/B07KW22ZK1

## Code

The project is [ currently ] implemented in [Processing](https://processing.org), and can be found in the [NO_DANCING](./NO_DANCING) directory.

## Running Interactively

```console
$ find . | entr -c -n -r processing-java --sketch="$PWD/NO_DANCING" --run
```

## Run in the Simulator
- Open `/Processing_Light_Simulator` in processing
- Use the LightManager and Light to simluate DMX controller
- Use the processing `setup()` and `draw()` methods
- Will run a visual of the lights with a black background
- Minor porting needed to run using actual lights see `SineLights.pde` and `SineWaveScene.java` for comparison

## TODO
- [ ] build script
- [ ] better beat detect
- [ ] switch to, and stay on, beat detect on line in
- [ ] sine wave scene
- [ ] runtime randomization of scene parameters
