/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.aprollback;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 買掛ロールバック処理 Formクラス
 * @author tosco
 */
public class ApRollbackForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 締め年月 */
	private String closingMonth;

	/** 買掛締め日 */
	private Date payableDate;

	/** 担当者ID */
	private String tantoCd;

	/**
	 * コンストラクタ.買掛ロールバック
	 */
	public ApRollbackForm() {
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

		// 部署コード
		setOrganizationCd(null);
		// 部署名称
		setOrganizationName(null);
		// 締め年月
		setClosingMonth(null);
		// 買掛締め日
		setPayableDate(null);
		// 担当者ID
		setTantoCd(null);

	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * 部署コード取得.
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コード設定.
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 部署名称取得.
	 * @return String 部署名称
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称設定.
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
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
	 * 買掛締め日を取得します。
	 * @return payableDate
	 */
	public Date getPayableDate() {
		return payableDate;
	}

	/**
	 * 買掛締め日を設定します。
	 * @param payableDate 買掛締め日
	 */
	public void setPayableDate(final Date payableDate) {
		this.payableDate = payableDate;
	}

	/**
	 * 担当者IDを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * 担当者IDを設定します。
	 * @param tantoCd 担当者ID
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

}
