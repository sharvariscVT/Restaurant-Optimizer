package edu.vt.controllers;

import edu.vt.EntityBeans.FoodMenu;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.FoodMenuFacade;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import java.io.IOException;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.inject.Inject;

@Named("foodMenuController")
@SessionScoped
public class FoodMenuController implements Serializable {

    @EJB
    private edu.vt.FacadeBeans.FoodMenuFacade ejbFacade;
    
    @Inject 
    private FoodMenuFacade foodFacade;

    private Boolean FoodFileUploaded ;

    public Boolean isFoodFileUploaded() {
        return FoodFileUploaded;
    }

    public void setFoodFileUploaded(Boolean FoodFileUploaded) {
        this.FoodFileUploaded = FoodFileUploaded;
    }
    public FoodMenuFacade getFoodFacade() {
        return foodFacade;
    }

    public void setFoodFacade(FoodMenuFacade foodFacade) {
        this.foodFacade = foodFacade;
    }
    
    private List<FoodMenu> items = null;
    private FoodMenu selected;

    public FoodMenuController() {
    }

    public FoodMenu getSelected() {
        return selected;
    }

    public void setSelected(FoodMenu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FoodMenuFacade getFacade() {
        return ejbFacade;
    }
    public String foodPhotoStoragePath(){
        return Constants.ABSOLUTE_STORAGE_PATH;
    }
    

    public FoodMenu prepareCreate() {
        setFoodFileUploaded(false);
        selected = new FoodMenu();
      
        return selected;
    }

    public void create() {
          Methods.preserveMessages();

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FoodMenuCreated"));
        if (!JsfUtil.isValidationFailed()) {
            selected =null;
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
                 Methods.preserveMessages();

        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FoodMenuUpdated"));
    }

    public void destroy() {
       Methods.preserveMessages();
        
        
        try {
            // Obtain the company logo file URI
            String companyLogoFileURI = Constants.ABSOLUTE_FOODSTORAGRE_PATH + getSelected().getName() + ".png";

            // Obtain a Path object by converting the company logo file URI
            Path fileToDeletePath = Paths.get(companyLogoFileURI);

            // Delete the file under the Path object if it exists
            Files.deleteIfExists(fileToDeletePath);

        } catch (IOException ex) {
            Methods.showMessage("Fatal Error", "Something went wrong during wine image deletion!",
                    "See: " + ex.getMessage());
        }
        
        
        
        
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FoodMenuDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
           setFoodFileUploaded(false);

        }
    }

    public List<FoodMenu> getItems() {
        if (items == null) {
            items = getFoodFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFoodFacade().edit(selected);
                } else {
                    getFoodFacade().remove(selected);
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

    public FoodMenu getFoodMenu(java.lang.Integer id) {
        return getFoodFacade().find(id);
    }
        public String cancel() {
        // Unselect previously selected company if any
        selected = null;
        return "/userAccount/AdminProfile?faces-redirect=true";
    }
        
         public void deleteFoodFile(){
          FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        // Delete the company logo file stored in a directory external to the app directory
        try {
            // Obtain the company logo file URI
            String foodFileURI = Constants.ABSOLUTE_FOODSTORAGRE_PATH + getSelected().getName() + ".png";

            // Obtain a Path object by converting the company logo file URI
            Path fileToDeletePath = Paths.get(foodFileURI);

            // Delete the file under the Path object if it exists
            Files.deleteIfExists(fileToDeletePath);

            // Unselect previously selected company if any
            selected = null;
            setFoodFileUploaded(false);

        } catch (IOException ex) {
            Methods.showMessage("Fatal Error", "Something went wrong during logo file deletion!",
                    "See: " + ex.getMessage());
        }
         
     }

    public List<FoodMenu> getItemsAvailableSelectMany() {
        return getFoodFacade().findAll();
    }

    public List<FoodMenu> getItemsAvailableSelectOne() {
        return getFoodFacade().findAll();
    }

    @FacesConverter(forClass = FoodMenu.class)
    public static class FoodMenuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FoodMenuController controller = (FoodMenuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "foodMenuController");
            return controller.getFoodMenu(getKey(value));
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
            if (object instanceof FoodMenu) {
                FoodMenu o = (FoodMenu) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FoodMenu.class.getName()});
                return null;
            }
        }

    }

}
