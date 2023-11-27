/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

/**
 * ヘッダー情報更新処理インターフェイス
 * @author tosco
 */
public interface MgrecipeHeaderRegister {
	/**
	 * ヘッダー情報更新処理
	 * @param form ヘッダー情報画面Form
	 * @throws MgrecipeLogicException 処理例外発生時
	 */
	void update(MgrecipeHeaderForm form) throws MgrecipeLogicException;
}
