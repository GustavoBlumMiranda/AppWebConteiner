package commons.util;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

/**
 * 
 * 
 *
 * @param <T>
 */
public abstract class EntityClass<T extends Serializable> {

	/**
	 * Clona o objeto em quest�o
	 * 
	 * @param objeto
	 * @return clone do objeto
	 */
	@SuppressWarnings("unchecked")
	public  T clone() {
	    return SerializationUtils.clone((T)this);
	}

	
	public abstract Long getId();
	
}
