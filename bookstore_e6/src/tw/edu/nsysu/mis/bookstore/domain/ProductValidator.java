package tw.edu.nsysu.mis.bookstore.domain;

import tw.edu.nsysu.mis.bookstore.domain.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pNo", "required.pNo", "Product id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pName", "required.pName", "Product name is required.");		
		Product prod = (Product) target;
		if (prod.getUnitPrice() <= 0 ) {
			errors.rejectValue("unitPrice", "invalid.unitPrice", "Unit price must be greater than zero");
		}

	}
}
