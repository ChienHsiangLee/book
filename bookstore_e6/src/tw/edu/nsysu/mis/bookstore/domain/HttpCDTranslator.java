package tw.edu.nsysu.mis.bookstore.domain;

import javax.servlet.http.HttpServletRequest;

public class HttpCDTranslator extends HttpProductTranslator {
	private CompactDisc product = new CompactDisc();

	public HttpCDTranslator() {
        super();
	}

	public HttpCDTranslator(HttpServletRequest request) {
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
			this.product.setComposer(getParameter("p_composer"));
			this.product.setPerformer(getParameter("p_performer"));
			this.product.setNumber(Double.valueOf(getParameter("p_number", true)));
			this.product.setPublisher(getParameter("p_publisher"));
		} else {
			isSuccess = false;
		}
		return isSuccess;
	}

}
