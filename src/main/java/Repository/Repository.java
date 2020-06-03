package Repository;

import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class Repository<T> implements CRUDRepository<T> {
    T ob;

    public Repository(T ob) {
        this.ob = ob;
    }

    @Override
    public List<T> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(ob.getClass());
            Root<T> rootEntry = (Root<T>) criteriaQuery.from(ob.getClass());
            CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
            TypedQuery<T> allQuery = session.createQuery(all);
            Transaction tx1 = session.beginTransaction();
            var res = allQuery.getResultList();
            tx1.commit();
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public T find(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            //Начинается создание запроса
            CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(ob.getClass());
            //Указываем целевую таблицу
            Root<T> rootEntry = (Root<T>) criteriaQuery.from(ob.getClass());
            //Описание параметра
            ParameterExpression<Integer> parameterExpression = criteriaBuilder.parameter(Integer.class);
            //Описание запроса
            criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), parameterExpression));
            TypedQuery<T> query = session.createQuery(criteriaQuery);
            query.setParameter(parameterExpression, id);
            Transaction tx1 = session.beginTransaction();
            T res = (T) query.getSingleResult();
            tx1.commit();
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(T t) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(t);
        tx1.commit();
        session.close();
        System.out.println("\n**** Объект добавлен ****\n");
    }

    @Override
    public void delete(T t) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(t);
        tx1.commit();
        session.close();
        System.out.println("\n**** Объект удален ****\n");
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<T> delete = (CriteriaDelete<T>) criteriaBuilder.createCriteriaDelete(ob.getClass());
        Root<T> rootEntry = delete.from((Class<T>) ob.getClass());
        delete.where(criteriaBuilder.equal(rootEntry.get("id"), id));
        Transaction tx1 = session.beginTransaction();
        session.createQuery(delete).executeUpdate();
        tx1.commit();
        session.close();
        System.out.println();
        System.out.println("\n**** Объект удален ****\n");
        System.out.println();
    }

    @Override
    public void update(T t) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(t);
        tx1.commit();
        session.close();
        System.out.println();
        System.out.println("\n**** Объект обновлен ****\n");
        System.out.println();

    }
}
