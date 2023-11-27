/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 処方ヘッダデータ格納クラス.
 * 
 * @author tosco
 */
public class RecipeHeaderListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeHeaderListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "RECIPE_HEADER";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション recipeType */
	public static final String recipeType_COLUMN = "RECIPE_TYPE";

	/** COLUMNアノテーション recipeDescription */
	public static final String recipeDescription_COLUMN = "RECIPE_DESCRIPTION";

	/** COLUMNアノテーション recipeMemo */
	public static final String recipeMemo_COLUMN = "RECIPE_MEMO";

	/** COLUMNアノテーション recipeStatus */
	public static final String recipeStatus_COLUMN = "RECIPE_STATUS";

	/** COLUMNアノテーション recipeUse */
	public static final String recipeUse_COLUMN = "RECIPE_USE";

	/** COLUMNアノテーション recipePriority */
	public static final String recipePriority_COLUMN = "RECIPE_PRIORITY";

	/** COLUMNアノテーション originalRecipeId */
	public static final String originalRecipeId_COLUMN = "ORIGINAL_RECIPE_ID";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション product */
	public static final String product_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション minQty */
	public static final String minQty_COLUMN = "MIN_QTY";

	/** COLUMNアノテーション maxQty */
	public static final String maxQty_COLUMN = "MAX_QTY";

	/** COLUMNアノテーション stdQty */
	public static final String stdQty_COLUMN = "STD_QTY";

	/** COLUMNアノテーション unitQty */
	public static final String unitQty_COLUMN = "UNIT_QTY";

	/** COLUMNアノテーション cost */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション processLoss */
	public static final String processLoss_COLUMN = "PROCESS_LOSS";

	/** COLUMNアノテーション startDate */
	public static final String startDate_COLUMN = "START_DATE";

	/** COLUMNアノテーション endDate */
	public static final String endDate_COLUMN = "END_DATE";

	/** COLUMNアノテーション deleteFlag */
	public static final String deleteFlag_COLUMN = "DELETE_FLAG";

	/** COLUMNアノテーション printFlag */
	public static final String printFlag_COLUMN = "PRINT_FLAG";

	/** COLUMNアノテーション printDate */
	public static final String printDate_COLUMN = "PRINT_DATE";

	/** COLUMNアノテーション printTantoCd */
	public static final String printTantoCd_COLUMN = "PRINT_TANTO_CD";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション appTantoCd */
	public static final String appTantoCd_COLUMN = "APP_TANTO_CD";

	/** COLUMNアノテーション appDate */
	public static final String appDate_COLUMN = "APP_DATE";

	/** COLUMNアノテーション lastAppTantoCd */
	public static final String lastAppTantoCd_COLUMN = "LAST_APP_TANTO_CD";

	/** COLUMNアノテーション lastAppDate */
	public static final String lastAppDate_COLUMN = "LAST_APP_DATE";

	/** COLUMNアノテーション recipeName */
	public static final String recipeName_COLUMN = "RECIPE_NAME";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCdCOLUMN = "SECTION_CD";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** TIMESTAMPアノテーション updateDate */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	//
	// インスタンスフィールド
	//

	/** レシピインデックス */
	private BigDecimal recipeId;

	/** レシピコード */
	private String recipeCd;

	/** レシピバージョン */
	private BigDecimal recipeVersion;

	/** レシピタイプ 1:General,2:Site,3:Master */
	private BigDecimal recipeType;

	/** 注釈 */
	private String recipeDescription;

	/** 備考 */
	private String recipeMemo;

	/** ステータス 0..ラボ使用 1..試作用 2..一般使用 3..廃棄 4..改訂中 5..保留中 */
	private BigDecimal recipeStatus;

	/** 用途 1:Planning,2:Costing,3:製造,4:包装 */
	private BigDecimal recipeUse;

	/** 優先度 */
	private BigDecimal recipePriority;

	/** 原処方レシピインデックス */
	private BigDecimal originalRecipeId;

	/** PR生産ライン */
	private String productionLine;

	/** 主要製品コード(品目コード） */
	private String product;

	/** 最小生産量 */
	private BigDecimal minQty;

	/** 最大生産量 */
	private BigDecimal maxQty;

	/** 標準生産量 */
	private BigDecimal stdQty;

	/** 単位生産量 */
	private BigDecimal unitQty;

	/** 原価 */
	private BigDecimal cost;

	/** ロス率 */
	private BigDecimal processLoss;

	/** 有効開始日 */
	private Timestamp startDate;

	/** 有効終了日 */
	private Timestamp endDate;

	/** 削除フラグ */
	private BigDecimal deleteFlag;

	/** 処方箋印刷フラグ|0:未発行 1:発行 */
	private BigDecimal printFlag;

	/** 処方印刷日 */
	private Timestamp printDate;

	/** 処方印刷者 */
	private String printTantoCd;

	/** 承認ステータス 1:入力中 2:承認依頼中 3:承認済み */
	private BigDecimal approvalStatus;

	/** 承認者ID */
	private String appTantoCd;

	/** 承認日時 */
	private Timestamp appDate;

	/** 最終承認者ID */
	private String lastAppTantoCd;

	/** 最終承認日時 */
	private Timestamp lastAppDate;

	/** 基本処方名称 */
	private String recipeName;

	/** 会計部門コード */
	private String sectionCd;

	/** 登録者ID */
	private String inputorCd;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 更新者ID */
	private String updatorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * レシピインデックス取得
	 * @return BigDecimal レシピインデックス
	 */
	public BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * レシピインデックス設定
	 * @param recipeId レシピインデックス
	 */
	public void setRecipeId(final BigDecimal recipeId) {
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
	public BigDecimal getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * レシピバージョン設定
	 * @param recipeVersion レシピバージョン
	 */
	public void setRecipeVersion(final BigDecimal recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * レシピタイプ－1:General,2:Site,3:Master取得
	 * @return BigDecimal 1:General,2:Site,3:Master
	 */
	public BigDecimal getRecipeType() {
		return this.recipeType;
	}

	/**
	 * レシピタイプ－1:General,2:Site,3:Master設定
	 * @param recipeType 1:General,2:Site,3:Master
	 */
	public void setRecipeType(final BigDecimal recipeType) {
		this.recipeType = recipeType;
	}

	/**
	 * 注釈取得
	 * @return String 注釈
	 */
	public String getRecipeDescription() {
		return this.recipeDescription;
	}

	/**
	 * 注釈設定
	 * @param recipeDescription 注釈
	 */
	public void setRecipeDescription(final String recipeDescription) {
		this.recipeDescription = recipeDescription;
	}

	/**
	 * 備考取得
	 * @return String 備考
	 */
	public String getRecipeMemo() {
		return this.recipeMemo;
	}

	/**
	 * 備考設定
	 * @param recipeMemo 備考
	 */
	public void setRecipeMemo(final String recipeMemo) {
		this.recipeMemo = recipeMemo;
	}

	/**
	 * レシピステータス－0..ラボ使用 1..試作用 2..一般使用 3..廃棄 4..改訂中 5..保留中取得
	 * @return BigDecimal 0..ラボ使用 1..試作用 2..一般使用 3..廃棄 4..改訂中 5..保留中
	 */
	public BigDecimal getRecipeStatus() {
		return this.recipeStatus;
	}

	/**
	 * レシピステータス－0..ラボ使用 1..試作用 2..一般使用 3..廃棄 4..改訂中 5..保留中設定
	 * @param recipeStatus 0..ラボ使用 1..試作用 2..一般使用 3..廃棄 4..改訂中 5..保留中
	 */
	public void setRecipeStatus(final BigDecimal recipeStatus) {
		this.recipeStatus = recipeStatus;
	}

	/**
	 * 用途－1:Planning,2:Costing,3:製造,4:包装取得
	 * @return BigDecimal 1:Planning,2:Costing,3:製造,4:包装
	 */
	public BigDecimal getRecipeUse() {
		return this.recipeUse;
	}

	/**
	 * 用途－1:Planning,2:Costing,3:製造,4:包装設定
	 * @param recipeUse 1:Planning,2:Costing,3:製造,4:包装
	 */
	public void setRecipeUse(final BigDecimal recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * 優先度取得
	 * @return BigDecimal 優先度
	 */
	public BigDecimal getRecipePriority() {
		return this.recipePriority;
	}

	/**
	 * 優先度設定
	 * @param recipePriority 優先度
	 */
	public void setRecipePriority(final BigDecimal recipePriority) {
		this.recipePriority = recipePriority;
	}

	/**
	 * 原処方レシピインデックス取得
	 * @return BigDecimal 原処方レシピインデックス
	 */
	public BigDecimal getOriginalRecipeId() {
		return this.originalRecipeId;
	}

	/**
	 * 原処方レシピインデックス設定
	 * @param originalRecipeId 原処方レシピインデックス
	 */
	public void setOriginalRecipeId(final BigDecimal originalRecipeId) {
		this.originalRecipeId = originalRecipeId;
	}

	/**
	 * PR生産ライン取得
	 * @return String PR生産ライン
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * PR生産ライン設定
	 * @param productionLine PR生産ライン
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 主要製品コード(品目コード）取得
	 * @return String 主要製品コード(品目コード）
	 */
	public String getProduct() {
		return this.product;
	}

	/**
	 * 主要製品コード(品目コード）設定
	 * @param product 主要製品コード(品目コード）
	 */
	public void setProduct(final String product) {
		this.product = product;
	}

	/**
	 * 最小生産量取得
	 * @return BigDecimal 最小生産量
	 */
	public BigDecimal getMinQty() {
		return this.minQty;
	}

	/**
	 * 最小生産量設定
	 * @param minQty 最小生産量
	 */
	public void setMinQty(final BigDecimal minQty) {
		this.minQty = minQty;
	}

	/**
	 * 最大生産量取得
	 * @return BigDecimal 最大生産量
	 */
	public BigDecimal getMaxQty() {
		return this.maxQty;
	}

	/**
	 * 最大生産量設定
	 * @param maxQty 最大生産量
	 */
	public void setMaxQty(final BigDecimal maxQty) {
		this.maxQty = maxQty;
	}

	/**
	 * 標準生産量取得
	 * @return BigDecimal 標準生産量
	 */
	public BigDecimal getStdQty() {
		return this.stdQty;
	}

	/**
	 * 標準生産量設定
	 * @param stdQty 標準生産量
	 */
	public void setStdQty(final BigDecimal stdQty) {
		this.stdQty = stdQty;
	}

	/**
	 * 単位生産量取得
	 * @return BigDecimal 単位生産量
	 */
	public BigDecimal getUnitQty() {
		return this.unitQty;
	}

	/**
	 * 単位生産量設定
	 * @param unitQty 単位生産量
	 */
	public void setUnitQty(final BigDecimal unitQty) {
		this.unitQty = unitQty;
	}

	/**
	 * 原価取得
	 * @return BigDecimal 原価
	 */
	public BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * 原価設定
	 * @param cost 原価
	 */
	public void setCost(final BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * ロス率取得
	 * @return BigDecimal ロス率
	 */
	public BigDecimal getProcessLoss() {
		return this.processLoss;
	}

	/**
	 * ロス率設定
	 * @param processLoss ロス率
	 */
	public void setProcessLoss(final BigDecimal processLoss) {
		this.processLoss = processLoss;
	}

	/**
	 * 有効開始日取得
	 * @return Timestamp 有効開始日
	 */
	public Timestamp getStartDate() {
		return this.startDate;
	}

	/**
	 * 有効開始日設定
	 * @param startDate 有効開始日
	 */
	public void setStartDate(final Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * 有効終了日取得
	 * @return Timestamp 有効終了日
	 */
	public Timestamp getEndDate() {
		return this.endDate;
	}

	/**
	 * 有効終了日設定
	 * @param endDate 有効終了日
	 */
	public void setEndDate(final Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * 使いますか？取得
	 * @return BigDecimal 使いますか？
	 */
	public BigDecimal getDeleteFlag() {
		return this.deleteFlag;
	}

	/**
	 * 使いますか？設定
	 * @param deleteFlag 使いますか？
	 */
	public void setDeleteFlag(final BigDecimal deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * 処方箋印刷フラグ|0:未発行 1:発行取得
	 * @return BigDecimal 処方箋印刷フラグ|0:未発行 1:発行
	 */
	public BigDecimal getPrintFlag() {
		return this.printFlag;
	}

	/**
	 * 処方箋印刷フラグ|0:未発行 1:発行設定
	 * @param printFlag 処方箋印刷フラグ|0:未発行 1:発行
	 */
	public void setPrintFlag(final BigDecimal printFlag) {
		this.printFlag = printFlag;
	}

	/**
	 * 処方印刷日取得
	 * @return Timestamp 処方印刷日
	 */
	public Timestamp getPrintDate() {
		return this.printDate;
	}

	/**
	 * 処方印刷日設定
	 * @param printDate 処方印刷日
	 */
	public void setPrintDate(final Timestamp printDate) {
		this.printDate = printDate;
	}

	/**
	 * 処方印刷者取得
	 * @return String 処方印刷者
	 */
	public String getPrintTantoCd() {
		return this.printTantoCd;
	}

	/**
	 * 処方印刷者設定
	 * @param printTantoCd 処方印刷者
	 */
	public void setPrintTantoCd(final String printTantoCd) {
		this.printTantoCd = printTantoCd;
	}

	/**
	 * 承認ステータス 1:入力中 2:承認依頼中 3:承認済み取得
	 * @return BigDecimal 承認ステータス 1:入力中 2:承認依頼中 3:承認済み
	 */
	public BigDecimal getApprovalStatus() {
		return this.approvalStatus;
	}

	/**
	 * 承認ステータス 1:入力中 2:承認依頼中 3:承認済み設定
	 * @param approvalStatus 承認ステータス 1:入力中 2:承認依頼中 3:承認済み
	 */
	public void setApprovalStatus(final BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * 承認者ID取得
	 * @return String 承認者ID
	 */
	public String getAppTantoCd() {
		return this.appTantoCd;
	}

	/**
	 * 承認者ID設定
	 * @param appTantoCd 承認者ID
	 */
	public void setAppTantoCd(final String appTantoCd) {
		this.appTantoCd = appTantoCd;
	}

	/**
	 * 承認日時取得
	 * @return Timestamp 承認日時
	 */
	public Timestamp getAppDate() {
		return this.appDate;
	}

	/**
	 * 承認日時設定
	 * @param appDate 承認日時
	 */
	public void setAppDate(final Timestamp appDate) {
		this.appDate = appDate;
	}

	/**
	 * 最終承認者ID取得
	 * @return String 最終承認者ID
	 */
	public String getLastAppTantoCd() {
		return this.lastAppTantoCd;
	}

	/**
	 * 最終承認者ID設定
	 * @param lastAppTantoCd 最終承認者ID
	 */
	public void setLastAppTantoCd(final String lastAppTantoCd) {
		this.lastAppTantoCd = lastAppTantoCd;
	}

	/**
	 * 最終承認日時取得
	 * @return Timestamp 最終承認日時
	 */
	public Timestamp getLastAppDate() {
		return this.lastAppDate;
	}

	/**
	 * 最終承認日時設定
	 * @param lastAppDate 最終承認日時
	 */
	public void setLastAppDate(final Timestamp lastAppDate) {
		this.lastAppDate = lastAppDate;
	}

	/**
	 * 基本処方名称取得
	 * @return String 基本処方名称
	 */
	public String getRecipeName() {
		return this.recipeName;
	}

	/**
	 * 基本処方名称設定
	 * @param recipeName 基本処方名称
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	 */
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * sectionCdを取得します。
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * sectionCdを設定します。
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}
}
