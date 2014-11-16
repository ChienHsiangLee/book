package tw.edu.nsysu.mis.bookstore.domain;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class HttpTranslatorFactory {
	private HttpServletRequest request = null;

	public HttpTranslatorFactory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpTranslatorFactory(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public HttpProductTranslator createHttpTranslator(String category) {
		if ("book".equalsIgnoreCase(category)) {
			return new HttpBookTranslator(request);
		} else if ("CD".equalsIgnoreCase(category)) {
			return new HttpCDTranslator(request);
		} else if ("DVD".equalsIgnoreCase(category)) {
			return new HttpDVDTranslator(request);
		}
		throw new IllegalArgumentException("Unknown product");
	}

}
