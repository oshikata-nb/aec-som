/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 受注検索(ポップアップ) Formクラス.
 * @author tosco
 */
public final class ShippingOrderSearchForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 検索入力：受注番号 */
	private String srhOrderNo;

	/** 検索入力：受注区分 */
	private String srhOrderDivision;

	/** 検索入力：出荷予定日FROM */
	private String srhScheduledShippingDateFrom;

	/** 検索入力：出荷予定日TO */
	private String srhScheduledShippingDateTo;

	/** 検索入力：納入先コード */
	private String srhDeliveryCd;

	/** 検索入力：納入先名称 */
	private String srhDeliveryName1;

	/** 検索入力：得意先コード */
	private String srhVenderCd;

	/** 検索入力：取引先名称 */
	private String srhVenderName1;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：他社コード1 */
	private String srhOtherCompanyCd1;

	/** 検索入力：荷姿 */
	private String srhStyleOfPacking;

	/** 検索入力：単位 */
	private String srhUnitOfOperationManagement;

	private String tempCarryCd;

	/** 検索結果リスト */
	private List<ShippingOrderSearchList> searchList;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.shipping.search.order"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.shipping.search.order"));
	}

	/**
	 * コンストラクタ.
	 */
	public ShippingOrderSearchForm() {
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
		return ShippingOrderSearchListPagerCondition.class;
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
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {

		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.受注検索
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<ShippingOrderSearchList>());

		/** 検索入力：受注番号 */
		setSrhOrderNo(null);
		/** 検索入力：受注区分 */
		setSrhOrderDivision("0");
		/** 検索入力：出荷予定日FROM */
		setSrhScheduledShippingDateFrom(null);
		/** 検索入力：出荷予定日TO */
		setSrhScheduledShippingDateTo(null);
		/** 検索入力：取引先コード */
		setSrhVenderCd(null);
		/** 検索入力：取引先名称 */
		setSrhVenderName1(null);
		/** 検索入力：納入先コード */
		setSrhDeliveryCd(null);
		/** 検索入力：納入先名称 */
		setSrhDeliveryName1(null);
		/** 検索入力：品目コード */
		setSrhItemCd(null);
		/** 検索入力：品目名称 */
		setSrhItemName(null);
		/** 検索入力：他社コード1 */
		setSrhOtherCompanyCd1(null);
		/** 検索入力：荷姿 */
		setSrhStyleOfPacking(null);

	}

	/**
	 * 検索入力：受注番号
	 * @return String 受注番号
	 */
	public String getSrhOrderNo() {
		return this.srhOrderNo;
	}

	/**
	 * 検索入力：受注番号
	 * @param srhOrderNo 受注番号
	 */
	public void setSrhOrderNo(final String srhOrderNo) {
		this.srhOrderNo = srhOrderNo;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<ShippingOrderSearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索入力：受注区分取得.
	 * @return String 受注区分
	 */
	public String getSrhOrderDivision() {
		return this.srhOrderDivision;
	}

	/**
	 * 検索入力：受注区分設定.
	 * @param srhOrderDivision 受注区分
	 */
	public void setSrhOrderDivision(final String srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * 検索入力：出荷予定日FROM取得.
	 * @return String 出荷予定日FROM
	 */
	public String getSrhScheduledShippingDateFrom() {
		return this.srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力：出荷予定日FROM設定.
	 * @param srhScheduledShippingDateFrom 出荷予定日FROM
	 */
	public void setSrhScheduledShippingDateFrom(
			final String srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力：出荷予定日TO取得.
	 * @return String 出荷予定日TO
	 */
	public String getSrhScheduledShippingDateTo() {
		return this.srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力：出荷予定日TO設定.
	 * @param srhScheduledShippingDateTo 出荷予定日TO
	 */
	public void setSrhScheduledShippingDateTo(
			final String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力：得意先コード取得.
	 * @return String 得意先コード
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力：取引先名称取得.
	 * @return String 取引先名称
	 */
	public String getSrhVenderName1() {
		return this.srhVenderName1;
	}

	/**
	 * 検索入力：取引先名称設定.
	 * @param srhVenderName1 取引先名称
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = srhVenderName1;
	}

	/**
	 * 検索入力：納入先コード取得.
	 * @return String 納入先コード
	 */
	public String getSrhDeliveryCd() {
		return this.srhDeliveryCd;
	}

	/**
	 * 検索入力：納入先コード設定.
	 * @param srhDeliveryCd 納入先コード
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = srhDeliveryCd;
	}

	/**
	 * 検索入力：納入先名称取得.
	 * @return String 納入先名称
	 */
	public String getSrhDeliveryName1() {
		return this.srhDeliveryName1;
	}

	/**
	 * 検索入力：納入先名称設定.
	 * @param srhDeliveryName1 納入先名称
	 */
	public void setSrhDeliveryName1(final String srhDeliveryName1) {
		this.srhDeliveryName1 = srhDeliveryName1;
	}

	/**
	 * 検索入力：得意先コード設定.
	 * @param srhVenderCd 得意先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 品目コード設定
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return this.srhItemName;
	}

	/**
	 * 品目名称設定
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param srhOtherCompanyCd1 他社コード1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getSrhStyleOfPacking() {
		return this.srhStyleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param srhStyleOfPacking 荷姿
	 */
	public void setSrhStyleOfPacking(final String srhStyleOfPacking) {
		this.srhStyleOfPacking = srhStyleOfPacking;
	}

	/**
	 * 単位
	 * @return String 単位
	 */
	public String getSrhUnitOfOperationManagement() {
		return this.srhUnitOfOperationManagement;
	}

	/**
	 * 単位
	 * @param srhUnitOfOperationManagement 単位
	 */
	public void setSrhUnitOfOperationManagement(
			final String srhUnitOfOperationManagement) {
		this.srhUnitOfOperationManagement = srhUnitOfOperationManagement;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(final List<ShippingOrderSearchList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * tempCarryCd
	 * @return tempCarryCd tempCarryCd
	 */
	public String getTempCarryCd() {
		return tempCarryCd;
	}

	/**
	 * tempCarryCd
	 * @param tempCarryCd tempCarryCd
	 */
	public void setTempCarryCd(final String tempCarryCd) {
		this.tempCarryCd = tempCarryCd;
	}

}
