/*
 * Created on 2008/07/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.cal;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.entity.master.cal.Cal;
import com.asahikaseieng.dao.nonentity.master.caldetaillist.CalDetailList;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * カレンダー詳細ロジック interface.
 * @author tosco
 */
public interface CalDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param calCd カレンダーコード
	 * @param calDate 日付
	 * @return Cal
	 */
	Cal getEntity(final String calCd, final Timestamp calDate);

	/**
	 * 検索処理を行う.
	 * @param calCd カレンダーコード
	 * @param calYear 会計年度
	 * @return List<CalDetailList>
	 */
	List<CalDetailList> getDetailList(final String calCd, final String calYear);

	/**
	 * 自社検索処理を行う.
	 * @param companyCd 自社コード
	 * @return CompanyDetail
	 */
	CompanyDetail getCompanyEntity(final String companyCd);

	/**
	 * カレンダー一括削除を行う.
	 * @param calCd calCd
	 * @param calYear calyear
	 * @return 削除件数
	 */
	int deleteCalList(final String calCd, final String calYear);

	/**
	 * 追加処理を行う.
	 * @param frm 登録データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void insert(final CalDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Cal bean) throws NoDataException;
}
