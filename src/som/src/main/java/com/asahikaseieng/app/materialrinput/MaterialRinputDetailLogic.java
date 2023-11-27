/*
 * Created on 2009/03/26
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.materialrinput;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetail;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetailList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 外注原材料投入実績入力 ロジッククラス interface. 
 * @author tosco
 */
public interface MaterialRinputDetailLogic {

	/**
	 * 購買外注オーダー検索処理を行う.
	 * @param buySubcontractOrderNo 発注番号
	 * @param check                 数値項目用表示ロジッククラス
	 * @return MaterialRinputDetail ヘッダ部データ
	 * @throws NoDataException データが存在しない例外
	 */
	MaterialRinputDetail getHeader(final String buySubcontractOrderNo
									, final CheckDigitUtilsLogic check)
									throws NoDataException;

	/**
	 * 購買外注原材料投入実績検索処理を行う.
	 * @param frm					 外注原材料投入実績入力 Formクラス
	 * @param check                 数値項目用表示ロジッククラス
	 * @param venderCd              仕入先コード
	 * @return List<MaterialRinputDetailList> 明細部データ
	 */
	List<MaterialRinputDetailList> getDetailList(final MaterialRinputDetailForm frm
										, final CheckDigitUtilsLogic check
										, final String venderCd);

	/**
	 * 基本処方の存在チェック
	 * @param frm   購買外注原材料投入実績入力用画面Form
	 * @return ActionMessages エラーメッセージ
	 */
	ActionMessages checkRecipe(final MaterialRinputDetailForm frm);

	/**
	 * 指定した基本処方と生産出来高を元に、原材料の実績数量を計算したリストを作成する.
	 * @param frm   購買外注原材料投入実績入力用画面Form
	 * @param check 数値項目用表示ロジッククラス
	 * @return List<MaterialRinputDetailList> 明細部データ
	 */
	List<MaterialRinputDetailList> calculate(final MaterialRinputDetailForm frm
										, final CheckDigitUtilsLogic check);

//	/**
//	 * 登録時のマスタチェックを行う.<br>
//	 * 　部署マスタにデータがない場合はエラーとする。
//	 * @param organizationCd 部署コード
//	 * @return ActionMessages エラー内容
//	 */
//	ActionMessages checkForRegist(final String organizationCd);
//
//	/**
//	 * 購買外注データを購買NO(KEY)で全項目取得する.
//	 * @param purchaseNo 購買NO
//	 * @return PurchaseSubcontract 購買外注データBean
//	 * @throws NoDataException 対象データ無しエラー
//	 */
//	PurchaseSubcontract getEntity(final String purchaseNo) throws NoDataException;

//	/**
//	 * 購買オーダーテーブル登録処理(DELETE/INSERT)を行う.
//	 * @param frm            受入入力画面FORM
//	 * @param organizationCd ログイン者の部署コード
//	 * @param tantoCd        ログイン者コード
//	 * @param bean           登録前検索購買データBean
//	 * @param check          数値項目用表示ロジッククラス
//	 * @throws NoDataException データ無し例外
//	 * @throws Exception 例外
//	 */
//	void insert(final AcceptDetailForm frm
//				, final String organizationCd
//				, final String tantoCd
//				, final PurchaseSubcontract bean
//				, final CheckDigitUtilsLogic check) throws NoDataException, Exception;
//
	/**
	 * 購買外注オーダー更新処理、購買外注原材料投入実績削除／登録処理を行う.
	 * @param frm 外注原材料投入実績入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void insert(final MaterialRinputDetailForm frm
				, final String tantoCd) throws NoDataException, Exception;

	/**
	 * 在庫引落をリセットする
	 * 
	 * @param frm 外注原材料投入実績入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void reset(final MaterialRinputDetailForm frm, final String tantoCd)
		throws NoDataException, Exception;


}
