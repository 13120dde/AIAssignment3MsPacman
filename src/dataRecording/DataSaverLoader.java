package dataRecording;

import pacman.game.util.*;

/**
 * This class uses the IO class in the PacMan framework to do the actual saving/loading of
 * training data.
 * @author andershh
 *
 */
public class DataSaverLoader {
	
	private static String FileName = "trainingData.txt";
	
	public static void SavePacManData(DataTuple data)
	{
		IO.saveFile(FileName, data.getSaveString(), true);
	}

	public static DataTuple[] LoadPacManData()
	{
		return LoadPacManData(FileName);
	}

	public static DataTuple[] LoadPacManData(String fileName)
	{
		String data = IO.loadFile(fileName);
		String[] dataLine = data.split("\n");
		DataTuple[] dataTuples = new DataTuple[dataLine.length];
		
		for(int i = 0; i < dataLine.length; i++)
		{
			dataTuples[i] = new DataTuple(dataLine[i]);
		}
		
		return dataTuples;
	}
}
