/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.aptrans;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ApTransJournalListクラス.
 * @author t0011036
 */
public class ApTransJournalListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApTransJournalListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション denYmd */
	public static final String denYmd_COLUMN = "DEN_YMD";

	/** COLUMNアノテーション denNo */
	public static final String denNo_COLUMN = "DEN_NO";

	/** COLUMNアノテーション gyoNo */
	public static final String gyoNo_COLUMN = "GYO_NO";

	/** COLUMNアノテーション denKbn */
	public static final String denKbn_COLUMN = "DEN_KBN";

	/** COLUMNアノテーション krBnaibuNo */
	public static final String krBnaibuNo_COLUMN = "KR_BNAIBU_NO";

	/** COLUMNアノテーション krKnaibuNo */
	public static final String krKnaibuNo_COLUMN = "KR_KNAIBU_NO";

	/** COLUMNアノテーション krToriCd */
	public static final String krToriCd_COLUMN = "KR_TORI_CD";

	/** COLUMNアノテーション krTekimei */
	public static final String krTekimei_COLUMN = "KR_TEKIMEI";

	/** COLUMNアノテーション krProjCd */
	public static final String krProjCd_COLUMN = "KR_PROJ_CD";

	/** COLUMNアノテーション krKingaku */
	public static final String krKingaku_COLUMN = "KR_KINGAKU";

	/** COLUMNアノテーション krZei */
	public static final String krZei_COLUMN = "KR_ZEI";

	/** COLUMNアノテーション krKazei */
	public static final String krKazei_COLUMN = "KR_KAZEI";

	/** COLUMNアノテーション krZeiKbn */
	public static final String krZeiKbn_COLUMN = "KR_ZEI_KBN";

	/** COLUMNアノテーション krToritYmd */
	public static final String krToritYmd_COLUMN = "KR_TORIT_YMD";

	/** COLUMNアノテーション krGaikim */
	public static final String krGaikim_COLUMN = "KR_GAIKIM";

	/** COLUMNアノテーション krGairate */
	public static final String krGairate_COLUMN = "KR_GAIRATE";

	/** COLUMNアノテーション krGaiKbn */
	public static final String krGaiKbn_COLUMN = "KR_GAI_KBN";

	/** COLUMNアノテーション krBibou1 */
	public static final String krBibou1_COLUMN = "KR_BIBOU1";

	/** COLUMNアノテーション krBibou2 */
	public static final String krBibou2_COLUMN = "KR_BIBOU2";

	/** COLUMNアノテーション krBibou3 */
	public static final String krBibou3_COLUMN = "KR_BIBOU3";

	/** COLUMNアノテーション krFusen */
	public static final String krFusen_COLUMN = "KR_FUSEN";

	/** COLUMNアノテーション krMemo */
	public static final String krMemo_COLUMN = "KR_MEMO";

	/** COLUMNアノテーション krBiko1 */
	public static final String krBiko1_COLUMN = "KR_BIKO1";

	/** COLUMNアノテーション krBiko2 */
	public static final String krBiko2_COLUMN = "KR_BIKO2";

	/** COLUMNアノテーション krBiko3 */
	public static final String krBiko3_COLUMN = "KR_BIKO3";

	/** COLUMNアノテーション krBiko4 */
	public static final String krBiko4_COLUMN = "KR_BIKO4";

	/** COLUMNアノテーション krTegata */
	public static final String krTegata_COLUMN = "KR_TEGATA";

	/** COLUMNアノテーション krSkazei */
	public static final String krSkazei_COLUMN = "KR_SKAZEI";

	/** COLUMNアノテーション krSknnaibuNo */
	public static final String krSknnaibuNo_COLUMN = "KR_SKNNAIBU_NO";

	/** COLUMNアノテーション ksBnaibuNo */
	public static final String ksBnaibuNo_COLUMN = "KS_BNAIBU_NO";

	/** COLUMNアノテーション ksKnaibuNo */
	public static final String ksKnaibuNo_COLUMN = "KS_KNAIBU_NO";

	/** COLUMNアノテーション ksToriCd */
	public static final String ksToriCd_COLUMN = "KS_TORI_CD";

	/** COLUMNアノテーション ksTekimei */
	public static final String ksTekimei_COLUMN = "KS_TEKIMEI";

	/** COLUMNアノテーション ksProjCd */
	public static final String ksProjCd_COLUMN = "KS_PROJ_CD";

	/** COLUMNアノテーション ksKingaku */
	public static final String ksKingaku_COLUMN = "KS_KINGAKU";

	/** COLUMNアノテーション ksZei */
	public static final String ksZei_COLUMN = "KS_ZEI";

	/** COLUMNアノテーション ksKazei */
	public static final String ksKazei_COLUMN = "KS_KAZEI";

	/** COLUMNアノテーション ksZeiKbn */
	public static final String ksZeiKbn_COLUMN = "KS_ZEI_KBN";

	/** COLUMNアノテーション ksToritYmd */
	public static final String ksToritYmd_COLUMN = "KS_TORIT_YMD";

	/** COLUMNアノテーション ksGaikim */
	public static final String ksGaikim_COLUMN = "KS_GAIKIM";

	/** COLUMNアノテーション ksGaiRate */
	public static final String ksGaiRate_COLUMN = "KS_GAI_RATE";

	/** COLUMNアノテーション ksGaiKbn */
	public static final String ksGaiKbn_COLUMN = "KS_GAI_KBN";

	/** COLUMNアノテーション ksBibou1 */
	public static final String ksBibou1_COLUMN = "KS_BIBOU1";

	/** COLUMNアノテーション ksBibou2 */
	public static final String ksBibou2_COLUMN = "KS_BIBOU2";

	/** COLUMNアノテーション ksBibou3 */
	public static final String ksBibou3_COLUMN = "KS_BIBOU3";

	/** COLUMNアノテーション ksFusen */
	public static final String ksFusen_COLUMN = "KS_FUSEN";

	/** COLUMNアノテーション ksMemo */
	public static final String ksMemo_COLUMN = "KS_MEMO";

	/** COLUMNアノテーション ksBiko1 */
	public static final String ksBiko1_COLUMN = "KS_BIKO1";

	/** COLUMNアノテーション ksBiko2 */
	public static final String ksBiko2_COLUMN = "KS_BIKO2";

	/** COLUMNアノテーション ksBiko3 */
	public static final String ksBiko3_COLUMN = "KS_BIKO3";

	/** COLUMNアノテーション ksBiko4 */
	public static final String ksBiko4_COLUMN = "KS_BIKO4";

	/** COLUMNアノテーション ksTegata */
	public static final String ksTegata_COLUMN = "KS_TEGATA";

	/** COLUMNアノテーション ksSkazei */
	public static final String ksSkazei_COLUMN = "KS_SKAZEI";

	/** COLUMNアノテーション ksSknnaibuNo */
	public static final String ksSknnaibuNo_COLUMN = "KS_SKNNAIBU_NO";

	//
	// インスタンスフィールド
	//

	private String denYmd;

	private String denNo;

	private java.math.BigDecimal gyoNo;

	private String denKbn;

	private String krBnaibuNo;

	private String krKnaibuNo;

	private String krToriCd;

	private String krTekimei;

	private String krProjCd;

	private java.math.BigDecimal krKingaku;

	private java.math.BigDecimal krZei;

	private String krKazei;

	private String krZeiKbn;

	private String krToritYmd;

	private java.math.BigDecimal krGaikim;

	private java.math.BigDecimal krGairate;

	private String krGaiKbn;

	private String krBibou1;

	private String krBibou2;

	private String krBibou3;

	private String krFusen;

	private String krMemo;

	private String krBiko1;

	private java.math.BigDecimal krBiko2;

	private String krBiko3;

	private String krBiko4;

	private String krTegata;

	private String krSkazei;

	private String krSknnaibuNo;

	private String ksBnaibuNo;

	private String ksKnaibuNo;

	private String ksToriCd;

	private String ksTekimei;

	private String ksProjCd;

	private java.math.BigDecimal ksKingaku;

	private java.math.BigDecimal ksZei;

	private String ksKazei;

	private String ksZeiKbn;

	private String ksToritYmd;

	private java.math.BigDecimal ksGaikim;

	private java.math.BigDecimal ksGaiRate;

	private String ksGaiKbn;

	private String ksBibou1;

	private String ksBibou2;

	private String ksBibou3;

	private String ksFusen;

	private String ksMemo;

	private String ksBiko1;

	private java.math.BigDecimal ksBiko2;

	private String ksBiko3;

	private String ksBiko4;

	private String ksTegata;

	private String ksSkazei;

	private String ksSknnaibuNo;

	//
	// インスタンスメソッド
	//

	/**
	 * denYmd取得.
	 * @return denYmd
	 */
	public String getDenYmd() {
		return this.denYmd;
	}

	/**
	 * denYmd設定.
	 * @param denYmd denYmd
	 */
	public void setDenYmd(final String denYmd) {
		this.denYmd = denYmd;
	}

	/**
	 * denNo取得.
	 * @return denNo
	 */
	public String getDenNo() {
		return this.denNo;
	}

	/**
	 * denNo設定.
	 * @param denNo denNo
	 */
	public void setDenNo(final String denNo) {
		this.denNo = denNo;
	}

	/**
	 * gyoNo取得.
	 * @return gyoNo
	 */
	public java.math.BigDecimal getGyoNo() {
		return this.gyoNo;
	}

	/**
	 * gyoNo設定.
	 * @param gyoNo gyoNo
	 */
	public void setGyoNo(final java.math.BigDecimal gyoNo) {
		this.gyoNo = gyoNo;
	}

	/**
	 * denKbn取得.
	 * @return denKbn
	 */
	public String getDenKbn() {
		return this.denKbn;
	}

	/**
	 * denKbn設定.
	 * @param denKbn denKbn
	 */
	public void setDenKbn(final String denKbn) {
		this.denKbn = denKbn;
	}

	/**
	 * krBnaibuNo取得.
	 * @return krBnaibuNo
	 */
	public String getKrBnaibuNo() {
		return this.krBnaibuNo;
	}

	/**
	 * krBnaibuNo設定.
	 * @param krBnaibuNo krBnaibuNo
	 */
	public void setKrBnaibuNo(final String krBnaibuNo) {
		this.krBnaibuNo = krBnaibuNo;
	}

	/**
	 * krKnaibuNo取得.
	 * @return krKnaibuNo
	 */
	public String getKrKnaibuNo() {
		return this.krKnaibuNo;
	}

	/**
	 * krKnaibuNo設定.
	 * @param krKnaibuNo krKnaibuNo
	 */
	public void setKrKnaibuNo(final String krKnaibuNo) {
		this.krKnaibuNo = krKnaibuNo;
	}

	/**
	 * krToriCd取得.
	 * @return krToriCd
	 */
	public String getKrToriCd() {
		return this.krToriCd;
	}

	/**
	 * krToriCd設定.
	 * @param krToriCd krToriCd
	 */
	public void setKrToriCd(final String krToriCd) {
		this.krToriCd = krToriCd;
	}

	/**
	 * krTekimei取得.
	 * @return krTekimei
	 */
	public String getKrTekimei() {
		return this.krTekimei;
	}

	/**
	 * krTekimei設定.
	 * @param krTekimei krTekimei
	 */
	public void setKrTekimei(final String krTekimei) {
		this.krTekimei = krTekimei;
	}

	/**
	 * krProjCd取得.
	 * @return krProjCd
	 */
	public String getKrProjCd() {
		return this.krProjCd;
	}

	/**
	 * krProjCd設定.
	 * @param krProjCd krProjCd
	 */
	public void setKrProjCd(final String krProjCd) {
		this.krProjCd = krProjCd;
	}

	/**
	 * krKingaku取得.
	 * @return krKingaku
	 */
	public java.math.BigDecimal getKrKingaku() {
		return this.krKingaku;
	}

	/**
	 * krKingaku設定.
	 * @param krKingaku krKingaku
	 */
	public void setKrKingaku(final java.math.BigDecimal krKingaku) {
		this.krKingaku = krKingaku;
	}

	/**
	 * krZei取得.
	 * @return krZei
	 */
	public java.math.BigDecimal getKrZei() {
		return this.krZei;
	}

	/**
	 * krZei設定.
	 * @param krZei krZei
	 */
	public void setKrZei(final java.math.BigDecimal krZei) {
		this.krZei = krZei;
	}

	/**
	 * krKazei取得.
	 * @return krKazei
	 */
	public String getKrKazei() {
		return this.krKazei;
	}

	/**
	 * krKazei設定.
	 * @param krKazei krKazei
	 */
	public void setKrKazei(final String krKazei) {
		this.krKazei = krKazei;
	}

	/**
	 * krZeiKbn取得.
	 * @return krZeiKbn
	 */
	public String getKrZeiKbn() {
		return this.krZeiKbn;
	}

	/**
	 * krZeiKbn設定.
	 * @param krZeiKbn krZeiKbn
	 */
	public void setKrZeiKbn(final String krZeiKbn) {
		this.krZeiKbn = krZeiKbn;
	}

	/**
	 * krToritYmd取得.
	 * @return krToritYmd
	 */
	public String getKrToritYmd() {
		return this.krToritYmd;
	}

	/**
	 * krToritYmd設定.
	 * @param krToritYmd krToritYmd
	 */
	public void setKrToritYmd(final String krToritYmd) {
		this.krToritYmd = krToritYmd;
	}

	/**
	 * krGaikim取得.
	 * @return krGaikim
	 */
	public java.math.BigDecimal getKrGaikim() {
		return this.krGaikim;
	}

	/**
	 * krGaikim設定.
	 * @param krGaikim krGaikim
	 */
	public void setKrGaikim(final java.math.BigDecimal krGaikim) {
		this.krGaikim = krGaikim;
	}

	/**
	 * krGairate取得.
	 * @return krGairate
	 */
	public java.math.BigDecimal getKrGairate() {
		return this.krGairate;
	}

	/**
	 * krGairate設定.
	 * @param krGairate krGairate
	 */
	public void setKrGairate(final java.math.BigDecimal krGairate) {
		this.krGairate = krGairate;
	}

	/**
	 * krGaiKbn取得.
	 * @return krGaiKbn
	 */
	public String getKrGaiKbn() {
		return this.krGaiKbn;
	}

	/**
	 * krGaiKbn設定.
	 * @param krGaiKbn krGaiKbn
	 */
	public void setKrGaiKbn(final String krGaiKbn) {
		this.krGaiKbn = krGaiKbn;
	}

	/**
	 * krBibou1取得.
	 * @return krBibou1
	 */
	public String getKrBibou1() {
		return this.krBibou1;
	}

	/**
	 * krBibou1設定.
	 * @param krBibou1 krBibou1
	 */
	public void setKrBibou1(final String krBibou1) {
		this.krBibou1 = krBibou1;
	}

	/**
	 * krBibou2取得.
	 * @return krBibou2
	 */
	public String getKrBibou2() {
		return this.krBibou2;
	}

	/**
	 * krBibou2設定.
	 * @param krBibou2 krBibou2
	 */
	public void setKrBibou2(final String krBibou2) {
		this.krBibou2 = krBibou2;
	}

	/**
	 * krBibou3取得.
	 * @return krBibou3
	 */
	public String getKrBibou3() {
		return this.krBibou3;
	}

	/**
	 * krBibou3設定.
	 * @param krBibou3 krBibou3
	 */
	public void setKrBibou3(final String krBibou3) {
		this.krBibou3 = krBibou3;
	}

	/**
	 * krFusen取得.
	 * @return krFusen
	 */
	public String getKrFusen() {
		return this.krFusen;
	}

	/**
	 * krFusen設定.
	 * @param krFusen krFusen
	 */
	public void setKrFusen(final String krFusen) {
		this.krFusen = krFusen;
	}

	/**
	 * krMemo取得.
	 * @return krMemo
	 */
	public String getKrMemo() {
		return this.krMemo;
	}

	/**
	 * krMemo設定.
	 * @param krMemo krMemo
	 */
	public void setKrMemo(final String krMemo) {
		this.krMemo = krMemo;
	}

	/**
	 * krBiko1取得.
	 * @return krBiko1
	 */
	public String getKrBiko1() {
		return this.krBiko1;
	}

	/**
	 * krBiko1設定.
	 * @param krBiko1 krBiko1
	 */
	public void setKrBiko1(final String krBiko1) {
		this.krBiko1 = krBiko1;
	}

	/**
	 * krBiko2取得.
	 * @return krBiko2
	 */
	public java.math.BigDecimal getKrBiko2() {
		return this.krBiko2;
	}

	/**
	 * krBiko2設定.
	 * @param krBiko2 krBiko2
	 */
	public void setKrBiko2(final java.math.BigDecimal krBiko2) {
		this.krBiko2 = krBiko2;
	}

	/**
	 * krBiko3取得.
	 * @return krBiko3
	 */
	public String getKrBiko3() {
		return this.krBiko3;
	}

	/**
	 * krBiko3設定.
	 * @param krBiko3 krBiko3
	 */
	public void setKrBiko3(final String krBiko3) {
		this.krBiko3 = krBiko3;
	}

	/**
	 * krBiko4取得.
	 * @return krBiko4
	 */
	public String getKrBiko4() {
		return this.krBiko4;
	}

	/**
	 * krBiko4設定.
	 * @param krBiko4 krBiko4
	 */
	public void setKrBiko4(final String krBiko4) {
		this.krBiko4 = krBiko4;
	}

	/**
	 * krTegata取得.
	 * @return krTegata
	 */
	public String getKrTegata() {
		return this.krTegata;
	}

	/**
	 * krTegata設定.
	 * @param krTegata krTegata
	 */
	public void setKrTegata(final String krTegata) {
		this.krTegata = krTegata;
	}

	/**
	 * krSkazei取得.
	 * @return krSkazei
	 */
	public String getKrSkazei() {
		return this.krSkazei;
	}

	/**
	 * krSkazei設定.
	 * @param krSkazei krSkazei
	 */
	public void setKrSkazei(final String krSkazei) {
		this.krSkazei = krSkazei;
	}

	/**
	 * krSknnaibuNo取得.
	 * @return krSknnaibuNo
	 */
	public String getKrSknnaibuNo() {
		return this.krSknnaibuNo;
	}

	/**
	 * krSknnaibuNo設定.
	 * @param krSknnaibuNo krSknnaibuNo
	 */
	public void setKrSknnaibuNo(final String krSknnaibuNo) {
		this.krSknnaibuNo = krSknnaibuNo;
	}

	/**
	 * ksBnaibuNo取得.
	 * @return ksBnaibuNo
	 */
	public String getKsBnaibuNo() {
		return this.ksBnaibuNo;
	}

	/**
	 * ksBnaibuNo設定.
	 * @param ksBnaibuNo ksBnaibuNo
	 */
	public void setKsBnaibuNo(final String ksBnaibuNo) {
		this.ksBnaibuNo = ksBnaibuNo;
	}

	/**
	 * ksKnaibuNo取得.
	 * @return ksKnaibuNo
	 */
	public String getKsKnaibuNo() {
		return this.ksKnaibuNo;
	}

	/**
	 * ksKnaibuNo設定.
	 * @param ksKnaibuNo ksKnaibuNo
	 */
	public void setKsKnaibuNo(final String ksKnaibuNo) {
		this.ksKnaibuNo = ksKnaibuNo;
	}

	/**
	 * ksToriCd取得.
	 * @return ksToriCd
	 */
	public String getKsToriCd() {
		return this.ksToriCd;
	}

	/**
	 * ksToriCd設定.
	 * @param ksToriCd ksToriCd
	 */
	public void setKsToriCd(final String ksToriCd) {
		this.ksToriCd = ksToriCd;
	}

	/**
	 * ksTekimei取得.
	 * @return ksTekimei
	 */
	public String getKsTekimei() {
		return this.ksTekimei;
	}

	/**
	 * ksTekimei設定.
	 * @param ksTekimei ksTekimei
	 */
	public void setKsTekimei(final String ksTekimei) {
		this.ksTekimei = ksTekimei;
	}

	/**
	 * ksProjCd取得.
	 * @return ksProjCd
	 */
	public String getKsProjCd() {
		return this.ksProjCd;
	}

	/**
	 * ksProjCd設定.
	 * @param ksProjCd ksProjCd
	 */
	public void setKsProjCd(final String ksProjCd) {
		this.ksProjCd = ksProjCd;
	}

	/**
	 * ksKingaku取得.
	 * @return ksKingaku
	 */
	public java.math.BigDecimal getKsKingaku() {
		return this.ksKingaku;
	}

	/**
	 * ksKingaku設定.
	 * @param ksKingaku ksKingaku
	 */
	public void setKsKingaku(final java.math.BigDecimal ksKingaku) {
		this.ksKingaku = ksKingaku;
	}

	/**
	 * ksZei取得.
	 * @return ksZei
	 */
	public java.math.BigDecimal getKsZei() {
		return this.ksZei;
	}

	/**
	 * ksZei設定.
	 * @param ksZei ksZei
	 */
	public void setKsZei(final java.math.BigDecimal ksZei) {
		this.ksZei = ksZei;
	}

	/**
	 * ksKazei取得.
	 * @return ksKazei
	 */
	public String getKsKazei() {
		return this.ksKazei;
	}

	/**
	 * ksKazei設定.
	 * @param ksKazei ksKazei
	 */
	public void setKsKazei(final String ksKazei) {
		this.ksKazei = ksKazei;
	}

	/**
	 * ksZeiKbn取得.
	 * @return ksZeiKbn
	 */
	public String getKsZeiKbn() {
		return this.ksZeiKbn;
	}

	/**
	 * ksZeiKbn設定.
	 * @param ksZeiKbn ksZeiKbn
	 */
	public void setKsZeiKbn(final String ksZeiKbn) {
		this.ksZeiKbn = ksZeiKbn;
	}

	/**
	 * ksToritYmd取得.
	 * @return ksToritYmd
	 */
	public String getKsToritYmd() {
		return this.ksToritYmd;
	}

	/**
	 * ksToritYmd設定.
	 * @param ksToritYmd ksToritYmd
	 */
	public void setKsToritYmd(final String ksToritYmd) {
		this.ksToritYmd = ksToritYmd;
	}

	/**
	 * ksGaikim取得.
	 * @return ksGaikim
	 */
	public java.math.BigDecimal getKsGaikim() {
		return this.ksGaikim;
	}

	/**
	 * ksGaikim設定.
	 * @param ksGaikim ksGaikim
	 */
	public void setKsGaikim(final java.math.BigDecimal ksGaikim) {
		this.ksGaikim = ksGaikim;
	}

	/**
	 * ksGaiRate取得.
	 * @return ksGaiRate
	 */
	public java.math.BigDecimal getKsGaiRate() {
		return this.ksGaiRate;
	}

	/**
	 * ksGaiRate設定.
	 * @param ksGaiRate ksGaiRate
	 */
	public void setKsGaiRate(final java.math.BigDecimal ksGaiRate) {
		this.ksGaiRate = ksGaiRate;
	}

	/**
	 * ksGaiKbn取得.
	 * @return ksGaiKbn
	 */
	public String getKsGaiKbn() {
		return this.ksGaiKbn;
	}

	/**
	 * ksGaiKbn設定.
	 * @param ksGaiKbn ksGaiKbn
	 */
	public void setKsGaiKbn(final String ksGaiKbn) {
		this.ksGaiKbn = ksGaiKbn;
	}

	/**
	 * ksBibou1取得.
	 * @return ksBibou1
	 */
	public String getKsBibou1() {
		return this.ksBibou1;
	}

	/**
	 * ksBibou1設定.
	 * @param ksBibou1 ksBibou1
	 */
	public void setKsBibou1(final String ksBibou1) {
		this.ksBibou1 = ksBibou1;
	}

	/**
	 * ksBibou2取得.
	 * @return ksBibou2
	 */
	public String getKsBibou2() {
		return this.ksBibou2;
	}

	/**
	 * ksBibou2設定.
	 * @param ksBibou2 ksBibou2
	 */
	public void setKsBibou2(final String ksBibou2) {
		this.ksBibou2 = ksBibou2;
	}

	/**
	 * ksBibou3取得.
	 * @return ksBibou3
	 */
	public String getKsBibou3() {
		return this.ksBibou3;
	}

	/**
	 * ksBibou3設定.
	 * @param ksBibou3 ksBibou3
	 */
	public void setKsBibou3(final String ksBibou3) {
		this.ksBibou3 = ksBibou3;
	}

	/**
	 * ksFusen取得.
	 * @return ksFusen
	 */
	public String getKsFusen() {
		return this.ksFusen;
	}

	/**
	 * ksFusen設定.
	 * @param ksFusen ksFusen
	 */
	public void setKsFusen(final String ksFusen) {
		this.ksFusen = ksFusen;
	}

	/**
	 * ksMemo取得.
	 * @return ksMemo
	 */
	public String getKsMemo() {
		return this.ksMemo;
	}

	/**
	 * ksMemo設定.
	 * @param ksMemo ksMemo
	 */
	public void setKsMemo(final String ksMemo) {
		this.ksMemo = ksMemo;
	}

	/**
	 * ksBiko1取得.
	 * @return ksBiko1
	 */
	public String getKsBiko1() {
		return this.ksBiko1;
	}

	/**
	 * ksBiko1設定.
	 * @param ksBiko1 ksBiko1
	 */
	public void setKsBiko1(final String ksBiko1) {
		this.ksBiko1 = ksBiko1;
	}

	/**
	 * ksBiko2取得.
	 * @return ksBiko2
	 */
	public java.math.BigDecimal getKsBiko2() {
		return this.ksBiko2;
	}

	/**
	 * ksBiko2設定.
	 * @param ksBiko2 ksBiko2
	 */
	public void setKsBiko2(final java.math.BigDecimal ksBiko2) {
		this.ksBiko2 = ksBiko2;
	}

	/**
	 * ksBiko3取得.
	 * @return ksBiko3
	 */
	public String getKsBiko3() {
		return this.ksBiko3;
	}

	/**
	 * ksBiko3設定.
	 * @param ksBiko3 ksBiko3
	 */
	public void setKsBiko3(final String ksBiko3) {
		this.ksBiko3 = ksBiko3;
	}

	/**
	 * ksBiko4取得.
	 * @return ksBiko4
	 */
	public String getKsBiko4() {
		return this.ksBiko4;
	}

	/**
	 * ksBiko4設定.
	 * @param ksBiko4 ksBiko4
	 */
	public void setKsBiko4(final String ksBiko4) {
		this.ksBiko4 = ksBiko4;
	}

	/**
	 * ksTegata取得.
	 * @return ksTegata
	 */
	public String getKsTegata() {
		return this.ksTegata;
	}

	/**
	 * ksTegata設定.
	 * @param ksTegata ksTegata
	 */
	public void setKsTegata(final String ksTegata) {
		this.ksTegata = ksTegata;
	}

	/**
	 * ksSkazei取得.
	 * @return ksSkazei
	 */
	public String getKsSkazei() {
		return this.ksSkazei;
	}

	/**
	 * ksSkazei設定.
	 * @param ksSkazei ksSkazei
	 */
	public void setKsSkazei(final String ksSkazei) {
		this.ksSkazei = ksSkazei;
	}

	/**
	 * ksSknnaibuNo取得.
	 * @return ksSknnaibuNo
	 */
	public String getKsSknnaibuNo() {
		return this.ksSknnaibuNo;
	}

	/**
	 * ksSknnaibuNo設定.
	 * @param ksSknnaibuNo ksSknnaibuNo
	 */
	public void setKsSknnaibuNo(final String ksSknnaibuNo) {
		this.ksSknnaibuNo = ksSknnaibuNo;
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

