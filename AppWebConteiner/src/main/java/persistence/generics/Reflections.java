package persistence.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Reflections {

	protected Reflections() {
		// Impede instanciar subclasses desse tipo.
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the parametized type used with a concrete implementation of a class that accepts generics. Ex: If you
	 * declare
	 * <pre>
	 * public class SpecializedCollection implements Collection&#60;SpecializedType&#62; {
	 *   // ...
	 * }
	 * </pre>
	 * then the code <code>getGenericTypeArgument(SpecializedCollection.class , 0);</code> will return the type
	 * <code>SpecializedType</code>.
	 *
	 * @param type Base type to check for generic arguments
	 * @param idx  zero based index of the generic argument to get
	 * @param <T>  Type of the generic argument
	 * @return The class representing the type of the generic argument
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericTypeArgument(final Type type, final int idx) {
		ParameterizedType paramType;
		try {
			paramType = (ParameterizedType) type;
		} catch (ClassCastException cause) {
			return getGenericTypeArgument((Class<T>) type, idx);
		}

		return (Class<T>) paramType.getActualTypeArguments()[idx];
	}

	/**
	 * Return the parametized type used with a concrete implementation of a class that accepts generics. Ex: If you
	 * declare
	 * <pre>
	 * <code>
	 * public class SpecializedCollection implements Collection&#60;SpecializedType&#62; {
	 *   // ...
	 * }
	 * </code>
	 * </pre>
	 * then the code <code>getGenericTypeArgument(SpecializedCollection.class , 0);</code> will return the type
	 * <code>SpecializedType</code>.
	 *
	 * @param clazz Base type to check for generic arguments
	 * @param idx   zero based index of the generic argument to get
	 * @param <T>   Type of the generic argument
	 * @return The class representing the type of the generic argument
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericTypeArgument(final Class<?> clazz, final int idx) {
		final Type type = clazz.getGenericSuperclass();

		ParameterizedType paramType;
		try {
			paramType = (ParameterizedType) type;
		} catch (ClassCastException cause) {
			return getGenericTypeArgument((Class<T>) type, idx);
		}

		return (Class<T>) paramType.getActualTypeArguments()[idx];
	}

	}
