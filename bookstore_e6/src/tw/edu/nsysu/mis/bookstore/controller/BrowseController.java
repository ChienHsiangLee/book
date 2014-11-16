package tw.edu.nsysu.mis.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import tw.edu.nsysu.mis.bookstore.domain.JdbcProductDao;
import tw.edu.nsysu.mis.bookstore.domain.Product;


@Controller
public class BrowseController {

	@RequestMapping("browse")
	protected String doDispatch(HttpSession session, HttpServletRequest request,
			@RequestParam(value="p_pNo", defaultValue="") String p_pNo, 
			@RequestParam(value="p_category", defaultValue="") String p_category,
			Model model) {
		
		String q_pName = (String) session.getAttribute("q_pName");
		WebApplicationContext ctx =RequestContextUtils.getWebApplicationContext(request);
		JdbcProductDao prodDao =  (JdbcProductDao) ctx.getBean("jdbcProductDao");
		List<Product> products = prodDao.getListByProductName(q_pName);
		model.addAttribute("products", products);
        
        return "list";
	}
}
