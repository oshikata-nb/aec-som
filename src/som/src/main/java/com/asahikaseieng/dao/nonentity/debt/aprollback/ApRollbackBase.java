/*
 * Created on 2008/07/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.aprollback;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 買掛ロールバック用Entityクラス.
 * @author tosco
 */
public class ApRollbackBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApRollbackBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "PAYABLE_HEADER";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション payableDate */
	public static final String payableDate_COLUMN = "PAYABLE_DATE";

	/** COLUMNアノテーション closingMonth */
	public static final String closingMonth_COLUMN = "CLOSING_MONTH";

	//
	// インスタンスフィールド
	//

	/** 部署コード */
	private String organizationCd;

	/** 買掛締め日 */
	private Date payableDate;
	
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始	
	/**買掛確認From**/
	private Date offsetFromDate;
	/**買掛確認To**/
	private Date offsetToDate;
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了
	
	/** 買掛締め日の翌月 */
	private String closingMonth;

	//
	// インスタンスメソッド
	//

	/**
	 * 部署コード取得.
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コード設定.
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
	/**
	 * 相殺日From取得.
	 * @return String 締め日From
	 */
	public Date getOffsetFromDate() {
		return offsetFromDate;
	}
	/**
	 * 相殺日From設定.
	 * @param payableDate 締め日From
	 */
	public void setOffsetFromDate(final Date offsetFromDate) {
		this.offsetFromDate = offsetFromDate;
	}	
	/**
	 * 相殺日To取得.
	 * @return String 締め日To
	 */
	public Date getOffsetToDate() {
		return offsetToDate;
	}
	/**
	 * 相殺日To設定.
	 * @param payableToDate 締め日To
	 */
	public void setOffsetToDate(final Date offsetToDate) {
		this.offsetToDate = offsetToDate;
	}
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了
	/**
	 * 買掛締め日取得.
	 * @return String 買掛締め日
	 */
	public Date getPayableDate() {
		return payableDate;
	}

	/**
	 * 買掛締め日設定.
	 * @param payableDate 買掛締め日
	 */
	public void setPayableDate(final Date payableDate) {
		this.payableDate = payableDate;
	}

	/**
	 * 買掛締め日の翌月取得.
	 * @return String 買掛締め日の翌月
	 */
	public String getClosingMonth() {
		return closingMonth;
	}

	/**
	 * 買掛締め日の翌月設定.
	 * @param closingMonth 買掛締め日の翌月
	 */
	public void setClosingMonth(final String closingMonth) {
		this.closingMonth = closingMonth;
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

