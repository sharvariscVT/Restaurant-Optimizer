package edu.vt.controllers;

import edu.vt.EntityBeans.WineCollection;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.WineCollectionFacade;
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

@Named("wineCollectionController")
@SessionScoped
public class WineCollectionController implements Serializable {

    @EJB
    private edu.vt.FacadeBeans.WineCollectionFacade ejbFacade;
    private List<WineCollection> items = null;
    private WineCollection selected;

    @Inject 
    private WineCollectionFacade wineFacade;
    
      public WineCollectionFacade getWineFacade() {
        return wineFacade;
    }
       public void setWineFacade(WineCollectionFacade wineFacade) {
        this.wineFacade = wineFacade;
    }

    @Inject
    MenuController menuController;
    
    private boolean isWineFileUploaded;

    public boolean isWineFileUploaded() {
        return isWineFileUploaded;
    }
   

    public void setWineFileUploaded(boolean wineFileUploaded) {
        this.isWineFileUploaded = wineFileUploaded;
    }
    public String winPhotoStoragePath() {
        return Constants.RELATIVE_STORAGE_PATH;
    }
    public String winePhotoStoragePath(){
        return Constants.ABSOLUTE_STORAGE_PATH;
    }
    
    public WineCollectionController() {
    }

    public WineCollection getSelected() {
        return selected;
    }

    public void setSelected(WineCollection selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private WineCollectionFacade getFacade() {
        return ejbFacade;
    }
     public String cancel() {
        // Unselect previously selected company if any
        selected = null;
        return "/userAccount/AdminProfile?faces-redirect=true";
    }
      public void deleteWineFile() {

        // This sets the necessary flag to ensure the messages are preserved.
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        // Delete the company logo file stored in a directory external to the app directory
        try {
            // Obtain the company logo file URI
            String companyLogoFileURI = Constants.ABSOLUTE_STORAGE_PATH + getSelected().getWineName() + ".png";

            // Obtain a Path object by converting the company logo file URI
            Path fileToDeletePath = Paths.get(companyLogoFileURI);

            // Delete the file under the Path object if it exists
            Files.deleteIfExists(fileToDeletePath);

            // Unselect previously selected company if any
            selected = null;
            setWineFileUploaded(false);

        } catch (IOException ex) {
            Methods.showMessage("Fatal Error", "Something went wrong during logo file deletion!",
                    "See: " + ex.getMessage());
        }
    }


    public WineCollection prepareCreate() {
        
        setWineFileUploaded(false);
        selected = new WineCollection();
        
        return selected;
    }

    public void create() {
         Methods.preserveMessages();
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("WineCollectionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
         Methods.preserveMessages();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("WineCollectionUpdated"));
    }

    public void destroy() {
         Methods.preserveMessages();
        
        
         try {
            // Obtain the company logo file URI
            String companyLogoFileURI = Constants.ABSOLUTE_STORAGE_PATH + getSelected().getWineName() + ".png";

            // Obtain a Path object by converting the company logo file URI
            Path fileToDeletePath = Paths.get(companyLogoFileURI);

            // Delete the file under the Path object if it exists
            Files.deleteIfExists(fileToDeletePath);

        } catch (IOException ex) {
            Methods.showMessage("Fatal Error", "Something went wrong during wine image deletion!",
                    "See: " + ex.getMessage());
        }
        
        
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("WineCollectionDeleted"));
        
        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            setWineFileUploaded(false);
        }
    }

    public List<WineCollection> getItems() {
        if (items == null) {
            items = getWineFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        
          Methods.preserveMessages();
        if (selected != null) {
          
            try {
                if (persistAction != PersistAction.DELETE) {
                    getWineFacade().edit(selected);
                } else {
                    getWineFacade().remove(selected);
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
        return getWineFacade().find(id);
    }

    public List<WineCollection> getItemsAvailableSelectMany() {
        return getWineFacade().findAll();
    }

    public List<WineCollection> getItemsAvailableSelectOne() {
        return getWineFacade().findAll();
    }

    @FacesConverter(forClass = WineCollection.class)
    public static class WineCollectionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WineCollectionController controller = (WineCollectionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "wineCollectionController");
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
