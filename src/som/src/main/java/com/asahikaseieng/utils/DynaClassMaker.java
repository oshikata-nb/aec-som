/*
 * Created on 2004/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;

/**
 * DynaClassを作るクラス.
 * @author jbd
 */
public final class DynaClassMaker {

	private Collection<DynaProperty> dynaProperties = new ArrayList<DynaProperty>();

	private String dynaClassName;

	/**
	 * コンストラクタ(DynaClass名をセットする).
	 * @param name DynaClass名
	 */
	public DynaClassMaker(final String name) {
		if (name == null) {
			throw new NullPointerException();
		} else if (name.length() <= 0) {
			throw new IllegalArgumentException();
		}
		dynaClassName = name;
	}

	/**
	 * プロパティを詰める.
	 * @param name プロパティ名
	 * @param clazz クラス
	 */
	public void addProperty(final String name, final Class clazz) {
		if (name == null || clazz == null) {
			throw new NullPointerException();
		} else if (name.length() <= 0) {
			throw new IllegalArgumentException();
		}

		for (Iterator ite = dynaProperties.iterator(); ite.hasNext();) {
			DynaProperty dp = (DynaProperty) ite.next();
			if (dp.getName().equals(name)) {
				throw new IllegalArgumentException("already exist property:"
						+ name);
			}
		}
		dynaProperties.add(new DynaProperty(name, clazz));
	}

	/**
	 * DynaClassを取得する.
	 * @return DynaClass
	 */
	public DynaClass getDynaClass() {
		return new BasicDynaClass(dynaClassName, BasicDynaBean.class,
				dynaProperties.toArray(new DynaProperty[0]));
	}
}
