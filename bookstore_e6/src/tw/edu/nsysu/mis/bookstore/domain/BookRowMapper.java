package tw.edu.nsysu.mis.bookstore.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setAuthor(rs.getString("author"));
		book.setISBN(rs.getString("ISBN"));
		book.setEdition(rs.getDouble("edition"));
		book.setPublisher(rs.getString("publisher"));
		return book;
	}

}
