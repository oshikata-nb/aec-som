/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 *　受注先データ得意先連携マスタの納入先設定用コンボボックス
 * @author tosco
 */
public class SelectDeliveryConf extends AbstractSelectComboBox {

	private String[] myValue = {"1", "2"};

	private String[] myLabel = {"設定済","未設定"};

	/**
	 * コンストラクタ
	 */
	public SelectDeliveryConf() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectDeliveryConf(final boolean zero, final boolean withNum) {
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
