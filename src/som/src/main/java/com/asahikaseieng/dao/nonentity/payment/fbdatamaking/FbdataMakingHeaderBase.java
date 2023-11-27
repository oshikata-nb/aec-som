/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.fbdatamaking;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ＦＢデータ作成 ＦＢヘッダー用BaseEntityクラス.
 * @author tosco
 */
public class FbdataMakingHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public FbdataMakingHeaderBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "FB_HEADER";

	/** COLUMNアノテーション dataDivision. */
	public static final String dataDivision_COLUMN = "DATA_DIVISION";

	/** COLUMNアノテーション divisionCd. */
	public static final String divisionCd_COLUMN = "DIVISION_CD";

	/** COLUMNアノテーション categoryDivision. */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション transferClientCd. */
	public static final String transferClientCd_COLUMN = "TRANSFER_CLIENT_CD";

	/** COLUMNアノテーション transferClientName. */
	public static final String transferClientName_COLUMN = "TRANSFER_CLIENT_NAME";

	/** COLUMNアノテーション trasferDate. */
	public static final String trasferDate_COLUMN = "TRASFER_DATE";

	/** COLUMNアノテーション bankMasterCd. */
	public static final String bankMasterCd_COLUMN = "BANK_MASTER_CD";

	/** COLUMNアノテーション bankKanaName. */
	public static final String bankKanaName_COLUMN = "BANK_KANA_NAME";

	/** COLUMNアノテーション branchCd. */
	public static final String branchCd_COLUMN = "BRANCH_CD";

	/** COLUMNアノテーション branchKanaName. */
	public static final String branchKanaName_COLUMN = "BRANCH_KANA_NAME";

	/** COLUMNアノテーション accountDivision. */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/** COLUMNアノテーション accountNo. */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	/** COLUMNアノテーション dummy. */
	public static final String dummy_COLUMN = "DUMMY";

	/** COLUMNアノテーション dlDate. */
	public static final String dlDate_COLUMN = "DL_DATE";

	/** COLUMNアノテーション paymentDate. */
	public static final String paymentDate_COLUMN = "PAYMENT_DATE";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** データ区分（ヘッダーレコード：1固定） */
	private String dataDivision;

	/** 種別コード（総合：21固定） */
	private String divisionCd;

	/** コード区分（省略：0固定） */
	private String categoryDivision;

	/** 依頼人コード */
	private String transferClientCd;

	/** 依頼人名 */
	private String transferClientName;

	/** 振込指定日(月日の4桁（MMDD）) */
	private String trasferDate;

	/** 仕向金融機関番号 */
	private String bankMasterCd;

	/** 仕向金融機関名 */
	private String bankKanaName;

	/** 仕向支店番号 */
	private String branchCd;

	/** 仕向支店名 */
	private String branchKanaName;

	/** 預金種目（依頼人）1:普通 2:当座 4:貯蓄 */
	private String accountDivision;

	/** 口座番号（依頼人） */
	private String accountNo;

	/** スペース */
	private String dummy;

	/** ファイル作成日時 */
	private Timestamp dlDate;

	/** 振込日付(YYYYMMDD) */
	private String paymentDate;

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
	 * データ区分（ヘッダーレコード：1固定）取得.
	 * @return String データ区分（ヘッダーレコード：1固定）
	 */
	public String getDataDivision() {
		return this.dataDivision;
	}

	/**
	 * データ区分（ヘッダーレコード：1固定）設定.
	 * @param dataDivision データ区分（ヘッダーレコード：1固定）
	 */
	public void setDataDivision(final String dataDivision) {
		this.dataDivision = dataDivision;
	}

	/**
	 * 種別コード（総合：21固定）取得.
	 * @return String 種別コード（総合：21固定）
	 */
	public String getDivisionCd() {
		return this.divisionCd;
	}

	/**
	 * 種別コード（総合：21固定）設定.
	 * @param divisionCd 種別コード（総合：21固定）
	 */
	public void setDivisionCd(final String divisionCd) {
		this.divisionCd = divisionCd;
	}

	/**
	 * コード区分（省略：0固定）取得.
	 * @return String コード区分（省略：0固定）
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * コード区分（省略：0固定）設定.
	 * @param categoryDivision コード区分（省略：0固定）
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 依頼人コード取得.
	 * @return String 依頼人コード
	 */
	public String getTransferClientCd() {
		return this.transferClientCd;
	}

	/**
	 * 依頼人コード設定.
	 * @param transferClientCd 依頼人コード
	 */
	public void setTransferClientCd(final String transferClientCd) {
		this.transferClientCd = transferClientCd;
	}

	/**
	 * 依頼人名取得.
	 * @return String 依頼人名
	 */
	public String getTransferClientName() {
		return this.transferClientName;
	}

	/**
	 * 依頼人名設定.
	 * @param transferClientName 依頼人名
	 */
	public void setTransferClientName(final String transferClientName) {
		this.transferClientName = transferClientName;
	}

	/**
	 * 振込指定日(月日の4桁（MMDD）)取得.
	 * @return String 振込指定日(月日の4桁（MMDD）)
	 */
	public String getTrasferDate() {
		return this.trasferDate;
	}

	/**
	 * 振込指定日(月日の4桁（MMDD）)設定.
	 * @param trasferDate 振込指定日(月日の4桁（MMDD）)
	 */
	public void setTrasferDate(final String trasferDate) {
		this.trasferDate = trasferDate;
	}

	/**
	 * 仕向金融機関番号取得.
	 * @return String 仕向金融機関番号
	 */
	public String getBankMasterCd() {
		return this.bankMasterCd;
	}

	/**
	 * 仕向金融機関番号設定.
	 * @param bankMasterCd 仕向金融機関番号
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * 仕向金融機関名取得.
	 * @return String 仕向金融機関名
	 */
	public String getBankKanaName() {
		return this.bankKanaName;
	}

	/**
	 * 仕向金融機関名設定.
	 * @param bankKanaName 仕向金融機関名
	 */
	public void setBankKanaName(final String bankKanaName) {
		this.bankKanaName = bankKanaName;
	}

	/**
	 * 仕向支店番号取得.
	 * @return String 仕向支店番号
	 */
	public String getBranchCd() {
		return this.branchCd;
	}

	/**
	 * 仕向支店番号設定.
	 * @param branchCd 仕向支店番号
	 */
	public void setBranchCd(final String branchCd) {
		this.branchCd = branchCd;
	}

	/**
	 * 仕向支店名取得.
	 * @return String 仕向支店名
	 */
	public String getBranchKanaName() {
		return this.branchKanaName;
	}

	/**
	 * 仕向支店名設定.
	 * @param branchKanaName 仕向支店名
	 */
	public void setBranchKanaName(final String branchKanaName) {
		this.branchKanaName = branchKanaName;
	}

	/**
	 * 預金種目（依頼人）1:普通 2:当座 4:貯蓄取得.
	 * @return String 預金種目（依頼人）1:普通 2:当座 4:貯蓄
	 */
	public String getAccountDivision() {
		return this.accountDivision;
	}

	/**
	 * 預金種目（依頼人）1:普通 2:当座 4:貯蓄設定.
	 * @param accountDivision 預金種目（依頼人）1:普通 2:当座 4:貯蓄
	 */
	public void setAccountDivision(final String accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * 口座番号（依頼人）取得.
	 * @return String 口座番号（依頼人）
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * 口座番号（依頼人）設定.
	 * @param accountNo 口座番号（依頼人）
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * スペース取得.
	 * @return String スペース
	 */
	public String getDummy() {
		return this.dummy;
	}

	/**
	 * スペース設定.
	 * @param dummy スペース
	 */
	public void setDummy(final String dummy) {
		this.dummy = dummy;
	}

	/**
	 * ファイル作成日時を取得します。
	 * @return Timestamp ファイル作成日時
	 */
	public Timestamp getDlDate() {
		return dlDate;
	}

	/**
	 * ファイル作成日時を設定します。
	 * @param dlDate ファイル作成日時
	 */
	public void setDlDate(final Timestamp dlDate) {
		this.dlDate = dlDate;
	}

	/**
	 * 振込日付(YYYYMMDD)取得.
	 * @return String 振込日付(YYYYMMDD)
	 */
	public String getPaymentDate() {
		return this.paymentDate;
	}

	/**
	 * 振込日付(YYYYMMDD)設定.
	 * @param paymentDate 振込日付(YYYYMMDD)
	 */
	public void setPaymentDate(final String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * 登録日時取得.
	 * @return Timestamp 登録日時
	 */
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定.
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得.
	 * @return String 登録者ID
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定.
	 * @param inputorCd 登録者ID
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得.
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定.
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得.
	 * @return String 更新者ID
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定.
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
