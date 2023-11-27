/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arrollback;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 売掛ロールバック処理 Formクラス
 * @author tosco
 */
public class ArRollbackForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//

	/** 部門コード */
	private String sectionCd;

	/** 部門名称 */
	private String sectionName;

	/** 締め年月 */
	private String closingMonth;

	/** 売掛締め日 */
	private Date creditDate;

	/** 担当者ID */
	private String tantoCd;

	/**
	 * コンストラクタ.売掛ロールバック
	 */
	public ArRollbackForm() {
	}

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

		// 部門コード
		setSectionCd(null);

		// 部門名称
		setSectionName(null);

		// 締め年月
		setClosingMonth(null);

		// 売掛締め日
		setCreditDate(null);

	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * 部門コード取得.
	 * @return String 部門コード
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コード設定.
	 * @param sectionCd 部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 部門名称取得.
	 * @return String 部門名称
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 部門名称設定.
	 * @param sectionName 部門名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * 締め年月取得.
	 * @return String 締め年月
	 */
	public String getClosingMonth() {
		return closingMonth;
	}

	/**
	 * 締め年月設定.
	 * @param closingMonth 締め年月
	 */
	public void setClosingMonth(final String closingMonth) {
		this.closingMonth = closingMonth;
	}

	/**
	 * 売掛締め日取得.
	 * @return Date 売掛締め日
	 */
	public Date getCreditDate() {
		return creditDate;
	}

	/**
	 * 売掛締め日設定.
	 * @param creditDate 売掛締め日
	 */
	public void setCreditDate(final Date creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * 担当者ID取得.
	 * @return String 担当者ID
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * 担当者ID設定.
	 * @param tantoCd 担当者ID
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

}
