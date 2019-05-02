/*
 * Created by Sharvari Chougule 
 * Copyright © 2019 Sharvari Chougule. All rights reserved. 
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.TableBooking;
import edu.vt.EntityBeans.User;

import edu.vt.EntityBeans.UserPhoto;
import edu.vt.FacadeBeans.TableBookingFacade;
import edu.vt.FacadeBeans.UserFacade;

import edu.vt.FacadeBeans.UserPhotoFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import edu.vt.globals.Password;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/*
---------------------------------------------------------------------------
The @Named (javax.inject.Named) annotation indicates that the objects
instantiated from this class will be managed by the Contexts and Dependency
Injection (CDI) container. The name "userController" is used within
Expression Language (EL) expressions in JSF (XHTML) facelets pages to
access the properties and invoke methods of this class.
---------------------------------------------------------------------------
 */
@Named("tableBookingController")

/*
The @SessionScoped annotation preserves the values of the UserController
object's instance variables across multiple HTTP request-response cycles
as long as the user's established HTTP session is alive.
 */
@SessionScoped

/*
--------------------------------------------------------------------------
Marking the UserController class as "implements Serializable" implies that
instances of the class can be automatically serialized and deserialized. 

Serialization is the process of converting a class instance (object)
from memory into a suitable format for storage in a file or memory buffer, 
or for transmission across a network connection link.

Deserialization is the process of recreating a class instance (object)
in memory from the format under which it was stored.
--------------------------------------------------------------------------
 */
public class TableBookingController implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private String username;
  //  private String password;
  //  private String confirmPassword;

    private String firstName;
    private String middleName;
    private String lastName;
    private Date bookingDate;
   private Date bookingTime;
      
    private Integer numberPeople;
    private String email;
 


  //  private Map<String, Object> security_questions;
  //  private List<User> allUsers = null;
    
  private List<TableBooking> allBookings = null;
    
    
           
    private TableBooking newBooking;


    private TableBooking selected;
    
    private User loggedInUser;
    
    private static Integer numTablesBooked=0;
     private static final Integer MAXTABLES = 20; //Maximum number of tables available in the restaurant

    public User getLoggedInUser() {
             if (loggedInUser == null) {
            // Store the object reference of the signed-in User into the instance variable selected.
            loggedInUser = (User) Methods.sessionMap().get("user");
        }
        // Return the object reference of the selected (i.e., signed-in) User object
  
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    /*
    The instance variable 'userFacade' is annotated with the @EJB annotation.
    The @EJB annotation directs the EJB Container (of the GlassFish AS) to inject (store) the object reference
    of the UserFacade object, after it is instantiated at runtime, into the instance variable 'userFacade'.
     */
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private TableBookingFacade tableBookingFacade;

    /*
    The instance variable 'userPhotoFacade' is annotated with the @EJB annotation.
    The @EJB annotation directs the EJB Container (of the GlassFish AS) to inject (store) the object reference 
    of the UserPhotoFacade object, after it is instantiated at runtime, into the instance variable 'userPhotoFacade'.
     */
    @EJB
    private UserPhotoFacade userPhotoFacade;

    /*
    The instance variable 'userFileFacade' is annotated with the @EJB annotation.
    The @EJB annotation directs the EJB Container (of the GlassFish AS) to inject (store) the object reference 
    of the UserFileFacade object, after it is instantiated at runtime, into the instance variable 'userFileFacade'.
     */
  

    /*
    ==================
    Constructor Method
    ==================
     */
    public TableBookingController() {
        
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    
     public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }
    
    
    
     public Integer getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }

    public Integer getNumTablesBooked() {
        return numTablesBooked;
    }

public void setNumTablesBooked(Integer numTablesBooked) {
    this.numTablesBooked = numTablesBooked;
    }
   
      
/*
    public User getSelected() {
        if (selected == null) {
            // Store the object reference of the signed-in User into the instance variable selected.
            selected = (User) Methods.sessionMap().get("user");
        }
        // Return the object reference of the selected (i.e., signed-in) User object
        return selected;
    

    public void setSelected(User selected) {
        this.selected = selected;
    }
    */
       public TableBooking getSelected() {
        return selected;
    }

    public void setSelected(TableBooking selected) {
       // surveyItems = null;    // Invalidate list of BEVQ items to trigger re-query.
        this.selected = selected;
    }

    public TableBookingFacade getTableBookingFacade() {
        return tableBookingFacade;
    }

    public void setTableBookingFacade(TableBookingFacade tableBookingFacade) {
        this.tableBookingFacade = tableBookingFacade;
    }

    public UserPhotoFacade getUserPhotoFacade() {
        return userPhotoFacade;
    }

    public void setUserPhotoFacade(UserPhotoFacade userPhotoFacade) {
        this.userPhotoFacade = userPhotoFacade;
    }

  

    /*
    ================
    Instance Methods
    ================

    **********************************
    Return True if a User is Signed In
    **********************************
     */
    public boolean isLoggedIn() {
        /*
        The username of a signed-in user is put into the SessionMap in the
        initializeSessionMap() method in LoginManager upon user's sign in.
        If there is a username, that means, there is a signed-in user.
         */
        return Methods.sessionMap().get("username") != null;
    }

  
    
    public boolean isAdministrator(){
       // User signedInUser = (User) Methods.sessionMap().get("user");
        
       if( Methods.sessionMap().get("username") == null)
           return false;
       
       if(Methods.sessionMap().get("username").equals("Administrator"))
            return true;
              
        
       /* if(signedInUser.getUsername()== null){
            return false;}
        else if("Administrator".equals(signedInUser.getUsername())){
        return true;
        }*/
        return false;
    }

  
     
      public String bookTable() {
           
           //Considering 1 table for upto 4 people
           
           if(numberPeople<=4)
               
               numTablesBooked+=1;
           else
           
        numTablesBooked += (numberPeople/4);
           
           System.out.println("Number of tables booked"+ numTablesBooked );
           
           if(numTablesBooked<MAXTABLES)
           {
         User loggedUser =  getLoggedInUser();
        
     
           
            newBooking=new TableBooking();


            /*
             Set the properties of the newly created newUser object with the values
             entered by the user in the AccountCreationForm in CreateAccount.xhtml             
             */
             newBooking.setUsername(loggedUser.getUsername());
            newBooking.setFirstName(loggedUser.getFirstName());
            newBooking.setMiddleName(loggedUser.getMiddleName());
            newBooking.setLastName(loggedUser.getLastName());
           newBooking.setBookingDate(bookingDate);
           newBooking.setBookingTime(bookingTime);
             newBooking.setNumberPeople(numberPeople);
              newBooking.setEmail(loggedUser.getEmail());
           
           // newBooking.setLastName(loggedUser.getLastName());
           
            
                        // Create table booking object  in the database
           getTableBookingFacade().create(newBooking);
          
              Methods.showMessage("Information", "Success!", "You have successfully booked a table!");
              
               return "/tableBooking/DisplayBooking?faces-redirect=true";
           }
           else
           {
               
                 Methods.showMessage("Error", "Sorry! There are no tables available for booking!", "Please try booking a table after some time!");
                  return "/tableBooking/BookTable?faces-redirect=false";
                 
           }
           
    }
    
      public void clearBooking() {

        newBooking = null;
        
        bookingDate=null;
        
        bookingTime=null;
        
        numberPeople=null;
       

      
    } 
      
      
   public List<TableBooking> getItems() {
        if (allBookings == null) {
           

            allBookings = getTableBookingFacade().findAll();
        }
        return allBookings;
    }

    public void setItems(List<TableBooking> allBookings) {
        this.allBookings = allBookings;
    }
    
     public List<TableBooking> getUserBookings() {
       
           

       // wineCollection = wineFacade.findAllWines();
            allBookings = getTableBookingFacade().findAll();
        
        return allBookings;
    }

    public void setUserBookings(List<TableBooking> allBookings) {
        this.allBookings = allBookings;
    }
    
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        
        Methods.preserveMessages();
        
        if (selected != null) {
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    /*
                     -----------------------------------------------
                     Perform SAVE or EDIT operation in the database.
                     -----------------------------------------------
                     The edit method performs the SAVE (STORE) operation in the database to
                     save a newly created publicVideo (CREATE) or an Edited publicVideo (UPDATE).
                    
                     PublicVideoFacade inherits the edit method from the AbstractFacade class.
                     */
                    getTableBookingFacade().edit(selected);
                } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove method performs the DELETE operation in the database.
                    
                     PublicVideoFacade inherits the remove method from the AbstractFacade class.
                     */
                    getTableBookingFacade().remove(selected);
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
    
    
    public void destroy() {

        //-----------------
        // Delete Logo File
        //-----------------
        
        /*
        Redirecting to show a JSF page involves more than one subsequent requests and
        the messages would die from one request to another if not kept in the Flash scope.
        Since we will redirect to show the SignIn page, we invoke preserveMessages().
         */
        Methods.preserveMessages();

        // Delete the publicVideo logo file stored in a directory external to the app directory
        
        //----------------------------------------------
        // Remove the Selected PublicVideo from the Database
        //----------------------------------------------
        /*
         The object reference of the publicVideo to be deleted is stored in the instance variable 'selected'
         See the persist method below.
         */
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TableBookingDeleted"));

        if (!JsfUtil.isValidationFailed()) {
            // The DELETE operation is successfully performed
            selected = null;    // Remove selection
            allBookings = null;       // Invalidate list of items to trigger re-query
            numTablesBooked-=1;
            
        }
    }

     public List<TableBooking> showUserBookings(){
         
         
        //allBookings = new ArrayList<>();
               
        allBookings=getTableBookingFacade().findBookingsByUsername(getLoggedInUser().getUsername());
       
        return allBookings;
        
         
    }
    

    /*
    ********************************************************
    Delete the photo, thumbnail, and tempFile that belong to
    the User object whose database primary key is primaryKey
    ********************************************************
     */
    public void deleteAllUserPhotos(int primaryKey) {

        /*
        Obtain the list of Photo objects that belong to the User whose
        database primary key is userId.
         */
        List<UserPhoto> photoList = getUserPhotoFacade().findPhotosByUserPrimaryKey(primaryKey);

        // photoList.isEmpty implies that the user has never uploaded a photo file
        if (!photoList.isEmpty()) {

            // Obtain the object reference of the first Photo object in the list.
            UserPhoto photo = photoList.get(0);
            try {
                /*
                NOTE: Files and Paths are imported as
                        import java.nio.file.Files;
                        import java.nio.file.Paths;

                 Delete the user's photo if it exists.
                 getFilePath() is given in UserPhoto.java file.
                 */
                Files.deleteIfExists(Paths.get(photo.getPhotoFilePath()));

                /*
                 Delete the user's thumbnail image if it exists.
                 getThumbnailFilePath() is given in UserPhoto.java file.
                 */
                Files.deleteIfExists(Paths.get(photo.getThumbnailFilePath()));

                // Delete the photo file record from the database.
                // UserPhotoFacade inherits the remove() method from AbstractFacade.
                getUserPhotoFacade().remove(photo);

                /*
                 Delete the user's captured photo file if it exists.
                 The file is named "user's primary key_tempFile".
                 */
                String capturedPhotoFilepath = Constants.PHOTOS_ABSOLUTE_PATH + primaryKey + "_tempFile";
                Files.deleteIfExists(Paths.get(capturedPhotoFilepath));

            } catch (IOException ex) {
                Methods.showMessage("Fatal Error",
                        "Something went wrong while deleting user's photo!",
                        "See: " + ex.getMessage());
            }
        }
    }

    /*
    ***********************************************
    Delete all of the files that belong to the User
    object whose database primary key is primaryKey
    ***********************************************
     */

    
   

}
