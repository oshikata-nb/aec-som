/*
 * Created on 2021/03/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.extension.jdbc.SqlLogRegistryLocator;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.common.stockinout.StockinoutForOrder;
import com.asahikaseieng.app.common.stockinout.StockinoutForPurchase;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.app.order.OrderLogicException;
import com.asahikaseieng.app.unitprice.GetValidUnitpriceLogic;
import com.asahikaseieng.dao.entity.aspproduction.AspProduction;
import com.asahikaseieng.dao.entity.aspproduction.AspProductionDao;
import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetail;
import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetailDao;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHeadDao;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueue;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.entity.master.carry.Carry;
import com.asahikaseieng.dao.entity.master.carry.CarryDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueue;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.orderdetail.OrderDetail;
import com.asahikaseieng.dao.entity.orderdetail.OrderDetailDao;
import com.asahikaseieng.dao.entity.orderhead.OrderHead;
import com.asahikaseieng.dao.entity.orderhead.OrderHeadDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitprice;
import com.asahikaseieng.dao.nonentity.orderdetailaspproduction.OrderDetailAspProduction;
import com.asahikaseieng.dao.nonentity.orderdetailaspproduction.OrderDetailAspProductionDao;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailListDao;
import com.asahikaseieng.dao.nonentity.orderdetailpurchasesubcontract.OrderDetailPurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.orderdetailpurchasesubcontract.OrderDetailPurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.orderdetailsalesterms.OrderDetailSalesTerms;
import com.asahikaseieng.dao.nonentity.orderdetailsalesterms.OrderDetailSalesTermsDao;
import com.asahikaseieng.dao.nonentity.orderdetailunitprice.OrderDetailUnitprice;
import com.asahikaseieng.dao.nonentity.orderdetailunitprice.OrderDetailUnitpriceDao;
import com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailList;
import com.asahikaseieng.dao.nonentity.orderimportlog.OrderImportLog;
import com.asahikaseieng.dao.nonentity.orderimportlog.OrderImportLogDao;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

public class OrderImportCommonLogicImpl implements OrderImportCommonLogic {

	private OrderImportLogDao orderImportLogDao;
	private FrstOrderHeadDao frstOrderHeadDao;
	private FrstOrderDetailDao frstOrderDetailDao;
	private OrderHeadDao orderHeadDao;
	private OrderDetailDao orderDetailDao;
	private OrderDetailPurchaseSubcontractDao orderDetailPurchaseSubcontractDao;
	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;
	private AspProductionDao aspProductionDao;
	private PurchaseSubcontractDao purchaseSubcontractDao;	
	private GetValidUnitpriceLogic getValidUnitpriceLogic;
	private GetNumberLogic getNumberLogic;
	private ItemDao itemDao;
	private PurchaseAttributeQueueDao purchaseAttributeQueueDao;
	private VenderDao venderDao;
	private OrderRemarkListDao orderRemarkListDao;
	private OrderDetailUnitpriceDao orderDetailUnitpriceDao;
	private OrderDetailAspProductionDao orderDetailAspProductionDao;
	private OrderDetailListDao orderDetailListDao;
	
	private ArticleAttributeQueueDao articleAttributeQueueDao;
	private BalanceDao balanceDao;
	private CarryDao carryDao;
	private OrderDetailSalesTermsDao orderDetailSalesTermsDao;
	
	private OrderImportDetailLogic orderImportDetailLogic;
	
	/**
	 * 受注納期連絡表の出力ログ登録
	 */
	public void inserFrstOrdertLog( List<String> checkedList , final String tantoCd , final String logCls){
		OrderImportLog seqBean =  this.orderImportLogDao.getLogSeq();
		BigDecimal logSeq = seqBean.getSeq();
		
		/* リスト部 */
		for(String ｆrstOrderNo : checkedList){
			this.inserFrstOrdertLog( ｆrstOrderNo, tantoCd, logCls ,logSeq );
		}

	}

	/**
	 * 受注納期連絡表の出力ログ登録
	 */
	public void inserFrstOrdertLog( String frstOrderNo  , final String tantoCd , final String logCls , final BigDecimal logSeq){
		
		/* リスト部 */
		this.orderImportLogDao.insertHeadLog( logCls, frstOrderNo,  logSeq, tantoCd);
		this.orderImportLogDao.insertDetailLog( logCls, frstOrderNo,  logSeq, tantoCd);
	}
	

	/**
	 * 確定受注情報の更新処理
	 * @param tantoCd
	 * @param tantoOrgCd
	 * @param updateDetailList
	 * @param beforeOrderImportDetailList
	 * @param orderNo
	 * @param frstHeadBean
	 * @throws NoDataException
	 * @throws Exception
	 * @throws OrderLogicException
	 */
	public void fixUpdate(final String tantoCd, final String tantoOrgCd,List<OrderImportDetailList> updateDetailList,List<OrderImportDetailList> beforeOrderImportDetailList,String orderNo, FrstOrderHead frstHeadBean) throws NoDataException,	Exception, OrderLogicException {
		// 運賃計算
		List<FrstOrderDetail> updateList = new ArrayList<FrstOrderDetail>();
		List<String> frstOrderNoList = new ArrayList<String>();
		String errMsg = "errors.order.stock.update";
		BigDecimal sumAmount = BigDecimal.ZERO;
		
		// **********************************
		// 単価取得処理
		// 有効単価を取得して更新対象リストに保持
		// *********************************
		for (OrderImportDetailList detailBean : updateDetailList) {
			
			FrstOrderDetail updateBean = frstOrderDetailDao.getEntity(
				detailBean.getFrstOrderNo(), detailBean.getFrstOrderRowNo());

			if( !frstOrderNoList.contains(detailBean.getFrstOrderNo()) ){
				frstOrderNoList.add(detailBean.getFrstOrderNo());
			}
			
			/* 有効単価取得 */
			VaridUnitprice unitBean = getValidUnitpriceLogic.getValidUnitprice(frstHeadBean.getBalanceCd(),updateBean.getItemCd(),	AecDateUtils.formatStringFromTimestamp(frstHeadBean.getScheduledShippingDate(), "yyyy/MM/dd"));

			if (unitBean == null || unitBean.getTmpUnitpriceFlg().equals("1") ) { /* 有効な単価が無い場合 */
				/* 単価、値引き、増付数を0 */
				updateBean.setOrderUnitprice(BigDecimal.ZERO);
				updateBean.setStandardUnitprice(BigDecimal.ZERO);
				updateBean.setStandardDiscount(BigDecimal.ZERO);
				updateBean.setSpecialDiscount(BigDecimal.ZERO);
				updateBean.setMatss(BigDecimal.ZERO);
				// 基準増付数
				updateBean.setEstimateMatss(BigDecimal.ZERO);
				// 基準数量
				updateBean.setEstimateStandardAmount(BigDecimal.ZERO);
				/* 仮単価フラグON */
				updateBean.setTmpUnitpriceFlg(BigDecimal.ONE);

			} else { /* 有効な単価が在る場合 */
				// 更新日時を退避
				Timestamp updateDate = updateBean.getUpdateDate();
				/* 単価データのコピー処理 */
				updateBean.setEstimateMatss(unitBean.getMatss()); // AP増付数
				updateBean.setStandardUnitprice(unitBean.getStandardUnitPrice()); // AP標準機額
				updateBean.setStandardDiscount(unitBean.getStandardDiscount()); // AP標準値引
				updateBean.setSpecialDiscount(unitBean.getSpecialDiscount()); // AP特別値引
				updateBean.setOrderUnitprice(unitBean.getUnitprice()); // 受注金額
				updateBean.setEstimateStandardAmount(unitBean.getStandardAmount()); // 基準数量

				// 20220602 add S.Fujimaki 単価更新時に合計金額計算を追加
				sumAmount = sumAmount.add(unitBean.getUnitprice().multiply(updateBean.getOrderQty()));
				// 20220602 add S.Fujimaki
				
				// 2021/12/10 セッツ様の要望で、在庫引当時に正単価となり、さらに増し付けがある場合、増し付けの計算を行うように修正
				if(!AecNumberUtils.compareToEqual(updateBean.getEstimateMatss(), BigDecimal.ZERO) && 
						!AecNumberUtils.compareToEqual(updateBean.getEstimateStandardAmount(), BigDecimal.ZERO)
						){
					// =======================================================================
					// 見積単価マスタの増し付けと基準数量がゼロではない場合、増し付けを計算
					// =======================================================================
					
					// 受注数量を見積単価の基準数量（10個買ったら1個おまけであると、10)で割って切り捨てする。
					BigDecimal matssCalc = updateBean.getOrderQty().divide(updateBean.getEstimateStandardAmount(), 0,BigDecimal.ROUND_DOWN);
					
					// さらに増し付け数量をかける。
					matssCalc = matssCalc.multiply(updateBean.getEstimateMatss());
					
					updateBean.setMatss(matssCalc);
					
				}
				
				
				// 退避した更新日時を設定
				updateBean.setUpdateDate(updateDate);
				/* 仮単価フラグOFF */
				updateBean.setTmpUnitpriceFlg(BigDecimal.ZERO);
			}
			

			// 更新対象リストに追加
			updateList.add(updateBean);
		}

		for (String frstOrderNo : frstOrderNoList) {
			// ヘッダに受注番号を登録
			FrstOrderHead updateHeadBean = frstOrderHeadDao.getEntity(frstOrderNo);
			if( updateHeadBean.frstOrderNo.equals( frstHeadBean.frstOrderNo ) ){
				updateHeadBean.setUpdateDate(frstHeadBean.updateDate);
			}
			
			updateHeadBean.setUpdatorCd(tantoCd);
			updateHeadBean.setOrderNo(orderNo);
			
			//20220602 add S.Fujimaki　単価が存在すれば合計金額を設定する。
			if( !(sumAmount.equals(BigDecimal.ZERO)) ){
				updateHeadBean.setOrderAmount(sumAmount);
			}
			//20220602 add S.Fujimaki

			try {
					// 更新の場合、
					frstOrderHeadDao.update(updateHeadBean);
			
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

				
		// 画面表示時の検索リスト取得用
		// 受注詳細登録時のデータ取得用
		List<FrstOrderDetail> importList = new ArrayList<FrstOrderDetail>();
		List<FrstOrderDetail> delList = new ArrayList<FrstOrderDetail>();

		
		int i = 0;
		for (FrstOrderDetail updateBean : updateList) {
			
			if( updateBean.getCancelFlg().intValue() == 1 || updateBean.getDelFlg().intValue() == 1 ){

				// 削除リストに追加
				FrstOrderDetail delData = new FrstOrderDetail();
				delData.setOrderNo( updateBean.getOrderNo() );
				delData.setRowNo( updateBean.getRowNo() );
				delList.add( delData );
				// 取込ステータス、更新者情報、運賃、受注金額を登録する
				updateBean.setOrderNo(null);
				updateBean.setRowNo(null);
			}else{
				// 取込ステータス、更新者情報、運賃、受注金額を登録する
				updateBean.setOrderNo(orderNo);
				updateBean.setRowNo(updateBean.getFrstOrderRowNo());
				importList.add(updateBean);
			}

			updateBean.setUpdateDate(beforeOrderImportDetailList
					.get(i).getUpdateDate()); // 更新日は画面を開いた時に取得した更新日付を設定しこれで排他制御を行う
			updateBean.setUpdatorCd(tantoCd); // 登録者

			// 生成した備考を受注取込に登録
			try {
				frstOrderDetailDao.update(updateBean);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 排他エラー
				e.printStackTrace();
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				e.printStackTrace();
				throw new DuplicateRuntimeException(0);
			}

			i++;
		}

		// 受注ヘッダ登録
		OrderHead orderHeadBean = orderHeadDao.getEntity(orderNo);
		List<OrderDetail> orderDetailBeanList = orderDetailDao.getList(orderNo);
		
		boolean updateFlg = false;
		
		// 受注ヘッダが空の場合、新規登録
		if( orderHeadBean == null ){
			orderHeadBean = new OrderHead();
			updateFlg = false;
		}else{
			// 受注ヘッダがあった場合、更新モード、
			updateFlg = true;
		}
		
		// 受注取込のデータを受注ヘッダにセット
		createOrderHead(tantoCd, orderNo, frstHeadBean, orderHeadBean , updateFlg);
		
		//20220602 add S.Fujimaki　単価が存在すれば合計金額を設定する。
		if( !(sumAmount.equals(BigDecimal.ZERO)) ){
			orderHeadBean.setOrderAmount(sumAmount);
		}
		//20220602 add S.Fujimaki


		// 受注更新処理の場合、在庫引当を解除
		if( updateFlg && !(orderHeadBean.getOrderDivision().intValue() == 3) && orderDetailBeanList.size() > 0 ){
			try {
				// 在庫更新処理
				StockinoutForOrder stock = new StockinoutForOrder(
						zaiCtrlDao);
				// 在庫更新－受注取消(全）
				if (!stock.cancelOrder(orderHeadBean.getOrderNo(),
					tantoCd)) {
					OrderLogicException ex = new OrderLogicException(
						"errors.order.stock.update", "");
					throw ex;
				}
			} catch (LogicExceptionEx e) {
				OrderLogicException ex = new OrderLogicException("errors.order.stock.update", "");
				ex.setModuleCd("StockinoutForOrder");
				ex.setInsideErrCd(orderHeadBean.getOrderNo());
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}
		}
		
		try {
 			if( updateFlg ){
				// 登録
				orderHeadDao.update(orderHeadBean);
			}
			else{
				// 登録
				orderHeadDao.insert(orderHeadBean);
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			e.printStackTrace();
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			e.printStackTrace();
			throw new DuplicateRuntimeException(0);
		}
		
		
		// 受注詳細登録
		for (FrstOrderDetail importBean : importList) {
			
			if( !AecStringUtils.isNotNullAndEmpty( importBean.getOrderNo() )){
				continue;
			}
			
			// 受注情報を取得
			OrderDetail orderDetailBean = orderDetailDao.getEntity(importBean.getOrderNo(), importBean.getRowNo());

			boolean detailUpdateFlg = false;
			if( !(orderDetailBean == null) ){
				detailUpdateFlg = true;
			}else{
				orderDetailBean = new OrderDetail();
			}
			
			// 更新用受注明細を作成
			createOrderDetail(tantoCd,importBean,orderDetailBean,detailUpdateFlg);

			try {
				if( detailUpdateFlg ){
					orderDetailDao.update(orderDetailBean);
				}else{
					orderDetailDao.insert(orderDetailBean);
				}
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 排他エラー
				e.printStackTrace();
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				e.printStackTrace();
				throw new DuplicateRuntimeException(0);
			}
		}
		
		// 削除対象データの削除
		// 削除対象テーブルにデータがあれば消す
		if (updateFlg) {
			for (FrstOrderDetail detailBean : delList) {
				// 削除対象の場合、関連情報を削除
				if(  AecStringUtils.isNotNullAndEmpty( detailBean.getOrderNo() ))
				{
					OrderDetail deleteOrderDetailBean = orderDetailDao
							.getEntity(detailBean.getOrderNo(), detailBean.getRowNo());
				
					if( deleteOrderDetailBean != null ){	
						// 更新者セット
						int delNum = orderDetailDao
								.delete(deleteOrderDetailBean);
						if (delNum == 0) {
							System.out.println( SqlLogRegistryLocator.getInstance().getLast().getCompleteSql() );
							// 対象データ無し
							throw new NoDataException();
						}
					}

					// 更新用データ
					PurchaseSubcontract newPurSubBean = new PurchaseSubcontract();

					// PURCHASE_SUBCONTRACTからORDER_NOとROW_NOでデータを取得
					OrderDetailPurchaseSubcontract orDePurSubBean = orderDetailPurchaseSubcontractDao
							.getEntity(detailBean.getOrderNo(), detailBean.getRowNo().toString());

					if (orDePurSubBean != null) {
						// 取得したデータを更新用データにコピー
						IgnoreCaseBeanUtils.copyProperties(
							newPurSubBean, orDePurSubBean);

						// 更新者セット
						newPurSubBean.setUpdatorCd(tantoCd);
						int  delNum = purchaseSubcontractDao
								.delete(newPurSubBean);
						if (delNum == 0) {
							System.out.println( SqlLogRegistryLocator.getInstance().getLast().getCompleteSql() );
							// 対象データ無し
							throw new NoDataException();
						}
					}

					// 更新用データ
					AspProduction aspProBean = new AspProduction();

					// ASP_PRODUCTIONからORDER_NOとROW_NOでデータを取得
					OrderDetailAspProduction orDeAspProBean = orderDetailAspProductionDao
							.getEntity(detailBean.getOrderNo(), detailBean.getRowNo().toString());

					if (orDeAspProBean != null) {
						// 取得したデータを更新用データにコピー
						IgnoreCaseBeanUtils.copyProperties(aspProBean,
							orDeAspProBean);

						// 更新者セット
						aspProBean.setUpdatorCd(tantoCd);

						int delNum = aspProductionDao
								.delete(aspProBean);
						if (delNum == 0) {
							System.out.println( SqlLogRegistryLocator.getInstance().getLast().getCompleteSql() );
							// 対象データ無し
							throw new NoDataException();
						}
					}
				}
			}
		}

		// 在庫更新
		// *********************************************************************
		// 仕入直送品の場合の購買トランの登録処理
		// *********************************************************************
		// 受注区分が３仕入直送品の場合
		if (orderHeadBean.getOrderDivision().compareTo(new BigDecimal(3)) == 0) {
			// 在庫更新処理
			StockinoutForPurchase stock = new StockinoutForPurchase(
					zaiCtrlDao);
			
			for (FrstOrderDetail importBean : importList) {

				// 受注対象外データは登録しない
				if( !AecStringUtils.isNotNullAndEmpty( importBean.getOrderNo() )){
					continue;
				}
				
				PurchaseSubcontract purSubBean = new PurchaseSubcontract();
				
				// PURCHASE_SUBCONTRACTからORDER_NOとROW_NOでデータを取得
				OrderDetailPurchaseSubcontract orDePurSubBean = orderDetailPurchaseSubcontractDao
						.getEntity(orderHeadBean.getOrderNo(), importBean
								.getRowNo().toString());

				// 取得できたら
				if (orDePurSubBean != null) {
					try {
						// 在庫更新－発注入力取消
						if (!stock.canselPurchase(orDePurSubBean
								.getPurchaseNo(), tantoCd)) {
							OrderLogicException ex = new OrderLogicException(
									errMsg, "");
							throw ex;
						}
					} catch (LogicExceptionEx e) {
						OrderLogicException ex = new OrderLogicException(
								errMsg, "");
						ex.setModuleCd("StockinoutForPurchase");
						ex.setInsideErrCd(orderHeadBean.getOrderNo());
						ex.setInsideErrMsg(e.getMessage());
						throw ex;
					}

					// 取得したデータを更新用データにコピー
					IgnoreCaseBeanUtils.copyProperties(purSubBean,
						orDePurSubBean);

					purchaseSubcontractDao.delete(purSubBean);

					// 諸々データのコピー
					setPurchaseSubcontractBean(frstHeadBean, importBean,
						purSubBean, tantoCd, tantoOrgCd);

					// 更新者セット
					purSubBean.setUpdatorCd(tantoCd);

					purchaseSubcontractDao.insert(purSubBean);
				} else {
					// 発注番号
					String buySubcontractOrderNo = getNumberLogic
							.getBuySubcontractOrderNo();
					purSubBean.setBuySubcontractOrderNo(buySubcontractOrderNo);
					// 受注番号
					purSubBean.setOrderNo(importBean.getOrderNo());
					// 受注行番号
					purSubBean.setOrderRowNo(importBean.getRowNo());
					// 諸々データのコピー
					setPurchaseSubcontractBean(frstHeadBean, importBean,
						purSubBean, tantoCd, tantoOrgCd);
					// 登録者セット
					purSubBean.setInputDate(AecDateUtils.getCurrentTimestamp());
					purSubBean.setInputorCd(tantoCd);
					// 更新者セット
					purSubBean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
					purSubBean.setUpdatorCd(tantoCd);
	
					purchaseSubcontractDao.insert(purSubBean);
				}
				try {
					// 購買Noを取得
					OrderDetailPurchaseSubcontract purBean = orderDetailPurchaseSubcontractDao
							.getPurchaseNo(purSubBean
									.getBuySubcontractOrderNo());
					// 在庫更新－発注入力
					if (!stock.entryPurchase(purBean.getPurchaseNo(), tantoCd)) {
						OrderLogicException ex = new OrderLogicException(
								"errors.order.stock.update", "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					OrderLogicException ex = new OrderLogicException(
							"errors.order.stock.update", "");
					ex.setModuleCd("StockinoutForPurchase");
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}
		} else {
			try {
				// 在庫更新処理
				StockinoutForOrder stock = new StockinoutForOrder(zaiCtrlDao);
				
				List<OrderDetail> list = orderDetailDao.getList(orderNo);
				// 受注明細が全てキャンセルの場合、在庫更新の対象がない為、在庫更新のエラーとなる。全キャンセルでも在庫引当し、納期連絡表を送付する必要がある。そのため、受注詳細データが全くない場合、在庫更新をしない様に修正
				if(list.size() != 0 ){
					// 受注詳細データが存在する為在庫引当処理を実行
					// 在庫更新－受注入力(全）
					if (!stock.entryOrder(orderHeadBean.getOrderNo(), tantoCd)) {
						OrderLogicException ex = new OrderLogicException(
								"errors.order.stock.update", "");
						throw ex;
					}
				}
				
			} catch (LogicExceptionEx e) {
				OrderLogicException ex = new OrderLogicException(
						"errors.order.stock.update", "");
				ex.setModuleCd("StockinoutForOrder");
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}
		}

		// *********************************************************************
		// 受注生産の場合の生産要求ASPの登録処理
		// *********************************************************************
		// 受注区分が２受注生産の場合
		if (orderHeadBean.getOrderDivision().compareTo(new BigDecimal(2)) == 0) {
			for (FrstOrderDetail importBean : importList) {

				// 受注対象外データは登録しない
				if( !AecStringUtils.isNotNullAndEmpty( importBean.getOrderNo() )){
					continue;
				}

				AspProduction aspProBean = new AspProduction();
				// ASP_PRODUCTIONからORDER_NOとROW_NOでデータを取得
				OrderDetailAspProduction orDeAspProBean = orderDetailAspProductionDao
						.getEntity(orderHeadBean.getOrderNo(), importBean
								.getRowNo().toString());
				// 取得できたら
				if (orDeAspProBean != null) {
					// 取得できた場合は更新処理
					// 主キーが変更される場合もあるため、削除してから、インサートする

					// 取得したデータを更新用データにコピー
					IgnoreCaseBeanUtils.copyProperties(aspProBean,
						orDeAspProBean);
					aspProductionDao.delete(aspProBean);

					// 品目コード
					aspProBean.setItemCd(importBean.getItemCd());
					// 納期をセット
					aspProBean.setOrderLet(orderHeadBean.getScheduledShippingDate());
					// 受注数量セット
					aspProBean.setOrderAcceptQty(importBean
							.getOrderQty());

					// 更新者セット
					aspProBean.setUpdatorCd(tantoCd);

					aspProductionDao.insert(aspProBean);
				} else {
					// オーダーコード
					Calendar cal1 = Calendar.getInstance(); // システム日時を取得
					cal1.add(Calendar.DAY_OF_MONTH, -1); // 日付-1
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
					String date = df.format(cal1.getTime());
	
					String orderCd = "O_" + importBean.getOrderNo() + "_" + date;
					aspProBean.setOrderCd(orderCd);
	
					// 品目コード
					aspProBean.setItemCd(importBean.getItemCd());
					// 納期
					aspProBean.setOrderLet(frstHeadBean.getScheduledShippingDate());
					// 受注数量
					aspProBean.setOrderAcceptQty(importBean.getOrderQty());
					// 受注番号
					aspProBean.setOrderNo(importBean.getOrderNo());
					// 受注行番号
					aspProBean.setOrderRowNo(importBean.getRowNo());
	
					// 登録者セット
					aspProBean.setInputDate(AecDateUtils.getCurrentTimestamp());
					aspProBean.setInputorCd(tantoCd);
					// 更新者セット
					aspProBean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
					aspProBean.setUpdatorCd(tantoCd);
	
					aspProductionDao.insert(aspProBean);
				}
			}
		}
		

	}


	/**
	 * 受注取込ヘッド・詳細テーブル確定取消処理を行う
	 * @param tantoCd
	 * @param orderNo
	 * @param orderDivision
	 * @param frstOrderNo
	 * @throws OrderLogicException
	 */
	public void deleteOrder(final String tantoCd, String orderNo,
			String orderDivision, String frstOrderNo, List<OrderImportDetailList> detailList)
			throws OrderLogicException {
		// 受注ヘッダ・詳細更新
		OrderHead orderHeadBean = new OrderHead();
		orderHeadBean = orderHeadDao.getEntity(orderNo);

		// 受注区分が３仕入直送品以外の場合
		if (!orderDivision.equals("3")) {
			try {
				// 在庫更新処理
				StockinoutForOrder stock = new StockinoutForOrder(zaiCtrlDao);

				List<OrderDetail> list = orderDetailDao.getList(orderNo);
				if(list.size() != 0 ){
					// 在庫更新－受注取消(全）
					if (!stock.cancelOrder(orderHeadBean.getOrderNo(), tantoCd)) {
						OrderLogicException ex = new OrderLogicException(
								"errors.order.stock.update", "");
						throw ex;
					}

				}
			} catch (LogicExceptionEx e) {
				OrderLogicException ex = new OrderLogicException(
						"errors.order.stock.update", "");
				ex.setModuleCd("StockinoutForOrder");
				ex.setInsideErrCd(orderHeadBean.getOrderNo());
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}
		}

		// 受注ヘッダ削除
		try {
			orderHeadDao.delete(orderHeadBean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			e.printStackTrace();
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			e.printStackTrace();
			throw new DuplicateRuntimeException(0);
		}

		// *********************************************************************
		// OrderDetailの削除処理
		// *********************************************************************
		//20220512 add S.Fujimaki 排他追加
		for (OrderImportDetailList detailBean : detailList) {
			try{
	            //　更新日付がnull以外の行を設定
				if(detailBean.getOrderDetailDate() != null){
					//　１行ずつ削除を実行する。
					OrderDetail orderDetailBean = new OrderDetail();
					orderDetailBean = orderDetailDao.getEntity(detailBean.getOrderNo(),detailBean.getRowNo());
					orderDetailBean.setUpdateDate(detailBean.getOrderDetailDate());
					orderDetailDao.delete(orderDetailBean);
				}
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 排他エラー
				e.printStackTrace();
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				e.printStackTrace();
				throw new DuplicateRuntimeException(0);
			}
		}
		// 最終処理として元の削除SQLを実行する。
		getOrderDetailListDao().deleteByOrderNo(orderNo);
		//20220512 add S.Fujimaki 排他追加
		// *********************************************************************
		// 受注生産の場合の生産要求ASPの削除
		// *********************************************************************
		// 受注区分が２受注生産の場合
		if (orderHeadBean.getOrderDivision().compareTo(new BigDecimal(2)) == 0) {
			orderDetailAspProductionDao.deleteByOrderNo(orderNo);
		}

		// *********************************************************************
		// 仕入直送品の場合の購買トランの登録処理
		// *********************************************************************
		// 受注区分が３仕入直送品の場合
		if (orderHeadBean.getOrderDivision().compareTo(new BigDecimal(3)) == 0) {
			try {
				// 在庫更新処理
				StockinoutForPurchase stock = new StockinoutForPurchase(
						zaiCtrlDao);
				List<OrderDetailPurchaseSubcontract> list = new ArrayList<OrderDetailPurchaseSubcontract>();
				list = orderDetailPurchaseSubcontractDao
						.getPurchaseNoList(orderNo);
				if (list != null && list.size() > 0) {
					for (OrderDetailPurchaseSubcontract bean : list) {
						// 在庫更新－発注入力取消
						if (!stock.canselPurchase(bean.getPurchaseNo(),
							tantoCd)) {
							OrderLogicException ex = new OrderLogicException(
									"errors.order.stock.update", "");
							throw ex;
						}
					}
				}
			} catch (LogicExceptionEx e) {
				OrderLogicException ex = new OrderLogicException(
						"errors.order.stock.update", "");
				ex.setModuleCd("StockinoutForPurchase");
				ex.setInsideErrCd(orderNo);
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}
			orderDetailPurchaseSubcontractDao.deleteByOrderNo(orderNo);
		}

		// 先付け受注情報の引当解除処理
		releaseFrstOrder(tantoCd,  frstOrderNo);
	}


	/**
	 * 先付け受注情報の引当解除
	 * @param tantoCd
	 * @param cancelList
	 * @param frstOrderNo
	 */
	public void releaseFrstOrder(final String tantoCd, String frstOrderNo ) {
		// 受注取込更新
		FrstOrderHead frstHeadUpdateBean = new FrstOrderHead();
		// 取込番号のレコードを取得する
		frstHeadUpdateBean = frstOrderHeadDao.getEntity(frstOrderNo);
		// 受注番号、行番号(受注)取込ステータス、更新者情報、価格情報を登録する
		frstHeadUpdateBean.setOrderNo(null);
		frstHeadUpdateBean.setUpdatorCd(tantoCd); // 登録者
		try {
			// 更新
			frstOrderHeadDao.update(frstHeadUpdateBean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			e.printStackTrace();
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			e.printStackTrace();
			throw new DuplicateRuntimeException(0);
		}

		// 取込番号のレコードを取得する
		List<FrstOrderDetail> frstOrderDetail = frstOrderDetailDao.getList(frstOrderNo);

		// 自動運賃計算
		for (FrstOrderDetail updateBean : frstOrderDetail) {
			// 受注番号、行番号(受注)取込ステータス、更新者情報、価格情報を登録する
			updateBean.setOrderNo(null);
			updateBean.setRowNo(BigDecimal.ZERO);
			updateBean.setUpdateDate(updateBean.getUpdateDate()); // 排他制御用に更新日は画面を開いた時の更新日付を設定
			updateBean.setUpdatorCd(tantoCd); // 登録者

			try {
				// 更新
				frstOrderDetailDao.update(updateBean);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 排他エラー
				e.printStackTrace();
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				e.printStackTrace();
				throw new DuplicateRuntimeException(0);
			}

		}
	}


	/**
	 * 先付け受注情報の削除
	 * @param tantoCd
	 * @param cancelList
	 * @param frstOrderNo
	 */
	public void deleteFrstOrder(final String tantoCd, String frstOrderNo , FrstOrderHead frstHeadUpdateBean ) {
		// 取込番号のレコードを取得する
		frstHeadUpdateBean = frstOrderHeadDao.getEntity(frstOrderNo);
		// 受注番号、行番号(受注)取込ステータス、更新者情報、価格情報を登録する
		frstHeadUpdateBean.setDelFlg(BigDecimal.ONE);
		frstHeadUpdateBean.setUpdatorCd(tantoCd); // 登録者
		try {
			// 更新
			frstOrderHeadDao.update(frstHeadUpdateBean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			e.printStackTrace();
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			e.printStackTrace();
			throw new DuplicateRuntimeException(0);
		}


		// 取込番号のレコードを取得する
		List<FrstOrderDetail> frstOrderDetail = frstOrderDetailDao.getList(frstOrderNo);

		// 自動運賃計算
		for (FrstOrderDetail updateBean : frstOrderDetail) {
			// 受注番号、行番号(受注)取込ステータス、更新者情報、価格情報を登録する
			updateBean.setDelFlg(BigDecimal.ONE);
			updateBean.setUpdateDate(updateBean.getUpdateDate()); // 排他制御用に更新日は画面を開いた時の更新日付を設定
			updateBean.setUpdatorCd(tantoCd); // 登録者

			try {
				// 更新
				frstOrderDetailDao.update(updateBean);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 排他エラー
				e.printStackTrace();
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				e.printStackTrace();
				throw new DuplicateRuntimeException(0);
			}

		}
	}

	
	/**
	 * 受注明細の作成
	 * @param tantoCd
	 * @param importBean
	 * @param orderDetailBean
	 */
	private void createOrderDetail(final String tantoCd,
			FrstOrderDetail importBean, OrderDetail orderDetailBean , boolean detailUpdateFlg) {
		orderDetailBean.setOrderNo(importBean.getOrderNo()); // 受注番号
		orderDetailBean.setRowNo(importBean.getRowNo()); // 行番号(受注)
		orderDetailBean.setItemCd(importBean.getItemCd()); // 品目コード
		orderDetailBean.setOrderQty(importBean.getOrderQty()); // 受注数量
		orderDetailBean.setOrderUnitprice(importBean.getOrderUnitprice()); // 受注単価
		orderDetailBean.setStandardUnitprice(importBean.getStandardUnitprice()); // 標準単価
		orderDetailBean.setStandardDiscount(importBean.getStandardDiscount()); // 標準値引
		orderDetailBean.setSpecialDiscount(importBean.getSpecialDiscount()); // 特別値引
		orderDetailBean.setTmpUnitpriceFlg(importBean.getTmpUnitpriceFlg()); // 仮単価FLG
		orderDetailBean.setMatss(importBean.getMatss()); // 受注増付数
		orderDetailBean.setEstimateStandardAmount(importBean
				.getEstimateStandardAmount()); // 見積基準数量
		orderDetailBean.setEstimateMatss(importBean.getEstimateMatss()); // 見積増付数
		orderDetailBean.setInputDivision(new BigDecimal("2")); // データ入力区分
																// 2:受注取込から新規追加
		orderDetailBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		orderDetailBean.setInputorCd(tantoCd); // 登録者ID
		if(!detailUpdateFlg){
			orderDetailBean.setUpdateDate(AecDateUtils.getCurrentTimestamp()); // 更新日時
		}
		orderDetailBean.setUpdatorCd(tantoCd); // 更新者ID
	}

	/**
	 * 受注ヘッダの作成
	 * @param tantoCd
	 * @param orderNo
	 * @param frstHeadBean
	 * @param orderHeadBean
	 */
	private void createOrderHead(final String tantoCd, String orderNo,
			FrstOrderHead frstHeadBean, OrderHead orderHeadBean , boolean updateFlg) {
		orderHeadBean.setOrderNo(orderNo); // 受注番号
		orderHeadBean.setOrderDivision(frstHeadBean.getOrderDivision()); // 受注区分
		orderHeadBean.setOrderDate(frstHeadBean.getOrderDate()); // 受注日
		orderHeadBean.setOrganizationCd(frstHeadBean.getOrganizationCd()); // 担当部署コード
		orderHeadBean.setInputTantoCd(tantoCd); // 入力担当者コード
		orderHeadBean.setSalesTantoCd(frstHeadBean.getSalesTantoCd()); // 営業担当者コード
		orderHeadBean.setVenderCd(frstHeadBean.getVenderCd()); // 得意先コード
		orderHeadBean.setDeliveryCd(frstHeadBean.getDeliveryCd()); // 納入先コード
		orderHeadBean.setDeliveryAddress(frstHeadBean.getDeliveryAddress()); // 納入先宛先
		orderHeadBean.setDeliveryTelNo(frstHeadBean.getDeliveryTelNo()); // 納入先電話番号
		orderHeadBean.setBalanceCd(frstHeadBean.getBalanceCd()); // 帳合コード
		orderHeadBean.setCustomerOrderNo(frstHeadBean.getCustomerOrderNo()); // 客先注文番号
		orderHeadBean.setStatus(BigDecimal.ONE); // ステータス:1．受注登録
		orderHeadBean.setOrderAmount(frstHeadBean.getOrderAmount()); // 受注金額
		orderHeadBean.setSuggestedDeliverlimit(frstHeadBean
				.getSuggestedDeliverlimit()); // 希望納期
		orderHeadBean.setScheduledShippingDate(frstHeadBean
				.getScheduledShippingDate()); // 出荷予定日
		orderHeadBean.setLeadTime(frstHeadBean.getLeadTime()); // リードタイム
		orderHeadBean.setDeliveryExpectedDate(frstHeadBean
				.getDeliveryExpectedDate()); // 納入予定日
		orderHeadBean.setDeliveryExpectedTime(frstHeadBean
				.getDeliveryExpectedTime()); // 納入時刻
		orderHeadBean.setCarryCd(frstHeadBean.getCarryCd()); // 運送会社コード
		orderHeadBean.setCarryFare(frstHeadBean.getCarryFare()); // 運賃
		orderHeadBean.setCarryInvoiceFlag(frstHeadBean.getCarryInvoiceFlag()); // 運賃請求フラグ
		orderHeadBean.setOrderPicture(frstHeadBean.getOrderPicture()); // 注文書画像
		orderHeadBean.setPrintSummery(frstHeadBean.getPrintSummery()); // 備考(印字用)
		orderHeadBean.setDeliverySlipSummery(frstHeadBean
				.getDeliverySlipSummery()); // 自動表示備考
		orderHeadBean.setOrderSummery(frstHeadBean.getOrderSummery()); // 参照
		orderHeadBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		orderHeadBean.setInputorCd(tantoCd); // 登録者ID
		if(!updateFlg){
			orderHeadBean.setUpdateDate(AecDateUtils.getCurrentTimestamp()); // 更新日時
		}
		orderHeadBean.setUpdatorCd(tantoCd); // 更新者ID
	}
	

	/**
	 * 購買テーブル情報設定
	 */
	private void setPurchaseSubcontractBean(final FrstOrderHead headBean,
			final FrstOrderDetail detailBean,
			final PurchaseSubcontract purSubBean, final String tantoCd,
			final String tantoOrgCd) throws Exception {

		// 発注日
		purSubBean.setOrderDate(headBean.getOrderDate());

		// 納入先コード
		purSubBean.setLocationCd(headBean.getDeliveryCd());
		// 2015/2/5 品目マスタのVerを取得する ->
		Item itemBean = itemDao.getEntity(detailBean.getItemCd());

		// 仕入先コード 、担当部署コード
		PurchaseAttributeQueue purAttQueBean = purchaseAttributeQueueDao
				.getEntity(itemBean.getItemCd(), itemBean.getVersion());
		// 2015/2/5 品目マスタのVerを取得する <-

		if (purAttQueBean != null) {
			purSubBean.setVenderCd(purAttQueBean.getVenderCd());

			if (StringUtils.isNotEmpty(purAttQueBean.getVenderCd())) {
				// 担当部署コード
				Vender venderBean = venderDao.getEntity(
					purAttQueBean.getVenderCd(), "SI");
				if (venderBean != null) {
					purSubBean.setChargeOrganizationCd(venderBean
							.getOrganizationCd());
				}
			} else {
				purSubBean.setChargeOrganizationCd(null);
			}
		} else {
			purSubBean.setVenderCd(null);
		}

		// 品目コード
		purSubBean.setItemCd(detailBean.getItemCd());

		// 品目名称
		purSubBean.setItemName(itemBean.getItemName());

		// 受注数量
		purSubBean.setOrderQuantity(detailBean.getOrderQty());

		// 重量
		if (itemBean != null) {
			purSubBean.setOrderConvertQuantity(detailBean.getOrderQty()
					.multiply(itemBean.getKgOfFractionManagement()));
		}

		// 希望納期
		purSubBean.setSuggestedDeliverlimitDate(headBean
				.getSuggestedDeliverlimit());

		// 発注書備考、備考(仕入先コードで備考を取得する)
		String venderCd = null;
		venderCd = purSubBean.getVenderCd();

		List<OrderRemarkList> orRemarkList = orderRemarkListDao.getRemarkList(
			venderCd, headBean.getDeliveryCd(), detailBean.getItemCd());

		StringBuffer sbfRemark12 = new StringBuffer();
		StringBuffer sbfRemark13 = new StringBuffer();
		int lngRemark12 = 0;
		int lngRemark13 = 0;

		// 発注時備考に納入予定時間をセット
		if (headBean.getDeliveryExpectedTime() != null
				&& !headBean.getDeliveryExpectedTime().equals("")) {
			sbfRemark12.append(headBean.getDeliveryExpectedTime());
			lngRemark12++;
		}
		if (orRemarkList != null) {

			for (int i = 0; i < orRemarkList.size(); i++) {
				if (orRemarkList.get(i).getRemark12() != null) {
					if (lngRemark12 != 0) {
						sbfRemark12
								.append(System.getProperty("line.separator"));
					}
					sbfRemark12.append(orRemarkList.get(i).getRemark12());
					lngRemark12++;
				}
				if (orRemarkList.get(i).getRemark13() != null) {
					if (lngRemark13 != 0) {
						sbfRemark13
								.append(System.getProperty("line.separator"));
					}
					sbfRemark13.append(orRemarkList.get(i).getRemark13());
					lngRemark13++;
				}
			}
		}
		purSubBean.setOrderSheetRemark(sbfRemark13.toString());
		purSubBean.setRemark(sbfRemark12.toString());

		// 発注単価
		OrderDetailUnitprice orderUnitprice = orderDetailUnitpriceDao
				.getOrderQuantityRelatedData(detailBean.getItemCd(),
					purSubBean.getVenderCd(), detailBean.getOrderQty());
		if (orderUnitprice != null) {
			purSubBean.setOrderUnitprice(orderUnitprice.getUnitprice());
		}

		// 単価決定単位区分
		if (orderUnitprice != null) {
			purSubBean.setUnitpriceDefineunit(new BigDecimal(orderUnitprice
					.getUnitpriceDivision()));
		}

		// 金額設定
		BigDecimal orderAmount = calcOrderAmount(orderUnitprice, detailBean,
			itemBean);
		purSubBean.setSupplierOrdAmount(orderAmount);

		// 部署コード
		purSubBean.setOrganizationCd(tantoOrgCd);
		// 発注担当者コード
		purSubBean.setTantoCd(tantoCd);

		purSubBean.setMaterialDivision(BigDecimal.ONE);
		purSubBean.setOrderDivision(new BigDecimal(5));
		purSubBean.setStatus(PurchaseStatus.NOT_ISSUE);
		purSubBean.setOrderSheeFlag(BigDecimal.ZERO);
		purSubBean.setGoodHousingSum(BigDecimal.ZERO);
		purSubBean.setReplyContentsDivision(BigDecimal.ZERO);
		purSubBean.setDeliveryComp(BigDecimal.ZERO);
		purSubBean.setDataType(BigDecimal.ZERO);
		purSubBean.setDataTotalDivision(BigDecimal.ZERO);
		purSubBean.setRowNo(BigDecimal.ZERO);
		purSubBean.setCancelRowNo(BigDecimal.ZERO);
		purSubBean.setHousingUnitprice(BigDecimal.ZERO);
		purSubBean.setStockingAmount(BigDecimal.ZERO);

		purSubBean.setTaxDivision(BigDecimal.ZERO);
		purSubBean.setTaxAmount(BigDecimal.ZERO);

		purSubBean.setPayableTargetDivision(BigDecimal.ZERO);
		purSubBean.setPaymentTargetDivision(BigDecimal.ZERO);
		purSubBean.setPayableUpdateDivision(BigDecimal.ZERO);
		purSubBean.setPaymentUpdateDivision(BigDecimal.ZERO);

		purSubBean.setLabelFlag(BigDecimal.ZERO);

		purSubBean.setTmpUnitpriceFlg(BigDecimal.ZERO);
		purSubBean.setInspectMethod(BigDecimal.ZERO);
		purSubBean.setMortgageDetailFlg(BigDecimal.ZERO);
		purSubBean.setInspreceiptWaitQuantity(BigDecimal.ZERO);
		purSubBean.setBadQuantity(BigDecimal.ZERO);
		purSubBean.setCostEntry(BigDecimal.ZERO);
		purSubBean.setAdvPurNoticeDecideDivision(BigDecimal.ZERO);
		purSubBean.setOrderMortgagedQuantity(BigDecimal.ZERO);
		purSubBean.setSumHousingConvertQuant(BigDecimal.ZERO);
		purSubBean.setInspectWaitConvertQuantity(BigDecimal.ZERO);
		purSubBean.setExtractionsQuantity(BigDecimal.ZERO);
		purSubBean.setDefectiveQuantity(BigDecimal.ZERO);
		purSubBean.setLaboratoryMethod(BigDecimal.ZERO);
		purSubBean.setProvisionDivision(BigDecimal.ZERO);
		purSubBean.setTaxRatio(BigDecimal.ZERO);
		purSubBean.setCheckQuantity(BigDecimal.ZERO);
		purSubBean.setSlipIssueDivision(BigDecimal.ZERO);
		purSubBean.setApprovalStatus(BigDecimal.ZERO);
	}


	/**
	 * 購買テーブル書き込み時の金額計算
	 */
	private BigDecimal calcOrderAmount(
			final OrderDetailUnitprice orderUnitprice,
			final FrstOrderDetail bean, final Item itemBean) throws Exception {

		String unitPriceDivision = null;
		String unitOfOpeManage = null;
		if (orderUnitprice == null || bean == null || itemBean == null) {
			return BigDecimal.ZERO;
		}
		unitPriceDivision = orderUnitprice.getUnitpriceDivision();
		unitOfOpeManage = itemBean.getUnitOfOperationManagement();
		if (unitOfOpeManage == null || unitPriceDivision == null) {
			return BigDecimal.ZERO;
		}

		BigDecimal qty = bean.getOrderQty();
		BigDecimal tanka = orderUnitprice.getUnitprice();
		BigDecimal kgOffrac = itemBean.getKgOfFractionManagement();
		if (qty == null || tanka == null || kgOffrac == null) {
			return BigDecimal.ZERO;
		}

		BigDecimal orderAmount = null;

		// 運用管理単位がKg以外 かつ 単価決定単位区分が 個
		if (!unitOfOpeManage.equals("1") && unitPriceDivision.equals("1")) {
			orderAmount = tanka.multiply(qty);
			// 運用管理単位がKg以外 かつ 単価決定単位区分が Kg
		} else if (!unitOfOpeManage.equals("1")
				&& unitPriceDivision.equals("2")) {
			orderAmount = kgOffrac.multiply(tanka.multiply(qty));
			// 運用管理単位がKg かつ 単価決定単位区分が Kg
		} else if (unitOfOpeManage.equals("1") && unitPriceDivision.equals("2")) {
			orderAmount = tanka.multiply(qty);
			// 運用管理単位がKg かつ 単価決定単位区分が 個
		} else if (unitOfOpeManage.equals("1") && unitPriceDivision.equals("1")) {
			return BigDecimal.ZERO;
		}
		return orderAmount;
	}
	

	/**
	 * ##ここにメソッドの説明を書いてください##
	 * @param frm
	 * @param orderDate
	 * @param scheduledShippingDate
	 * @param deliveryExpectedDate
	 * @return
	 */
	public ActionMessages checkValidDate(final int insertFlg,
			String orderDate, String scheduledShippingDate,
			String deliveryExpectedDate) {
		ActionMessages messages = new ActionMessages();
		// 今を取得
		String now = AecDateUtils.dateFormat(AecDateUtils.getCurrentTimestamp(), "yyyy/MM/dd");
		
		//新規登録時のみ受注チェック
		if(insertFlg == 1){
			// 受注日
			if (now.compareTo(orderDate) > 0) {
				messages.add("", new ActionMessage("errors.order.order.date"));
			}
		}

		// 出荷予定日
		if (now.compareTo(scheduledShippingDate) > 0) {
			messages.add("", new ActionMessage("errors.order.scheduled.shipping.date"));
		}
		// 納入予定日
		if (now.compareTo(deliveryExpectedDate) > 0) {
			messages.add("", new ActionMessage("errors.order.delivery.expected.date"));
		}
		return messages;
	}	

	


	/**
	 * 登録時の品目マスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkItem(final List<OrderImportDetailList> detailList,
			final int insertFlg,
			final ActionMessages errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		int index = 1;

		for (OrderImportDetailList bean : detailList) {
			
			// 削除レコードはチェックしない
			if(bean.getDelFlg().intValue() == 1){
				continue;
			}

			// 削除レコードはチェックしない
			if(bean.getCancelFlg().intValue() == 1){
				index++;
				continue;
			}
			
			// 新規登録時のみ品目コードが空でもスキップ
			if (bean.getItemCd().isEmpty() && insertFlg == 1) {
				index++;
				continue;
			}
			
			// 品目マスタ検索
			Item itemBean = itemDao.getEntity(bean.getItemCd());
			if (itemBean == null) {
				// データが存在しない場合
				errors.add(
					"",
					new ActionMessage("errors.nodata.master.row", rb
							.getString("item.order.item.cd"), Integer
							.toString(index)));
			} else {

				// 販売品区分が0:該当なしの場合エラーとする
				if (BigDecimal.ZERO.compareTo(itemBean.getArticleDivision()) == 0) {
					errors.add("",
						new ActionMessage("errors.order.article.division.row",
								Integer.toString(index)));
				} else {

					ArticleAttributeQueue artBean = articleAttributeQueueDao
							.getEntity(itemBean.getItemCd(),
								itemBean.getVersion());

					if (artBean == null) {
						// データが存在しない場合
						errors.add(
							"",
							new ActionMessage("errors.nodata.master.row", rb
									.getString("item.order.item.cd"), Integer
									.toString(index)));
					} else {
						// 預り品品目の場合エラー
						if (artBean.getKeepDivision().equals(new BigDecimal(2))) {
							errors.add("", new ActionMessage(
									"errors.order.article.keep.division.row",
									Integer.toString(index)));
						}
					}

				}
			}
			index++;
		}
		return errors;
	}

	/**
	 * 品目が全て仕入直送品どうかをかチェックを行う.<br>
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkItemTypeDivision(
			final List<OrderImportDetailList> detailList,
			final String orderDivision,
			final int insertFlg,
			final ActionMessages errors) {
		boolean checkFlag = false; // チェックOKはfalse

		if (orderDivision.equals("3")) {
			// 品目が全て仕入直送品かをチェックする
			for (OrderImportDetailList bean : detailList) {
				// 削除レコードはチェックしない
				if(bean.getDelFlg().intValue() == 1){
					continue;
				}

				// 削除レコードはチェックしない
				if(bean.getCancelFlg().intValue() == 1){
					continue;
				}
				
				// 新規登録時のみ品目コードが空でもスキップ
				if (bean.getItemCd().isEmpty() && insertFlg == 1) {
					continue;
				}
				// 品目マスタ検索
				Item itemBean = itemDao.getEntity(bean.getItemCd());
				if (itemBean != null) {
					// 4:仕入直送品でなければ
					if (!itemBean.getTypeDivision().equals(new BigDecimal("4"))) {
						checkFlag = true;
					}
				}
			}

			if (checkFlag) {
				// 仕入直送品以外の品目があった場合
				errors.add("", new ActionMessage(
						"errors.order.item.not.shiiretyokusou"));
			}
		} else {
			// 品目が全て仕入直送品以外かをチェックする
			for (OrderImportDetailList bean : detailList) {
				// 削除レコードはチェックしない
				if(bean.getDelFlg().intValue() == 1){
					continue;
				}

				// 削除レコードはチェックしない
				if(bean.getCancelFlg().intValue() == 1){
					continue;
				}
				
				// 新規登録時のみ品目コードが空でもスキップ
				if (bean.getItemCd().isEmpty() && insertFlg == 1) {
					continue;
				}
				
				Item itemBean = itemDao.getEntity(bean.getItemCd());
				if (itemBean != null) {
					// 4:仕入直送品であれば
					if (itemBean.getTypeDivision().equals(new BigDecimal("4"))) {
						checkFlag = true;
					}
				}
			}

			if (checkFlag) {
				// 仕入直送品の品目があった場合
				errors.add("", new ActionMessage(
						"errors.order.item.shiiretyokusou"));
			}
		}

		return errors;
	}

	/**
	 * 登録時の販売条件マスタチェックを行う.<br>
	 * 販売条件マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkSalesTerms(final List<OrderImportDetailList> detailList,
			final String balanceCd ,
			final String deliveryCd ,
			final int insertFlg ,
			final ActionMessages errors) {
		int index = 1;

		for (OrderImportDetailList bean : detailList) {
			// 削除レコードはチェックしない
			if(bean.getDelFlg().intValue() == 1){
				continue;
			}

			// 削除レコードはチェックしない
			if(bean.getCancelFlg().intValue() == 1){
				index++;
				continue;
			}
			
			// 新規登録時のみ品目コードが空でもスキップ
			if (bean.getItemCd().isEmpty() && insertFlg == 1) {
				index++;
				continue;
			}
			
			String itemCd = bean.getItemCd();
			// 販売条件マスタ検索
			OrderDetailSalesTerms salesTermsBean = orderDetailSalesTermsDao
					.getEntity(deliveryCd, balanceCd, itemCd);

			if (salesTermsBean == null) {
				// データが存在しない場合
				errors.add("",
					new ActionMessage(
							"errors.orderimport.nodata.sales.terms.row",
							Integer.toString(index), bean.getItemCd()));
			}
			index++;
		}
		return errors;
	}

	/**
	 * 登録時の帳合コードマスタチェックを行う.<br>
	 * 帳合マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkBalance(final String balanceCd,
			final ActionMessages errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		Balance balanceBean = balanceDao.getEntity(balanceCd);
		if (balanceBean == null) {
			// データが存在しない場合
			errors.add(
				"",
				new ActionMessage("errors.nodata.master", rb
						.getString("item.order.balance.cd")));
		}

		return errors;
	}

	/**
	 * 運送会社コードマスタチェックを行う.<br>
	 * 運送会社マスタにデータがない場合はエラーとする。
	 * @param carryCd 運送会社コード
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkCarry(final String carryCd,
			final ActionMessages errors) {
		Carry carryBean = carryDao.getEntity(carryCd);
		if (carryBean == null || carryCd == null) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.order.carry.not.exist"));
		}

		return errors;
	}

	/**
	 * 登録時の取引先コードマスタチェックを行う.<br>
	 * 取引先マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkVender(final String venderCd,
			final ActionMessages errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		Vender venderBean = venderDao.getEntity(venderCd,
			Constants.VENDER_DIVISION_TS);
		if (venderBean == null) {
			// データが存在しない場合
			errors.add(
				"",
				new ActionMessage("errors.nodata.master", rb
						.getString("item.order.vender.cd")));
		}

		return errors;
	}
	
	/**
	 *　数量の不足チェック
	 * @param orderDivision
	 * @param orderImportDetailList
	 * @param frstOrderNo
	 * @return
	 */
	public ActionMessages checkOrderQuantity(String orderDivision,
			List<OrderImportDetailList> orderImportDetailList,
			String frstOrderNo) {
		ActionMessages messages = new ActionMessages();
		int index = 1;
		if (orderDivision.equals("1")
				|| orderDivision.equals("4")) {
			for (OrderImportDetailList bean : orderImportDetailList) {
				String itemCd = bean.getItemCd();
				
				if( bean.getDelFlg().intValue() == 1  ){
					continue;
				}
				if(  bean.getCancelFlg().intValue() == 1 ){
					index++;
					continue;
				}

				
				try {
					ItemInventory inventEntity = orderImportDetailLogic.getItemInventoryEntity(itemCd);
	
					double inventoryQty = inventEntity.getInventoryQty().doubleValue();
					double assignQty = inventEntity.getAssignQty().doubleValue();
					double salesAssignQty = inventEntity.getSalesAssignQty().doubleValue();
					BigDecimal noGood = orderImportDetailLogic.getNoGoodQty(itemCd);
					double noGoodQty = noGood.doubleValue();
	 				double orderQty = AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getOrderQty()).doubleValue();
					double matss = AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getMatss()).doubleValue();
					double beforeOrderQty = 0;
					double beforeMatss = 0;
					
					List<OrderImportDetailList> orderList = this.orderImportDetailLogic.getOrderImportDetailList( frstOrderNo );
	
					if( orderList != null ){
						for( OrderImportDetailList detail : orderList ){
							// 登録済みの受注の場合、前回の受注数を加算する
							if( AecStringUtils.isNotNullAndEmpty(detail.getOrderNo()) && detail.getItemCd().equals(bean.getItemCd())){
								beforeOrderQty = AecNumberUtils.convertBigDecimalNullToZeroFromString(detail.getOrderQty()).doubleValue();
								beforeMatss = AecNumberUtils.convertBigDecimalNullToZeroFromString(detail.getMatss()).doubleValue();
							}
						}
					}
	
					
					double qty = inventoryQty + assignQty + salesAssignQty - noGoodQty + beforeMatss + beforeOrderQty ;
					
	
					// 数量＋増付数が在庫よりも少ない場合エラー
					if (qty < ( orderQty + matss )) {
						messages.add("", new ActionMessage(
								"errors.orderimport.item.quantity.row", Integer.toString(index), qty - orderQty - matss));
					}
				} catch (NoDataException e) {
					messages.add("", new ActionMessage(
							"errors.order.item.quantity.row", Integer.toString(index)));
				}
				index++;
			}
		}
		return messages;
	}
	
	/**
	 * orderImportLogDaoを設定します。
	 * @param orderImportLogDao orderImportLogDao
	 */
	public void setOrderImportLogDao(OrderImportLogDao orderImportLogDao) {
		this.orderImportLogDao = orderImportLogDao;
	}

	/**
	 * frstOrderHeadDaoを設定します。
	 * @param frstOrderHeadDao frstOrderHeadDao
	 */
	public void setFrstOrderHeadDao(FrstOrderHeadDao frstOrderHeadDao) {
		this.frstOrderHeadDao = frstOrderHeadDao;
	}

	/**
	 * frstOrderDetailDaoを設定します。
	 * @param frstOrderDetailDao frstOrderDetailDao
	 */
	public void setFrstOrderDetailDao(FrstOrderDetailDao frstOrderDetailDao) {
		this.frstOrderDetailDao = frstOrderDetailDao;
	}

	/**
	 * orderHeadDaoを設定します。
	 * @param orderHeadDao orderHeadDao
	 */
	public void setOrderHeadDao(OrderHeadDao orderHeadDao) {
		this.orderHeadDao = orderHeadDao;
	}

	/**
	 * orderDetailDaoを設定します。
	 * @param orderDetailDao orderDetailDao
	 */
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	/**
	 * orderDetailPurchaseSubcontractDaoを設定します。
	 * @param orderDetailPurchaseSubcontractDao orderDetailPurchaseSubcontractDao
	 */
	public void setOrderDetailPurchaseSubcontractDao(
			OrderDetailPurchaseSubcontractDao orderDetailPurchaseSubcontractDao) {
		this.orderDetailPurchaseSubcontractDao = orderDetailPurchaseSubcontractDao;
	}

	/**
	 * zaiCtrlDaoを設定します。
	 * @param zaiCtrlDao zaiCtrlDao
	 */
	public void setZaiCtrlDao(ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * aspProductionDaoを設定します。
	 * @param aspProductionDao aspProductionDao
	 */
	public void setAspProductionDao(AspProductionDao aspProductionDao) {
		this.aspProductionDao = aspProductionDao;
	}

	/**
	 * purchaseSubcontractDaoを設定します。
	 * @param purchaseSubcontractDao purchaseSubcontractDao
	 */
	public void setPurchaseSubcontractDao(
			PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * getValidUnitpriceLogicを設定します。
	 * @param getValidUnitpriceLogic getValidUnitpriceLogic
	 */
	public void setGetValidUnitpriceLogic(
			GetValidUnitpriceLogic getValidUnitpriceLogic) {
		this.getValidUnitpriceLogic = getValidUnitpriceLogic;
	}

	/**
	 * getNumberLogicを設定します。
	 * @param getNumberLogic getNumberLogic
	 */
	public void setGetNumberLogic(GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * itemDaoを設定します。
	 * @param itemDao itemDao
	 */
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * purchaseAttributeQueueDaoを設定します。
	 * @param purchaseAttributeQueueDao purchaseAttributeQueueDao
	 */
	public void setPurchaseAttributeQueueDao(
			PurchaseAttributeQueueDao purchaseAttributeQueueDao) {
		this.purchaseAttributeQueueDao = purchaseAttributeQueueDao;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(VenderDao venderDao) {
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
	 * orderDetailUnitpriceDaoを設定します。
	 * @param orderDetailUnitpriceDao orderDetailUnitpriceDao
	 */
	public void setOrderDetailUnitpriceDao(
			OrderDetailUnitpriceDao orderDetailUnitpriceDao) {
		this.orderDetailUnitpriceDao = orderDetailUnitpriceDao;
	}

	/**
	 * orderDetailListDaoを取得します。
	 * @return orderDetailListDao
	 */
	public OrderDetailListDao getOrderDetailListDao() {
		return orderDetailListDao;
	}

	/**
	 * orderDetailListDaoを設定します。
	 * @param orderDetailListDao orderDetailListDao
	 */
	public void setOrderDetailListDao(OrderDetailListDao orderDetailListDao) {
		this.orderDetailListDao = orderDetailListDao;
	}

	/**
	 * orderDetailAspProductionDaoを設定します。
	 * @param orderDetailAspProductionDao orderDetailAspProductionDao
	 */
	public void setOrderDetailAspProductionDao(
			OrderDetailAspProductionDao orderDetailAspProductionDao) {
		this.orderDetailAspProductionDao = orderDetailAspProductionDao;
	}

	/**
	 * balanceDaoを設定します。
	 * @param balanceDao balanceDao
	 */
	public void setBalanceDao(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	/**
	 * carryDaoを設定します。
	 * @param carryDao carryDao
	 */
	public void setCarryDao(CarryDao carryDao) {
		this.carryDao = carryDao;
	}

	/**
	 * orderDetailSalesTermsDaoを設定します。
	 * @param orderDetailSalesTermsDao orderDetailSalesTermsDao
	 */
	public void setOrderDetailSalesTermsDao(
			OrderDetailSalesTermsDao orderDetailSalesTermsDao) {
		this.orderDetailSalesTermsDao = orderDetailSalesTermsDao;
	}

	/**
	 * orderImportDetailLogicを設定します。
	 * @param orderImportDetailLogic orderImportDetailLogic
	 */
	public void setOrderImportDetailLogic(
			OrderImportDetailLogic orderImportDetailLogic) {
		this.orderImportDetailLogic = orderImportDetailLogic;
	}

	/**
	 * articleAttributeQueueDaoを設定します。
	 * @param articleAttributeQueueDao articleAttributeQueueDao
	 */
	public void setArticleAttributeQueueDao(
			ArticleAttributeQueueDao articleAttributeQueueDao) {
		this.articleAttributeQueueDao = articleAttributeQueueDao;
	}

}
