/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * グループ間相殺詳細画面用Daoクラス.
 * @author tosco
 */
public class OffsetDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OffsetDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "OFFSET_GROUP_HEADER";

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

	/** COLUMNアノテーション srhSummaryCd */
	public static final String srhSummaryCd_COLUMN = "SUMMARY_CD";

	/** COLUMNアノテーション srhSummary */
	public static final String srhSummary_COLUMN = "SUMMARY";

	/** COLUMNアノテーション offsetDate */
	public static final String offsetDate_COLUMN = "OFFSET_DATE";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション 登録日時 */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション 登録者ＩＤ */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション 更新日時 */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション 更新者ＩＤ */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド	//

	/** 相殺番号 */	private String offsetNo;
	/** 部署コード */
	private String organizationCd;
	/** 部署名称 */
	private String organizationName;
	/** 相殺グループコード */
	private String offsetGroupCd;
	/** 相殺グループ名称 */
	private String offsetGroupName;
	/** 摘要コード */
	private String srhSummaryCd;
	/** 摘要名 */
	private String srhSummary;
	/** 相殺締め日 */
	private Date offsetDate;
	/** 相殺金額 */
	private BigDecimal offsetAmount;
	/** 承認フラグ */
	private BigDecimal approvalStatus;

	/** 登録日時 */
	private java.sql.Timestamp inputDate;
	/** 登録者ＩＤ */
	private String inputorCd;
	/** 更新日時 */
	private java.sql.Timestamp updateDate;
	/** 更新者ＩＤ */
	private String updatorCd;

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
	 * 部署コードを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 部署名称を取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称を設定します。
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
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
	 * 摘要名を取得します。
	 * @return srhSummary
	 */
	public String getSrhSummary() {
		return srhSummary;
	}

	/**
	 * 摘要名を設定します。
	 * @param srhSummary 摘要名
	 */
	public void setSrhSummary(final String srhSummary) {
		this.srhSummary = srhSummary;
	}

	/**
	 * 摘要コードを取得します。
	 * @return srhSummaryCd
	 */
	public String getSrhSummaryCd() {
		return srhSummaryCd;
	}

	/**
	 * 摘要コードを設定します。
	 * @param srhSummaryCd 摘要コード
	 */
	public void setSrhSummaryCd(final String srhSummaryCd) {
		this.srhSummaryCd = srhSummaryCd;
	}

	/**
	 * 相殺締め日を取得します。
	 * @return offsetDate
	 */
	public Date getOffsetDate() {
		return offsetDate;
	}

	/**
	 * 相殺締め日を設定します。
	 * @param offsetDate 相殺締め日
	 */
	public void setOffsetDate(final Date offsetDate) {
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
	 * 登録日時取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ＩＤ取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ＩＤ設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ＩＤ取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ＩＤ設定.
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

