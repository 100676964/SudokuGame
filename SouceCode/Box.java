
public class Box 
{
	
	public Integer[][] box()
	{
		Integer[][]game = new Integer[9][9];
		for(int i = 0; i < game[0].length; ++i) 
		{
			for(int j = 0; j < game.length; ++j) 
			{
				game[i][j] = 0;
			}
		}
		return game;
	}

	
	
	

}
