//Edward and Victor
import java.util.Scanner;
import java.text.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
public class Map extends JPanel
{
   private static  int FRAME = 500;  
   private static  int  bombtimev= 50;
   private static  int  bombtimee= 50;
   private static  int turnspeede= 5;
   private static  int turnspeedv= 5;
   private static  int speede = 5;
   private static  int speedv = 5;
   private static  int INVINCIBLE = 600;
   private static  int DEATH = 400;
   private static  int rangev = 200;
   private static  int rangee = 200;
   private static int cooldown = 1200;
   private static int chargetimee = 100;
   private static int chargetimev=100;
   private boolean [][] traveledE, traveledV;
   private static final Color BACKGROUND = new Color(204, 204, 204);
   public boolean aPressed,wPressed,sPressed,dPressed,ePressed,pPressed,upPressed,downPressed,rightPressed,leftPressed,vDead=false,eDead=false,qPressed,lPressed;
   private int count=101, count2=101,count3=101,count4=101,expX,expY,bX,bY,expX2,expY2,bX2,bY2,expXV2,expYV2,bXV2,bYV2,expXE2,expYE2,bXE2,bYE2,deathCountV=300,deathCountE=300,wallCount=0,traveledCountV,traveledCountE,s,activateCountV=200,activateCountE=200,laserCountV=11,laserCountE=11,laserDistE,laserDistV;
   private BufferedImage myImage;
   private Graphics myBuffer;
   private ImageIcon explode;
   int[][] arrayX;
   int[][] arrayY;
   int[][][] maze;
   private Tank victor, ed, victor2, ed2;
   int xPos1, yPos1, xPos2, yPos2;
   private Timer t;
   private Scanner infile;
   private Graphics2D myBuffer2D;
   public Map()
   {
      try{
         infile = new Scanner(new File("data.txt"));
      }
      catch(FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null, "Error 404 file not found");
      }
      infile.nextLine();
      s = Integer.parseInt(infile.nextLine());
      traveledE = new boolean[s][s];
      traveledV = new boolean[s][s];
      arrayX = new int[s][s];
      arrayY = new int[s][s];
      maze = Maze.createMaze(s-14);
      for(int x=0;x<arrayX.length;x++)
      {
         for(int y=0;y<arrayX[0].length;y++)
         {
            arrayX[y][x] = 0;
            arrayY[y][x] = 0;
            traveledE[y][x]=false;
            traveledV[y][x]=false;
         }
      }
      for(int a=7;a<s-7;a++)
      {
         for(int b=7;b<s-7;b++)
         {
            arrayX[b][a] = maze[a-7][b-7][0];
            arrayY[b][a] = maze[a-7][b-7][1];
         }
      }
      int randX= 0;
      int randY=0;
      int coin=0;
      while(wallCount<(s-14)*(s-14)/4)
      {
         randX=(int)(Math.random()*(s-14)*100+700);
         randY=(int)(Math.random()*(s-14)*100+700);
         coin=(int)(Math.random()*2);
         if(coin==0)
         {
            if(arrayX[(int)(randX/100)][(int)(randY/100)]==1)
            {
               arrayX[(int)(randX/100)][(int)(randY/100)]=0;
               wallCount++;
            }
         }
         else
         {
            if(arrayY[(int)(randX/100)][(int)(randY/100)]==1)
            {
               arrayY[(int)(randX/100)][(int)(randY/100)]=0;
               wallCount++;
            }
         }
      }
      for(int a=7;a<=s-7;a++)
      {
         for(int b=7;b<=s-7;b++)
         {
            if(b==7||b==s-7)
            {
               arrayY[a][b]=1;
            }
            if(a==7||a==s-7)
            {
               arrayX[a][b]=1;
            }
         }
      }
      infile.nextLine();
      infile.nextLine();
      xPos1=((int)(Math.random()*(s-14)*100+700))/100*100+50;
      yPos1=((int)(Math.random()*(s-14)*100+700))/100*100+50;
      xPos2=((int)(Math.random()*(s-14)*100+700))/100*100+50;
      yPos2=((int)(Math.random()*(s-14)*100+700))/100*100+50;
      victor=new Tank(500,500,1,1,infile.nextLine(),180,new Color(Integer.parseInt(infile.nextLine()), Integer.parseInt(infile.nextLine()), Integer.parseInt(infile.nextLine())));
      infile.nextLine();
      if(infile.nextLine().equals("Bombs"))
         victor.setPWeap(1);
      else victor.setPWeap(2);
      if(infile.nextLine().equals("Boost"))
         victor.setSWeap(1);
      infile.nextLine();
      ed = new Tank(1600, 500, 1, 1, infile.nextLine(),0,new Color(Integer.parseInt(infile.nextLine()), Integer.parseInt(infile.nextLine()), Integer.parseInt(infile.nextLine())));
      infile.nextLine();
      if(infile.nextLine().equals("Bombs"))
         ed.setPWeap(1);
      else ed.setPWeap(2);
      if(infile.nextLine().equals("Boost"))
         ed.setSWeap(1);
      bombtimev = Integer.parseInt(infile.nextLine());
      bombtimee = Integer.parseInt(infile.nextLine());
      turnspeedv = Integer.parseInt(infile.nextLine());
      turnspeede = Integer.parseInt(infile.nextLine());
      infile.close();
      victor2=new Tank(xPos1-xPos2+1600,yPos1-yPos2+500,1,1,victor.getName(),victor.getDir(),victor.getColor());
      ed2 = new Tank(xPos2-xPos1+500, yPos2-yPos1+500, 1, 1, ed.getName(), ed.getDir(), ed.getColor());
      explode=new ImageIcon("Explosion.png");
      myImage =  new BufferedImage(2100, 1000, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer2D=(Graphics2D)myBuffer;
      myBuffer.fillRect(0, 0, 1000, 500);
      t = new Timer(10, new Listener());
      addKeyListener(new Key());
      setFocusable(true);
      if(victor.getPWeap()==2)
      {
         turnspeedv=10;
      }
      if(ed.getPWeap()==2)
      {
         turnspeede=10;
      }
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
         deathCountV++;
         deathCountE++;
         activateCountE++;
         activateCountV++;
         if(victor.getPWeap()==2&&ePressed&&laserCountV>2)  
            laserCountV++;
         if(ed.getPWeap()==2&&pPressed&&laserCountE>2)  
         {
            laserCountE++;
         }
         if(laserCountV<=10)
            laserCountV++;
         if(laserCountE<=10)
            laserCountE++;
         if(victor.getSWeap()==1&&activateCountV>cooldown&&qPressed)
         {
            speedv=speedv*2;
            rangev=rangev+100;
            bombtimev=10;
            turnspeedv=10;
            activateCountV=0;
         }
         if(victor.getSWeap()==1&&activateCountV==200)
         {
            speedv=(speedv+1)/2;
            rangev=rangev-100;
            bombtimev=50;
            if(victor.getPWeap()!=2)
               turnspeedv=5;
         }
         if(ed.getSWeap()==1&&activateCountE>cooldown&&lPressed)
         {
            speede=speede*2;
            rangee=rangee+100;
            bombtimee=10;
            turnspeede=10;
            activateCountE=0;
         }
         if(ed.getSWeap()==1&&activateCountE==200)
         {
            speede=(speede+1)/2;
            rangee=rangee-100;
            bombtimee=50;
            if(ed.getPWeap()!=2)
               turnspeede=5;
         }
         if(deathCountE>DEATH)
         {
            eDead=false;
         }
         if(deathCountV>DEATH)
         {
            vDead=false;
         }
         if(aPressed)
         {
            victor.setDir(victor.getDir()+turnspeedv);
            victor2.setDir(victor2.getDir()+turnspeedv);
         }
         if(dPressed)
         {
            victor.setDir(victor.getDir()-turnspeedv);
            victor2.setDir(victor2.getDir()-turnspeedv);
         }
         if(wPressed)
         {
            if(!vDead)
            {
               xPos1=xPos1+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               yPos1=yPos1-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
               victor2.setX(victor2.getX()+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir())))));
               victor2.setY(victor2.getY()-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir())))));
               ed2.setX(ed2.getX()-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir())))));
               ed2.setY(ed2.getY()+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir())))));
               bX=bX-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               bY=bY+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
               bXE2=bXE2-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               bYE2=bYE2+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
               expX=expX-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               expY=expY+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
               expXE2=expXE2-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               expYE2=expYE2+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
            }
         }
         if(sPressed)
         {
            if(!vDead)
            {
               xPos1=xPos1-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               yPos1=yPos1+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
               victor2.setX(victor2.getX()-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir())))));
               victor2.setY(victor2.getY()+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir())))));
               ed2.setX(ed2.getX()+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir())))));
               ed2.setY(ed2.getY()-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir())))));
               bX=bX+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               bY=bY-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
               expX=expX+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               expY=expY-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
               bXE2=bXE2+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               bYE2=bYE2-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
               expXE2=expXE2+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
               expYE2=expYE2-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
            }
         }
         if(count<bombtimev&&victor.getPWeap()==1)
         {
            if(ePressed)
            {
               if(!vDead)
               {
                  victor.shoot(new Bomb(rangev),myBuffer);
                  count=0;
                  myBuffer.setColor(BACKGROUND);
               }
            }
         }
         if(count==1&&victor.getPWeap()==1)
         {
            bX=victor.getBombX();
            bY=victor.getBombY();
            bXV2=victor2.getBombX();
            bYV2=victor2.getBombY();
         }
         if(count<bombtimev&&count2>bombtimev&&victor.getPWeap()==1)
         {
            myBuffer.setColor(BACKGROUND);
            gridLines();
            myBuffer.setColor(Color.BLACK);
            myBuffer.fillOval(bX,bY,30,30);
            if(bXV2>1000)
               myBuffer.fillOval(bXV2,bYV2,30,30);
            myBuffer.setColor(BACKGROUND);
         }
         if(count<bombtimev&&count2<bombtimev&&victor.getPWeap()==1)
         {
            gridLines();
            myBuffer.setColor(Color.BLACK);
            myBuffer.fillOval(bX,bY,30,30);
            if(bXV2>1000)
               myBuffer.fillOval(bXV2,bYV2,30,30);
            myBuffer.setColor(BACKGROUND);
            myBuffer.drawImage(explode.getImage(),expX,expY,75,75, null);
            if(expXV2>1000)
               myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
            if(victor.getPWeap()==1&&isDeadBomb(ed,expXV2,expYV2)&&deathCountE>INVINCIBLE)
            {
               eDead=true;
               deathCountE=0;
            }
         }
         if(count==bombtimev&&victor.getPWeap()==1)
         {
            expX=bX;
            expY=bY;
            expXV2=bXV2;
            expYV2=bYV2;
            gridLines();
            count2=0;
         }
         if(count>bombtimev&&count2<bombtimev&&victor.getPWeap()==1)
         {
            gridLines();
            myBuffer.drawImage(explode.getImage(),expX,expY,75,75, null);
            if(expXV2>1000)
               myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
            if(victor.getPWeap()==1&&isDeadBomb(ed,expXV2,expYV2)&&deathCountE>INVINCIBLE)
            {
               deathCountE=0;
               eDead=true;
            }
         }
         if(count>bombtimev&&count2>bombtimev&&victor.getPWeap()==1)
         {
            gridLines();
         }
         
         
         
         
         
         
         count3++;
         count4++;
         if(leftPressed)
         {
            ed.setDir(ed.getDir()+turnspeede);
            ed2.setDir(ed2.getDir()+turnspeede);
         }
         if(rightPressed)
         {
            ed.setDir(ed.getDir()-turnspeede);
            ed2.setDir(ed2.getDir()-turnspeede);
         }
         if(upPressed)
         {
            if(!eDead)
            {
               xPos2=xPos2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               yPos2=yPos2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
               ed2.setX(ed2.getX()+(int)(speede*(Math.cos(Math.toRadians(ed.getDir())))));
               ed2.setY(ed2.getY()-(int)(speede*(Math.sin(Math.toRadians(ed.getDir())))));
               victor2.setX(victor2.getX()-(int)(speede*(Math.cos(Math.toRadians(ed.getDir())))));
               victor2.setY(victor2.getY()+(int)(speede*(Math.sin(Math.toRadians(ed.getDir())))));
               bX2=bX2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               bY2=bY2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
               expX2=expX2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               expY2=expY2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
               bXV2=bXV2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               bYV2=bYV2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
               expXV2=expXV2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               expYV2=expYV2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
            }
         }
         if(downPressed)
         {
            if(!eDead)
            {
               xPos2=xPos2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               yPos2=yPos2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
               ed2.setX(ed2.getX()-(int)(speede*(Math.cos(Math.toRadians(ed.getDir())))));
               ed2.setY(ed2.getY()+(int)(speede*(Math.sin(Math.toRadians(ed.getDir())))));
               victor2.setX(victor2.getX()+(int)(speede*(Math.cos(Math.toRadians(ed.getDir())))));
               victor2.setY(victor2.getY()-(int)(speede*(Math.sin(Math.toRadians(ed.getDir())))));
               bX2=bX2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               bY2=bY2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
               expX2=expX2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               expY2=expY2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
               bXV2=bXV2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               bYV2=bYV2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
               expXV2=expXV2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
               expYV2=expYV2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
            }
         }
         if(count3<bombtimee&&ed.getPWeap()==1)
         {
            if(pPressed&&ed.getPWeap()==1)
            {
               if(!eDead)
               {
                  ed.shoot(new Bomb(rangee),myBuffer);
                  ed2.shoot(new Bomb(rangee),myBuffer);
                  count3=0;
                  pPressed=false;
                  myBuffer.setColor(BACKGROUND);
               }
            }
         }
         if(count3==1&&ed.getPWeap()==1)
         {
            bX2=ed.getBombX();
            bY2=ed.getBombY();
            bXE2=ed2.getBombX();
            bYE2=ed2.getBombY();
         }
         if(count3<bombtimee&&count4>bombtimee&&ed.getPWeap()==1)
         {
            myBuffer.setColor(BACKGROUND);
            gridLines2();
            myBuffer.setColor(Color.BLACK);
            if(bX2>1000)
               myBuffer.fillOval(bX2,bY2,30,30);
            if(bXE2<1000)
               myBuffer.fillOval(bXE2,bYE2,30,30);
            if(count<bombtimev)
            {
               if(ePressed&&victor.getPWeap()==1)
               {
                  if(!vDead)
                  {
                     myBuffer.setColor(Color.BLACK);
                     victor2.shoot(new Bomb(rangev),myBuffer);
                     ePressed=false;
                     myBuffer.setColor(BACKGROUND);
                  }
               }
            }
            if(count==1&&victor.getPWeap()==1)
            {
               bXV2=victor2.getBombX();
               bYV2=victor2.getBombY();
            }
            if(count<bombtimev&&count2>bombtimev&&victor.getPWeap()==1)
            {
               myBuffer.setColor(Color.BLACK);
               if(bXV2>1000)
                  myBuffer.fillOval(bXV2,bYV2,30,30);
            }
            if(count<bombtimev&&count2<bombtimev&&victor.getPWeap()==1)
            {
               myBuffer.setColor(Color.BLACK);
               if(bXV2>1000)
                  myBuffer.fillOval(bXV2,bYV2,30,30);
               if(expXV2>1000)
                  myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
               if(victor.getPWeap()==1&&isDeadBomb(ed,expXV2,expYV2)&&deathCountE>INVINCIBLE)
               {
                  deathCountE=0;
                  eDead=true;
               }
            }
            if(count==bombtimev&&victor.getPWeap()==1)
            {
               expXV2=bXV2;
               expYV2=bYV2;
               count2=0;
            }
            if(count>bombtimev&&count2<bombtimev&&victor.getPWeap()==1)
            {
               if(expXV2>1000)
                  myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
               if(victor.getPWeap()==1&&isDeadBomb(ed,expXV2,expYV2)&&deathCountE>INVINCIBLE)
               {
                  deathCountE=0;
                  eDead=true;
               }
            }
            myBuffer.setColor(BACKGROUND);
         }
         if(count3<bombtimee&&count4<bombtimee&&ed.getPWeap()==1)
         {
            gridLines2();
            myBuffer.setColor(Color.BLACK);
            if(bX2>1000)
               myBuffer.fillOval(bX2,bY2,30,30);
            if(bXE2<1000)
               myBuffer.fillOval(bXE2,bYE2,30,30);
            myBuffer.setColor(BACKGROUND);
            if(expX2>1000)
               myBuffer.drawImage(explode.getImage(),expX2,expY2,75,75, null);
            if(expXE2<1000)
               myBuffer.drawImage(explode.getImage(),expXE2,expYE2,75,75, null);
            if(ed.getPWeap()==1&&isDeadBomb(victor,expXE2,expYE2)&&deathCountV>INVINCIBLE)
            {
               deathCountV=0;
               vDead=true;
            }
            if(count<bombtimev)
            {
               if(ePressed&&victor.getPWeap()==1)
               {
                  if(!vDead)
                  {
                     myBuffer.setColor(Color.BLACK);
                     victor2.shoot(new Bomb(rangev),myBuffer);
                     ePressed=false;
                     myBuffer.setColor(BACKGROUND);
                  }
               }
            }
            if(count==1&&victor.getPWeap()==1)
            {
               bXV2=victor2.getBombX();
               bYV2=victor2.getBombY();
            }
            if(count<bombtimev&&count2>bombtimev&&victor.getPWeap()==1)
            {
               myBuffer.setColor(Color.BLACK);
               if(bXV2>1050)
                  myBuffer.fillOval(bXV2,bYV2,30,30);
            }
            if(count<bombtimev&&count2<bombtimev&&victor.getPWeap()==1)
            {
               myBuffer.setColor(Color.BLACK);
               if(bXV2>1050)
                  myBuffer.fillOval(bXV2,bYV2,30,30);
               if(expXV2>1000)
                  myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
            }
            if(count==bombtimev&&victor.getPWeap()==1)
            {
               expXV2=bXV2;
               expYV2=bYV2;
               count2=0;
            }
            if(count>bombtimev&&count2<bombtimev&&victor.getPWeap()==1)
            {
               if(expXV2>1000)
                  myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
               if(victor.getPWeap()==1&&isDeadBomb(ed,expXV2,expYV2)&&deathCountE>INVINCIBLE)
               {
                  deathCountE=0;
                  eDead=true;
               }
            }
         }
         if(count3==bombtimee&&ed.getPWeap()==1)
         {
            expX2=bX2;
            expY2=bY2;
            expXE2=bXE2;
            expYE2=bYE2;
            gridLines2();
            count4=0;
         }
         if(count3>bombtimee&&count4<bombtimee&&ed.getPWeap()==1)
         {
            gridLines2();
            if(expX2>1000)
               myBuffer.drawImage(explode.getImage(),expX2,expY2,75,75, null);
            if(expXE2<1000)
               myBuffer.drawImage(explode.getImage(),expXE2,expYE2,75,75, null); 
            if(ed.getPWeap()==1&&isDeadBomb(victor,expXE2,expYE2)&&deathCountV>INVINCIBLE)
            {
               deathCountV=0;
               vDead=true;
            }
            if(count<bombtimev)
            {
               if(ePressed&&victor.getPWeap()==1)
               {
                  if(!vDead)
                  {
                     myBuffer.setColor(Color.BLACK);
                     victor2.shoot(new Bomb(rangev),myBuffer);
                     ePressed=false;
                     myBuffer.setColor(BACKGROUND);
                  }
               }
            }
            if(count==1)
            {
               bXV2=victor2.getBombX();
               bYV2=victor2.getBombY();
            }
            if(count<bombtimev&&count2>bombtimev)
            {
               myBuffer.setColor(Color.BLACK);
               if(bXV2>1050)
                  myBuffer.fillOval(bXV2,bYV2,30,30);
            }
            if(count<bombtimev&&count2<bombtimev)
            {
               myBuffer.setColor(Color.BLACK);
               if(bXV2>1050)
                  myBuffer.fillOval(bXV2,bYV2,30,30);
               if(expXV2>1000)
                  myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
               if(victor.getPWeap()==1&&isDeadBomb(ed,expXV2,expYV2)&&deathCountE>INVINCIBLE)
               {
                  deathCountE=0;
                  eDead=true;
               }
            }
            if(count==bombtimev)
            {
               expXV2=bXV2;
               expYV2=bYV2;
               count2=0;
            }
            if(count>bombtimev&&count2<bombtimev)
            {
               if(expXV2>1000)
                  myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
               if(victor.getPWeap()==1&&isDeadBomb(ed,expXV2,expYV2)&&deathCountE>INVINCIBLE)
               {
                  deathCountE=0;
                  eDead=true;
               }
            }
         }
         if(count3>bombtimee&&count4>bombtimee&&ed.getPWeap()==1)
         {
            gridLines2();
            if(count<bombtimev)
            {
               if(ePressed&&victor.getPWeap()==1)
               {
                  if(!vDead)
                  {
                     myBuffer.setColor(Color.BLACK);
                     victor2.shoot(new Bomb(rangev),myBuffer);
                     ePressed=false;
                     myBuffer.setColor(BACKGROUND);
                  }
               }
            }
            if(count==1)
            {
               bXV2=victor2.getBombX();
               bYV2=victor2.getBombY();
            }
            if(count<bombtimev&&count2>bombtimev)
            {
               myBuffer.setColor(Color.BLACK);
               if(bXV2>1050)
                  myBuffer.fillOval(bXV2,bYV2,30,30);
            }
            if(count<bombtimev&&count2<bombtimev)
            {
               myBuffer.setColor(Color.BLACK);
               if(bXV2>1050)
                  myBuffer.fillOval(bXV2,bYV2,30,30);
               if(expXV2>1000)
                  myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
               if(victor.getPWeap()==1&&isDeadBomb(ed,expXV2,expYV2)&&deathCountE>INVINCIBLE)
               {
                  deathCountE=0;
                  eDead=true;
               }
            }
            if(count==bombtimev)
            {
               expXV2=bXV2;
               expYV2=bYV2;
               count2=0;
            }
            if(count>bombtimev&&count2<bombtimev)
            {
               if(expXV2>1000)
                  myBuffer.drawImage(explode.getImage(),expXV2,expYV2,75,75, null);
               if(victor.getPWeap()==1&&isDeadBomb(ed,expXV2,expYV2)&&deathCountE>INVINCIBLE)
               {
                  deathCountE=0;
                  eDead=true;
               }
            }
         }
         if(traveledE[(int)(yPos2/100)][(int)(xPos2/100)]==false)
         {
            traveledE[(int)(yPos2/100)][(int)(xPos2/100)]=true;
            traveledCountE++;
         }
         if(traveledV[(int)(yPos1/100)][(int)(xPos1/100)]==false)
         {
            traveledV[(int)(yPos1/100)][(int)(xPos1/100)]=true;
            traveledCountV++;
         }
         if(traveledCountE>=(int)((s-14)*(s-14)*0.7)&&traveledCountV>=(int)((s-14)*(s-14)*0.7))
         {
            System.out.println("Both "+ed.getName()+" and "+victor.getName()+ " tied!");
            System.exit(0);
         }
         else
         {
            if(traveledCountE>=(int)((s-14)*(s-14)*0.7))
            {
               System.out.println(ed.getName()+" is the winner!");
               System.exit(0);
            }
            if(traveledCountV>=(int)((s-14)*(s-14)*0.7))
            {
               System.out.println(victor.getName()+" is the winner!");
               System.exit(0);
            }
         }
         if(victor.getPWeap()==2&&ePressed&&laserCountV>2&&!vDead)
         {
            gridLines();
            victor.shoot(new Laser(laserCountV),myBuffer);
            bX=victor.getBombX();
            bY=victor.getBombY();
            bXV2=victor2.getBombX();
            bYV2=victor2.getBombY();
         }
         if(victor.getPWeap()==2&&!ePressed&&laserCountV>2)
         {
            gridLines();
            laserCountV=2;
         }
         if(victor.getPWeap()==2&&laserCountV>300&&!ePressed)
         {
            laserDistV=laserCountV;
            laserCountV=0;
         }
         if(victor.getPWeap()==2&&laserCountV<=2)
         {
            gridLines();
            myBuffer.setColor(Color.RED);
            Rectangle2D rect = new Rectangle2D.Double(0, -5 , laserDistV, 5);
            Rectangle2D rect2 = new Rectangle2D.Double(0, -5 , laserDistV, 5);
            if(victor.getX()+(int)(Math.cos(Math.toRadians(victor.getDir()))*laserDistV)>1100)
               rect.setRect(0, -5 , 500, 5);
            AffineTransform transform = new AffineTransform();
            transform.translate(bX,bY);
            transform.rotate(Math.toRadians(-victor.getDir()));
            Shape rotatedRect = transform.createTransformedShape(rect);
            bX=victor2.getX();
            bY=victor2.getY();
            Shape rotatedRect2 = transform.createTransformedShape(rect2);
            myBuffer2D.fill(rotatedRect);
            myBuffer2D.fill(rotatedRect2);
            if(isDeadLaser(ed,victor2.getX(),victor2.getY(),victor2.getDir(),laserDistV)&&deathCountE>INVINCIBLE)
            {
            eDead=true;
            deathCountE=0;
            }
         }
         if(ed.getPWeap()==2&&pPressed&&laserCountE>2&&!eDead)
         {
            gridLines2();
            ed.shoot(new Laser(laserCountE),myBuffer);
            bX2=ed.getBombX();
            bY2=ed.getBombY();
            bXE2=ed2.getBombX();
            bYE2=ed2.getBombY();
         }
         if(ed.getPWeap()==2&&laserCountE>300&&!pPressed)
         {
            laserDistE=laserCountE;
            laserCountE=0;
         }
         if(ed.getPWeap()==2&&laserCountE<=2)
         {
            gridLines2();
            myBuffer.setColor(Color.RED);
            Rectangle2D rect = new Rectangle2D.Double(0, -5 , laserDistE, 5);
            Rectangle2D rect2 = new Rectangle2D.Double(0, -5 , laserDistE, 5);
            if(ed.getX()+(int)(Math.cos(Math.toRadians(ed.getDir()))*laserDistE)<1100)
               rect.setRect(0, -5 , 500, 5);
            AffineTransform transform = new AffineTransform();
            transform.translate(bX2,bY2);
            transform.rotate(Math.toRadians(-ed.getDir()));
            Shape rotatedRect = transform.createTransformedShape(rect);
            bX2=ed2.getX();
            bY2=ed2.getY();
            Shape rotatedRect2 = transform.createTransformedShape(rect2);
            myBuffer2D.fill(rotatedRect);
            myBuffer2D.fill(rotatedRect2);
            if(isDeadLaser(victor,ed2.getX(),ed2.getY(),ed2.getDir(),laserDistE)&&deathCountV>INVINCIBLE)
            {
            vDead=true;
            deathCountV=0;
            }
         }
         if(ed.getPWeap()==2&&!pPressed&&laserCountE>2)
         {
            gridLines2();
            laserCountE=2;
         }
         if(!vDead)
         {
            if(victor2.getX()>1050)
               victor2.draw(myBuffer);
            victor.draw(myBuffer);
         }
         if(!eDead)
         {
            ed.draw(myBuffer);
            if(ed2.getX()<1050)
               ed2.draw(myBuffer);
         }
         myBuffer.setColor(BACKGROUND);
         myBuffer.fillRect(1000,0,100,1050);
         myBuffer.setColor(Color.RED);
         myBuffer.drawRect(775,875,201,100);
         myBuffer.drawRect(1874,875,201,100);
         myBuffer.drawRect(750,875,20,100);
         myBuffer.drawRect(1850,875,20,100);
         myBuffer.drawRect(725,875,20,100);
         myBuffer.drawRect(1825,875,20,100);
         myBuffer.setColor(BACKGROUND);
         myBuffer.fillRect(776,876,198,98);
         myBuffer.fillRect(1876,876,198,98);
         myBuffer.setColor(Color.GREEN.darker());
         myBuffer.fillRect(751,975-(int)(traveledCountV/((s-14)*(s-14)*0.7)*98),18,(int)(traveledCountV/((s-14)*(s-14)*0.7)*98));
         myBuffer.fillRect(1851,975-(int)(traveledCountE/((s-14)*(s-14)*0.7)*98),18,(int)(traveledCountE/((s-14)*(s-14)*0.7)*98));
         myBuffer.setColor(new Color(255,140,0));
         if(activateCountV<cooldown)
         {
            myBuffer.fillRect(726,975-(int)(activateCountV*98/cooldown),18,(int)(activateCountV*98/cooldown));
         }
         if(activateCountE<cooldown)
         {
            myBuffer.fillRect(1826,975-(int)(activateCountE*98/cooldown),18,(int)(activateCountE*98/cooldown));
         }
         if(activateCountV>=cooldown)
         {
            myBuffer.setColor(Color.RED);
            myBuffer.fillRect(726,875,19,100);
         }
         if(activateCountE>=cooldown)
         {
            myBuffer.setColor(Color.RED);
            myBuffer.fillRect(1826,875,19,100);
         }
         repaint();
      }
   }
   public void gridLines()
   {
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,1000,1000);
      for(int y=(int)((yPos1-500)/100);y<(int)((yPos1+500)/100+2);y++)
      {
         for(int x=(int)((xPos1-500)/100);x<(int)((xPos1+500)/100+1);x++)
         {
            if(traveledV[y][x])
            {
               myBuffer.setColor(Color.GREEN.darker());
               myBuffer.fillRect(500-(xPos1-(x*100))+1,500-(yPos1-(y*100))+1,98,98);
            }
         }
      }
      for(int x=(int)((xPos1-500)/100);x<(int)((xPos1+500)/100+1);x++)
      {
         for(int z=-100;z<1000;z++)
         {
            if(arrayY[(int)((yPos1-500+z)/100)][x]==0)
            {
               myBuffer.setColor(Color.RED);
               if((yPos1+z)%100==0)
               {
                  myBuffer.drawLine(500-(xPos1-(x*100)),z,500-(xPos1-(x*100)),z+100);
               }
            }
            if((yPos1+z)%100==0)
            {
               if(arrayY[(int)((yPos1-500+z)/100)][x]==1)
               {
                  Wall wall= new Wall(500-(xPos1-x*100),z,true);
                  wall.draw(myBuffer);
                  if(wall.collide(victor))
                  {
                     if(wPressed)
                     {
                        xPos1=xPos1-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                        victor2.setX(victor2.getX()-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir())))));
                        ed2.setX(ed2.getX()+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir())))));
                        bX=bX+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                        bXE2=bXE2+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                        expX=expX+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                        expXE2=expXE2+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                     }
                     if(sPressed)
                     {
                        xPos1=xPos1+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                        victor2.setX(victor2.getX()+(int)(speedv*(Math.cos(Math.toRadians(victor.getDir())))));
                        ed2.setX(ed2.getX()-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir())))));
                        bX=bX-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                        bXE2=bXE2-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                        expX=expX-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                        expXE2=expXE2-(int)(speedv*(Math.cos(Math.toRadians(victor.getDir()))));
                     }
                  }
               }
            }
         }   
      }
      for(int y=(int)((yPos1-500)/100);y<(int)((yPos1+500)/100+2);y++)
      {
         for(int z=-100;z<1000;z++)
         {
            if(arrayX[y][(int)((xPos1-500+z)/100)]==0)
            {
               if((xPos1+z)%100==0)
               {
                  myBuffer.setColor(Color.RED);
                  myBuffer.drawLine(z,500-(yPos1-(y*100)),z+100, 500-(yPos1-(y*100)));
               }
            }
            if(arrayX[y][(int)(xPos1-500+z)/100]==1)
            {
               if((xPos1+z)%100==0)
               {
                  Wall wall= new Wall(z,500-(yPos1-y*100),false);
                  wall.draw(myBuffer);
                  if(wall.collide(victor))
                  {
                     if(wPressed)
                     {
                        yPos1=yPos1+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                        victor2.setY(victor2.getY()+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir())))));
                        ed2.setY(ed2.getY()-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir())))));
                        bY=bY-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                        bYE2=bYE2-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                        expY=expY-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                        expYE2=expYE2-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                     }
                     if(sPressed)
                     {
                        yPos1=yPos1-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                        victor2.setY(victor2.getY()-(int)(speedv*(Math.sin(Math.toRadians(victor.getDir())))));
                        ed2.setY(ed2.getY()+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir())))));
                        bY=bY+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                        bYE2=bYE2+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                        expY=expY+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                        expYE2=expYE2+(int)(speedv*(Math.sin(Math.toRadians(victor.getDir()))));
                     }
                  }
               
               }
            }
         }
      }
   }
   public void gridLines2()
   {
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(1100,0,1000,1000);
      for(int y=(int)((yPos2-500)/100);y<(int)((yPos2+500)/100+2);y++)
      {
         for(int x=(int)((xPos2-500)/100);x<(int)((xPos2+500)/100+1);x++)
         {
            if(traveledE[y][x])
            {
               myBuffer.setColor(Color.GREEN.darker());
               myBuffer.fillRect(1600-(xPos2-(x*100))+1,500-(yPos2-(y*100))+1,98,98);
            }
         }
      }
      for(int x=(int)((xPos2-500)/100);x<(int)((xPos2+500)/100+1);x++)
      {
         for(int z=-100;z<1000;z++)
         {
            if(arrayY[(int)((yPos2-500+z)/100)][x]==0)
            {
               myBuffer.setColor(Color.RED);
               if((yPos2+z)%100==0)
               {
                  myBuffer.drawLine(500-(xPos2-(x*100))+1100,z,500-(xPos2-(x*100))+1100,z+100);
               }
            }
            if((yPos2+z)%100==0)
            {
               if(arrayY[(int)((yPos2-500+z)/100)][x]==1)
               {
                  Wall wall= new Wall(500-(xPos2-x*100)+1100,z,true);
                  wall.draw(myBuffer);
                  if(wall.collide(ed))
                  {
                     if(upPressed)
                     {
                        xPos2=xPos2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                        ed2.setX(ed2.getX()-(int)(speede*(Math.cos(Math.toRadians(ed.getDir())))));
                        victor2.setX(victor2.getX()+(int)(speede*(Math.cos(Math.toRadians(ed.getDir())))));
                        bX2=bX2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                        expX2=expX2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                        bXV2=bXV2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                        expXV2=expXV2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                     }
                     if(downPressed)
                     {
                        xPos2=xPos2+(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                        ed2.setX(ed2.getX()+(int)(speede*(Math.cos(Math.toRadians(ed.getDir())))));
                        victor2.setX(victor2.getX()-(int)(speede*(Math.cos(Math.toRadians(ed.getDir())))));
                        bX2=bX2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                        expX2=expX2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                        bXV2=bXV2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                        expXV2=expXV2-(int)(speede*(Math.cos(Math.toRadians(ed.getDir()))));
                     }
                  }
               }
            }
         }
      }
      for(int y=(int)((yPos2-500)/100);y<(int)((yPos2+500)/100+2);y++)
      {
         for(int z=-100;z<1000;z++)
         {
            if(arrayX[y][(int)((xPos2-500+z)/100)]==0)
            {
               if((xPos2+z)%100==0)
               {
                  myBuffer.setColor(Color.RED);
                  myBuffer.drawLine(z+1100,500-(yPos2-(y*100)),z+1200, 500-(yPos2-(y*100)));
               }
            }
            if(arrayX[y][(int)(xPos2-500+z)/100]==1)
            {
               if((xPos2+z)%100==0)
               {
                  Wall wall= new Wall(z+1100,500-(yPos2-y*100),false);
                  wall.draw(myBuffer);
                  if(wall.collide(ed))
                  {
                     if(upPressed)
                     {
                        yPos2=yPos2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                        ed2.setY(ed2.getY()+(int)(speede*(Math.sin(Math.toRadians(ed.getDir())))));
                        victor2.setY(victor2.getY()-(int)(speede*(Math.sin(Math.toRadians(ed.getDir())))));
                        bY2=bY2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                        expY2=expY2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                        bYV2=bYV2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                        expYV2=expYV2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                     }
                     if(downPressed)
                     {
                        yPos2=yPos2-(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                        ed2.setY(ed2.getY()-(int)(speede*(Math.sin(Math.toRadians(ed.getDir())))));
                        victor2.setY(victor2.getY()+(int)(speede*(Math.sin(Math.toRadians(ed.getDir())))));
                        bY2=bY2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                        expY2=expY2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                        bYV2=bYV2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                        expYV2=expYV2+(int)(speede*(Math.sin(Math.toRadians(ed.getDir()))));
                     }
                  }
               }
            }
         }
      }
   }
   public Boolean isDeadBomb(Tank arg,int x,int y)
   {
      if(distance(arg.getX(),arg.getY(),x+37.5,y+37.5)<65)
         return true;
      else 
         return false;
   }
   private double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }	
   public Boolean isDeadLaser(Tank arg,int x, int y, double dir,double dist)
   {
   if((int)pDistance(arg.getX(),arg.getY(),x,y,(int)(Math.cos(Math.toRadians(dir))*dist)+x,(int)(-Math.sin(Math.toRadians(dir))*dist)+y)<30)
   return true;
   else return false;
   }
   public double pDistance(int x, int y, int x1, int y1, int x2, int y2) {

      int A = x - x1;
      int B = y - y1;
      int C = x2 - x1;
      int D = y2 - y1;
      int E = -D; 
      int F = C;

      double dot = A * E + B * F;
      double len_sq = E * E + F * F;

      return (Math.abs(dot) / Math.sqrt(len_sq));
    }
   private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode() == KeyEvent.VK_Q)
         {
            qPressed=true;
         }
         if(e.getKeyCode() == KeyEvent.VK_L)
         {
            lPressed=true;
         }
         if(count>=bombtimev&&victor.getPWeap()==1)
         {
            if(!vDead)
            {
               if(e.getKeyCode() == KeyEvent.VK_E)
               {
                  ePressed=true;
                  count=0; 
               }
            }
         }
         if(victor.getPWeap()==2)
         {
            if(e.getKeyCode() == KeyEvent.VK_E)
               ePressed=true;
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
         if(count3>=bombtimee)
         {
            if(e.getKeyCode() == KeyEvent.VK_P)
            {
               if(!eDead)
               {
                  if(ed.getPWeap()==1)
                  {
                     pPressed=true;
                     count3=0;
                  } 
               }
            }
         }
         if(e.getKeyCode() == KeyEvent.VK_P&&ed.getPWeap()==2)
         {
            pPressed=true;
         }
         if(e.getKeyCode() == KeyEvent.VK_LEFT)
         {
            leftPressed=true;
         }
         if(e.getKeyCode() == KeyEvent.VK_UP)
            upPressed=true;
         if(e.getKeyCode() == KeyEvent.VK_DOWN)
         {
            downPressed=true;
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT)
         {
            rightPressed=true;
         }
      }
      public void keyReleased(KeyEvent e)
      {
         if(e.getKeyCode() == KeyEvent.VK_Q)
         {
            qPressed=false;
         }
         if(e.getKeyCode() == KeyEvent.VK_L)
         {
            lPressed=false;
         }
         if(e.getKeyCode() == KeyEvent.VK_E)
            ePressed=false;
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            aPressed=false;
         }
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
         if(e.getKeyCode() == KeyEvent.VK_P)
            pPressed=false;
         if(e.getKeyCode() == KeyEvent.VK_LEFT)
         {
            leftPressed=false;
         }
         if(e.getKeyCode() == KeyEvent.VK_UP)
            upPressed=false;
         if(e.getKeyCode() == KeyEvent.VK_DOWN)
         {
            downPressed=false;
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT)
         {
            rightPressed=false;
         }
      }
   }
}
