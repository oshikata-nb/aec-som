/*
 * Created on 2008/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 設備マスタメンテ画面の指図書発行有無用フラグコンボボックス
 * @author tosco
 */
public class SelectOrderPublishFlg extends AbstractSelectComboBox {

	private String[] myValue = {"1", "2"};

	private String[] myLabel = {"あり", "なし"};

	/**
	 * コンストラクタ
	 */
	public SelectOrderPublishFlg() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectOrderPublishFlg(final boolean zero, final boolean withNum) {
		super(zero, withNum);
		setItem();
	}

	/**
	 * @see com.asahikaseieng.app.comboboxes.AbstractSelectComboBox#getLabels()
	 * @return Label String.
	 */
	@Override
	public String[] getLabels() {

		return super.getLab();
	}

	/**
	 * @see com.asahikaseieng.app.comboboxes.AbstractSelectComboBox#getValues()
	 * @return Values String.
	 */
	@Override
	public String[] getValues() {
		return super.getVal();
	}

	@Override
	void setItem() {
		List<ComboBoxItems> cb = new ArrayList<ComboBoxItems>();

		if (this.myLabel.length == this.myValue.length) {

			for (int i = 0; i < myValue.length; i++) {
				ComboBoxItems cbItems = new ComboBoxItems();
				cbItems.setLabales(myLabel[i]);
				cbItems.setValues(myValue[i]);
				cb.add(cbItems);
			}
		}
		super.setComboBoxList(cb);
	}
}