
public class Book {
	String name;
	String author;
	int releaseYear;
	String ISBN;
	
	/**
	 * @param name
	 * @param author
	 * @param releaseYear
	 * @param iSBN
	 */
	public Book(String name, String author, int releaseYear, String iSBN) {
		this.name = name;
		this.author = author;
		setReleaseYear(releaseYear);
		ISBN = iSBN;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", releaseYear=" + releaseYear + ", ISBN=" + ISBN + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the releaseYear
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * @param releaseYear the releaseYear to set
	 */
	public void setReleaseYear(int releaseYear) {
		if (releaseYear > 0)
			this.releaseYear = releaseYear;
		else
			System.err.println("Invalid Year: Year must be positive. ");
	}

	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

}
