/*
 * Created on 2008/08/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;


/**
 * 請求更新処理用Dtoクラス
 * @author tosco
 */
public final class ProClUpdateCallDto {

	/** Constructor */
	public ProClUpdateCallDto() {
	}

	/** pStrOrganizationCd_PROCEDURE_PARAMETER */
	public static final String pStrOrganizationCd_PROCEDURE_PARAMETER = "in";

	/** pStrVenderCd_PROCEDURE_PARAMETER */
	public static final String pStrVenderCd_PROCEDURE_PARAMETER = "in";

	/** pStrCreditDate_PROCEDURE_PARAMETER */
	public static final String pStrCreditDate_PROCEDURE_PARAMETER = "in";

	/** pStrClosingDate_PROCEDURE_PARAMETER */
	public static final String pStrClosingDate_PROCEDURE_PARAMETER = "in";

	/** pStrTempClosingFlg_PROCEDURE_PARAMETER */
	public static final String pStrTempClosingFlg_PROCEDURE_PARAMETER = "in";

	/** pStrInputorCd_PROCEDURE_PARAMETER */
	public static final String pStrInputorCd_PROCEDURE_PARAMETER = "in";

	/** pNumRet_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	/** pErrCd_PROCEDURE_PARAMETER */
	public static final String pErrCd_PROCEDURE_PARAMETER = "out";

	/** pErrMsg_PROCEDURE_PARAMETER */
	public static final String pErrMsg_PROCEDURE_PARAMETER = "out";

	private String pStrOrganizationCd;

	private String pStrVenderCd;

	private String pStrCreditDate;

	private String pStrClosingDate;

	private String pStrTempClosingFlg;

	private String pStrInputorCd;

	private BigDecimal pNumRet;

	private BigDecimal pErrCd;

	private String pErrMsg;

	/**
	 * 部署コード取得
	 * @return String 部署コード
	 */
	public String getPStrOrganizationCd() {
		return pStrOrganizationCd;
	}

	/**
	 * 部署コード設定
	 * @param strOrganizationCd 部署コード
	 */
	public void setPStrOrganizationCd(final String strOrganizationCd) {
		pStrOrganizationCd = strOrganizationCd;
	}

	/**
	 * 請求先コード取得
	 * @return String 請求先コード
	 */
	public String getPStrVenderCd() {
		return pStrVenderCd;
	}

	/**
	 * 請求先コード設定
	 * @param strVenderCd 請求先コード
	 */
	public void setPStrVenderCd(final String strVenderCd) {
		pStrVenderCd = strVenderCd;
	}

	/**
	 * 請求締め日取得
	 * @return String 請求締め日
	 */
	public String getPStrCreditDate() {
		return pStrCreditDate;
	}

	/**
	 * 請求締め日設定
	 * @param strCreditDate 請求締め日
	 */
	public void setPStrCreditDate(final String strCreditDate) {
		pStrCreditDate = strCreditDate;
	}

	/**
	 * 売掛締め日取得

	 * @return String 売掛締め日
	 */
	public String getPStrClosingDate() {
		return pStrClosingDate;
	}

	/**
	 * 売掛締め日設定

	 * @param strClosingDate 売掛締め日
	 */
	public void setPStrClosingDate(final String strClosingDate) {
		pStrClosingDate = strClosingDate;
	}

	/**
	 * 仮締区分取得
	 * @return String 仮締区分
	 */
	public String getPStrTempClosingFlg() {
		return pStrTempClosingFlg;
	}

	/**
	 * 仮締区分設定
	 * @param strTempClosingFlg 仮締区分
	 */
	public void setPStrTempClosingFlg(final String strTempClosingFlg) {
		pStrTempClosingFlg = strTempClosingFlg;
	}

	/**
	 * 登録者ＩＤ取得
	 * @return String 登録者ＩＤ
	 */
	public String getPStrInputorCd() {
		return pStrInputorCd;
	}

	/**
	 * 登録者ＩＤ設定
	 * @param strInputorCd 登録者ＩＤ
	 */
	public void setPStrInputorCd(final String strInputorCd) {
		pStrInputorCd = strInputorCd;
	}

	/**
	 * 戻り値取得
	 * @return BigDecimal 戻り値
	 */
	public BigDecimal getPNumRet() {
		return pNumRet;
	}

	/**
	 * 戻り値設定
	 * @param numRet 戻り値
	 */
	public void setPNumRetPara(final BigDecimal numRet) {
		this.pNumRet = numRet;
	}

	/**
	 * SQLエラーコード取得
	 * @return BigDecimal 戻り値
	 */
	public BigDecimal getPErrCd() {
		return pErrCd;
	}

	/**
	 * SQLエラーコード設定
	 * @param pErrCd 戻り値
	 */
	public void setPErrCd(final BigDecimal pErrCd) {
		this.pErrCd = pErrCd;
	}

	/**
	 * SQLエラーメッセージ取得
	 * @return String SQLエラーメッセージ
	 */
	public String getPErrMsg() {
		return pErrMsg;
	}

	/**
	 * SQLエラーメッセージ設定
	 * @param pErrMsg SQLエラーメッセージ
	 */
	public void setPErrMsg(final String pErrMsg) {
		this.pErrMsg = pErrMsg;
	}
}
