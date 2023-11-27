/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCredit;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.companybank.CompanyBankListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * DepositDetailCategoryLogicクラス．入金入力（詳細)入金分類変更イベントロジック
 * @author tosco
 */
public interface DepositDetailLogic {
	/**
	 * 検索処理を行う.入金入力検索処理
	 * @param venderCd 取引先コード
	 * @param venderDivision 取引先分類
	 * @return VenderDetail
	 * @throws NoDataException 検索結果が存在しない場合発生
	 */
	VenderDetail getVenderEntity(String venderCd, String venderDivision)
			throws NoDataException;

	/**
	 * 入金テーブルにデータを挿入する。
	 * @param list 更新明細データ
	 * @return エラーメッセージ（正常時null)
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	String insert(List<DepositCredit> list) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 入金番号で入金トランザクションテーブルからデータを取得
	 * @param creditNo 入金番号
	 * @return 入金明細データ
	 * @throws NoDataException データが存在しない場合発生
	 */
	List<DepositCredit> findByCreditNo(final String creditNo)
			throws NoDataException;

	/**
	 * 入金テーブルにデータを更新する。（実際はDeleteInsert）
	 * @param list 更新明細データ
	 * @return エラーメッセージ（正常時null)
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	String update(final List<DepositCredit> list)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 入金テーブルから入金番号に合致するデータを全て削除する
	 * @param creditNo 入金番号
	 * @return 削除件数
	 */
	int delete(final String creditNo);

	/**
	 * 入金テーブルから入金番号に合致するデータを全て削除する
	 * @param credit 削除データ
	 * @return 削除件数
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	// int delete(final DepositCredit credit) throws IllegalAccessException,
	// InvocationTargetException;
	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	void statusUpdate(final DepositDetailForm frm, final BigDecimal status,
			final String tantoCd) throws NoDataException;

	/**
	 * 入金勘定科目一覧検索
	 * @return List<DepositCredit>
	 * @throws NoDataException NoDataException
	 */
	List<DepositCredit> getClassificationAccountsList() throws NoDataException;

	/**
	 * 勘定科目検索
	 * @param accountsCd 勘定科目コード
	 * @return AccountsDetail
	 */
	AccountsDetail getAccountsEntity(String accountsCd);

	/**
	 * 銀行取得
	 * @return List<CompanyBankListForComboboxes>
	 */
	List<CompanyBankListForComboboxes> getBankList();

	/**
	 * 名称マスタ検索
	 * @return NamesDetail
	 */
	NamesDetail getNamesEntity();

	/**
	 * 分類マスタ検索
	 * @param dataType データ種別
	 * @param categoryDivision 分類コード
	 * @return ClassificationDetail
	 */
	ClassificationDetail getClassificationEntity(BigDecimal dataType,
			String categoryDivision);

	/**
	 * 分類取得
	 * @param dataType サイトデータ種別
	 * @param arDivision 売掛対象区分
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType, final BigDecimal arDivision);
	
	/**
	 * 
	 * 入力補助用請求額、売上額取得
	 * @param organizationCd
	 * @param venderCd
	 * @param creditDateFrom
	 * @param creditDateTo
	 * @return
	 * @throws NoDataException
	 */
	DepositCredit getClaimSalesAmount(final String organizationCd,
			final String venderCd,
			final String creditDate) throws NoDataException;
}
