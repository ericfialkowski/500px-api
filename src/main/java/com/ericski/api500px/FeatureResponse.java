package com.ericski.api500px;

import java.util.ArrayList;

public class FeatureResponse
{

	String					feature;
	ArrayList<Photo>		photos;
	int						total_pages;
	int						current_page;
	int						total_items;
	Filters					filters;

	Api500pxStreamBuilder	builder;

	public String getFeature()
	{
		return feature;
	}

	protected void setFeature(String feature)
	{
		this.feature = feature;
	}

	public int getTotal_pages()
	{
		return total_pages;
	}

	protected void setTotal_pages(int total_pages)
	{
		this.total_pages = total_pages;
	}

	public ArrayList<Photo> getPhotos()
	{
		return photos;
	}

	protected void setPhotos(ArrayList<Photo> photos)
	{
		this.photos = photos;
	}

	public int getCurrent_page()
	{
		return current_page;
	}

	protected void setCurrent_page(int current_page)
	{
		this.current_page = current_page;
	}

	public int getTotal_items()
	{
		return total_items;
	}

	protected void setTotal_items(int total_items)
	{
		this.total_items = total_items;
	}

	public Filters getFilters()
	{
		return filters;
	}

	protected void setFilters(Filters filters)
	{
		this.filters = filters;
	}

	public void setBuilder(Api500pxStreamBuilder builder)
	{
		this.builder = builder;
	}

	public Api500pxStreamBuilder getBuilder()
	{
		return builder;
	}

	public FeatureResponse getNextPage()
	{
		if (current_page == total_pages)
		{
			return null;
		}
		else
		{
			return builder.page(current_page + 1).getResponse();
		}
	}

	public FeatureResponse getAllPhotos()
	{
		int page = current_page;
		FeatureResponse tempFr = null;
		while (page++ < total_pages)
		{
			tempFr = builder.page(page).getResponse();
			if (tempFr != null)
			{
				for (Photo p : tempFr.getPhotos())
				{
					photos.add(p);
				}
			}
		}
		total_pages = 1; // can't really iterate anymore so set the total_pages to be 1 
		return this;
	}

}
