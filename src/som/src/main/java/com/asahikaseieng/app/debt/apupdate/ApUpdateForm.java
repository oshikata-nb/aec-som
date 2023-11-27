/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apupdate;

import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 買掛更新処理 Formクラス
 * @author tosco
 */
public class ApUpdateForm extends AbstractForm {

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

	/** 本締区分 */
	private boolean closingFlg;

	/** 担当者ID */
	private String tantoCd;

	/** 未来日チェックフラグ */
	private String checkDateFlg;

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
	 * コンストラクタ.買掛更新処理
	 */
	public ApUpdateForm() {
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

		if ("update".equals(getOp())) {
			// 本締区分
			setClosingFlg(false);
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
		setOrganizationCd(null); // 部署コード
		setOrganizationName(null); // 部署名称
		setClosingMonth(null); // 締め年月
		setPayableDate(null); // 買掛締め日
		setClosingFlg(false); // 本締区分
		setTantoCd(null); // 担当者ID
		setCheckDateFlg(null); // 未来日チェックフラグ
		setScreenId(Constants.MENU_ID_AP_UPDATE); // 画面ID
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
	 * 本締区分取得.
	 * @return boolean 本締区分
	 */
	public boolean isClosingFlg() {
		return closingFlg;
	}

	/**
	 * 本締区分設定
	 * @param closingFlg 本締区分
	 */
	public void setClosingFlg(final boolean closingFlg) {
		this.closingFlg = closingFlg;
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
