//Edward and Victor
import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
public class Laser extends PWeap
{
private int myDist,myX,myY;
public Laser()
{
myDist=0;
}
public Laser(int dist)
{
myDist=dist;
}
public int getDist()
{
return myDist;
}
public int getX()
{
return myX;
}
public int getY()
{
return myY;
}
public void shoot(int x,int y,double dir,Graphics graphics)
{
Graphics2D myBuffer2D=(Graphics2D)graphics;
if(myDist<300)
graphics.setColor(Color.BLUE);
else
graphics.setColor(Color.RED);

if(myDist>600)
myDist=600;
Rectangle2D rect = new Rectangle2D.Double(0, -2 , myDist, 2);
AffineTransform transform = new AffineTransform();
transform.translate(x,y);
transform.rotate(Math.toRadians(-dir));
Shape rotatedRect = transform.createTransformedShape(rect);
myBuffer2D.fill(rotatedRect);
myX=x;
myY=y;
}
}