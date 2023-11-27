/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;

/**
 * 包装指図－製造指図明細ポップアップ画面データ格納クラス.
 * @author tosco
 */
public class PkgDirectionDirectionDetailSearchList extends PkgDirectionDirectionDetailSearchListBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionDetailSearchList() {
		super();
	}

	//
	// 定数
	//

	/** COLUMNアノテーション pkgItemCd */
	public static final String pkgItemCd_COLUMN = "PKG_ITEM_CD";

	/** COLUMNアノテーション pkgItemName */
	public static final String pkgItemName_COLUMN = "PKG_ITEM_NAME";

	/** COLUMNアノテーション dirItemCd */
	public static final String dirItemCd_COLUMN = "DIR_ITEM_CD";

	/** COLUMNアノテーション dirItemName */
	public static final String dirItemName_COLUMN = "DIR_ITEM_NAME";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション planedQty */
	public static final String planedQty_COLUMN = "PLANED_QTY";

	/** COLUMNアノテーション resultQty */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション compoundTankNo */
	public static final String compoundTankNo_COLUMN = "COMPOUND_TANK_NO";

	//
	// インスタンスフィールド
	//

	/** 品目コード(包装) */
	private String pkgItemCd;

	/** 品目名称(包装) */
	private String pkgItemName;

	/** 品目コード(製造) */
	private String dirItemCd;

	/** 品目名称(製造) */
	private String dirItemName;

	/** 運用管理単位 */
	private String unitDiv;

	/** 予定生産量 */
	private BigDecimal planedQty;

	/** 予定生産量(フォーマット編集) */
	private String strPlanedQty;

	/** 実績生産量 */
	private BigDecimal resultQty;

	/** 実績生産量(フォーマット編集) */
	private String strResultQty;

	/** 調合タンクNO */
	private String compoundTankNo;

	//
	// インスタンスメソッド
	//

	/**
	 * 品目コード(包装)取得.
	 * @return String 品目コード(包装)
	 */
	public String getPkgItemCd() {
		return this.pkgItemCd;
	}

	/**
	 * 品目コード(包装)設定.
	 * @param pkgItemCd 品目コード(包装)
	 */
	public void setPkgItemCd(final String pkgItemCd) {
		this.pkgItemCd = pkgItemCd;
	}

	/**
	 * 品目名称(包装)取得.
	 * @return String 品目名称(包装)
	 */
	public String getPkgItemName() {
		return this.pkgItemName;
	}

	/**
	 * 品目名称(包装)設定.
	 * @param pkgItemName 品目名称(包装)
	 */
	public void setPkgItemName(final String pkgItemName) {
		this.pkgItemName = pkgItemName;
	}

	/**
	 * 品目コード(製造)取得.
	 * @return String 品目コード(製造)
	 */
	public String getDirItemCd() {
		return this.dirItemCd;
	}

	/**
	 * 品目コード(製造)設定.
	 * @param dirItemCd 品目コード(製造)
	 */
	public void setDirItemCd(final String dirItemCd) {
		this.dirItemCd = dirItemCd;
	}

	/**
	 * 品目名称(製造)取得.
	 * @return String 品目名称(製造)
	 */
	public String getDirItemName() {
		return this.dirItemName;
	}

	/**
	 * 品目名称(製造)設定.
	 * @param dirItemName 品目名称(製造)
	 */
	public void setDirItemName(final String dirItemName) {
		this.dirItemName = dirItemName;
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
	 * 予定生産量取得.
	 * @return BigDecimal 予定生産量
	 */
	public BigDecimal getPlanedQty() {
		return this.planedQty;
	}

	/**
	 * 予定生産量設定.
	 * @param planedQty 予定生産量
	 */
	public void setPlanedQty(final BigDecimal planedQty) {
		this.planedQty = planedQty;
	}

	/**
	 * 予定生産量(フォーマット編集)取得.
	 * @return String 予定生産量(フォーマット編集)
	 */
	public String getStrPlanedQty() {
		return strPlanedQty;
	}

	/**
	 * 予定生産量(フォーマット編集)設定.
	 * @param strPlanedQty 予定生産量(フォーマット編集)
	 */
	public void setStrPlanedQty(final String strPlanedQty) {
		this.strPlanedQty = strPlanedQty;
	}

	/**
	 * 実績生産量取得.
	 * @return BigDecimal 実績生産量
	 */
	public BigDecimal getResultQty() {
		return this.resultQty;
	}

	/**
	 * 実績生産量設定.
	 * @param resultQty 実績生産量
	 */
	public void setResultQty(final BigDecimal resultQty) {
		this.resultQty = resultQty;
	}

	/**
	 * 実績生産量(フォーマット編集)取得.
	 * @return String 実績生産量(フォーマット編集)
	 */
	public String getStrResultQty() {
		return strResultQty;
	}

	/**
	 * 実績生産量(フォーマット編集)設定.
	 * @param strResultQty 実績生産量(フォーマット編集)
	 */
	public void setStrResultQty(final String strResultQty) {
		this.strResultQty = strResultQty;
	}

	/**
	 * 調合タンクNO取得.
	 * @return String 調合タンクNO
	 */
	public String getCompoundTankNo() {
		return this.compoundTankNo;
	}

	/**
	 * 調合タンクNO設定.
	 * @param compoundTankNo 調合タンクNO
	 */
	public void setCompoundTankNo(final String compoundTankNo) {
		this.compoundTankNo = compoundTankNo;
	}
}

