package tw.edu.nsysu.mis.bookstore.controller;

import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping("/ModifyPage")
public class ModifyPageController {
	
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


		model.addAttribute("p_pNo", "A0001");
		model.addAttribute("message", "has been modified successfully");

		return "endModification";
	}

}
