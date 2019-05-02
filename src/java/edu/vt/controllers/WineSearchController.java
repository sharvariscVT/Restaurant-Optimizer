package edu.vt.controllers;


import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;


import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.ArrayList;
import edu.vt.EntityBeans.*;
import edu.vt.FacadeBeans.*;

@Named("wineSearchController")
@SessionScoped
public class WineSearchController implements Serializable {

    
    // searchField refers to Game Name or All
    private String searchField;

    // searchString contains the character string the user entered for searching the selected searchField
    private String searchString;
    private float price;
    
    @EJB
    private WineCollectionFacade wineCollectionFacade;
    
    private WineCollection selected;
    //private String tableTitle = "Games that you should definitely play";

    private List<WineCollection> searchItems = null;
    private List<WineCollection> items = null;

//    public String getTableTitle() {
//        return tableTitle;
//    }
    
//    public void setTableTitle(String s) {
//        tableTitle = s;
//    }

    public WineSearchController() {
    }

    public WineCollection getSelected() {
        return selected;
    }

    public void setSelected(WineCollection selected) {
        this.selected = selected;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private WineCollectionFacade getWineCollectionFacade() {
        return wineCollectionFacade;
    }

    public void setGamesFacade(WineCollectionFacade wineCollectionFacade) {
        this.wineCollectionFacade = wineCollectionFacade;
    }
    public WineCollection prepareCreate() {
        selected = new WineCollection();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("GamesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("GamesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("GamesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    
    public String cancel() {
        // Unselect previously selected company if any
        selected = null;
        return "/menu/pages/WineMenu?faces-redirect=true";
    }

    
  public String goBackToList() {
       // Unselect a company selected in search results if any before showing the List page
       selected = null;
       return "/menuPages/WineMenu?faces-redirect=true";
    }
    
    
    public List<WineCollection> getItems() {
        if (items == null) {
            // CompanyFacade inherits the findAll() method from the AbstractFacade class
            items = getWineCollectionFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getWineCollectionFacade().edit(selected);
                } else {
                    getWineCollectionFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public WineCollection getWineCollection(java.lang.Integer id) {
        return getWineCollectionFacade().find(id);
    }

    public List<WineCollection> getItemsAvailableSelectMany() {
        return getWineCollectionFacade().findAll();
    }

    public List<WineCollection> getItemsAvailableSelectOne() {
        return getWineCollectionFacade().findAll();
    }
    
    
    
    public WineCollection getWineCollection(java.lang.Long id) {
        // CompanyFacade inherits the findAll() method from the AbstractFacade class
        return getWineCollectionFacade().find(id);
    }
    
    
    
    public List<WineCollection> getSearchItems() {
        /*
        =============================================================================================
        You must construct and return the search results List "searchItems" ONLY IF the List is null. 
        Any List provided to p:dataTable to display must be returned ONLY IF the List is null
        ===> in order for the column-sort to work. <===
        =============================================================================================
         */
        if (searchItems == null) {
            switch (searchField) {
                case "Name":
                    // Return the list of object references of all those companies where 
                    // company name contains the search string 'searchString' entered by the user.
                    searchItems = getWineCollectionFacade().nameQuery(searchString);
                    break;
                    
                case "Country":
                    // Return the list of object references of all those companies where 
                    // company name contains the search string 'searchString' entered by the user.
                    searchItems = getWineCollectionFacade().countryQuery(searchString);
                    break;
                    
                case "Year":
                    // Return the list of object references of all those companies where 
                    // company name contains the search string 'searchString' entered by the user.
                    searchItems = getWineCollectionFacade().yearQuery(searchString);
                    break;
                    
                case "Type":
                    // Return the list of object references of all those companies where 
                    // company name contains the search string 'searchString' entered by the user.
                    searchItems = getWineCollectionFacade().typeQuery(searchString);
                    break;
                
//                case "GreaterThan":
//                    // Return the list of object references of all those companies where 
//                    // company name contains the search string 'searchString' entered by the user.
//                    searchItems = getWineCollectionFacade().greaterthanQuery(price);
//                    break;

                default:
                    // Return the list of object references of all those companies where company name,
                    // ticker symbol, or sector name contains the search string 'searchString' entered by the user.
                    searchItems = getWineCollectionFacade().allQuery(searchString);
            }
        }
        return searchItems;
    }
    
        /*
     ********************************************
     *   Display the SearchResults.xhtml Page   *
     ********************************************
     */
    public String search() {
        // Unselect previously selected game if any before showing the search results
        selected = null;

        // Invalidate list of search items to trigger re-query.
        searchItems = null;
        
        //System.out.println("..................Done");
        return "/SearchResults?faces-redirect=true";
       
    }

//    public void test()
//    {
//        
//        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
//        
//    }

    @FacesConverter(forClass = WineCollection.class)
    public static class GamesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WineSearchController controller = (WineSearchController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "wineSearchController");
            return controller.getWineCollection(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof WineCollection) {
                WineCollection o = (WineCollection) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), WineCollection.class.getName()});
                return null;
            }
        }

    }

}
