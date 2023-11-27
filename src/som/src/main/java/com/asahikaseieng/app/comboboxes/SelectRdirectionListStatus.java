/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 製造実績一覧-指図ステータス
 * @author tosco
 */
public class SelectRdirectionListStatus extends AbstractSelectComboBox {
	/** 指図ステータス－FA受信 */
	public static final String FA_RECEIVE = "6";
	/** 指図ステータス－製造記録出力済 */
	public static final String OUTPUTED = "7";
	/** 指図ステータス－完了 */
	public static final String COMPLETED = "8";

	/** 値 */
	private static final String[] VALUE =
		{"6", "7", "8"};
	/** ラベル */
	private static final String[] LABEL =
		{"FA受信済", "製造記録出力済", "完了"};

	/**
	 * コンストラクタ
	 */
	public SelectRdirectionListStatus() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectRdirectionListStatus(final boolean zero, final boolean withNum) {
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

		if (LABEL.length == VALUE.length) {
			for (int i = 0; i < VALUE.length; i++) {
				ComboBoxItems cbItems = new ComboBoxItems();
				cbItems.setLabales(LABEL[i]);
				cbItems.setValues(VALUE[i]);
				cb.add(cbItems);
			}
		}
		super.setComboBoxList(cb);
	}
	/**
	 * コードに対応する名称を返す。
	 * 対応するコードがない場合はnullを返す
	 * @param code コード
	 * @return 名称
	 */
	public static final String getName(final String code) {
		String res = null;
		int len = VALUE.length;
		for (int i = 0; i < len; i++) {
			if (VALUE[i].equals(code)) {
				res = LABEL[i];
				break;
			}
		}
		return res;
	}
}
