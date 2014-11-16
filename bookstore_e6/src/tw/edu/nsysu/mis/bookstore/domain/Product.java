package tw.edu.nsysu.mis.bookstore.domain;

public class Product {
	private String pNo = "";
	private String pName = "";
	private double unitPrice = 0;
	private String category = "Book";
	private String targetView;

	public String getpNo() {
		return pNo;
	}

	public void setpNo(String pNo) {
		this.pNo = pNo;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTargetView() {
		return targetView;
	}

	public void setTargetView(String targetView) {
		this.targetView = targetView;
	}

	public String getValue(String propName) {
		if (propName.equalsIgnoreCase("pNo")) {
			return this.getpNo();
		} else if (propName.equalsIgnoreCase("pName")) {
			return this.getpName();
		} else if (propName.equalsIgnoreCase("unitPrice")) {
			return Double.toString(this.getUnitPrice());
		} else if (propName.equalsIgnoreCase("category")) {
			return this.getCategory();
		} else {
			return "";
		}
	}

}
