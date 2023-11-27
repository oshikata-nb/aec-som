/*
 * Created on 2007/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage;

import java.util.List;

import com.asahikaseieng.dao.entity.gadget.GadgetLink;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.dao.nonentity.authority.Authority;

/**
 * マイページロジック interface.
 * @author jbd
 */
public interface MyPageLogic {

	/**
	 * ガジェット設定を正規化する.
	 * @param tantoCd 担当者コード
	 * @param authList ガジェット権限リスト
	 */
	void updateGadgetConfig(final String tantoCd, final List<Authority> authList);

	/**
	 * ガジェットを取得する.
	 * @param tantoCd 担当者コード
	 * @return List<GadgetConfig>
	 */
	List<GadgetConfig> getGadgetList(final String tantoCd);

	/**
	 * ガジェットに表示するリンクデータリストを取得する.
	 * @param sqlCd SQL文
	 * @param tantoCd 担当者コード
	 * @return List<GadgetLink> リンクデータリスト
	 */
	List<GadgetLink> getGadgetLinkList(final String sqlCd, final String tantoCd);

	/**
	 * ガジェットの情報を更新する.
	 * @param lightIds 左のID(s)
	 * @param rightIds 右のID(s)
	 * @param tantoCd 担当者コード
	 * @param leftMenuIds 左のメニューID(s)
	 * @param rightMenuIds 右のメニューID(s)
	 */
	void registGadget(final String lightIds, final String rightIds,
			final String tantoCd, final String leftMenuIds,
			final String rightMenuIds);
}
