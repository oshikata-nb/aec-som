/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * 
 * OffsetDetailDao．グループ間相殺入力
 * @author tosco
 */
public interface OffsetPayableDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.offset.OffsetPayable.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PAYABLE_NO";

	/**
	 * エンティティ取得.
	 * @param payableNo 買掛番号
	 * @return BankDetail 買掛ヘッダー
	 */
	OffsetPayable getEntity(String payableNo);

	/** ARGSアノテーション getPayableNew(). */
	String getPayableNew_ARGS = "organizationCd,offsetGroupCd,payableFlg";

	/**
	 * 買掛ヘッダー取得.検索ボタン押下時
	 * @param organizationCd 部署コード
	 * @param offsetGroupCd 相殺グループコード
	 * @param payableFlg 仕入科目区分
	 * @return List<OffsetPayable> 買掛リスト
	 */
	List<OffsetPayable> getPayableNew(final String organizationCd,
		final String offsetGroupCd,  final BigDecimal payableFlg);

	/** ARGSアノテーション getPayable(). */
	String getPayable_ARGS = "organizationCd,venderCd,payableDate,payableFlg";

	/**
	 * 買掛ヘッダー取得.リンク遷移時
	 * @param organizationCd   部署コード
	 * @param venderCd    支払先コード
	 * @param payableDate システム日時もしくはnull
	 * @param payableFlg payableFlg
	 * @return OffsetPayable 買掛データ
	 */
	OffsetPayable getPayable(final String organizationCd,
			final String venderCd, final Date payableDate, final BigDecimal payableFlg);


}
