/*
 * Created by Sharvari Chougule on 2019.05.07  * 
 * Copyright Â© 2019 Sharvari Chougule. All rights reserved. * 
 */
package edu.vt.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ADMIN
 */
@FacesValidator("yearofManufactureValidator")
public class YearofManufactureValidator implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String yom = (String) value;

        if (yom == null || yom.isEmpty()) {
            // Do not take any action. 
            // The required="true" in the XHTML file will catch this and produce an error message.
            return;
        }
        
        
       String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
       if (!yom.matches(regex)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Year of Manufacture Failed!", "Year of Manufacture should consist of only digits!"));
        
    }
       
       if(Integer.parseInt(yom)>2019)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Year of Manufacture Failed!", "Year of Manufacture is invalid!")); 
       }
       
       
       
    
}
}
