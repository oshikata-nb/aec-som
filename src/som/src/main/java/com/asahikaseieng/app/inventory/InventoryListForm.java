/*
 * Created on 2007/11/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.inventorylist.InventoryList;
import com.asahikaseieng.dao.nonentity.inventorylist.InventoryListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryListForReportCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 在庫照会リスト Formクラス
 * @author FPC
 */
public final class InventoryListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);
		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.inventory.list"));
	}

	/* ロケーションコード */
	private String srhLocationCd;

	/* ロケーション名称 */
	private String srhLocationName;

	/* 品目コード */
	private String srhItemCd;

	/* 品目名称 */
	private String srhItemName;

	/* 他社コード1 */
	private String srhOtherCompanyCd1;

	/* 荷姿 */
	private String srhStyleOfPacking;

	/* 入荷ロット番号/包装指図番号 */
	private String srhLotNo;

	/* 在庫数量合計 */
	private BigDecimal srhInventoryQty;

	private String strSrhInventoryQty;

	/* 区分 */
	private String unitDivision;

	private int indexVal;

	/* オーダー番号 */
	private String srhOderNo;

	/* リンクフラグ */
	private String srhLink;

	/* リスト */
	private List<InventoryList> searchList;

	/* EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/* 帳票出力条件 */
	private InventoryListForReportCondition reportCondition;

	/**
	 * コンストラクタ.
	 */
	public InventoryListForm() {
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
		return InventoryListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
		if ("init".equals(getOp())) {
			/* 初期化 */
			clear();
		}

		/* ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("search".equals(getOp()) || "relocation".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<InventoryList>());
		}

		if ("search".equals(getOp()) || "relocation".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhLocationCd(null);
		setSrhLocationName(null);
		setSrhItemCd(null);
		setSrhItemName(null);
		setSrhOtherCompanyCd1(null);
		setSrhStyleOfPacking(null);
		setSrhLotNo(null);
		setSrhInventoryQty(null);
		setStrSrhInventoryQty(null);
		setUnitDivision("SONOTA3");
		setIndexVal(-1);
		setSrhOderNo(null);
		setSrhLink(null);
		setSearchList(new ArrayList<InventoryList>());
		setReportCondition(null);
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
	 * indexValを取得します。
	 * @return indexVal
	 */
	public int getIndexVal() {
		return indexVal;
	}

	/**
	 * indexValを設定します。
	 * @param indexVal indexVal
	 */
	public void setIndexVal(final int indexVal) {
		this.indexVal = indexVal;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<InventoryList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<InventoryList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhLotNoを取得します。
	 * @return srhLotNo
	 */
	public String getSrhLotNo() {
		return srhLotNo;
	}

	/**
	 * srhLotNoを設定します。
	 * @param srhLotNo srhLotNo
	 */
	public void setSrhLotNo(final String srhLotNo) {
		this.srhLotNo = srhLotNo;
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
	 * srhLocationCdを取得します。
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return srhLocationCd;
	}

	/**
	 * srhLocationCdを設定します。
	 * @param srhLocationCd srhLocationCd
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * srhLocationNameを取得します。
	 * @return srhLocationName
	 */
	public String getSrhLocationName() {
		return srhLocationName;
	}

	/**
	 * srhLocationNameを設定します。
	 * @param srhLocationName srhLocationName
	 */
	public void setSrhLocationName(final String srhLocationName) {
		this.srhLocationName = srhLocationName;
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

	/**
	 * srhStyleOfPackingを取得します。
	 * @return srhStyleOfPacking
	 */
	public String getSrhStyleOfPacking() {
		return srhStyleOfPacking;
	}

	/**
	 * srhStyleOfPackingを設定します。
	 * @param srhStyleOfPacking srhStyleOfPacking
	 */
	public void setSrhStyleOfPacking(final String srhStyleOfPacking) {
		this.srhStyleOfPacking = srhStyleOfPacking;
	}

	/**
	 * unitDivisionを取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}

	/**
	 * unitDivisionを設定します。
	 * @param unitDivision unitDivision
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * srhInventoryQtyを取得します。
	 * @return srhInventoryQty
	 */
	public BigDecimal getSrhInventoryQty() {
		return srhInventoryQty;
	}

	/**
	 * srhInventoryQtyを設定します。
	 * @param srhInventoryQty srhInventoryQty
	 */
	public void setSrhInventoryQty(final BigDecimal srhInventoryQty) {
		this.srhInventoryQty = srhInventoryQty;
	}

	/**
	 * strSrhInventoryQtyを取得します。
	 * @return strSrhInventoryQty
	 */
	public String getStrSrhInventoryQty() {
		return strSrhInventoryQty;
	}

	/**
	 * strSrhInventoryQtyを設定します。
	 * @param strSrhInventoryQty strSrhInventoryQty
	 */
	public void setStrSrhInventoryQty(final String strSrhInventoryQty) {
		this.strSrhInventoryQty = strSrhInventoryQty;
	}

	/**
	 * srhOderNoを取得します。
	 * @return srhOderNo
	 */
	public String getSrhOderNo() {
		return srhOderNo;
	}

	/**
	 * srhOderNoを設定します。
	 * @param srhOderNo srhOderNo
	 */
	public void setSrhOderNo(final String srhOderNo) {
		this.srhOderNo = srhOderNo;
	}

	/**
	 * srhLinkを取得します。
	 * @return srhLink
	 */
	public String getSrhLink() {
		return srhLink;
	}

	/**
	 * srhLinkを設定します。
	 * @param srhLink srhLink
	 */
	public void setSrhLink(final String srhLink) {
		this.srhLink = srhLink;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition
	 */
	public InventoryListForReportCondition getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final InventoryListForReportCondition reportCondition) {
		this.reportCondition = reportCondition;
	}
}
