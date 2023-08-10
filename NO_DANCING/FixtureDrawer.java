import com.jaysonh.dmx4artists.*;

import processing.*;

abstract class FixtureDrawer {
    FixtureDrawer(DMXFixture fixture, int xPos, int yPos, int xSize, int ySize) {
        this.fixture = fixture;

        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    abstract void draw();

    DMXFixture fixture;

    int xPos;
    int yPos;
    int xSize;
    int ySize;
}
