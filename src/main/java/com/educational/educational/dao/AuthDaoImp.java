package com.educational.educational.dao;

import com.educational.educational.models.Users;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.lang.Integer.parseInt;

@Repository
@Transactional
public class AuthDaoImp implements AuthDao {

    @PersistenceContext
//  entity for connection to database
    private EntityManager entityManager;

    @Override
    public Users verifyCrendentials(Users user) {
        String query = "FROM Users WHERE email = :email AND role = :role AND status = 1";
        List<Users> result =  entityManager.createQuery(query, Users.class)
                .setParameter("email", user.getEmail())
                .setParameter("role", user.getRole())
                .getResultList();

        if(result.isEmpty()){
            return null;
        }

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        boolean isPassword = argon2.verify(result.get(0).getPassword(), user.getPassword());

        if (isPassword) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public boolean createUser(Users user) {

        String query = "FROM Users WHERE email = :email OR name = :name AND status = 1";

        List<Users> result = entityManager.createQuery(query, Users.class)
                .setParameter("email", user.getEmail())
                .setParameter("name", user.getName())
                .getResultList();

        if(!result.isEmpty()) {
            return false;
        }

        entityManager.merge(user);
        return true;
    }

    @Override
    public Users getUserByID(String userID) {
        String query = "FROM Users WHERE id = :id AND status = 1";
        List<Users> result = entityManager.createQuery(query, Users.class)
                            .setParameter("id", parseInt(userID))
                            .getResultList();

        if(result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
