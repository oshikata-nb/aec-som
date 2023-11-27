/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.stockbooking;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 検査待ち在庫計上一覧表示用データ格納クラス.
 *
 * @author tosco
 */
public class StockBookingList extends StockBookingListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public StockBookingList() {
		super();
	}

	/** チェックボックス */
	private boolean stockBookingCheckBox;

	/** 品目名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位 */
	private String unitName;

	/** 物流-生産(差分) */
	private BigDecimal subtractionQty;

	/** 物流-生産(差分)(String) */
	private String strSubtractionQty;

	/** 物流入庫数量 */
	private BigDecimal sumSuuryou;

	/** 物流入庫数量(String) */
	private String strSumSuuryou;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 実績生産量(String) */
	private String strResultQty;

	/** 数量小数点桁数 */
	private String quantityDecimalPoint;

	/** 数量端数区分 */
	private String quantityRound;

	/** ロケーション */
	private String location;

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 終了実績日時取得(YYYY/MM/DD)
	 * @return String 終了実績日時(YYYY/MM/DD)
	*/
	public String getStrResultEdate() {
		return AecDateUtils.dateFormat(getResultEdate(), "yyyy/MM/dd");
	}

	/**
	 * 物流-生産(差分)(String) を取得します。
	 * @return strSubtractionQty
	 */
	public String getStrSubtractionQty() {
		return strSubtractionQty;
	}

	/**
	 * 物流-生産(差分)(String) を設定します。
	 * @param strSubtractionQty 物流-生産(差分)(String) 
	 */
	public void setStrSubtractionQty(final String strSubtractionQty) {
		this.strSubtractionQty = strSubtractionQty;
	}

	/**
	 * 物流-生産(差分)を取得します。
	 * @return subtractionQty
	 */
	public BigDecimal getSubtractionQty() {
		return subtractionQty;
	}

	/**
	 * 物流-生産(差分)を設定します。
	 * @param subtractionQty 物流-生産(差分)
	 */
	public void setSubtractionQty(final BigDecimal subtractionQty) {
		this.subtractionQty = subtractionQty;
	}

	/**
	 * チェックボックス取得
	 * @return stockBookingCheckBox
	 */
	public boolean isStockBookingCheckBox() {
		return stockBookingCheckBox;
	}

	/**
	 * チェックボックス設定
	 * @param stockBookingCheckBox チェックボックス
	 */
	public void setStockBookingCheckBox(final boolean stockBookingCheckBox) {
		this.stockBookingCheckBox = stockBookingCheckBox;
	}

	/**
	 * 品目名称を取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 荷姿を取得します。
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 包装開始実績日(String)を取得します。
	 * @return strResultSdate
	 */
	public String getStrResultSdate() {
		//取得した包装開始実績日をyyyy/MM/ddに変換
		return AecDateUtils.dateFormat(getResultSdate(), "yyyy/MM/dd");
	}

	/**
	 * 物流入庫数量を取得します。
	 * @return sumSuuryou
	 */
	public BigDecimal getSumSuuryou() {
		return sumSuuryou;
	}

	/**
	 * 物流入庫数量を設定します。
	 * @param sumSuuryou 物流入庫数量
	 */
	public void setSumSuuryou(final BigDecimal sumSuuryou) {
		this.sumSuuryou = sumSuuryou;
	}

	/**
	 * 物流入庫数量を取得します。
	 * @return strSumSuuryou
	 */
	public String getStrSumSuuryou() {
		return strSumSuuryou;
	}

	/**
	 * 物流入庫数量を設定します。
	 * @param strSumSuuryou 物流入庫数量
	 */
	public void setStrSumSuuryou(final String strSumSuuryou) {
		this.strSumSuuryou = strSumSuuryou;
	}

	/**
	 * 単位を取得します。
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位を設定します。
	 * @param unitName 単位
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 運用管理単位を取得します。
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位を設定します。
	 * @param unitOfOperationManagement 運用管理単位
	 */
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 実績生産量(String)を取得します。
	 * @return strResultQty
	 */
	public String getStrResultQty() {
		return strResultQty;
	}

	/**
	 * 実績生産量(String)を設定します。
	 * @param strResultQty 実績生産量(String)
	 */
	public void setStrResultQty(final String strResultQty) {
		this.strResultQty = strResultQty;
	}

	/**
	 * 数量小数点桁数を取得します。
	 * @return quantityDecimalPoint
	 */
	public String getQuantityDecimalPoint() {
		return quantityDecimalPoint;
	}

	/**
	 * 数量小数点桁数を設定します。
	 * @param quantityDecimalPoint 数量小数点桁数
	 */
	public void setQuantityDecimalPoint(final String quantityDecimalPoint) {
		this.quantityDecimalPoint = quantityDecimalPoint;
	}

	/**
	 * 数量端数区分を取得します。
	 * @return quantityRound
	 */
	public String getQuantityRound() {
		return quantityRound;
	}

	/**
	 * 数量端数区分を設定します。
	 * @param quantityRound 数量端数区分
	 */
	public void setQuantityRound(final String quantityRound) {
		this.quantityRound = quantityRound;
	}

	/**
	 * ロケーションを取得します。
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * ロケーションを設定します。
	 * @param location ロケーション
	 */
	public void setLocation(final String location) {
		this.location = location;
	}
}
