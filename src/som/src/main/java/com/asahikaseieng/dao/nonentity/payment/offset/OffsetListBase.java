/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * グループ間相殺入力検索画面用Daoクラス.
 * @author tosco
 */
public class OffsetListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OffsetListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション offsetNo */
	public static final String offsetNo_COLUMN = "OFFSET_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション offsetGroupCd */
	public static final String offsetGroupCd_COLUMN = "OFFSET_GROUP_CD";

	/** COLUMNアノテーション offsetGroupName */
	public static final String offsetGroupName_COLUMN = "OFFSET_GROUP_NAME";

	/** COLUMNアノテーション offsetDate */
	public static final String offsetDate_COLUMN = "OFFSET_DATE";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	//
	// インスタンスフィールド	//

	/** 相殺番号 */	private String offsetNo;
	/** 相殺グループコード */
	private String offsetGroupCd;
	/** 相殺グループ名称 */
	private String offsetGroupName;
	/** 相殺締め日 */
	private String offsetDate;
	/** 相殺金額 */
	private BigDecimal offsetAmount;
	/** 承認フラグ */
	private BigDecimal approvalStatus;
	/** 分類コード */
	private String categoryDivision;
	/** 分類名称 */
	private String categoryName;

	//
	// インスタンスメソッド
	//

	/**
	 * 相殺番号を取得します。
	 * @return offsetNo
	 */
	public String getOffsetNo() {
		return offsetNo;
	}

	/**
	 * 相殺番号を設定します。
	 * @param offsetNo 相殺番号
	 */
	public void setOffsetNo(final String offsetNo) {
		this.offsetNo = offsetNo;
	}

	/**
	 * 相殺グループコードを取得します。
	 * @return offsetGroupCd
	 */
	public String getOffsetGroupCd() {
		return offsetGroupCd;
	}

	/**
	 * 相殺グループコードを設定します。
	 * @param offsetGroupCd 相殺グループコード
	 */
	public void setOffsetGroupCd(final String offsetGroupCd) {
		this.offsetGroupCd = offsetGroupCd;
	}

	/**
	 * 相殺グループ名称を取得します。
	 * @return offsetGroupName
	 */
	public String getOffsetGroupName() {
		return offsetGroupName;
	}

	/**
	 * 相殺グループ名称を設定します。
	 * @param offsetGroupName 相殺グループ名称
	 */
	public void setOffsetGroupName(final String offsetGroupName) {
		this.offsetGroupName = offsetGroupName;
	}

	/**
	 * 相殺締め日を取得します。
	 * @return offsetDate
	 */
	public String getOffsetDate() {
		return offsetDate;
	}

	/**
	 * 相殺締め日を設定します。
	 * @param offsetDate 相殺締め日
	 */
	public void setOffsetDate(final String offsetDate) {
		this.offsetDate = offsetDate;
	}

	/**
	 * 相殺金額を取得します。
	 * @return offsetAmount
	 */
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}

	/**
	 * 相殺金額を設定します。
	 * @param offsetAmount 相殺金額
	 */
	public void setOffsetAmount(final BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * 承認フラグを取得します。
	 * @return approvalStatus
	 */
	public BigDecimal getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * 承認フラグを設定します。
	 * @param approvalStatus 承認フラグ
	 */
	public void setApprovalStatus(final BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * 分類コードを取得します。
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * 分類コードを設定します。
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 分類名称を取得します。
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分類名称を設定します。
	 * @param categoryName 分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
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

