/*
 * Created by Sharvari Chougule on 2019.04.23  * 
 * Copyright Â© 2019 Sharvari Chougule. All rights reserved. * 
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.WineCollection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMIN
 */
@Stateless
public class WineCollectionFacade extends AbstractFacade<WineCollection> {

    @PersistenceContext(unitName = "Restaurant-OptimizerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WineCollectionFacade() {
        super(WineCollection.class);
    }
    public List<WineCollection> findAllWines() {  
           List<WineCollection> wines =em.createNamedQuery("WineCollection.findAll")
       
            .getResultList();
        System.out.println("============"+wines.size());
        return wines;
    
    
       }
}
