

public class Pixel {
	
	int red,green,blue;
	
	
	//Constructor
	Pixel(int r, int g, int b)
	{
		if(r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0)
		{throw new IllegalArgumentException();}
		this.red = r;
		this.green = g;
		this.blue = b;
	}
	
	//Second Constuctor;
	Pixel(int intensity)
	{
		this(intensity,intensity,intensity);
	}
	
	//Red getter
	public int getRed()
	{
		return red;
	}
	
	//Green Getter
	public int getGreen()
	{
		return green;
	}
	
	//Blue Getter
	public int getBlue()
	{
		return blue;
	}
	
	// Grey Getter
	public int getGrey()
	{
		return ((red + green + blue)/3);
	}
	
	//Grey Convert;
	public void toGrey()
	{
		int grey = getGrey();
		this.red = grey;
		this.green = grey;
		this.blue = grey;
	}
	
	
}
