
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dispatcher{
	public static void main(String[] args) throws IOException {
		
		
		System.out.println("Choose action \n1 - Clean \n2 - Count");
		
		BufferedReader bf1 = new BufferedReader(new InputStreamReader(System.in));
		int method = Integer.parseInt(bf1.readLine());
		
		System.out.println("Enter path to folder");
		
		BufferedReader bf2 = new BufferedReader(new InputStreamReader(System.in));
		String folderPath = bf2.readLine();
		
		
		File folder = new File(folderPath);
		
		Controller.toDo(folder, method);
		
	}
}
class Controller{
	
	static public void toDo(File f, int i) {
		if(i == 1) {
			FileMethods.cleanFolderFromFiles(f);
		}else if(i == 2) {
			FileMethods.listFilesForFolder(f);
		}else {
			System.out.println("number " + i + " out of the range");
		}
	}
}

class FileAdditionalCommands{
	
	public static int fileSize(File folder) {
		
		int result = 0;
		
		for (File fileEntry : folder.listFiles()) {
	        result++;
	    }
		return result;
	}
}

class FileMethods{
	
	private static int amount = 0;
//	private static int uniqueAmount = 0;
	private static String mainFolderName = "";

	static public void listFilesForFolder(File folder) {
		
		if(mainFolderName.equals("")) {
			mainFolderName = folder.getName();
		}
		
//		uniqueAmount = 0;
		
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	amount++;
//	        	uniqueAmount++;
	        }
	        
	    }
	    if(folder.getName().equals(mainFolderName)) {
        	System.out.println("Folder " + mainFolderName + " have: " + amount + " files");
        }
//	    else {
//	    	System.out.println("Folder " + folder.getName() + " have: " + uniqueAmount + " files");
//	    }
	}
	
	
	static public void cleanFolderFromFiles(File folder) {
		
		int deletes = 0;
		int expectedDeletes = FileAdditionalCommands.fileSize(folder);
		
	    for (File fileEntry : folder.listFiles()) {
	        if(fileEntry.delete()) {
	        	deletes++;
	        }
	    }
	    if(deletes == 0) {
			System.out.println("Folder is empty");
		}else {
			if(deletes == expectedDeletes) {
	        	System.out.println("Successfully deleted " + deletes + " files");
	        }else {
	        	System.out.println("Something went Wrong");
	        	System.out.println("File xpected to delete " + expectedDeletes + "\nWas deleted " + deletes);
	        }
		}
	    
	}
}