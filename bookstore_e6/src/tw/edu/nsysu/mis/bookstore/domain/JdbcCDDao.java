package tw.edu.nsysu.mis.bookstore.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
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
public class JdbcCDDao extends JdbcProductDao {

	@Autowired
	public JdbcCDDao(DataSource dataSource,
			PlatformTransactionManager transactionManager) {
		super(dataSource, transactionManager);
	}

	@Transactional(propagation = Propagation.REQUIRED)	
	public void insert(final Product product) {
		//TransactionDefinition def = new DefaultTransactionDefinition();
		//TransactionStatus status = this.getTransactionManager().getTransaction(def);	

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());	
	
		//try {
			super.insert(product);
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					String sql = "INSERT INTO CD (pNo, composer, performer, number, publisher) "
							+ "VALUES (?, ?, ?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, product.getValue("pNo"));
					ps.setString(2, product.getValue("composer"));
					ps.setString(3, product.getValue("performer"));
					ps.setDouble(4, Double.valueOf(product.getValue("number")));
					ps.setString(5, product.getValue("publisher"));
					return ps;
				}
			});
			//this.getTransactionManager().commit(status);
		//} catch (DataAccessException e) {
			//this.getTransactionManager().rollback(status);
			//throw e;
		//}
	}

	@Override
	public void update(Product product) {
		final CompactDisc cd = (CompactDisc) product;
		String sql = "UPDATE CD SET composer=?, performer=?, number=?, publisher=? WHERE pNo=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());

		int no = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, cd.getComposer());
				ps.setString(2, cd.getPerformer());
				ps.setDouble(3, cd.getNumber());
				ps.setString(4, cd.getPublisher());
			}
		});
		if (no > 0) {
			super.update(product);
		}
	}

	@Override
	public void delete(Product product) {
		String sql = "DELETE CD WHERE pNo = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		int no = jdbcTemplate.update(sql, product.getpNo());

		if (no > 0) {
			super.delete(product);
		}
	}

	@Override
	public Product findByProductNo(String pNo) {

		String sql = "SELECT * FROM CD WHERE pNo = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());

		final CompactDisc cd = new CompactDisc();
		jdbcTemplate.query(sql, new Object[] { pNo }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				cd.setComposer(rs.getString("composer"));
				cd.setPerformer(rs.getString("performer"));
				cd.setNumber(rs.getFloat("number"));
				cd.setPublisher(rs.getString("publisher"));
			}
		});
		this.setProductInstance(cd);
		super.findByProductNo(pNo);
		// TODO Auto-generated method stub
		return cd;
	}

	@Override
	protected Product getProductInstance() {
		if (this.instance == null) {
			this.instance = new CompactDisc();
		}
		return this.instance;
	}
}
