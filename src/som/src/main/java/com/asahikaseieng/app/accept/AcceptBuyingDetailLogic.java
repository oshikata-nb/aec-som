/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.accept;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboboxesBean;
import com.asahikaseieng.dao.nonentity.accept.AcceptBuyingDetailList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 受入・仕入　仕入入力詳細 ロジッククラス interface. 
 * @author tosco
 */
public interface AcceptBuyingDetailLogic {

	/**
	 * 仕入区分コンボボックス作成
	 * @param frm 仕入入力画面FORM
	 */
	void createAcceptStockingDivisionCombobox(final AcceptBuyingDetailForm frm);

	/**
	 * 購買オーダーテーブル検索処理を行う.
	 * @param slipNo 仕入番号
	 * @param check  数値項目用表示ロジッククラス
	 * @return List<AcceptBuyingDetailList> 詳細データ
	 * @throws NoDataException データが存在しない例外
	 */
	List<AcceptBuyingDetailList> getEntity(final String slipNo
									, final CheckDigitUtilsLogic check)
									throws NoDataException;

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　会計部門マスタ、科目マスタにデータがない場合はエラーとする。
	 * @param frm 仕入入力画面FORM
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final AcceptBuyingDetailForm frm);

	/**
	 * 購買オーダーテーブル更新処理を行う.
	 * @param frm 仕入入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @param check   数値項目用表示ロジッククラス
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void update(final AcceptBuyingDetailForm frm
				, final String tantoCd
				, final CheckDigitUtilsLogic check) throws NoDataException, Exception;

	/**
	 * 購買外注オーダー.仕入ステータスの更新処理を行う.
	 * @param frm 仕入入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws OptimisticLockRuntimeException 排他エラー
	 */
	void approve(final AcceptBuyingDetailForm frm, final String tantoCd) throws OptimisticLockRuntimeException;

	/**
	 * 仕入単価を取得する.
	 * @param frm 仕入入力画面FORM
	 * @param check   数値項目用表示ロジッククラス
	 */
	void getHousingUnitprice(final AcceptBuyingDetailForm frm, final CheckDigitUtilsLogic check);
	

	/**
	 * 消費税率コンボボックス用データ取得
	 *
	 * @param locale
	 *            言語コード
	 * @return bean
	 *            コンボボックス
	 * @throws Exception
	 *            NoDataException
	 */
	ComboboxesBean getPurchaseTaxCombobox() throws NoDataException;
}
