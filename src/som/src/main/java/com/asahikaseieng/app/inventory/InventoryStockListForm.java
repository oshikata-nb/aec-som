/*
 * Created on 2008/01/17
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
import com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockList;
import com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 在庫品目別予定在庫照会 Formクラス
 * @author FPC
 */
public class InventoryStockListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer
				.parseInt(rb.getString("linage.inventory.stock.list"));
		DATA_ROW = Integer.parseInt(rb
				.getString("threshold.inventory.stock.list"));
	}

	/** 品目コード */
	private String srhItemCd;

	/** 品目名称 */
	private String srhItemName;

	/** 他社コード1 */
	private String srhOtherCompanyCd1;

	/** 荷姿 */
	private String srhStyleOfPacking;

	/** 手持在庫数 */
	private BigDecimal inventoryQty;

	private String strInventoryQty;

	/** 在庫検索リスト */
	private List<InventoryStockList> searchList;

	/** リンク */
	private String link;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票出力用品目コード */
	private String repItemCd;

	/** 帳票出力用他社コード1 */
	private String repOtherCompanyCd1;

	/**
	 * コンストラクタ.
	 */
	public InventoryStockListForm() {

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
		return InventoryStockListPagerCondition.class;
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
			setSearchList(new ArrayList<InventoryStockList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhItemCd(null);
		setSrhItemName(null);
		setSrhOtherCompanyCd1(null);
		setSrhStyleOfPacking(null);
		setInventoryQty(null);
		setStrInventoryQty(null);
		setSearchList(new ArrayList<InventoryStockList>());
		setLink(null);
		setRepItemCd(null);
		setRepOtherCompanyCd1(null);
	}

	/**
	 * inventoryQtyを取得します。
	 * @return inventoryQty
	 */
	public BigDecimal getInventoryQty() {
		return inventoryQty;
	}

	/**
	 * inventoryQtyを設定します。
	 * @param inventoryQty inventoryQty
	 */
	public void setInventoryQty(final BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * linkを取得します。
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * linkを設定します。
	 * @param link link
	 */
	public void setLink(final String link) {
		this.link = link;
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

	/**
	 * strInventoryQtyを取得します。
	 * @return strInventoryQty
	 */
	public String getStrInventoryQty() {
		return strInventoryQty;
	}

	/**
	 * strInventoryQtyを設定します。
	 * @param strInventoryQty strInventoryQty
	 */
	public void setStrInventoryQty(final String strInventoryQty) {
		this.strInventoryQty = strInventoryQty;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<InventoryStockList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<InventoryStockList> getSearchList() {
		return searchList;
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
	 * repItemCdを取得します。
	 * @return repItemCd
	 */
	public String getRepItemCd() {
		return repItemCd;
	}

	/**
	 * repItemCdを設定します。
	 * @param repItemCd repItemCd
	 */
	public void setRepItemCd(final String repItemCd) {
		this.repItemCd = repItemCd;
	}

	/**
	 * repOtherCompanyCd1を取得します。
	 * @return repOtherCompanyCd1
	 */
	public String getRepOtherCompanyCd1() {
		return repOtherCompanyCd1;
	}

	/**
	 * repOtherCompanyCd1を設定します。
	 * @param repOtherCompanyCd1 repOtherCompanyCd1
	 */
	public void setRepOtherCompanyCd1(final String repOtherCompanyCd1) {
		this.repOtherCompanyCd1 = repOtherCompanyCd1;
	}
}
