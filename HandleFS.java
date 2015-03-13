import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/* 1. Create a new folder - Takes a parameter of absolute folder path
2. Create a new file - Take a parameter of absolute file path
3. Add content to a file - Take 2 parameters: Content to append to a file; Absolute path to a file
4. Copy files - Takes 2 parameters: Absolute path to a source file; Absolute path to a destination file (NOTE: If destination file exists, it will be overwritten)
5. Display file contents - Takes absolute path to a file as an input; Prints out file contents as an output
6. List folder contents - Takes absolute path to a folder as an input; Prints out folder contents as an output
7. Search for a file by name - Takes name of a file to find; Prints out list of absolute paths to files with matching names
8. Search for a file by name - Takes 2 parameters: Absolute path to a starting folder and file name; Outputs list of absolute paths to files with matching names
9. (Optional) Copy folders - Takes 2 parameters: Absolute path to source folder, Absolute path to destination folder

*/


public class HandleFS {
	private File inputDir = new File("/Users/spadaval/Downloads/inputDir");
	private File outPutDir = new File("/Users/spadaval/Downloads/outDir");
	private File inputFile = new File("/Users/spadaval/Downloads/inputDir/inputFile.txt");
	private File outputFile = new File("/Users/spadaval/Downloads/outputDir/outputFile.txt");
	private String fName2Search = "inputFile.txt";
	private File searchDir = new File("/Users/spadaval/downloads");
	private List<String> result = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		HandleFS hFS = new HandleFS();
		
		// 1. Create new Folder
		try {
		
			if(!hFS.inputDir.exists())
				hFS.inputDir.mkdir();
			
		
		// 2. Create a new File
			if(!hFS.inputFile.exists())
				hFS.inputFile.createNewFile();
		
		// 3. Add contents to the file
			FileWriter fw = new FileWriter(hFS.inputFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Madhavi\n");
			bw.close();
		
		// 4. Copying file from one location to other
			hFS.fileCopy(hFS.inputFile, hFS.outputFile);
		
		// 5. Display Contents of a file
			hFS.dispContents(hFS.inputFile);
			
		// 6. List folder contents
			hFS.listDirContents(hFS.inputDir);
			
		// 7. Search for fileName in a givenDirectory
			hFS.searchDirectory(hFS.fName2Search);
			 
			int count1 = hFS.getResult().size();
			
			if(count1 ==0){
			    System.out.println("\nNo result found!");
			}else{
			    System.out.println("\nFound " + count1 + " result!\n");
			    for (String matched : hFS.getResult()){
				System.out.println("Found : " + matched);
			    }
			}
			
		// 8. Search fileName 
			hFS.result.clear();
			hFS.searchDirectory(hFS.searchDir, hFS.fName2Search);
			 
			int count2 = hFS.getResult().size();
			
			if(count2 ==0){
			    System.out.println("\nNo result found!");
			}else{
			    System.out.println("\nFound " + count2 + " result!\n");
			    for (String matched : hFS.getResult()){
				System.out.println("Found : " + matched);
			    }
			}
			
		// 9. Copy folder from one location to other location
			hFS.copyFolder(hFS.inputDir, hFS.outPutDir);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void fileCopy(File inDir, File outDir){
		Path FROM = Paths.get(inDir.getAbsolutePath());
	    Path TO = Paths.get(outDir.getAbsolutePath());
	    //overwrite existing file, if exists
	    CopyOption[] options = new CopyOption[]{
	      StandardCopyOption.REPLACE_EXISTING,
	      StandardCopyOption.COPY_ATTRIBUTES
	    }; 
	    
	    try {
			Files.copy(FROM, TO, options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dispContents(File inFile){
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(inFile));
			String line;
			
			while((line = in.readLine()) != null)
			{
			    System.out.println(line);
			}
			
			in.close();
		}	
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void listDirContents(File inDir){
		String files;
		File folder = new File(inDir.getAbsolutePath());
		File[] listOfFiles = folder.listFiles(); 
 
		for (int i = 0; i < listOfFiles.length; i++) 
		{
		   if (listOfFiles[i].isFile()) 
		   {
			   files = listOfFiles[i].getName();
			   System.out.println(files);
		   }
		}
	}
	
	public List<String> getResult() {
		return result;
	}
	
	public void searchDirectory(String fileNameToSearch) {
		 
		if (this.searchDir.isDirectory()) {
		    search(this.searchDir, fileNameToSearch);
		} else {
		    System.out.println(this.searchDir.getAbsoluteFile() + " is not a directory!");
		}
	 
	}
	
	public void searchDirectory(File directory, String fileNameToSearch) {
		 
		//setFileNameToSearch(fileNameToSearch);
	 
		if (directory.isDirectory()) {
		    search(directory, fileNameToSearch);
		} else {
		    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
		}
	 
	}
	 
	private void search(File file, String fName) {
	 
		if (file.isDirectory()) {
		  System.out.println("Searching directory ... " + file.getAbsoluteFile());
	 
	      if (file.canRead()) {
			for (File temp : file.listFiles()) {
			    if (temp.isDirectory()) {
			    	search(temp, fName);
			    } else {
				if (fName.equals(temp.getName())) {			
				    result.add(temp.getAbsoluteFile().toString());
			    }
	 
			    }
		    }
	 
		 } else {
			System.out.println(file.getAbsoluteFile() + "Permission Denied");
		 }
	 
		}
	}
	
	public void copyFolder(File srcFolderPath, File destFolderPath){
		try {
			if (!srcFolderPath.isDirectory()) {
				InputStream in;
				in = new FileInputStream(srcFolderPath);
			
				OutputStream out = new FileOutputStream(destFolderPath);
				byte[] buffer = new byte[1024];
				int length;
	        
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
	            
				in.close();
	            out.close();
	            
	            System.out.println("File copied from " + srcFolderPath + " to "
                    + destFolderPath + " successfully");
			} 
			else {
				if (!destFolderPath.exists()) {
					destFolderPath.mkdir();
					System.out.println("Directory copied from " + srcFolderPath
                        + "  to " + destFolderPath + " successfully");
				}
				String folder_contents[] = srcFolderPath.list();
				for (String file : folder_contents) {
	                File srcFile = new File(srcFolderPath, file);
	                File destFile = new File(destFolderPath, file);
	                copyFolder(srcFile, destFile);
				}
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException io) {
			// TODO Auto-generated catch block
			io.printStackTrace();
		}
	}
}
