/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

/**
 * ヘッダー情報更新処理インターフェイス
 * @author tosco
 */
public interface GrecipeHeaderRegister {
	/**
	 * ヘッダー情報更新処理
	 * @param form ヘッダー情報画面Form
	 * @throws GrecipeLogicException 処理例外発生時
	 */
	void update(GrecipeHeaderForm form) throws GrecipeLogicException;
}
