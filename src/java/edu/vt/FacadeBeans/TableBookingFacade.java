package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.TableBooking;
import edu.vt.EntityBeans.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
public class TableBookingFacade extends AbstractFacade<TableBooking> {

    @PersistenceContext(unitName = "Restaurant-OptimizerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TableBookingFacade() {
        super(TableBooking.class);
    }

    public void remove(User selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void edit(User selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<TableBooking> findBookingsByUsername(String username) {

   
        List<TableBooking> tables =em.createNamedQuery("TableBooking.findByUsername").setParameter("username", username).getResultList();
          
         //return em.createNamedQuery("TableBooking.findByUsername") .getResultList();
         return tables;
       
           
    }
    
}
