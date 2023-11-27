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
 * ##ここにクラスの説明を書いてください##
 * @author a7710658
 */
public class SelectStockingDivision extends AbstractSelectComboBox {

	private static final String[] myValue = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

	private static final String[] myLabel
	= {"受入", "仕入", "返品", "値引", "運賃", "仕入取消", "返品取消", "値引取消", "運賃取消", "その他"};

	/**
	 * コンストラクタ
	 */
	public SelectStockingDivision() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero [true:0:全てを追加][false:追加しない]
	 * @param withNum [true:ラベルの前に数値を追加][false:追加しない]
	 */
	public SelectStockingDivision(final boolean zero, final boolean withNum) {
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
