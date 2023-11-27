/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.materialrinput;

import java.util.List;

import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputLotInventorySearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 外注原材料投入実績入力画面_ロット検索ポップアップ画面ロジック interface.
 * @author tosco
 */
public interface MaterialRinputLotInventorySearchLogic {

	/**
	 * ロット検索一覧検索処理
	 * @param condition 検索条件
	 * @return List<MaterialRinputLotInventorySearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<MaterialRinputLotInventorySearchList> getSearchList
		(final MaterialRinputLotInventorySearchListPagerCondition condition) throws NoDataException;

	/**
	 * 在庫引落情報登録処理
	 * 
	 * @param frm ロット検索画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws Exception 例外
	 */
	void insert(final MaterialRinputLotInventorySearchForm frm, final String tantoCd) throws Exception;
}
