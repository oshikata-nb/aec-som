/*
 * Created on 2008/08/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCreditBase;

/**
 * 入金入力（リスト）用画面表示用1行クラス
 * @author tosco
 */
public class CreditBean extends DepositCreditBase {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 補助科目コンボボックス */
	private List<ComboBoxItems> subAccounts;

	/** 削除フラグ */
	private boolean deleteFlag;

	/** 払出日文字列 */
	private String drawalDateString;

	/** 満期日文字列 */
	private String noteDateString;

	/** 銀行必須区分 */
	private int bankDivision;

	/** 手形種別 */
	// private int noteDivision;
	private int checkNoteDivision;

	/** 読取入金分類 */
	private String viewCategoryDivision;

	/** 読取預金種別 */
	private String viewAccountDivision;

	/** 読取科目 */
	private String viewDebitTitle;

	/** 読取補助科目 */
	private String viewDebitSubTitle;

	/** 分類マスタ-データ集計区分 */
	private int classificationDataTotalDivision;

	/** 勘定科目内容 */
	private String accountsCdName;

	/** 銀行名称 */
	private String[] bankCdValues;

	private String[] bankCdLabels;

	/** 前受金区分 */
	private BigDecimal advanceDivision;

	/**
	 * 払出日文字列を取得します。
	 * @return 払出日文字列
	 */
	public String getDrawalDateString() {
		return drawalDateString;
	}

	/**
	 * 払出日文字列を設定します。
	 * @param drawalDateString 払出日文字列
	 */
	public void setDrawalDateString(final String drawalDateString) {
		this.drawalDateString = drawalDateString;
	}

	/**
	 * 満期日文字列を取得します。
	 * @return 満期日文字列
	 */
	public String getNoteDateString() {
		return noteDateString;
	}

	/**
	 * 満期日文字列を設定します。
	 * @param noteDateString 満期日文字列
	 */
	public void setNoteDateString(final String noteDateString) {
		this.noteDateString = noteDateString;
	}

	/**
	 * コンストラクタ
	 */
	public CreditBean() {
	}

	/**
	 * 補助科目コンボボックスを取得します。
	 * @return 補助科目コンボボックス
	 */
	public List<ComboBoxItems> getSubAccounts() {
		return subAccounts;
	}

	/**
	 * 補助科目コンボボックスを設定します。
	 * @param subAccounts 補助科目コンボボックス
	 */
	public void setSubAccounts(final List<ComboBoxItems> subAccounts) {
		this.subAccounts = subAccounts;
	}

	/**
	 * 削除フラグを取得します。
	 * @return 削除フラグ
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * 削除フラグを設定します。
	 * @param deleteFlag 削除フラグ
	 */
	public void setDeleteFlag(final boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * 銀行必須区分を取得します。
	 * @return 銀行必須区分
	 */
	public int getBankDivision() {
		return bankDivision;
	}

	/**
	 * 銀行必須区分を設定します。
	 * @param bankDivision 銀行必須区分
	 */
	public void setBankDivision(final int bankDivision) {
		this.bankDivision = bankDivision;
	}

	/**
	 * 読取預金種別を取得します。
	 * @return 読取預金種別
	 */
	public String getViewAccountDivision() {
		return viewAccountDivision;
	}

	/**
	 * 読取預金種別を設定します。
	 * @param viewAccountDivision 読取預金種別
	 */
	public void setViewAccountDivision(final String viewAccountDivision) {
		this.viewAccountDivision = viewAccountDivision;
	}

	/**
	 * 読取補助科目を取得します。
	 * @return 読取補助科目
	 */
	public String getViewDebitSubTitle() {
		return viewDebitSubTitle;
	}

	/**
	 * 読取補助科目を設定します。
	 * @param viewDebitSubTitle 読取補助科目
	 */
	public void setViewDebitSubTitle(final String viewDebitSubTitle) {
		this.viewDebitSubTitle = viewDebitSubTitle;
	}

	/**
	 * 読取科目を取得します。
	 * @return 読取科目
	 */
	public String getViewDebitTitle() {
		return viewDebitTitle;
	}

	/**
	 * 読取科目を設定します。
	 * @param viewDebitTitle 読取科目
	 */
	public void setViewDebitTitle(final String viewDebitTitle) {
		this.viewDebitTitle = viewDebitTitle;
	}

	/**
	 * 補助科目コード:補助科目名を取得する。
	 * @param value 補助科目コード
	 * @return 補助科目コード:補助科目名
	 */
	public String getSubAccountLabel(final String value) {
		String label = null;
		for (ComboBoxItems item : subAccounts) {
			if (item.getValues().equals(value)) {
				label = item.getLabales();
			}
		}
		if (StringUtils.isEmpty(label)) {
			label = value;
		}
		return label;
	}

	/**
	 * 分類マスタ-データ集計区分を取得します。
	 * @return 分類マスタ-データ集計区分
	 */
	public int getClassificationDataTotalDivision() {
		return classificationDataTotalDivision;
	}

	/**
	 * 分類マスタ-データ集計区分を設定します。
	 * @param classificationDataTotalDivision 分類マスタ-データ集計区分
	 */
	public void setClassificationDataTotalDivision(
			final int classificationDataTotalDivision) {
		this.classificationDataTotalDivision = classificationDataTotalDivision;
	}

	/**
	 * viewCategoryDivisionを取得します。
	 * @return viewCategoryDivision
	 */
	public String getViewCategoryDivision() {
		return viewCategoryDivision;
	}

	/**
	 * viewCategoryDivisionを設定します。
	 * @param viewCategoryDivision viewCategoryDivision
	 */
	public void setViewCategoryDivision(final String viewCategoryDivision) {
		this.viewCategoryDivision = viewCategoryDivision;
	}

	/**
	 * accountsCdNameを取得します。
	 * @return accountsCdName
	 */
	public String getAccountsCdName() {
		return accountsCdName;
	}

	/**
	 * accountsCdNameを設定します。
	 * @param accountsCdName accountsCdName
	 */
	public void setAccountsCdName(final String accountsCdName) {
		this.accountsCdName = accountsCdName;
	}

	// /**
	// * noteDivisionを取得します。
	// * @return noteDivision
	// */
	// public int getNoteDivision() {
	// return noteDivision;
	// }
	//
	// /**
	// * noteDivisionを設定します。
	// * @param noteDivision noteDivision
	// */
	// public void setNoteDivision(final int noteDivision) {
	// this.noteDivision = noteDivision;
	// }

	/**
	 * checkNoteDivisionを取得します。
	 * @return checkNoteDivision
	 */
	public int getCheckNoteDivision() {
		return checkNoteDivision;
	}

	/**
	 * checkNoteDivisionを設定します。
	 * @param checkNoteDivision checkNoteDivision
	 */
	public void setCheckNoteDivision(final int checkNoteDivision) {
		this.checkNoteDivision = checkNoteDivision;
	}

	/**
	 * bankCdLabelsを取得します。
	 * @return bankCdLabels
	 */
	public String[] getBankCdLabels() {
		return bankCdLabels;
	}

	/**
	 * bankCdLabelsを設定します。
	 * @param bankCdLabels bankCdLabels
	 */
	public void setBankCdLabels(final String[] bankCdLabels) {
		this.bankCdLabels = bankCdLabels;
	}

	/**
	 * bankCdValuesを取得します。
	 * @return bankCdValues
	 */
	public String[] getBankCdValues() {
		return bankCdValues;
	}

	/**
	 * bankCdValuesを設定します。
	 * @param bankCdValues bankCdValues
	 */
	public void setBankCdValues(final String[] bankCdValues) {
		this.bankCdValues = bankCdValues;
	}

	/**
	 * advanceDivisionを取得します。
	 * @return advanceDivision
	 */
	public BigDecimal getAdvanceDivision() {
		return advanceDivision;
	}

	/**
	 * advanceDivisionを設定します。
	 * @param advanceDivision advanceDivision
	 */
	public void setAdvanceDivision(final BigDecimal advanceDivision) {
		this.advanceDivision = advanceDivision;
	}
}
