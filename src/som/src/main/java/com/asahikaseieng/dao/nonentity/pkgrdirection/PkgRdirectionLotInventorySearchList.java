/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.math.BigDecimal;

/**
 * 包装実績－ロット検索画面データ格納クラス.
 * @author tosco
 */
public class PkgRdirectionLotInventorySearchList extends
		PkgRdirectionLotInventorySearchListBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionLotInventorySearchList() {
		super();
	}

	//
	// 定数
	//
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション stockpdQty */
	public static final String stockpdQty_COLUMN = "STOCKPD_QTY";

	/** COLUMNアノテーション resultQty */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション sampleQty */
	public static final String sampleQty_COLUMN = "SAMPLE_QTY";

	/** COLUMNアノテーション lossQty */
	public static final String lossQty_COLUMN = "LOSS_QTY";

	/** COLUMNアノテーション defectQty */
	public static final String defectQty_COLUMN = "DEFECT_QTY";

	/** COLUMNアノテーション adjustQty */
	public static final String adjustQty_COLUMN = "ADJUST_QTY";

	//
	// インスタンスフィールド
	//
	/** 品目名称 */
	private String itemName;

	/** 運用管理単位 */
	private String unitDiv;

	/** 単位名 */
	private String unitName;

	/** ロケーション名称 */
	private String locationName;

	/** 使用量 */
	private String strUseSumQty;

	/** 在庫量 */
	private String strInventoryQty;

	/** 使用数 */
	private String strUseQty;

	/** サンプル */
	private String strSampleQty;

	/** ロス数量 */
	private String strLossQty;

	/** 不良 */
	private String strDefectQty;

	/** 調整数量 */
	private String strAdjustQty;

	/** 在庫引落量 */
	private BigDecimal stockpdQty;

	/** 実績数量 */
	private BigDecimal resultQty;

	/** サンプル */
	private BigDecimal sampleQty;

	/** ロス数量 */
	private BigDecimal lossQty;

	/** 不良 */
	private BigDecimal defectQty;

	/** 調整数量 */
	private BigDecimal adjustQty;

	//
	// インスタンスメソッド
	//
	/**
	 * 品目名称取得.
	 * @return String 品目名称
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 運用管理単位取得.
	 * @return String 運用管理単位
	 */
	public String getUnitDiv() {
		return this.unitDiv;
	}

	/**
	 * 運用管理単位設定.
	 * @param unitDiv 運用管理単位
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
	}

	/**
	 * 単位名を取得します。
	 * @return 単位名
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位名を設定します。
	 * @param unitName 単位名
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * ロケーション名称取得.
	 * @return String ロケーション名称
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * ロケーション名称設定.
	 * @param locationName ロケーション名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 使用量取得.
	 * @return String 使用量
	 */
	public String getStrUseSumQty() {
		return this.strUseSumQty;
	}

	/**
	 * 使用量設定.
	 * @param strUseSumQty 使用量
	 */
	public void setStrUseSumQty(final String strUseSumQty) {
		this.strUseSumQty = strUseSumQty;
	}

	/**
	 * 在庫量取得.
	 * @return String 在庫量
	 */
	public String getStrInventoryQty() {
		return this.strInventoryQty;
	}

	/**
	 * 在庫量設定.
	 * @param strInventoryQty 在庫量
	 */
	public void setStrInventoryQty(final String strInventoryQty) {
		this.strInventoryQty = strInventoryQty;
	}

	/**
	 * 使用数量取得.
	 * @return String 使用数量
	 */
	public String getStrUseQty() {
		return this.strUseQty;
	}

	/**
	 * 使用数量設定.
	 * @param strUseQty 使用数量
	 */
	public void setStrUseQty(final String strUseQty) {
		this.strUseQty = strUseQty;
	}

	/**
	 * サンプル取得
	 * @return String サンプル
	 */
	public String getStrSampleQty() {
		return this.strSampleQty;
	}

	/**
	 * サンプル設定
	 * @param strSampleQty サンプル
	 */
	public void setStrSampleQty(final String strSampleQty) {
		this.strSampleQty = strSampleQty;
	}

	/**
	 * ロス数量取得
	 * @return String ロス数量
	 */
	public String getStrLossQty() {
		return this.strLossQty;
	}

	/**
	 * ロス数量設定
	 * @param strLossQty ロス数量
	 */
	public void setStrLossQty(final String strLossQty) {
		this.strLossQty = strLossQty;
	}

	/**
	 * 不良取得
	 * @return String 不良
	 */
	public String getStrDefectQty() {
		return this.strDefectQty;
	}

	/**
	 * 不良設定
	 * @param strDefectQty 不良
	 */
	public void setStrDefectQty(final String strDefectQty) {
		this.strDefectQty = strDefectQty;
	}

	/**
	 * 調整数量取得
	 * @return String 調整数量
	 */
	public String getStrAdjustQty() {
		return this.strAdjustQty;
	}

	/**
	 * 調整数量設定
	 * @param strAdjustQty 調整数量
	 */
	public void setStrAdjustQty(final String strAdjustQty) {
		this.strAdjustQty = strAdjustQty;
	}

	/**
	 * 在庫引落量取得
	 * @return BigDecimal 在庫引落量
	 */
	public BigDecimal getStockpdQty() {
		return this.stockpdQty;
	}

	/**
	 * 在庫引落量設定
	 * @param stockpdQty 在庫引落量
	 */
	public void setStockpdQty(final BigDecimal stockpdQty) {
		this.stockpdQty = stockpdQty;
	}

	/**
	 * 実績数量取得
	 * @return BigDecimal 実績数量
	 */
	public BigDecimal getResultQty() {
		return this.resultQty;
	}

	/**
	 * 実績数量設定
	 * @param resultQty 実績数量
	 */
	public void setResultQty(final BigDecimal resultQty) {
		this.resultQty = resultQty;
	}

	/**
	 * サンプル取得
	 * @return BigDecimal サンプル
	 */
	public BigDecimal getSampleQty() {
		return this.sampleQty;
	}

	/**
	 * サンプル設定
	 * @param sampleQty サンプル
	 */
	public void setSampleQty(final BigDecimal sampleQty) {
		this.sampleQty = sampleQty;
	}

	/**
	 * ロス数量取得
	 * @return BigDecimal ロス数量
	 */
	public BigDecimal getLossQty() {
		return this.lossQty;
	}

	/**
	 * ロス数量設定
	 * @param lossQty ロス数量
	 */
	public void setLossQty(final BigDecimal lossQty) {
		this.lossQty = lossQty;
	}

	/**
	 * 不良取得
	 * @return BigDecimal 不良
	 */
	public BigDecimal getDefectQty() {
		return this.defectQty;
	}

	/**
	 * 不良設定
	 * @param defectQty 不良
	 */
	public void setDefectQty(final BigDecimal defectQty) {
		this.defectQty = defectQty;
	}

	/**
	 * 調整数量取得
	 * @return BigDecimal 調整数量
	 */
	public BigDecimal getAdjustQty() {
		return this.adjustQty;
	}

	/**
	 * 調整数量設定
	 * @param adjustQty 調整数量
	 */
	public void setAdjustQty(final BigDecimal adjustQty) {
		this.adjustQty = adjustQty;
	}
}
