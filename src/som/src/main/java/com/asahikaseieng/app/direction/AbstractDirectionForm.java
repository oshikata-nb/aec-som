/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.sql.Timestamp;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 製造指図-共通抽象 Formクラス.
 * @author tosco
 */
public abstract class AbstractDirectionForm extends AbstractForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 変更フラグ **/
	private String dirtyFlg;
	/** タブID */
	private String tabId;

	// インスタンスフィールド
	/** 指図区分 */
	private String directionDivision;
	/** 指図番号 */
	private String directionNo;
	/** 調合タンクNO */
	private String compoundTankNo;
	/** ホールドタンクNO */
	private String holdTankNo;
	/** 予備溶解タンクNO */
	private String dissolutionTankNo;
	/** 荷主|0:自社,1:花王,2:OEM,3:油脂 */
	private String shipperDivision;
	/** 他社コード1 */
	private String otherCompanyCd1;
	/** 生産ライン */
	private String productionLine;
	/** 生産ライン名称 */
	private String productionLineName;
	/** 主要製品コード */
	private String itemCd;
	/** 品目名称 */
	private String itemName;
	/** レシピインデックス */
	private String recipeId;
	/** レシピコード */
	private String recipeCd;
	/** レシピバージョン */
	private String recipeVersion;
	/** 洗浄水絶対重量計 */
	private String waterWeight;
	/** 予定生産量|仕込数量 */
	private String planedQty;
	/** 開始予定日時 */
	private Timestamp planedSdate;
	/** 終了予定日時 */
	private Timestamp planedEdate;
	/** 指図ステータス */
	private String directionStatus;
	/** 指図ステータス名称 */
	private String directionStatusName;
	/** 品目単位コード */
	private String unitOfOperationManagement;
	/** 品目単位名称 */
	private String unitName;
	/** 荷主ラベル */
	private String shipperDivisionLabel;
	/** 表示時処方ヘッダー情報 */
	private DirectionDirectionHeaderList headerBean;

	/**
	 * クリア処理.
	 */
	protected void clear() {
		setDirectionNo(null);
		setDirectionStatus(null);
		setRecipeId(null);
		setRecipeCd(null);
		setRecipeVersion(null);
		setProductionLine(null);
		setCompoundTankNo(null);
		setHoldTankNo(null);
		setDissolutionTankNo(null);
		setItemCd(null);
		setPlanedQty(null);
		setPlanedSdate(null);
		setPlanedEdate(null);
		setDirtyFlg(null);
		setShipperDivision(null);
		setOtherCompanyCd1(null);
		setProductionLineName(null);
		setItemName(null);
		setWaterWeight(null);
		setDirectionStatusName(null);
		setUnitOfOperationManagement(null);
		setUnitName(null);
		setTabId(null);
		setDirectionDivision(null);
		setShipperDivisionLabel(null);
		setHeaderBean(null);
	}

	//インスタンスメソッド---------------------------------------------------------

	/**
	 * 指図番号取得
	 * @return String 指図番号
	*/
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 指図番号設定
	 * @param directionNo 指図番号
	*/
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}


	/**
	 * 指図ステータス
	 * @return BigDecimal 指図ステータス
	 */
	public String getDirectionStatus() {
		return this.directionStatus;
	}

	/**
	 * 指図ステータス
	 * @param directionStatus 指図ステータス
	 */
	public void setDirectionStatus(final String directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * レシピインデックス取得
	 * @return BigDecimal レシピインデックス
	*/
	public String getRecipeId() {
		return this.recipeId;
	}

	/**
	 * レシピインデックス設定
	 * @param recipeId レシピインデックス
	*/
	public void setRecipeId(final String recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * レシピコード取得
	 * @return String レシピコード
	*/
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * レシピコード設定
	 * @param recipeCd レシピコード
	*/
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * レシピバージョン取得
	 * @return BigDecimal レシピバージョン
	*/
	public String getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * レシピバージョン設定
	 * @param recipeVersion レシピバージョン
	*/
	public void setRecipeVersion(final String recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * 生産ライン取得
	 * @return String 生産ライン
	*/
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産ライン設定
	 * @param productionLine 生産ライン
	*/
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 調合タンクNO取得
	 * @return String 調合タンクNO
	*/
	public String getCompoundTankNo() {
		return this.compoundTankNo;
	}

	/**
	 * 調合タンクNO設定
	 * @param compoundTankNo 調合タンクNO
	*/
	public void setCompoundTankNo(final String compoundTankNo) {
		this.compoundTankNo = compoundTankNo;
	}

	/**
	 * ホールドタンクNO取得
	 * @return String ホールドタンクNO
	*/
	public String getHoldTankNo() {
		return this.holdTankNo;
	}

	/**
	 * ホールドタンクNO設定
	 * @param holdTankNo ホールドタンクNO
	*/
	public void setHoldTankNo(final String holdTankNo) {
		this.holdTankNo = holdTankNo;
	}

	/**
	 * 予備溶解タンクNO取得
	 * @return String 予備溶解タンクNO
	*/
	public String getDissolutionTankNo() {
		return this.dissolutionTankNo;
	}

	/**
	 * 予備溶解タンクNO設定
	 * @param dissolutionTankNo 予備溶解タンクNO
	*/
	public void setDissolutionTankNo(final String dissolutionTankNo) {
		this.dissolutionTankNo = dissolutionTankNo;
	}

	/**
	 * 主要製品コード取得
	 * @return String 主要製品コード
	*/
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 主要製品コード設定
	 * @param itemCd 主要製品コード
	*/
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 予定生産量|仕込数量取得
	 * @return BigDecimal 予定生産量|仕込数量
	*/
	public String getPlanedQty() {
		return this.planedQty;
	}

	/**
	 * 予定生産量|仕込数量設定
	 * @param planedQty 予定生産量|仕込数量
	*/
	public void setPlanedQty(final String planedQty) {
		this.planedQty = planedQty;
	}


	/**
	 * 開始予定日時取得
	 * @return Timestamp 開始予定日時
	*/
	public Timestamp getPlanedSdate() {
		return this.planedSdate;
	}

	/**
	 * 開始予定日時設定
	 * @param planedSdate 開始予定日時
	*/
	public void setPlanedSdate(final Timestamp planedSdate) {
		this.planedSdate = planedSdate;
	}

	/**
	 * 終了予定日時取得
	 * @return Timestamp 終了予定日時
	*/
	public Timestamp getPlanedEdate() {
		return this.planedEdate;
	}

	/**
	 * 終了予定日時設定
	 * @param planedEdate 終了予定日時
	*/
	public void setPlanedEdate(final Timestamp planedEdate) {
		this.planedEdate = planedEdate;
	}

	/**
	 * 変更フラグを取得します。
	 * @return 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 荷主を取得します。
	 * @return 荷主
	 */
	public String getShipperDivision() {
		return shipperDivision;
	}

	/**
	 * 荷主を設定します。
	 * @param shipperDivision 荷主
	 */
	public void setShipperDivision(final String shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * 他社コード1 を取得します。
	 * @return 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード1 を設定します。
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 生産ライン名称を取得します。
	 * @return 生産ライン名称
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 生産ライン名称を設定します。
	 * @param productionLineName 生産ライン名称
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * 品目名称を取得します。
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 洗浄水絶対重量計を取得します。
	 * @return 洗浄水絶対重量計
	 */
	public String getWaterWeight() {
		return waterWeight;
	}

	/**
	 * 洗浄水絶対重量計を設定します。
	 * @param waterWeight 洗浄水絶対重量計
	 */
	public void setWaterWeight(final String waterWeight) {
		this.waterWeight = waterWeight;
	}

	/**
	 * 指図ステータス名称を取得します。
	 * @return 指図ステータス名称
	 */
	public String getDirectionStatusName() {
		return directionStatusName;
	}

	/**
	 * 指図ステータス名称を設定します。
	 * @param directionStatusName 指図ステータス名称
	 */
	public void setDirectionStatusName(final String directionStatusName) {
		this.directionStatusName = directionStatusName;
	}

	/**
	 * 品目単位コードを取得します。
	 * @return 品目単位コード
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 品目単位コードを設定します。
	 * @param unitOfOperationManagement 品目単位コード
	 */
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 品目単位名称を取得します。
	 * @return 品目単位名称
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 品目単位名称を設定します。
	 * @param unitName 品目単位名称
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * タブIDを取得します。
	 * @return タブID
	 */
	public String getTabId() {
		return tabId;
	}

	/**
	 * タブIDを設定します。
	 * @param tabId タブID
	 */
	public void setTabId(final String tabId) {
		this.tabId = tabId;
	}

	/**
	 * 指図区分を取得します。
	 * @return 指図区分
	 */
	public String getDirectionDivision() {
		return directionDivision;
	}

	/**
	 * 指図区分を設定します。
	 * @param directionDivision 指図区分
	 */
	public void setDirectionDivision(final String directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 荷主ラベルを取得します。
	 * @return 荷主ラベル
	 */
	public String getShipperDivisionLabel() {
		return shipperDivisionLabel;
	}

	/**
	 * 荷主ラベルを設定します。
	 * @param shipperDivisionLabel 荷主ラベル
	 */
	public void setShipperDivisionLabel(final String shipperDivisionLabel) {
		this.shipperDivisionLabel = shipperDivisionLabel;
	}

	/**
	 * 表示時処方ヘッダー情報を取得します。
	 * @return 表示時処方ヘッダー情報
	 */
	public DirectionDirectionHeaderList getHeaderBean() {
		return headerBean;
	}

	/**
	 * 表示時処方ヘッダー情報を設定します。
	 * @param headerBean 表示時処方ヘッダー情報
	 */
	public void setHeaderBean(final DirectionDirectionHeaderList headerBean) {
		this.headerBean = headerBean;
	}


}
