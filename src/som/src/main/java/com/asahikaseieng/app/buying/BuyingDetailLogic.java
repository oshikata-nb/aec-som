/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.buying;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboboxesBean;
import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.buying.BuyingDetail;
import com.asahikaseieng.dao.nonentity.buyinginout.BuyingGetInoutData;
import com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxes;
import com.asahikaseieng.exception.NoDataException;

/**
 * 仕入詳細 ロジッククラス interface.
 * @author tosco
 */
public interface BuyingDetailLogic {

	/**
	 * 仕入詳細検索処理を行う.
	 * @param slipNo 仕入番号
	 * @return BuyingDetail
	 * @throws NoDataException データ無し例外
	 */
	BuyingDetail getEntity(final String slipNo) throws NoDataException;

	/**
	 * 仕入詳細更新処理を行う.
	 * @param bean 更新対象ビーン
	 * @param frm 画面情報
	 * 
	 * @throws NoDataException データ無し例外
	 */
	void update(final PurchaseSubcontract bean, final BuyingDetailForm frm)
			throws NoDataException;

	/**
	 * 承認依頼ボタンが押された時の更新処理を行う.
	 * @param bean 更新対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	void updateApprovalRequest(final PurchaseSubcontract bean)
			throws NoDataException;

	/**
	 * 仕入詳細更新処理を行う.(値がある項目のみ更新)
	 * @param bean 更新対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	void updateUnlessNull(final PurchaseSubcontract bean)
			throws NoDataException;

	/**
	 * 仕入詳細登録処理を行う.
	 * @param bean 登録対象ビーン
	 * @param inoutNo 受払番号
	 */
	void insert(final PurchaseSubcontract bean, final String inoutNo);

	/**
	 * 削除処理を行う
	 * @param deleteBean 削除対象ビーン
	 * @param loginUserId ログインユーザー
	 * @param frm 画面情報
	 * @throws NoDataException データ無し例外
	 */
	void delete(final PurchaseSubcontract deleteBean, final String loginUserId,
			final BuyingDetailForm frm) throws NoDataException;

	/**
	 * 取消元データの更新処理を行う
	 * @param updateSlipNo 更新対象の仕入番号
	 * @param slipNo 取消データの仕入番号
	 * @param rowNo 取消データの行番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データ無し例外
	 */
	void updateCancel(final String updateSlipNo, final String slipNo,
			final BigDecimal rowNo, final String loginUserId)
			throws NoDataException;

	/**
	 * データ集計区分取得
	 * @param categoryDivision 仕入先コード
	 * @return String
	 */
	String getDataTotalDivision(final String categoryDivision);

	/**
	 * 取引先マスタ存在チェック
	 * @param venderCd 仕入先コード
	 * @return 取得件数
	 */
	int getCountVender(final String venderCd);

	/**
	 * 品目マスタ存在チェック用
	 * @param itemCd 品目コード
	 * @return PurchaseDetail
	 */
	int getCountItem(final String itemCd);

	/**
	 * 組織マスタ存在チェック
	 * @param organizationId 部署コード
	 * @return 取得件数
	 */
	int getCountOrganization(final String organizationId);

	/**
	 * 会計部門マスタ存在チェック
	 * @param bumonCd 会計部門コード
	 * @return 取得件数
	 */
	int getCountBumon(final String bumonCd);

	/**
	 * 科目マスタ存在チェック
	 * @param accountsCd 科目コード
	 * @return 取得件数
	 */
	int getCountAccounts(final String accountsCd);

	/**
	 * ログインユーザ定義マスタ存在チェック
	 * @param tantoCd 担当者コード
	 * @return 取得件数
	 */
	int getCountLogin(final String tantoCd);

	/**
	 * ロケーションマスタ存在チェック
	 * @param locationCd ロケーションコード
	 * @return 取得件数
	 */
	Location getCountLocation(final String locationCd);

	/**
	 * 仕入先別単価マスタ存在チェック用
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return ある・無し値
	 */
	int getCountUnitprice(final String itemCd, final String venderCd);

	/**
	 * 仕入区分コンボボックスを取得する
	 * @param cancelFlg 取消の区分を表示するかしないかのフラグ
	 * @return List<BuyingStockingDivisionComboboxes>
	 */
	List<BuyingStockingDivisionComboboxes> getBuyingStockingDivisionComboboxes(
			final boolean cancelFlg);

	/**
	 * 仕入区分コンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @param frm 仕入入力画面フォーム
	 */
	void createBuyingStockingDivisionCombobox(final boolean zero,
			final BuyingDetailForm frm);

	/**
	 * 品目コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @return PurchaseDetail
	 * @throws NoDataException データが存在しない例外
	 */
	BuyingDetail getItemRelatedData(final String itemCd) throws NoDataException;

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return BuyingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	BuyingDetail getVenderRelatedData(final String itemCd, final String venderCd)
			throws NoDataException;

	/**
	 * 仕入数量入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param stockingQuantity 仕入数量
	 * @return BuyingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	BuyingDetail getStockingQuantityRelatedData(final String itemCd,
			final String venderCd, final BigDecimal stockingQuantity)
			throws NoDataException;

	/**
	 * 消費税関連項目を取得する。
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return BuyingDetail 消費税関連項目
	 */
	BuyingDetail getTaxRelatedData(final String itemCd, final String venderCd);

	/**
	 * 支払先コードを取得する
	 * @param venderCd 仕入先コード
	 * @return 支払先コード
	 */
	String getPayeeCd(final String venderCd);

	/**
	 * 仕入れ番号から受払データを取得する
	 * @param slipNo 仕入番号
	 * @return BuyingGetInoutData
	 */
	BuyingGetInoutData getInoutData(final String slipNo);

	/**
	 * 受払数量の数値変換
	 * @param inoutQty 受払数量
	 * @param unitOfOperationManagement 運用管理単位
	 * @param venderCd 仕入先コード
	 * @return String 受払数量
	 */
	String getInoutQty(final BigDecimal inoutQty,
			final String unitOfOperationManagement, final String venderCd);

	/**
	 * 消費税率コンボボックス用データ取得
	 *
	 * @param locale
	 *            言語コード
	 * @return bean
	 *            コンボボックス
	 * @throws Exception
	 *            NoDataException
	 */
	ComboboxesBean getPurchaseTaxCombobox() throws NoDataException;

}
