/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.orderlist.OrderList;
import com.asahikaseieng.dao.nonentity.orderlist.OrderListPagerCondition;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 受注ヘッダー Formクラス
 * @author t1344224
 */
public final class OrderListForm extends AbstractSearchForm {

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

	//
	// 受注検索入力用
	//
	/* 検索条件.受注番号From */
	private String srhOrderNoFrom;

	/* 検索条件.受注番号 */
	private String srhOrderNoTo;

	/* 検索条件.受注区分 */
	private java.math.BigDecimal srhOrderDivision;

	/* 検索条件.ステータス */
	private java.math.BigDecimal srhStatus;

	/* 検索条件.受注日From */
	private String srhOrderDateFrom;

	/* 検索条件.受注日To */
	private String srhOrderDateTo;

	/* 検索条件.出荷予定日From */
	private String srhScheduledShippingDateFrom;

	/* 検索条件.出荷予定日To */
	private String srhScheduledShippingDateTo;

	/* 検索条件.納入先コード */
	private String srhDeliveryCd;

	/* 検索条件.納入先名称 */
	private String srhDeliveryName1;

	/* 検索条件.納入先住所 */
	private String srhDeliveryAddress;

	/* 検索条件.納入先電話番号 */
	private String srhDeliveryTelNo;

	/* 検索条件.得意先コード */
	private String srhVenderCd;

	/* 検索条件.得意先名称 */
	private String srhVenderName1;

	/* 検索条件.担当部署コード */
	private String srhOrganizationCd;

	/* 検索条件.担当部署名称 */
	private String srhOrganizationName;

	/* 検索条件.営業担当者コード */
	private String srhSalesTantoCd;

	/* 検索条件.営業担当者名称 */
	private String srhSalesTantoName;

	/* 検索条件.入力担当者コード */
	private String srhInputTantoCd;

	/* 検索条件.入力担当者名称 */
	private String srhInputTantoName;

	/* 検索条件品目コード */
	private String srhItemCd;

	/* 検索条件品目コード */
	private String srhItemName;

	/* 検索条件他社コード */
	private String srhOtherCompanyCd1;

	/** 運送会社コンボボックス */
	private List<ComboBoxItems> carryCombo;

	// 2015/5/15 検索条件追加
	private String srhCarryCd;

	/* 受注リスト */
	private List<OrderList> searchList;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/* 受注区分名称 */
	private String[] orderDivisionValues;

	private String[] orderDivisionLabels;

	/* 受注ステータス名称 */
	private String[] orderStatusValues;

	private String[] orderStatusLabels;

	private OrderListConditionForReport reportCondition;

	private RepOrderSlipConditionForReport slipCondition;

	/** EXCELダウンロードフラグ */
	private boolean excelSlipDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public OrderListForm() {
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
		return OrderListPagerCondition.class;
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

		/* 受注一覧用Ｅｘｃｅｌダウンロードフラグを倒す */
		setExcelSlipDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<OrderList>());
			// Validatorによる入力チェック
			errors = super.validate(mapping, request);
		}

		if ("reSearch".equals(getOp())) {
			// リストのクリア
			setSearchList(new ArrayList<OrderList>());
			// Validatorによる入力チェック
			// errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {

		/* 検索条件.受注番号From */
		setSrhOrderNoFrom(null);

		/* 検索条件.受注番号 */
		setSrhOrderNoTo(null);

		/* 検索条件.受注区分 */
		setSrhOrderDivision(null);

		/* 検索条件.ステータス */
		setSrhStatus(null);

		/* 検索条件.受注日From */
		setSrhOrderDateFrom(null);

		/* 検索条件.受注日To */
		setSrhOrderDateTo(null);

		/* 検索条件.出荷予定日From */
		setSrhScheduledShippingDateFrom(null);

		/* 検索条件.出荷予定日To */
		setSrhScheduledShippingDateTo(null);

		/* 検索条件.納入先コード */
		setSrhDeliveryCd(null);

		/* 検索条件.納入先名称 */
		setSrhDeliveryName1(null);

		/* 検索条件.納入先住所 */
		setSrhDeliveryAddress(null);

		/* 検索条件.納入先電話番号 */
		setSrhDeliveryTelNo(null);

		/* 検索条件.得意先コード */
		setSrhVenderCd(null);

		/* 検索条件.得意先名称 */
		setSrhVenderName1(null);

		/* 検索条件.担当部署コード */
		setSrhOrganizationCd(null);

		/* 検索条件.担当部署名称 */
		setSrhOrganizationName(null);

		/* 検索条件.営業担当者コード */
		setSrhSalesTantoCd(null);

		/* 検索条件.営業担当者名称 */
		setSrhSalesTantoName(null);

		/* 検索条件.入力担当者コード */
		setSrhInputTantoCd(null);

		/* 検索条件.入力担当者名称 */
		setSrhInputTantoName(null);

		/* 検索条件品目コード */
		setSrhItemCd(null);

		/* 検索条件品目コード */
		setSrhItemName(null);

		/* 検索条件他社コード */
		setSrhOtherCompanyCd1(null);

		setSrhCarryCd(null);

		/** EXCELダウンロードフラグ */
		setExcelDownloadFlg(false);

		setSearchList(new ArrayList<OrderList>());

		/** 帳票Ｅｘｃｅｌ検索条件 */
		setReportCondition(null);
		/** 受注一覧検索条件 */
		setSlipCondition(null);
		/** 受注一覧ダウンロードフラグ */
		setExcelSlipDownloadFlg(false);

	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<OrderList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<OrderList> searchList) {
		this.searchList = searchList;
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
	 * 検索入力.納入先宛先設定.
	 * @return srhDeliveryAddress
	 */
	public String getSrhDeliveryAddress() {
		return srhDeliveryAddress;
	}

	/**
	 * 検索入力.納入先宛先取得.
	 * @param srhDeliveryAddress srhDeliveryAddress
	 */
	public void setSrhDeliveryAddress(final String srhDeliveryAddress) {
		this.srhDeliveryAddress = srhDeliveryAddress;
	}

	/**
	 * 検索入力.納入先コード取得
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return srhDeliveryCd;
	}

	/**
	 * 検索入力.納入先コード設定.
	 * @param srhDeliveryCd srhDeliveryCd
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = srhDeliveryCd;
	}

	/**
	 * 納入先名称取得
	 * @return srhDeliveryName1
	 */
	public String getSrhDeliveryName1() {
		return srhDeliveryName1;
	}

	/**
	 * 納入先名称設定.
	 * @param srhDeliveryName1 srhDeliveryName1
	 */
	public void setSrhDeliveryName1(final String srhDeliveryName1) {
		this.srhDeliveryName1 = srhDeliveryName1;
	}

	/**
	 * 検索入力.納入先電話番号取得
	 * @return srhDeliveryTelNo
	 */
	public String getSrhDeliveryTelNo() {
		return srhDeliveryTelNo;
	}

	/**
	 * 検索入力.納入先電話番号設定.
	 * @param srhDeliveryTelNo srhDeliveryTelNo
	 */
	public void setSrhDeliveryTelNo(final String srhDeliveryTelNo) {
		this.srhDeliveryTelNo = srhDeliveryTelNo;
	}

	/**
	 * 検索入力.入力担当者コード取得
	 * @return srhInputTantoCd
	 */
	public String getSrhInputTantoCd() {
		return srhInputTantoCd;
	}

	/**
	 * 検索入力.入力担当者コード設定.
	 * @param srhInputTantoCd srhInputTantoCd
	 */
	public void setSrhInputTantoCd(final String srhInputTantoCd) {
		this.srhInputTantoCd = srhInputTantoCd;
	}

	/**
	 * 入力担当者取得
	 * @return srhInputTantoName
	 */
	public String getSrhInputTantoName() {
		return srhInputTantoName;
	}

	/**
	 * 入力担当者設定.
	 * @param srhInputTantoName srhInputTantoName
	 */
	public void setSrhInputTantoName(final String srhInputTantoName) {
		this.srhInputTantoName = srhInputTantoName;
	}

	/**
	 * 検索入力.品目コード取得
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 検索入力.品目コード設定.
	 * @param srhItemCd srhItemCd
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 検索入力.品目名称取得
	 * @return srhItemName
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 検索入力.品目名称設定.
	 * @param srhItemName srhItemName
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 検索入力.担当部署コード取得
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * 検索入力.担当部署コード設定.
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * 担当部署名称取得
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return srhOrganizationName;
	}

	/**
	 * 担当部署名称設定.
	 * @param srhOrganizationName srhOrganizationName
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
	}

	/**
	 * 検索入力.他社コード１取得
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * 検索入力.他社コード１設定.
	 * @param srhOtherCompanyCd1 srhOtherCompanyCd1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 検索入力.営業担当者コード取得
	 * @return srhSalesTantoCd
	 */
	public String getSrhSalesTantoCd() {
		return srhSalesTantoCd;
	}

	/**
	 * 検索入力.営業担当者コード設定.
	 * @param srhSalesTantoCd srhSalesTantoCd
	 */
	public void setSrhSalesTantoCd(final String srhSalesTantoCd) {
		this.srhSalesTantoCd = srhSalesTantoCd;
	}

	/**
	 * 営業担当者名称取得
	 * @return srhSalesTantoName
	 */
	public String getSrhSalesTantoName() {
		return srhSalesTantoName;
	}

	/**
	 * 営業担当者名称設定.
	 * @param srhSalesTantoName srhSalesTantoName
	 */
	public void setSrhSalesTantoName(final String srhSalesTantoName) {
		this.srhSalesTantoName = srhSalesTantoName;
	}

	/**
	 * 検索入力.出荷予定日From取得
	 * @return srhScheduledShippingDateFrom
	 */
	public String getSrhScheduledShippingDateFrom() {
		return srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力.出荷予定日From設定.
	 * @param srhScheduledShippingDateFrom srhScheduledShippingDateFrom
	 */
	public void setSrhScheduledShippingDateFrom(
			final String srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力.出荷予定日To取得
	 * @return srhScheduledShippingDateTo
	 */
	public String getSrhScheduledShippingDateTo() {
		return srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力.出荷予定日To設定.
	 * @param srhScheduledShippingDateTo srhScheduledShippingDateTo
	 */
	public void setSrhScheduledShippingDateTo(
			final String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力.ｽﾃｰﾀｽ取得
	 * @return srhStatus
	 */
	public java.math.BigDecimal getSrhStatus() {
		return srhStatus;
	}

	/**
	 * 検索入力.ｽﾃｰﾀｽ設定.
	 * @param srhStatus srhStatus
	 */
	public void setSrhStatus(final java.math.BigDecimal srhStatus) {
		this.srhStatus = srhStatus;
	}

	/**
	 * 検索入力.受注日From取得
	 * @return srhOrderDateFrom
	 */
	public String getSrhOrderDateFrom() {
		return srhOrderDateFrom;
	}

	/**
	 * 検索入力.受注日From設定.
	 * @param srhOrderDateFrom srhOrderDateFrom
	 */
	public void setSrhOrderDateFrom(final String srhOrderDateFrom) {
		this.srhOrderDateFrom = srhOrderDateFrom;
	}

	/**
	 * 検索入力.受注日To取得
	 * @return srhOrderDateTo
	 */
	public String getSrhOrderDateTo() {
		return srhOrderDateTo;
	}

	/**
	 * 検索入力.注日To設定.
	 * @param srhOrderDateTo srhOrderDateTo
	 */
	public void setSrhOrderDateTo(final String srhOrderDateTo) {
		this.srhOrderDateTo = srhOrderDateTo;
	}

	/**
	 * 検索入力.受注区分取得
	 * @return srhOrderDivision
	 */
	public java.math.BigDecimal getSrhOrderDivision() {
		return srhOrderDivision;
	}

	/**
	 * 検索入力.受注区分設定.
	 * @param srhOrderDivision srhOrderDivision
	 */
	public void setSrhOrderDivision(final java.math.BigDecimal srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * 検索入力.受注番号From取得
	 * @return srhOrderNoFrom
	 */
	public String getSrhOrderNoFrom() {
		return srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号From設定.
	 * @param srhOrderNoFrom srhOrderNoFrom
	 */
	public void setSrhOrderNoFrom(final String srhOrderNoFrom) {
		this.srhOrderNoFrom = srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号To取得
	 * @return srhOrderNoTo
	 */
	public String getSrhOrderNoTo() {
		return srhOrderNoTo;
	}

	/**
	 * 検索入力.受注番号To設定.
	 * @param srhOrderNoTo srhOrderNoTo
	 */
	public void setSrhOrderNoTo(final String srhOrderNoTo) {
		this.srhOrderNoTo = srhOrderNoTo;
	}

	/**
	 * 検索入力.取引先コード取得
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 検索入力.取引先コード設定.
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 受注区分ラベル取得
	 * @return orderDivisionLabels
	 */
	public String[] getOrderDivisionLabels() {
		return orderDivisionLabels;
	}

	/**
	 * 受注区分ラベル設定.
	 * @param orderDivisionLabels orderDivisionLabels
	 */
	public void setOrderDivisionLabels(final String[] orderDivisionLabels) {
		this.orderDivisionLabels = orderDivisionLabels;
	}

	/**
	 * 受注区分値取得
	 * @return orderDivisionValues
	 */
	public String[] getOrderDivisionValues() {
		return orderDivisionValues;
	}

	/**
	 * 受注区分値設定.
	 * @param orderDivisionValues orderDivisionValues
	 */
	public void setOrderDivisionValues(final String[] orderDivisionValues) {
		this.orderDivisionValues = orderDivisionValues;
	}

	/**
	 * 受注ステータスラベル取得
	 * @return orderStatusLabels
	 */
	public String[] getOrderStatusLabels() {
		return orderStatusLabels;
	}

	/**
	 * 受注ステータスラベル設定.
	 * @param orderStatusLabels orderStatusLabels
	 */
	public void setOrderStatusLabels(final String[] orderStatusLabels) {
		this.orderStatusLabels = orderStatusLabels;
	}

	/**
	 * 受注ステータス値取得
	 * @return orderStatusValues
	 */
	public String[] getOrderStatusValues() {
		return orderStatusValues;
	}

	/**
	 * 受注ステータス値設定.
	 * @param orderStatusValues orderStatusValues
	 */
	public void setOrderStatusValues(final String[] orderStatusValues) {
		this.orderStatusValues = orderStatusValues;
	}

	/**
	 * 得意先名称取得
	 * @return srhVenderName1
	 */
	public String getSrhVenderName1() {
		return srhVenderName1;
	}

	/**
	 * 得意先名称設定.
	 * @param srhVenderName1 srhVenderName1
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = srhVenderName1;
	}

	/**
	 * 帳票用コンディション取得
	 * @return reportCondition
	 */
	public OrderListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * 帳票用コンディション設定.
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final OrderListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * 受注一覧コンディション取得
	 * @return slipCondition
	 */
	public RepOrderSlipConditionForReport getSlipCondition() {
		return slipCondition;
	}

	/**
	 * 受注一覧コンディション設定.
	 * @param slipCondition slipCondition
	 */
	public void setSlipCondition(
			final RepOrderSlipConditionForReport slipCondition) {
		this.slipCondition = slipCondition;
	}

	/**
	 * 運送会社コンボボックスを取得します。
	 * @return 運送会社コンボボックス
	 */
	public List<ComboBoxItems> getCarryCombo() {
		return carryCombo;
	}

	/**
	 * 運送会社コンボボックスを設定します。
	 * @param carryCombo 運送会社コンボボックス
	 */
	public void setCarryCombo(final List<ComboBoxItems> carryCombo) {
		this.carryCombo = carryCombo;
	}

	/**
	 * 受注一覧Excelダウンロードフラグ取得
	 * @return excelSlipDownloadFlg
	 */
	public boolean isExcelSlipDownloadFlg() {
		return excelSlipDownloadFlg;
	}

	/**
	 * 受注一覧Excelダウンロードフラグ設定.
	 * @param excelSlipDownloadFlg excelSlipDownloadFlg
	 */
	public void setExcelSlipDownloadFlg(final boolean excelSlipDownloadFlg) {
		this.excelSlipDownloadFlg = excelSlipDownloadFlg;
	}

	/**
	 * srhCarryCdを取得します。
	 * @return srhCarryCd
	 */
	public String getSrhCarryCd() {
		return srhCarryCd;
	}

	/**
	 * srhCarryCdを設定します。
	 * @param srhCarryCd srhCarryCd
	 */
	public void setSrhCarryCd(final String srhCarryCd) {
		this.srhCarryCd = srhCarryCd;
	}
}
