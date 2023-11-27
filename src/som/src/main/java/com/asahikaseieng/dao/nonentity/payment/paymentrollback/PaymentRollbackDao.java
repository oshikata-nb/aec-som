/*
 * Created on 2008/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentrollback;

import java.sql.Date;
import java.util.List;


/**
 * 支払更新ロールバック用Daoクラス
 * 
 * @author tosco
 */
public interface PaymentRollbackDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentrollback.PaymentRollback.class;

	/** ARGSアノテーション getPayableDate */
	String getPayableDate_ARGS = "venderCd";

	/** ARGSアノテーション getSearchRbList */
	String getSearchRbList_ARGS = "organizationCd,venderCd,payableDate";

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,venderCd,payableDate";

	/** ARGSアノテーション getSearchEraserList */
	String getSearchEraserList_ARGS = "organizationCd,venderCd,payableDate";

	/**
	 * MAX(支払締め日)取得メソッド
	 * @param venderCd  支払先コード	 * @return PaymentRollback 支払ヘッダーデータ(MAX(支払締め日)
	 */
	PaymentRollback getPayableDate(final String venderCd);

	/**
	 * 支払ヘッダー(ロールバック対象)検索メソッド
	 * @param organizationCd  部署コード	 * @param venderCd   支払先コード	 * @param payableDate 支払締め日
	 * @return List<PaymentRollback> ロールバック対象の支払ヘッダーデータ
	 */
	List<PaymentRollback> getSearchRbList(final String organizationCd,
						final String venderCd, final Date payableDate);

	/**
	 * 支払ヘッダー(未来締めデータ)検索メソッド
	 * @param organizationCd  部署コード	 * @param venderCd   支払先コード	 * @param payableDate 支払締め日
	 * @return List<PaymentRollback> 支払ヘッダーデータ
	 */
	List<PaymentRollback> getSearchList(final String organizationCd,
						final String venderCd, final Date payableDate);

	/**
	 * 消込データ検索メソッド
	 * @param organizationCd  部署コード
	 * @param venderCd   支払先コード
	 * @param payableDate 支払締め日
	 * @return List<PaymentRollback> 検索データ
	 */
	List<PaymentRollback> getSearchEraserList(final String organizationCd,
						final String venderCd, final Date payableDate);

}
