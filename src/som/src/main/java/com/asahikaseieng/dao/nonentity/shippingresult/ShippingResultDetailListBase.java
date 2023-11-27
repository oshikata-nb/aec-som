/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 出荷実績詳細画面_出荷実績詳細データ格納クラス.
 * 
 * @author tosco
 */
public class ShippingResultDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultDetailListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "SHIPPING_DETAIL";

	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション shippingRowNo */
	public static final String shippingRowNo_COLUMN = "SHIPPING_ROW_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション shippingInstruction */
	public static final String shippingInstruction_COLUMN = "SHIPPING_INSTRUCTION";

	/** COLUMNアノテーション shippingResultDate */
	public static final String shippingResultDate_COLUMN = "SHIPPING_RESULT_DATE";

	/** COLUMNアノテーション shippingResultQuantity */
	public static final String shippingResultQuantity_COLUMN = "SHIPPING_RESULT_QUANTITY";

	/** COLUMNアノテーション shippingStatus */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";

	/** COLUMNアノテーション summery */
	public static final String summery_COLUMN = "SUMMERY";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション deliveryComp */
	public static final String deliveryComp_COLUMN = "DELIVERY_COMP";

	/** COLUMNアノテーション productOutOrderBc. */
	public static final String productOutOrderBc_COLUMN = "PRODUCT_OUT_ORDER_BC";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 出荷伝票番号 */
	private String shippingNo;

	/** 行番号(出荷) */
	private BigDecimal shippingRowNo;

	/** 行番号(未使用) */
	private BigDecimal rowNo;

	/** ロット番号 */
	private String lotNo;

	/** 出荷指図数量 */
	private BigDecimal shippingInstruction;

	/** 出荷実績日 */
	private Timestamp shippingResultDate;

	/** 出荷実績数 */
	private BigDecimal shippingResultQuantity;

	/** 出荷詳細ステータス */
	private BigDecimal shippingStatus;

	/** 摘要 */
	private String summery;

	/** ロケーションコード */
	private String locationCd;

	/** 完納区分 */
	private BigDecimal deliveryComp;

	/** 製品出庫指図書バーコード */
	private String productOutOrderBc;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 出荷伝票番号取得
	 * @return String 出荷伝票番号
	*/
	public String getShippingNo() {
		return this.shippingNo;
	}

	/**
	 * 出荷伝票番号設定
	 * @param shippingNo 出荷伝票番号
	*/
	public void setShippingNo(final String shippingNo) {
		this.shippingNo = shippingNo;
	}

	/**
	 * 行番号(出荷)取得
	 * @return BigDecimal 行番号(出荷)
	*/
	public BigDecimal getShippingRowNo() {
		return this.shippingRowNo;
	}

	/**
	 * 行番号(出荷)設定
	 * @param shippingRowNo 行番号(出荷)
	*/
	public void setShippingRowNo(final BigDecimal shippingRowNo) {
		this.shippingRowNo = shippingRowNo;
	}

	/**
	 * 行番号(未使用)取得
	 * @return BigDecimal 行番号(未使用)
	*/
	public BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * 行番号(未使用)設定
	 * @param rowNo 行番号(未使用)
	*/
	public void setRowNo(final BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * ロット番号取得
	 * @return String ロット番号
	*/
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * ロット番号設定
	 * @param lotNo ロット番号
	*/
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 出荷指図数量取得
	 * @return BigDecimal 出荷指図数量
	*/
	public BigDecimal getShippingInstruction() {
		return this.shippingInstruction;
	}

	/**
	 * 出荷指図数量設定
	 * @param shippingInstruction 出荷指図数量
	*/
	public void setShippingInstruction(final BigDecimal shippingInstruction) {
		this.shippingInstruction = shippingInstruction;
	}

	/**
	 * 出荷実績日取得
	 * @return Timestamp 出荷実績日
	*/
	public Timestamp getShippingResultDate() {
		return this.shippingResultDate;
	}

	/**
	 * 出荷実績日設定
	 * @param shippingResultDate 出荷実績日
	*/
	public void setShippingResultDate(final Timestamp shippingResultDate) {
		this.shippingResultDate = shippingResultDate;
	}

	/**
	 * 出荷実績数取得
	 * @return BigDecimal 出荷実績数
	*/
	public BigDecimal getShippingResultQuantity() {
		return this.shippingResultQuantity;
	}

	/**
	 * 出荷実績数設定
	 * @param shippingResultQuantity 出荷実績数
	*/
	public void setShippingResultQuantity(final BigDecimal shippingResultQuantity) {
		this.shippingResultQuantity = shippingResultQuantity;
	}

	/**
	 * 出荷詳細ステータス
	 * @return BigDecimal 出荷詳細ステータス
	*/
	public BigDecimal getShippingStatus() {
		return this.shippingStatus;
	}

	/**
	 * 出荷詳細ステータス
	 * @param shippingStatus 出荷詳細ステータス
	*/
	public void setShippingStatus(final BigDecimal shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	/**
	 * 摘要取得
	 * @return String 摘要
	*/
	public String getSummery() {
		return this.summery;
	}

	/**
	 * 摘要設定
	 * @param summery 摘要
	*/
	public void setSummery(final String summery) {
		this.summery = summery;
	}

	/**
	 * ロケーションコード取得
	 * @return String ロケーションコード
	*/
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * ロケーションコード設定
	 * @param locationCd ロケーションコード
	*/
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * 完納区分取得
	 * @return BigDecimal 完納区分
	*/
	public BigDecimal getDeliveryComp() {
		return this.deliveryComp;
	}

	/**
	 * 完納区分設定
	 * @param deliveryComp 完納区分
	*/
	public void setDeliveryComp(final BigDecimal deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * 製品出庫指図書バーコード取得.
	 * @return productOutOrderBc
	 */
	public String getProductOutOrderBc() {
		return this.productOutOrderBc;
	}

	/**
	 * 製品出庫指図書バーコード設定.
	 * @param productOutOrderBc 製品出庫指図書バーコード
	 */
	public void setProductOutOrderBc(final String productOutOrderBc) {
		this.productOutOrderBc = productOutOrderBc;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
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
