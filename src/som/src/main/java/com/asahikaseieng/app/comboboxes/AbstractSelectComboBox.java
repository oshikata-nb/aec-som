/*
 * Created on 2007/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * JSPでの使用例： <% pageContext.setAttribute("selectkubun",new
 * com.asahikaseieng.app.comboboxes.SelectOrderKubun()); %> <nested:select
 * property="jujkbn" disabled="true"> <nested:options name="selectkubun"
 * property="values" labelName="selectkubun" labelProperty="labels" />
 * </nested:select>
 * @author a7710658
 */
abstract class AbstractSelectComboBox {

	/**
	 * コンストラクタ
	 */
	public AbstractSelectComboBox() {
	}

	/**
	 * コンストラクタ
	 * @param zero ;
	 * @param withNum ;
	 */
	public AbstractSelectComboBox(final boolean zero, final boolean withNum) {
		zeroflg = zero;
		withNumFlg = withNum;
	}

	private List<ComboBoxItems> comboBoxList = new ArrayList<ComboBoxItems>();

	private boolean zeroflg = true;

	private boolean withNumFlg = true;

	private String zeroValue = "0";

	private String zeroLabel = "すべて";

	/**
	 * ##ComboBox用Listを作成する##
	 */
	abstract void setItem();

	abstract String[] getValues();

	abstract String[] getLabels();

	/**
	 * ##html(jsp)のOptionタグのvalueを返す##
	 * @return String[] value
	 */
	public String[] getVal() {

		String[] labels;
		int st;

		int ct = comboBoxList.size();
		if (zeroflg) {
			labels = new String[ct + 1];
			labels[0] = zeroValue;
			st = 1;
		} else {
			labels = new String[ct];
			st = 0;
		}
		for (int i = 0; i < ct; i++) {
			labels[st] = comboBoxList.get(i).getValues();
			st++;
		}
		return labels;
	}

	/**
	 * ##html(jsp)のOptionタグのlabelを返す##
	 * @return none
	 */
	public String[] getLab() {

		String[] labels;
		int st;

		int ct = comboBoxList.size();
		if (zeroflg) {
			labels = new String[ct + 1];
			if (withNumFlg) {
				labels[0] = zeroValue + ":" + zeroLabel;

			} else {
				labels[0] = zeroLabel;

			}
			st = 1;
		} else {
			labels = new String[ct];
			st = 0;
		}
		for (int i = 0; i < ct; i++) {
			if (withNumFlg) {
				labels[st] = comboBoxList.get(i).getValues() + ":"
						+ comboBoxList.get(i).getLabales();
			} else {
				labels[st] = comboBoxList.get(i).getLabales();
			}
			st++;
		}
		return labels;
	}

	/**
	 * comboBoxListを取得します。
	 * @return comboBoxList
	 */
	public List<ComboBoxItems> getComboBoxList() {
		return comboBoxList;
	}

	/**
	 * comboBoxListを設定します。
	 * @param comboBoxList comboBoxList
	 */
	public void setComboBoxList(final List<ComboBoxItems> comboBoxList) {
		this.comboBoxList = comboBoxList;
	}

	/**
	 * valに対応するLabelを返します。
	 * @param val .
	 * @return Label .
	 */
	public String getLabel(final String val) {
		int ct = comboBoxList.size();
		for (int i = 0; i < ct; i++) {
			if (comboBoxList.get(i).getValues().equals(val)) {
				return comboBoxList.get(i).getLabales();
			}
		}
		return null;
	}

}
