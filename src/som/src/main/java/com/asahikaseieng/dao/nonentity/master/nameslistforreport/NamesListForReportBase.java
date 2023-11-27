/*
 * Created on 2009/08/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.nameslistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * NamesListForReportクラス.
 * @author t0011036
 */
public class NamesListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public NamesListForReportBase() {
	}

	//
	// 定数
	//

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

	/** COLUMNアノテーション quantityRoundup */
	public static final String quantityRoundup_COLUMN = "QUANTITY_ROUNDUP";

	/** COLUMNアノテーション quantityRoundupUnit */
	public static final String quantityRoundupUnit_COLUMN = "QUANTITY_ROUNDUP_UNIT";

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

	/** COLUMNアノテーション nameDivisionName */
	public static final String nameDivisionName_COLUMN = "NAME_DIVISION_NAME";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String nameDivision;

	private String nameCd;

	private String name01;

	private String name02;

	private String name03;

	private java.math.BigDecimal quantityRoundup;

	private java.math.BigDecimal quantityRoundupUnit;

	private java.math.BigDecimal nmqty01;

	private java.math.BigDecimal nmqty02;

	private java.math.BigDecimal nmqty03;

	private java.math.BigDecimal nmeflg1;

	private java.math.BigDecimal nmeflg2;

	private java.math.BigDecimal nmeflg3;

	private java.math.BigDecimal nmeflg4;

	private java.math.BigDecimal nmeflg5;

	private java.math.BigDecimal nmekbn1;

	private java.math.BigDecimal nmekbn2;

	private java.math.BigDecimal nmekbn3;

	private java.math.BigDecimal nmekbn4;

	private java.math.BigDecimal nmekbn5;

	private java.math.BigDecimal nmedate1;

	private java.math.BigDecimal nmedate2;

	private java.math.BigDecimal nmedate3;

	private java.math.BigDecimal nmedate4;

	private java.math.BigDecimal nmedate5;

	private java.math.BigDecimal nmetime1;

	private java.math.BigDecimal nmetime2;

	private java.math.BigDecimal nmetime3;

	private java.math.BigDecimal nmetime4;

	private java.math.BigDecimal nmetime5;

	private java.math.BigDecimal nmenum1;

	private java.math.BigDecimal nmenum2;

	private java.math.BigDecimal nmenum3;

	private java.math.BigDecimal nmenum4;

	private java.math.BigDecimal nmenum5;

	private String nmevalue1;

	private String nmevalue2;

	private String nmevalue3;

	private String nmevalue4;

	private String nmevalue5;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String nameDivisionName;

	private String inputorName;

	private String updatorName;

	//
	// インスタンスメソッド
	//

	/**
	 * nameDivision取得.
	 * @return nameDivision
	 */
	public String getNameDivision() {
		return this.nameDivision;
	}

	/**
	 * nameDivision設定.
	 * @param nameDivision nameDivision
	 */
	public void setNameDivision(final String nameDivision) {
		this.nameDivision = nameDivision;
	}

	/**
	 * nameCd取得.
	 * @return nameCd
	 */
	public String getNameCd() {
		return this.nameCd;
	}

	/**
	 * nameCd設定.
	 * @param nameCd nameCd
	 */
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}

	/**
	 * name01取得.
	 * @return name01
	 */
	public String getName01() {
		return this.name01;
	}

	/**
	 * name01設定.
	 * @param name01 name01
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * name02取得.
	 * @return name02
	 */
	public String getName02() {
		return this.name02;
	}

	/**
	 * name02設定.
	 * @param name02 name02
	 */
	public void setName02(final String name02) {
		this.name02 = name02;
	}

	/**
	 * name03取得.
	 * @return name03
	 */
	public String getName03() {
		return this.name03;
	}

	/**
	 * name03設定.
	 * @param name03 name03
	 */
	public void setName03(final String name03) {
		this.name03 = name03;
	}

	/**
	 * quantityRoundup取得.
	 * @return quantityRoundup
	 */
	public java.math.BigDecimal getQuantityRoundup() {
		return this.quantityRoundup;
	}

	/**
	 * quantityRoundup設定.
	 * @param quantityRoundup quantityRoundup
	 */
	public void setQuantityRoundup(final java.math.BigDecimal quantityRoundup) {
		this.quantityRoundup = quantityRoundup;
	}

	/**
	 * quantityRoundupUnit取得.
	 * @return quantityRoundupUnit
	 */
	public java.math.BigDecimal getQuantityRoundupUnit() {
		return this.quantityRoundupUnit;
	}

	/**
	 * quantityRoundupUnit設定.
	 * @param quantityRoundupUnit quantityRoundupUnit
	 */
	public void setQuantityRoundupUnit(final java.math.BigDecimal quantityRoundupUnit) {
		this.quantityRoundupUnit = quantityRoundupUnit;
	}

	/**
	 * nmqty01取得.
	 * @return nmqty01
	 */
	public java.math.BigDecimal getNmqty01() {
		return this.nmqty01;
	}

	/**
	 * nmqty01設定.
	 * @param nmqty01 nmqty01
	 */
	public void setNmqty01(final java.math.BigDecimal nmqty01) {
		this.nmqty01 = nmqty01;
	}

	/**
	 * nmqty02取得.
	 * @return nmqty02
	 */
	public java.math.BigDecimal getNmqty02() {
		return this.nmqty02;
	}

	/**
	 * nmqty02設定.
	 * @param nmqty02 nmqty02
	 */
	public void setNmqty02(final java.math.BigDecimal nmqty02) {
		this.nmqty02 = nmqty02;
	}

	/**
	 * nmqty03取得.
	 * @return nmqty03
	 */
	public java.math.BigDecimal getNmqty03() {
		return this.nmqty03;
	}

	/**
	 * nmqty03設定.
	 * @param nmqty03 nmqty03
	 */
	public void setNmqty03(final java.math.BigDecimal nmqty03) {
		this.nmqty03 = nmqty03;
	}

	/**
	 * nmeflg1取得.
	 * @return nmeflg1
	 */
	public java.math.BigDecimal getNmeflg1() {
		return this.nmeflg1;
	}

	/**
	 * nmeflg1設定.
	 * @param nmeflg1 nmeflg1
	 */
	public void setNmeflg1(final java.math.BigDecimal nmeflg1) {
		this.nmeflg1 = nmeflg1;
	}

	/**
	 * nmeflg2取得.
	 * @return nmeflg2
	 */
	public java.math.BigDecimal getNmeflg2() {
		return this.nmeflg2;
	}

	/**
	 * nmeflg2設定.
	 * @param nmeflg2 nmeflg2
	 */
	public void setNmeflg2(final java.math.BigDecimal nmeflg2) {
		this.nmeflg2 = nmeflg2;
	}

	/**
	 * nmeflg3取得.
	 * @return nmeflg3
	 */
	public java.math.BigDecimal getNmeflg3() {
		return this.nmeflg3;
	}

	/**
	 * nmeflg3設定.
	 * @param nmeflg3 nmeflg3
	 */
	public void setNmeflg3(final java.math.BigDecimal nmeflg3) {
		this.nmeflg3 = nmeflg3;
	}

	/**
	 * nmeflg4取得.
	 * @return nmeflg4
	 */
	public java.math.BigDecimal getNmeflg4() {
		return this.nmeflg4;
	}

	/**
	 * nmeflg4設定.
	 * @param nmeflg4 nmeflg4
	 */
	public void setNmeflg4(final java.math.BigDecimal nmeflg4) {
		this.nmeflg4 = nmeflg4;
	}

	/**
	 * nmeflg5取得.
	 * @return nmeflg5
	 */
	public java.math.BigDecimal getNmeflg5() {
		return this.nmeflg5;
	}

	/**
	 * nmeflg5設定.
	 * @param nmeflg5 nmeflg5
	 */
	public void setNmeflg5(final java.math.BigDecimal nmeflg5) {
		this.nmeflg5 = nmeflg5;
	}

	/**
	 * nmekbn1取得.
	 * @return nmekbn1
	 */
	public java.math.BigDecimal getNmekbn1() {
		return this.nmekbn1;
	}

	/**
	 * nmekbn1設定.
	 * @param nmekbn1 nmekbn1
	 */
	public void setNmekbn1(final java.math.BigDecimal nmekbn1) {
		this.nmekbn1 = nmekbn1;
	}

	/**
	 * nmekbn2取得.
	 * @return nmekbn2
	 */
	public java.math.BigDecimal getNmekbn2() {
		return this.nmekbn2;
	}

	/**
	 * nmekbn2設定.
	 * @param nmekbn2 nmekbn2
	 */
	public void setNmekbn2(final java.math.BigDecimal nmekbn2) {
		this.nmekbn2 = nmekbn2;
	}

	/**
	 * nmekbn3取得.
	 * @return nmekbn3
	 */
	public java.math.BigDecimal getNmekbn3() {
		return this.nmekbn3;
	}

	/**
	 * nmekbn3設定.
	 * @param nmekbn3 nmekbn3
	 */
	public void setNmekbn3(final java.math.BigDecimal nmekbn3) {
		this.nmekbn3 = nmekbn3;
	}

	/**
	 * nmekbn4取得.
	 * @return nmekbn4
	 */
	public java.math.BigDecimal getNmekbn4() {
		return this.nmekbn4;
	}

	/**
	 * nmekbn4設定.
	 * @param nmekbn4 nmekbn4
	 */
	public void setNmekbn4(final java.math.BigDecimal nmekbn4) {
		this.nmekbn4 = nmekbn4;
	}

	/**
	 * nmekbn5取得.
	 * @return nmekbn5
	 */
	public java.math.BigDecimal getNmekbn5() {
		return this.nmekbn5;
	}

	/**
	 * nmekbn5設定.
	 * @param nmekbn5 nmekbn5
	 */
	public void setNmekbn5(final java.math.BigDecimal nmekbn5) {
		this.nmekbn5 = nmekbn5;
	}

	/**
	 * nmedate1取得.
	 * @return nmedate1
	 */
	public java.math.BigDecimal getNmedate1() {
		return this.nmedate1;
	}

	/**
	 * nmedate1設定.
	 * @param nmedate1 nmedate1
	 */
	public void setNmedate1(final java.math.BigDecimal nmedate1) {
		this.nmedate1 = nmedate1;
	}

	/**
	 * nmedate2取得.
	 * @return nmedate2
	 */
	public java.math.BigDecimal getNmedate2() {
		return this.nmedate2;
	}

	/**
	 * nmedate2設定.
	 * @param nmedate2 nmedate2
	 */
	public void setNmedate2(final java.math.BigDecimal nmedate2) {
		this.nmedate2 = nmedate2;
	}

	/**
	 * nmedate3取得.
	 * @return nmedate3
	 */
	public java.math.BigDecimal getNmedate3() {
		return this.nmedate3;
	}

	/**
	 * nmedate3設定.
	 * @param nmedate3 nmedate3
	 */
	public void setNmedate3(final java.math.BigDecimal nmedate3) {
		this.nmedate3 = nmedate3;
	}

	/**
	 * nmedate4取得.
	 * @return nmedate4
	 */
	public java.math.BigDecimal getNmedate4() {
		return this.nmedate4;
	}

	/**
	 * nmedate4設定.
	 * @param nmedate4 nmedate4
	 */
	public void setNmedate4(final java.math.BigDecimal nmedate4) {
		this.nmedate4 = nmedate4;
	}

	/**
	 * nmedate5取得.
	 * @return nmedate5
	 */
	public java.math.BigDecimal getNmedate5() {
		return this.nmedate5;
	}

	/**
	 * nmedate5設定.
	 * @param nmedate5 nmedate5
	 */
	public void setNmedate5(final java.math.BigDecimal nmedate5) {
		this.nmedate5 = nmedate5;
	}

	/**
	 * nmetime1取得.
	 * @return nmetime1
	 */
	public java.math.BigDecimal getNmetime1() {
		return this.nmetime1;
	}

	/**
	 * nmetime1設定.
	 * @param nmetime1 nmetime1
	 */
	public void setNmetime1(final java.math.BigDecimal nmetime1) {
		this.nmetime1 = nmetime1;
	}

	/**
	 * nmetime2取得.
	 * @return nmetime2
	 */
	public java.math.BigDecimal getNmetime2() {
		return this.nmetime2;
	}

	/**
	 * nmetime2設定.
	 * @param nmetime2 nmetime2
	 */
	public void setNmetime2(final java.math.BigDecimal nmetime2) {
		this.nmetime2 = nmetime2;
	}

	/**
	 * nmetime3取得.
	 * @return nmetime3
	 */
	public java.math.BigDecimal getNmetime3() {
		return this.nmetime3;
	}

	/**
	 * nmetime3設定.
	 * @param nmetime3 nmetime3
	 */
	public void setNmetime3(final java.math.BigDecimal nmetime3) {
		this.nmetime3 = nmetime3;
	}

	/**
	 * nmetime4取得.
	 * @return nmetime4
	 */
	public java.math.BigDecimal getNmetime4() {
		return this.nmetime4;
	}

	/**
	 * nmetime4設定.
	 * @param nmetime4 nmetime4
	 */
	public void setNmetime4(final java.math.BigDecimal nmetime4) {
		this.nmetime4 = nmetime4;
	}

	/**
	 * nmetime5取得.
	 * @return nmetime5
	 */
	public java.math.BigDecimal getNmetime5() {
		return this.nmetime5;
	}

	/**
	 * nmetime5設定.
	 * @param nmetime5 nmetime5
	 */
	public void setNmetime5(final java.math.BigDecimal nmetime5) {
		this.nmetime5 = nmetime5;
	}

	/**
	 * nmenum1取得.
	 * @return nmenum1
	 */
	public java.math.BigDecimal getNmenum1() {
		return this.nmenum1;
	}

	/**
	 * nmenum1設定.
	 * @param nmenum1 nmenum1
	 */
	public void setNmenum1(final java.math.BigDecimal nmenum1) {
		this.nmenum1 = nmenum1;
	}

	/**
	 * nmenum2取得.
	 * @return nmenum2
	 */
	public java.math.BigDecimal getNmenum2() {
		return this.nmenum2;
	}

	/**
	 * nmenum2設定.
	 * @param nmenum2 nmenum2
	 */
	public void setNmenum2(final java.math.BigDecimal nmenum2) {
		this.nmenum2 = nmenum2;
	}

	/**
	 * nmenum3取得.
	 * @return nmenum3
	 */
	public java.math.BigDecimal getNmenum3() {
		return this.nmenum3;
	}

	/**
	 * nmenum3設定.
	 * @param nmenum3 nmenum3
	 */
	public void setNmenum3(final java.math.BigDecimal nmenum3) {
		this.nmenum3 = nmenum3;
	}

	/**
	 * nmenum4取得.
	 * @return nmenum4
	 */
	public java.math.BigDecimal getNmenum4() {
		return this.nmenum4;
	}

	/**
	 * nmenum4設定.
	 * @param nmenum4 nmenum4
	 */
	public void setNmenum4(final java.math.BigDecimal nmenum4) {
		this.nmenum4 = nmenum4;
	}

	/**
	 * nmenum5取得.
	 * @return nmenum5
	 */
	public java.math.BigDecimal getNmenum5() {
		return this.nmenum5;
	}

	/**
	 * nmenum5設定.
	 * @param nmenum5 nmenum5
	 */
	public void setNmenum5(final java.math.BigDecimal nmenum5) {
		this.nmenum5 = nmenum5;
	}

	/**
	 * nmevalue1取得.
	 * @return nmevalue1
	 */
	public String getNmevalue1() {
		return this.nmevalue1;
	}

	/**
	 * nmevalue1設定.
	 * @param nmevalue1 nmevalue1
	 */
	public void setNmevalue1(final String nmevalue1) {
		this.nmevalue1 = nmevalue1;
	}

	/**
	 * nmevalue2取得.
	 * @return nmevalue2
	 */
	public String getNmevalue2() {
		return this.nmevalue2;
	}

	/**
	 * nmevalue2設定.
	 * @param nmevalue2 nmevalue2
	 */
	public void setNmevalue2(final String nmevalue2) {
		this.nmevalue2 = nmevalue2;
	}

	/**
	 * nmevalue3取得.
	 * @return nmevalue3
	 */
	public String getNmevalue3() {
		return this.nmevalue3;
	}

	/**
	 * nmevalue3設定.
	 * @param nmevalue3 nmevalue3
	 */
	public void setNmevalue3(final String nmevalue3) {
		this.nmevalue3 = nmevalue3;
	}

	/**
	 * nmevalue4取得.
	 * @return nmevalue4
	 */
	public String getNmevalue4() {
		return this.nmevalue4;
	}

	/**
	 * nmevalue4設定.
	 * @param nmevalue4 nmevalue4
	 */
	public void setNmevalue4(final String nmevalue4) {
		this.nmevalue4 = nmevalue4;
	}

	/**
	 * nmevalue5取得.
	 * @return nmevalue5
	 */
	public String getNmevalue5() {
		return this.nmevalue5;
	}

	/**
	 * nmevalue5設定.
	 * @param nmevalue5 nmevalue5
	 */
	public void setNmevalue5(final String nmevalue5) {
		this.nmevalue5 = nmevalue5;
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
	 * nameDivisionName取得.
	 * @return nameDivisionName
	 */
	public String getNameDivisionName() {
		return this.nameDivisionName;
	}

	/**
	 * nameDivisionName設定.
	 * @param nameDivisionName nameDivisionName
	 */
	public void setNameDivisionName(final String nameDivisionName) {
		this.nameDivisionName = nameDivisionName;
	}

	/**
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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

