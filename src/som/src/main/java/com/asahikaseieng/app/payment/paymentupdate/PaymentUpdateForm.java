/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 支払更新処理 Formクラス
 * @author tosco
 */
public class PaymentUpdateForm extends AbstractForm {

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

	/** 支払先名称 */
	private String venderName;

	/** 支払締め日（Date型） */
	private Date payableDate;

	/** 支払締め日（String型） */
	private String strPayableDate;

	/** 担当者ID */
	private String tantoCd;

	/** 未来日チェックフラグ */
	private String checkDateFlg;

	/** 支払先名称＋支払締め日(オートコンプ用) */
	private String venderPayableDate;

	/** 画面ID */
	private BigDecimal screenId;

	/** プロシージャログメッセージ */
	private String logMsg;

	/** プロシージャログフラグ */
	private String logFlg;

	/** プロシージャ実行フラグ */
	private String procFlg;

	/** プロシージャエラーメッセージ */
	private String errorMsg;

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
	 * コンストラクタ.支払更新処理
	 */
	public PaymentUpdateForm() {
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
			validateDate(errors);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {
		setOrganizationCd(null); // 部署コード
		setOrganizationName(null); // 部署名称
		setVenderCd(null); // 支払先コード
		setVenderName(null); // 支払先名称
		setStrPayableDate(null); // 支払締め日（Date型）
		setPayableDate(null); // 支払締め日（String型）
		setTantoCd(null); // 担当者ID
		setCheckDateFlg(null); // 未来チェック
		setValidatorResults(null); // 支払先名称＋支払締め日(オートコンプ用)
		setScreenId(Constants.MENU_ID_PAYMENT_UPDATE); // 画面ID
		setLogFlg(null); // ログフラグ
		setLogMsg(null); // ログメッセージ
		setProcFlg(null); // プロシジャ実行フラグ
		setErrorMsg(null); // プロシジャエラーメッセージ
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
	 * 支払締め日（String型）.
	 * @return String 支払締め日（String型）
	 */
	public String getStrPayableDate() {
		return strPayableDate;
	}

	/**
	 * 支払締め日（String型）.
	 * @param strPayableDate 支払締め日（String型）
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
	 * 支払締め日（Date型）を取得します。
	 * @return payableDate
	 */
	public Date getPayableDate() {
		return payableDate;
	}

	/**
	 * 支払締め日（Date型）を設定します。
	 * @param payableDate 支払締め日（Date型）
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
	 * 未来日チェックフラグ取得.
	 * @return String 未来日チェックフラグ
	 */
	public String getCheckDateFlg() {
		return checkDateFlg;
	}

	/**
	 * 未来日チェックフラグ設定.
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

	/**
	 * screenIdを取得します。
	 * @return screenId
	 */
	public BigDecimal getScreenId() {
		return screenId;
	}

	/**
	 * screenIdを設定します。
	 * @param screenId screenId
	 */
	public void setScreenId(final BigDecimal screenId) {
		this.screenId = screenId;
	}

	/**
	 * logFlgを取得します。
	 * @return logFlg
	 */
	public String getLogFlg() {
		return logFlg;
	}

	/**
	 * logFlgを設定します。
	 * @param logFlg logFlg
	 */
	public void setLogFlg(final String logFlg) {
		this.logFlg = logFlg;
	}

	/**
	 * logMsgを取得します。
	 * @return logMsg
	 */
	public String getLogMsg() {
		return logMsg;
	}

	/**
	 * logMsgを設定します。
	 * @param logMsg logMsg
	 */
	public void setLogMsg(final String logMsg) {
		this.logMsg = logMsg;
	}

	/**
	 * procFlgを取得します。
	 * @return procFlg
	 */
	public String getProcFlg() {
		return procFlg;
	}

	/**
	 * procFlgを設定します。
	 * @param procFlg procFlg
	 */
	public void setProcFlg(final String procFlg) {
		this.procFlg = procFlg;
	}

	/**
	 * errorMsgを取得します。
	 * @return errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * errorMsgを設定します。
	 * @param errorMsg errorMsg
	 */
	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
