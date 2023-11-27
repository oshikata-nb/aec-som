/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.aptrans;

import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.aptrans.ApTransJournalList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 会計送信ロジック interface.
 * @author t0011036
 */
public interface ApTransLogic {
	/**
	 * 実行
	 * @param organizationCd 部署コード
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	void insert(final String organizationCd, final String inputDate,
			final String tantoCd);

	/**
	 * CSVデータ検索
	 * @param inputDate 対象年月
	 * @throws NoDataException NoDataException
	 * @return List<TransJournalList>
	 */
	List<ApTransJournalList> getList(final String inputDate)
			throws NoDataException;

	/**
	 * CSVデータ作成
	 * @param list List<ApTransJournalList>
	 * @return List<String>
	 */
	List<String> getData(final List<ApTransJournalList> list);
}
