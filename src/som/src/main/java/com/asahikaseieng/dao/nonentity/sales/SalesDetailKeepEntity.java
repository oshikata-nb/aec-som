/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 詳細画面(預り品)_表示用データ格納クラス.
 *
 * @author tosco
 */
public class SalesDetailKeepEntity extends SalesDetailEntity implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** COLUMNアノテーション keepDivision */
	public static final String keepDivision_COLUMN = "KEEP_DIVISION";

	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";

	/** 売上区分名称 */
	private String categoryName;

	/** 品名名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位区分 */
	private String unitDivision;

	/** 預かり品区分 */
	private BigDecimal keepDivision;

	/** 納入先宛先 */
	private String deliveryAddress;

	/** 帳合コード */
	private String balanceCd;

	/** 増付数 */
	private BigDecimal matss;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailKeepEntity() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		super.init();
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */
	/**
	 * 売上区分名称取得.
	 * @return String 売上区分名称
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * 売上区分名称設定.
	 * @param categoryName 売上区分名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	*/
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	*/
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 他社コード1取得
	 * @return String 他社コード1
	*/
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param otherCompanyCd1 他社コード1
	*/
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	*/
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	*/
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 単位区分
	 * @return String 単位区分
	*/
	public String getUnitDivision() {
		return this.unitDivision;
	}

	/**
	 * 単位区分
	 * @param unitDivision 単位区分
	*/
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * 預かり品区分を取得します。
	 * @return BigDecimal 預かり品区分
	 */
	public BigDecimal getKeepDivision() {
		return keepDivision;
	}

	/**
	 * 預かり品区分を設定します。
	 * @param keepDivision 預かり品区分
	 */
	public void setKeepDivision(final BigDecimal keepDivision) {
		this.keepDivision = keepDivision;
	}

	/**
	 * 納入先宛先取得
	 * @return String 納入先宛先
	*/
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * 納入先宛先設定
	 * @param deliveryAddress 納入先宛先
	*/
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * 帳合コード取得
	 * @return String 帳合コード
	*/
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * 帳合コード設定
	 * @param balanceCd 帳合コード
	*/
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * 増付数取得
	 * @return BigDecimal 増付数
	*/
	public BigDecimal getMatss() {
		return this.matss;
	}

	/**
	 * 増付数設定
	 * @param matss 増付数
	*/
	public void setMatss(final BigDecimal matss) {
		this.matss = matss;
	}

}
