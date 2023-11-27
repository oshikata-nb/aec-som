/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 包装実績－指図ステータスコンボボックス
 * @author tosco
 */
public class SelectPkgRdirectionDirectionStatus extends AbstractSelectComboBox {

	private static final  String[] myValue = {"2", "3", "4", "5", "6", "7"};

	private static final  String[] myLabel = {"指図書発行済", "包装実績入力済",
		"包装記録出力済", "検査待ち在庫計上", "製品検査入力済", "完了"};

	private String status;

	/** 完了ステータス */
	private static final String DIRECTION_STATUS_COMPLETE = "7";

	/**
	 * コンストラクタ
	 */
	public SelectPkgRdirectionDirectionStatus() {
		this.status = null;
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectPkgRdirectionDirectionStatus(final boolean zero, final boolean withNum) {
		super(zero, withNum);
		this.status = null;
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 * @param status ステータス
	 */
	public SelectPkgRdirectionDirectionStatus(final boolean zero, final boolean withNum, final String status) {
		super(zero, withNum);
		this.status = status;
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
				if (this.status != null) {
					if (!this.status.equals(myValue[i])) {

						// 完了ステータスでない場合
						if (!DIRECTION_STATUS_COMPLETE.equals(myValue[i])) {
							continue;
						}
					}
				}
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
