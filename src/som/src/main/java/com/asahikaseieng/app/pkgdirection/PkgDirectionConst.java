/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;

/**
 * 包装指図－共通定数定義
 * @author tosco
 */
public final class PkgDirectionConst {
	/** コンボボックスにおいて「すべて」選択 */
	public static final String COMBO_ALL_LABEL = "すべて";

	/** コンボボックスにおいて「すべて」選択コード */
	public static final BigDecimal COMBO_ALL_VALUE = BigDecimal.ZERO;

	/** タブID－ヘッダー情報タブ */
	protected static final String HEADER = "Header";

	/** タブID－工程タブ */
	protected static final String PROCEDURELIST = "ProcedureList";

	/** タブID－工程詳細 */
	protected static final String PROCEDUREDETAIL = "ProcedureDetail";

	/** タブID－配合タブ */
	protected static final String FORMULALIST = "FormulaList";

	/** タブID－配合詳細 */
	protected static final String FORMULADETAIL = "FormulaDetail";

	/** タブID－検査タブ */
	protected static final String INSPECTIONLIST = "InspectionList";

	/** タブID－検査詳細 */
	protected static final String INSPECTIONDETAIL = "InspectionDetail";

	/** タブID－仕上タブ */
	protected static final String FINISHLIST = "FinishList";

	/** レシピコードとレシピバージョンの分離文字 */
	protected static final String RECIPE_CD_VERSION_SEPARATOR = ",";

	/** 指図区分 充填・包装指図 */
	protected static final BigDecimal DIRECTION_DIVISION_PACK = new BigDecimal(
			2);

	/** 指図区分 詰替・貼替指図 */
	protected static final BigDecimal DIRECTION_DIVISION_REPACK = new BigDecimal(
			3);

	/** 指図ステータス 全て */
	protected static final BigDecimal DIRECTION_STATUS_ALL = new BigDecimal(0);

	/** 指図ステータス 未確定 */
	protected static final BigDecimal DIRECTION_STATUS_UN_CONFIRMED = new BigDecimal(
			1);

	/** 指図ステータス 指図書発行済 */
	protected static final BigDecimal DIRECTION_STATUS_ISSUE = new BigDecimal(2);

	/** 最小時間文字列 */
	protected static final String STR_MIN_TIME = "00:00";

	/** 最大時間文字列 */
	protected static final String STR_MAX_TIME = "23:59";

	/** 配合時のLINE_TYPE */
	protected static final BigDecimal LINE_TYPE_COMBINE = new BigDecimal(-1);

	/** 製品時のLINE_TYPE */
	protected static final BigDecimal LINE_TYPE_PRODUCT = new BigDecimal(1);

	/** 仕上時のLINE_NO開始番号 */
	protected static final BigDecimal LINE_NO_FINISH_START_NO = new BigDecimal(
			1001);

	/** 品目マスタの製造品区分 該当なし */
	protected static final BigDecimal ITEM_PRODUCT_DIVISION_NONE = BigDecimal.ZERO;

	/** 工程マスタの用途 包装 */
	protected static final String OPERATION_RECIPE_USE_PACKAGE = "4";

	/** 数値桁数チェックマスタの区分 RECIPE5 */
	public static final String UNIT_DIV_RECIPE5 = "RECIPE5";

	/** 数値桁数チェックマスタの区分 HAIGO */
	public static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/** 種別 0:製品 */
	protected static final BigDecimal TYPE_DIVISION_PRODUCT = new BigDecimal(0);

	/**
	 * コンストラクタ
	 */
	private PkgDirectionConst() {
	}
}
