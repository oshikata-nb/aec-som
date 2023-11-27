/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shipping;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 詳細画面(花王・その他)_表示用データ格納クラス.
 *
 * @author tosco
 */
public class ShippingDetailOtherEntity extends ShippingDetailEntity implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション systemDate */
	public static final String keepDivision_COLUMN = "KEEP_DIVISION";

	/** 預かり品区分 */
	private BigDecimal keepDivision;

	/**
	 * コンストラクタ.
	 */
	public ShippingDetailOtherEntity() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		super.init();
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 預かり品区分を取得します。
	 * @return BigDecimal 預かり品区分
	 */
	public BigDecimal getKeepDivision() {
		return keepDivision;
	}

	/**
	 * 預かり品区分を設定します。
	 * @param keepDivision 預かり品区分
	 */
	public void setKeepDivision(final BigDecimal keepDivision) {
		this.keepDivision = keepDivision;
	}

}
