package be.beeckman.steven.linkshortener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LinkRedirect {
	
	@Id
	@Column(name = "SHORT_URL")
	private String shortUrl;
	@Column(name = "LONG_URL")
	private String longUrl;
	@Column(name = "AUTHOR")
	private String author;
	@Column(name = "NUMBER_OF_VISITS")
	private int numberOfVisits;
	@Column(name = "ACTIVE")
	private boolean active;
	
	public LinkRedirect() {
		
	}
	
	public LinkRedirect(String shortUrl, String longUrl, String author, int numberOfVisits, boolean active) {
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.author = author;
		this.numberOfVisits = numberOfVisits;
		this.active = active;
	}
	
	public LinkRedirect(String shortUrl, String longUrl, String author) {
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.author = author;
		this.numberOfVisits = 0;
		this.active = true;
	}
	
	public LinkRedirect(String shortUrl, String longUrl, String author, boolean active) {
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.author = author;
		this.active = active;
		this.numberOfVisits = 0;
	}

	/**
	 * @return the shortUrl
	 */
	public String getShortUrl() {
		return shortUrl;
	}

	/**
	 * @param shortUrl the shortUrl to set
	 */
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	/**
	 * @return the longUrl
	 */
	public String getLongUrl() {
		return longUrl;
	}

	/**
	 * @param longUrl the longUrl to set
	 */
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the numberOfVisits
	 */
	public int getNumberOfVisits() {
		return numberOfVisits;
	}

	/**
	 * @param numberOfVisits the numberOfVisits to set
	 */
	public void setNumberOfVisits(int numberOfVisits) {
		this.numberOfVisits = numberOfVisits;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
