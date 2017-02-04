

import java.io.File;
import java.io.IOException;

public class Comp202Photoshop {

	public static void main(String[] args) {
		
		ImageFileUtilities Utility = new ImageFileUtilities();
		
		try {
			Utility.read(new File(args[0]));
		} catch (IOException e) {
			System.out.println("Couldn't find the entry file...");
		}

		if (args[3].equals("-fh")) {
			Utility.content.flip(true);
		} else if (args[3].equals("-fy")) {
			Utility.content.flip(false);
		} else if (args[3].equals("-gs")) {
			Utility.content.toGrey();
		} else if (args[3].equals("-cr")) {
			try{
			Utility.content.crop(Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]),
					Integer.parseInt(args[7]));
			} catch (ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Don't forget to input the crop 4 crop arguments");
			}
		}

		try {
			if (args[2].equals("PNM")) {
				Utility.writePNM(Utility.content, args[1]);

			} else if (args[2].equals("PGM")) {
				Utility.writePGM(Utility.content, args[1]);
			}
		} catch (IOException e) {
			System.out.println("Couldn't find the output Path");
		}

	}

}
