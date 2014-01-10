package com.fialkowski.api500px;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;

public class Photo
{
	protected int id;
	protected String name;
	protected String description;
	protected int category;
	protected String image_url;
	protected int votes_count;
	protected int favorites_count;
	protected int comments_count;
	protected double rating;
	protected Date created_at;
	protected int status; // Status of the photo in the system,
				// integer. An active photo always has
				// the status of 1.
	protected User user;
	protected String camera;
	protected String lens;
	protected String aperture;
	protected String focal_length;
	protected String iso;
	protected String shutter_speed;
	protected Date taken_at;
	protected String location;
	protected double latitude;
	protected double longitude;
	protected BufferedImage cachedImage = null;

	protected boolean nsfw;
	protected int times_viewed;
	protected boolean voted;
	protected boolean favorited;
	
	static protected final String defaultImageUrl;
	static
	{
		defaultImageUrl = Photo.class.getResource("/images/warning_3.png").toString();
	}
	
	public Photo()
	{
		image_url = defaultImageUrl;
	}
	
	public int getId()
	{
		return id;
	}

	protected void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	protected void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	protected void setDescription(String description)
	{
		this.description = description;
	}

	public Category getCategory()
	{
		return Category.parseInt(category);
	}

	protected void setCategory(int category)
	{
		this.category = category;
	}

	public String getImageUrl()
	{
		return image_url;
	}

	protected void setImage_url(String image_url)
	{
		this.image_url = image_url;
	}

	public int getVotesCount()
	{
		return votes_count;
	}

	protected void setVotes_count(int votes_count)
	{
		this.votes_count = votes_count;
	}

	public int getFavoritesCount()
	{
		return favorites_count;
	}

	protected void setFavorites_count(int favorites_count)
	{
		this.favorites_count = favorites_count;
	}

	public int getCommentsCount()
	{
		return comments_count;
	}

	protected void setComments_count(int comments_count)
	{
		this.comments_count = comments_count;
	}

	public double getRating()
	{
		return rating;
	}

	protected void setRating(double rating)
	{
		this.rating = rating;
	}

	public Date getCreatedDate()
	{
		return created_at;
	}

	protected void setCreated_at(Date created_at)
	{
		this.created_at = created_at;
	}

	public int getStatus()
	{
		return status;
	}

	protected void setStatus(int status)
	{
		this.status = status;
	}

	public User getUser()
	{
		return user;
	}

	protected void setUser(User user)
	{
		this.user = user;
	}

	public String getCamera()
	{
		return camera;
	}

	protected void setCamera(String camera)
	{
		this.camera = camera;
	}

	public String getLens()
	{
		return lens;
	}

	protected void setLens(String lens)
	{
		this.lens = lens;
	}

	public String getAperture()
	{
		return aperture;
	}

	protected void setAperture(String aperture)
	{
		this.aperture = aperture;
	}

	public String getFocalLength()
	{
		return focal_length;
	}

	protected void setFocal_length(String focal_length)
	{
		this.focal_length = focal_length;
	}

	public String getIso()
	{
		return iso;
	}

	protected void setIso(String iso)
	{
		this.iso = iso;
	}

	public String getShutterSpeed()
	{
		return shutter_speed;
	}

	protected void setShutter_speed(String shutter_speed)
	{
		this.shutter_speed = shutter_speed;
	}

	public Date getTakenDate()
	{
		return taken_at;
	}

	protected void setTaken_at(Date taken_at)
	{
		this.taken_at = taken_at;
	}

	public String getLocation()
	{
		return location;
	}

	protected void setLocation(String location)
	{
		this.location = location;
	}

	public double getLatitude()
	{
		return latitude;
	}

	protected void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	protected void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public BufferedImage getImage()
	{
		if (cachedImage == null)
		{
			BufferedImage rtn = null;

			try
			{
				rtn = ImageIO.read(new URL(image_url));
			}
			catch (Exception e)
			{
				// TODO: probably should log it or something
				//System.out.println(e.getMessage());
				rtn = null;
			}
			cachedImage = rtn;
		}
		return cachedImage;
	}

	public int getTimesViewed()
	{
		return times_viewed;
	}
	
	public void setTimesViewed(int views)
	{
		times_viewed = views;
	}

	public boolean getNsfw()
	{
		return nsfw;
	}

	public void setNsfw(boolean nsfw)
	{
		this.nsfw = nsfw;
	}

	public boolean getVoted()
	{
		return voted;
	}

	public void setVoted(boolean voted)
	{
		this.voted = voted;
	}

	public boolean getFavorited()
	{
		return favorited;
	}

	public void setFavorited(boolean favorited)
	{
		this.favorited = favorited;
	}	

	@Override
	public String toString()
	{
		return String
				.format("Photo [id=%s, name=%s, description=%s, category=%s, image_url=%s, votes_count=%s, favorites_count=%s, comments_count=%s, rating=%s, created_at=%s, status=%s, user=%s, camera=%s, lens=%s, aperture=%s, focal_length=%s, iso=%s, shutter_speed=%s, taken_at=%s, location=%s, latitude=%s, longitude=%s, cachedImage=%s, nsfw=%s]",
						id, name, description, category, image_url, votes_count, favorites_count, comments_count, rating,
						created_at, status, user, camera, lens, aperture, focal_length, iso, shutter_speed, taken_at, location,
						latitude, longitude, cachedImage,nsfw);
	}		
}