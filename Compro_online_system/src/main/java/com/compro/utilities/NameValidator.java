/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Nesanet
 */
@FacesValidator("com.compro.utilities.NameValidator")
public class NameValidator implements Validator{
 
	private static final String EMAIL_PATTERN = "^[_A-Za-z]*$";
 
	private Pattern pattern;
	private Matcher matcher;
 
	public NameValidator(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
 
			FacesMessage msg = 
				new FacesMessage("Name validation failed.", 
						"Invalid Name format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}