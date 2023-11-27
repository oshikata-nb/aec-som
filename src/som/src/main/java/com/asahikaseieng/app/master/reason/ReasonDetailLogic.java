/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reason;

import com.asahikaseieng.dao.entity.master.reason.Reason;
import com.asahikaseieng.dao.nonentity.master.reasondefaultdetail.ReasonDefaultDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 理由詳細ロジック interface.
 * @author t0011036
 */
public interface ReasonDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param ryCd 理由コード
	 * @throws NoDataException NoDataException
	 * @return Reason
	 */
	Reason getEntity(final String ryCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param ryCd 理由コード
	 * @return ReasonDetail
	 */
	ReasonDetail getDetailEntity(final String ryCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Reason bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Reason bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Reason bean) throws NoDataException;

	/**
	 * デフォルト理由ちぇっく
	 * @return ReasonDefaultDetail
	 */
	ReasonDefaultDetail chkDefault();
}
