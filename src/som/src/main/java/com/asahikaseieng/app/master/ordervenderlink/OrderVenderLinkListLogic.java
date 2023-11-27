/*
 * Created on 2020/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.ordervenderlink;

import java.io.IOException;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport.OrderVenderLinkListForReport;
import com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterList;
import com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 取引先グループ一覧ロジック interface.
 * @author
 */
public interface OrderVenderLinkListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<OrderVenderMasterList>
	 */
	List<OrderVenderMasterList> getList(final OrderVenderMasterListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用(納入先)検索処理を行う.
	 * @param venderGroupCd 得意先グループ番号
	 * @return List<OrderVenderLinkListForReport>
	 */
	List<OrderVenderLinkListForReport> getDeliveryListForReport(
			final String venderGroupCd);
	
	/**
	 * 帳票用(品目)検索処理を行う.
	 * @param venderGroupCd 得意先グループ番号
	 * @return List<OrderVenderLinkListForReport>
	 */
	List<OrderVenderLinkListForReport> getItemListForReport(
			final String venderGroupCd);
	
	/**
	 * 連携データ追加登録
	 * @param beanList 登録データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */

	
	/**
	 * 得意先グループコンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createVenderGroupAllCombobox();
	

	/**
	 * ファイルを画面表示のリストに追加する.
	 * @param frm
	 * @param srhFilePath
	 * @return
	 */
	ActionMessages getDataFromUploadFile(final String strFilePath,
			final CheckDigitUtilsLogic checker, final String tantoCd) throws IOException,Exception;
	
	/**
	 * TMPファイルパス取得
	 */
	public String getTmpFilePath();
}
