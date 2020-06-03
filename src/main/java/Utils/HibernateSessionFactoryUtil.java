package Utils;

import Entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(AdaptationsEntity.class);
                configuration.addAnnotatedClass(AuthorEntity.class);
                configuration.addAnnotatedClass(BookCopyEntity.class);
                configuration.addAnnotatedClass(BookEntity.class);
                configuration.addAnnotatedClass(BookIssuanceEntity.class);
                configuration.addAnnotatedClass(GenreEntity.class);
                configuration.addAnnotatedClass(PublishingHouseEntity.class);
                configuration.addAnnotatedClass(ReadersEntity.class);
                configuration.addAnnotatedClass(FineEntity.class);
                configuration.addAnnotatedClass(LogInAndSignUpEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                System.out.println();
                System.out.println("Создана SessionFactory!");
                System.out.println();

            }
            catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
