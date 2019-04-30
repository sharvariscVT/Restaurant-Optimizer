/*
 * Created by Sharvari Chougule on 2019.04.29  * 
 * Copyright Â© 2019 Sharvari Chougule. All rights reserved. * 
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.FoodMenu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMIN
 */
@Stateless
public class FoodMenuFacade extends AbstractFacade<FoodMenu> {

    @PersistenceContext(unitName = "Restaurant-OptimizerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FoodMenuFacade() {
        super(FoodMenu.class);
    }
    
     public List<FoodMenu> findAllFood() {  
           List<FoodMenu> food =em.createNamedQuery("FoodMenu.findAll")
       
            .getResultList();
        System.out.println("============"+food.size());
        
        return food;
    
    
       }
}
