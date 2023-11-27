/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * LINE_TYPE（区分）用コンボボックスクラス（入荷入力用）
 * @author tosco
 */
public class SelectRecipeLineTypeDivision extends AbstractSelectComboBox {

	private String[] myValue = {"1", "2", "3", "4", "5"};

	private String[] myLabel = {"製品", "中間品", "回収品", "副製品", "廃棄物"};

	/**
	 * コンストラクタ
	 */
	public SelectRecipeLineTypeDivision() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectRecipeLineTypeDivision(final boolean zero, final boolean withNum) {
		super(zero, withNum);
		setItem();
	}

	/**
	 * 
	 * @return String[]
	 */
	@Override
	public String[] getLabels() {

		return super.getLab();
	}

	/**
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
