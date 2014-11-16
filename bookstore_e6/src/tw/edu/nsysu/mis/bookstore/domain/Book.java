package tw.edu.nsysu.mis.bookstore.domain;

public class Book extends Product {
	private String author="";
	private String ISBN="";
	private double edition=1;
	private String publisher="";
	
	public Book() {
		super();
		this.setCategory("BOOK");
		this.setTargetView("bookPage.jsp");
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public double getEdition() {
		return edition;
	}
	public void setEdition(double edition) {
		this.edition = edition;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Override
	public String getValue(String propName) {
		
		String retVal = super.getValue(propName);
		if (retVal.length()==0) {
			if (propName.equalsIgnoreCase("author")) {
				return this.getAuthor();
			} else if (propName.equalsIgnoreCase("ISBN")) {
				return this.getISBN();
			} else if (propName.equalsIgnoreCase("edition")) {
				return Double.toString(this.getEdition());
			} else if (propName.equalsIgnoreCase("publisher")) {
				return this.getPublisher();
			} else {
				return "";
			}
		} else {
			return retVal;
		}
	}
	


}
