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

import tw.edu.nsysu.mis.bookstore.domain.Book;
import tw.edu.nsysu.mis.bookstore.domain.CompactDisc;
import tw.edu.nsysu.mis.bookstore.domain.Product;
import tw.edu.nsysu.mis.bookstore.domain.ProductValidator;
import tw.edu.nsysu.mis.bookstore.domain.digitalVideoDisk;

@Controller
@RequestMapping("insertDetail")
public class InsertionController {
	private ProductValidator productValidator;

	@Autowired
	public InsertionController(ProductValidator productValidator) {
		this.productValidator = productValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			HttpServletRequest request,
			@RequestParam("p_category") String p_category,
			@RequestParam(value = "prod_confirmed", defaultValue = "notconfirmed") String prod_confirmed,
			@RequestParam(value = "prod_cancelled", defaultValue = "notcancelled") String prod_cancelled,
			Model model) {

		String nextView = doDispatch(request, prod_cancelled, p_category,
				model, "GET");

		return nextView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(
			HttpServletRequest request,
			@RequestParam("p_category") String p_category,
			@RequestParam(value = "prod_confirmed", defaultValue = "notconfirmed") String prod_confirmed,
			@RequestParam(value = "prod_cancelled", defaultValue = "notcancelled") String prod_cancelled,
			@ModelAttribute("product") Product product, BindingResult result,
			SessionStatus status, Model model) {

		String nextView = "";
		productValidator.validate(product, result);
		if (result.hasErrors()) {
			model.addAttribute("product", product);
			nextView = "productForm";
		} else {
			nextView = doDispatch(request, prod_cancelled, p_category, model,"POST");
		}

		return nextView;
	}

	protected String doDispatch(HttpServletRequest request,
			String prod_cancelled, String p_category, Model model,
			String reqMethod) {
		String p_targetView = "bookPage.jsp";
		String nextView = "insertion";
		Product prod = null;

		if ("notcancelled".equals(prod_cancelled)) {
			if (p_category.equalsIgnoreCase("CD")) {
				p_targetView = "CDPage.jsp";
				prod = new CompactDisc();
			} else if (p_category.equalsIgnoreCase("DVD")) {
				p_targetView = "DVDPage.jsp";
				prod = new digitalVideoDisk();
			} else {
				// initiate the attributes of book
				prod = new Book();
			}
		} else {
			nextView = "endInsertion";
			model.addAttribute("message", "新增資料取消");
		}

		// forward parameters from last page
		for (Enumeration<String> e = request.getParameterNames(); e
				.hasMoreElements();) {
			String paramName = (String) e.nextElement();
			model.addAttribute(paramName, request.getParameter(paramName));
		}
		model.addAttribute("product", prod);
		model.addAttribute("requestMethod", reqMethod);
		model.addAttribute("p_act", "I");
		model.addAttribute("p_targetView", p_targetView);
		return nextView;
	}

}
