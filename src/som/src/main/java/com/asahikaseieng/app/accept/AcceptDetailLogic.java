/*
 * Created on 2009/02/20
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.accept;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受入入力詳細 ロジッククラス interface.
 * @author tosco
 */
public interface AcceptDetailLogic {

	/**
	 * 仕入区分コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createAcceptStockingDivisionCombobox();

	/**
	 * 購買オーダーテーブル検索処理を行う.
	 * @param purchaseNo 購買NO
	 * @param check 数値項目用表示ロジッククラス
	 * @param kbn 区分(A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 *            ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 * @return List<AcceptDetailList> 詳細データ
	 * @throws NoDataException データが存在しない例外
	 */
	List<AcceptDetailList> getEntity(final String purchaseNo,
			final CheckDigitUtilsLogic check, final String kbn)
			throws NoDataException;

	/**
	 * 発注番号にひもづくデータの受入数量の合計値を取得する.
	 * @param buySubcontractOrderNo 発注番号
	 * @param slipNo 仕入番号
	 * @return BigDecimal 受入数量の合計値
	 */
	BigDecimal getSumAcceptQty(final String buySubcontractOrderNo,
			final String slipNo);

	/**
	 * 未受入データ件数を取得する.
	 * @param buySubcontractOrderNo 発注番号
	 * @return BigDecimal 未受入データ件数
	 */
	BigDecimal getCountNotAccept(final String buySubcontractOrderNo);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 部署マスタ、ロケーションマスタにデータがない場合はエラーとする。
	 * @param frm 受入入力画面FORM
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final AcceptDetailForm frm);

	/**
	 * 購買外注データを購買NO(KEY)で全項目取得する.
	 * @param purchaseNo 購買NO
	 * @return PurchaseSubcontract 購買外注データBean
	 * @throws NoDataException 対象データ無しエラー
	 */
	PurchaseSubcontract getEntity(final String purchaseNo)
			throws NoDataException;

	/**
	 * 計装IF入荷ロットコード対応にデータを書き込む
	 * 
	 * @param frm 受入入力画面FORM
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void setCodeNyukalot(final AcceptDetailForm frm) throws NoDataException,
			Exception;

	/**
	 * 購買オーダーテーブル登録処理(DELETE/INSERT)を行う.
	 * @param frm 受入入力画面FORM
	 * @param organizationCd ログイン者の部署コード
	 * @param tantoCd ログイン者コード
	 * @param bean 登録前検索購買データBean
	 * @param check 数値項目用表示ロジッククラス
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void insert(final AcceptDetailForm frm, final String organizationCd,
			final String tantoCd, final PurchaseSubcontract bean,
			final CheckDigitUtilsLogic check) throws NoDataException, Exception;

	/**
	 * 購買オーダーテーブル更新処理を行う.
	 * 
	 * @param frm 受入入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @param bean 購買外注オーダテーブルBean
	 * @param check 数値項目用表示ロジッククラス
	 * @return PurchaseSubcontract 購買外注オーダテーブルBean
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	PurchaseSubcontract update(final AcceptDetailForm frm,
			final String tantoCd, final PurchaseSubcontract bean,
			final CheckDigitUtilsLogic check) throws NoDataException, Exception;

	/**
	 * 購買オーダーテーブル削除処理を行う.
	 * 
	 * @param frm 受入入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @param bean 購買外注オーダテーブルBean
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void delete(final AcceptDetailForm frm, final String tantoCd,
			final PurchaseSubcontract bean) throws NoDataException, Exception;

	/**
	 * PRO_IF_MATERIAL_IMPORT_RESULTを実行する
	 * @param frm 購買外注オーダテーブル
	 * @param tantoCd 担当者コード
	 * @throws AcceptLogicException プロシージャ実行時エラー
	 */
	void callProIf(final AcceptDetailForm frm, final String tantoCd)
			throws AcceptLogicException;

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strModuleCd, final String strErrorCd,
			final String strErrorMsg, final String tantoCd) throws Exception;

	/**
	 * 品目が無償支給か否かを取得する
	 * @param itemCd 品目コード
	 * @return True:無償支給品
	 */
	boolean isMusyoSikyu(final String itemCd);
}
