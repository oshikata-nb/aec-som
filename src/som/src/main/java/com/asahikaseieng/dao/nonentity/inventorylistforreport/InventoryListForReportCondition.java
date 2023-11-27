/*
 * Created on 2007/12/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorylistforreport;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 在庫照会検索条件
 * @author fpc
 */
public class InventoryListForReportCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InventoryListForReportCondition() {
	}

	/** 検索条件プロパティ * */

	private String srhLocationCd; /* ロケーションコード */

	private String srhItemCd; /* 品目コード */

	private String srhOtherCompanyCd1; /* 他社コード1 */

	private String srhLotNo; /* 入荷ロット番号/包装指図番号 */

	private BigDecimal srhAvailableFlg; /* 在庫可能フラグ */

	/**
	 * srhItemCdを取得します。
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * srhItemCdを設定します。
	 * @param srhItemCd srhItemCd
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * srhLocationCdを取得します。
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return srhLocationCd;
	}

	/**
	 * srhLocationCdを設定します。
	 * @param srhLocationCd srhLocationCd
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * srhOtherCompanyCd1を取得します。
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * srhOtherCompanyCd1を設定します。
	 * @param srhOtherCompanyCd1 srhOtherCompanyCd1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * srhAvailableFlgを取得します。
	 * @return srhAvailableFlg
	 */
	public BigDecimal getSrhAvailableFlg() {
		return srhAvailableFlg;
	}

	/**
	 * srhAvailableFlgを設定します。
	 * @param srhAvailableFlg srhAvailableFlg
	 */
	public void setSrhAvailableFlg(final BigDecimal srhAvailableFlg) {
		this.srhAvailableFlg = srhAvailableFlg;
	}

	/**
	 * srhLotNoを取得します。
	 * @return srhLotNo
	 */
	public String getSrhLotNo() {
		return srhLotNo;
	}

	/**
	 * srhLotNoを設定します。
	 * @param srhLotNo srhLotNo
	 */
	public void setSrhLotNo(final String srhLotNo) {
		this.srhLotNo = srhLotNo;
	}
}
