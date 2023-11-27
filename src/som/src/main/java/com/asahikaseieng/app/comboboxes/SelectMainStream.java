/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 処方プロシージャ 本流／予備溶解コンボボックスクラス
 * @author tosco
 */
public class SelectMainStream extends AbstractSelectComboBox {

	private static final String[] myValue = {"0", "1", "2", "3", "4", "5", "6", "8"};

	private static final String[] myLabel
		= {"本流", "予備溶解1", "予備溶解2", "予備溶解3", "予備溶解4", "予備溶解5", "予備溶解6", "準備"};

	/**
	 * コンストラクタ
	 */
	public SelectMainStream() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectMainStream(final boolean zero, final boolean withNum) {
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

		if (myLabel.length == myValue.length) {

			for (int i = 0; i < myValue.length; i++) {
				ComboBoxItems cbItems = new ComboBoxItems();
				cbItems.setLabales(myLabel[i]);
				cbItems.setValues(myValue[i]);
				cb.add(cbItems);
			}
		}
		super.setComboBoxList(cb);
	}

	/**
	 * 指定したコードに対する名称を取得する。
	 * @param value コード
	 * @return コードに対応する値（存在しない場合は空白）
	 */
	public static String getLabelName(final String value) {
		String res = "";
		for (int i = 0; i < myValue.length; i++) {
			if (myValue[i].equals(value)) {
				res  = myLabel[i];
			}
		}
		return res;
	}
}
