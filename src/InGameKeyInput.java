import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class InGameKeyInput 
{
	
	Buttons BigBox = new Buttons(80,150,450,450);
	Buttons quit = new Buttons(BigBox.x+BigBox.w+50,BigBox.y+BigBox.h-50, 150, 50);
	Buttons clear = new Buttons(BigBox.x+BigBox.w+50,BigBox.y,150,50);
	Buttons fillins = new Buttons(BigBox.x+BigBox.w+50 ,BigBox.y+150,150,150);
	Buttons backspace = new Buttons(BigBox.x+BigBox.w+50 ,BigBox.y+300,150,50);
	Buttons savegame = new Buttons(BigBox.x+BigBox.w+50,BigBox.y-100, 150, 50);
	Buttons Solve = new Buttons(BigBox.x+BigBox.w-150,BigBox.y-100, 150, 50);
	Buttons nextgame = new Buttons(BigBox.x+BigBox.w-150,BigBox.y+BigBox.h+50, 150, 50);
	
	int Hovercoodx = BigBox.x;
	int Hovercoody = BigBox.y;
	int Hover = 0;
	
	int mousetrackx = BigBox.x;
	int mousetracky = BigBox.y;
	int Mousefollow = 0;
	
	int quithover = 0;
	int quitcoodx = quit.x;
	int quitcoody = quit.y;
	
	int clearhover = 0;
	int clearcoodx = clear.x;
	int clearcoody = clear.y;
	
	int fillinshover = 0;
	int fillinscoodx = fillins.x;
	int fillinscoody = fillins.y;
	
	int backspacehover = 0; 
	int backspacecoodx = backspace.x;
	int backspacecoody = backspace.y;
	
	int savegamehover = 0;
	int savegamecoodx = savegame.x;
	int savegamecoody = savegame.y;
	
	int solvehover = 0;
	int solvecoodx = Solve.x;
	int solvecoody = Solve.y;
	
	int nextgamehover = 0;
	int nextgamecoodx = nextgame.x;
	int nextgamecoody = nextgame.y;
	
	static boolean[][] IsFixed = new boolean[9][9];
	boolean[][] WrongNumber = new boolean[9][9];
	boolean Conflict = false;
	
	
	MouseMotionListener mousemove = new MouseMotionListener()
	{

		@Override
		public void mouseDragged(MouseEvent e) 
		{
			if(e.getX() < BigBox.w+BigBox.x  && e.getX() > BigBox.x && e.getY() < BigBox.y+BigBox.h  && e.getY() > BigBox.y)
			{
				Hovercoodx = (int) (((e.getX()-BigBox.x)/50)*50)+BigBox.x;
				Hovercoody = (int) (((e.getY()-BigBox.y)/50)*50)+BigBox.y;
				Mousefollow = 1;
				mousetrackx = (int) (((e.getX()-BigBox.x)/50)*50)+BigBox.x;
				mousetracky = (int) (((e.getY()-BigBox.y)/50)*50)+BigBox.y;
			}
			else if(e.getX() < fillins.w+fillins.x && e.getX() > fillins.x && e.getY() < fillins.y+fillins.h && e.getY() > fillins.y) 
			{
				backspacehover = 0;
			
				fillinshover  = 1;
				fillinscoodx = (int) (((e.getX()-fillins.x)/50)*50)+fillins.x;
				fillinscoody = (int) (((e.getY()-fillins.y)/50)*50)+fillins.y;
				
				if(fillinshover == 1 && Hover == 1 && GameUI.Loading == false && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = ((e.getY()-fillins.y)/50)*3 +((e.getX()-fillins.x)/50)+1;
					InputCheck();
				}
			}
			else if(e.getX() < backspace.w+backspace.x && e.getX() > backspace.x && e.getY() < backspace.y+backspace.h && e.getY() > backspace.y)
			{
				fillinshover  = 0;
				if(backspacehover == 0 && Hover == 1)
					new SoundEffects().Hover();
				backspacehover = 1;
				backspacecoodx = backspace.x;
				backspacecoody = backspace.y;
				if(backspacehover == 1 && Hover == 1 && GameUI.Loading == false && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 0;
					InputCheck();
				}
			}
			else
			{
				backspacehover = 0;
				fillinshover  = 0;
				clearhover = 0;
				Mousefollow = 0;
				quithover  = 0;
				
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) 
		{
			if(e.getX() < BigBox.w+BigBox.x  && e.getX() > BigBox.x && e.getY() < BigBox.y+BigBox.h  && e.getY() > BigBox.y)
			{
				Mousefollow = 1;
				mousetrackx = (int) (((e.getX()-BigBox.x)/50)*50)+BigBox.x;
				mousetracky = (int) (((e.getY()-BigBox.y)/50)*50)+BigBox.y;
			}
			else if(e.getX() < quit.w + quit.x && e.getX() > quit.x && e.getY() < quit.h + quit.y && e.getY() > quit.y) 
			{
				if(quithover == 0)
					new SoundEffects().Hover();
				quithover  = 1;
				quitcoodx = quit.x;
				quitcoody = quit.y;
			}
			else if(e.getX() < clear.w+clear.x && e.getX() > clear.x && e.getY() < clear.y+clear.h && e.getY() > clear.y)
			{
				if(clearhover == 0)
					new SoundEffects().Hover();
				clearhover  = 1;
				clearcoodx = clear.x;
				clearcoody = clear.y;
			}
			else if(e.getX() < fillins.w+fillins.x && e.getX() > fillins.x && e.getY() < fillins.y+fillins.h && e.getY() > fillins.y) 
			{
				backspacehover = 0;
				fillinshover  = 1;
				fillinscoodx = (int) (((e.getX()-fillins.x)/50)*50)+fillins.x;
				fillinscoody = (int) (((e.getY()-fillins.y)/50)*50)+fillins.y;
			}
			else if(e.getX() < backspace.w+backspace.x && e.getX() > backspace.x && e.getY() < backspace.y+backspace.h && e.getY() > backspace.y)
			{
				if(backspacehover == 0 && Hover == 1)
					new SoundEffects().Hover();
				fillinshover  = 0;
				backspacehover = 1;
				backspacecoodx = backspace.x;
				backspacecoody = backspace.y;
			}
			else if(e.getX() < savegame.w+savegame.x  && e.getX() > savegame.x && e.getY() < savegame.y+savegame.h  && e.getY() > savegame.y)
			{
				
				if(GameUI.Loading == false) 
				{
					if(savegamehover == 0)
						new SoundEffects().Hover();
					savegamehover = 1;
					savegamecoodx = savegame.x;
					savegamecoody = savegame.y;
				}
			}
			else if(e.getX() < Solve.w+Solve.x  && e.getX() > Solve.x && e.getY() < Solve.y+Solve.h  && e.getY() > Solve.y) 
			{
				
				if(GameUI.Loading == false) 
				{
					if(solvehover == 0)
						new SoundEffects().Hover();
					solvehover = 1;
					solvecoodx = Solve.x;
					solvecoody = Solve.y;
				}
			}
			else if(e.getX() < nextgame.w+nextgame.x  && e.getX() > nextgame.x && e.getY() < nextgame.y+nextgame.h  && e.getY() > nextgame.y) 
			{
				
				if(GameUI.Finish == true && !GameUI.Difficulty.equals("C")) 
				{
					if(nextgamehover == 0)
						new SoundEffects().Hover();
					nextgamehover = 1;
					nextgamecoodx = nextgame.x;
					nextgamecoody = nextgame.y;
				}
			}
			else
			{
				backspacehover = 0;
				fillinshover  = 0;
				clearhover = 0;
				Mousefollow = 0;
				quithover  = 0;
				savegamehover = 0;
				solvehover = 0;
				nextgamehover = 0;
				
			}
			
		}
		
	};
	MouseListener mouseaction = new MouseListener() 
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < BigBox.w+BigBox.x  && e.getX() > BigBox.x && e.getY() < BigBox.y+BigBox.h  && e.getY() > BigBox.y)
			{
				new SoundEffects().Hover();
				Hover = 1;
				Hovercoodx = (int) (((e.getX()-BigBox.x)/50)*50)+BigBox.x;
				Hovercoody = (int) (((e.getY()-BigBox.y)/50)*50)+BigBox.y;
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < quit.w + quit.x && e.getX() > quit.x && e.getY() < quit.h + quit.y && e.getY() > quit.y) 
			{
				new SoundEffects().Click();
				WrongNumber = new boolean[9][9];
				GameUI.States = 0;
				GameUI.Reset = true;
				GameUI.Difficulty = "";
				GameUI.Loading = true;
				GameUI.LoadedGame = false;
				GameUI.FirstGame = true;
				Hover = 0;
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < clear.w+clear.x && e.getX() > clear.x && e.getY() < clear.y+clear.h && e.getY() > clear.y)
			{
				new SoundEffects().Click();
				for(int i = 0; i < 9; ++i) 
				{
					for(int j = 0; j < 9; ++j) 
					{
						if(!IsFixed[i][j])
							GameUI.game[i][j] = 0;
							Conflict = false;
					}
				}
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < fillins.w+fillins.x && e.getX() > fillins.x && e.getY() < fillins.y+fillins.h && e.getY() > fillins.y) 
			{
				if(fillinshover == 1 && Hover == 1 && GameUI.Loading == false && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = ((e.getY()-fillins.y)/50)*3 +((e.getX()-fillins.x)/50)+1;
					InputCheck();
				}
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < backspace.w+backspace.x && e.getX() > backspace.x && e.getY() < backspace.y+backspace.h && e.getY() > backspace.y)
			{
				if(backspacehover == 1 && Hover == 1 && GameUI.Loading == false && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 0;
					InputCheck();
				}
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < savegame.w+savegame.x  && e.getX() > savegame.x && e.getY() < savegame.y+savegame.h  && e.getY() > savegame.y)
			{
				if(GameUI.Loading == false) 
				{
					new SoundEffects().Click();
					try {
						new SaveNLoad().Save();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < Solve.w+Solve.x  && e.getX() > Solve.x && e.getY() < Solve.y+Solve.h  && e.getY() > Solve.y) 
			{
				if(GameUI.Loading == false) 
				{
					new SoundEffects().Click();
					for(int i = 0; i < 9; ++i) 
					{
						for(int j = 0; j < 9; ++j) 
						{
							if(!IsFixed[i][j])
								GameUI.game[i][j] = 0;
								Conflict = false;
						}
					}
					new Generate().creatpool(GameUI.game, 0, 0);
				}
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() < nextgame.w+nextgame.x  && e.getX() > nextgame.x && e.getY() < nextgame.y+nextgame.h  && e.getY() > nextgame.y) 
			{
				if(GameUI.Finish == true && !GameUI.Difficulty.equals("C")) 
				{
					new SoundEffects().Click();
					GameUI.nextgame = true;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	KeyListener InGameKeyboard = new KeyListener() {
		
		@Override
		public void keyPressed(KeyEvent e) 
		{	
			if(e.getKeyCode() == KeyEvent.VK_SPACE) 
			{
				new SoundEffects().Hover();
				Space();	
			}	
			if(e.getKeyCode() == KeyEvent.VK_UP) 
			{
				new SoundEffects().Hover();
				Up();
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
			{
				new SoundEffects().Hover();
				Right();
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT) 
			{
				new SoundEffects().Hover();
				Left();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			{
				new SoundEffects().Hover();
				Down();
			}
			if(e.getKeyCode() == KeyEvent.VK_Q) 
			{
				new SoundEffects().Click();
				WrongNumber = new boolean[9][9];
				GameUI.States = 0;
				GameUI.Reset = true;
				GameUI.Difficulty = "";
				GameUI.Loading = true;
				GameUI.LoadedGame = false;
				GameUI.FirstGame = true;
				Hover = 0;
			}
			if(Hover == 1 && GameUI.Loading == false) 
			{	
				if((e.getKeyCode() == KeyEvent.VK_0 ||e.getKeyCode() == KeyEvent.VK_BACK_SPACE ||e.getKeyCode() == KeyEvent.VK_NUMPAD0) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 0;
					InputCheck();
					
				}
				if((e.getKeyCode() == KeyEvent.VK_1 ||e.getKeyCode() == KeyEvent.VK_NUMPAD1) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 1;
					InputCheck();
					
				}
				if((e.getKeyCode() == KeyEvent.VK_2 ||e.getKeyCode() == KeyEvent.VK_NUMPAD2) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 2;
					InputCheck();
				}
				if((e.getKeyCode() == KeyEvent.VK_3 ||e.getKeyCode() == KeyEvent.VK_NUMPAD3) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 3;
					InputCheck();
				}
				if((e.getKeyCode() == KeyEvent.VK_4 ||e.getKeyCode() == KeyEvent.VK_NUMPAD4) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 4;
					InputCheck();
				}
				if((e.getKeyCode() == KeyEvent.VK_5 ||e.getKeyCode() == KeyEvent.VK_NUMPAD5) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 5;
					InputCheck();
				}
				if((e.getKeyCode() == KeyEvent.VK_6 ||e.getKeyCode() == KeyEvent.VK_NUMPAD6) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 6;
					InputCheck();
				}
				if((e.getKeyCode() == KeyEvent.VK_7 ||e.getKeyCode() == KeyEvent.VK_NUMPAD7) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 7;
					InputCheck();
				}
				if((e.getKeyCode() == KeyEvent.VK_8 ||e.getKeyCode() == KeyEvent.VK_NUMPAD8) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 8;
					InputCheck();
				}
				if((e.getKeyCode() == KeyEvent.VK_9 ||e.getKeyCode() == KeyEvent.VK_NUMPAD9) && !IsFixed[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50]) 
				{
					new SoundEffects().Click();
					GameUI.game[(Hovercoodx+16-(BigBox.x+16))/50][(Hovercoody+38-(BigBox.y+38))/50] = 9;
					InputCheck();
				}
				
			}
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			
		}

		@Override
		public void keyTyped(KeyEvent e) 
		{
			// TODO Auto-generated method stub
		}};
	public void Space() 
	{
		if(Hover == 0) 
		{
			Hovercoodx = BigBox.x;
			Hovercoody = BigBox.y;
			Hover = 1;
		}
		else
		{
			if(Hovercoodx < BigBox.w+BigBox.x-50) 
			{
				Hovercoodx += 50;
			}
			else if(Hovercoody < BigBox.h+BigBox.y-50)
			{
				Hovercoodx = BigBox.x;
				Hovercoody += 50;
			}
			else
			{
				Hover = 0;
			}
			
		}
	}
	public void Up() 
	{
		if(Hover == 0) 
		{
			Hovercoodx = BigBox.x;
			Hovercoody = BigBox.y;
			Hover = 1;
		}
		else 
		{
			if(Hovercoody <= BigBox.h+BigBox.y-50 && Hovercoody > BigBox.y) 
			{
				Hovercoody -= 50;
			}
			else if(Hovercoody <= BigBox.y)
			{
				Hovercoody = BigBox.h+BigBox.y-50;
			}
			else 
			{
				Hover = 0;
				Hovercoodx = BigBox.x;
				Hovercoody = BigBox.y;
			}
		}
	}
	public void Down() 
	{
		if(Hover == 0) 
		{
			Hovercoodx = BigBox.x;
			Hovercoody = BigBox.y;
			Hover = 1;
		}
		else 
		{
			if(Hovercoody < BigBox.h+BigBox.y-50 && Hovercoody >= BigBox.y) 
			{
				Hovercoody += 50;
			}
			else if(Hovercoody >= BigBox.h+BigBox.y-50)
			{
				Hovercoody = BigBox.y;
			}
			else 
			{
				Hover = 0;
				Hovercoodx = BigBox.x;
				Hovercoody = BigBox.y;
			}
		}
	}	
	public void Right() 
		{
			if(Hover == 0) 
			{
				Hovercoodx = BigBox.x;
				Hovercoody = BigBox.y;
				Hover = 1;
			}
			else 
			{
				
				if(Hovercoodx < BigBox.w+BigBox.x-50 && Hovercoodx >= BigBox.x) 
				{
					Hovercoodx += 50;
				}
				else if(Hovercoodx >= BigBox.w-50)
				{
					Hovercoodx = BigBox.x;
				}

			}	
		}
	public void Left() 
	{
		if(Hover == 0) 
		{
			Hovercoodx = BigBox.x;
			Hovercoody = BigBox.y;
			Hover = 1;
		}
		else 
		{
			
			if(Hovercoodx <= BigBox.w+BigBox.x-50 && Hovercoodx > BigBox.x) 
			{
				Hovercoodx -= 50;
			}
			else if(Hovercoodx <= BigBox.x)
			{
				Hovercoodx = BigBox.w+BigBox.x-50;
			}

		}
	}	
	public void CheckFixedPoints() 
		{
			for(int i = 0; i < GameUI.game[0].length; ++i) 
			{
				for(int j = 0; j < GameUI.game.length; ++j) 
				{
					if(GameUI.game[i][j] == 0) 
					{
						IsFixed[i][j] = false; 
					}else 
					{
						IsFixed[i][j] = true;
					}
				}
			}
		}	
	public void InputCheck()
	{
		for(int x = 0; x < 9; ++x) 
		{
			for(int y = 0; y < 9; ++y )
			{
				WrongNumber[x][y] = false;
			}
		}
		for(int x = 0; x < 9; ++x) 
		{
			for(int y = 0; y < 9; ++y )
			{
				if(GameUI.game[x][y] != 0)
				{
					for(int i = 0; i < 9; ++i) 
					{
							if(GameUI.game[x][y] == GameUI.game[x][i] && i != y) 
							{
								WrongNumber[x][i] = true;
								WrongNumber[x][y] = true;
							}
					}
					for(int i = 0; i < 9; ++i) 
					{
							if(GameUI.game[x][y] == GameUI.game[i][y] && x != i) 
							{
								WrongNumber[i][y] = true;
								WrongNumber[x][y] = true;
							}
					}
					for(int i = (x/3)*3; i < (x/3)*3+3; ++i) 
					{
						for(int j = (y/3)*3; j < (y/3)*3+3; ++j) 
						{
							if(GameUI.game[x][y] == GameUI.game[i][j] && i*9+j != x*9+y) 
							{
								
								WrongNumber[i][j] = true;
								WrongNumber[x][y] = true;
							}	
						}
					}
					
				}
			}
		}
		
		String check ="";
		for(int i = 0; i < GameUI.game[0].length; ++i) 
		{
			for(int j = 0; j < GameUI.game.length; ++j) 
			{
				check += String.valueOf(WrongNumber[i][j]);
			}
		}
		
		
		if(check.contains("true")) 
		{
			Conflict = true;
		}
		else 
		{
			Conflict = false;
		}
		

	}	
		
}
