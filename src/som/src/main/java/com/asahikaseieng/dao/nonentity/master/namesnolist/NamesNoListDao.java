/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.namesnolist;

import java.math.BigDecimal;

/**
 * NamesListDaoクラス
 * @author t0011036
 */
public interface NamesNoListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.namesnolist.NamesNoList.class;

	/** ARGSアノテーション getNo(). */
	String getNo_ARGS = "nameCd";

	/**
	 * 発番番号取得.
	 * @param nameCd 名称コード(null:発注書NO HT:発注番号 SI:仕入番号 JT:受注番号 SK:出荷番号 UR:売上番号 
	 *            MI:見積番号 PK:取込番号 OI:一時取込番号 CF:運賃計算用シーケンス番号 SAES:販売条件・見積単価コピー・削除)
	 * @return BigDecimal 発番番号
	 */
	BigDecimal getNo(final String nameCd);
}
