package tw.edu.nsysu.mis.bookstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import tw.edu.nsysu.mis.bookstore.domain.HttpProductTranslator;
import tw.edu.nsysu.mis.bookstore.domain.HttpTranslatorFactory;
import tw.edu.nsysu.mis.bookstore.domain.ProductDao;
import tw.edu.nsysu.mis.bookstore.domain.ProductDaoFactory;

@Controller
@RequestMapping("/backendJob/*")
public class doBackendJob {

	@RequestMapping("insertion")
	public String doInsertion(
			HttpServletRequest request,
			@RequestParam(value="category") String p_category,
			@RequestParam(value = "confirmed", defaultValue = "notconfirmed") String confirmed,
			@RequestParam(value = "cancelled", defaultValue = "notcancelled") String cancelled,
			Model model) {
		String nextView = "endInsertion";
		String messageCode = "message.insertionOK";

		System.out.println("in doBackendJob");
		WebApplicationContext ctx = RequestContextUtils
				.getWebApplicationContext(request);
		ProductDaoFactory productDaoFactory = (ProductDaoFactory) ctx
				.getBean("productDaoFactory");
		productDaoFactory.setRequest(request);
		ProductDao productDao = productDaoFactory.createProductDao(p_category);
		HttpTranslatorFactory httpTranslatorFactory = (HttpTranslatorFactory) ctx
				.getBean("httpTranslatorFactory");
		httpTranslatorFactory.setRequest(request);
		HttpProductTranslator httpProductTranslator = httpTranslatorFactory
				.createHttpTranslator(p_category);

		if ("notcancelled".equals(cancelled)) {
			if (httpProductTranslator.setValues()) {
				productDao.insert(httpProductTranslator.getProduct());
			} else {
				messageCode = "message.insertionFail";
			}
		} else {
			messageCode = "message.insertionAbort";
		}
		model.addAttribute("messageCode", messageCode);

		return nextView;
	}

}
