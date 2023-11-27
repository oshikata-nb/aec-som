/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.math.BigDecimal;

/**
 * 出荷指図共通定数定義
 * @author tosco
 */
public final class ShippingConst {
	/** コンボボックスにおいて「すべて」選択 */
	public static final String COMBO_ALL_LABEL = "すべて";
	/** コンボボックスにおいて「すべて」選択コード */
	public static final String COMBO_ALL_VALUE = "0";

	/** 出荷ステータス　1:出荷指図未確定 */
	public static final BigDecimal SHIPPING_STATUS_NOT_FIX = new BigDecimal(1);

	/** 出荷ステータス　2:出荷指図確定 */
	public static final BigDecimal SHIPPING_STATUS_FIX = new BigDecimal(2);

	/** 預かり品区分 1:通常品 */
	public static final BigDecimal KEEP_DIVISION_NORMAL = new BigDecimal(1);

	/** 預かり品区分 2:預かり品 */
	public static final BigDecimal KEEP_DIVISION_KEEP = new BigDecimal(2);

	/** 取引先区分区分 TS:得意先 */
	public static final String VENDER_DIVISION_TS = "TS";

	/** 区分　UNTIN */
	public static final String UNIT_DIVISION_UNTIN = "UNTIN";

	/**
	 * コンストラクタ
	 */
	private ShippingConst() {
	}
}
