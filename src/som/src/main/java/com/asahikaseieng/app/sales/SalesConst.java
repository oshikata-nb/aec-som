/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;

/**
 * 売上共通定数定義
 * @author tosco
 */
public final class SalesConst {
	/** コンボボックスにおいて「すべて」選択 */
	protected static final String COMBO_ALL_LABEL = "すべて";

	/** コンボボックスにおいて「すべて」選択コード */
	protected static final String COMBO_ALL_VALUE = "0";

	/** 取引先区分区分 TS:得意先 */
	protected static final String VENDER_DIVISION_TS = "TS";

	/** 単位区分 URKINGAKU */
	protected static final String UNIT_DIVISION_URKINGAKU = "URKINGAKU";

	/** 単位区分 URTANKA */
	protected static final String UNIT_DIVISION_URTANKA = "URTANKA";

	/** 単位区分 消費税額 */
	protected static final String UNIT_DIVISION_TAX_AMOUNT = "TAX_AMOUNT";

	/** 売上区分 売上(売掛金) */
	protected static final String CATEGOTRY_DIVISION_SALES = "1";

	/** 売上区分 売上(前受金) */
	protected static final String CATEGOTRY_DIVISION_SALES_ADVANCE = "11";

	/** 売上区分 返品(売掛金) */
	protected static final String CATEGOTRY_DIVISION_RETURNS = "2";

	/** 売上区分 返品(前受金) */
	protected static final String CATEGOTRY_DIVISION_RETURNS_ADVANCE = "12";

	/** 売上区分 値引(売掛金) */
	protected static final String CATEGOTRY_DIVISION_DISCOUNT = "3";

	/** 売上区分 値引(前受金) */
	protected static final String CATEGOTRY_DIVISION_DISCOUNT_ADVANCE = "13";

	/** 売上区分 運賃(売掛金) */
	protected static final String CATEGOTRY_DIVISION_CARRY = "4";

	/** 売上区分 運賃(前受金) */
	protected static final String CATEGOTRY_DIVISION_CARRY_ADVANCE = "14";

	/** 売上区分 その他(売掛金) */
	protected static final String CATEGOTRY_DIVISION_OTHER = "9";

	/** 売上区分 その他(前受金) */
	protected static final String CATEGOTRY_DIVISION_OTHER_ADVANCE = "19";

	/** 売上区分 返品取消(売掛金) */
	protected static final String CATEGOTRY_DIVISION_RETURNS_CANCEL = "-2";

	/** 売上区分 返品取消(前受金) */
	protected static final String CATEGOTRY_DIVISION_RETURNS_CANCEL_ADVANCE = "-12";

	/** 預かり品区分 1:通常 */
	protected static final BigDecimal KEEP_DIVISION_STANDARD = new BigDecimal(1);

	/** 預かり品区分 2:預かり品 */
	protected static final BigDecimal KEEP_DIVISION_KEEP = new BigDecimal(2);

	/** 伝票発行済区分 1：発行済 */
	protected static final BigDecimal SLIP_PUBLISH_COMP_ISSUED = BigDecimal.ONE;

	/** 伝票発行済区分 0：未発行 */
	protected static final BigDecimal SLIP_PUBLISH_COMP_NOT_ISSUE = BigDecimal.ZERO;

	/** 伝票発行済区分名称 発行済 */
	protected static final String SLIP_PUBLISH_COMP_NAME_ISSUED = "売上伝票発行済";

	/** 仮単価FLG 1:仮単価 */
	protected static final BigDecimal TMP_UNITPRICE_FLG_TMP = BigDecimal.ONE;

	/** 入力チェック用：数量の最小値 */
	protected static final BigDecimal QUANTITY_MIN = BigDecimal.ZERO;

	// 2014/1/29 -> 新消費税対応
	/** 消費税区分外税 */
	protected static final String TAX_DIVISION_OUT = "1";
	/** 消費税区分内税 */
	protected static final String TAX_DIVISION_IN = "2";
	// 2014/1/29 <- 新消費税対応
	
	//軽減税率対応
	protected static final String SALES_CATEGORY = "SALES";
	/**
	 * コンストラクタ
	 */
	private SalesConst() {
	}
}
