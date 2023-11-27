/*
 * Created on 2020/11/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.orderimportfileloglist.OrderImportFileLogList;
import com.asahikaseieng.dao.nonentity.orderimporttemplist.OrderImportTempList;

/**
 * 受注取込詳細ロジック interface.
 * @author 
 */
public interface OrderFileImportLogic {
	
	/**
	 * CSVデータを取得します。
	 * @param fileName String
	 * @param encode String
	 * @return リストに格納したCSVデータ
	 * @throws Exception 
	 */
	
	List<List<String>> getRows(final String fileName, final String encode) throws Exception;

	/**
	 * リストに格納したCSVデータを一時取込テーブルに登録
	 * @param csvData List<List<String>>
	 * @return 
	 */
	ActionMessages setTempTable(final List<List<String>> csvData, final OrderFileImportForm frm, final String tantoCd) throws Exception;
	
	/**
	 * 得意先グループコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createVenderGroupCombobox();
	
	/**
	 * プロシージャ実行ログ取得
	 * @param tantoCd String
	 * @return List<OrderImportFileLogList>
	 */
	List<OrderImportFileLogList> getImportLog(final String tantoCd);
	
	/**
	 * 
	 * 過去1か月間に取り込まれたファイルをmd5チェックサム値で検索する
	 * @param OrderFileImportForm frm
	 * @return
	 * @throws Exception
	 */
	OrderImportTempList getMd5Checksum(OrderFileImportForm frm) throws Exception;
	
	/**
	 * プロシージャ実行ログ確認フラグ更新
	 * @param tantoCd String
	 * @return
	 */
	ActionMessages updateCheckedFlg(final String tantoCd) throws Exception;
	
	/**
	 * TMPファイルパス取得
	 */
	String getTmpFilePath();

}
