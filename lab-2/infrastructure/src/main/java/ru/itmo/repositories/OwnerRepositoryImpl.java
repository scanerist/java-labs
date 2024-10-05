package ru.itmo.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.itmo.abstractions.OwnerRepository;
import ru.itmo.models.Owner;

import java.util.List;

public class OwnerRepositoryImpl implements OwnerRepository {
    private final SessionFactory sessionFactory;

    public OwnerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Owner> index() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        var result = session.createQuery("select p from Owner p", Owner.class)
                .getResultList();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public Owner getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var result = session.get(Owner.class, id);
        session.getTransaction().commit();
        return result;
    }

    @Override
    public Owner getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var result = session.createQuery("select p from Owner p where p.name = :name", Owner.class)
                .setParameter("name", name)
                .getSingleResult();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void save(Owner owner) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(owner);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Owner owner) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(owner);
        session.getTransaction().commit();
    }
}
