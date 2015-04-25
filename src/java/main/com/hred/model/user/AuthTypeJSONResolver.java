package com.hred.model.user;


import java.util.HashMap;

import org.codehaus.jackson.map.jsontype.impl.TypeNameIdResolver;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

import com.hred.model.Employee;
import com.hred.model.User;

public class AuthTypeJSONResolver extends TypeNameIdResolver {

 public AuthTypeJSONResolver() {
        super(getBaseType(), getTypeToId(), getIdToType());
	}
	
	public static JavaType getBaseType() {
		JavaType type = TypeFactory.type(AuthenticationInput.class);
		return type;
	}
	
	public static HashMap<String, String> getTypeToId() {
		HashMap<String, String> typeToId = new HashMap<String, String>();
		typeToId.put("com.hred.model.employee.DatabaseAuthenticationInput", Employee.AUTH_TYPE_REGULAR);
		return typeToId;
	}

	public static HashMap<String, JavaType> getIdToType() {
		HashMap<String, JavaType> idToType = new HashMap<String, JavaType>();
		idToType.put(Employee.AUTH_TYPE_REGULAR, TypeFactory.type(DefaultAuthenticationInput.class));
		return idToType;
	}


}
