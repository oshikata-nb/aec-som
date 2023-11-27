/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.names;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.names.NamesForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 各種名称マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface NamesForAutoCompleteLogic {
	//名称コードまたは、名称１で検索--------------------------------
	/**
	 * 各種名称マスタのオートコンプリート用データの取得
	 * @param nameCd 名称コードまたは名称１
	 * @param nameDivision 名称区分
	 * @return List<NamesForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<NamesForAutoComplete> getSearchList(String nameCd, String nameDivision) throws NoDataException;
}
