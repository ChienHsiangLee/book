package tw.edu.nsysu.mis.bookstore.domain;

import org.springframework.stereotype.Component;

import tw.edu.nsysu.mis.bookstore.domain.Product;
import tw.edu.nsysu.mis.bookstore.domain.digitalVideoDisk;
import tw.edu.nsysu.mis.bookstore.domain.CompactDisc;
import tw.edu.nsysu.mis.bookstore.domain.Book;

@Component
public class ProductFactory {
	
	
	public ProductFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product createProduct(String category) {
		if ("book".equalsIgnoreCase(category)) {
		return new Book();
		} else if ("CD".equalsIgnoreCase(category)) {
		return new CompactDisc();
		} else if ("DVD".equalsIgnoreCase(category)) {
			return new digitalVideoDisk();
		}
		throw new IllegalArgumentException("Unknown product");
	}

}
