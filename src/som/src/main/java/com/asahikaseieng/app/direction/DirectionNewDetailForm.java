/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製造指図－新規入力Form
 * @author tosco
 */
public class DirectionNewDetailForm extends AbstractDirectionForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 日付チェックフォーマット */
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";

	private static ResourceBundle rb;
	static {
		rb = ResourceBundle.getBundle(Constants.APPLICATION_PROPERTIES);
	}

	/** 仕込数量 */
	private String planedQty;

	/** 開始予定日時-年月日 */
	private String planedSdateDate;

	/** 開始予定日時-時間 */
	private String planedSdateTime;

	/** 終了予定日時-年月日 */
	private String planedEdateDate;

	/** 終了予定日時-時間 */
	private String planedEdateTime;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 基本処方コード・バージョン */
	private String recipeIdVersion;

	/** 基本処方名 */
	private String recipeName;

	/** 調合タンク名 */
	private String compoundTankName;

	/** ホールドタンク名 */
	private String holdTankName;

	/** 予備溶解タンク名 */
	private String dissolutionTankName;

	/** 小数点桁数(HAIGO) */
	private String decimalPoint;

	/** 端数区分(HAIGO) */
	private String roundDivision;

	/**
	 * コンストラクタ
	 */
	public DirectionNewDetailForm() {
	}

	/**
	 * リセット
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
		clear();
	}

	/**
	 * クリア
	 */
	@Override
	protected void clear() {
		if ("init".equals(getOp())) {
			super.clear();
			setPlanedQty(null);
			setPlanedSdateDate(null);
			setPlanedSdateTime(null);
			setPlanedEdateDate(null);
			setPlanedEdateTime(null);
			setRemark(null);
			setNotes(null);
			setRecipeIdVersion(null);
			setRecipeName(null);
			setCompoundTankName(null);
			setHoldTankName(null);
			setDissolutionTankName(null);
			setDecimalPoint(null);
			setRoundDivision(null);
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
			// 日付チェック
			validateDate(errors);
		}
		return errors;
	}

	/**
	 * 日付チェック
	 * @param errors errors
	 * @return ActionErrors
	 */
	private ActionErrors validateDate(final ActionErrors errors) {
		// 開始予定日(From)
		Date start = checkDate(errors, planedSdateDate, planedSdateTime,
			"item.direction.planed.sdate", true);
		// 終了予定日
		Date endFrom = checkDate(errors, planedEdateDate, planedEdateTime,
			"item.direction.planed.edate", true);

		if (!compareDate(start, endFrom)) {
			addErrors(errors, "errors.compare", "item.direction.planed.sdate",
				"item.direction.planed.edate");
		}
		return errors;
	}

	/**
	 * 日・時のチェックを行う
	 * @param errors ActionErrors
	 * @param day 日付
	 * @param time 時刻
	 * @param itemKey 項目名
	 * @param isStart [true:From][false:To]
	 * @return Date（チェックOK時）
	 */
	private Date checkDate(final ActionErrors errors, final String day,
			final String time, final String itemKey, final boolean isStart) {
		Date res = null;
		if (StringUtils.isNotEmpty(day)) {
			if (StringUtils.isNotEmpty(time)) {
				String dateString = getDateTime(day, time, isStart);
				try {
					res = checkDate(dateString, DATE_FORMAT);
				} catch (ParseException e) {
					// 日付フォーマットエラー
					addErrors(errors, "errors.direction.datetime", itemKey);
				}
			} else {
				// 日付のみを入力した場合エラー
				addErrors(errors, "errors.direction.date.notinputday", itemKey);
			}
		} else {
			if (StringUtils.isNotEmpty(time)) {
				// 時刻のみを入力した場合エラー
				addErrors(errors, "errors.direction.time.notinputday", itemKey);
			}
		}
		return res;
	}

	/**
	 * 日と時から日時文字列を作成する。
	 * @param day 日付
	 * @param time 時刻
	 * @param itemKey 項目名
	 * @param isStart [true:From][false:To]
	 * @return 日時文字列
	 */
	private String getDateTime(final String day, final String time,
			final boolean isStart) {
		String res = null;
		if (StringUtils.isNotEmpty(day)) {
			if (StringUtils.isEmpty(time)) {
				if (isStart) {
					res = day + " 00:00";
				} else {
					res = day + " 23:59";
				}
			} else {
				res = day + " " + time;
			}
		}
		return res;
	}

	/**
	 * ActionErrorsにエラーメッセージを追加する。
	 * @param errors ActionErrors
	 * @param key エラーメッセージキー
	 * @param replace 置換文字列
	 */
	private void addErrors(final ActionErrors errors, final String key,
			final String... replace) {
		ActionMessages messages = new ActionMessages();
		int num = replace.length;
		String[] repls = new String[num];
		for (int i = 0; i < num; i++) {
			repls[i] = rb.getString(replace[i]);
		}
		messages.add("", new ActionMessage(key, repls));
		errors.add(messages);
	}

	/**
	 * 日付フォーマットチェック
	 * @param source 対象文字列
	 * @param pattern 日付フォーマット
	 * @return [true:チェックOK][false:チェックNG]
	 * @throws ParseException 日付フォーマットエラー時発生
	 */
	private Date checkDate(final String source, final String pattern)
			throws ParseException {
		return AecDateUtils.getDate(source, pattern);
	}

	/**
	 * 日付を比較する
	 * @param start 開始Date
	 * @param to 終了Date
	 * @return [true:開始≦終了][false:開始＞終了]
	 */
	private boolean compareDate(final Date start, final Date to) {
		boolean res = true;
		if ((start != null) && (to != null)) {
			if (start.compareTo(to) > 0) {
				// startがtoより後なのでエラー
				res = false;
			}
		}
		return res;
	}

	// setter,getter---------------------------------------------------------
	/**
	 * 仕込数量を取得します。
	 * @return 仕込数量
	 */
	public String getPlanedQty() {
		return planedQty;
	}

	/**
	 * 仕込数量を設定します。
	 * @param planedQty 仕込数量
	 */
	public void setPlanedQty(final String planedQty) {
		this.planedQty = planedQty;
	}

	/**
	 * 開始予定日時-年月日を取得します。
	 * @return 開始予定日時-年月日
	 */
	public String getPlanedSdateDate() {
		return planedSdateDate;
	}

	/**
	 * 開始予定日時-年月日を設定します。
	 * @param planedSdateDate 開始予定日時-年月日
	 */
	public void setPlanedSdateDate(final String planedSdateDate) {
		this.planedSdateDate = planedSdateDate;
	}

	/**
	 * 開始予定日時-時間を取得します。
	 * @return 開始予定日時-時間
	 */
	public String getPlanedSdateTime() {
		return planedSdateTime;
	}

	/**
	 * 開始予定日時-時間を設定します。
	 * @param planedSdateTime 開始予定日時-時間
	 */
	public void setPlanedSdateTime(final String planedSdateTime) {
		this.planedSdateTime = planedSdateTime;
	}

	/**
	 * 終了予定日時-年月日を取得します。
	 * @return 終了予定日時-年月日
	 */
	public String getPlanedEdateDate() {
		return planedEdateDate;
	}

	/**
	 * 終了予定日時-年月日を設定します。
	 * @param planedEdateDate 終了予定日時-年月日
	 */
	public void setPlanedEdateDate(final String planedEdateDate) {
		this.planedEdateDate = planedEdateDate;
	}

	/**
	 * 終了予定日時-時間を取得します。
	 * @return 終了予定日時-時間
	 */
	public String getPlanedEdateTime() {
		return planedEdateTime;
	}

	/**
	 * 終了予定日時-時間を設定します。
	 * @param planedEdateTime 終了予定日時-時間
	 */
	public void setPlanedEdateTime(final String planedEdateTime) {
		this.planedEdateTime = planedEdateTime;
	}

	/**
	 * 備考を取得します。
	 * @return 備考
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 備考を設定します。
	 * @param remark 備考
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 注釈を取得します。
	 * @return 注釈
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 注釈を設定します。
	 * @param notes 注釈
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/**
	 * 基本処方コード・バージョンを取得します。
	 * @return 基本処方コード・バージョン
	 */
	public String getRecipeIdVersion() {
		return recipeIdVersion;
	}

	/**
	 * 基本処方コード・バージョンを設定します。
	 * @param recipeIdVersion 基本処方コード・バージョン
	 */
	public void setRecipeIdVersion(final String recipeIdVersion) {
		this.recipeIdVersion = recipeIdVersion;
	}

	/**
	 * 基本処方名を取得します。
	 * @return 基本処方名
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * 基本処方名を設定します。
	 * @param recipeName 基本処方名
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * 調合タンク名を取得します。
	 * @return 調合タンク名
	 */
	public String getCompoundTankName() {
		return compoundTankName;
	}

	/**
	 * 調合タンク名を設定します。
	 * @param compoundTankName 調合タンク名
	 */
	public void setCompoundTankName(final String compoundTankName) {
		this.compoundTankName = compoundTankName;
	}

	/**
	 * ホールドタンク名を取得します。
	 * @return ホールドタンク名
	 */
	public String getHoldTankName() {
		return holdTankName;
	}

	/**
	 * ホールドタンク名を設定します。
	 * @param holdTankName ホールドタンク名
	 */
	public void setHoldTankName(final String holdTankName) {
		this.holdTankName = holdTankName;
	}

	/**
	 * 予備溶解タンク名を取得します。
	 * @return 予備溶解タンク名
	 */
	public String getDissolutionTankName() {
		return dissolutionTankName;
	}

	/**
	 * 予備溶解タンク名を設定します。
	 * @param dissolutionTankName 予備溶解タンク名
	 */
	public void setDissolutionTankName(final String dissolutionTankName) {
		this.dissolutionTankName = dissolutionTankName;
	}

	/**
	 * 小数点桁数(HAIGO)を取得します。
	 * @return decimalPoint 小数点桁数(HAIGO)
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数(HAIGO)を設定します。
	 * @param decimalPoint 小数点桁数(HAIGO)
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分(HAIGO)を取得します。
	 * @return roundDivision 端数区分(HAIGO)
	 */
	public String getRoundDivision() {
		return roundDivision;
	}

	/**
	 * 端数区分(HAIGO)を設定します。
	 * @param roundDivision 端数区分(HAIGO)
	 */
	public void setRoundDivision(final String roundDivision) {
		this.roundDivision = roundDivision;
	}

}
