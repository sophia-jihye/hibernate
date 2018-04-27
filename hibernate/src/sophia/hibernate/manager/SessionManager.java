package sophia.hibernate.manager;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sophia.hibernate.model.Book;

public class SessionManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final SessionManager instance = new SessionManager();

	private SessionManager() {
	}

	public static SessionManager instance() {
		return instance;
	}

	private SessionFactory sessionFactory;
	private SessionFactory sessionFactory2;
	private ServiceRegistry serviceRegistry;

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
				if (serviceRegistry == null) {
					serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
							.buildServiceRegistry();
				}

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			} catch (HibernateException e) {
				logger.error("[sophia-hibernate] HibernateException occurred while getting sessionFactory", e);
			}
		}
		return sessionFactory;
	}

	public SessionFactory getSessionFactory2() {
		if (sessionFactory2 == null) {
			try {
				Configuration configuration = new Configuration().configure("/hibernate.cfg2.xml");
				if (serviceRegistry == null) {
					serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
							.buildServiceRegistry();
				}

				sessionFactory2 = configuration.buildSessionFactory(serviceRegistry);

			} catch (HibernateException e) {
				logger.error("[sophia-hibernate] HibernateException occurred while getting sessionFactory2", e);
			}
		}
		return sessionFactory2;
	}

	public void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
		if (sessionFactory2 != null) {
			sessionFactory2.close();
		}
	}

	public Object select(SessionFactory sessionFactory, Class<?> targetClass, String primaryKeyValue) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Object output = session.get(targetClass, primaryKeyValue);

		logger.debug("[sophia-hibernate] Select completed: {}", output);
		logger.debug("--------------------");

		session.close();
		return output;
	}

	public Object select(SessionFactory sessionFactory, Class<?> targetClass, Integer primaryKeyValue) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Object output = session.get(targetClass, primaryKeyValue);

		logger.debug("[sophia-hibernate] Select completed: {}", output);
		logger.debug("--------------------");

		session.close();
		return output;
	}

	public void insert(SessionFactory sessionFactory, Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();

		logger.debug("[sophia-hibernate] Insert completed");
		logger.debug("--------------------");

		session.close();
	}

	public void update(SessionFactory sessionFactory, Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();

		logger.debug("[sophia-hibernate] Update completed");
		logger.debug("--------------------");

		session.close();
	}

	public void delete(SessionFactory sessionFactory, Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();

		logger.debug("[sophia-hibernate] Delete completed");
		logger.debug("--------------------");

		session.close();
	}

	public List<Book> selectByQuery(SessionFactory sessionFactory, String hql) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery(hql);
		List<Book> outputList = (List<Book>) query.list();

		logger.debug("[sophia-hibernate] Select by query completed: {}", outputList);
		logger.debug("--------------------");

		// check
		for (int i = 0; i < outputList.size(); i++) {
			logger.debug("[sophia-hibernate] check: {}", outputList.get(i));
		}

		session.close();
		return outputList;
	}

}
