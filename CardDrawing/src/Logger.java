import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR ="\n";
	
	public Logger(){
		
	}
	
	public boolean writeFile(String filename, String content){
		File file = null;
		FileWriter filewriter = null;
		BufferedWriter bufferedwriter = null;
		
		try {
			file = new File(filename);
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			filewriter = new FileWriter(filename, true);
			bufferedwriter = new BufferedWriter(filewriter);
			bufferedwriter.newLine();
			bufferedwriter.write(content);
			bufferedwriter.flush();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			writeError(filename, e.getMessage());
			return  false;
		}finally{
			
			try {
				if(filewriter != null){
					filewriter.close();
				}
				if(bufferedwriter != null){
					bufferedwriter.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void writeCSV(String filename, int numTrials, int trialno, int numCards, int sum, boolean withReplacement){
		File file = null;
		FileWriter filewriter = null;
		BufferedWriter bufferedwriter = null;
		
		try {
			file = new File(filename);
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			filewriter = new FileWriter(filename, true);
			bufferedwriter = new BufferedWriter(filewriter);
			bufferedwriter.write(numTrials);
			bufferedwriter.write(COMMA_DELIMITER);
			bufferedwriter.write(trialno);
			bufferedwriter.write(COMMA_DELIMITER);
			bufferedwriter.write(numCards);
			bufferedwriter.write(COMMA_DELIMITER);
			bufferedwriter.write(sum);
			bufferedwriter.write(COMMA_DELIMITER);
			
			if(withReplacement)
				bufferedwriter.write("with");
			else
				bufferedwriter.write("without");
			bufferedwriter.write(COMMA_DELIMITER);
			bufferedwriter.newLine();
			bufferedwriter.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			writeError(filename, e.getMessage());
		}finally{
			
			try {
				if(filewriter != null){
					filewriter.close();
				}
				if(bufferedwriter != null){
					bufferedwriter.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void writeError(String filename, String message){
		File file;
		FileWriter filewriter = null;
		BufferedWriter bufferedwriter = null;
		
		try {
			file = new File(filename);
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			filewriter = new FileWriter(filename, true);
			bufferedwriter = new BufferedWriter(filewriter);
			bufferedwriter.write(NEW_LINE_SEPARATOR);
			bufferedwriter.write("====================ERROR MESSAGE====================");
			bufferedwriter.write(message);
			bufferedwriter.write("=====================================================");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
