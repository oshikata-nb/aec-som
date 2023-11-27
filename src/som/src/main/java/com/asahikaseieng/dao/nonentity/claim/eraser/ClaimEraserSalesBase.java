/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 消込入力詳細(下段) 請求データまたはグループ間相殺データ用Daoクラス.
 * @author tosco
 */
public class ClaimEraserSalesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserSalesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SALES";

	/** COLUMNアノテーション checkKbn */
	public static final String checkKbn_COLUMN = "CHECK_KBN";

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション customerCd */
	public static final String customerCd_COLUMN = "CUSTOMER_CD";

	/** COLUMNアノテーション customerName */
	public static final String customerName_COLUMN = "CUSTOMER_NAME";

	/** COLUMNアノテーション salesDate */
	public static final String salesDate_COLUMN = "SALES_DATE";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション salesQuantity */
	public static final String salesQuantity_COLUMN = "SALES_QUANTITY";

	/** COLUMNアノテーション operateManagementUnit */
	public static final String operateManagementUnit_COLUMN = "OPERATE_MANAGEMENT_UNIT";

	/** COLUMNアノテーション defineUnitprice */
	public static final String defineUnitprice_COLUMN = "DEFINE_UNITPRICE";

	/** COLUMNアノテーション salesAmount */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション totalSalesAmount */
	public static final String totalSalesAmount_COLUMN = "TOTAL_SALES_AMOUNT";

	/** COLUMNアノテーション eraserNo */
	public static final String eraserNo_COLUMN = "ERASER_NO";

	/** COLUMNアノテーション eraserCompleteDivision */
	public static final String eraserCompleteDivision_COLUMN = "ERASER_COMPLETE_DIVISION";

	/** COLUMNアノテーション eraserCompleteDate */
	public static final String eraserCompleteDate_COLUMN = "ERASER_COMPLETE_DATE";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** チェック区分 */
	private String checkKbn;

	/** データ種別 */
	private BigDecimal dataType;

	/** 得意先コード */
	private String customerCd;

	/** 得意先名称 */
	private String customerName;

	/** 売上日付 */
	private Timestamp salesDate;

	/** 伝票番号(相殺番号) */
	private String slipNo;

	/** 行番号 */
	private BigDecimal rowNo;

	/** 分類名称 */
	private String categoryName;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 売上数量 */
	private BigDecimal salesQuantity;

	/** 運用管理単位 */
	private String operateManagementUnit;

	/** 決定単価 */
	private BigDecimal defineUnitprice;

	/** 売上金額 */
	private BigDecimal salesAmount;

	/** 消費税額 */
	private BigDecimal taxAmount;

	/** 請求金額 */
	private BigDecimal totalSalesAmount;

	/** 消込番号 */
	private String eraserNo;

	/** 更新用 */
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
	 * チェック区分取得.
	 * @return String チェック区分
	 */
	public String getCheckKbn() {
		return checkKbn;
	}

	/**
	 * チェック区分設定.
	 * @param checkKbn チェック区分
	 */
	public void setCheckKbn(final String checkKbn) {
		this.checkKbn = checkKbn;
	}

	/**
	 * データ種別取得.
	 * @return BigDecimal データ種別
	 */
	public BigDecimal getDataType() {
		return dataType;
	}

	/**
	 * データ種別設定.
	 * @param dataType データ種別
	 */
	public void setDataType(final BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * 得意先コード取得.
	 * @return String 得意先コード
	 */
	public String getCustomerCd() {
		return customerCd;
	}

	/**
	 * 得意先コード設定.
	 * @param customerCd 得意先コード
	 */
	public void setCustomerCd(final String customerCd) {
		this.customerCd = customerCd;
	}

	/**
	 * 得意先名称取得.
	 * @return String 得意先名称
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 得意先名称設定.
	 * @param customerName 得意先名称
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 売上日付取得.
	 * @return Timestamp 売上日付
	 */
	public Timestamp getSalesDate() {
		return salesDate;
	}

	/**
	 * 売上日付設定.
	 * @param salesDate 売上日付
	 */
	public void setSalesDate(final Timestamp salesDate) {
		this.salesDate = salesDate;
	}

	/**
	 * 伝票番号(相殺番号)取得.
	 * @return String 伝票番号(相殺番号)
	 */
	public String getSlipNo() {
		return slipNo;
	}

	/**
	 * 伝票番号(相殺番号)設定.
	 * @param slipNo 伝票番号(相殺番号)
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * 行番号取得.
	 * @return BigDecimal 行番号
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
	 * 分類名称取得.
	 * @return String 分類名称
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分類名称設定.
	 * @param categoryName 分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コード設定.
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得.
	 * @return String 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 売上数量取得.
	 * @return BigDecimal 売上数量
	 */
	public BigDecimal getSalesQuantity() {
		return salesQuantity;
	}

	/**
	 * 売上数量設定.
	 * @param salesQuantity 売上数量
	 */
	public void setSalesQuantity(final BigDecimal salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	/**
	 * 運用管理単位取得.
	 * @return String 運用管理単位
	 */
	public String getOperateManagementUnit() {
		return operateManagementUnit;
	}

	/**
	 * 運用管理単位設定.
	 * @param operateManagementUnit 運用管理単位
	 */
	public void setOperateManagementUnit(final String operateManagementUnit) {
		this.operateManagementUnit = operateManagementUnit;
	}

	/**
	 * 決定単価取得.
	 * @return BigDecimal 決定単価
	 */
	public BigDecimal getDefineUnitprice() {
		return defineUnitprice;
	}

	/**
	 * 決定単価設定.
	 * @param defineUnitprice 決定単価
	 */
	public void setDefineUnitprice(final BigDecimal defineUnitprice) {
		this.defineUnitprice = defineUnitprice;
	}

	/**
	 * 売上金額取得.
	 * @return BigDecimal 売上金額
	 */
	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	/**
	 * 売上金額設定.
	 * @param salesAmount 売上金額
	 */
	public void setSalesAmount(final BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * 消費税額取得.
	 * @return BigDecimal 消費税額
	 */
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	/**
	 * 消費税額設定.
	 * @param taxAmount 消費税額
	 */
	public void setTaxAmount(final BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * 請求金額取得.
	 * @return BigDecimal 請求金額
	 */
	public BigDecimal getTotalSalesAmount() {
		return totalSalesAmount;
	}

	/**
	 * 請求金額設定.
	 * @param totalSalesAmount 請求金額
	 */
	public void setTotalSalesAmount(final BigDecimal totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}

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
	public void setEraserCompleteDivision(final BigDecimal eraserCompleteDivision) {
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

}

