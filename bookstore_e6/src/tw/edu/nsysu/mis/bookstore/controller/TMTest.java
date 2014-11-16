package tw.edu.nsysu.mis.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import tw.edu.nsysu.mis.bookstore.domain.Book;
import tw.edu.nsysu.mis.bookstore.domain.BookStore;
import tw.edu.nsysu.mis.bookstore.domain.CompactDisc;
import tw.edu.nsysu.mis.bookstore.domain.Product;


@Controller
@RequestMapping("/tmtest")
public class TMTest {
	@RequestMapping(method=RequestMethod.GET)
	public void setupForm() {
	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submitForm(HttpServletRequest request, HttpSession session, 
			@RequestParam(value="p_pNo1", defaultValue="") String p_pNo1, 
			@RequestParam(value="p_pNo2", defaultValue="") String p_pNo2, 
			Model model) {
		

			Book b1 = new Book();
			b1.setpNo(p_pNo1);
			b1.setpName(p_pNo1);
			b1.setUnitPrice(500);
			b1.setAuthor(p_pNo1);
			b1.setISBN(p_pNo1);
			b1.setEdition(1);
			b1.setPublisher(p_pNo1);
			CompactDisc cd2 = new CompactDisc();
			cd2.setpNo(p_pNo2);
			cd2.setpName(p_pNo2);
			cd2.setUnitPrice(600);
			cd2.setComposer(p_pNo2);
			cd2.setPerformer(p_pNo2);
			cd2.setNumber(2);
			cd2.setPublisher(p_pNo2);
			CompactDisc cd1 = new CompactDisc();
			cd1.setpNo(p_pNo1);
			cd1.setpName(p_pNo1);
			cd1.setUnitPrice(600);
			cd1.setComposer(p_pNo1);
			cd1.setPerformer(p_pNo1);
			cd1.setNumber(2);
			cd1.setPublisher(p_pNo1);
			BookStore bookStore = new BookStore(request);
			List<Product> products = new ArrayList<Product>();
			
			products.add(cd1);
			products.add(cd2);
			
			String messageCode;
			if (bookStore.insert(products)) {				
				messageCode = "message.insertionOK";
			} else {
				messageCode = "message.insertionFail";				
			}
			model.addAttribute("messageCode", messageCode);
;		return "endInsertion";
	}
}
