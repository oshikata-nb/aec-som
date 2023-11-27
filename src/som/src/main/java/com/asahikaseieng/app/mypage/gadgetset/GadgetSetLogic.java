/*
 * Created on 2008/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.gadgetset;

import java.util.List;

import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.exception.NoDataException;

/**
 * ガジェット設定画面ロジックインターフェース interface.
 * @author tosco
 */
public interface GadgetSetLogic {

	/**
	 * ガジェット設定NEW(GADGET_CONFIG)テーブルの登録処理(DELETE/INSERT)を行う.
	 * 
	 * @param tantoCd ログインしている担当者コード
	 * 
	 * @param list 登録対象のガジェット設定リスト
	 * 
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	void update(final String tantoCd, final List<GadgetConfig> list)
			throws NoDataException;

}
