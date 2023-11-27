/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;

/**
 * 購買テーブルの購買ステータス用コンボボックスクラス（入荷入力用）
 * @author tosco
 */
public class SelectPurchaseStatusArrival extends AbstractSelectComboBox {

	private static final String[] myValue = {PurchaseStatus.ISSUED.toString(),
			PurchaseStatus.TYOSEI.toString(), PurchaseStatus.FIXED.toString(),
			PurchaseStatus.ARRIVALED.toString()};

	private static final String[] myLabel = {PurchaseStatus.ISSUED_NAME,
			PurchaseStatus.TYOSEI_NAME, PurchaseStatus.FIXED_NAME,
			PurchaseStatus.ARRIVALED_NAME};

	/**
	 * コンストラクタ
	 */
	public SelectPurchaseStatusArrival() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectPurchaseStatusArrival(final boolean zero, final boolean withNum) {
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
				res = myLabel[i];
			}
		}
		return res;
	}
}
