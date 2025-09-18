package Util;

import entity.Corso;
import entity.Esame;
import entity.Studente;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final String JDBC_URL = "jdbc:h2:file:./database/universita";; // TODO
    // private static final String HOST = "localhost";
    // private static final int PORT = 8081; // TODO
    private static final String USER = "sa";
    private static final String PASS = "";



    // private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        // hibernate configuration string h2
        // https://www.javaguides.net/2019/11/hibernate-h2-database-example-tutorial.html
        try {
            // Costruiamo oggetto per configurare hibernate
            Configuration cfg = new Configuration();
            cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
            cfg.setProperty("hibernate.connection.url",JDBC_URL);
            cfg.setProperty("hibernate.connection.username",USER);
            cfg.setProperty("hibernate.connection.password", PASS);
            cfg.setProperty("hibernate.hbm2ddl.auto", "create-drop"); // Specifica se creare DB allo start dell'app
            cfg.addAnnotatedClass(Studente.class); // aggiungi classi al contesto
            cfg.addAnnotatedClass(Corso.class); // aggiungi classi al contesto
            cfg.addAnnotatedClass(Esame.class); // aggiungi classi al contesto
            return cfg.buildSessionFactory();
        } catch (Throwable t){
            System.err.println(t.getMessage());
            return null;
        }
    }

}
