package com.educational.educational.dao;

import com.educational.educational.models.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
//  entity for connection to database
    private EntityManager entityManager;

    @Override
    public List<Users> getUsers() {
        String query = "FROM Users";
        List<Users> users = entityManager.createQuery(query, Users.class).getResultList();
        return users;
    }

}
