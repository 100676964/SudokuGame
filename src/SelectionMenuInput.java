import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SelectionMenuInput 
{
	int x = Easy.x;
	int y = Easy.y;
	int hover = 0;
	
	static Buttons Easy = new Buttons(270,100, 230, 100);
	static Buttons Medium = new Buttons(270,250, 230, 100);
	static Buttons Hard = new Buttons(270,400,230, 100);
	static Buttons Custom = new  Buttons(270,550,230,100);
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
			{	
				if(hover == 0)
					new SoundEffects().Hover();
				hover = 1;
				x = Easy.x;
				y = Easy.y;
			}
			else if(e.getX() < Medium.x+Medium.w && e.getX() > Medium.x && e.getY() < Medium.y+Medium.h && e.getY() > Medium.y) 
			{
				if(hover == 0)
					new SoundEffects().Hover();
				hover = 1;
				x = Medium.x;
				y = Medium.y;
			}
			else if(e.getX() < Hard.x+Hard.w && e.getX() > Hard.x && e.getY() < Hard.y+Hard.h && e.getY() > Hard.y) 
			{
				if(hover == 0)
					new SoundEffects().Hover();
				hover = 1;
				x = Hard.x;
				y = Hard.y;
			}
			else if(e.getX() < Custom.x+Custom.w && e.getX() > Custom.x && e.getY() < Custom.y+Custom.h && e.getY() > Custom.y) 
			{
				if(hover == 0)
					new SoundEffects().Hover();
				hover = 1;
				x = Custom.x;
				y = Custom.y;
			}
			else 
			{
				hover = 0;
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
				new SoundEffects().Click();
				GameUI.States =2;
				GameUI.Difficulty = "E";
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < Medium.x+Medium.w && e.getX() > Medium.x && e.getY() < Medium.y+Medium.h && e.getY() > Medium.y) 
			{
				new SoundEffects().Click();
				GameUI.States =2;
				GameUI.Difficulty = "M";	
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < Hard.x+Hard.w && e.getX() > Hard.x && e.getY() < Hard.y+Hard.h && e.getY() > Hard.y) 
			{
				new SoundEffects().Click();
				GameUI.States =2;
				GameUI.Difficulty = "H";  
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < Custom.x+Custom.w && e.getX() > Custom.x && e.getY() < Custom.y+Custom.h && e.getY() > Custom.y) 
			{
				new SoundEffects().Click();
				GameUI.States =2;
				GameUI.game = new Box().box();
				GameUI.Difficulty = "C";
				GameUI.LoadedGame = true;
				GameUI.Loading = false; 
				InGameKeyInput.IsFixed = new boolean[9][9];  
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
			new SoundEffects().Hover();
			if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			{
				if(y <= Custom.y && y >= Easy.y) 
				{
					y += 150;
				}
				if(y > Custom.y) 
				{
					y = Easy.y;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_UP) 
			{
				new SoundEffects().Hover();
				if(y <= Custom.y && y >= Easy.y) 
				{
					y -= 150;
				}
				if(y < Easy.y) 
				{
					y = Custom.y;
				}
			} 
			if(e.getKeyCode() == KeyEvent.VK_ENTER &&  x == Easy.x && y == Easy.y) 
			{
				new SoundEffects().Click();
				GameUI.States =2;
				GameUI.Difficulty = "E";
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER && x == Medium.x && y == Medium.y ) 
			{
				new SoundEffects().Click();
				GameUI.States =2;
				GameUI.Difficulty = "M"; 
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER && x == Hard.x && y == Hard.y) 
			{
				new SoundEffects().Click();
				GameUI.States =2;
				GameUI.Difficulty = "H"; 
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER && x == Custom.x && y == Custom.y) 
			{
				new SoundEffects().Click();
				GameUI.States =2;
				GameUI.game = new Box().box();
				GameUI.Difficulty = "C";
				GameUI.LoadedGame = true;
				GameUI.Loading = false;
				InGameKeyInput.IsFixed = new boolean[9][9];
			}
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) 
			{
				new SoundEffects().Click();
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
