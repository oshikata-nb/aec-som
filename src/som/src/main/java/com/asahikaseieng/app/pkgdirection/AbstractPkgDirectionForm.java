/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装指図-共通抽象 Formクラス.
 * @author tosco
 */
public abstract class AbstractPkgDirectionForm extends AbstractForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//

	/** 変更フラグ **/
	private String dirtyFlg;

	/** 指図区分 **/
	private String directionDivision;

	/** 包装指図番号 **/
	private String directionNo;

	/** 生産工場 **/
	private String productionLine;

	/** 生産工場名称 **/
	private String productionLineName;

	/** 包装ライン **/
	private String packageLine;

	/** 包装ライン名称 **/
	private String packageLineName;

	/** 充填タンクNo **/
	private String filltankNo;

	/** 充填タンク名称 **/
	private String filltankName;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 親品目コード */
	private String parentItemCd;

	/** レシピインデックス */
	private String recipeId;

	/** レシピコード */
	private String recipeCd;

	/** 基本処方コード(レシピコード+レシピバージョン) */
	private String recipeCdVersion;

	/** 基本処方名称 */
	private String recipeName;

	/** レシピバージョン */
	private BigDecimal recipeVersion;

	/** 生産予定数量 */
	private String planedQty;

	/** 単位 */
	private String unitName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 入数 */
	private BigDecimal numberOfInsertions;

	/** 包装開始予定日時 */
	private Timestamp planedSdate;

	/** 包装終了予定日時 */
	private Timestamp planedEdate;

	/** ロット番号 */
	private String lotNo;

	/** ステータス */
	private String directionStatus;

	/** タブID */
	private String tabId;

	/** 製品入出庫実績存在フラグ(0:無 1:有) */
	private String jissekiFlag;

	/** 用途 */
	private String recipeUse;

	/** 製造指図ヘッダー更新日付 */
	private Timestamp headerUpdateDate;

	/**
	 * クリア処理.
	 */
	protected void clear() {
		// 指図区分
		this.setDirectionDivision(null);
		// 包装指図番号
		this.setDirectionNo(null);
		// 生産工場
		this.setProductionLine(null);
		// 生産工場名称
		this.setProductionLineName(null);
		// 包装ライン
		this.setPackageLine(null);
		// 包装ライン名称
		this.setPackageLineName(null);
		// 充填タンクNo
		this.setFilltankNo(null);
		// 充填タンク名称
		this.setFilltankName(null);
		// 品目コード
		this.setItemCd(null);
		// 品目名称
		this.setItemName(null);
		// 他社コード１
		this.setOtherCompanyCd1(null);
		// 親品目コード
		this.setParentItemCd(null);
		// レシピインデックス
		this.setRecipeId(null);
		// レシピコード
		this.setRecipeCd(null);
		// 基本処方コード(レシピコード+レシピバージョン)
		this.setRecipeCdVersion(null);
		// 基本処方名称
		this.setRecipeName(null);
		// レシピバージョン
		this.setRecipeVersion(null);
		// 生産予定数量
		this.setPlanedQty(null);
		// 単位
		this.setUnitName(null);
		// 荷姿
		this.setStyleOfPacking(null);
		// 包装開始予定日時
		this.setPlanedSdate(null);
		// 包装終了予定日時
		this.setPlanedEdate(null);
		// ロットNo
		this.setLotNo(null);
		// タブID
		this.setTabId(null);
		// ステータス
		this.setDirectionStatus(null);
		// 製品入出庫実績存在フラグ
		this.setJissekiFlag(null);
		// 用途
		this.setRecipeUse(PkgDirectionConst.OPERATION_RECIPE_USE_PACKAGE);
		// 製造指図ヘッダー更新日付
		this.setHeaderUpdateDate(null);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 変更フラグを取得します。
	 * @return String 変更フラグ
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
	 * 指図区分取得.
	 * @return String 指図区分
	 */
	public String getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分設定.
	 * @param directionDivision 指図区分
	 */
	public void setDirectionDivision(final String directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 包装指図番号取得.
	 * @return String 包装指図番号
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 包装指図番号設定.
	 * @param directionNo 包装指図番号
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 生産工場取得.
	 * @return String 生産工場
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産工場設定.
	 * @param productionLine 生産工場
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 生産工場名称を取得します。
	 * @return 生産工場名称
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 生産工場名称を設定します。
	 * @param productionLineName 生産工場名称
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * 包装ライン取得.
	 * @return String 包装ライン
	 */
	public String getPackageLine() {
		return this.packageLine;
	}

	/**
	 * 包装ライン設定.
	 * @param packageLine 包装ライン
	 */
	public void setPackageLine(final String packageLine) {
		this.packageLine = packageLine;
	}

	/**
	 * 包装ライン名称取得.
	 * @return String 包装ライン
	 */
	public String getPackageLineName() {
		return this.packageLineName;
	}

	/**
	 * 包装ライン名称設定.
	 * @param packageLineName 包装ライン
	 */
	public void setPackageLineName(final String packageLineName) {
		this.packageLineName = packageLineName;
	}

	/**
	 * 充填タンクNo取得.
	 * @return String 充填タンクNo
	 */
	public String getFilltankNo() {
		return this.filltankNo;
	}

	/**
	 * 充填タンクNo設定.
	 * @param filltankNo 充填タンクNo
	 */
	public void setFilltankNo(final String filltankNo) {
		this.filltankNo = filltankNo;
	}

	/**
	 * 充填タンクNo取得.
	 * @return String 充填タンクNo
	 */
	public String getFilltankName() {
		return this.filltankName;
	}

	/**
	 * 充填タンクNo設定.
	 * @param filltankName 充填タンクNo
	 */
	public void setFilltankName(final String filltankName) {
		this.filltankName = filltankName;
	}

	/**
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定.
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得.
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
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
	 * @return 品目名称
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位設定.
	 * @param unitOfOperationManagement 品目名称
	 */
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 他社コード１取得.
	 * @return 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１設定.
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 親品目コードを取得します。
	 * @return 親品目コード
	 */
	public String getParentItemCd() {
		return parentItemCd;
	}

	/**
	 * 親品目コードを設定します。
	 * @param parentItemCd 親品目コード
	 */
	public void setParentItemCd(final String parentItemCd) {
		this.parentItemCd = parentItemCd;
	}

	/**
	 * レシピインデックス取得.
	 * @return String レシピインデックス
	 */
	public String getRecipeId() {
		return this.recipeId;
	}

	/**
	 * レシピインデックス設定.
	 * @param strRecipeId レシピインデックス
	 */
	public void setRecipeId(final String strRecipeId) {
		this.recipeId = strRecipeId;
	}

	/**
	 * レシピコード取得.
	 * @return String レシピコード
	 */
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * レシピコード設定.
	 * @param recipeCd レシピコード
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * 基本処方コード(レシピコード+レシピバージョン)取得.
	 * @return String 基本処方コード(レシピコード+レシピバージョン)
	 */
	public String getRecipeCdVersion() {
		return this.recipeCdVersion;
	}

	/**
	 * 基本処方コード(レシピコード+レシピバージョン)設定.
	 * @param recipeCdVersion 基本処方コード(レシピコード+レシピバージョン)
	 */
	public void setRecipeCdVersion(final String recipeCdVersion) {
		this.recipeCdVersion = recipeCdVersion;
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
	 * レシピバージョン取得.
	 * @return BigDecimal レシピバージョン
	 */
	public BigDecimal getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * レシピバージョン設定.
	 * @param recipeVersion レシピバージョン
	 */
	public void setRecipeVersion(final BigDecimal recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * 生産予定数量取得.
	 * @return 生産予定数量
	 */
	public String getPlanedQty() {
		return planedQty;
	}

	/**
	 * 生産予定数量設定.
	 * @param planedQty 生産予定数量
	 */
	public void setPlanedQty(final String planedQty) {
		this.planedQty = planedQty;
	}

	/**
	 * 単位取得.
	 * @return 単位
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位設定.
	 * @param unitName 単位
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 荷姿取得.
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿設定.
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 入数取得.
	 * @return BigDecimal 入数
	 */
	public BigDecimal getNumberOfInsertions() {
		return this.numberOfInsertions;
	}

	/**
	 * 入数設定.
	 * @param numberOfInsertions 入数
	 */
	public void setNumberOfInsertions(final BigDecimal numberOfInsertions) {
		this.numberOfInsertions = numberOfInsertions;
	}

	/**
	 * 包装開始予定日時取得.
	 * @return String 包装開始予定日時
	 */
	public String getStrPlanedSdate() {
		return AecDateUtils.dateFormat(this.planedSdate, "yyyy/MM/dd HH:mm");
	}

	/**
	 * 包装開始予定日時取得.
	 * @return Timestamp 包装開始予定日時
	 */
	public Timestamp getPlanedSdate() {
		return planedSdate;
	}

	/**
	 * 包装開始予定日時設定.
	 * @param planedSdate 包装開始予定日時
	 */
	public void setPlanedSdate(final Timestamp planedSdate) {
		this.planedSdate = planedSdate;
	}

	/**
	 * 包装終了予定日時取得.
	 * @return Timestamp 包装終了予定日時
	 */
	public String getStrPlanedEdate() {
		return AecDateUtils.dateFormat(this.planedEdate, "yyyy/MM/dd HH:mm");
	}

	/**
	 * 包装終了予定日時取得.
	 * @return Timestamp 包装終了予定日時
	 */
	public Timestamp getPlanedEdate() {
		return planedEdate;
	}

	/**
	 * 包装終了予定日時設定.
	 * @param planedEdate 包装終了予定日時
	 */
	public void setPlanedEdate(final Timestamp planedEdate) {
		this.planedEdate = planedEdate;
	}

	/**
	 * ロットNo取得.
	 * @return ロットNo
	 */
	public String getLotNo() {
		return lotNo;
	}

	/**
	 * ロットNo設定.
	 * @param lotNo ロットNo
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
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
	 * ステータス取得.
	 * @return String ステータス
	 */
	public String getDirectionStatus() {
		return this.directionStatus;
	}

	/**
	 * ステータス設定.
	 * @param directionStatus ステータス
	 */
	public void setDirectionStatus(final String directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * 製品入出庫実績存在フラグ(0:無 1:有)取得.
	 * @return String 製品入出庫実績存在フラグ
	 */
	public String getJissekiFlag() {
		return this.jissekiFlag;
	}

	/**
	 * 製品入出庫実績存在フラグ(0:無 1:有)設定.
	 * @param jissekiFlag 製品入出庫実績存在フラグ
	 */
	public void setJissekiFlag(final String jissekiFlag) {
		this.jissekiFlag = jissekiFlag;
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

	/**
	 * 製造指図ヘッダー更新日付取得.
	 * @return Timestamp 製造指図ヘッダー更新日付
	 */
	public Timestamp getHeaderUpdateDate() {
		return this.headerUpdateDate;
	}

	/**
	 * 製造指図ヘッダー更新日付設定.
	 * @param headerUpdateDate 製造指図ヘッダー更新日付
	 */
	public void setHeaderUpdateDate(final Timestamp headerUpdateDate) {
		this.headerUpdateDate = headerUpdateDate;
	}
}
