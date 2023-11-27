/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.sql.Timestamp;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 製造実績-共通抽象 Formクラス.
 * @author tosco
 */
public abstract class AbstractRdirectionForm extends AbstractForm {

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
	/** 基本処方名称 */
	private String recipeName;
	/** 予定生産量|仕込数量 */
	private String planedQty;
	/** 開始実績日時 */
	private Timestamp resultSdate;
	/** 終了実績日時 */
	private Timestamp resultEdate;
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
	/** 実績生産量 */
	private String resultQty;
	/** 製造担当者 */
	private String seizotantocode;
	/** 調合タンク洗浄担当者 */
	private String senjotantocode;
	/** 調合タンク滅菌作業担当者 */
	private String mekkintantocode;
	/** 製造担当者名 */
	private String seizotantoName;
	/** 調合タンク洗浄担当者名 */
	private String senjotantoName;
	/** 調合タンク滅菌作業担当者名 */
	private String mekkintantoName;
	/** 表示時処方ヘッダー情報 */
	private RdirectionDirectionHeaderList headerBean;
	/** 用途 */
	private String recipeUse;



	/**
	 * クリア処理.
	 */
	protected void clear() {
		setDirectionNo(null);
		setDirectionStatus(null);
		setRecipeId(null);
		setRecipeCd(null);
		setRecipeVersion(null);
		setRecipeName(null);
		setProductionLine(null);
		setCompoundTankNo(null);
		setHoldTankNo(null);
		setDissolutionTankNo(null);
		setItemCd(null);
		setPlanedQty(null);
		setResultSdate(null);
		setResultEdate(null);
		setDirtyFlg(null);
		setShipperDivision(null);
		setOtherCompanyCd1(null);
		setProductionLineName(null);
		setItemName(null);
		setDirectionStatusName(null);
		setUnitOfOperationManagement(null);
		setUnitName(null);
		setTabId(null);
		setDirectionDivision(null);
		setShipperDivisionLabel(null);
		setResultQty(null);
		setSeizotantocode(null);
		setSenjotantocode(null);
		setMekkintantocode(null);
		setSeizotantoName(null);
		setSenjotantoName(null);
		setMekkintantoName(null);
		setHeaderBean(null);
		setRecipeUse(RdirectionConst.OPERATION_RECIPE_USE_PRODUCT); //用途(製造)
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
	 * 基本処方名称取得.
	 * @return String 基本処方名称
	 */
	public String getRecipeName() {
		return this.recipeName;
	}

	/**
	 * 基本処方名称設定.
	 * @param recipeName 基本処方名称
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
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
	 * 開始実績日時取得
	 * @return Timestamp 開始実績日時
	*/
	public Timestamp getResultSdate() {
		return this.resultSdate;
	}

	/**
	 * 開始実績日時設定
	 * @param resultSdate 開始実績日時
	*/
	public void setResultSdate(final Timestamp resultSdate) {
		this.resultSdate = resultSdate;
	}

	/**
	 * 終了実績日時取得
	 * @return Timestamp 終了実績日時
	*/
	public Timestamp getResultEdate() {
		return this.resultEdate;
	}

	/**
	 * 終了実績日時設定
	 * @param resultEdate 終了実績日時
	*/
	public void setResultEdate(final Timestamp resultEdate) {
		this.resultEdate = resultEdate;
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
	 * 実績生産量を取得します。
	 * @return 実績生産量
	 */
	public String getResultQty() {
		return resultQty;
	}

	/**
	 * 実績生産量を設定します。
	 * @param resultQty 実績生産量
	 */
	public void setResultQty(final String resultQty) {
		this.resultQty = resultQty;
	}

	/**
	 * 製造担当者を取得します。
	 * @return 製造担当者
	 */
	public String getSeizotantocode() {
		return seizotantocode;
	}

	/**
	 * 製造担当者を設定します。
	 * @param seizotantocode 製造担当者
	 */
	public void setSeizotantocode(final String seizotantocode) {
		this.seizotantocode = seizotantocode;
	}

	/**
	 * 調合タンク洗浄担当者を取得します。
	 * @return 調合タンク洗浄担当者
	 */
	public String getSenjotantocode() {
		return senjotantocode;
	}

	/**
	 * 調合タンク洗浄担当者を設定します。
	 * @param senjotantocode 調合タンク洗浄担当者
	 */
	public void setSenjotantocode(final String senjotantocode) {
		this.senjotantocode = senjotantocode;
	}

	/**
	 * 調合タンク滅菌作業担当者を取得します。
	 * @return 調合タンク滅菌作業担当者
	 */
	public String getMekkintantocode() {
		return mekkintantocode;
	}

	/**
	 * 調合タンク滅菌作業担当者を設定します。
	 * @param mekkintantocode 調合タンク滅菌作業担当者
	 */
	public void setMekkintantocode(final String mekkintantocode) {
		this.mekkintantocode = mekkintantocode;
	}

	/**
	 * 製造担当者名を取得します。
	 * @return 製造担当者名
	 */
	public String getSeizotantoName() {
		return seizotantoName;
	}

	/**
	 * 製造担当者名を設定します。
	 * @param seizotantoName 製造担当者名
	 */
	public void setSeizotantoName(final String seizotantoName) {
		this.seizotantoName = seizotantoName;
	}

	/**
	 * 調合タンク洗浄担当者名を取得します。
	 * @return 調合タンク洗浄担当者名
	 */
	public String getSenjotantoName() {
		return senjotantoName;
	}

	/**
	 * 調合タンク洗浄担当者名を設定します。
	 * @param senjotantoName 調合タンク洗浄担当者名
	 */
	public void setSenjotantoName(final String senjotantoName) {
		this.senjotantoName = senjotantoName;
	}

	/**
	 * 調合タンク滅菌作業担当者名を取得します。
	 * @return 調合タンク滅菌作業担当者名
	 */
	public String getMekkintantoName() {
		return mekkintantoName;
	}

	/**
	 * 調合タンク滅菌作業担当者名を設定します。
	 * @param mekkintantoName 調合タンク滅菌作業担当者名
	 */
	public void setMekkintantoName(final String mekkintantoName) {
		this.mekkintantoName = mekkintantoName;
	}

	/**
	 * 表示時処方ヘッダー情報を取得します。
	 * @return 表示時処方ヘッダー情報
	 */
	public RdirectionDirectionHeaderList getHeaderBean() {
		return headerBean;
	}
	/**
	 * 表示時処方ヘッダー情報を設定します。
	 * @param headerBean 表示時処方ヘッダー情報
	 */
	public void setHeaderBean(final RdirectionDirectionHeaderList headerBean) {
		this.headerBean = headerBean;
	}

	/**
	 * 用途取得
	 * @return String 用途
	 */
	public String getRecipeUse() {
		return this.recipeUse;
	}

	/**
	 * 用途設定
	 * @param recipeUse 用途
	 */
	public void setRecipeUse(final String recipeUse) {
		this.recipeUse = recipeUse;
	}
}
