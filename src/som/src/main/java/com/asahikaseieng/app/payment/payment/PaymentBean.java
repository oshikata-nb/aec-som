/*
 * Created on 2008/08/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.SelectAccountDivision;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentBase;

import org.apache.commons.lang.StringUtils;

/**
 * 支払入力用画面表示用1行クラス
 * @author tosco
 */
public class PaymentBean extends AltPaymentBase {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 預金種別コンボボックス */
	private static final SelectAccountDivision combo = new SelectAccountDivision(false, true);

	/** 補助科目コンボボックス */
	private List<ComboBoxItems> subAccounts;
	/** 削除フラグ */
	private boolean deleteFlag;
	/** 銀行本店＋支店名 */
	private String bankName;
	/** 銀行必須区分 */
	private int bankDivision;
	/** 読取入金分類 */
	private String viewCategoryDivision;
	/** 読取預金種別 */
	private String viewAccountDivision;
	/** 読取科目 */
	private String viewCreditTitleCd;
	/** 読取補助科目 */
	private String viewCreditSubTitleCd;
	/** 分類マスタ-データ集計区分 */
	private int classificationDataTotalDivision;

	/**
	 * コンストラクタ
	 */
	public PaymentBean() {
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
	 * 銀行本店＋支店名を取得します。
	 * @return 銀行本店＋支店名
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * 銀行本店＋支店名を設定します。
	 * @param bankName 銀行本店＋支店名
	 */
	public void setBankName(final String bankName) {
		this.bankName = bankName;
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
		if (StringUtils.isNotEmpty(getAccountDivision())) {
			viewAccountDivision = getAccountDivision() + ":" + combo.getLabel(getAccountDivision());
		} else {
			viewAccountDivision = "";
		}
		return viewAccountDivision;
	}
//	/**
//	 * 読取預金種別を設定します。
//	 * @param viewAccountDivision 読取預金種別
//	 */
//	public void setViewAccountDivision(final String viewAccountDivision) {
//		if (StringUtils.isNotEmpty(getAccountDivision())) {
//			this.viewAccountDivision = getAccountDivision() + ":" + combo.getLabel(getAccountDivision());
//		}
//	}
	/**
	 * 読取入金分類を取得します。
	 * @return 読取入金分類
	 */
	public String getViewCategoryDivision() {
		return viewCategoryDivision;
	}
	/**
	 * 読取入金分類を設定します。
	 * @param viewCategoryDivision 読取入金分類
	 */
	public void setViewCategoryDivision(final String viewCategoryDivision) {
		this.viewCategoryDivision = viewCategoryDivision;
	}
	/**
	 * 読取補助科目を取得します。
	 * @return 読取補助科目
	 */
	public String getViewCreditSubTitleCd() {
		return viewCreditSubTitleCd;
	}
	/**
	 * 読取補助科目を設定します。
	 * @param viewCreditSubTitleCd 読取補助科目
	 */
	public void setViewCreditSubTitleCd(final String viewCreditSubTitleCd) {
		this.viewCreditSubTitleCd = viewCreditSubTitleCd;
	}
	/**
	 * 読取科目を取得します。
	 * @return 読取科目
	 */
	public String getViewCreditTitleCd() {
		return viewCreditTitleCd;
	}
	/**
	 * 読取科目を設定します。
	 * @param viewCreditTitleCd 読取科目
	 */
	public void setViewCreditTitleCd(final String viewCreditTitleCd) {
		this.viewCreditTitleCd = viewCreditTitleCd;
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
}
