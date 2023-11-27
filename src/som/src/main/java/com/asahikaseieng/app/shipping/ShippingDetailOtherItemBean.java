/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.app.shipping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherList;

/**
 * 花王・その他用新規画面_品目情報表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ShippingDetailOtherItemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingDetailOtherItemBean() {
	}

	//
	// 定数
	//

	//
	// インスタンスフィールド
	//

	/** チェックフラグ */
	private boolean checkFlg;

	/** 品名コード */
	private String itemCd;

	/** 品名名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 指図量累計(文字列) */
	private String strShippingInstructionSum;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位区分 */
	private String unitDivision;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String round;

	/** 出荷指図詳細リスト */
	private List<ShippingDetailOtherList> detailList;

	//
	// インスタンスメソッド
	//

	/**
	 * クリア処理.
	 */
	public void clear() {
		/** チェックフラグ */
		setCheckFlg(false);

		/** 品名コード */
		setItemCd(null);

		/** 品名名称 */
		setItemName(null);

		/** 他社コード1 */
		setOtherCompanyCd1(null);

		/** 指図量累計(文字列) */
		setStrShippingInstructionSum(null);

		/** 荷姿 */
		setStyleOfPacking(null);

		/** 単位区分 */
		setUnitDivision(null);

		/** 小数点桁数 */
		setDecimalPoint(null);

		/** 端数区分 */
		setRound(null);

		/** 出荷指図詳細リストのクリア */
		setDetailList(new ArrayList<ShippingDetailOtherList>());

	}

	/**
	 * チェックフラグを取得します。
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグを設定します。
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
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
	 * 小数点桁数取得
	 * @return String 小数点桁数
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数設定
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分取得
	 * @return String 端数区分
	 */
	public String getRound() {
		return round;
	}

	/**
	 * 端数区分設定
	 * @param round 端数区分
	 */
	public void setRound(final String round) {
		this.round = round;
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
	 * 品目コード取得
	 * @return String 品目コード
	*/
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 品目コード設定
	 * @param otherCompanyCd1 品目コード
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
	 * 指図量累計(文字列)取得
	 * @return BigDecimal 指図量累計(文字列)
	*/
	public String getStrShippingInstructionSum() {
		return this.strShippingInstructionSum;
	}

	/**
	 * 指図量累計(文字列)設定
	 * @param strShippingInstructionSum 指図量累計(文字列)
	*/
	public void setStrShippingInstructionSum(final String strShippingInstructionSum) {
		this.strShippingInstructionSum = strShippingInstructionSum;
	}

	/**
	 * 出荷指図詳細（花王・その他）：detailListを取得します。
	 * @return itemList
	 */
	public List<ShippingDetailOtherList> getDetailList() {
		return detailList;
	}

	/**
	 * 出荷指図詳細（花王・その他）：detailListを設定します。

	 * @param detailList detailList
	 */
	public void setDetailList(final List<ShippingDetailOtherList> detailList) {
		this.detailList = detailList;
	}

	/**
	 * 明細行数を取得する。
	 * @return 明細行数
	 */
	public int getDetailCount() {
		int count = 0;
		if (detailList != null) {
			count = detailList.size();
		}
		return count;
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
