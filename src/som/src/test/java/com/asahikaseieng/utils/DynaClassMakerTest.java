/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.beanutils.DynaClass;

/**
 * DynaClassMakerのテストクラス.
 * 
 * @author jbd
 */
public class DynaClassMakerTest extends TestCase {

	/**
	 * Constructor for DynaClassMakerTest.
	 * @param arg0 arg0
	 */
	public DynaClassMakerTest(final String arg0) {
		super(arg0);
	}

	/**
	 * dynaClassMakerテスト.
	 */
	public final void testDynaClassMaker() {
		try {
			new DynaClassMaker(null);
			fail("NoDataException");
		} catch (NullPointerException e) {
			;
		}

		try {
			new DynaClassMaker("");
			fail("IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			;
		}
	}

	/**
	 * addPropertyテスト.
	 */
	public final void testAddProperty() {
		try {
			DynaClassMaker dcm = new DynaClassMaker("test");
			dcm.addProperty(null, String.class);
			fail("NoDataException");
		} catch (NullPointerException e) {
			;
		}

		try {
			DynaClassMaker dcm = new DynaClassMaker("test");
			dcm.addProperty("no", null);
			fail("NoDataException");
		} catch (NullPointerException e) {
			;
		}

		try {
			DynaClassMaker dcm = new DynaClassMaker("test");
			dcm.addProperty("", String.class);
			fail("IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			;
		}

		try {
			DynaClassMaker dcm = new DynaClassMaker("test");
			dcm.addProperty("no", Integer.class);
			dcm.addProperty("no", Integer.class);
			fail("IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			;
		}
	}

	/**
	 * getDynaClassテスト.
	 */
	public final void testGetDynaClass() {
		DynaClassMaker dcm = new DynaClassMaker("test");
		dcm.addProperty("no", Integer.class);
		dcm.addProperty("name", String.class);
		dcm.addProperty("fromdate", Date.class);
		DynaClass dc = dcm.getDynaClass();

		assertEquals("test", dc.getName());
		assertEquals("no", dc.getDynaProperty("no").getName());
		assertEquals(Integer.class, dc.getDynaProperty("no").getType());
		assertEquals("name", dc.getDynaProperty("name").getName());
		assertEquals(String.class, dc.getDynaProperty("name").getType());
		assertEquals("fromdate", dc.getDynaProperty("fromdate").getName());
		assertEquals(Date.class, dc.getDynaProperty("fromdate").getType());
	}

}
