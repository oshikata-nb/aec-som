/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.articleattributequeuedetail;

/**
 * ArticleAttributeQueueDetailクラス.
 * @author t0011036
 */
public class ArticleAttributeQueueDetail extends
		ArticleAttributeQueueDetailBase {

	private static final long serialVersionUID = 1L;

	private String strSellingPrice;

	private String strPaletteProducts;

	private String strSafetyLeadTime;

	/**
	 * コンストラクタ.
	 */
	public ArticleAttributeQueueDetail() {
		super();
	}

	/**
	 * strPaletteProductsを取得します。
	 * @return strPaletteProducts
	 */
	public String getStrPaletteProducts() {
		return strPaletteProducts;
	}

	/**
	 * strPaletteProductsを設定します。
	 * @param strPaletteProducts strPaletteProducts
	 */
	public void setStrPaletteProducts(final String strPaletteProducts) {
		this.strPaletteProducts = strPaletteProducts;
	}

	/**
	 * strSafetyLeadTimeを取得します。
	 * @return strSafetyLeadTime
	 */
	public String getStrSafetyLeadTime() {
		return strSafetyLeadTime;
	}

	/**
	 * strSafetyLeadTimeを設定します。
	 * @param strSafetyLeadTime strSafetyLeadTime
	 */
	public void setStrSafetyLeadTime(final String strSafetyLeadTime) {
		this.strSafetyLeadTime = strSafetyLeadTime;
	}

	/**
	 * strSellingPriceを取得します。
	 * @return strSellingPrice
	 */
	public String getStrSellingPrice() {
		return strSellingPrice;
	}

	/**
	 * strSellingPriceを設定します。
	 * @param strSellingPrice strSellingPrice
	 */
	public void setStrSellingPrice(final String strSellingPrice) {
		this.strSellingPrice = strSellingPrice;
	}
}
