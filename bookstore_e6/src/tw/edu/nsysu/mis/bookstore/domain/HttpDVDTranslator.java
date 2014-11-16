package tw.edu.nsysu.mis.bookstore.domain;

import javax.servlet.http.HttpServletRequest;

public class HttpDVDTranslator extends HttpProductTranslator {
	private digitalVideoDisk product = new digitalVideoDisk();
	
	
	public HttpDVDTranslator() {
		super();
		// TODO Auto-generated constructor stub
	}


	public HttpDVDTranslator(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Product getProduct() {
		
		return this.product;
	}


	@Override
	public boolean setValues() {
		boolean isSuccess = true;
		if (super.setValues()) {
			this.product.setDirector(super.getRequest().getParameter("p_director"));
			this.product.setActor1(super.getRequest().getParameter("p_actor1"));
			this.product.setActor2(super.getRequest().getParameter("p_actor2"));
			this.product.setRating(super.getRequest().getParameter("p_rating"));
			this.product.setPublisher(super.getRequest().getParameter("p_publisher"));
		} else {
			isSuccess = false;
		}
		return isSuccess;
	}
	
	

}
