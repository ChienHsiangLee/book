package tw.edu.nsysu.mis.bookstore.domain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

public class BookStore {
	private HttpServletRequest request;

	public BookStore(HttpServletRequest request) {
		super();
		this.request = request;
	}
	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)	
	public boolean insert(List<Product> products) {
		boolean isSuccess = true;
		
		WebApplicationContext ctx =RequestContextUtils.getWebApplicationContext(request);
		ProductDaoFactory daoFactory = (ProductDaoFactory) ctx.getBean("productDaoFactory");
		daoFactory.setRequest(request);
		DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) ctx.getBean("transactionManager");
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);		
		try {
			for (Product prod : products) {
				ProductDao productDao = (ProductDao) daoFactory
						.createProductDao(prod.getCategory());
				System.out.println(prod.getpNo());
				productDao.insert(prod);
			}
			transactionManager.commit(status);
		} catch (DataAccessException e) {
			transactionManager.rollback(status);
			isSuccess=false;
		}
		return isSuccess;
	}
	

}
