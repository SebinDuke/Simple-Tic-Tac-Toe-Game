//Multi Player ZeroKatta game
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Rect extends Applet implements MouseListener
{
	int x,y,rectx=50,recty=50,height=50,width=50;
	boolean win;
	byte bo=1;
	byte arr[][]=new byte[3][3];
	public void init()
	{
		addMouseListener(this);
	}
	public void mouseEntered(MouseEvent m)
	{}
	public void mouseExited(MouseEvent m)
	{}
	public void paint(Graphics g)
	{
		win=false;
		g.drawString("WARNING: Minimizing or resizing the window will RESTART the game",10,10);
		showStatus("Player "+bo+"'s move:");
		g.setColor(Color.red);
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				g.drawRect(rectx,recty,width,height);
				rectx+=width;
				arr[i][j]=0;
			}
			rectx-=3*width;
			recty+=height;
		}
		recty-=3*height;
	}
	public void mouseClicked (MouseEvent me) 
	{
		x=me.getX();
		y=me.getY();
		Graphics g=getGraphics();
		if(win==false)
		{
		System.out.println("mouseClicked at: "+x+":"+y+"::"+rectx+":"+recty);
		if(x<rectx+3*width && x>rectx && y<recty+3*height && y>recty)
		{	
			if(x>rectx+2*width){x=2;}
			else if(x>rectx+width){x=1;}
			else{x=0;}
			if(y>recty+2*height){y=2;}
			else if(y>recty+height){y=1;}
			else{y=0;}
			if(arr[x][y]==0)
			{
				arr[x][y]=bo;
				x=(int)(rectx+width/2+x*width);
				y=(int)(recty+height/2+y*height);
				if(bo==1){bo=2;g.drawString("X",x,y);}
				else{bo=1;g.drawString("O",x,y);}
				showStatus("Player "+bo+"'s move:");
			}
			else
			{
				showStatus("Wrong Choice");
			}
			
//Checking if someone has won or not
			
			for(int i=0;i<3;i++)
			{
				if(arr[i][0]!=0 && arr[i][0]==arr[i][1] & arr[i][0]==arr[i][2])
				{
					win=true;
				}
				else if(arr[0][i]!=0 && arr[0][i]==arr[1][i] & arr[0][i]==arr[2][i])
				{
					win=true;
				}
			}
			if(arr[0][0]!=0 && arr[1][1]==arr[0][0] & arr[2][2]==arr[0][0])
			{
				win=true;
			}
			else if(arr[0][2]!=0 && arr[0][2]==arr[1][1] & arr[0][2]==arr[2][0])
			{
				win=true;
			}
			if(win)
			{
				if(bo==1){g.drawString("Player 2 has won the game",50,3*height+recty+50);}
				else{g.drawString("Player 1 has won the game",50,3*height+recty+50);}
				showStatus("Game Over");
				return;
			}
		}
		} 
	}
	public void mousePressed (MouseEvent me) {} 
	public void mouseReleased (MouseEvent me) {}  
}