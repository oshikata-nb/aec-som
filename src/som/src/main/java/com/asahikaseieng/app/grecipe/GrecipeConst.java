/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectRecipeUse;

/**
 * 原処方-共通定数定義
 * @author tosco
 */
public final class GrecipeConst {
	/** コンボボックスにおいて「すべて」選択 */
	public static final String COMBO_ALL_LABEL = "すべて";

	/** コンボボックスにおいて「すべて」選択コード */
	public static final String COMBO_ALL_VALUE = "0";

	/** タブID－ヘッダー情報タブ */
	public static final String HEADER = "Header";

	/** タブID－工程タブ */
	public static final String PROCEDURELIST = "ProcedureList";

	/** タブID－工程詳細 */
	public static final String PROCEDUREDETAIL = "ProcedureDetail";

	/** タブID－配合タブ */
	public static final String FORMULALIST = "FormulaList";

	/** タブID－配合詳細 */
	public static final String FORMULADETAIL = "FormulaDetail";

	/** タブID－検査タブ */
	public static final String INSPECTIONLIST = "InspectionList";

	/** タブID－検査詳細 */
	public static final String INSPECTIONDETAIL = "InspectionDetail";

	/** タブID－仕上タブ */
	public static final String FINISHLIST = "FinishList";

	/** タブID－Asprovaタブ */
	public static final String ASPROVALIST = "AsprovaList";

	/** タブID－Asprova詳細 */
	public static final String ASPROVADETAIL = "AsprovaDetail";

	/** タブID－詳細タブ */
	public static final String SPECIFICS = "Specifics";

	/** タブID－その他タブ */
	public static final String OTHER = "Other";

	/** レシピタイプ-GENERAL */
	public static final String RECIPE_TYPE_GENERAL = "1";

	/** レシピタイプ-SITE */
	public static final String RECIPE_TYPE_SITE = "2";

	/** レシピタイプ-MASTER */
	// public static final String RECIPE_TYPE_MASTER = "3";
	/** レシピタイプ-工程パターン */
	public static final String RECIPE_TYPE_PATTERN = "4";

	/** 承認ステータス－1:入力中 */
	public static final String APPROVAL_STATUS_INPUT = "1";

	/** 承認ステータス－2:承認依頼中 */
	public static final String APPROVAL_STATUS_REQUEST = "2";

	/** 承認ステータス－3:承認済み */
	public static final String APPROVAL_STATUS_APPROVAL = "3";

	/** 承認ステータス名称－1:入力中 */
	public static final String APPROVAL_STATUS_NAME_INPUT = "入力中";

	/** 承認ステータス名称－2:承認依頼中 */
	public static final String APPROVAL_STATUS_NAME_REQUEST = "承認依頼中";

	/** 承認ステータス名称－3:承認済み */
	public static final String APPROVAL_STATUS_NAME_APPROVAL = "承認済み";

	/** 用途-製造 */
	public static final BigDecimal RECIPE_USE_PRODUCTION = new BigDecimal(
			SelectRecipeUse.RECIPE_USE_PRODUCTION);

	/** 用途-包装 */
	public static final BigDecimal RECIPE_USE_PACKING = new BigDecimal(
			SelectRecipeUse.RECIPE_USE_PACKING);

	/** テンプレート区分 GRECIPE_DETAIL */
	public static final String COMMON_CD_GR = "GRECIPE_DETAIL";

	/** テンプレート区分 MRECIPE_DETAIL */
	public static final String COMMON_CD_MR = "MRECIPE_DETAIL";

	/** ファイル名の年月日時分秒部分の桁数 */
	public static final int START_FILE_NAME = 14;

	/** 単位区分 "HAIGO" */
	public static final String UNIT_DIVISION_HAIGO = "HAIGO_RITU";

	/** 包装時の収率(100%) */
	public static final BigDecimal PACKAGE_PROCESS_LOSS = new BigDecimal(100);

	/**
	 * コンストラクタ
	 */
	private GrecipeConst() {
	}
}
