/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 製品検査完了入力画面用　指図ステータスコンボボックス
 * @author a7710658
 */
public class SelectProductInspectCompDirectionStatus extends AbstractSelectComboBox {

	private static String[] myValue = {"5", "6"};

	private static String[] myLabel = {"検査待ち在庫計上", "製品検査入力済"};

	/**
	 * コンストラクタ
	 */
	public SelectProductInspectCompDirectionStatus() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectProductInspectCompDirectionStatus(final boolean zero, final boolean withNum) {
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
