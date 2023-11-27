/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts.validator;

import java.util.Collection;
import java.util.Date;

/**
 * Rulesテストケースで利用するBeanクラス.
 * @author jbd
 */
public final class TestBean {

	private String value;

	private String value1;

	private String value2;

	private String value3;

	private Collection list;

	private Boolean check;

	private String[] stringArray;

	private Date date;

	private Date date1;

	/**
	 * コンストラクタ.
	 */
	public TestBean() {
	}

	/**
	 * valueを取得します。
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * valueを設定します。
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * value1を取得します。
	 * @return value1
	 */
	public String getValue1() {
		return value1;
	}

	/**
	 * value1を設定します。
	 * @param value1 value1
	 */
	public void setValue1(final String value1) {
		this.value1 = value1;
	}

	/**
	 * value2を取得します。
	 * @return value2
	 */
	public String getValue2() {
		return value2;
	}

	/**
	 * value2を設定します。
	 * @param value2 value2
	 */
	public void setValue2(final String value2) {
		this.value2 = value2;
	}

	/**
	 * value3を取得します。
	 * @return value3
	 */
	public String getValue3() {
		return value3;
	}

	/**
	 * value3を設定します。
	 * @param value3 value32
	 */
	public void setValue3(final String value3) {
		this.value3 = value3;
	}

	/**
	 * listを取得します。
	 * @return list
	 */
	public Collection getList() {
		return list;
	}

	/**
	 * listを設定します。
	 * @param list list
	 */
	public void setList(final Collection list) {
		this.list = list;
	}

	/**
	 * checkを取得します。
	 * @return check
	 */
	public Boolean getCheck() {
		return check;
	}

	/**
	 * checkを設定します。
	 * @param check check
	 */
	public void setCheck(final Boolean check) {
		this.check = check;
	}

	/**
	 * stringArrayを取得します。
	 * @return stringArray
	 */
	public String[] getStringArray() {
		return stringArray;
	}

	/**
	 * stringArrayを設定します。
	 * @param stringArray stringArray
	 */
	public void setStringArray(final String[] stringArray) {
		this.stringArray = stringArray;
	}

	/**
	 * dateを取得します。
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * dateを設定します。
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * date1を取得します。
	 * @return date1
	 */
	public Date getDate1() {
		return date1;
	}

	/**
	 * date1を設定します。
	 * @param date1 date1
	 */
	public void setDate1(final Date date1) {
		this.date1 = date1;
	}
}
