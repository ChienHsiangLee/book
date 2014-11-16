package tw.edu.nsysu.mis.bookstore.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Scope("singleton")
public class JdbcProductDao implements ProductDao {
	private DataSource dataSource;
	private PlatformTransactionManager transactionManager;
	protected Product instance = null;

	@Autowired
	public JdbcProductDao(DataSource dataSource,
			PlatformTransactionManager transactionManager) {
		super();
		this.dataSource = dataSource;
		this.transactionManager = transactionManager;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Transactional
	public void insert(Product product) {

		String sql = "INSERT INTO Product (pNo, pName, unitPrice, category) "
				+ "VALUES (?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.update(
				sql,
				new Object[] { product.getpNo(), product.getpName(),
						product.getUnitPrice(), product.getCategory() });
	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE Product SET pNo=:pNo, pName=:pName, unitPrice=:unitPrice, category=:category WHERE pNo = :key";
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
				this.dataSource);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pNo", product.getpNo());
		parameters.put("pName", product.getpName());
		parameters.put("unitPrice", product.getUnitPrice());
		parameters.put("category", product.getCategory());
		parameters.put("key", product.getpNo());

		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void delete(Product product) {
		String sql = "DELETE Product WHERE pNo = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.update(sql, product.getpNo());
	}

	@Override
	public Product findByProductNo(String pNo) {
		String sql = "SELECT * FROM Product WHERE pNo = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

		final Product product = this.getProductInstance();
		jdbcTemplate.query(sql, new Object[] { pNo }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				product.setpNo(rs.getString("pNo"));
				product.setpName(rs.getString("pName"));
				product.setUnitPrice(rs.getFloat("unitPrice"));
				product.setCategory(rs.getString("category"));
			}
		});
		return product;
	}

	protected Product getProductInstance() {
		if (this.instance == null) {
			this.instance = new Product();
		}
		return this.instance;
	}

	protected void setProductInstance(Product instance) {
		this.instance = instance;
	}

	public List<Product> getListByProductName(String pName) {
		String sql = "SELECT * FROM Product WHERE pName LIKE '%" + pName + "%'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

		List<Product> products = new ArrayList<Product>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			Product product = new Product();
			product.setpNo((String) row.get("pNo"));
			product.setpName((String) row.get("pName"));
			product.setUnitPrice((Double) row.get("unitPrice"));
			product.setCategory((String) row.get("category"));
			products.add(product);
		}

		return products;
	}

}
