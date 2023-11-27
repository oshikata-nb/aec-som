/*
 * Created on 2009/08/21
 * 受注の帳票Excelにおけるヘッダーデータへの、納入先住所の追加、運送会社コード&運送会社名称の位置修正 AEC佐藤 2020/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;

import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.orderhead.OrderHead;
import com.asahikaseieng.dao.entity.orderhead.OrderHeadDao;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailList;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailListDao;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderDetailListForReport;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListForReport;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkListDao;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipConditionForReport;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipDetailForReport;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipHeaderForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * ExcelDecorator実装クラス.受注一覧
 * @author t1344224
 */
public class OrderListExcelDecoratorImpl implements OrderListExcelDecorator {

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

	private OrderListLogic orderListLogic;

	private BalanceDao balanceDao;

	private VenderDao venderDao;

	private OrderRemarkListDao orderRemarkListDao;

	private OrderDetailListDao orderDetailListDao;

	private OrderHeadDao orderHeadDao;

	/** 納品書備考長さ */
	protected static final short REMARK_LENGTH = 42;

	/**
	 * コンストラクタ
	 */
	public OrderListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final OrderListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("orderlist");

		// ヘッダデータをセット
		setHeader(condition);

		// 詳細データをセット
		setDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createOrderReport(
			final RepOrderSlipConditionForReport condition) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_order");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// ヘッダデータをセット
		setOrderHeader(condition);

		// 詳細データをセット
		setOrderDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * ヘッダーデータをセット
	 * @param condition 検索条件
	 */
	private void setHeader(final OrderListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<OrderListForReport> list = orderListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("order_head");

		/* リスト部 */
		for (OrderListForReport bean : list) {
			colNum = TEMP_START_COL;
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderDivision());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getDivisionName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getOrderDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInputTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getInputTantoName());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getSalesTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getSalesTantoName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getDeliveryName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryAddress());
			// 納入先住所追加 AEC佐藤 2020/05/08
			builder.setExcelDataString(rowNum, colNum++, bean
				.getDeliveryAddressFull());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryTelNo());
			// 運送会社コード&運送会社名称位置修正 AEC佐藤 2020/05/08
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getBalanceCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStatus());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCustomerOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderAmount());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSuggestedDeliverlimit());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getScheduledShippingDate());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getLeadTime());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getDeliveryExpectedDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryExpectedTime());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCarryFare());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCarryInvoiceFlag());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getOrderPicture());
			builder
					.setExcelDataString(rowNum, colNum++, bean
						.getPrintSummery());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliverySlipSummery());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getOrderSummery());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipPublishComp());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipPublishDate());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());

			rowNum++;
		}
	}

	/**
	 * ヘッダーデータをセット
	 * @param condition 検索条件
	 */
	private void setDetail(final OrderListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<OrderDetailListForReport> detailList = orderListLogic
				.getDetailListForReport(condition);

		/* シートをセット */
		builder.setSheet("order_detail");

		/* リスト部 */
		for (OrderDetailListForReport bean : detailList) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRowNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getShippingNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getStyleOfPacking());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOtherCompanyCd1());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getOrderQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStandardUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStandardDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSpecialDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTmpUnitpriceFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getMatss());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEstimateStandardAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEstimateMatss());
			builder.setExcelDataString(rowNum, colNum++, bean.getStatusName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			rowNum++;
		}
	}

	/**
	 * 受注帳票ヘッダーデータをセット
	 * @param condition 検索条件
	 */
	private void setOrderHeader(final RepOrderSlipConditionForReport condition) {
		short rowNum = TEMP_ORDER_START_ROW;
		short colNum = TEMP_ORDER_START_COL;

		List<RepOrderSlipHeaderForReport> list = orderListLogic
				.getSlipHeaderListForReport(condition);

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		/* リスト部 */
		for (RepOrderSlipHeaderForReport bean : list) {
			colNum = TEMP_START_COL;
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderDivision());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getDivisionName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getOrderDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInputTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getInputTantoName());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getSalesTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getSalesTantoName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getDeliveryName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryAddress());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryAddressAll());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryTelNo());

			builder.setExcelDataString(rowNum, colNum++, bean.getBalanceCd());

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 1, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 1, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 1, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 2, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 2, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 2, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 3, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 3, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 3, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 4, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 4, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 4, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 5, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 5, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 5, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 6, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 6, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 6, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 7, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 7, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 7, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 8, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 8, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 8, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 9, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 9, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 9, GET_VENDER_NAME));

			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 10, GET_VENDER_SHOP));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 10, GET_VENDER_CD));
			builder.setExcelDataString(rowNum, colNum++, getVenderFromBalance(
				bean.getBalanceCd(), 10, GET_VENDER_NAME));

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStatus());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCustomerOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderAmount());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSuggestedDeliverlimit());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getScheduledShippingDate());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getLeadTime());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getDeliveryExpectedDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryExpectedTime());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCarryFare());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCarryInvoiceFlag());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCarryInvoiceString());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getOrderPicture());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getPrintSummery());
			builder.setExcelDataString(rowNum, colNum++, AecStringUtils.split(
				bean.getDeliverySlipSummery(), REMARK_LENGTH));
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getOrderSummery());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipPublishComp());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipPublishDate());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());

			List<OrderRemarkList> remarkList = null;
			HSSFRichTextString rtext = new HSSFRichTextString("CRLF");
			try {
				// 備考の検索
				remarkList = getRemarkList(bean.getOrderNo());
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

			rowNum++;
		}
	}

	/**
	 * 受注帳票詳細データをセット
	 * @param condition 検索条件
	 */
	private void setOrderDetail(final RepOrderSlipConditionForReport condition) {
		short rowNum = TEMP_ORDER_START_ROW;
		short colNum = TEMP_ORDER_START_COL;

		List<RepOrderSlipDetailForReport> detailList = orderListLogic
				.getSlipDetailListForReport(condition);

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		/* リスト部 */
		for (RepOrderSlipDetailForReport bean : detailList) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRowNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getShippingNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getStyleOfPacking());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOtherCompanyCd1());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getOrderQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStandardUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStandardDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSpecialDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderAmount());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTmpUnitpriceFlg());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getTmpUnitpriceString());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getMatss());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEstimateStandardAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEstimateMatss());
			builder.setExcelDataString(rowNum, colNum++, bean.getStatusName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
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
		List<OrderDetailList> itemList = orderDetailListDao
				.getDetailList(orderNo);

		OrderHead head = orderHeadDao.getEntity(orderNo);

		venderCd = head.getVenderCd();
		deliveryCd = head.getDeliveryCd();

		// 得意先と納入先に紐付く備考を検索。
		List<OrderRemarkList> remarkList = orderRemarkListDao
				.getRemarkListNoItem(venderCd, deliveryCd);

		for (OrderDetailList bean : itemList) {
			List<OrderRemarkList> list = orderRemarkListDao
					.getRemarkListWithItem(venderCd, deliveryCd, bean
							.getItemCd());

			remarkList.addAll(list);
		}

		if (remarkList.isEmpty()) {
			throw new NoDataException();
		}
		return remarkList;
	}

	/**
	 * orderListLogicを設定します。
	 * @param orderListLogic orderListLogic
	 */
	public void setOrderListLogic(final OrderListLogic orderListLogic) {
		this.orderListLogic = orderListLogic;
	}

	/**
	 * balanceDaoを設定します。
	 * @param balanceDao balanceDao
	 */
	public void setBalanceDao(final BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * orderRemarkListDaoを設定します。
	 * @param orderRemarkListDao orderRemarkListDao
	 */
	public void setOrderRemarkListDao(OrderRemarkListDao orderRemarkListDao) {
		this.orderRemarkListDao = orderRemarkListDao;
	}

	/**
	 * orderDetailListDaoを設定します。
	 * @param orderDetailListDao orderDetailListDao
	 */
	public void setOrderDetailListDao(OrderDetailListDao orderDetailListDao) {
		this.orderDetailListDao = orderDetailListDao;
	}

	/**
	 * orderHeadDaoを設定します。
	 * @param orderHeadDao orderHeadDao
	 */
	public void setOrderHeadDao(OrderHeadDao orderHeadDao) {
		this.orderHeadDao = orderHeadDao;
	}
}
