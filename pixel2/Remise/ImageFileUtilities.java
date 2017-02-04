

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;

public class ImageFileUtilities {

	Image content;
	Scanner theScanner;

	String metadata = new String("");
	int dimx = -1;
	int dimy = -1;
	int maxValue = -1;
	Pixel[][] tempPixel;
	int yCount = -1;

	ImageFileUtilities() {

	}
	
	//Main read methods
	public void read(File input) throws IOException {
		try {
			Scanner sc;
			sc = new Scanner(input);
			String format = sc.nextLine();

			if (fileIsPNM(format)) {
				readPNM(sc);

			} else if (fileIsPGM(format)) {
				readPGM(sc);
			} else {
				sc.close();
				throw new IOException();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	//Read PnM
	private void readPNM(Scanner sc) {
		String currentLine = sc.nextLine();
	
		boolean foundComments = false;
		boolean foundMaxValue = false;
		boolean foundSize = false;
		
		while (!foundComments) {
			
			if (currentLine.startsWith("#")) {
				addComments(currentLine);
				currentLine = sc.nextLine();
			} else {
				foundComments = true;
			}
		}
		while (!foundSize) {
			dimx = Integer.parseInt(parseDimension(currentLine)[0]);
			dimy = Integer.parseInt(parseDimension(currentLine)[1]);
			foundSize = true;
			currentLine = sc.nextLine();
		}
		
		while (!foundMaxValue) {
			maxValue = Integer.parseInt(currentLine);
			foundMaxValue = true;
		}
		
		//Read for each pixels values, calls nextline 3 time for each pixel.
		tempPixel = new Pixel[dimy][dimx];
		for (int y = 0; y < dimy; y++) {
			for (int x = 0; x < dimx; x++) {		
				tempPixel[y][x] = new Pixel(Integer.parseInt(sc.nextLine()),Integer.parseInt(sc.nextLine()),Integer.parseInt(sc.nextLine()));
			}
		}
		content = new Image(metadata, maxValue, tempPixel);
	}

	private void readPGM(Scanner sc) {
		String currentLine = sc.nextLine();
		System.out.println("File is PGM and is one dimension");
		boolean foundComments = false;
		boolean foundMaxValue = false;
		boolean foundSize = false;

		while (!foundComments) {
			currentLine = sc.nextLine();
			
			if (currentLine.startsWith("#")) {
				addComments(currentLine);
			} else {
				foundComments = true;
			}

		}
		while (!foundSize) {
			dimx = Integer.parseInt(parseDimension(currentLine)[0]);
			dimy = Integer.parseInt(parseDimension(currentLine)[1]);
			foundSize = true;
			currentLine = sc.nextLine();
		}
		while (!foundMaxValue) {
			maxValue = Integer.parseInt(currentLine);
			foundMaxValue = true;
			currentLine = sc.nextLine();
		}
		
		//Call nextLine() only once since it's a PGM.
		tempPixel = new Pixel[dimy][dimx];
		for (int y = 0; y < dimy; y++) {
			for (int x = 0; x < dimx; x++) {
				
				if(sc.hasNextLine())
				{
				tempPixel[y][x] = new Pixel(Integer.parseInt(sc.nextLine()));
				}
			}
		}
		content = new Image(metadata, maxValue, tempPixel);
	}
	
	
	public void writePNM(Image input, String fileName) throws IOException {
		
		File fout = new File(fileName);
		FileOutputStream fos = new FileOutputStream(fout);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		bw.write("P3");
		bw.newLine();
		bw.write(input.getMetadata());
		bw.newLine();
		bw.write(input.getWidth() + " " + input.getHeight());
		bw.newLine();
		
		bw.write(Integer.toString(input.getMaxRange()));
		for(int y = 0; y < input.getHeight();y++)
		{
			for(int x = 0; x < input.getWidth();x++)
			{
				bw.newLine();
				bw.write(Integer.toString(input.getPixel(y, x).getRed()));
				bw.newLine();
				bw.write(Integer.toString(input.getPixel(y, x).getGreen()));
				bw.newLine();
				bw.write(Integer.toString(input.getPixel(y, x).getBlue()));
			}
		}
	 
		bw.close();
	}
	
	public void writePGM(Image input,String fileName) throws IOException
	{
		
		input.toGrey();
		File fout = new File(fileName);
		FileOutputStream fos = new FileOutputStream(fout);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		bw.write("P2");
		bw.newLine();
		bw.write(input.getMetadata());
		bw.newLine();
		bw.write(input.getWidth() + " " + input.getHeight());
		bw.newLine();
		
		bw.write(Integer.toString(input.getMaxRange()));
		for(int y = 0; y < input.getHeight();y++)
		{
			for(int x = 0; x < input.getWidth();x++)
			{
				bw.newLine();
				bw.write(Integer.toString(input.getPixel(y, x).getRed()));
			}
		}
		
		bw.close();
	}

	public String[] parseDimension(String input) {
		return input.split(" ");
	}
	
	private void addComments(String inputLine) {
			metadata += inputLine + "/n";
	}

	private boolean fileIsPNM(String format) {
		if (format.equals("P3")) {
			return true;
		}
		return false;
	}

	private boolean fileIsPGM(String format) {
		if (format.equals("P2")) {	
			return true;
		}
		return false;
	}

}

