/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail.ItemTechLabelDetail;

/**
 * 技術情報ロジック interface.
 * @author t0011036
 */
public interface ItemTechLogic {
	/**
	 * ファイル情報取得
	 * @param labelCd ラベルコード
	 * @param commonCd ラベル種類
	 * @return ItemTechLabelDetail
	 */
	ItemTechLabelDetail getLabelEntity(final String labelCd,
			final String commonCd);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * 登録処理を行う.
	 * @param frm 登録対象データ
	 * @param tantoCd 担当者コード
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	void regist(final ItemTechForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 元のファイル名を取得
	 * @param lblPath 登録されているファイル名
	 * @return 元のファイル名
	 */
	String getOriginalLabelPath(final String lblPath);

	/**
	 * ダウンロードパス取得
	 * @param commonCd ラベル種類
	 * @return String ダウンロードパス
	 */
	String getDownloadPath(final String commonCd);
}
