import java.io.*;
import java.util.*;

public class CSVReader {
		
	private ArrayList<String> allLines = new ArrayList<String>();
	
	public CSVReader(String fileName) {
		try {
			FileReader f = new FileReader(fileName);
			Scanner s = new Scanner(f);
			s.nextLine(); //header line skip
			while(s.hasNextLine()) {
				String line = s.nextLine();
				allLines.add(line);
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
	
	public ArrayList<String> getAllLines(){
		return allLines;
	}

}
