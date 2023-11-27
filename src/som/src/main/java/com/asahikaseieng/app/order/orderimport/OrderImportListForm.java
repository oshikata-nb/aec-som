/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportList;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportListPagerCondition;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 受注取込一覧 Formクラス
 * @author 
 */
public final class OrderImportListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
//		ResourceBundle rb = ResourceBundle.getBundle(Constants.SYSTEM_PROPERTIES);

//		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		PAGE_ROW = 500;
//		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
		DATA_ROW = 5000;
	}

	//
	// 受注取込検索入力用
	//
	/** 受注リスト */
	private List<OrderImportList> searchList;
	
	/** 受注リスト長さ*/
	private int searchListCount;
	/** 検索ボタン押下時の検索結果リスト */
	private  List<OrderImportList> beforeSearchList;
	/** 得意先グループコンボボックス */
	private List<ComboBoxItems> venderGroupCombo;
	/** 受注区分コンボボックス*/
	private List<ComboBoxItems> orderDivisionCombo;
	/** 受注ステータスコンボボックス*/
	private List<ComboBoxItems> statusCombo;
	/** 客先返信コンボボックス*/
	private List<ComboBoxItems> slipPublishCompCombo;
	/** 仮単価コンボボックス*/
	private List<ComboBoxItems> tmpUnitpriceFlgCombo;
	/** 運送会社コンボボックス */
	private List<ComboBoxItems> carryCombo;
	/** エラー状態コンボボックス*/
	private List<ComboBoxItems> errorStatusCombo;
	/** データ作成区分コンボボックス*/
	private List<ComboBoxItems> inputDivisionCombo;

	/* 検索条件.客先注文番号 */
	private String srhCtmOrderNo;
	/* 検索条件.得意先グループコード */
	private String srhVenderGroupCd;
	/* 検索条件.取込日From*/
	private String srhImportDateFrom;
	/* 検索条件.取込日To*/
	private String srhImportDateTo;
	/* 検索条件.受注番号From */
	private String srhOrderNoFrom;
	/* 検索条件.受注番号To */
	private String srhOrderNoTo;
	/* 検索条件.受注区分 */
	private BigDecimal srhOrderDivision;
	/* 検索条件.受注ステータス */
	private BigDecimal srhOrderStatus;
	/* 検索条件.受注日From */
	private String srhOrderDateFrom;
	/* 検索条件.受注日To */
	private String srhOrderDateTo;
	/* 検索条件.納入先コード */
	private String srhDeliveryCd;
	/* 検索条件.納入先名称 */
	private String srhDeliveryName;
	/* 検索条件.納入先住所 */
	private String srhAddress;
	/* 検索条件.客先返信 */
	private BigDecimal srhSlipPublishComp;
	/* 検索条件.出荷予定日From */
	private String srhScheduledShippingDateFrom;
	/* 検索条件.出荷予定日To */
	private String srhScheduledShippingDateTo;
	/* 検索条件.得意先コード */
	private String srhVenderCd;
	/* 検索条件.得意先名称 */
	private String srhVenderName;
	/* 検索条件.担当部署コード */
	private String srhOrganizationCd;
	/* 検索条件.担当部署名称 */
	private String srhOrganizationName;
	/* 検索条件.仮単価 */
	private String srhTmpUnitpriceFlg;
	/* 検索条件.営業担当者コード */
	private String srhSalesTantoCd;
	/* 検索条件.営業担当者名称 */
	private String srhSalesTantoName;
	/* 検索条件.入力担当者コード */
	private String srhInputTantoCd;
	/* 検索条件.入力担当者名称 */
	private String srhInputTantoName;
	/* 検索条件.出荷BC */
	private String srhCarryBc;
	/* 検索条件品目コード */
	private String srhItemCd;
	/* 検索条件品目名 */
	private String srhItemName;
	/* 検索条件他社コード1 */
	private String srhOtherCompanyCd1;
	/* 検索条件.納入先電話番号 */
	private String srhDeliveryTelNo;
	/* 検索条件.希望納期From*/
	private String srhSuggestedDeliverlimitFrom;
	/* 検索条件.希望納期To*/
	private String srhSuggestedDeliverlimitTo;
	/* 検索条件.運送会社コード */
	private String srhCarryCd;
	/* 検索条件.エラー状態*/
	private String srhErrorStatus;
	/* 検索条件.データ作成区分*/
	private BigDecimal srhInputDivision;
	/* 検索条件.納品予定日From*/
	private String srhDeliveryExpectedDateFrom;
	/* 検索条件.納品予定日To*/
	private String srhDeliveryExpectedDateTo;
	/* 検索条件.取込番号From*/
	private String srhTempNoFrom;
	/* 検索条件.取込番号To*/
	private String srhTempNoTo;
	/* 検索条件.受注グループ番号From*/
	private String srhFrstOrderNoFrom;
	/* 検索条件.受注グループ番号To*/
	private String srhFrstOrderNoTo;
	/* 検索条件.入力チェック */
	private String srhOrderInputCheck;
	/* 検索条件.納期連絡 */
	private String srhDelDateSend;
	/* 検索条件.削除、キャンセル */
	private String srhDeleteCancel;

	
	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;
	/* 受注区分名称 */
	private String[] orderDivisionValues;
	private String[] orderDivisionLabels;
	/* 受注ステータス名称 */
	private String[] orderStatusValues;
	private String[] orderStatusLabels;
	private OrderImportListConditionForReport reportCondition;
	private RepOrderImportSlipConditionForReport slipCondition;
	/** EXCELダウンロードフラグ */
	private boolean excelSlipDownloadFlg;
	/** 変更フラグ */
	private Boolean dirtyFlg;
	
	// 得意先への返信ファイル送信してもよいかのフラグ。得意先グループでの検索ができているかのチェック
	private Boolean fileSendOk;

	// 送信完了フラグ
	private int sendCompFlg;
	
	/**
	 * コンストラクタ.
	 */
	public OrderImportListForm() {
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
	protected Class<OrderImportListPagerCondition> getPagerConditionClass() {
		return OrderImportListPagerCondition.class;
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
			setSearchList(new ArrayList<OrderImportList>());
			// Validatorによる入力チェック
			errors = super.validate(mapping, request);
		}

		if ("reSearch".equals(getOp())) {
			// リストのクリア
			setSearchList(new ArrayList<OrderImportList>());
			// Validatorによる入力チェック
			// errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		
		/* 検索ボタン押下時検索リスト*/
		setBeforeSearchList(new ArrayList<OrderImportList>());

		/* 検索条件.客先注文番号To */
		setSrhCtmOrderNo(null);
		
		/* 検索条件.得意先グループコード */
		setSrhVenderGroupCd(null);
		
		/* 検索条件.取込日From*/
		setSrhImportDateFrom(null);
		
		/* 検索条件.取込日To*/
		setSrhImportDateTo(null);
		
		/* 検索条件.受注番号From */
		setSrhOrderNoFrom(null);

		/* 検索条件.受注番号To */
		setSrhOrderNoTo(null);

		/* 検索条件.受注区分 */
		setSrhOrderDivision(null);
		
		/* 検索条件.受注ステータス */
		setSrhOrderStatus(null);

		/* 検索条件.受注日From */
		setSrhOrderDateFrom(null);

		/* 検索条件.受注日To */
		setSrhOrderDateTo(null);

		/* 検索条件.納入先コード */
		setSrhDeliveryCd(null);

		/* 検索条件.納入先名称 */
		setSrhDeliveryName(null);

		/* 検索条件.納入先住所 */
		setSrhAddress(null);
		
		/* 検索条件.客先返信 */
		setSrhSlipPublishComp(null);

		/* 検索条件.出荷予定日From */
		setSrhScheduledShippingDateFrom(null);

		/* 検索条件.出荷予定日To */
		setSrhScheduledShippingDateTo(null);

		/* 検索条件.得意先コード */
		setSrhVenderCd(null);

		/* 検索条件.得意先名称 */
		setSrhVenderName(null);

		/* 検索条件.担当部署コード */
		setSrhOrganizationCd(null);

		/* 検索条件.担当部署名称 */
		setSrhOrganizationName(null);
		
		/* 検索条件.仮単価(初期値 9:すべて)*/
		setSrhTmpUnitpriceFlg("9");
		
		/* 検索条件.営業担当者コード */
		setSrhSalesTantoCd(null);

		/* 検索条件.営業担当者名称 */
		setSrhSalesTantoName(null);

		/* 検索条件.入力担当者コード */
		setSrhInputTantoCd(null);

		/* 検索条件.入力担当者名称 */
		setSrhInputTantoName(null);
		
		/* 検索条件.出荷BC */
		setSrhCarryBc(null);

		/* 検索条件品目コード */
		setSrhItemCd(null);

		/* 検索条件品目名 */
		setSrhItemName(null);

		/* 検索条件他社コード1 */
		setSrhOtherCompanyCd1(null);

		/* 検索条件.納入先電話番号 */
		setSrhDeliveryTelNo(null);
		
		/* 検索条件.希望納期From*/
		setSrhSuggestedDeliverlimitFrom(null);

		/* 検索条件.希望納期To*/
		setSrhSuggestedDeliverlimitTo(null);

		/* 検索条件.運送会社コード */
		setSrhCarryCd(null);
		
		/* 検索条件.エラー状態*/
		setSrhErrorStatus(null);
		
		/* 検索条件.データ作成区分*/
		setSrhInputDivision(null);
		
		/* 検索条件.納品予定日From*/
		setSrhDeliveryExpectedDateFrom(null);
		
		/* 検索条件.納品予定日To*/
		setSrhDeliveryExpectedDateTo(null);
		
		/* 検索条件.受注グループ番号From*/
		setSrhFrstOrderNoFrom(null);
		
		/* 検索条件.受注グループ番号To*/
		setSrhFrstOrderNoTo(null);
		
		/* 受注リスト */
		setSearchList(new ArrayList<OrderImportList>());


		/* 検索条件.取込ステータス*/
		setSrhDeleteCancel(null);

		/* 検索条件.取込ステータス*/
		setSrhDelDateSend(null);

		/* 検索条件.取込ステータス*/
		setSrhOrderInputCheck(null);

		/** EXCELダウンロードフラグ */
		setExcelDownloadFlg(false);

		/** 帳票EXCEL検索条件 */
		setReportCondition(null);
		/** 受注一覧ダウンロードフラグ */
		setExcelSlipDownloadFlg(false);
		
		/** 変更フラグ */
		setDirtyFlg(false);
		
		// 得意先ファイル送信判定OKフラグ
		setFileSendOk(Boolean.FALSE);
		
	}
	
	/**
	 * beforeSearchListを取得します。
	 * @return beforeSearchList
	 */
	public List<OrderImportList> getBeforeSearchList() {
		return beforeSearchList;
	}

	/**
	 * beforeSearchListを設定します。
	 * @param beforeSearchList beforeSearchList
	 */
	public void setBeforeSearchList(final List<OrderImportList> beforeSearchList) {
		this.beforeSearchList = beforeSearchList;
	}

	/**
	 * 検索結果件数を取得
	 * @return int 件数
	 */
	public int getSearchListLength() {
		return searchList.size();
	}

	/**
	 * searchListCountを取得します。
	 * @return searchListCount
	 */
	public int getSearchListCount() {
		return searchListCount;
	}

	/**
	 * searchListCountを設定します。
	 * @param searchListCount searchListCount
	 */
	public void setSearchListCount(int searchListCount) {
		this.searchListCount = searchListCount;
	}

	/**
	 * srhCtmOrderNoを取得します。
	 * @return srhCtmOrderNo
	 */
	public String getSrhCtmOrderNo() {
		return srhCtmOrderNo;
	}

	/**
	 * srhCtmOrderNoを設定します。
	 * @param srhCtmOrderNo srhCtmOrderNo
	 */
	public void setSrhCtmOrderNo(final String srhCtmOrderNo) {
		this.srhCtmOrderNo = srhCtmOrderNo;
	}

	/**
	 * venderGroupComboを取得します。
	 * @return venderGroupCombo
	 */
	public List<ComboBoxItems> getVenderGroupCombo() {
		return venderGroupCombo;
	}

	/**
	 * venderGroupComboを設定します。
	 * @param venderGroupCombo venderGroupCombo
	 */
	public void setVenderGroupCombo(final List<ComboBoxItems> venderGroupCombo) {
		this.venderGroupCombo = venderGroupCombo;
	}

	/**
	 * srhVenderGroupCdを取得します。
	 * @return srhVenderGroupCd
	 */
	public String getSrhVenderGroupCd() {
		return srhVenderGroupCd;
	}

	/**
	 * srhVenderGroupCdを設定します。
	 * @param srhVenderGroupCd srhVenderGroupCd
	 */
	public void setSrhVenderGroupCd(final String srhVenderGroupCd) {
		this.srhVenderGroupCd = srhVenderGroupCd;
	}

	/**
	 * srhImportDateFromを取得します。
	 * @return srhImportDateFrom
	 */
	public String getSrhImportDateFrom() {
		return srhImportDateFrom;
	}

	/**
	 * srhImportDateFromを設定します。
	 * @param srhImportDateFrom srhImportDateFrom
	 */
	public void setSrhImportDateFrom(final String srhImportDateFrom) {
		this.srhImportDateFrom = srhImportDateFrom;
	}

	/**
	 * srhImportDateToを取得します。
	 * @return srhImportDateTo
	 */
	public String getSrhImportDateTo() {
		return srhImportDateTo;
	}

	/**
	 * srhImportDateToを設定します。
	 * @param srhImportDateTo srhImportDateTo
	 */
	public void setSrhImportDateTo(final String srhImportDateTo) {
		this.srhImportDateTo = srhImportDateTo;
	}

	/**
	 * srhOrderNoFromを取得します。
	 * @return srhOrderNoFrom
	 */
	public String getSrhOrderNoFrom() {
		return srhOrderNoFrom;
	}

	/**
	 * srhOrderNoFromを設定します。
	 * @param srhOrderNoFrom srhOrderNoFrom
	 */
	public void setSrhOrderNoFrom(final String srhOrderNoFrom) {
		this.srhOrderNoFrom = srhOrderNoFrom;
	}

	/**
	 * srhOrderNoToを取得します。
	 * @return srhOrderNoTo
	 */
	public String getSrhOrderNoTo() {
		return srhOrderNoTo;
	}

	/**
	 * srhOrderNoToを設定します。
	 * @param srhOrderNoTo srhOrderNoTo
	 */
	public void setSrhOrderNoTo(final String srhOrderNoTo) {
		this.srhOrderNoTo = srhOrderNoTo;
	}

	/**
	 * orderDivisionComboを取得します。
	 * @return orderDivisionCombo
	 */
	public List<ComboBoxItems> getOrderDivisionCombo() {
		return orderDivisionCombo;
	}

	/**
	 * orderDivisionComboを設定します。
	 * @param orderDivisionCombo orderDivisionCombo
	 */
	public void setOrderDivisionCombo(List<ComboBoxItems> orderDivisionCombo) {
		this.orderDivisionCombo = orderDivisionCombo;
	}

	/**
	 * srhOrderDivisionを取得します。
	 * @return srhOrderDivision
	 */
	public BigDecimal getSrhOrderDivision() {
		return srhOrderDivision;
	}

	/**
	 * srhOrderDivisionを設定します。
	 * @param srhOrderDivision srhOrderDivision
	 */
	public void setSrhOrderDivision(final BigDecimal srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * statusComboを取得します。
	 * @return statusCombo
	 */
	public List<ComboBoxItems> getStatusCombo() {
		return statusCombo;
	}

	/**
	 * statusComboを設定します。
	 * @param statusCombo statusCombo
	 */
	public void setStatusCombo(List<ComboBoxItems> statusCombo) {
		this.statusCombo = statusCombo;
	}

	/**
	 * srhOrderStatusを取得します。
	 * @return srhOrderStatus
	 */
	public BigDecimal getSrhOrderStatus() {
		return srhOrderStatus;
	}

	/**
	 * srhOrderStatusを設定します。
	 * @param srhOrderStatus srhOrderStatus
	 */
	public void setSrhOrderStatus(final BigDecimal srhOrderStatus) {
		this.srhOrderStatus = srhOrderStatus;
	}

	/**
	 * srhOrderDateFromを取得します。
	 * @return srhOrderDateFrom
	 */
	public String getSrhOrderDateFrom() {
		return srhOrderDateFrom;
	}

	/**
	 * srhOrderDateFromを設定します。
	 * @param srhOrderDateFrom srhOrderDateFrom
	 */
	public void setSrhOrderDateFrom(final String srhOrderDateFrom) {
		this.srhOrderDateFrom = srhOrderDateFrom;
	}

	/**
	 * srhOrderDateToを取得します。
	 * @return srhOrderDateTo
	 */
	public String getSrhOrderDateTo() {
		return srhOrderDateTo;
	}

	/**
	 * srhOrderDateToを設定します。
	 * @param srhOrderDateTo srhOrderDateTo
	 */
	public void setSrhOrderDateTo(final String srhOrderDateTo) {
		this.srhOrderDateTo = srhOrderDateTo;
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
	 * srhDeliveryNameを取得します。
	 * @return srhDeliveryName
	 */
	public String getSrhDeliveryName() {
		return srhDeliveryName;
	}

	/**
	 * srhDeliveryNameを設定します。
	 * @param srhDeliveryName srhDeliveryName
	 */
	public void setSrhDeliveryName(final String srhDeliveryName) {
		this.srhDeliveryName = srhDeliveryName;
	}

	/**
	 * srhAddressを取得します。
	 * @return srhAddress
	 */
	public String getSrhAddress() {
		return srhAddress;
	}

	/**
	 * srhAddressを設定します。
	 * @param srhAddress srhAddress
	 */
	public void setSrhAddress(final String srhAddress) {
		this.srhAddress = srhAddress;
	}

	/**
	 * slipPublishCompComboを取得します。
	 * @return slipPublishCompCombo
	 */
	public List<ComboBoxItems> getSlipPublishCompCombo() {
		return slipPublishCompCombo;
	}

	/**
	 * slipPublishCompComboを設定します。
	 * @param slipPublishCompCombo slipPublishCompCombo
	 */
	public void setSlipPublishCompCombo(List<ComboBoxItems> slipPublishCompCombo) {
		this.slipPublishCompCombo = slipPublishCompCombo;
	}

	/**
	 * srhSlipPublishCompを取得します。
	 * @return srhSlipPublishComp
	 */
	public BigDecimal getSrhSlipPublishComp() {
		return srhSlipPublishComp;
	}

	/**
	 * srhSlipPublishCompを設定します。
	 * @param srhSlipPublishComp srhSlipPublishComp
	 */
	public void setSrhSlipPublishComp(final BigDecimal srhSlipPublishComp) {
		this.srhSlipPublishComp = srhSlipPublishComp;
	}

	/**
	 * srhScheduledShippingDateFromを取得します。
	 * @return srhScheduledShippingDateFrom
	 */
	public String getSrhScheduledShippingDateFrom() {
		return srhScheduledShippingDateFrom;
	}

	/**
	 * srhScheduledShippingDateFromを設定します。
	 * @param srhScheduledShippingDateFrom srhScheduledShippingDateFrom
	 */
	public void setSrhScheduledShippingDateFrom(final String srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}

	/**
	 * srhScheduledShippingDateToを取得します。
	 * @return srhScheduledShippingDateTo
	 */
	public String getSrhScheduledShippingDateTo() {
		return srhScheduledShippingDateTo;
	}

	/**
	 * srhScheduledShippingDateToを設定します。
	 * @param srhScheduledShippingDateTo srhScheduledShippingDateTo
	 */
	public void setSrhScheduledShippingDateTo(final String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * srhVenderCdを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * srhVenderCdを設定します。
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * srhVenderNameを取得します。
	 * @return srhVenderName
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * srhVenderNameを設定します。
	 * @param srhVenderName srhVenderName
	 */
	public void setSrhVenderName(final String srhVenderName) {
		this.srhVenderName = srhVenderName;
	}

	/**
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * srhOrganizationNameを取得します。
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return srhOrganizationName;
	}

	/**
	 * srhOrganizationNameを設定します。
	 * @param srhOrganizationName srhOrganizationName
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
	}

	/**
	 * tmpUnitpriceFlgComboを取得します。
	 * @return tmpUnitpriceFlgCombo
	 */
	public List<ComboBoxItems> getTmpUnitpriceFlgCombo() {
		return tmpUnitpriceFlgCombo;
	}

	/**
	 * tmpUnitpriceFlgComboを設定します。
	 * @param tmpUnitpriceFlgCombo tmpUnitpriceFlgCombo
	 */
	public void setTmpUnitpriceFlgCombo(List<ComboBoxItems> tmpUnitpriceFlgCombo) {
		this.tmpUnitpriceFlgCombo = tmpUnitpriceFlgCombo;
	}

	/**
	 * srhTmpUnitpriceFlgを取得します。
	 * @return srhTmpUnitpriceFlg
	 */
	public String getSrhTmpUnitpriceFlg() {
		return srhTmpUnitpriceFlg;
	}

	/**
	 * srhTmpUnitpriceFlgを設定します。
	 * @param srhTmpUnitpriceFlg srhTmpUnitpriceFlg
	 */
	public void setSrhTmpUnitpriceFlg(final String srhTmpUnitpriceFlg) {
		this.srhTmpUnitpriceFlg = srhTmpUnitpriceFlg;
	}

	/**
	 * srhSalesTantoCdを取得します。
	 * @return srhSalesTantoCd
	 */
	public String getSrhSalesTantoCd() {
		return srhSalesTantoCd;
	}

	/**
	 * srhSalesTantoCdを設定します。
	 * @param srhSalesTantoCd srhSalesTantoCd
	 */
	public void setSrhSalesTantoCd(final String srhSalesTantoCd) {
		this.srhSalesTantoCd = srhSalesTantoCd;
	}

	/**
	 * srhSalesTantoNameを取得します。
	 * @return srhSalesTantoName
	 */
	public String getSrhSalesTantoName() {
		return srhSalesTantoName;
	}

	/**
	 * srhSalesTantoNameを設定します。
	 * @param srhSalesTantoName srhSalesTantoName
	 */
	public void setSrhSalesTantoName(final String srhSalesTantoName) {
		this.srhSalesTantoName = srhSalesTantoName;
	}

	/**
	 * srhInputTantoCdを取得します。
	 * @return srhInputTantoCd
	 */
	public String getSrhInputTantoCd() {
		return srhInputTantoCd;
	}

	/**
	 * srhInputTantoCdを設定します。
	 * @param srhInputTantoCd srhInputTantoCd
	 */
	public void setSrhInputTantoCd(final String srhInputTantoCd) {
		this.srhInputTantoCd = srhInputTantoCd;
	}

	/**
	 * srhInputTantoNameを取得します。
	 * @return srhInputTantoName
	 */
	public String getSrhInputTantoName() {
		return srhInputTantoName;
	}

	/**
	 * srhInputTantoNameを設定します。
	 * @param srhInputTantoName srhInputTantoName
	 */
	public void setSrhInputTantoName(final String srhInputTantoName) {
		this.srhInputTantoName = srhInputTantoName;
	}

	/**
	 * srhCarryBcを取得します。
	 * @return srhCarryBc
	 */
	public String getSrhCarryBc() {
		return srhCarryBc;
	}

	/**
	 * srhCarryBcを設定します。
	 * @param srhCarryBc srhCarryBc
	 */
	public void setSrhCarryBc(final String srhCarryBc) {
		this.srhCarryBc = srhCarryBc;
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
	 * srhDeliveryTelNoを取得します。
	 * @return srhDeliveryTelNo
	 */
	public String getSrhDeliveryTelNo() {
		return srhDeliveryTelNo;
	}

	/**
	 * srhDeliveryTelNoを設定します。
	 * @param srhDeliveryTelNo srhDeliveryTelNo
	 */
	public void setSrhDeliveryTelNo(final String srhDeliveryTelNo) {
		this.srhDeliveryTelNo = srhDeliveryTelNo;
	}

	/**
	 * srhSuggestedDeliverlimitFromを取得します。
	 * @return srhSuggestedDeliverlimitFrom
	 */
	public String getSrhSuggestedDeliverlimitFrom() {
		return srhSuggestedDeliverlimitFrom;
	}

	/**
	 * srhSuggestedDeliverlimitFromを設定します。
	 * @param srhSuggestedDeliverlimitFrom srhSuggestedDeliverlimitFrom
	 */
	public void setSrhSuggestedDeliverlimitFrom(final String srhSuggestedDeliverlimitFrom) {
		this.srhSuggestedDeliverlimitFrom = srhSuggestedDeliverlimitFrom;
	}

	/**
	 * srhSuggestedDeliverlimitToを取得します。
	 * @return srhSuggestedDeliverlimitTo
	 */
	public String getSrhSuggestedDeliverlimitTo() {
		return srhSuggestedDeliverlimitTo;
	}

	/**
	 * srhSuggestedDeliverlimitToを設定します。
	 * @param srhSuggestedDeliverlimitTo srhSuggestedDeliverlimitTo
	 */
	public void setSrhSuggestedDeliverlimitTo(final String srhSuggestedDeliverlimitTo) {
		this.srhSuggestedDeliverlimitTo = srhSuggestedDeliverlimitTo;
	}

	/**
	 * carryComboを取得します。
	 * @return carryCombo
	 */
	public List<ComboBoxItems> getCarryCombo() {
		return carryCombo;
	}

	/**
	 * carryComboを設定します。
	 * @param carryCombo carryCombo
	 */
	public void setCarryCombo(List<ComboBoxItems> carryCombo) {
		this.carryCombo = carryCombo;
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

	/**
	 * errorStatusComboを取得します。
	 * @return errorStatusCombo
	 */
	public List<ComboBoxItems> getErrorStatusCombo() {
		return errorStatusCombo;
	}

	/**
	 * errorStatusComboを設定します。
	 * @param errorStatusCombo errorStatusCombo
	 */
	public void setErrorStatusCombo(List<ComboBoxItems> errorStatusCombo) {
		this.errorStatusCombo = errorStatusCombo;
	}

	/**
	 * srhErrorStatusを取得します。
	 * @return srhErrorStatus
	 */
	public String getSrhErrorStatus() {
		return srhErrorStatus;
	}

	/**
	 * srhErrorStatusを設定します。
	 * @param srhErrorStatus srhErrorStatus
	 */
	public void setSrhErrorStatus(final String srhErrorStatus) {
		this.srhErrorStatus = srhErrorStatus;
	}

	/**
	 * inputDivisionComboを取得します。
	 * @return inputDivisionCombo
	 */
	public List<ComboBoxItems> getInputDivisionCombo() {
		return inputDivisionCombo;
	}

	/**
	 * inputDivisionComboを設定します。
	 * @param inputDivisionCombo inputDivisionCombo
	 */
	public void setInputDivisionCombo(List<ComboBoxItems> inputDivisionCombo) {
		this.inputDivisionCombo = inputDivisionCombo;
	}

	/**
	 * srhInputDivisionを取得します。
	 * @return srhInputDivision
	 */
	public BigDecimal getSrhInputDivision() {
		return srhInputDivision;
	}

	/**
	 * srhInputDivisionを設定します。
	 * @param srhInputDivision srhInputDivision
	 */
	public void setSrhInputDivision(final BigDecimal srhInputDivision) {
		this.srhInputDivision = srhInputDivision;
	}

	/**
	 * srhDeliveryExpectedDateFromを取得します。
	 * @return srhDeliveryExpectedDateFrom
	 */
	public String getSrhDeliveryExpectedDateFrom() {
		return srhDeliveryExpectedDateFrom;
	}

	/**
	 * srhDeliveryExpectedDateFromを設定します。
	 * @param srhDeliveryExpectedDateFrom srhDeliveryExpectedDateFrom
	 */
	public void setSrhDeliveryExpectedDateFrom(final String srhDeliveryExpectedDateFrom) {
		this.srhDeliveryExpectedDateFrom = srhDeliveryExpectedDateFrom;
	}

	/**
	 * srhDeliveryExpectedDateToを取得します。
	 * @return srhDeliveryExpectedDateTo
	 */
	public String getSrhDeliveryExpectedDateTo() {
		return srhDeliveryExpectedDateTo;
	}

	/**
	 * srhDeliveryExpectedDateToを設定します。
	 * @param srhDeliveryExpectedDateTo srhDeliveryExpectedDateTo
	 */
	public void setSrhDeliveryExpectedDateTo(final String srhDeliveryExpectedDateTo) {
		this.srhDeliveryExpectedDateTo = srhDeliveryExpectedDateTo;
	}

	/**
	 * srhFrstOrderNoFromを取得します。
	 * @return srhFrstOrderNoFrom
	 */
	public String getSrhFrstOrderNoFrom() {
		return srhFrstOrderNoFrom;
	}

	/**
	 * srhFrstOrderNoFromを設定します。
	 * @param srhFrstOrderNoFrom srhFrstOrderNoFrom
	 */
	public void setSrhFrstOrderNoFrom(String srhFrstOrderNoFrom) {
		this.srhFrstOrderNoFrom = srhFrstOrderNoFrom;
	}

	/**
	 * srhFrstOrderNoToを取得します。
	 * @return srhFrstOrderNoTo
	 */
	public String getSrhFrstOrderNoTo() {
		return srhFrstOrderNoTo;
	}

	/**
	 * srhFrstOrderNoToを設定します。
	 * @param srhFrstOrderNoTo srhFrstOrderNoTo
	 */
	public void setSrhFrstOrderNoTo(String srhFrstOrderNoTo) {
		this.srhFrstOrderNoTo = srhFrstOrderNoTo;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<OrderImportList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(List<OrderImportList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * orderDivisionValuesを取得します。
	 * @return orderDivisionValues
	 */
	public String[] getOrderDivisionValues() {
		return orderDivisionValues;
	}

	/**
	 * orderDivisionValuesを設定します。
	 * @param orderDivisionValues orderDivisionValues
	 */
	public void setOrderDivisionValues(final String[] orderDivisionValues) {
		this.orderDivisionValues = orderDivisionValues;
	}

	/**
	 * orderDivisionLabelsを取得します。
	 * @return orderDivisionLabels
	 */
	public String[] getOrderDivisionLabels() {
		return orderDivisionLabels;
	}

	/**
	 * orderDivisionLabelsを設定します。
	 * @param orderDivisionLabels orderDivisionLabels
	 */
	public void setOrderDivisionLabels(final String[] orderDivisionLabels) {
		this.orderDivisionLabels = orderDivisionLabels;
	}

	/**
	 * orderStatusValuesを取得します。
	 * @return orderStatusValues
	 */
	public String[] getOrderStatusValues() {
		return orderStatusValues;
	}

	/**
	 * orderStatusValuesを設定します。
	 * @param orderStatusValues orderStatusValues
	 */
	public void setOrderStatusValues(final String[] orderStatusValues) {
		this.orderStatusValues = orderStatusValues;
	}

	/**
	 * orderStatusLabelsを取得します。
	 * @return orderStatusLabels
	 */
	public String[] getOrderStatusLabels() {
		return orderStatusLabels;
	}

	/**
	 * orderStatusLabelsを設定します。
	 * @param orderStatusLabels orderStatusLabels
	 */
	public void setOrderStatusLabels(final String[] orderStatusLabels) {
		this.orderStatusLabels = orderStatusLabels;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition
	 */
	public OrderImportListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(final OrderImportListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}
	
	/**
	 * slipConditionを取得します。
	 * @return slipCondition
	 */
	public RepOrderImportSlipConditionForReport getSlipCondition() {
		return slipCondition;
	}

	/**
	 * slipConditionを設定します。
	 * @param slipCondition slipCondition
	 */
	public void setSlipCondition(RepOrderImportSlipConditionForReport slipCondition) {
		this.slipCondition = slipCondition;
	}

	/**
	 * excelSlipDownloadFlgを取得します。
	 * @return excelSlipDownloadFlg
	 */
	public boolean isExcelSlipDownloadFlg() {
		return excelSlipDownloadFlg;
	}

	/**
	 * excelSlipDownloadFlgを設定します。
	 * @param excelSlipDownloadFlg excelSlipDownloadFlg
	 */
	public void setExcelSlipDownloadFlg(final boolean excelSlipDownloadFlg) {
		this.excelSlipDownloadFlg = excelSlipDownloadFlg;
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
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * searchDirtyFlgを取得します。
	 * @return searchDirtyFlg
	 */
	public Boolean getSearchDirtyFlg() {
		if( 
		   (this.srhAddress != null && !this.srhAddress.isEmpty())
		   || (this.srhCarryBc != null && !this.srhCarryBc.isEmpty())
		   || (this.srhCarryCd != null && !this.srhCarryCd.equals( "0" ) )
		   || (this.srhCtmOrderNo != null && !this.srhCtmOrderNo.isEmpty())
		   || (this.srhDeliveryCd != null && !this.srhDeliveryCd.isEmpty())
		   || (this.srhDeliveryExpectedDateFrom != null && !this.srhDeliveryExpectedDateFrom.isEmpty())
		   || (this.srhDeliveryExpectedDateTo != null && !this.srhDeliveryExpectedDateTo.isEmpty())
		   || (this.srhDeliveryName != null && !this.srhDeliveryName.isEmpty())
		   || (this.srhDeliveryTelNo != null && !this.srhDeliveryTelNo.isEmpty())
		   || (this.srhErrorStatus != null && !this.srhErrorStatus.equals("0"))
		   || (this.srhImportDateFrom != null && !this.srhImportDateFrom.isEmpty())
		   || (this.srhImportDateTo != null && !this.srhImportDateTo.isEmpty())
		   || (this.srhInputDivision != null && this.srhInputDivision.intValue() != 0)
		   || (this.srhInputTantoCd != null && !this.srhInputTantoCd.isEmpty())
		   || (this.srhInputTantoName != null && !this.srhInputTantoName.isEmpty())
		   || (this.srhItemCd != null && !this.srhItemCd.isEmpty())
		   || (this.srhItemName != null && !this.srhItemName.isEmpty())
		   || (this.srhOrderDateFrom != null && !this.srhOrderDateFrom.isEmpty())
		   || (this.srhOrderDateTo != null && !this.srhOrderDateTo.isEmpty())
		   || (this.srhOrderDivision  != null && this.srhOrderDivision.intValue() != 0)
		   || (this.srhFrstOrderNoFrom != null && !this.srhFrstOrderNoFrom.isEmpty())
		   || (this.srhFrstOrderNoTo != null && !this.srhFrstOrderNoTo.isEmpty())
		   || (this.srhOrderNoFrom != null && !this.srhOrderNoFrom.isEmpty())
		   || (this.srhOrderNoTo != null && !this.srhOrderNoTo.isEmpty())
		   || (this.srhOrderStatus  != null && this.srhOrderStatus.intValue() != 0)
		   || (this.srhOrganizationCd != null && !this.srhOrganizationCd.isEmpty())
		   || (this.srhOrganizationName != null && !this.srhOrganizationName.isEmpty())
		   || (this.srhTempNoFrom != null  && !this.srhTempNoFrom.isEmpty() )
		   || (this.srhTempNoTo != null  && !this.srhTempNoTo.isEmpty() )
		   || (this.srhSalesTantoCd != null  && !this.srhSalesTantoCd.isEmpty() )
		   || (this.srhSalesTantoName != null  && !this.srhSalesTantoName.isEmpty() )
		   || (this.srhScheduledShippingDateFrom != null  && !this.srhScheduledShippingDateFrom.isEmpty() )
		   || (this.srhScheduledShippingDateTo != null  && !this.srhScheduledShippingDateTo.isEmpty() )
		   || (this.srhSlipPublishComp != null && this.srhSlipPublishComp.intValue() != 0)
		   || (this.srhSuggestedDeliverlimitFrom != null  && !this.srhSuggestedDeliverlimitFrom.isEmpty() )
		   || (this.srhSuggestedDeliverlimitTo != null  && !this.srhSuggestedDeliverlimitTo.isEmpty() )
		   || (this.srhTmpUnitpriceFlg != null   && !this.srhTmpUnitpriceFlg.equals( "9") )
		   || (this.srhVenderCd != null  && !this.srhVenderCd.isEmpty() )
		   || (this.srhVenderGroupCd != null  && !this.srhVenderGroupCd.equals( "0") )
		   || (this.srhVenderName != null && !this.srhVenderName.isEmpty() )
		   || (this.srhOrderInputCheck != null && !this.srhOrderInputCheck.equals( "0") )
		   || (this.srhDelDateSend != null && !this.srhDelDateSend.equals( "0")  )
		   || (this.srhDeleteCancel != null && !this.srhDeleteCancel.equals( "0") )
		 ){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * searchDirtyFlgを取得します。
	 * @return searchDirtyFlg
	 */
	public Boolean getDefaultSearchDirtyFlg() {
		if( 
		   (this.srhAddress != null && !this.srhAddress.isEmpty())
		   || (this.srhCarryBc != null && !this.srhCarryBc.isEmpty())
		   || (this.srhCarryCd != null && !this.srhCarryCd.equals( "0" ) )
		   || (this.srhCtmOrderNo != null && !this.srhCtmOrderNo.isEmpty())
		   || (this.srhDeliveryCd != null && !this.srhDeliveryCd.isEmpty())
		   || (this.srhDeliveryExpectedDateFrom != null && !this.srhDeliveryExpectedDateFrom.isEmpty())
		   || (this.srhDeliveryExpectedDateTo != null && !this.srhDeliveryExpectedDateTo.isEmpty())
		   || (this.srhDeliveryName != null && !this.srhDeliveryName.isEmpty())
		   || (this.srhDeliveryTelNo != null && !this.srhDeliveryTelNo.isEmpty())
		   || (this.srhErrorStatus != null && !this.srhErrorStatus.equals("0"))
		   || (this.srhImportDateFrom != null && !this.srhImportDateFrom.isEmpty())
		   || (this.srhImportDateTo != null && !this.srhImportDateTo.isEmpty())
		   || (this.srhInputDivision != null && this.srhInputDivision.intValue() != 0)
		   || (this.srhInputTantoCd != null && !this.srhInputTantoCd.isEmpty())
		   || (this.srhInputTantoName != null && !this.srhInputTantoName.isEmpty())
		   || (this.srhItemCd != null && !this.srhItemCd.isEmpty())
		   || (this.srhItemName != null && !this.srhItemName.isEmpty())
		   || (this.srhOrderDateFrom != null && !this.srhOrderDateFrom.isEmpty())
		   || (this.srhOrderDateTo != null && !this.srhOrderDateTo.isEmpty())
		   || (this.srhOrderDivision  != null && this.srhOrderDivision.intValue() != 0)
		   || (this.srhFrstOrderNoFrom != null && !this.srhFrstOrderNoFrom.isEmpty())
		   || (this.srhFrstOrderNoTo != null && !this.srhFrstOrderNoTo.isEmpty())
		   || (this.srhOrderNoFrom != null && !this.srhOrderNoFrom.isEmpty())
		   || (this.srhOrderNoTo != null && !this.srhOrderNoTo.isEmpty())
		   || (this.srhOrderStatus  != null && this.srhOrderStatus.intValue() != 0)
		   || (this.srhTempNoFrom != null  && !this.srhTempNoFrom.isEmpty() )
		   || (this.srhTempNoTo != null  && !this.srhTempNoTo.isEmpty() )
		   || (this.srhSalesTantoCd != null  && !this.srhSalesTantoCd.isEmpty() )
		   || (this.srhSalesTantoName != null  && !this.srhSalesTantoName.isEmpty() )
		   || (this.srhScheduledShippingDateFrom != null  && !this.srhScheduledShippingDateFrom.isEmpty() )
		   || (this.srhScheduledShippingDateTo != null  && !this.srhScheduledShippingDateTo.isEmpty() )
		   || (this.srhSlipPublishComp != null && this.srhSlipPublishComp.intValue() != 0)
		   || (this.srhSuggestedDeliverlimitFrom != null  && !this.srhSuggestedDeliverlimitFrom.isEmpty() )
		   || (this.srhSuggestedDeliverlimitTo != null  && !this.srhSuggestedDeliverlimitTo.isEmpty() )
		   || (this.srhTmpUnitpriceFlg != null   && !this.srhTmpUnitpriceFlg.equals( "9") )
		   || (this.srhVenderCd != null  && !this.srhVenderCd.isEmpty() )
		   || (this.srhVenderGroupCd != null  && !this.srhVenderGroupCd.equals( "0") )
		   || (this.srhVenderName != null && !this.srhVenderName.isEmpty() )
		   || (this.srhOrderInputCheck != null && !this.srhOrderInputCheck.equals( "0") )
		   || (this.srhDelDateSend != null && !this.srhDelDateSend.equals( "0")  )
		   || (this.srhDeleteCancel != null && !this.srhDeleteCancel.equals( "0") )
		 ){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * searchDirtyFlgを取得します。
	 * @return searchDirtyFlg
	 */
	public Boolean getNoActionSearchFlg() {
		if( 
		    (this.srhCarryBc != null && !this.srhCarryBc.isEmpty())
		   || (this.srhErrorStatus != null && !this.srhErrorStatus.equals("0"))
		   || (this.srhImportDateFrom != null && !this.srhImportDateFrom.isEmpty())
		   || (this.srhImportDateTo != null && !this.srhImportDateTo.isEmpty())
		   || (this.srhItemCd != null && !this.srhItemCd.isEmpty())
		   || (this.srhItemName != null && !this.srhItemName.isEmpty())		  
		   || (this.srhTempNoFrom != null  && !this.srhTempNoFrom.isEmpty() )
		   || (this.srhTempNoTo != null  && !this.srhTempNoTo.isEmpty() )
		   || (this.srhTmpUnitpriceFlg != null   && !this.srhTmpUnitpriceFlg.equals( "9") )
		   || (this.srhOrderInputCheck != null && !this.srhOrderInputCheck.equals( "0") )
		   || (this.srhDeleteCancel != null && !this.srhDeleteCancel.equals( "0") )
		   || (this.srhInputDivision != null && !(this.srhInputDivision.intValue() == 0))
		 ){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * fileSendOkを取得します。
	 * @return fileSendOk
	 */
	public Boolean getFileSendOk() {
		return fileSendOk;
	}

	/**
	 * fileSendOkを設定します。
	 * @param fileSendOk fileSendOk
	 */
	public void setFileSendOk(Boolean fileSendOk) {
		this.fileSendOk = fileSendOk;
	}
	
	/**
	 * srhOrderInputCheckを取得します。
	 * @return srhOrderInputCheck
	 */
	public String getSrhOrderInputCheck() {
		return srhOrderInputCheck;
	}

	/**
	 * srhOrderInputCheckを設定します。
	 * @param srhOrderInputCheck srhOrderInputCheck
	 */
	public void setSrhOrderInputCheck(String srhOrderInputCheck) {
		this.srhOrderInputCheck = srhOrderInputCheck;
	}

	/**
	 * srhDelDateSendを取得します。
	 * @return srhDelDateSend
	 */
	public String getSrhDelDateSend() {
		return srhDelDateSend;
	}

	/**
	 * srhDelDateSendを設定します。
	 * @param srhDelDateSend srhDelDateSend
	 */
	public void setSrhDelDateSend(String srhDelDateSend) {
		this.srhDelDateSend = srhDelDateSend;
	}

	/**
	 * srhDeleteCancelを取得します。
	 * @return srhDeleteCancel
	 */
	public String getSrhDeleteCancel() {
		return srhDeleteCancel;
	}

	/**
	 * srhDeleteCancelを設定します。
	 * @param srhDeleteCancel srhDeleteCancel
	 */
	public void setSrhDeleteCancel(String srhDeleteCancel) {
		this.srhDeleteCancel = srhDeleteCancel;
	}

	/**
	 * srhTempNoFromを取得します。
	 * @return srhTempNoFrom
	 */
	public String getSrhTempNoFrom() {
		return srhTempNoFrom;
	}

	/**
	 * srhTempNoFromを設定します。
	 * @param srhTempNoFrom srhTempNoFrom
	 */
	public void setSrhTempNoFrom(String srhTempNoFrom) {
		this.srhTempNoFrom = srhTempNoFrom;
	}

	/**
	 * srhTempNoToを取得します。
	 * @return srhTempNoTo
	 */
	public String getSrhTempNoTo() {
		return srhTempNoTo;
	}

	/**
	 * srhTempNoToを設定します。
	 * @param srhTempNoTo srhTempNoTo
	 */
	public void setSrhTempNoTo(String srhTempNoTo) {
		this.srhTempNoTo = srhTempNoTo;
	}

	/**
	 * sendCompFlgを取得します。
	 * @return sendCompFlg
	 */
	public int getSendCompFlg() {
		return sendCompFlg;
	}

	/**
	 * sendCompFlgを設定します。
	 * @param sendCompFlg sendCompFlg
	 */
	public void setSendCompFlg(int sendCompFlg) {
		this.sendCompFlg = sendCompFlg;
	}

}
