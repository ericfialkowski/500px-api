package com.ericski.api500px;

public enum Category 
{
	None(0),
	Abstract(10), 
	Animals(11), 
	BlackAndWhite(5,"Black And White"), 
	Celebrities(1), 
	CityAndArchitecture(9,"City and Architecture"), 
	Commercial(15), 
	Concert(16), 
	Family(20), 
	Fashion(14), 
	Film(2), 
	FineArt(24,"Fine Art"), 
	Food(23), 
	Journalism(3), 
	Landscapes(8), 
	Macro(12), 
	Nature(18), 
	Nude(4), 
	People(7), 
	PerformingArts(19,"Performing Arts"), 
	Sport(17), 
	StillLife(6, "Still Life"), 
	Street(21), 
	Travel(13), 
	Underwater(22);

	private int val;
	private String catName;

	private Category(int val, String catName) 
	{
		this.val = val;
		this.catName = catName;
	}

	private Category(int val) 
	{
		this.val = val;
		this.catName = name();
	}
	
	public int getCategoryValue() 
	{
		return val;
	}
	
	public String getCategoryName()
	{		
		return catName;
	}
	
	public static Category parseInt(int categoryValue)
	{
		for (Category cat :Category.values())
		{
			if ( cat.getCategoryValue() == categoryValue)
			{
				return cat;
			}
		}
		return Category.None;
	}
}
