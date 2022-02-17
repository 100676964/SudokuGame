
import java.util.ArrayList;
import java.util.Collections;

public class PullNumbers 
{
	
	public boolean removerA(Integer[][] game, int x , int y) 
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
				return removerA(game, x+1 , y);
			}
			else
			{
				return removerA(game, 0 , y+1);
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
					game[x][y] = pool.get(0);
					failednumber.add(game[x][y]);
					if (x < 8) 
					{
						
						repeat = removerA(game, x+1 , y);
					}
					else
					{
						repeat = removerA(game, 0 , y+1);
					}
				}
				else
				{
					return false;
				}
			}
		
			
		
		
		return repeat;
	}
	public boolean removerB(Integer[][] game, int x , int y) 
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
				return removerB(game, x+1 , y);
			}
			else
			{
				return removerB(game, 0 , y+1);
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
					game[x][y] = pool.get(pool.size()-1);
					failednumber.add(game[x][y]);
					if (x < 8) 
					{
						
						repeat = removerB(game, x+1 , y);
					}
					else
					{
						repeat = removerB(game, 0 , y+1);
					}
				}
				else
				{
					return false;
				}
			}
		
			
		
		
		return repeat;
	}	
	public Integer[][] pullE(Integer[][]game) 
	{
		Integer[][] temp1 = new Integer[game.length][game[0].length];
		ArrayList<Integer> pool = new ArrayList<Integer>();
		for(int l = 0; l < 9; ++l) 
		{
			for(int x = 0; x < 9; ++x)
			{
				pool.add(l*9+x);
			}
			
		}
		for(int i = 0; i < game[0].length; ++i) 
		{
			for(int j = 0; j < game.length; ++j) 
			{
				temp1[i][j] = game[i][j];
			}
		}
		Collections.shuffle(pool);
		for(int times = 0; times < 81; ++times) 
		{	
			int i	=	(int)((double)pool.get(times)/9);
			int j	= pool.get(times)%9;
				temp1[i][j] = 0;
				new Solver().NakedSingle(temp1,0,0);
				if(!new Generate().toString(temp1).contains("0")) 
				{
					game[i][j] = 0;
					for(int x = 0; x < game[0].length; ++x) 
					{
						for(int y = 0; y < game.length; ++y) 
						{
							temp1[x][y] = game[x][y];
						}
					} 
					
				}
				else 
				{
					for(int x = 0; x < game[0].length; ++x) 
					{
						for(int y = 0; y < game.length; ++y) 
						{
							temp1[x][y] = game[x][y];
						}
					} 
				}
		}
	return game;
	}
	public Integer[][] pullM(Integer[][]game)
	{
		Integer[][] temp1 = new Integer[game.length][game[0].length];
		ArrayList<Integer> pool = new ArrayList<Integer>();
		for(int l = 0; l < 9; ++l) 
		{
			for(int x = 0; x < 9; ++x)
			{
				pool.add(l*9+x);
			}
			
		}
		for(int i = 0; i < game[0].length; ++i) 
		{
			for(int j = 0; j < game.length; ++j) 
			{
				temp1[i][j] = game[i][j];
			}
		}
		Collections.shuffle(pool);
		for(int times = 0; times < 81; ++times) 
		{	
			int i	=	(int)((double)pool.get(times)/9);
			int j	= pool.get(times)%9;
			temp1[i][j] = 0;
			new Solver().Solve(temp1);
			if(!new Generate().toString(temp1).contains("0")) 
			{
				
				game[i][j] = 0;
				for(int x = 0; x < game[0].length; ++x) 
				{
					for(int y = 0; y < game.length; ++y) 
					{
						temp1[x][y] = game[x][y];
					}
				} 
				
			}
			else 
			{
				for(int x = 0; x < game[0].length; ++x) 
				{
					for(int y = 0; y < game.length; ++y) 
					{
						temp1[x][y] = game[x][y];
					}
				}
			}
		}
		return game;
	}
	public Integer[][] pullH(Integer[][]game)
	{
		Integer[][] temp1 = new Integer[game.length][game[0].length];
		Integer[][] temp2 = new Integer[game.length][game[0].length];
		ArrayList<Integer> pool = new ArrayList<Integer>();
		for(int l = 0; l < 9; ++l) 
		{
			for(int x = 0; x < 9; ++x)
			{
				pool.add(l*9+x);
			}
			
		}
		for(int i = 0; i < game[0].length; ++i) 
		{
			for(int j = 0; j < game.length; ++j) 
			{
				temp1[i][j] = game[i][j];
				temp2[i][j] = game[i][j];
			}
		}
		Collections.shuffle(pool);
		for(int times = 0; times < 81; ++times) 
		{		
			int i	=	(int)((double)pool.get(times)/9);
			int j	= pool.get(times)%9;
			temp1[i][j] = 0;
			temp2[i][j] = 0;
			removerA(temp1,0,0);
			removerB(temp2,0,0);
			if(new Generate().toString(temp1).equals(new Generate().toString(temp2))) 
			{
				game[i][j] = 0;
				for(int x = 0; x < game[0].length; ++x) 
				{
					for(int y = 0; y < game.length; ++y) 
					{
						temp1[x][y] = game[x][y];
						temp2[x][y] = game[x][y];
					}
				} 
				
			}
			else 
			{
				for(int x = 0; x < game[0].length; ++x) 
				{
					for(int y = 0; y < game.length; ++y) 
					{
						temp1[x][y] = game[x][y];
						temp2[x][y] = game[x][y];
					}
				} 
			}
		}
//		for(int i = 0; i < game[0].length; ++i) 
//		{
//			for(int j = 0; j < game.length; ++j) 
//			{
//				if(game[i][j] != 0) 
//				{
//					temp1[i][j] = 0;
//					temp2[i][j] = 0;
//					removerA(temp1,0,0);
//					removerB(temp2,0,0);
//					if(new Generate().toString(temp1).equals(new Generate().toString(temp2))) 
//					{
//						game[i][j] = 0;
//						for(int x = 0; x < game[0].length; ++x) 
//						{
//							for(int y = 0; y < game.length; ++y) 
//							{
//								temp1[x][y] = game[x][y];
//								temp2[x][y] = game[x][y];
//							}
//						} 
//						
//					}
//					else 
//					{
//						for(int x = 0; x < game[0].length; ++x) 
//						{
//							for(int y = 0; y < game.length; ++y) 
//							{
//								temp1[x][y] = game[x][y];
//								temp2[x][y] = game[x][y];
//							}
//						} 
//					}			
//				}		
//			}
//		}
		return game;
	}
}
