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
 * 売掛取引先一覧用Dao．OffsetDepositDao
 * @author tosco
 */
public interface OffsetDepositDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.offset.OffsetDeposit.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DEPOSIT_NO";

	/**
	 * エンティティ取得.
	 * @param depositNo 売掛番号
	 * @return BankDetail 売掛ヘッダー
	 */
	OffsetDeposit getEntity(String depositNo);

	/** ARGSアノテーション getDepositNew(). */
	String getDepositNew_ARGS = "organizationCd,offsetGroupCd,creditFlg";

	/**
	 * 売掛ヘッダー取得.検索ボタン押下時
	 * @param organizationCd 部署コード
	 * @param offsetGroupCd 相殺グループコード
	 * @param creditFlg 売上科目区分
	 * @return List<OffsetDeposit> 売掛リスト
	 */
	List<OffsetDeposit> getDepositNew(final String organizationCd,
		final String offsetGroupCd, final BigDecimal creditFlg);

	/** ARGSアノテーション getDeposit(). */
	String getDeposit_ARGS = "organizationCd,venderCd,depositDate,creditFlg";

	/**
	 * 売掛ヘッダー取得.リンク遷移時時
	 * @param organizationCd   部署コード
	 * @param venderCd    請求先コード
	 * @param depositDate システム日付もしくはnull
	 * @param creditFlg creditFlg
	 * @return OffsetDeposit 売掛データ
	 */
	OffsetDeposit getDeposit(final String organizationCd,
			final String venderCd, final Date depositDate, final BigDecimal creditFlg);


}
