/*
 * Created on 2008/05/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.dividedstring;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;

/**
 * ##ここにクラスの説明を書いてください##
 * @author a7710658
 */
public abstract class AbstractDevidedString {
	// 保存されたデータの数値部分の項目名
	private String[] label;

	// 保存されたデータのうち数値部分
	private String[] values;

	// labelをComboBoxにしたもの
	private List<ComboBoxItems> name;

	// valuesがとる値の翻訳
	private String[] nameLabels;

	// 保存されたデータのうち文字部分
	private String other;

	// 保存されたデータの数値部分の項目数
	private int valueSize;

	/**
	 * コンストラクタ
	 * @param label .
	 */
	public AbstractDevidedString(final String[] label) {
		this.label = label;
		this.valueSize = label.length;
		this.values = new String[this.valueSize];
	}

	/**
	 * ##連結した文字列を返す##
	 * @return 連結文字列
	 */
	public String getValue() {
		String value = "";
		for (int i = 0; i < values.length; i++) {
			value = value + values[i];
		}
		value = value + other;
		return value;
	}

	/**
	 * ##文字列を分解します##
	 * @param value .
	 */
	public void setValue(final String value) {
		int sz;
		if (this.valueSize > value.length()) {
			sz = value.length();
		} else {
			sz = this.valueSize;
		}

		for (int i = 0; i < sz; i++) {
			try {
				values[i] = Integer.valueOf(value.charAt(i) + "").toString();
			} catch (NumberFormatException e) {
				sz = i;
				break;
			}
		}
		this.other = value.substring(sz + 1);
		for (int i = sz; i < this.valueSize; i++) {
			values[i] = "0";
		}
	}

	/**
	 * nameを取得します。
	 * @return name
	 */
	public List<ComboBoxItems> getName() {
		return name;
	}

	/**
	 * nameを設定します。
	 * @param name name
	 */
	public void setName(final List<ComboBoxItems> name) {
		this.name = name;
	}

	/**
	 * otherを取得します。
	 * @return other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * otherを設定します。
	 * @param other other
	 */
	public void setOther(final String other) {
		this.other = other;
	}

	/**
	 * valueSizeを取得します。
	 * @return valueSize
	 */
	public int getValueSize() {
		return valueSize;
	}

	/**
	 * valueSizeを設定します。
	 * @param valueSize valueSize
	 */
	public void setValueSize(final int valueSize) {
		this.valueSize = valueSize;
	}

	/**
	 * labelを取得します。
	 * @return label
	 */
	public String[] getLabel() {
		return label;
	}

	/**
	 * labelを設定します。
	 * @param label label
	 */
	public void setLabel(final String[] label) {
		valueSize = label.length;
		this.label = label;
	}

	/**
	 * nameLabelsを取得します。
	 * @return nameLabels
	 */
	public String[] getNameLabels() {
		return nameLabels;
	}

	/**
	 * nameLabelsを設定します。
	 * @param nameLabels nameLabels
	 */
	public void setNameLabels(final String[] nameLabels) {
		this.nameLabels = nameLabels;
	}

}
