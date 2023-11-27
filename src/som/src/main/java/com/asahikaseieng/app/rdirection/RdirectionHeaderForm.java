/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

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
 * 製造実績ヘッダー画面Form
 * @author tosco
 */
public class RdirectionHeaderForm extends AbstractRdirectionForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 日付チェックフォーマット */
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";

	private static ResourceBundle rb;
	static {
		rb = ResourceBundle.getBundle(Constants.APPLICATION_PROPERTIES);
	}

	/** ロット番号 */
	private String lotNo;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 入力用－ステータス */
	private String inputDirectionStatus;

	/** 入力用－製造開始実績日時－日 */
	private String inputResultSdateDay;

	/** 入力用－製造開始実績日時－時 */
	private String inputResultSdateTime;

	/** 入力用－滅菌作業開始時間－日 */
	private String inputSteritSdateDay;

	/** 入力用－滅菌作業開始時間－時 */
	private String inputSteritSdateTime;

	/** 入力用－製造終了実績日時ー日 */
	private String inputResultEdateDay;

	/** 入力用－製造終了実績日時－時 */
	private String inputResultEdateTime;

	/** 入力用－滅菌作業終了時間－日 */
	private String inputSteritEdateDay;

	/** 入力用－滅菌作業終了時間－時 */
	private String inputSteritEdateTime;

	/**
	 * コンストラクタ
	 */
	public RdirectionHeaderForm() {
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
			setRemark(null);
			setNotes(null);
			setLotNo(null);
			setInputDirectionStatus(null);
			setInputResultSdateDay(null);
			setInputResultSdateTime(null);
			setInputSteritSdateDay(null);
			setInputSteritSdateTime(null);
			setInputResultEdateDay(null);
			setInputResultEdateTime(null);
			setInputSteritEdateDay(null);
			setInputSteritEdateTime(null);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("update".equals(getOp())) {
			// 更新時のみvalidateを行う
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
		// 製造開始実績日時
		Date start = checkDate(errors, inputResultSdateDay,
			inputResultSdateTime, "item.rdirection.result.sdate", true);
		// 製造終了実績日時
		Date end = checkDate(errors, inputResultEdateDay, inputResultEdateTime,
			"item.rdirection.result.edate", true);
		if (!compareDate(start, end)) {
			addErrors(errors, "errors.compare", "item.rdirection.result.sdate",
				"item.rdirection.result.edate");
		}
		// 滅菌作業開始時間
		start = checkDate(errors, inputSteritSdateDay, inputSteritSdateTime,
			"item.rdirection.sterit.sdate", true);
		// 滅菌作業終了時間
		end = checkDate(errors, inputSteritEdateDay, inputSteritEdateTime,
			"item.rdirection.sterit.edate", true);
		if (!compareDate(start, end)) {
			addErrors(errors, "errors.compare", "item.rdirection.sterit.sdate",
				"item.rdirection.sterit.edate");
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
					addErrors(errors, "errors.rdirection.datetime", itemKey);
				}
			} else {
				// 日付のみを入力した場合エラー
				addErrors(errors, "errors.rdirection.date.notinputday", itemKey);
			}
		} else {
			if (StringUtils.isNotEmpty(time)) {
				// 時刻のみを入力した場合エラー
				addErrors(errors, "errors.rdirection.time.notinputday", itemKey);
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
	 * ロット番号を取得します。
	 * @return ロット番号
	 */
	public String getLotNo() {
		return lotNo;
	}

	/**
	 * ロット番号を設定します。
	 * @param lotNo ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 入力用－ステータスを取得します。
	 * @return 入力用－ステータス
	 */
	public String getInputDirectionStatus() {
		return inputDirectionStatus;
	}

	/**
	 * 入力用－ステータスを設定します。
	 * @param inputDirectionStatus 入力用－ステータス
	 */
	public void setInputDirectionStatus(final String inputDirectionStatus) {
		this.inputDirectionStatus = inputDirectionStatus;
	}

	/**
	 * 入力用－製造開始実績日時－日を取得します。
	 * @return 入力用－製造開始実績日時－日
	 */
	public String getInputResultSdateDay() {
		return inputResultSdateDay;
	}

	/**
	 * 入力用－製造開始実績日時－日を設定します。
	 * @param inputResultSdateDay 入力用－製造開始実績日時－日
	 */
	public void setInputResultSdateDay(final String inputResultSdateDay) {
		this.inputResultSdateDay = inputResultSdateDay;
	}

	/**
	 * 入力用－製造開始実績日時－時を取得します。
	 * @return 入力用－製造開始実績日時－時
	 */
	public String getInputResultSdateTime() {
		return inputResultSdateTime;
	}

	/**
	 * 入力用－製造開始実績日時－時を設定します。
	 * @param inputResultSdateTime 入力用－製造開始実績日時－時
	 */
	public void setInputResultSdateTime(final String inputResultSdateTime) {
		this.inputResultSdateTime = inputResultSdateTime;
	}

	/**
	 * 入力用－滅菌作業開始時間－日を取得します。
	 * @return 入力用－滅菌作業開始時間－日
	 */
	public String getInputSteritSdateDay() {
		return inputSteritSdateDay;
	}

	/**
	 * 入力用－滅菌作業開始時間－日を設定します。
	 * @param inputSteritSdateDay 入力用－滅菌作業開始時間－日
	 */
	public void setInputSteritSdateDay(final String inputSteritSdateDay) {
		this.inputSteritSdateDay = inputSteritSdateDay;
	}

	/**
	 * 入力用－滅菌作業開始時間－時を取得します。
	 * @return 入力用－滅菌作業開始時間－時
	 */
	public String getInputSteritSdateTime() {
		return inputSteritSdateTime;
	}

	/**
	 * 入力用－滅菌作業開始時間－時を設定します。
	 * @param inputSteritSdateTime 入力用－滅菌作業開始時間－時
	 */
	public void setInputSteritSdateTime(final String inputSteritSdateTime) {
		this.inputSteritSdateTime = inputSteritSdateTime;
	}

	/**
	 * 入力用－製造終了実績日時ー日を取得します。
	 * @return 入力用－製造終了実績日時ー日
	 */
	public String getInputResultEdateDay() {
		return inputResultEdateDay;
	}

	/**
	 * 入力用－製造終了実績日時ー日を設定します。
	 * @param inputResultEdateDay 入力用－製造終了実績日時ー日
	 */
	public void setInputResultEdateDay(final String inputResultEdateDay) {
		this.inputResultEdateDay = inputResultEdateDay;
	}

	/**
	 * 入力用－製造終了実績日時－時を取得します。
	 * @return 入力用－製造終了実績日時－時
	 */
	public String getInputResultEdateTime() {
		return inputResultEdateTime;
	}

	/**
	 * 入力用－製造終了実績日時－時を設定します。
	 * @param inputResultEdateTime 入力用－製造終了実績日時－時
	 */
	public void setInputResultEdateTime(final String inputResultEdateTime) {
		this.inputResultEdateTime = inputResultEdateTime;
	}

	/**
	 * 入力用－滅菌作業終了時間－日を取得します。
	 * @return 入力用－滅菌作業終了時間－日
	 */
	public String getInputSteritEdateDay() {
		return inputSteritEdateDay;
	}

	/**
	 * 入力用－滅菌作業終了時間－日を設定します。
	 * @param inputSteritEdateDay 入力用－滅菌作業終了時間－日
	 */
	public void setInputSteritEdateDay(final String inputSteritEdateDay) {
		this.inputSteritEdateDay = inputSteritEdateDay;
	}

	/**
	 * 入力用－滅菌作業終了時間－時を取得します。
	 * @return 入力用－滅菌作業終了時間－時
	 */
	public String getInputSteritEdateTime() {
		return inputSteritEdateTime;
	}

	/**
	 * 入力用－滅菌作業終了時間－時を設定します。
	 * @param inputSteritEdateTime 入力用－滅菌作業終了時間－時
	 */
	public void setInputSteritEdateTime(final String inputSteritEdateTime) {
		this.inputSteritEdateTime = inputSteritEdateTime;
	}

}
