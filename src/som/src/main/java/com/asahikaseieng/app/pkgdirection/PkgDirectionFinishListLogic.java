/*
 * Created on 2009/03/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;
import com.asahikaseieng.exception.NoDataException;


/**
 * 包装指図－仕上タブ ロジック interface.
 * @author tosco
 */
public interface PkgDirectionFinishListLogic {

	/**
	 * 
	 * 一覧検索処理を行う.
	 * @param directionDiv 区分
	 * @param directionNo 指図番号
	 * @return List<PkgDirectionDirectionFormulaList> 検索結果一覧
	 * @throws NoDataException 例外
	 */
	List<PkgDirectionDirectionFormulaList> getSearchList(
		final BigDecimal directionDiv, final String directionNo) throws NoDataException;

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param frm 仕上タブForm
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final PkgDirectionFinishListForm frm);

	/**
	 * 包装指図－フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final PkgDirectionFinishListForm frm, final String tantoCd) throws Exception;

}
