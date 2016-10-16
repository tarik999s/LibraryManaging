/**
 * 
 */
package com.botscrew.main;

import java.io.Serializable;

/**
 * @author Taras Savitskyy
 *
 * Entity class Book
 */
public class Book implements Serializable {

	private static final long serialVersionUID = -6538852396211775098L;

	private Integer Id;
	private String Name;
	private String AuthorName;
	
	
	/**
	 * Default constructor
	 */
	public Book() {
		super();
	}


	/**
	 * Constructor with parameters
	 * @param id
	 * @param name
	 * @param authorName
	 */
	public Book(Integer id, String name, String authorName) {
		super();
		Id = id;
		Name = name;
		AuthorName = authorName;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return Id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		Id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}


	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return AuthorName;
	}


	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}
	
	/**
	 * Check book's Name and AuthorName values
	 * 
	 * @return true if book's name and autherName 
	 *  is not null
	 */
	public Boolean checkBookValue() {
		if (this.Name == null || this.AuthorName == null)
			return false;
		return true;
	}
 
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		 return this.getAuthorName() + " - \"" + this.getName() + "\"" ;
	}
	
	
}
