/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.nonentity.orderdetailentity.OrderDetailEntity;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailList;
import com.asahikaseieng.dao.nonentity.orderdetailvenderdetail.OrderDetailVenderDetail;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderList;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCalcDateFromCalendarCallDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受注詳細ロジック interface.
 * @author t1344224
 */
public interface OrderDetailLogic {
	/**
	 * 更新処理を行う
	 * @param frm 更新対象画面フォーム
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザ部署コード
	 * @throws Exception 例外
	 */
	void update(final OrderDetailForm frm, final String loginUserId,
			final String loginUserOrganizationCd) throws Exception;

	/**
	 * 登録処理を行う
	 * @param frm 登録対象画面フォーム
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザ部署コード
	 * @throws Exception 例外
	 */
	void insert(final OrderDetailForm frm, final String loginUserId,
			final String loginUserOrganizationCd) throws Exception;

	/**
	 * 削除処理を行う
	 * @param frm 削除対象が画面ビーン
	 * @param loginUserId ログインユーザー
	 * @throws Exception Exception
	 */
	void delete(final OrderDetailForm frm, final String loginUserId)
			throws Exception;

	/**
	 * 検索処理を行う.
	 * @param orderCd 受注コードコード
	 * @throws NoDataException NoDataException
	 * @return OrderDetail
	 */
	OrderDetailEntity getDetailEntity(final String orderCd)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param orderCd 受注コードコード
	 * @throws NoDataException NoDataException
	 * @return OrderDetail
	 */
	List<OrderDetailList> getDetailList(final String orderCd)
			throws NoDataException;

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
	 * @param frm OrderDetailForm
	 * @return List<OrderRemarkList>備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<OrderRemarkList> getRemarkList(final OrderDetailForm frm)
			throws NoDataException;

	/**
	 * 納入先のみで備考マスタ検索処理
	 * @param frm OrderDetailForm
	 * @return OrderRemarkList備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	OrderRemarkList getRemarkDeliveryOnly(final OrderDetailForm frm)
			throws NoDataException;

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
	 * 登録時の品目マスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param frm 売上詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkItem(final OrderDetailForm frm,
			final ActionMessages errors);

	/**
	 * 登録時の帳合コードマスタチェックを行う.<br>
	 * 帳合マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkBalance(final OrderDetailForm frm,
			final ActionMessages errors);

	/**
	 * 登録時の取引先コードマスタチェックを行う.<br>
	 * 取引先マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkVender(final OrderDetailForm frm,
			final ActionMessages errors);

	/**
	 * 運送会社コードマスタチェックを行う.<br>
	 * 運送会社マスタにデータがない場合はエラーとする。
	 * @param carryCd 運送会社コード
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkCarry(final String carryCd, final ActionMessages errors);

	/**
	 * 登録時の販売条件マスタチェックを行う.<br>
	 * 販売条件マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkSalesTerms(final OrderDetailForm frm,
			final ActionMessages errors);

	/**
	 * 品目が全て仕入帳送品どうかをかチェックを行う.<br>
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkItemTypeDivision(final OrderDetailForm frm,
			final ActionMessages errors);

	/**
	 * ダウンロードパス取得
	 * @return String
	 */
	String getDownloadPath();

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
	 * 担当者コードから担当者名称を取得する
	 * @param tantoCd tantoCd
	 * @return tantoName 担当者名称
	 */
	String getLoginName(final String tantoCd);

	/**
	 * 休日を考慮した出荷予定日などの日付を計算
	 * @param strDate     日付(文字列)
	 * @param leadTime    リードタイム
	 * @param calCd       カレンダーコード
	 */
	ProCalcDateFromCalendarCallDto callProCalcDateFromCalendar(final String strDate, final BigDecimal leadTime, final String calCd) ;

}
