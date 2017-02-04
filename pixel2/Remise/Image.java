

public class Image {
	
	String metadata;
	int maxRange;
	Pixel[][] data;
	
	
	//Constructor;
	Image(String _metadata,int _maxRange,Pixel[][] _data)
	{
		if(_maxRange < 0)
		{
			throw new IllegalArgumentException();
		}
		this.metadata = _metadata;
		this.maxRange = _maxRange;	
		data = _data;
	}
	
	//Metadata Getter
	public String getMetadata() {
		return metadata;
	}
	//Max Range Getter
	public int getMaxRange() {
		return maxRange;
	}

	//Width Getter
	public int getWidth()
	{
		return data[1].length;
	}
	//Height Getter
	public int getHeight()
	{
		return data.length;
	}
	
	
	//Pixel Getter
	public Pixel getPixel(int i , int j ) {
		return data[i][j];
	}

	
	//Flip method
	public void flip(boolean horizontal)
	{
		Pixel[][] temp = new Pixel[data[0].length][data[1].length];
		
		
		if(horizontal)
		{
			for(int i = 0; i < this.getHeight();i++)
			{
				int count = 0;
				for(int j = this.getWidth()-1;j >= 0;j--)
				{	
					temp[i][count] = data[i][j];
					count++;
				}
			}
		} else if (!horizontal)
		{
			int count = this.getHeight()-1;
			
			for(int i = 0; i < this.getHeight();i++)
			{
				for(int j = 0;j < this.getWidth();j++)
				{
					temp[count][j] = data[i][j];
				}
				count--;
			}
		}
		this.data = temp;
	}
	
	//To Grey Method
	//Call togrey on every pixels;
	public void toGrey()
	{
		for(int i =0; i < this.getHeight();i++)
		{
			for(int j = 0; j < this.getWidth();j++)
			{
				data[i][j].toGrey();
			}
		}
	}
	
	//Crop method.
	//Copy the content of the sub array and put it in a new array
	public void crop(int startX,int startY,int endX,int endY)
	{
		
		if(startX < 0 || startX > data[1].length || startY < 0 || startY > data[0].length)
		{throw new IllegalArgumentException();}
		
		Pixel[][] temp = new Pixel[endY-startY][endX-startX];
		int countY = 0;
		for(int i  = startY; i < endY;i++ )
		{
			int countX = 0;
			for(int j = startX;j < endX;j++)
			{
				temp[countY][countX] = data[i][j];
				countX++;
			}
			countY++;
		}
		this.data = temp;
	}
	
}
