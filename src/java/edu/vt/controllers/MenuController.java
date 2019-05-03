/*
 * Created by Sharvari Chougule on 2019.04.25  * 
 * Copyright Â© 2019 Sharvari Chougule. All rights reserved. * 
 */
package edu.vt.controllers;


import edu.vt.EntityBeans.FoodMenu;
import edu.vt.EntityBeans.WineCollection;
import edu.vt.FacadeBeans.FoodMenuFacade;
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
    
    @Inject
    private FoodMenuFacade foodFacade;

    public FoodMenuFacade getFoodFacade() {
        return foodFacade;
    }

    public void setFoodFacade(FoodMenuFacade foodFacade) {
        this.foodFacade = foodFacade;
    }
    
    private WineCollection selected;
    private FoodMenu selected2;

    public FoodMenu getSelected2() {
        return selected2;
    }

    public void setSelected2(FoodMenu selected2) {
        this.selected2 = selected2;
    }
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

    private List<FoodMenu> foodMenu = null;

    public List<FoodMenu> getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(List<FoodMenu> foodMenu) {
        this.foodMenu = foodMenu;
    }
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
        return Constants.ABSOLUTE_STORAGE_PATH;
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
    
    public void showFoodMenu(){
        getFoodMenu();
        foodMenu = new ArrayList<>();
        System.out.println("inside foodmenu");
        foodMenu = foodFacade.findAllFood();
        System.out.println(foodMenu.size());
    }
    
    
    
}
