/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.direction;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.production.ProductionPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 製品別原材料消費量リスト Formクラス.
 * @author t2712372
 * 
 */
public final class DirectionMaterialUsedForm extends AbstractSearchForm {

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

	/** 油脂化成品区分 */
	private String srhBalanceType;

	/** 原料包材区分 */
	private String srhDirectionDivision;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	private String srhShipperDivision;

	private String srhItemCd;

	private String srhItemName;

	private String srhOtherCompanyCd1;

	private String srhDateFrom;

	private String srhDateTo;

	//
	// インスタンスフィールド.
	//

	/**
	 * コンストラクタ
	 */
	public DirectionMaterialUsedForm() {
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
		setExcelDownloadFlg(false);

		setSrhBalanceType(null);

		setSrhDirectionDivision(null);

		setSrhShipperDivision(null);

		setSrhDateFrom(null);

		setSrhDateTo(null);

		setSrhItemCd(null);

		setSrhItemName(null);

		setSrhOtherCompanyCd1(null);

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
	 * srhDirectionDivisionを取得します。
	 * @return srhDirectionDivision
	 */
	public String getSrhDirectionDivision() {
		return srhDirectionDivision;
	}

	/**
	 * srhDirectionDivisionを設定します。
	 * @param srhDirectionDivision srhDirectionDivision
	 */
	public void setSrhDirectionDivision(final String srhDirectionDivision) {
		this.srhDirectionDivision = srhDirectionDivision;
	}

	/**
	 * srhBalanceTypeを取得します。
	 * @return srhBalanceType
	 */
	public String getSrhBalanceType() {
		return srhBalanceType;
	}

	/**
	 * srhBalanceTypeを設定します。
	 * @param srhBalanceType srhBalanceType
	 */
	public void setSrhBalanceType(final String srhBalanceType) {
		this.srhBalanceType = srhBalanceType;
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
	 * srhShipperDivisionを取得します。
	 * @return srhShipperDivision
	 */
	public String getSrhShipperDivision() {
		return srhShipperDivision;
	}

	/**
	 * srhShipperDivisionを設定します。
	 * @param srhShipperDivision srhShipperDivision
	 */
	public void setSrhShipperDivision(final String srhShipperDivision) {
		this.srhShipperDivision = srhShipperDivision;
	}

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
		this.srhItemCd = srhItemCd;
	}

	/**
	 * srhItemNameを取得します。
	 * @return srhItemName
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * srhItemNameを設定します。
	 * @param srhItemName srhItemName
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
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
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}
}
