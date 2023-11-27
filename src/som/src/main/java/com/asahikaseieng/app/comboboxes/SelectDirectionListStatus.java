/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 製造指図一覧-指図ステータス
 * @author tosco
 */
public class SelectDirectionListStatus extends AbstractSelectComboBox {
	/** 指図ステータス－未確定 */
	public static final String UNFIXED = "1";
	/** 指図ステータス－指図書発行済 */
	public static final String ISSUANCE = "2";
	/** 指図ステータス－製造開始 */
	public static final String MAKED = "3";

	/** 値 */
	private static final String[] VALUE =
		{"1", "2", "3"};
	/** ラベル */
	private static final String[] LABEL =
		{"未確定", "指図書発行済", "製造開始"};

	/**
	 * コンストラクタ
	 */
	public SelectDirectionListStatus() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectDirectionListStatus(final boolean zero, final boolean withNum) {
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
