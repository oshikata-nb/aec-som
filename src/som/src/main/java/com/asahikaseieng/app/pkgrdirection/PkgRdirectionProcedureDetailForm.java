/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.sql.Timestamp;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装実績－工程詳細画面 Formクラス.
 * @author tosco
 */
public final class PkgRdirectionProcedureDetailForm extends
		AbstractPkgRdirectionForm {

	private static final long serialVersionUID = 1L;

	/** STEP_NO */
	private String stepNo;

	/** 表示順 */
	private String seq;

	/** 工程開始実績日 */
	private String strResultSDay;

	/** 工程開始実績時 */
	private String strResultSTime;

	/** 工程終了実績日 */
	private String strResultEDay;

	/** 工程終了実績時 */
	private String strResultETime;

	/** 工程コード */
	private String operationCd;

	/** 工程名称 */
	private String operationName;

	/** 条件 */
	private String condition;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 作業時間（機械） */
	private String strMachineTime;

	/** 作業時間（人） */
	private String strManTime;

	/** 稼動時間 */
	private String strWorkTime;

	/** 洗浄水絶対重量 */
	private String strWaterWeight;

	/** 本流/予備溶解 */
	private String strMainStream;

	/** 正味質量 */
	private String strNet;

	/** 量目管理幅最小 */
	private String strWeightMin;

	/** 量目管理幅最大 */
	private String strWeightMax;

	/** 濾過用フィルター */
	private String strFilter;

	/** 濾過用メッシュ */
	private String strMesh;

	/** オートチェッカー最小 */
	private String strAutoCheckerMin;

	/** オートチェッカー最大 */
	private String strAutoCheckerMax;

	/** グロスチェッカー最小 */
	private String strGrossCheckerMin;

	/** グロスチェッカー最大 */
	private String strGrossCheckerMax;

	/** オートチェッカー中心 */
	private String strAutoCheckerAve;

	/** グロスチェッカー中心 */
	private String strGrossCheckerAve;

	/** 開きトルク最小 */
	private String strOpeningTorqueMin;

	/** 開きトルク最大 */
	private String strOpeningTorqueMax;

	/** 閉めトルク最小 */
	private String strClosingTorqueMin;

	/** 閉めトルク最大 */
	private String strClosingTorqueMax;

	/** トルク圧 */
	private String strTorquePressure;

	/** エアー圧 */
	private String strAirPressure;

	/** 巻締め時間 */
	private String strVcloseTime;

	/** ホットエアー設定温度 */
	private String strHotAirPresetTemp;

	/** ホットエアー吹き出し圧力 */
	private String strHotAirPressure;

	/** 第一ヒートシール設定温度 */
	private String strFirstHeatSeal;

	/** 第二ヒートシール設定温度 */
	private String strSecondHeatSeal;

	/** 製造指図プロシージャ更新日時 */
	private Timestamp procedureUpdateDate;

	/** 小数点桁数(RECIPE5) */
	private String decimalPointRecipe5;

	/** 端数区分(RECIPE5) */
	private String roundDivisionRecipe5;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionProcedureDetailForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("update".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
			// validateメソッドによる入力チェック
			validateUpdateList(errors);
		}
		return errors;
	}

	/**
	 * 工程登録処理の入力チェック
	 * @param errors エラー内容
	 */
	private void validateUpdateList(final ActionErrors errors) {
		String strStrResultSdate = null;
		String strStrResultEdate = null;
		String strFormat = "yyyy/MM/dd HH:mm";
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		// 工程開始実績日時
		if (!StringUtils.isEmpty(getStrResultSDay())) {
			strStrResultSdate = getStrResultSDay();
			if (!StringUtils.isEmpty(getStrResultSTime())) {
				strStrResultSdate = strStrResultSdate + " "
						+ getStrResultSTime();
				// 日付チェック
				if (!AecDateUtils.chkDate(strStrResultSdate, strFormat)) {
					errors.add("", new ActionMessage(
							"errors.pkgrdirection.datetime",
							rb.getString("item.pkgrdirection.result.sdate")));
				}
			}
		}

		// 工程終了実績日時
		if (!StringUtils.isEmpty(getStrResultEDay())) {
			strStrResultEdate = getStrResultEDay();
			if (!StringUtils.isEmpty(getStrResultETime())) {
				strStrResultEdate = strStrResultEdate + " "
						+ getStrResultETime();
				// 日付チェック
				if (!AecDateUtils.chkDate(strStrResultEdate, strFormat)) {
					errors.add("", new ActionMessage(
							"errors.pkgrdirection.datetime",
							rb.getString("item.pkgrdirection.result.edate")));
				}
			}
		}
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();
		// STEP_NO
		setStepNo(null);
		// 表示順
		setSeq(null);
		// 荷姿
		setStyleOfPacking(null);
		// 工程コード
		setOperationCd(null);
		// 工程開始実績日
		setStrResultSDay(null);
		// 工程開始実績時
		setStrResultSTime(null);
		// 工程終了実績日
		setStrResultEDay(null);
		// 工程終了実績時
		setStrResultETime(null);
		// 条件
		setCondition(null);
		// 備考
		setRemark(null);
		// 注釈
		setNotes(null);
		// 正味質量
		setStrNet(null);
		// 量目管理幅最小
		setStrWeightMin(null);
		// 量目管理幅最大
		setStrWeightMax(null);
		// 濾過用フィルター
		setStrFilter(null);
		// 濾過用メッシュ
		setStrMesh(null);
		// オートチェッカー最小
		setStrAutoCheckerMin(null);
		// オートチェッカー最大
		setStrAutoCheckerMax(null);
		// グロスチェッカー最小
		setStrGrossCheckerMin(null);
		// グロスチェッカー最大
		setStrGrossCheckerMax(null);
		// オートチェッカー中心
		setStrAutoCheckerAve(null);
		// グロスチェッカー中心
		setStrGrossCheckerAve(null);
		// 開きトルク最小
		setStrOpeningTorqueMin(null);
		// 開きトルク最大
		setStrOpeningTorqueMax(null);
		// 閉めトルク最小
		setStrClosingTorqueMin(null);
		// 閉めトルク最大
		setStrClosingTorqueMax(null);
		// トルク圧
		setStrTorquePressure(null);
		// エアー圧
		setStrAirPressure(null);
		// 巻締め時間
		setStrVcloseTime(null);
		// ホットエアー設定温度
		setStrHotAirPresetTemp(null);
		// ホットエアー吹き出し圧力
		setStrHotAirPressure(null);
		// 第一ヒートシール設定温度
		setStrFirstHeatSeal(null);
		// 第二ヒートシール設定温度
		setStrSecondHeatSeal(null);
		// 工程名称
		setOperationName(null);
		// 製造指図プロシージャ更新日時
		setProcedureUpdateDate(null);
		// 小数点桁数(RECIPE5)
		setDecimalPointRecipe5(null);
		// 端数区分(RECIPE5)
		setRoundDivisionRecipe5(null);
	}

	// getter,setter
	/**
	 * STEP_NO取得
	 * @return String STEP_NO
	 */
	public String getStepNo() {
		return this.stepNo;
	}

	/**
	 * STEP_NO設定
	 * @param stepNo STEP_NO
	 */
	public void setStepNo(final String stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * 表示順取得
	 * @return String 表示順
	 */
	public String getSeq() {
		return this.seq;
	}

	/**
	 * 表示順設定
	 * @param seq 表示順
	 */
	public void setSeq(final String seq) {
		this.seq = seq;
	}

	/**
	 * 工程終了実績日を取得します。
	 * @return strResultEDay
	 */
	public String getStrResultEDay() {
		return strResultEDay;
	}

	/**
	 * 工程終了実績日を設定します。
	 * @param strResultEDay 工程終了実績日
	 */
	public void setStrResultEDay(final String strResultEDay) {
		this.strResultEDay = strResultEDay;
	}

	/**
	 * 工程終了実績時を取得します。
	 * @return strResultETime
	 */
	public String getStrResultETime() {
		return strResultETime;
	}

	/**
	 * 工程終了実績時を設定します。
	 * @param strResultETime 工程終了実績時
	 */
	public void setStrResultETime(final String strResultETime) {
		this.strResultETime = strResultETime;
	}

	/**
	 * 工程開始実績日を取得します。
	 * @return strResultSDay
	 */
	public String getStrResultSDay() {
		return strResultSDay;
	}

	/**
	 * 工程開始実績日を設定します。
	 * @param strResultSDay 工程開始実績日
	 */
	public void setStrResultSDay(final String strResultSDay) {
		this.strResultSDay = strResultSDay;
	}

	/**
	 * 工程開始実績時を取得します。
	 * @return strResultSTime
	 */
	public String getStrResultSTime() {
		return strResultSTime;
	}

	/**
	 * 工程開始実績時を設定します。
	 * @param strResultSTime 工程開始実績時
	 */
	public void setStrResultSTime(final String strResultSTime) {
		this.strResultSTime = strResultSTime;
	}

	/**
	 * 工程コード取得
	 * @return String 工程コード
	 */
	public String getOperationCd() {
		return this.operationCd;
	}

	/**
	 * 工程コード設定
	 * @param operationCd 工程コード
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * 工程名称取得
	 * @return String 工程名称
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称設定
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * 条件取得
	 * @return String 条件
	 */
	public String getCondition() {
		return this.condition;
	}

	/**
	 * 条件設定
	 * @param condition 条件
	 */
	public void setCondition(final String condition) {
		this.condition = condition;
	}

	/**
	 * 備考取得
	 * @return String 備考
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 備考設定
	 * @param remark 備考
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
	 * 作業時間（機械）取得
	 * @return String 作業時間（機械）
	 */
	public String getStrMachineTime() {
		return this.strMachineTime;
	}

	/**
	 * 作業時間（機械）設定
	 * @param strMachineTime 作業時間（機械）
	 */
	public void setStrMachineTime(final String strMachineTime) {
		this.strMachineTime = strMachineTime;
	}

	/**
	 * 作業時間（人）取得
	 * @return String 作業時間（人）
	 */
	public String getStrManTime() {
		return this.strManTime;
	}

	/**
	 * 作業時間（人）設定
	 * @param strManTime 作業時間（人）
	 */
	public void setStrManTime(final String strManTime) {
		this.strManTime = strManTime;
	}

	/**
	 * 稼動時間取得
	 * @return String 稼動時間
	 */
	public String getStrWorkTime() {
		return this.strWorkTime;
	}

	/**
	 * 稼動時間設定
	 * @param strWorkTime 稼動時間
	 */
	public void setStrWorkTime(final String strWorkTime) {
		this.strWorkTime = strWorkTime;
	}

	/**
	 * 洗浄水絶対重量取得
	 * @return String 洗浄水絶対重量
	 */
	public String getStrWaterWeight() {
		return this.strWaterWeight;
	}

	/**
	 * 洗浄水絶対重量設定
	 * @param strWaterWeight 洗浄水絶対重量
	 */
	public void setStrWaterWeight(final String strWaterWeight) {
		this.strWaterWeight = strWaterWeight;
	}

	/**
	 * 本流/予備溶解取得
	 * @return String 本流/予備溶解
	 */
	public String getStrMainStream() {
		return this.strMainStream;
	}

	/**
	 * 本流/予備溶解設定
	 * @param strMainStream 本流/予備溶解
	 */
	public void setStrMainStream(final String strMainStream) {
		this.strMainStream = strMainStream;
	}

	/**
	 * 正味質量取得
	 * @return String 正味質量
	 */
	public String getStrNet() {
		return this.strNet;
	}

	/**
	 * 正味質量設定
	 * @param strNet 正味質量
	 */
	public void setStrNet(final String strNet) {
		this.strNet = strNet;
	}

	/**
	 * 量目管理幅最小取得
	 * @return String 量目管理幅最小
	 */
	public String getStrWeightMin() {
		return this.strWeightMin;
	}

	/**
	 * 量目管理幅最小設定
	 * @param strWeightMin 量目管理幅最小
	 */
	public void setStrWeightMin(final String strWeightMin) {
		this.strWeightMin = strWeightMin;
	}

	/**
	 * 量目管理幅最大取得
	 * @return String 量目管理幅最大
	 */
	public String getStrWeightMax() {
		return this.strWeightMax;
	}

	/**
	 * 量目管理幅最大設定
	 * @param strWeightMax 量目管理幅最大
	 */
	public void setStrWeightMax(final String strWeightMax) {
		this.strWeightMax = strWeightMax;
	}

	/**
	 * 濾過用フィルター取得
	 * @return String 濾過用フィルター
	 */
	public String getStrFilter() {
		return this.strFilter;
	}

	/**
	 * 濾過用フィルター設定
	 * @param strFilter 濾過用フィルター
	 */
	public void setStrFilter(final String strFilter) {
		this.strFilter = strFilter;
	}

	/**
	 * 濾過用メッシュ取得
	 * @return String 濾過用メッシュ
	 */
	public String getStrMesh() {
		return this.strMesh;
	}

	/**
	 * 濾過用メッシュ設定
	 * @param strMesh 濾過用メッシュ
	 */
	public void setStrMesh(final String strMesh) {
		this.strMesh = strMesh;
	}

	/**
	 * オートチェッカー最小取得
	 * @return String オートチェッカー最小
	 */
	public String getStrAutoCheckerMin() {
		return this.strAutoCheckerMin;
	}

	/**
	 * オートチェッカー最小設定
	 * @param strAutoCheckerMin オートチェッカー最小
	 */
	public void setStrAutoCheckerMin(final String strAutoCheckerMin) {
		this.strAutoCheckerMin = strAutoCheckerMin;
	}

	/**
	 * オートチェッカー最大取得
	 * @return String オートチェッカー最大
	 */
	public String getStrAutoCheckerMax() {
		return this.strAutoCheckerMax;
	}

	/**
	 * オートチェッカー最大設定
	 * @param strAutoCheckerMax オートチェッカー最大
	 */
	public void setStrAutoCheckerMax(final String strAutoCheckerMax) {
		this.strAutoCheckerMax = strAutoCheckerMax;
	}

	/**
	 * グロスチェッカー最小取得
	 * @return String グロスチェッカー最小
	 */
	public String getStrGrossCheckerMin() {
		return this.strGrossCheckerMin;
	}

	/**
	 * グロスチェッカー最小設定
	 * @param strGrossCheckerMin グロスチェッカー最小
	 */
	public void setStrGrossCheckerMin(final String strGrossCheckerMin) {
		this.strGrossCheckerMin = strGrossCheckerMin;
	}

	/**
	 * グロスチェッカー最大取得
	 * @return String グロスチェッカー最大
	 */
	public String getStrGrossCheckerMax() {
		return this.strGrossCheckerMax;
	}

	/**
	 * グロスチェッカー最大設定
	 * @param strGrossCheckerMax グロスチェッカー最大
	 */
	public void setStrGrossCheckerMax(final String strGrossCheckerMax) {
		this.strGrossCheckerMax = strGrossCheckerMax;
	}

	/**
	 * オートチェッカー中心取得
	 * @return String オートチェッカー中心
	 */
	public String getStrAutoCheckerAve() {
		return this.strAutoCheckerAve;
	}

	/**
	 * オートチェッカー中心設定
	 * @param strAutoCheckerAve オートチェッカー中心
	 */
	public void setStrAutoCheckerAve(final String strAutoCheckerAve) {
		this.strAutoCheckerAve = strAutoCheckerAve;
	}

	/**
	 * グロスチェッカー中心取得
	 * @return String グロスチェッカー中心
	 */
	public String getStrGrossCheckerAve() {
		return this.strGrossCheckerAve;
	}

	/**
	 * グロスチェッカー中心設定
	 * @param strGrossCheckerAve グロスチェッカー中心
	 */
	public void setStrGrossCheckerAve(final String strGrossCheckerAve) {
		this.strGrossCheckerAve = strGrossCheckerAve;
	}

	/**
	 * 開きトルク最小取得
	 * @return String 開きトルク最小
	 */
	public String getStrOpeningTorqueMin() {
		return this.strOpeningTorqueMin;
	}

	/**
	 * 開きトルク最小設定
	 * @param strOpeningTorqueMin 開きトルク最小
	 */
	public void setStrOpeningTorqueMin(final String strOpeningTorqueMin) {
		this.strOpeningTorqueMin = strOpeningTorqueMin;
	}

	/**
	 * 開きトルク最大取得
	 * @return String 開きトルク最大
	 */
	public String getStrOpeningTorqueMax() {
		return this.strOpeningTorqueMax;
	}

	/**
	 * 開きトルク最大設定
	 * @param strOpeningTorqueMax 開きトルク最大
	 */
	public void setStrOpeningTorqueMax(final String strOpeningTorqueMax) {
		this.strOpeningTorqueMax = strOpeningTorqueMax;
	}

	/**
	 * 閉めトルク最小取得
	 * @return String 閉めトルク最小
	 */
	public String getStrClosingTorqueMin() {
		return this.strClosingTorqueMin;
	}

	/**
	 * 閉めトルク最小設定
	 * @param strClosingTorqueMin 閉めトルク最小
	 */
	public void setStrClosingTorqueMin(final String strClosingTorqueMin) {
		this.strClosingTorqueMin = strClosingTorqueMin;
	}

	/**
	 * 閉めトルク最大取得
	 * @return String 閉めトルク最大
	 */
	public String getStrClosingTorqueMax() {
		return this.strClosingTorqueMax;
	}

	/**
	 * 閉めトルク最大設定
	 * @param strClosingTorqueMax 閉めトルク最大
	 */
	public void setStrClosingTorqueMax(final String strClosingTorqueMax) {
		this.strClosingTorqueMax = strClosingTorqueMax;
	}

	/**
	 * トルク圧取得
	 * @return String トルク圧
	 */
	public String getStrTorquePressure() {
		return this.strTorquePressure;
	}

	/**
	 * トルク圧設定
	 * @param strTorquePressure トルク圧
	 */
	public void setStrTorquePressure(final String strTorquePressure) {
		this.strTorquePressure = strTorquePressure;
	}

	/**
	 * エアー圧取得
	 * @return String エアー圧
	 */
	public String getStrAirPressure() {
		return this.strAirPressure;
	}

	/**
	 * エアー圧設定
	 * @param strAirPressure エアー圧
	 */
	public void setStrAirPressure(final String strAirPressure) {
		this.strAirPressure = strAirPressure;
	}

	/**
	 * 巻締め時間取得
	 * @return String 巻締め時間
	 */
	public String getStrVcloseTime() {
		return this.strVcloseTime;
	}

	/**
	 * 巻締め時間設定
	 * @param strVcloseTime 巻締め時間
	 */
	public void setStrVcloseTime(final String strVcloseTime) {
		this.strVcloseTime = strVcloseTime;
	}

	/**
	 * ホットエアー設定温度取得
	 * @return String ホットエアー設定温度
	 */
	public String getStrHotAirPresetTemp() {
		return this.strHotAirPresetTemp;
	}

	/**
	 * ホットエアー設定温度設定
	 * @param strHotAirPresetTemp ホットエアー設定温度
	 */
	public void setStrHotAirPresetTemp(final String strHotAirPresetTemp) {
		this.strHotAirPresetTemp = strHotAirPresetTemp;
	}

	/**
	 * ホットエアー吹き出し圧力取得
	 * @return String ホットエアー吹き出し圧力
	 */
	public String getStrHotAirPressure() {
		return this.strHotAirPressure;
	}

	/**
	 * ホットエアー吹き出し圧力設定
	 * @param strHotAirPressure ホットエアー吹き出し圧力
	 */
	public void setStrHotAirPressure(final String strHotAirPressure) {
		this.strHotAirPressure = strHotAirPressure;
	}

	/**
	 * 第一ヒートシール設定温度取得
	 * @return String 第一ヒートシール設定温度
	 */
	public String getStrFirstHeatSeal() {
		return this.strFirstHeatSeal;
	}

	/**
	 * 第一ヒートシール設定温度設定
	 * @param strFirstHeatSeal 第一ヒートシール設定温度
	 */
	public void setStrFirstHeatSeal(final String strFirstHeatSeal) {
		this.strFirstHeatSeal = strFirstHeatSeal;
	}

	/**
	 * 第二ヒートシール設定温度取得
	 * @return String 第二ヒートシール設定温度
	 */
	public String getStrSecondHeatSeal() {
		return this.strSecondHeatSeal;
	}

	/**
	 * 第二ヒートシール設定温度設定
	 * @param strSecondHeatSeal 第二ヒートシール設定温度
	 */
	public void setStrSecondHeatSeal(final String strSecondHeatSeal) {
		this.strSecondHeatSeal = strSecondHeatSeal;
	}

	/**
	 * 製造指図プロシージャ更新日時取得.
	 * @return Timestamp 製造指図プロシージャ更新日時
	 */
	public Timestamp getProcedureUpdateDate() {
		return this.procedureUpdateDate;
	}

	/**
	 * 製造指図プロシージャ更新日時設定.
	 * @param procedureUpdateDate 製造指図プロシージャ更新日時
	 */
	public void setProcedureUpdateDate(final Timestamp procedureUpdateDate) {
		this.procedureUpdateDate = procedureUpdateDate;
	}

	/**
	 * 数値桁数チェック部品の固定区分取得.
	 * @return String 区分
	 */
	public String getFixedUnitDiv() {
		return PkgRdirectionConst.UNIT_DIV_RECIPE5;
	}

	/**
	 * 小数点桁数(RECIPE5)を取得します。
	 * @return decimalPointRecipe5 小数点桁数(RECIPE5)
	 */
	public String getDecimalPointRecipe5() {
		return decimalPointRecipe5;
	}

	/**
	 * 小数点桁数(RECIPE5)を設定します。
	 * @param decimalPointRecipe5 小数点桁数(RECIPE5)
	 */
	public void setDecimalPointRecipe5(final String decimalPointRecipe5) {
		this.decimalPointRecipe5 = decimalPointRecipe5;
	}

	/**
	 * 端数区分(RECIPE5)を取得します。
	 * @return roundDivisionRecipe5 端数区分(RECIPE5)
	 */
	public String getRoundDivisionRecipe5() {
		return roundDivisionRecipe5;
	}

	/**
	 * 端数区分(RECIPE5)を設定します。
	 * @param roundDivisionRecipe5 端数区分(RECIPE5)
	 */
	public void setRoundDivisionRecipe5(final String roundDivisionRecipe5) {
		this.roundDivisionRecipe5 = roundDivisionRecipe5;
	}

}
