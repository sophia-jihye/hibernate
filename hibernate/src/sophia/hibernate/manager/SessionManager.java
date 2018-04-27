package sophia.hibernate.manager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final SessionManager instance = new SessionManager();

	private SessionManager() {
	}

	public static SessionManager instance() {
		return instance;
	}

	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;

	private SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
				serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
						.buildServiceRegistry();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			} catch (HibernateException e) {
				logger.error("[sophia-hibernate] HibernateException occurred while getting sessionFactory", e);
			}
		}
		return sessionFactory;
	}

	public void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	public Object select(Class<?> targetClass, String primaryKeyValue) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Object output = session.get(targetClass, primaryKeyValue);

		logger.debug("[sophia-hibernate] Select completed: {}", output);
		logger.debug("--------------------");

		session.close();
		return output;
	}

	public Object select(Class<?> targetClass, Integer primaryKeyValue) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Object output = session.get(targetClass, primaryKeyValue);

		logger.debug("[sophia-hibernate] Select completed: {}", output);
		logger.debug("--------------------");

		session.close();
		return output;
	}

	public void insert(Object obj) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();

		logger.debug("[sophia-hibernate] Insert completed");
		logger.debug("--------------------");

		session.close();
	}

	public void update(Object obj) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();

		logger.debug("[sophia-hibernate] Update completed");
		logger.debug("--------------------");

		session.close();
	}

	public void delete(Object obj) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();

		logger.debug("[sophia-hibernate] Delete completed");
		logger.debug("--------------------");

		session.close();
	}

}
