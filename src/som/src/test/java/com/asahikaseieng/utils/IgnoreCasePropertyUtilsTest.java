/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;

/**
 * IgnoreCasePropertyUtilsのテストケース.
 * @author jbd
 */
public final class IgnoreCasePropertyUtilsTest extends TestCase {

	/**
	 * Constructor for IgnoreCasePropertyUtilsTest.
	 * @param arg0 arg0
	 */
	public IgnoreCasePropertyUtilsTest(final String arg0) {
		super(arg0);
	}

	/**
	 * copyPropertiesテスト.
	 */
	public void testCopyProperties() {
		try {

			DynaClassMaker dcm1 = new DynaClassMaker("testBean");
			dcm1.addProperty("strValue", String.class);
			DynaClass dc1 = dcm1.getDynaClass();
			DynaBean db1 = dc1.newInstance();
			db1.set("strValue", "abc");

			DynaClassMaker dcm2 = new DynaClassMaker("testBean2");
			dcm2.addProperty("strValue", String.class);
			DynaClass dc2 = dcm2.getDynaClass();
			DynaBean db2 = dc2.newInstance();

			IgnoreCasePropertyUtils.copyProperties(db2, db1);

			assertEquals("abc", db2.get("strValue"));

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail("Raise an " + e.getClass());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			fail("Raise an " + e.getClass());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			fail("Raise a " + e.getClass());
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail("Raise an " + e.getClass());
		}
	}
}
