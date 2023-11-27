/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 各種名称マスタデータ格納クラス.
 * 
 * @author tosco
 */
public class MgrecipeNameListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeNameListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "NAMES";

	/** COLUMNアノテーション nameDivision */
	public static final String nameDivision_COLUMN = "NAME_DIVISION";

	/** COLUMNアノテーション nameCd */
	public static final String nameCd_COLUMN = "NAME_CD";

	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	/** COLUMNアノテーション name02 */
	public static final String name02_COLUMN = "NAME02";

	/** COLUMNアノテーション name03 */
	public static final String name03_COLUMN = "NAME03";

	/** COLUMNアノテーション nmqty01 */
	public static final String nmqty01_COLUMN = "NMQTY01";

	/** COLUMNアノテーション nmqty02 */
	public static final String nmqty02_COLUMN = "NMQTY02";

	/** COLUMNアノテーション nmqty03 */
	public static final String nmqty03_COLUMN = "NMQTY03";

	/** COLUMNアノテーション nmeflg1 */
	public static final String nmeflg1_COLUMN = "NMEFLG1";

	/** COLUMNアノテーション nmeflg2 */
	public static final String nmeflg2_COLUMN = "NMEFLG2";

	/** COLUMNアノテーション nmeflg3 */
	public static final String nmeflg3_COLUMN = "NMEFLG3";

	/** COLUMNアノテーション nmeflg4 */
	public static final String nmeflg4_COLUMN = "NMEFLG4";

	/** COLUMNアノテーション nmeflg5 */
	public static final String nmeflg5_COLUMN = "NMEFLG5";

	/** COLUMNアノテーション nmekbn1 */
	public static final String nmekbn1_COLUMN = "NMEKBN1";

	/** COLUMNアノテーション nmekbn2 */
	public static final String nmekbn2_COLUMN = "NMEKBN2";

	/** COLUMNアノテーション nmekbn3 */
	public static final String nmekbn3_COLUMN = "NMEKBN3";

	/** COLUMNアノテーション nmekbn4 */
	public static final String nmekbn4_COLUMN = "NMEKBN4";

	/** COLUMNアノテーション nmekbn5 */
	public static final String nmekbn5_COLUMN = "NMEKBN5";

	/** COLUMNアノテーション nmedate1 */
	public static final String nmedate1_COLUMN = "NMEDATE1";

	/** COLUMNアノテーション nmedate2 */
	public static final String nmedate2_COLUMN = "NMEDATE2";

	/** COLUMNアノテーション nmedate3 */
	public static final String nmedate3_COLUMN = "NMEDATE3";

	/** COLUMNアノテーション nmedate4 */
	public static final String nmedate4_COLUMN = "NMEDATE4";

	/** COLUMNアノテーション nmedate5 */
	public static final String nmedate5_COLUMN = "NMEDATE5";

	/** COLUMNアノテーション nmetime1 */
	public static final String nmetime1_COLUMN = "NMETIME1";

	/** COLUMNアノテーション nmetime2 */
	public static final String nmetime2_COLUMN = "NMETIME2";

	/** COLUMNアノテーション nmetime3 */
	public static final String nmetime3_COLUMN = "NMETIME3";

	/** COLUMNアノテーション nmetime4 */
	public static final String nmetime4_COLUMN = "NMETIME4";

	/** COLUMNアノテーション nmetime5 */
	public static final String nmetime5_COLUMN = "NMETIME5";

	/** COLUMNアノテーション nmenum1 */
	public static final String nmenum1_COLUMN = "NMENUM1";

	/** COLUMNアノテーション nmenum2 */
	public static final String nmenum2_COLUMN = "NMENUM2";

	/** COLUMNアノテーション nmenum3 */
	public static final String nmenum3_COLUMN = "NMENUM3";

	/** COLUMNアノテーション nmenum4 */
	public static final String nmenum4_COLUMN = "NMENUM4";

	/** COLUMNアノテーション nmenum5 */
	public static final String nmenum5_COLUMN = "NMENUM5";

	/** COLUMNアノテーション nmevalue1 */
	public static final String nmevalue1_COLUMN = "NMEVALUE1";

	/** COLUMNアノテーション nmevalue2 */
	public static final String nmevalue2_COLUMN = "NMEVALUE2";

	/** COLUMNアノテーション nmevalue3 */
	public static final String nmevalue3_COLUMN = "NMEVALUE3";

	/** COLUMNアノテーション nmevalue4 */
	public static final String nmevalue4_COLUMN = "NMEVALUE4";

	/** COLUMNアノテーション nmevalue5 */
	public static final String nmevalue5_COLUMN = "NMEVALUE5";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 名称区分|標準で11種類存在 （BANK:銀行、DIVI:部門、GREP:品目分類 FLVR:香質 FEAT:特徴 STDV:規格値 ETC...） */
	private String nameDivision;

	/** 名称コード */
	private String nameCd;

	/** 名称１ */
	private String name01;

	/** 名称２ */
	private String name02;

	/** 名称３ */
	private String name03;

	/** 数値１ */
	private BigDecimal nmqty01;

	/** 数値２ */
	private BigDecimal nmqty02;

	/** 数値３ */
	private BigDecimal nmqty03;

	/** 拡張フラグ１ */
	private BigDecimal nmeflg1;

	/** 拡張フラグ２ */
	private BigDecimal nmeflg2;

	/** 拡張フラグ３ */
	private BigDecimal nmeflg3;

	/** 拡張フラグ４ */
	private BigDecimal nmeflg4;

	/** 拡張フラグ５ */
	private BigDecimal nmeflg5;

	/** 拡張区分１ */
	private BigDecimal nmekbn1;

	/** 拡張区分２ */
	private BigDecimal nmekbn2;

	/** 拡張区分３ */
	private BigDecimal nmekbn3;

	/** 拡張区分４ */
	private BigDecimal nmekbn4;

	/** 拡張区分５ */
	private BigDecimal nmekbn5;

	/** 拡張日付１ */
	private BigDecimal nmedate1;

	/** 拡張日付２ */
	private BigDecimal nmedate2;

	/** 拡張日付３ */
	private BigDecimal nmedate3;

	/** 拡張日付４ */
	private BigDecimal nmedate4;

	/** 拡張日付５ */
	private BigDecimal nmedate5;

	/** 拡張時間１ */
	private BigDecimal nmetime1;

	/** 拡張時間２ */
	private BigDecimal nmetime2;

	/** 拡張時間３ */
	private BigDecimal nmetime3;

	/** 拡張時間４ */
	private BigDecimal nmetime4;

	/** 拡張時間５ */
	private BigDecimal nmetime5;

	/** 拡張数値１ */
	private BigDecimal nmenum1;

	/** 拡張数値２ */
	private BigDecimal nmenum2;

	/** 拡張数値３ */
	private BigDecimal nmenum3;

	/** 拡張数値４ */
	private BigDecimal nmenum4;

	/** 拡張数値５ */
	private BigDecimal nmenum5;

	/** 拡張文字列１ */
	private String nmevalue1;

	/** 拡張文字列２ */
	private String nmevalue2;

	/** 拡張文字列３ */
	private String nmevalue3;

	/** 拡張文字列４ */
	private String nmevalue4;

	/** 拡張文字列５ */
	private String nmevalue5;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 名称区分|標準で11種類存在 （BANK:銀行、DIVI:部門、GREP:品目分類 FLVR:香質 FEAT:特徴 STDV:規格値 ETC...）取得
	 * @return String 名称区分|標準で11種類存在 （BANK:銀行、DIVI:部門、GREP:品目分類 FLVR:香質 FEAT:特徴 STDV:規格値 ETC...）
	*/
	public String getNameDivision() {
		return this.nameDivision;
	}

	/**
	 * 名称区分|標準で11種類存在 （BANK:銀行、DIVI:部門、GREP:品目分類 FLVR:香質 FEAT:特徴 STDV:規格値 ETC...）設定
	 * @param nameDivision 名称区分|標準で11種類存在 （BANK:銀行、DIVI:部門、GREP:品目分類 FLVR:香質 FEAT:特徴 STDV:規格値 ETC...）
	*/
	public void setNameDivision(final String nameDivision) {
		this.nameDivision = nameDivision;
	}

	/**
	 * 名称コード取得
	 * @return String 名称コード
	*/
	public String getNameCd() {
		return this.nameCd;
	}

	/**
	 * 名称コード設定
	 * @param nameCd 名称コード
	*/
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}

	/**
	 * 名称１取得
	 * @return String 名称１
	*/
	public String getName01() {
		return this.name01;
	}

	/**
	 * 名称１設定
	 * @param name01 名称１
	*/
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * 名称２取得
	 * @return String 名称２
	*/
	public String getName02() {
		return this.name02;
	}

	/**
	 * 名称２設定
	 * @param name02 名称２
	*/
	public void setName02(final String name02) {
		this.name02 = name02;
	}

	/**
	 * 名称３取得
	 * @return String 名称３
	*/
	public String getName03() {
		return this.name03;
	}

	/**
	 * 名称３設定
	 * @param name03 名称３
	*/
	public void setName03(final String name03) {
		this.name03 = name03;
	}

	/**
	 * 数値１取得
	 * @return BigDecimal 数値１
	*/
	public BigDecimal getNmqty01() {
		return this.nmqty01;
	}

	/**
	 * 数値１設定
	 * @param nmqty01 数値１
	*/
	public void setNmqty01(final BigDecimal nmqty01) {
		this.nmqty01 = nmqty01;
	}

	/**
	 * 数値２取得
	 * @return BigDecimal 数値２
	*/
	public BigDecimal getNmqty02() {
		return this.nmqty02;
	}

	/**
	 * 数値２設定
	 * @param nmqty02 数値２
	*/
	public void setNmqty02(final BigDecimal nmqty02) {
		this.nmqty02 = nmqty02;
	}

	/**
	 * 数値３取得
	 * @return BigDecimal 数値３
	*/
	public BigDecimal getNmqty03() {
		return this.nmqty03;
	}

	/**
	 * 数値３設定
	 * @param nmqty03 数値３
	*/
	public void setNmqty03(final BigDecimal nmqty03) {
		this.nmqty03 = nmqty03;
	}

	/**
	 * 拡張フラグ１取得
	 * @return BigDecimal 拡張フラグ１
	*/
	public BigDecimal getNmeflg1() {
		return this.nmeflg1;
	}

	/**
	 * 拡張フラグ１設定
	 * @param nmeflg1 拡張フラグ１
	*/
	public void setNmeflg1(final BigDecimal nmeflg1) {
		this.nmeflg1 = nmeflg1;
	}

	/**
	 * 拡張フラグ２取得
	 * @return BigDecimal 拡張フラグ２
	*/
	public BigDecimal getNmeflg2() {
		return this.nmeflg2;
	}

	/**
	 * 拡張フラグ２設定
	 * @param nmeflg2 拡張フラグ２
	*/
	public void setNmeflg2(final BigDecimal nmeflg2) {
		this.nmeflg2 = nmeflg2;
	}

	/**
	 * 拡張フラグ３取得
	 * @return BigDecimal 拡張フラグ３
	*/
	public BigDecimal getNmeflg3() {
		return this.nmeflg3;
	}

	/**
	 * 拡張フラグ３設定
	 * @param nmeflg3 拡張フラグ３
	*/
	public void setNmeflg3(final BigDecimal nmeflg3) {
		this.nmeflg3 = nmeflg3;
	}

	/**
	 * 拡張フラグ４取得
	 * @return BigDecimal 拡張フラグ４
	*/
	public BigDecimal getNmeflg4() {
		return this.nmeflg4;
	}

	/**
	 * 拡張フラグ４設定
	 * @param nmeflg4 拡張フラグ４
	*/
	public void setNmeflg4(final BigDecimal nmeflg4) {
		this.nmeflg4 = nmeflg4;
	}

	/**
	 * 拡張フラグ５取得
	 * @return BigDecimal 拡張フラグ５
	*/
	public BigDecimal getNmeflg5() {
		return this.nmeflg5;
	}

	/**
	 * 拡張フラグ５設定
	 * @param nmeflg5 拡張フラグ５
	*/
	public void setNmeflg5(final BigDecimal nmeflg5) {
		this.nmeflg5 = nmeflg5;
	}

	/**
	 * 拡張区分１取得
	 * @return BigDecimal 拡張区分１
	*/
	public BigDecimal getNmekbn1() {
		return this.nmekbn1;
	}

	/**
	 * 拡張区分１設定
	 * @param nmekbn1 拡張区分１
	*/
	public void setNmekbn1(final BigDecimal nmekbn1) {
		this.nmekbn1 = nmekbn1;
	}

	/**
	 * 拡張区分２取得
	 * @return BigDecimal 拡張区分２
	*/
	public BigDecimal getNmekbn2() {
		return this.nmekbn2;
	}

	/**
	 * 拡張区分２設定
	 * @param nmekbn2 拡張区分２
	*/
	public void setNmekbn2(final BigDecimal nmekbn2) {
		this.nmekbn2 = nmekbn2;
	}

	/**
	 * 拡張区分３取得
	 * @return BigDecimal 拡張区分３
	*/
	public BigDecimal getNmekbn3() {
		return this.nmekbn3;
	}

	/**
	 * 拡張区分３設定
	 * @param nmekbn3 拡張区分３
	*/
	public void setNmekbn3(final BigDecimal nmekbn3) {
		this.nmekbn3 = nmekbn3;
	}

	/**
	 * 拡張区分４取得
	 * @return BigDecimal 拡張区分４
	*/
	public BigDecimal getNmekbn4() {
		return this.nmekbn4;
	}

	/**
	 * 拡張区分４設定
	 * @param nmekbn4 拡張区分４
	*/
	public void setNmekbn4(final BigDecimal nmekbn4) {
		this.nmekbn4 = nmekbn4;
	}

	/**
	 * 拡張区分５取得
	 * @return BigDecimal 拡張区分５
	*/
	public BigDecimal getNmekbn5() {
		return this.nmekbn5;
	}

	/**
	 * 拡張区分５設定
	 * @param nmekbn5 拡張区分５
	*/
	public void setNmekbn5(final BigDecimal nmekbn5) {
		this.nmekbn5 = nmekbn5;
	}

	/**
	 * 拡張日付１取得
	 * @return BigDecimal 拡張日付１
	*/
	public BigDecimal getNmedate1() {
		return this.nmedate1;
	}

	/**
	 * 拡張日付１設定
	 * @param nmedate1 拡張日付１
	*/
	public void setNmedate1(final BigDecimal nmedate1) {
		this.nmedate1 = nmedate1;
	}

	/**
	 * 拡張日付２取得
	 * @return BigDecimal 拡張日付２
	*/
	public BigDecimal getNmedate2() {
		return this.nmedate2;
	}

	/**
	 * 拡張日付２設定
	 * @param nmedate2 拡張日付２
	*/
	public void setNmedate2(final BigDecimal nmedate2) {
		this.nmedate2 = nmedate2;
	}

	/**
	 * 拡張日付３取得
	 * @return BigDecimal 拡張日付３
	*/
	public BigDecimal getNmedate3() {
		return this.nmedate3;
	}

	/**
	 * 拡張日付３設定
	 * @param nmedate3 拡張日付３
	*/
	public void setNmedate3(final BigDecimal nmedate3) {
		this.nmedate3 = nmedate3;
	}

	/**
	 * 拡張日付４取得
	 * @return BigDecimal 拡張日付４
	*/
	public BigDecimal getNmedate4() {
		return this.nmedate4;
	}

	/**
	 * 拡張日付４設定
	 * @param nmedate4 拡張日付４
	*/
	public void setNmedate4(final BigDecimal nmedate4) {
		this.nmedate4 = nmedate4;
	}

	/**
	 * 拡張日付５取得
	 * @return BigDecimal 拡張日付５
	*/
	public BigDecimal getNmedate5() {
		return this.nmedate5;
	}

	/**
	 * 拡張日付５設定
	 * @param nmedate5 拡張日付５
	*/
	public void setNmedate5(final BigDecimal nmedate5) {
		this.nmedate5 = nmedate5;
	}

	/**
	 * 拡張時間１取得
	 * @return BigDecimal 拡張時間１
	*/
	public BigDecimal getNmetime1() {
		return this.nmetime1;
	}

	/**
	 * 拡張時間１設定
	 * @param nmetime1 拡張時間１
	*/
	public void setNmetime1(final BigDecimal nmetime1) {
		this.nmetime1 = nmetime1;
	}

	/**
	 * 拡張時間２取得
	 * @return BigDecimal 拡張時間２
	*/
	public BigDecimal getNmetime2() {
		return this.nmetime2;
	}

	/**
	 * 拡張時間２設定
	 * @param nmetime2 拡張時間２
	*/
	public void setNmetime2(final BigDecimal nmetime2) {
		this.nmetime2 = nmetime2;
	}

	/**
	 * 拡張時間３取得
	 * @return BigDecimal 拡張時間３
	*/
	public BigDecimal getNmetime3() {
		return this.nmetime3;
	}

	/**
	 * 拡張時間３設定
	 * @param nmetime3 拡張時間３
	*/
	public void setNmetime3(final BigDecimal nmetime3) {
		this.nmetime3 = nmetime3;
	}

	/**
	 * 拡張時間４取得
	 * @return BigDecimal 拡張時間４
	*/
	public BigDecimal getNmetime4() {
		return this.nmetime4;
	}

	/**
	 * 拡張時間４設定
	 * @param nmetime4 拡張時間４
	*/
	public void setNmetime4(final BigDecimal nmetime4) {
		this.nmetime4 = nmetime4;
	}

	/**
	 * 拡張時間５取得
	 * @return BigDecimal 拡張時間５
	*/
	public BigDecimal getNmetime5() {
		return this.nmetime5;
	}

	/**
	 * 拡張時間５設定
	 * @param nmetime5 拡張時間５
	*/
	public void setNmetime5(final BigDecimal nmetime5) {
		this.nmetime5 = nmetime5;
	}

	/**
	 * 拡張数値１取得
	 * @return BigDecimal 拡張数値１
	*/
	public BigDecimal getNmenum1() {
		return this.nmenum1;
	}

	/**
	 * 拡張数値１設定
	 * @param nmenum1 拡張数値１
	*/
	public void setNmenum1(final BigDecimal nmenum1) {
		this.nmenum1 = nmenum1;
	}

	/**
	 * 拡張数値２取得
	 * @return BigDecimal 拡張数値２
	*/
	public BigDecimal getNmenum2() {
		return this.nmenum2;
	}

	/**
	 * 拡張数値２設定
	 * @param nmenum2 拡張数値２
	*/
	public void setNmenum2(final BigDecimal nmenum2) {
		this.nmenum2 = nmenum2;
	}

	/**
	 * 拡張数値３取得
	 * @return BigDecimal 拡張数値３
	*/
	public BigDecimal getNmenum3() {
		return this.nmenum3;
	}

	/**
	 * 拡張数値３設定
	 * @param nmenum3 拡張数値３
	*/
	public void setNmenum3(final BigDecimal nmenum3) {
		this.nmenum3 = nmenum3;
	}

	/**
	 * 拡張数値４取得
	 * @return BigDecimal 拡張数値４
	*/
	public BigDecimal getNmenum4() {
		return this.nmenum4;
	}

	/**
	 * 拡張数値４設定
	 * @param nmenum4 拡張数値４
	*/
	public void setNmenum4(final BigDecimal nmenum4) {
		this.nmenum4 = nmenum4;
	}

	/**
	 * 拡張数値５取得
	 * @return BigDecimal 拡張数値５
	*/
	public BigDecimal getNmenum5() {
		return this.nmenum5;
	}

	/**
	 * 拡張数値５設定
	 * @param nmenum5 拡張数値５
	*/
	public void setNmenum5(final BigDecimal nmenum5) {
		this.nmenum5 = nmenum5;
	}

	/**
	 * 拡張文字列１取得
	 * @return String 拡張文字列１
	*/
	public String getNmevalue1() {
		return this.nmevalue1;
	}

	/**
	 * 拡張文字列１設定
	 * @param nmevalue1 拡張文字列１
	*/
	public void setNmevalue1(final String nmevalue1) {
		this.nmevalue1 = nmevalue1;
	}

	/**
	 * 拡張文字列２取得
	 * @return String 拡張文字列２
	*/
	public String getNmevalue2() {
		return this.nmevalue2;
	}

	/**
	 * 拡張文字列２設定
	 * @param nmevalue2 拡張文字列２
	*/
	public void setNmevalue2(final String nmevalue2) {
		this.nmevalue2 = nmevalue2;
	}

	/**
	 * 拡張文字列３取得
	 * @return String 拡張文字列３
	*/
	public String getNmevalue3() {
		return this.nmevalue3;
	}

	/**
	 * 拡張文字列３設定
	 * @param nmevalue3 拡張文字列３
	*/
	public void setNmevalue3(final String nmevalue3) {
		this.nmevalue3 = nmevalue3;
	}

	/**
	 * 拡張文字列４取得
	 * @return String 拡張文字列４
	*/
	public String getNmevalue4() {
		return this.nmevalue4;
	}

	/**
	 * 拡張文字列４設定
	 * @param nmevalue4 拡張文字列４
	*/
	public void setNmevalue4(final String nmevalue4) {
		this.nmevalue4 = nmevalue4;
	}

	/**
	 * 拡張文字列５取得
	 * @return String 拡張文字列５
	*/
	public String getNmevalue5() {
		return this.nmevalue5;
	}

	/**
	 * 拡張文字列５設定
	 * @param nmevalue5 拡張文字列５
	*/
	public void setNmevalue5(final String nmevalue5) {
		this.nmevalue5 = nmevalue5;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
	*/
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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
