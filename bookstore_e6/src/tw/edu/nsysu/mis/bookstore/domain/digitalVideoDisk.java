package tw.edu.nsysu.mis.bookstore.domain;

public class digitalVideoDisk extends Product {
	private String director="";
	private String actor1="";
	private String actor2="";
	private String rating="";
	private String publisher="";

	
	public digitalVideoDisk() {
		super();
		this.setCategory("DVD");
		this.setTargetView("DVDPage.jsp");
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor1() {
		return actor1;
	}
	public void setActor1(String actor1) {
		this.actor1 = actor1;
	}
	public String getActor2() {
		return actor2;
	}
	public void setActor2(String actor2) {
		this.actor2 = actor2;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
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
			if (propName.equalsIgnoreCase("director")) {
				return this.getDirector();
			} else if (propName.equalsIgnoreCase("actor1")) {
				return this.getActor1();
			} else if (propName.equalsIgnoreCase("actor2")) {
				return this.getActor2();				
			} else if (propName.equalsIgnoreCase("rating")) {
				return this.getRating();	
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
