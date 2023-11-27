/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.production;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 生産計画入力画面_明細部　表示用　一覧データ格納クラス.
 *
 * @author tosco
 */
public class ProductionDetailList extends ProductionDetailListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 曜日
	 * 0:日 1:月 2:火 3:水 4:木 5:金 6:土
	 */
	public static final String[] WEEk = {"日", "月", "火", "水", "木", "金", "土"};

	/** 日付　*/
	private Timestamp calDate;

	/** 見込み数量(String) */
	private String strOrderExpectQty;

	/** 受注数量(String) */
	private String strOrderAcceptQty;

	/** 合計(受注数量+見込数量)(String) */
	private String strSumOrderQty;

	/** 合計(受注数量+見込数量) */
	private BigDecimal sumOrderQty;

	/** 単位 */
	private String unit;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 小数点桁数 */
	private String quantityDecimalPoint;

	/** 端数区分 */
	private String quantityRound;

	/** 日付　休日フラグ
	 * 1:休日　0：休日ではない
	 * */
	private BigDecimal calHolidayFlag;

	/** 日付の曜日
	 * 0:日 1:月 2:火 3:水 4:木 5:金 6:土
	 */
	private BigDecimal calWeek;

	/** ステータス */
	private String workStatus;

	/**
	 * コンストラクタ.
	 */
	public ProductionDetailList() {
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
	 * 受注数量(String)を取得します。
	 * @return strOrderAcceptQty
	 */
	public String getStrOrderAcceptQty() {
		return strOrderAcceptQty;
	}

	/**
	 * 受注数量(String)を設定します。
	 * @param strOrderAcceptQty 受注数量(String)
	 */
	public void setStrOrderAcceptQty(final String strOrderAcceptQty) {
		this.strOrderAcceptQty = strOrderAcceptQty;
	}

	/**
	 * 見込み数量(String)を取得します。
	 * @return strOrderExpectQty
	 */
	public String getStrOrderExpectQty() {
		return strOrderExpectQty;
	}

	/**
	 * 見込み数量(String)を設定します。
	 * @param strOrderExpectQty 見込み数量(String)
	 */
	public void setStrOrderExpectQty(final String strOrderExpectQty) {
		this.strOrderExpectQty = strOrderExpectQty;
	}

	/**
	 * 合計(受注数量+見込数量)(String)を取得します。
	 * @return strSumOrderQty
	 */
	public String getStrSumOrderQty() {
		return strSumOrderQty;
	}

	/**
	 * 合計(受注数量+見込数量)(String)を設定します。
	 * @param strSumOrderQty 合計(受注数量+見込数量)(String)
	 */
	public void setStrSumOrderQty(final String strSumOrderQty) {
		this.strSumOrderQty = strSumOrderQty;
	}

	/**
	 * 合計(受注数量+見込数量)を取得します。
	 * @return sumOrderQty
	 */
	public BigDecimal getSumOrderQty() {
		return sumOrderQty;
	}

	/**
	 * 合計(受注数量+見込数量)を設定します。
	 * @param sumOrderQty 合計(受注数量+見込数量)
	 */
	public void setSumOrderQty(final BigDecimal sumOrderQty) {
		this.sumOrderQty = sumOrderQty;
	}


	/**
	 * 単位取得
	 * @return String 単位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 単位設定
	 * @param unit 単位
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
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
	 * 小数点桁数を取得します。
	 * @return quantityDecimalPoint
	 */
	public String getQuantityDecimalPoint() {
		return quantityDecimalPoint;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param quantityDecimalPoint 小数点桁数
	 */
	public void setQuantityDecimalPoint(final String quantityDecimalPoint) {
		this.quantityDecimalPoint = quantityDecimalPoint;
	}

	/**
	 * 端数区分を取得します。
	 * @return quantityRound
	 */
	public String getQuantityRound() {
		return quantityRound;
	}

	/**
	 * 端数区分を設定します。
	 * @param quantityRound 端数区分
	 */
	public void setQuantityRound(final String quantityRound) {
		this.quantityRound = quantityRound;
	}

	/**
	 * 日付　休日フラグを取得します。
	 * @return calHolidayFlag
	 */
	public BigDecimal getCalHolidayFlag() {
		return calHolidayFlag;
	}

	/**
	 * 日付　休日フラグを設定します。
	 * @param calHolidayFlag 日付　休日フラグ
	 */
	public void setCalHolidayFlag(final BigDecimal calHolidayFlag) {
		this.calHolidayFlag = calHolidayFlag;
	}

	/**
	 * 日付の曜日を取得します。
	 * @return calWeek
	 */
	public BigDecimal getCalWeek() {
		return calWeek;
	}

	/**
	 * 日付の曜日を設定します。
	 * @param calWeek 日付の曜日
	 */
	public void setCalWeek(final BigDecimal calWeek) {
		this.calWeek = calWeek;
	}

	/**
	 * 日付(yyyy/MM/dd(曜日))取得
	 * @return strOrderLet 
	 */
	public String getStrCalDate() {
		String strCDate = null;
		if (getCalDate() != null) {
			strCDate = AecDateUtils.dateFormat(getCalDate(), "yyyy/MM/dd");
			if (getCalWeek() != null) {
				strCDate = strCDate + "(" + WEEk[Integer.parseInt(getCalWeek().toString())] + ")";
			}
		}
		return strCDate;
	}

	/**
	 * ステータスを取得します。
	 * @return workStatus
	 */
	public String getWorkStatus() {
		return workStatus;
	}

	/**
	 * ステータスを設定します。
	 * @param workStatus ステータス
	 */
	public void setWorkStatus(final String workStatus) {
		this.workStatus = workStatus;
	}

	/**
	 * ステータスを取得します。
	 * 0:未確定 1:確定済み
	 * @return workStatus
	 */
	public String getStatus() {
		String wStatus = getWorkStatus();
		String status = "0";

		if (wStatus != null) {
			if (wStatus.compareTo("B") == 0  || wStatus.compareTo("D") == 0) {
				status = "1";
			}
		}
		return status;
	}

	/**
	 * 日付を取得します。
	 * @return calDate
	 */
	public Timestamp getCalDate() {
		return calDate;
	}

	/**
	 * 日付を設定します。
	 * @param calDate 日付
	 */
	public void setCalDate(final Timestamp calDate) {
		this.calDate = calDate;
	}
}
