//  Edward and Victor

   import javax.swing.JFrame;
    public class MazeTest
   {
       public static void main(String[] args) throws Exception
      { 
         JFrame frame = new JFrame("Get the Prize");
         frame.setSize(1000, 1000);
         frame.setLocation(0, 0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	frame.setContentPane(new Maze());
         frame.setVisible(true);
      }
   }