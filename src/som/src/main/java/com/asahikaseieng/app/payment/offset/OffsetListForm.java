/*
 * Created on 2008/07/03
 *
 * $copyright$
 */
package com.asahikaseieng.app.payment.offset;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetList;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 相殺処理 Formクラス
 * @author tosco
 */
public final class OffsetListForm extends AbstractSearchForm {

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
	// 検索用.グループ間相殺入力
	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhOrganizationName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：相殺グループ */
	private String srhOffsetGrp;

	/** 相殺グループリスト */
	private List<ComboBoxItems> offsetGrpList;

	/** 検索入力：相殺日付FROM */
	private String srhOffsetDateFrom;

	/** 検索入力：相殺日付TO */
	private String srhOffsetDateTo;

	/** 検索入力：分類 */
	private String srhCassification;

	/** 分類コンボボックス内容 */
	private List<ComboBoxItems> categoryList;

	/** 出力区分 */
	private String srhOutputDivision;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/* 帳票Excel用検索条件 */
	private OffsetListConditionForReport condition;

	//
	// 一覧用.グループ間相殺入力
	//

	/** リスト */
	private List<OffsetList> searchList;

	/**
	 * コンストラクタ.相殺
	 */
	public OffsetListForm() {
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
		return OffsetPagerCondition.class;
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
			setSearchList(new ArrayList<OffsetList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.グループ間相殺入力
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<OffsetList>());

		// 検索入力：部署コード
		setSrhOrganizationCd(null);
		// 検索入力：部署名称
		setSrhOrganizationName(null);
		// 検索入力：担当者コード
		setSrhTantoCd(null);
		// 検索入力：担当者名称
		setSrhTantoNm(null);
		// 検索入力：相殺グループ
		setSrhOffsetGrp(null);
		// 検索入力：相殺日付FROM
		setSrhOffsetDateFrom(null);
		// 検索入力：相殺日付TO
		setSrhOffsetDateTo(null);
		// 検索入力：出力区分
		setSrhOutputDivision(null);
		// 検索入力：分類
		setSrhCassification(null);
		/* 検索条件 */
		setCondition(null);
	}

	//
	// 検索入力.グループ間相殺入力
	//

	/**
	 * 検索入力：部署コード取得.
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return this.srhOrganizationCd;
	}

	/**
	 * 検索入力：部署コード設定.
	 * @param srhOrganizationCd 部署コード
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * 検索入力：部署名称取得.
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return this.srhOrganizationName;
	}

	/**
	 * 検索入力：部署名称設定.
	 * @param srhOrganizationName 部署名称
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
	}

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
	 * 検索入力：相殺グループ取得.
	 * @return String 相殺グループ
	 */
	public String getSrhOffsetGrp() {
		return srhOffsetGrp;
	}

	/**
	 * 検索入力：相殺グループ設定.
	 * @param srhOffsetGrp 相殺グループ
	 */
	public void setSrhOffsetGrp(final String srhOffsetGrp) {
		this.srhOffsetGrp = srhOffsetGrp;
	}

	/**
	 * 検索入力：相殺日付FROM取得.
	 * @return String 相殺日付FROM
	 */
	public String getSrhOffsetDateFrom() {
		return srhOffsetDateFrom;
	}

	/**
	 * 検索入力：相殺日付FROM設定.
	 * @param srhOffsetDateFrom 相殺日付FROM
	 */
	public void setSrhOffsetDateFrom(final String srhOffsetDateFrom) {
		this.srhOffsetDateFrom = srhOffsetDateFrom;
	}

	/**
	 * 検索入力：相殺日付TO取得.
	 * @return Date 相殺日付TO
	 */
	public String getSrhOffsetDateTo() {
		return srhOffsetDateTo;
	}

	/**
	 * 検索入力：相殺日付TO設定.
	 * @param srhOffsetDateTo 相殺日付TO
	 */
	public void setSrhOffsetDateTo(final String srhOffsetDateTo) {
		this.srhOffsetDateTo = srhOffsetDateTo;
	}

	/**
	 * 検索入力：分類を取得します。
	 * @return srhCassification
	 */
	public String getSrhCassification() {
		return srhCassification;
	}

	/**
	 * 検索入力：分類を設定します。
	 * @param srhCassification 検索入力：分類
	 */
	public void setSrhCassification(final String srhCassification) {
		this.srhCassification = srhCassification;
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
	 * 相殺グループリストを取得します。
	 * @return offsetGrpList
	 */
	public List<ComboBoxItems> getOffsetGrpList() {
		return offsetGrpList;
	}

	/**
	 * 相殺グループリストを設定します。
	 * @param offsetGrpList 相殺グループリスト
	 */
	public void setOffsetGrpList(final List<ComboBoxItems> offsetGrpList) {
		this.offsetGrpList = offsetGrpList;
	}

	/**
	 * 検索入力：出力区分取得.
	 * @return String 出力区分
	 * 
	 */
	public String getSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * 検索入力：出力区分設定.
	 * @param srhOutputDivision 出力区分
	 * 
	 */
	public void setSrhOutputDivision(final String srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}

	//
	// インスタンスメソッド.相殺
	//

	/**
	 * 検索結果リストを取得します。
	 * 
	 * @return List<OffsetList> 検索結果リスト
	 * 
	 */
	public List<OffsetList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * 
	 * @param searchList 検索結果リスト
	 * 
	 */
	public void setSearchList(final List<OffsetList> searchList) {
		this.searchList = searchList;
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
	public OffsetListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final OffsetListConditionForReport condition) {
		this.condition = condition;
	}
}
