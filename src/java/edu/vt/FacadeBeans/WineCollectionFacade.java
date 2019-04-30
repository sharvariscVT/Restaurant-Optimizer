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
    
         public List<WineCollection> nameQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the company name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT w FROM WineCollection w WHERE w.WineName LIKE :searchString").setParameter("searchString", searchString).getResultList();
    }
    
    public List<WineCollection> allQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in company name, ticker, or sector name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT w FROM WineCollection w WHERE w.WineName LIKE :searchString OR w.Description LIKE :searchString OR w.Country LIKE :searchString").setParameter("searchString", searchString).getResultList();
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
