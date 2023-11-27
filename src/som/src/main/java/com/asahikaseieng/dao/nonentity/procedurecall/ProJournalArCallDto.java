/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

/**
 * 債権会計送信用データ作成処理
 * @author T1344224
 */
public final class ProJournalArCallDto {

	/** Constructor */
	public ProJournalArCallDto() {
	}

	/** pStrOrganization_PROCEDURE_PARAMETER */
	public static final String pStrOrganizationCd_PROCEDURE_PARAMETER = "in";

	/** pStrTargetYears_PROCEDURE_PARAMETER */
	public static final String pStrTargetYears_PROCEDURE_PARAMETER = "in";

	/** pStrTantoCd_PROCEDURE_PARAMETER */
	public static final String pStrTantoCd_PROCEDURE_PARAMETER = "in";

	/** pStrErrorReturnCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorReturnCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorReturnMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorReturnMsg_PROCEDURE_PARAMETER = "out";

	private String pStrOrganizationCd;

	private String pStrTargetYears;

	private String pStrTantoCd;

	private String pStrErrorReturnCd;

	private String pStrErrorReturnMsg;

	/**
	 * pStrOrganizationCdを取得します。
	 * @return pStrOrganizationCd
	 */
	public String getPStrOrganizationCd() {
		return pStrOrganizationCd;
	}

	/**
	 * pStrOrganizationCdを設定します。
	 * @param strOrganizationCd pStrOrganizationCd
	 */
	public void setPStrOrganizationCd(final String strOrganizationCd) {
		pStrOrganizationCd = strOrganizationCd;
	}

	/**
	 * pStrTargetYearsを取得します。
	 * @return pStrTargetYears
	 */
	public String getPStrTargetYears() {
		return pStrTargetYears;
	}

	/**
	 * pStrTargetYearsを設定します。
	 * @param strTargetYears pStrTargetYears
	 */
	public void setPStrTargetYears(final String strTargetYears) {
		pStrTargetYears = strTargetYears;
	}

	/**
	 * pStrErrorReturnCdを取得します。
	 * @return pStrErrorReturnCd
	 */
	public String getPStrErrorReturnCd() {
		return pStrErrorReturnCd;
	}

	/**
	 * pStrErrorReturnCdを設定します。
	 * @param strErrorReturnCd pStrErrorReturnCd
	 */
	public void setPStrErrorReturnCd(final String strErrorReturnCd) {
		pStrErrorReturnCd = strErrorReturnCd;
	}

	/**
	 * pStrErrorReturnMsgを取得します。
	 * @return pStrErrorReturnMsg
	 */
	public String getPStrErrorReturnMsg() {
		return pStrErrorReturnMsg;
	}

	/**
	 * pStrErrorReturnMsgを設定します。
	 * @param strErrorReturnMsg pStrErrorReturnMsg
	 */
	public void setPStrErrorReturnMsg(final String strErrorReturnMsg) {
		pStrErrorReturnMsg = strErrorReturnMsg;
	}

	/**
	 * pStrTantoCdを取得します。
	 * @return pStrTantoCd
	 */
	public String getPStrTantoCd() {
		return pStrTantoCd;
	}

	/**
	 * pStrTantoCdを設定します。
	 * @param strTantoCd pStrTantoCd
	 */
	public void setPStrTantoCd(final String strTantoCd) {
		pStrTantoCd = strTantoCd;
	}
}
