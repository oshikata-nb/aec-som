/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailList;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装指図ヘッダー Formクラス.
 * @author tosco
 */
public final class PkgDirectionHeaderForm extends AbstractPkgDirectionForm {

	private static final long serialVersionUID = 1L;

	private int insertFlg; /* 新規用切替フラグ */

	//
	// インスタンスフィールド
	//
	/** 包装開始予定日 */
	private String planedSDay;

	/** 包装開始予定時刻 */
	private String planedSTime;

	/** 包装終了予定日 */
	private String planedEDay;

	/** 包装終了予定時刻 */
	private String planedETime;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** 製造指図詳細リスト */
	private List<PkgDirectionDirectionDetailList> directionDetailList;

	/** 最小生産量 */
	private BigDecimal minQty;

	/** 最大生産量 */
	private BigDecimal maxQty;

	/** 標準生産量 */
	private BigDecimal stdQty;

	/** 単位生産量 */
	private BigDecimal unitQty;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String roundDivision;

	/**
	 * コンストラクタ.包装指図ヘッダー
	 */
	public PkgDirectionHeaderForm() {
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
		if ("initNew".equals(getOp())) {
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
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			validateInsert(errors);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();

		/** 包装開始予定日 */
		this.setPlanedSDay(null);
		/** 包装開始予定時刻 */
		this.setPlanedSTime(null);
		/** 包装終了予定日 */
		this.setPlanedEDay(null);
		/** 包装終了予定時刻 */
		this.setPlanedETime(null);
		/** 備考 */
		setRemark(null);
		/** 注釈 */
		setNotes(null);
		/** 生産工場コンボボックス */
		setLineCombo(null);
		/** 最小生産量 */
		setMinQty(null);
		/** 最大生産量 */
		setMaxQty(null);
		/** 標準生産量 */
		setStdQty(null);
		/** 単位生産量 */
		setUnitQty(null);
		/** 製造指図詳細リスト */
		setDirectionDetailList(new ArrayList<PkgDirectionDirectionDetailList>());
	}

	/**
	 * 検索処理のチェック
	 * @param errors エラー内容
	 */
	private void validateInsert(final ActionErrors errors) {
		String strPlanedSdate = null;
		String strPlanedEdate = null;
		Timestamp planedSdate = null;
		Timestamp planedEdate = null;
		String strFormat = "yyyy/MM/dd HH:mm";
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		// 包装開始予定日時(FROM)
		if (!StringUtils.isEmpty(getPlanedSDay())) {
			strPlanedSdate = getPlanedSDay();
			if (!StringUtils.isEmpty(getPlanedSTime())) {
				strPlanedSdate = strPlanedSdate + " " + getPlanedSTime();
			} else {
				strPlanedSdate = strPlanedSdate + " "
						+ PkgDirectionConst.STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedSdate, strFormat)) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgdirection.datetime", rb
								.getString("item.pkgdirection.pkg.sdate")));
			} else {
				planedSdate = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedSdate, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getPlanedSTime())) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgdirection.time.notinputday", rb
								.getString("item.pkgdirection.pkg.sdate")));
			}
		}
		// 包装開始予定日時(To)
		if (!StringUtils.isEmpty(getPlanedEDay())) {
			strPlanedEdate = getPlanedEDay();
			if (!StringUtils.isEmpty(getPlanedETime())) {
				strPlanedEdate = strPlanedEdate + " " + getPlanedETime();
			} else {
				strPlanedEdate = strPlanedEdate + " "
						+ PkgDirectionConst.STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedEdate, strFormat)) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgdirection.datetime", rb
								.getString("item.pkgdirection.pkg.edate")));
			} else {
				planedEdate = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedEdate, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getPlanedETime())) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgdirection.time.notinputday", rb
								.getString("item.pkgdirection.pkg.edate")));
			}
		}

		// 包装開始予定日時と包装終了予定日時の大小関係チェック
		if (planedSdate != null && planedEdate != null) {
			if (planedSdate.compareTo(planedEdate) > 0) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.pkgdirection.datetime.compare", rb
								.getString("item.pkgdirection.pkg.sdate"), rb
								.getString("item.pkgdirection.pkg.edate")));
			}
		}
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 備考取得.
	 * @return String 備考
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 備考設定.
	 * @param remark 備考
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 注釈取得.
	 * @return String 注釈
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * 注釈設定.
	 * @param notes 注釈
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/**
	 * 生産工場コンボボックス取得.
	 * @return List<ComboBoxItems> 生産工場コンボボックス
	 */
	public List<ComboBoxItems> getLineCombo() {
		return lineCombo;
	}

	/**
	 * 生産工場コンボボックス設定.
	 * @param lineCombo 生産工場コンボボックス
	 */
	public void setLineCombo(final List<ComboBoxItems> lineCombo) {
		this.lineCombo = lineCombo;
	}

	/**
	 * 新規用切替フラグを取得します。
	 * @return int 新規用切替フラグ
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * 新規用切替フラグを設定します。
	 * @param insertFlg 新規用切替フラグ
	 */
	public void setInsertFlg(final int insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * 包装開始予定日取得.
	 * @return String 包装開始予定日
	 */
	public String getPlanedSDay() {
		return planedSDay;
	}

	/**
	 * 包装開始予定日設定.
	 * @param planedSDay 包装開始予定日
	 */
	public void setPlanedSDay(final String planedSDay) {
		this.planedSDay = planedSDay;
	}

	/**
	 * 包装開始予定時刻取得.
	 * @return String 包装開始予定時刻
	 */
	public String getPlanedSTime() {
		return planedSTime;
	}

	/**
	 * 包装開始予定時刻設定.
	 * @param planedSTime 包装開始予定時刻
	 */
	public void setPlanedSTime(final String planedSTime) {
		this.planedSTime = planedSTime;
	}

	/**
	 * 包装終了予定日取得.
	 * @return String 包装終了予定日
	 */
	public String getPlanedEDay() {
		return planedEDay;
	}

	/**
	 * 包装終了予定日設定.
	 * @param planedEDay 包装終了予定日
	 */
	public void setPlanedEDay(final String planedEDay) {
		this.planedEDay = planedEDay;
	}

	/**
	 * 包装終了予定時刻取得.
	 * @return String 包装終了予定時刻
	 */
	public String getPlanedETime() {
		return planedETime;
	}

	/**
	 * 包装終了予定時刻設定.
	 * @param planedETime 包装終了予定時刻
	 */
	public void setPlanedETime(final String planedETime) {
		this.planedETime = planedETime;
	}

	/**
	 * 製造指図詳細リスト取得.
	 * @return List<DirectionDetail> 製造指図詳細リスト
	 */
	public List<PkgDirectionDirectionDetailList> getDirectionDetailList() {
		return directionDetailList;
	}

	/**
	 * 製造指図詳細リスト設定.
	 * @param directionDetailList 製造指図詳細リスト
	 */
	public void setDirectionDetailList(
			final List<PkgDirectionDirectionDetailList> directionDetailList) {
		this.directionDetailList = directionDetailList;
	}

	/**
	 * 最小生産量取得.
	 * @return String 最小生産量
	 */
	public BigDecimal getMinQty() {
		return this.minQty;
	}

	/**
	 * 最小生産量設定.
	 * @param minQty 最小生産量
	 */
	public void setMinQty(final BigDecimal minQty) {
		this.minQty = minQty;
	}

	/**
	 * 最大生産量取得.
	 * @return BigDecimal 最大生産量
	 */
	public BigDecimal getMaxQty() {
		return this.maxQty;
	}

	/**
	 * 最大生産量設定.
	 * @param maxQty 最大生産量
	 */
	public void setMaxQty(final BigDecimal maxQty) {
		this.maxQty = maxQty;
	}

	/**
	 * 標準生産量取得.
	 * @return BigDecimal 標準生産量
	 */
	public BigDecimal getStdQty() {
		return this.stdQty;
	}

	/**
	 * 標準生産量設定.
	 * @param stdQty 標準生産量
	 */
	public void setStdQty(final BigDecimal stdQty) {
		this.stdQty = stdQty;
	}

	/**
	 * 単位生産量取得.
	 * @return BigDecimal 単位生産量
	 */
	public BigDecimal getUnitQty() {
		return this.unitQty;
	}

	/**
	 * 単位生産量設定.
	 * @param unitQty 単位生産量
	 */
	public void setUnitQty(final BigDecimal unitQty) {
		this.unitQty = unitQty;
	}

	/**
	 * 小数点桁数を取得します。
	 * @return decimalPoint 小数点桁数
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分を取得します。
	 * @return roundDivision
	 */
	public String getRoundDivision() {
		return roundDivision;
	}

	/**
	 * 端数区分を設定します。
	 * @param roundDivision 端数区分
	 */
	public void setRoundDivision(final String roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * 検索結果の件数を取得する
	 * @return 件数
	 */
	public int getCount() {
		int count = 0;
		if (directionDetailList != null) {
			count = directionDetailList.size();
		}
		return count;
	}

}
