import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class GameUI extends Thread
{
	static JFrame Game = new JFrame();
	Canvas canvas = new Canvas();
	static int thread1 = 0;
	static int thread2 = 0;
	static int States = 0;
	
	static boolean Reset = false;
	static boolean Loading = true;
	static boolean FirstGame = true;
	static boolean LoadedGame = false;
	
	static String Difficulty = "";
	static Integer[][] game = new Integer[9][9];
	
	MainMenuKeyInput mainkey = new MainMenuKeyInput();
	InGameKeyInput keyboard = new InGameKeyInput();
	SelectionMenuInput selectionkeyboard = new SelectionMenuInput();
	BufferStrategy bs;
	
	
	public  GameUI() 
	{
		
		Game.setLayout(null);
		Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game.setSize(800, 800);
		Game.setResizable(false);
		Game.setLocationRelativeTo(null);
		Game.add(canvas);
		Game.setVisible(true);
		//Game.getContentPane().setBackground(Color.WHITE);
		
		canvas.setBounds(0, 0, 800, 800);
		canvas.setIgnoreRepaint(true);
		canvas.setBackground(Color.getHSBColor(0.091f, 0.08f, 1f));
		
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
		
		canvas.requestFocus();
		
		
	}
	public void DrawMain() 
	{
		Graphics2D box = (Graphics2D) bs.getDrawGraphics();
		Graphics2D Hover = (Graphics2D) bs.getDrawGraphics();
		Graphics2D Title = (Graphics2D) bs.getDrawGraphics();
		Hover.setColor(Color.CYAN);
		Title.setFont(new Font("TimesRoman",Font.BOLD,80));
		box.setFont(new Font("TimesRoman",Font.BOLD,32));
		box.setStroke(new BasicStroke(3));
		canvas.addKeyListener(mainkey.mainmenukeyinput);
		canvas.addMouseListener(mainkey.mouseaction);
		canvas.addMouseMotionListener(mainkey.mousemove);
		while(States == 0) 
		{
			
			box.clearRect(0, 0, 800, 800);
			Title.drawString("A SUDOKU GAME", 40, 100);
			Hover.fillRect(mainkey.hovercoodx, mainkey.hovercoody, mainkey.newgame.w, mainkey.newgame.h);
			
			
			box.drawString("NEW GAME", mainkey.newgame.x+22,mainkey.newgame.y+62);
			box.drawString("LOAD GAME", mainkey.loadgame.x+15,mainkey.loadgame.y+62);
			box.drawString("QUIT GAME", mainkey.quit.x+22, mainkey.quit.y+62);
			
			box.drawRect(mainkey.newgame.x, mainkey.newgame.y, mainkey.newgame.w, mainkey.newgame.h);
			box.drawRect(mainkey.loadgame.x, mainkey.loadgame.y,mainkey.loadgame.w,mainkey.loadgame.h );
			box.drawRect(mainkey.quit.x, mainkey.quit.y, mainkey.quit.w, mainkey.quit.h);
			try 
			{
				Thread.sleep(12);
			} catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bs.show();
		}
		canvas.removeKeyListener(mainkey.mainmenukeyinput);
		canvas.removeMouseListener(mainkey.mouseaction);
		canvas.removeMouseMotionListener(mainkey.mousemove);
		
	}
	public void DrawSelect() 
	{
		canvas.addKeyListener(selectionkeyboard.SelectionKeyboard);
		canvas.addMouseListener(selectionkeyboard.mouseaction);
		canvas.addMouseMotionListener(selectionkeyboard.mousemove);
		Graphics2D Selections = (Graphics2D) bs.getDrawGraphics();
		Selections.setFont(new Font("TimesRoman",Font.BOLD,40));

		Graphics2D SelectionHover = (Graphics2D) bs.getDrawGraphics();
		Selections.setStroke(new BasicStroke(3));
		SelectionHover.setColor(Color.CYAN);
		
		while(States == 1) 
		{
			
			Selections.clearRect(0, 0, 800, 800);
			SelectionHover.fillRect(selectionkeyboard.x, selectionkeyboard.y, 200, 100);
			Selections.drawString("EASY", SelectionMenuInput.Easy.x+50,SelectionMenuInput.Easy.y+62);
			Selections.drawString("MEDIUM",SelectionMenuInput.Medium.x+10,SelectionMenuInput.Medium.y+62);
			Selections.drawString("HARD",SelectionMenuInput.Hard.x+45,SelectionMenuInput.Hard.y+62);
			Selections.drawRect(SelectionMenuInput.Easy.x,SelectionMenuInput.Easy.y,SelectionMenuInput.Easy.w,SelectionMenuInput.Easy.h);
			Selections.drawRect(SelectionMenuInput.Medium.x,SelectionMenuInput.Medium.y,SelectionMenuInput.Medium.w,SelectionMenuInput.Medium.h);
			Selections.drawRect(SelectionMenuInput.Hard.x,SelectionMenuInput.Hard.y,SelectionMenuInput.Hard.w,SelectionMenuInput.Hard.h);
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bs.show();
		}
		canvas.removeKeyListener(selectionkeyboard.SelectionKeyboard);
		canvas.removeMouseListener(selectionkeyboard.mouseaction);
		canvas.removeMouseMotionListener(selectionkeyboard.mousemove);
		
	}
	public void DrawNumBox() 
	{	
		canvas.addKeyListener(keyboard.InGameKeyboard);
		canvas.addMouseListener(keyboard.mouseaction);
		canvas.addMouseMotionListener(keyboard.mousemove);
		//
		Graphics2D puzzlebox = (Graphics2D) bs.getDrawGraphics();
		Graphics2D Hoverframe = (Graphics2D) bs.getDrawGraphics();
		Graphics2D numbers = (Graphics2D) bs.getDrawGraphics();
		Graphics2D ConFlict = (Graphics2D) bs.getDrawGraphics();
		Graphics2D FixedHL = (Graphics2D) bs.getDrawGraphics();
		Graphics2D OutLine = (Graphics2D) bs.getDrawGraphics();
		Graphics2D Mousemove = (Graphics2D) bs.getDrawGraphics();
		FixedHL.setColor(Color.lightGray);
		
		Hoverframe.setStroke(new BasicStroke(5));
		Hoverframe.setColor(Color.cyan);
		
		puzzlebox.setFont(new Font("TimesRoman",Font.BOLD,20));
		
		numbers.setFont(new Font("TimesRoman",Font.BOLD,40));
		
		ConFlict.setColor(Color.getHSBColor(0.01f, 1f, 1f));
		ConFlict.setFont(new Font("TimesRoman",Font.BOLD,40));
		Mousemove.setColor(Color.getHSBColor( 0.4444f, 0.07f, 1f));
		
		OutLine.setColor(Color.black);
		OutLine.setStroke(new BasicStroke(3));
		
		
		FixedHL.setFont(new Font("TimesRoman",Font.BOLD,25));
		//
		while(States == 2) 
		{
			puzzlebox.clearRect(0, 0, 800, 800);
			
			if(keyboard.Mousefollow == 1)
			{
				Mousemove.fillRect(keyboard.mousetrackx, keyboard.mousetracky, 50, 50);
			}
			for(int i = 0; i < 9; ++i) 
			{
				for(int j = 0; j < 9; ++j) 
				{
					if(InGameKeyInput.IsFixed[i][j] && Loading == false) 
					{
						FixedHL.fillRect(keyboard.BigBox.x+(i*50), keyboard.BigBox.y+(j*50), 50, 50);
					}
				}
			}
			if(keyboard.fillinshover == 1 && keyboard.Hover == 1) 
			{
				for(int i = 0; i < 9; ++i) 
				{
					Hoverframe.fillRect(keyboard.fillinscoodx,keyboard.fillinscoody,50,50);
				}
			}
			
			if(keyboard.quithover == 1) 
			{
				Hoverframe.fillRect(keyboard.quitcoodx,keyboard.quitcoody,keyboard.quit.w,keyboard.quit.h);
			}
			if(keyboard.clearhover == 1) 
			{
				Hoverframe.fillRect(keyboard.clearcoodx,keyboard.clearcoody,keyboard.clear.w,keyboard.clear.h);
			}
			if(keyboard.backspacehover == 1 && keyboard.Hover == 1) 
			{
				Hoverframe.fillRect(keyboard.backspacecoodx,keyboard.backspacecoody,keyboard.backspace.w,keyboard.backspace.h);
			}
			
			int x=0;
			int y=0;
			for(int i = 0; i < 9; ++i) 
			{
				for(int j =  0; j <  9; ++j) 
				{
					puzzlebox.drawRect(keyboard.BigBox.x+(i*50), keyboard.BigBox.y+(j*50), 50, 50);
					
					if(Loading == true) 
					{
						ConFlict.drawString("LOADING...", keyboard.BigBox.x+125,keyboard.BigBox.y-20);
					}
					else 
					{
						if(game[x][y] != 0) 
						{
							numbers.drawString(game[x][y].toString(), keyboard.BigBox.x+16+(i*50),keyboard.BigBox.y+38+(j*50));
							if(keyboard.Conflict && keyboard.WrongNumber[x][y] == true) 
							{
								ConFlict.drawString(game[x][y].toString(), keyboard.BigBox.x+16+(i*50), keyboard.BigBox.y+38+(j*50));
							}
						}
					}
					++y;
				}
				y = 0;
				++x;	
			}
			for(int i = 0; i < 3; ++i) 
			{
				for(int j = 0; j < 3; ++j) 
				{
					numbers.drawString(String.valueOf(j*3+i+1), keyboard.fillins.x+16+(i*50),keyboard.fillins.y+38+(j*50));
					puzzlebox.drawRect(keyboard.fillins.x+(i*50), keyboard.fillins.y+(j*50), keyboard.fillins.w/3, keyboard.fillins.h/3);
					OutLine.drawRect(keyboard.BigBox.x+(i*150), keyboard.BigBox.y+(j*150), 150, 150);
				}
			}
			if(Loading == false) 
			{
				if(Difficulty.equals("E")) 
				{
					puzzlebox.drawString("DIFFICULTY: EASY", keyboard.BigBox.x+5, keyboard.savegame.y+30);
				}
				if(Difficulty.equals("M")) 
				{
					puzzlebox.drawString("DIFFICULTY: MEDIUM", keyboard.BigBox.x+5, keyboard.savegame.y+30);
				}
				if(Difficulty.equals("H")) 
				{
					puzzlebox.drawString("DIFFICULTY: HARD", keyboard.BigBox.x+5, keyboard.savegame.y+30);
				}
				if(keyboard.savegamehover == 1) 
				{
					Hoverframe.fillRect(keyboard.savegamecoodx, keyboard.savegamecoody, keyboard.savegame.w, keyboard.savegame.h);
				}
				OutLine.drawRect(keyboard.BigBox.x,keyboard.savegame.y,keyboard.BigBox.w/2,keyboard.savegame.h);
				OutLine.drawRect(keyboard.savegame.x,keyboard.savegame.y,keyboard.savegame.w,keyboard.savegame.h);
				puzzlebox.drawString("SAVE GAME", keyboard.savegame.x+15,keyboard.savegame.y+30);
			}
			
			OutLine.drawRect(keyboard.fillins.x,keyboard.fillins.y-50,keyboard.fillins.w,keyboard.fillins.h/3);
			OutLine.drawRect(keyboard.backspace.x,keyboard.backspace.y,keyboard.backspace.w,keyboard.backspace.h);
			OutLine.drawRect(keyboard.fillins.x, keyboard.fillins.y, keyboard.fillins.w, keyboard.fillins.h);
			OutLine.drawRect(keyboard.quit.x,keyboard.quit.y,keyboard.quit.w,keyboard.quit.h);
			OutLine.drawRect(keyboard.clear.x,keyboard.clear.y,keyboard.clear.w,keyboard.clear.h);
			if(keyboard.Hover == 1) 
			{
				Hoverframe.drawRect(keyboard.Hovercoodx,keyboard.Hovercoody,50,50);
				
			}
			
			puzzlebox.drawString("INPUTPAD", keyboard.fillins.x+25,keyboard.fillins.y-50+30);
			puzzlebox.drawString("BACKSPACE", keyboard.backspace.x+18, keyboard.backspace.y+30);
			puzzlebox.drawString("CLEAR ALL", keyboard.clear.x+20, keyboard.clear.y+30);
			puzzlebox.drawString("QUIT TO MAIN", keyboard.quit.x+4, keyboard.quit.y+30);

			bs.show();
			try 
			{
				Thread.sleep(16);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		//
		canvas.removeKeyListener(keyboard.InGameKeyboard);
		canvas.removeMouseListener(keyboard.mouseaction);
		canvas.removeMouseMotionListener(keyboard.mousemove);
	}
	public void run()
	{
		 while(true) 
		 {
			 while(Difficulty == "") 
				{
				 try {
						Thread.sleep(16);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			 	if(FirstGame && thread1 == thread2 && !LoadedGame)
				{
					 
					// for(long ix=0;ix<1000000000;ix++);
					 game = new Generate().GetGame(Difficulty);
					// System.out.println(new Generate().toString(game)+" "+(thread2));
					 
				}
			 	if(thread1 == thread2 && !LoadedGame)
				{
					keyboard.CheckFixedPoints();
					keyboard.InputCheck();
					Loading = false;
					keyboard.Hover = 0;
				}
			 	
				if(thread1 == thread2) 
				{
					System.out.println(Difficulty);
					Integer[][] nxtgame = new Generate().GetGame(Difficulty);
					System.out.println(new Generate().toString(nxtgame)+" "+thread1+" "+thread2);
					while(States == 2 && Loading == false) 
					{
						 try {
								Thread.sleep(16);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						if(!new Generate().toString(game).contains("0") && !keyboard.Conflict) 
						{
							Loading = true;
							keyboard.Hover = 0;
							game = nxtgame;
							FirstGame = false;
							LoadedGame = false;
							++thread1;
							break;
						}
						if(Reset) 
						{
							Loading = true;
							Difficulty = "";
							FirstGame = true;
							LoadedGame = false;
							keyboard.Hover = 0;
							break;
						}
					}
				}
				++thread2;	
		 }	
	}
	public void StartGame(GameUI game)
	{
		while(true) 
		{
			Reset = false;
			if(!game.isAlive()) 
			{
				game.start();
			}
			game.DrawMain();
			game.DrawSelect();
			game.DrawNumBox();
			++thread1;
		}
		
	}
	
}
