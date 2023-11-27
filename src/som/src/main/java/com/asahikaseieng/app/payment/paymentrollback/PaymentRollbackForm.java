/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentrollback;

import java.sql.Date;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 支払更新ロールバック処理 Formクラス
 * @author tosco
 */
public class PaymentRollbackForm extends AbstractForm {

	/** エラー項目名 支払締め日 */
	private static final String ERROR_ITEM_PAYABLE_DATE = "支払締め日";

	/** エラーメッセージKEY 日付 */
	private static final String ERROR_KEY_DATE = "errors.date";

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 支払先コード */
	private String venderCd;

	/** 支払名称 */
	private String venderName;

	/** 支払締め日 */
	private String strPayableDate;

	/** 支払締め日 */
	private Date payableDate;

	/** 担当者ID */
	private String tantoCd;

	/** 未来日チェックフラグ */
	private String checkDateFlg;

	/** 支払先名称＋支払締め日(オートコンプ用) */
	private String venderPayableDate;

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
	 * コンストラクタ.支払更新ロールバック処理
	 */
	public PaymentRollbackForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("rollback".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			validateDate(errors);
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
		// 支払先コード
		setVenderCd(null);
		// 支払先名称
		setVenderName(null);
		// 締め年月
		setStrPayableDate(null);
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
	public String getStrPayableDate() {
		return strPayableDate;
	}

	/**
	 * 締め年月設定.
	 * @param strPayableDate 締め年月
	 */
	public void setStrPayableDate(final String strPayableDate) {
		this.strPayableDate = strPayableDate;
	}

	/**
	 * 支払先コード取得.
	 * @return String 支払先コード
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 支払先コード設定
	 * @param venderCd 支払先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 支払先名称取得.
	 * @return String 支払先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 支払先名称設定
	 * @param venderName 支払先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * payableDateを取得します。
	 * @return payableDate
	 */
	public Date getPayableDate() {
		return payableDate;
	}

	/**
	 * payableDateを設定します。
	 * @param payableDate payableDate
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

	/**
	 * 未来日チェックフラグを取得します。
	 * @return checkDateFlg
	 */
	public String getCheckDateFlg() {
		return checkDateFlg;
	}

	/**
	 * 未来日チェックフラグを設定します。
	 * @param checkDateFlg 未来日チェックフラグ
	 */
	public void setCheckDateFlg(final String checkDateFlg) {
		this.checkDateFlg = checkDateFlg;
	}

	/**
	 * 支払先名称＋支払締め日(オートコンプ用)を取得します。
	 * @return venderPayableDate
	 */
	public String getVenderPayableDate() {
		return venderPayableDate;
	}

	/**
	 * 支払先名称＋支払締め日(オートコンプ用)を設定します。
	 * @param venderPayableDate 支払先名称＋支払締め日(オートコンプ用)
	 */
	public void setVenderPayableDate(final String venderPayableDate) {
		this.venderPayableDate = venderPayableDate;
	}

	/**
	 * 支払締め日(文字列)チェック<br>
	 * @param errors ActionErrors
	 */
	private void validateDate(final ActionErrors errors) {

		String strDate = this.strPayableDate;

		try {
			int ret = AecDateUtils.validateDate(strDate);
			switch (ret) {
			case 1:
				errors.add(strDate, new ActionMessage("errors.date",
						ERROR_ITEM_PAYABLE_DATE));
				break;
			case 2:
				errors.add(strDate, new ActionMessage(ERROR_KEY_DATE,
						ERROR_ITEM_PAYABLE_DATE));
				break;
			case 3:
				errors.add(strDate, new ActionMessage(ERROR_KEY_DATE,
						ERROR_ITEM_PAYABLE_DATE));
				break;
			default:

			}
		} catch (ParseException e) {
			/* 日付が変換できなかったら、エラーで返す */
			errors.add(strDate, new ActionMessage(ERROR_KEY_DATE,
					ERROR_ITEM_PAYABLE_DATE));
		}
	}

}
