/*
 * Created by Sharvari Chougule on 2019.04.23  * 
 * Copyright Â© 2019 Sharvari Chougule. All rights reserved. * 
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMIN
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "Restaurant-OptimizerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
      public User findByUsername(String username) {
        if (em.createQuery("SELECT c FROM User c WHERE c.username = :username")
                .setParameter("username", username)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (User) (em.createQuery("SELECT c FROM User c WHERE c.username = :username")
                    .setParameter("username", username)
                    .getSingleResult());
        }
    }
      public void deleteUser(int id) {
        
        // The find method is inherited from the parent AbstractFacade class
        User user = em.find(User.class, id);
        
        // The remove method is inherited from the parent AbstractFacade class
        em.remove(user); 
    }
      
      public List<User> findAllUser() {  
           List<User> users =em.createNamedQuery("User.findAll")
        //    .setParameter("primaryKey", dbPrimaryKey)
            .getResultList();
    //   System.out.println("--;;;;;;----"+users);
        return users;
    
    
       }
    
    public List<Subscribers> findAllSubscribers() {  
           List<Subscribers> subs =em.createNamedQuery("Subscribers.findAll")
        //    .setParameter("primaryKey", dbPrimaryKey)
            .getResultList();
    //   System.out.println("--;;;;;;----"+users);
        return subs;
    
    
       }
      
    
      public void createSubscriberEntry(User forUser) {
        int user_id = forUser.getId();
        String email = forUser.getEmail();
        
        em.createNativeQuery("INSERT INTO Subscribers (id, email) VALUES (?, ?)")
            .setParameter(1, user_id)
            .setParameter(2, email)
            .executeUpdate();
    }
      public void removeSubscriberEntry(User forUser) {
        int user_id = forUser.getId();
        String email = forUser.getEmail();
        
        em.createNativeQuery("DELETE FROM Subscribers WHERE id=?")
            .setParameter(1, user_id)
            .executeUpdate();
    }
      
      public boolean findSubscriber(String email) {
        if (em.createQuery("SELECT c FROM Subscribers c WHERE c.email = :userEmail")
                .setParameter("userEmail", email)
                .getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
      }
}
