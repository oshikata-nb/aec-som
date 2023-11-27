/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportdetailentity;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;


/**
 * OrderImportDetailクラス.
 * @author 
 */
public class OrderImportDetailHeaderEntity extends OrderImportDetailHeaderEntityBase {

	private static final long serialVersionUID = 1L;
	
	/**
	 * コンストラクタ.
	 */
	public OrderImportDetailHeaderEntity() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックをクリア */
		setChecked(Boolean.FALSE);
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(), "yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {

		setOrderDate(AecDateUtils.getTimestampYmdFormat(getStrOrderDate()));
		
		setOrderAmount(AecNumberUtils.convertBigDecimal(getStrOrderAmount()));

	}
	
	/** COLUMNアノテーション maxPkRow */
	public static final String maxPkRow_COLUMN = "MAX_PK_ROW";

	/* String Value : 合計金額 */
	private String strOrderAmount;
	
	// ポップアップチェック
	private Boolean checked;

	// チェックボックス
	private Boolean checkline;

	/* 受注日 */
	private String strOrderDate;
	
	/* PK_ROW最大*/
	private BigDecimal maxPkRow;
	
	/**


	/**
	 * strOrderAmount取得.
	 * 
	 * @return strOrderAmount
	 */
	public String getStrOrderAmount() {
		return strOrderAmount;
	}

	/**
	 * strOrderAmount設定.
	 * 
	 * @param strOrderAmount
	 *            strOrderAmount
	 */
	public void setStrOrderAmount(final String strOrderAmount) {
		this.strOrderAmount = strOrderAmount;
	}


	/**
	 * checkを取得します。
	 * 
	 * @return check
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * checkを設定します。
	 * 
	 * @param checked
	 *            checked
	 */
	public void setChecked(final Boolean checked) {
		this.checked = checked;
	}

	/**
	 * checklineを取得します。
	 * 
	 * @return checkline
	 */
	public Boolean getCheckline() {
		return checkline;
	}

	/**
	 * checklineを設定します。
	 * 
	 * @param checkline
	 *            checkline
	 */
	public void setCheckline(final Boolean checkline) {
		this.checkline = checkline;
	}

	/**
	 * strOrderDate取得.
	 * 
	 * @return strOrderDate
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * strOrderDate設定.
	 * 
	 * @param strOrderDate
	 *            strOrderDate
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * maxPkRowを取得します。
	 * @return maxPkRow
	 */
	public BigDecimal getMaxPkRow() {
		return maxPkRow;
	}

	/**
	 * maxPkRowを設定します。
	 * @param maxPkRow maxPkRow
	 */
	public void setMaxPkRow(BigDecimal maxPkRow) {
		this.maxPkRow = maxPkRow;
	}
	
}
