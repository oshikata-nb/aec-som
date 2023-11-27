/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class SalesListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SALES";

	/** COLUMNアノテーション salesDate */
	public static final String salesDate_COLUMN = "SALES_DATE";

	/** COLUMNアノテーション salesNo */
	public static final String salesNo_COLUMN = "SALES_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション salesQuantity */
	public static final String salesQuantity_COLUMN = "SALES_QUANTITY";

	/** COLUMNアノテーション salesAmount */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/** COLUMNアノテーション tmpUnitpriceFlg */
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";

	/** COLUMNアノテーション slipPublishComp */
	public static final String slipPublishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション accountYears */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

	/** COLUMNアノテーション keepFlag */
	public static final String keepFlag_COLUMN = "KEEP_FLAG";

	//
	// インスタンスフィールド
	//

	/** 売上番号 */
	private String salesNo;

	/** 分類コード */
	private String categoryDivision;

	/** 勘定年月 */
	private String accountYears;

	/** 受注番号 */
	private String orderNo;

	/** 売上日 */
	private Timestamp salesDate;

	/** 得意先コード */
	private String venderCd;

	/** 品目コード */
	private String itemCd;

	/** 売上数量 */
	private BigDecimal salesQuantity;

	/** 金額 */
	private BigDecimal salesAmount;

	/** 仮単価FLG */
	private BigDecimal tmpUnitpriceFlg;

	/** 伝票発行済区分 */
	private BigDecimal slipPublishComp;

	/** 預り品フラグ */
	private BigDecimal keepFlag;

	//
	// インスタンスメソッド
	//

	/**
	 * 売上番号取得
	 * @return String 売上番号
	*/
	public String getSalesNo() {
		return this.salesNo;
	}

	/**
	 * 売上番号設定
	 * @param salesNo 売上番号
	*/
	public void setSalesNo(final String salesNo) {
		this.salesNo = salesNo;
	}

	/**
	 * 分類コード取得
	 * @return String 分類コード
	*/
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * 分類コード設定
	 * @param categoryDivision 分類コード
	*/
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 勘定年月取得
	 * @return String 勘定年月
	*/
	public String getAccountYears() {
		return this.accountYears;
	}

	/**
	 * 勘定年月設定
	 * @param accountYears 勘定年月
	*/
	public void setAccountYears(final String accountYears) {
		this.accountYears = accountYears;
	}

	/**
	 * 受注番号取得
	 * @return String 受注番号
	*/
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 受注番号設定
	 * @param orderNo 受注番号
	*/
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 売上日取得
	 * @return Timestamp 売上日
	*/
	public Timestamp getSalesDate() {
		return this.salesDate;
	}

	/**
	 * 売上日設定
	 * @param salesDate 売上日
	*/
	public void setSalesDate(final Timestamp salesDate) {
		this.salesDate = salesDate;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	*/
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	*/
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 得意先コード取得
	 * @return String 得意先コード
	*/
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 得意先コード設定
	 * @param venderCd 得意先コード
	*/
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 売上数量取得
	 * @return BigDecimal 売上数量
	*/
	public BigDecimal getSalesQuantity() {
		return this.salesQuantity;
	}

	/**
	 * 売上数量設定
	 * @param salesQuantity 売上数量
	*/
	public void setSalesQuantity(final BigDecimal salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	/**
	 * 金額取得
	 * @return BigDecimal 金額
	*/
	public BigDecimal getSalesAmount() {
		return this.salesAmount;
	}

	/**
	 * 金額設定
	 * @param salesAmount 金額
	*/
	public void setSalesAmount(final BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * 仮単価FLG取得
	 * @return BigDecimal 仮単価FLG
	*/
	public BigDecimal getTmpUnitpriceFlg() {
		return this.tmpUnitpriceFlg;
	}

	/**
	 * 仮単価FLG設定
	 * @param tmpUnitpriceFlg 仮単価FLG
	*/
	public void setTmpUnitpriceFlg(final BigDecimal tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
	}

	/**
	 * 伝票発行済区分取得
	 * @return BigDecimal 伝票発行済区分
	*/
	public BigDecimal getSlipPublishComp() {
		return this.slipPublishComp;
	}

	/**
	 * 伝票発行済区分設定
	 * @param slipPublishComp 伝票発行済区分
	*/
	public void setSlipPublishComp(final BigDecimal slipPublishComp) {
		this.slipPublishComp = slipPublishComp;
	}

	/**
	 * 預り品フラグ取得
	 * @return BigDecimal 預り品フラグ
	*/
	public BigDecimal getKeepFlag() {
		return this.keepFlag;
	}

	/**
	 * 預り品フラグ設定
	 * @param keepFlag 預り品フラグ
	*/
	public void setKeepFlag(final BigDecimal keepFlag) {
		this.keepFlag = keepFlag;
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
