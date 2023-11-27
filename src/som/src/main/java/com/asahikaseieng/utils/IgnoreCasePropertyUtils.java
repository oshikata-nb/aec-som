/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.MappedPropertyDescriptor;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.collections.FastHashMap;

/**
 * IgnoreCasePropertyUtilsクラス(PropertyUtils改良版) <br>
 * プロパティ名の大文字/小文字を無視してコピーする機能へ変更.
 * 
 * @author jbd
 */
public final class IgnoreCasePropertyUtils {

	// ----------------------------------------------------- Manifest Constants

	/**
	 * The delimiter that preceeds the zero-relative subscript for an indexed.
	 * reference.
	 */
	public static final char INDEXED_DELIM = '[';

	/**
	 * The delimiter that follows the zero-relative subscript for an indexed.
	 * reference.
	 */
	public static final char INDEXED_DELIM2 = ']';

	/**
	 * The delimiter that preceeds the key of a mapped property.
	 */
	public static final char MAPPED_DELIM = '(';

	/**
	 * The delimiter that follows the key of a mapped property.
	 */
	public static final char MAPPED_DELIM2 = ')';

	/**
	 * The delimiter that separates the components of a nested reference.
	 */
	public static final char NESTED_DELIM = '.';

	// ------------------------------------------------------- Static Variables

	/**
	 * The debugging detail level for this component.
	 * @deprecated The <code>debug</code> static property is no longer used
	 */
	private static int debug; // =0;

	/**
	 * コンストラクタ.
	 */
	private IgnoreCasePropertyUtils() {
		super();
	}

	/**
	 * @deprecated The <code>debug</code> static property is no longer used
	 * @return debug
	 */
	public static int getDebug() {
		return debug;
	}

	/**
	 * @deprecated The <code>debug</code> static property is no longer used
	 * @param newDebug newDebug
	 */
	public static void setDebug(final int newDebug) {
		debug = newDebug;
	}

	/**
	 * The cache of PropertyDescriptor arrays for beans we have already
	 * introspected, keyed by the java.lang.Class of this object.
	 */
	private static FastHashMap descriptorsCache;

	private static FastHashMap mappedDescriptorsCache;

	static {
		descriptorsCache = new FastHashMap();
		descriptorsCache.setFast(true);
		mappedDescriptorsCache = new FastHashMap();
		mappedDescriptorsCache.setFast(true);
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Clear any cached property descriptors information for all classes loaded
	 * by any class loaders. This is useful in cases where class loaders are
	 * thrown away to implement class reloading.
	 */
	public static void clearDescriptors() {

		descriptorsCache.clear();
		mappedDescriptorsCache.clear();
		Introspector.flushCaches();

	}

	/**
	 * <p>
	 * Copy property values from the "origin" bean to the "destination" bean for
	 * all cases where the property names are the same (even though the actual
	 * getter and setter methods might have been customized via
	 * <code>BeanInfo</code> classes). No conversions are performed on the
	 * actual property values -- it is assumed that the values retrieved from
	 * the origin bean are assignment-compatible with the types expected by the
	 * destination bean.
	 * </p>
	 * 
	 * <p>
	 * If the origin "bean" is actually a <code>Map</code>, it is assumed to
	 * contain String-valued <strong>simple </strong> property names as the
	 * keys, pointing at the corresponding property values that will be set in
	 * the destination bean. <strong>Note </strong> that this method is intended
	 * to perform a "shallow copy" of the properties and so complex properties
	 * (for example, nested ones) will not be copied.
	 * </p>
	 * 
	 * @param dest Destination bean whose properties are modified
	 * @param orig Origin bean whose properties are retrieved
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if the
	 *                <code>dest</code> or <code>orig</code> argument is
	 *                null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 */
	public static void copyProperties(final Object dest, final Object orig)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}

		if (orig instanceof DynaBean) {
			DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if (dest instanceof DynaBean) {
					if (isWriteable(dest, name)) {
						Object value = ((DynaBean) orig).get(name);
						((DynaBean) dest).set(name, value);
					}
				} else {
					if (isWriteable(dest, name)) {
						Object value = ((DynaBean) orig).get(name);
						IgnoreCasePropertyUtils.setSimpleProperty(dest, name,
							value);
					}
				}
			}
		} else if (orig instanceof Map) {
			Iterator names = ((Map) orig).keySet().iterator();
			while (names.hasNext()) {
				String name = (String) names.next();
				if (dest instanceof DynaBean) {
					if (isWriteable(dest, name)) {
						Object value = ((Map) orig).get(name);
						((DynaBean) dest).set(name, value);
					}
				} else {
					if (isWriteable(dest, name)) {
						Object value = ((Map) orig).get(name);
						IgnoreCasePropertyUtils.setSimpleProperty(dest, name,
							value);
					}
				}
			}
		} else { /* if (orig is a standard JavaBean) */
			PropertyDescriptor[] origDescriptors = getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if (isReadable(orig, name)) {
					if (dest instanceof DynaBean) {
						if (isWriteable(dest, name)) {
							Object value = getSimpleProperty(orig, name);
							((DynaBean) dest).set(name, value);
						}
					} else {
						if (isWriteable(dest, name)) {
							Object value = getSimpleProperty(orig, name);
							setSimpleProperty(dest, name, value);
						}
					}
				}
			}
		}

	}

	/**
	 * <p>
	 * Return the entire set of properties for which the specified bean provides
	 * a read method. This map contains the unconverted property values for all
	 * properties for which a read method is provided (i.e. where the
	 * <code>getReadMethod()</code> returns non-null).
	 * </p>
	 * 
	 * <p>
	 * <strong>FIXME </strong>- Does not account for mapped properties.
	 * </p>
	 * 
	 * @param bean Bean whose properties are to be extracted
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 *                <code>bean</code> is null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return map map
	 */
	public static Map describe(final Object bean)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		Map<String, Object> description = new HashMap<String, Object>();
		if (bean instanceof DynaBean) {
			DynaProperty[] descriptors = ((DynaBean) bean).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				description.put(name, getProperty(bean, name));
			}
		} else {
			PropertyDescriptor[] descriptors = IgnoreCasePropertyUtils
					.getPropertyDescriptors(bean);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (descriptors[i].getReadMethod() != null) {
					description.put(name, getProperty(bean, name));
				}
			}
		}
		return description;
	}

	/**
	 * Return the value of the specified indexed property of the specified bean,
	 * with no type conversions. The zero-relative index of the required value
	 * must be included (in square brackets) as a suffix to the property name,
	 * or <code>IllegalArgumentException</code> will be thrown. In addition to
	 * supporting the JavaBeans specification, this method has been extended to
	 * support <code>List</code> objects as well.
	 * 
	 * @param bean Bean whose property is to be extracted
	 * @param name <code>propertyname[index]</code> of the property value to
	 * be extracted @ exception ArrayIndexOutOfBoundsException if the specified
	 *            index is outside the valid range for the underlying array
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 *                <code>bean</code> or <code>name</code> is null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return object object
	 */
	public static Object getIndexedProperty(final Object bean, final String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Identify the index of the requested individual property
		int delim = name.indexOf(INDEXED_DELIM);
		int delim2 = name.indexOf(INDEXED_DELIM2);
		if ((delim < 0) || (delim2 <= delim)) {
			throw new IllegalArgumentException("Invalid indexed property '"
					+ name + "'");
		}
		int index = -1;
		try {
			String subscript = name.substring(delim + 1, delim2);
			index = Integer.parseInt(subscript);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid indexed property '"
					+ name + "'");
		}
		String newName = name.substring(0, delim);

		// Request the specified indexed property value
		return getIndexedProperty(bean, newName, index);

	}

	/**
	 * Return the value of the specified indexed property of the specified bean,
	 * with no type conversions. In addition to supporting the JavaBeans
	 * specification, this method has been extended to support <code>List</code>
	 * objects as well.
	 * 
	 * @param bean Bean whose property is to be extracted
	 * @param name Simple property name of the property value to be extracted
	 * @param index Index of the property value to be extracted @ exception
	 *            ArrayIndexOutOfBoundsException if the specified index is
	 *            outside the valid range for the underlying array
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 *                <code>bean</code> or <code>name</code> is null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return object object
	 */
	public static Object getIndexedProperty(final Object bean,
			final String name, final int index) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Handle DynaBean instances specially
		if (bean instanceof DynaBean) {
			DynaProperty descriptor = ((DynaBean) bean).getDynaClass()
					.getDynaProperty(name);
			if (descriptor == null) {
				throw new NoSuchMethodException("Unknown property '" + name
						+ "'");
			}
			return ((DynaBean) bean).get(name, index);
		}

		// Retrieve the property descriptor for the specified property
		PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if (descriptor == null) {
			throw new NoSuchMethodException("Unknown property '" + name + "'");
		}

		// Call the indexed getter method if there is one
		if (descriptor instanceof IndexedPropertyDescriptor) {
			Method readMethod = ((IndexedPropertyDescriptor) descriptor)
					.getIndexedReadMethod();
			if (readMethod != null) {
				Object[] subscript = new Object[1];
				subscript[0] = new Integer(index);
				try {
					return readMethod.invoke(bean, subscript);
				} catch (InvocationTargetException e) {
					if (e.getTargetException() instanceof ArrayIndexOutOfBoundsException) {
						throw (ArrayIndexOutOfBoundsException) e
								.getTargetException();
					}
					throw e;
				}
			}
		}

		// Otherwise, the underlying property must be an array
		Method readMethod = getReadMethod(descriptor);
		if (readMethod == null) {
			throw new NoSuchMethodException("Property '" + name
					+ "' has no getter method");
		}

		// Call the property getter and return the value
		Object value = readMethod.invoke(bean, new Object[0]);
		if (!value.getClass().isArray()) {
			if (!(value instanceof java.util.List)) {
				throw new IllegalArgumentException("Property '" + name
						+ "' is not indexed");
			}
			// get the List's value
			return ((java.util.List) value).get(index);
		}
		// get the array's value
		return Array.get(value, index);

	}

	/**
	 * Return the value of the specified mapped property of the specified bean,
	 * with no type conversions. The key of the required value must be included
	 * (in brackets) as a suffix to the property name, or
	 * <code>IllegalArgumentException</code> will be thrown.
	 * 
	 * @param bean Bean whose property is to be extracted
	 * @param name <code>propertyname(key)</code> of the property value to be
	 *            extracted
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 *                the property accessor method
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return object object
	 */
	public static Object getMappedProperty(final Object bean, final String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Identify the index of the requested individual property
		int delim = name.indexOf(MAPPED_DELIM);
		int delim2 = name.indexOf(MAPPED_DELIM2);
		if ((delim < 0) || (delim2 <= delim)) {
			throw new IllegalArgumentException("Invalid mapped property '"
					+ name + "'");
		}

		// Isolate the name and the key
		String key = name.substring(delim + 1, delim2);
		String newName = name.substring(0, delim);

		// Request the specified indexed property value
		return getMappedProperty(bean, newName, key);

	}

	/**
	 * Return the value of the specified mapped property of the specified bean,
	 * with no type conversions.
	 * 
	 * @param bean Bean whose property is to be extracted
	 * @param name Mapped property name of the property value to be extracted
	 * @param key Key of the property value to be extracted
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 *                the property accessor method
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return object oject
	 */
	public static Object getMappedProperty(final Object bean,
			final String name, final String key) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}
		if (key == null) {
			throw new IllegalArgumentException("No key specified");
		}

		// Handle DynaBean instances specially
		if (bean instanceof DynaBean) {
			DynaProperty descriptor = ((DynaBean) bean).getDynaClass()
					.getDynaProperty(name);
			if (descriptor == null) {
				throw new NoSuchMethodException("Unknown property '" + name
						+ "'");
			}
			return ((DynaBean) bean).get(name, key);
		}

		Object result = null;

		// Retrieve the property descriptor for the specified property
		PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if (descriptor == null) {
			throw new NoSuchMethodException("Unknown property '" + name + "'");
		}

		if (descriptor instanceof MappedPropertyDescriptor) {
			// Call the keyed getter method if there is one
			Method readMethod = ((MappedPropertyDescriptor) descriptor)
					.getMappedReadMethod();
			if (readMethod != null) {
				Object[] keyArray = new Object[1];
				keyArray[0] = key;
				result = readMethod.invoke(bean, keyArray);
			} else {
				throw new NoSuchMethodException("Property '" + name
						+ "' has no mapped getter method");
			}
		} else {
			/* means that the result has to be retrieved from a map */
			Method readMethod = descriptor.getReadMethod();
			if (readMethod != null) {
				Object invokeResult = readMethod.invoke(bean, new Object[0]);
				/* test and fetch from the map */
				if (invokeResult instanceof java.util.Map) {
					result = ((java.util.Map) invokeResult).get(key);
				}
			} else {
				throw new NoSuchMethodException("Property '" + name
						+ "' has no mapped getter method");
			}
		}
		return result;

	}

	/**
	 * <p>
	 * Return the mapped property descriptors for this bean class.
	 * </p>
	 * 
	 * <p>
	 * <strong>FIXME </strong>- Does not work with DynaBeans.
	 * </p>
	 * 
	 * @param beanClass Bean class to be introspected
	 * @return fastHashMap fasHashMap
	 */
	public static FastHashMap getMappedPropertyDescriptors(final Class beanClass) {

		if (beanClass == null) {
			return null;
		}

		// Look up any cached descriptors for this bean class
		return (FastHashMap) mappedDescriptorsCache.get(beanClass);

	}

	/**
	 * <p>
	 * Return the mapped property descriptors for this bean.
	 * </p>
	 * 
	 * <p>
	 * <strong>FIXME </strong>- Does not work with DynaBeans.
	 * </p>
	 * 
	 * @param bean Bean to be introspected
	 * @return fastHashMap fastHashMap
	 */
	public static FastHashMap getMappedPropertyDescriptors(final Object bean) {

		if (bean == null) {
			return null;
		}
		return getMappedPropertyDescriptors(bean.getClass());

	}

	/**
	 * Return the value of the (possibly nested) property of the specified name,
	 * for the specified bean, with no type conversions.
	 * 
	 * @param bean Bean whose property is to be extracted
	 * @param name Possibly nested name of the property to be extracted
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 * <code>bean</code> or <code>name</code> is null @ exception
	 *                IllegalArgumentException if a nested reference to a
	 *                property returns null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return object object
	 */
	public static Object getNestedProperty(final Object bean, final String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		int indexOfIndexedDelim = -1;
		int indexOfMappedDelim = -1;
		int indexOfMappedDelim2 = -1;
		int indexOfNestedDelim = -1;
		String newName = name;
		Object newBean = bean;
		while (true) {
			indexOfNestedDelim = newName.indexOf(NESTED_DELIM);
			indexOfMappedDelim = newName.indexOf(MAPPED_DELIM);
			indexOfMappedDelim2 = newName.indexOf(MAPPED_DELIM2);
			if (0 <= indexOfMappedDelim2
					&& 0 <= indexOfMappedDelim
					&& (indexOfNestedDelim < 0 || indexOfMappedDelim < indexOfNestedDelim)) {
				indexOfNestedDelim = newName.indexOf(NESTED_DELIM,
					indexOfMappedDelim2);
			} else {
				indexOfNestedDelim = newName.indexOf(NESTED_DELIM);
			}
			if (indexOfNestedDelim < 0) {
				break;
			}
			String next = newName.substring(0, indexOfNestedDelim);
			indexOfIndexedDelim = next.indexOf(INDEXED_DELIM);
			indexOfMappedDelim = next.indexOf(MAPPED_DELIM);
			if (newBean instanceof Map) {
				newBean = ((Map) newBean).get(next);
			} else if (0 <= indexOfMappedDelim) {
				newBean = getMappedProperty(newBean, next);
			} else if (0 <= indexOfIndexedDelim) {
				newBean = getIndexedProperty(newBean, next);
			} else {
				newBean = getSimpleProperty(newBean, next);
			}
			if (newBean == null) {
				throw new IllegalArgumentException("Null property value for '"
						+ newName.substring(0, indexOfNestedDelim) + "'");
			}
			newName = newName.substring(indexOfNestedDelim + 1);
		}

		indexOfIndexedDelim = newName.indexOf(INDEXED_DELIM);
		indexOfMappedDelim = newName.indexOf(MAPPED_DELIM);

		if (newBean instanceof Map) {
			newBean = ((Map) newBean).get(newName);
		} else if (0 <= indexOfMappedDelim) {
			newBean = getMappedProperty(newBean, newName);
		} else if (0 <= indexOfIndexedDelim) {
			newBean = getIndexedProperty(newBean, newName);
		} else {
			newBean = getSimpleProperty(newBean, newName);
		}
		return newBean;

	}

	/**
	 * Return the value of the specified property of the specified bean, no
	 * matter which property reference format is used, with no type conversions.
	 * 
	 * @param bean Bean whose property is to be extracted
	 * @param name Possibly indexed and/or nested name of the property to be
	 *            extracted
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 *                <code>bean</code> or <code>name</code> is null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return object object
	 */
	public static Object getProperty(final Object bean, final String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return getNestedProperty(bean, name);

	}

	/**
	 * <p>
	 * Retrieve the property descriptor for the specified property of the
	 * specified bean, or return <code>null</code> if there is no such
	 * descriptor. This method resolves indexed and nested property references
	 * in the same manner as other methods in this class, except that if the
	 * last (or only) name element is indexed, the descriptor for the last
	 * resolved property itself is returned.
	 * </p>
	 * 
	 * <p>
	 * <strong>FIXME </strong>- Does not work with DynaBeans.
	 * </p>
	 * 
	 * @param bean Bean for which a property descriptor is requested
	 * @param name Possibly indexed and/or nested name of the property for which
	 *            a property descriptor is requested
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 * <code>bean</code> or <code>name</code> is null @ exception
	 *                IllegalArgumentException if a nested reference to a
	 *                property returns null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return propertyDescriptor propertyDescriptor
	 */
	public static PropertyDescriptor getPropertyDescriptor(final Object bean,
			final String name) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Resolve nested references
		Object newBean = bean;
		String newName = name.toLowerCase();
		while (true) {
			int period = newName.indexOf(NESTED_DELIM);
			if (period < 0) {
				break;
			}
			String next = newName.substring(0, period);
			int indexOfIndexedDelim = next.indexOf(INDEXED_DELIM);
			int indexOfMappedDelim = next.indexOf(MAPPED_DELIM);
			if (0 <= indexOfMappedDelim
					&& (indexOfIndexedDelim < 0 || indexOfMappedDelim < indexOfIndexedDelim)) {
				newBean = getMappedProperty(newBean, next);
			} else {
				if (0 <= indexOfIndexedDelim) {
					newBean = getIndexedProperty(newBean, next);
				} else {
					newBean = getSimpleProperty(newBean, next);
				}
			}
			if (newBean == null) {
				throw new IllegalArgumentException("Null property value for '"
						+ newName.substring(0, period) + "'");
			}
			newName = newName.substring(period + 1);
		}

		// Remove any subscript from the final name value
		int left = newName.indexOf(INDEXED_DELIM);
		if (0 <= left) {
			newName = newName.substring(0, left);
		}
		left = newName.indexOf(MAPPED_DELIM);
		if (0 <= left) {
			newName = newName.substring(0, left);
		}

		// Look up and return this property from our cache
		// creating and adding it to the cache if not found.
		if ((newBean == null) || (newName == null)) {
			return null;
		}

		PropertyDescriptor[] descriptors = getPropertyDescriptors(newBean);
		if (descriptors != null) {
			for (int i = 0; i < descriptors.length; i++) {
				// 2004.01.09 ここでプロパティ名を小文字に変換し、一致させる by matsui
				if (newName.equals(descriptors[i].getName().toLowerCase())) {
					return descriptors[i];
				}
			}
		}

		PropertyDescriptor result = null;
		FastHashMap mappedDescriptors = getMappedPropertyDescriptors(newBean);
		if (mappedDescriptors == null) {
			mappedDescriptors = new FastHashMap();
			mappedDescriptors.setFast(true);
			mappedDescriptorsCache.put(newBean.getClass(), mappedDescriptors);
		}
		result = (PropertyDescriptor) mappedDescriptors.get(newName);
		if (result == null) {
			// not found, try to create it
			try {
				result = new MappedPropertyDescriptor(newName, newBean
						.getClass());
			} catch (IntrospectionException ie) {
				ie.getClass();
			}
			if (result != null) {
				mappedDescriptors.put(newName, result);
			}
		}
		return result;

	}

	/**
	 * <p>
	 * Retrieve the property descriptors for the specified class, introspecting
	 * and caching them the first time a particular bean class is encountered.
	 * </p>
	 * 
	 * <p>
	 * <strong>FIXME </strong>- Does not work with DynaBeans.
	 * </p>
	 * 
	 * @param beanClass Bean class for which property descriptors are requested @ exception
	 *            IllegalArgumentException if <code>beanClass</code> is null
	 * @return PropertyDescriptor[] PropertyDescriptor[]
	 */
	public static PropertyDescriptor[] getPropertyDescriptors(
			final Class beanClass) {

		if (beanClass == null) {
			throw new IllegalArgumentException("No bean class specified");
		}

		// Look up any cached descriptors for this bean class
		PropertyDescriptor[] descriptors = null;
		descriptors = (PropertyDescriptor[]) descriptorsCache.get(beanClass);
		if (descriptors != null) {
			return descriptors;
		}

		// Introspect the bean and cache the generated descriptors
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(beanClass);
		} catch (IntrospectionException e) {
			return new PropertyDescriptor[0];
		}
		descriptors = beanInfo.getPropertyDescriptors();
		if (descriptors == null) {
			descriptors = new PropertyDescriptor[0];
		}
		descriptorsCache.put(beanClass, descriptors);
		return descriptors;

	}

	/**
	 * <p>
	 * Retrieve the property descriptors for the specified bean, introspecting
	 * and caching them the first time a particular bean class is encountered.
	 * </p>
	 * 
	 * <p>
	 * <strong>FIXME </strong>- Does not work with DynaBeans.
	 * </p>
	 * 
	 * @param bean Bean for which property descriptors are requested @ exception
	 *            IllegalArgumentException if <code>bean</code> is null
	 * @return PropertyDescriptor[] PropertyDescriptor[]
	 */
	public static PropertyDescriptor[] getPropertyDescriptors(final Object bean) {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		return getPropertyDescriptors(bean.getClass());
	}

	/**
	 * <p>
	 * Return the Java Class repesenting the property editor class that has been
	 * registered for this property (if any). This method follows the same name
	 * resolution rules used by <code>getPropertyDescriptor()</code>, so if
	 * the last element of a name reference is indexed, the property editor for
	 * the underlying property's class is returned.
	 * </p>
	 * 
	 * <p>
	 * Note that <code>null</code> will be returned if there is no property,
	 * or if there is no registered property editor class. Because this return
	 * value is ambiguous, you should determine the existence of the property
	 * itself by other means.
	 * </p>
	 * 
	 * <p>
	 * <strong>FIXME </strong>- Does not work with DynaBeans.
	 * </p>
	 * 
	 * @param bean Bean for which a property descriptor is requested
	 * @param name Possibly indexed and/or nested name of the property for which
	 *            a property descriptor is requested
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 * <code>bean</code> or <code>name</code> is null @ exception
	 *                IllegalArgumentException if a nested reference to a
	 *                property returns null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return class class
	 */
	public static Class getPropertyEditorClass(final Object bean,
			final String name) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if (descriptor != null) {
			return descriptor.getPropertyEditorClass();
		}
		return null;

	}

	/**
	 * Return the Java Class representing the property type of the specified
	 * property, or <code>null</code> if there is no such property for the
	 * specified bean. This method follows the same name resolution rules used
	 * by <code>getPropertyDescriptor()</code>, so if the last element of a
	 * name reference is indexed, the type of the property itself will be
	 * returned. If the last (or only) element has no property with the
	 * specified name, <code>null</code> is returned.
	 * 
	 * @param bean Bean for which a property descriptor is requested
	 * @param name Possibly indexed and/or nested name of the property for which
	 *            a property descriptor is requested
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 * <code>bean</code> or <code>name</code> is null @ exception
	 *                IllegalArgumentException if a nested reference to a
	 *                property returns null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return class class
	 */
	public static Class getPropertyType(final Object bean, final String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Special handling for DynaBeans
		if (bean instanceof DynaBean) {
			DynaProperty descriptor = ((DynaBean) bean).getDynaClass()
					.getDynaProperty(name);
			if (descriptor == null) {
				return null;
			}
			Class type = descriptor.getType();
			if (type == null) {
				return null;
			} else if (type.isArray()) {
				return type.getComponentType();
			} else {
				return type;
			}
		}

		PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if (descriptor == null) {
			return null;
		} else if (descriptor instanceof IndexedPropertyDescriptor) {
			return ((IndexedPropertyDescriptor) descriptor)
					.getIndexedPropertyType();
		} else if (descriptor instanceof MappedPropertyDescriptor) {
			return ((MappedPropertyDescriptor) descriptor)
					.getMappedPropertyType();
		} else {
			return descriptor.getPropertyType();
		}

	}

	/**
	 * <p>
	 * Return an accessible property getter method for this property, if there
	 * is one; otherwise return <code>null</code>.
	 * </p>
	 * 
	 * <p>
	 * <strong>FIXME </strong>- Does not work with DynaBeans.
	 * </p>
	 * 
	 * @param descriptor Property descriptor to return a getter for
	 * @return method method
	 */
	public static Method getReadMethod(final PropertyDescriptor descriptor) {

		return MethodUtils.getAccessibleMethod(descriptor.getReadMethod());

	}

	/**
	 * Return the value of the specified simple property of the specified bean,
	 * with no type conversions.
	 * 
	 * @param bean Bean whose property is to be extracted
	 * @param name Name of the property to be extracted
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 * <code>bean</code> or <code>name</code> is null @ exception
	 *                IllegalArgumentException if the property name is nested or
	 *                indexed
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 * @return object object
	 */
	public static Object getSimpleProperty(final Object bean, final String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Validate the syntax of the property name
		if (0 <= name.indexOf(NESTED_DELIM)) {
			throw new IllegalArgumentException(
					"Nested property names are not allowed");
		} else if (0 <= name.indexOf(INDEXED_DELIM)) {
			throw new IllegalArgumentException(
					"Indexed property names are not allowed");
		} else if (0 <= name.indexOf(MAPPED_DELIM)) {
			throw new IllegalArgumentException(
					"Mapped property names are not allowed");
		}

		// Handle DynaBean instances specially
		if (bean instanceof DynaBean) {
			DynaProperty descriptor = ((DynaBean) bean).getDynaClass()
					.getDynaProperty(name);
			if (descriptor == null) {
				throw new NoSuchMethodException("Unknown property '" + name
						+ "'");
			}
			return ((DynaBean) bean).get(name);
		}

		// Retrieve the property getter method for the specified property
		PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if (descriptor == null) {
			throw new NoSuchMethodException("Unknown property '" + name + "'");
		}
		Method readMethod = getReadMethod(descriptor);
		if (readMethod == null) {
			throw new NoSuchMethodException("Property '" + name
					+ "' has no getter method");
		}

		// Call the property getter and return the value
		Object value = readMethod.invoke(bean, new Object[0]);
		return value;

	}

	/**
	 * <p>
	 * Return an accessible property setter method for this property, if there
	 * is one; otherwise return <code>null</code>.
	 * </p>
	 * 
	 * <p>
	 * <strong>FIXME </strong>- Does not work with DynaBeans.
	 * </p>
	 * 
	 * @param descriptor Property descriptor to return a setter for
	 * @return method method
	 */
	public static Method getWriteMethod(final PropertyDescriptor descriptor) {

		return MethodUtils.getAccessibleMethod(descriptor.getWriteMethod());

	}

	/**
	 * <p>
	 * Return <code>true</code> if the specified property name identifies a
	 * readable property on the specified bean; otherwise, return
	 * <code>false</code>.
	 * 
	 * @param bean Bean to be examined (may be a {@link DynaBean}
	 * @param name Property name to be evaluated @ exception
	 *            IllegalArgumentException if <code>bean</code> or
	 *            <code>name</code> is <code>null</code>
	 * 
	 * @since BeanUtils 1.6
	 * @return boolean boolean
	 */
	public static boolean isReadable(final Object bean, final String name) {

		// Validate method parameters
		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Return the requested result
		if (bean instanceof DynaBean) {
			// All DynaBean properties are readable
			return ((DynaBean) bean).getDynaClass().getDynaProperty(name) != null;
		}
		try {
			PropertyDescriptor desc = getPropertyDescriptor(bean, name);
			if (desc != null) {
				Method readMethod = desc.getReadMethod();
				if ((readMethod == null)
						&& (desc instanceof IndexedPropertyDescriptor)) {
					readMethod = ((IndexedPropertyDescriptor) desc)
							.getIndexedReadMethod();
				}
				return readMethod != null;
			}
			return false;
		} catch (IllegalAccessException e) {
			return false;
		} catch (InvocationTargetException e) {
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

	/**
	 * <p>
	 * Return <code>true</code> if the specified property name identifies a
	 * writeable property on the specified bean; otherwise, return
	 * <code>false</code>.
	 * 
	 * @param bean Bean to be examined (may be a {@link DynaBean}
	 * @param name Property name to be evaluated @ exception
	 *            IllegalPointerException if <code>bean</code> or
	 *            <code>name</code> is <code>null</code>
	 * 
	 * @since BeanUtils 1.6
	 * @return boolean boolean
	 */
	public static boolean isWriteable(final Object bean, final String name) {

		// Validate method parameters
		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Return the requested result
		if (bean instanceof DynaBean) {
			// All DynaBean properties are writeable
			return ((DynaBean) bean).getDynaClass().getDynaProperty(name) != null;
		}
		try {
			PropertyDescriptor desc = getPropertyDescriptor(bean, name);
			if (desc != null) {
				Method writeMethod = desc.getWriteMethod();
				if ((writeMethod == null)
						&& (desc instanceof IndexedPropertyDescriptor)) {
					writeMethod = ((IndexedPropertyDescriptor) desc)
							.getIndexedWriteMethod();
				}
				return writeMethod != null;
			}
			return false;
		} catch (IllegalAccessException e) {
			return false;
		} catch (InvocationTargetException e) {
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

	/**
	 * Set the value of the specified indexed property of the specified bean,
	 * with no type conversions. The zero-relative index of the required value
	 * must be included (in square brackets) as a suffix to the property name,
	 * or <code>IllegalArgumentException</code> will be thrown. In addition to
	 * supporting the JavaBeans specification, this method has been extended to
	 * support <code>List</code> objects as well.
	 * 
	 * @param bean Bean whose property is to be modified
	 * @param name <code>propertyname[index]</code> of the property value to
	 *            be modified
	 * @param value Value to which the specified property element should be set @ exception
	 *            ArrayIndexOutOfBoundsException if the specified index is
	 *            outside the valid range for the underlying array
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 *                <code>bean</code> or <code>name</code> is null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 */
	public static void setIndexedProperty(final Object bean, final String name,
			final Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Identify the index of the requested individual property
		int delim = name.indexOf(INDEXED_DELIM);
		int delim2 = name.indexOf(INDEXED_DELIM2);
		if ((delim < 0) || (delim2 <= delim)) {
			throw new IllegalArgumentException("Invalid indexed property '"
					+ name + "'");
		}
		int index = -1;
		try {
			String subscript = name.substring(delim + 1, delim2);
			index = Integer.parseInt(subscript);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid indexed property '"
					+ name + "'");
		}
		String newName = name.substring(0, delim);

		// Set the specified indexed property value
		setIndexedProperty(bean, newName, index, value);

	}

	/**
	 * Set the value of the specified indexed property of the specified bean,
	 * with no type conversions. In addition to supporting the JavaBeans
	 * specification, this method has been extended to support <code>List</code>
	 * objects as well.
	 * 
	 * @param bean Bean whose property is to be set
	 * @param name Simple property name of the property value to be set
	 * @param index Index of the property value to be set
	 * @param value Value to which the indexed property element is to be set @ exception
	 *            ArrayIndexOutOfBoundsException if the specified index is
	 *            outside the valid range for the underlying array
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 *                <code>bean</code> or <code>name</code> is null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 */
	@SuppressWarnings("unchecked")
	public static void setIndexedProperty(final Object bean, final String name,
			final int index, final Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Handle DynaBean instances specially
		if (bean instanceof DynaBean) {
			DynaProperty descriptor = ((DynaBean) bean).getDynaClass()
					.getDynaProperty(name);
			if (descriptor == null) {
				throw new NoSuchMethodException("Unknown property '" + name
						+ "'");
			}
			((DynaBean) bean).set(name, index, value);
			return;
		}

		// Retrieve the property descriptor for the specified property
		PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if (descriptor == null) {
			throw new NoSuchMethodException("Unknown property '" + name + "'");
		}

		// Call the indexed setter method if there is one
		if (descriptor instanceof IndexedPropertyDescriptor) {
			Method writeMethod = ((IndexedPropertyDescriptor) descriptor)
					.getIndexedWriteMethod();
			if (writeMethod != null) {
				Object[] subscript = new Object[2];
				subscript[0] = new Integer(index);
				subscript[1] = value;
				try {
					writeMethod.invoke(bean, subscript);
				} catch (InvocationTargetException e) {
					if (e.getTargetException() instanceof ArrayIndexOutOfBoundsException) {
						throw (ArrayIndexOutOfBoundsException) e
								.getTargetException();
					}
					throw e;
				}
				return;
			}
		}

		// Otherwise, the underlying property must be an array or a list
		Method readMethod = descriptor.getReadMethod();
		if (readMethod == null) {
			throw new NoSuchMethodException("Property '" + name
					+ "' has no getter method");
		}

		// Call the property getter to get the array or list
		Object array = readMethod.invoke(bean, new Object[0]);
		if (!array.getClass().isArray()) {
			if (array instanceof List) {
				// Modify the specified value in the List
				((List) array).set(index, value);
			} else {
				throw new IllegalArgumentException("Property '" + name
						+ "' is not indexed");
			}
		} else {
			// Modify the specified value in the array
			Array.set(array, index, value);
		}

	}

	/**
	 * Set the value of the specified mapped property of the specified bean,
	 * with no type conversions. The key of the value to set must be included
	 * (in brackets) as a suffix to the property name, or
	 * <code>IllegalArgumentException</code> will be thrown.
	 * 
	 * @param bean Bean whose property is to be set
	 * @param name <code>propertyname(key)</code> of the property value to be
	 *            set
	 * @param value The property value to be set
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 *                the property accessor method
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 */
	public static void setMappedProperty(final Object bean, final String name,
			final Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Identify the index of the requested individual property
		int delim = name.indexOf(MAPPED_DELIM);
		int delim2 = name.indexOf(MAPPED_DELIM2);
		if ((delim < 0) || (delim2 <= delim)) {
			throw new IllegalArgumentException("Invalid mapped property '"
					+ name + "'");
		}

		// Isolate the name and the key
		String key = name.substring(delim + 1, delim2);
		String newName = name.substring(0, delim);

		// Request the specified indexed property value
		setMappedProperty(bean, newName, key, value);

	}

	/**
	 * Set the value of the specified mapped property of the specified bean,
	 * with no type conversions.
	 * 
	 * @param bean Bean whose property is to be set
	 * @param name Mapped property name of the property value to be set
	 * @param key Key of the property value to be set
	 * @param value The property value to be set
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 *                the property accessor method
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 */
	@SuppressWarnings("unchecked")
	public static void setMappedProperty(final Object bean, final String name,
			final String key, final Object value)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}
		if (key == null) {
			throw new IllegalArgumentException("No key specified");
		}

		// Handle DynaBean instances specially
		if (bean instanceof DynaBean) {
			DynaProperty descriptor = ((DynaBean) bean).getDynaClass()
					.getDynaProperty(name);
			if (descriptor == null) {
				throw new NoSuchMethodException("Unknown property '" + name
						+ "'");
			}
			((DynaBean) bean).set(name, key, value);
			return;
		}

		// Retrieve the property descriptor for the specified property
		PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if (descriptor == null) {
			throw new NoSuchMethodException("Unknown property '" + name + "'");
		}

		if (descriptor instanceof MappedPropertyDescriptor) {
			// Call the keyed setter method if there is one
			Method mappedWriteMethod = ((MappedPropertyDescriptor) descriptor)
					.getMappedWriteMethod();
			if (mappedWriteMethod != null) {
				Object[] params = new Object[2];
				params[0] = key;
				params[1] = value;
				mappedWriteMethod.invoke(bean, params);
			} else {
				throw new NoSuchMethodException("Property '" + name
						+ "' has no mapped setter method");
			}
		} else {
			/* means that the result has to be retrieved from a map */
			Method readMethod = descriptor.getReadMethod();
			if (readMethod != null) {
				Object invokeResult = readMethod.invoke(bean, new Object[0]);
				/* test and fetch from the map */
				if (invokeResult instanceof java.util.Map) {
					((java.util.Map) invokeResult).put(key, value);
				}
			} else {
				throw new NoSuchMethodException("Property '" + name
						+ "' has no mapped getter method");
			}
		}

	}

	/**
	 * Set the value of the (possibly nested) property of the specified name,
	 * for the specified bean, with no type conversions.
	 * 
	 * @param bean Bean whose property is to be modified
	 * @param name Possibly nested name of the property to be modified
	 * @param value Value to which the property is to be set
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 * <code>bean</code> or <code>name</code> is null @ exception
	 *                IllegalArgumentException if a nested reference to a
	 *                property returns null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 */
	@SuppressWarnings("unchecked")
	public static void setNestedProperty(final Object bean, final String name,
			final Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		int indexOfIndexedDelim = -1;
		int indexOfMappedDelim = -1;
		Object newBean = bean;
		String newName = name;
		while (true) {
			int delim = newName.indexOf(NESTED_DELIM);
			if (delim < 0) {
				break;
			}
			String next = newName.substring(0, delim);
			indexOfIndexedDelim = next.indexOf(INDEXED_DELIM);
			indexOfMappedDelim = next.indexOf(MAPPED_DELIM);
			if (newBean instanceof Map) {
				newBean = ((Map) newBean).get(next);
			} else if (0 <= indexOfMappedDelim) {
				newBean = getMappedProperty(newBean, next);
			} else if (0 <= indexOfIndexedDelim) {
				newBean = getIndexedProperty(newBean, next);
			} else {
				newBean = getSimpleProperty(newBean, next);
			}
			if (newBean == null) {
				throw new IllegalArgumentException("Null property value for '"
						+ newName.substring(0, delim) + "'");
			}
			newName = newName.substring(delim + 1);
		}

		indexOfIndexedDelim = newName.indexOf(INDEXED_DELIM);
		indexOfMappedDelim = newName.indexOf(MAPPED_DELIM);

		if (newBean instanceof Map) {
			// check to see if the class has a standard property
			PropertyDescriptor descriptor = getPropertyDescriptor(newBean,
				newName);
			if (descriptor == null) {
				// no - then put the value into the map
				((Map) newBean).put(newName, value);
			} else {
				// yes - use that instead
				setSimpleProperty(newBean, newName, value);
			}
		} else if (0 <= indexOfMappedDelim) {
			setMappedProperty(newBean, newName, value);
		} else if (0 <= indexOfIndexedDelim) {
			setIndexedProperty(newBean, newName, value);
		} else {
			setSimpleProperty(newBean, newName, value);
		}

	}

	/**
	 * Set the value of the specified property of the specified bean, no matter
	 * which property reference format is used, with no type conversions.
	 * 
	 * @param bean Bean whose property is to be modified
	 * @param name Possibly indexed and/or nested name of the property to be
	 *            modified
	 * @param value Value to which this property is to be set
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 *                <code>bean</code> or <code>name</code> is null
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 */
	public static void setProperty(final Object bean, final String name,
			final Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		setNestedProperty(bean, name, value);

	}

	/**
	 * Set the value of the specified simple property of the specified bean,
	 * with no type conversions.
	 * 
	 * @param bean Bean whose property is to be modified
	 * @param name Name of the property to be modified
	 * @param value Value to which the property should be set
	 * 
	 * @exception IllegalAccessException if the caller does not have access to
	 * the property accessor method @ exception IllegalArgumentException if
	 * <code>bean</code> or <code>name</code> is null @ exception
	 *                IllegalArgumentException if the property name is nested or
	 *                indexed
	 * @exception InvocationTargetException if the property accessor method
	 *                throws an exception
	 * @exception NoSuchMethodException if an accessor method for this propety
	 *                cannot be found
	 */
	public static void setSimpleProperty(final Object bean, final String name,
			final Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}

		// Validate the syntax of the property name
		if (0 <= name.indexOf(NESTED_DELIM)) {
			throw new IllegalArgumentException(
					"Nested property names are not allowed");
		} else if (0 <= name.indexOf(INDEXED_DELIM)) {
			throw new IllegalArgumentException(
					"Indexed property names are not allowed");
		} else if (0 <= name.indexOf(MAPPED_DELIM)) {
			throw new IllegalArgumentException(
					"Mapped property names are not allowed");
		}

		// Handle DynaBean instances specially
		if (bean instanceof DynaBean) {
			DynaProperty descriptor = ((DynaBean) bean).getDynaClass()
					.getDynaProperty(name);
			if (descriptor == null) {
				throw new NoSuchMethodException("Unknown property '" + name
						+ "'");
			}
			((DynaBean) bean).set(name, value);
			return;
		}

		// Retrieve the property setter method for the specified property
		PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if (descriptor == null) {
			throw new NoSuchMethodException("Unknown property '" + name + "'");
		}
		Method writeMethod = getWriteMethod(descriptor);
		if (writeMethod == null) {
			throw new NoSuchMethodException("Property '" + name
					+ "' has no setter method");
		}

		// Call the property setter method
		Object[] values = new Object[1];
		values[0] = value;
		writeMethod.invoke(bean, values);

	}
}
