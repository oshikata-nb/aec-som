/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectDirectionListStatus;


/**
 * 製造指図共通定数定義
 * @author tosco
 */
public final class DirectionConst {
	/** コンボボックスにおいて「すべて」選択 */
	public static final String COMBO_ALL_LABEL = "すべて";
	/** コンボボックスにおいて「すべて」選択コード */
	public static final String COMBO_ALL_VALUE = "0";

	/** タブID－ヘッダー情報タブ */
	public static final String HEADER = "Header";
	/** タブID－工程タブ */
	public static final String PROCEDURELIST = "ProcedureList";
	/** タブID－配合タブ */
	public static final String FORMULALIST = "FormulaList";
	/** タブID－検査タブ */
	public static final String INSPECTIONLIST = "InspectionList";
	/** タブID－検査詳細 */
	public static final String INSPECTIONDETAIL = "InspectionDetail";
	/** タブID－仕上タブ */
	public static final String FINISHLIST = "FinishList";
	/** Kgの単位区分 */
	public static final String UNIT_DIVISION_KG = "1";
	/** 洗浄水絶対重量計－数値フォーマット単位 */
	public static final String WATER_WEIGHT_UNIT = "SONOTA";
	/** 仕込み予定数量－数値フォーマット単位"HAIGO" */
	public static final String UNIT_DIVISION_HAIGO = "HAIGO";
	//荷主
	/** 荷主－自社 */
	public static final int SHIPPER_DIVISION_OWN = 0;
	/** 荷主－花王 */
	public static final int SHIPPER_DIVISION_KAO = 1;
	/** 荷主－OEM */
	public static final int SHIPPER_DIVISION_OEM = 2;
	/** 荷主－油脂 */
	public static final int SHIPPER_DIVISION_FAT = 3;
	/** 荷主ラベル－自社 */
	public static final String SHIPPER_DIVISION_OWN_LABEL = "自社";
	/** 荷主ラベル－花王 */
	public static final String SHIPPER_DIVISION_KAO_LABEL = "花王";

	/** 製造計画　荷主－花王 */
	public static final String SEIZO_IF_SHIPPER_DIVISION_KAO = "2";
	/** 製造計画　荷主－その他 */
	public static final String SEIZO_IF_SHIPPER_DIVISION_OTHER = "1";


	/** 指図区分 */
	public static final BigDecimal DIRECTION_DIVISION = new BigDecimal(1);

	/** 指図ステータス－未確定 */
	public static final BigDecimal DIRECTION_STATUS_UN_CONFIRMED = new BigDecimal(1);
	/** 指図ステータス－指図書発行済み */
	public static final BigDecimal DIRECTION_STATUS_ISSUED = new BigDecimal(2);
	/** 指図ステータス－製造開始 */
	public static final BigDecimal DIRECTION_STATUS_START = new BigDecimal(3);
	/** 指図ステータス－中間品最終検査待ち */
	public static final BigDecimal DIRECTION_STATUS_INSPECTION_WAIT = new BigDecimal(4);
	/** 指図ステータス－中間品最終検査済み */
	public static final BigDecimal DIRECTION_STATUS_INSPECTED = new BigDecimal(5);
	/** 指図ステータス－FA受信 */
	public static final BigDecimal DIRECTION_STATUS_FA_RECEIVE = new BigDecimal(6);
	/** 指図ステータス－製造記録出力済 */
	public static final BigDecimal DIRECTION_STATUS_OUTPUTED = new BigDecimal(7);
	/** 指図ステータス－完了 */
	public static final BigDecimal DIRECTION_STATUS_COMPLETED = new BigDecimal(8);

	/** 指図書発行フラグ－未発行(0) */
	public static final BigDecimal STAMP_FLAG_UN_ISSUANCE = new BigDecimal(0);
	/** 指図書発行フラグ－発行済み(1) */
	public static final BigDecimal STAMP_FLAG_ISSUANCE = new BigDecimal("1");
	/** 指図ステータス－指図書発行済み(2) */
	public static final BigDecimal DIRECTION_STATUS_ISSUANCE = new BigDecimal(SelectDirectionListStatus.ISSUANCE);

	/** 製品ラベル発行フラグ－未発行(0) */
	public static final BigDecimal PRODUCT_LABEL_FLAG_UN_ISSUANCE = new BigDecimal(0);

	/** 製造・包装記録発行発行フラグ－未発行(0) */
	public static final BigDecimal PRODUCT_RECORD_FLAG_UN_ISSUANCE = new BigDecimal(0);

	/** 予備溶解ラベル発行フラグ－未発行(0) */
	public static final BigDecimal STOCKDISS_LABEL_FLAG_UN_ISSUANCE = new BigDecimal(0);

	/** 指図書発行有無フラグ 2:なし */
	public static final BigDecimal ORDER_PUBLISH_FLG_OFF = new BigDecimal(2);
	/**
	 * コンストラクタ
	 */
	private DirectionConst() {
	}
}
