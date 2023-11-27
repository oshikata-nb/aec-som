/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatedetaillist;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * EstimateDetailListクラス.
 * @author t0011036
 */
public class EstimateDetailList extends EstimateDetailListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	private Boolean checked;

	private String strEstimateInputDate;

	private String strStandardUnitPrice;

	private String strStandardDiscount;

	private String strSpecialDiscount;

	private String strUnitprice;

	private String strStandardAmount;

	private String strMatss;

	private String strEstimateValidDateFrom;

	private String strEstimateValidDateTo;

	private BigDecimal seq;

	/**
	 * コンストラクタ.
	 */
	public EstimateDetailList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックをクリア */
		setChecked(Boolean.FALSE);

		setStrEstimateInputDate(AecDateUtils.dateFormat(getEstimateInputDate(),
			"yyyy/MM/dd"));
		setStrEstimateValidDateFrom(AecDateUtils.dateFormat(
			getEstimateValidDateFrom(), "yyyy/MM/dd"));
		setStrEstimateValidDateTo(AecDateUtils.dateFormat(
			getEstimateValidDateTo(), "yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * checkedを取得します。
	 * @return checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * checkedを設定します。
	 * @param checked checked
	 */
	public void setChecked(final Boolean checked) {
		this.checked = checked;
	}

	/**
	 * strEstimateInputDateを取得します。
	 * @return strEstimateInputDate
	 */
	public String getStrEstimateInputDate() {
		return strEstimateInputDate;
	}

	/**
	 * strEstimateInputDateを設定します。
	 * @param strEstimateInputDate strEstimateInputDate
	 */
	public void setStrEstimateInputDate(final String strEstimateInputDate) {
		this.strEstimateInputDate = strEstimateInputDate;
	}

	/**
	 * strEstimateValidDateFromを取得します。
	 * @return strEstimateValidDateFrom
	 */
	public String getStrEstimateValidDateFrom() {
		return strEstimateValidDateFrom;
	}

	/**
	 * strEstimateValidDateFromを設定します。
	 * @param strEstimateValidDateFrom strEstimateValidDateFrom
	 */
	public void setStrEstimateValidDateFrom(
			final String strEstimateValidDateFrom) {
		this.strEstimateValidDateFrom = strEstimateValidDateFrom;
	}

	/**
	 * strEstimateValidDateToを取得します。
	 * @return strEstimateValidDateTo
	 */
	public String getStrEstimateValidDateTo() {
		return strEstimateValidDateTo;
	}

	/**
	 * strEstimateValidDateToを設定します。
	 * @param strEstimateValidDateTo strEstimateValidDateTo
	 */
	public void setStrEstimateValidDateTo(final String strEstimateValidDateTo) {
		this.strEstimateValidDateTo = strEstimateValidDateTo;
	}

	/**
	 * strMatssを取得します。
	 * @return strMatss
	 */
	public String getStrMatss() {
		return strMatss;
	}

	/**
	 * strMatssを設定します。
	 * @param strMatss strMatss
	 */
	public void setStrMatss(final String strMatss) {
		this.strMatss = strMatss;
	}

	/**
	 * strSpecialDiscountを取得します。
	 * @return strSpecialDiscount
	 */
	public String getStrSpecialDiscount() {
		return strSpecialDiscount;
	}

	/**
	 * strSpecialDiscountを設定します。
	 * @param strSpecialDiscount strSpecialDiscount
	 */
	public void setStrSpecialDiscount(final String strSpecialDiscount) {
		this.strSpecialDiscount = strSpecialDiscount;
	}

	/**
	 * strStandardAmountを取得します。
	 * @return strStandardAmount
	 */
	public String getStrStandardAmount() {
		return strStandardAmount;
	}

	/**
	 * strStandardAmountを設定します。
	 * @param strStandardAmount strStandardAmount
	 */
	public void setStrStandardAmount(final String strStandardAmount) {
		this.strStandardAmount = strStandardAmount;
	}

	/**
	 * strStandardDiscountを取得します。
	 * @return strStandardDiscount
	 */
	public String getStrStandardDiscount() {
		return strStandardDiscount;
	}

	/**
	 * strStandardDiscountを設定します。
	 * @param strStandardDiscount strStandardDiscount
	 */
	public void setStrStandardDiscount(final String strStandardDiscount) {
		this.strStandardDiscount = strStandardDiscount;
	}

	/**
	 * strStandardUnitPriceを取得します。
	 * @return strStandardUnitPrice
	 */
	public String getStrStandardUnitPrice() {
		return strStandardUnitPrice;
	}

	/**
	 * strStandardUnitPriceを設定します。
	 * @param strStandardUnitPrice strStandardUnitPrice
	 */
	public void setStrStandardUnitPrice(final String strStandardUnitPrice) {
		this.strStandardUnitPrice = strStandardUnitPrice;
	}

	/**
	 * strUnitpriceを取得します。
	 * @return strUnitprice
	 */
	public String getStrUnitprice() {
		return strUnitprice;
	}

	/**
	 * strUnitpriceを設定します。
	 * @param strUnitprice strUnitprice
	 */
	public void setStrUnitprice(final String strUnitprice) {
		this.strUnitprice = strUnitprice;
	}

	/**
	 * seqを取得します。
	 * @return seq
	 */
	public BigDecimal getSeq() {
		return seq;
	}

	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}
}
