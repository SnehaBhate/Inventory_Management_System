package com.InventoryManagement.Exceptions;

//This Class is used to throw Exception.
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
 	
    String resourceName;
    String field;
    long fieldValue;
 
    //Parameterized Constructor
    public ResourceNotFoundException(String resourceName, String field, long fieldValue) {
	    
    	 super(String.format("%s not found with %s:%s",resourceName, field, fieldValue));
	     this.resourceName = resourceName;
	     this.field = field;
	     this.fieldValue = fieldValue;
    }
}