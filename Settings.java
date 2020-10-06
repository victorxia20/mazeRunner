//Victor and Edward
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class Settings extends JPanel
{  
   private int size;
   private int timer, exptimer, speed, speed2;
   private String land;
   JButton button1, button2, button3, button4, button5, button6;
   public Settings() throws Exception
   { 
      Scanner infile = new Scanner(new File("data.txt"));
      infile.next(); 
      size = infile.nextInt()-14;
      land = infile.next();
      setLayout(new BorderLayout());
      JPanel panel = new JPanel();
      add(panel);
      JLabel label = new JLabel("Settings");
      label.setFont(new Font("Serif", Font.BOLD, 30));
      label.setHorizontalAlignment(SwingConstants.CENTER);
      add(label, BorderLayout.NORTH);
      panel.setLayout(new GridLayout(4, 2, 10, 10));
      JLabel label1 = new JLabel("World Size");
      label1.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(label1);
      button1 = new JButton("Size: " + size);
      button1.addActionListener(new Listener1());
      panel.add(button1);
      JLabel label2 = new JLabel("Landmarks");
      label2.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(label2);
      button2 = new JButton(land);
      button2.addActionListener(new Listener2());
      panel.add(button2);
      JPanel subsubpanel = new JPanel();
      subsubpanel.setLayout(new GridLayout(1, 2));
      JLabel label5 = new JLabel("Shoot Speed");
      label5.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(label5);
      button5 = new JButton("T1: " + timer);
      button5.addActionListener(new Listener3());
      subsubpanel.add(button5);
      button6 = new JButton("T2: " + exptimer);
      button6.addActionListener(new Listener4());
      subsubpanel.add(button6);
      panel.add(subsubpanel);
      JLabel label3 = new JLabel("Turn Speed");
      label3.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(label3);
      JPanel subsubpanel1 = new JPanel();
      subsubpanel1.setLayout(new GridLayout(1, 2));
      button3 = new JButton("T1: " + speed);
      button3.addActionListener(new Listener5());
      subsubpanel1.add(button3);
      button4 = new JButton("T2: " + speed2);
      button4.addActionListener(new Listener6());
      subsubpanel1.add(button4);
      panel.add(subsubpanel1);
      
   }
   public int getMySize()
   {
      return size+14;
   }
   public int getTimer()
   {
      return timer;
   }
   public int getExplosionTimer()
   {
      return exptimer;
   }
   public int getSpeed()
   {
      return speed;
   }
   public int getSpeed2()
   {
      return speed2;
   }
   public void setSpeed2(int x)
   {
      speed2 = x;
      button4.setText("T2: " + speed2);
   }
   public void setTimer(int x)
   {
      timer = x;
      button5.setText("T1: " + timer);
   }
   public void setExplosionTimer(int x)
   {
      exptimer = x;
      button6.setText("T2: " + exptimer);
   }
   public void setSpeed(int x)
   {
      speed = x;
      button3.setText("T1: " + speed);
   }
   public String getLandmarks()
   {
      return button2.getText();
   }
   private class Listener1 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         size += 20;
         if(size > 200)
            size = 20;
         button1.setText("Size: \n" + size);
      }
   }
   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(land.equals("Regular"))
            land = "Frequent";
         else if(land.equals("Frequent"))
            land = "None";
         else if(land.equals("None"))
            land = "Rare";
         else land = "Regular";
         button2.setText(land);
      }
   }
   private class Listener3 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         timer += 2;
         if(timer >= 50)
            timer = 0;
         button5.setText("T1: " + timer);
      }
   }
   private class Listener4 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         exptimer += 2;
         if(exptimer >= 50)
            exptimer = 0;
         button6.setText("T2: " + exptimer);
      }
   }
   private class Listener5 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         speed += 1;
         if(speed >= 15)
            speed = 5;
         button3.setText("T1: " + speed);
      }
   }
   private class Listener6 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         speed2 += 1;
         if(speed2 >= 15)
            speed2 = 5;
         button4.setText("T2: " + speed);
      }
   }

}