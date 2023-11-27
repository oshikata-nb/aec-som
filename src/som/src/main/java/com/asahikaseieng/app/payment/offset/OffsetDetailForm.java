/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.offset;

// import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetDeposit;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetPayable;
import com.asahikaseieng.struts.AbstractForm;

/**
 * グループ間相殺処理詳細 Formクラス
 * @author tosco
 */
public final class OffsetDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 変更フラグ */
	private String dirtyFlg;

	/** 新規用切替フラグ */
	private int insertFlg;

	/** 参照フラグ */
	private int referFlg;

	/** 検索フラグ */
	private int searchFlg;

	/** 登録ボタンフラグ */
	private int buttonFlg;

	/** 戻り先文字列 */
	private String backForward;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String srhOrganizationName;

	/** 相殺番号 */
	private String offsetNo;

	/** 相殺グループコード */
	private String offsetGroupCd;

	/** 相殺グループ名称 */
	private String offsetGroupName;

	/** 相殺グループコードリスト */
	private List<ComboBoxItems> offsetGroupCdList;

	/** 相殺日付 */
	private String strOffsetDate;

	/** 差額 */
	private String balanceAmount;

	/** 摘要コード */
	private String srhSummaryCd;

	/** 摘要名 */
	private String srhSummary;

	/** 登録日時 */
	private java.sql.Timestamp inputDate;

	/** 登録者ＩＤ */
	private String inputorCd;

	/** 更新日時 */
	private java.sql.Timestamp updateDate;

	/** 更新者ＩＤ */
	private String updatorCd;

	/** 分類 */
	private String cassification;

	/** 分類コンボボックス内容 */
	private List<ComboBoxItems> categoryList;

	/** 分類コード */
	private String categoryDivi;

	/** 分類名称 */
	private String categoryName;

	/** 承認ステータス */
	private BigDecimal approvalStatus;

	//
	// インスタンスフィールド
	//

	/** 買掛データリスト */
	private List<OffsetPayable> payableList;

	/** 売掛データリスト */
	private List<OffsetDeposit> depositList;

	/** 買掛残合計 */
	private String strTotalPayableAmount;

	/** 売掛残合計 */
	private String strTotalCreditAmount;

	/** 買掛相殺金額 */
	private String strTotalPayableOffset;

	/** 売掛相殺金額 */
	private String strTotalDepositOffset;

	/** 買掛相殺金額 */
	private String hiddenPayableOffset;

	/** 売掛相殺金額 */
	private String hiddenDepositOffset;

	/** 買掛側タイトル */
	private String titlePayable;

	/** 売掛側タイトル */
	private String titleCredit;

	/**
	 * コンストラクタ.グループ間相殺入力詳細
	 */
	public OffsetDetailForm() {
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
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setPayableList(new ArrayList<OffsetPayable>());
			setDepositList(new ArrayList<OffsetDeposit>());
			// 摘要名クリア
			// setSrhSummary(null);
			// setSrhSummaryCd(null);
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

		// 部署コード
		setOrganizationCd(null);
		// 部署名称
		setSrhOrganizationName(null);
		// 相殺番号
		setOffsetNo(null);
		// 相殺グループ
		setOffsetGroupCd(null);
		// 相殺グループリスト
		setOffsetGroupCdList(null);
		// 相殺日付
		setStrOffsetDate(null);
		// 残高
		setBalanceAmount(null);
		// 摘要コード
		setSrhSummaryCd(null);
		// 摘要名
		setSrhSummary(null);
		// 売掛残合計
		setStrTotalCreditAmount(null);
		// 売掛相殺金額（文字列）
		setStrTotalDepositOffset(null);
		// 売掛相殺金額（数値）
		setHiddenDepositOffset(null);
		// 買掛残合計
		setStrTotalPayableAmount(null);
		// 買掛相殺金額（文字列）
		setStrTotalPayableOffset(null);
		// 買掛相殺金額（数値）
		setHiddenPayableOffset(null);
		// 分類
		setCassification(null);
		// 分類コンボボックス
		setCategoryList(null);
		// 売掛側タイトル
		setTitleCredit(null);
		// 買掛側タイトル
		setTitlePayable(null);

		// 売掛側リスト
		setDepositList(null);
		// 買掛側リスト
		setPayableList(null);

		// 新規更新切替フラグ
		setInsertFlg(0);
		// 参照フラグ
		setReferFlg(0);
		// 検索実行フラグ
		setSearchFlg(0);
		// 新規登録ボタン表示フラグ
		setButtonFlg(0);
		// 変更フラグ
		setDirtyFlg(null);

		// 遷移先設定
		setBackForward(null);
		// 承認ステータス
		setApprovalStatus(new BigDecimal("0"));
	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * 部署名称を取得します。
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return srhOrganizationName;
	}

	/**
	 * 部署名称を設定します。
	 * @param srhOrganizationName 部署名称
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
	}

	/**
	 * 相殺番号を取得します。
	 * @return offsetNo
	 */
	public String getOffsetNo() {
		return offsetNo;
	}

	/**
	 * 相殺番号を設定します。
	 * @param offsetNo 相殺番号
	 */
	public void setOffsetNo(final String offsetNo) {
		this.offsetNo = offsetNo;
	}

	/**
	 * 分類を取得します。
	 * @return srhCassification
	 */
	public String getCassification() {
		return cassification;
	}

	/**
	 * 分類を設定します。
	 * @param cassification 分類
	 */
	public void setCassification(final String cassification) {
		this.cassification = cassification;
	}

	/**
	 * 分類コンボボックス内容を取得します。
	 * @return 分類コンボボックス内容
	 */
	public List<ComboBoxItems> getCategoryList() {
		return categoryList;
	}

	/**
	 * 分類コンボボックス内容を設定します。
	 * @param categoryList 分類コンボボックス内容
	 */
	public void setCategoryList(final List<ComboBoxItems> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * 分類コードを取得します。
	 * @return categoryDivi
	 */
	public String getCategoryDivi() {
		return categoryDivi;
	}

	/**
	 * 分類コードを設定します。
	 * @param categoryDivi 分類コード
	 */
	public void setCategoryDivi(final String categoryDivi) {
		this.categoryDivi = categoryDivi;
	}

	/**
	 * 分類名称を取得します。
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分類名称を設定します。
	 * @param categoryName 分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 相殺グループコードを取得します。
	 * @return offsetGroupCd
	 */
	public String getOffsetGroupCd() {
		return offsetGroupCd;
	}

	/**
	 * 相殺グループコードを設定します。
	 * @param offsetGroupCd 相殺グループコード
	 */
	public void setOffsetGroupCd(final String offsetGroupCd) {
		this.offsetGroupCd = offsetGroupCd;
	}

	/**
	 * 相殺グループ名称を取得します。
	 * @return offsetGroupName
	 */
	public String getOffsetGroupName() {
		return offsetGroupName;
	}

	/**
	 * 相殺グループ名称を設定します。
	 * @param offsetGroupName 相殺グループ名称
	 */
	public void setOffsetGroupName(final String offsetGroupName) {
		this.offsetGroupName = offsetGroupName;
	}

	/**
	 * 相殺グループコードリストを取得します。
	 * @return offsetGroupCdList
	 */
	public List<ComboBoxItems> getOffsetGroupCdList() {
		return offsetGroupCdList;
	}

	/**
	 * 相殺グループコードリストを設定します。
	 * @param offsetGroupCdList 相殺グループコードリスト
	 */
	public void setOffsetGroupCdList(final List<ComboBoxItems> offsetGroupCdList) {
		this.offsetGroupCdList = offsetGroupCdList;
	}

	/**
	 * 差額を取得します。
	 * @return balanceAmount
	 */
	public String getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * 差額を設定します。
	 * @param balanceAmount 差額
	 */
	public void setBalanceAmount(final String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 * 摘要コードを取得します。
	 * @return srhSummaryCd
	 */
	public String getSrhSummaryCd() {
		return srhSummaryCd;
	}

	/**
	 * 摘要コードを設定します。
	 * @param srhSummaryCd 摘要コード
	 */
	public void setSrhSummaryCd(final String srhSummaryCd) {
		this.srhSummaryCd = srhSummaryCd;
	}

	/**
	 * 摘要名を取得します。
	 * @return srhSummary
	 */
	public String getSrhSummary() {
		return srhSummary;
	}

	/**
	 * 摘要名を設定します。
	 * @param srhSummary 摘要名
	 */
	public void setSrhSummary(final String srhSummary) {
		this.srhSummary = srhSummary;
	}

	/**
	 * 売掛リストを取得します。
	 * @return depositList
	 */
	public List<OffsetDeposit> getDepositList() {
		return depositList;
	}

	/**
	 * 売掛リストを設定します。
	 * @param depositList 売掛リスト
	 */
	public void setDepositList(final List<OffsetDeposit> depositList) {
		this.depositList = depositList;
	}

	/**
	 * 買掛リストを取得します。
	 * @return payableList
	 */
	public List<OffsetPayable> getPayableList() {
		return payableList;
	}

	/**
	 * 買掛リストを設定します。
	 * @param payableList 買掛リスト
	 */
	public void setPayableList(final List<OffsetPayable> payableList) {
		this.payableList = payableList;
	}

	/**
	 * 買掛相殺金額を取得します。
	 * @return strTotalPayableOffset
	 */
	public String getStrTotalPayableOffset() {
		return strTotalPayableOffset;
	}

	/**
	 * 買掛相殺金額を設定します。
	 * @param strTotalPayableOffset 買掛相殺金額
	 */
	public void setStrTotalPayableOffset(final String strTotalPayableOffset) {
		this.strTotalPayableOffset = strTotalPayableOffset;
	}

	/**
	 * 売掛相殺金額を取得します。
	 * @return strTotalDepositOffset
	 */
	public String getStrTotalDepositOffset() {
		return strTotalDepositOffset;
	}

	/**
	 * 売掛相殺金額を設定します。
	 * @param strTotalDepositOffset 売掛相殺金額
	 */
	public void setStrTotalDepositOffset(final String strTotalDepositOffset) {
		this.strTotalDepositOffset = strTotalDepositOffset;
	}

	/**
	 * 売掛残合計を取得します。
	 * @return strTotalCreditAmount
	 */
	public String getStrTotalCreditAmount() {
		return strTotalCreditAmount;
	}

	/**
	 * 売掛残合計を設定します。
	 * @param strTotalCreditAmount 売掛残合計
	 */
	public void setStrTotalCreditAmount(final String strTotalCreditAmount) {
		this.strTotalCreditAmount = strTotalCreditAmount;
	}

	/**
	 * 買掛残合計を取得します。
	 * @return strTotalPayableAmount
	 */
	public String getStrTotalPayableAmount() {
		return strTotalPayableAmount;
	}

	/**
	 * 買掛残合計を設定します。
	 * @param strTotalPayableAmount 買掛残合計
	 */
	public void setStrTotalPayableAmount(final String strTotalPayableAmount) {
		this.strTotalPayableAmount = strTotalPayableAmount;
	}

	/**
	 * 戻り先文字列を取得します。
	 * @return backForward
	 */
	public String getBackForward() {
		return backForward;
	}

	/**
	 * 売掛側タイトルを取得します。
	 * @return titleCredit
	 */
	public String getTitleCredit() {
		return titleCredit;
	}

	/**
	 * 売掛側タイトルを設定します。
	 * @param titleCredit 売掛側タイトル
	 */
	public void setTitleCredit(final String titleCredit) {
		this.titleCredit = titleCredit;
	}

	/**
	 * 買掛側タイトルを取得します。
	 * @return titlePayable
	 */
	public String getTitlePayable() {
		return titlePayable;
	}

	/**
	 * 買掛側タイトルを設定します。
	 * @param titlePayable 買掛側タイトル
	 */
	public void setTitlePayable(final String titlePayable) {
		this.titlePayable = titlePayable;
	}

	/**
	 * 戻り先文字列を設定します。
	 * @param backForward 戻り先文字列
	 */
	public void setBackForward(final String backForward) {
		this.backForward = backForward;
	}

	/**
	 * inTotalDepositOffsetを取得します。
	 * @return hiddenDepositOffset
	 */
	public String getHiddenDepositOffset() {
		return hiddenDepositOffset;
	}

	/**
	 * inTotalDepositOffsetを設定します。
	 * @param hiddenDepositOffset hiddenDepositOffset
	 */
	public void setHiddenDepositOffset(final String hiddenDepositOffset) {
		this.hiddenDepositOffset = hiddenDepositOffset;
	}

	/**
	 * inTotalPayableOffsetを取得します。
	 * @return hiddenPayableOffset
	 */
	public String getHiddenPayableOffset() {
		return hiddenPayableOffset;
	}

	/**
	 * inTotalPayableOffsetを設定します。
	 * @param hiddenPayableOffset hiddenPayableOffset
	 */
	public void setHiddenPayableOffset(final String hiddenPayableOffset) {
		this.hiddenPayableOffset = hiddenPayableOffset;
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
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
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
	 * referFlgを取得します。
	 * @return referFlg
	 */
	public int getReferFlg() {
		return referFlg;
	}

	/**
	 * referFlgを設定します。
	 * @param referFlg referFlg
	 */
	public void setReferFlg(final int referFlg) {
		this.referFlg = referFlg;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * insertFlgを取得します。
	 * @return insertFlg
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * insertFlgを設定します。
	 * @param insertFlg insertFlg
	 */
	public void setInsertFlg(final int insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * searchFlgを取得します。
	 * @return searchFlg
	 */
	public int getSearchFlg() {
		return searchFlg;
	}

	/**
	 * searchFlgを設定します。
	 * @param searchFlg searchFlg
	 */
	public void setSearchFlg(final int searchFlg) {
		this.searchFlg = searchFlg;
	}

	/**
	 * buttonFlgを取得します。
	 * @return buttonFlg
	 */
	public int getButtonFlg() {
		return buttonFlg;
	}

	/**
	 * buttonFlgを設定します。
	 * @param buttonFlg buttonFlg
	 */
	public void setButtonFlg(final int buttonFlg) {
		this.buttonFlg = buttonFlg;
	}

	/**
	 * approvalStatusを取得します。
	 * @return approvalStatus
	 */
	public BigDecimal getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * approvalStatusを設定します。
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * strOffsetDateを取得します。
	 * @return strOffsetDate
	 */
	public String getStrOffsetDate() {
		return strOffsetDate;
	}

	/**
	 * strOffsetDateを設定します。
	 * @param strOffsetDate strOffsetDate
	 */
	public void setStrOffsetDate(final String strOffsetDate) {
		this.strOffsetDate = strOffsetDate;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}
}
