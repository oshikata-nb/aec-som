/*
 * Created on 2008/07/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arrollback;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 売掛ロールバック用Entityクラス.
 * @author tosco
 */
public class ArRollbackBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArRollbackBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "DEPOSIT_HEADER";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/** COLUMNアノテーション closingMonth */
	public static final String closingMonth_COLUMN = "CLOSING_MONTH";

	//
	// インスタンスフィールド
	//

	/** 部署コード */
	private String organizationCd;

	/** 請求先コード */
	private String venderCd;

	/** 売掛締め日 */
	private Date creditDate;

	/** 売掛締め日の翌月 */
	private String closingMonth;
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
	/** 相殺日のFrom */
	private Date offsetFromDate;
	/** 相殺日のTo */
	private Date offsetToDate;
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了
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

	/**
	 * 請求先コード取得.
	 * @return String 請求先コード

	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 請求先コード設定.
	 * @param venderCd 請求先コード

	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 売掛締め日取得.
	 * @return String 売掛締め日
	 */
	public Date getCreditDate() {
		return creditDate;
	}

	/**
	 * 売掛締め日設定.
	 * @param creditDate 売掛締め日
	 */
	public void setCreditDate(final Date creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * 売掛締め日の翌月取得.
	 * @return String 売掛締め日の翌月
	 */
	public String getClosingMonth() {
		return closingMonth;
	}

	/**
	 * 売掛締め日の翌月設定.
	 * @param closingMonth 売掛締め日の翌月
	 */
	public void setClosingMonth(final String closingMonth) {
		this.closingMonth = closingMonth;
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

