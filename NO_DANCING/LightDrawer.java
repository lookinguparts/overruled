import processing.core.PApplet;

class LightDrawer extends FixtureDrawer {
    protected final PApplet app;

    LightDrawer(final PApplet app, final Light light, int xPos, int yPos, int xSize, int ySize) {
        super(light, xPos, yPos, xSize, ySize);
        this.app = app;
    }

    void draw() {
        this.app.pushStyle();

        float bri = this.fixture.getParam(Light.CHAN_BRIGHTNESS - 1).getValue();
        double w = this.fixture.getParam(Light.CHAN_WARM - 1).getValue() * (bri / 255.0);
        double c = this.fixture.getParam(Light.CHAN_COOL - 1).getValue() * (bri / 255.0);

        this.app.fill(bri, bri, bri);
        this.app.ellipse(xPos, yPos, xSize, ySize);

        this.app.popStyle();
    }

}
