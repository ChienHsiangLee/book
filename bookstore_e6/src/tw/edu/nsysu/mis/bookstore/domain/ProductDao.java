package tw.edu.nsysu.mis.bookstore.domain;

public interface ProductDao {
	public void insert(Product product);
	public void update(Product product);
	public void delete(Product product);
	public Product findByProductNo(String pNo);

}
