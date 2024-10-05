package ru.itmo.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.itmo.abstractions.CatRepository;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

import java.util.List;

public class CatRepositoryImpl implements CatRepository {
    private final SessionFactory sessionFactory;

    public CatRepositoryImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Cat> index() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        var result = session.createQuery("select p from Cat p", Cat.class)
                .getResultList();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public Cat getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var result = session.get(Cat.class, id);
        session.getTransaction().commit();
        return result;
    }

    @Override
    public Cat getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var result = session.createQuery("select p from Cat p where p.name = :name", Cat.class)
                .setParameter("name", name)
                .getSingleResult();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public List<Cat> getCatsByOwner(Owner owner) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var result = session.createQuery("select c from Cat c where c.owner=:owner", Cat.class)
                .setParameter("owner", owner)
                .getResultList();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public List<Cat> getCatFriends(Cat cat) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Cat> friends = cat.getFriends();
        session.getTransaction().commit();
        return friends;
    }

    @Override
    public void save(Cat cat) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(cat);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Cat cat) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(cat);
        session.getTransaction().commit();
    }

    @Override
    public void deleteFriend(Cat firstCat, Cat secondCat) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(firstCat);
        session.update(secondCat);
        session.getTransaction().commit();
    }

    @Override
    public void addFriend(Cat firstCat, Cat secondCat) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(firstCat);
        session.update(secondCat);
        session.getTransaction().commit();
    }
}
