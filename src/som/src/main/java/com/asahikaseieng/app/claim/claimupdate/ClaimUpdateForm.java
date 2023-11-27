/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.claimupdate;

import java.sql.Date;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 請求更新処理 Formクラス
 * @author tosco
 */
public class ClaimUpdateForm extends AbstractForm {

	/** エラー項目名 請求締め日 */
	private static final String ERROR_ITEM_CREDITDATE = "請求締め日";

	/** エラーメッセージKEY 日付 */
	private static final String ERROR_KEY_DATE = "errors.date";

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド

	//

	/** 部門コード */
	private String sectionCd;

	/** 部門名称 */
	private String sectionName;

	/** 請求先コード */
	private String venderCd;

	/** 請求先名称 */
	private String venderName;

	/** 請求締め日 */
	private Date creditDate;

	/** 請求締め日(文字列) */
	private String strCreditDate;

	/** 仮締区分 */
	private boolean tempClosingFlg;

	/** 担当者ID */
	private String tantoCd;

	/** 未来日チェックフラグ */
	private String checkDateFlg;

	/** 請求先名称＋請求締め日(オートコンプ用) */
	private String venderCreditDate;

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
			// 仮締区分

			setTempClosingFlg(false);
		}
	}

	/**
	 * コンストラクタ.請求更新処理
	 * 
	 */
	public ClaimUpdateForm() {
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

		// 部門コード

		setSectionCd(null);

		// 部門名称
		setSectionName(null);

		// 請求先コード

		setVenderCd(null);

		// 請求先名称
		setVenderName(null);

		// 請求締め日
		setCreditDate(null);

		// 請求締め日(文字列)
		setStrCreditDate(null);

		// 仮締区分

		setTempClosingFlg(false);

		// 担当者ID
		setTantoCd(null);

		// 未来日チェックフラグ
		setCheckDateFlg(null);

		// 請求先名称＋請求締め日
		setVenderCreditDate(null);

	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * 部門コード取得.
	 * @return String 部門コード
	 * 
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コード設定.
	 * @param sectionCd 部門コード
	 * 
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
	 * 仮締区分取得.
	 * @return boolean 仮締区分
	 * 
	 */
	public boolean isTempClosingFlg() {
		return tempClosingFlg;
	}

	/**
	 * 仮締区分設定
	 * 
	 * @param tempClosingFlg 仮締区分
	 * 
	 */
	public void setTempClosingFlg(final boolean tempClosingFlg) {
		this.tempClosingFlg = tempClosingFlg;
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

}
