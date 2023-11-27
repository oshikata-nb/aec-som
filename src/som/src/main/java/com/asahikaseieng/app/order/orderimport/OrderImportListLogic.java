/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.order.orderimport.OrderImportListAction.CheckRegist;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate;
import com.asahikaseieng.dao.nonentity.master.orderstatuslist.OrderStatusList;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportList;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportListPagerCondition;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipConditionForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipDetailForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipHeaderForReport;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受注取込一覧 Logicクラス
 * @author 
 */
public interface OrderImportListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @param venderCd 得意先コード
	 * @throws NoDataException NoDataException
	 * @return List<OrderImportList>
	 */
	List<OrderImportList> getList(final OrderImportListForm form,
			final OrderImportListPagerCondition condition,
			final String venderCd) throws NoDataException;

	/**
	 * 受注取込ヘッダ一覧（帳票用）
	 * @param condition 検索条件
	 * @return List<OrderImportListForReport>
	 */
	List<OrderImportListForReport> getHeaderListForReport(final OrderImportListConditionForReport condition);

	/**
	 * 受注取込詳細一覧（帳票用）
	 * @param condition 検索条件
	 * @return List<OrderImportListForReport>
	 */
	List<OrderImportListForReport> getDetailListForReport(final OrderImportListConditionForReport condition);
	

	
	/**
	 * 受注取込テーブル確定処理を行う
	 * 
	 * @param searchList
	 * @param tantoCd
	 * @throws NoDataException
	 * @throws Exception
	 */
	CheckRegist fix(final OrderImportListForm form,
			final String tantoCd, final String tantoOrgCd) throws NoDataException, Exception;
	
	/**
	 * 受注取込ヘッド・詳細テーブル引当解除処理を行う
	 * 
	 * @param searchList
	 * @throws NoDataException
	 * @throws Exception
	 */
	CheckRegist orderRelease(final OrderImportListForm form,
			final String tantoCd) throws NoDataException, Exception;
	
	/**
	 * メール、FAX(納期連絡表)送信処理
	 * 
	 * @param dm
	 * @param filePathList
	 * @param deliveryCd
	 * @param tantoCd
	 * @param actionSeq
	 * @return CheckRegist
	 * @throws MessagingException
	 */
	public CheckRegist sendMail(final OrderImportDeliveryDateDataModel dm , List<String> filePathList, final String deliveryCd , final String tantoCd, final BigDecimal actionSeq) throws MessagingException;
	
	public void addOrderImportDeliveryDateDataModel(
			HashMap<String , OrderImportDeliveryDateDataModel > dmMap
			, OrderImportList bean
			, int rowNum);
	
	/**
	 * 得意先へのファイル送信処理
	 * 
	 * @param frm
	 * @param tantoCd
	 * @param actionSeq
	 * @return CheckRegist
	 * @throws MessagingException
	 */
	CheckRegist sendFileToMail(final OrderImportListForm frm, final String tantoCd, final BigDecimal actionSeq) throws MessagingException;

	
	/**
	 * PKNOとPKROWから納期連絡表先頭カラムのKEYを取得
	 * @param pkNo
	 * @param pkRow
	 * @return key String
	 */
	String getContactTableKey(final String pkNo);
	
	/**
	 * メールテンプレート取得
	 * @param mailFormatId String
	 * @return MailTemplate
	 */
	MailTemplate getMailTemplate(final String mailFormatId);
	
	/**
	 * 受注ヘッダ一覧（受注一覧用）
	 * @param condition 検索条件
	 * @return List<RepOrderImportSlipHeaderForReport>
	 */
	List<RepOrderImportSlipHeaderForReport> getSlipHeaderListForReport(final RepOrderImportSlipConditionForReport condition);

	/**
	 * 受注詳細一覧（受注一覧用）
	 * @param condition 検索条件
	 * @return List<RepOrderImportSlipDetailForReport>
	 */
	List<RepOrderImportSlipDetailForReport> getSlipDetailListForReport(final RepOrderImportSlipConditionForReport condition);

	
	/**
	 * 受注区分リスト取得
	 * @return List<OrderNamesList>
	 */
	List<OrderNamesList> getOrderDivisionList();

	/**
	 * 受注ステータスリスト取得
	 * @return List<NamesList>
	 */
	List<OrderStatusList> getOrderStatusList();
	
	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strModuleCd,
			final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception;

	/**
	 * 運送会社コンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createCarryAllCombobox();
	
	/**
	 * 得意先グループコンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createVenderGroupAllCombobox();
}
