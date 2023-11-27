/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shippingresult;

import java.math.BigDecimal;

/**
 * 出荷実績共通定数定義
 * @author tosco
 */
public final class ShippingResultConst {
	/** コンボボックスにおいて「すべて」選択 */
	protected static final String COMBO_ALL_LABEL = "すべて";

	/** コンボボックスにおいて「すべて」選択コード */
	protected static final String COMBO_ALL_VALUE = "0";

	/** 出荷ステータス　4:実績受信中 */
	protected static final BigDecimal SHIPPING_STATUS_RECEIVING = new BigDecimal(4);

	/** 出荷ステータス　5:出荷完了 */
	protected static final BigDecimal SHIPPING_STATUS_COMPLETE = new BigDecimal(5);

	/** 完納状態 1:完納 */
	protected static final BigDecimal DELIVERY_COMP_COMPLETE = BigDecimal.ONE;

	/** 完納状態 0:未完 */
	protected static final BigDecimal DELIVERY_COMP_NOT_COMPLETE = BigDecimal.ZERO;

	/** 取引先区分区分 TS:得意先 */
	protected static final String VENDER_DIVISION_TS = "TS";

	/** 区分　UNTIN */
	protected static final String UNIT_DIVISION_UNTIN = "UNTIN";

	/**
	 * コンストラクタ
	 */
	private ShippingResultConst() {
	}
}
