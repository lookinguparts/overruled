public class Light {
    float x, y;     // Position of the light
    float size;     // Size of the light
    color c;        // Color of the light

    public Light(float x, float y, float size, color c) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.c = c;
    }

    void setBrightness(float b) {
        this.c = color(red(c), green(c), blue(c), b);
    }

    void display() {
        fill(c);
        ellipse(x, y, size, size);
    }
}
  
