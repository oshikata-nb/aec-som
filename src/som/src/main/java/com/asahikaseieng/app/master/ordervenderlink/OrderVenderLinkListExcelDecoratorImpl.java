/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.ordervenderlink;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.orderrecieve.ordervendermaster.OrderVenderMaster;
import com.asahikaseieng.dao.entity.orderrecieve.ordervendermaster.OrderVenderMasterDao;
import com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport.OrderVenderLinkListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.受注データ得意先連携マスタ一覧
 * @author
 */
public class OrderVenderLinkListExcelDecoratorImpl implements OrderVenderLinkListExcelDecorator {

	/** 得意先グループ記入開始行位置*/
	protected static final short GROUP_START_ROW = 1;
	
	/** 得意先グループ記入開始列位置*/
	protected static final short GROUP_START_COL = 2;
	
	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 7;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 1;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private OrderVenderLinkListLogic orderVenderLinkListLogic;
	
	private OrderVenderMasterDao orderVenderMasterDao;

	/**
	 * コンストラクタ
	 */
	public OrderVenderLinkListExcelDecoratorImpl() {
		super();
	}

	/**
	 * ファイルダウンロード時の処理
	 */
	public FileDownloadInfo createReport(final String venderGroupCd) {
		
		OrderVenderMaster entity = orderVenderMasterDao.getEntity(venderGroupCd, null);
		String venderName = "";
		if(entity != null){
			venderName = entity.getVenderGroupName();
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("ordervenderlinklist");
		if(venderGroupCd != null){
			/* 明細をセット */
			setDetail(venderGroupCd);
		}
		else{
			//アップロード用Excelファイル設定
			builder.setSheet("order_vender_delivery");
			builder.setExcelDataString(GROUP_START_ROW, GROUP_START_COL, "99999");
			builder.setSheet("order_vender_item");
			builder.setExcelDataString(GROUP_START_ROW, GROUP_START_COL, "99999");
			return new FileDownloadInfo(Constants.RB_REPORT_PROPERTIES.getString("excel.ordervenderlinklist.download.filename") + "_" +entity.getVenderGroupName()+ "_"
										+ Constants.RB_REPORT_PROPERTIES.getString("excel.extension"), builder.create());
		}
		
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileNameAddName(venderName), builder.create());
	}

	/**
	 * ファイルダウンロード時に明細を設定
	 * @param condition 検索条件
	 */
	private void setDetail(final String venderGroupCd) {
		/* 得意先グループ入力用位置セット*/
		short rowNum = GROUP_START_ROW;
		short colNum = GROUP_START_COL;
		
		/* 得意先グループ名取得用エンティティ*/
		OrderVenderMaster entity = orderVenderMasterDao.getEntity(venderGroupCd, null);
		
		/* 帳票(納入先)用情報取得*/
		List<OrderVenderLinkListForReport> deliveryList = orderVenderLinkListLogic
				.getDeliveryListForReport(venderGroupCd);
		
		/* 帳票(品目)用情報取得*/
		List<OrderVenderLinkListForReport> itemList = orderVenderLinkListLogic
				.getItemListForReport(venderGroupCd);
		
		/* 納入先連携情報シートをセット */
		builder.setSheet("order_vender_delivery");

		/* 得意先グループ情報入力*/
		builder.setExcelDataString(rowNum++, colNum, venderGroupCd);
		builder.setExcelDataString(rowNum, colNum, entity.getVenderGroupName());
		
		/* リスト記入開始前に位置セット*/
		rowNum = TEMP_START_ROW;
		
		/* リスト部 */
		if(deliveryList != null){
			for (OrderVenderLinkListForReport bean : deliveryList) {
				colNum = TEMP_START_COL;
				
				//データセット
				builder.setExcelDataString(rowNum, colNum++, bean.getSomDeliveryCd());
				builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryName());
				builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryFullName());
				builder.setExcelDataString(rowNum, colNum++, bean.getAddress());
				builder.setExcelDataString(rowNum, colNum++, bean.getTelNo());
				builder.setExcelDataString(rowNum, colNum++, bean.getZipcodeNo());
				builder.setExcelDataString(rowNum, colNum++, bean.getCarryName());
				builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd1());
				builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd2());
				builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd3());
				
				rowNum++;
			}
		}
		builder.setExcelDataString(rowNum, TEMP_START_COL, "EOF");
		
		
		/* 納入先連携情報入力終*/
		
		
		/* 得意先グループ入力用位置セット*/
		rowNum = GROUP_START_ROW;
		colNum = GROUP_START_COL;
		
		/* 品目連携情報シートをセット*/
		builder.setSheet("order_vender_item");
		
		/* 得意先グループ情報入力*/
		builder.setExcelDataString(rowNum++, colNum, venderGroupCd);
		builder.setExcelDataString(rowNum, colNum, entity.getVenderGroupName());
		
		/* リスト記入開始前に位置セット*/
		rowNum = TEMP_START_ROW;
		
		/* リスト部 */
		if(itemList != null){
			for (OrderVenderLinkListForReport bean : itemList) {
				colNum = TEMP_START_COL;
				
				//データセット
				builder.setExcelDataString(rowNum, colNum++, bean.getSomItemCd());
				builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
				builder.setExcelDataString(rowNum, colNum++, bean.getStyleOfPacking());
				builder.setExcelDataString(rowNum, colNum++, bean.getOtherCompanyCd1());
				builder.setExcelDataString(rowNum, colNum++, bean.getUnitOfStockControl());
				builder.setExcelDataString(rowNum, colNum++, bean.getAllUpWeight());
				builder.setExcelDataString(rowNum, colNum++, bean.getNumberOfInsertions());
				builder.setExcelDataString(rowNum, colNum++, bean.getItemCd1());
				builder.setExcelDataString(rowNum, colNum++, bean.getItemCd2());
				builder.setExcelDataString(rowNum, colNum++, bean.getItemCd3());
	
				rowNum++;
			}
		}
		builder.setExcelDataString(rowNum, TEMP_START_COL, "EOF");
		/* 品目連携情報入力終*/
	}

	/**
	 * orderVenderLinkListLogicを設定します。
	 * @param orderVenderLinkListLogic orderVenderLinkListLogic
	 */
	public void setOrderVenderLinkListLogic(final OrderVenderLinkListLogic orderVenderLinkListLogic) {
		this.orderVenderLinkListLogic = orderVenderLinkListLogic;
	}
	
	/**
	 * orderVenderLinkDaoを設定します。
	 * @param orderVenderLinkListLogic orderVenderLinkListLogic
	 */
	public void setOrderVenderLinkDao(OrderVenderMasterDao orderVenderMasterDao) {
		this.orderVenderMasterDao = orderVenderMasterDao;
	}
}
