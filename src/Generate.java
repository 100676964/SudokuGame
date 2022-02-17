
import java.util.ArrayList;




public class Generate
{
	Integer[][] game = new Box().box();
	boolean[][] IsFixed = new boolean[9][9];
	public String toString(Integer[][] Array) 
	{
		String string = "";
		for(int i = 0; i < Array[0].length; ++i) 
		{
			for(int j = 0; j < Array.length; ++j) 
			{
				string += Array[i][j];
				
				
			}
			
		}
		return string;
	}
	public void print() 
	{
		for(int i = 0; i < game[0].length; ++i) 
		{
			for(int j = 0; j < game.length; ++j) 
			{
				
				System.out.print(game[i][j]+" ");
				
			}
			System.out.println();
		}
	}
	public boolean creatpool(Integer[][] game, int x , int y) 
	{
		if(y > 8) 
		{
			return true;
		}
		boolean repeat = false;
		ArrayList<Integer> failednumber = new ArrayList<Integer>();
		if(game[x][y] != 0)
		{
			if (x < 8) 
			{
				return creatpool(game, x+1 , y);
			}
			else
			{
				return creatpool(game, 0 , y+1);
			}
		}
				
				
			while(!repeat)
			{
				ArrayList<Integer> pool = new ArrayList<Integer>();
				game[x][y] = 0;
				for(int l = 0; l < 9; ++l) 
				{
					pool.add(l+1);
				}
				for(int i = 0; i < 9; ++i) 
				{
					pool.remove(game[x][i]);
				}
				for(int i = 0; i < 9; ++i) 
				{
					pool.remove(game[i][y]);
				}
				for(int i = 0; i < failednumber.size(); ++i) 
				{
					pool.remove(failednumber.get(i));
				}
				for(int i = 0; i < 3; ++i) 
				{
					for(int j = 0; j < 3; ++j)
					{
						if(x < 3 && y <3)// box1
						{
							pool.remove(game[i][j]);
						}
						if(x < 3 && y >= 3 && y < 6)  // box2
						{
							pool.remove(game[i][j+3]);
						}
						if(x < 3 && y >= 6)  //box 3
						{
							pool.remove(game[i][j+6]);
						}
						if(x >= 3 && x < 6 && y < 3) //box4
						{
							pool.remove(game[i+3][j]);
						}
						if(x >= 3 && x < 6 && y >= 3 && y < 6)//box5
						{
							pool.remove(game[i+3][j+3]);
						}
						if(x >= 3 && x < 6 && y >= 6) //box6
						{
							pool.remove(game[i+3][j+6]);
						}
						if(x >= 6 && y < 3) //box7
						{
							pool.remove(game[i+6][j]);
						}
						if(x >= 6 && y >= 3 && y < 6) //box8
						{
							pool.remove(game[i+6][j+3]);
						}
						if(x >= 6 && y >= 6) //box9
						{
							pool.remove(game[i+6][j+6]);
						}
						
					}
				}
				
			
				if(pool.size() != 0) 
				{
					game[x][y] = pool.get((int) (Math.random()*pool.size()));
					failednumber.add(game[x][y]);
					if (x < 8) 
					{
						
						repeat = creatpool(game, x+1 , y);
					}
					else
					{
						repeat = creatpool(game, 0 , y+1);
					}
				}
				else
				{
					return false;
				}
			}
		
			
		
		
		return repeat;
	}	
	public Integer[][] GetGame(String difficulty)
	{
		if(difficulty.equals("E")) 
		{
			creatpool(game,0,0);
			game = new PullNumbers().pullE(game);
			//for(long i=0;i<1000000000;i++) for(long j=0;j<10;j++);
			return game;
		}
		else if(difficulty.equals("M")) 
		{
			creatpool(game,0,0);
			game = new PullNumbers().pullM(game);
			return game;
		}
		else if(difficulty.equals("H"))
		{
			
			
			
			while(GameUI.thread1 == GameUI.thread2)
			{
				Solver Hard = new Solver();
				Integer[][] temp = new Integer[9][9];
				creatpool(game,0,0);
				game = new PullNumbers().pullH(game);
				for(int x = 0; x < game[0].length; ++x) 
				{
					for(int y = 0; y < game.length; ++y) 
					{
						temp[x][y] = game[x][y];
					}
				}
				Hard.Solve(temp);
				System.out.println(Hard.HSB+Hard.HSC+Hard.HSR+Hard.NS);
				GameUI.loadingvar = "Patient";
				if(Hard.HSB+Hard.HSC+Hard.HSR+Hard.NS < 15) 
				{
					GameUI.loadingvar = "";
					break;
				}
			}
		}
		
			return game;
		
		
	}

}
		



