/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.organization;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.organization.OrganizationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 部署マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface OrganizationForAutoCompleteLogic {
	//部署コードまたは、部署名称で検索--------------------------------
	/**
	 * 検索画面用部署マスタのオートコンプリート用データの取得
	 * @param itemCd 部署コードまたは部署名称
	 * @return List<OrganizationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<OrganizationForAutoComplete> getSearchList(String itemCd) throws NoDataException;
}
