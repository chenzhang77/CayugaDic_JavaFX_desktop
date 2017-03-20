/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * @author cz5670
 *
 */
public class RemoveLine {

	
	public void removeLine(String cayuga,String english) {
		
		String outsingleStirng = english+"     "+cayuga+"\n";
		System.out.println("out="+outsingleStirng);
		try {
	      File inputFile = new File("data/cayuga-english_5.txt");
          if (!inputFile.isFile()) {
              System.out.println("Parameter is not an existing file");
              return;
          }
//          //Construct the new file that will later be renamed to the original filename.
          File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
//          
//          BufferedReader br = new BufferedReader(new FileReader("data/cayuga-english_5.txt"));
          //--
          

          FileOutputStream fos = new FileOutputStream(tempFile,true);
          OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");
          
          
      	String file = "data/cayuga-english_5.txt";
  		FileInputStream fstream;
  		try {
  			fstream = new FileInputStream(file);
  			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
  			BufferedReader br = new BufferedReader(chars);
  			String strLine;
  			
  			//Read File Line By Line
  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
  				
  				String[] outstring = strLine.split("     ");
  				//System.out.printf("1=%s-compare-%s-%d-%d   %d\n",outstring[0].replaceAll("\\p{C}", "?"),cayuga,outstring[0].replaceAll("\\p{C}", "?").length(),cayuga.length(),outstring[0].trim().compareTo(cayuga.trim()));
  				//System.out.printf("2=%s-compare-%s-%d-%d   %d\n",outstring[1].replaceAll("\\p{C}", "?"),english,outstring[1].replaceAll("\\p{C}", "?").length(),english.length(),outstring[1].trim().compareTo(english.trim()));
  				if(outstring[0].replaceAll("\\p{C}", "").trim().compareTo(cayuga.replaceAll("\\p{C}", "").trim()) != 0||outstring[1].replaceAll("\\p{C}", "").trim().compareTo(english.replaceAll("\\p{C}", "").trim()) !=0) {
  					strLine = String.format("%s%s",strLine,System.getProperty("line.separator"));
  					bw.append(strLine);
            	  //bw.write("\n");
  				}
//  	            if (!strLine.trim().equals(outsingleStirng.trim())) {
//  	            	System.out.println("1="+strLine.trim());
//  	            	System.out.println("2="+outsingleStirng.trim());
//              	  bw.append(strLine);
//              	  bw.write("\n");
//                   
                else {
                	System.out.println("3="+outsingleStirng);
                }
  			}
  			bw.close();
  			br.close();
  		} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
          
          
          //==
         

          
          //Read from the original file and write to the new
          //unless content matches data to be removed.
//          while ((line = br.readLine()) != null) {
//              if (!line.trim().equals(outsingleStirng)) {
//                  
//            	  bw.append(line);
//            	  bw.write("\n");
//                 
//              }
//          }
//          bw.close();
//          br.close();

          //Delete the original file
          if (!inputFile.delete()) {
              System.out.println("Could not delete file");
              return;
          }

          //Rename the new file to the filename the original file had.
          if (!tempFile.renameTo(inputFile))
              System.out.println("Could not rename file");
          }
      		catch (FileNotFoundException ex) {
      		ex.printStackTrace();
      }catch (IOException ex) {
          ex.printStackTrace();
      }
		
	}
}
