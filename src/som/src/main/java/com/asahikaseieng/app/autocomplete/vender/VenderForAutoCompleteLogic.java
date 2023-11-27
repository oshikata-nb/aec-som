/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.vender.VenderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 取引先マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface VenderForAutoCompleteLogic {
	// 取引先コードまたは、取引先名称で検索--------------------------------
	/**
	 * 検索画面用取引先マスタのオートコンプリート用データの取得
	 * @param itemCd 取引先コードまたは取引先名称
	 * @param venderDivision 用途
	 * @return List<VenderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<VenderForAutoComplete> getSearchList(String itemCd,
			String venderDivision) throws NoDataException;
}
