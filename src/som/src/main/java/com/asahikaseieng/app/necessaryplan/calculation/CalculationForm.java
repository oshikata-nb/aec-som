/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.calculation;

import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 
 * 原材料所要量計算Form
 * @author tosco
 */
public class CalculationForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.原材料所要量計算Form
	 */
	public CalculationForm() {
	}

	//
	// インスタンスフィールド
	//

	/** 展開開始日 */
	private String strStartDate;

	/** 展開終了日 */
	private String strEndDate;

	/** 展開開始日 */
	private Date startDate;

	/** 展開終了日 */
	private Date endDate;

	/** リードタイム */
	private BigDecimal leadTimeDivision;

	/** 安全在庫確認 */
	private BigDecimal safetyDivision;

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		// 展開開始日：文字列
		setStrStartDate(null);
		// 展開終了日：文字列
		setStrEndDate(null);
		// 展開開始日：日付型
		setStartDate(null);
		// 展開終了日：日付型
		setEndDate(null);
		// リードタイム区分[0:固定、1:変動、2:日当数]
		setLeadTimeDivision(null);
		// 安全在庫確認[0:する、1:しない]
		setSafetyDivision(null);

	}


	//	
	// インスタンスメソッド
	//		

	/**
	 * endDateを取得します。
	 * @return strEndDate
	 */
	public String getStrEndDate() {
		return strEndDate;
	}

	/**
	 * endDateを設定します。
	 * @param strEndDate strEndDate
	 */
	public void setStrEndDate(final String strEndDate) {
		this.strEndDate = strEndDate;
	}

	/**
	 * startDateを取得します。
	 * @return strStartDate
	 */
	public String getStrStartDate() {
		return strStartDate;
	}

	/**
	 * startDateを設定します。
	 * @param strStartDate strStartDate
	 */
	public void setStrStartDate(final String strStartDate) {
		this.strStartDate = strStartDate;
	}

	/**
	 * leadTimeDivisionを取得します。
	 * @return leadTimeDivision
	 */
	public BigDecimal getLeadTimeDivision() {
		return leadTimeDivision;
	}

	/**
	 * leadTimeDivisionを設定します。
	 * @param leadTimeDivision leadTimeDivision
	 */
	public void setLeadTimeDivision(final BigDecimal leadTimeDivision) {
		this.leadTimeDivision = leadTimeDivision;
	}

	/**
	 * safetyDivisionを取得します。
	 * @return safetyDivision
	 */
	public BigDecimal getSafetyDivision() {
		return safetyDivision;
	}

	/**
	 * safetyDivisionを設定します。
	 * @param safetyDivision safetyDivision
	 */
	public void setSafetyDivision(final BigDecimal safetyDivision) {
		this.safetyDivision = safetyDivision;
	}

	/**
	 * endDateを取得します。
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * endDateを設定します。
	 * @param endDate endDate
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * startDateを取得します。
	 * @return startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * startDateを設定します。
	 * @param startDate startDate
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

}
