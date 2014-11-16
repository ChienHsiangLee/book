package tw.edu.nsysu.mis.bookstore.domain;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

@Component
public class ProductDaoFactory {
	private HttpServletRequest request = null;

	public ProductDaoFactory() {
		super();
	}

	public ProductDaoFactory(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public ProductDao createProductDao(String category) {

		if (request == null) {
			throw new IllegalArgumentException("lack of request object");
		} else {
			WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(this.request);

			if ("book".equalsIgnoreCase(category)) {
				return (ProductDao) ctx.getBean("jdbcBookDao");
			} else if ("CD".equalsIgnoreCase(category)) {
				return (ProductDao) ctx.getBean("jdbcCDDao");				
			} else if ("DVD".equalsIgnoreCase(category)) {
				return (ProductDao) ctx.getBean("jdbcProductDao");				
			}
			throw new IllegalArgumentException("Unknown product");
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
