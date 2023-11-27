/*
 * Created on 2013/10/03
 * 
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.io.Serializable;

/**
 * コンボボックス結果表示用Bean
 * @author atts
 */
public class ComboboxesBean implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** コード */
	private String[] values;
	private String[] values2;

	/** 名称 */
	private String[] labels;
//	/** 名称３ */
//	private String[] name03;
	
	/** 非表示フラグ 表示(0),非表示(1) */
	private String[] invisibility;
	
	/**
	 * コンストラクタ
	 */
	public ComboboxesBean() {
	}

	/**
	 * コンストラクタ
	 * @param values コード
	 * @param labels 名称
	 */
	public ComboboxesBean(final String[] values, final String[] labels) {
		this.values = values;
		this.labels = labels;
	}

	/**
	 * コンストラクタ
	 * @param values コード
	 * @param labels 名称
	 * @param invisibility 非表示フラグ
	 */
	public ComboboxesBean(final String[] values, final String[] labels, String[] invisibility) {
		this.values = values;
		this.labels = labels;
		this.invisibility = invisibility;
	}

	/**
	 * valuesを取得します。
	 * @return values
	 */
	public String[] getValues() {
		return values;
	}

	/**
	 *  valuesを設定します。
	 * @param values values
	 */
	public void setValues(final String[] values) {
		this.values = values;
	}
	/**
	 * valuesを取得します。
	 * @return values
	 */
	public String[] getValues2() {
		return values2;
	}

	/**
	 *  valuesを設定します。
	 * @param values values
	 */
	public void setValues2(final String[] values2) {
		this.values2 = values2;
	}
	/**
	 * labelsを取得します。
	 * @return labels
	 */
	public String[] getLabels() {
		return labels;
	}

	/**
	 *  labelsを設定します。
	 * @param labels labels
	 */
	public void setLabels(final String[] labels) {
		this.labels = labels;
	}

//	/**
//	 * @return name03
//	 */
//	public String[] getName03() {
//		return name03;
//	}
//
//	/**
//	 * @param name03 セットする name03
//	 */
//	public void setName03(String[] name03) {
//		this.name03 = name03;
//	}
//
	/**
	 * @return invisibility
	 */
	public String[] getInvisibility() {
		return invisibility;
	}

	/**
	 * @param invisibility セットする invisibility
	 */
	public void setInvisibility(String[] invisibility) {
		this.invisibility = invisibility;
	}
}
