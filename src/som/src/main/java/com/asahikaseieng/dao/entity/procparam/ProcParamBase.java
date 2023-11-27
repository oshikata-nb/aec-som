/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Mon Mar 03 10:10:51 JST 2014
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.procparam;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ProcParamBaseクラス.
 * @author atts
 */
public class ProcParamBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProcParamBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "PROC_PARAM";

	// /** IDアノテーション procCd. */
	// public static final String procCd_ID = "assigned";

	/** COLUMNアノテーション procCd. */
	public static final String procCd_COLUMN = "PROC_CD";

	/** COLUMNアノテーション param1. */
	public static final String param1_COLUMN = "PARAM1";

	/** COLUMNアノテーション param2. */
	public static final String param2_COLUMN = "PARAM2";

	/** COLUMNアノテーション param3. */
	public static final String param3_COLUMN = "PARAM3";

	/** COLUMNアノテーション param4. */
	public static final String param4_COLUMN = "PARAM4";

	/** COLUMNアノテーション param5. */
	public static final String param5_COLUMN = "PARAM5";

	/** COLUMNアノテーション param6. */
	public static final String param6_COLUMN = "PARAM6";

	/** COLUMNアノテーション param7. */
	public static final String param7_COLUMN = "PARAM7";

	/** COLUMNアノテーション param8. */
	public static final String param8_COLUMN = "PARAM8";

	/** COLUMNアノテーション param9. */
	public static final String param9_COLUMN = "PARAM9";

	/** COLUMNアノテーション param10. */
	public static final String param10_COLUMN = "PARAM10";

	/** COLUMNアノテーション param11. */
	public static final String param11_COLUMN = "PARAM11";

	/** COLUMNアノテーション param12. */
	public static final String param12_COLUMN = "PARAM12";

	/** COLUMNアノテーション param13. */
	public static final String param13_COLUMN = "PARAM13";

	/** COLUMNアノテーション param14. */
	public static final String param14_COLUMN = "PARAM14";

	/** COLUMNアノテーション param15. */
	public static final String param15_COLUMN = "PARAM15";

	/** COLUMNアノテーション param16. */
	public static final String param16_COLUMN = "PARAM16";

	/** COLUMNアノテーション param17. */
	public static final String param17_COLUMN = "PARAM17";

	/** COLUMNアノテーション param18. */
	public static final String param18_COLUMN = "PARAM18";

	/** COLUMNアノテーション param19. */
	public static final String param19_COLUMN = "PARAM19";

	/** COLUMNアノテーション param20. */
	public static final String param20_COLUMN = "PARAM20";

	/** COLUMNアノテーション checkFlg. */
	public static final String checkFlg_COLUMN = "CHECK_FLG";

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

	private String procCd;

	private String param1;

	private String param2;

	private String param3;

	private String param4;

	private String param5;

	private String param6;

	private String param7;

	private String param8;

	private String param9;

	private String param10;

	private String param11;

	private String param12;

	private String param13;

	private String param14;

	private String param15;

	private String param16;

	private String param17;

	private String param18;

	private String param19;

	private String param20;

	private java.math.BigDecimal checkFlg;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * procCd取得.
	 * @return procCd
	 */
	public String getProcCd() {
		return this.procCd;
	}

	/**
	 * procCd設定.
	 * @param procCd procCd
	 */
	public void setProcCd(final String procCd) {
		this.procCd = procCd;
	}

	/**
	 * param1取得.
	 * @return param1
	 */
	public String getParam1() {
		return this.param1;
	}

	/**
	 * param1設定.
	 * @param param1 param1
	 */
	public void setParam1(final String param1) {
		this.param1 = param1;
	}

	/**
	 * param2取得.
	 * @return param2
	 */
	public String getParam2() {
		return this.param2;
	}

	/**
	 * param2設定.
	 * @param param2 param2
	 */
	public void setParam2(final String param2) {
		this.param2 = param2;
	}

	/**
	 * param3取得.
	 * @return param3
	 */
	public String getParam3() {
		return this.param3;
	}

	/**
	 * param3設定.
	 * @param param3 param3
	 */
	public void setParam3(final String param3) {
		this.param3 = param3;
	}

	/**
	 * param4取得.
	 * @return param4
	 */
	public String getParam4() {
		return this.param4;
	}

	/**
	 * param4設定.
	 * @param param4 param4
	 */
	public void setParam4(final String param4) {
		this.param4 = param4;
	}

	/**
	 * param5取得.
	 * @return param5
	 */
	public String getParam5() {
		return this.param5;
	}

	/**
	 * param5設定.
	 * @param param5 param5
	 */
	public void setParam5(final String param5) {
		this.param5 = param5;
	}

	/**
	 * param6取得.
	 * @return param6
	 */
	public String getParam6() {
		return this.param6;
	}

	/**
	 * param6設定.
	 * @param param6 param6
	 */
	public void setParam6(final String param6) {
		this.param6 = param6;
	}

	/**
	 * param7取得.
	 * @return param7
	 */
	public String getParam7() {
		return this.param7;
	}

	/**
	 * param7設定.
	 * @param param7 param7
	 */
	public void setParam7(final String param7) {
		this.param7 = param7;
	}

	/**
	 * param8取得.
	 * @return param8
	 */
	public String getParam8() {
		return this.param8;
	}

	/**
	 * param8設定.
	 * @param param8 param8
	 */
	public void setParam8(final String param8) {
		this.param8 = param8;
	}

	/**
	 * param9取得.
	 * @return param9
	 */
	public String getParam9() {
		return this.param9;
	}

	/**
	 * param9設定.
	 * @param param9 param9
	 */
	public void setParam9(final String param9) {
		this.param9 = param9;
	}

	/**
	 * param10取得.
	 * @return param10
	 */
	public String getParam10() {
		return this.param10;
	}

	/**
	 * param10設定.
	 * @param param10 param10
	 */
	public void setParam10(final String param10) {
		this.param10 = param10;
	}

	/**
	 * param11取得.
	 * @return param11
	 */
	public String getParam11() {
		return this.param11;
	}

	/**
	 * param11設定.
	 * @param param11 param11
	 */
	public void setParam11(final String param11) {
		this.param11 = param11;
	}

	/**
	 * param12取得.
	 * @return param12
	 */
	public String getParam12() {
		return this.param12;
	}

	/**
	 * param12設定.
	 * @param param12 param12
	 */
	public void setParam12(final String param12) {
		this.param12 = param12;
	}

	/**
	 * param13取得.
	 * @return param13
	 */
	public String getParam13() {
		return this.param13;
	}

	/**
	 * param13設定.
	 * @param param13 param13
	 */
	public void setParam13(final String param13) {
		this.param13 = param13;
	}

	/**
	 * param14取得.
	 * @return param14
	 */
	public String getParam14() {
		return this.param14;
	}

	/**
	 * param14設定.
	 * @param param14 param14
	 */
	public void setParam14(final String param14) {
		this.param14 = param14;
	}

	/**
	 * param15取得.
	 * @return param15
	 */
	public String getParam15() {
		return this.param15;
	}

	/**
	 * param15設定.
	 * @param param15 param15
	 */
	public void setParam15(final String param15) {
		this.param15 = param15;
	}

	/**
	 * param16取得.
	 * @return param16
	 */
	public String getParam16() {
		return this.param16;
	}

	/**
	 * param16設定.
	 * @param param16 param16
	 */
	public void setParam16(final String param16) {
		this.param16 = param16;
	}

	/**
	 * param17取得.
	 * @return param17
	 */
	public String getParam17() {
		return this.param17;
	}

	/**
	 * param17設定.
	 * @param param17 param17
	 */
	public void setParam17(final String param17) {
		this.param17 = param17;
	}

	/**
	 * param18取得.
	 * @return param18
	 */
	public String getParam18() {
		return this.param18;
	}

	/**
	 * param18設定.
	 * @param param18 param18
	 */
	public void setParam18(final String param18) {
		this.param18 = param18;
	}

	/**
	 * param19取得.
	 * @return param19
	 */
	public String getParam19() {
		return this.param19;
	}

	/**
	 * param19設定.
	 * @param param19 param19
	 */
	public void setParam19(final String param19) {
		this.param19 = param19;
	}

	/**
	 * param20取得.
	 * @return param20
	 */
	public String getParam20() {
		return this.param20;
	}

	/**
	 * param20設定.
	 * @param param20 param20
	 */
	public void setParam20(final String param20) {
		this.param20 = param20;
	}

	/**
	 * checkFlg取得.
	 * @return checkFlg
	 */
	public java.math.BigDecimal getCheckFlg() {
		return this.checkFlg;
	}

	/**
	 * checkFlg設定.
	 * @param checkFlg checkFlg
	 */
	public void setCheckFlg(final java.math.BigDecimal checkFlg) {
		this.checkFlg = checkFlg;
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
