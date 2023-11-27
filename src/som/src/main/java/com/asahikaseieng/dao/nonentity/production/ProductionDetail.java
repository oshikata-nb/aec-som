/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.production;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectProductionShipperDivision;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 生産計画入力画面_ヘッダ部表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ProductionDetail extends ProductionDetailBase implements
		PropertyTransferCallbacker {

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	private static final long serialVersionUID = 1L;

	/** 全オーダー件数 */
	private BigDecimal orderCount;

	/**
	 * コンストラクタ.
	 */
	public ProductionDetail() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 編集処理
	 * @throws Exception 例外
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
	 * 全オーダー件数取得
	 * @return BigDecimal 全オーダー件数
	 */
	public BigDecimal getOrderCount() {
		return orderCount;
	}

	/**
	 * 全オーダー件数設定
	 * @param orderCount 全オーダー件数
	 */
	public void setOrderCount(final BigDecimal orderCount) {
		this.orderCount = orderCount;
	}

	/**
	 * 荷主(String)取得
	 * @return strShipperDivision
	 */
	public String getStrShipperDivision() {
		String sDiv = null;
		if (getShipperDivision() != null) {
			sDiv = SelectProductionShipperDivision
					.getLabelName(getShipperDivision().add(BigDecimal.ONE)
							.toString());
		}
		return sDiv;
	}

	/**
	 * 社内製造品/外注品区分(String)取得
	 * @return strTypeDivision
	 */
	public String getStrTypeDivision() {
		String strTDiv = null;
		if (getTypeDivision() != null) {
			BigDecimal typeDivision = getTypeDivision();
			if (typeDivision.compareTo(new BigDecimal(6)) == 0
					|| typeDivision.compareTo(new BigDecimal(7)) == 0) {
				strTDiv = "外注品";
			} else {
				strTDiv = "社内製造品";
			}
		}
		return strTDiv;
	}

	/**
	 * 生産計画年月(yyyyMM)取得
	 * @return strOrderLet
	 */
	public String getStrOrderLet() {
		String strOLet = null;
		if (getOrderLet() != null) {
			strOLet = AecDateUtils.dateFormat(getOrderLet(), "yyyy/MM");
		}
		return strOLet;
	}
}
