/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.monthlyvender;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.production.ProductionPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 得意先別管理月報 Formクラス.
 * @author t1344224
 * 
 */
public final class MonthlyVenderForm extends AbstractSearchForm {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		// SYSTEM_PROPERTIESの取得
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		// 明細行数をSYSTEM_PROPERTIESから取得
		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		// 最大データ数をSYSTEM_PROPERTIESから取得
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	private String srhDateFrom;

	private String srhDateTo;

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhSectionName;

	//
	// インスタンスフィールド.
	//

	/**
	 * コンストラクタ
	 */
	public MonthlyVenderForm() {
	}

	/**
	 * ページの明細行数取得
	 * @return int ページの明細行数
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * 最大データ数取得
	 * @return int 最大データ数
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * PagerConditionClass取得
	 * @return BuyingPagerCondition BuyingPagerCondition
	 */
	protected Class getPagerConditionClass() {
		return ProductionPagerCondition.class;
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
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
	 * validate
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("report".equals(getOp())) {
			// Validatorによる入力チェック
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		// Excelダウンロードフラグ
		setExcelDownloadFlg(false);

		setSrhDateFrom(null);

		setSrhDateTo(null);

		/** 検索入力：部署コード */
		setSrhOrganizationCd(null);
		/** 検索入力：部署名称 */
		setSrhSectionName(null);

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
	 * srhDateFromを取得します。
	 * @return srhDateFrom
	 */
	public String getSrhDateFrom() {
		return srhDateFrom;
	}

	/**
	 * srhDateFromを設定します。
	 * @param srhDateFrom srhDateFrom
	 */
	public void setSrhDateFrom(final String srhDateFrom) {
		this.srhDateFrom = srhDateFrom;
	}

	/**
	 * srhDateToを取得します。
	 * @return srhDateTo
	 */
	public String getSrhDateTo() {
		return srhDateTo;
	}

	/**
	 * srhDateToを設定します。
	 * @param srhDateTo srhDateTo
	 */
	public void setSrhDateTo(final String srhDateTo) {
		this.srhDateTo = srhDateTo;
	}

	/**
	 * 検索入力：部署コード取得.
	 * @return 部署コード
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
	 * @return 部署名称
	 */
	public String getSrhSectionName() {
		return srhSectionName;
	}

	/**
	 * 検索入力：部署名称設定.
	 * @param srhSectionName 部署名称
	 */
	public void setSrhSectionName(final String srhSectionName) {
		this.srhSectionName = srhSectionName;
	}

}
