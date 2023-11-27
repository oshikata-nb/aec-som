/*
 * Created on 2009/07/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepTegataDetailクラス.
 * @author kanri-user
 */
public class RepTegataDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepTegataDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション paymentDate */
	public static final String paymentDate_COLUMN = "PAYMENT_DATE";

	/** COLUMNアノテーション tegataNo */
	public static final String tegataNo_COLUMN = "TEGATA_NO";

	/** COLUMNアノテーション edaban */
	public static final String edaban_COLUMN = "EDABAN";

	/** COLUMNアノテーション kubun */
	public static final String kubun_COLUMN = "KUBUN";

	/** COLUMNアノテーション shubetu */
	public static final String shubetu_COLUMN = "SHUBETU";

	/** COLUMNアノテーション bnaibuNo */
	public static final String bnaibuNo_COLUMN = "BNAIBU_NO";

	/** COLUMNアノテーション furiYmd */
	public static final String furiYmd_COLUMN = "FURI_YMD";

	/** COLUMNアノテーション mankiYmd */
	public static final String mankiYmd_COLUMN = "MANKI_YMD";

	/** COLUMNアノテーション kessaiYmd */
	public static final String kessaiYmd_COLUMN = "KESSAI_YMD";

	/** COLUMNアノテーション kingaku */
	public static final String kingaku_COLUMN = "KINGAKU";

	/** COLUMNアノテーション saito */
	public static final String saito_COLUMN = "SAITO";

	/** COLUMNアノテーション toriCd */
	public static final String toriCd_COLUMN = "TORI_CD";

	/** COLUMNアノテーション bankCd */
	public static final String bankCd_COLUMN = "BANK_CD";

	/** COLUMNアノテーション kozaNo */
	public static final String kozaNo_COLUMN = "KOZA_NO";

	/** COLUMNアノテーション yShubetu */
	public static final String yShubetu_COLUMN = "Y_SHUBETU";

	/** COLUMNアノテーション tekiyo */
	public static final String tekiyo_COLUMN = "TEKIYO";

	/** COLUMNアノテーション idoKubun1 */
	public static final String idoKubun1_COLUMN = "IDO_KUBUN1";

	/** COLUMNアノテーション jSiwaKbn */
	public static final String jSiwaKbn_COLUMN = "J_SIWA_KBN";

	/** COLUMNアノテーション jSiwaNo */
	public static final String jSiwaNo_COLUMN = "J_SIWA_NO";

	/** COLUMNアノテーション eitan */
	public static final String eitan_COLUMN = "EITAN";

	/** COLUMNアノテーション furiBank */
	public static final String furiBank_COLUMN = "FURI_BANK";

	/** COLUMNアノテーション furiNin */
	public static final String furiNin_COLUMN = "FURI_NIN";

	/** COLUMNアノテーション transmissionDate */
	public static final String transmissionDate_COLUMN = "TRANSMISSION_DATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション bankMasterName */
	public static final String bankMasterName_COLUMN = "BANK_MASTER_NAME";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	//
	// インスタンスフィールド
	//

	private String key;

	private String slipNo;

	private java.math.BigDecimal rowNo;

	private java.sql.Timestamp paymentDate;

	private String tegataNo;

	private String edaban;

	private String kubun;

	private String shubetu;

	private String bnaibuNo;

	private java.sql.Timestamp furiYmd;

	private java.sql.Timestamp mankiYmd;

	private java.sql.Timestamp kessaiYmd;

	private java.math.BigDecimal kingaku;

	private java.math.BigDecimal saito;

	private String toriCd;

	private String bankCd;

	private String kozaNo;

	private String yShubetu;

	private String tekiyo;

	private String idoKubun1;

	private String jSiwaKbn;

	private String jSiwaNo;

	private String eitan;

	private String furiBank;

	private String furiNin;

	private java.sql.Timestamp transmissionDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String organizationName;

	private String bankMasterName;

	private String venderName1;

	//
	// インスタンスメソッド
	//

	/**
	 * key取得.
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * key設定.
	 * @param key key
	 */
	public void setKey(final String key) {
		this.key = key;
	}

	/**
	 * slipNo取得.
	 * @return slipNo
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * slipNo設定.
	 * @param slipNo slipNo
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * rowNo取得.
	 * @return rowNo
	 */
	public java.math.BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * rowNo設定.
	 * @param rowNo rowNo
	 */
	public void setRowNo(final java.math.BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * paymentDate取得.
	 * @return paymentDate
	 */
	public java.sql.Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	/**
	 * paymentDate設定.
	 * @param paymentDate paymentDate
	 */
	public void setPaymentDate(final java.sql.Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * tegataNo取得.
	 * @return tegataNo
	 */
	public String getTegataNo() {
		return this.tegataNo;
	}

	/**
	 * tegataNo設定.
	 * @param tegataNo tegataNo
	 */
	public void setTegataNo(final String tegataNo) {
		this.tegataNo = tegataNo;
	}

	/**
	 * edaban取得.
	 * @return edaban
	 */
	public String getEdaban() {
		return this.edaban;
	}

	/**
	 * edaban設定.
	 * @param edaban edaban
	 */
	public void setEdaban(final String edaban) {
		this.edaban = edaban;
	}

	/**
	 * kubun取得.
	 * @return kubun
	 */
	public String getKubun() {
		return this.kubun;
	}

	/**
	 * kubun設定.
	 * @param kubun kubun
	 */
	public void setKubun(final String kubun) {
		this.kubun = kubun;
	}

	/**
	 * shubetu取得.
	 * @return shubetu
	 */
	public String getShubetu() {
		return this.shubetu;
	}

	/**
	 * shubetu設定.
	 * @param shubetu shubetu
	 */
	public void setShubetu(final String shubetu) {
		this.shubetu = shubetu;
	}

	/**
	 * bnaibuNo取得.
	 * @return bnaibuNo
	 */
	public String getBnaibuNo() {
		return this.bnaibuNo;
	}

	/**
	 * bnaibuNo設定.
	 * @param bnaibuNo bnaibuNo
	 */
	public void setBnaibuNo(final String bnaibuNo) {
		this.bnaibuNo = bnaibuNo;
	}

	/**
	 * furiYmd取得.
	 * @return furiYmd
	 */
	public java.sql.Timestamp getFuriYmd() {
		return this.furiYmd;
	}

	/**
	 * furiYmd設定.
	 * @param furiYmd furiYmd
	 */
	public void setFuriYmd(final java.sql.Timestamp furiYmd) {
		this.furiYmd = furiYmd;
	}

	/**
	 * mankiYmd取得.
	 * @return mankiYmd
	 */
	public java.sql.Timestamp getMankiYmd() {
		return this.mankiYmd;
	}

	/**
	 * mankiYmd設定.
	 * @param mankiYmd mankiYmd
	 */
	public void setMankiYmd(final java.sql.Timestamp mankiYmd) {
		this.mankiYmd = mankiYmd;
	}

	/**
	 * kessaiYmd取得.
	 * @return kessaiYmd
	 */
	public java.sql.Timestamp getKessaiYmd() {
		return this.kessaiYmd;
	}

	/**
	 * kessaiYmd設定.
	 * @param kessaiYmd kessaiYmd
	 */
	public void setKessaiYmd(final java.sql.Timestamp kessaiYmd) {
		this.kessaiYmd = kessaiYmd;
	}

	/**
	 * kingaku取得.
	 * @return kingaku
	 */
	public java.math.BigDecimal getKingaku() {
		return this.kingaku;
	}

	/**
	 * kingaku設定.
	 * @param kingaku kingaku
	 */
	public void setKingaku(final java.math.BigDecimal kingaku) {
		this.kingaku = kingaku;
	}

	/**
	 * saito取得.
	 * @return saito
	 */
	public java.math.BigDecimal getSaito() {
		return this.saito;
	}

	/**
	 * saito設定.
	 * @param saito saito
	 */
	public void setSaito(final java.math.BigDecimal saito) {
		this.saito = saito;
	}

	/**
	 * toriCd取得.
	 * @return toriCd
	 */
	public String getToriCd() {
		return this.toriCd;
	}

	/**
	 * toriCd設定.
	 * @param toriCd toriCd
	 */
	public void setToriCd(final String toriCd) {
		this.toriCd = toriCd;
	}

	/**
	 * bankCd取得.
	 * @return bankCd
	 */
	public String getBankCd() {
		return this.bankCd;
	}

	/**
	 * bankCd設定.
	 * @param bankCd bankCd
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
	}

	/**
	 * kozaNo取得.
	 * @return kozaNo
	 */
	public String getKozaNo() {
		return this.kozaNo;
	}

	/**
	 * kozaNo設定.
	 * @param kozaNo kozaNo
	 */
	public void setKozaNo(final String kozaNo) {
		this.kozaNo = kozaNo;
	}

	/**
	 * yShubetu取得.
	 * @return yShubetu
	 */
	public String getYShubetu() {
		return this.yShubetu;
	}

	/**
	 * yShubetu設定.
	 * @param yShubetu yShubetu
	 */
	public void setYShubetu(final String yShubetu) {
		this.yShubetu = yShubetu;
	}

	/**
	 * tekiyo取得.
	 * @return tekiyo
	 */
	public String getTekiyo() {
		return this.tekiyo;
	}

	/**
	 * tekiyo設定.
	 * @param tekiyo tekiyo
	 */
	public void setTekiyo(final String tekiyo) {
		this.tekiyo = tekiyo;
	}

	/**
	 * idoKubun1取得.
	 * @return idoKubun1
	 */
	public String getIdoKubun1() {
		return this.idoKubun1;
	}

	/**
	 * idoKubun1設定.
	 * @param idoKubun1 idoKubun1
	 */
	public void setIdoKubun1(final String idoKubun1) {
		this.idoKubun1 = idoKubun1;
	}

	/**
	 * jSiwaKbn取得.
	 * @return jSiwaKbn
	 */
	public String getJSiwaKbn() {
		return this.jSiwaKbn;
	}

	/**
	 * jSiwaKbn設定.
	 * @param jSiwaKbn jSiwaKbn
	 */
	public void setJSiwaKbn(final String jSiwaKbn) {
		this.jSiwaKbn = jSiwaKbn;
	}

	/**
	 * jSiwaNo取得.
	 * @return jSiwaNo
	 */
	public String getJSiwaNo() {
		return this.jSiwaNo;
	}

	/**
	 * jSiwaNo設定.
	 * @param jSiwaNo jSiwaNo
	 */
	public void setJSiwaNo(final String jSiwaNo) {
		this.jSiwaNo = jSiwaNo;
	}

	/**
	 * eitan取得.
	 * @return eitan
	 */
	public String getEitan() {
		return this.eitan;
	}

	/**
	 * eitan設定.
	 * @param eitan eitan
	 */
	public void setEitan(final String eitan) {
		this.eitan = eitan;
	}

	/**
	 * furiBank取得.
	 * @return furiBank
	 */
	public String getFuriBank() {
		return this.furiBank;
	}

	/**
	 * furiBank設定.
	 * @param furiBank furiBank
	 */
	public void setFuriBank(final String furiBank) {
		this.furiBank = furiBank;
	}

	/**
	 * furiNin取得.
	 * @return furiNin
	 */
	public String getFuriNin() {
		return this.furiNin;
	}

	/**
	 * furiNin設定.
	 * @param furiNin furiNin
	 */
	public void setFuriNin(final String furiNin) {
		this.furiNin = furiNin;
	}

	/**
	 * transmissionDate取得.
	 * @return transmissionDate
	 */
	public java.sql.Timestamp getTransmissionDate() {
		return this.transmissionDate;
	}

	/**
	 * transmissionDate設定.
	 * @param transmissionDate transmissionDate
	 */
	public void setTransmissionDate(final java.sql.Timestamp transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * organizationName取得.
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * bankMasterName取得.
	 * @return bankMasterName
	 */
	public String getBankMasterName() {
		return this.bankMasterName;
	}

	/**
	 * bankMasterName設定.
	 * @param bankMasterName bankMasterName
	 */
	public void setBankMasterName(final String bankMasterName) {
		this.bankMasterName = bankMasterName;
	}

	/**
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}

