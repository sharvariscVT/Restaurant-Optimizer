/*
 * Created by Osman Balci on 2018.06.18
 * Copyright © 2018 Osman Balci. All rights reserved.
 */
package edu.vt.managers;

import edu.vt.controllers.FoodMenuController;
import edu.vt.globals.Constants;
import edu.vt.controllers.MenuController;
import edu.vt.controllers.WineCollectionController;
import edu.vt.globals.Methods;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

@Named(value = "fileUploadManager")
@SessionScoped

public class FileUploadManager implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private UploadedFile file;

    /*
    Using the @Inject annotation, the compiler is directed to store the object reference
    of the CompanyController CDI-managed bean into the instance variable companyController
    at runtime. With this injection, the instance variables and instance methods of the 
    CompanyController class can be accessed in this CDI-managed bean.
     */
    @Inject
    private MenuController menuController;

    @Inject
    private WineCollectionController wineCollectionController;
    
    @Inject 
    private FoodMenuController foodMenuController ;
    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /*
     ================
     Instance Methods
     ================

     **********************************************************************
     *   Handle the Logo File Upload and Store it in External Directory   *
     **********************************************************************
     */
    
    public String uploadFoodFile() throws IOException{
         Methods.preserveMessages();

        // Check if a file is selected
        if (file.getSize() == 0) {
            Methods.showMessage("Fatal Error", "No File Selected!", "Please choose a file first!");
            foodMenuController.setFoodFileUploaded(false);
            return "/foodMenu/List?faces-redirect=true";
        }

        /*
        MIME (Multipurpose Internet Mail Extensions) is a way of identifying files on
        the Internet according to their nature and format. 

        A "Content-type" is simply a header defined in many protocols, such as HTTP, that 
        makes use of MIME types to specify the nature of the file currently being handled.

        Some MIME file types: (See https://www.freeformatter.com/mime-types-list.html)

            JPEG Image      = image/jpeg or image/jpg
            PNG image       = image/png
            Plain text file = text/plain
            HTML file       = text/html
            JSON file       = application/json
         */
        // Obtain the uploaded file's MIME file type
        String mimeFileType = file.getContentType();

        if (mimeFileType.startsWith("image/")) {
            // The uploaded file is an image file
            /*
            The subSequence() method returns the portion of the mimeFileType string from the 6th
            position to the last character. Note that it starts with "image/" which has 6 characters at
            positions 0,1,2,3,4,5. Therefore, we start the subsequence at position 6 to obtain the file extension.
             */
            String fileExtension = mimeFileType.subSequence(6, mimeFileType.length()).toString();

            String fileExtensionInCaps = fileExtension.toUpperCase();

            if (fileExtensionInCaps.endsWith("PNG")) {
                // File type is acceptable
            } else {
                Methods.showMessage("Fatal Error", "Unrecognized Image File!", 
                        "The uploaded image file must be of type PNG with transparency!");
                return "/foodMenu/List?faces-redirect=true";
            }
        } else {
            Methods.showMessage("Fatal Error", "Unrecognized File Type!", 
                        "The uploaded file must be an image file of type PNG with transparency!");
            return "/foodMenu/List?faces-redirect=true";
        }

        // See the method below
        storeFoodFile(file);

        // Settings
        foodMenuController.setFoodFileUploaded(true);

        // Redirect to show the Create page
        return "/foodMenu/List?faces-redirect=true";
    }
    
    
    
    public String upload() throws IOException {

        Methods.preserveMessages();

        // Check if a file is selected
        if (file.getSize() == 0) {
            Methods.showMessage("Fatal Error", "No File Selected!", "Please choose a file first!");
            wineCollectionController.setWineFileUploaded(false);
            return "/wineCollection/List?faces-redirect=true";
        }

        /*
        MIME (Multipurpose Internet Mail Extensions) is a way of identifying files on
        the Internet according to their nature and format. 

        A "Content-type" is simply a header defined in many protocols, such as HTTP, that 
        makes use of MIME types to specify the nature of the file currently being handled.

        Some MIME file types: (See https://www.freeformatter.com/mime-types-list.html)

            JPEG Image      = image/jpeg or image/jpg
            PNG image       = image/png
            Plain text file = text/plain
            HTML file       = text/html
            JSON file       = application/json
         */
        // Obtain the uploaded file's MIME file type
        String mimeFileType = file.getContentType();

        if (mimeFileType.startsWith("image/")) {
            // The uploaded file is an image file
            /*
            The subSequence() method returns the portion of the mimeFileType string from the 6th
            position to the last character. Note that it starts with "image/" which has 6 characters at
            positions 0,1,2,3,4,5. Therefore, we start the subsequence at position 6 to obtain the file extension.
             */
            String fileExtension = mimeFileType.subSequence(6, mimeFileType.length()).toString();

            String fileExtensionInCaps = fileExtension.toUpperCase();

            if (fileExtensionInCaps.endsWith("PNG")) {
                // File type is acceptable
            } else {
                Methods.showMessage("Fatal Error", "Unrecognized Image File!", 
                        "The uploaded image file must be of type PNG with transparency!");
                return "/wineCollection/List?faces-redirect=true";
            }
        } else {
            Methods.showMessage("Fatal Error", "Unrecognized File Type!", 
                        "The uploaded file must be an image file of type PNG with transparency!");
            return "/wineCollection/List?faces-redirect=true";
        }

        // See the method below
        storeWineFile(file);

        // Settings
        wineCollectionController.setWineFileUploaded(true);

        // Redirect to show the Create page
        return "/wineCollection/List?faces-redirect=true";
    }

    /*
     ******************************************************
     *   Store Uploaded Logo File in External Directory   *
     ******************************************************
     */
    public void storeWineFile(UploadedFile file) throws IOException {

        Methods.preserveMessages();
        
        InputStream inputStream;

        try {
            /*
            InputStream is an abstract class, which is the superclass of all classes representing
            an input stream of bytes. Convert the uploaded file into an input stream of bytes.
             */
            inputStream = file.getInputstream();

            // Set the company's logo file name to company's stock symbol (ticker).png
            String wineFileName = wineCollectionController.getSelected().getId() + ".png";

            /*
            Write the uploaded file's input stream of bytes into the file named
            logoFileName in the external directory Constants.ABSOLUTE_STORAGE_PATH
            using the method inputStreamToFile below.
             */
            File wineFile = inputStreamToFile(inputStream, wineFileName, false);

            // Close the input stream and release any system resources associated with it
            inputStream.close();
            
            Methods.showMessage("Information", "Success!", "Wine File Successfully Uploaded!");

        } catch (IOException ex) {
            Methods.showMessage("Fatal Error", "Something went wrong while writing the uploaded logo file!",
                    "See: " + ex.getMessage());
        }
    }

     public void storeFoodFile(UploadedFile file) throws IOException {
     Methods.preserveMessages();
        
        InputStream inputStream;

        try {
            /*
            InputStream is an abstract class, which is the superclass of all classes representing
            an input stream of bytes. Convert the uploaded file into an input stream of bytes.
             */
            inputStream = file.getInputstream();

            // Set the company's logo file name to company's stock symbol (ticker).png
            String foodFileName = foodMenuController.getSelected().getName() + ".png";

            /*
            Write the uploaded file's input stream of bytes into the file named
            logoFileName in the external directory Constants.ABSOLUTE_STORAGE_PATH
            using the method inputStreamToFile below.
             */
            File foodFile = inputStreamToFile(inputStream, foodFileName, true);

            // Close the input stream and release any system resources associated with it
            inputStream.close();
            
            Methods.showMessage("Information", "Success!", "Food File Successfully Uploaded!");

        } catch (IOException ex) {
            Methods.showMessage("Fatal Error", "Something went wrong while writing the uploaded logo file!",
                    "See: " + ex.getMessage());
        }
     }
    /*
     *******************************************************************************
     *   Write the Given Input Stream of Bytes into a File in External Directory   *
     *******************************************************************************
     */
    /**
     * @param inputStream of bytes to be written into file with name targetFilename
     * @param targetFilename
     * @return the created file targetFile
     * @throws IOException
     */
    private File inputStreamToFile(InputStream inputStream, String targetFilename, Boolean flag) throws IOException {

        /*
        inputStream.available() returns an estimate of the number of bytes that can be read from
        the inputStream without blocking by the next invocation of a method for this input stream.
        A memory buffer of bytes is created with the size of estimated number of bytes.
         */
        byte[] buffer = new byte[inputStream.available()];

        // Read the bytes of data from the given inputStream into the created memory buffer. 
        inputStream.read(buffer);

        /*
        Create targetFile with the given targetFilename in the external 
        directory Constants.ABSOLUTE_STORAGE_PATH
         */
         File targetFile = null;
        if(flag){
            targetFile = new File(Constants.ABSOLUTE_FOODSTORAGRE_PATH, targetFilename);
        }
        else if(!flag){
            targetFile = new File(Constants.ABSOLUTE_STORAGE_PATH, targetFilename);
        }
        // A file OutputStream is an output stream for writing data to a file
        OutputStream outputStream;

        /*
        FileOutputStream is intended for writing streams of raw bytes such as image data.
        Create a new FileOutputStream for writing to targetFile
         */
        outputStream = new FileOutputStream(targetFile);

        // Write the given inputStream from the memory buffer into the targetFile
        outputStream.write(buffer);

        // Close the output stream and release any system resources associated with it. 
        outputStream.close();

        // Return the created file targetFile
        return targetFile;
    }

}
