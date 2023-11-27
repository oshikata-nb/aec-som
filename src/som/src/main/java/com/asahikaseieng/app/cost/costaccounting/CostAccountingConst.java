/*
 * Created on 2009/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costaccounting;

/**
 * 原価計算データ送信共通定数定義
 * @author t0011036
 */
public final class CostAccountingConst {

	/** 工程マスタインデックス */
	public static final int OPERATION_IDX = 0;

	/** 品目マスタインデックス */
	public static final int ITEM_IDX = 1;

	/** 作業手順マスタインデックス */
	public static final int PROCEDURE_IDX = 2;

	/** 部品構成マスタインデックス */
	public static final int FORMATION_IDX = 3;

	/** 製造オーダオファイルンデックス */
	public static final int ORDER_IDX = 4;

	/** 作業日報ファイルインデックス */
	public static final int REPORT_IDX = 5;

	/** 注文書ファイルインデックス */
	public static final int SALES_ORDER_IDX = 6;

	/** 仕入ファイルインデックス */
	public static final int STOCKING_IDX = 7;

	/** 売上ファイルインデックス */
	public static final int SALES_IDX = 8;

	/** 受払ファイルインデックス */
	public static final int RECEIPT_IDX = 9;

	/** 材料・製品元帳ファイルインデックス */
	public static final int LEDGER_IDX = 10;

	/** データ取込 材料・製品元帳ファイルインデックス */
	public static final int IMPORT_LEDGER_IDX = 0;

	/** 工程マスタ名称 */
	public static final String OPERATION_NAME = "工程マスタ";

	/** 品目マスタ名称 */
	public static final String ITEM_NAME = "品目マスタ";

	/** 作業手順マスタ名称 */
	public static final String PROCEDURE_NAME = "作業手順マスタ";

	/** 部品構成マスタ名称 */
	public static final String FORMATION_NAME = "部品構成マスタ";

	/** 製造オーダオファイルンデックス */
	public static final String ORDER_NAME = "製造オーダーファイル";

	/** 作業日報ファイル名称 */
	public static final String REPORT_NAME = "作業日報ファイル";

	/** 注文書ファイル名称 */
	public static final String SALES_ORDER_NAME = "注文書ファイル";

	/** 仕入ファイル名称 */
	public static final String STOCKING_NAME = "仕入ファイル";

	/** 売上ファイル名称 */
	public static final String SALES_NAME = "売上ファイル";

	/** 受払ファイル名称 */
	public static final String RECEIPT_NAME = "受払ファイル";

	/** 材料・製品元帳ファイル名称 */
	public static final String LEDGER_NAME = "材料・製品元帳ファイル";

	/** データ取込 月次在庫金額名称 */
	public static final String IMPORT_LEDGER_NAME = "月次在庫金額";

	/**
	 * コンストラクタ
	 */
	private CostAccountingConst() {
	}
}
