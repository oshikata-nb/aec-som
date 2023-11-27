/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repproductionplanforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RepProductionPlanForReportクラス.
 * @author kanri-user
 */
public class RepProductionPlanForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepProductionPlanForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション pKey */
	public static final String pKey_COLUMN = "P_KEY";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション numberOfInsertions */
	public static final String numberOfInsertions_COLUMN = "NUMBER_OF_INSERTIONS";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション packageLine */
	public static final String packageLine_COLUMN = "PACKAGE_LINE";

	/** COLUMNアノテーション hizuke */
	public static final String hizuke_COLUMN = "HIZUKE";

	/** COLUMNアノテーション plan01 */
	public static final String plan01_COLUMN = "PLAN01";

	/** COLUMNアノテーション plan02 */
	public static final String plan02_COLUMN = "PLAN02";

	/** COLUMNアノテーション plan03 */
	public static final String plan03_COLUMN = "PLAN03";

	/** COLUMNアノテーション plan04 */
	public static final String plan04_COLUMN = "PLAN04";

	/** COLUMNアノテーション plan05 */
	public static final String plan05_COLUMN = "PLAN05";

	/** COLUMNアノテーション plan06 */
	public static final String plan06_COLUMN = "PLAN06";

	/** COLUMNアノテーション plan07 */
	public static final String plan07_COLUMN = "PLAN07";

	/** COLUMNアノテーション plan08 */
	public static final String plan08_COLUMN = "PLAN08";

	/** COLUMNアノテーション plan09 */
	public static final String plan09_COLUMN = "PLAN09";

	/** COLUMNアノテーション plan10 */
	public static final String plan10_COLUMN = "PLAN10";

	/** COLUMNアノテーション plan11 */
	public static final String plan11_COLUMN = "PLAN11";

	/** COLUMNアノテーション plan12 */
	public static final String plan12_COLUMN = "PLAN12";

	/** COLUMNアノテーション plan13 */
	public static final String plan13_COLUMN = "PLAN13";

	/** COLUMNアノテーション plan14 */
	public static final String plan14_COLUMN = "PLAN14";

	/** COLUMNアノテーション plan15 */
	public static final String plan15_COLUMN = "PLAN15";

	/** COLUMNアノテーション plan16 */
	public static final String plan16_COLUMN = "PLAN16";

	/** COLUMNアノテーション plan17 */
	public static final String plan17_COLUMN = "PLAN17";

	/** COLUMNアノテーション plan18 */
	public static final String plan18_COLUMN = "PLAN18";

	/** COLUMNアノテーション plan19 */
	public static final String plan19_COLUMN = "PLAN19";

	/** COLUMNアノテーション plan20 */
	public static final String plan20_COLUMN = "PLAN20";

	/** COLUMNアノテーション plan21 */
	public static final String plan21_COLUMN = "PLAN21";

	/** COLUMNアノテーション plan22 */
	public static final String plan22_COLUMN = "PLAN22";

	/** COLUMNアノテーション plan23 */
	public static final String plan23_COLUMN = "PLAN23";

	/** COLUMNアノテーション plan24 */
	public static final String plan24_COLUMN = "PLAN24";

	/** COLUMNアノテーション plan25 */
	public static final String plan25_COLUMN = "PLAN25";

	/** COLUMNアノテーション plan26 */
	public static final String plan26_COLUMN = "PLAN26";

	/** COLUMNアノテーション plan27 */
	public static final String plan27_COLUMN = "PLAN27";

	/** COLUMNアノテーション plan28 */
	public static final String plan28_COLUMN = "PLAN28";

	/** COLUMNアノテーション plan29 */
	public static final String plan29_COLUMN = "PLAN29";

	/** COLUMNアノテーション plan30 */
	public static final String plan30_COLUMN = "PLAN30";

	/** COLUMNアノテーション plan31 */
	public static final String plan31_COLUMN = "PLAN31";

	/** COLUMNアノテーション reslt01 */
	public static final String reslt01_COLUMN = "RESLT01";

	/** COLUMNアノテーション reslt02 */
	public static final String reslt02_COLUMN = "RESLT02";

	/** COLUMNアノテーション reslt03 */
	public static final String reslt03_COLUMN = "RESLT03";

	/** COLUMNアノテーション reslt04 */
	public static final String reslt04_COLUMN = "RESLT04";

	/** COLUMNアノテーション reslt05 */
	public static final String reslt05_COLUMN = "RESLT05";

	/** COLUMNアノテーション reslt06 */
	public static final String reslt06_COLUMN = "RESLT06";

	/** COLUMNアノテーション reslt07 */
	public static final String reslt07_COLUMN = "RESLT07";

	/** COLUMNアノテーション reslt08 */
	public static final String reslt08_COLUMN = "RESLT08";

	/** COLUMNアノテーション reslt09 */
	public static final String reslt09_COLUMN = "RESLT09";

	/** COLUMNアノテーション reslt10 */
	public static final String reslt10_COLUMN = "RESLT10";

	/** COLUMNアノテーション reslt11 */
	public static final String reslt11_COLUMN = "RESLT11";

	/** COLUMNアノテーション reslt12 */
	public static final String reslt12_COLUMN = "RESLT12";

	/** COLUMNアノテーション reslt13 */
	public static final String reslt13_COLUMN = "RESLT13";

	/** COLUMNアノテーション reslt14 */
	public static final String reslt14_COLUMN = "RESLT14";

	/** COLUMNアノテーション reslt15 */
	public static final String reslt15_COLUMN = "RESLT15";

	/** COLUMNアノテーション reslt16 */
	public static final String reslt16_COLUMN = "RESLT16";

	/** COLUMNアノテーション reslt17 */
	public static final String reslt17_COLUMN = "RESLT17";

	/** COLUMNアノテーション reslt18 */
	public static final String reslt18_COLUMN = "RESLT18";

	/** COLUMNアノテーション reslt19 */
	public static final String reslt19_COLUMN = "RESLT19";

	/** COLUMNアノテーション reslt20 */
	public static final String reslt20_COLUMN = "RESLT20";

	/** COLUMNアノテーション reslt21 */
	public static final String reslt21_COLUMN = "RESLT21";

	/** COLUMNアノテーション reslt22 */
	public static final String reslt22_COLUMN = "RESLT22";

	/** COLUMNアノテーション reslt23 */
	public static final String reslt23_COLUMN = "RESLT23";

	/** COLUMNアノテーション reslt24 */
	public static final String reslt24_COLUMN = "RESLT24";

	/** COLUMNアノテーション reslt25 */
	public static final String reslt25_COLUMN = "RESLT25";

	/** COLUMNアノテーション reslt26 */
	public static final String reslt26_COLUMN = "RESLT26";

	/** COLUMNアノテーション reslt27 */
	public static final String reslt27_COLUMN = "RESLT27";

	/** COLUMNアノテーション reslt28 */
	public static final String reslt28_COLUMN = "RESLT28";

	/** COLUMNアノテーション reslt29 */
	public static final String reslt29_COLUMN = "RESLT29";

	/** COLUMNアノテーション reslt30 */
	public static final String reslt30_COLUMN = "RESLT30";

	/** COLUMNアノテーション reslt31 */
	public static final String reslt31_COLUMN = "RESLT31";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private String pKey;

	private String itemName;

	private String otherCompanyCd1;

	private String styleOfPacking;

	private java.math.BigDecimal shipperDivision;

	private java.math.BigDecimal typeDivision;

	private java.math.BigDecimal kgOfFractionManagement;

	private java.math.BigDecimal numberOfInsertions;

	private String productionLine;

	private String productionLineName;

	private String packageLine;

	private String hizuke;

	private java.math.BigDecimal plan01;

	private java.math.BigDecimal plan02;

	private java.math.BigDecimal plan03;

	private java.math.BigDecimal plan04;

	private java.math.BigDecimal plan05;

	private java.math.BigDecimal plan06;

	private java.math.BigDecimal plan07;

	private java.math.BigDecimal plan08;

	private java.math.BigDecimal plan09;

	private java.math.BigDecimal plan10;

	private java.math.BigDecimal plan11;

	private java.math.BigDecimal plan12;

	private java.math.BigDecimal plan13;

	private java.math.BigDecimal plan14;

	private java.math.BigDecimal plan15;

	private java.math.BigDecimal plan16;

	private java.math.BigDecimal plan17;

	private java.math.BigDecimal plan18;

	private java.math.BigDecimal plan19;

	private java.math.BigDecimal plan20;

	private java.math.BigDecimal plan21;

	private java.math.BigDecimal plan22;

	private java.math.BigDecimal plan23;

	private java.math.BigDecimal plan24;

	private java.math.BigDecimal plan25;

	private java.math.BigDecimal plan26;

	private java.math.BigDecimal plan27;

	private java.math.BigDecimal plan28;

	private java.math.BigDecimal plan29;

	private java.math.BigDecimal plan30;

	private java.math.BigDecimal plan31;

	private java.math.BigDecimal reslt01;

	private java.math.BigDecimal reslt02;

	private java.math.BigDecimal reslt03;

	private java.math.BigDecimal reslt04;

	private java.math.BigDecimal reslt05;

	private java.math.BigDecimal reslt06;

	private java.math.BigDecimal reslt07;

	private java.math.BigDecimal reslt08;

	private java.math.BigDecimal reslt09;

	private java.math.BigDecimal reslt10;

	private java.math.BigDecimal reslt11;

	private java.math.BigDecimal reslt12;

	private java.math.BigDecimal reslt13;

	private java.math.BigDecimal reslt14;

	private java.math.BigDecimal reslt15;

	private java.math.BigDecimal reslt16;

	private java.math.BigDecimal reslt17;

	private java.math.BigDecimal reslt18;

	private java.math.BigDecimal reslt19;

	private java.math.BigDecimal reslt20;

	private java.math.BigDecimal reslt21;

	private java.math.BigDecimal reslt22;

	private java.math.BigDecimal reslt23;

	private java.math.BigDecimal reslt24;

	private java.math.BigDecimal reslt25;

	private java.math.BigDecimal reslt26;

	private java.math.BigDecimal reslt27;

	private java.math.BigDecimal reslt28;

	private java.math.BigDecimal reslt29;

	private java.math.BigDecimal reslt30;

	private java.math.BigDecimal reslt31;

	//
	// インスタンスメソッド
	//

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
	 * pKey取得.
	 * @return pKey
	 */
	public String getPKey() {
		return this.pKey;
	}

	/**
	 * pKey設定.
	 * @param pKey pKey
	 */
	public void setPKey(final String pKey) {
		this.pKey = pKey;
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
	 * otherCompanyCd1取得.
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1設定.
	 * @param otherCompanyCd1 itemName
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * styleOfPacking取得.
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * styleOfPacking設定.
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * shipperDivision取得.
	 * @return shipperDivision
	 */
	public java.math.BigDecimal getShipperDivision() {
		return this.shipperDivision;
	}

	/**
	 * shipperDivision設定.
	 * @param shipperDivision shipperDivision
	 */
	public void setShipperDivision(final java.math.BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * typeDivision取得.
	 * @return typeDivision
	 */
	public java.math.BigDecimal getTypeDivision() {
		return this.typeDivision;
	}

	/**
	 * typeDivision設定.
	 * @param typeDivision typeDivision
	 */
	public void setTypeDivision(final java.math.BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
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
	public void setKgOfFractionManagement(
			final java.math.BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * numberOfInsertions取得.
	 * @return numberOfInsertions
	 */
	public java.math.BigDecimal getNumberOfInsertions() {
		return this.numberOfInsertions;
	}

	/**
	 * numberOfInsertions設定.
	 * @param numberOfInsertions numberOfInsertions
	 */
	public void setNumberOfInsertions(
			final java.math.BigDecimal numberOfInsertions) {
		this.numberOfInsertions = numberOfInsertions;
	}

	/**
	 * productionLine取得.
	 * @return productionLine
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * productionLine設定.
	 * @param productionLine productionLine
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * productionLineName取得.
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return this.productionLineName;
	}

	/**
	 * productionLineName設定.
	 * @param productionLineName productionLineName
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * hizuke取得.
	 * @return hizuke
	 */
	public String getHizuke() {
		return this.hizuke;
	}

	/**
	 * hizuke設定.
	 * @param hizuke hizuke
	 */
	public void setHizuke(final String hizuke) {
		this.hizuke = hizuke;
	}

	/**
	 * plan01取得.
	 * @return plan01
	 */
	public java.math.BigDecimal getPlan01() {
		return this.plan01;
	}

	/**
	 * plan01設定.
	 * @param plan01 plan01
	 */
	public void setPlan01(final java.math.BigDecimal plan01) {
		this.plan01 = plan01;
	}

	/**
	 * plan02取得.
	 * @return plan02
	 */
	public java.math.BigDecimal getPlan02() {
		return this.plan02;
	}

	/**
	 * plan02設定.
	 * @param plan02 plan02
	 */
	public void setPlan02(final java.math.BigDecimal plan02) {
		this.plan02 = plan02;
	}

	/**
	 * plan03取得.
	 * @return plan03
	 */
	public java.math.BigDecimal getPlan03() {
		return this.plan03;
	}

	/**
	 * plan03設定.
	 * @param plan03 plan03
	 */
	public void setPlan03(final java.math.BigDecimal plan03) {
		this.plan03 = plan03;
	}

	/**
	 * plan04取得.
	 * @return plan04
	 */
	public java.math.BigDecimal getPlan04() {
		return this.plan04;
	}

	/**
	 * plan04設定.
	 * @param plan04 plan04
	 */
	public void setPlan04(final java.math.BigDecimal plan04) {
		this.plan04 = plan04;
	}

	/**
	 * plan05取得.
	 * @return plan05
	 */
	public java.math.BigDecimal getPlan05() {
		return this.plan05;
	}

	/**
	 * plan05設定.
	 * @param plan05 plan05
	 */
	public void setPlan05(final java.math.BigDecimal plan05) {
		this.plan05 = plan05;
	}

	/**
	 * plan06取得.
	 * @return plan06
	 */
	public java.math.BigDecimal getPlan06() {
		return this.plan06;
	}

	/**
	 * plan06設定.
	 * @param plan06 plan06
	 */
	public void setPlan06(final java.math.BigDecimal plan06) {
		this.plan06 = plan06;
	}

	/**
	 * plan07取得.
	 * @return plan07
	 */
	public java.math.BigDecimal getPlan07() {
		return this.plan07;
	}

	/**
	 * plan07設定.
	 * @param plan07 plan07
	 */
	public void setPlan07(final java.math.BigDecimal plan07) {
		this.plan07 = plan07;
	}

	/**
	 * plan08取得.
	 * @return plan08
	 */
	public java.math.BigDecimal getPlan08() {
		return this.plan08;
	}

	/**
	 * plan08設定.
	 * @param plan08 plan08
	 */
	public void setPlan08(final java.math.BigDecimal plan08) {
		this.plan08 = plan08;
	}

	/**
	 * plan09取得.
	 * @return plan09
	 */
	public java.math.BigDecimal getPlan09() {
		return this.plan09;
	}

	/**
	 * plan09設定.
	 * @param plan09 plan09
	 */
	public void setPlan09(final java.math.BigDecimal plan09) {
		this.plan09 = plan09;
	}

	/**
	 * plan10取得.
	 * @return plan10
	 */
	public java.math.BigDecimal getPlan10() {
		return this.plan10;
	}

	/**
	 * plan10設定.
	 * @param plan10 plan10
	 */
	public void setPlan10(final java.math.BigDecimal plan10) {
		this.plan10 = plan10;
	}

	/**
	 * plan11取得.
	 * @return plan11
	 */
	public java.math.BigDecimal getPlan11() {
		return this.plan11;
	}

	/**
	 * plan11設定.
	 * @param plan11 plan11
	 */
	public void setPlan11(final java.math.BigDecimal plan11) {
		this.plan11 = plan11;
	}

	/**
	 * plan12取得.
	 * @return plan12
	 */
	public java.math.BigDecimal getPlan12() {
		return this.plan12;
	}

	/**
	 * plan12設定.
	 * @param plan12 plan12
	 */
	public void setPlan12(final java.math.BigDecimal plan12) {
		this.plan12 = plan12;
	}

	/**
	 * plan13取得.
	 * @return plan13
	 */
	public java.math.BigDecimal getPlan13() {
		return this.plan13;
	}

	/**
	 * plan13設定.
	 * @param plan13 plan13
	 */
	public void setPlan13(final java.math.BigDecimal plan13) {
		this.plan13 = plan13;
	}

	/**
	 * plan14取得.
	 * @return plan14
	 */
	public java.math.BigDecimal getPlan14() {
		return this.plan14;
	}

	/**
	 * plan14設定.
	 * @param plan14 plan14
	 */
	public void setPlan14(final java.math.BigDecimal plan14) {
		this.plan14 = plan14;
	}

	/**
	 * plan15取得.
	 * @return plan15
	 */
	public java.math.BigDecimal getPlan15() {
		return this.plan15;
	}

	/**
	 * plan15設定.
	 * @param plan15 plan15
	 */
	public void setPlan15(final java.math.BigDecimal plan15) {
		this.plan15 = plan15;
	}

	/**
	 * plan16取得.
	 * @return plan16
	 */
	public java.math.BigDecimal getPlan16() {
		return this.plan16;
	}

	/**
	 * plan16設定.
	 * @param plan16 plan16
	 */
	public void setPlan16(final java.math.BigDecimal plan16) {
		this.plan16 = plan16;
	}

	/**
	 * plan17取得.
	 * @return plan17
	 */
	public java.math.BigDecimal getPlan17() {
		return this.plan17;
	}

	/**
	 * plan17設定.
	 * @param plan17 plan17
	 */
	public void setPlan17(final java.math.BigDecimal plan17) {
		this.plan17 = plan17;
	}

	/**
	 * plan18取得.
	 * @return plan18
	 */
	public java.math.BigDecimal getPlan18() {
		return this.plan18;
	}

	/**
	 * plan18設定.
	 * @param plan18 plan18
	 */
	public void setPlan18(final java.math.BigDecimal plan18) {
		this.plan18 = plan18;
	}

	/**
	 * plan19取得.
	 * @return plan19
	 */
	public java.math.BigDecimal getPlan19() {
		return this.plan19;
	}

	/**
	 * plan19設定.
	 * @param plan19 plan19
	 */
	public void setPlan19(final java.math.BigDecimal plan19) {
		this.plan19 = plan19;
	}

	/**
	 * plan20取得.
	 * @return plan20
	 */
	public java.math.BigDecimal getPlan20() {
		return this.plan20;
	}

	/**
	 * plan20設定.
	 * @param plan20 plan20
	 */
	public void setPlan20(final java.math.BigDecimal plan20) {
		this.plan20 = plan20;
	}

	/**
	 * plan21取得.
	 * @return plan21
	 */
	public java.math.BigDecimal getPlan21() {
		return this.plan21;
	}

	/**
	 * plan21設定.
	 * @param plan21 plan21
	 */
	public void setPlan21(final java.math.BigDecimal plan21) {
		this.plan21 = plan21;
	}

	/**
	 * plan22取得.
	 * @return plan22
	 */
	public java.math.BigDecimal getPlan22() {
		return this.plan22;
	}

	/**
	 * plan22設定.
	 * @param plan22 plan22
	 */
	public void setPlan22(final java.math.BigDecimal plan22) {
		this.plan22 = plan22;
	}

	/**
	 * plan23取得.
	 * @return plan23
	 */
	public java.math.BigDecimal getPlan23() {
		return this.plan23;
	}

	/**
	 * plan23設定.
	 * @param plan23 plan23
	 */
	public void setPlan23(final java.math.BigDecimal plan23) {
		this.plan23 = plan23;
	}

	/**
	 * plan24取得.
	 * @return plan24
	 */
	public java.math.BigDecimal getPlan24() {
		return this.plan24;
	}

	/**
	 * plan24設定.
	 * @param plan24 plan24
	 */
	public void setPlan24(final java.math.BigDecimal plan24) {
		this.plan24 = plan24;
	}

	/**
	 * plan25取得.
	 * @return plan25
	 */
	public java.math.BigDecimal getPlan25() {
		return this.plan25;
	}

	/**
	 * plan25設定.
	 * @param plan25 plan25
	 */
	public void setPlan25(final java.math.BigDecimal plan25) {
		this.plan25 = plan25;
	}

	/**
	 * plan26取得.
	 * @return plan26
	 */
	public java.math.BigDecimal getPlan26() {
		return this.plan26;
	}

	/**
	 * plan26設定.
	 * @param plan26 plan26
	 */
	public void setPlan26(final java.math.BigDecimal plan26) {
		this.plan26 = plan26;
	}

	/**
	 * plan27取得.
	 * @return plan27
	 */
	public java.math.BigDecimal getPlan27() {
		return this.plan27;
	}

	/**
	 * plan27設定.
	 * @param plan27 plan27
	 */
	public void setPlan27(final java.math.BigDecimal plan27) {
		this.plan27 = plan27;
	}

	/**
	 * plan28取得.
	 * @return plan28
	 */
	public java.math.BigDecimal getPlan28() {
		return this.plan28;
	}

	/**
	 * plan28設定.
	 * @param plan28 plan28
	 */
	public void setPlan28(final java.math.BigDecimal plan28) {
		this.plan28 = plan28;
	}

	/**
	 * plan29取得.
	 * @return plan29
	 */
	public java.math.BigDecimal getPlan29() {
		return this.plan29;
	}

	/**
	 * plan29設定.
	 * @param plan29 plan29
	 */
	public void setPlan29(final java.math.BigDecimal plan29) {
		this.plan29 = plan29;
	}

	/**
	 * plan30取得.
	 * @return plan30
	 */
	public java.math.BigDecimal getPlan30() {
		return this.plan30;
	}

	/**
	 * plan30設定.
	 * @param plan30 plan30
	 */
	public void setPlan30(final java.math.BigDecimal plan30) {
		this.plan30 = plan30;
	}

	/**
	 * plan31取得.
	 * @return plan31
	 */
	public java.math.BigDecimal getPlan31() {
		return this.plan31;
	}

	/**
	 * plan31設定.
	 * @param plan31 plan31
	 */
	public void setPlan31(final java.math.BigDecimal plan31) {
		this.plan31 = plan31;
	}

	/**
	 * reslt01取得.
	 * @return reslt01
	 */
	public java.math.BigDecimal getReslt01() {
		return this.reslt01;
	}

	/**
	 * reslt01設定.
	 * @param reslt01 reslt01
	 */
	public void setReslt01(final java.math.BigDecimal reslt01) {
		this.reslt01 = reslt01;
	}

	/**
	 * reslt02取得.
	 * @return reslt02
	 */
	public java.math.BigDecimal getReslt02() {
		return this.reslt02;
	}

	/**
	 * reslt02設定.
	 * @param reslt02 reslt02
	 */
	public void setReslt02(final java.math.BigDecimal reslt02) {
		this.reslt02 = reslt02;
	}

	/**
	 * reslt03取得.
	 * @return reslt03
	 */
	public java.math.BigDecimal getReslt03() {
		return this.reslt03;
	}

	/**
	 * reslt03設定.
	 * @param reslt03 reslt03
	 */
	public void setReslt03(final java.math.BigDecimal reslt03) {
		this.reslt03 = reslt03;
	}

	/**
	 * reslt04取得.
	 * @return reslt04
	 */
	public java.math.BigDecimal getReslt04() {
		return this.reslt04;
	}

	/**
	 * reslt04設定.
	 * @param reslt04 reslt04
	 */
	public void setReslt04(final java.math.BigDecimal reslt04) {
		this.reslt04 = reslt04;
	}

	/**
	 * reslt05取得.
	 * @return reslt05
	 */
	public java.math.BigDecimal getReslt05() {
		return this.reslt05;
	}

	/**
	 * reslt05設定.
	 * @param reslt05 reslt05
	 */
	public void setReslt05(final java.math.BigDecimal reslt05) {
		this.reslt05 = reslt05;
	}

	/**
	 * reslt06取得.
	 * @return reslt06
	 */
	public java.math.BigDecimal getReslt06() {
		return this.reslt06;
	}

	/**
	 * reslt06設定.
	 * @param reslt06 reslt06
	 */
	public void setReslt06(final java.math.BigDecimal reslt06) {
		this.reslt06 = reslt06;
	}

	/**
	 * reslt07取得.
	 * @return reslt07
	 */
	public java.math.BigDecimal getReslt07() {
		return this.reslt07;
	}

	/**
	 * reslt07設定.
	 * @param reslt07 reslt07
	 */
	public void setReslt07(final java.math.BigDecimal reslt07) {
		this.reslt07 = reslt07;
	}

	/**
	 * reslt08取得.
	 * @return reslt08
	 */
	public java.math.BigDecimal getReslt08() {
		return this.reslt08;
	}

	/**
	 * reslt08設定.
	 * @param reslt08 reslt08
	 */
	public void setReslt08(final java.math.BigDecimal reslt08) {
		this.reslt08 = reslt08;
	}

	/**
	 * reslt09取得.
	 * @return reslt09
	 */
	public java.math.BigDecimal getReslt09() {
		return this.reslt09;
	}

	/**
	 * reslt09設定.
	 * @param reslt09 reslt09
	 */
	public void setReslt09(final java.math.BigDecimal reslt09) {
		this.reslt09 = reslt09;
	}

	/**
	 * reslt10取得.
	 * @return reslt10
	 */
	public java.math.BigDecimal getReslt10() {
		return this.reslt10;
	}

	/**
	 * reslt10設定.
	 * @param reslt10 reslt10
	 */
	public void setReslt10(final java.math.BigDecimal reslt10) {
		this.reslt10 = reslt10;
	}

	/**
	 * reslt11取得.
	 * @return reslt11
	 */
	public java.math.BigDecimal getReslt11() {
		return this.reslt11;
	}

	/**
	 * reslt11設定.
	 * @param reslt11 reslt11
	 */
	public void setReslt11(final java.math.BigDecimal reslt11) {
		this.reslt11 = reslt11;
	}

	/**
	 * reslt12取得.
	 * @return reslt12
	 */
	public java.math.BigDecimal getReslt12() {
		return this.reslt12;
	}

	/**
	 * reslt12設定.
	 * @param reslt12 reslt12
	 */
	public void setReslt12(final java.math.BigDecimal reslt12) {
		this.reslt12 = reslt12;
	}

	/**
	 * reslt13取得.
	 * @return reslt13
	 */
	public java.math.BigDecimal getReslt13() {
		return this.reslt13;
	}

	/**
	 * reslt13設定.
	 * @param reslt13 reslt13
	 */
	public void setReslt13(final java.math.BigDecimal reslt13) {
		this.reslt13 = reslt13;
	}

	/**
	 * reslt14取得.
	 * @return reslt14
	 */
	public java.math.BigDecimal getReslt14() {
		return this.reslt14;
	}

	/**
	 * reslt14設定.
	 * @param reslt14 reslt14
	 */
	public void setReslt14(final java.math.BigDecimal reslt14) {
		this.reslt14 = reslt14;
	}

	/**
	 * reslt15取得.
	 * @return reslt15
	 */
	public java.math.BigDecimal getReslt15() {
		return this.reslt15;
	}

	/**
	 * reslt15設定.
	 * @param reslt15 reslt15
	 */
	public void setReslt15(final java.math.BigDecimal reslt15) {
		this.reslt15 = reslt15;
	}

	/**
	 * reslt16取得.
	 * @return reslt16
	 */
	public java.math.BigDecimal getReslt16() {
		return this.reslt16;
	}

	/**
	 * reslt16設定.
	 * @param reslt16 reslt16
	 */
	public void setReslt16(final java.math.BigDecimal reslt16) {
		this.reslt16 = reslt16;
	}

	/**
	 * reslt17取得.
	 * @return reslt17
	 */
	public java.math.BigDecimal getReslt17() {
		return this.reslt17;
	}

	/**
	 * reslt17設定.
	 * @param reslt17 reslt17
	 */
	public void setReslt17(final java.math.BigDecimal reslt17) {
		this.reslt17 = reslt17;
	}

	/**
	 * reslt18取得.
	 * @return reslt18
	 */
	public java.math.BigDecimal getReslt18() {
		return this.reslt18;
	}

	/**
	 * reslt18設定.
	 * @param reslt18 reslt18
	 */
	public void setReslt18(final java.math.BigDecimal reslt18) {
		this.reslt18 = reslt18;
	}

	/**
	 * reslt19取得.
	 * @return reslt19
	 */
	public java.math.BigDecimal getReslt19() {
		return this.reslt19;
	}

	/**
	 * reslt19設定.
	 * @param reslt19 reslt19
	 */
	public void setReslt19(final java.math.BigDecimal reslt19) {
		this.reslt19 = reslt19;
	}

	/**
	 * reslt20取得.
	 * @return reslt20
	 */
	public java.math.BigDecimal getReslt20() {
		return this.reslt20;
	}

	/**
	 * reslt20設定.
	 * @param reslt20 reslt20
	 */
	public void setReslt20(final java.math.BigDecimal reslt20) {
		this.reslt20 = reslt20;
	}

	/**
	 * reslt21取得.
	 * @return reslt21
	 */
	public java.math.BigDecimal getReslt21() {
		return this.reslt21;
	}

	/**
	 * reslt21設定.
	 * @param reslt21 reslt21
	 */
	public void setReslt21(final java.math.BigDecimal reslt21) {
		this.reslt21 = reslt21;
	}

	/**
	 * reslt22取得.
	 * @return reslt22
	 */
	public java.math.BigDecimal getReslt22() {
		return this.reslt22;
	}

	/**
	 * reslt22設定.
	 * @param reslt22 reslt22
	 */
	public void setReslt22(final java.math.BigDecimal reslt22) {
		this.reslt22 = reslt22;
	}

	/**
	 * reslt23取得.
	 * @return reslt23
	 */
	public java.math.BigDecimal getReslt23() {
		return this.reslt23;
	}

	/**
	 * reslt23設定.
	 * @param reslt23 reslt23
	 */
	public void setReslt23(final java.math.BigDecimal reslt23) {
		this.reslt23 = reslt23;
	}

	/**
	 * reslt24取得.
	 * @return reslt24
	 */
	public java.math.BigDecimal getReslt24() {
		return this.reslt24;
	}

	/**
	 * reslt24設定.
	 * @param reslt24 reslt24
	 */
	public void setReslt24(final java.math.BigDecimal reslt24) {
		this.reslt24 = reslt24;
	}

	/**
	 * reslt25取得.
	 * @return reslt25
	 */
	public java.math.BigDecimal getReslt25() {
		return this.reslt25;
	}

	/**
	 * reslt25設定.
	 * @param reslt25 reslt25
	 */
	public void setReslt25(final java.math.BigDecimal reslt25) {
		this.reslt25 = reslt25;
	}

	/**
	 * reslt26取得.
	 * @return reslt26
	 */
	public java.math.BigDecimal getReslt26() {
		return this.reslt26;
	}

	/**
	 * reslt26設定.
	 * @param reslt26 reslt26
	 */
	public void setReslt26(final java.math.BigDecimal reslt26) {
		this.reslt26 = reslt26;
	}

	/**
	 * reslt27取得.
	 * @return reslt27
	 */
	public java.math.BigDecimal getReslt27() {
		return this.reslt27;
	}

	/**
	 * reslt27設定.
	 * @param reslt27 reslt27
	 */
	public void setReslt27(final java.math.BigDecimal reslt27) {
		this.reslt27 = reslt27;
	}

	/**
	 * reslt28取得.
	 * @return reslt28
	 */
	public java.math.BigDecimal getReslt28() {
		return this.reslt28;
	}

	/**
	 * reslt28設定.
	 * @param reslt28 reslt28
	 */
	public void setReslt28(final java.math.BigDecimal reslt28) {
		this.reslt28 = reslt28;
	}

	/**
	 * reslt29取得.
	 * @return reslt29
	 */
	public java.math.BigDecimal getReslt29() {
		return this.reslt29;
	}

	/**
	 * reslt29設定.
	 * @param reslt29 reslt29
	 */
	public void setReslt29(final java.math.BigDecimal reslt29) {
		this.reslt29 = reslt29;
	}

	/**
	 * reslt30取得.
	 * @return reslt30
	 */
	public java.math.BigDecimal getReslt30() {
		return this.reslt30;
	}

	/**
	 * reslt30設定.
	 * @param reslt30 reslt30
	 */
	public void setReslt30(final java.math.BigDecimal reslt30) {
		this.reslt30 = reslt30;
	}

	/**
	 * reslt31取得.
	 * @return reslt31
	 */
	public java.math.BigDecimal getReslt31() {
		return this.reslt31;
	}

	/**
	 * reslt31設定.
	 * @param reslt31 reslt31
	 */
	public void setReslt31(final java.math.BigDecimal reslt31) {
		this.reslt31 = reslt31;
	}

	/**
	 * packageLine取得.
	 * @return packageLine
	 */
	public String getPackageLine() {
		return packageLine;
	}

	/**
	 * packageLine設定.
	 * @param packageLine packageLine
	 */
	public void setPackageLine(final String packageLine) {
		this.packageLine = packageLine;
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
