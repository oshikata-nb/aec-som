/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import com.asahikaseieng.dao.entity.recipeheader.RecipeHeader;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;


/**
 * 製造指図-新規入力 ロジッククラス interface.
 * @author tosco
 */
public interface DirectionNewDetailLogic {

	/**
	 * 調合タンクの存在チェック
	 * @param recipeId レシピインデックス
	 * @param compoundTankNo 調合タンクNO
	 * @return 調合タンク名称
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	String checkExistsCompoundTank(String recipeId, String compoundTankNo) throws NoDataException;
	/**
	 * ホールドタンクの存在チェック
	 * @param compoundTankNo 調合タンクNO
	 * @param holdTankNo ホールドタンクNO
	 * @return ホールドタンク名称
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	String checkExistsHoldTank(String compoundTankNo, String holdTankNo) throws NoDataException;
	/**
	 * 予備溶解タンクの存在チェック
	 * @param productionLine 生産ライン
	 * @param dissolutionTankNo 予備溶解タンクNO
	 * @return 予備溶解タンク名称
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	String checkExistsDissolutionTank(String productionLine, String dissolutionTankNo) throws NoDataException;
	/**
	 * 処方ヘッダーを取得する
	 * @param recipeId レシピインデックス
	 * @return RecipeHeader
	 * @throws NoDataException データが取得できなかった場合
	 */
	RecipeHeader getRecipeHeader(String recipeId) throws NoDataException;
	/**
	 * 登録処理
	 * @param bean 指図ヘッダ登録データ
	 * @param unitDivision 単位区分
	 */
	void update(final DirectionDirectionHeaderList bean, final String unitDivision);

}
