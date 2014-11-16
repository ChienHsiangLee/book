package tw.edu.nsysu.mis.bookstore.domain;


public class CompactDisc extends Product {

	private String composer="";
	private String performer="";
	private double number=0;
	private String publisher="";

	public CompactDisc() {
		super();
		this.setCategory("CD");
		this.setTargetView("CDPage.jsp");		
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
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
			if (propName.equalsIgnoreCase("composer")) {
				return this.getComposer();
			} else if (propName.equalsIgnoreCase("performer")) {
				return this.getPerformer();
			} else if (propName.equalsIgnoreCase("number")) {
				return Double.toString(this.getNumber());
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
