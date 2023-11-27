/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.delivery;

import com.asahikaseieng.dao.entity.master.delivery.Delivery;
import com.asahikaseieng.dao.nonentity.master.carrydetail.CarryDetail;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先詳細ロジック interface.
 * @author t0011036
 */
public interface DeliveryDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param deliveryCd 納入先コード
	 * @throws NoDataException NoDataException
	 * @return Delivery
	 */
	Delivery getEntity(final String deliveryCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param deliveryCd 納入先コード
	 * @return DeliveryDetail
	 */
	DeliveryDetail getDetailEntity(final String deliveryCd);

	/**
	 * 運送会社検索処理を行う.
	 * @param carryCd 運送会社コード
	 * @return CarryDetail
	 */
	CarryDetail getCarryEntity(final String carryCd);

	/**
	 * 営業担当者検索処理を行う.
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	LoginDetail getLoginEntity(final String tantoCd);

	/**
	 * 取引先検索処理を行う.
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return VenderDetail
	 */
	VenderDetail getVenderEntity(final String venderDivision,
			final String venderCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Delivery bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Delivery bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Delivery bean) throws NoDataException;
}
