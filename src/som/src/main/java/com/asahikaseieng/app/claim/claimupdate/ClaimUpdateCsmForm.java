/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.claimupdate;

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
 * 請求更新処理 Formクラス(カスタマイズ)
 * @author tosco
 */
public class ClaimUpdateCsmForm extends AbstractForm {

	/** エラー項目名 請求締め日 */
	private static final String ERROR_ITEM_CREDITDATE = "請求締め日";

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

	/** 請求先コード */
	private String venderCd;

	/** 請求先名称 */
	private String venderName;

	/** 請求締め日 */
	private Date creditDate;

	/** 請求締め日(文字列) */
	private String strCreditDate;

	/** 本締区分 */
	private boolean closingFlg;

	/** 担当者ID */
	private String tantoCd;

	/** 未来日チェックフラグ */
	private String checkDateFlg;

	/** 請求先名称＋請求締め日(オートコンプ用) */
	private String venderCreditDate;

	/** 売掛締め日 */
	private Date closingDate;

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

		if ("update".equals(getOp())) {

			// 本締区分
			setClosingFlg(false);
		}
	}

	/**
	 * コンストラクタ.請求更新処理(カスタマイズ)
	 * 
	 */
	public ClaimUpdateCsmForm() {
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
		setVenderCd(null); // 請求先コード
		setVenderName(null); // 請求先名称
		setCreditDate(null); // 請求締め日
		setStrCreditDate(null); // 請求締め日(文字列)
		setClosingFlg(false); // 本締区分
		setTantoCd(null); // 担当者ID
		setCheckDateFlg(null); // 未来日チェックフラグ
		setVenderCreditDate(null); // 請求先名称＋請求締め日
		setClosingDate(null); // 売掛締め日
		setScreenId(Constants.MENU_ID_CLAIM_UPDATE); // 画面ID
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
	 * 
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コード設定.
	 * @param organizationCd 部署コード
	 * 
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 部署名称取得.
	 * @return String 部署名称
	 * 
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称設定.
	 * @param organizationName 部署名称
	 * 
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 請求先コード取得.
	 * @return String 請求先コード
	 * 
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 請求先コード設定
	 * 
	 * @param venderCd 請求先コード
	 * 
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 請求先名称取得.
	 * @return String 請求先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 請求先名称設定
	 * 
	 * @param venderName 請求先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 請求締め日取得.
	 * @return Date 請求締め日
	 */
	public Date getCreditDate() {
		return creditDate;
	}

	/**
	 * 請求締め日設定.
	 * @param creditDate 請求締め日
	 */
	public void setCreditDate(final Date creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * 請求締め日(文字列)取得.
	 * @return String 請求締め日(文字列)
	 */
	public String getStrCreditDate() {
		return strCreditDate;
	}

	/**
	 * 請求締め日(文字列)設定.
	 * @param strCreditDate 請求締め日(文字列)
	 */
	public void setStrCreditDate(final String strCreditDate) {
		this.strCreditDate = strCreditDate;
	}

	/**
	 * 本締区分取得.
	 * @return boolean 本締区分
	 * 
	 */
	public boolean isClosingFlg() {
		return closingFlg;
	}

	/**
	 * 本締区分設定
	 * 
	 * @param closingFlg 本締区分
	 * 
	 */
	public void setClosingFlg(final boolean closingFlg) {
		this.closingFlg = closingFlg;
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
	 * 請求先名称＋請求締め日(オートコンプ用)取得.
	 * @return String 請求先名称＋請求締め日(オートコンプ用)
	 */
	public String getVenderCreditDate() {
		return venderCreditDate;
	}

	/**
	 * 請求先名称＋請求締め日(オートコンプ用)設定.
	 * @param venderCreditDate 請求先名称＋請求締め日(オートコンプ用)
	 */
	public void setVenderCreditDate(final String venderCreditDate) {
		this.venderCreditDate = venderCreditDate;
	}

	/**
	 * 売掛締め日取得.
	 * @return Date 売掛締め日
	 */
	public Date getClosingDate() {
		return closingDate;
	}

	/**
	 * 売掛締め日設定.
	 * @param closingDate 売掛締め日
	 */
	public void setClosingDate(final Date closingDate) {
		this.closingDate = closingDate;
	}

	/**
	 * 請求締め日(文字列)チェック<br>
	 * @param errors ActionErrors
	 */
	private void validateDate(final ActionErrors errors) {

		String strDate = this.strCreditDate;

		try {
			int ret = AecDateUtils.validateDate(strDate);
			switch (ret) {
			case 1:
				errors.add(strDate, new ActionMessage("errors.date",
						ERROR_ITEM_CREDITDATE));
				break;
			case 2:
				errors.add(strDate, new ActionMessage(ERROR_KEY_DATE,
						ERROR_ITEM_CREDITDATE));
				break;
			case 3:
				errors.add(strDate, new ActionMessage(ERROR_KEY_DATE,
						ERROR_ITEM_CREDITDATE));
				break;
			default:

			}
		} catch (ParseException e) {
			/* 日付が変換できなかったら、エラーで返す */
			errors.add(strDate, new ActionMessage(ERROR_KEY_DATE,
					ERROR_ITEM_CREDITDATE));
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
