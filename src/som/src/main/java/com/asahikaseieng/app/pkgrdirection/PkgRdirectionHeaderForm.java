/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.pkgdirection.PkgDirectionConst;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装実績ヘッダー Formクラス.
 * @author tosco
 */
public final class PkgRdirectionHeaderForm extends AbstractPkgRdirectionForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//
	/** ステータスコンボボックス */
	private List<ComboBoxItems> statusCombo;

	/** 包装開始実施日 */
	private String resultSDay;

	/** 包装開始実施時刻 */
	private String resultSTime;

	/** 包装終了実施日 */
	private String resultEDay;

	/** 包装終了実施時刻 */
	private String resultETime;

	/** 分納フラグ */
	private boolean divideFlag;

	/** 次回予定数量 */
	private String nextPlanedQty;

	/** 次回包装開始予定日 */
	private String nextSDay;

	/** 次回包装開始予定時刻 */
	private String nextSTime;

	/** 次回包装終了予定日 */
	private String nextEDay;

	/** 次回包装終了予定時刻 */
	private String nextETime;

	/** 小数点桁数(次回予定数量) */
	private String qtyDecimalPoint;

	/** 端数区分(次回予定数量) */
	private String qtyRoundDivision;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 入力用－ステータス */
	private String inputDirectionStatus;

	/**
	 * コンストラクタ.包装指図ヘッダー
	 */
	public PkgRdirectionHeaderForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}

		// 分納フラグクリア
		this.setDivideFlag(false);
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			validateUpdate(errors);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();

		/** ステータスコンボボックス */
		this.setStatusCombo(null);
		/** 包装開始予定日 */
		this.setResultSDay(null);
		/** 包装開始予定時刻 */
		this.setResultSTime(null);
		/** 包装終了予定日 */
		this.setResultEDay(null);
		/** 包装終了予定時刻 */
		this.setResultETime(null);
		/** 分納フラグ */
		this.setDivideFlag(false);
		/** 次回予定数量 */
		this.setNextPlanedQty(null);
		/** 少数桁数(次回予定数量) */
		this.setQtyDecimalPoint(null);
		/** 端数区分(次回予定数量) */
		this.setQtyRoundDivision(null);
		/** 備考 */
		this.setRemark(null);
		/** 注釈 */
		this.setNotes(null);
		/** 入力用ステータス */
		this.setInputDirectionStatus(null);

		setNextEDay(null);
		setNextETime(null);
		setNextSDay(null);
		setNextSTime(null);
	}

	/**
	 * 更新時のチェック
	 * @param errors エラー内容
	 */
	private void validateUpdate(final ActionErrors errors) {
		String strResultSdate = null;
		String strResultEdate = null;
		Timestamp resultSdate = null;
		Timestamp resultEdate = null;
		String strFormat = "yyyy/MM/dd HH:mm";
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		// 包装開始実績日時(FROM)
		if (!StringUtils.isEmpty(getResultSDay())) {
			strResultSdate = getResultSDay();
			if (!StringUtils.isEmpty(getResultSTime())) {
				strResultSdate = strResultSdate + " " + getResultSTime();
			} else {
				strResultSdate = strResultSdate + " "
						+ PkgRdirectionConst.STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strResultSdate, strFormat)) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgrdirection.datetime",
						rb.getString("item.pkgrdirection.header.pkg.sdate")));
			} else {
				resultSdate = AecDateUtils.getTimestampYmdHmFormat(
					strResultSdate, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getResultSTime())) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgrdirection.time.notinputday",
						rb.getString("item.pkgrdirection.header.pkg.sdate")));
			}
		}
		// 包装終了実績日時(To)
		if (!StringUtils.isEmpty(getResultEDay())) {
			strResultEdate = getResultEDay();
			if (!StringUtils.isEmpty(getResultETime())) {
				strResultEdate = strResultEdate + " " + getResultETime();
			} else {
				strResultEdate = strResultEdate + " "
						+ PkgRdirectionConst.STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strResultEdate, strFormat)) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgrdirection.datetime",
						rb.getString("item.pkgrdirection.header.pkg.edate")));
			} else {
				resultEdate = AecDateUtils.getTimestampYmdHmFormat(
					strResultEdate, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getResultETime())) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgrdirection.time.notinputday",
						rb.getString("item.pkgrdirection.header.pkg.edate")));
			}
		}

		// 包装開始実績日時と包装終了実績日時の大小関係チェック
		if (resultSdate != null && resultEdate != null) {
			if (resultSdate.compareTo(resultEdate) > 0) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgrdirection.datetime.compare",
						rb.getString("item.pkgrdirection.header.pkg.sdate"),
						rb.getString("item.pkgrdirection.header.pkg.edate")));
			}
		}

		// 分納ありの場合
		if (this.divideFlag) {
			// 包装開始日時(FROM)
			if (!StringUtils.isEmpty(getNextSDay())) {
				strResultSdate = getNextSDay();
				if (!StringUtils.isEmpty(getNextSTime())) {
					strResultSdate = strResultSdate + " " + getNextSTime();
				} else {
					strResultSdate = strResultSdate + " "
							+ PkgRdirectionConst.STR_MIN_TIME;
				}
				// 日付チェック
				if (!AecDateUtils.chkDate(strResultSdate, strFormat)) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.pkgrdirection.datetime", rb
								.getString("item.pkgdirection.pkg.sdate")));
				} else {
					resultSdate = AecDateUtils.getTimestampYmdHmFormat(
						strResultSdate, strFormat);
				}
			} else {
				if (!StringUtils.isEmpty(getNextSTime())) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage(
								"errors.pkgrdirection.time.notinputday",
								rb.getString("item.pkgdirection.pkg.sdate")));
				}
			}
			// 包装終了日時(To)
			if (!StringUtils.isEmpty(getNextEDay())) {
				strResultEdate = getNextEDay();
				if (!StringUtils.isEmpty(getNextETime())) {
					strResultEdate = strResultEdate + " " + getNextETime();
				} else {
					strResultEdate = strResultEdate + " "
							+ PkgRdirectionConst.STR_MAX_TIME;
				}
				// 日付チェック
				if (!AecDateUtils.chkDate(strResultEdate, strFormat)) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.pkgrdirection.datetime", rb
								.getString("item.pkgdirection.pkg.edate")));
				} else {
					resultEdate = AecDateUtils.getTimestampYmdHmFormat(
						strResultEdate, strFormat);
				}
			} else {
				if (!StringUtils.isEmpty(getNextETime())) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage(
								"errors.pkgrdirection.time.notinputday",
								rb.getString("item.pkgdirection.pkg.edate")));
				}
			}
			if (resultSdate != null && resultEdate != null) {
				if (resultSdate.compareTo(resultEdate) > 0) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage(
								"errors.pkgrdirection.datetime.compare",
								rb.getString("item.pkgdirection.pkg.sdate"),
								rb.getString("item.pkgdirection.pkg.edate")));
				}
			}

		}
	}

	//
	// インスタンスメソッド
	//
	/**
	 * ステータスコンボボックス取得.
	 * @return List<ComboBoxItems> ステータスコンボボックス
	 */
	public List<ComboBoxItems> getStatusCombo() {
		return statusCombo;
	}

	/**
	 * ステータスコンボボックス設定.
	 * @param statusCombo ステータスコンボボックス
	 */
	public void setStatusCombo(final List<ComboBoxItems> statusCombo) {
		this.statusCombo = statusCombo;
	}

	/**
	 * 次回予定数量取得
	 * @return String 次回予定数量
	 */
	public String getNextPlanedQty() {
		return this.nextPlanedQty;
	}

	/**
	 * 次回予定数量設定
	 * @param nextPlanedQty 次回予定数量
	 */
	public void setNextPlanedQty(final String nextPlanedQty) {
		this.nextPlanedQty = nextPlanedQty;
	}

	/**
	 * 小数点桁数(次回予定数量)取得
	 * @return qtyDecimalPoint
	 */
	public String getQtyDecimalPoint() {
		return qtyDecimalPoint;
	}

	/**
	 * 小数点桁数(次回予定数量)設定
	 * @param qtyDecimalPoint 小数点桁数(次回予定数量)
	 */
	public void setQtyDecimalPoint(final String qtyDecimalPoint) {
		this.qtyDecimalPoint = qtyDecimalPoint;
	}

	/**
	 * 端数区分(次回予定数量)取得
	 * @return qtyRoundDivision
	 */
	public String getQtyRoundDivision() {
		return qtyRoundDivision;
	}

	/**
	 * 端数区分(次回予定数量)設定
	 * @param qtyRoundDivision 端数区分(次回予定数量)
	 */
	public void setQtyRoundDivision(final String qtyRoundDivision) {
		this.qtyRoundDivision = qtyRoundDivision;
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
	 * 包装開始予定日取得
	 * @return String 包装開始予定日
	 */
	public String getResultSDay() {
		return resultSDay;
	}

	/**
	 * 包装開始実施日設定
	 * @param resultSDay 包装開始実施日
	 */
	public void setResultSDay(final String resultSDay) {
		this.resultSDay = resultSDay;
	}

	/**
	 * 包装開始実施時刻取得
	 * @return String 包装開始実施時刻
	 */
	public String getResultSTime() {
		return resultSTime;
	}

	/**
	 * 包装開始実施時刻設定
	 * @param resultSTime 包装開始実施時刻
	 */
	public void setResultSTime(final String resultSTime) {
		this.resultSTime = resultSTime;
	}

	/**
	 * 包装終了実施日取得
	 * @return String 包装終了実施日
	 */
	public String getResultEDay() {
		return resultEDay;
	}

	/**
	 * 包装終了実施日設定
	 * @param resultEDay 包装終了実施日
	 */
	public void setResultEDay(final String resultEDay) {
		this.resultEDay = resultEDay;
	}

	/**
	 * 包装終了実施時刻取得
	 * @return String 包装終了実施時刻
	 */
	public String getResultETime() {
		return resultETime;
	}

	/**
	 * 包装終了実施時刻設定
	 * @param resultETime 包装終了実施時刻
	 */
	public void setResultETime(final String resultETime) {
		this.resultETime = resultETime;
	}

	/**
	 * 分納フラグ取得
	 * @return boolean 分納フラグ
	 */
	public boolean isDivideFlag() {
		return divideFlag;
	}

	/**
	 * 分納フラグ設定
	 * @param divideFlag 分納フラグ
	 */
	public void setDivideFlag(final boolean divideFlag) {
		this.divideFlag = divideFlag;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
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
	 * 数値桁数チェック部品の固定区分取得.
	 * @return String 区分
	 */
	public String getFixedUnitDiv() {
		return PkgDirectionConst.UNIT_DIVISION_HAIGO;
	}

	/**
	 * nextEDayを取得します。
	 * @return nextEDay
	 */
	public String getNextEDay() {
		return nextEDay;
	}

	/**
	 * nextEDayを設定します。
	 * @param nextEDay nextEDay
	 */
	public void setNextEDay(final String nextEDay) {
		this.nextEDay = nextEDay;
	}

	/**
	 * nextETimeを取得します。
	 * @return nextETime
	 */
	public String getNextETime() {
		return nextETime;
	}

	/**
	 * nextETimeを設定します。
	 * @param nextETime nextETime
	 */
	public void setNextETime(final String nextETime) {
		this.nextETime = nextETime;
	}

	/**
	 * nextSDayを取得します。
	 * @return nextSDay
	 */
	public String getNextSDay() {
		return nextSDay;
	}

	/**
	 * nextSDayを設定します。
	 * @param nextSDay nextSDay
	 */
	public void setNextSDay(final String nextSDay) {
		this.nextSDay = nextSDay;
	}

	/**
	 * nextSTimeを取得します。
	 * @return nextSTime
	 */
	public String getNextSTime() {
		return nextSTime;
	}

	/**
	 * nextSTimeを設定します。
	 * @param nextSTime nextSTime
	 */
	public void setNextSTime(final String nextSTime) {
		this.nextSTime = nextSTime;
	}

}
