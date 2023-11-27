package com.asahikaseieng.dao.nonentity.master.banklistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 銀行検索条件
 * @author a1020630
 */
public class BankListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public BankListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhBankCd; /* 銀行コード */

	private String srhBranchCd; /* 支店コード */

	private String srhBankMasterCd; /* 銀行マスタコード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhBankCdを取得します。
	 * @return srhBankCd
	 */
	public String getSrhBankCd() {
		return srhBankCd;
	}

	/**
	 * srhBankCdを設定します。
	 * @param srhBankCd srhBankCd
	 */
	public void setSrhBankCd(final String srhBankCd) {
		this.srhBankCd = AecTextUtils.likeFilter(srhBankCd);
	}

	/**
	 * srhBankMasterCdを取得します。
	 * @return srhBankMasterCd
	 */
	public String getSrhBankMasterCd() {
		return srhBankMasterCd;
	}

	/**
	 * srhBankMasterCdを設定します。
	 * @param srhBankMasterCd srhBankMasterCd
	 */
	public void setSrhBankMasterCd(final String srhBankMasterCd) {
		this.srhBankMasterCd = AecTextUtils.likeFilter(srhBankMasterCd);
	}

	/**
	 * srhBranchCdを取得します。
	 * @return srhBranchCd
	 */
	public String getSrhBranchCd() {
		return srhBranchCd;
	}

	/**
	 * srhBranchCdを設定します。
	 * @param srhBranchCd srhBranchCd
	 */
	public void setSrhBranchCd(final String srhBranchCd) {
		this.srhBranchCd = AecTextUtils.likeFilter(srhBranchCd);
	}
}
