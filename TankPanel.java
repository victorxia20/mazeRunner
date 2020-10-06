//Victor and Edward
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TankPanel extends JPanel
{
   JTextField text1;
   JButton button2, button3, button4, button5, button6;
   public TankPanel(Tank tank)
   {
      
      setLayout(new BorderLayout());
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(4, 2));
      add(panel);
      JLabel label = new JLabel("Tank Customization");
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label.setFont(new Font("Serif", Font.BOLD, 23));
      add(label, BorderLayout.NORTH);
      JLabel label1 = new JLabel("Tank Name");
      label1.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(label1);
      text1 = new JTextField("A Tank");
      panel.add(text1);
      JLabel label2 = new JLabel("Tank Color");
      label2.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(label2);
      button2 = new JButton("Red");
      button2.setBackground(Color.red);
      button2.addActionListener(new Listener());
      panel.add(button2);
      JLabel label3 = new JLabel("Primary Weapon");
      label3.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(label3);
      button3 = new JButton("Bombs");
      button3.addActionListener(new Listener1());
      panel.add(button3);
      JLabel label4 = new JLabel("Secondary Weapon");
      label4.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(label4);
      button4 = new JButton("Boost");
      button4.addActionListener(new Listener2());
      panel.add(button4);
   }
   public String getName()
   {
      return text1.getText();
   }
   public String getColor()
   {
      return button2.getText();
   }
   public Color getColors()
   {
      return button2.getBackground();
   }
   public int getRed()
   {
      return button2.getBackground().getRed();
   }
   public int getGreen()
   {
      return button2.getBackground().getGreen();
   }
   public int getBlue()
   {
      return button2.getBackground().getBlue();
   }
   public String getPWeap()
   {
      return button3.getText();
   }
   public String getSWeap()
   {
      return button4.getText();
   }
   public void setName(String s)
   {
      text1.setText(s);
   }
   public void setColor(int r, int g, int b, String c)
   {
      button2.setBackground(new Color(r, g, b));
      button2.setText("" + c);
   }
   public void setPWeap(String s)
   {
      button3.setText(s);
   }
   public void setSWeap(String s)
   {
      button4.setText(s);
   }
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(button2.getText().equals("Red"))
         {
            button2.setText("Orange");
            button2.setBackground(Color.orange);
         }
         else if(button2.getText().equals("Orange"))
         {
            button2.setText("Yellow");
            button2.setBackground(Color.yellow);
         }
         else if(button2.getText().equals("Yellow"))
         {
            button2.setText("Green");
            button2.setBackground(Color.green);
         }
         else if(button2.getText().equals("Green"))
         {
            button2.setText("Blue");
            button2.setBackground(Color.blue);
         }
         else if(button2.getText().equals("Blue"))
         {
            button2.setText("Magenta");
            button2.setBackground(Color.magenta);
         }
         else if(button2.getText().equals("Magenta"))
         {
            button2.setText("Pink");
            button2.setBackground(Color.pink);
         }
         else{
            button2.setText("Red");
            button2.setBackground(Color.red);
         }
      }
   }
   private class Listener1 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(button3.getText().equals("Bombs"))
            button3.setText("Laser");
         else button3.setText("Bombs");
      }
   }
   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
      
      }
   }
   }