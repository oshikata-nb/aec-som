/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;

/**
 * IgnoreCaseBeanUtilsのテストケース.
 * @author jbd
 */
public class IgnoreCaseBeanUtilsTest extends TestCase {

	/**
	 * Constructor for IgnoreCaseBeanUtilsTest.
	 * @param arg0 arg0
	 */
	public IgnoreCaseBeanUtilsTest(final String arg0) {
		super(arg0);
	}

	/**
	 * copyPropertiesテスト.
	 */
	public void testCopyProperties() {
		try {

			DynaClassMaker dcm1 = new DynaClassMaker("testBean");
			dcm1.addProperty("intValue", String.class);
			dcm1.addProperty("strValue", String.class);
			DynaClass dc1 = dcm1.getDynaClass();
			DynaBean db1 = dc1.newInstance();
			db1.set("intValue", "1");
			db1.set("strValue", "2");

			DynaClassMaker dcm2 = new DynaClassMaker("testBean2");
			dcm2.addProperty("intValue", Integer.class);
			dcm2.addProperty("strValue", String.class);
			DynaClass dc2 = dcm2.getDynaClass();
			DynaBean db2 = dc2.newInstance();

			IgnoreCaseBeanUtils.copyProperties(db2, db1);

			assertEquals(new Integer(1), db2.get("intValue"));
			assertEquals("2", db2.get("strValue"));

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail("Raise a " + e.getClass());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			fail("Raise a " + e.getClass());
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail("Raise a " + e.getClass());
		}
	}

	/**
	 * populateメソッドのテスト.
	 * @throws Exception 例外
	 */
	public void testPopulate() throws Exception {
		DynaClassMaker dcm = new DynaClassMaker("testBean");
		dcm.addProperty("intValue", Integer.class);
		DynaClass dc = dcm.getDynaClass();
		DynaBean db = dc.newInstance();
		db.set("intValue", new Integer(1));

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("intValue", new Integer(2));
		map.put(null, "dummy");

		IgnoreCaseBeanUtils.populate(db, map);
		assertEquals(new Integer(2), db.get("intValue"));
	}
}
