/*
 * Created on 2008/10/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmList;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 消込入力一覧 ロジッククラス(カスタマイズ)
 * @author tosco
 */
public interface EraserCsmListLogic {

	/**
	 * 消込入力一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<ClaimEraserCsmList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<ClaimEraserCsmList> getSearchList(
			final ClaimEraserCsmListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ClaimEraserCsmListForReport>
	 */
	List<ClaimEraserCsmListForReport> getListForReport(
			final ClaimEraserCsmListConditionForReport condition);
}
