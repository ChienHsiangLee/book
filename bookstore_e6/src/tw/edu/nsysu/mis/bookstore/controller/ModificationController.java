package tw.edu.nsysu.mis.bookstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.edu.nsysu.mis.bookstore.domain.Book;

@Controller
public class ModificationController {

	@RequestMapping("/modification")
	protected String doDispatch(HttpServletRequest request,
			@RequestParam(value="p_pNo", defaultValue="") String p_pNo, 
			@RequestParam(value="p_category", defaultValue="") String p_category,
			Model model) {
		
		Book book = new Book();
		book.setpNo("A0001");
		book.setpName("Name of A0001");
		book.setCategory("Book");
		book.setUnitPrice(100);
		book.setAuthor("01 Author");
		book.setISBN("1234565");
		book.setEdition(1);
		book.setPublisher("01 publisher");
		model.addAttribute("product", book);
		model.addAttribute("message", "has been modified successfully");
		return "ModifyPage";
	}
}
