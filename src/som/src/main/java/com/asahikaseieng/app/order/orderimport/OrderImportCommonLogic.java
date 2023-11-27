/*
 * Created on 2021/03/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.order.OrderLogicException;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailList;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受注取込一覧/詳細共通ロジック
 * @author a1053739
 */
public interface OrderImportCommonLogic {

	/**
	 * 受注納期連絡表の出力ログ登録
	 */
	void inserFrstOrdertLog( List<String> checkedList , final String tantoCd , final String logCls);
	
	/**
	 * 受注の確定処理
	 */
	void fixUpdate(final String tantoCd, final String tantoOrgCd,
			List<OrderImportDetailList> updateDetailList,
			List<OrderImportDetailList> beforeOrderImportDetailList,
			String orderNo, FrstOrderHead frstHeadBean) throws NoDataException,
			Exception, OrderLogicException;
	
	
	public void deleteFrstOrder(final String tantoCd, String frstOrderNo , FrstOrderHead frstHeadUpdateBean);

	public void deleteOrder(final String tantoCd, String orderNo,
				String orderDivision, String frstOrderNo, List<OrderImportDetailList> detailList)
				throws OrderLogicException;
	
	public ActionMessages checkValidDate(final int insertFlg,
			String orderDate, String scheduledShippingDate,
			String deliveryExpectedDate);

	/**
	 * 登録時の取引先コードマスタチェックを行う.<br>
	 * 取引先マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkVender(final String venderCd,
			final ActionMessages errors);

	/**
	 * 運送会社コードマスタチェックを行う.<br>
	 * 運送会社マスタにデータがない場合はエラーとする。
	 * @param carryCd 運送会社コード
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkCarry(final String carryCd,
			final ActionMessages errors);

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
			final ActionMessages errors);

	/**
	 * 品目が全て仕入帳送品どうかをかチェックを行う.<br>
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkItemTypeDivision(
			final List<OrderImportDetailList> detailList,
			final String orderDivision,
			final int insertFlg,
			final ActionMessages errors);
	
	/**
	 * 登録時の品目マスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param frm 売上詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkItem(final List<OrderImportDetailList> detailList,
			final int insertFlg,
			final ActionMessages errors);

	/**
	 * 登録時の帳合コードマスタチェックを行う.<br>
	 * 帳合マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkBalance(final String balanceCd,
			final ActionMessages errors);	
	
	/**
	 *　数量の不足チェック
	 * @param orderDivision
	 * @param orderImportDetailList
	 * @param frstOrderNo
	 * @return
	 */
	public ActionMessages checkOrderQuantity(String orderDivision,
			List<OrderImportDetailList> orderImportDetailList,
			String frstOrderNo);	
}
