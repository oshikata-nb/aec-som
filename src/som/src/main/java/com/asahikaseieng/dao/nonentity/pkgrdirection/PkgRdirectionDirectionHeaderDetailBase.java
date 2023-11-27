/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 包装実績－製造指図ヘッダー情報データ格納クラス.
 *
 * @author tosco
 */
public class PkgRdirectionDirectionHeaderDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionDirectionHeaderDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "DIRECTION_HEADER";

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション directionDate */
	public static final String directionDate_COLUMN = "DIRECTION_DATE";

	/** COLUMNアノテーション aspOrderNo */
	public static final String aspOrderNo_COLUMN = "ASP_ORDER_NO";

	/** COLUMNアノテーション directionStatus */
	public static final String directionStatus_COLUMN = "DIRECTION_STATUS";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション compoundTankNo */
	public static final String compoundTankNo_COLUMN = "COMPOUND_TANK_NO";

	/** COLUMNアノテーション holdTankNo */
	public static final String holdTankNo_COLUMN = "HOLD_TANK_NO";

	/** COLUMNアノテーション dissolutionTankNo */
	public static final String dissolutionTankNo_COLUMN = "DISSOLUTION_TANK_NO";

	/** COLUMNアノテーション filltankNo */
	public static final String filltankNo_COLUMN = "FILLTANK_NO";

	/** COLUMNアノテーション packageLine */
	public static final String packageLine_COLUMN = "PACKAGE_LINE";

	/** COLUMNアノテーション currentStepNo */
	public static final String currentStepNo_COLUMN = "CURRENT_STEP_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション planedQty */
	public static final String planedQty_COLUMN = "PLANED_QTY";

	/** COLUMNアノテーション resultQty */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション divideFlag */
	public static final String divideFlag_COLUMN = "DIVIDE_FLAG";

	/** COLUMNアノテーション nextPlanedQty */
	public static final String nextPlanedQty_COLUMN = "NEXT_PLANED_QTY";

	/** COLUMNアノテーション pdwResult */
	public static final String pdwResult_COLUMN = "PDW_RESULT";

	/** COLUMNアノテーション peocessLoss */
	public static final String peocessLoss_COLUMN = "PEOCESS_LOSS";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション planedSdate */
	public static final String planedSdate_COLUMN = "PLANED_SDATE";

	/** COLUMNアノテーション planedEdate */
	public static final String planedEdate_COLUMN = "PLANED_EDATE";

	/** COLUMNアノテーション resultSdate */
	public static final String resultSdate_COLUMN = "RESULT_SDATE";

	/** COLUMNアノテーション resultEdate */
	public static final String resultEdate_COLUMN = "RESULT_EDATE";

	/** COLUMNアノテーション steritSdate */
	public static final String steritSdate_COLUMN = "STERIT_SDATE";

	/** COLUMNアノテーション steritEdate */
	public static final String steritEdate_COLUMN = "STERIT_EDATE";

	/** COLUMNアノテーション mekkinTankTempMin */
	public static final String mekkinTankTempMin_COLUMN = "MEKKIN_TANK_TEMP_MIN";

	/** COLUMNアノテーション mekkinTankTempMax */
	public static final String mekkinTankTempMax_COLUMN = "MEKKIN_TANK_TEMP_MAX";

	/** COLUMNアノテーション haisuiCheck */
	public static final String haisuiCheck_COLUMN = "HAISUI_CHECK";

	/** COLUMNアノテーション stampFlag */
	public static final String stampFlag_COLUMN = "STAMP_FLAG";

	/** COLUMNアノテーション stampDate */
	public static final String stampDate_COLUMN = "STAMP_DATE";

	/** COLUMNアノテーション stampTantoCd */
	public static final String stampTantoCd_COLUMN = "STAMP_TANTO_CD";

	/** COLUMNアノテーション productLabelFlag */
	public static final String productLabelFlag_COLUMN = "PRODUCT_LABEL_FLAG";

	/** COLUMNアノテーション productLabelDate */
	public static final String productLabelDate_COLUMN = "PRODUCT_LABEL_DATE";

	/** COLUMNアノテーション productLabelTantoCd */
	public static final String productLabelTantoCd_COLUMN = "PRODUCT_LABEL_TANTO_CD";

	/** COLUMNアノテーション productRecordFlag */
	public static final String productRecordFlag_COLUMN = "PRODUCT_RECORD_FLAG";

	/** COLUMNアノテーション productRecordDate */
	public static final String productRecordDate_COLUMN = "PRODUCT_RECORD_DATE";

	/** COLUMNアノテーション productRecordTantoCd */
	public static final String productRecordTantoCd_COLUMN = "PRODUCT_RECORD_TANTO_CD";

	/** COLUMNアノテーション stockdissLabelFlag */
	public static final String stockdissLabelFlag_COLUMN = "STOCKDISS_LABEL_FLAG";

	/** COLUMNアノテーション stockdissLabelDate */
	public static final String stockdissLabelDate_COLUMN = "STOCKDISS_LABEL_DATE";

	/** COLUMNアノテーション stockdissLabelTantoCd */
	public static final String stockdissLabelTantoCd_COLUMN = "STOCKDISS_LABEL_TANTO_CD";

	/** COLUMNアノテーション certificationFlag */
	public static final String certificationFlag_COLUMN = "CERTIFICATION_FLAG";

	/** COLUMNアノテーション certificationDate */
	public static final String certificationDate_COLUMN = "CERTIFICATION_DATE";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション deleteFlag */
	public static final String deleteFlag_COLUMN = "DELETE_FLAG";

	/** COLUMNアノテーション seizotantocode */
	public static final String seizotantocode_COLUMN = "SEIZOTANTOCODE";

	/** COLUMNアノテーション senjotantocode */
	public static final String senjotantocode_COLUMN = "SENJOTANTOCODE";

	/** COLUMNアノテーション mekkintantocode */
	public static final String mekkintantocode_COLUMN = "MEKKINTANTOCODE";

	/** COLUMNアノテーション chogotankcondi */
	public static final String chogotankcondi_COLUMN = "CHOGOTANKCONDI";

	/** COLUMNアノテーション yobiyokaicondi */
	public static final String yobiyokaicondi_COLUMN = "YOBIYOKAICONDI";

	/** COLUMNアノテーション holdtankcondi */
	public static final String holdtankcondi_COLUMN = "HOLDTANKCONDI";

	/** COLUMNアノテーション totalJobtime */
	public static final String totalJobtime_COLUMN = "TOTAL_JOBTIME";

	/** COLUMNアノテーション enployJobtime */
	public static final String enployJobtime_COLUMN = "ENPLOY_JOBTIME";

	/** COLUMNアノテーション cooperJobtime */
	public static final String cooperJobtime_COLUMN = "COOPER_JOBTIME";

	/** COLUMNアノテーション appTantoCd */
	public static final String appTantoCd_COLUMN = "APP_TANTO_CD";

	/** COLUMNアノテーション appDate */
	public static final String appDate_COLUMN = "APP_DATE";

	/** COLUMNアノテーション lastAppTantoCd */
	public static final String lastAppTantoCd_COLUMN = "LAST_APP_TANTO_CD";

	/** COLUMNアノテーション lastAppDate */
	public static final String lastAppDate_COLUMN = "LAST_APP_DATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** TIMESTAMPアノテーション updateDate */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	//
	// インスタンスフィールド
	//

	/** 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図 */
	private BigDecimal directionDivision;

	/** 指図番号 */
	private String directionNo;

	/** 指図日時 */
	private Timestamp directionDate;

	/** アスプローバオーダーコード　*/
	private String aspOrderNo;

	/** 指図ステータス*/
	private BigDecimal directionStatus;

	/** レシピインデックス */
	private BigDecimal recipeId;

	/** レシピコード */
	private String recipeCd;

	/** レシピバージョン */
	private BigDecimal recipeVersion;

	/** 生産ライン */
	private String productionLine;

	/** 調合タンクNO */
	private String compoundTankNo;

	/** 充填タンクNO */
	private String filltankNo;

	/** 包装ライン */
	private String packageLine;

	/** 主要製品コード */
	private String itemCd;

	/** 予定生産量|仕込数量 */
	private BigDecimal planedQty;

	/** 実績生産量 */
	private BigDecimal resultQty;

	/** ロット番号 */
	private String lotNo;

	/** 開始予定日時 */
	private Timestamp planedSdate;

	/** 終了予定日時 */
	private Timestamp planedEdate;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 指図区分取得
	 * @return BigDecimal 指図区分
	*/
	public BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分設定
	 * @param directionDivision 指図区分
	*/
	public void setDirectionDivision(final BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

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
	 * 指図日時取得
	 * @return Timestamp 指図日時
	*/
	public Timestamp getDirectionDate() {
		return this.directionDate;
	}

	/**
	 * 指図日時設定
	 * @param directionDate 指図日時
	*/
	public void setDirectionDate(final Timestamp directionDate) {
		this.directionDate = directionDate;
	}

	/**
	 * アスプローバオーダーコード取得
	 * @return aspOrderNo アスプローバオーダーコード
	 */
	public String getAspOrderNo() {
		return this.aspOrderNo;
	}

	/**
	 * アスプローバオーダーコード設定
	 * @param aspOrderNo アスプローバオーダーコード
	 */
	public void setAspOrderNo(final String aspOrderNo) {
		this.aspOrderNo = aspOrderNo;
	}

	/**
	 * 指図ステータス
	 * @return BigDecimal 指図ステータス
	*/
	public BigDecimal getDirectionStatus() {
		return this.directionStatus;
	}

	/**
	 * 指図ステータス
	 * @param directionStatus 指図ステータス
	*/
	public void setDirectionStatus(final BigDecimal directionStatus) {
		this.directionStatus = directionStatus;
	}

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
	 * 充填タンクNO取得
	 * @return String 充填タンクNO
	*/
	public String getFilltankNo() {
		return this.filltankNo;
	}

	/**
	 * 充填タンクNO設定
	 * @param filltankNo 充填タンクNO
	*/
	public void setFilltankNo(final String filltankNo) {
		this.filltankNo = filltankNo;
	}

	/**
	 * 包装ライン取得
	 * @return String 包装ライン
	*/
	public String getPackageLine() {
		return this.packageLine;
	}

	/**
	 * 包装ライン設定
	 * @param packageLine 包装ライン
	*/
	public void setPackageLine(final String packageLine) {
		this.packageLine = packageLine;
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
	public BigDecimal getPlanedQty() {
		return this.planedQty;
	}

	/**
	 * 予定生産量|仕込数量設定
	 * @param planedQty 予定生産量|仕込数量
	*/
	public void setPlanedQty(final BigDecimal planedQty) {
		this.planedQty = planedQty;
	}

	/**
	 * 実績生産量を取得します。
	 * @return resultQty 実績生産量
	 */
	public BigDecimal getResultQty() {
		return resultQty;
	}

	/**
	 * 実績生産量を設定します。
	 * @param resultQty 実績生産量
	 */
	public void setResultQty(final BigDecimal resultQty) {
		this.resultQty = resultQty;
	}
	/**
	 * ロット番号取得
	 * @return String ロット番号
	*/
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * ロット番号設定
	 * @param lotNo ロット番号
	*/
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
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
	 * 摘要取得
	 * @return String 摘要
	*/
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 摘要設定
	 * @param remark 摘要
	*/
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 注釈取得
	 * @return String 注釈
	*/
	public String getNotes() {
		return this.notes;
	}

	/**
	 * 注釈設定
	 * @param notes 注釈
	*/
	public void setNotes(final String notes) {
		this.notes = notes;
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

}
