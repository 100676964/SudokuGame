
import java.util.ArrayList;


public class Solver 
{
	int NS = 0;
	int HSR = 0;
	int HSC = 0;
	int HSB = 0;
	
	public ArrayList<Integer> CheckCandidates(Integer[][] game, int x, int y) 
	{	
		if(game[x][y] == 0) 
		{
			ArrayList<Integer> pool = new ArrayList<Integer>();
			
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
			return pool;
		}
		else 
		{
			ArrayList<Integer> pool = new ArrayList<Integer>();
			pool.add(game[x][y]);
			return pool;
		}
	}
	public ArrayList<ArrayList<Integer>> TotalCandidates(Integer[][]game) 

	{
		ArrayList<ArrayList<Integer>> numbers = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < game[0].length; ++i) 
		{
			for(int j = 0; j < game.length; ++j) 
			{
				numbers.add(i*9+j,CheckCandidates(game,i,j));
				//System.out.print(CheckCandidates(game,i,j));
			}
			//System.out.println();
		}
		 
		return numbers;
	}
	public void NakedSingle(Integer game[][],int x, int y) 
	{
		
		ArrayList<ArrayList<Integer>> CandidatesMap = TotalCandidates(game);
		if(y > 8)
		{
			
		}
		else 
		{
			if(x < 8) 
			{
				if(game[x][y] == 0 && CandidatesMap.get(x*9+y).size() == 1)
				{
					game[x][y] = CandidatesMap.get(x*9+y).get(0);
					//System.out.print(CandidatesMap.get(x*9+y));
					++NS;
					NakedSingle(game,0,0);
				}
				else 
				{
					NakedSingle(game,x+1,y);
				}
				
			}
			else 
			{
				if(game[x][y] == 0 && CandidatesMap.get(x*9+y).size() == 1)
				{
					game[x][y] = CandidatesMap.get(x*9+y).get(0);
					//System.out.print(CandidatesMap.get(x*9+y));
					++NS;
					NakedSingle(game,0,0);
				}
				else 
				{
					NakedSingle(game,0,y+1);
				}
				
			}
		}
		
				
	}
	public void HiddenSingleR(Integer game[][],int x, int y) 
	{
		
		ArrayList<ArrayList<Integer>> CandidatesMap = TotalCandidates(game);
		if(x < 9)
		{
				for(int j = 0; j < 9; ++j) 
				{
					//System.out.print(CandidatesMap.get(x*9+j));
					for(int i = 0; i < 9; ++i) 
					{
						
						if(j != i) 
						{
							CandidatesMap.get(x*9+j).removeAll(CandidatesMap.get(x*9+i));
							
						}
						
					}
					if(!CandidatesMap.get(x*9+j).isEmpty() && CandidatesMap.get(x*9+j).size() == 1 && game[x][j] == 0) 
					{
						game[x][j] = CandidatesMap.get(x*9+j).get(0);
						//System.out.print(CandidatesMap.get(x*9+j).get(0));
						++HSR;
						HiddenSingleR(game,0,0);
						
					}
					CandidatesMap = TotalCandidates(game);
					//System.out.print(CandidatesMap.get(x*9+j));

				}
				//System.out.println();
				HiddenSingleR(game,x+1,0);
			}
		
	
		
			
		
			
			
		
	}
	public void HiddenSingleC(Integer game[][],int x, int y) 
	{
		ArrayList<ArrayList<Integer>> CandidatesMap = TotalCandidates(game);
		if(y < 9)
		{		
				for(int j = 0; j < 9; ++j) 
				{
					//System.out.print(CandidatesMap.get(x*9+j));
					for(int i = 0; i < 9; ++i) 
					{
						
						if(j != i) 
						{
							
							CandidatesMap.get(j*9+y).removeAll(CandidatesMap.get(i*9+y));
							
						}
						
					}
					if(!CandidatesMap.get(j*9+y).isEmpty() && CandidatesMap.get(j*9+y).size() == 1 && game[j][y] == 0) 
					{
						game[j][y] = CandidatesMap.get(j*9+y).get(0);
						//System.out.print(CandidatesMap.get(j*9+y).get(0));
						++HSC;
						HiddenSingleC(game,0,0);
					}
					CandidatesMap = TotalCandidates(game);
					//System.out.print(CandidatesMap.get(j*9+y));

				}
				//System.out.println();
				HiddenSingleC(game,0,y+1);
			}
			
	}
	public void HiddenSingleB(Integer game[][],int x, int y) 
	{
		if(y > 8) 
		{
			
		}
		else 
		{	
			ArrayList<ArrayList<Integer>> CandidatesMap = TotalCandidates(game);
//			if(!CandidatesMap.get(x*9+y).isEmpty()){
//				System.out.print(CandidatesMap.get(x*9+y)+"-"+ x+" "+y);
//				}else {System.out.print("[]"+"-"+x+" "+y+" ");}
			if(x < 8)
			{	
					for(int i = (x/3)*3; i < (x/3)*3+3; ++i) 
					{
						
						for(int j = (y/3)*3; j < (y/3)*3+3; ++j) 
						{
							
							if(x*9+y != i*9+j) 
							{	
								CandidatesMap.get(x*9+y).removeAll(CandidatesMap.get(i*9+j));
								
							}
//							System.out.print(i+"+"+j+" ");
//							System.out.print(CandidatesMap.get(i*9+j));
							
						}
//						System.out.println();
					}
//					System.out.print(CandidatesMap.get(x*9+y));
//					if(!CandidatesMap.get(x*9+y).isEmpty()){
//					System.out.print(CandidatesMap.get(x*9+y)+"-"+ x+" "+y+" ");
//					}else {System.out.print("[]"+"-"+x+" "+y+" ");}
					if(!CandidatesMap.get(x*9+y).isEmpty() && CandidatesMap.get(x*9+y).size() == 1 && game[x][y] == 0) 
					{
						game[x][y] = CandidatesMap.get(x*9+y).get(0);
						++HSB;
						
						HiddenSingleB(game,0,0);
						
							
					}
					HiddenSingleB(game,x+1,y);
					
				
			}
			else
			{
					for(int i = (x/3)*3; i < (x/3)*3+3; ++i) 
					{
						for(int j = (y/3)*3; j < (y/3)*3+3; ++j) 
						{
							
							if(x*9+y != i*9+j) 
							{	
								
								CandidatesMap.get(x*9+y).removeAll(CandidatesMap.get(i*9+j));
								
							}
//							System.out.print(CandidatesMap.get(i*9+j));
							
						}
//						System.out.println();
					}
//					System.out.print(CandidatesMap.get(x*9+y));
//					if(!CandidatesMap.get(x*9+y).isEmpty()){
//						System.out.print(CandidatesMap.get(x*9+y)+"-"+ x+" "+y+" ");
//						}else {System.out.print("[]"+"-"+x+" "+y+" ");}
					if(!CandidatesMap.get(x*9+y).isEmpty() && CandidatesMap.get(x*9+y).size() == 1 && game[x][y] == 0) 
					{
						game[x][y] = CandidatesMap.get(x*9+y).get(0);
						++HSB;
						HiddenSingleB(game,0,0);	
							
					}
					
					HiddenSingleB(game,0,y+1);
				
			}
		}
		
	}
	public void Solve(Integer game[][])
	{
		for(int i = 0; i < 3; ++i) 
		{
			if(new Generate().toString(game).contains("0")) 
			{
				NakedSingle(game,0,0);
				HiddenSingleR(game,0,0);
				HiddenSingleC(game,0,0);
				HiddenSingleB(game,0,0);
				NakedSingle(game,0,0);
			}
		}		
	}

}
