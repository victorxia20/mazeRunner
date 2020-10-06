//Victor and Edward
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
public class MainMenu extends JPanel
{
   JLabel title;
   Settings setting;
   JButton save, newGame, tank1, tank2, settings, quit;
   CardLayout layout;
   JPanel card;
   TankPanel tp1, tp2;
   Tank tanks1, tanks2;
   Map map;
   Scanner infile;
   BufferedImage myImage;
   PrintStream outfile;
   public MainMenu() throws Exception
   {
      setLayout(new BorderLayout());
      layout = new CardLayout();
      setting = new Settings();
      card = new JPanel(layout);
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(360, 200));
      JPanel over = new JPanel();
      JPanel south = new JPanel();
      over.setLayout(new FlowLayout());
      south.setLayout(new FlowLayout());
      add(card);
      tp1 = new TankPanel(tanks1);
      tp2 = new TankPanel(tanks2);
      add(south, BorderLayout.SOUTH);
      panel.setLayout(new GridLayout(6,1, 30, 5));
      // add(new JLabel("   "), BorderLayout.EAST);
   //       add(new JLabel("   "), BorderLayout.WEST);
      
      ImageIcon maze = new ImageIcon("Maze_Runner_Logo.svg.png");
      title = new JLabel();
      title.setIcon(maze);
      
      title.setHorizontalAlignment(SwingConstants.CENTER);
      title.setFont(new Font("Serif", Font.BOLD, 80));
      over.add(title);
      over.add(panel);
      copy(tp1, tp2, infile);
      newGame = new JButton("Start Game");
      newGame.addActionListener(new Listener1());
      panel.add(newGame);
      save = new JButton("Save Settings");
      save.addActionListener(new Listener2());
      panel.add(save);
      settings = new JButton("Settings");
      settings.addActionListener(new Listener("Settings"));
      panel.add(settings);
      tank1 = new JButton("Tank 1");
      tank2 = new JButton("Tank 2");
      tank1.addActionListener(new Listener("Tank 1"));
      tank2.addActionListener(new Listener("Tank 2"));
      panel.add(tank1);
      panel.add(tank2);
      quit = new JButton("Quit");
      quit.addActionListener(new Listener6());
      panel.add(quit);
      JButton back = new JButton("Back");
      back.addActionListener(new Listener("Maze Runner"));
      south.add(back);
      card.add(over, "Maze Runner");
      card.add(setting, "Settings");
      card.add(tp1, "Tank 1");
      card.add(tp2, "Tank 2");
      
   }

   private void copy(TankPanel a, TankPanel b, Scanner infile) throws Exception
   {
      infile = new Scanner(new File("data.txt"));
      infile.nextLine(); infile.nextLine(); infile.nextLine(); infile.nextLine();
      tp1.setName(infile.nextLine());
      tp1.setColor(Integer.parseInt(infile.nextLine()), Integer.parseInt(infile.nextLine()), Integer.parseInt(infile.nextLine()), infile.nextLine()); 
      tp1.setPWeap(infile.nextLine()); 
      tp1.setSWeap(infile.nextLine());
      infile.nextLine();
      tp2.setName(infile.nextLine()); 
      tp2.setColor(Integer.parseInt(infile.nextLine()), Integer.parseInt(infile.nextLine()), Integer.parseInt(infile.nextLine()), infile.nextLine()); 
      tp2.setPWeap(infile.nextLine()); 
      tp2.setSWeap(infile.nextLine());
      setting.setTimer(Integer.parseInt(infile.nextLine()));
      setting.setExplosionTimer(Integer.parseInt(infile.nextLine()));
      setting.setSpeed(Integer.parseInt(infile.nextLine()));
      setting.setSpeed2(Integer.parseInt(infile.nextLine()));
      infile.close();
   }
   private class Listener1 implements ActionListener 
   {
      public void actionPerformed(ActionEvent e)
      {
         JFrame frame = new JFrame("Maze Runner");
         frame.setSize(2100, 1000);  
         frame.setLocation(0, 0);
         Map map1 = new Map();
         frame.setContentPane(map1);
         frame.setVisible(true);
         newGame.setEnabled(false);
         
         frame.addWindowListener(
               new WindowAdapter()
               {
                  public void windowClosing(WindowEvent e)
                  {
                     newGame.setEnabled(true);
                  }
                  public void windowOpened(WindowEvent e)
                  {
                     newGame.setEnabled(false);
                  }
               });
      }
   }
   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try{
            outfile = new PrintStream(new FileOutputStream("data.txt"));
            System.setOut(outfile);
         }
         catch(FileNotFoundException a)
         {
            JOptionPane.showMessageDialog(null,"Report error to Community Support.");
            
         }
         System.out.println("worldgeneration");
         System.out.println("" + setting.getMySize());
         System.out.println("" + setting.getLandmarks());
         System.out.println("Tank 1");
         System.out.println("" + tp1.getName());
         System.out.println("" + tp1.getRed());
         System.out.println("" + tp1.getGreen());
         System.out.println("" + tp1.getBlue());
         System.out.println("" + tp1.getColor());         
         System.out.println("" + tp1.getPWeap());
         System.out.println("" + tp1.getSWeap());
         System.out.println("Tank 2");
         System.out.println("" + tp2.getName());
         System.out.println("" + tp2.getRed());
         System.out.println("" + tp2.getGreen());
         System.out.println("" + tp2.getBlue());
         System.out.println("" + tp2.getColor());
         System.out.println("" + tp2.getPWeap());
         System.out.println("" + tp2.getSWeap());
         System.out.println("" + setting.getTimer());
         System.out.println("" + setting.getExplosionTimer());
         System.out.println("" + setting.getSpeed());
         System.out.println("" + setting.getSpeed2());
         JOptionPane.showMessageDialog(null, "Saved!");
         outfile.close();
      }
   }
   private class Listener implements ActionListener
   {
      String myString;
      public Listener(String s)
      {
         myString = s;
      }
      public void actionPerformed(ActionEvent e)
      {
         layout.show(card, myString);
         if(myString.equals("Maze Runner"))
         {
         try{
            outfile = new PrintStream(new FileOutputStream("data.txt"));
            System.setOut(outfile);
         }
         catch(FileNotFoundException a)
         {
            JOptionPane.showMessageDialog(null,"Report error to Community Support.");
            
         }
         System.out.println("worldgeneration");
         System.out.println("" + setting.getMySize());
         System.out.println("" + setting.getLandmarks());
         System.out.println("Tank 1");
         System.out.println("" + tp1.getName());
         System.out.println("" + tp1.getRed());
         System.out.println("" + tp1.getGreen());
         System.out.println("" + tp1.getBlue());
         System.out.println("" + tp1.getColor());         
         System.out.println("" + tp1.getPWeap());
         System.out.println("" + tp1.getSWeap());
         System.out.println("Tank 2");
         System.out.println("" + tp2.getName());
         System.out.println("" + tp2.getRed());
         System.out.println("" + tp2.getGreen());
         System.out.println("" + tp2.getBlue());
         System.out.println("" + tp2.getColor());
         System.out.println("" + tp2.getPWeap());
         System.out.println("" + tp2.getSWeap());
         System.out.println("" + setting.getTimer());
         System.out.println("" + setting.getExplosionTimer());
         System.out.println("" + setting.getSpeed());
         System.out.println("" + setting.getSpeed2());
         outfile.close();

         }
      }
   }
   private class Listener6 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? Don't forget to save.");
         if(answer == 0)
            System.exit(0);
      }
   }

}