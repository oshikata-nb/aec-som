/*
 * Created on 2008/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserHeader;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserSales;
import com.asahikaseieng.exception.NoDataException;

/**
 * 消込入力詳細 ロジッククラス interface.
 * @author tosco
 */
public interface EraserDetailLogic {

	/**
	 * 消込・請求・入金データ検索処理を行う.消込入力詳細(上・中段)
	 * 
	 * @param eraserNo 消込番号
	 * @param sectionCd 部門コード
	 * @param venderCd  請求先コード
	 * @param tantoCd 	 担当者コード
	 * @param tantoNm 	 担当者名称
	 * @return List<EraserDetail> 消込入力詳細(上・中段)データ
	 * @throws NoDataException NoDataException
	 */
	List<ClaimEraserDetail> getDetailData(final String eraserNo
									, final String sectionCd
									, final String venderCd
									, final String tantoCd
									, final String tantoNm) throws NoDataException;

	/**
	 * 請求明細データ検索処理を行う.消込入力詳細(下段)
	 * 
	 * @param eraserNo 消込番号
	 * @param sectionCd 部門コード
	 * @param venderCd  請求先コード
	 * @param tantoCd 	 担当者コード
	 * @param tantoNm 	 担当者名称
	 * @return List<EraserSales> 消込入力詳細(下段) 請求明細データ
	 * @throws NoDataException NoDataException
	 */
	List<ClaimEraserSales> getSalesData(final String eraserNo
									, final String sectionCd
									, final String venderCd
									, final String tantoCd
									, final String tantoNm) throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param headerBean 消込ヘッダーBean
	 * @param detailList 請求ヘッダ・入金データリスト
	 * @param salesList 請求データリスト
	 * @throws NoDataException データ無し例外
	 */
	void insert(final ClaimEraserHeader headerBean
				, final List<ClaimEraserDetail> detailList
				, final List<ClaimEraserSales> salesList) throws NoDataException;

	/**
	 * 更新処理を行う.
	 * @param headerBean 消込ヘッダーBean
	 * @param detailList 請求ヘッダ・入金データリスト
	 * @param salesList 請求データリスト
	 * @throws NoDataException データ無し例外

	 */
	void update(final ClaimEraserHeader headerBean
			, final List<ClaimEraserDetail> detailList
			, final List<ClaimEraserSales> salesList) throws NoDataException;

	/**
	 * 削除処理を行う.
	 * @param headerBean 消込ヘッダーBean
	 * @param detailList 請求ヘッダ・入金データリスト
	 * @param salesList 請求データリスト
	 * @throws NoDataException データ無し例外
	 */
	void delete(final ClaimEraserHeader headerBean
			, final List<ClaimEraserDetail> detailList
			, final List<ClaimEraserSales> salesList) throws NoDataException;

}
