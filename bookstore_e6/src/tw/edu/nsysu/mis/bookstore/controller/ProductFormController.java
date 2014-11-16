package tw.edu.nsysu.mis.bookstore.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import tw.edu.nsysu.mis.bookstore.domain.Product;
import tw.edu.nsysu.mis.bookstore.domain.ProductFactory;
import tw.edu.nsysu.mis.bookstore.domain.ProductValidator;



@Controller
@RequestMapping("/productForm")
public class ProductFormController {
	private ProductValidator productValidator;
	
	@Autowired
	public ProductFormController(ProductValidator productValidator) {
		this.productValidator = productValidator;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void setupForm() {
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("product") Product product, BindingResult result,
			SessionStatus status, Model model,
			HttpServletRequest request,
			@RequestParam(value = "p_category", defaultValue = "BOOK") String p_category,
			@RequestParam(value = "prod_confirmed", defaultValue = "notconfirmed") String prod_confirmed,
			@RequestParam(value = "prod_cancelled", defaultValue = "notcancelled") String prod_cancelled) {

		String nextView = "";

		if ("notconfirmed".equals(prod_confirmed)) {
			nextView = "endInsertion";
			model.addAttribute("messageCode", "message.insertionCancelled");
		} else {
			productValidator.validate(product, result);
			if (result.hasErrors()) {
				model.addAttribute("product", product);
				nextView = "productForm";
			} else {
				WebApplicationContext ctx =RequestContextUtils.getWebApplicationContext(request);		
				ProductFactory productFactory = (ProductFactory) ctx.getBean("productFactory");
				Product newProd = productFactory.createProduct(p_category);
				newProd.setpNo(product.getpNo());
				newProd.setpName(product.getpName());
				newProd.setUnitPrice(product.getUnitPrice());
				System.out.println("in handler methold");
				nextView = doDispatch(request, prod_confirmed, newProd, model,"POST");
			}
		}

		return nextView;
	}
	
	protected String doDispatch(HttpServletRequest request,
			String prod_confirmed, Product newProd, Model model,
			String reqMethod) {

		String nextView = "insertion";


		if ("notconfirmed".equals(prod_confirmed)) {
			nextView = "endInsertion";
			model.addAttribute("messageCode", "message.insertionCancelled");
		} 
		// forward parameters from last page
		for (Enumeration<String> e = request.getParameterNames(); e
				.hasMoreElements();) {
			String paramName = (String) e.nextElement();
			model.addAttribute(paramName, request.getParameter(paramName));
		}
		model.addAttribute("product", newProd);
		model.addAttribute("requestMethod", reqMethod);
		model.addAttribute("p_act", "I");
		model.addAttribute("p_targetView", newProd.getTargetView());
		System.out.println("in doDispatch");
		return nextView;
	}

	


}
