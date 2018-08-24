package com.data.collect.model;

import java.io.PrintStream;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSessionFactory
{
  private static final ThreadLocal<Session> threadLocal = new ThreadLocal();
  private static SessionFactory sessionFactory;
  private static Configuration configuration = new Configuration();
  private static ServiceRegistry serviceRegistry;
  
  static
  {
    try
    {
      configuration.configure();
      serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    catch (Exception e)
    {
      System.err.println("%%%% Error Creating SessionFactory %%%%");
      e.printStackTrace();
    }
  }
  
  public static Session getSession()
    throws HibernateException
  {
    Session session = (Session)threadLocal.get();
    if ((session == null) || (!session.isOpen()))
    {
      if (sessionFactory == null) {
        rebuildSessionFactory();
      }
      session = sessionFactory != null ? sessionFactory.openSession() : 
        null;
      threadLocal.set(session);
    }
    return session;
  }
  
  public static void rebuildSessionFactory()
  {
    try
    {
      configuration.configure();
      serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    catch (Exception e)
    {
      System.err.println("%%%% Error Creating SessionFactory %%%%");
      e.printStackTrace();
    }
  }
  
  public static void closeSession()
    throws HibernateException
  {
    Session session = (Session)threadLocal.get();
    threadLocal.set(null);
    if (session != null) {
      session.close();
    }
  }
  
  public static SessionFactory getSessionFactory()
  {
    return sessionFactory;
  }
  
  public static Configuration getConfiguration()
  {
    return configuration;
  }
}
