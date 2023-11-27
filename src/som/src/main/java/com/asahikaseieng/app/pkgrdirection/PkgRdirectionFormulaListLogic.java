/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績 配合一覧画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgRdirectionFormulaListLogic {

	/**
	 * 配合一覧検索処理
	 * @param frm 包装実績－配合一覧画面 Form
	 * @return List<PkgRdirectionDirectionFormulaList> データ
	 * @throws NoDataException データなし
	 */
	List<PkgRdirectionDirectionFormulaList> getSearchList(
			final PkgRdirectionFormulaListForm frm) throws NoDataException;

	/**
	 * 配合一覧編集処理
	 * @param request リクエスト
	 * @param frm 包装実績－配合一覧画面 Form
	 */
	void editList(final HttpServletRequest request,
			final PkgRdirectionFormulaListForm frm);

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param frm 包装実績－配合一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddDelList(final PkgRdirectionFormulaListForm frm);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param frm 包装実績－配合一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final PkgRdirectionFormulaListForm frm);

	/**
	 * 処方フォーミュラ登録処理を行う.
	 * @param frm 包装実績－配合一覧画面 Form
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final PkgRdirectionFormulaListForm frm, final String tantoCd)
			throws Exception;

	/**
	 * 投入実績取消
	 * @param frm 包装実績－配合一覧画面 Form
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void reset(final PkgRdirectionFormulaListForm frm, final String tantoCd)
			throws Exception;

	/**
	 * 更新時に開始、終了の実績日時が入っているかチェックする 開始、終了の実績日時がNullの場合エラー
	 * @param directionNo 製造指図No
	 * @param directionDivision 製造指図区分
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForResultDate(final String directionDivision,
			final String directionNo);

	/**
	 * 確定済みの配合で戻し場所に在庫があるかチェック
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @param directionDivision directionDivision
	 * @return ActionMessages エラー内容
	 */
	boolean checkForFormulaResultDate(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo,
			final BigDecimal directionDivision);

}
