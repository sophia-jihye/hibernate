package sophia.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sophia.hibernate.manager.SessionManager;
import sophia.hibernate.model.Book;

public class TestMain {
	public static void main(String[] args) {

		// sessionFactory, session OPEN
		SessionFactory sessionFactory = SessionManager.instance().getSessionFactory();
		Session session = sessionFactory.openSession();

		Book book = new Book();
		book.setBookNm("북네임");
		book.setBookPrice("1000");

		// insert
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		System.out.println("Insert completed");

		// update : select 후 변경하여 update
		session.beginTransaction();
		// select: 두번째 파라미터로 PK인 bookNm 입력
		Book book2 = (Book) session.get(Book.class, "북네임");
		System.out.println(book2);
		book2.setBookPrice("2000");
		session.getTransaction().commit();

		// delete
		session.beginTransaction();
		Book book3 = (Book) session.get(Book.class, "북네임");
		session.delete(book3);
		session.getTransaction().commit();

		// sessionFactory, session CLOSE
		session.close();
		sessionFactory.close();

	}
}
