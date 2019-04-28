/*
 * Created by Sharvari Chougule on 2019.04.25  * 
 * Copyright Â© 2019 Sharvari Chougule. All rights reserved. * 
 */
package edu.vt.controllers;


import edu.vt.EntityBeans.WineCollection;
import edu.vt.FacadeBeans.WineCollectionFacade;
import edu.vt.globals.Constants;
import java.io.Serializable;

import javax.inject.Named;
import edu.vt.globals.Methods;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author ADMIN
 */
@SessionScoped
@Named(value = "menuController")

/**
 *
 * @author ADMIN
 */
public class MenuController implements Serializable {
    
    
    
    @Inject 
    private WineCollectionFacade wineFacade;
    
    private WineCollection selected;

    public WineCollection getSelected() {
        return selected;
    }

    public void setSelected(WineCollection selected) {
        this.selected = selected;
    }
    private boolean isWineFileUploaded;

    public boolean isWineFileUploaded() {
        return isWineFileUploaded;
    }
   

    public void setWineFileUploaded(boolean wineFileUploaded) {
        this.isWineFileUploaded = wineFileUploaded;
    }
    private List<WineCollection> wineCollection = null;

    public WineCollectionFacade getWineFacade() {
        return wineFacade;
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


    public void setWineFacade(WineCollectionFacade wineFacade) {
        this.wineFacade = wineFacade;
    }

    
//    @PostConstruct
//    void init(){
//        getWineCollection();
//        wineCollection = new ArrayList<>();
//        wineCollection = wineFacade.findAllWines();
//        System.out.println(wineCollection);
//    }
//    
     public String winPhotoStoragePath() {
        return Constants.RELATIVE_STORAGE_PATH;
    }
    
    public List<WineCollection> getWineCollection() {
        return wineCollection;
    }

    public void setWineCollection(List<WineCollection> wineCollection) {
        this.wineCollection = wineCollection;
    }
    
    public void showWineCollection(){
        getWineCollection();
        wineCollection = new ArrayList<>();
        wineCollection = wineFacade.findAllWines();
        System.out.println(wineCollection);
        
         
    }
    
    
    
}
