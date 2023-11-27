/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.deliverylist.DeliveryList;
import com.asahikaseieng.dao.nonentity.master.deliverylist.DeliveryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 納入先一覧 Formクラス.
 * @author t0011036
 */
public final class DeliveryListForm extends AbstractSearchForm {

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

	/* 納入先コード */
	private String srhDeliveryCd;

	/* 納入先名称1 */
	private String srhDeliveryName1;

	/* 納入先リスト */
	private List<DeliveryList> searchList;

	/* 帳票Excel用検索条件 */
	private DeliveryListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public DeliveryListForm() {
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
		return DeliveryListPagerCondition.class;
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
			setSearchList(new ArrayList<DeliveryList>());
		}

		if ("search".equals(getOp()) || "update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhDeliveryCd(null);
		setSrhDeliveryName1(null);
		setSearchList(new ArrayList<DeliveryList>());
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
	 * srhDeliveryCdを取得します。
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return srhDeliveryCd;
	}

	/**
	 * srhDeliveryCdを設定します。
	 * @param srhDeliveryCd srhDeliveryCd
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = srhDeliveryCd;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<DeliveryList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<DeliveryList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhDeliveryName1を取得します。
	 * @return srhDeliveryName1
	 */
	public String getSrhDeliveryName1() {
		return srhDeliveryName1;
	}

	/**
	 * srhDeliveryName1を設定します。
	 * @param srhDeliveryName1 srhDeliveryName1
	 */
	public void setSrhDeliveryName1(final String srhDeliveryName1) {
		this.srhDeliveryName1 = srhDeliveryName1;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public DeliveryListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final DeliveryListConditionForReport condition) {
		this.condition = condition;
	}
}
