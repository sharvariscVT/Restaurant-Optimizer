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
        return getEntityManager().createQuery("SELECT w FROM WineCollection w WHERE w.wineName LIKE :searchString").setParameter("searchString", searchString).getResultList();
    }
         
         public List<WineCollection> countryQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the company name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT w FROM WineCollection w WHERE w.country LIKE :searchString").setParameter("searchString", searchString).getResultList();
    }
         
         public List<WineCollection> yearQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the company name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT w FROM WineCollection w WHERE w.yearofManufacture LIKE :searchString").setParameter("searchString", searchString).getResultList();
         }
         
                  public List<WineCollection> typeQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the company name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT w FROM WineCollection w WHERE w.winetype LIKE :searchString").setParameter("searchString", searchString).getResultList();
    }
                  
//                   public List<WineCollection> greaterthanQuery(float price) {
//        // Place the % wildcard before and after the search string to search for it anywhere in the company name 
//        
//        // Conduct the search in a case-insensitive manner and return the results in a list.
//        return getEntityManager().createQuery("SELECT w FROM WineCollection w WHERE w.bottleprice > :price").setParameter("price", price).getResultList();
//    }
        
    
    public List<WineCollection> allQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in company name, ticker, or sector name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT w FROM WineCollection w WHERE w.wineName LIKE :searchString OR w.description LIKE :searchString OR w.country LIKE :searchString").setParameter("searchString", searchString).getResultList();
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