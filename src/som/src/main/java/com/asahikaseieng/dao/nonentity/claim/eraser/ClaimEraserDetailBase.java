/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 消込入力詳細画面用Daoクラス.
 * @author tosco
 */
public class ClaimEraserDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ERASER_HEADER";

	/** 上段 */
	/** COLUMNアノテーション eraserNo */
	public static final String eraserNo_COLUMN = "ERASER_NO";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション sectionName */
	public static final String sectionName_COLUMN = "SECTION_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	// /** COLUMNアノテーション claimNo */
	// public static final String claimNo_COLUMN = "CLAIM_NO";
	//
	// /** COLUMNアノテーション claimCreditDate */
	// public static final String claimCreditDate_COLUMN = "CLAIM_CREDIT_DATE";
	//
	// /** COLUMNアノテーション creditScheduledDate */
	// public static final String creditScheduledDate_COLUMN =
	// "CREDIT_SCHEDULED_DATE";
	//
	// /** COLUMNアノテーション kessaiCateName */
	// public static final String kessaiCateName_COLUMN = "KESSAI_CATE_NAME";

	/** COLUMNアノテーション eraserCapaAmount */
	public static final String eraserCapaAmount_COLUMN = "ERASER_CAPA_AMOUNT";

	/** COLUMNアノテーション eraserAmount */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** COLUMNアノテーション eraserBalanceAmount */
	public static final String eraserBalanceAmount_COLUMN = "ERASER_BALANCE_AMOUNT";

	/** COLUMNアノテーション sumCreditAmount */
	public static final String sumCreditAmount_COLUMN = "SUM_CREDIT_AMOUNT";

	/** COLUMNアノテーション sumEraserAmount */
	public static final String sumEraserAmount_COLUMN = "SUM_ERASER_AMOUNT";

	/** COLUMNアノテーション sumCreditEraserAmount */
	public static final String sumCreditEraserAmount_COLUMN = "SUM_CREDIT_ERASER_AMOUNT";

	/** 中段 */
	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/** COLUMNアノテーション creditNo */
	public static final String creditNo_COLUMN = "CREDIT_NO";

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション nyukinCateName */
	public static final String nyukinCateName_COLUMN = "NYUKIN_CATE_NAME";

	/** COLUMNアノテーション creditAmount */
	public static final String creditAmount_COLUMN = "CREDIT_AMOUNT";

	/** COLUMNアノテーション crEraserAmount */
	public static final String crEraserAmount_COLUMN = "CR_ERASER_AMOUNT";

	/** COLUMNアノテーション creditEraserAmount */
	public static final String creditEraserAmount_COLUMN = "CREDIT_ERASER_AMOUNT";

	//
	// インスタンスフィールド
	//

	/** 上段 */
	/** 消込番号 */
	private String eraserNo;

	/** 部門コード */
	private String sectionCd;

	/** 部門名称 */
	private String sectionName;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 消込日付 */
	private Timestamp eraserDate;

	/** 取引先区分 */
	private String venderDivision;

	/** 請求先コード */
	private String venderCd;

	/** 請求先名称 */
	private String venderName;

	private String venderName1;

	/** 支払先略称 */
	private String venderShortedName;

	/** 消込可能額 */
	private BigDecimal eraserCapaAmount;

	/** 消込額 */
	private BigDecimal eraserAmount;

	/** 消込残合計 */
	private BigDecimal eraserBalanceAmount;

	/** 入金ﾄﾗﾝ.入金金額合計 */
	private BigDecimal sumCreditAmount;

	/** 入金ﾄﾗﾝ.消込額合計 */
	private BigDecimal sumEraserAmount;

	/** 入金ﾄﾗﾝ.入金消込残合計 */
	private BigDecimal sumCreditEraserAmount;

	/** 中段 */
	/** 入金日付 */
	private Timestamp creditDate;

	/** 入金番号 */
	private String creditNo;

	/** データ種別 */
	private BigDecimal dataType;

	/** 行番号 */
	private BigDecimal rowNo;

	/** 分類名称(入金分類) */
	private String nyukinCateName;

	/** 入金ﾄﾗﾝ.入金金額 */
	private BigDecimal creditAmount;

	/** 入金ﾄﾗﾝ.消込額 */
	private BigDecimal crEraserAmount;

	/** 入金ﾄﾗﾝ.入金消込残 */
	private BigDecimal creditEraserAmount;

	/** 更新用(請求ヘッダー、入金トラン、相殺トラン、支払トラン) */
	/** 相殺番号 */
	private String offsetNo;

	/** 伝票番号(支払トラン) */
	private String paySlipNo;

	/** 行番号(支払トラン) */
	private BigDecimal payRowNo;

	/** 消込完了フラグ */
	private BigDecimal eraserCompleteDivision;

	/** 消込完了日 */
	private Date eraserCompleteDate;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ＩＤ */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 消込番号取得.
	 * @return String 消込番号
	 */
	public String getEraserNo() {
		return eraserNo;
	}

	/**
	 * 消込番号設定.
	 * @param eraserNo 消込番号
	 */
	public void setEraserNo(final String eraserNo) {
		this.eraserNo = eraserNo;
	}

	/**
	 * 部門コード取得.
	 * @return String 部門コード
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コード設定.
	 * @param sectionCd 部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 部門名称取得.
	 * @return String 部門名称
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 部門名称設定.
	 * @param sectionName 部門名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
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
	 * 請求先名称取得.
	 * @return String 請求先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 請求先名称設定.
	 * @param venderName 請求先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	// /**
	// * 請求番号取得.
	// * @return String 請求番号
	// */
	// public String getClaimNo() {
	// return claimNo;
	// }
	//
	// /**
	// * 請求番号設定.
	// * @param claimNo 請求番号
	// */
	// public void setClaimNo(final String claimNo) {
	// this.claimNo = claimNo;
	// }
	//
	// /**
	// * 請求締め日取得.
	// * @return Timestamp 請求締め日
	// */
	// public Timestamp getClaimCreditDate() {
	// return claimCreditDate;
	// }
	//
	// /**
	// * 請求締め日設定.
	// * @param claimCreditDate 請求締め日
	// */
	// public void setClaimCreditDate(final Timestamp claimCreditDate) {
	// this.claimCreditDate = claimCreditDate;
	// }
	//
	// /**
	// * 入金予定日取得.
	// * @return Timestamp 入金予定日
	// */
	// public Timestamp getCreditScheduledDate() {
	// return creditScheduledDate;
	// }
	//
	// /**
	// * 入金予定日設定.
	// * @param creditScheduledDate 入金予定日
	// */
	// public void setCreditScheduledDate(final Timestamp creditScheduledDate) {
	// this.creditScheduledDate = creditScheduledDate;
	// }
	//
	// /**
	// * 分類名称(決済方法)取得.
	// * @return String 分類名称(決済方法)
	// */
	// public String getKessaiCateName() {
	// return kessaiCateName;
	// }
	//
	// /**
	// * 分類名称(決済方法)設定.
	// * @param kessaiCateName 分類名称(決済方法)
	// */
	// public void setKessaiCateName(final String kessaiCateName) {
	// this.kessaiCateName = kessaiCateName;
	// }

	/**
	 * 消込可能額取得.
	 * @return BigDecimal 消込可能額
	 */
	public BigDecimal getEraserCapaAmount() {
		return eraserCapaAmount;
	}

	/**
	 * 消込可能額設定.
	 * @param eraserCapaAmount 消込可能額
	 */
	public void setEraserCapaAmount(final BigDecimal eraserCapaAmount) {
		this.eraserCapaAmount = eraserCapaAmount;
	}

	/**
	 * 消込額取得.
	 * @return BigDecimal 消込額
	 */
	public BigDecimal getEraserAmount() {
		return eraserAmount;
	}

	/**
	 * 消込額設定.
	 * @param eraserAmount 消込額
	 */
	public void setEraserAmount(final BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * 消込残合計取得.
	 * @return BigDecimal 消込残合計
	 */
	public BigDecimal getEraserBalanceAmount() {
		return eraserBalanceAmount;
	}

	/**
	 * 消込残合計設定.
	 * @param eraserBalanceAmount 消込残合計
	 */
	public void setEraserBalanceAmount(final BigDecimal eraserBalanceAmount) {
		this.eraserBalanceAmount = eraserBalanceAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額合計取得.
	 * @return BigDecimal 入金ﾄﾗﾝ.入金金額合計
	 */
	public BigDecimal getSumCreditAmount() {
		return sumCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額合計設定.
	 * @param sumCreditAmount 入金ﾄﾗﾝ.入金金額合計
	 */
	public void setSumCreditAmount(final BigDecimal sumCreditAmount) {
		this.sumCreditAmount = sumCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計取得.
	 * @return BigDecimal 入金ﾄﾗﾝ.消込額合計
	 */
	public BigDecimal getSumEraserAmount() {
		return sumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計設定.
	 * @param sumEraserAmount 入金ﾄﾗﾝ.消込額合計
	 */
	public void setSumEraserAmount(final BigDecimal sumEraserAmount) {
		this.sumEraserAmount = sumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計取得.
	 * @return BigDecimal 入金ﾄﾗﾝ.入金消込残合計
	 */
	public BigDecimal getSumCreditEraserAmount() {
		return sumCreditEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計設定.
	 * @param sumCreditEraserAmount 入金ﾄﾗﾝ.入金消込残合計
	 */
	public void setSumCreditEraserAmount(final BigDecimal sumCreditEraserAmount) {
		this.sumCreditEraserAmount = sumCreditEraserAmount;
	}

	/**
	 * 入金日付取得.
	 * @return Timestamp 入金日付
	 */
	public Timestamp getCreditDate() {
		return creditDate;
	}

	/**
	 * 入金日付設定.
	 * @param creditDate 入金日付
	 */
	public void setCreditDate(final Timestamp creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * 入金番号取得.
	 * @return String 入金番号
	 */
	public String getCreditNo() {
		return creditNo;
	}

	/**
	 * 入金番号設定.
	 * @param creditNo 入金番号
	 */
	public void setCreditNo(final String creditNo) {
		this.creditNo = creditNo;
	}

	/**
	 * 行番号取得.
	 * @return String 行番号
	 */
	public BigDecimal getRowNo() {
		return rowNo;
	}

	/**
	 * 行番号設定.
	 * @param rowNo 行番号
	 */
	public void setRowNo(final BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * 分類名称(入金分類)取得.
	 * @return String 分類名称(入金分類)
	 */
	public String getNyukinCateName() {
		return nyukinCateName;
	}

	/**
	 * 分類名称(入金分類)設定.
	 * @param nyukinCateName 分類名称(入金分類)
	 */
	public void setNyukinCateName(final String nyukinCateName) {
		this.nyukinCateName = nyukinCateName;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額取得.
	 * @return BigDecimal 入金ﾄﾗﾝ.入金金額
	 */
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額設定.
	 * @param creditAmount 入金ﾄﾗﾝ.入金金額
	 */
	public void setCreditAmount(final BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額取得.
	 * @return BigDecimal 入金ﾄﾗﾝ.消込額
	 */
	public BigDecimal getCrEraserAmount() {
		return crEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額設定.
	 * @param crEraserAmount 入金ﾄﾗﾝ.消込額
	 */
	public void setCrEraserAmount(final BigDecimal crEraserAmount) {
		this.crEraserAmount = crEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残取得.
	 * @return BigDecimal 入金ﾄﾗﾝ.入金消込残
	 */
	public BigDecimal getCreditEraserAmount() {
		return creditEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残設定.
	 * @param creditEraserAmount 入金ﾄﾗﾝ.入金消込残
	 */
	public void setCreditEraserAmount(final BigDecimal creditEraserAmount) {
		this.creditEraserAmount = creditEraserAmount;
	}

	/**
	 * 相殺番号取得.
	 * @return String 相殺番号
	 */
	public String getOffsetNo() {
		return offsetNo;
	}

	/**
	 * 相殺番号設定.
	 * @param offsetNo 相殺番号
	 */
	public void setOffsetNo(final String offsetNo) {
		this.offsetNo = offsetNo;
	}

	/**
	 * 伝票番号取得.
	 * @return String 伝票番号
	 */
	public String getPaySlipNo() {
		return paySlipNo;
	}

	/**
	 * 伝票番号設定.
	 * @param paySlipNo 伝票番号
	 */
	public void setPaySlipNo(final String paySlipNo) {
		this.paySlipNo = paySlipNo;
	}

	/**
	 * 行番号取得.
	 * @return BigDecimal 行番号
	 */
	public BigDecimal getPayRowNo() {
		return payRowNo;
	}

	/**
	 * 行番号設定.
	 * @param payRowNo 行番号
	 */
	public void setPayRowNo(final BigDecimal payRowNo) {
		this.payRowNo = payRowNo;
	}

	/**
	 * 消込完了フラグ取得.
	 * @return BigDecimal 消込完了フラグ
	 */
	public BigDecimal getEraserCompleteDivision() {
		return eraserCompleteDivision;
	}

	/**
	 * 消込完了フラグ設定.
	 * @param eraserCompleteDivision 消込完了フラグ
	 */
	public void setEraserCompleteDivision(
			final BigDecimal eraserCompleteDivision) {
		this.eraserCompleteDivision = eraserCompleteDivision;
	}

	/**
	 * 消込完了日取得.
	 * @return Date 消込完了日
	 */
	public Date getEraserCompleteDate() {
		return eraserCompleteDate;
	}

	/**
	 * 消込完了日設定.
	 * @param eraserCompleteDate 消込完了日
	 */
	public void setEraserCompleteDate(final Date eraserCompleteDate) {
		this.eraserCompleteDate = eraserCompleteDate;
	}

	/**
	 * 更新日時を取得します。
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日時を設定します。
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ＩＤを取得します。
	 * @return String 更新者ＩＤ
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * 更新者ＩＤを設定します。
	 * @param updatorCd 更新者ＩＤ
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
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * eraserDateを取得します。
	 * @return eraserDate
	 */
	public Timestamp getEraserDate() {
		return eraserDate;
	}

	/**
	 * eraserDateを設定します。
	 * @param eraserDate eraserDate
	 */
	public void setEraserDate(final Timestamp eraserDate) {
		this.eraserDate = eraserDate;
	}

	/**
	 * dataTypeを取得します。
	 * @return dataType
	 */
	public BigDecimal getDataType() {
		return dataType;
	}

	/**
	 * dataTypeを設定します。
	 * @param dataType dataType
	 */
	public void setDataType(final BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}
}
