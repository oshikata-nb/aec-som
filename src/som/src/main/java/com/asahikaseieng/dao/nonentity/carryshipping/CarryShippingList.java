/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.carryshipping;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 運送店別出荷指図画面_検索結果表示用データ格納クラス.
 *
 * @author tosco
 */
public class CarryShippingList extends CarryShippingListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 未発行 */
	private static final String NOT_ISSUE = "未";

	/** 発行済み */
	private static final String ISSUE = "済";

	/**
	 * コンストラクタ.
	 */
	public CarryShippingList() {
		super();
	}

	/** COLUMNアノテーション carryName1 */
	public static final String carryName1_COLUMN = "CARRY_NAME1";

	/** COLUMNアノテーション carryName2 */
	public static final String carryName2_COLUMN = "CARRY_NAME2";

	/** チェックボックス */
	private boolean carryShippingCheckBox;

	/** 運送店名・工場名称１ */
	private String carryName1;

	/** 運送店名・工場名称２ */
	private String carryName2;

	/** 運送店名・工場名称 */
	private String carryName;


	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 発行済フラグ名称を取得します。
	 * @return String
	 */
	public String getStrShippingOrderSendCompFlag() {
		//発行フラグがnullじゃなかったら
		if (getShippingOrderSendCompFlag() != null) {
			//発行フラグが0であれば、未発行
			if (getShippingOrderSendCompFlag().equals(new BigDecimal(0))) {
				return NOT_ISSUE;

			//発行フラグが1であれば、発行済み
			} else if (getShippingOrderSendCompFlag().equals(new BigDecimal(1))) {
				return ISSUE;
			}
		}
		return "";
	}

	/**
	 * チェックボックス取得
	 * @return carryShippingCheckBox
	 */
	public boolean isCarryShippingCheckBox() {
		return carryShippingCheckBox;
	}

	/**
	 * チェックボックス設定
	 * @param carryShippingCheckBox チェックボックス
	 */
	public void setCarryShippingCheckBox(final boolean carryShippingCheckBox) {
		this.carryShippingCheckBox = carryShippingCheckBox;
	}

	/**
	 * 運送店名・工場名称１を取得します。
	 * @return carryName1
	 */
	public String getCarryName1() {
		return carryName1;
	}

	/**
	 * 運送店名・工場名称１を設定します。
	 * @param carryName1 運送店名・工場名称１
	 */
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * 運送店名・工場名称２を取得します。
	 * @return carryName2
	 */
	public String getCarryName2() {
		return carryName2;
	}

	/**
	 * 運送店名・工場名称２を設定します。
	 * @param carryName2 運送店名・工場名称２
	 */
	public void setCarryName2(final String carryName2) {
		this.carryName2 = carryName2;
	}

	/**
	 * 運送店名・工場名称を取得します。
	 * @return carryName
	 */
	public String getCarryName() {
		return carryName;
	}

	/**
	 * 運送店名・工場名称を設定します。
	 * @param carryName 運送店名・工場名称
	 */
	public void setCarryName(final String carryName) {
		this.carryName = carryName;
	}
}
