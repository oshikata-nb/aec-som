/*
 * Created on 2008/08/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * PaymentDao．支払トランザクションテーブル用DAO
 * @author tosco
 */
public interface AltPaymentDao {

	/** BEANアノテーション. */
	Class BEAN = AltPayment.class;

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * 支払一覧画面－検索
	 * @param condition 検索条件
	 * @return BalanceList 支払予定一覧結果
	 */
	List<AltPayment> getSearchList(AltPaymentPagerCondition condition);

	/** ARGSアノテーション findBySlipNo(). */
	// String findBySlipNo_ARGS = "SLIP_NO";
	String findBySlipNo_ARGS = "slipNo";

	/** QUERYアノテーション findByCreditNo(). */
	// String findBySlipNo_QUERY = "ORDER BY ROW_NO";
	/**
	 * 支払詳細画面－検索
	 * @param slipNo 支払番号
	 * @return List 支払一覧結果
	 */
	List<AltPayment> findBySlipNo(final String slipNo);

	/**
	 * 登録処理
	 * 
	 * @param bean 支払Bean
	 * @return 登録件数
	 */
	int insert(AltPayment bean);

	/**
	 * 更新処理
	 * 
	 * @param bean 支払Bean
	 * @return 更新件数
	 */
	int update(AltPayment bean);

	/** QUERYアノテーション delete(). */
	String delete_QUERY = "SLIP_NO = ? ";

	/**
	 * 削除処理
	 * 
	 * @param slipNo 支払番号
	 * @return 削除件数
	 */
	int delete(String slipNo);

	/** ARGSアノテーション updateApprovalStatus(). */
	String updateApprovalStatus_ARGS = "approvalStatus, approvedby, approvaldate, slipNo";

	/**
	 * 承認ステータス更新処理
	 * 
	 * @param approvalStatus 承認ステータス
	 * @param approvedby 承認者
	 * @param approvaldate 承認日時
	 * @param slipNo 伝票番号
	 * @return 更新件数
	 */
	int updateApprovalStatus(BigDecimal approvalStatus, String approvedby,
			Timestamp approvaldate, String slipNo);

	/** ARGSアノテーション getPaymentHeaderList(). */
	String getPaymentHeaderList_ARGS = "supplierCd, payableAmount";

	/**
	 * 支払予定検索
	 * 
	 * @param supplierCd 仕入先コード
	 * @param payableAmount --支払残高
	 * @return List<AltPayment>
	 */
	List<AltPayment> getPaymentHeaderList(String supplierCd,
			BigDecimal payableAmount);

	/** ARGSアノテーション getPaidEntity(). */
	String getPaidEntity_ARGS = "supplierCd, strPaymentDate";

	/**
	 * 支払済金額取得
	 * 
	 * @param supplierCd 仕入先コード
	 * @param strPaymentDate 支払日付
	 * @return AltPayment
	 */
	AltPayment getPaidEntity(String supplierCd, String strPaymentDate);

	/** ARGSアノテーション getBalanceEntity(). */
	String getBalanceEntity_ARGS = "supplierCd, strPaymentDate";

	/**
	 * 支払残高取得
	 * 
	 * @param supplierCd 仕入先コード
	 * @param strPaymentDate 支払日付
	 * @return AltPayment
	 */
	AltPayment getBalanceEntity(String supplierCd, String strPaymentDate);

	/** ARGSアノテーション checkEntity(). */
	String checkEntity_ARGS = "paymentDate, supplierCd, organizationCd";

	/**
	 * 支払登録チェック
	 * 
	 * @param paymentDate 支払日付
	 * @param supplierCd 仕入先コード
	 * @param organizationCd 部署コード
	 * @return List<AltPayment>
	 */
	List<AltPayment> checkEntity(Timestamp paymentDate, String supplierCd,
			String organizationCd);

	/** ARGSアノテーション updateIssuedDivision(). */
	String updateIssuedDivision_ARGS = "issuedDivision, tantoCd, slipNo";

	/**
	 * 伝票発行フラグ更新処理
	 * @param issuedDivision 伝票発行フラグ
	 * @param tantocd 担当者コード
	 * @param slipNo 伝票番号
	 * @return 更新件数
	 */
	int updateIssuedDivision(BigDecimal issuedDivision, String tantocd,
			String slipNo);

	/** ARGSアノテーション getOffsetAmount */
	String getOffsetAmount_ARGS = "offsetDate, venderCd";

	/**
	 * 支払予定 相殺検索
	 * @param offsetDate 相殺日
	 * @param venderCd 支払先コード
	 * @return AltPayment
	 */
	AltPayment getOffsetAmount(Timestamp offsetDate, String venderCd);

	/** ARGSアノテーション getTotalPaymentAmount */
	String getTotalPaymentAmount_ARGS = "supplierCd";

	/**
	 * 支払額合計検索
	 * @param supplierCd 支払先コード
	 * @return AltPayment
	 */
	AltPayment getTotalPaymentAmount(String supplierCd);
}
