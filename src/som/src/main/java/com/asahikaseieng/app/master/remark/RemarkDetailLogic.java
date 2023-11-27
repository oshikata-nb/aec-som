/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.remark;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.master.remark.Remark;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.remarkdetail.RemarkDetail;
import com.asahikaseieng.dao.nonentity.master.remarkdetailcheck.RemarkDetailCheck;
import com.asahikaseieng.dao.nonentity.master.remarkdetailgetmaxno.RemarkDetailGetMaxRemarkNo;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 備考詳細ロジック interface.
 * @author t0011036
 */
public interface RemarkDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param remarkNo 備考コード
	 * @throws NoDataException NoDataException
	 * @return Remark
	 */
	Remark getEntity(final BigDecimal remarkNo) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param remarkNo 備考コード
	 * @return RemarkDetail
	 */
	RemarkDetail getDetailEntity(final BigDecimal remarkNo);

	/**
	 * 取引先検索処理を行う.
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return VenderDetail
	 */
	VenderDetail getVenderEntity(final String venderDivision,
			final String venderCd);

	/**
	 * 納入先検索処理を行う.
	 * @param deliveryCd 納入先コード
	 * @return DeliveryDetail
	 */
	DeliveryDetail getDeliveryEntity(final String deliveryCd);

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	ItemQueueLastVersion getItemQueueEntity(final String itemCd);

	/**
	 * 最大備考番号取得
	 * @return RemarkDetailGetMaxRemarkNo
	 */
	RemarkDetailGetMaxRemarkNo getMaxRemarkNo();

	/**
	 * 重複チェック
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @param deliveryCd 納入先コード
	 * @param itemCd 品目コード
	 * @return RemarkDetailCheck
	 */
	RemarkDetailCheck getRemarkDetailCheck(final String venderDivision,
			final String venderCd, final String deliveryCd, final String itemCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Remark bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Remark bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Remark bean) throws NoDataException;
}
