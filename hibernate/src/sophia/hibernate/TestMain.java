package sophia.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestMain {
	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Book book = new Book();
		book.setBookNm("북네임");
		book.setBookPrice("1000");

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		System.out.println("Insert completed");

		session.close();
		sessionFactory.close();

	}
}
