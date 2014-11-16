package tw.edu.nsysu.mis.bookstore.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class JdbcBookDao extends JdbcProductDao {

	@Autowired
	public JdbcBookDao(DataSource dataSource,
			PlatformTransactionManager transactionManager) {
		super(dataSource, transactionManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(final Product product) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = this.getTransactionManager().getTransaction(def);
		

		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
			super.insert(product);
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					String sql = "INSERT INTO Book (pNo, author, ISBN, edition, publisher) "
							+ "VALUES (?, ?, ?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, product.getValue("pNo"));
					ps.setString(2, product.getValue("author"));
					ps.setString(3, product.getValue("ISBN"));
					ps.setDouble(4, Double.valueOf(product.getValue("edition")));
					ps.setString(5, product.getValue("publisher"));
					return ps;
				}
			});
			this.getTransactionManager().commit(status);
		} catch (DataAccessException e) {
			this.getTransactionManager().rollback(status);
			throw e;
		}
	}

	@Override
	public void update(Product product) {
		final Book book = (Book) product;
		String sql = "UPDATE Book SET author=?, ISBN=?, edition=?, publisher=? WHERE pNo=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());

		int no = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, book.getAuthor());
				ps.setString(2, book.getISBN());
				ps.setDouble(3, book.getEdition());
				ps.setString(4, book.getPublisher());
			}
		});
		if (no > 0) {
			super.update(product);
		}

	}

	@Override
	public void delete(Product product) {
		String sql = "DELETE Book WHERE pNo = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		int no = jdbcTemplate.update(sql, product.getpNo());

		if (no > 0) {
			super.delete(product);
		}
	}

	@Override
	public Product findByProductNo(String pNo) {

		String sql = "SELECT * FROM Book WHERE pNo = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());

		Book book = (Book) jdbcTemplate.queryForObject(sql,
				new Object[] { pNo }, new BookRowMapper());
		this.setProductInstance(book);
		super.findByProductNo(pNo);
		// TODO Auto-generated method stub
		return book;
	}

	@Override
	protected Product getProductInstance() {
		if (this.instance == null) {
			this.instance = new Book();
		}
		return this.instance;
	}

}
