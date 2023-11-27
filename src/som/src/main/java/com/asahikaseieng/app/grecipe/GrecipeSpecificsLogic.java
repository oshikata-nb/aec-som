/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.util.List;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeLabelList;



/**
 * 原処方 詳細タブ ロジック interface.
 * @author tosco
 */
public interface GrecipeSpecificsLogic {

	/**
	 * 詳細タブ一覧検索
	 *
	 * @param labelCd ラベルコード
	 * @return List<GrecipeLabelList>
	 */
	List<GrecipeLabelList> getGrSearch(final String labelCd);

	/**
	 * 登録処理
	 *
	 * @param frm GrecipeSpecificsForm
	 * @param tantoCd 更新者
	 * @throws Exception 例外
	 */
	void regist(final GrecipeSpecificsForm frm, final String tantoCd) throws Exception;

	/**
	 * ダウンロードパス取得
	 *
	 * @param commonId 共通コード
	 * @return String ダウンロードパス
	 */
	String getDownloadPath(final String commonId);
}
