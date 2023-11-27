/*
 * Created on 2020/09/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.checkdigit.NumberChkDigitCtl;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.nonentity.orderdetailvenderdetail.OrderDetailVenderDetail;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderList;
import com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailList;
import com.asahikaseieng.dao.nonentity.orderimportsamelist.OrderImportSameList;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCalcDateFromCalendarCallDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受注取込詳細ロジック interface.
 * @author 
 */
public interface OrderImportDetailLogic {
	/**
	 * 受注取込のヘッダと詳細データを取得する
	 *
	 * @param supplierOrderNo
	 *            受注番号
	 * @param tantoCd 
	 *            String
	 * @throws NoDataException
	 *             NoDataException
	 * @return OrderImportEntity
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	void getOrderImportData(final OrderImportDetailForm frm, final String tantoCd, NumberChkDigitCtl numberChkDigitCtl) throws NoDataException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * 登録更新処理を行う
	 * @param frm 更新対象画面フォーム
	 * @param tantoCd ログインユーザ
	 * @throws Exception 例外
	 */
	ActionMessages regist(final OrderImportDetailForm frm, final String tantoCd, final boolean canConflictCheck , final FrstOrderHead frstHeadBean) throws Exception;

	/**
	 * 受注取込ヘッド・詳細テーブル確定処理を行う
	 * 
	 * @param frm
	 * @param tantoCd
	 * @param tantoOrgCd
	 * @return ActionMessages
	 * @throws NoDataException
	 * @throws Exception
	 */
	ActionMessages fix(final OrderImportDetailForm frm, final String tantoCd,
			final String tantoOrgCd, final boolean canConflictCheck , final FrstOrderHead frstHeadBean) throws NoDataException, Exception;
	
	
	
	/**
	 * 受注取込ヘッド・詳細テーブル確定取消処理を行う
	 * 
	 * @param frm
	 * @param tantoCd
	 * @return ActionMessages
	 * @throws NoDataException
	 * @throws Exception
	 */
	ActionMessages cancel(final OrderImportDetailForm frm, final String tantoCd) throws NoDataException, Exception;
	
	/**
	 * 削除処理を行う
	 * @param frm 削除対象が画面ビーン
	 * @param tantoCd ログインユーザ
	 * @throws Exception Exception
	 */
	ActionMessages delete(final OrderImportDetailForm frm, final String tantoCd) throws Exception;


	/**
	 * 非表示処理を行う
	 * @param frm 削除対象が画面ビーン
	 * @param loginUserId ログインユーザー
	 * @throws Exception Exception
	 */
	public ActionMessages invisible(final OrderImportDetailForm frm,
			final String tantoCd) throws Exception;
	
	/**
	 * 得意先リスト検索
	 * @param balanceCd 帳合コード
	 * @throws NoDataException NoDataException
	 * @return OrderDetailVenderList
	 */
	List<OrderDetailVenderList> getVenderList(final String balanceCd)
			throws NoDataException;
	
	/**
	 * 取引先詳細検索
	 * @param venderCd 取引先コード
	 * @throws NoDataException NoDataException
	 * @return OrderDetailVenderDetail
	 */
	OrderDetailVenderDetail getVenderDetail(final String venderCd)
			throws NoDataException;
	
	/**
	 * 品目重量を取得する
	 * @param itemCd 品目コード
	 * @throws NoDataException NoDataException
	 * @return allUpWeight BigDecimal
	 */
	BigDecimal getAllUpWeight(final String itemCd) 
		throws NoDataException;
	
	/**
	 * 品目在庫を取得する
	 * @param itemCd 品目コード
	 * @throws NoDataException NoDataException
	 * @return ItemInventory
	 */
	ItemInventory getItemInventoryEntity(final String itemCd)
			throws NoDataException;
	
	/**
	 * 品目在庫を取得する
	 * @param itemCd 品目コード
	 * @throws NoDataException NoDataException
	 * @return ItemInventory
	 */
	BigDecimal getNoGoodQty(final String itemCd) throws NoDataException;
	
	/**
	 * 備考マスタ検索処理
	 *
	 * @param frm OrderImportDetailForm
	 * @return List<OrderRemarkList>備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<OrderRemarkList> getRemarkList(final OrderImportDetailForm frm)
			throws NoDataException;
	
	/**
	 * 仮単価化処理
	 * @param frm OrderImportDetailForm
	 * @param tantoCd String
	 * @throws throws NoDataException データが存在しない例外
	 */
	ActionMessages changeTmpUnitprice(final OrderImportDetailForm frm,
			final String tantoCd,
			NumberChkDigitCtl numberChkDigitCtl) throws NoDataException;
			
	/**
	 * 運賃計算処理
	 * @param frm OrderImportDetailForm
	 * @param sameList OrderImportSameList
	 * @param tantoCd String
	 * @return BigDecimal calcCarryFare
	 * @throws throws NoDataException データが存在しない例外
	 */
	public BigDecimal calcCarryFare(final String deliveryCd, final OrderImportDetailForm frm, final List<OrderImportSameList> sameList, final String tantoCd) throws NoDataException;
	
	



	/**
	 * 受注区分コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> getCreateOrderDivisionCombobox();

	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> getCreateCarryCombobox();
	
	/**
	 * 得意先グループコンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createVenderGroupCombobox();

	/**
	 * 休日を考慮した出荷予定日などの日付を計算
	 * @param strDate     日付(文字列)
	 * @param leadTime    リードタイム
	 * @param calCd       カレンダーコード
	 */
	ProCalcDateFromCalendarCallDto callProCalcDateFromCalendar(final String strDate, final BigDecimal leadTime, final String calCd) ;
	
	/**
	 * 
	 * ファイル取込時のエラーログ出力
	 * @param pkNo
	 * @param pkRow
	 * @return errorMsg ActionMessages
	 * @throws Exception
	 */
	public ActionMessages getImportFileErrorLog(final String pkNo, final BigDecimal pkRow) throws Exception;
	
	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strModuleCd, final String strErrorCd,
			final String strErrorMsg, final String tantoCd) throws Exception;
	
	/**
	 * 入力承認処理
	 * @param frm
	 * @param tantoCd
	 * @param tantoOrgCd
	 * @return
	 * @throws NoDataException
	 * @throws Exception
	 */
	ActionMessages approval(final OrderImportDetailForm frm,
			final String tantoCd, final String tantoOrgCd)
			throws NoDataException, Exception;
	
	public List<OrderImportDetailList> getOrderImportDetailList( String frstOrderNo );

	// 単価設定
	public void setUnitprice(OrderImportDetailForm frm,
			OrderImportDetailList detailBean,
			NumberChkDigitCtl numberChkDigitCtl);
	/**
	 * 品目リスト全体の合計金額、個数、重量を計算、設定
	 * @param OrderImportDetailForm
	 * @return OrderImportDetailForm
	 */
	 OrderImportDetailForm calcTotalOrderAmount(final OrderImportDetailForm frm,NumberChkDigitCtl numberChkDigitCtl)
			throws NoDataException;
	
	/**
	 * 品目リスト各行の合計金額・個数・重量を計算
	 * @param OrderImportDetailForm
	 * @return OrderImportDetailForm
	 */
	 OrderImportDetailForm calcOrderAmout(final OrderImportDetailForm frm,NumberChkDigitCtl numberChkDigitCtl)
			throws NoDataException;
	
}
