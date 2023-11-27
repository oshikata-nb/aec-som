/*
 * Created on 2009/04/08
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.production;

import java.util.List;

import com.asahikaseieng.dao.entity.aspproduction.AspProduction;
import com.asahikaseieng.dao.nonentity.production.ProductionDetail;
import com.asahikaseieng.dao.nonentity.production.ProductionDetailList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 生産計画入力　明細画面 ロジッククラス interface. 
 * @author tosco
 */
public interface ProductionDetailLogic {

	/**
	 * 選択された生産計画に該当するデータを取得する.（ヘッダ部）
	 * @param itemCd 品目コード
	 * @param orderLet 生産計画年月
	 * @return ProductionDetail ヘッダデータ
	 * @throws NoDataException 対象データなしエラー
	 */
	ProductionDetail getHeader(final String itemCd, final String orderLet) throws NoDataException;

	/**
	 * 選択された生産計画に該当するデータを取得する.（明細部）
	 * @param itemCd 品目コード
	 * @param orderLet 生産計画年月
	 * @param headBean ヘッダ部データ
	 * @return List<ProductionDetailList> 明細部データ
	 * @throws NoDataException データが存在しない例外
	 */
	List<ProductionDetailList> getEntity(final String itemCd
								, final String orderLet
								, final ProductionDetail headBean)
									throws NoDataException;

	/**
	 * 選択された生産計画の件数を取得する
	 * @param itemCd 品目コード
	 * @param orderLet 生産計画年月
	 * @return int ヘッダデータ
	 * @throws NoDataException 対象データなしエラー
	 */
	int getCountList(final String itemCd, final String orderLet)
		throws NoDataException;

	/**
	 * 入力された品目データを取得する.
	 * @param itemCd 品目コード
	 * @return ProductionDetail ヘッダデータ
	 * @throws NoDataException 対象データなしエラー
	 */
	ProductionDetail getItemEntity(final String itemCd)
		throws NoDataException;

	/**
	 * 更新、登録処理を行う
	 * @param newBean 更新、登録対象ビーン
	 * @param frm Form
	 * @param loginUserId 更新者ID
	 * @throws NoDataException データ無し例外
	 */
	void update(final List<AspProduction> newBean
			, final ProductionDetailForm frm
			, final String loginUserId) throws NoDataException;
}
