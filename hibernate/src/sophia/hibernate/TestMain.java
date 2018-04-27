package sophia.hibernate;

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
		book.setBookNm("북네임!");
		book.setBookPrice("1000");

		// sessionFactory OPEN
		SessionFactory sessionFactory = SessionManager.instance().getSessionFactory();
		SessionFactory sessionFactory2 = SessionManager.instance().getSessionFactory2();

		// insert
		SessionManager.instance().insert(sessionFactory2, book);

		// select
		Book bookToUpdate = (Book) SessionManager.instance().select(sessionFactory2, Book.class, "북네임!");

		// update
		bookToUpdate.setBookPrice("9999");
		SessionManager.instance().update(sessionFactory2, bookToUpdate);

		// delete
		SessionManager.instance().delete(sessionFactory, book);

		// sessionFactory CLOSE
		SessionManager.instance().closeSessionFactory();

	}
}
