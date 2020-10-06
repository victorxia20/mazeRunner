//   Edward and Victor
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public class TestPanel extends JPanel
{
   //constants
   private static final int FRAME = 400;   
   private static final Color BACKGROUND = new Color(204, 204, 204);
   //fields
   private boolean aPressed,wPressed,sPressed,dPressed,ePressed;
   private int count=101, count2=101,expX,expY,bX,bY;
   private BufferedImage myImage;
   private Graphics myBuffer;
   private Timer t;
   private Tank victor;
   private ImageIcon explode;
   public TestPanel()
   {
      explode=new ImageIcon("Explosion.png");
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0, 0, FRAME, FRAME);
      victor = new Tank(200,200,1,1,"a",50,Color.BLACK);
      t = new Timer(10, new Listener());
      addKeyListener(new Key());
      setFocusable(true);
      t.start();
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         count++;
         count2++;
         myBuffer.setColor(BACKGROUND);
         if(aPressed)
         {
         victor.setDir(victor.getDir()+5);
         }
         if(wPressed)
         {
         victor.setX(victor.getX()+(int)(5*(Math.cos(Math.toRadians(victor.getDir())))));
            victor.setY(victor.getY()-(int)(5*(Math.sin(Math.toRadians(victor.getDir())))));
         }
         if(dPressed)
         {
         victor.setDir(victor.getDir()-5);
         }
         if(sPressed)
         {
         victor.setX(victor.getX()-(int)(5*(Math.cos(Math.toRadians(victor.getDir())))));
            victor.setY(victor.getY()+(int)(5*(Math.sin(Math.toRadians(victor.getDir())))));
         }
         if(count<100)
         {
         if(ePressed)
         {
         victor.shoot(new Bomb(100),myBuffer);
         count=0;
         ePressed=false;
         myBuffer.setColor(BACKGROUND);
         }
         }
         if(count==1)
         {
         bX=victor.getBombX();
         bY=victor.getBombY();
         }
         if(count<100&&count2>100)
         {
         myBuffer.fillRect(0,0,FRAME,FRAME);
         myBuffer.setColor(Color.BLACK);
         myBuffer.fillOval(bX,bY,30,30);
         myBuffer.setColor(BACKGROUND);
         }
         if(count<100&&count2<100)
         {
            myBuffer.fillRect(0,0,FRAME,FRAME);
            myBuffer.setColor(Color.BLACK);
            myBuffer.fillOval(bX,bY,30,30);
            myBuffer.setColor(BACKGROUND);
            myBuffer.drawImage(explode.getImage(),expX,expY,75,75, null);
         }
         if(count==100)
         {
            expX=victor.getBombX();
            expY=victor.getBombY();
            myBuffer.fillRect(0,0,FRAME,FRAME);
            count2=0;
         }
         if(count>100)
         {
            myBuffer.fillRect(0,0,FRAME,FRAME);
            myBuffer.drawImage(explode.getImage(),expX,expY,75,75, null);
         }
         if(count>100&&count2>100)
         {
            myBuffer.fillRect(0,0,FRAME,FRAME);
         }
         victor.draw(myBuffer);
         repaint();
      }
   }
   private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(count>=100)
         {
            if(e.getKeyCode() == KeyEvent.VK_E)
            {
             ePressed=true;
             count=0; 
            }
         }
         if(e.getKeyCode() == KeyEvent.VK_A)
            aPressed=true;
         if(e.getKeyCode() == KeyEvent.VK_D)
            dPressed=true;
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            wPressed=true;
         }
         if(e.getKeyCode() == KeyEvent.VK_S)
         {
            sPressed=true;
         }
      }
      public void keyReleased(KeyEvent e)
      {
            if(e.getKeyCode() == KeyEvent.VK_E)
            {
             ePressed=false;  
            }
         if(e.getKeyCode() == KeyEvent.VK_A)
            aPressed=false;
         if(e.getKeyCode() == KeyEvent.VK_D)
            dPressed=false;
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            wPressed=false;
         }
         if(e.getKeyCode() == KeyEvent.VK_S)
         {
            sPressed=false;
         }
      }
   }
}