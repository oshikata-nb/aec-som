/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.unitprice;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.master.unitprice.Unitprice;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.unitpricedetaillist.UnitpriceDetailList;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;

/**
 * 仕入先別単価詳細ロジック interface.
 * @author t0011036
 */
public interface UnitpriceDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param venderDivision 取引先区分
	 * @param venderCd 仕入先コード
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @param consecutiveNo 行番
	 * @return Unitprice
	 */
	Unitprice getEntity(final String venderDivision, final String venderCd,
			final String itemCd, final BigDecimal version,
			final BigDecimal consecutiveNo);

	/**
	 * 検索処理を行う.
	 * @param venderDivision 取引先区分
	 * @param venderCd 仕入先コード
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return List<UnitpriceDetail>
	 */
	List<UnitpriceDetailList> getList(final String venderDivision,
			final String venderCd, final String itemCd, final BigDecimal version);

	/**
	 * 仕入先検索処理を行う.
	 * @param venderDivision 取引先区分
	 * @param venderCd 仕入期コード
	 * @return VenderDetail
	 */
	VenderDetail getVenderEntity(final String venderDivision,
			final String venderCd);

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	ItemQueueLastVersion getItemQueueEntity(final String itemCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Unitprice bean);

	/**
	 * 追加処理を行う.
	 * @param frm 追加対象データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void insert(final UnitpriceDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 一括削除処理を行う.
	 * @param venderDivision 取引先区分
	 * @param venderCd 仕入先コード
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return 削除件数
	 */
	int deleteUnitpriceList(final String venderDivision, final String venderCd,
			final String itemCd, final BigDecimal version);
}
