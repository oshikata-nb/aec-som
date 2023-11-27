/*
 * Created on 2008/01/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall.zaictrl;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ##ここにクラスの説明を書いてください##
 * @author a1020630
 */
public final class InoutDto {

	/** Constructor */
	public InoutDto() {
	}

	/** lngPno_PROCEDURE_PARAMETER */
	public static final String lngPno_PROCEDURE_PARAMETER = "in";

	/** strItemCd_PROCEDURE_PARAMETER */
	public static final String strItemCd_PROCEDURE_PARAMETER = "in";

	/** dbQty_PROCEDURE_PARAMETER */
	public static final String dbQty_PROCEDURE_PARAMETER = "in";

	/** dtInoutDate_PROCEDURE_PARAMETER */
	public static final String dtInoutDate_PROCEDURE_PARAMETER = "in";

	/** strLocationCd_PROCEDURE_PARAMETER */
	public static final String strLocationCd_PROCEDURE_PARAMETER = "in";

	/** strLotNo_PROCEDURE_PARAMETER */
	public static final String strLotNo_PROCEDURE_PARAMETER = "in";

	/** strRemark_PROCEDURE_PARAMETER */
	public static final String strRemark_PROCEDURE_PARAMETER = "in";

	/** strReason_PROCEDURE_PARAMETER */
	public static final String strReason_PROCEDURE_PARAMETER = "in";

	/** strReason_PROCEDURE_PARAMETER */
	public static final String strReasonCd_PROCEDURE_PARAMETER = "in";

	/** lngFunc_PROCEDURE_PARAMETER */
	public static final String lngFunc_PROCEDURE_PARAMETER = "in";

	/** lngInout_PROCEDURE_PARAMETER */
	public static final String lngInout_PROCEDURE_PARAMETER = "in";

	/** strAlot_PROCEDURE_PARAMETER */
	public static final String strAlot_PROCEDURE_PARAMETER = "in";

	/** lngCost_PROCEDURE_PARAMETER */
	public static final String lngCost_PROCEDURE_PARAMETER = "in";

	/** strLoginUser_PROCEDURE_PARAMETER */
	public static final String strLoginUser_PROCEDURE_PARAMETER = "in";

	/** outPara_PROCEDURE_PARAMETER */
	public static final String outPara_PROCEDURE_PARAMETER = "out";

	private BigDecimal lngPno;

	private String strItemCd;

	private BigDecimal dbQty;

	private Timestamp dtInoutDate;

	private String strLocationCd;

	private String strLotNo;

	private String strRemark;

	private String strReason;

	private String strReasonCd;

	private BigDecimal lngFunc;

	private BigDecimal lngInout;

	private String strAlot;

	private BigDecimal lngCost;

	private String strLoginUser;

	private String outPara;

	/**
	 * 品目コード・ゲッター
	 * @return String 品目コード
	 */
	public String getStrItemCd() {
		return strItemCd;
	}

	/**
	 * 品目コード・セッター
	 * @param strItemCd 品目コード
	 */
	public void setStrItemCd(final String strItemCd) {
		this.strItemCd = strItemCd;
	}

	/**
	 * 数量を取得します。
	 * @return BigDecimal 数量
	 */
	public BigDecimal getDbQty() {
		return dbQty;
	}

	/**
	 * 数量を設定します。
	 * @param dbQty 数量
	 */
	public void setDbQty(final BigDecimal dbQty) {
		this.dbQty = dbQty;
	}

	/**
	 * dtInoutDateを取得します。
	 * @return Timestamp 入出庫時刻
	 */
	public Timestamp getDtInoutDate() {
		return dtInoutDate;
	}

	/**
	 * dtInoutDateを設定します。
	 * @param dtInoutDate 入出庫時刻
	 */
	public void setDtInoutDate(final Timestamp dtInoutDate) {
		this.dtInoutDate = dtInoutDate;
	}

	/**
	 * ログインユーザー・ゲッター
	 * @return String ログインユーザーコード
	 */
	public String getStrLoginUser() {
		return strLoginUser;
	}

	/**
	 * ログインユーザー・セッター
	 * @param strLoginUser ログインユーザーコード
	 */
	public void setStrLoginUser(final String strLoginUser) {
		this.strLoginUser = strLoginUser;
	}

	/**
	 * 出力パラメータのセッター
	 * @param outPara outPara
	 */
	public void setOutPara(final String outPara) {
		this.outPara = outPara;
	}

	/**
	 * 出力パラメータのゲッター
	 * @return outPara
	 */
	public String getOutPara() {
		return this.outPara;
	}

	/**
	 * 処理番号を取得します。
	 * @return BigDecimal 処理番号
	 */
	public BigDecimal getLngPno() {
		return lngPno;
	}

	/**
	 * lngPnoを設定します。
	 * @param lngPno 処理番号
	 */
	public void setLngPno(final BigDecimal lngPno) {
		this.lngPno = lngPno;
	}

	/**
	 * ロケーションコードを取得します。
	 * @return String ロケーションコード
	 */
	public String getStrLocationCd() {
		return strLocationCd;
	}

	/**
	 * ロケーションコードを設定します。
	 * @param strLocationCd ロケーションコード
	 */
	public void setStrLocationCd(final String strLocationCd) {
		this.strLocationCd = strLocationCd;
	}

	/**
	 * ロット番号を取得します。
	 * @return String ロット番号
	 */
	public String getStrLotNo() {
		return strLotNo;
	}

	/**
	 * ロット番号を設定します。
	 * @param strLotNo ロット番号
	 */
	public void setStrLotNo(final String strLotNo) {
		this.strLotNo = strLotNo;
	}

	/**
	 * 理由を取得します。
	 * @return String 理由
	 */
	public String getStrReason() {
		return strReason;
	}

	/**
	 * 理由を設定します。
	 * @param strReason 理由
	 */
	public void setStrReason(final String strReason) {
		this.strReason = strReason;
	}

	/**
	 * 摘要を取得します。
	 * @return String 摘要
	 */
	public String getStrRemark() {
		return strRemark;
	}

	/**
	 * 摘要を設定します。
	 * @param strRemark 摘要
	 */
	public void setStrRemark(final String strRemark) {
		this.strRemark = strRemark;
	}

	/**
	 * lngFuncを取得します。
	 * @return lngFunc
	 */
	public BigDecimal getLngFunc() {
		return lngFunc;
	}

	/**
	 * lngFuncを設定します。
	 * @param lngFunc lngFunc
	 */
	public void setLngFunc(final BigDecimal lngFunc) {
		this.lngFunc = lngFunc;
	}

	/**
	 * lngCostを取得します。
	 * @return lngCost
	 */
	public BigDecimal getLngCost() {
		return lngCost;
	}

	/**
	 * lngCostを設定します。
	 * @param lngCost lngCost
	 */
	public void setLngCost(final BigDecimal lngCost) {
		this.lngCost = lngCost;
	}

	/**
	 * lngInoutを取得します。
	 * @return lngInout
	 */
	public BigDecimal getLngInout() {
		return lngInout;
	}

	/**
	 * lngInoutを設定します。
	 * @param lngInout lngInout
	 */
	public void setLngInout(final BigDecimal lngInout) {
		this.lngInout = lngInout;
	}

	/**
	 * strAlotを取得します。
	 * @return strAlot
	 */
	public String getStrAlot() {
		return strAlot;
	}

	/**
	 * strAlotを設定します。
	 * @param strAlot strAlot
	 */
	public void setStrAlot(final String strAlot) {
		this.strAlot = strAlot;
	}

	/**
	 * strReasonCdを取得します。
	 * @return strReasonCd
	 */
	public String getStrReasonCd() {
		return strReasonCd;
	}

	/**
	 * strReasonCdを設定します。
	 * @param strReasonCd strReasonCd
	 */
	public void setStrReasonCd(final String strReasonCd) {
		this.strReasonCd = strReasonCd;
	}

}
