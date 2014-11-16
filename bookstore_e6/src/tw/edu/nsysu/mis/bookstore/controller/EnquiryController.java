package tw.edu.nsysu.mis.bookstore.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import tw.edu.nsysu.mis.bookstore.domain.Product;
import tw.edu.nsysu.mis.bookstore.domain.ProductDao;
import tw.edu.nsysu.mis.bookstore.domain.ProductDaoFactory;

@Controller
public class EnquiryController {
	
	@RequestMapping("enquiry")
	protected String doDispatch(HttpServletRequest request,
			@RequestParam(value="p_pNo", defaultValue="") String p_pNo, 
			@RequestParam(value="p_category", defaultValue="") String p_category,
			Model model) {
		
        WebApplicationContext ctx =RequestContextUtils.getWebApplicationContext(request);
		ProductDaoFactory productDaoFactory = (ProductDaoFactory) ctx.getBean("productDaoFactory");
		productDaoFactory.setRequest(request);
        ProductDao productDao = productDaoFactory.createProductDao(p_category);
        
        /* fetch record from table */
        Product prod = productDao.findByProductNo(p_pNo);
        
        model.addAttribute("product", prod);
		model.addAttribute("p_act", "I");
		model.addAttribute("p_targetView", prod.getTargetView());
        
        return "enquiryPage";
	}
}
