import java.util.ArrayList;;

class Visualiser {

   void add(FixtureDrawer fix) {
      fixtures.add(fix);
   }

   void draw() {
      for (FixtureDrawer f : fixtures) {
         f.draw();
      }
   }

   ArrayList<FixtureDrawer> fixtures = new ArrayList<FixtureDrawer>();
}
