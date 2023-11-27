/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.slipshipping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.entity.master.carry.Carry;
import com.asahikaseieng.dao.entity.master.carry.CarryDao;
import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.orderhead.OrderHead;
import com.asahikaseieng.dao.entity.orderhead.OrderHeadDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProGetCarryBcNextValDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.procedurecall.pakcarryfile.MakeCarryFileDto;
import com.asahikaseieng.dao.nonentity.procedurecall.pakcarryfile.PakCarryFileDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryDetail.RepSlipShippingDeliveryDetail;
import com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryDetail.RepSlipShippingDeliveryDetailDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryHeader.RepSlipShippingDeliveryHeader;
import com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryHeader.RepSlipShippingDeliveryHeaderDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingFareDetail.RepSlipShippingFareDetail;
import com.asahikaseieng.dao.nonentity.repSlipShippingFareDetail.RepSlipShippingFareDetailDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingFareHeader.RepSlipShippingFareHeader;
import com.asahikaseieng.dao.nonentity.repSlipShippingFareHeader.RepSlipShippingFareHeaderDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingNewTagDetail.RepSlipShippingNewTagDetail;
import com.asahikaseieng.dao.nonentity.repSlipShippingNewTagDetail.RepSlipShippingNewTagDetailDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingNewTagHeader.RepSlipShippingNewTagHeader;
import com.asahikaseieng.dao.nonentity.repSlipShippingNewTagHeader.RepSlipShippingNewTagHeaderDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingOrderDetail.RepSlipShippingOrderDetail;
import com.asahikaseieng.dao.nonentity.repSlipShippingOrderDetail.RepSlipShippingOrderDetailDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingOrderHead.RepSlipShippingOrderHead;
import com.asahikaseieng.dao.nonentity.repSlipShippingOrderHead.RepSlipShippingOrderHeadDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingPostalDetail.RepSlipShippingPostalDetail;
import com.asahikaseieng.dao.nonentity.repSlipShippingPostalDetail.RepSlipShippingPostalDetailDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingReqDetail.RepSlipShippingReqDetail;
import com.asahikaseieng.dao.nonentity.repSlipShippingReqDetail.RepSlipShippingReqDetailDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingReqHeader.RepSlipShippingReqHeader;
import com.asahikaseieng.dao.nonentity.repSlipShippingReqHeader.RepSlipShippingReqHeaderDao;
import com.asahikaseieng.dao.nonentity.slipshipping.CarryCheckDigit;
import com.asahikaseieng.dao.nonentity.slipshipping.CarryCheckDigitCondition;
import com.asahikaseieng.dao.nonentity.slipshipping.CarryCheckDigitDao;
import com.asahikaseieng.dao.nonentity.slipshipping.CarryFile;
import com.asahikaseieng.dao.nonentity.slipshipping.CarryFileDao;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingDirDetail;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingDirDetailDao;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingDirHeader;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingDirHeaderDao;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingNifuda;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingNifudaDao;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingPerican;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingPericanDao;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingSchDetail;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingSchDetailDao;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingSchHeader;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingSchHeaderDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 出荷ＥＸＣＥＬダウンロード用ファイル作成
 * 19/11/29　AECS佐藤　出荷指図一覧、出荷予定一覧の改修
 * @author t2712372
 */
public class SlipShippingOrderListExcelDecoratorImpl implements
		SlipShippingOrderListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepSlipShippingOrderHeadDao repSlipShippingOrderHeadDao;

	private RepSlipShippingOrderDetailDao repSlipShippingOrderDetailDao;

	private RepSlipShippingReqHeaderDao repSlipShippingReqHeaderDao;

	private RepSlipShippingReqDetailDao repSlipShippingReqDetailDao;
	
	private RepSlipShippingFareHeaderDao repSlipShippingFareHeaderDao;
	
	private RepSlipShippingFareDetailDao repSlipShippingFareDetailDao;

	private RepSlipShippingDirHeaderDao repSlipShippingDirHeaderDao;

	private RepSlipShippingDirDetailDao repSlipShippingDirDetailDao;

	private RepSlipShippingSchHeaderDao repSlipShippingSchHeaderDao;

	private RepSlipShippingSchDetailDao repSlipShippingSchDetailDao;

	private RepSlipShippingNifudaDao repSlipShippingNifudaDao;

	private RepSlipShippingPericanDao repSlipShippingPericanDao;

	private RepSlipShippingDeliveryHeaderDao repSlipShippingDeliveryHeaderDao;
	
	private RepSlipShippingDeliveryDetailDao repSlipShippingDeliveryDetailDao;
	
	private RepSlipShippingPostalDetailDao repSlipShippingPostalDetailDao;

	private RepSlipShippingNewTagHeaderDao repSlipShippingNewTagHeaderDao;
	
	private RepSlipShippingNewTagDetailDao repSlipShippingNewTagDetailDao;

	private OrganizationDao organizationDao;

	private VenderDao venderDao;

	private BalanceDao balanceDao;

	private OrderHeadDao orderHeadDao;

	private LoginDao loginDao;
	
	private SlipShippingListLogic slipShippingListLogic;

	private ProcedureCallDao procedureCallDao;
	
	private PakCarryFileDao pakCarryFileDao;

	private CarryFileDao carryFileDao;

	private CarryCheckDigitDao carryCheckDigitDao;
	
	private CarryDao carryDao;

	
	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** 納品書備考長さ */
	protected static final short REMARK_LENGTH = 42;

	// /** 印刷モード（AUTO/MANUAL）印刷取得列位置 */
	// protected static final short PRINTER_MODE_GETCOL = 3;
	//
	// /** プリンタ名取得列位置（出荷伝票） */
	// protected static final short PRINTER_NAME_GETCOL_DENPYO = 10;
	//
	// /** プリンタ名取得列位置（出荷依頼書） */
	// protected static final short PRINTER_NAME_GETCOL_REQUEST = 25;
	//
	// /** プリンタ名取得列位置（出荷指図書） */
	// protected static final short PRINTER_NAME_GETCOL_DIRECTION = 9;
	//
	// /** プリンタ名取得列位置（出荷予定表） */
	// protected static final short PRINTER_NAME_GETCOL_SCHEDULE = 11;
	//
	// /** プリンタ名取得列位置（荷札） */
	// protected static final short PRINTER_NAME_GETCOL_NIFUDA = 18;
	//
	// /** プリンタ名取得列位置（ペリカン伝票） */
	// protected static final short PRINTER_NAME_GETCOL_PERICAN = 19;
	//
	// /** プリンタ定義（プリンタ名、印刷モード）出力行位置 */
	// protected static final short PRINTER_SETTING_ROW = 2;
	//
	// /** 印刷モード（AUTO/MANUAL）印刷出力列位置 */
	// protected static final short PRINTER_MODE_COL = 0;
	//
	// /** プリンタ名出力列位置 */
	// protected static final short PRINTER_NAME_COL = 1;

	/** 出荷伝票ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 出荷伝票詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/** 出荷依頼書ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME_REQUEST = "header_data_request";

	/** 出荷依頼書詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME_REQUEST = "detail_data_request";

	/** 出荷指図書ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME_DIRECTION = "header_data_direction";

	/** 出荷指図書詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME_DIRECTION = "detail_data_direction";

	/** 出荷予定表ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME_SCHEDULE = "header_data_schedule";

	/** 出荷予定表詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME_SCHEDULE = "detail_data_schedule";

	/** 荷札ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME_NIFUDA = "header_data_nifuda";

	/** 荷札詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME_NIFUDA = "detail_data_nifuda";

	/** ペリカン伝票ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME_PERICAN = "header_data_perican";

	/** ペリカン伝票詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME_PERICAN = "detail_data_perican";
	
	/** 運賃表ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME_FARE = "header_data_fare";

	/** 運賃表詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME_FARE = "detail_data_fare";
	
	/** 納品伝票ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME_DELIVERY = "header_data_delivery";

	/** 納品伝票詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME_DELIVERY = "detail_data_delivery";

	/** 新荷札ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME_NEW_NIFUDA = "header_data_new_tag";

	/** 新荷札詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME_NEW_NIFUDA = "detail_data_new_tag";

	/** 新郵政詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME_POSTAL = "slip_shipping_postal";

	private static final String YYYYMMDD = null;
	/**
	 * コンストラクタ
	 */
	public SlipShippingOrderListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ArrayList<String> slipShippingNo,
			final ArrayList<String> shippingNo, final String tantoName,
			final Timestamp currentDate, final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_shipping");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 出荷伝票ヘッダを取得
		List<RepSlipShippingOrderHead> headerList = repSlipShippingOrderHeadDao
				.getSlipShippingList(slipShippingNo);

		List<RepSlipShippingOrderDetail> detailList = repSlipShippingOrderDetailDao
				.getSlipShippingList(slipShippingNo, shippingNo);

		/* ヘッダ情報をセット */
		setHeader(headerList, tantoName, currentDate);
		/* 明細をセット */
		setDetail(detailList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReportRequest(
			final ArrayList<String> shippingNo, final String tantoName,
			final String organizationCd, final Timestamp currentDate,
			final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_shipping_request");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 出荷依頼書ヘッダを取得
		List<RepSlipShippingReqHeader> headerList = repSlipShippingReqHeaderDao
				.getSlipShippingList(shippingNo);

		List<RepSlipShippingReqDetail> detailList = repSlipShippingReqDetailDao
				.getSlipShippingList(shippingNo);

		/* ヘッダ情報をセット */
		setHeaderRequest(headerList, tantoName, organizationCd, currentDate);
		/* 明細をセット */
		setDetailRequest(detailList);
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReportDirection(
			final ArrayList<String> shippingNo, final String tantoName,
			final Timestamp currentDate, final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_shipping_direction");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 出荷指図書ヘッダを取得
		List<RepSlipShippingDirHeader> headerList = repSlipShippingDirHeaderDao
				.getListForReport(shippingNo);

		List<RepSlipShippingDirDetail> detailList = repSlipShippingDirDetailDao
				.getListForReport(shippingNo);

		/* ヘッダ情報をセット */
		setHeaderDirection(headerList, tantoName, currentDate);
		/* 明細をセット */
		setDetailDirection(detailList);
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReportSchedule(
			final ArrayList<String> shippingNo, final String tantoName,
			final Timestamp currentDate, final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_shipping_schedule");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 出荷予定表ヘッダを取得
		List<RepSlipShippingSchHeader> headerList = repSlipShippingSchHeaderDao
				.getListForReport(shippingNo);

		List<RepSlipShippingSchDetail> detailList = repSlipShippingSchDetailDao
				.getListForReport(shippingNo);

		/* ヘッダ情報をセット */
		setHeaderSchedule(headerList, tantoName, currentDate);
		/* 明細をセット */
		setDetailSchedule(detailList);
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReportTag(final ArrayList<String> shippingNo,
			final String tantoName, final String organizationCd,
			final Timestamp currentDate, final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_shipping_tag");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 荷札ヘッダを取得
		List<RepSlipShippingNifuda> headerListNifuda = repSlipShippingNifudaDao
				.getListForReport(shippingNo);

		List<RepSlipShippingNifuda> detailListNifuda = repSlipShippingNifudaDao
				.getListForReport(shippingNo);

		/* 荷札ヘッダ情報をセット */
		setHeaderNifuda(headerListNifuda, tantoName, currentDate);
		/* 荷札明細をセット */
		setDetailNifuda(detailListNifuda, organizationCd);

		// ペリカン伝票ヘッダを取得
		List<RepSlipShippingPerican> headerListPerican = repSlipShippingPericanDao
				.getListForReport(shippingNo);

		List<RepSlipShippingPerican> detailListPerican = repSlipShippingPericanDao
				.getListForReport(shippingNo);

		/* ペリカン伝票ヘッダ情報をセット */
		setHeaderPerican(headerListPerican, tantoName, currentDate);
		/* ペリカン伝票明細をセット */
		setDetailPerican(detailListPerican);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 運賃表
	 * {@inheritDoc}TODO
	 */	
	public FileDownloadInfo createReportFare(
			final ArrayList<String> shippingNo, final String tantoName,final String organizationCd,
			final Timestamp currentDate, final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_shipping_fare");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		/*運賃表ヘッダを取得*/
		List<RepSlipShippingFareHeader> headerList = repSlipShippingFareHeaderDao
				.getSlipShippingList(shippingNo);

		List<RepSlipShippingFareDetail> detailList = repSlipShippingFareDetailDao
				.getSlipShippingList(shippingNo);
				

		/* ヘッダ情報をセット */
		setHeaderFare(headerList, tantoName, currentDate);
				
		/* 明細をセット */
		setDetailFare(detailList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	
	/**
	 * {@inheritDoc}
	 * 納品伝票
	 */
	public FileDownloadInfo createReportDelivery(
			final ArrayList<String> shippingNo, final String tantoName,
			final String organizationCd, final Timestamp currentDate,
			final String ipAddress , final String loginUserId) throws NoDataException{

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_shipping_delivery");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		//納品伝票ヘッダを取得
		List<RepSlipShippingDeliveryHeader> headerList = repSlipShippingDeliveryHeaderDao
				.getSlipShippingList(shippingNo);

		List<RepSlipShippingDeliveryDetail> detailList = repSlipShippingDeliveryDetailDao
				.getSlipShippingList(shippingNo);
		
		// 出荷バーコード付番処理
		// Java野中でヘッダのレコードをループしてバーコードが付与されていない付番が必要なグループごとにBCを作って付番する。
		// 終わったらSHIPPINGをUpdateする。Transactionなので付けたそのまま。
		HashMap< String , String > bcSettingList = this.setCarryBC(headerList);
		
		slipShippingListLogic.setCarryBC(bcSettingList, detailList, loginUserId);
		
		/* ヘッダ情報をセット */
		setHeaderDelivery(headerList, tantoName, organizationCd, currentDate,shippingNo);
		/* 明細をセット */
		setDetailDelivery(detailList);
		
		
		
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}
	
	/**
	 * {@inheritDoc}
	 * 新郵政
	 * @throws IOException 
	 */
	public FileDownloadInfo createReportPostal(
			final ArrayList<String> shippingNo, final String tantoName,
			final String postalClientCd, final Timestamp currentDate,
			final String ipAddress) throws IOException {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		List<RepSlipShippingPostalDetail> detailList = repSlipShippingPostalDetailDao
				.getSlipShippingList(shippingNo,postalClientCd);

		/* 明細をセット */
		List<String> csvDataList = setDetailPostal(detailList);

//		/* CSVデータ出力 */
		PrintWriter out = null;
		/* 一回ファイルに出力するためのパス */
		File tempDir = SystemUtils.getJavaIoTmpDir();
		File csv = new File(tempDir, "新郵政_" + AecDateUtils.dateFormat(AecDateUtils.getCurrentTimestamp(), "yyyyMMdd") + ".csv");
		try {
			/* CSVデータファイル */
			out = new PrintWriter(new BufferedWriter(new FileWriter(csv)));

			for (String csvData : csvDataList) {
				out.println(csvData);
			}
		} catch (IOException e) {
			throw new IOException();
		}finally{
			if(out != null){
				out.flush();
				out.close();
				out = null;
			}
		}
		
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(csv.getName(), csv.getPath());
	}
	
	/**
	 * {@inheritDoc}
	 * 新荷札
	 */
	public FileDownloadInfo createReportNewTag(final ArrayList<String> shippingNoList,
			final String tantoName,
			final Timestamp currentDate, final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_shipping_new_tag");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 荷札ヘッダを取得
		List<RepSlipShippingNewTagHeader> headerListNewNifuda = repSlipShippingNewTagHeaderDao
				.getSlipShippingList(shippingNoList);

		List<RepSlipShippingNewTagDetail> detailListNifuda = repSlipShippingNewTagDetailDao
				.getSlipShippingList(shippingNoList);

		/* 荷札ヘッダ情報をセット */
		setHeaderNewNifuda(headerListNewNifuda, tantoName, currentDate);
		/* 荷札明細をセット */
		setDetailNewNifuda(detailListNifuda);
		
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 行、列を指定してExcelシートにデータを貼り付け
	 * @param tantoName 担当者名
	 */
	private void setExcelDataString(final short sRow, final short sCol,
			final String value) {

		// nullでは無い場合のみデータをセット
		if (value != null) {
			// データをセット
			builder.setCellValue(sRow, sCol, value);
		}
	}

	/**
	 * 出荷伝票ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RepSlipShippingOrderHead> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepSlipShippingOrderHead bean : headerList) {
			sCol = TEMP_START_COL;

			Vender vender = getVender(bean.getOrderNo(), bean.getDeliveryCd());

			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());

			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());

			// 上位ロケーション
			setExcelDataString(sRow, sCol++, bean.getUpperLocationCd());

			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());

			// 客先注文番号
			setExcelDataString(sRow, sCol++, bean.getCustomerOrderNo());

			// 納入予定日(受注が存在する場合のみ）
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedDate());

			// 納入時刻(受注が存在する場合のみ）
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedTime());
			// 納入先宛先(受注が存在する場合のみ）
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddress());
			// 納品書備考(受注が存在する場合のみ）
			// setExcelDataString(sRow, sCol++, bean.getDeliverySlipSummery());
			setExcelDataString(sRow, sCol++, AecStringUtils.split(bean
					.getDeliverySlipSummery(), REMARK_LENGTH));
			// 取引区分（受注が有る場合[売上]ない場合（花王その他）空欄
			setExcelDataString(sRow, sCol++, bean.getDealingsDivision());

			// 得意先毎に処理を分岐
			if (vender != null) {
				// 得意先コード
				setExcelDataString(sRow, sCol++, vender.getVenderCd());
				// 得意先名称１
				setExcelDataString(sRow, sCol++, getVenderName(vender));
				// 得意先名称２
				setExcelDataString(sRow, sCol++, "");
			} else {
				// 得意先コード
				setExcelDataString(sRow, sCol++, bean.getVenderCd());
				// 得意先名称１
				setExcelDataString(sRow, sCol++, "");
				// 得意先名称２
				setExcelDataString(sRow, sCol++, "");
			}
			// 納入先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			// 納入先名称１
			setExcelDataString(sRow, sCol++, bean.getDeliveryName1());
			// 納入先名称２
			setExcelDataString(sRow, sCol++, bean.getDeliveryName2());
			// 納入先住所１
			setExcelDataString(sRow, sCol++, bean.getAddress1());
			// 納入先住所２
			setExcelDataString(sRow, sCol++, bean.getAddress2());
			// 納入先住所３
			setExcelDataString(sRow, sCol++, bean.getAddress3());
			// 納入先電話番号
			setExcelDataString(sRow, sCol++, bean.getTelNo());
			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			// 運送会社名称１
			setExcelDataString(sRow, sCol++, bean.getCarryName1());
			// 運送会社名称２
			setExcelDataString(sRow, sCol++, bean.getCarryName2());

			// 担当者コード
			setExcelDataString(sRow, sCol++, tantoName);

			// 担当者名称
			Login loginBean = loginDao.getEntity(tantoName);

			if (loginBean != null) {
				setExcelDataString(sRow, sCol++, loginBean.getTantoNm());
			} else {
				setExcelDataString(sRow, sCol++, "");
			}

			// 受注担当者コード
			setExcelDataString(sRow, sCol++, bean.getOrderTantoCd());
			// 受注担当者名称
			setExcelDataString(sRow, sCol++, bean.getOrderTantoName());

			sRow++;
		}
	}
	
	/**TODO
	 * 運賃表ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderFare(final List<RepSlipShippingFareHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME_FARE);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepSlipShippingFareHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 出荷番号
			setExcelDataString(sRow, sCol++, bean.getShippingNo());
			// 分類コード
			setExcelDataString(sRow, sCol++, bean.getCategoryDivision());
			// 得意先コード
			setExcelDataString(sRow, sCol++, bean.getVenderCd());
			// お得意先名
			setExcelDataString(sRow, sCol++, bean.getVenderName());
			// お届先名
			setExcelDataString(sRow, sCol++, bean.getDeliveryNameAll());
			// お届先宛先
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddress());
			// お届先住所
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddressAll());
			// ＴＥＬ
			setExcelDataString(sRow, sCol++, bean.getDeliveryTelNo());
			// 届先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			// 出荷指図日
			setExcelDataString(sRow, sCol++, bean.getShippingInstructDate());
			// 出荷年月日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 出荷担当者コード
			setExcelDataString(sRow, sCol++, bean.getTantoCd());
			// 到着指定日
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedDate());
			// 到着時間指定
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedTime());
			// 出荷地
			setExcelDataString(sRow, sCol++, bean.getUpperLocationCd());
			// 出荷地名称
			setExcelDataString(sRow, sCol++, bean.getLocationName());
			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			// 運送会社1
			setExcelDataString(sRow, sCol++, bean.getCarryName1());
			// 運送会社2
			setExcelDataString(sRow, sCol++, bean.getCarryName2());
			// 備考(印字用)
			setExcelDataString(sRow, sCol++, bean.getPrintSummery());			
			// 自動表示備考
			setExcelDataString(sRow, sCol++, bean.getDeliverySlipSummery());
			// 参照
			setExcelDataString(sRow, sCol++, bean.getReference());
			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getCustomerOrderNo());
			// 発注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			// 行番号(受注)
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());
			// 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
			setExcelDataString(sRow, sCol++, bean.getShippingStatus());
			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getItemCd());
			// 完納区分
			setExcelDataString(sRow, sCol++, bean.getDeliveryComp());
			// 出荷完了日
			setExcelDataString(sRow, sCol++, bean.getShippingResultDate());
			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());
			// 出荷伝票行番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipRowNo());
			// 出荷伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishComp());
			// 伝票発行日
			setExcelDataString(sRow, sCol++, bean.getSlipPublishDate());
			// 出荷指図書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderComp());
			// 出荷指図書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderDate());
			// 出荷予定表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleComp());
			// 出荷予定表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleDate());
			// 荷札,ペリカン発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagComp());
			// 荷札,ペリカン発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagDate());
			// 出荷依頼書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestComp());
			// 出荷依頼書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestDate());
			// 運賃表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingFareComp());
			// 運賃表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingFareDate());
			// 納品伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingDelivelyComp());
			// 納品伝票発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingDelivelyDate());
			// 新荷札発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingNewTagComp());
			// 新荷札発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingNewTagDate());
			// 新郵政発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingPostalComp());
			// 新郵政発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingPostalDate());
			// 希望納期
			setExcelDataString(sRow, sCol++, bean.getSuggestedDeliverlimit());
			// 運賃
			setExcelDataString(sRow, sCol++, bean.getCarryFare());
			// 積出ナンバー
			setExcelDataString(sRow, sCol++, bean.getSendingOffNumber());
			// 登録日時
			setExcelDataString(sRow, sCol++, bean.getInputDate());
			// 登録者ID
			setExcelDataString(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			setExcelDataString(sRow, sCol++, bean.getUpdateDate());
			// 更新者ID
			setExcelDataString(sRow, sCol++, bean.getUpdatorCd());

			sRow++;
		}
	}
	
	/**
	 * 運賃表詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailFare(
			final List<RepSlipShippingFareDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME_FARE);

		for (RepSlipShippingFareDetail bean : detailList) {
			sCol = TEMP_START_COL;
			
			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
						
			// 出荷番号
			setExcelDataString(sRow, sCol++, bean.getShippingNo());

			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getItemCd());

			// JANコード
			setExcelDataString(sRow, sCol++, bean.getJanCd());
			// 品目名称
			setExcelDataString(sRow, sCol++, bean.getItemName());

			// 荷姿
			setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());

			// 増付
			setExcelDataString(sRow, sCol++, bean.getMatss());

			// 売上数量
			setExcelDataString(sRow, sCol++, bean.getSalesQuantity());

			// 増付、納品数量（バラ）
			setExcelDataString(sRow, sCol++, bean.getMatssIns());

			// 納品数量（バラ）
			setExcelDataString(sRow, sCol++, bean.getQtyIns());

			// 総重量
			setExcelDataString(sRow, sCol++, bean.getAllUpWeight());

			// 出荷指図日
			setExcelDataString(sRow, sCol++, bean.getShippingInstructDate());

			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());

			// 出荷担当者コード
			setExcelDataString(sRow, sCol++, bean.getTantoCd());

			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());

			// 行番号(受注)
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());

			// 取引先区分(未使用)
			setExcelDataString(sRow, sCol++, bean.getVenderDivision());

			// 取引先コード
			setExcelDataString(sRow, sCol++, bean.getVenderCd());

			// 納入先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());

			// 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
			setExcelDataString(sRow, sCol++, bean.getShippingStatus());

			// 摘要(未使用)
			setExcelDataString(sRow, sCol++, bean.getSummery());

			// 完納区分
			setExcelDataString(sRow, sCol++, bean.getDeliveryComp());

			// 出荷完了日
			setExcelDataString(sRow, sCol++, bean.getShippingResultDate());

			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());

			// 出荷伝票行番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipRowNo());

			// 出荷伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishComp());

			// 伝票発行日
			setExcelDataString(sRow, sCol++, bean.getSlipPublishDate());

			// 出荷指図書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderComp());

			// 出荷指図書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderDate());

			// 出荷予定表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleComp());

			// 出荷予定表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleDate());

			// 荷札,ペリカン発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagComp());

			// 荷札,ペリカン発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagDate());

			// 出荷依頼書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestComp());

			// 出荷依頼書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestDate());

			// 運賃表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingFareComp());

			// 運賃表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingFareDate());

			// 納品伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingDelivelyComp());

			// 納品伝票発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingDelivelyDate());

			// 新荷札発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingNewTagComp());

			// 新荷札発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingNewTagDate());

			// 新郵政発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingPostalComp());

			// 新郵政発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingPostalDate());

			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());

			// 希望納期
			setExcelDataString(sRow, sCol++, bean.getSuggestedDeliverlimit());

			// 運賃
			setExcelDataString(sRow, sCol++, bean.getCarryFare());

			// 積出ナンバー
			setExcelDataString(sRow, sCol++, bean.getSendingOffNumber());
			
			// 出荷バーコード
			setExcelDataString(sRow, sCol++, bean.getCarryBc());
			

			// 登録日時
			setExcelDataString(sRow, sCol++, bean.getInputDate());

			// 登録者ID
			setExcelDataString(sRow, sCol++, bean.getInputorCd());

			// 更新日時
			setExcelDataString(sRow, sCol++, bean.getUpdateDate());

			// 更新者ID
			setExcelDataString(sRow, sCol++, bean.getUpdatorCd());

			sRow++;
		}
	}

	/**
	 * 得意先の名称を返す
	 * @param bean 得意先データ
	 */
	private String getVenderName(final Vender bean) {

		String ret = "";

		if (bean == null) {
			return ret;
		}

		if (bean.getVenderName1() != null) {
			ret = bean.getVenderName1();
		}

		if (bean.getVenderName2() != null) {
			ret += " " + bean.getVenderName2();
		}

		return ret;
	}

	/**
	 * 出荷伝票詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepSlipShippingOrderDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepSlipShippingOrderDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());
			// 出荷伝票行番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipRowNo());
			// 出荷番号
			setExcelDataString(sRow, sCol++, bean.getShippingNo());
			// 出荷指図日
			setExcelDataString(sRow, sCol++, bean.getShippingInstructDate());
			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 出荷担当者コード
			setExcelDataString(sRow, sCol++, bean.getTantoCd());
			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			// 行番号(受注)
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());
			// 取引先区分:不要
			setExcelDataString(sRow, sCol++, bean.getVenderDivision());
			// 取引先コード
			setExcelDataString(sRow, sCol++, bean.getVenderDivision());
			// 納入先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			// 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
			setExcelDataString(sRow, sCol++, bean.getShippingStatus());
			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getItemCd());
			// 品目名称
			setExcelDataString(sRow, sCol++, bean.getItemName());
			// 入数
			setExcelDataString(sRow, sCol++, bean.getNumberOfInsertions());
			// 荷姿
			setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			// JANコード
			setExcelDataString(sRow, sCol++, bean.getJanCd());

			// 摘要:未使用
			setExcelDataString(sRow, sCol++, bean.getSummery());
			// 完納区分
			setExcelDataString(sRow, sCol++, bean.getDeliveryComp());
			// 出荷完了日
			setExcelDataString(sRow, sCol++, bean.getShippingResultDate());
			// 出荷伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishComp());
			// 伝票発行日(必須一時解除)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishDate());
			// 出荷指図書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderComp());
			// 出荷指図書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderDate());
			// 出荷予定表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleComp());
			// 出荷予定表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleDate());
			// 荷札,ペリカン発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagComp());
			// 荷札,ペリカン発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagDate());
			// 出荷依頼書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestComp());
			// 出荷依頼書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestDate());
			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			// 希望納期
			setExcelDataString(sRow, sCol++, bean.getSuggestedDeliverlimit());
			// 運賃
			setExcelDataString(sRow, sCol++, bean.getCarryFare());
			// 積出ナンバー
			setExcelDataString(sRow, sCol++, bean.getSendingOffNumber());
			// 登録日時
			setExcelDataString(sRow, sCol++, bean.getInputDate());
			// 登録者ID
			setExcelDataString(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			setExcelDataString(sRow, sCol++, bean.getUpdateDate());
			// 更新者ID
			setExcelDataString(sRow, sCol++, bean.getUpdatorCd());

			// 出荷指図量累計
			setExcelDataString(sRow, sCol++, bean.getShippingSum());

			// 受注量もしくは出荷指図量累計
			setExcelDataString(sRow, sCol++, bean.getOrderQty());

			// 増付
			setExcelDataString(sRow, sCol++, bean.getMatss());

			// 納入数量
			setExcelDataString(sRow, sCol++, bean.getNumberOfInsertionsOrder());

			// 納入数量(増し付け）
			setExcelDataString(sRow, sCol++, bean.getNumberOfInsertionsMatss());

			// 総重量
			setExcelDataString(sRow, sCol++, bean.getAllUpWeight());

			sRow++;

		}
	}

	/**
	 * 出荷依頼書ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderRequest(
			final List<RepSlipShippingReqHeader> headerList,
			final String tantoName, final String organizationCd,
			final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME_REQUEST);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		Organization organizationBean = organizationDao
				.getEntity(organizationCd);

		for (RepSlipShippingReqHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 上位ロケーション
			setExcelDataString(sRow, sCol++, bean.getLocationCd());
			// 上位ロケーション
			setExcelDataString(sRow, sCol++, bean.getLocationName());
			// 担当者名
			setExcelDataString(sRow, sCol++, tantoName);
			// 担当部署名
			setExcelDataString(sRow, sCol++, organizationBean
					.getOrganizationName());
			// 担当部署FAX番号
			setExcelDataString(sRow, sCol++, organizationBean.getFaxNo());
			// 担当部署TEL番号
			setExcelDataString(sRow, sCol++, organizationBean.getTelNo());

			sRow++;
		}
	}

	/**
	 * 出荷依頼書詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailRequest(
			final List<RepSlipShippingReqDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		String prevOrderNo = "";
		int prevOrderFlg = 0;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME_REQUEST);

		for (RepSlipShippingReqDetail bean : detailList) {
			sCol = TEMP_START_COL;
			prevOrderFlg = 0;

			// 前行の受注番号と同じかチェック
			if (bean.getOrderNo() != null) {
				if (prevOrderNo.equals(bean.getOrderNo())) {
					prevOrderFlg = 1;
				}
			}

			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 出荷番号
			setExcelDataString(sRow, sCol++, bean.getShippingNo());
			// 出荷指図日
			setExcelDataString(sRow, sCol++, bean.getShippingInstructDate());
			// 出荷予定日
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean
						.getScheduledShippingDate());
			}
			// 出荷担当者コード
			setExcelDataString(sRow, sCol++, bean.getTantoCd());
			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			// 行番号(受注)
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());
			// 取引先区分:不要
			setExcelDataString(sRow, sCol++, bean.getVenderDivision());
			// 取引先コード
			setExcelDataString(sRow, sCol++, bean.getVenderCd());
			// 納入先コード
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			}
			// 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
			setExcelDataString(sRow, sCol++, bean.getShippingStatus());
			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getItemCd());
			// 摘要:未使用
			setExcelDataString(sRow, sCol++, bean.getSummery());
			// 完納区分
			setExcelDataString(sRow, sCol++, bean.getDeliveryComp());
			// 出荷完了日
			setExcelDataString(sRow, sCol++, bean.getShippingResultDate());
			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());
			// 出荷伝票行番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipRowNo());
			// 出荷伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishComp());
			// 伝票発行日(必須一時解除)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishDate());
			// 出荷指図書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderComp());
			// 出荷指図書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderDate());
			// 出荷予定表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleComp());
			// 出荷予定表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleDate());
			// 荷札,ペリカン発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagComp());
			// 荷札,ペリカン発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagDate());
			// 出荷依頼書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestComp());
			// 出荷依頼書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestDate());
			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			// 希望納期
			setExcelDataString(sRow, sCol++, bean.getSuggestedDeliverlimit());
			// 運賃
			setExcelDataString(sRow, sCol++, bean.getCarryFare());
			// 積出ナンバー
			setExcelDataString(sRow, sCol++, bean.getSendingOffNumber());
			// 登録日時
			setExcelDataString(sRow, sCol++, bean.getInputDate());
			// 登録者ID
			setExcelDataString(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			setExcelDataString(sRow, sCol++, bean.getUpdateDate());
			// 更新者ID
			setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			// 受注量もしくは出荷指図量累計
			setExcelDataString(sRow, sCol++, bean.getOrderQty());
			// 品目名称
			setExcelDataString(sRow, sCol++, bean.getItemName());
			// 荷姿
			setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			// 他社コード
			setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			// お届け先名称１
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean.getDeliveryName1());
			}
			// お届け先名称２
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean.getDeliveryName2());
			}
			// お届け先電話番号
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean.getTelNo());
			}
			// お届け先郵便番号
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean.getZipcodeNo());
			}
			// 住所
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean.getAddress());
			}
			// 納入予定日
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedDate());
			}
			// 納入予定時刻
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedTime());
			}
			// 客先注文番号
			setExcelDataString(sRow, sCol++, bean.getCustomerOrderNo());
			// 納品書備考
			setExcelDataString(sRow, sCol++, bean.getDeliverySlipSummery());
			// 上位ロケーションコード
			setExcelDataString(sRow, sCol++, bean.getLocationCd());

			// 納入先名称２＋納入先宛先 2013/12/03 追加要望対応
			if (prevOrderFlg == 1) {
				setExcelDataString(sRow, sCol++, "");
			} else {
				setExcelDataString(sRow, sCol++, bean
						.getDeliveryName2AndAddress());
			}
			sRow++;

			// 受注番号を保持
			if (bean.getOrderNo() != null) {
				prevOrderNo = bean.getOrderNo();
			} else {
				prevOrderNo = "";
			}

		}
	}

	/**
	 * 出荷指図書ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderDirection(
			final List<RepSlipShippingDirHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME_DIRECTION);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepSlipShippingDirHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 上位ロケーションコード
			setExcelDataString(sRow, sCol++, bean.getLocationCd());
			// 上位ロケーション名
			setExcelDataString(sRow, sCol++, bean.getLocationName());
			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			// 運送会社名
			setExcelDataString(sRow, sCol++, bean.getCarryName());

			sRow++;
		}
	}

	// 19/12/10　AECS佐藤（原料ロット番号追加）
	/**
	 * 出荷指図書詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailDirection(
			final List<RepSlipShippingDirDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME_DIRECTION);

		for (RepSlipShippingDirDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 出荷番号
			setExcelDataString(sRow, sCol++, bean.getShippingNo());
			// 出荷指図日
			setExcelDataString(sRow, sCol++, bean.getShippingInstructDate());
			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 出荷担当者コード
			setExcelDataString(sRow, sCol++, bean.getTantoCd());
			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			// 行番号(受注)
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());
			// 取引先区分:不要
			setExcelDataString(sRow, sCol++, bean.getVenderDivision());
			// 取引先コード
			setExcelDataString(sRow, sCol++, bean.getVenderCd());
			// 納入先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			// 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
			setExcelDataString(sRow, sCol++, bean.getShippingStatus());
			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getItemCd());
			// 摘要:未使用
			setExcelDataString(sRow, sCol++, bean.getSummery());
			// 完納区分
			setExcelDataString(sRow, sCol++, bean.getDeliveryComp());
			// 出荷完了日
			setExcelDataString(sRow, sCol++, bean.getShippingResultDate());
			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());
			// 出荷伝票行番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipRowNo());
			// 出荷伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishComp());
			// 伝票発行日(必須一時解除)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishDate());
			// 出荷指図書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderComp());
			// 出荷指図書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderDate());
			// 出荷予定表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleComp());
			// 出荷予定表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleDate());
			// 荷札,ペリカン発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagComp());
			// 荷札,ペリカン発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagDate());
			// 出荷依頼書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestComp());
			// 出荷依頼書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestDate());
			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			// 希望納期
			setExcelDataString(sRow, sCol++, bean.getSuggestedDeliverlimit());
			// 運賃
			setExcelDataString(sRow, sCol++, bean.getCarryFare());
			// 積出ナンバー
			setExcelDataString(sRow, sCol++, bean.getSendingOffNumber());
			// 登録日時
			setExcelDataString(sRow, sCol++, bean.getInputDate());
			// 登録者ID
			setExcelDataString(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			setExcelDataString(sRow, sCol++, bean.getUpdateDate());
			// 更新者ID
			setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			// 品目名称
			setExcelDataString(sRow, sCol++, bean.getItemName());
			// 荷姿
			setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			// 受注量もしくは出荷指図量累計
			setExcelDataString(sRow, sCol++, bean.getOrderQty());
			// ロット番号
			setExcelDataString(sRow, sCol++, bean.getLotNo());
			// 出荷指図数量
			setExcelDataString(sRow, sCol++, bean.getShippingInstruction());
			// ロケーションコード　19/11/29　AECS佐藤（変更）
			setExcelDataString(sRow, sCol++, bean.getLocationCd());
			// 重量
			setExcelDataString(sRow, sCol++, bean.getConvertQty());
			// 原料ロット番号　19/12/10　AECS佐藤（追加）
			setExcelDataString(sRow, sCol++, bean.getAliasLotNo());
			
			sRow++;

		}
	}

	/**
	 * 出荷予定表ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderSchedule(
			final List<RepSlipShippingSchHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME_SCHEDULE);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepSlipShippingSchHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 上位ロケーション
			setExcelDataString(sRow, sCol++, bean.getLocationCd());
			// 上位ロケーション
			setExcelDataString(sRow, sCol++, bean.getLocationName());

			sRow++;
		}
	}

	// 2019/12/06 AECS佐藤（出荷後在庫数追加）
	/**
	 * 出荷予定表詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailSchedule(
			final List<RepSlipShippingSchDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME_SCHEDULE);

		for (RepSlipShippingSchDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getItemCd());
			// 品目名称
			setExcelDataString(sRow, sCol++, bean.getItemName());
			// 荷姿
			setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			// 出荷数
			setExcelDataString(sRow, sCol++, bean.getSumOrderQty());
			// 在庫数
			setExcelDataString(sRow, sCol++, bean.getInventoryQty());
			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 上位ロケーション
			setExcelDataString(sRow, sCol++, bean.getLocationCd());
			// 出荷後在庫数　2019/12/06 AECS佐藤（追加）
			setExcelDataString(sRow, sCol++, bean.getAfterInventoryQty());
			
			sRow++;

		}
	}

	/**
	 * 荷札ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderNifuda(final List<RepSlipShippingNifuda> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME_NIFUDA);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepSlipShippingNifuda bean : headerList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());

			sRow++;
		}
	}

	/**
	 * 荷札詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailNifuda(final List<RepSlipShippingNifuda> detailList,
			final String organizationCd) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME_NIFUDA);

		Organization organizationBean = organizationDao
				.getEntity(organizationCd);

		for (RepSlipShippingNifuda bean : detailList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 納入先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());

			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			// ロケーションコード
			setExcelDataString(sRow, sCol++, bean.getLocationCd());
			// 納入先名
			setExcelDataString(sRow, sCol++, bean.getDeliveryName1());
			setExcelDataString(sRow, sCol++, bean.getDeliveryName2());
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddress());
			// 納入先電話
			setExcelDataString(sRow, sCol++, bean.getTelNo());
			// 郵便番号
			setExcelDataString(sRow, sCol++, bean.getZipcodeNo());
			// 納入先住所
			setExcelDataString(sRow, sCol++, bean.getAddress1());
			setExcelDataString(sRow, sCol++, bean.getAddress2());
			setExcelDataString(sRow, sCol++, bean.getAddress3());
			// 数量
			setExcelDataString(sRow, sCol++, bean.getOrderQty());
			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 部署住所１
			setExcelDataString(sRow, sCol++, organizationBean.getAddress1());
			// 部署住所２
			setExcelDataString(sRow, sCol++, organizationBean.getAddress2());
			// 部署住所３
			setExcelDataString(sRow, sCol++, organizationBean.getAddress3());
			// 担当部署名
			setExcelDataString(sRow, sCol++, "攝津製油株式会社"
					+ organizationBean.getOrganizationName());
			// 担当部署TEL番号
			setExcelDataString(sRow, sCol++, organizationBean.getTelNo());

			sRow++;

		}
	}

	/**
	 * ペリカン伝票ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderPerican(
			final List<RepSlipShippingPerican> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME_PERICAN);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepSlipShippingPerican bean : headerList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());

			sRow++;
		}
	}

	/**
	 * ペリカン伝票詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailPerican(final List<RepSlipShippingPerican> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME_PERICAN);

		for (RepSlipShippingPerican bean : detailList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 出荷番号
			setExcelDataString(sRow, sCol++, bean.getShippingNo());
			// 出荷指図日
			setExcelDataString(sRow, sCol++, bean.getShippingInstructDate());
			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 出荷担当者コード
			setExcelDataString(sRow, sCol++, bean.getTantoCd());
			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			// 行番号(受注)
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());
			// 取引先区分:不要
			setExcelDataString(sRow, sCol++, bean.getVenderDivision());
			// 取引先コード
			setExcelDataString(sRow, sCol++, bean.getVenderCd());
			// 納入先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			// 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
			setExcelDataString(sRow, sCol++, bean.getShippingStatus());
			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getItemCd());
			// 摘要:未使用
			setExcelDataString(sRow, sCol++, bean.getSummery());
			// 完納区分
			setExcelDataString(sRow, sCol++, bean.getDeliveryComp());
			// 出荷完了日
			setExcelDataString(sRow, sCol++, bean.getShippingResultDate());
			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());
			// 出荷伝票行番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipRowNo());
			// 出荷伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishComp());
			// 伝票発行日(必須一時解除)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishDate());
			// 出荷指図書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderComp());
			// 出荷指図書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderDate());
			// 出荷予定表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleComp());
			// 出荷予定表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleDate());
			// 荷札,ペリカン発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagComp());
			// 荷札,ペリカン発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagDate());
			// 出荷依頼書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestComp());
			// 出荷依頼書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestDate());
			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			// 希望納期
			setExcelDataString(sRow, sCol++, bean.getSuggestedDeliverlimit());
			// 運賃
			setExcelDataString(sRow, sCol++, bean.getCarryFare());
			// 積出ナンバー
			setExcelDataString(sRow, sCol++, bean.getSendingOffNumber());
			// 登録日時
			setExcelDataString(sRow, sCol++, bean.getInputDate());
			// 登録者ID
			setExcelDataString(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			setExcelDataString(sRow, sCol++, bean.getUpdateDate());
			// 更新者ID
			setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			// 受注量もしくは出荷指図量累計
			setExcelDataString(sRow, sCol++, bean.getOrderQty());
			// 品目名称
			setExcelDataString(sRow, sCol++, bean.getItemName());
			// 荷姿
			setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			// 届先名称1
			setExcelDataString(sRow, sCol++, bean.getDeliveryName1());
			// 届け先名称２
			setExcelDataString(sRow, sCol++, bean.getDeliveryName2());
			// 納入先宛先
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddress());
			// お届け先郵便番号
			setExcelDataString(sRow, sCol++, bean.getZipcodeNo());
			// 住所
			setExcelDataString(sRow, sCol++, bean.getAddress1());
			setExcelDataString(sRow, sCol++, bean.getAddress2());
			setExcelDataString(sRow, sCol++, bean.getAddress3());
			// お届け先電話番号
			setExcelDataString(sRow, sCol++, bean.getTelNo());
			// 納入予定日
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedDate());
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedDate());
			// 上位ロケーションコード
			setExcelDataString(sRow, sCol++, bean.getLocationCd());

			sRow++;

		}
	}

	/**
	 * 表示する得意先を返す
	 * @param tantoName 担当者名
	 */
	private Vender getVender(final String orderNo, final String deliverCd) {

		// 受注番号がない場合
		if (orderNo == null) {
			return null;
		}

		OrderHead ordBean = orderHeadDao.getEntity(orderNo);

		if (ordBean == null) {
			return null;
		}

		if (ordBean.getBalanceCd() == null) {
			return null;
		}

		// 帳合コードを帳合マスタから検索
		Balance balance = balanceDao.getEntity(ordBean.getBalanceCd());

		// 帳合マスタに帳合コードがない場合
		if (balance == null) {
			return null;
		}

		while (true) {

			// 得意先と納入先が同一の場合
			if (balance.getVenderCd().equals(deliverCd)) {

				// 上位帳合がある場合
				if (balance.getUpperBalanceCd() != null) {
					Balance upperBalance = balanceDao.getEntity(balance
							.getUpperBalanceCd());

					// 上位帳合がある場合上位帳合の得意先名称
					if (upperBalance != null) {
						// Vender vender = venderDao.getEntity(upperBalance
						// .getVenderCd(), "TS");
						// return vender;
						balance = upperBalance;
						// 上位帳合がない場合帳合コードの得意先名称
					} else {
						Vender vender = venderDao.getEntity(balance
								.getVenderCd(), "TS");
						return vender;
					}

				} else {
					Vender vender = venderDao.getEntity(balance.getVenderCd(),
						"TS");
					return vender;
				}
			} else {
				Vender vender = venderDao
						.getEntity(balance.getVenderCd(), "TS");
				return vender;
			}
		}
	}
	/**
	 * 納品伝票ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderDelivery(
			final List<RepSlipShippingDeliveryHeader> headerList,
			final String tantoName, final String organizationCd,
			final Timestamp currentDate,final ArrayList<String> shippingNo) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME_DELIVERY);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}
		

		for (RepSlipShippingDeliveryHeader bean : headerList) {
			String ctmOrderNo = "";
			
			RepSlipShippingDeliveryHeader cOrderNo = repSlipShippingDeliveryHeaderDao.getCstOrderNo(shippingNo, bean.getOrderNo());
			if(cOrderNo != null){
				ctmOrderNo = cOrderNo.getCstOrderNo();
			}
			
			sCol = TEMP_START_COL;
			setExcelDataString(sRow, sCol++, bean.getKey());
			// キー
			setExcelDataString(sRow, sCol++, bean.getShippingNo());
			// 出荷番号
			setExcelDataString(sRow, sCol++, bean.getCategoryDivision());
			// 分類コード
			setExcelDataString(sRow, sCol++, bean.getVenderCd());
			// 得意先コード
			setExcelDataString(sRow, sCol++, bean.getVenderName());
			// お得意先名
			setExcelDataString(sRow, sCol++, bean.getDeliveryNameAll());
			// お届先名
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddressAll());
			// お届先住所
			setExcelDataString(sRow, sCol++, bean.getDeliveryTelNo());
			// ＴＥＬ
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			// 届先コード
			setExcelDataString(sRow, sCol++, bean.getShippingInstructDate());
			// 出荷指図日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 出荷年月日
			setExcelDataString(sRow, sCol++, bean.getTantoCd());
			// 出荷担当者コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedDate());
			// 到着指定日
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedTime());
			// 到着時間指定
			setExcelDataString(sRow, sCol++, bean.getUpperLocationCd());
			// 出荷地
			setExcelDataString(sRow, sCol++, bean.getLocationName());
			// 出荷地名称
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryName1());
			// 運送会社1
			setExcelDataString(sRow, sCol++, bean.getCarryName2());
			// 運送会社2
			setExcelDataString(sRow, sCol++, bean.getReference());
			// 参照
			setExcelDataString(sRow, sCol++, bean.getCustomerOrderNo());
			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			// 発注番号
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());
			// 行番号(受注)
			setExcelDataString(sRow, sCol++, bean.getShippingStatus());
			// 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
			setExcelDataString(sRow, sCol++, bean.getItemCd());
			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryComp());
			// 完納区分
			setExcelDataString(sRow, sCol++, bean.getShippingResultDate());
			// 出荷完了日
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());
			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipRowNo());
			// 出荷伝票行番号
			setExcelDataString(sRow, sCol++, bean.getSlipPublishComp());
			// 出荷伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishDate());
			// 伝票発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderComp());
			// 出荷指図書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderDate());
			// 出荷指図書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleComp());
			// 出荷予定表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleDate());
			// 出荷予定表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagComp());
			// 荷札,ペリカン発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagDate());
			// 荷札,ペリカン発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestComp());
			// 出荷依頼書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestDate());
			// 出荷依頼書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingFareComp());
			// 運賃表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingFareDate());
			// 運賃表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingDelivelyComp());
			// 納品伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingDelivelyDate());
			// 納品伝票発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingNewTagComp());
			// 新荷札発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingNewTagDate());
			// 新荷札発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingPostalComp());
			// 新郵政発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingPostalDate());
			// 新郵政発行日
			setExcelDataString(sRow, sCol++, bean.getSuggestedDeliverlimit());
			// 希望納期
			setExcelDataString(sRow, sCol++, bean.getCarryFare());
			// 運賃
			setExcelDataString(sRow, sCol++, bean.getSendingOffNumber());
			// 積出ナンバー
			setExcelDataString(sRow, sCol++, bean.getInputDate());
			// 登録日時
			setExcelDataString(sRow, sCol++, bean.getInputorCd());
			// 登録者ID
			setExcelDataString(sRow, sCol++, bean.getUpdateDate());
			// 更新日時
			setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			// 登録者ID
			
			if(ctmOrderNo != null && bean.getPrintSummery() != null){
				setExcelDataString(sRow, sCol++, bean.getPrintSummery() + " " + ctmOrderNo);
			}else if(ctmOrderNo!= null ){
				setExcelDataString(sRow, sCol++, ctmOrderNo);
			}else{
				setExcelDataString(sRow, sCol++, bean.getPrintSummery());
			}
			// 備考(印字用)
			
			
			
			setExcelDataString(sRow, sCol++, bean.getDeliverySlipSummery());
			// 納品書備考 
			setExcelDataString(sRow, sCol++, bean.getOrderSummery());
			// 受注時備考
			setExcelDataString(sRow, sCol++,  bean.getCarryBarcode());
			// 運送会社バーコード
			setExcelDataString(sRow, sCol++, bean.getCarryBarcode());
			// 運送会社バーコード文字列
			
			sRow++;
		}
	}

	/**
	 * 納品伝票詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailDelivery(
			final List<RepSlipShippingDeliveryDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME_DELIVERY);

		for (RepSlipShippingDeliveryDetail bean : detailList) {
			sCol = TEMP_START_COL;
			
			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
						
			// 出荷番号
			setExcelDataString(sRow, sCol++, bean.getShippingNo());
			
			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getItemCd());

			// JANコード
			setExcelDataString(sRow, sCol++, bean.getJanCd());
			

			// 危険物品番の場合、★をつける。
			String itemName = bean.getItemName();
			if(bean.getItemDivision() != null && bean.getItemDivision().equals("2") ){
				itemName = "★" + itemName;
			}
			
			// 品目名称
			setExcelDataString(sRow, sCol++,itemName);

			// 荷姿
			setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());

			// 増付
			setExcelDataString(sRow, sCol++, bean.getMatss());

			// 売上数量
			setExcelDataString(sRow, sCol++, bean.getSalesQuantity());

			// 増付、納品数量（バラ）
			setExcelDataString(sRow, sCol++, bean.getMatssIns());

			// 納品数量（バラ）
			setExcelDataString(sRow, sCol++, bean.getQtyIns());

			// 備考
			setExcelDataString(sRow, sCol++, bean.getRemark());

			// 総重量
			setExcelDataString(sRow, sCol++, bean.getAllUpWeight());

			// 出荷指図日
			setExcelDataString(sRow, sCol++, bean.getShippingInstructDate());

			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());

			// 出荷担当者コード
			setExcelDataString(sRow, sCol++, bean.getTantoCd());

			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());

			// 行番号(受注)
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());

			// 取引先区分(未使用)
			setExcelDataString(sRow, sCol++, bean.getVenderDivision());

			// 取引先コード
			setExcelDataString(sRow, sCol++, bean.getVenderCd());

			// 納入先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());

			// 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
			setExcelDataString(sRow, sCol++, bean.getShippingStatus());

			// 摘要(未使用)
			setExcelDataString(sRow, sCol++, bean.getSummery());

			// 完納区分
			setExcelDataString(sRow, sCol++, bean.getDeliveryComp());

			// 出荷完了日
			setExcelDataString(sRow, sCol++, bean.getShippingResultDate());

			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());

			// 出荷伝票行番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipRowNo());

			// 出荷伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipPublishComp());

			// 伝票発行日
			setExcelDataString(sRow, sCol++, bean.getSlipPublishDate());

			// 出荷指図書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderComp());

			// 出荷指図書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingOrderDate());

			// 出荷予定表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleComp());

			// 出荷予定表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingScheduleDate());

			// 荷札,ペリカン発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagComp());

			// 荷札,ペリカン発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingTagDate());

			// 出荷依頼書発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestComp());

			// 出荷依頼書発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingRequestDate());

			// 運賃表発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingFareComp());

			// 運賃表発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingFareDate());

			// 納品伝票発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingDelivelyComp());

			// 納品伝票発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingDelivelyDate());

			// 新荷札発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingNewTagComp());

			// 新荷札発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingNewTagDate());

			// 新郵政発行済区分(0:未発行 1:発行済)
			setExcelDataString(sRow, sCol++, bean.getSlipShippingPostalComp());

			// 新郵政発行日
			setExcelDataString(sRow, sCol++, bean.getSlipShippingPostalDate());

			// 運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());

			// 希望納期
			setExcelDataString(sRow, sCol++, bean.getSuggestedDeliverlimit());

			// 運賃
			setExcelDataString(sRow, sCol++, bean.getCarryFare());

			// 積出ナンバー
			setExcelDataString(sRow, sCol++, bean.getSendingOffNumber());

			// 登録日時
			setExcelDataString(sRow, sCol++, bean.getInputDate());

			// 登録者ID
			setExcelDataString(sRow, sCol++, bean.getInputorCd());

			// 更新日時
			setExcelDataString(sRow, sCol++, bean.getUpdateDate());

			// 更新者ID
			setExcelDataString(sRow, sCol++, bean.getUpdatorCd());

			sRow++;
		}
	}
	
	/**
	 * 新郵政詳細をセット(CSV)
	 * @param tantoName 担当者名
	 */
	public List<String> setDetailPostal(
			final List<RepSlipShippingPostalDetail> detailList) {
		
		List<String> csvDataList = new ArrayList<String>();
		String strData = null;
		
		for (RepSlipShippingPostalDetail bean : detailList) {
			
		int num = 	Integer.valueOf(bean.getShippingNum()); //指図数量は10まで1行で表示
			for (int i = 0; num > 0 || i < (num / 10) + 1 ;i++){
			// 商品 1
			strData = addComma(bean.getItemCategory());

			// 着払/代引 2
			strData += addComma(bean.getPaymentMethod());

			// ゴルフ/スキー/空港 3
			strData += addComma(bean.getGolfSkyAirport());

			// 往復 4
			strData += addComma(bean.getRoundTrip());

			// 書留/特定記録 5
			strData += addComma(bean.getShippingOption());

			// 配達方法 6
			strData += addComma(bean.getDeliveryMethod());

			// 作成数 7
//			strData += addComma(bean.getShippingNum());
			if(num - 10 > 0){
				strData += addComma("10");
			}
			else{
				strData += addComma(String.valueOf(num));		
			}

			// お届け先のお名前  8
			strData += addComma(bean.getDeliveryName());

			// お届け先の敬称 9
			strData += addComma(bean.getTitle());

			// お届け先のお名前（カナ） 10
			strData += addComma(bean.getDeliveryNameKana());

			// お届け先の郵便番号 11
			strData += addComma(bean.getDelZipcodeNo());

			// お届け先の都道府県 12
			strData += addComma(bean.getPrefectures());

			// お届け先の市区町村郡 13
			strData += addComma(bean.getDeliveryAddress1());

			// お届け先の丁目番地号 14
			strData += addComma(bean.getDeliveryAddress2());

			// お届け先の建物名・部屋番号など 15
			strData += addComma(bean.getDeliveryAddress3());

			// お届け先の電話番号 16
			strData += addComma(bean.getDeliveryTelNo());

			// お届け先の法人名 17
			strData += addComma(bean.getDeliveryName1());

			// お届け先の部署名 18
			strData += addComma(bean.getDeliveryName2());

			// お届け先のメールアドレス 19
			strData += addComma(bean.getDelMail());

			// 空港略称 20
			strData += addComma(bean.getAirport());

			// 空港コード 21
			strData += addComma(bean.getAirportCd());

			// 受取人様のお名前 22
			strData += addComma(bean.getReceiverName());

			// ご依頼主のお名前 23
			strData += addComma(bean.getClientName());

			// ご依頼主の敬称 24
			strData += addComma(bean.getClientTitle());

			// ご依頼主のお名前（カナ） 25
			strData += addComma(bean.getClientNameKana());

//			// 
//			strData += addComma(bean.getPostalClientCd());

			// ご依頼主の郵便番号 26
			strData += addComma(bean.getClientZipcodeNo());

			// ご依頼主の都道府県 27
			strData += addComma(bean.getClientAddress1());

			// ご依頼主の市区町村郡 28
			strData += addComma(bean.getClientAddress2());

			// ご依頼主の丁目番地号 29
			strData += addComma(bean.getClientAddress3());

			// ご依頼主の建物名・部屋番号など 30
			strData += addComma(bean.getClientAddress4());

			// ご依頼主の電話番号 31
			strData += addComma(bean.getClientTelNo());

			// ご依頼主の法人名 32
			strData += addComma(bean.getPostalClientName());

			// ご依頼主の部署名33
			strData += addComma(bean.getClientSection());

			// ご依頼主のメールアドレス 34
			strData += addComma(bean.getClientMail());

			// 品名 35
			strData += addComma(bean.getItemName());

			// 品名番号 36
			strData += addComma(bean.getStyleOfPacking());

			// 個数 37
			strData += addComma(bean.getShippingInstruction());

			// 発送予定日 38
			strData += addComma(bean.getScheduledShippingDate());

			// 発送予定時間帯 32
			strData += addComma(bean.getScheduledShippingTime());

			// セキュリティ 33
			strData += addComma(bean.getSecurity());

			// 重量 34
			strData += addComma(bean.getUpWeight());

			// 損害要償額
			strData += addComma(bean.getDamageAmount());

			// 保冷
			strData += addComma(bean.getCooling());

			// 取扱上の注意　こわれもの
			strData += addComma(bean.getWarningBreak());

			// 
			strData += addComma(bean.getWarningRaw());

			// 取扱上の注意　ビン類
			strData += addComma(bean.getBin());

			// 取扱上の注意　逆さま厳禁
			strData += addComma(bean.getUpsideDown());

			// 取扱上の注意　下積み厳禁
			strData += addComma(bean.getWarningLower());

			// 25kg超荷物
			strData += addComma(bean.getHeavy());

			// 差出予定日
			strData += addComma(bean.getShippingPlanDate());

			// 差出予定時間帯
			strData += addComma(bean.getShippingPlanTime());

			// 配達希望日
			strData += addComma(bean.getDeliveryExpectedDate());

			// 配達希望時間帯
			strData += addComma(bean.getDeliveryExpectedTime());

			// クラブ本数
			strData += addComma(bean.getClubNum());

			// ご使用日(プレー日)
			strData += addComma(bean.getPlayDate());

			// ご使用時間
			strData += addComma(bean.getUseTime());

			// 搭乗日
			strData += addComma(bean.getBoardingDate());

			// 搭乗時間
			strData += addComma(bean.getBoardingTime());

			// 搭乗便名
			strData += addComma(bean.getBoardingName());

			// 復路発送予定日
			strData += addComma(bean.getReturnPathScheduledDate());

			// お支払方法
			strData += addComma(bean.getPaymentMethod2());

			// 摘要/記事
			strData += addComma(bean.getOrderNo());

			// 
			strData += addComma(bean.getShippngSize());

			// 差出方法
			strData += addComma(bean.getShippingMethod());

			// 割引
			strData += addComma(bean.getDiscount());

			// 代金引換金額
			strData += addComma(bean.getCodAmount());

			// うち消費税等
			strData += addComma(bean.getTax());

			// 配達予定日通知 (お届け先)
			strData += addComma(bean.getDeliveryDateInfo());

			// 配達完了通知 (お届け先)
			strData += addComma(bean.getDelCompInfoDel());

			// 不在持戻り通知 (お届け先)
			strData += addComma(bean.getAbsenceInfo());

			// 郵便局留通知 (お届け先)
			strData += addComma(bean.getPostOfficeInfo());

			// 配達完了通知 (依頼主)
			strData += addComma(bean.getDelCompInfoCli());

			csvDataList.add(strData);
			
			num = num - 10 ;
			i++;
			}
		}
		return csvDataList;
	}
	
	/**
	 * 新荷札ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderNewNifuda(final List<RepSlipShippingNewTagHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME_NEW_NIFUDA);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepSlipShippingNewTagHeader bean : headerList) {
			sCol = TEMP_START_COL;

			//キー
			setExcelDataString(sRow, sCol++, bean.getKey());
			
			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());

			// 納入先住所
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddressAll());

			// 納入先名称
			setExcelDataString(sRow, sCol++, bean.getDeliveryName1());

			// 納入先宛先
			setExcelDataString(sRow, sCol++, bean.getDeliveryName2());

			// 納入先電話番号
			setExcelDataString(sRow, sCol++, bean.getDeliveryTelNo());

			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());

			// 納入予定日
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedDate());

			// 納入時刻
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedTime());

			// 発送元住所
			setExcelDataString(sRow, sCol++, bean.getShipFromAddress());

			// 発送元宛先
			setExcelDataString(sRow, sCol++, bean.getShipFromName());

			// 発送元電話番号
			setExcelDataString(sRow, sCol++, bean.getShipFromTelNo());

			// 納入先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			
			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippingSlipNo());
			
			//運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			
			//帳票出力順
			setExcelDataString(sRow, sCol++, bean.getRepotrOutputNum());

			sRow++;
		}
	}

	/**
	 * 新荷札詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailNewNifuda(final List<RepSlipShippingNewTagDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME_NEW_NIFUDA);

		for (RepSlipShippingNewTagDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// キー
			setExcelDataString(sRow, sCol++, bean.getKey());
			// 受注番号
			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			// 品目コード
			setExcelDataString(sRow, sCol++, bean.getItemCd());
			//品目名称
			setExcelDataString(sRow, sCol++, bean.getItemName());
			// 荷姿
			setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			// 品目コード/品目名称/荷姿
			setExcelDataString(sRow, sCol++, bean.getCdNameStyle());
			// 受注数量
			setExcelDataString(sRow, sCol++, bean.getOrderQty());
			// 出荷伝票番号
			setExcelDataString(sRow, sCol++, bean.getShippinfSlipNo());
			// 出荷予定日
			setExcelDataString(sRow, sCol++, bean.getScheduledShippingDate());
			// 行番号(受注)
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());
			// 納入先コード
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			
			//運送会社コード
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			
			//帳票出力順
			setExcelDataString(sRow, sCol++, bean.getRepotrOutputNum());
			sRow++;

		}
	}

	/**
	 * カンマ追加(文字)
	 * @param data 元データ
	 * @return String
	 */
	public String addComma(final String data) {
		String s = ",";

		if (!StringUtils.isEmpty(data)) {
			s = data + s;
		}

		return s;
	}

	/**
	 * 
	 * 出荷バーコード設定 付番対象範囲をグループ化してデータ取得後に決定する必要があったため、Java上で実施。
	 * @param headerList
	 * @return 新規付番情報 HashMap<String, String>
	 * @throws LogicExceptionEx
	 */
	private HashMap<String, String> setCarryBC( final List<RepSlipShippingDeliveryHeader> headerList )
			throws LogicExceptionEx {

		HashMap<String, String> bcHashMap = new HashMap<String, String>();
		
		// 出力対象となっており、バーコードが過去に設定されていない場合、新規バーコードの付番処理を行う。
		// この時、既に同一のキーでバーコードが付番されていれば、それを採用する。
		for (RepSlipShippingDeliveryHeader bean : headerList) {
			
			int bcPublishDivision = 0;
			if( bean.getBcPublishDivision() != null )
			{
				bcPublishDivision = bean.getBcPublishDivision().intValue();
			}
			
			String carryBC = "";
			
			if( bean.getCarryBarcode() != null )
			{
				carryBC = bean.getCarryBarcode().trim();
			}

			
			if( bcPublishDivision == 1 && carryBC.length() == 0  ){
				if( bcHashMap.containsKey(bean.getKey())){
					bean.setCarryBarcode( bcHashMap.get(bean.getKey()));
				}else
				{
					// 連番の取得
					ProGetCarryBcNextValDto dto = new ProGetCarryBcNextValDto();
					dto.setPCarryCd(bean.getCarryCd());
					
					this.procedureCallDao.proGetCarryBcNextVal(dto);
					
					int retValue = dto.getPNumRet().intValue();
					if( retValue != 0) {
						throw new LogicExceptionEx("運送会社用バーコード付番処理で異常が発生しました。番号が取得できません。運送会社コード:" + bean.getCarryCd());
					}
					
					// バーコード整形
					String bcValue = dto.getpNextVal().toString();
					bcValue = bean.getBcHeader() + String.format("%" + bean.getBcNumberOfDigit().toString() + "s", bcValue).replace(" ", "0") + bean.getBcFooter();
					bcValue = bcValue.replace(" ", "");
					
					// チェックディジット計算
					CarryCheckDigitCondition condition = new CarryCheckDigitCondition();
					condition.setBase_str(bcValue);
					condition.setBcCheckdigitStr( bean.getBcNumCheckDigitStart() );
					condition.setBcCheckdigitEnd( bean.getBcNumCheckDigitEnd() );
					List< CarryCheckDigit > checkDigitList = this.carryCheckDigitDao.get7DR(condition);
					
					// チェックデジットが取得できた場合、設定
					if( checkDigitList.size() > 0 && checkDigitList.get(0).getBarcode().trim().length() > 0 ) {
						bcValue = checkDigitList.get(0).getBarcode().trim();
					}
					
					bean.setCarryBarcode(bcValue);
				
					bcHashMap.put(bean.getKey(), bcValue);
				}
			}
		}
		
		return bcHashMap;

	}

	/**
	 * {@inheritDoc}
	 * 運送会社連携ファイル
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public FileDownloadInfo createCarryFile(
			final String carryCd ,
			final List<String> shippingNoList, final String pUserCd,
			final String postalClientCd, final Timestamp currentDate,
			final String ipAddress) throws IOException, SQLException ,SlipShippingLogicException {

		/* パラメータチェック */
		if (StringUtils.isEmpty(pUserCd)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		String pShippingNoList = "";
		// 引数の作成
		for(String shippingNo : shippingNoList )
		{
			if( pShippingNoList.trim().length() > 0)
			{
				pShippingNoList = pShippingNoList + ",";
			}
			pShippingNoList = pShippingNoList + "'" + shippingNo + "'";
		}
		
		MakeCarryFileDto dto = new MakeCarryFileDto();
        
		dto.setPCarryCd(carryCd);
		dto.setpUserCd(pUserCd);
		dto.setpShippingNoList(pShippingNoList);
		
		this.pakCarryFileDao.makeCarryFile(dto);
				
		if(  !dto.getPNumRet().toString().equals("0") ){
			throw new SQLException( dto.getPStrErrorMsg() );
		}
		
		List<CarryFile> carryFileList = this.carryFileDao.getCarryFile(pUserCd);

		if( carryFileList.size() == 0 )
		{
			// データがない場合
			SlipShippingLogicException ex = new SlipShippingLogicException();
			ex.setKey("errors.slipshipping.nodata.carryfile");
			throw ex;
		}
		
//		/* CSVデータ出力 */
		PrintWriter out = null;
		
		Carry carry = carryDao.getEntity(carryCd);
		String carryName = "";
		if(carry != null){
			if(carry.getCarryName2() != null && !carry.getCarryName2().equals("")){
				carryName = carry.getCarryName1() + carry.getCarryName2();
			}else{
				carryName = carry.getCarryName1();
			}
		}else{
			carryName = carryCd;
		}
		
		/* 一回ファイルに出力するためのパス */
		File tempDir = SystemUtils.getJavaIoTmpDir();
		// 2021/4/28 セッツ様要望でファイル名の運送会社コードを運送会社名に変更
//		File csv = new File(tempDir, "運送会社連携ファイル_" + carryCd + "_" + AecDateUtils.dateFormat(AecDateUtils.getCurrentTimestamp(), "yyyyMMdd") + ".csv");
		File csv = new File(tempDir, "運送会社連携ファイル_" + carryName + "_" + AecDateUtils.dateFormat(AecDateUtils.getCurrentTimestamp(), "yyyyMMdd") + ".csv");
		try {
			/* CSVデータファイル */
			out = new PrintWriter(new BufferedWriter(new FileWriter(csv)));

			for (CarryFile carryFile : carryFileList) {
				out.println(carryFile.getCsvData());
			}
		} catch (IOException e) {
			throw new IOException();
		}finally{
			if(out != null){
				out.flush();
				out.close();
				out = null;
			}
		}
		
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(csv.getName(), csv.getPath());
	}
	
	/**
	 * 出荷伝票ヘッダ検索用Dao
	 * @param repSlipShippingOrderHeadDao 出荷伝票ヘッダ検索用Dao
	 */
	public void setRepSlipShippingOrderHeadDao(
			final RepSlipShippingOrderHeadDao repSlipShippingOrderHeadDao) {
		this.repSlipShippingOrderHeadDao = repSlipShippingOrderHeadDao;
	}

	/**
	 * 出荷伝票明細検索用Dao
	 * @param repSlipShippingOrderDetailDao 出荷伝票明細検索用Dao
	 */
	public void setRepSlipShippingOrderDetailDao(
			final RepSlipShippingOrderDetailDao repSlipShippingOrderDetailDao) {
		this.repSlipShippingOrderDetailDao = repSlipShippingOrderDetailDao;
	}

	/**
	 * 出荷依頼書ヘッダ検索用Dao
	 * @param repSlipShippingReqHeaderDao 出荷依頼書ヘッダ検索用Dao
	 */
	public void setRepSlipShippingReqHeaderDao(
			final RepSlipShippingReqHeaderDao repSlipShippingReqHeaderDao) {
		this.repSlipShippingReqHeaderDao = repSlipShippingReqHeaderDao;
	}

	/**
	 * 出荷依頼書明細検索用Dao
	 * @param repSlipShippingReqDetailDao 出荷依頼書明細検索用Dao
	 */
	public void setRepSlipShippingReqDetailDao(
			final RepSlipShippingReqDetailDao repSlipShippingReqDetailDao) {
		this.repSlipShippingReqDetailDao = repSlipShippingReqDetailDao;
	}

	/**
	 * 運賃表ヘッダ検索用Dao
	 * @param repSlipShippingFareHeaderDao 運賃表ヘッダ検索用Dao
	 */ 

	public void setRepSlipShippingFareHeaderDao(
			RepSlipShippingFareHeaderDao repSlipShippingFareHeaderDao) {
		this.repSlipShippingFareHeaderDao = repSlipShippingFareHeaderDao;
	}
	

	/**
	 * 運賃表明細検索用Dao
	 * @param repSlipShippingDeliveryDetailDao 納品伝票明細検索用Dao
	 */ 

	public void setRepSlipShippingFareDetailDao(
			RepSlipShippingFareDetailDao repSlipShippingFareDetailDao) {
		this.repSlipShippingFareDetailDao = repSlipShippingFareDetailDao;
	}
	
	/**
	 * 出荷指図書ヘッダ検索用Dao
	 * @param repSlipShippingDirHeaderDao 出荷指図書ヘッダ検索用Dao
	 */
	public void setRepSlipShippingDirHeaderDao(
			final RepSlipShippingDirHeaderDao repSlipShippingDirHeaderDao) {
		this.repSlipShippingDirHeaderDao = repSlipShippingDirHeaderDao;
	}

	/**
	 * 出荷指図書明細検索用Dao
	 * @param repSlipShippingDirDetailDao 出荷指図書明細検索用Dao
	 */
	public void setRepSlipShippingDirDetailDao(
			final RepSlipShippingDirDetailDao repSlipShippingDirDetailDao) {
		this.repSlipShippingDirDetailDao = repSlipShippingDirDetailDao;
	}

	/**
	 * 出荷予定表ヘッダ検索用Dao
	 * @param repSlipShippingSchHeaderDao 出荷予定表ヘッダ検索用Dao
	 */
	public void setRepSlipShippingSchHeaderDao(
			final RepSlipShippingSchHeaderDao repSlipShippingSchHeaderDao) {
		this.repSlipShippingSchHeaderDao = repSlipShippingSchHeaderDao;
	}

	/**
	 * 出荷予定表明細検索用Dao
	 * @param repSlipShippingSchDetailDao 出荷予定表明細検索用Dao
	 */
	public void setRepSlipShippingSchDetailDao(
			final RepSlipShippingSchDetailDao repSlipShippingSchDetailDao) {
		this.repSlipShippingSchDetailDao = repSlipShippingSchDetailDao;
	}

	/**
	 * 荷札検索用Dao
	 * @param repSlipShippingNifudaDao 荷札検索用Dao
	 */
	public void setRepSlipShippingNifudaDao(
			final RepSlipShippingNifudaDao repSlipShippingNifudaDao) {
		this.repSlipShippingNifudaDao = repSlipShippingNifudaDao;
	}

	/**
	 * ペリカン伝票検索用Dao
	 * @param repSlipShippingPericanDao ペリカン伝票明細検索用Dao
	 */
	public void setRepSlipShippingPericanDao(
			final RepSlipShippingPericanDao repSlipShippingPericanDao) {
		this.repSlipShippingPericanDao = repSlipShippingPericanDao;
	}

	/**
	 * 納品伝票検索用Dao
	 * @param repSlipShippingDeliveryHeaderDao 納品伝票ヘッダ検索用Dao
	 */
	public void setRepSlipShippingDeliveryHeaderDao(
			RepSlipShippingDeliveryHeaderDao repSlipShippingDeliveryHeaderDao) {
		this.repSlipShippingDeliveryHeaderDao = repSlipShippingDeliveryHeaderDao;
	}

	/**
	 * 納品伝票検索用Dao
	 * @param repSlipShippingDeliveryDetailDao 納品伝票明細検索用Dao
	 */
	public void setRepSlipShippingDeliveryDetailDao(
			RepSlipShippingDeliveryDetailDao repSlipShippingDeliveryDetailDao) {
		this.repSlipShippingDeliveryDetailDao = repSlipShippingDeliveryDetailDao;
	}
	/**
	 * repSlipShippingPostalDetailDaoを設定します。
	 * @param repSlipShippingPostalDetailDao 新郵政検索用Dao
	 */
	public void setRepSlipShippingPostalDetailDao(
			RepSlipShippingPostalDetailDao repSlipShippingPostalDetailDao) {
		this.repSlipShippingPostalDetailDao = repSlipShippingPostalDetailDao;
	}
	
	/**
	 * repSlipShippingNewTagHeaderDaoを設定します。
	 * @param repSlipShippingNewTagHeaderDao 新荷札検索用Dao
	 */
	public void setRepSlipShippingNewTagHeaderDao(
			RepSlipShippingNewTagHeaderDao repSlipShippingNewTagHeaderDao) {
		this.repSlipShippingNewTagHeaderDao = repSlipShippingNewTagHeaderDao;
	}

	/**
	 * repSlipShippingNewTagDetailDaoを設定します。
	 * @param repSlipShippingNewTagDetailDao 新荷札検索用Dao
	 */
	public void setRepSlipShippingNewTagDetailDao(
			RepSlipShippingNewTagDetailDao repSlipShippingNewTagDetailDao) {
		this.repSlipShippingNewTagDetailDao = repSlipShippingNewTagDetailDao;
	}
	/**
	 * 部署取得用Daoを設定します。
	 * @param organizationDao 部署取得用Dao
	 */
	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/**
	 * 請求先用Daoを設定します。
	 * @param venderDao 請求先用Dao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * 帳合マスタ用Daoを設定します。
	 * @param balanceDao 帳合マスタ用Dao
	 */
	public void setBalanceDao(final BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	/**
	 * 受注ヘッダ用Daoを設定します。
	 * @param orderHeadDao 受注ヘッダ用Dao
	 */
	public void setOrderHeadDao(final OrderHeadDao orderHeadDao) {
		this.orderHeadDao = orderHeadDao;
	}

	/**
	 * ログイン用Daoを設定します。
	 * @param loginDao ログイン用Dao
	 */
	public void setLoginDao(final LoginDao loginDao) {
		this.loginDao = loginDao;
	}


	/**
	 * slipShippingListLogicを設定します。
	 * @param slipShippingListLogic slipShippingListLogic
	 */
	public void setSlipShippingListLogic(SlipShippingListLogic slipShippingListLogic) {
		this.slipShippingListLogic = slipShippingListLogic;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}
	

	/**
	 * pakCarryFileDaoを設定します。
	 * @param pakCarryFileDao pakCarryFileDao
	 */
	public void setPakCarryFileDao(PakCarryFileDao pakCarryFileDao) {
		this.pakCarryFileDao = pakCarryFileDao;
	}


	/**
	 * ｃarryFileDaoを設定します。
	 * @param ｃarryFileDao ｃarryFileDao
	 */
	public void setＣarryFileDao(CarryFileDao carryFileDao) {
		this.carryFileDao = carryFileDao;
	}

	/**
	 * carryCheckDigitDaoを設定します。
	 * @param carryCheckDigitDao carryCheckDigitDao
	 */
	public void setCarryCheckDigitDao(CarryCheckDigitDao carryCheckDigitDao) {
		this.carryCheckDigitDao = carryCheckDigitDao;
	}

	/**
	 * carryDaoを設定します。
	 * @param carryDao carryDao
	 */
	public void setCarryDao(CarryDao carryDao) {
		this.carryDao = carryDao;
	}

}
