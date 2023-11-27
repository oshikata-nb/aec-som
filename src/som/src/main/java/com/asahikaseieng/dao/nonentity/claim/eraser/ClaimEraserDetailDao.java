/*
 * Created on 2008/07/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.util.List;

/**
 * 消込入力詳細画面用Daoインターフェース.
 * @author tosco
 */
public interface ClaimEraserDetailDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getDetailData(). */
	String getDetailData_ARGS = "eraserNo, sectionCd, venderCd, tantoCd, tantoNm";

	/**
	 * 消込入力詳細データ検索処理.
	 * @param eraserNo 消込番号
	 * @param sectionCd 部門コード
	 * @param venderCd 請求先コード
	 * @param tantoCd 担当者コード
	 * @param tantoNm 担当者名称
	 * @return List<EraserDetail> 消込入力詳細データ
	 */
	List<ClaimEraserDetail> getDetailData(String eraserNo, String sectionCd,
			String venderCd, String tantoCd, String tantoNm);

	/**
	 * 消込入力詳細 入金トランザクション(入金)更新処理 （消込額、入金消込残、消込完了フラグ、消込完了日）
	 * 
	 * @param bean 消込入力用Bean
	 * @return 更新件数
	 */
	int updateCredit(ClaimEraserDetail bean);

	/**
	 * 消込入力詳細 入金トランザクション(相殺)更新処理 （消込額、入金消込残、消込完了フラグ、消込完了日）
	 * 
	 * @param bean 消込入力用Bean
	 * @return 更新件数
	 */
	int updateOffsetGroupData(ClaimEraserDetail bean);

	/**
	 * 消込入力詳細 グループ間相殺トランザクション更新処理 （消込完了フラグ、消込完了日）
	 * 
	 * @param bean 消込入力用Bean
	 * @return 更新件数
	 */
	int updateOffsetGroup(ClaimEraserDetail bean);

	/**
	 * 消込入力詳細 支払トランザクション更新処理 （消込完了フラグ、消込完了日）
	 * 
	 * @param bean 消込入力用Bean
	 * @return 更新件数
	 */
	int updatePayment(ClaimEraserDetail bean);

	/**
	 * 消込入力詳細 入金トランザクション更新処理(相殺データ) （消込完了フラグ、消込完了日）
	 * 
	 * @param bean 消込入力用Bean
	 * @return 更新件数
	 */
	int updateCreditOffset(ClaimEraserDetail bean);

}
