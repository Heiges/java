package biz.heiges.java.h2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionPool implements AutoCloseable {

	private SessionFactory sessionFactory = null;
	private Session session = null;
	
	public SessionPool() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
    	session = sessionFactory.openSession();
	}

	@Override
	public void close() throws Exception {
		System.out.println("calling close()");
		if(session.isOpen()== true) session.close();
		if(sessionFactory.isOpen() == true) sessionFactory.close();
	}

    protected Session getSession() {
    	return session;
	}
}
