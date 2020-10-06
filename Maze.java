//Edward and Victor
import java.util.Scanner;
import java.text.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public class Maze extends JPanel
{
   private BufferedImage myImage;
   private Graphics myBuffer;
   private ImageIcon explode;
   private final int SIZE=100;
   private static final Color BACKGROUND = new Color(204, 204, 204);
   private int[][] arrayX;
   private int[][] arrayY;
   private int[][][]maze;
   
   public Maze()
   {
      myImage =  new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0, 0, 1000, 1000);
      maze = createMaze(SIZE);
      arrayX= new int[SIZE][SIZE];
      arrayY=new int[SIZE][SIZE];
      for(int a=0;a<SIZE;a++)
      {
         for(int b=0;b<SIZE;b++)
         {
            arrayX[a][b] = maze[a][b][0];
            arrayY[a][b] = maze[a][b][1];
         }
      }
   myBuffer.setColor(Color.RED);
   myBuffer.drawLine(0,0,999,0);
   myBuffer.drawLine(0,0,0,999);
   myBuffer.drawLine(999,0,999,999);
   myBuffer.drawLine(0,999,999,999);
   for(int a=0;a<SIZE;a++)
   {
   for(int b=0;b<SIZE;b++)
   {
   if(arrayX[b][a]==1)
   {
   myBuffer.drawLine(a*(int)(1000/SIZE),b*(int)(1000/SIZE),a*(int)(1000/SIZE),(b+1)*(int)(1000/SIZE));
   }
   if(arrayY[b][a]==1)
   {
   myBuffer.drawLine(a*(int)(1000/SIZE),b*(int)(1000/SIZE),(a+1)*(int)(1000/SIZE),b*(int)(1000/SIZE));
   }
   }
   }
   }
   public void paintComponent(Graphics g)
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
   public static int [][][] createMaze(int x)
   {
      int totalVisited=0;
      int search=0;
      int count=0;
      int [][][] walls = new int[x][x][3];
      for(int a=0;a<x;a++)
      {
         for(int b=0;b<x;b++)
         {
           walls[a][b][2]=0;
            for(int c=0;c<2;c++)
            {
               walls[a][b][c]=1;
            }
         }
      }
      int startX=(int)(Math.random()*x);
      int startY=(int)(Math.random()*x);
      totalVisited++;
      search++;
      walls[startX][startY][2]=search;
      int rand=0;
      while(totalVisited<x*x)
      {
         if(isNeighbor(startX,startY,walls))
         {
            count=totalVisited;
            while(count==totalVisited)
            {
               rand=(int)(Math.random()*4);
               switch(rand)
               {
                  case 0: 
                     if(!isChecked(startX+1,startY,walls))
                     {
                        totalVisited++;
                        search++;
                        walls[startX+1][startY][2]=search;
                        walls[startX+1][startY][1]=0;
                        startX=startX+1;
                     }
                     break;
                  case 1: 
                     if(!isChecked(startX-1,startY,walls))
                     {
                        totalVisited++;
                        search++;
                        walls[startX-1][startY][2]=search;
                        walls[startX][startY][1]=0;
                        startX=startX-1;
                     }
                     break;
                  case 2: 
                     if(!isChecked(startX,startY+1,walls))
                     {
                        totalVisited++;
                        search++;
                        walls[startX][startY+1][2]=search;
                        walls[startX][startY+1][0]=0;
                        startY=startY+1;
                     }
                     break;
                  case 3:
                     if(!isChecked(startX,startY-1,walls))
                     {
                        totalVisited++;
                        search++;
                        walls[startX][startY-1][2]=search;
                        walls[startX][startY][0]=0;
                        startY=startY-1;
                     }
                     break;
               }
            }
         }
         else
         {
            search--;
            if(startX-1>-1)
            {
            if(walls[startX-1][startY][2]==search)
            {
               startX=startX-1;
            }
            }
            if(startX+1<walls.length)
            {
            if(walls[startX+1][startY][2]==search)
            {
               startX=startX+1;
            }
            }
            if(startY-1>-1)
            {
            if(walls[startX][startY-1][2]==search)
            {
               startY=startY-1;
            }
            }
            if(startY+1<walls.length)
            {
            if(walls[startX][startY+1][2]==search)
            {
               startY=startY+1;
            }
            }
         }
      }
      return walls;
   }
   
   public static boolean isNeighbor(int x,int y,int[][][] walls)
   {
      if(x+1<walls.length)
      {
         if(walls[x+1][y][2]==0)
            return true;
      }
      if(x-1>-1)
      {
         if(walls[x-1][y][2]==0)
            return true;
      }
      if(y+1<walls.length)
      {
         if(walls[x][y+1][2]==0)
            return true;
      }
      if(y-1>-1)
      {
         if(walls[x][y-1][2]==0)
            return true;
      }
      return false;
   }
   public static boolean isChecked(int x,int y,int[][][] walls)
   {
      if(x>-1&&x<walls.length&&y>-1&&y<walls.length)
      {
         if(walls[x][y][2]==0)
            return false;
         else
            return true;
      }
      else
         return true;
   }
}