/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchase;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseDetail;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseRemarkList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 発注詳細 ロジッククラス interface.
 * @author tosco
 */
public interface PurchaseDetailLogic {

	/**
	 * 発注詳細検索処理を行う.
	 * @param purchaseNo 購買NO
	 * @param tantoCd ログインユーザー
	 * @return PurchaseDetail
	 * @throws NoDataException データが存在しない例外
	 */
	PurchaseDetail getEntity(final String purchaseNo, final String tantoCd)
			throws NoDataException;

	/**
	 * 発注詳細更新処理を行う.
	 * @param bean 更新対象ビーン
	 * @param orderLocation 発注まとめ場所
	 * @throws NoDataException データ無し例外
	 * 
	 */
	void update(final PurchaseSubcontract bean, final String orderLocation)
			throws NoDataException;

	/**
	 * 発注詳細登録処理を行う.
	 * @param bean 登録対象ビーン
	 */
	void insert(final PurchaseSubcontract bean);

	/**
	 * 発注詳細削除処理を行う.
	 * @param bean 削除対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	void delete(final PurchaseSubcontract bean) throws NoDataException;

	/**
	 * 品目マスタチェック
	 * @param itemCd 品目コード
	 * @return 取得件数
	 */
	Item getItem(final String itemCd);

	/**
	 * 取引先マスタ存在チェック
	 * @param venderCd 仕入先コード
	 * @return 取得件数
	 */
	int getCountVender(final String venderCd);

	/**
	 * 組織マスタ存在チェック
	 * @param organizationCd 部署コード
	 * @return 取得件数
	 */
	int getCountOrganization(final String organizationCd);

	/**
	 * 納入先マスタ存在チェック
	 * @param locationCd 納入先コード
	 * @return 取得件数
	 */
	int getCountDelivery(final String locationCd);

	/**
	 * 納入先マスタ存在チェック
	 * @param locationCd 納入先コード
	 * @return 取得件数
	 */
	Location getCountLocation(final String locationCd);

	/**
	 * ログインユーザ定義マスタ存在チェック
	 * @param tantoCd 担当者コード
	 * @return 取得件数
	 */
	int getCountLogin(final String tantoCd);

	/**
	 * 仕入先別単価マスタ存在チェック用
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return ある・無し値
	 */
	int getCountUnitprice(final String itemCd, final String venderCd);

	/**
	 * 所属コード存在チェック用
	 * @param organizationCd 部署コード
	 * @param tantoCd 担当者コード
	 * @return 登録件数
	 */
	int getCountBelong(final String organizationCd, final String tantoCd);

	/**
	 * 備考マスタ検索処理を行う
	 * @param venderCd 検索条件
	 * @param locationCd 検索条件
	 * @param itemCd 検索条件
	 * @return List<PurchaseRemarkList> 備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PurchaseRemarkList> getRemarkList(final String venderCd,
			final String locationCd, final String itemCd)
			throws NoDataException;

	/**
	 * 購買ステータス更新処理を行う.
	 * @param bean 更新対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	void updateStatus(final PurchaseSubcontract bean) throws NoDataException;

	/**
	 * 品目コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @return PurchaseDetail
	 * @throws NoDataException データが存在しない例外
	 */
	PurchaseDetail getItemRelatedData(final String itemCd)
			throws NoDataException;

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param orderQuantity 発注数量
	 * @return PurchaseDetail
	 * @throws NoDataException データが存在しない例外
	 */
	PurchaseDetail getVenderRelatedData(final String itemCd,
			final String venderCd, final BigDecimal orderQuantity)
			throws NoDataException;

	/**
	 * 発注数量入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param orderQuantity 発注数量
	 * @return PurchaseDetail
	 * @throws NoDataException データが存在しない例外
	 */
	PurchaseDetail getOrderQuantityRelatedData(final String itemCd,
			final String venderCd, final BigDecimal orderQuantity)
			throws NoDataException;
}
