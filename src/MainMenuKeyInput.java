import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class MainMenuKeyInput 
{
	Buttons newgame = new Buttons(270, 180, 230, 100);
	Buttons loadgame = new Buttons(newgame.x,newgame.y+150,newgame.w,newgame.h);
	Buttons quit = new Buttons(newgame.x,newgame.y+newgame.h+200,newgame.w,newgame.h);
	
	int hovercoodx = newgame.x;
	int hovercoody = newgame.y;
	int hover = 0;
	
	MouseMotionListener mousemove = new MouseMotionListener() 
	{

		@Override
		public void mouseDragged(MouseEvent e) 
		{
			
			
		}

		@Override
		public void mouseMoved(MouseEvent e) 
		{
			if(e.getX() < newgame.x+newgame.w && e.getX() > newgame.x && e.getY() < newgame.y+newgame.h && e.getY() > newgame.y) 
			{
				if(hover == 0)
					new SoundEffects().Hover();
				hover = 1;
				hovercoodx = newgame.x;
				hovercoody = newgame.y;
				
			}
			else if(e.getX() < loadgame.x+loadgame.w && e.getX() > loadgame.x && e.getY() < loadgame.y+loadgame.h && e.getY() > loadgame.y) 
			{
				if(hover == 0)
					new SoundEffects().Hover();
				hover = 1;
				hovercoodx = loadgame.x;
				hovercoody = loadgame.y;
			}
			else if(e.getX() < quit.x+quit.w && e.getX() > quit.x && e.getY() < quit.y+quit.h && e.getY() > quit.y) 
			{
				if(hover == 0)
					new SoundEffects().Hover();
				hover = 1;
				hovercoodx = quit.x;
				hovercoody = quit.y;
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
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < newgame.x+newgame.w && e.getX() > newgame.x && e.getY() < newgame.y+newgame.h && e.getY() > newgame.y) 
			{
				new SoundEffects().Click();
				GameUI.States = 1; 	
			}
			else if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < loadgame.x+loadgame.w && e.getX() > loadgame.x && e.getY() < loadgame.y+loadgame.h && e.getY() > loadgame.y) 
			{
				new SoundEffects().Click();
				try 
				{
					new SaveNLoad().Load();
				} catch (FileNotFoundException e1) 
				{
					JOptionPane.showMessageDialog(null, "no game saves");
				}
			}
			else if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < quit.x+quit.w && e.getX() > quit.x && e.getY() < quit.y+quit.h && e.getY() > quit.y) 
			{
				new SoundEffects().Click();
				Runtime.getRuntime().exit(1);
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
 	KeyListener mainmenukeyinput = new KeyListener() 
	{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP) 
			{
				new SoundEffects().Hover();
				if(hovercoody <= quit.y && hovercoody >= newgame.y) 
				{
					hovercoody -= quit.y-loadgame.y;
				}
				if(hovercoody < newgame.y ) 
				{
					hovercoody = quit.y;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			{
				new SoundEffects().Hover();
				if(hovercoody <= quit.y && hovercoody >= newgame.y) 
				{
					hovercoody += quit.y-loadgame.y;
				}
				if(hovercoody > quit.y ) 
				{
					hovercoody = newgame.y;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER && hovercoodx == newgame.x && hovercoody == newgame.y) 
			{
				new SoundEffects().Click();
				GameUI.States = 1; 
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER && hovercoodx == loadgame.x && hovercoody == loadgame.y) 
			{
				new SoundEffects().Click();
				try 
				{
					new SaveNLoad().Load();
				} catch (FileNotFoundException e1) 
				{
					JOptionPane.showMessageDialog(null, "no game saves");
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER && hovercoodx == quit.x && hovercoody == quit.y) 
			{
				new SoundEffects().Click();
				Runtime.getRuntime().exit(1);
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}};

}
