package sophia.hibernate;

import org.apache.log4j.PropertyConfigurator;

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

		// insert
		SessionManager.instance().insert(book);

		// select
		Book bookToUpdate = (Book) SessionManager.instance().select(Book.class, "북네임");

		// update
		bookToUpdate.setBookPrice("9999");
		SessionManager.instance().update(bookToUpdate);

		// delete
		SessionManager.instance().delete(book);

		// sessionFactory CLOSE
		SessionManager.instance().closeSessionFactory();

	}
}
