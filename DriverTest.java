//  Edward and Victor

   import javax.swing.JFrame;
    public class DriverTest
   {
       public static void main(String[] args) throws Exception
      { 
         JFrame frame = new JFrame("Unit2, Lab10: Polka Dots");
         frame.setSize(2100, 1000);
         frame.setLocation(0, 0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	Map p = new Map();
         frame.setContentPane(p);
         p.requestFocus();
         frame.setVisible(true);
      }
   }