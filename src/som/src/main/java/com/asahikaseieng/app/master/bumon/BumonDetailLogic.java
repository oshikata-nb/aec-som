/*
 * Created on 2007/08/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bumon;

import com.asahikaseieng.dao.entity.master.bumon.Bumon;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 会計部門マスタ詳細 ロジッククラス interface.
 * @author TanakaSatoru
 */
public interface BumonDetailLogic {
	/**
	 * 検索処理を行う(検索用)
	 * @param sectionCd 会計部門コード
	 * @throws NoDataException NoDataException
	 * @return BumonDetail
	 */
	BumonDetail getDetailEntity(final String sectionCd) throws NoDataException;

	/**
	 * 検索処理を行う(登録用)
	 * @param sectionCd 会計部門コード
	 * @throws NoDataException NoDataException
	 * @return Bumon
	 */
	Bumon getEntity(final String sectionCd) throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象ビーン
	 */
	void update(final Bumon bean);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象ビーン
	 */
	void insert(final Bumon bean);

	/**
	 * 登録処理を行う.
	 * @param bean 削除対象ビーン
	 * @throws NoDataException NoDataException
	 */
	void delete(final Bumon bean) throws NoDataException;
}
