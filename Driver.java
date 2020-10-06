//Victor Xia
import javax.swing.JFrame;
public class Driver
{
   public static void main(String[] args) throws Exception
   { 
      JFrame frame = new JFrame("Maze Runner");
      frame.setSize(400, 390);  
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new MainMenu());
      frame.setVisible(true);
      
      
   }

}
