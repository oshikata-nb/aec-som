/*
 * Created on 2008/01/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 製造指図作成処理
 * @author a1020630
 */
public final class ProCrdirCallDto {

	/** Constructor */
	public ProCrdirCallDto() {
	}

	/** pLngDirdiv_PROCEDURE_PARAMETER */
	public static final String pLngDirdiv_PROCEDURE_PARAMETER = "in";

	/** pLngPardiv_PROCEDURE_PARAMETER */
	public static final String pLngPardiv_PROCEDURE_PARAMETER = "in";

	/** pStrParno_PROCEDURE_PARAMETER */
	public static final String pStrParno_PROCEDURE_PARAMETER = "in";

	/** pStrOrderno_PROCEDURE_PARAMETER */
	public static final String pStrOrderno_PROCEDURE_PARAMETER = "in";

	/** pLngOrderlineno_PROCEDURE_PARAMETER */
	public static final String pLngOrderlineno_PROCEDURE_PARAMETER = "in";

	/** pStrPdlinecd_PROCEDURE_PARAMETER */
	public static final String pStrPdlinecd_PROCEDURE_PARAMETER = "in";

	/** pStrItemcd_PROCEDURE_PARAMETER */
	public static final String pStrItemcd_PROCEDURE_PARAMETER = "in";

	/** pStrRcpidxcd_PROCEDURE_PARAMETER */
	public static final String pStrRcpidxcd_PROCEDURE_PARAMETER = "in";

	/** pLngNeedqty_PROCEDURE_PARAMETER */
	public static final String pLngNeedqty_PROCEDURE_PARAMETER = "in";

	/** pDtePdstart_PROCEDURE_PARAMETER */
	public static final String pDtePdstart_PROCEDURE_PARAMETER = "in";

	/** pDtePdend_PROCEDURE_PARAMETER */
	public static final String pDtePdend_PROCEDURE_PARAMETER = "in";

	/** pStrLot_PROCEDURE_PARAMETER */
	public static final String pStrLot_PROCEDURE_PARAMETER = "in";

	/** pStrLocationcd_PROCEDURE_PARAMETER */
	public static final String pStrLocationcd_PROCEDURE_PARAMETER = "in";

	/** pStrTeki_PROCEDURE_PARAMETER */
	public static final String pStrTeki_PROCEDURE_PARAMETER = "in";

	/** pLngDirsts_PROCEDURE_PARAMETER */
	public static final String pLngDirsts_PROCEDURE_PARAMETER = "in";

	/** pStrInputorcd_PROCEDURE_PARAMETER */
	public static final String pStrInputorcd_PROCEDURE_PARAMETER = "in";

	/** outPara_PROCEDURE_PARAMETER */
	public static final String outPara_PROCEDURE_PARAMETER = "out";

	private java.math.BigDecimal pLngDirdiv;

	private BigDecimal pLngPardiv;

	private String pStrParno;

	private String pStrOrderno;

	private java.math.BigDecimal pLngOrderlineno;

	private String pStrPdlinecd;

	private String pStrItemcd;

	private String pStrRcpidxcd;

	private java.math.BigDecimal pLngNeedqty;

	private java.sql.Timestamp pDtePdstart;

	private java.sql.Timestamp pDtePdend;

	private String pStrLot;

	private String pStrLocationcd;

	private String pStrTeki;

	private java.math.BigDecimal pLngDirsts;

	private String pStrInputorcd;

	private String outPara;

	/**
	 * pLngDirdivを取得します。
	 * @return pLngDirdiv
	 */
	public java.math.BigDecimal getPLngDirdiv() {
		return pLngDirdiv;
	}

	/**
	 * pLngDirdivを設定します。
	 * @param lngDirdiv pLngDirdiv
	 */
	public void setPLngDirdiv(final java.math.BigDecimal lngDirdiv) {
		pLngDirdiv = lngDirdiv;
	}

	/**
	 * pStrParnoを取得します。
	 * @return pStrParno
	 */
	public String getPStrParno() {
		return pStrParno;
	}

	/**
	 * pStrParnoを設定します。
	 * @param strParno pStrParno
	 */
	public void setPStrParno(final String strParno) {
		pStrParno = strParno;
	}

	/**
	 * pStrOrdernoを取得します。
	 * @return pStrOrderno
	 */
	public String getPStrOrderno() {
		return pStrOrderno;
	}

	/**
	 * pStrOrdernoを設定します。
	 * @param strOrderno pStrOrderno
	 */
	public void setPStrOrderno(final String strOrderno) {
		pStrOrderno = strOrderno;
	}

	/**
	 * pLngOrderlinenoを取得します。
	 * @return pLngOrderlineno
	 */
	public java.math.BigDecimal getPLngOrderlineno() {
		return pLngOrderlineno;
	}

	/**
	 * pLngOrderlinenoを設定します。
	 * @param lngOrderlineno pLngOrderlineno
	 */
	public void setPLngOrderlineno(final java.math.BigDecimal lngOrderlineno) {
		pLngOrderlineno = lngOrderlineno;
	}

	/**
	 * pStrPdlinecdを取得します。
	 * @return pStrPdlinecd
	 */
	public String getPStrPdlinecd() {
		return pStrPdlinecd;
	}

	/**
	 * pStrPdlinecdを設定します。
	 * @param strPdlinecd pStrPdlinecd
	 */
	public void setPStrPdlinecd(final String strPdlinecd) {
		pStrPdlinecd = strPdlinecd;
	}

	/**
	 * pStrItemcdを取得します。
	 * @return pStrItemcd
	 */
	public String getPStrItemcd() {
		return pStrItemcd;
	}

	/**
	 * pStrItemcdを設定します。
	 * @param strItemcd pStrItemcd
	 */
	public void setPStrItemcd(final String strItemcd) {
		pStrItemcd = strItemcd;
	}

	/**
	 * pStrRcpidxcdを取得します。
	 * @return pStrRcpidxcd
	 */
	public String getPStrRcpidxcd() {
		return pStrRcpidxcd;
	}

	/**
	 * pStrRcpidxcdを設定します。
	 * @param strRcpidxcd pStrRcpidxcd
	 */
	public void setPStrRcpidxcd(final String strRcpidxcd) {
		pStrRcpidxcd = strRcpidxcd;
	}

	/**
	 * pLngNeedqtyを取得します。
	 * @return pLngNeedqty
	 */
	public java.math.BigDecimal getPLngNeedqty() {
		return pLngNeedqty;
	}

	/**
	 * pLngNeedqtyを設定します。
	 * @param lngNeedqty pLngNeedqty
	 */
	public void setPLngNeedqty(final java.math.BigDecimal lngNeedqty) {
		pLngNeedqty = lngNeedqty;
	}

	/**
	 * pDtePdstartを取得します。
	 * @return pDtePdstart
	 */
	public java.sql.Timestamp getPDtePdstart() {
		return pDtePdstart;
	}

	/**
	 * pDtePdstartを設定します。
	 * @param dtePdstart pDtePdstart
	 */
	public void setPDtePdstart(final java.sql.Timestamp dtePdstart) {
		pDtePdstart = dtePdstart;
	}

	/**
	 * pDtePdendを取得します。
	 * @return pDtePdend
	 */
	public java.sql.Timestamp getPDtePdend() {
		return pDtePdend;
	}

	/**
	 * pDtePdendを設定します。
	 * @param dtePdend pDtePdend
	 */
	public void setPDtePdend(final java.sql.Timestamp dtePdend) {
		pDtePdend = dtePdend;
	}

	/**
	 * pStrLotを取得します。
	 * @return pStrLot
	 */
	public String getPStrLot() {
		return pStrLot;
	}

	/**
	 * pStrLotを設定します。
	 * @param strLot pStrLot
	 */
	public void setPStrLot(final String strLot) {
		pStrLot = strLot;
	}

	/**
	 * pStrLocationcdを取得します。
	 * @return pStrLocationcd
	 */
	public String getPStrLocationcd() {
		return pStrLocationcd;
	}

	/**
	 * pStrLocationcdを設定します。
	 * @param strLocationcd pStrLocationcd
	 */
	public void setPStrLocationcd(final String strLocationcd) {
		pStrLocationcd = strLocationcd;
	}

	/**
	 * pStrTekiを取得します。
	 * @return pStrTeki
	 */
	public String getPStrTeki() {
		return pStrTeki;
	}

	/**
	 * pStrTekiを設定します。
	 * @param strTeki pStrTeki
	 */
	public void setPStrTeki(final String strTeki) {
		pStrTeki = strTeki;
	}

	/**
	 * pLngDirstsを取得します。
	 * @return pLngDirsts
	 */
	public java.math.BigDecimal getPLngDirsts() {
		return pLngDirsts;
	}

	/**
	 * pLngDirstsを設定します。
	 * @param lngDirsts pLngDirsts
	 */
	public void setPLngDirsts(final java.math.BigDecimal lngDirsts) {
		pLngDirsts = lngDirsts;
	}

	/**
	 * outParaを取得します。
	 * @return outPara
	 */
	public String getOutPara() {
		return outPara;
	}

	/**
	 * outParaを設定します。
	 * @param outPara outPara
	 */
	public void setOutPara(final String outPara) {
		this.outPara = outPara;
	}

	/**
	 * pLngPardivを取得します。
	 * @return pLngPardiv
	 */
	public BigDecimal getPLngPardiv() {
		return pLngPardiv;
	}

	/**
	 * pLngPardivを設定します。
	 * @param lngPardiv pLngPardiv
	 */
	public void setPLngPardiv(final BigDecimal lngPardiv) {
		pLngPardiv = lngPardiv;
	}

	/**
	 * pStrInputorcdを取得します。
	 * @return pStrInputorcd
	 */
	public String getPStrInputorcd() {
		return pStrInputorcd;
	}

	/**
	 * pStrInputorcdを設定します。
	 * @param strInputorcd pStrInputorcd
	 */
	public void setPStrInputorcd(final String strInputorcd) {
		pStrInputorcd = strInputorcd;
	}
}
