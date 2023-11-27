/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.rdirection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 製造指図_表示用データ格納クラス.
 *
 * @author tosco
 */
public class RdirectionDirectionHeaderListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RdirectionDirectionHeaderListBase() {
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

	/** COLUMNアノテーション aspOrderNo */
	public static final String aspOrderNo_COLUMN = "ASP_ORDER_NO";

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

	/** 指図ステータス1:未確定,2:指図書発行済,3:製造開始,4:中間品最終検査待,5:中間品最終検査済,6:FA受信,7:製造記録出力済,8:完了
包装ステータス 1:未確定,2:指図書発行済,3:包装実績入力,4:包装記録出力済,5:検査待ち在庫計上,6:製品検査入力済,7:完了 */
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

	/** ホールドタンクNO */
	private String holdTankNo;

	/** 予備溶解タンクNO */
	private String dissolutionTankNo;

	/** 充填タンクNO */
	private String filltankNo;

	/** 包装ライン */
	private String packageLine;

	/** 現工程 */
	private String currentStepNo;

	/** 主要製品コード */
	private String itemCd;

	/** 予定生産量|仕込数量 */
	private BigDecimal planedQty;

	/** 実績生産量 */
	private BigDecimal resultQty;

	/** 分納フラグ */
	private BigDecimal divideFlag;

	/** 次回予定数量 */
	private BigDecimal nextPlanedQty;

	/** 物流入庫実績 */
	private BigDecimal pdwResult;

	/** ロス率 */
	private BigDecimal peocessLoss;

	/** ロット番号 */
	private String lotNo;

	/** 開始予定日時 */
	private Timestamp planedSdate;

	/** 終了予定日時 */
	private Timestamp planedEdate;

	/** 開始実績日時 */
	private Timestamp resultSdate;

	/** 終了実績日時 */
	private Timestamp resultEdate;

	/** 滅菌作業開始日時 */
	private Timestamp steritSdate;

	/** 滅菌作業終了日時 */
	private Timestamp steritEdate;

	/** 滅菌タンク内最小温度 */
	private BigDecimal mekkinTankTempMin;

	/** 滅菌タンク内最大温度 */
	private BigDecimal mekkinTankTempMax;

	/** 滅菌排水確認(1:適,0:不適) */
	private String haisuiCheck;

	/** 指図書発行フラグ|0:未発行,1:発行 */
	private BigDecimal stampFlag;

	/** 指図書発行日 */
	private Timestamp stampDate;

	/** 指図書発行者 */
	private String stampTantoCd;

	/** 製品ラベル発行フラグ|0:未発行,1:発行 */
	private BigDecimal productLabelFlag;

	/** 製品ラベル発行日 */
	private Timestamp productLabelDate;

	/** 製品ラベル発行者 */
	private String productLabelTantoCd;

	/** 製造/包装記録発行フラグ|0:未発行,1:発行 */
	private BigDecimal productRecordFlag;

	/** 製造/包装記録発行日 */
	private Timestamp productRecordDate;

	/** 製造/包装記録発行者 */
	private String productRecordTantoCd;

	/** 予備溶解ラベル発行フラグ|0:未発行,1:発行 */
	private BigDecimal stockdissLabelFlag;

	/** 予備溶解ラベル発行日 */
	private Timestamp stockdissLabelDate;

	/** 予備溶解ラベル発行者 */
	private String stockdissLabelTantoCd;

	/** 検査判定フラグ */
	private BigDecimal certificationFlag;

	/** 検査合格日 */
	private Timestamp certificationDate;

	/** 摘要 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 削除フラグ ※使いますか？ */
	private BigDecimal deleteFlag;

	/** 製造担当者 */
	private String seizotantocode;

	/** 調合タンク洗浄担当者 */
	private String senjotantocode;

	/** 調合タンク滅菌作業担当者 */
	private String mekkintantocode;

	/** 調合タンク内部状態(1:適,0:不適) */
	private String chogotankcondi;

	/** 予備溶解槽内部状態(1:適,0:不適) */
	private String yobiyokaicondi;

	/** ホールドタンク内部状態(1:適,0:不適) */
	private String holdtankcondi;

	/** 総作業時間 */
	private BigDecimal totalJobtime;

	/** 社員作業時間 */
	private BigDecimal enployJobtime;

	/** 協力作業時間 */
	private BigDecimal cooperJobtime;

	/** 承認者ID */
	private String appTantoCd;

	/** 承認日時 */
	private Timestamp appDate;

	/** 最終承認者ID */
	private String lastAppTantoCd;

	/** 最終承認日時 */
	private Timestamp lastAppDate;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	/** アスプローバオーダーコード */
	private String aspOrderNo;

	//
	// インスタンスメソッド
	//

	/**
	 * 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図取得
	 * @return BigDecimal 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図
	*/
	public BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図設定
	 * @param directionDivision 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図
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
	 * 指図ステータス1:未確定,2:指図書発行済,3:製造開始,4:中間品最終検査待,5:中間品最終検査済,6:FA受信,7:製造記録出力済,8:完了
包装ステータス 1:未確定,2:指図書発行済,3:包装実績入力,4:包装記録出力済,5:検査待ち在庫計上,6:製品検査入力済,7:完了取得
	 * @return BigDecimal 指図ステータス1:未確定,2:指図書発行済,3:製造開始,4:中間品最終検査待,5:中間品最終検査済,6:FA受信,7:製造記録出力済,8:完了
包装ステータス 1:未確定,2:指図書発行済,3:包装実績入力,4:包装記録出力済,5:検査待ち在庫計上,6:製品検査入力済,7:完了
	*/
	public BigDecimal getDirectionStatus() {
		return this.directionStatus;
	}

	/**
	 * 指図ステータス1:未確定,2:指図書発行済,3:製造開始,4:中間品最終検査待,5:中間品最終検査済,6:FA受信,7:製造記録出力済,8:完了
包装ステータス 1:未確定,2:指図書発行済,3:包装実績入力,4:包装記録出力済,5:検査待ち在庫計上,6:製品検査入力済,7:完了設定
	 * @param directionStatus 指図ステータス1:未確定,2:指図書発行済,3:製造開始,4:中間品最終検査待,5:中間品最終検査済,6:FA受信,7:製造記録出力済,8:完了
包装ステータス 1:未確定,2:指図書発行済,3:包装実績入力,4:包装記録出力済,5:検査待ち在庫計上,6:製品検査入力済,7:完了
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
	 * 現工程取得
	 * @return String 現工程
	*/
	public String getCurrentStepNo() {
		return this.currentStepNo;
	}

	/**
	 * 現工程設定
	 * @param currentStepNo 現工程
	*/
	public void setCurrentStepNo(final String currentStepNo) {
		this.currentStepNo = currentStepNo;
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
	 * 実績生産量取得
	 * @return BigDecimal 実績生産量
	*/
	public BigDecimal getResultQty() {
		return this.resultQty;
	}

	/**
	 * 実績生産量設定
	 * @param resultQty 実績生産量
	*/
	public void setResultQty(final BigDecimal resultQty) {
		this.resultQty = resultQty;
	}

	/**
	 * 分納フラグ取得
	 * @return BigDecimal 分納フラグ
	*/
	public BigDecimal getDivideFlag() {
		return this.divideFlag;
	}

	/**
	 * 分納フラグ設定
	 * @param divideFlag 分納フラグ
	*/
	public void setDivideFlag(final BigDecimal divideFlag) {
		this.divideFlag = divideFlag;
	}

	/**
	 * 次回予定数量取得
	 * @return BigDecimal 次回予定数量
	*/
	public BigDecimal getNextPlanedQty() {
		return this.nextPlanedQty;
	}

	/**
	 * 次回予定数量設定
	 * @param nextPlanedQty 次回予定数量
	*/
	public void setNextPlanedQty(final BigDecimal nextPlanedQty) {
		this.nextPlanedQty = nextPlanedQty;
	}

	/**
	 * 物流入庫実績取得
	 * @return BigDecimal 物流入庫実績
	*/
	public BigDecimal getPdwResult() {
		return this.pdwResult;
	}

	/**
	 * 物流入庫実績設定
	 * @param pdwResult 物流入庫実績
	*/
	public void setPdwResult(final BigDecimal pdwResult) {
		this.pdwResult = pdwResult;
	}

	/**
	 * ロス率取得
	 * @return BigDecimal ロス率
	*/
	public BigDecimal getPeocessLoss() {
		return this.peocessLoss;
	}

	/**
	 * ロス率設定
	 * @param peocessLoss ロス率
	*/
	public void setPeocessLoss(final BigDecimal peocessLoss) {
		this.peocessLoss = peocessLoss;
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
	 * 滅菌作業開始日時取得
	 * @return Timestamp 滅菌作業開始日時
	*/
	public Timestamp getSteritSdate() {
		return this.steritSdate;
	}

	/**
	 * 滅菌作業開始日時設定
	 * @param steritSdate 滅菌作業開始日時
	*/
	public void setSteritSdate(final Timestamp steritSdate) {
		this.steritSdate = steritSdate;
	}

	/**
	 * 滅菌作業終了日時取得
	 * @return Timestamp 滅菌作業終了日時
	*/
	public Timestamp getSteritEdate() {
		return this.steritEdate;
	}

	/**
	 * 滅菌作業終了日時設定
	 * @param steritEdate 滅菌作業終了日時
	*/
	public void setSteritEdate(final Timestamp steritEdate) {
		this.steritEdate = steritEdate;
	}

	/**
	 * 滅菌タンク内最小温度取得
	 * @return BigDecimal 滅菌タンク内最小温度
	*/
	public BigDecimal getMekkinTankTempMin() {
		return this.mekkinTankTempMin;
	}

	/**
	 * 滅菌タンク内最小温度設定
	 * @param mekkinTankTempMin 滅菌タンク内最小温度
	*/
	public void setMekkinTankTempMin(final BigDecimal mekkinTankTempMin) {
		this.mekkinTankTempMin = mekkinTankTempMin;
	}

	/**
	 * 滅菌タンク内最大温度取得
	 * @return BigDecimal 滅菌タンク内最大温度
	*/
	public BigDecimal getMekkinTankTempMax() {
		return this.mekkinTankTempMax;
	}

	/**
	 * 滅菌タンク内最大温度設定
	 * @param mekkinTankTempMax 滅菌タンク内最大温度
	*/
	public void setMekkinTankTempMax(final BigDecimal mekkinTankTempMax) {
		this.mekkinTankTempMax = mekkinTankTempMax;
	}

	/**
	 * 滅菌排水確認(1:適,0:不適)取得
	 * @return String 滅菌排水確認(1:適,0:不適)
	*/
	public String getHaisuiCheck() {
		return this.haisuiCheck;
	}

	/**
	 * 滅菌排水確認(1:適,0:不適)設定
	 * @param haisuiCheck 滅菌排水確認(1:適,0:不適)
	*/
	public void setHaisuiCheck(final String haisuiCheck) {
		this.haisuiCheck = haisuiCheck;
	}

	/**
	 * 指図書発行フラグ|0:未発行,1:発行取得
	 * @return BigDecimal 指図書発行フラグ|0:未発行,1:発行
	*/
	public BigDecimal getStampFlag() {
		return this.stampFlag;
	}

	/**
	 * 指図書発行フラグ|0:未発行,1:発行設定
	 * @param stampFlag 指図書発行フラグ|0:未発行,1:発行
	*/
	public void setStampFlag(final BigDecimal stampFlag) {
		this.stampFlag = stampFlag;
	}

	/**
	 * 指図書発行日取得
	 * @return Timestamp 指図書発行日
	*/
	public Timestamp getStampDate() {
		return this.stampDate;
	}

	/**
	 * 指図書発行日設定
	 * @param stampDate 指図書発行日
	*/
	public void setStampDate(final Timestamp stampDate) {
		this.stampDate = stampDate;
	}

	/**
	 * 指図書発行者取得
	 * @return String 指図書発行者
	*/
	public String getStampTantoCd() {
		return this.stampTantoCd;
	}

	/**
	 * 指図書発行者設定
	 * @param stampTantoCd 指図書発行者
	*/
	public void setStampTantoCd(final String stampTantoCd) {
		this.stampTantoCd = stampTantoCd;
	}

	/**
	 * 製品ラベル発行フラグ|0:未発行,1:発行取得
	 * @return BigDecimal 製品ラベル発行フラグ|0:未発行,1:発行
	*/
	public BigDecimal getProductLabelFlag() {
		return this.productLabelFlag;
	}

	/**
	 * 製品ラベル発行フラグ|0:未発行,1:発行設定
	 * @param productLabelFlag 製品ラベル発行フラグ|0:未発行,1:発行
	*/
	public void setProductLabelFlag(final BigDecimal productLabelFlag) {
		this.productLabelFlag = productLabelFlag;
	}

	/**
	 * 製品ラベル発行日取得
	 * @return Timestamp 製品ラベル発行日
	*/
	public Timestamp getProductLabelDate() {
		return this.productLabelDate;
	}

	/**
	 * 製品ラベル発行日設定
	 * @param productLabelDate 製品ラベル発行日
	*/
	public void setProductLabelDate(final Timestamp productLabelDate) {
		this.productLabelDate = productLabelDate;
	}

	/**
	 * 製品ラベル発行者取得
	 * @return String 製品ラベル発行者
	*/
	public String getProductLabelTantoCd() {
		return this.productLabelTantoCd;
	}

	/**
	 * 製品ラベル発行者設定
	 * @param productLabelTantoCd 製品ラベル発行者
	*/
	public void setProductLabelTantoCd(final String productLabelTantoCd) {
		this.productLabelTantoCd = productLabelTantoCd;
	}

	/**
	 * 製造/包装記録発行フラグ|0:未発行,1:発行取得
	 * @return BigDecimal 製造/包装記録発行フラグ|0:未発行,1:発行
	*/
	public BigDecimal getProductRecordFlag() {
		return this.productRecordFlag;
	}

	/**
	 * 製造/包装記録発行フラグ|0:未発行,1:発行設定
	 * @param productRecordFlag 製造/包装記録発行フラグ|0:未発行,1:発行
	*/
	public void setProductRecordFlag(final BigDecimal productRecordFlag) {
		this.productRecordFlag = productRecordFlag;
	}

	/**
	 * 製造/包装記録発行日取得
	 * @return Timestamp 製造/包装記録発行日
	*/
	public Timestamp getProductRecordDate() {
		return this.productRecordDate;
	}

	/**
	 * 製造/包装記録発行日設定
	 * @param productRecordDate 製造/包装記録発行日
	*/
	public void setProductRecordDate(final Timestamp productRecordDate) {
		this.productRecordDate = productRecordDate;
	}

	/**
	 * 製造/包装記録発行者取得
	 * @return String 製造/包装記録発行者
	*/
	public String getProductRecordTantoCd() {
		return this.productRecordTantoCd;
	}

	/**
	 * 製造/包装記録発行者設定
	 * @param productRecordTantoCd 製造/包装記録発行者
	*/
	public void setProductRecordTantoCd(final String productRecordTantoCd) {
		this.productRecordTantoCd = productRecordTantoCd;
	}

	/**
	 * 予備溶解ラベル発行フラグ|0:未発行,1:発行取得
	 * @return BigDecimal 予備溶解ラベル発行フラグ|0:未発行,1:発行
	*/
	public BigDecimal getStockdissLabelFlag() {
		return this.stockdissLabelFlag;
	}

	/**
	 * 予備溶解ラベル発行フラグ|0:未発行,1:発行設定
	 * @param stockdissLabelFlag 予備溶解ラベル発行フラグ|0:未発行,1:発行
	*/
	public void setStockdissLabelFlag(final BigDecimal stockdissLabelFlag) {
		this.stockdissLabelFlag = stockdissLabelFlag;
	}

	/**
	 * 予備溶解ラベル発行日取得
	 * @return Timestamp 予備溶解ラベル発行日
	*/
	public Timestamp getStockdissLabelDate() {
		return this.stockdissLabelDate;
	}

	/**
	 * 予備溶解ラベル発行日設定
	 * @param stockdissLabelDate 予備溶解ラベル発行日
	*/
	public void setStockdissLabelDate(final Timestamp stockdissLabelDate) {
		this.stockdissLabelDate = stockdissLabelDate;
	}

	/**
	 * 予備溶解ラベル発行者取得
	 * @return String 予備溶解ラベル発行者
	*/
	public String getStockdissLabelTantoCd() {
		return this.stockdissLabelTantoCd;
	}

	/**
	 * 予備溶解ラベル発行者設定
	 * @param stockdissLabelTantoCd 予備溶解ラベル発行者
	*/
	public void setStockdissLabelTantoCd(final String stockdissLabelTantoCd) {
		this.stockdissLabelTantoCd = stockdissLabelTantoCd;
	}

	/**
	 * 検査判定フラグ取得
	 * @return BigDecimal 検査判定フラグ
	*/
	public BigDecimal getCertificationFlag() {
		return this.certificationFlag;
	}

	/**
	 * 検査判定フラグ設定
	 * @param certificationFlag 検査判定フラグ
	*/
	public void setCertificationFlag(final BigDecimal certificationFlag) {
		this.certificationFlag = certificationFlag;
	}

	/**
	 * 検査合格日取得
	 * @return Timestamp 検査合格日
	*/
	public Timestamp getCertificationDate() {
		return this.certificationDate;
	}

	/**
	 * 検査合格日設定
	 * @param certificationDate 検査合格日
	*/
	public void setCertificationDate(final Timestamp certificationDate) {
		this.certificationDate = certificationDate;
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
	 * 削除フラグ ※使いますか？取得
	 * @return BigDecimal 削除フラグ ※使いますか？
	*/
	public BigDecimal getDeleteFlag() {
		return this.deleteFlag;
	}

	/**
	 * 削除フラグ ※使いますか？設定
	 * @param deleteFlag 削除フラグ ※使いますか？
	*/
	public void setDeleteFlag(final BigDecimal deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * 製造担当者取得
	 * @return String 製造担当者
	*/
	public String getSeizotantocode() {
		return this.seizotantocode;
	}

	/**
	 * 製造担当者設定
	 * @param seizotantocode 製造担当者
	*/
	public void setSeizotantocode(final String seizotantocode) {
		this.seizotantocode = seizotantocode;
	}

	/**
	 * 調合タンク洗浄担当者取得
	 * @return String 調合タンク洗浄担当者
	*/
	public String getSenjotantocode() {
		return this.senjotantocode;
	}

	/**
	 * 調合タンク洗浄担当者設定
	 * @param senjotantocode 調合タンク洗浄担当者
	*/
	public void setSenjotantocode(final String senjotantocode) {
		this.senjotantocode = senjotantocode;
	}

	/**
	 * 調合タンク滅菌作業担当者取得
	 * @return String 調合タンク滅菌作業担当者
	*/
	public String getMekkintantocode() {
		return this.mekkintantocode;
	}

	/**
	 * 調合タンク滅菌作業担当者設定
	 * @param mekkintantocode 調合タンク滅菌作業担当者
	*/
	public void setMekkintantocode(final String mekkintantocode) {
		this.mekkintantocode = mekkintantocode;
	}

	/**
	 * 調合タンク内部状態(1:適,0:不適)取得
	 * @return String 調合タンク内部状態(1:適,0:不適)
	*/
	public String getChogotankcondi() {
		return this.chogotankcondi;
	}

	/**
	 * 調合タンク内部状態(1:適,0:不適)設定
	 * @param chogotankcondi 調合タンク内部状態(1:適,0:不適)
	*/
	public void setChogotankcondi(final String chogotankcondi) {
		this.chogotankcondi = chogotankcondi;
	}

	/**
	 * 予備溶解槽内部状態(1:適,0:不適)取得
	 * @return String 予備溶解槽内部状態(1:適,0:不適)
	*/
	public String getYobiyokaicondi() {
		return this.yobiyokaicondi;
	}

	/**
	 * 予備溶解槽内部状態(1:適,0:不適)設定
	 * @param yobiyokaicondi 予備溶解槽内部状態(1:適,0:不適)
	*/
	public void setYobiyokaicondi(final String yobiyokaicondi) {
		this.yobiyokaicondi = yobiyokaicondi;
	}

	/**
	 * ホールドタンク内部状態(1:適,0:不適)取得
	 * @return String ホールドタンク内部状態(1:適,0:不適)
	*/
	public String getHoldtankcondi() {
		return this.holdtankcondi;
	}

	/**
	 * ホールドタンク内部状態(1:適,0:不適)設定
	 * @param holdtankcondi ホールドタンク内部状態(1:適,0:不適)
	*/
	public void setHoldtankcondi(final String holdtankcondi) {
		this.holdtankcondi = holdtankcondi;
	}

	/**
	 * 総作業時間取得
	 * @return BigDecimal 総作業時間
	*/
	public BigDecimal getTotalJobtime() {
		return this.totalJobtime;
	}

	/**
	 * 総作業時間設定
	 * @param totalJobtime 総作業時間
	*/
	public void setTotalJobtime(final BigDecimal totalJobtime) {
		this.totalJobtime = totalJobtime;
	}

	/**
	 * 社員作業時間取得
	 * @return BigDecimal 社員作業時間
	*/
	public BigDecimal getEnployJobtime() {
		return this.enployJobtime;
	}

	/**
	 * 社員作業時間設定
	 * @param enployJobtime 社員作業時間
	*/
	public void setEnployJobtime(final BigDecimal enployJobtime) {
		this.enployJobtime = enployJobtime;
	}

	/**
	 * 協力作業時間取得
	 * @return BigDecimal 協力作業時間
	*/
	public BigDecimal getCooperJobtime() {
		return this.cooperJobtime;
	}

	/**
	 * 協力作業時間設定
	 * @param cooperJobtime 協力作業時間
	*/
	public void setCooperJobtime(final BigDecimal cooperJobtime) {
		this.cooperJobtime = cooperJobtime;
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
	/**
	 * アスプローバオーダーコードを取得します。
	 * @return アスプローバオーダーコード
	 */
	public String getAspOrderNo() {
		return aspOrderNo;
	}

	/**
	 * アスプローバオーダーコードを設定します。
	 * @param aspOrderNo アスプローバオーダーコード
	 */
	public void setAspOrderNo(final String aspOrderNo) {
		this.aspOrderNo = aspOrderNo;
	}
}
