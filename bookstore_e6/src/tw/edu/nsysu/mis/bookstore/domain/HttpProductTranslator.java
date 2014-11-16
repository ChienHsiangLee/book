package tw.edu.nsysu.mis.bookstore.domain;

import javax.servlet.http.HttpServletRequest;

public abstract class HttpProductTranslator {
	private HttpServletRequest request;

	public HttpProductTranslator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HttpProductTranslator(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public abstract Product getProduct();

	public String getParameter(String propName, boolean isNumeric) {
		String value = request.getParameter(propName);
		if (value == null) {
			if (isNumeric) {
				value = "0";
			} else {
				value = "";
			}
		}
		return value;
	}

	public String getParameter(String propName) {
		return getParameter(propName, false);
	}
	public boolean setValues() {
		boolean isSuccess = true;

		if (getProduct() != null && this.request != null) {
			getProduct().setpNo(getParameter("pNo"));
			getProduct().setpName(getParameter("pName"));
			getProduct().setUnitPrice(
					Double.valueOf(getParameter("unitPrice", true)));
			getProduct().setCategory(getParameter("category"));
		} else {
			isSuccess = false;
		}
		return isSuccess;

	}

}
