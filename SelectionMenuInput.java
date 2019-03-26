import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SelectionMenuInput 
{
	int x = Easy.x;
	int y = Easy.y;
	
	static Buttons Easy = new Buttons(300, 100, 200, 100);
	static Buttons Medium = new Buttons(300, 250, 200, 100);
	static Buttons Hard = new Buttons(300, 400, 200, 100);
	MouseMotionListener mousemove = new MouseMotionListener()
	{

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getX() < Easy.x+Easy.w && e.getX() > Easy.x && e.getY() < Easy.y+Easy.h && e.getY() > Easy.y)
			{	x = Easy.x;
				y = Easy.y;
			}
			if(e.getX() < Medium.x+Medium.w && e.getX() > Medium.x && e.getY() < Medium.y+Medium.h && e.getY() > Medium.y) 
			{
				x = Medium.x;
				y = Medium.y;
			}
			if(e.getX() < Hard.x+Hard.w && e.getX() > Hard.x && e.getY() < Hard.y+Hard.h && e.getY() > Hard.y) 
			{
				x = Hard.x;
				y = Hard.y;
			}
		}
		
	};
	MouseListener mouseaction = new MouseListener() 
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < Easy.x+Easy.w && e.getX() > Easy.x && e.getY() < Easy.y+Easy.h && e.getY() > Easy.y) 
			{
				GameUI.States =2;
				GameUI.Difficulty = "E";  
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < Medium.x+Medium.w && e.getX() > Medium.x && e.getY() < Medium.y+Medium.h && e.getY() > Medium.y) 
			{
				GameUI.States =2;
				GameUI.Difficulty = "M";	
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < Hard.x+Hard.w && e.getX() > Hard.x && e.getY() < Hard.y+Hard.h && e.getY() > Hard.y) 
			{
				GameUI.States =2;
				GameUI.Difficulty = "H";  
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	KeyListener SelectionKeyboard = new KeyListener() 
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			{
				if(y <= Hard.y && y >= Easy.y) 
				{
					y += 150;
				}
				if(y > Hard.y) 
				{
					y = Easy.y;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_UP) 
			{
				if(y <= Hard.y && y >= Easy.y) 
				{
					y -= 150;
				}
				if(y < Easy.y) 
				{
					y = Hard.y;
				}
			} 
			if(e.getKeyCode() == KeyEvent.VK_ENTER && x == Easy.x && Easy.y == 100) 
			{
				GameUI.States =2;
				GameUI.Difficulty = "E"; 
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER && Medium.x == 300 && Medium.y == 250) 
			{
				GameUI.States =2;
				GameUI.Difficulty = "M"; 
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER && Hard.x == 300 && Hard.y == 400) 
			{
				GameUI.States =2;
				GameUI.Difficulty = "H"; 
			}
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) 
			{
				GameUI.States =0;
				GameUI.Reset = true;
			}
			
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};

}
