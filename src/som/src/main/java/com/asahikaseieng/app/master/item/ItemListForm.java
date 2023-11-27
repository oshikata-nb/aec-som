/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueList;
import com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 品目一覧 Formクラス.
 * @author t0011036
 */
public final class ItemListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

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

	/* 品目コード */
	private String srhItemCd;

	/* 品目名称 */
	private String srhItemName;

	/* 親品目コード */
	private String srhParentItemCd;

	/* 親品目名称 */
	private String srhParentItemName;

	/* 他社コード1 */
	private String srhOtherCompanyCd1;

	/* 基本情報ステータス */
	private java.math.BigDecimal srhStatus;

	/* 在庫・単価情報ステータス */
	private java.math.BigDecimal srhDetailStatus;

	/* 開始有効日時 */
	private Timestamp srhActiveDateFrom;

	private String strSrhActiveDateFrom;

	/* 終了有効日時 */
	private Timestamp srhActiveDateTo;

	private String strSrhActiveDateTo;

	/* 品目リスト */
	private List<ItemQueueList> searchList;

	/* 帳票Excel用検索条件 */
	private ItemQueueListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public ItemListForm() {
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
		return ItemQueueListPagerCondition.class;
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
		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<ItemQueueList>());
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
		setSrhParentItemCd(null);
		setSrhParentItemName(null);
		setSrhOtherCompanyCd1(null);
		setSrhStatus(null);
		setSrhDetailStatus(null);
		setStrSrhActiveDateFrom(null);
		setSrhActiveDateFrom(null);
		setStrSrhActiveDateTo(null);
		setSrhActiveDateTo(null);
		setSearchList(new ArrayList<ItemQueueList>());
		setCondition(null);
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
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<ItemQueueList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ItemQueueList> searchList) {
		this.searchList = searchList;
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
	 * srhActiveDateFromを取得します。
	 * @return srhActiveDateFrom
	 */
	public Timestamp getSrhActiveDateFrom() {
		return srhActiveDateFrom;
	}

	/**
	 * srhActiveDateFromを設定します。
	 * @param srhActiveDateFrom srhActiveDateFrom
	 */
	public void setSrhActiveDateFrom(final Timestamp srhActiveDateFrom) {
		this.srhActiveDateFrom = srhActiveDateFrom;
	}

	/**
	 * srhActiveDateToを取得します。
	 * @return srhActiveDateTo
	 */
	public Timestamp getSrhActiveDateTo() {
		return srhActiveDateTo;
	}

	/**
	 * srhActiveDateToを設定します。
	 * @param srhActiveDateTo srhActiveDateTo
	 */
	public void setSrhActiveDateTo(final Timestamp srhActiveDateTo) {
		this.srhActiveDateTo = srhActiveDateTo;
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
	 * srhParentItemCdを取得します。
	 * @return srhParentItemCd
	 */
	public String getSrhParentItemCd() {
		return srhParentItemCd;
	}

	/**
	 * srhParentItemCdを設定します。
	 * @param srhParentItemCd srhParentItemCd
	 */
	public void setSrhParentItemCd(final String srhParentItemCd) {
		this.srhParentItemCd = srhParentItemCd;
	}

	/**
	 * srhParentItemNameを取得します。
	 * @return srhParentItemName
	 */
	public String getSrhParentItemName() {
		return srhParentItemName;
	}

	/**
	 * srhParentItemNameを設定します。
	 * @param srhParentItemName srhParentItemName
	 */
	public void setSrhParentItemName(final String srhParentItemName) {
		this.srhParentItemName = srhParentItemName;
	}

	/**
	 * strSrhActiveDateFromを取得します。
	 * @return strSrhActiveDateFrom
	 */
	public String getStrSrhActiveDateFrom() {
		return strSrhActiveDateFrom;
	}

	/**
	 * strSrhActiveDateFromを設定します。
	 * @param strSrhActiveDateFrom strSrhActiveDateFrom
	 */
	public void setStrSrhActiveDateFrom(final String strSrhActiveDateFrom) {
		this.strSrhActiveDateFrom = strSrhActiveDateFrom;
	}

	/**
	 * strSrhActiveDateToを取得します。
	 * @return strSrhActiveDateTo
	 */
	public String getStrSrhActiveDateTo() {
		return strSrhActiveDateTo;
	}

	/**
	 * strSrhActiveDateToを設定します。
	 * @param strSrhActiveDateTo strSrhActiveDateTo
	 */
	public void setStrSrhActiveDateTo(final String strSrhActiveDateTo) {
		this.strSrhActiveDateTo = strSrhActiveDateTo;
	}

	/**
	 * srhStatusを取得します。
	 * @return srhStatus
	 */
	public java.math.BigDecimal getSrhStatus() {
		return srhStatus;
	}

	/**
	 * srhStatusを設定します。
	 * @param srhStatus srhStatus
	 */
	public void setSrhStatus(final java.math.BigDecimal srhStatus) {
		this.srhStatus = srhStatus;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public ItemQueueListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final ItemQueueListConditionForReport condition) {
		this.condition = condition;
	}

	/**
	 * srhDetailStatusを取得します。
	 * @return srhDetailStatus
	 */
	public java.math.BigDecimal getSrhDetailStatus() {
		return srhDetailStatus;
	}

	/**
	 * srhDetailStatusを設定します。
	 * @param srhDetailStatus srhDetailStatus
	 */
	public void setSrhDetailStatus(final java.math.BigDecimal srhDetailStatus) {
		this.srhDetailStatus = srhDetailStatus;
	}
}
