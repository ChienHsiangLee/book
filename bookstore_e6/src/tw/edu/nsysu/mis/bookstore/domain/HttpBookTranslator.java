package tw.edu.nsysu.mis.bookstore.domain;

import javax.servlet.http.HttpServletRequest;


public class HttpBookTranslator extends HttpProductTranslator {
	private Book product = new Book();

	public HttpBookTranslator() {
        super();
	}
	

	public HttpBookTranslator(HttpServletRequest request) {
		super(request);
	}


	@Override
	public Product getProduct() {

		return this.product;
	}

	@Override
	public boolean setValues() {
		boolean isSuccess = true;
		if (super.setValues()) {
			this.product.setAuthor(getParameter("author"));
			this.product.setISBN(getParameter("ISBN"));
			this.product.setEdition(Double.valueOf(getParameter("edition", true)));
			this.product.setPublisher(getParameter("publisher"));
		} else {
			isSuccess = false;
		}
		return isSuccess;
	}

}
