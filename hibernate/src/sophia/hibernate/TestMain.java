package sophia.hibernate;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.SessionFactory;

import sophia.hibernate.manager.SessionManager;
import sophia.hibernate.model.Book;

public class TestMain {
	public static void main(String[] args) {

		// log4j.properties
		PropertyConfigurator
				.configure("C:\\DevelopTools\\Workspace\\git2\\hibernate\\hibernate\\config\\log4j-copier.properties");

		Book book = new Book();
		book.setBookNm("북네임~");
		book.setBookPrice("1000");

		// sessionFactory OPEN
		SessionFactory sessionFactory = SessionManager.instance().getSessionFactory();
		SessionFactory sessionFactory2 = SessionManager.instance().getSessionFactory2();

		// insert
		SessionManager.instance().insert(sessionFactory2, book);

		// select
		Book bookToUpdate = (Book) SessionManager.instance().select(sessionFactory, Book.class, "북네임");

		// update
		bookToUpdate.setBookPrice("9999");
		SessionManager.instance().update(sessionFactory, bookToUpdate);

		// delete
		SessionManager.instance().delete(sessionFactory2, book);

		// select by query
		// Table name under 'FROM' should be exactly the case-sensitive name of the class
		// under model package.
		// Column name should be exactly the case-sensitive name of the property in the
		// class under model package.
		String hql = "SELECT bookNm, bookPrice FROM Book ORDER BY bookPrice DESC";
		List<Object> bookList = SessionManager.instance().selectByQuery(sessionFactory, Book.class, hql);

		// 테이블 join
		hql = "SELECT book.bookNm, book.bookPrice FROM Book as book, Book2 as book2 WHERE book.bookNm = book2.bookNm AND book.bookNm LIKE '%북%' ORDER BY book.bookPrice DESC";
		List<Object> bookList2 = SessionManager.instance().selectByQuery(sessionFactory, Book.class, hql);

		// sessionFactory CLOSE
		SessionManager.instance().closeSessionFactory();

	}
}
