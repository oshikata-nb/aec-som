/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.SendMailConstants;
import com.asahikaseieng.app.common.SendMailLogic;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.app.order.orderimport.OrderImportListAction.CheckRegist;
import com.asahikaseieng.app.shipping.ShippingConst;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetail;
import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetailDao;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHeadDao;
import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.entity.master.carry.Carry;
import com.asahikaseieng.dao.entity.master.carry.CarryDao;
import com.asahikaseieng.dao.entity.master.common.Common;
import com.asahikaseieng.dao.entity.master.common.CommonDao;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplateDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.orderimportfileout.OrderImportFileOut;
import com.asahikaseieng.dao.entity.orderimportfileout.OrderImportFileOutDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.nonentity.comboboxes.shipping.ShippingCarryForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.shipping.ShippingCarryForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.vendergroup.VenderGroupForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.vendergroup.VenderGroupForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.namesnolist.NamesNoListDao;
import com.asahikaseieng.dao.nonentity.master.orderstatuslist.OrderStatusList;
import com.asahikaseieng.dao.nonentity.master.orderstatuslist.OrderStatusListDao;
import com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailList;
import com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailListDao;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportList;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportListDao;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportListPagerCondition;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListForReport;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListForReportDao;
import com.asahikaseieng.dao.nonentity.orderimportsamelist.OrderImportSameListDao;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipConditionForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipDetailForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipDetailForReportDao;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipHeaderForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipHeaderForReportDao;
import com.asahikaseieng.dao.nonentity.orderimporttemplist.OrderImportTempList;
import com.asahikaseieng.dao.nonentity.orderimporttemplist.OrderImportTempListDao;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesList;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesListDao;
import com.asahikaseieng.dao.nonentity.repdeliverydatecontactheader.RepDeliveryDateContactHeader;
import com.asahikaseieng.dao.nonentity.repdeliverydatecontactheader.RepDeliveryDateContactHeaderDao;
import com.asahikaseieng.dao.nonentity.vmailvalueentity.VMailValueEntity;
import com.asahikaseieng.dao.nonentity.vmailvalueentity.VMailValueEntityDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;


/**
 * 受注取込一覧 LogicImplクラス
 * @author 
 */
public class OrderImportListLogicImpl implements OrderImportListLogic {
	
	/** エラーログ出力最大サイズ */
	private static int ERROR_LOG_SQL_STR_MAX_LEN = 2000;
	
	
	/** 取込ステータス:確定済*/
	private static BigDecimal IMPORT_STATUS_FIX = new BigDecimal("30");
	

	private GetNumberLogic getNumberLogic;

	private OrderImportListDao orderImportListDao;

	private OrderNamesListDao orderNamesListDao;

	private OrderStatusListDao orderStatusListDao;
	
	private BalanceDao balanceDao;
	
	private VenderDao venderDao;
		
	private OrderImportListForReportDao orderImportListForReportDao;
	
	private OrderImportSameListDao orderImportSameListDao;
	
	private SendMailLogic sendMailLogic;
	
	private VMailValueEntityDao vMailValueEntityDao;
	
	private RepDeliveryDateContactHeaderDao repDeliveryDateContactHeaderDao;
	
	private MailTemplateDao mailTemplateDao;
	
	private ErrorLogDao errorLogDao;

	private OrderImportTempListDao orderImportTempListDao;
	
	private NamesNoListDao namesNoListDao;
	
	private OrderImportFileOutDao orderImportFileOutDao;
	
	private CommonDao commonDao;
	
	private CarryDao carryDao;
	
	private RepOrderImportSlipHeaderForReportDao repOrderImportSlipHeaderForReportDao;
	
	private RepOrderImportSlipDetailForReportDao repOrderImportSlipDetailForReportDao;
	
	/** 運送会社コンボボックス用DAO */
	private ShippingCarryForComboboxesDao shippingCarryForComboboxesDao;
	
	/** 得意先グループコンボボックス用DAO*/
	private VenderGroupForComboboxesDao venderGroupForComboboxesDao;
	
	private OrderImportDetailListDao orderImportDetailListDao;
	private OrderImportCommonLogic orderImportCommonLogic;

	private FrstOrderHeadDao frstOrderHeadDao;
	private FrstOrderDetailDao frstOrderDetailDao;
	
	/**
	 * コンストラクタ.
	 */
	public OrderImportListLogicImpl() {
	}

	/**
	 * 受注一覧検索
	 * @param condition 検索条件
	 * @return List<OrderImportList>
	 * @throws NoDataException NoDataException
	 */
	public List<OrderImportList> getList(final OrderImportListForm form,
			final OrderImportListPagerCondition condition,
			final String venderCd) throws NoDataException {
		
		OrderImportListForm frm = (OrderImportListForm) form;
		/* パラメータチェック */
		checkParams(condition);

		// 2次店以下で検索できるよう追加
		condition.setSrhVenderList(getVenderList(venderCd));

		List<OrderImportList> list = orderImportListDao.getSearchList(condition);
		
		//排他処理用に検索時の結果を保存
		frm.setBeforeSearchList(list);
		
		//検索リストの件数保持
		frm.setSearchListCount(list.size());
		
		for(OrderImportList bean : list){
 			bean.setStrScheduledShippingDate(AecDateUtils.dateFormat(bean.getScheduledShippingDate(), "yyyy/MM/dd"));
			bean.setStrDeliveryDate(AecDateUtils.dateFormat(bean.getDeliveryExpectedDate(), "yyyy/MM/dd"));
			bean.setStrOrderQty(AecNumberUtils.decimalFormatEx(bean.getOrderQty()));
			bean.setStrMatss(AecNumberUtils.decimalFormatEx(bean.getMatss()));
			bean.setBeforeOrderQty(bean.getOrderQty());
			bean.setBeforeMatss(bean.getMatss());

		}
		
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		
		return list;
	}

	/**
	 * 
	 * 上位の得意先コードを取得する
	 * @param venderCd
	 * @return
	 */
	private ArrayList<String> getVenderList(final String venderCd) {

		ArrayList<String> venderList = new ArrayList<String>();
		List<Balance> list = balanceDao.getList(venderCd);
		if (list == null) {
			return null;
		} else {
			venderList.add(venderCd);
		}

		String upperBalanceCd = "";

		for (Balance bean : list) {
			// 上位帳合コードある場合のみ処理を行う
			if (bean.getUpperBalanceCd() != null) {
				upperBalanceCd = bean.getUpperBalanceCd();
				while (true) {

					Balance balance = balanceDao.getEntity(upperBalanceCd);
					// 上位帳合コードがある場合
					if (balance != null) {
						// 得意先コードがある場合リストに追加
						if (balance.getVenderCd() != null) {
							venderList.add(balance.getVenderCd());
						}
						// 上位帳合コードがある場合、上位帳合コードで検索を行う
						if (balance.getUpperBalanceCd() != null) {
							upperBalanceCd = balance.getUpperBalanceCd();
						} else {
							break;
						}

					} else {
						break;
					}
				}
			}
		}
		return venderList;
	}

	/**
	 * 受注ヘッダ一覧（帳票用）
	 * @param condition 検索条件
	 * @return List<OrderImportListForReport>
	 */
	public List<OrderImportListForReport> getHeaderListForReport(final OrderImportListConditionForReport condition) {
		List<OrderImportListForReport> headerList = orderImportListForReportDao.getHeaderListForReport(condition);
		if (headerList.isEmpty()) {
			return null;
		}

		return headerList;
	}
	
	/**
	 * 受注詳細一覧（帳票用）
	 * @param condition 検索条件
	 * @return List<OrderImportListForReport>
	 */
	public List<OrderImportListForReport> getDetailListForReport(final OrderImportListConditionForReport condition) {
		List<OrderImportListForReport> detailList = orderImportListForReportDao.getDetailListForReport(condition);
		if (detailList.isEmpty()) {
			return null;
		}

		return detailList;
	}
	
	/**
	 * 確定ボタン押下時処理　受注取込テーブル確定処理を行う
	 * 
	 * @param searchList
	 * @param tantoCd
	 * @return ActionMessages
	 * @throws NoDataException
	 * @throws Exception
	 */
	public CheckRegist fix(final OrderImportListForm form,
			final String tantoCd, final String tantoOrgCd) throws NoDataException, Exception{
		
		CheckRegist checkRegist = new CheckRegist();
		OrderImportListForm frm = (OrderImportListForm) form;
		List<OrderImportList> searchList = frm.getSearchList();
		int i = 0;
		int rowNum = 0;

		if (searchList == null || searchList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}

		// リストでループ
		for (OrderImportList bean : searchList) {
			rowNum++;
			// ランク1のもののみ、行番号をインクリメント（詳細行はカウントしない）
			if( bean.getRank().equals("1") ){
				i ++;
			}
			
			if(!bean.isOrderImportCheckBox()){
				//チェック無
				continue;
			}

			// 受注番号の取得
			String orderNo = getNumberLogic.getSupplierOrderNo();
	
			// 受注ヘッダ情報の取得
			FrstOrderHead frstHeadBean = this.frstOrderHeadDao
					.getEntity(bean.getFrstOrderNo());
	
			// 削除、またはキャンセルされたヘッダ情報の場合、スキップ
			if( frstHeadBean.getDelFlg().intValue() == 1 || frstHeadBean.getCancelFlg().intValue() == 1 ){
				continue;
			}
			
			// 確定済みの受注の場合、スキップ
			if( AecStringUtils.isNotNullAndEmpty( frstHeadBean.getOrderNo() )){
				continue;
			}
			
			// ヘッダの登録先の確認
			OrderImportDetailList concliftOrder =  orderImportDetailListDao.getConcliftFrstOrderNo(frstHeadBean.getOrderDivision()
				 , frstHeadBean.getVenderCd()
				 , frstHeadBean.getDeliveryCd()
				 , frstHeadBean.getBalanceCd()
				 , frstHeadBean.getScheduledShippingDate()
				 , frstHeadBean.getDeliveryExpectedDate()
				 , frstHeadBean.getCarryCd()
				 , frstHeadBean.getFrstOrderNo()
				 , frstHeadBean.getDeliveryAddress()
				 , frstHeadBean.getDeliveryTelNo());
			
			// 重複するヘッダがあった場合、エラー
			if( concliftOrder != null ){
				//{0}行目 重複する受注があるため、詳細画面から確定を行ってください。
				checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.conflict.error", i));
				continue;
			}
			
			List<OrderImportDetailList> updateDetailList = orderImportDetailListDao.getList(frstHeadBean.getFrstOrderNo());

			// 承認済みチェック
			if( !this.isApprovaled(updateDetailList, checkRegist,i)){
				continue;
			}
			
			// 全てキャンセルの受注チェック
			if( this.isAllCanceled(updateDetailList, checkRegist,i) ){
				continue;
			}

			// メッセージ
			ActionMessages messages = null;
			
			String orderDate = AecDateUtils.formatStringFromTimestamp(
				frstHeadBean.getOrderDate(), "yyyy/MM/dd");

			String scheduledShippingDate = AecDateUtils.formatStringFromTimestamp(
				frstHeadBean.getScheduledShippingDate(), "yyyy/MM/dd");

			String deliveryExpectedDate = AecDateUtils.formatStringFromTimestamp(
				frstHeadBean.getDeliveryExpectedDate(), "yyyy/MM/dd");


			// 日付チェック
			 messages = this.orderImportCommonLogic.checkValidDate(0 , orderDate,scheduledShippingDate
				 , deliveryExpectedDate);

			if (messages.size() > 0) {
				// rowNum行目でエラーが発生しました。
				checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.listerror", rowNum));
				checkRegist.errorMsg.add(messages);
				continue;
			}

			// マスタチェック
			messages = new ActionMessages();
			// 帳合コード
			orderImportCommonLogic.checkBalance(frstHeadBean.getBalanceCd(), messages);
			// 取引先コード
			orderImportCommonLogic.checkVender(frstHeadBean.getVenderCd(), messages);
			// 品目マスタ
			orderImportCommonLogic.checkItem(updateDetailList , 0, messages);
			// 販売条件マスタ
			orderImportCommonLogic.checkSalesTerms(updateDetailList, frstHeadBean.getBalanceCd() , frstHeadBean.getDeliveryCd() , 0 , messages);
			// 受注区分で品目チェック
			orderImportCommonLogic.checkItemTypeDivision( updateDetailList , frstHeadBean.getOrderDivision().toString() , 0,  messages);

			if (!messages.isEmpty()) {
				// rowNum行目でエラーが発生しました。
				checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.listerror", rowNum));
				checkRegist.errorMsg.add(messages);
				continue;
			}

			// 数量のチェック
			messages = orderImportCommonLogic.checkOrderQuantity(frstHeadBean.getOrderDivision().toString(),
				updateDetailList, frstHeadBean.getFrstOrderNo());

			if (!messages.isEmpty()) {
				// rowNum行目でエラーが発生しました。
				checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.listerror", rowNum));
				checkRegist.errorMsg.add(messages);
				continue;
			}
			
			// データベースの更新処理
			orderImportCommonLogic.fixUpdate(tantoCd, tantoOrgCd, updateDetailList,
				updateDetailList, orderNo, frstHeadBean);		
			
			// 更新件数を追加
			checkRegist.updateNums++;
			
		}

		return checkRegist;

	}
	
	
	/**
	 * 承認済みチェック処理
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean isApprovaled(final List<OrderImportDetailList> list,CheckRegist checkRegist , int rowNum) {

		boolean hasNotApprovaled = false;
		// 受注データをループして、削除、キャンセルまたは未承認の情報がないかを確認
		for (OrderImportDetailList bean : list) {
			int approvalFlg = bean.getInputApprovalFlg().intValue();
			int deleteFlg = bean.getDelFlg().intValue();
			int cancelFlg = bean.getCancelFlg().intValue();
			
			if( deleteFlg == 1 || cancelFlg == 1 || approvalFlg == 1 ){
				continue;
			}else{
				hasNotApprovaled = true;
				break;
			}
				
		}

		if (hasNotApprovaled) {
			//{0}行目 未承認の受注があります。承認処理を行ってください。
			checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.not.approvalrow", rowNum));
			return false;
		}
		return true;
	}	
	
	/**
	 * 全てキャンセルされた行のチェック処理
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean isAllCanceled(final List<OrderImportDetailList> list,CheckRegist checkRegist , int rowNum) {

		boolean hasActiveOrder = false;
		// 受注データをループして、削除、キャンセルまたは未承認の情報がないかを確認
		for (OrderImportDetailList bean : list) {
			int deleteFlg = bean.getDelFlg().intValue();
			int cancelFlg = bean.getCancelFlg().intValue();
			
			if( deleteFlg == 1 || cancelFlg == 1  ){
				continue;
			}else{
				hasActiveOrder = true;
				break;
			}
				
		}

		if (!hasActiveOrder) {
			return true;
		}
		return false;
	}	
	
	
	
	/**
	 * 受注取込テーブル引当解除処理を行う
	 * 
	 * @param searchList
	 * @throws NoDataException
	 * @throws Exception
	 */
	public CheckRegist orderRelease(final OrderImportListForm frm,
			final String tantoCd) throws NoDataException, Exception{

		CheckRegist checkRegist = new CheckRegist();
		List<OrderImportList> searchList = frm.getSearchList();

		if (searchList == null || searchList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}
		int rowNum = 0;

		// リストでループ
		for (OrderImportList bean : searchList) {
			rowNum++;
			
			if(!bean.isOrderImportCheckBox()){
				//チェック無
				continue;
			}

			
			try {
				// 受注ヘッダ取得
				String orderNo = bean.getOrderNo();
				String orderDivision = bean.getOrderDivision().toString();
				String frstOrderNo = bean.getFrstOrderNo();

				// 受注ヘッダ情報の取得
				FrstOrderHead frstHeadBean = this.frstOrderHeadDao
						.getEntity(bean.getFrstOrderNo());
				
				List<OrderImportList> orderImportList = new ArrayList<OrderImportList>();
				for (OrderImportList checkedBean : searchList){
					if( checkedBean.getFrstOrderNo().equals(bean.getFrstOrderNo()) ){
						orderImportList.add(checkedBean);
					}
				}
				
				// 削除、またはキャンセルされたヘッダ情報の場合、スキップ
				if( frstHeadBean.getDelFlg().intValue() == 1 || frstHeadBean.getCancelFlg().intValue() == 1 ){
					continue;
				}
				
				boolean hasError = false;
				
				for(OrderImportList orderImportRow : orderImportList ){
					
				
					// ##############################################
					// #####変更不可、可能判定処理 ########
					// ##############################################
					// 受注区分が１在庫引当か４例外出荷の場合
					if (frstHeadBean.getOrderDivision().intValue() == 1 
							|| frstHeadBean.getOrderDivision().intValue() == 4) {
						if (orderImportRow.getOrderStatus() == null && !AecStringUtils.isNotNullAndEmpty(orderImportRow.getOrderNo())) {
							// rowNum行目でエラーが発生しました。
							checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.orderrelease", rowNum));
							hasError = true;
							break;
						}
						if ( orderImportRow.getOrderStatus() != null && ( !(orderImportRow.getOrderStatus().equals(new BigDecimal("99")) || orderImportRow.getOrderStatus().equals(new BigDecimal("90"))))) {
							// ステータスに受注登録、先付受注以外のものがあれば変更不可
							checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.orderrelease", rowNum));
							hasError = true;
							break;
						}
					}
					// 受注区分が３仕入直送品の場合
					if (frstHeadBean.getOrderDivision().intValue() == 3) {
						if ( orderImportRow.getPurchaseStatus().intValue()  != PurchaseStatus.NOT_ISSUE.intValue() ) {
							// 購買ステータスが未発行以外の場合は変更不可
							checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.orderrelease", rowNum));
							hasError = true;
							break;
						}
					}
					// 受注区分が２受注生産の場合
					if (frstHeadBean.getOrderDivision().intValue() == 2) {
						if (orderImportRow.getAspStatus() != null
								&& orderImportRow.getOrderStatus() != null) {
							if (orderImportRow.getAspStatus().equals("B")
									|| orderImportRow.getAspStatus().equals("D")
									|| !(orderImportRow.getOrderStatus().equals(new BigDecimal("99")) || orderImportRow.getOrderStatus().equals(new BigDecimal("90")))) {
								checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.orderrelease", rowNum));
								hasError = true;
								break;
							}
						}
					}
					
					// 出荷レコードが作成されている場合、変更不可
					if( bean.getShippingStatus() != null ){
						checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.orderrelease", rowNum));
						hasError = true;
						break;
					}
					
				}
				
				// 受注の削除処理
				if( !hasError && AecStringUtils.isNotNullAndEmpty(orderNo) ){

					//20220512 add S.Fujimaki受注詳細　排他追加
					List<OrderImportDetailList> detailList = new ArrayList<OrderImportDetailList>();//orderImportDetailListDao.getList(frstOrderNo);
					List <OrderImportList> beforeList = frm.getBeforeSearchList();
					for(OrderImportList orderList : beforeList){

						if( orderList.getFrstOrderNo().equals(bean.getFrstOrderNo()) ){
							//画面データから排他情報をセットする。
							OrderImportDetailList detail = new OrderImportDetailList();
							detail.setOrderNo(orderList.getOrderNo());
							detail.setRowNo(orderList.getRowNo());
							detail.setOrderDetailDate(orderList.getOrderDetailDate());
							detailList.add(detail);
						}
					}
					//20220512 add S.Fujimaki受注詳細　排他追加
					orderImportCommonLogic.deleteOrder(tantoCd, orderNo, orderDivision, frstOrderNo, detailList);
					checkRegist.updateNums++;
				}

			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 更新エラー OptimisticLockRuntimeExceptionをthrowする
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				throw new DuplicateRuntimeException(0);
			}
			
		}
		return checkRegist;
	}
	
	
	/**
	 * 納期連絡表返信(メール,FAX)処理
	 * 
	 * @param dm　納期連絡メール用データモデル
	 * @param filePathList
	 * @param deliveryCd 
	 * @param tantoCd　担当者コード
	 * @param actionSeq　メール送信シーケンス番号
	 * @return　CheckRegist 送信結果
	 * @throws MessagingException
	 */
	public CheckRegist sendMail(final OrderImportDeliveryDateDataModel dm, List<String> filePathList , final String deliveryCd , final String tantoCd, final BigDecimal actionSeq) throws MessagingException {
		CheckRegist checkRegist = new CheckRegist();
		
		
		//取引先コード取得
		FrstOrderHead headBean = dm.getHeadBean();
		FrstOrderDetail detailBean = dm.getDetailBean();
		Vender venderBean = dm.getVenderBean();
		
		int rowNum = dm.getRowNum();
				
		// 更新対象データを取得する
		String mailFormatId = null;
		List<String> subjectParamCsv = createMailSubjectParam(detailBean.getFrstOrderNo(), detailBean.getFrstOrderRowNo(), tantoCd);
		
		
		// 受注メール・FAXの送信区分がどちらも1:送信するではない場合、エラー
		if(AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getOrderFaxOutput()).compareTo(BigDecimal.ONE) != 0 &&
				AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getOrderMailOutput()).compareTo(BigDecimal.ONE) != 0){
			// {0}行目:受注メール、受注FAXの送信区分がどちらも「送信しない」となっているため、送信できません。
			checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.mail.fax.output", rowNum));
		}
		
		boolean successed = false;
		
		// 受注メールの送信区分が1:送信するの場合メール送信
		if(AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getOrderMailOutput()).compareTo(BigDecimal.ONE) == 0){
			mailFormatId = "O_10001";
			List<String> bodyParamCsv = createMailBodyParamCsv(mailFormatId, detailBean.getFrstOrderNo(), detailBean.getFrstOrderRowNo(), tantoCd);
			// メール送信
			boolean sendMail = sendMailLogic.sendMailVender("TS", headBean.getVenderCd(), mailFormatId, subjectParamCsv,
				bodyParamCsv, filePathList, tantoCd, SendMailConstants.SEND_MODE_MAIL_ONLY, SendMailConstants.GET_ORDER_INFO, actionSeq);
			//メール送信失敗時はエラーメッセージ追加
			if(!sendMail){
				//{0}行目:メール送信時に問題が生じました。
				checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.mail.send.row", rowNum));
			}
			//メール送信成功時には客先送信関連のパラメータ更新
			if(sendMail){
				checkRegist.updateNums += 1;// 更新件数カウント
			}
			if(!successed){
				successed = sendMail;
			}
		}
		AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getOrderMailOutput());
		// 受注FAXの送信区分が1:送信するの場合FAX送信
		if(AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getOrderFaxOutput()).compareTo(BigDecimal.ONE) == 0){
			mailFormatId = "O_20001";
			List<String> bodyParamCsv = createMailBodyParamCsv(mailFormatId, detailBean.getFrstOrderNo(), detailBean.getFrstOrderRowNo(), tantoCd);
			// メール送信
			boolean sendMail = sendMailLogic.sendMailVender("TS", headBean.getVenderCd(), mailFormatId, subjectParamCsv, 
				bodyParamCsv, filePathList, tantoCd, SendMailConstants.SEND_MODE_AUTO_FAX, SendMailConstants.GET_ORDER_INFO, BigDecimal.ZERO);
			//FAX送信失敗時はエラーメッセージ追加
			if(!sendMail){
				//{0}行目:FAX送信時に問題が生じました。
				checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.fax.send.row", rowNum));
			}
			//FAX送信成功時には客先送信関連のパラメータ更新
			if(sendMail){
				checkRegist.updateNums += 1;// 更新件数カウント
			}
			if(!successed){
				successed = sendMail;
			}

		}
		
		return checkRegist;
	}

	/**
	 * 納期連絡メール用データモデルの追加
	 * @param dmMap
	 * @param bean
	 * @param filePathList
	 * @param rowNum
	 */
	public void addOrderImportDeliveryDateDataModel(
			HashMap<String , OrderImportDeliveryDateDataModel > dmMap
			, OrderImportList bean
			, int rowNum){
		
		// Beanの作成
		FrstOrderHead headBean  = frstOrderHeadDao.getEntity(bean.getFrstOrderNo());
		FrstOrderDetail detailBean = frstOrderDetailDao.getEntity(bean.getFrstOrderNo(),bean.getFrstOrderRowNo());

		//取引先から受注メールと受注FAXの送信区分を取得
		Vender venderBean = venderDao.getEntity(headBean.getVenderCd(), "TS");

		
		if( !dmMap.containsKey( venderBean.getVenderCd() ) ){
			OrderImportDeliveryDateDataModel dm = new OrderImportDeliveryDateDataModel();
			dm.setDetailBean(detailBean);
			dm.setCheckedList( new ArrayList<OrderImportList>() );
			dm.setHeadBean(headBean);
			dm.setRowNum(rowNum);
			dm.setVenderBean(venderBean);
			dmMap.put(venderBean.getVenderCd(), dm);
		}
		dmMap.get(venderBean.getVenderCd()).getCheckedList().add(bean);

		
	}
	
	/**
	 * メール、FAX返信(ファイル)処理
	 * 
	 * @param frm
	 * @param tantoCd　担当者コード
	 * @param actionSeq メール送信シーケンス番号
	 * @return　CheckRegist
	 * @throws MessagingException
	 */
	public CheckRegist sendFileToMail(final OrderImportListForm frm, final String tantoCd, final BigDecimal actionSeq) throws MessagingException {
		CheckRegist checkRegist = new CheckRegist();
		
	   	long row = 1;
	   	int csvColumnSize =22;
    	int exportColumnNo = 0;
    	int thisColumn = 0;
    	String reciveFileName = "";
    	String sendFileName= "";

		// =======================================================================================================
		// ①初期化処理
		// =======================================================================================================
		//画面からチェックを入れたデータをテーブルに格納用の初期定義
		BigDecimal sequenceNo = namesNoListDao.getNo("CD");
		OrderImportFileOut insert= new OrderImportFileOut();
		insert.setSeq(sequenceNo);
		insert.setInputDate(AecDateUtils.getCurrentTimestamp());
		insert.setInputorCd(tantoCd);
		insert.setUpdateDate(AecDateUtils.getCurrentTimestamp());
		insert.setUpdatorCd(tantoCd);

		// DBからディレクトリがなければ作成
		Common common = commonDao.getEntity("ORDER_IMPORT_REPLY");
		File savDir = new File(common.getCommonValue());
		if(!savDir.exists()){
			savDir.mkdirs();
		}
		
		// =======================================================================================================
		// ②出力対象リストを作成。一覧画面の検索結果のループ処理（チェックボックスがONに対して処理を行う。
		// =======================================================================================================
		for(OrderImportList bean : frm.getSearchList()){
		
			// チェックボックスがONか判断し、ON明細のリストを作成する
			if (bean.isOrderImportCheckBox() ) {
				// チェックを入れたデータをテーブルに格納
				insert.setPkNo(bean.getFrstOrderNo());
				insert.setPkRow(bean.getFrstOrderRowNo());
				insert.setScreenRow(new BigDecimal(row));
				insert.setDeliveryName(bean.getDeliveryShortedName());
				insert.setItemName(bean.getItemName());
				orderImportFileOutDao.insert(insert);
				
			}// チェックボックスがONか判断し、ON明細のリストを作成する
			row++;
		}// 一覧画面の検索結果のループ処理（チェックボックスがONに対して処理を行う
		
		// =======================================================================================================
		// ③チェック処理
		// =======================================================================================================
		Boolean errorFlag = Boolean.FALSE;
		List<OrderImportTempList> checkList = orderImportTempListDao.getCheckList(insert.getSeq().toString());
		for(OrderImportTempList check : checkList){
			if(check.getImportDataNum().compareTo(BigDecimal.ZERO)< 1 ){
				
				// errors.orderimport.sendfile.datacount={0}行目:納入先:{1} 品目:{2} ファイル取込のデータがないので、ファイル返信できません。
				checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.sendfile.datacount", check.getScreenRow().toString(),check.getDeliveryName(),check.getItemName()));

				errorFlag = Boolean.TRUE;
			}
			
			// 確定ではない場合エラーとする。
			if(!check.getImportStatus().equals(IMPORT_STATUS_FIX.toString())){
				// errors.orderimport.sendfile.datacount={0}行目:納入先:{1} 品目:{2} ファイル取込のデータがないので、ファイル返信できません。
				checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.sendfile.status", check.getScreenRow().toString(),check.getDeliveryName(),check.getItemName()));

				errorFlag = Boolean.TRUE;
				
			}

			// 受注メール送信区分が送信しないの場合エラーとする。
			if(!check.getOrderMailOutput().equals("1")){
				checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.sendfile.mailoutput", check.getScreenRow().toString(),check.getDeliveryName(),check.getItemName()));
				errorFlag = Boolean.TRUE;
			}else{
				// メール送信設定が送信するの場合、メールアドレスのチェックを行う。
				// 1次店取引先マスタのメールアドレスが設定されていません。
				if(check.getOrderMailAddress1() != null &&!check.getOrderMailAddress1().isEmpty() 
						&& check.getOrderMailAddress2() != null &&  !check.getOrderMailAddress2().isEmpty() 
						&& check.getOrderMailAddress3() != null && !check.getOrderMailAddress3().isEmpty()){
					// errors.orderimport.sendfile.mail={0}行目:納入先:{1} 品目:{2} 1次店・取引先マスタのメールアドレスが設定されていません。
					checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.sendfile.mail", check.getScreenRow().toString(),check.getDeliveryName(),check.getItemName()));
					errorFlag = Boolean.TRUE;
					
				}
			}
		}
		
		if (errorFlag){
			return checkRegist;
		}
		
		// =======================================================================================================
		// ③ファイル作成前準備（APのデータの取得と運賃・エラーのコメントを取得等）
		// =======================================================================================================
		// 受注画面からチェックをした、受注取込のリストから、受注ファイルデータ一時取込テーブルから取得する。
		List<OrderImportTempList> detailList = orderImportTempListDao.getDetailList(insert.getSeq().toString());
		
		// AEC用のデータ用のリストを作成（受注取込のデータ）
		List<OrderImportTempList> aecList = orderImportTempListDao.getAecDataList(insert.getSeq().toString());
		
		// AECリストでループ
        for(int i = 0 ; i < aecList.size();i++){
        	
        	OrderImportTempList aec = aecList.get(i);
        	
        	// チェック用のメッセージを作成
        	String checkMsg = AecStringUtils.changeNullExist_(aec.getCheck01())  + AecStringUtils.changeNullExist_(aec.getCheck02())
        			+ "_" + AecStringUtils.changeNullExist_(aec.getCheck02()) + AecStringUtils.changeNullExist_(aec.getCheck03())
        			+ "_" + AecStringUtils.changeNullExist_(aec.getCheck04()) + AecStringUtils.changeNullExist_(aec.getCheck05())
        			+ "_" + AecStringUtils.changeNullExist_(aec.getCheck06()) + AecStringUtils.changeNullExist_(aec.getCheck07())
        			+ "_" + AecStringUtils.changeNullExist_(aec.getCheck08()) + AecStringUtils.changeNullExist_(aec.getCheck09())
        			+ "_" + AecStringUtils.changeNullExist_(aec.getCheck10());
        	// 不要な文字列を削除
        	checkMsg = checkMsg.replace("__", "_").replace("__", "_").replace("__", "_");
        	
        	if(checkMsg.equals("_")){
        		checkMsg = "";
        	}
        	
        	aec.setCheckAll(checkMsg);
        	
        	// 同一の運賃グループの運賃をゼロとする。
        	for(int j = i+1; j < aecList.size();j++){
        		
            	OrderImportTempList check = aecList.get(j);
            	
            	if(check.getCheckFlag() != null &&check.getCheckFlag().equals("1")){
            		break;
            	}
            	
            	// 運送会社グループ（運送会社計上のグループが同一の場合）を判定
        		if(aec.getCarryGroup().equals(check.getCarryGroup())){
        			// 同一運送会社計上グループの場合、最初以外運賃をゼロとする。
        			check.setCarryFare(BigDecimal.ZERO);
        			check.setCheckFlag("1");
        			
        		}
        	}
        }
		// 受信したファイル名を取得しファイル名と拡張子に分割
		reciveFileName = detailList.get(0).getOrderFileName();
		String fileName = reciveFileName.substring(0,reciveFileName.lastIndexOf('.'));
		String ext = reciveFileName.substring(reciveFileName.lastIndexOf(".") + 1);
		
		// 得意先コードを取得（この単位にファイルを作成し、メールを送信する。
		String mailSendVenderCd = detailList.get(0).getVenderCd();
		
		// 返信用ファイル名を作成。　commonテーブルのパス+ファイル名+ セッツ返信メッセージ+日付+拡張子
		sendFileName = common.getCommonValue()  + mailSendVenderCd  +"_SOM_" + AecDateUtils.getCurrentTimestamp().toString().replace("-", "").replace(":", "").replace(".", "").replace(" ", "") + "." + ext;
		
		// =======================================================================================================
		// ④ファイル作成処理
		// =======================================================================================================
		// 画面からチェックを行ったデータのループ
        // 出力ファイルの作成
        try {
        	row = 1;
        	// 受注ファイルデータ一時取込テーブルの行を特定するキー
        	String nowTempNo = "";					// 一時取込データの番号
        	String title = "";					// 一時取込データの番号
        	BigDecimal nowRow = BigDecimal.ZERO;	// 一時取込データの行番号
        	
	        FileWriter fileWriter = new FileWriter(sendFileName, false);
	        PrintWriter printF = new PrintWriter(new BufferedWriter(fileWriter));

	        // -------------------------------------------------------------------------------------------------
	        // ヘッダ部を作成
	        // -------------------------------------------------------------------------------------------------
	        for(OrderImportTempList header : detailList){
	        	if(row == 1){
	        		exportColumnNo = 0;
	        		nowTempNo= header.getTempNo();
	        		nowRow = header.getCellRowNumber();
	        	}else{
	        		// 一行目以外の場合、行が変わったか判定する
	        		if(detailList.size() == row ||(!nowTempNo.equals(header.getTempNo()) || !nowRow.equals(header.getCellRowNumber()))){
	        			if(detailList.size() == row){
	                		title = title + header.getOrderFileColumnName();
	        	        	title = title +","; 
	        			}

	        			if(exportColumnNo < csvColumnSize){
    	    				for(;exportColumnNo < csvColumnSize;exportColumnNo++){
    	    		        	title = title +","; 
    	    				}
    	    				exportColumnNo++;
    	    			}
	        			// ここにAP-21の項目を追加
	        			title = title + "_納入予定日,";
	        			title = title + "_個数,";
	        			title = title + "_運賃,";
	        			title = title + "_読込区分 OK/NG,";
	        			title = title + "_状態　確定／未確定,";
	        			title = title + "_コメント,";
	        			title = title + "_取込グループ";
	        				        			
	        			break;
	        		}
	        	}
				// 列スキップを検知したとき、カンマで埋める。
    			thisColumn = header.getCellColumnNumber().intValue();
    			if(thisColumn > exportColumnNo){
    				for(;exportColumnNo < thisColumn-1;exportColumnNo++){
    		        	title = title +","; 
    				}
    				exportColumnNo++;;
    			}
        		title = title + header.getOrderFileColumnName();
	        	title = title +","; 
	        	row++;
	        }
            printF.print(AecStringUtils.changeNull(title));
            printF.println();    // 改行
	        
        	row = 1;
        	nowTempNo = "";					// 一時取込データの番号
        	OrderImportTempList beforeDetail = new OrderImportTempList();

	        // -------------------------------------------------------------------------------------------------
	        // データ部を作成
	        // -------------------------------------------------------------------------------------------------
//	        for(OrderImportTempList detail : detailList){
			for(int iDetail = 0;iDetail <detailList.size();iDetail++){
				
				OrderImportTempList detail = detailList.get(iDetail);
	        	
	        	if(row == 1){
	        		// 一行目の場合、行を特定する情報を保持する
	        		nowTempNo= detail.getTempNo();
	        		nowRow = detail.getCellRowNumber();
	        		mailSendVenderCd = detail.getVenderCd();

	        		exportColumnNo = 0;
    				// 列スキップを検知したとき、カンマで埋める。
        			thisColumn = detail.getCellColumnNumber().intValue();
        			if(thisColumn > exportColumnNo){
        				for(;exportColumnNo < thisColumn-1;exportColumnNo++){
        		        	printF.print(","); 
        				}
        				exportColumnNo++;;
        			}
		            printF.print(AecStringUtils.changeNull(detail.getCellData()));
		            printF.print(",");
	        	}else{
	        		// 一行目以外の場合、受信ファイルの行が変わったか判定する
	        		if(detailList.size() == row ||(!nowTempNo.equals(detail.getTempNo()) || !nowRow.equals(detail.getCellRowNumber()))){
	        			// 受信ファイルの行が変わった場合
	        			
	        			// APの情報を表示する
//	        	        for(OrderImportTempList aecData:aecList){
	        			for(int iAec = 0;iAec <aecList.size();iAec++){
	        				
	        				OrderImportTempList aecData = aecList.get(iAec);
	        	        	
	        	        	// 受注取込の主キーが変わった場合、ファイルの改行判定とAEC情報をファイルに添付
	        	        	if(aecData.getPkNo().equals(beforeDetail.getPkNo()) && aecData.getPkRow().equals(beforeDetail.getPkRow())){
	        	        		
			        			if(detailList.size() == row){
			        				// 最終行最終列で列スキップを検知したとき、カンマで埋める。データ設定するため-1
				        			if(exportColumnNo < detail.getCellColumnNumber().intValue()){
			    	    				for(;exportColumnNo < detail.getCellColumnNumber().intValue()-1;exportColumnNo++){
			    	    					printF.print(",");
			    	    				}
			    	    				exportColumnNo++;
			    	    			}
			        				// 最終行の場合、データを設定する
				    	            printF.print(AecStringUtils.changeNull(detail.getCellData()));
				    	            printF.print(",");
			        				
			        			}
		        				// 最終列で列スキップを検知したとき、カンマで埋める。　
			        			if(exportColumnNo < csvColumnSize){
		    	    				for(;exportColumnNo < csvColumnSize;exportColumnNo++){
		    	    					printF.print(",");
		    	    				}
		    	    				exportColumnNo++;
		    	    			}
		        	            printF.print(AecStringUtils.changeNull(aecData.getDeliveryExpectedDate() + ","));	// 納入予定日
		        	            printF.print(AecStringUtils.changeNull(aecData.getOrderQty().toString() + ","));	// 個数
		        	            printF.print(AecStringUtils.changeNull(aecData.getCarryFare() + ","));	// 運賃
		        	            printF.print(AecStringUtils.changeNull(aecData.getErrorStatus() + ","));	// 読込区分（OK,NG）
		        	            printF.print(AecStringUtils.changeNull(aecData.getImportStatus() + ","));	// 状態（確定,未確定）
		        	            printF.print(AecStringUtils.changeNull(aecData.getCheckAll() + ","));	// AECコメント
		        	            printF.print(AecStringUtils.changeNull(aecData.getCarryGroup()));	// 運送会社グループ
		        				exportColumnNo = 1;
			        			
			        			// 最終行の場合かまたは次データと取引先が異なる場合、メール送信を行う
			        			if(detailList.size() == row || (detailList.size() != 0 && !detail.getVenderCd().equals(detailList.get(iDetail-1).getVenderCd()))){
			        		        // ファイルに書き出し閉じる
			        		        printF.close();
			        		        
			        		        // -------------------------------------------------------------------------------------------------
			        		        // メール送信処理実行
			        		        // -------------------------------------------------------------------------------------------------
			        		        boolean sendMail= sendFileToMail_SendProc(aecData.getPkNo(),aecData.getPkRow(),detailList.get(iDetail-1).getVenderCd(),tantoCd,sendFileName, actionSeq);
			        		        
			        				//FAX送信失敗時はエラーメッセージ追加
			        				if(!sendMail){
			        					//{0}行目:FAX送信時に問題が生じました。
			        					checkRegist.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.sendfile.mailsend", aecData.getVenderShortedName()));
			        				}

			        				// 最終行ではない場合、次のファイルを準備
			        				if(detailList.size() != row){
				        				// 返信用ファイル名を作成。　commonテーブルのパス+ファイル名+ AEC返信メッセージ+日付+拡張子
				        				sendFileName = common.getCommonValue() + fileName + "_" + detailList.get(iDetail).getVenderCd() +  "_" +"_SOM_" + AecDateUtils.getCurrentTimestamp().toString().replace("-", "").replace(":", "").replace(".", "").replace(" ", "") + "." + ext;
				        		        fileWriter = new FileWriter(sendFileName, false);
				        		        printF = new PrintWriter(new BufferedWriter(fileWriter));
				        	            printF.print(AecStringUtils.changeNull(title));
			        					
			        				}
			        				
			        			}
			        			// 行が変わった場合、の処理
			        			if(detailList.size() != row){
			        	            printF.println();    // 改行
			        	            //入力前はカラム0をセットして空行
			            			thisColumn = detail.getCellColumnNumber().intValue();
			            			if(thisColumn > exportColumnNo){
				        	            exportColumnNo = 0; //入力前なので0:値未セット
			            				for(;exportColumnNo < thisColumn-1;exportColumnNo++){
			            		        	printF.print(","); 
			            				}
			            				exportColumnNo++;;
			            			}
				    	            printF.print(AecStringUtils.changeNull(detail.getCellData()));
				    	            printF.print(",");
			        			}
	        	        		
	        	        		break;
	        	        	}
	        	        	
	        	        }
		        		nowTempNo= detail.getTempNo();
		        		nowRow = detail.getCellRowNumber();
	        	        
	        		}else{
	        			// 行が変わっていない場合、データをファイルに出力

        				// 列スキップを検知したとき、カンマで埋める。
            			thisColumn = detail.getCellColumnNumber().intValue();
            			if(thisColumn > exportColumnNo){
            				for(;exportColumnNo < thisColumn-1;exportColumnNo++){
            		        	printF.print(","); 
            				}
            				exportColumnNo++;;
            			}
	    	            printF.print(AecStringUtils.changeNull(detail.getCellData()));
	    	            printF.print(",");
	        		}
	        	}
	        	
	        	beforeDetail = detail;
	            row++;
			}
 				
	        // ファイルに書き出し閉じる
	        printF.close();
	        
	        
        } catch (IOException ex) {
        	ex.printStackTrace();
        }

        // =======================================================================================================================
        // ステータス更新処理
        // =======================================================================================================================
        for(OrderImportList update:frm.getSearchList()){
			// チェックボックスがONか判断し、ON明細のリストを作成する
			if (update.isOrderImportCheckBox()) {
				// 20230307 S.Fujimaki　複数行あっても1行目しか更新されない不具合を修正。
				List<FrstOrderDetail> updateBeanList = frstOrderDetailDao.getList(update.getFrstOrderNo());		
				
				for( FrstOrderDetail updateBean : updateBeanList ){		
						
					if( AecStringUtils.isNotNullAndEmpty(updateBean.getDelDateSenderCd() )){	
						continue;
					}
					
					updateBean.setDelDateSendDate(AecDateUtils.getCurrentTimestamp());
					updateBean.setDelDateSenderCd(tantoCd);
					updateBean.setUpdatorCd(tantoCd);
					
					//更新処理
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
					checkRegist.updateNums += 1;// 更新件数カウント
				}
			}       	
        }
        
        // 出力のために登録した一次リストを削除
		List<OrderImportFileOut> delList = orderImportFileOutDao.getList(insert.getSeq());
		
		for( OrderImportFileOut deldata : delList ){
			// チェックを入れたデータをテーブルに格納
			try{
				orderImportFileOutDao.delete(deldata);				
			}catch( Exception ex ){
				System.out.println( org.seasar.extension.jdbc.SqlLogRegistryLocator.getInstance().getLast().getCompleteSql() );
	        	ex.printStackTrace();
			}
		}
		
		return checkRegist;

	}

	
	/**
	 * 返信ファイルメール送信処理
	 * 
	 * @param pkNo
	 * @param pkRow
	 * @param venderCd
	 * @param sendFile
	 * @param actionSeq
	 * @return boolean
	 * @throws MessagingException
	 */
	private boolean sendFileToMail_SendProc(final String pkNo,final BigDecimal pkRow,final String venderCd, final String tantoCd,final String sendFile, final BigDecimal actionSeq) throws MessagingException {
		
		// 更新対象データを取得する
		String mailFormatId = null;
		List<String> subjectParamCsv = createMailSubjectParam(pkNo, pkRow, tantoCd);
		
		mailFormatId = "O_30001";
		List<String> bodyParamCsv = createMailBodyParamCsv(mailFormatId, pkNo, pkRow, tantoCd);
		
		List<String> filePathList = new ArrayList<String>();
		filePathList.add(sendFile);
		
		// メール送信
		boolean sendMail = sendMailLogic.sendMailVender("TS", venderCd, mailFormatId, subjectParamCsv, 
			bodyParamCsv, filePathList, tantoCd, SendMailConstants.SEND_MODE_MAIL_ONLY, SendMailConstants.GET_ORDER_INFO, actionSeq);
	
		return sendMail;
	}

	/**
	 * PKNOとPKROWから納期連絡表先頭カラムのKEYを取得
	 * @param pkNo
	 * @param pkRow
	 * @return key String
	 */
	public String getContactTableKey(final String pkNo){
		
		RepDeliveryDateContactHeader bean = repDeliveryDateContactHeaderDao.getEntity(pkNo  , null);
		
		String key = "";
		
		if( bean != null ){
			key = bean.getKey();
		}
		
		return key;
	}
	
	/**
	 * メールテンプレート取得
	 * @param mailFormatId String
	 * @return MailTemplate
	 */
	public MailTemplate getMailTemplate(final String mailFormatId){
		
		MailTemplate bean = mailTemplateDao.getTemplate(mailFormatId);
		
		return bean;
	}

	/**
	 * 通知メール用の件名パラメータリスト作成
	 * 
	 * @param updateStatus
	 * @param bean
	 * @param tantoCd
	 * @return List<String>
	 */
	private List<String> createMailSubjectParam(final String pkNo, final BigDecimal pkRow, final String tantoCd) {

		List<String> subjectParamCsv = new ArrayList<String>();
		
		return subjectParamCsv;
	}

	/**
	 * 通知メール用の本文パラメータリスト作成
	 * 
	 * @param updateStatus
	 * @param purchaseMode
	 * @param bean
	 * @param tantoCd
	 * @param locale
	 * @param url
	 * @return List<String>
	 */
	private List<String> createMailBodyParamCsv(final String mailFormatId, final String pkNo, final BigDecimal pkRow, final String tantoCd) {

		List<String> bodyParamCsv = new ArrayList<String>();

		// パラメータになる値を取得
		List<VMailValueEntity> mailValue = vMailValueEntityDao.getParam(mailFormatId, pkNo, AecNumberUtils.convertString(pkRow), null, null, null);
		
		for(VMailValueEntity bean : mailValue){
			bodyParamCsv.add(bean.getVals());
		}
		
		return bodyParamCsv;
	}
	
	/**
	 * 受注ヘッダ一覧（受注一覧用）
	 * @param condition 検索条件
	 * @return List<RepOrderImportSlipHeaderForReport>
	 */
	public List<RepOrderImportSlipHeaderForReport> getSlipHeaderListForReport(
			final RepOrderImportSlipConditionForReport condition) {

		List<RepOrderImportSlipHeaderForReport> list = repOrderImportSlipHeaderForReportDao.getHeaderListForReport(condition);
		if (list.isEmpty()) {
			return null;
		}

		return list;

	}

	/**
	 * 受注詳細一覧（受注一覧用）
	 * @param condition 検索条件
	 * @return List<RepOrderImportSlipDetailForReport>
	 */
	public List<RepOrderImportSlipDetailForReport> getSlipDetailListForReport(
			final RepOrderImportSlipConditionForReport condition) {

		List<RepOrderImportSlipDetailForReport> list = repOrderImportSlipDetailForReportDao.getDetailListForReport(condition);
		if (list.isEmpty()) {
			return null;
		}

		return list;
	}
	
	/**
	 * 運送会社コードマスタチェックを行う.<br>
	 * 運送会社マスタにデータがない場合はエラーとする。
	 * @param carryCd 運送会社コード
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkCarry(final String carryCd, final int checkRowNo, final ActionMessages errors) {
		Carry carryBean = carryDao.getEntity(carryCd);
		if (carryBean == null || carryCd == null) {
			// データが存在しない場合
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.order.carry.not.exist", checkRowNo));
		}

		return errors;
	}

	
	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCarryCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 運送会社検索
		List<ShippingCarryForComboboxes> lineList = getCarryList();
		for (ShippingCarryForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getCarryCd());
			// 運送会社名称は運送会社名称１_運送会社名称２とする
			StringBuffer nameBuf = new StringBuffer("");
			nameBuf.append(bean.getCarryName1());
			if (bean.getCarryName2() != null
					&& !bean.getCarryName2().equals("")) {
				nameBuf.append("_").append(bean.getCarryName2());
			}
			item.setLabales(nameBuf.toString());
			res.add(item);
		}
		return res;
	}

	/**
	 * 運送会社一覧取得
	 * @return List<ShippingCarryForComboboxes>
	 */
	public List<ShippingCarryForComboboxes> getCarryList() {
		return shippingCarryForComboboxesDao.getCarryList();
	}

	/**
	 * 運送会社コンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCarryAllCombobox() {
		// 運送会社マスタからステータスコンボボックス用配列を取得
		List<ComboBoxItems> res = createCarryCombobox();
		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(ShippingConst.COMBO_ALL_VALUE);
		allItem.setLabales(ShippingConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}
	
	/**
	 * 得意先グループコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createVenderGroupCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 得意先グループ検索
		List<VenderGroupForComboboxes> venderGroupList = getVenderGroupList();
		for (VenderGroupForComboboxes bean : venderGroupList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getVenderGroupCd());
			// 得意先グループ名称を取得
			StringBuffer nameBuf = new StringBuffer("");
			nameBuf.append(bean.getVenderGroupName());
			item.setLabales(nameBuf.toString());
			res.add(item);
		}
		// 「99999:設定なし」を追加
		ComboBoxItems noData = new ComboBoxItems();
		noData.setLabales("設定なし");
		noData.setValues("99999");
		res.add(noData);
		
		return res;
	}
	
	/**
	 * 得意先グループ一覧取得
	 * @return List<VenderGroupForComboboxes>
	 */
	public List<VenderGroupForComboboxes> getVenderGroupList() {
		return venderGroupForComboboxesDao.getListForComboboxes();
	}

	/**
	 * 得意先グループコンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createVenderGroupAllCombobox() {
		// 得意先グループマスタからステータスコンボボックス用配列を取得
		List<ComboBoxItems> res = createVenderGroupCombobox();
		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(ShippingConst.COMBO_ALL_VALUE);
		allItem.setLabales(ShippingConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * 受注区分リスト取得
	 * @return List<OrderNamesList>
	 */
	public List<OrderNamesList> getOrderDivisionList() {
		List<OrderNamesList> list = orderNamesListDao.getList("ORDR");
		return list;
	}

	/**
	 * 受注ステータスリスト取得
	 * @return List<OrderStatusList>
	 */
	public List<OrderStatusList> getOrderStatusList() {
		List<OrderStatusList> list = orderStatusListDao.getList(null);
		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final OrderImportListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}
	
	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void outPutErrorLog(final String strModuleCd,
			final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception {

		ErrorLog log = new ErrorLog();

		log.setModuleCd(strModuleCd);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		log.setErrorMes(strErrorCd);
		String cutMsg = strErrorMsg;
		if (StringUtils.isNotEmpty(strErrorMsg)
				&& strErrorMsg.length() > ERROR_LOG_SQL_STR_MAX_LEN) {
			cutMsg = StringUtils.substring(strErrorMsg, 0,
				ERROR_LOG_SQL_STR_MAX_LEN);
		}
		log.setSqlStr(cutMsg);

		try {
			errorLogDao.insert(log);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/* --------------------- setter --------------------*/
	
	/**
	 * orderImportListDaoを設定します。
	 * @param orderImportListDao orderImportListDao
	 */
	public void setOrderImportListDao(OrderImportListDao orderImportListDao) {
		this.orderImportListDao = orderImportListDao;
	}

	/**
	 * orderNamesListDaoを設定します。
	 * @param orderNamesListDao orderNamesListDao
	 */
	public void setOrderNamesListDao(OrderNamesListDao orderNamesListDao) {
		this.orderNamesListDao = orderNamesListDao;
	}

	/**
	 * orderStatusListDaoを設定します。
	 * @param orderStatusListDao orderStatusListDao
	 */
	public void setOrderStatusListDao(OrderStatusListDao orderStatusListDao) {
		this.orderStatusListDao = orderStatusListDao;
	}

	/**
	 * balanceDaoを設定します。
	 * @param balanceDao balanceDao
	 */
	public void setBalanceDao(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}
	
	/**
	 * getNumberLogicを設定します。
	 * @param getNumberLogic getNumberLogic
	 */
	public void setGetNumberLogic(GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * orderImportListForReportDaoを設定します。
	 * @param orderImportListForReportDao orderImportListForReportDao
	 */
	public void setOrderImportListForReportDao(
			OrderImportListForReportDao orderImportListForReportDao) {
		this.orderImportListForReportDao = orderImportListForReportDao;
	}

	/**
	 * orderImportSameListDaoを取得します。
	 * @return orderImportSameListDao
	 */
	public OrderImportSameListDao getOrderImportSameListDao() {
		return orderImportSameListDao;
	}

	/**
	 * orderImportSameListDaoを設定します。
	 * @param orderImportSameListDao orderImportSameListDao
	 */
	public void setOrderImportSameListDao(
			OrderImportSameListDao orderImportSameListDao) {
		this.orderImportSameListDao = orderImportSameListDao;
	}

	/**
	 * sendMailLogicを設定します。
	 * @param sendMailLogic sendMailLogic
	 */
	public void setSendMailLogic(SendMailLogic sendMailLogic) {
		this.sendMailLogic = sendMailLogic;
	}

	/**
	 * vMailValueEntityDaoを設定します。
	 * @param vMailValueEntityDao vMailValueEntityDao
	 */
	public void setvMailValueEntityDao(VMailValueEntityDao vMailValueEntityDao) {
		this.vMailValueEntityDao = vMailValueEntityDao;
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
	 * namesNoListDaoを設定します。
	 * @param namesNoListDao namesNoListDao
	 */
	public void setNamesNoListDao(NamesNoListDao namesNoListDao) {
		this.namesNoListDao = namesNoListDao;
	}

	/**
	 * mailTemplateDaoを設定します。
	 * @param mailTemplateDao mailTemplateDao
	 */
	public void setMailTemplateDao(MailTemplateDao mailTemplateDao) {
		this.mailTemplateDao = mailTemplateDao;
	}

	/**
	 * errorLogDaoを設定します。
	 * @param errorLogDao errorLogDao
	 */
	public void setErrorLogDao(ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * shippingCarryForComboboxesDaoを設定します。
	 * @param shippingCarryForComboboxesDao shippingCarryForComboboxesDao
	 */
	public void setShippingCarryForComboboxesDao(
			ShippingCarryForComboboxesDao shippingCarryForComboboxesDao) {
		this.shippingCarryForComboboxesDao = shippingCarryForComboboxesDao;
	}

	/**
	 * venderGroupForComboboxesDaoを設定します。
	 * @param venderGroupForComboboxesDao venderGroupForComboboxesDao
	 */
	public void setVenderGroupForComboboxesDao(
			VenderGroupForComboboxesDao venderGroupForComboboxesDao) {
		this.venderGroupForComboboxesDao = venderGroupForComboboxesDao;
	}
	

	/**
	 * orderImportTempListDaoを設定します。
	 * @param orderImportTempListDao orderImportTempListDao
	 */
	public void setOrderImportTempListDao(
			OrderImportTempListDao orderImportTempListDao) {
		this.orderImportTempListDao = orderImportTempListDao;
	}

	/**
	 * orderImportFileOutDaoを設定します。
	 * @param orderImportFileOutDao orderImportFileOutDao
	 */
	public void setOrderImportFileOutDao(OrderImportFileOutDao orderImportFileOutDao) {
		this.orderImportFileOutDao = orderImportFileOutDao;
	}

	/**
	 * commonDaoを設定します。
	 * @param commonDao commonDao
	 */
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	/**
	 * carryDaoを設定します。
	 * @param carryDao carryDao
	 */
	public void setCarryDao(CarryDao carryDao) {
		this.carryDao = carryDao;
	}

	/**
	 * repOrderImportSlipHeaderForReportDaoを設定します。
	 * @param repOrderImportSlipHeaderForReportDao repOrderImportSlipHeaderForReportDao
	 */
	public void setRepOrderImportSlipHeaderForReportDao(
			RepOrderImportSlipHeaderForReportDao repOrderImportSlipHeaderForReportDao) {
		this.repOrderImportSlipHeaderForReportDao = repOrderImportSlipHeaderForReportDao;
	}

	/**
	 * repOrderImportSlipDetailForReportDaoを設定します。
	 * @param repOrderImportSlipDetailForReportDao repOrderImportSlipDetailForReportDao
	 */
	public void setRepOrderImportSlipDetailForReportDao(
			RepOrderImportSlipDetailForReportDao repOrderImportSlipDetailForReportDao) {
		this.repOrderImportSlipDetailForReportDao = repOrderImportSlipDetailForReportDao;
	}

	/**
	 * errorLogSqlStrMaxLenを設定します。
	 * @param errorLogSqlStrMaxLen errorLogSqlStrMaxLen
	 */
	public static void setErrorLogSqlStrMaxLen(int errorLogSqlStrMaxLen) {
		ERROR_LOG_SQL_STR_MAX_LEN = errorLogSqlStrMaxLen;
	}

	/**
	 * importStatusFixを設定します。
	 * @param importStatusFix importStatusFix
	 */
	public static void setImportStatusFix(BigDecimal importStatusFix) {
		IMPORT_STATUS_FIX = importStatusFix;
	}

	/**
	 * frstOrderHeadDaoを設定します。
	 * @param frstOrderHeadDao frstOrderHeadDao
	 */
	public void setFrstOrderHeadDao(FrstOrderHeadDao frstOrderHeadDao) {
		this.frstOrderHeadDao = frstOrderHeadDao;
	}

	/**
	 * eRROR_LOG_SQL_STR_MAX_LENを設定します。
	 * @param eRROR_LOG_SQL_STR_MAX_LEN eRROR_LOG_SQL_STR_MAX_LEN
	 */
	public static void setERROR_LOG_SQL_STR_MAX_LEN(int eRROR_LOG_SQL_STR_MAX_LEN) {
		ERROR_LOG_SQL_STR_MAX_LEN = eRROR_LOG_SQL_STR_MAX_LEN;
	}

	/**
	 * iMPORT_STATUS_FIXを設定します。
	 * @param iMPORT_STATUS_FIX iMPORT_STATUS_FIX
	 */
	public static void setIMPORT_STATUS_FIX(BigDecimal iMPORT_STATUS_FIX) {
		IMPORT_STATUS_FIX = iMPORT_STATUS_FIX;
	}

	/**
	 * orderImportDetailListDaoを設定します。
	 * @param orderImportDetailListDao orderImportDetailListDao
	 */
	public void setOrderImportDetailListDao(
			OrderImportDetailListDao orderImportDetailListDao) {
		this.orderImportDetailListDao = orderImportDetailListDao;
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
	
}