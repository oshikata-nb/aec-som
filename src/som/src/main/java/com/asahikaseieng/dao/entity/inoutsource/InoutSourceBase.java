/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Wed Jul 15 10:16:00 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutsource;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InoutSourceBaseクラス.
 * @author a7710658
 */
public class InoutSourceBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InoutSourceBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "INOUT_SOURCE";


//	/** IDアノテーション inoutSourceNo. */
//	public static final String inoutSourceNo_ID = "assigned";

	/** COLUMNアノテーション inoutSourceNo. */
	public static final String inoutSourceNo_COLUMN = "INOUT_SOURCE_NO";

	/** COLUMNアノテーション inoutDivision. */
	public static final String inoutDivision_COLUMN = "INOUT_DIVISION";

	/** COLUMNアノテーション oderNo. */
	public static final String oderNo_COLUMN = "ODER_NO";

	/** COLUMNアノテーション oderLineNo. */
	public static final String oderLineNo_COLUMN = "ODER_LINE_NO";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション inoutQty. */
	public static final String inoutQty_COLUMN = "INOUT_QTY";

	/** COLUMNアノテーション inoutDate. */
	public static final String inoutDate_COLUMN = "INOUT_DATE";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション itemType. */
	public static final String itemType_COLUMN = "ITEM_TYPE";

	/** COLUMNアノテーション referenceNo. */
	public static final String referenceNo_COLUMN = "REFERENCE_NO";

	/** COLUMNアノテーション referenceLineNo. */
	public static final String referenceLineNo_COLUMN = "REFERENCE_LINE_NO";

	/** COLUMNアノテーション assignFlag. */
	public static final String assignFlag_COLUMN = "ASSIGN_FLAG";

	/** COLUMNアノテーション overFlg. */
	public static final String overFlg_COLUMN = "OVER_FLG";

	//
	// インスタンスフィールド
	//

	private String inoutSourceNo;

	private java.math.BigDecimal inoutDivision;

	private String oderNo;

	private java.math.BigDecimal oderLineNo;

	private String itemCd;

	private String locationCd;

	private String lotNo;

	private java.math.BigDecimal inoutQty;

	private java.sql.Timestamp inoutDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private java.math.BigDecimal itemType;

	private String referenceNo;

	private java.math.BigDecimal referenceLineNo;

	private java.math.BigDecimal assignFlag;

	private java.math.BigDecimal overFlg;

	//
	// インスタンスメソッド
	//

	/**
	 * inoutSourceNo取得.
	 * @return inoutSourceNo
	 */
	public String getInoutSourceNo() {
		return this.inoutSourceNo;
	}

	/**
	 * inoutSourceNo設定.
	 * @param inoutSourceNo inoutSourceNo
	 */
	public void setInoutSourceNo(final String inoutSourceNo) {
		this.inoutSourceNo = inoutSourceNo;
	}

	/**
	 * inoutDivision取得.
	 * @return inoutDivision
	 */
	public java.math.BigDecimal getInoutDivision() {
		return this.inoutDivision;
	}

	/**
	 * inoutDivision設定.
	 * @param inoutDivision inoutDivision
	 */
	public void setInoutDivision(final java.math.BigDecimal inoutDivision) {
		this.inoutDivision = inoutDivision;
	}

	/**
	 * oderNo取得.
	 * @return oderNo
	 */
	public String getOderNo() {
		return this.oderNo;
	}

	/**
	 * oderNo設定.
	 * @param oderNo oderNo
	 */
	public void setOderNo(final String oderNo) {
		this.oderNo = oderNo;
	}

	/**
	 * oderLineNo取得.
	 * @return oderLineNo
	 */
	public java.math.BigDecimal getOderLineNo() {
		return this.oderLineNo;
	}

	/**
	 * oderLineNo設定.
	 * @param oderLineNo oderLineNo
	 */
	public void setOderLineNo(final java.math.BigDecimal oderLineNo) {
		this.oderLineNo = oderLineNo;
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
	 * lotNo取得.
	 * @return lotNo
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * lotNo設定.
	 * @param lotNo lotNo
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * inoutQty取得.
	 * @return inoutQty
	 */
	public java.math.BigDecimal getInoutQty() {
		return this.inoutQty;
	}

	/**
	 * inoutQty設定.
	 * @param inoutQty inoutQty
	 */
	public void setInoutQty(final java.math.BigDecimal inoutQty) {
		this.inoutQty = inoutQty;
	}

	/**
	 * inoutDate取得.
	 * @return inoutDate
	 */
	public java.sql.Timestamp getInoutDate() {
		return this.inoutDate;
	}

	/**
	 * inoutDate設定.
	 * @param inoutDate inoutDate
	 */
	public void setInoutDate(final java.sql.Timestamp inoutDate) {
		this.inoutDate = inoutDate;
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
	 * itemType取得.
	 * @return itemType
	 */
	public java.math.BigDecimal getItemType() {
		return this.itemType;
	}

	/**
	 * itemType設定.
	 * @param itemType itemType
	 */
	public void setItemType(final java.math.BigDecimal itemType) {
		this.itemType = itemType;
	}

	/**
	 * referenceNo取得.
	 * @return referenceNo
	 */
	public String getReferenceNo() {
		return this.referenceNo;
	}

	/**
	 * referenceNo設定.
	 * @param referenceNo referenceNo
	 */
	public void setReferenceNo(final String referenceNo) {
		this.referenceNo = referenceNo;
	}

	/**
	 * referenceLineNo取得.
	 * @return referenceLineNo
	 */
	public java.math.BigDecimal getReferenceLineNo() {
		return this.referenceLineNo;
	}

	/**
	 * referenceLineNo設定.
	 * @param referenceLineNo referenceLineNo
	 */
	public void setReferenceLineNo(final java.math.BigDecimal referenceLineNo) {
		this.referenceLineNo = referenceLineNo;
	}

	/**
	 * assignFlag取得.
	 * @return assignFlag
	 */
	public java.math.BigDecimal getAssignFlag() {
		return this.assignFlag;
	}

	/**
	 * assignFlag設定.
	 * @param assignFlag assignFlag
	 */
	public void setAssignFlag(final java.math.BigDecimal assignFlag) {
		this.assignFlag = assignFlag;
	}

	/**
	 * overFlg取得.
	 * @return overFlg
	 */
	public java.math.BigDecimal getOverFlg() {
		return this.overFlg;
	}

	/**
	 * overFlg設定.
	 * @param overFlg overFlg
	 */
	public void setOverFlg(final java.math.BigDecimal overFlg) {
		this.overFlg = overFlg;
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
