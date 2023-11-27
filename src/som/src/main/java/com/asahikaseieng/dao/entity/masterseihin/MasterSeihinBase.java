/*
 * Created on Tue May 12 16:14:34 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterseihin;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MasterSeihinBaseクラス.
 * @author kanri-user
 */
public class MasterSeihinBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MasterSeihinBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MASTER_SEIHIN";


//	/** IDアノテーション seihincode. */
//	public static final String seihincode_ID = "assigned";

	/** COLUMNアノテーション daiseicode. */
	public static final String daiseicode_COLUMN = "DAISEICODE";

	/** COLUMNアノテーション daiseimei. */
	public static final String daiseimei_COLUMN = "DAISEIMEI";

	/** COLUMNアノテーション seihincode. */
	public static final String seihincode_COLUMN = "SEIHINCODE";

	/** COLUMNアノテーション shohinkubun. */
	public static final String shohinkubun_COLUMN = "SHOHINKUBUN";

	/** COLUMNアノテーション seihinmei. */
	public static final String seihinmei_COLUMN = "SEIHINMEI";

	/** COLUMNアノテーション seihinkana. */
	public static final String seihinkana_COLUMN = "SEIHINKANA";

	/** COLUMNアノテーション furikaecode. */
	public static final String furikaecode_COLUMN = "FURIKAECODE";

	/** COLUMNアノテーション nisugata. */
	public static final String nisugata_COLUMN = "NISUGATA";

	/** COLUMNアノテーション kanrikubun. */
	public static final String kanrikubun_COLUMN = "KANRIKUBUN";

	/** COLUMNアノテーション kgkanzanchi. */
	public static final String kgkanzanchi_COLUMN = "KGKANZANCHI";

	/** COLUMNアノテーション totaljuryo. */
	public static final String totaljuryo_COLUMN = "TOTALJURYO";

	/** COLUMNアノテーション irisu. */
	public static final String irisu_COLUMN = "IRISU";

	/** COLUMNアノテーション kojocode. */
	public static final String kojocode_COLUMN = "KOJOCODE";

	/** COLUMNアノテーション kensanissu. */
	public static final String kensanissu_COLUMN = "KENSANISSU";

	/** COLUMNアノテーション seibunrui. */
	public static final String seibunrui_COLUMN = "SEIBUNRUI";

	/** COLUMNアノテーション oemsakicode. */
	public static final String oemsakicode_COLUMN = "OEMSAKICODE";

	/** COLUMNアノテーション kyotencode. */
	public static final String kyotencode_COLUMN = "KYOTENCODE";

	/** COLUMNアノテーション lotkubun. */
	public static final String lotkubun_COLUMN = "LOTKUBUN";

	/** COLUMNアノテーション chogotank1. */
	public static final String chogotank1_COLUMN = "CHOGOTANK1";

	/** COLUMNアノテーション chogotank2. */
	public static final String chogotank2_COLUMN = "CHOGOTANK2";

	/** COLUMNアノテーション chogotank3. */
	public static final String chogotank3_COLUMN = "CHOGOTANK3";

	/** COLUMNアノテーション hosoline1. */
	public static final String hosoline1_COLUMN = "HOSOLINE1";

	/** COLUMNアノテーション hosoline2. */
	public static final String hosoline2_COLUMN = "HOSOLINE2";

	/** COLUMNアノテーション hosoline3. */
	public static final String hosoline3_COLUMN = "HOSOLINE3";

	/** COLUMNアノテーション atohosoline. */
	public static final String atohosoline_COLUMN = "ATOHOSOLINE";

	/** COLUMNアノテーション maxchogojikan. */
	public static final String maxchogojikan_COLUMN = "MAXCHOGOJIKAN";

	/** COLUMNアノテーション maxseizoryo. */
	public static final String maxseizoryo_COLUMN = "MAXSEIZORYO";

	/** COLUMNアノテーション minchogojikan. */
	public static final String minchogojikan_COLUMN = "MINCHOGOJIKAN";

	/** COLUMNアノテーション minseizoryo. */
	public static final String minseizoryo_COLUMN = "MINSEIZORYO";

	/** COLUMNアノテーション kensajikan. */
	public static final String kensajikan_COLUMN = "KENSAJIKAN";

	/** COLUMNアノテーション isojikan. */
	public static final String isojikan_COLUMN = "ISOJIKAN";

	/** COLUMNアノテーション maejikan. */
	public static final String maejikan_COLUMN = "MAEJIKAN";

	/** COLUMNアノテーション hosojikan. */
	public static final String hosojikan_COLUMN = "HOSOJIKAN";

	/** COLUMNアノテーション atohosojikan. */
	public static final String atohosojikan_COLUMN = "ATOHOSOJIKAN";

	/** COLUMNアノテーション atojikan. */
	public static final String atojikan_COLUMN = "ATOJIKAN";

	/** COLUMNアノテーション oemflag. */
	public static final String oemflag_COLUMN = "OEMFLAG";

	/** COLUMNアノテーション plonseisu. */
	public static final String plonseisu_COLUMN = "PLONSEISU";

	/** COLUMNアノテーション shubetsu. */
	public static final String shubetsu_COLUMN = "SHUBETSU";

	/** COLUMNアノテーション iocode. */
	public static final String iocode_COLUMN = "IOCODE";

	/** COLUMNアノテーション gaichukako. */
	public static final String gaichukako_COLUMN = "GAICHUKAKO";

	/** COLUMNアノテーション torihikicode. */
	public static final String torihikicode_COLUMN = "TORIHIKICODE";

	/** COLUMNアノテーション hiju. */
	public static final String hiju_COLUMN = "HIJU";

	/** COLUMNアノテーション budomari. */
	public static final String budomari_COLUMN = "BUDOMARI";

	/** COLUMNアノテーション hososabun. */
	public static final String hososabun_COLUMN = "HOSOSABUN";

	/** COLUMNアノテーション drymaxkaisu. */
	public static final String drymaxkaisu_COLUMN = "DRYMAXKAISU";

	/** COLUMNアノテーション agingtime. */
	public static final String agingtime_COLUMN = "AGINGTIME";

	//
	// インスタンスフィールド
	//

	private String daiseicode;

	private String daiseimei;

	private String seihincode;

	private String shohinkubun;

	private String seihinmei;

	private String seihinkana;

	private String furikaecode;

	private String nisugata;

	private String kanrikubun;

	private java.math.BigDecimal kgkanzanchi;

	private java.math.BigDecimal totaljuryo;

	private java.math.BigDecimal irisu;

	private String kojocode;

	private java.math.BigDecimal kensanissu;

	private String seibunrui;

	private String oemsakicode;

	private String kyotencode;

	private String lotkubun;

	private String chogotank1;

	private String chogotank2;

	private String chogotank3;

	private String hosoline1;

	private String hosoline2;

	private String hosoline3;

	private String atohosoline;

	private java.math.BigDecimal maxchogojikan;

	private java.math.BigDecimal maxseizoryo;

	private java.math.BigDecimal minchogojikan;

	private java.math.BigDecimal minseizoryo;

	private java.math.BigDecimal kensajikan;

	private java.math.BigDecimal isojikan;

	private java.math.BigDecimal maejikan;

	private java.math.BigDecimal hosojikan;

	private java.math.BigDecimal atohosojikan;

	private java.math.BigDecimal atojikan;

	private String oemflag;

	private java.math.BigDecimal plonseisu;

	private String shubetsu;

	private String iocode;

	private String gaichukako;

	private String torihikicode;

	private java.math.BigDecimal hiju;

	private java.math.BigDecimal budomari;

	private String hososabun;

	private java.math.BigDecimal drymaxkaisu;

	private java.math.BigDecimal agingtime;

	//
	// インスタンスメソッド
	//

	/**
	 * daiseicode取得.
	 * @return daiseicode
	 */
	public String getDaiseicode() {
		return this.daiseicode;
	}

	/**
	 * daiseicode設定.
	 * @param daiseicode daiseicode
	 */
	public void setDaiseicode(final String daiseicode) {
		this.daiseicode = daiseicode;
	}

	/**
	 * daiseimei取得.
	 * @return daiseimei
	 */
	public String getDaiseimei() {
		return this.daiseimei;
	}

	/**
	 * daiseimei設定.
	 * @param daiseimei daiseimei
	 */
	public void setDaiseimei(final String daiseimei) {
		this.daiseimei = daiseimei;
	}

	/**
	 * seihincode取得.
	 * @return seihincode
	 */
	public String getSeihincode() {
		return this.seihincode;
	}

	/**
	 * seihincode設定.
	 * @param seihincode seihincode
	 */
	public void setSeihincode(final String seihincode) {
		this.seihincode = seihincode;
	}

	/**
	 * shohinkubun取得.
	 * @return shohinkubun
	 */
	public String getShohinkubun() {
		return this.shohinkubun;
	}

	/**
	 * shohinkubun設定.
	 * @param shohinkubun shohinkubun
	 */
	public void setShohinkubun(final String shohinkubun) {
		this.shohinkubun = shohinkubun;
	}

	/**
	 * seihinmei取得.
	 * @return seihinmei
	 */
	public String getSeihinmei() {
		return this.seihinmei;
	}

	/**
	 * seihinmei設定.
	 * @param seihinmei seihinmei
	 */
	public void setSeihinmei(final String seihinmei) {
		this.seihinmei = seihinmei;
	}

	/**
	 * seihinkana取得.
	 * @return seihinkana
	 */
	public String getSeihinkana() {
		return this.seihinkana;
	}

	/**
	 * seihinkana設定.
	 * @param seihinkana seihinkana
	 */
	public void setSeihinkana(final String seihinkana) {
		this.seihinkana = seihinkana;
	}

	/**
	 * furikaecode取得.
	 * @return furikaecode
	 */
	public String getFurikaecode() {
		return this.furikaecode;
	}

	/**
	 * furikaecode設定.
	 * @param furikaecode furikaecode
	 */
	public void setFurikaecode(final String furikaecode) {
		this.furikaecode = furikaecode;
	}

	/**
	 * nisugata取得.
	 * @return nisugata
	 */
	public String getNisugata() {
		return this.nisugata;
	}

	/**
	 * nisugata設定.
	 * @param nisugata nisugata
	 */
	public void setNisugata(final String nisugata) {
		this.nisugata = nisugata;
	}

	/**
	 * kanrikubun取得.
	 * @return kanrikubun
	 */
	public String getKanrikubun() {
		return this.kanrikubun;
	}

	/**
	 * kanrikubun設定.
	 * @param kanrikubun kanrikubun
	 */
	public void setKanrikubun(final String kanrikubun) {
		this.kanrikubun = kanrikubun;
	}

	/**
	 * kgkanzanchi取得.
	 * @return kgkanzanchi
	 */
	public java.math.BigDecimal getKgkanzanchi() {
		return this.kgkanzanchi;
	}

	/**
	 * kgkanzanchi設定.
	 * @param kgkanzanchi kgkanzanchi
	 */
	public void setKgkanzanchi(final java.math.BigDecimal kgkanzanchi) {
		this.kgkanzanchi = kgkanzanchi;
	}

	/**
	 * totaljuryo取得.
	 * @return totaljuryo
	 */
	public java.math.BigDecimal getTotaljuryo() {
		return this.totaljuryo;
	}

	/**
	 * totaljuryo設定.
	 * @param totaljuryo totaljuryo
	 */
	public void setTotaljuryo(final java.math.BigDecimal totaljuryo) {
		this.totaljuryo = totaljuryo;
	}

	/**
	 * irisu取得.
	 * @return irisu
	 */
	public java.math.BigDecimal getIrisu() {
		return this.irisu;
	}

	/**
	 * irisu設定.
	 * @param irisu irisu
	 */
	public void setIrisu(final java.math.BigDecimal irisu) {
		this.irisu = irisu;
	}

	/**
	 * kojocode取得.
	 * @return kojocode
	 */
	public String getKojocode() {
		return this.kojocode;
	}

	/**
	 * kojocode設定.
	 * @param kojocode kojocode
	 */
	public void setKojocode(final String kojocode) {
		this.kojocode = kojocode;
	}

	/**
	 * kensanissu取得.
	 * @return kensanissu
	 */
	public java.math.BigDecimal getKensanissu() {
		return this.kensanissu;
	}

	/**
	 * kensanissu設定.
	 * @param kensanissu kensanissu
	 */
	public void setKensanissu(final java.math.BigDecimal kensanissu) {
		this.kensanissu = kensanissu;
	}

	/**
	 * seibunrui取得.
	 * @return seibunrui
	 */
	public String getSeibunrui() {
		return this.seibunrui;
	}

	/**
	 * seibunrui設定.
	 * @param seibunrui seibunrui
	 */
	public void setSeibunrui(final String seibunrui) {
		this.seibunrui = seibunrui;
	}

	/**
	 * oemsakicode取得.
	 * @return oemsakicode
	 */
	public String getOemsakicode() {
		return this.oemsakicode;
	}

	/**
	 * oemsakicode設定.
	 * @param oemsakicode oemsakicode
	 */
	public void setOemsakicode(final String oemsakicode) {
		this.oemsakicode = oemsakicode;
	}

	/**
	 * kyotencode取得.
	 * @return kyotencode
	 */
	public String getKyotencode() {
		return this.kyotencode;
	}

	/**
	 * kyotencode設定.
	 * @param kyotencode kyotencode
	 */
	public void setKyotencode(final String kyotencode) {
		this.kyotencode = kyotencode;
	}

	/**
	 * lotkubun取得.
	 * @return lotkubun
	 */
	public String getLotkubun() {
		return this.lotkubun;
	}

	/**
	 * lotkubun設定.
	 * @param lotkubun lotkubun
	 */
	public void setLotkubun(final String lotkubun) {
		this.lotkubun = lotkubun;
	}

	/**
	 * chogotank1取得.
	 * @return chogotank1
	 */
	public String getChogotank1() {
		return this.chogotank1;
	}

	/**
	 * chogotank1設定.
	 * @param chogotank1 chogotank1
	 */
	public void setChogotank1(final String chogotank1) {
		this.chogotank1 = chogotank1;
	}

	/**
	 * chogotank2取得.
	 * @return chogotank2
	 */
	public String getChogotank2() {
		return this.chogotank2;
	}

	/**
	 * chogotank2設定.
	 * @param chogotank2 chogotank2
	 */
	public void setChogotank2(final String chogotank2) {
		this.chogotank2 = chogotank2;
	}

	/**
	 * chogotank3取得.
	 * @return chogotank3
	 */
	public String getChogotank3() {
		return this.chogotank3;
	}

	/**
	 * chogotank3設定.
	 * @param chogotank3 chogotank3
	 */
	public void setChogotank3(final String chogotank3) {
		this.chogotank3 = chogotank3;
	}

	/**
	 * hosoline1取得.
	 * @return hosoline1
	 */
	public String getHosoline1() {
		return this.hosoline1;
	}

	/**
	 * hosoline1設定.
	 * @param hosoline1 hosoline1
	 */
	public void setHosoline1(final String hosoline1) {
		this.hosoline1 = hosoline1;
	}

	/**
	 * hosoline2取得.
	 * @return hosoline2
	 */
	public String getHosoline2() {
		return this.hosoline2;
	}

	/**
	 * hosoline2設定.
	 * @param hosoline2 hosoline2
	 */
	public void setHosoline2(final String hosoline2) {
		this.hosoline2 = hosoline2;
	}

	/**
	 * hosoline3取得.
	 * @return hosoline3
	 */
	public String getHosoline3() {
		return this.hosoline3;
	}

	/**
	 * hosoline3設定.
	 * @param hosoline3 hosoline3
	 */
	public void setHosoline3(final String hosoline3) {
		this.hosoline3 = hosoline3;
	}

	/**
	 * atohosoline取得.
	 * @return atohosoline
	 */
	public String getAtohosoline() {
		return this.atohosoline;
	}

	/**
	 * atohosoline設定.
	 * @param atohosoline atohosoline
	 */
	public void setAtohosoline(final String atohosoline) {
		this.atohosoline = atohosoline;
	}

	/**
	 * maxchogojikan取得.
	 * @return maxchogojikan
	 */
	public java.math.BigDecimal getMaxchogojikan() {
		return this.maxchogojikan;
	}

	/**
	 * maxchogojikan設定.
	 * @param maxchogojikan maxchogojikan
	 */
	public void setMaxchogojikan(final java.math.BigDecimal maxchogojikan) {
		this.maxchogojikan = maxchogojikan;
	}

	/**
	 * maxseizoryo取得.
	 * @return maxseizoryo
	 */
	public java.math.BigDecimal getMaxseizoryo() {
		return this.maxseizoryo;
	}

	/**
	 * maxseizoryo設定.
	 * @param maxseizoryo maxseizoryo
	 */
	public void setMaxseizoryo(final java.math.BigDecimal maxseizoryo) {
		this.maxseizoryo = maxseizoryo;
	}

	/**
	 * minchogojikan取得.
	 * @return minchogojikan
	 */
	public java.math.BigDecimal getMinchogojikan() {
		return this.minchogojikan;
	}

	/**
	 * minchogojikan設定.
	 * @param minchogojikan minchogojikan
	 */
	public void setMinchogojikan(final java.math.BigDecimal minchogojikan) {
		this.minchogojikan = minchogojikan;
	}

	/**
	 * minseizoryo取得.
	 * @return minseizoryo
	 */
	public java.math.BigDecimal getMinseizoryo() {
		return this.minseizoryo;
	}

	/**
	 * minseizoryo設定.
	 * @param minseizoryo minseizoryo
	 */
	public void setMinseizoryo(final java.math.BigDecimal minseizoryo) {
		this.minseizoryo = minseizoryo;
	}

	/**
	 * kensajikan取得.
	 * @return kensajikan
	 */
	public java.math.BigDecimal getKensajikan() {
		return this.kensajikan;
	}

	/**
	 * kensajikan設定.
	 * @param kensajikan kensajikan
	 */
	public void setKensajikan(final java.math.BigDecimal kensajikan) {
		this.kensajikan = kensajikan;
	}

	/**
	 * isojikan取得.
	 * @return isojikan
	 */
	public java.math.BigDecimal getIsojikan() {
		return this.isojikan;
	}

	/**
	 * isojikan設定.
	 * @param isojikan isojikan
	 */
	public void setIsojikan(final java.math.BigDecimal isojikan) {
		this.isojikan = isojikan;
	}

	/**
	 * maejikan取得.
	 * @return maejikan
	 */
	public java.math.BigDecimal getMaejikan() {
		return this.maejikan;
	}

	/**
	 * maejikan設定.
	 * @param maejikan maejikan
	 */
	public void setMaejikan(final java.math.BigDecimal maejikan) {
		this.maejikan = maejikan;
	}

	/**
	 * hosojikan取得.
	 * @return hosojikan
	 */
	public java.math.BigDecimal getHosojikan() {
		return this.hosojikan;
	}

	/**
	 * hosojikan設定.
	 * @param hosojikan hosojikan
	 */
	public void setHosojikan(final java.math.BigDecimal hosojikan) {
		this.hosojikan = hosojikan;
	}

	/**
	 * atohosojikan取得.
	 * @return atohosojikan
	 */
	public java.math.BigDecimal getAtohosojikan() {
		return this.atohosojikan;
	}

	/**
	 * atohosojikan設定.
	 * @param atohosojikan atohosojikan
	 */
	public void setAtohosojikan(final java.math.BigDecimal atohosojikan) {
		this.atohosojikan = atohosojikan;
	}

	/**
	 * atojikan取得.
	 * @return atojikan
	 */
	public java.math.BigDecimal getAtojikan() {
		return this.atojikan;
	}

	/**
	 * atojikan設定.
	 * @param atojikan atojikan
	 */
	public void setAtojikan(final java.math.BigDecimal atojikan) {
		this.atojikan = atojikan;
	}

	/**
	 * oemflag取得.
	 * @return oemflag
	 */
	public String getOemflag() {
		return this.oemflag;
	}

	/**
	 * oemflag設定.
	 * @param oemflag oemflag
	 */
	public void setOemflag(final String oemflag) {
		this.oemflag = oemflag;
	}

	/**
	 * plonseisu取得.
	 * @return plonseisu
	 */
	public java.math.BigDecimal getPlonseisu() {
		return this.plonseisu;
	}

	/**
	 * plonseisu設定.
	 * @param plonseisu plonseisu
	 */
	public void setPlonseisu(final java.math.BigDecimal plonseisu) {
		this.plonseisu = plonseisu;
	}

	/**
	 * shubetsu取得.
	 * @return shubetsu
	 */
	public String getShubetsu() {
		return this.shubetsu;
	}

	/**
	 * shubetsu設定.
	 * @param shubetsu shubetsu
	 */
	public void setShubetsu(final String shubetsu) {
		this.shubetsu = shubetsu;
	}

	/**
	 * iocode取得.
	 * @return iocode
	 */
	public String getIocode() {
		return this.iocode;
	}

	/**
	 * iocode設定.
	 * @param iocode iocode
	 */
	public void setIocode(final String iocode) {
		this.iocode = iocode;
	}

	/**
	 * gaichukako取得.
	 * @return gaichukako
	 */
	public String getGaichukako() {
		return this.gaichukako;
	}

	/**
	 * gaichukako設定.
	 * @param gaichukako gaichukako
	 */
	public void setGaichukako(final String gaichukako) {
		this.gaichukako = gaichukako;
	}

	/**
	 * torihikicode取得.
	 * @return torihikicode
	 */
	public String getTorihikicode() {
		return this.torihikicode;
	}

	/**
	 * torihikicode設定.
	 * @param torihikicode torihikicode
	 */
	public void setTorihikicode(final String torihikicode) {
		this.torihikicode = torihikicode;
	}

	/**
	 * hiju取得.
	 * @return hiju
	 */
	public java.math.BigDecimal getHiju() {
		return this.hiju;
	}

	/**
	 * hiju設定.
	 * @param hiju hiju
	 */
	public void setHiju(final java.math.BigDecimal hiju) {
		this.hiju = hiju;
	}

	/**
	 * budomari取得.
	 * @return budomari
	 */
	public java.math.BigDecimal getBudomari() {
		return this.budomari;
	}

	/**
	 * budomari設定.
	 * @param budomari budomari
	 */
	public void setBudomari(final java.math.BigDecimal budomari) {
		this.budomari = budomari;
	}

	/**
	 * hososabun取得.
	 * @return hososabun
	 */
	public String getHososabun() {
		return this.hososabun;
	}

	/**
	 * hososabun設定.
	 * @param hososabun hososabun
	 */
	public void setHososabun(final String hososabun) {
		this.hososabun = hososabun;
	}

	/**
	 * drymaxkaisu取得.
	 * @return drymaxkaisu
	 */
	public java.math.BigDecimal getDrymaxkaisu() {
		return this.drymaxkaisu;
	}

	/**
	 * drymaxkaisu設定.
	 * @param drymaxkaisu drymaxkaisu
	 */
	public void setDrymaxkaisu(final java.math.BigDecimal drymaxkaisu) {
		this.drymaxkaisu = drymaxkaisu;
	}

	/**
	 * agingtime取得.
	 * @return agingtime
	 */
	public java.math.BigDecimal getAgingtime() {
		return this.agingtime;
	}

	/**
	 * agingtime設定.
	 * @param agingtime agingtime
	 */
	public void setAgingtime(final java.math.BigDecimal agingtime) {
		this.agingtime = agingtime;
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
