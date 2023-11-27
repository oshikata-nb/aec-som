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
 * 包装実績－指図区分コンボボックス
 * @author tosco
 */
public class SelectPkgRdirectionDirectionDivision extends AbstractSelectComboBox {

	private String[] myValue = {"2", "3"};

	private String[] myLabel = {"充填・包装指図", "詰替・貼替指図"};

	private boolean zeroflg = true;

	private boolean withNumFlg = true;

	private String zeroValue = "0";

	private String zeroLabel = "すべて";

	/**
	 * コンストラクタ
	 */
	public SelectPkgRdirectionDirectionDivision() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectPkgRdirectionDirectionDivision(final boolean zero, final boolean withNum) {
		super(zero, withNum);
		this.zeroflg = zero;
		this.withNumFlg = withNum;
		setItem();
	}

	/**
	 * @see com.asahikaseieng.app.comboboxes.AbstractSelectComboBox#getLabels()
	 * @return Label String.
	 */
	@Override
	public String[] getLabels() {

		String[] labels;
		int st;

		int ct = super.getComboBoxList().size();
		if (this.zeroflg) {
			labels = new String[ct + 1];
			if (this.withNumFlg) {
				labels[0] = zeroValue + ":" + zeroLabel;

			} else {
				labels[0] = zeroLabel;
			}
			st = 1;
		} else {
			labels = new String[ct];
			st = 0;
		}
		for (int i = 0; i < ct; i++) {
			if (withNumFlg) {
				Integer value = Integer.parseInt(super.getComboBoxList().get(i).getValues()) - 1;
				labels[st] = value.toString() + ":" + super.getComboBoxList().get(i).getLabales();
			} else {
				labels[st] = super.getComboBoxList().get(i).getLabales();
			}
			st++;
		}
		return labels;
//		return super.getLab();
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
