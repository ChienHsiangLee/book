package tw.edu.nsysu.mis.bookstore.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import tw.edu.nsysu.mis.bookstore.domain.JdbcProductDao;
import tw.edu.nsysu.mis.bookstore.domain.Product;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	@RequestMapping(method=RequestMethod.GET)
	public void setupForm() {
	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submitForm(HttpServletRequest request, HttpSession session, 
			@RequestParam(value="q_pName", defaultValue="") String q_pName, 
			@RequestParam(value="insertion", defaultValue="notinsertion") String insertion, 
			@RequestParam(value="browsing", defaultValue="notbrowsing") String browsing,
			Model model) {
		
		String nextView = "list";
		// 100
		 
		model.addAttribute("q_pName", q_pName);
		if ("notbrowsing".equals(browsing)) {
			// initiate the attributes of product
			Product prod = new Product();
			prod.setpName(q_pName);
			model.addAttribute("product", prod);
			nextView = "productForm";
		} else {
			session.setAttribute("q_pName", q_pName);
			WebApplicationContext ctx =RequestContextUtils.getWebApplicationContext(request);
			//JdbcProductDao prodDao = (JdbcProductDao) ctx.getBean("jdbcProductDao");
			//List<Product> products = prodDao.getListByProductName(q_pName);
			String sql = "SELECT * FROM Product WHERE pName LIKE '%"+q_pName+"%'";
			DataSource dataSource = (DataSource) ctx.getBean("dataSource");
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			List<Product> products = new ArrayList<Product>();
			List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql);
			for (Map<String, Object> row: rows) {
				Product product = new Product();
				product.setpNo((String) row.get("pNo"));
				product.setpName((String) row.get("pName"));
				product.setUnitPrice((Double) row.get("unitPrice"));
				product.setCategory((String) row.get("category"));
				products.add(product);			
			}
			model.addAttribute("products", products);
		}
	
		return nextView;
	}
}
