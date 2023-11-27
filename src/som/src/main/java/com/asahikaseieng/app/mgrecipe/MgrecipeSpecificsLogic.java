/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.List;

import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelList;


/**
 * 基本処方 詳細タブ ロジック interface.
 * @author tosco
 */
public interface MgrecipeSpecificsLogic {

	/**
	 * 詳細タブ一覧検索
	 * 
	 * @param labelCd ラベルコード
	 * @return List<MgrecipeLabelList> 
	 */
	List<MgrecipeLabelList> getGrSearch(final String labelCd);

	/**
	 * 詳細タブ一覧検索
	 * 
	 * @param labelCd ラベルコード
	 * @return List<MgrecipeLabelList> 
	 */
	List<MgrecipeLabelList> getMrSearch(final String labelCd);

	/**
	 * 登録処理
	 * 
	 * @param frm MgrecipeSpecificsForm
	 * @param tantoCd 更新者
	 * @throws Exception 例外
	 */
	void regist(final MgrecipeSpecificsForm frm, final String tantoCd) throws Exception;

	/**
	 * ダウンロードパス取得
	 * 
	 * @param commonId 共通コード
	 * @return String ダウンロードパス
	 */
	String getDownloadPath(final String commonId);
}
