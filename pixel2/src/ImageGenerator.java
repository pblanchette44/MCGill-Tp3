
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
// This class contains 2 utility methods that may aid you in testing.
// 1)generateRandomImage() will generate a "randomly" populated Image. The idea is to call this to make a small Image object and test your methods first.
// 2)printImage() : This method prints the red, green, and blue components of an Image object
// Note that the methods rely on the Image and Pixel class that you write, so they won't work until 
// until you have written them.
import java.util.Random;
public class ImageGenerator {
  // Note: Change the number 1 below to any other number for different results. e.g. new Random(2);
  private static Random r = new Random(1);


  public static void main(String[] args) {
	
	ImageFileUtilities testUtilities = new ImageFileUtilities();
	
	try {
		testUtilities.read(new File("catskype.pnm"));
	} catch (IOException e) {
		
		e.printStackTrace();
	}
		try {
			testUtilities.writePNM(testUtilities.content, "bob.pnm");
			testUtilities.writePGM(testUtilities.content, "bob.pgm");
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  public static void printImage(Image image) {
    System.out.println("Height: " +  image.getHeight());
    System.out.println("Width: " + image.getWidth());
    System.out.println("Red data:");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
       System.out.print(image.getPixel(i,j).getRed() + " " ); 
      }
      System.out.println();
    }
    
    System.out.println("\n\nGreen data");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
       System.out.print(image.getPixel(i,j).getGreen() + " " ); 
      }
      System.out.println();
    }
    
    System.out.println("\n\nBlue data");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
       System.out.print(image.getPixel(i,j).getBlue() + " " ); 
      }
      System.out.println();
    }    
  }
  
  public static Image generateRandomImage(int height, int width, boolean isColour) {
    Pixel[][] numbers = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (isColour) {
         numbers[i][j] = randomColourPixel(); 
        }
        else {
          numbers[i][j] = randomBwPixel();
        }
      }
    }
    
   return new Image("#Sample test image", 255, numbers); 
  }
  
  public static Pixel randomColourPixel() {
    int red = r.nextInt(256);
    int green = r.nextInt(256);
    int blue = r.nextInt(256);
    return new Pixel(red, green, blue);
  }
  
  public static Pixel randomBwPixel() {
   int intensity = r.nextInt(256);
   return new Pixel(intensity, intensity, intensity);
  }
}