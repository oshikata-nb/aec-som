/*
 * Created on 2014/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.common.procedurelog;

import java.util.List;

/**
 * ログ情報用DAO
 * @author atts
 */
public interface ProcedureLogDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "srhProcCd, srhInputorCd";

	/** ARGSアノテーション getSearchUpdateLog */
	String getSearchUpdateLog_ARGS = "srhProcCd, srhInputorCd";

	/** ARGSアノテーション updateProcLog */
	String updateProcLog_ARGS = "condition";

	/**
	 * ログ情報取得メソッド
	 * @param srhProcCd プロシージャ名
	 * @param srhInputorCd 登録者コード
	 * @return List<ProcedureLog>
	 */
	List<ProcedureLog> getSearchList(final String srhProcCd,
			final String srhInputorCd);

	/**
	 * 更新対象のログ情報取得メソッド
	 * @param srhProcCd プロシージャ名
	 * @param srhInputorCd 登録者コード
	 * @return ProcedureLog
	 */
	ProcedureLog getSearchUpdateLog(final String srhProcCd,
			final String srhInputorCd);

	/**
	 * 確認フラグ更新メソッド
	 * @param bean 更新情報
	 */
	void updateProcLog(final ProcedureLogPagerCondition bean);
}
