/*
 * Created on 2009/03/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.pkgrdirection;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.exception.LogicException;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績ヘッダー ロジッククラス interface. 
 * @author tosco
 */
public interface PkgRdirectionHeaderLogic {

	/**
	 * ヘッダー情報画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装実績ヘッダー Form
	 * @throws NoDataException データが存在しない例外
	 */
	void setPkgRdirectionHeaderForm(final HttpServletRequest request, final PkgRdirectionHeaderForm frm)
			throws NoDataException;

	/**
	 * 検索処理
	 * @param frm 包装指図ヘッダー Form
	 * @return DirectionHeader
	 * @throws NoDataException データが存在しない例外
	 */
	DirectionHeader getEntity(final PkgRdirectionHeaderForm frm) throws NoDataException;

	/**
	 * 更新処理を行う
	 * @param frm 包装指図ヘッダー Form
	 * @param tantoCd 更新者
	 */
	void update(final PkgRdirectionHeaderForm frm, final String tantoCd);

	/**
	 * 分納処理時のチェックを行う
	 * @param frm 包装実績ヘッダー Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkFordivide(final PkgRdirectionHeaderForm frm);

	/**
	 * 分納処理を行う
	 * @param frm 包装指図ヘッダー Form
	 * @param tantoCd 更新者
	 * @throws LogicException 発番失敗時の例外
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 * @return 分納先包装指図番号
	 */
	String divide(final PkgRdirectionHeaderForm frm, final String tantoCd)
		throws LogicException, IllegalAccessException, InvocationTargetException;
}
