/*
 * Created on 2007/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemconstraintlist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ItemConstraintListクラス.
 * @author t1344224
 */
public class ItemConstraintListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemConstraintListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション permission */
	public static final String permission_COLUMN = "PERMISSION";

	/** COLUMNアノテーション permissionDate */
	public static final String permissionDate_COLUMN = "PERMISSION_DATE";

	/** COLUMNアノテーション constraintCd */
	public static final String constraintCd_COLUMN = "CONSTRAINT_CD";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション prohibition */
	public static final String prohibition_COLUMN = "PROHIBITION";

	/** COLUMNアノテーション fromDate */
	public static final String fromDate_COLUMN = "FROM_DATE";

	/** COLUMNアノテーション toDate */
	public static final String toDate_COLUMN = "TO_DATE";

	/** COLUMNアノテーション reason */
	public static final String reason_COLUMN = "REASON";

	/** COLUMNアノテーション checkDate */
	public static final String checkDate_COLUMN = "CHECK_DATE";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	//製造移管
	private BigDecimal permission;

	//製造移管開始日
	private Timestamp permissionDate;

	private String constraintCd;

	private java.math.BigDecimal rowNo;

	private java.math.BigDecimal prohibition;

	private java.sql.Timestamp fromDate;

	private java.sql.Timestamp toDate;

	private String reason;

	private java.sql.Timestamp checkDate;

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
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * permissionを取得します。
	 * @return permission
	 */
	public BigDecimal getPermission() {
		return permission;
	}

	/**
	 * permissionを設定します。
	 * @param permission permission
	 */
	public void setPermission(final BigDecimal permission) {
		this.permission = permission;
	}

	/**
	 * permissionDateを取得します。
	 * @return permissionDate
	 */
	public Timestamp getPermissionDate() {
		return permissionDate;
	}

	/**
	 * permissionDateを設定します。
	 * @param permissionDate permissionDate
	 */
	public void setPermissionDate(final Timestamp permissionDate) {
		this.permissionDate = permissionDate;
	}


	/**
	 * constraintCd取得.
	 * @return constraintCd
	 */
	public String getConstraintCd() {
		return this.constraintCd;
	}

	/**
	 * constraintCd設定.
	 * @param constraintCd constraintCd
	 */
	public void setConstraintCd(final String constraintCd) {
		this.constraintCd = constraintCd;
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
	 * prohibition取得.
	 * @return prohibition
	 */
	public java.math.BigDecimal getProhibition() {
		return this.prohibition;
	}

	/**
	 * prohibition設定.
	 * @param prohibition prohibition
	 */
	public void setProhibition(final java.math.BigDecimal prohibition) {
		this.prohibition = prohibition;
	}

	/**
	 * fromDate取得.
	 * @return fromDate
	 */
	public java.sql.Timestamp getFromDate() {
		return this.fromDate;
	}

	/**
	 * fromDate設定.
	 * @param fromDate fromDate
	 */
	public void setFromDate(final java.sql.Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * toDate取得.
	 * @return toDate
	 */
	public java.sql.Timestamp getToDate() {
		return this.toDate;
	}

	/**
	 * toDate設定.
	 * @param toDate toDate
	 */
	public void setToDate(final java.sql.Timestamp toDate) {
		this.toDate = toDate;
	}

	/**
	 * reason取得.
	 * @return reason
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * reason設定.
	 * @param reason reason
	 */
	public void setReason(final String reason) {
		this.reason = reason;
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
	 * checkDate取得.
	 * @return checkDate
	 */
	public java.sql.Timestamp getCheckDate() {
		return checkDate;
	}

	/**
	 * checkDate設定.
	 * @param checkDate checkDate
	 */
	public void setCheckDate(final java.sql.Timestamp checkDate) {
		this.checkDate = checkDate;
	}
}
