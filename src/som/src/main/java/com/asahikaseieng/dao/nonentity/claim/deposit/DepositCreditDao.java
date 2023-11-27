/*
 * Created on 2008/08/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.deposit;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * CreditDao．入金トランザクションテーブル用DAO
 * @author tosco
 */
public interface DepositCreditDao {

	/** BEANアノテーション. */
	Class BEAN = DepositCredit.class;

	/** ARGSアノテーション getList */
	String getSearchList_ARGS = "condition";

	/**
	 * 入金一覧画面－検索
	 * @param condition 検索条件
	 * @return BalanceList 入金予定一覧結果
	 */
	List<DepositCredit> getSearchList(DepositCreditListPagerCondition condition);

	/** ARGSアノテーション findByCreditNo(). */
	String findByCreditNo_ARGS = "creditNo";

	/**
	 * 入金詳細画面－検索
	 * @param creditNo 入金番号
	 * @return BalanceList 入金予定一覧結果
	 */
	List<DepositCredit> findByCreditNo(String creditNo);

	/** ARGSアノテーション deleteCreditNo(). */
	String deleteCreditNo_ARGS = "creditNo";

	/**
	 * 削除処理(指定した入金番号のデータを全て削除)
	 * 
	 * @param creditNo 入金番号
	 * @return 削除件数
	 */
	int deleteCreditNo(String creditNo);

	/**
	 * 入金科目一覧検索
	 * @return List<DepositCredit>
	 */
	List<DepositCredit> getClassificationAccountsList();

	/** ARGSアノテーション updateApprovalStatus(). */
	String updateApprovalStatus_ARGS = "approvalStatus, approvedby, approvaldate, creditNo";

	/**
	 * 承認ステータス更新処理
	 * 
	 * @param approvalStatus 承認ステータス
	 * @param approvedby 承認者
	 * @param approvaldate 承認日時
	 * @param creditNo 入金番号
	 * @return 更新件数
	 */
	int updateApprovalStatus(BigDecimal approvalStatus, String approvedby,
			Timestamp approvaldate, String creditNo);

	/** ARGSアノテーション updateIssuedDivision(). */
	String updateIssuedDivision_ARGS = "issuedDivision, tantoCd, creditNo";

	/**
	 * 伝票発行フラグ更新処理
	 * @param issuedDivision 伝票発行フラグ
	 * @param tantocd 担当者コード
	 * @param creditNo 入金番号
	 * @return 更新件数
	 */
	int updateIssuedDivision(BigDecimal issuedDivision, String tantocd,
			String creditNo);

	/** ARGSアノテーション checkApproval(). */
	String checkApproval_ARGS = "creditNo";

	/**
	 * 入金承認操作可能チェック
	 * @param creditNo 入金番号
	 * @return List<DepositCredit> 承認可能リスト
	 */
	List<DepositCredit> checkApproval(String creditNo);
	
	/**
	 * 承認ステータス更新処理
	 * 
	 * @param approvalStatus 承認ステータス
	 * @param approvedby 承認者
	 * @param approvaldate 承認日時
	 * @param creditNo 入金番号
	 * @return 更新件数
	 */
	DepositCredit getClaimSalesAmount(String organizationCd,  String venderCd , String creditDate);

	/** ARGSアノテーション updateIssuedDivision(). */
	String getClaimSalesAmount_ARGS = "organizationCd, venderCd, creditDate";
}
