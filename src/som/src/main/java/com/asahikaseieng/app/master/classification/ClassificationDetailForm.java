/*
 * Created on 2008/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.classification;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 分類マスタ詳細 Formクラス
 * @author tosco
 */
public final class ClassificationDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド

	//

	/** データ種別 */
	private BigDecimal dataType;

	/** データ集計区分 */
	private BigDecimal dataTotalDivision;

	/** データ種別：表示用 */
	private String textDataType;

	/** データ集計区分:表示用 */
	private String textDataTotalDivision;

	/** 分類コード */
	private String categoryDivision;

	/** 分類名称 */
	private String categoryName;

	/** 対外分類名称 */
	private String externalCategoryName;

	/** 借方勘定科目 */
	private String debitAccountsCd;

	/** 借方補助科目 */
	private String debitSubAccountsCd;

	/** 貸方勘定科目 */
	private String creditAccountsCd;

	/** 貸方補助科目 */
	private String creditSubAccountsCd;

	/** 売掛対象区分 */
	private String arDivision;

	/** 請求対象区分 */
	private String claimDivision;

	/** 買掛対象区分 */
	private String creditDivision;

	/** 支払対象区分 */
	private String paymentDivision;

	/** 銀行必須区分 */
	private String bankDivision;

	/** 手形必須区分 */
	private String noteDivision;

	/** 登録日時 */
	private java.sql.Timestamp inputDate;

	/** 登録者ＩＤ */
	private String inputorCd;

	/** 更新日時 */
	private java.sql.Timestamp updateDate;

	/** 更新者ＩＤ */
	private String updatorCd;

	/** 借方勘定科目リスト(コンボボックス) */
	private List<ComboBoxItems> debitAccountsCdList;

	/** 借方補助科目リスト(コンボボックス) */
	private List<ComboBoxItems> debitSubAccountsCdList;

	/** 貸方勘定科目リスト(コンボボックス) */
	private List<ComboBoxItems> creditAccountsCdList;

	/** 貸方補助科目リスト(コンボボックス) */
	private List<ComboBoxItems> creditSubAccountsCdList;

	// 新規用切替フラグ
	private int insertFlg;

	/** データ集計区分リスト(コンボボックス) */
	private List<ComboBoxItems> totalDivisionList;

	/**
	 * コンストラクタ.分類マスタ詳細
	 */
	public ClassificationDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
		if ("initNew".equals(getOp())) {
			clear();
		}
		if (("update".equals(getOp())) || ("insert".equals(getOp()))) {
			/* チェックボックスの初期化 */
			/* 売掛対象区分 */
			setArDivision(null);
			/* 請求対象区分 */
			setClaimDivision(null);
			/* 買掛対象区分 */
			setCreditDivision(null);
			/* 支払対象区分 */
			setPaymentDivision(null);
			/* 銀行必須区分 */
			setBankDivision(null);
			/* 手形必須区分 */
			setNoteDivision(null);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		/* データ種別 */
		setDataType(null);
		/* データ集計区分 */
		setDataTotalDivision(null);
		/* 分類コード */
		setCategoryDivision(null);
		/* 分類名称 */
		setCategoryName(null);
		/* 対外分類名称 */
		setExternalCategoryName(null);
		/* 借方勘定科目 */
		setDebitAccountsCd(null);
		/* 借方補助科目 */
		setDebitSubAccountsCd(null);
		/* 貸方勘定科目 */
		setCreditAccountsCd(null);
		/* 貸方補助科目 */
		setCreditSubAccountsCd(null);
		/* 売掛対象区分 */
		setArDivision(null);
		/* 請求対象区分 */
		setClaimDivision(null);
		/* 買掛対象区分 */
		setCreditDivision(null);
		/* 支払対象区分 */
		setPaymentDivision(null);
		/* 銀行必須区分 */
		setBankDivision(null);
		/* 手形必須区分 */
		setNoteDivision(null);

		/* 登録日時 */
		setInputDate(null);
		/* 登録者ＩＤ */
		setInputorCd(null);
		/* 更新日時 */
		setUpdateDate(null);
		/* 更新者ＩＤ */
		setUpdatorCd(null);

		// 新規更新切替フラグ
		setInsertFlg(0);

		// データ集計区分リスト
		setTotalDivisionList(null);
	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * insertFlgを取得します。
	 * 
	 * @return insertFlg
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * insertFlgを設定します。
	 * 
	 * @param insertFlg insertFlg
	 */
	public void setInsertFlg(final int insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * arDivisionを取得します。
	 * @return arDivision
	 */
	public String getArDivision() {
		return arDivision;
	}

	/**
	 * arDivisionを設定します。
	 * @param arDivision arDivision
	 */
	public void setArDivision(final String arDivision) {
		this.arDivision = arDivision;
	}

	/**
	 * bankDivisionを取得します。
	 * @return bankDivision
	 */
	public String getBankDivision() {
		return bankDivision;
	}

	/**
	 * bankDivisionを設定します。
	 * @param bankDivision bankDivision
	 */
	public void setBankDivision(final String bankDivision) {
		this.bankDivision = bankDivision;
	}

	/**
	 * categoryDivisionを取得します。
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * categoryDivisionを設定します。
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * categoryNameを取得します。
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * categoryNameを設定します。
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * claimDivisionを取得します。
	 * @return claimDivision
	 */
	public String getClaimDivision() {
		return claimDivision;
	}

	/**
	 * claimDivisionを設定します。
	 * @param claimDivision claimDivision
	 */
	public void setClaimDivision(final String claimDivision) {
		this.claimDivision = claimDivision;
	}

	/**
	 * creditAccountsCdを取得します。
	 * @return creditAccountsCd
	 */
	public String getCreditAccountsCd() {
		return creditAccountsCd;
	}

	/**
	 * creditAccountsCdを設定します。
	 * @param creditAccountsCd creditAccountsCd
	 */
	public void setCreditAccountsCd(final String creditAccountsCd) {
		this.creditAccountsCd = creditAccountsCd;
	}

	/**
	 * creditDivisionを取得します。
	 * @return creditDivision
	 */
	public String getCreditDivision() {
		return creditDivision;
	}

	/**
	 * creditDivisionを設定します。
	 * @param creditDivision creditDivision
	 */
	public void setCreditDivision(final String creditDivision) {
		this.creditDivision = creditDivision;
	}

	/**
	 * creditSubAccountsCdを取得します。
	 * @return creditSubAccountsCd
	 */
	public String getCreditSubAccountsCd() {
		return creditSubAccountsCd;
	}

	/**
	 * creditSubAccountsCdを設定します。
	 * @param creditSubAccountsCd creditSubAccountsCd
	 */
	public void setCreditSubAccountsCd(final String creditSubAccountsCd) {
		this.creditSubAccountsCd = creditSubAccountsCd;
	}

	/**
	 * debitAccountsCdを取得します。
	 * @return debitAccountsCd
	 */
	public String getDebitAccountsCd() {
		return debitAccountsCd;
	}

	/**
	 * debitAccountsCdを設定します。
	 * @param debitAccountsCd debitAccountsCd
	 */
	public void setDebitAccountsCd(final String debitAccountsCd) {
		this.debitAccountsCd = debitAccountsCd;
	}

	/**
	 * debitSubAccountsCdを取得します。
	 * @return debitSubAccountsCd
	 */
	public String getDebitSubAccountsCd() {
		return debitSubAccountsCd;
	}

	/**
	 * debitSubAccountsCdを設定します。
	 * @param debitSubAccountsCd debitSubAccountsCd
	 */
	public void setDebitSubAccountsCd(final String debitSubAccountsCd) {
		this.debitSubAccountsCd = debitSubAccountsCd;
	}

	/**
	 * noteDivisionを取得します。
	 * @return noteDivision
	 */
	public String getNoteDivision() {
		return noteDivision;
	}

	/**
	 * noteDivisionを設定します。
	 * @param noteDivision noteDivision
	 */
	public void setNoteDivision(final String noteDivision) {
		this.noteDivision = noteDivision;
	}

	/**
	 * paymentDivisionを取得します。
	 * @return paymentDivision
	 */
	public String getPaymentDivision() {
		return paymentDivision;
	}

	/**
	 * paymentDivisionを設定します。
	 * @param paymentDivision paymentDivision
	 */
	public void setPaymentDivision(final String paymentDivision) {
		this.paymentDivision = paymentDivision;
	}

	/**
	 * creditAccountsCdListを取得します。
	 * @return creditAccountsCdList
	 */
	public List<ComboBoxItems> getCreditAccountsCdList() {
		return creditAccountsCdList;
	}

	/**
	 * creditAccountsCdListを設定します。
	 * @param creditAccountsCdList creditAccountsCdList
	 */
	public void setCreditAccountsCdList(
			final List<ComboBoxItems> creditAccountsCdList) {
		this.creditAccountsCdList = creditAccountsCdList;
	}

	/**
	 * creditSubAccountsCdListを取得します。
	 * @return creditSubAccountsCdList
	 */
	public List<ComboBoxItems> getCreditSubAccountsCdList() {
		return creditSubAccountsCdList;
	}

	/**
	 * creditSubAccountsCdListを設定します。
	 * @param creditSubAccountsCdList creditSubAccountsCdList
	 */
	public void setCreditSubAccountsCdList(
			final List<ComboBoxItems> creditSubAccountsCdList) {
		this.creditSubAccountsCdList = creditSubAccountsCdList;
	}

	/**
	 * debitAccountsCdListを取得します。
	 * @return debitAccountsCdList
	 */
	public List<ComboBoxItems> getDebitAccountsCdList() {
		return debitAccountsCdList;
	}

	/**
	 * debitAccountsCdListを設定します。
	 * @param debitAccountsCdList debitAccountsCdList
	 */
	public void setDebitAccountsCdList(
			final List<ComboBoxItems> debitAccountsCdList) {
		this.debitAccountsCdList = debitAccountsCdList;
	}

	/**
	 * debitSubAccountsCdListを取得します。
	 * @return debitSubAccountsCdList
	 */
	public List<ComboBoxItems> getDebitSubAccountsCdList() {
		return debitSubAccountsCdList;
	}

	/**
	 * debitSubAccountsCdListを設定します。
	 * @param debitSubAccountsCdList debitSubAccountsCdList
	 */
	public void setDebitSubAccountsCdList(
			final List<ComboBoxItems> debitSubAccountsCdList) {
		this.debitSubAccountsCdList = debitSubAccountsCdList;
	}

	/**
	 * textDataTypeを取得します。
	 * @return textDataType
	 */
	public String getTextDataType() {
		return textDataType;
	}

	/**
	 * textDataTypeを設定します。
	 * @param textDataType textDataType
	 */
	public void setTextDataType(final String textDataType) {
		this.textDataType = textDataType;
	}

	/**
	 * textDataTotalDivisionを取得します。
	 * @return textDataTotalDivision
	 */
	public String getTextDataTotalDivision() {
		return textDataTotalDivision;
	}

	/**
	 * textDataTotalDivisionを設定します。
	 * @param textDataTotalDivision textDataTotalDivision
	 */
	public void setTextDataTotalDivision(final String textDataTotalDivision) {
		this.textDataTotalDivision = textDataTotalDivision;
	}

	/**
	 * externalCategoryNameを取得します。
	 * @return externalCategoryName
	 */
	public String getExternalCategoryName() {
		return externalCategoryName;
	}

	/**
	 * externalCategoryNameを設定します。
	 * @param externalCategoryName externalCategoryName
	 */
	public void setExternalCategoryName(final String externalCategoryName) {
		this.externalCategoryName = externalCategoryName;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * データ集計区分リスト(コンボボックス)を取得します。
	 * @return データ集計区分リスト(コンボボックス)
	 */
	public List<ComboBoxItems> getTotalDivisionList() {
		return totalDivisionList;
	}

	/**
	 * データ集計区分リスト(コンボボックス)を設定します。
	 * @param totalDivisionList データ集計区分リスト(コンボボックス)
	 */
	public void setTotalDivisionList(final List<ComboBoxItems> totalDivisionList) {
		this.totalDivisionList = totalDivisionList;
	}

	/**
	 * dataTotalDivisionを取得します。
	 * @return dataTotalDivision
	 */
	public BigDecimal getDataTotalDivision() {
		return dataTotalDivision;
	}

	/**
	 * dataTotalDivisionを設定します。
	 * @param dataTotalDivision dataTotalDivision
	 */
	public void setDataTotalDivision(final BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
	}

	/**
	 * dataTypeを取得します。
	 * @return dataType
	 */
	public BigDecimal getDataType() {
		return dataType;
	}

	/**
	 * dataTypeを設定します。
	 * @param dataType dataType
	 */
	public void setDataType(final BigDecimal dataType) {
		this.dataType = dataType;
	}
}
