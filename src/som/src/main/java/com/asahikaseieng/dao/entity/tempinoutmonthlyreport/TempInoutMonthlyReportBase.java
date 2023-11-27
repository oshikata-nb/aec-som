/*
 * Created on Wed Apr 14 10:59:16 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tempinoutmonthlyreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * TempInoutMonthlyReportBaseクラス.
 * @author kanri-user
 */
public class TempInoutMonthlyReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TempInoutMonthlyReportBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "TEMP_INOUT_MONTHLY_REPORT";


//	/** IDアノテーション rowNo. */
//	public static final String rowNo_ID = "assigned";

	/** COLUMNアノテーション rowNo. */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション dateFrom. */
	public static final String dateFrom_COLUMN = "DATE_FROM";

	/** COLUMNアノテーション dateTo. */
	public static final String dateTo_COLUMN = "DATE_TO";

	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション locationName. */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション financialClassCd. */
	public static final String financialClassCd_COLUMN = "FINANCIAL_CLASS_CD";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName. */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking. */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション kgOfFractionManagement. */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション lm1. */
	public static final String lm1_COLUMN = "LM_1";

	/** COLUMNアノテーション lm2. */
	public static final String lm2_COLUMN = "LM_2";

	/** COLUMNアノテーション lm3. */
	public static final String lm3_COLUMN = "LM_3";

	/** COLUMNアノテーション lm4. */
	public static final String lm4_COLUMN = "LM_4";

	/** COLUMNアノテーション in1. */
	public static final String in1_COLUMN = "IN_1";

	/** COLUMNアノテーション in2. */
	public static final String in2_COLUMN = "IN_2";

	/** COLUMNアノテーション in3. */
	public static final String in3_COLUMN = "IN_3";

	/** COLUMNアノテーション in4. */
	public static final String in4_COLUMN = "IN_4";

	/** COLUMNアノテーション inAll. */
	public static final String inAll_COLUMN = "IN_ALL";

	/** COLUMNアノテーション out1. */
	public static final String out1_COLUMN = "OUT_1";

	/** COLUMNアノテーション out2. */
	public static final String out2_COLUMN = "OUT_2";

	/** COLUMNアノテーション out3. */
	public static final String out3_COLUMN = "OUT_3";

	/** COLUMNアノテーション out4. */
	public static final String out4_COLUMN = "OUT_4";

	/** COLUMNアノテーション out5. */
	public static final String out5_COLUMN = "OUT_5";

	/** COLUMNアノテーション out6. */
	public static final String out6_COLUMN = "OUT_6";

	/** COLUMNアノテーション out7. */
	public static final String out7_COLUMN = "OUT_7";

	/** COLUMNアノテーション outAll. */
	public static final String outAll_COLUMN = "OUT_ALL";

	/** COLUMNアノテーション nm1. */
	public static final String nm1_COLUMN = "NM_1";

	/** COLUMNアノテーション nm2. */
	public static final String nm2_COLUMN = "NM_2";

	/** COLUMNアノテーション nm3. */
	public static final String nm3_COLUMN = "NM_3";

	/** COLUMNアノテーション nm4. */
	public static final String nm4_COLUMN = "NM_4";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal rowNo;

	private String dateFrom;

	private String dateTo;

	private String locationCd;

	private String locationName;

	private String financialClassCd;

	private String itemCd;

	private String itemName;

	private String styleOfPacking;

	private java.math.BigDecimal kgOfFractionManagement;

	private java.math.BigDecimal lm1;

	private java.math.BigDecimal lm2;

	private java.math.BigDecimal lm3;

	private java.math.BigDecimal lm4;

	private java.math.BigDecimal in1;

	private java.math.BigDecimal in2;

	private java.math.BigDecimal in3;

	private java.math.BigDecimal in4;

	private java.math.BigDecimal inAll;

	private java.math.BigDecimal out1;

	private java.math.BigDecimal out2;

	private java.math.BigDecimal out3;

	private java.math.BigDecimal out4;

	private java.math.BigDecimal out5;

	private java.math.BigDecimal out6;

	private java.math.BigDecimal out7;

	private java.math.BigDecimal outAll;

	private java.math.BigDecimal nm1;

	private java.math.BigDecimal nm2;

	private java.math.BigDecimal nm3;

	private java.math.BigDecimal nm4;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

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
	 * dateFrom取得.
	 * @return dateFrom
	 */
	public String getDateFrom() {
		return this.dateFrom;
	}

	/**
	 * dateFrom設定.
	 * @param dateFrom dateFrom
	 */
	public void setDateFrom(final String dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * dateTo取得.
	 * @return dateTo
	 */
	public String getDateTo() {
		return this.dateTo;
	}

	/**
	 * dateTo設定.
	 * @param dateTo dateTo
	 */
	public void setDateTo(final String dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * locationCd取得.
	 * @return locationCd
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * locationCd設定.
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * locationName取得.
	 * @return locationName
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * locationName設定.
	 * @param locationName locationName
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * financialClassCd取得.
	 * @return financialClassCd
	 */
	public String getFinancialClassCd() {
		return this.financialClassCd;
	}

	/**
	 * financialClassCd設定.
	 * @param financialClassCd financialClassCd
	 */
	public void setFinancialClassCd(final String financialClassCd) {
		this.financialClassCd = financialClassCd;
	}

	/**
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * styleOfPacking取得.
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * styleOfPacking設定.
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * kgOfFractionManagement取得.
	 * @return kgOfFractionManagement
	 */
	public java.math.BigDecimal getKgOfFractionManagement() {
		return this.kgOfFractionManagement;
	}

	/**
	 * kgOfFractionManagement設定.
	 * @param kgOfFractionManagement kgOfFractionManagement
	 */
	public void setKgOfFractionManagement(final java.math.BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * lm1取得.
	 * @return lm1
	 */
	public java.math.BigDecimal getLm1() {
		return this.lm1;
	}

	/**
	 * lm1設定.
	 * @param lm1 lm1
	 */
	public void setLm1(final java.math.BigDecimal lm1) {
		this.lm1 = lm1;
	}

	/**
	 * lm2取得.
	 * @return lm2
	 */
	public java.math.BigDecimal getLm2() {
		return this.lm2;
	}

	/**
	 * lm2設定.
	 * @param lm2 lm2
	 */
	public void setLm2(final java.math.BigDecimal lm2) {
		this.lm2 = lm2;
	}

	/**
	 * lm3取得.
	 * @return lm3
	 */
	public java.math.BigDecimal getLm3() {
		return this.lm3;
	}

	/**
	 * lm3設定.
	 * @param lm3 lm3
	 */
	public void setLm3(final java.math.BigDecimal lm3) {
		this.lm3 = lm3;
	}

	/**
	 * lm4取得.
	 * @return lm4
	 */
	public java.math.BigDecimal getLm4() {
		return this.lm4;
	}

	/**
	 * lm4設定.
	 * @param lm4 lm4
	 */
	public void setLm4(final java.math.BigDecimal lm4) {
		this.lm4 = lm4;
	}

	/**
	 * in1取得.
	 * @return in1
	 */
	public java.math.BigDecimal getIn1() {
		return this.in1;
	}

	/**
	 * in1設定.
	 * @param in1 in1
	 */
	public void setIn1(final java.math.BigDecimal in1) {
		this.in1 = in1;
	}

	/**
	 * in2取得.
	 * @return in2
	 */
	public java.math.BigDecimal getIn2() {
		return this.in2;
	}

	/**
	 * in2設定.
	 * @param in2 in2
	 */
	public void setIn2(final java.math.BigDecimal in2) {
		this.in2 = in2;
	}

	/**
	 * in3取得.
	 * @return in3
	 */
	public java.math.BigDecimal getIn3() {
		return this.in3;
	}

	/**
	 * in3設定.
	 * @param in3 in3
	 */
	public void setIn3(final java.math.BigDecimal in3) {
		this.in3 = in3;
	}

	/**
	 * in4取得.
	 * @return in4
	 */
	public java.math.BigDecimal getIn4() {
		return this.in4;
	}

	/**
	 * in4設定.
	 * @param in4 in4
	 */
	public void setIn4(final java.math.BigDecimal in4) {
		this.in4 = in4;
	}

	/**
	 * inAll取得.
	 * @return inAll
	 */
	public java.math.BigDecimal getInAll() {
		return this.inAll;
	}

	/**
	 * inAll設定.
	 * @param inAll inAll
	 */
	public void setInAll(final java.math.BigDecimal inAll) {
		this.inAll = inAll;
	}

	/**
	 * out1取得.
	 * @return out1
	 */
	public java.math.BigDecimal getOut1() {
		return this.out1;
	}

	/**
	 * out1設定.
	 * @param out1 out1
	 */
	public void setOut1(final java.math.BigDecimal out1) {
		this.out1 = out1;
	}

	/**
	 * out2取得.
	 * @return out2
	 */
	public java.math.BigDecimal getOut2() {
		return this.out2;
	}

	/**
	 * out2設定.
	 * @param out2 out2
	 */
	public void setOut2(final java.math.BigDecimal out2) {
		this.out2 = out2;
	}

	/**
	 * out3取得.
	 * @return out3
	 */
	public java.math.BigDecimal getOut3() {
		return this.out3;
	}

	/**
	 * out3設定.
	 * @param out3 out3
	 */
	public void setOut3(final java.math.BigDecimal out3) {
		this.out3 = out3;
	}

	/**
	 * out4取得.
	 * @return out4
	 */
	public java.math.BigDecimal getOut4() {
		return this.out4;
	}

	/**
	 * out4設定.
	 * @param out4 out4
	 */
	public void setOut4(final java.math.BigDecimal out4) {
		this.out4 = out4;
	}

	/**
	 * out5取得.
	 * @return out5
	 */
	public java.math.BigDecimal getOut5() {
		return this.out5;
	}

	/**
	 * out5設定.
	 * @param out5 out5
	 */
	public void setOut5(final java.math.BigDecimal out5) {
		this.out5 = out5;
	}

	/**
	 * out6取得.
	 * @return out6
	 */
	public java.math.BigDecimal getOut6() {
		return this.out6;
	}

	/**
	 * out6設定.
	 * @param out6 out6
	 */
	public void setOut6(final java.math.BigDecimal out6) {
		this.out6 = out6;
	}

	/**
	 * out7取得.
	 * @return out7
	 */
	public java.math.BigDecimal getOut7() {
		return this.out7;
	}

	/**
	 * out7設定.
	 * @param out7 out7
	 */
	public void setOut7(final java.math.BigDecimal out7) {
		this.out7 = out7;
	}

	/**
	 * outAll取得.
	 * @return outAll
	 */
	public java.math.BigDecimal getOutAll() {
		return this.outAll;
	}

	/**
	 * outAll設定.
	 * @param outAll outAll
	 */
	public void setOutAll(final java.math.BigDecimal outAll) {
		this.outAll = outAll;
	}

	/**
	 * nm1取得.
	 * @return nm1
	 */
	public java.math.BigDecimal getNm1() {
		return this.nm1;
	}

	/**
	 * nm1設定.
	 * @param nm1 nm1
	 */
	public void setNm1(final java.math.BigDecimal nm1) {
		this.nm1 = nm1;
	}

	/**
	 * nm2取得.
	 * @return nm2
	 */
	public java.math.BigDecimal getNm2() {
		return this.nm2;
	}

	/**
	 * nm2設定.
	 * @param nm2 nm2
	 */
	public void setNm2(final java.math.BigDecimal nm2) {
		this.nm2 = nm2;
	}

	/**
	 * nm3取得.
	 * @return nm3
	 */
	public java.math.BigDecimal getNm3() {
		return this.nm3;
	}

	/**
	 * nm3設定.
	 * @param nm3 nm3
	 */
	public void setNm3(final java.math.BigDecimal nm3) {
		this.nm3 = nm3;
	}

	/**
	 * nm4取得.
	 * @return nm4
	 */
	public java.math.BigDecimal getNm4() {
		return this.nm4;
	}

	/**
	 * nm4設定.
	 * @param nm4 nm4
	 */
	public void setNm4(final java.math.BigDecimal nm4) {
		this.nm4 = nm4;
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
