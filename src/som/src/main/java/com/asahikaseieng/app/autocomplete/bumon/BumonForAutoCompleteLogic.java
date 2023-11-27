/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.bumon;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.bumon.BumonForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 会計部門マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface BumonForAutoCompleteLogic {
	//会計部門コードまたは、会計部門名称で検索--------------------------------
	/**
	 * 会計部門マスタのオートコンプリート用データの取得
	 * @param sectionCd 会計部門コードまたは会計部門名称
	 * @return List<BumonForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<BumonForAutoComplete> getSearchList(String sectionCd) throws NoDataException;
}
