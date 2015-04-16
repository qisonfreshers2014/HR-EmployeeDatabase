
package com.hred.handler.annotations;

/**
 * @author Nikitha
 *
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizeEntity {
	String entity();
	long[] roles();
	//String action();
}
