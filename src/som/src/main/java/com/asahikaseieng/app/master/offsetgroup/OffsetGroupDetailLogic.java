/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.offsetgroup;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.offsetgroupdetail.OffsetGroupDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 相殺グループ詳細 ロジッククラス interface.
 * @author tosco
 */
public interface OffsetGroupDetailLogic {

	/**
	 * 検索処理を行う.相殺グループマスタ
	 * @param offsetGroupCd 相殺グループコード
	 * @throws NoDataException NoDataException
	 * @return OffsetGroupDetail 詳細データ
	 */
	List<OffsetGroupDetail> getEntity(final String offsetGroupCd)
			throws NoDataException;

	/**
	 * 更新処理を行う.
	 * @param frm 画面フォーム
	 * @param tantoCd 更新者
	 * @throws NoDataException データ無し例外
	 * 
	 */
	void update(final OffsetGroupDetailForm frm, final String tantoCd)
			throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param frm 画面フォーム
	 * @param tantoCd 登録者
	 * @throws NoDataException データ無し例外
	 */
	void insert(final OffsetGroupDetailForm frm, final String tantoCd)
			throws NoDataException;

	/**
	 * 削除処理を行う.
	 * @param offsetGroupCd 削除対象キー
	 * @throws NoDataException データ無し例外
	 * 
	 */
	void delete(final String offsetGroupCd) throws NoDataException;
}
