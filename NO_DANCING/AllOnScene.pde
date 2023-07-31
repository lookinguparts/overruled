public class AllOnScene implements Scene {
  
  @Override()
  public void setup() {
    System.out.println("all on scene setting up...");
  }
  
  @Override()
  public void draw() {
    System.out.println("all on scene drawing up");
  }
  
  @Override()
  public void teardown(){
     System.out.println("all on scene tearing down");
  }
}
