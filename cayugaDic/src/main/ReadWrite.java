package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class ReadWrite {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String file = "data/cayuga-english.txt";
		File fout = new File("out.txt");
		// Open the file
		
		
		FileInputStream fstream = new FileInputStream(file);
		
		//UnicodeReader ur = new UnicodeReader(fstream, "UTF-8");
		
		Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
		
		BufferedReader br = new BufferedReader(chars);

		String strLine;

		FileOutputStream fos = new FileOutputStream(fout);
		 
		 OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)."UTF-16");
		
		
		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
			System.out.println (strLine);
			String[] outstring = strLine.split("\t");
			if(outstring.length == 3) {
				//String outsingleStirng = outstring[1]+"|"+outstring[2];
				//bw.write(outsingleStirng);
				bw.write("\n");
			}
			
		 
		}

		//Close the input stream
		br.close();
		
	}

}
