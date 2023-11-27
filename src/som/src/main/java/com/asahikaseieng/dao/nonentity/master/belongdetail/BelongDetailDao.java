/*
 * Created on 2009/03/24
 * AECS佐藤 受注納入先区分取得用追加 2020/01/21
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongdetail;

import java.math.BigDecimal;

/**
 * BelongDetailDaoクラス
 * @author t0011036
 */
public interface BelongDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.belongdetail.BelongDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "organizationCd, tantoCd, postId, belongKbn";

	/**
	 * BelongDetailメソッド
	 * 
	 * @param organizationCd organizationCd
	 * @param tantoCd tantoCd
	 * @param postId postId
	 * @param belongKbn belongKbn
	 * @return BelongDetail
	 */
	BelongDetail getEntity(final String organizationCd, final String tantoCd,
			final String postId, final BigDecimal belongKbn);

	/** ARGSアノテーション getCountMainBelong(). */
	String getCountMainBelong_ARGS = "tantoCd";

	/**
	 * 所属マスタ 主所属データ件数取得処理
	 * 
	 * @param tantoCd 担当者コード
	 * @return 主所属件数
	 */
	int getCountMainBelong(final String tantoCd);

	/** ARGSアノテーション getCountKenmuBelong(). */
	String getCountKenmuBelong_ARGS = "tantoCd";

	/**
	 * 所属マスタ 兼務所属データ件数取得処理
	 * 
	 * @param tantoCd 担当者コード
	 * @return 兼務所属件数
	 */
	int getCountKenmuBelong(final String tantoCd);

	/** ARGSアノテーション getCountKenmuBelong(). */
	String getCountBelong_ARGS = "organizationCd,tantoCd";

	/**
	 * 所属マスタ 所属データ件数取得処理
	 * @param organizationCd 部門コード
	 * @param tantoCd 担当者コード
	 * @return 兼務所属件数
	 */
	int getCountBelong(final String organizationCd, final String tantoCd);

	/** ARGSアノテーション update(). */
	String update_ARGS = "bean";

	/**
	 * 所属マスタ更新処理
	 * 
	 * @param bean 所属マスタ用Bean
	 * @return 更新件数
	 */
	int update(final BelongDetail bean);

	/** ARGSアノテーション insert(). */
	String insert_ARGS = "bean";

	/**
	 * 所属マスタ追加処理
	 * 
	 * @param bean 所属マスタ用Bean
	 * @return 追加件数
	 */
	int insert(final BelongDetail bean);

	/** ARGSアノテーション delete(). */
	String delete_ARGS = "bean";

	/**
	 * 所属マスタ削除処理
	 * 
	 * @param bean 所属マスタ用Bean
	 * @return 削除件数
	 */
	int delete(final BelongDetail bean);
	
	/* AECS佐藤 受注納入先区分取得用追加 2020/01/21 */
	/** ARGSアノテーション getOrderDeliveryKbn(). */
	String getOrderDeliveryKbn_ARGS = "TANTO_CD";
	
	/**
	 * 受注納入先区分取得.
	 * @param tantoCd tantoCd
	 * @return orderDeliveryKbn
	 */
	String getOrderDeliveryKbn(final String tantoCd);
}
