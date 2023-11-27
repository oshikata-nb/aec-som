/*
 * Created on 2020/11/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetail;
import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetailDao;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHeadDao;
import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailList;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailListDao;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportList;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipConditionForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipDetailForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipHeaderForReport;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkListDao;
import com.asahikaseieng.dao.nonentity.repdeliverydatecontactdetail.RepDeliveryDateContactDetail;
import com.asahikaseieng.dao.nonentity.repdeliverydatecontactdetail.RepDeliveryDateContactDetailDao;
import com.asahikaseieng.dao.nonentity.repdeliverydatecontactheader.RepDeliveryDateContactHeader;
import com.asahikaseieng.dao.nonentity.repdeliverydatecontactheader.RepDeliveryDateContactHeaderDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * ExcelDecorator実装クラス.受注取込一覧
 * @author
 */
public class OrderImportListExcelDecoratorImpl implements OrderImportListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_ORDER_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_ORDER_START_COL = 0;

	/** 得意先（次店)を取得する場合 */
	protected static final String GET_VENDER_SHOP = "SHOP";

	/** 得意先コードを取得する場合 */
	protected static final String GET_VENDER_CD = "CODE";

	/** 得意先名称を取得する場合 */
	protected static final String GET_VENDER_NAME = "NAME";

	/** ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";


	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private OrderImportListLogic orderImportListLogic;

	private RepDeliveryDateContactHeaderDao repDeliveryDateContactHeaderDao;

	private RepDeliveryDateContactDetailDao repDeliveryDateContactDetailDao;

	private FrstOrderDetailDao frstOrderDetailDao;


	private BalanceDao balanceDao;

	private VenderDao venderDao;

	private FrstOrderHeadDao frstOrderHeadDao;

	private OrderDetailListDao orderDetailListDao;

	private OrderRemarkListDao orderRemarkListDao;

	private OrderImportCommonLogic orderImportCommonLogic;


	/** 納品書備考長さ */
	protected static final short REMARK_LENGTH = 42;

	/**
	 * コンストラクタ
	 */
	public OrderImportListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final OrderImportListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("orderimportlist");

		// ヘッダデータをセット
		setHeader(condition);

		// 詳細データをセット
		setDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder.create());
	}
	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReportPdf(
			final OrderImportListConditionForReport condition, BigDecimal seq) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("orderimportlist");

		// ヘッダデータをセット
		setHeader(condition);

		// 詳細データをセット
		setDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder.createPdfExcel(seq));
	}

	/**
	 * ヘッダデータをセット
	 * @param condition 検索条件
	 */
	private void setHeader(final OrderImportListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<OrderImportListForReport> headerList = orderImportListLogic.getHeaderListForReport(condition);

		/* シートをセット */
		builder.setSheet("order_import_head");

		/* リスト部 */
		for (OrderImportListForReport bean : headerList) {
			colNum = TEMP_START_COL;
			builder.setExcelDataString(rowNum, colNum++, bean.getFrstOrderNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderDivisionName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getOrderDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputTantoName());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesTantoName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderGroupCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderGroupName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderShortedName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName2());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSearchKana());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryName2());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryAddress());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress3());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getBalanceCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStatus());
			builder.setExcelDataString(rowNum, colNum++, bean.getCustomerOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderAmount());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getSuggestedDeliverlimit());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getScheduledShippingDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getLeadTime());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getDeliveryExpectedDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryExpectedTime());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCarryFare());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCarryInvoiceFlag());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderPicture());
			builder.setExcelDataString(rowNum, colNum++, bean.getPrintSummery());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliverySlipSummery());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderSummery());
			// 20210906 Asclab Saita 納期連絡表専用備考追加対応
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliverydateContactSummery());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getTotalWeight());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getDelFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCancelFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getInvisibleFlg());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());


			rowNum++;
		}
	}

	/**
	 * 詳細データをセット
	 * @param condition 検索条件
	 */
	private void setDetail(final OrderImportListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<OrderImportListForReport> detailList = orderImportListLogic.getDetailListForReport(condition);

		/* シートをセット */
		builder.setSheet("order_import_detail");

		/* リスト部 */
		for (OrderImportListForReport bean : detailList) {
			colNum = TEMP_START_COL;
			builder.setExcelDataString(rowNum, colNum++, bean.getFrstOrderNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getFrstOrderRowNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderImpNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRowNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getShippingNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean.getImportStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean.getErrorStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean.getStyleOfPacking());
			builder.setExcelDataString(rowNum, colNum++, bean.getOtherCompanyCd1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getMatss());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getWeight());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStandardUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStandardDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSpecialDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getTmpUnitpriceFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getEstimateStandardAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getEstimateMatss());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getInputDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getCustomerOrderDetailNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getInputApprovalDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputApproverCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputApproverName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getDelDateSendDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getDelDateSenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getDelDateSenderName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getDeleteDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getDelFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCancelQty());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getCancelDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCancelFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getErrorFlg());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderImpNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderGroupCd());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getImportDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getTempNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCellRowNumber());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCellColNumber());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmOrderRow());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getCtmOrderDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getCtmDeliverlimit());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmVenderCd01());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmVenderCd02());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmVenderCd03());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmVenderName01());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmVenderName02());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmVenderName03());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryCd01());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryCd02());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryCd03());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryName01());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryName02());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryName03());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryAddress01());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryAddress02());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryAddress03());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryZipCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryAddress());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmItemCd01());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmItemCd02());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmItemCd03());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmJanCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmItemName01());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmItemName02());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmItemName03());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmStyleOfPacking());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmOrderQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmCaseNum());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmMatss());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmOrderPiece());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmRemark01());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmRemark02());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmRemark03());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmOrderUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmOrderAmount());


			rowNum++;
		}
	}


	/**
	 * 受注納期連絡表の作成
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createDeliveryDateContact(final List<OrderImportList> checkedList , final String tantoCd , List<String> checkedFrstOrderNoList ) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("deliverydate_contact");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		List<String> frstOrderNoList = setHeaderContact(checkedList);

		// キャンセル等でヘッダ情報の取得に失敗した場合、nullを返す。
		if( frstOrderNoList.size() == 0 ){
			return null;
		}

		for( String frstOrderNo : frstOrderNoList ){
			checkedFrstOrderNoList.add(frstOrderNo);
		}

		setDetailContact(checkedList);

		//設定シートのマクロ実行フラグを1:実行する に変更
		builder.setSheet("setting");
		builder.setExcelDataBigDecimal((short)1, (short)0, BigDecimal.ONE);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder.create());
	}
	
	/** 20220530 S.Fujimaki Add
	 * 受注納期連絡表の作成　PDF専用
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createDeliveryDateContact(final List<OrderImportList> checkedList , final String tantoCd , List<String> checkedFrstOrderNoList,BigDecimal seq) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("deliverydate_contact");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		List<String> frstOrderNoList = setHeaderContact(checkedList);

		// キャンセル等でヘッダ情報の取得に失敗した場合、nullを返す。
		if( frstOrderNoList.size() == 0 ){
			return null;
		}

		for( String frstOrderNo : frstOrderNoList ){
			checkedFrstOrderNoList.add(frstOrderNo);
		}

		setDetailContact(checkedList);

		//設定シートのマクロ実行フラグを1:実行する に変更
		builder.setSheet("setting");
		builder.setExcelDataBigDecimal((short)1, (short)0, BigDecimal.ONE);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder.createPdfExcel(seq));
	}

	/**
	 * 納期連絡済み更新
	 * @param tantoCd
	 * @param checkedFrstOrderNoList
	 */
	public void updateDelDateSender(final String tantoCd,
			List<String> checkedFrstOrderNoList) {

		// 入力済み履歴登録
		for( String frstOrderNo : checkedFrstOrderNoList ){
			List<FrstOrderDetail> updateBeanList = frstOrderDetailDao.getList(frstOrderNo);

			for( FrstOrderDetail updateBean : updateBeanList ){

				if( AecStringUtils.isNotNullAndEmpty(updateBean.getDelDateSenderCd() )){
					continue;
				}

				updateBean.setDelDateSenderCd(tantoCd);
				updateBean.setDelDateSendDate(AecDateUtils.getCurrentTimestamp());
				try {
					// 更新の場合、
					frstOrderDetailDao.update(updateBean);
				} catch (NotSingleRowUpdatedRuntimeException e) {
					// 排他エラー
					e.printStackTrace();
					// 登録終了後の画面の遷移に影響するため、登録フラグを元に戻す
					throw new OptimisticLockRuntimeException();
				} catch (SQLRuntimeException e) {
					e.printStackTrace();
					// 登録終了後の画面の遷移に影響するため、登録フラグを元に戻す
					throw new DuplicateRuntimeException(0);
				}

			}

		}

		// 入力結果のログ登録
		this.orderImportCommonLogic.inserFrstOrdertLog(checkedFrstOrderNoList , tantoCd , "20");
	}




	/**
	 * 納期連絡表ヘッダデータをセット
	 * @param checkedList OrderImportList
	 */
	private List<String> setHeaderContact(List<OrderImportList> checkedList){

		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		String befOrderNo = null;

		builder.setSheet("header_data_contact");

		List<String> outputFrstOrderNoList = new ArrayList<String>();

		for(OrderImportList checkedBean : checkedList){

			// 前回取得した内容と、受注番号が同じならヘッダ情報を取得しない
			if( checkedBean.getOrderNo() != null && befOrderNo != null && befOrderNo.equals(checkedBean.getOrderNo()) ){
				continue;
			}

			befOrderNo = checkedBean.getOrderNo();

			colNum = TEMP_START_COL;

			RepDeliveryDateContactHeader bean = repDeliveryDateContactHeaderDao.getEntity(checkedBean.getFrstOrderNo(), checkedBean.getOrderNo());

			// 出力対象でない場合、次
			if( bean == null ){
				continue;
			}

			outputFrstOrderNoList.add(checkedBean.getFrstOrderNo());

			builder.setExcelDataString(rowNum, colNum++, bean.getKey());
			builder.setExcelDataString(rowNum, colNum++, bean.getPkNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPkRow());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++,  bean.getVenderName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderFullAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderFullAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmVenderName());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryName());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryFullAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryFullAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryAddress());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryName());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryFullAddress());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmDeliveryZipCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCtmOrderNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getCtmOrderDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryExpectedDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesTantoName());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCarryFare());
			builder.setExcelDataString(rowNum, colNum++, bean.getPrintSummery());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliverySlipSummery());
			builder.setExcelDataString(rowNum, colNum++, bean.getScheduledShippingDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getHeaderRemark());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrgZipCode());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrgAddress());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrgTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrgFaxNo());

			// 20210908 Asclab Saita 納期連絡表改修対応
			builder.setExcelDataString(rowNum, colNum++,bean.getDeliverydateContactSummery());
			List<String> venderCdList = new ArrayList<String>();
			int index = 0;
			boolean isExist = false;
			// 比較するコードをセット
			venderCdList.add(bean.getVenderCd() + "");
			venderCdList.add(bean.getSecVenderCd() + "");
			venderCdList.add(bean.getThiVenderCd() + "");
			venderCdList.add(bean.getFouVenderCd() + "");
			venderCdList.add(bean.getFifVenderCd() + "");
			// 次店への表示データを判定
			// ２次店と３次店なので２週ループ
			for (int i = 0; i < 2; i++) {
				/*
				 *  ベンダーコードの数だけループする
				 *  開始は0からスタート、2週目以降は前回の終了地点からスタート
				 */
				for (int j = index; j < venderCdList.size() - 1; j++) {
					if (!venderCdList.get(j).equals(venderCdList.get(j + 1))) {
						// 異なる次店がある場合は、その位置を記録し次回スタートとする
						index = j + 1;
						isExist = true;
						break;
					}
				}
				if (!isExist) {
					// 表示対象がないのでindex = 99にして空文字を書き込む
					builder.setExcelDataString(rowNum, colNum++,"");
					builder.setExcelDataString(rowNum, colNum++,"");
					builder.setExcelDataString(rowNum, colNum++,"");
					builder.setExcelDataString(rowNum, colNum++,"");
					builder.setExcelDataString(rowNum, colNum++,"");
					index = 99;
				} else {
					// 上位コードと不一致になった次店をセットする
					switch (index) {
						case 1:
							builder.setExcelDataString(rowNum, colNum++,bean.getSecVenderCd());
							builder.setExcelDataString(rowNum, colNum++,bean.getSecVenderName());
							builder.setExcelDataString(rowNum, colNum++,bean.getSecVenderAddress());
							builder.setExcelDataString(rowNum, colNum++,bean.getSecVenderTelNo());
							builder.setExcelDataString(rowNum, colNum++,bean.getSecVenderFaxNo());
							break;
						case 2:
							builder.setExcelDataString(rowNum, colNum++,bean.getThiVenderCd());
							builder.setExcelDataString(rowNum, colNum++,bean.getThiVenderName());
							builder.setExcelDataString(rowNum, colNum++,bean.getThiVenderAddress());
							builder.setExcelDataString(rowNum, colNum++,bean.getThiVenderTelNo());
							builder.setExcelDataString(rowNum, colNum++,bean.getThiVenderFaxNo());
							break;
						case 3:
							builder.setExcelDataString(rowNum, colNum++,bean.getFouVenderCd());
							builder.setExcelDataString(rowNum, colNum++,bean.getFouVenderName());
							builder.setExcelDataString(rowNum, colNum++,bean.getFouVenderAddress());
							builder.setExcelDataString(rowNum, colNum++,bean.getFouVenderTelNo());
							builder.setExcelDataString(rowNum, colNum++,bean.getFouVenderFaxNo());
							break;
						case 4:
							builder.setExcelDataString(rowNum, colNum++,bean.getFifVenderCd());
							builder.setExcelDataString(rowNum, colNum++,bean.getFifVenderName());
							builder.setExcelDataString(rowNum, colNum++,bean.getFifVenderAddress());
							builder.setExcelDataString(rowNum, colNum++,bean.getFifVenderTelNo());
							builder.setExcelDataString(rowNum, colNum++,bean.getFifVenderFaxNo());
							break;
					}
				}
				isExist = false;
			}
			rowNum++;
		}

		return outputFrstOrderNoList;
	}

	/**
	 * 納期連絡表詳細データをセット
	 * @param checkedList OrderImportList
	 */
	private void setDetailContact(List<OrderImportList> checkedList){

		short rowNum = TEMP_START_ROW;

		builder.setSheet("detail_data_contact");

		for(OrderImportList checkedBean : checkedList){
			List<RepDeliveryDateContactDetail> beanList = repDeliveryDateContactDetailDao.getEntity(checkedBean.getFrstOrderNo());

			// 出力対象でない場合、次
			if( beanList == null ){
				continue;
			}

			for( RepDeliveryDateContactDetail bean : beanList ){
				short colNum = TEMP_START_COL;

				builder.setExcelDataString(rowNum, colNum++, bean.getKey());
				builder.setExcelDataString(rowNum, colNum++, bean.getPkNo());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPkRow());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSeq());
				builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
				// 20210908 Asclab Saita 納期連絡表改修対応
				// builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
				builder.setExcelDataString(rowNum, colNum++, bean.getItemCd() + " " + bean.getItemName());
				builder.setExcelDataString(rowNum, colNum++, bean.getStyleOfPacking());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getAllUpWeight());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderQty());
				builder.setExcelDataString(rowNum, colNum++, bean.getUnitOfOperationManagement());
				builder.setExcelDataString(rowNum, colNum++, bean.getUoomName());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderAmount());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderUnitprice());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStandardUnitprice());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStandardDiscount());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSpecialDiscount());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getMatss());
				builder.setExcelDataString(rowNum, colNum++, bean.getQty());
				builder.setExcelDataString(rowNum, colNum++, bean.getCtmItemCd());
				builder.setExcelDataString(rowNum, colNum++, bean.getCtmItemName());
				builder.setExcelDataString(rowNum, colNum++, bean.getCtmStyleOfPacking());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmOrderQty());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmCaseNum());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmOrderPiece());
				builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCtmOrderUnitprice());

				rowNum++;
			}

		}
	}



	/**
	 * 受注一覧票情報を格納したFileDownloadInfoを作成
	 * @param condition
	 * @return FileDownloadInfo
	 */
	public FileDownloadInfo createOrderReport(final RepOrderImportSlipConditionForReport condition) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_order_new");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// ヘッダデータをセット
		setOrderHeader(condition);

		// 詳細データをセット
		setOrderDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder.create());
	}

	/**
	 * 受注帳票ヘッダーデータをセット
	 * @param condition 検索条件
	 */
	private void setOrderHeader(final RepOrderImportSlipConditionForReport condition) {
		short rowNum = TEMP_ORDER_START_ROW;
		short colNum = TEMP_ORDER_START_COL;

		List<RepOrderImportSlipHeaderForReport> list = orderImportListLogic.getSlipHeaderListForReport(condition);

		if( list == null ){
			return;
		}

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		List<String> keyList = new ArrayList<String>();


		/* リスト部 */
		for (RepOrderImportSlipHeaderForReport bean : list) {

			if( keyList.contains(bean.getPkNo()) ){
				continue;
			}
			keyList.add(bean.getPkNo());

			colNum = TEMP_START_COL;
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getPkNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPkRow());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getDivisionName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getOrderDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputTantoName());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesTantoName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryName());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryAddress());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryAddressAll());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getBalanceCd());
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 1, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 1, GET_VENDER_CD));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 1, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 2, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 2, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 2, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 3, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 3, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 3, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 4, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 4, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 4, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 5, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 5, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 5, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 6, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 6, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 6, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 7, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 7, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 7, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 8, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 8, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 8, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 9, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 9, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 9, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 10, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 10, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(bean.getBalanceCd(), 10, GET_VENDER_NAME));

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStatus());
			builder.setExcelDataString(rowNum, colNum++, bean.getCustomerOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderAmount());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getSuggestedDeliverlimit());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getScheduledShippingDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getLeadTime());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getDeliveryExpectedDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryExpectedTime());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCarryFare());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCarryInvoiceFlag());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryInvoiceString());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderPicture());
			builder.setExcelDataString(rowNum, colNum++, bean.getPrintSummery());
			builder.setExcelDataString(rowNum, colNum++, AecStringUtils.split(bean.getDeliverySlipSummery(), REMARK_LENGTH));
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderSummery());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSlipPublishComp());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getSlipPublishDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());

			List<OrderRemarkList> remarkList = null;
//			HSSFRichTextString rtext = new HSSFRichTextString("CRLF");
			try {
				// 備考の検索
				remarkList = getRemarkList(bean.getPkNo());
				// *******************************
				// 取得した備考をセット
				// *******************************
				StringBuffer sbfRemark = new StringBuffer();
				String remark = new String();
				// 取得した備考を全てセット、区切りとして改行を追加
				for (int i = 0; i < remarkList.size(); i++) {

					if (remarkList.get(i).getRemark15() != null) {
						if (sbfRemark.length() != 0) {
							sbfRemark.append(System
									.getProperty("line.separator"));
							remark += "\n";
						}
						remark += remarkList.get(i).getRemark15() + "\n";
					}
				}
				builder.setExcelDataString(rowNum, colNum++, remark);

			} catch (NoDataException ex) {
				builder.setExcelDataString(rowNum, colNum++, "");
			}

			builder.setExcelDataString(rowNum, colNum++, bean.getOrgZipCode());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrgAddress());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrgTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrgFaxNo());

			rowNum++;
		}
	}

	/**
	 * 受注帳票詳細データをセット
	 * @param condition 検索条件
	 */
	private void setOrderDetail(final RepOrderImportSlipConditionForReport condition) {
		short rowNum = TEMP_ORDER_START_ROW;
		short colNum = TEMP_ORDER_START_COL;

		List<RepOrderImportSlipDetailForReport> detailList = orderImportListLogic.getSlipDetailListForReport(condition);

		if(detailList == null){
			return;
		}

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		/* リスト部 */
		for (RepOrderImportSlipDetailForReport bean : detailList) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRowNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getPkNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPkRow());
			builder.setExcelDataString(rowNum, colNum++, bean.getShippingNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean.getStyleOfPacking());
			builder.setExcelDataString(rowNum, colNum++, bean.getOtherCompanyCd1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean		.getOrderQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStandardUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStandardDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSpecialDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderAmount());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getTmpUnitpriceFlg());

			builder.setExcelDataString(rowNum, colNum++, bean.getTmpUnitpriceString());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getMatss());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getEstimateStandardAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getEstimateMatss());
			builder.setExcelDataString(rowNum, colNum++, bean.getStatusName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean		.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			rowNum++;
		}
	}

	/**
	 * 帳合コードから指定次店の得意先コードまたは得意先名称を返す
	 */
	private String getVenderFromBalance(final String balanceCd,
			final int level, final String type) {

		Balance bean = balanceDao.getEntity(balanceCd);

		// 帳合がマスタに無い場合
		if (bean == null) {
			return null;
		}

		// 帳合マスタの次店より指定次店のほうが大きい場合Nullをかえす
		if (bean.getShopLevel().compareTo(new BigDecimal(level)) < 0) {
			return null;
		}
		while (true) {

			// 帳合マスタの次点と引数の次点が等しい場合
			if (bean.getShopLevel().compareTo(new BigDecimal(level)) == 0) {
				return getVenderData(bean.getVenderCd(), level, type);
			}

			// 上位帳合コードが無い場合（これ以上上位にさかのぼれない場合
			if (bean.getUpperBalanceCd().equals("")
					|| bean.getUpperBalanceCd() == null) {
				return null;
			} else { // 上位帳合コードが存在する場合上位のさかのぼる
				bean = balanceDao.getEntity(bean.getUpperBalanceCd());

				// 上位帳合コードがマスタに無い場合Ｎｕｌｌを返す
				if (bean == null) {
					return null;
				}

			}

		}

	}

	/**
	 * 得意先コードから得意先コードまたは得意先名称を返す
	 */
	private String getVenderData(final String venderCd, final int level,
			final String type) {

		Vender bean = venderDao.getEntity(venderCd, "TS");

		// 得意先コードが取引先マスタに存在する場合
		if (bean != null) {
			if (GET_VENDER_CD.equals(type)) { // コードを返す場合
				return bean.getVenderCd();
			} else if (GET_VENDER_NAME.equals(type)) { // 名称を返す場合
				return bean.getVenderShortedName();
			} else if (GET_VENDER_SHOP.equals(type)) { // 次店を返す場合
				switch (level) {
				case 1:
					return "得意先";
				case 2:
					return "二次店";
				case 3:
					return "三次店";
				case 4:
					return "四次店";
				case 5:
					return "五次店";
				case 6:
					return "六次店";
				case 7:
					return "七次店";
				case 8:
					return "八次店";
				case 9:
					return "九次店";
				case 10:
					return "十次店";
				default:
					return null;
				}
			} else { // それ以外の場合 null
				return null;
			}

		} else { // 得意先コードがマスタに無い場合
			if (GET_VENDER_CD.equals(type)) {
				return venderCd;
			} else {
				return null;
			}
		}

	}

	/**
	 * 備考マスタ検索処理
	 *
	 * @param frm OrderDetailForm
	 * @return List<OrderRemarkList>備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	private List<OrderRemarkList> getRemarkList(final String orderNo)
			throws NoDataException {

		String venderCd = null;
		String deliveryCd = null;
		List<OrderDetailList> itemList = orderDetailListDao.getDetailList(orderNo);

		FrstOrderHead head = frstOrderHeadDao.getEntity(orderNo);

		venderCd = head.getVenderCd();
		deliveryCd = head.getDeliveryCd();

		// 得意先と納入先に紐付く備考を検索。
		List<OrderRemarkList> remarkList = orderRemarkListDao.getRemarkListNoItem(venderCd, deliveryCd);

		for (OrderDetailList bean : itemList) {
			List<OrderRemarkList> list = orderRemarkListDao.getRemarkListWithItem(venderCd, deliveryCd, bean.getItemCd());

			remarkList.addAll(list);
		}

		if (remarkList.isEmpty()) {
			throw new NoDataException();
		}
		return remarkList;
	}

	/**
	 * orderListLogicを設定します。
	 * @param orderImportListLogic orderImportListLogic
	 */
	public void setOrderImportListLogic(final OrderImportListLogic orderImportListLogic) {
		this.orderImportListLogic = orderImportListLogic;
	}

	/**
	 * repDeliveryDateContactHeaderDaoを取得します。
	 * @return repDeliveryDateContactHeaderDao
	 */
	public RepDeliveryDateContactHeaderDao getRepDeliveryDateContactHeaderDao() {
		return repDeliveryDateContactHeaderDao;
	}

	/**
	 * repDeliveryDateContactHeaderDaoを設定します。
	 * @param repDeliveryDateContactHeaderDao repDeliveryDateContactHeaderDao
	 */
	public void setRepDeliveryDateContactHeaderDao(
			RepDeliveryDateContactHeaderDao repDeliveryDateContactHeaderDao) {
		this.repDeliveryDateContactHeaderDao = repDeliveryDateContactHeaderDao;
	}

	/**
	 * repDeliveryDateContactDetailDaoを取得します。
	 * @return repDeliveryDateContactDetailDao
	 */
	public RepDeliveryDateContactDetailDao getRepDeliveryDateContactDetailDao() {
		return repDeliveryDateContactDetailDao;
	}

	/**
	 * repDeliveryDateContactDetailDaoを設定します。
	 * @param repDeliveryDateContactDetailDao repDeliveryDateContactDetailDao
	 */
	public void setRepDeliveryDateContactDetailDao(
			RepDeliveryDateContactDetailDao repDeliveryDateContactDetailDao) {
		this.repDeliveryDateContactDetailDao = repDeliveryDateContactDetailDao;
	}

	/**
	 * balanceDaoを設定します。
	 * @param balanceDao balanceDao
	 */
	public void setBalanceDao(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * orderDetailListDaoを設定します。
	 * @param orderDetailListDao orderDetailListDao
	 */
	public void setOrderDetailListDao(OrderDetailListDao orderDetailListDao) {
		this.orderDetailListDao = orderDetailListDao;
	}

	/**
	 * orderRemarkListDaoを設定します。
	 * @param orderRemarkListDao orderRemarkListDao
	 */
	public void setOrderRemarkListDao(OrderRemarkListDao orderRemarkListDao) {
		this.orderRemarkListDao = orderRemarkListDao;
	}


	/**
	 * orderImportCommonLogicを取得します。
	 * @return orderImportCommonLogic
	 */
	public OrderImportCommonLogic getOrderImportCommonLogic() {
		return orderImportCommonLogic;
	}

	/**
	 * orderImportCommonLogicを設定します。
	 * @param orderImportCommonLogic orderImportCommonLogic
	 */
	public void setOrderImportCommonLogic(
			OrderImportCommonLogic orderImportCommonLogic) {
		this.orderImportCommonLogic = orderImportCommonLogic;
	}

	/**
	 * frstOrderDetailDaoを設定します。
	 * @param frstOrderDetailDao frstOrderDetailDao
	 */
	public void setFrstOrderDetailDao(FrstOrderDetailDao frstOrderDetailDao) {
		this.frstOrderDetailDao = frstOrderDetailDao;
	}

	/**
	 * frstOrderHeadDaoを設定します。
	 * @param frstOrderHeadDao frstOrderHeadDao
	 */
	public void setFrstOrderHeadDao(FrstOrderHeadDao frstOrderHeadDao) {
		this.frstOrderHeadDao = frstOrderHeadDao;
	}

}