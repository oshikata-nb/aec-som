/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;



/**
 * 発番番号(売掛、請求、買掛、支払、相殺、消込、入金番号)取得用Dtoクラス
 * @author tosco
 */
public final class FunGetSlipNoCallDto {

	/** Constructor */
	public FunGetSlipNoCallDto() {
	}

	/** pStrSlipNo_PROCEDURE_PARAMETER */
	public static final String pStrSlipNo_PROCEDURE_PARAMETER = "return";

	/** pStrUpdatorCd_PROCEDURE_PARAMETER */
	public static final String pStrUpdatorCd_PROCEDURE_PARAMETER = "in";

	private String pStrSlipNo;

	private String pStrUpdatorCd;

	/**
	 * 発番番号取得
	 * @return String 発番番号
	 */
	public String getPStrSlipNo() {
		return pStrSlipNo;
	}

	/**
	 * 発番番号設定
	 * @param strSlipNo 発番番号
	 */
	public void setPStrSlipNo(final String strSlipNo) {
		pStrSlipNo = strSlipNo;
	}

	/**
	 * 更新者ＩＤ取得
	 * @return String 更新者ＩＤ
	 */
	public String getPStrUpdatorCd() {
		return pStrUpdatorCd;
	}

	/**
	 * 更新者ＩＤ設定
	 * @param strUpdatorCd 更新者ＩＤ
	 */
	public void setPStrUpdatorCd(final String strUpdatorCd) {
		pStrUpdatorCd = strUpdatorCd;
	}

}
