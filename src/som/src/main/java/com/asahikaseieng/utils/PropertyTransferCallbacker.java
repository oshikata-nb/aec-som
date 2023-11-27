/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

/**
 * プロパティーの移送用callbackのクラス<br>
 * MVCのV的ModelからEntityへの移送を意図したクラス. Entityを継承したクラス等で利用.
 * @author jbd
 */
public interface PropertyTransferCallbacker {
	/**
	 * プロパティーの移送処理を記述するinitメソッド.
	 * @throws Exception 例外
	 */
	void init() throws Exception;

	/**
	 * オーバーライド用のプロパティー移送処理メソッド<br>
	 * プロパティーの移送処理を記述するcallbackメソッド.
	 * @throws Exception 例外
	 */
	void callback() throws Exception;
}
