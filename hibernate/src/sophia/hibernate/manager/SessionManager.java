package sophia.hibernate.manager;

import org.hibernate.HibernateException;
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

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure("/hibernate.cfg2.xml");
				serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
						.buildServiceRegistry();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			} catch (HibernateException e) {
				logger.error("[sophia-hibernate] HibernateException occurred while getting sessionFactory", e);
			}
		}
		return sessionFactory;
	}

}
