//Edward and Victor
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Tank
{
   private int myX,myY, myPWeap,mySWeap,bombX,bombY;
   private String myName;
   private double myDir;
   private Color myColor;
   private double myRadius;

   public Tank()
   {
      myX=200;
      myY=200;
      myPWeap=1;
      mySWeap=1;
      myName="tank";
      myDir=0;
      myColor=Color.BLACK;
      myRadius=25;
   }
   public Tank(int x,int y, int pWeap, int sWeap,String s, double dir,Color c)
   {
      myX=x;
      myY=y;
      myPWeap=pWeap;
      mySWeap=sWeap;
      myName=s;
      myDir=dir;
      myColor=c;
      myRadius=25;
   }
   public int getX()
   {
      return myX;
   }
   public int getY()
   {
      return myY;
   }
   public int getPWeap()
   {
      return myPWeap;
   }
   public int getSWeap()
   {
      return mySWeap;
   }
   public String getName()
   {
      return myName;
   }
   public double getDir()
   {
      return myDir;
   }
   public Color getColor()
   {
      return myColor;
   }
   public double getRadius()
   {
      return myRadius;
   }
   public int getBombX()
   {
   return bombX;
   }
   public int getBombY()
   {
   return bombY;
   }
   public void setX(int x)
   {
      myX=x;
   }
   public void setY(int y)
   {
      myY=y;
   }
   public void setPWeap(int x)
   {
      myPWeap=x;
   }
   public void setSWeap(int x)
   {
      mySWeap=x;
   }
   public void setName(String x)
   {
      myName=x;
   }
   public void setDir(double x)
   {
      myDir=x;
   }
   public void setRadius(double x)
   {
      myRadius=x;
   }
   public void setColor(Color c)
   {
      myColor=c;
   }
   public void setBombX(int x)
   {
   bombX=x;
   }
   public void setBombY(int y)
   {
   bombY=y;
   }
   public void shoot(PWeap arg,Graphics myBuffer)
   {
      arg.shoot(myX,myY,myDir,myBuffer);
      bombX=arg.getX();
      bombY=arg.getY();
   }
   public void activate(SWeap arg)
   {
      arg.activate();
   }
   public void draw(Graphics myBuffer) 
   {
      Graphics2D myBuffer2D=(Graphics2D)myBuffer;
      myBuffer.setColor(getColor());
      myBuffer.fillOval((int)getX()-(int)getRadius(), (int)getY()-(int)getRadius(), (int)getRadius()*2, (int)getRadius()*2);
      Rectangle2D rect = new Rectangle2D.Double(0, -25/4, 50, 25/2);
      AffineTransform transform = new AffineTransform();
      transform.translate(myX,myY);
      transform.rotate(Math.toRadians(-myDir));
      Shape rotatedRect = transform.createTransformedShape(rect);
      myBuffer2D.fill(rotatedRect);
   }   
}