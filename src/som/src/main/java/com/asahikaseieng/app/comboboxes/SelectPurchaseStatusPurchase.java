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
 * 購買テーブルの購買ステータス用コンボボックスクラス
 * @author tosco
 */
public class SelectPurchaseStatusPurchase extends AbstractSelectComboBox {

	private static String[] myValue = {PurchaseStatus.ASPROVA.toString(),
			PurchaseStatus.ISSUED.toString(), PurchaseStatus.TYOSEI.toString(),
			PurchaseStatus.FIXED.toString(),
			PurchaseStatus.NOT_ISSUE.toString()};

	private static String[] myLabel = {PurchaseStatus.ASPROVA_NAME,
			PurchaseStatus.ISSUED_NAME, PurchaseStatus.TYOSEI_NAME,
			PurchaseStatus.FIXED_NAME, PurchaseStatus.NOT_ISSUE_NAME};

	private static String[] myValueDetail = {PurchaseStatus.NOT_ISSUE.toString(),
			PurchaseStatus.ISSUED.toString()};

	private static String[] myLabelDetail = {PurchaseStatus.NOT_ISSUE_NAME,
			PurchaseStatus.ISSUED_NAME};

	private Integer mode = 1;

	/**
	 * コンストラクタ
	 */
	public SelectPurchaseStatusPurchase() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectPurchaseStatusPurchase(final boolean zero,
			final boolean withNum) {
		super(zero, withNum);
		mode = 1;
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 * @param status String
	 */
	public SelectPurchaseStatusPurchase(final boolean zero,
			final boolean withNum, final String status) {
		super(zero, withNum);

		myValueDetail[1] = status;
		if (PurchaseStatus.ISSUED.toString().equals(status)) {
			myLabelDetail[1] = PurchaseStatus.ISSUED_NAME;
		}
		if (PurchaseStatus.TYOSEI.toString().equals(status)) {
			myLabelDetail[1] = PurchaseStatus.TYOSEI_NAME;
		}
		if (PurchaseStatus.FIXED.toString().equals(status)) {
			myLabelDetail[1] = PurchaseStatus.FIXED_NAME;
		}
		mode = 2;
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

		if (mode == 1) {
			if (myLabel.length == myValue.length) {

				for (int i = 0; i < myValue.length; i++) {
					ComboBoxItems cbItems = new ComboBoxItems();
					cbItems.setLabales(myLabel[i]);
					cbItems.setValues(myValue[i]);
					cb.add(cbItems);
				}
			}
		} else {
			if (myLabelDetail.length == myValueDetail.length) {
				for (int i = 0; i < myValueDetail.length; i++) {
					ComboBoxItems cbItems = new ComboBoxItems();
					cbItems.setLabales(myLabelDetail[i]);
					cbItems.setValues(myValueDetail[i]);
					cb.add(cbItems);
				}
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
