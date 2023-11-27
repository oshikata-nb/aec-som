/*
 * Created on 2008/01/28
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
import com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingList;
import com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 在庫品目別引当明細照会 Formクラス
 * @author FPC
 */
public class InventoryDrawingListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb
				.getString("linage.inventory.drawing.list"));
		DATA_ROW = Integer.parseInt(rb
				.getString("threshold.inventory.drawing.list"));
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

	/** 発注残数 */
	private BigDecimal backorderQty;

	private String strBackorderQty;

	/** 仕上り在庫 */
	private BigDecimal finishQty;

	private String strFinishQty;

	/** 検査待数 */
	private BigDecimal inspectionQty;

	private String strInspectionQty;
	
	// 先付受注数
	private String strPreOrderQty;

	/** 引当残数 */
	private BigDecimal assignQty;

	private String strAssignQty;

	/** 販売引当残数 */
	private BigDecimal salesAssignQty;

	private String strSalesAssignQty;

	/** 利用可能数 */
	private BigDecimal usepossibleQty;

	private String strUsepossibleQty;

	/** 単位 */
	private String itemUnit;

	/** 在庫検索リスト */
	private List<InventoryDrawingList> searchList;

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
	public InventoryDrawingListForm() {

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
		return InventoryDrawingListPagerCondition.class;
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
			setSearchList(new ArrayList<InventoryDrawingList>());
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
		setBackorderQty(null);
		setStrBackorderQty(null);
		setFinishQty(null);
		setStrFinishQty(null);
		setInspectionQty(null);
		setStrInspectionQty(null);
		setAssignQty(null);
		setStrAssignQty(null);
		setSalesAssignQty(null);
		setStrSalesAssignQty(null);
		setUsepossibleQty(null);
		setStrUsepossibleQty(null);
		setItemUnit(null);
		setSearchList(new ArrayList<InventoryDrawingList>());
		setLink(null);
		setRepItemCd(null);
		setRepOtherCompanyCd1(null);
		setStrPreOrderQty(null);
	}

	/**
	 * assignQtyを取得します。
	 * @return assignQty
	 */
	public BigDecimal getAssignQty() {
		return assignQty;
	}

	/**
	 * assignQtyを設定します。
	 * @param assignQty assignQty
	 */
	public void setAssignQty(final BigDecimal assignQty) {
		this.assignQty = assignQty;
	}

	/**
	 * backorderQtyを取得します。
	 * @return backorderQty
	 */
	public BigDecimal getBackorderQty() {
		return backorderQty;
	}

	/**
	 * backorderQtyを設定します。
	 * @param backorderQty backorderQty
	 */
	public void setBackorderQty(final BigDecimal backorderQty) {
		this.backorderQty = backorderQty;
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
	 * inspectionQtyを取得します。
	 * @return inspectionQty
	 */
	public BigDecimal getInspectionQty() {
		return inspectionQty;
	}

	/**
	 * inspectionQtyを設定します。
	 * @param inspectionQty inspectionQty
	 */
	public void setInspectionQty(final BigDecimal inspectionQty) {
		this.inspectionQty = inspectionQty;
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
	 * itemUnitを取得します。
	 * @return itemUnit
	 */
	public String getItemUnit() {
		return itemUnit;
	}

	/**
	 * itemUnitを設定します。
	 * @param itemUnit itemUnit
	 */
	public void setItemUnit(final String itemUnit) {
		this.itemUnit = itemUnit;
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
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<InventoryDrawingList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<InventoryDrawingList> searchList) {
		this.searchList = searchList;
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
	 * strAssignQtyを取得します。
	 * @return strAssignQty
	 */
	public String getStrAssignQty() {
		return strAssignQty;
	}

	/**
	 * strAssignQtyを設定します。
	 * @param strAssignQty strAssignQty
	 */
	public void setStrAssignQty(final String strAssignQty) {
		this.strAssignQty = strAssignQty;
	}

	/**
	 * strBackorderQtyを取得します。
	 * @return strBackorderQty
	 */
	public String getStrBackorderQty() {
		return strBackorderQty;
	}

	/**
	 * strBackorderQtyを設定します。
	 * @param strBackorderQty strBackorderQty
	 */
	public void setStrBackorderQty(final String strBackorderQty) {
		this.strBackorderQty = strBackorderQty;
	}

	/**
	 * strInspectionQtyを取得します。
	 * @return strInspectionQty
	 */
	public String getStrInspectionQty() {
		return strInspectionQty;
	}

	/**
	 * strInspectionQtyを設定します。
	 * @param strInspectionQty strInspectionQty
	 */
	public void setStrInspectionQty(final String strInspectionQty) {
		this.strInspectionQty = strInspectionQty;
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
	 * strUsepossibleQtyを取得します。
	 * @return strUsepossibleQty
	 */
	public String getStrUsepossibleQty() {
		return strUsepossibleQty;
	}

	/**
	 * strUsepossibleQtyを設定します。
	 * @param strUsepossibleQty strUsepossibleQty
	 */
	public void setStrUsepossibleQty(final String strUsepossibleQty) {
		this.strUsepossibleQty = strUsepossibleQty;
	}

	/**
	 * usepossibleQtyを取得します。
	 * @return usepossibleQty
	 */
	public BigDecimal getUsepossibleQty() {
		return usepossibleQty;
	}

	/**
	 * usepossibleQtyを設定します。
	 * @param usepossibleQty usepossibleQty
	 */
	public void setUsepossibleQty(final BigDecimal usepossibleQty) {
		this.usepossibleQty = usepossibleQty;
	}

	/**
	 * finishQtyを取得します。
	 * @return finishQty
	 */
	public BigDecimal getFinishQty() {
		return finishQty;
	}

	/**
	 * finishQtyを設定します。
	 * @param finishQty finishQty
	 */
	public void setFinishQty(final BigDecimal finishQty) {
		this.finishQty = finishQty;
	}

	/**
	 * strFinishQtyを取得します。
	 * @return strFinishQty
	 */
	public String getStrFinishQty() {
		return strFinishQty;
	}

	/**
	 * strFinishQtyを設定します。
	 * @param strFinishQty strFinishQty
	 */
	public void setStrFinishQty(final String strFinishQty) {
		this.strFinishQty = strFinishQty;
	}

	/**
	 * salesAssignQtyを取得します。
	 * @return salesAssignQty
	 */
	public BigDecimal getSalesAssignQty() {
		return salesAssignQty;
	}

	/**
	 * salesAssignQtyを設定します。
	 * @param salesAssignQty salesAssignQty
	 */
	public void setSalesAssignQty(final BigDecimal salesAssignQty) {
		this.salesAssignQty = salesAssignQty;
	}

	/**
	 * strSalesAssignQtyを取得します。
	 * @return strSalesAssignQty
	 */
	public String getStrSalesAssignQty() {
		return strSalesAssignQty;
	}

	/**
	 * strSalesAssignQtyを設定します。
	 * @param strSalesAssignQty strSalesAssignQty
	 */
	public void setStrSalesAssignQty(final String strSalesAssignQty) {
		this.strSalesAssignQty = strSalesAssignQty;
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

	/**
	 * strPreOrderQtyを取得します。
	 * @return strPreOrderQty
	 */
	public String getStrPreOrderQty() {
		return strPreOrderQty;
	}

	/**
	 * strPreOrderQtyを設定します。
	 * @param strPreOrderQty strPreOrderQty
	 */
	public void setStrPreOrderQty(String strPreOrderQty) {
		this.strPreOrderQty = strPreOrderQty;
	}
}
