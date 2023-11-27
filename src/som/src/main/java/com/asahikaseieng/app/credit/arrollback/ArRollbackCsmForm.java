/*
 * Created on 2008/10/17
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
 * 売掛ロールバック処理 Formクラス(カスタマイズ)
 * @author tosco
 */
public class ArRollbackCsmForm extends AbstractForm {

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

	/** 売掛締め日 */
	private Date creditDate;

	/** 担当者ID */
	private String tantoCd;

	/**
	 * コンストラクタ.売掛ロールバック
	 */
	public ArRollbackCsmForm() {
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

		// 部門名称
		setOrganizationName(null);

		// 締め年月
		setClosingMonth(null);

		// 売掛締め日
		setCreditDate(null);

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
