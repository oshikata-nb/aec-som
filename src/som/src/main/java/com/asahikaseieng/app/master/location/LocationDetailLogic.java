/*
 * Created on 2009/01/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.location;

import java.util.List;

import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.locationlowerlist.LocationLowerList;
import com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLogin;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロケーション詳細ロジック interface.
 * @author t0011036
 */
public interface LocationDetailLogic {
	/**
	 * 棚卸区分取得
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getCountDivisionList();

	/**
	 * 下位ロケーションチェック
	 * @param locationCd ロケーションコード
	 * @return List<LocationLowerList>
	 */
	List<LocationLowerList> getLowerLocation(final String locationCd);

	/**
	 * ロケーションレベル計算
	 * @param locationCd ロケーションコード
	 * @param upperLocationCd 上位ロケーションコード
	 * @throws NoDataException NoDataException
	 * @return ロケーションレベル
	 */
	int calcLocationLevel(final String locationCd, final String upperLocationCd)
			throws NoDataException;

	/**
	 * 会計部門検索処理を行う.
	 * @param sectionCd 会計部門コード
	 * @return BumonDetail
	 */
	BumonDetail getBumonEntity(final String sectionCd);

	/**
	 * 営業担当者検索処理を行う.
	 * @param tantoCd 営業担当者コード
	 * @return LoginLogin
	 */
	LoginLogin getLoginEntity(final String tantoCd);

	/**
	 * ローリー原料品目検索処理を行う.
	 * @param itemCd ローリー原料品目コード
	 * @return ItemQueueLastVersion
	 */
	ItemQueueLastVersion getItemQueueEntity(final String itemCd);

	/**
	 * 検索処理を行う.
	 * @param locationCd ロケーションコード
	 * @throws NoDataException NoDataException
	 * @return Location
	 */
	Location getEntity(final String locationCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	LocationDetail getDetailEntity(final String locationCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Location bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Location bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Location bean) throws NoDataException;
}
