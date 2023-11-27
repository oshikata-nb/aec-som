/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.aspimport;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * アスプローバ取込共通定義
 * @author tosco
 */
public final class AspImportConst {
	/** 発注書発行フラグ 0:未発行 */
	public static final BigDecimal ORDER_SHEET_FLG = new BigDecimal(0);

	/** 分納区分 0:無し */
	public static final BigDecimal REPLY_CONTENT_DIVISION = new BigDecimal(0);

	/** ラベル発行フラグ 0:未発行 */
	public static final BigDecimal LABEL_FLG = new BigDecimal(0);

	/** 指図区分 充填・包装指図 */
	protected static final BigDecimal DIRECTION_DIVISION_PACK = new BigDecimal(
			2);

	/** 種別 1:原料 */
	public static final int TYPE1_DIVISION = 1;

	/** 種別 2:包材 */
	public static final int TYPE2_DIVISION = 2;

	/** 種別 4:仕入直送品 */
	public static final int TYPE4_DIVISION = 4;

	/** 種別 5：仕入在庫品 */
	public static final int TYPE5_DIVISION = 5;

	/** 種別 6：外注品(直送） */
	public static final int TYPE6_DIVISION = 6;

	/** 種別 7：外注品(非直送） */
	public static final int TYPE7_DIVISION = 7;

	/** スポット区分 1:通常 */
	public static final BigDecimal SPOT1_DIVISION = new BigDecimal(1);

	/** スポット区分 2:スポット */
	public static final BigDecimal SPOT2_DIVISION = new BigDecimal(2);

	/** オーダー区分 1:原材料 */
	public static final BigDecimal ORDER1_DIVISION = new BigDecimal(1);

	/** オーダー区分 1:原材料 int */
	public static final int ORDER1_DIVISION_INT = 1;

	/** オーダー区分 2：外注製品（直送） */
	public static final BigDecimal ORDER2_DIVISION = new BigDecimal(2);

	/** オーダー区分 3：外注製品（非直送） */
	public static final BigDecimal ORDER3_DIVISION = new BigDecimal(3);

	/** オーダー区分 4：スポット品 */
	public static final BigDecimal ORDER4_DIVISION = new BigDecimal(4);

	/** オーダー区分 5：仕入直送品 */
	public static final BigDecimal ORDER5_DIVISION = new BigDecimal(5);

	/** オーダー区分 6：仕入在庫品 */
	public static final BigDecimal ORDER6_DIVISION = new BigDecimal(6);

	/** 指図区分 */
	public static final BigDecimal DIRECTION_DIVISION = new BigDecimal(1);

	/** 指図ステータス－未確定 */
	public static final BigDecimal DIRECTION_STATUS_UN_CONFIRMED = new BigDecimal(
			1);

	/** 指図書発行フラグ－未発行(0) */
	public static final BigDecimal STAMP_FLAG_UN_ISSUANCE = new BigDecimal(0);

	/** 製品ラベル発行フラグ－未発行(0) */
	public static final BigDecimal PRODUCT_LABEL_FLAG_UN_ISSUANCE = new BigDecimal(
			0);

	/** 製造・包装記録発行発行フラグ－未発行(0) */
	public static final BigDecimal PRODUCT_RECORD_FLAG_UN_ISSUANCE = new BigDecimal(
			0);

	/** 予備溶解ラベル発行フラグ－未発行(0) */
	public static final BigDecimal STOCKDISS_LABEL_FLAG_UN_ISSUANCE = new BigDecimal(
			0);

	/** 仕込み予定数量－数値フォーマット単位"HAIGO" */
	public static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/** 工程番号 10(調合) */
	public static final BigDecimal PROC_NO_10 = new BigDecimal(10);

	/** 工程番号 20(移送) */
	public static final BigDecimal PROC_NO_20 = new BigDecimal(20);

	/** 工程番号 30(充填) */
	public static final BigDecimal PROC_NO_30 = new BigDecimal(30);

	/** 工程番号 40(包装) */
	public static final BigDecimal PROC_NO_40 = new BigDecimal(40);

	/** 仕上時のLINE_NO開始番号 */
	protected static final BigDecimal LINE_NO_FINISH_START_NO = new BigDecimal(
			1001);

	/** 製品時のLINE_TYPE */
	protected static final BigDecimal LINE_TYPE_PRODUCT = new BigDecimal(1);

	/** 配合時のLINE_TYPE */
	protected static final BigDecimal LINE_TYPE_COMBINE = new BigDecimal(-1);

	/** 端数区分-四捨五入 */
	protected static final RoundingMode ROUND_HALF_UP = RoundingMode.HALF_UP;

	/** ミリ秒 */
	public static final long MILLISECOND = 1000;

	/** 秒 */
	public static final long SECOND = 60;

	/**
	 * コンストラクタ
	 */
	private AspImportConst() {
	}
}
