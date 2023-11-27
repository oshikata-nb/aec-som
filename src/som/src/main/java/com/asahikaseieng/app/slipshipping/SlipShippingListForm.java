/*
 * Created on 2009/02/27
 *
 * $copyright$
 */
package com.asahikaseieng.app.slipshipping;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingList;
import com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingListPagerCondition;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 出荷帳票検索画面 Formクラス.
 * @author tosco
 * 
 */
public final class SlipShippingListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.slipshipping.list"));
		DATA_ROW = Integer
				.parseInt(rb.getString("threshold.slipshipping.list"));
	}

	//
	// 検索用.出荷帳票
	//

	//
	// インスタンスフィールド.出荷帳票
	//

	/** リスト */
	private List<SlipShippingList> searchList;

	/** 検索入力：上位ロケーション */
	private String srhLocationCd;

	/** 検索入力：上位ロケーション名称 */
	private String srhLocationName;

	/** 検索入力：運送店 */
	private String srhCarryCd;

	/** 検索入力：出荷予定日FROM */
	private String srhScheduledShippingDateFrom;

	/** 検索入力：出荷予定日To */
	private String srhScheduledShippingDateTo;

	/** 検索入力：受注番号FROM */
	private String srhOrderNoFrom;

	/** 検索入力：受注番号TO */
	private String srhOrderNoTo;

	/** 検索入力：出荷番号 */
	private String srhShippingNo;

	/** 検索入力：ステータス */
	private String srhShippingStatus;
	
	/** 検索入力：郵政依頼主 */
	private String shippingPostalClient;

	/** 検索入力：出荷伝票発行済 */
	private Boolean srhSlipPublishComp;

	/** 検索入力：出荷指図書発行済 */
	private Boolean srhSlipShippingOrderComp;

	/** 検索入力：出荷予定表発行済 */
	private Boolean srhSlipShippingScheduleComp;

	/** 検索入力：荷札発行済 */
	private Boolean srhSlipShippingTagComp;

	/** 検索入力：出荷依頼書発行済 */
	private Boolean srhSlipShippingRequestComp;
	
	/** 検索入力：運賃表発行済 */
	private Boolean srhSlipShippingFareComp;

	/** 検索入力：納品伝票発行済 */
	private Boolean srhSlipDeliveryComp;

	/** 検索入力：新荷札発行済 */
	private Boolean srhSlipNewShippingTagComp;

	/** 検索入力：新郵政発行済 */
	private Boolean srhSlipPostalComp;

	/** 検索入力：荷札種別 */
	private String srhLabelPublish;

	/** 検索入力：荷札伝票番号 */
	private String srhShippingSlipNo;

	/** 検索入力：出荷バーコード */
	private String srhCarryBarcode;

	
	/** 運送会社コンボボックス */
	private List<ComboBoxItems> carryCombo;
	
	/** 郵政依頼主コンボボックス */
	private List<ComboBoxItems> postalClientCombo;
	
	/** 郵政依頼主 */
	private String postalClientCd;

	/** 出力対象出荷番号リスト */
	private ArrayList<String> printShippingNoList;
	
	/** エラーメッセージ */
	private String errMsg;

	/** EXCELダウンロードフラグ（AP専用帳票用） */
	private boolean excelDownloadFlg;

	/** EXCELダウンロードフラグ（帳票Excel用） */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel出力用検索条件 */
	private SlipShippingListConditionForReport reportCondition;

	//
	//
	//
	/**
	 * コンストラクタ
	 */
	public SlipShippingListForm() {
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
		return SlipShippingListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		} else if ("search".equals(getOp())) {
			clearCheck();
		}
		// 帳票Excel出力用フラグOFF
		setExcelReportDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<SlipShippingList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化
	 */
	public void clear() {
		/** 検索リストのクリア */
		setSearchList(new ArrayList<SlipShippingList>());

		// 上位ロケーション
		setSrhLocationCd(null);
		setSrhLocationName(null);
		// 運送店
		setSrhCarryCd(null);
		// 出荷予定日FROM
		setSrhScheduledShippingDateFrom(null);
		// 出荷予定日To
		setSrhScheduledShippingDateTo(null);
		// 受注番号FROM
		setSrhOrderNoFrom(null);
		// 受注番号TO
		setSrhOrderNoTo(null);
		// 出荷番号
		setSrhShippingNo(null);
		// ステータス
		setSrhShippingStatus(null);
		// 荷札種別
		setSrhLabelPublish(null);
		// 荷札伝票番号
		setSrhShippingSlipNo(null);
		// 出荷バーコード
		setSrhCarryBarcode(null);

		setSrhSlipPublishComp(Boolean.FALSE); // 出荷伝票発行済
		setSrhSlipShippingOrderComp(Boolean.FALSE); // 出荷指図書発行済
		setSrhSlipShippingScheduleComp(Boolean.FALSE); // 出荷予定表発行済
		setSrhSlipShippingTagComp(Boolean.FALSE); // 荷札発行済
		setSrhSlipShippingRequestComp(Boolean.FALSE); // 出荷依頼書発行済
		setSrhSlipShippingFareComp(Boolean.FALSE); // 運賃表発行済
		setSrhSlipDeliveryComp(Boolean.FALSE); // 納品伝票発行済
		setSrhSlipNewShippingTagComp(Boolean.FALSE); // 新荷札発行済
		setSrhSlipPostalComp(Boolean.FALSE); // 新郵政発行済
		
		setPostalClientCd("0"); // 郵政依頼主

		// 出力対象出荷番号リスト
		setPrintShippingNoList(null);

		// Excelダウンロードフラグ
		setExcelDownloadFlg(false);

		// エラーメッセージ
		setErrMsg(null);

		// 帳票Excel用検索条件
		setReportCondition(null);

		// 帳票Excel出力用フラグOFF
		setExcelReportDownloadFlg(false);
	}

	//
	//
	//
	/**
	 * 検索結果件数を取得
	 * @return int 件数
	 */
	public int getSearchListLength() {
		return searchList.size();
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<SlipShippingList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<SlipShippingList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力
	//

	/**
	 * 検索入力.受注番号FROM取得
	 * @return srhOrderNoFrom
	 */
	public String getSrhOrderNoFrom() {
		return this.srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号FROM設定 *
	 * @param srhOrderNoFrom organizationId
	 */
	public void setSrhOrderNoFrom(final String srhOrderNoFrom) {
		this.srhOrderNoFrom = srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号TO取得
	 * @return srhOrderNoTo
	 */
	public String getSrhOrderNoTo() {
		return this.srhOrderNoTo;
	}

	/**
	 * 検索入力.受注番号TO設定 *
	 * @param srhOrderNoTo organizationId
	 */
	public void setSrhOrderNoTo(final String srhOrderNoTo) {
		this.srhOrderNoTo = srhOrderNoTo;
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
	 * srhLabelPublishを取得します。
	 * @return srhLabelPublish
	 */
	public String getSrhLabelPublish() {
		return srhLabelPublish;
	}

	/**
	 * srhLabelPublishを設定します。
	 * @param srhLabelPublish srhLabelPublish
	 */
	public void setSrhLabelPublish(final String srhLabelPublish) {
		this.srhLabelPublish = srhLabelPublish;
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
	public void setSrhScheduledShippingDateFrom(
			final String srhScheduledShippingDateFrom) {
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
	public void setSrhScheduledShippingDateTo(
			final String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * srhShippingNoを取得します。
	 * @return srhShippingNo
	 */
	public String getSrhShippingNo() {
		return srhShippingNo;
	}

	/**
	 * srhShippingNoを設定します。
	 * @param srhShippingNo srhShippingNo
	 */
	public void setSrhShippingNo(final String srhShippingNo) {
		this.srhShippingNo = srhShippingNo;
	}

	/**
	 * srhShippingSlipNoを取得します。
	 * @return srhShippingSlipNo
	 */
	public String getSrhShippingSlipNo() {
		return srhShippingSlipNo;
	}

	/**
	 * srhShippingSlipNoを設定します。
	 * @param srhShippingSlipNo srhShippingSlipNo
	 */
	public void setSrhShippingSlipNo(final String srhShippingSlipNo) {
		this.srhShippingSlipNo = srhShippingSlipNo;
	}

	/**
	 * srhShippingStatusを取得します。
	 * @return srhShippingStatus
	 */
	public String getSrhShippingStatus() {
		return srhShippingStatus;
	}

	/**
	 * srhShippingStatusを設定します。
	 * @param srhShippingStatus srhShippingStatus
	 */
	public void setSrhShippingStatus(final String srhShippingStatus) {
		this.srhShippingStatus = srhShippingStatus;
	}
	
	/**
	 * shippingPostalClientを取得します。
	 * @return shippingPostalClient
	 */
	public String getShippingPostalClient() {
		return shippingPostalClient;
	}

	/**
	 * shippingPostalClientを設定します。
	 * @param shippingPostalClient shippingPostalClient
	 */
	public void setShippingPostalClient(String shippingPostalClient) {
		this.shippingPostalClient = shippingPostalClient;
	}

	/**
	 * postalClientCdを取得します。
	 * @return postalClientCd
	 */
	public String getPostalClientCd() {
		return postalClientCd;
	}

	/**
	 * postalClientCdを設定します。
	 * @param postalClientCd postalClientCd
	 */
	public void setPostalClientCd(String postalClientCd) {
		this.postalClientCd = postalClientCd;
	}

	/**
	 * srhSlipPublishCompを取得します。
	 * @return srhSlipPublishComp
	 */
	public Boolean getSrhSlipPublishComp() {
		return srhSlipPublishComp;
	}

	/**
	 * srhSlipPublishCompを設定します。
	 * @param srhSlipPublishComp srhSlipPublishComp
	 */
	public void setSrhSlipPublishComp(final Boolean srhSlipPublishComp) {
		this.srhSlipPublishComp = srhSlipPublishComp;
	}

	/**
	 * srhSlipShippingOrderCompを取得します。
	 * @return srhSlipShippingOrderComp
	 */
	public Boolean getSrhSlipShippingOrderComp() {
		return srhSlipShippingOrderComp;
	}

	/**
	 * srhSlipShippingOrderCompを設定します。
	 * @param srhSlipShippingOrderComp srhSlipShippingOrderComp
	 */
	public void setSrhSlipShippingOrderComp(
			final Boolean srhSlipShippingOrderComp) {
		this.srhSlipShippingOrderComp = srhSlipShippingOrderComp;
	}

	/**
	 * srhSlipShippingRequestCompを取得します。
	 * @return srhSlipShippingRequestComp
	 */
	public Boolean getSrhSlipShippingRequestComp() {
		return srhSlipShippingRequestComp;
	}

	/**
	 * srhSlipShippingRequestCompを設定します。
	 * @param srhSlipShippingRequestComp srhSlipShippingRequestComp
	 */
	public void setSrhSlipShippingRequestComp(
			final Boolean srhSlipShippingRequestComp) {
		this.srhSlipShippingRequestComp = srhSlipShippingRequestComp;
	}
	
	/**
	 * srhFareCompを取得します。
	 * @return srhFareComp
	 */
	public Boolean getSrhSlipShippingFareComp() {
		return srhSlipShippingFareComp;
	}

	/**
	 * srhFareCompを設定します。
	 * @param srhFareComp srhFareComp
	 */
	public void setSrhSlipShippingFareComp(Boolean srhSlipShippingFareComp) {
		this.srhSlipShippingFareComp = srhSlipShippingFareComp;
	}

	/**
	 * srhSlipShippingScheduleCompを取得します。
	 * @return srhSlipShippingScheduleComp
	 */
	public Boolean getSrhSlipShippingScheduleComp() {
		return srhSlipShippingScheduleComp;
	}

	/**
	 * srhSlipShippingScheduleCompを設定します。
	 * @param srhSlipShippingScheduleComp srhSlipShippingScheduleComp
	 */
	public void setSrhSlipShippingScheduleComp(
			final Boolean srhSlipShippingScheduleComp) {
		this.srhSlipShippingScheduleComp = srhSlipShippingScheduleComp;
	}

	/**
	 * srhSlipShippingTagCompを取得します。
	 * @return srhSlipShippingTagComp
	 */
	public Boolean getSrhSlipShippingTagComp() {
		return srhSlipShippingTagComp;
	}

	/**
	 * srhSlipShippingTagCompを設定します。
	 * @param srhSlipShippingTagComp srhSlipShippingTagComp
	 */
	public void setSrhSlipShippingTagComp(final Boolean srhSlipShippingTagComp) {
		this.srhSlipShippingTagComp = srhSlipShippingTagComp;
	}

	/**
	 * srhSlipDeliveryCompを取得します。
	 * @return srhSlipDeliveryComp
	 */
	public Boolean getSrhSlipDeliveryComp() {
		return srhSlipDeliveryComp;
	}

	/**
	 * srhSlipDeliveryCompを設定します。
	 * @param srhSlipDeliveryComp srhSlipDeliveryComp
	 */
	public void setSrhSlipDeliveryComp(Boolean srhSlipDeliveryComp) {
		this.srhSlipDeliveryComp = srhSlipDeliveryComp;
	}

	/**
	 * srhSlipNewShippingTagCompを取得します。
	 * @return srhSlipNewShippingTagComp
	 */
	public Boolean getSrhSlipNewShippingTagComp() {
		return srhSlipNewShippingTagComp;
	}

	/**
	 * srhSlipNewShippingTagCompを設定します。
	 * @param srhSlipNewShippingTagComp srhSlipNewShippingTagComp
	 */
	public void setSrhSlipNewShippingTagComp(Boolean srhSlipNewShippingTagComp) {
		this.srhSlipNewShippingTagComp = srhSlipNewShippingTagComp;
	}

	/**
	 * srhSlipPostalCompを取得します。
	 * @return srhSlipPostalComp
	 */
	public Boolean getSrhSlipPostalComp() {
		return srhSlipPostalComp;
	}

	/**
	 * srhSlipPostalCompを設定します。
	 * @param srhSlipPostalComp srhSlipPostalComp
	 */
	public void setSrhSlipPostalComp(Boolean srhSlipPostalComp) {
		this.srhSlipPostalComp = srhSlipPostalComp;
	}

	/**
	 * srhCarryBarcodeを取得します。
	 * @return srhCarryBarcode
	 */
	public String getSrhCarryBarcode() {
		return srhCarryBarcode;
	}

	/**
	 * srhCarryBarcodeを設定します。
	 * @param srhCarryBarcode srhCarryBarcode
	 */
	public void setSrhCarryBarcode(final String srhCarryBarcode) {
		this.srhCarryBarcode = srhCarryBarcode;
	}

	
	/**
	 * 変更フラグを取得します。
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		setSrhSlipPublishComp(Boolean.FALSE);
		setSrhSlipShippingOrderComp(Boolean.FALSE);
		setSrhSlipShippingScheduleComp(Boolean.FALSE);
		setSrhSlipShippingTagComp(Boolean.FALSE);
		setSrhSlipShippingFareComp(Boolean.FALSE);
		setSrhSlipShippingRequestComp(Boolean.FALSE);
		setSrhSlipDeliveryComp(Boolean.FALSE);
		setSrhSlipNewShippingTagComp(Boolean.FALSE);
		setSrhSlipPostalComp(Boolean.FALSE);
	}

	/**
	 * 上位ロケーション名称を取得します。
	 * @return srhLocationName
	 */
	public String getSrhLocationName() {
		return srhLocationName;
	}

	/**
	 * 上位ロケーション名称を設定します。
	 * @param srhLocationName 上位ロケーション名称
	 */
	public void setSrhLocationName(final String srhLocationName) {
		this.srhLocationName = srhLocationName;
	}

	/**
	 * 運送会社コンボボックスを取得します。
	 * @return carryCombo
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
	 * postalClientComboを取得します。
	 * @return postalClientCombo
	 */
	public List<ComboBoxItems> getPostalClientCombo() {
		return postalClientCombo;
	}

	/**
	 * postalClientComboを設定します。
	 * @param postalClientCombo postalClientCombo
	 */
	public void setPostalClientCombo(List<ComboBoxItems> postalClientCombo) {
		this.postalClientCombo = postalClientCombo;
	}

	/**
	 * 出力対象出荷番号リストを取得します。
	 * @return printDirectionNoList
	 */
	public ArrayList<String> getPrintShippingNoList() {
		return printShippingNoList;
	}

	/**
	 * 出力対象出荷番号リストを設定します。
	 * 
	 * @param printShippingNoList 出力対象出荷番号リスト
	 */
	public void setPrintShippingNoList(
			final ArrayList<String> printShippingNoList) {
		this.printShippingNoList = printShippingNoList;
	}

	/**
	 * エラーメッセージを取得します。
	 * @return エラーメッセージ
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * エラーメッセージを設定します。
	 * @param errMsg エラーメッセージ
	 */
	public void setErrMsg(final String errMsg) {
		this.errMsg = errMsg;
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
	 * reportConditionを取得します。
	 * @return reportCondition
	 */
	public SlipShippingListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final SlipShippingListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * excelReportDownloadFlgを取得します。
	 * @return excelReportDownloadFlg
	 */
	public boolean isExcelReportDownloadFlg() {
		return excelReportDownloadFlg;
	}

	/**
	 * excelReportDownloadFlgを設定します。
	 * @param excelReportDownloadFlg excelReportDownloadFlg
	 */
	public void setExcelReportDownloadFlg(final boolean excelReportDownloadFlg) {
		this.excelReportDownloadFlg = excelReportDownloadFlg;
	}

	
}
