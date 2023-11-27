/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.deposit;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCredit;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCreditListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 入金入力 Formクラス
 * @author tosco
 */
public final class DepositListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用.入金入力

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhOrganizationName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：入金日付FROM */
	private String srhCreditDateFrom;

	/** 検索入力：入金日付TO */
	private String srhCreditDateTo;

	/** 検索入力：請求先区分 */
	private String venderDivision;

	/** 検索入力：請求先コード */
	private String srhVenderCd;

	/** 検索入力：請求先名称 */
	private String srhVenderName1;

	/** 検索入力：入金番号FROM */
	private String srhSlipNoFrom;

	/** 検索入力：入金番号TO */
	private String srhSlipNoTo;

	/** 検索入力：入金分類 */
	private String srhCategoryDivision;

	/** 出力区分 */
	private String srhOutputDivision;

	/** 伝票発行チェック */
	private boolean srhIssuedCheck;

	/** 入金分類コンボボックス内容 */
	private List<ComboBoxItems> categoryList;

	/** 帳票Excel用検索条件 */
	private DepositCreditListConditionForReport condition;

	/** リスト */
	private List<DepositCredit> searchList;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.入金入力
	 */
	public DepositListForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return DepositCreditListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		// 伝票発行チェックボックスクリア
		srhIssuedCheck = false;

		if ("init".equals(getOp())) {
			clear();
		}

		if ("search".equals(getOp()) || "report".equals(getOp())) {
			/* チェックボックスクリア処理 */
			clearCheck();
		}

		// Excelダウンロードフラグ
		setExcelDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<DepositCredit>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.入金入力
	 */
	public void clear() {

		/* 検索リストのクリア */
		setSearchList(new ArrayList<DepositCredit>());

		/* 検索入力：部署コード */
		setSrhOrganizationCd(null);
		/* 検索入力：部署名称 */
		setSrhOrganizationName(null);
		/* 検索入力：担当者コード */
		setSrhTantoCd(null);
		/* 検索入力：担当者名称 */
		setSrhTantoNm(null);
		/* 検索入力：入金日付FROM */
		setSrhCreditDateFrom(null);
		/* 検索入力：入金日付TO */
		setSrhCreditDateTo(null);
		/* 検索入力：請求先区分 */
		setVenderDivision("TS");
		/* 検索入力：請求先コード */
		setSrhVenderCd(null);
		/* 検索入力：請求先名称 */
		setSrhVenderName1(null);
		/* 検索入力：入金番号FROM */
		setSrhSlipNoFrom(null);
		/* 検索入力：入金番号TO */
		setSrhSlipNoTo(null);
		/* 検索入力：入金区分 */
		setSrhCategoryDivision(null);
		/* 検索入力：出力区分 */
		setSrhOutputDivision(null);
		/* 検索入力：伝票発行チェック */
		setSrhIssuedCheck(false);
		/* 検索条件 */
		setCondition(null);
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (DepositCredit bean : getSearchList()) {
				bean.setChecked(Boolean.FALSE);
			}
		}
	}

	/**
	 * 入金入力：searchListを取得します。
	 * 
	 * @return searchList
	 */
	public List<DepositCredit> getSearchList() {
		return searchList;
	}

	/**
	 * 入金入力：searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<DepositCredit> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.入金入力

	/**
	 * 検索入力：担当者コードを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * 検索入力：担当者コードを設定します。
	 * @param srhTantoCd 検索入力：担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = srhTantoCd;
	}

	/**
	 * 検索入力：担当者名称を取得します。
	 * @return srhTantoNm
	 */
	public String getSrhTantoNm() {
		return srhTantoNm;
	}

	/**
	 * 検索入力：担当者名称を設定します。
	 * @param srhTantoNm 検索入力：担当者名称
	 */
	public void setSrhTantoNm(final String srhTantoNm) {
		this.srhTantoNm = srhTantoNm;
	}

	/**
	 * 検索入力：入金日付FROM取得.
	 * @return String 入金日付FROM
	 */
	public String getSrhCreditDateFrom() {
		return srhCreditDateFrom;
	}

	/**
	 * 検索入力：入金日付FROM設定.
	 * @param srhCreditDateFrom 入金日付FROM
	 */
	public void setSrhCreditDateFrom(final String srhCreditDateFrom) {
		this.srhCreditDateFrom = srhCreditDateFrom;
	}

	/**
	 * 検索入力：入金日付TO取得.
	 * @return String 入金日付TO
	 */
	public String getSrhCreditDateTo() {
		return srhCreditDateTo;
	}

	/**
	 * 検索入力：入金日付TO設定.
	 * @param srhCreditDateTo 入金日付TO
	 */
	public void setSrhCreditDateTo(final String srhCreditDateTo) {
		this.srhCreditDateTo = srhCreditDateTo;
	}

	/**
	 * 検索入力：入金番号FROM取得.
	 * @return String 入金番号FROM
	 */
	public String getSrhSlipNoFrom() {
		return srhSlipNoFrom;
	}

	/**
	 * 検索入力：入金番号FROM設定.
	 * @param srhSlipNoFrom 入金番号FROM
	 */
	public void setSrhSlipNoFrom(final String srhSlipNoFrom) {
		this.srhSlipNoFrom = srhSlipNoFrom;
	}

	/**
	 * 検索入力：入金番号TO取得.
	 * @return String 入金番号TO
	 */
	public String getSrhSlipNoTo() {
		return srhSlipNoTo;
	}

	/**
	 * 検索入力：入金番号TO設定.
	 * @param srhSlipNoTo 入金番号TO
	 */
	public void setSrhSlipNoTo(final String srhSlipNoTo) {
		this.srhSlipNoTo = srhSlipNoTo;
	}

	/**
	 * 検索入力：出力区分取得.
	 * @return String 出力区分
	 */
	public String getSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * 検索入力：出力区分設定.
	 * @param srhOutputDivision 出力区分
	 */
	public void setSrhOutputDivision(final String srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}

	/**
	 * 検索入力：伝票発行チェック取得.
	 * @return String 伝票発行チェック
	 */
	public boolean getSrhIssuedCheck() {
		return srhIssuedCheck;
	}

	/**
	 * 検索入力：伝票発行チェック設定.
	 * @param srhIssuedCheck 伝票発行チェック
	 */
	public void setSrhIssuedCheck(final boolean srhIssuedCheck) {
		this.srhIssuedCheck = srhIssuedCheck;
	}

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
	 * 入金分類コンボボックス内容を取得します。
	 * @return 入金分類コンボボックス内容
	 */
	public List<ComboBoxItems> getCategoryList() {
		return categoryList;
	}

	/**
	 * 入金分類コンボボックス内容を設定します。
	 * @param categoryList 入金分類コンボボックス内容
	 */
	public void setCategoryList(final List<ComboBoxItems> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * srhCategoryDivisionを取得します。
	 * @return srhCategoryDivision
	 */
	public String getSrhCategoryDivision() {
		return srhCategoryDivision;
	}

	/**
	 * srhCategoryDivisionを設定します。
	 * @param srhCategoryDivision srhCategoryDivision
	 */
	public void setSrhCategoryDivision(final String srhCategoryDivision) {
		this.srhCategoryDivision = srhCategoryDivision;
	}

	/**
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * srhOrganizationNameを取得します。
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return srhOrganizationName;
	}

	/**
	 * srhOrganizationNameを設定します。
	 * @param srhOrganizationName srhOrganizationName
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
	}

	/**
	 * srhVenderCdを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * srhVenderCdを設定します。
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * srhVenderName1を取得します。
	 * @return srhVenderName1
	 */
	public String getSrhVenderName1() {
		return srhVenderName1;
	}

	/**
	 * srhVenderName1を設定します。
	 * @param srhVenderName1 srhVenderName1
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = srhVenderName1;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg excelDownloadFlg
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public DepositCreditListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final DepositCreditListConditionForReport condition) {
		this.condition = condition;
	}
}
