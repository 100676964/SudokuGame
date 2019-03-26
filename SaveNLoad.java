import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveNLoad 
{
	
	public void Load() throws FileNotFoundException 
	{
		FileReader reader = new FileReader("C:/Sudoku/Gamesave");
		BufferedReader br = new BufferedReader(reader);
		String difficulty ="";
		String game = "";
		String fixed = "";
		try 
		{
			difficulty = br.readLine();
			game = br.readLine();
			fixed = br.readLine();
			br.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		for(int i = 0; i < 9; ++i) 
		{
			for(int j = 0; j < 9; ++j) 
			{
				GameUI.game[i][j] = Integer.valueOf(String.valueOf(game.charAt(i*9+j))) ;
				if(String.valueOf(fixed.charAt(i*9+j)).equalsIgnoreCase("1")) 
				{
					InGameKeyInput.IsFixed[i][j] = true;
					
				}
				else 
				{
					InGameKeyInput.IsFixed[i][j] = false;
				}
				
			}
		}
		
		GameUI.Difficulty = difficulty;
		GameUI.LoadedGame = true;
		GameUI.States = 2;
		GameUI.Loading = false;
		
		
		
		
	}
	
	public void Save() throws IOException 
	{
		String game = "";
		String fixed = "";
		File path = new File("C:/Sudoku");
		
		path.mkdir();
		
		File save = new File("C:/Sudoku/Gamesave");
		
		save.createNewFile();
		FileWriter write = new FileWriter(save);
		BufferedWriter bw = new BufferedWriter(write);
		for(int i = 0; i < 9; ++i) 
		{
			for(int j = 0; j < 9; ++j) 
			{
				game += GameUI.game[i][j].toString();
				if(InGameKeyInput.IsFixed[i][j] == true) 
				{
					fixed += "1";
				}
				else 
				{
					fixed += "0";
				}
				
			}
		}
		bw.write(GameUI.Difficulty);
		bw.newLine();
		bw.write(game);
		bw.newLine();
		bw.write(fixed);
		bw.close();
		 
	}
}
