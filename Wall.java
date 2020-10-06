// Victor and Edward
import java.awt.*;

public class Wall 
{
   private int myX,myY;
   private boolean myDir;
   public Wall(int x,int y,boolean dir)
   {
      myX=x;
      myY=y;
      myDir=dir;
   }
   public void setX(int x)
   {
      myX=x;
   }
   public void setY(int x)
   {
      myY=x;
   }
   public void setDir(boolean dir)
   {
      myDir=dir;
   }
   public int getX()
   {
      return myX;
   }
   public int getY()
   {
      return myY;
   }
   public boolean getDir()
   {
      return myDir;
   }
   public void draw(Graphics myBuffer)
   {
      myBuffer.setColor(Color.BLACK);
      if(myDir)
      {
         myBuffer.fillRect(myX-5,myY,10,100);
      }
      else
      {
         myBuffer.fillRect(myX,myY-5,100,10);
      }
   }
   public boolean collide(Tank arg)
   {
      if(!myDir)
      {
         if(arg.getX()<myX+125&&arg.getX()>myX-25&&Math.abs(arg.getY()-myY)<30)
            return true;
         else 
            return false;
      }
      else
      {
         if(arg.getY()<myY+125&&arg.getY()>myY-25&&Math.abs(arg.getX()-myX)<30)
            return true;
         else 
            return false;
      }
   }
   private double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }	
}