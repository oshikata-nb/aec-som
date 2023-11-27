/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Tue Mar 10 11:47:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.salestermsandestimate;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SalestermsAndEstimateBaseクラス.
 * @author t0011036
 */
public class SalestermsAndEstimateBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SALESTERMS_AND_ESTIMATE_CTRL";

	/** COLUMNアノテーション pkNo. */
	public static final String pkNo_COLUMN = "PK_NO";

	/** COLUMNアノテーション status. */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション processDivision. */
	public static final String processDivision_COLUMN = "PROCESS_DIVISION";

	/** COLUMNアノテーション itemCdFrom. */
	public static final String itemCdFrom_COLUMN = "ITEM_CD_FROM";

	/** COLUMNアノテーション itemCdTo. */
	public static final String itemCdTo_COLUMN = "ITEM_CD_TO";

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

	private String pkNo;
	
	private String status;
	
	private String processDivision;
	
	private String itemCdFrom;
		
	private String itemCdTo;
	
	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

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

	/**
	 * pkNoを取得します。
	 * @return pkNo
	 */
	public String getPkNo() {
		return pkNo;
	}

	/**
	 * pkNoを設定します。
	 * @param pkNo pkNo
	 */
	public void setPkNo(final String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * statusを取得します。
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * processDivisionを取得します。
	 * @return processDivision
	 */
	public String getProcessDivision() {
		return processDivision;
	}

	/**
	 * processDivisionを設定します。
	 * @param processDivision processDivision
	 */
	public void setProcessDivision(final String processDivision) {
		this.processDivision = processDivision;
	}

	/**
	 * itemCdFromを取得します。
	 * @return itemCdFrom
	 */
	public String getItemCdFrom() {
		return itemCdFrom;
	}

	/**
	 * itemCdFromを設定します。
	 * @param itemCdFrom itemCdFrom
	 */
	public void setItemCdFrom(final String itemCdFrom) {
		this.itemCdFrom = itemCdFrom;
	}

	/**
	 * itemCdToを取得します。
	 * @return itemCdTo
	 */
	public String getItemCdTo() {
		return itemCdTo;
	}

	/**
	 * itemCdToを設定します。
	 * @param itemCdTo itemCdTo
	 */
	public void setItemCdTo(final String itemCdTo) {
		this.itemCdTo = itemCdTo;
	}
}
