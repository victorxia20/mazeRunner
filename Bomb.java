//Edward and Victor
import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
public class Bomb extends PWeap
{
private int myDist,myX,myY;
public Bomb()
{
myDist=200;
}
public Bomb(int dist)
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
graphics.setColor(Color.BLACK);
graphics.fillOval((int)(Math.cos(Math.toRadians(dir))*myDist+x-15),(int)(-Math.sin(Math.toRadians(dir))*myDist+y-15),30,30);
myX=(int)(Math.cos(Math.toRadians(dir))*myDist+x-15);
myY=(int)(-Math.sin(Math.toRadians(dir))*myDist+y-15);
}
}