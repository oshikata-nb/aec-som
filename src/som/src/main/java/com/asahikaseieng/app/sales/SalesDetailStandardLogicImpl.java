/*
 * Created on 2009/03/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.sales;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.entity.inoutrecord.InoutRecord;
import com.asahikaseieng.dao.entity.inoutrecord.InoutRecordDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailStandardEntity;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailStandardEntityDao;
import com.asahikaseieng.dao.nonentity.salesinout.SalesGetInoutData;
import com.asahikaseieng.dao.nonentity.salesinout.SalesGetInoutDataDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;

/**
 * 売上詳細(標準) ロジック実装クラス
 * @author tosco
 */
public class SalesDetailStandardLogicImpl extends AbstractSalesDetailLogic
		implements SalesDetailStandardLogic {

	/** 売上詳細(標準)画面用売上トランザクションテーブル用Dao */
	private SalesDetailStandardEntityDao salesDetailStandardEntityDao;

	/** 取引先マスタ用Dao */
	private VenderDao venderDao;

	/** 受払履歴更新用Dao * */
	private InoutRecordDao inoutRecordDao;

	/** 受払履歴データ取得用Dao * */
	private SalesGetInoutDataDao salesGetInoutDataDao;

	/**
	 * コンストラクタ.出荷指図
	 */
	public SalesDetailStandardLogicImpl() {
	}

	/**
	 * 売上番号から受払データを取得する
	 * @param slipNo 売上番号
	 * @return BuyingGetInoutData
	 */
	public SalesGetInoutData getInoutData(final String slipNo) {

		return salesGetInoutDataDao.getEntity(slipNo);

	}

	/**
	 * 検索処理を行なう.売上番号
	 * @param salesNo 売上番号
	 * @return ShippingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public SalesDetailStandardEntity getEntity(final String salesNo)
			throws NoDataException {
		checkParams(salesNo);

		// 売上トランザクションデータ取得
		SalesDetailStandardEntity bean = salesDetailStandardEntityDao
				.getEntity(salesNo);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 品目情報を取得する
	 * @param itemCd 品目コード
	 * @return SalesDetailStandardEntity
	 * @throws NoDataException データが存在しない例外
	 */
	public SalesDetailStandardEntity getSalesByItem(final String itemCd)
			throws NoDataException {

		// 品目情報を取得
		SalesDetailStandardEntity bean = salesDetailStandardEntityDao
				.getSalesByItem(itemCd);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 数量の検証(autocompleteで取得していない場合の対応)
	 * @param frm 売上詳細(標準)画面FORM
	 * @param errors 検証エラー内容
	 * @return boolean チェック結果 true:OK false:エラー
	 */
	public boolean checkDigitForSales(final SalesDetailStandardForm frm,
			final ActionMessages errors) {
		// 品目関連の数値チェック
		return super.checkDigitForItem(frm, errors);
	}

	/**
	 * 売上トランザクションデータの登録処理を行う
	 * @param frm 売上詳細(標準)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void insert(final SalesDetailStandardForm frm,
			final LoginInfo loginInfo) throws NoDataException, Exception {

		// 区分が返品以外の場合クリアする
		if (!SalesConst.CATEGOTRY_DIVISION_RETURNS.equals(frm
				.getCategoryDivision())
				&& !SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE.equals(frm
						.getCategoryDivision())) {
			frm.setHousingLocationCd(null); // 入庫ロケーション
			frm.setPackageDirectionNo(null); // 包装指図番号
			frm.setProductLotno(null); // 製品ロット番号
		}

		// 売上トランザクションを登録
		super.insert(frm, loginInfo);

		// 区分が売上の場合 受払履歴のオーダー番号に売上番号をセット
		if ((SalesConst.CATEGOTRY_DIVISION_SALES.equals(frm
				.getCategoryDivision()) || SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE
				.equals(frm.getCategoryDivision()))
				&& frm.getInoutNo() != null && !frm.getInoutNo().equals("")) {

			// 受払履歴更新処理
			InoutRecord inout = inoutRecordDao.getEntity(frm.getInoutNo());
			inout.setOderNo(frm.getSalesNo());
			inout.setUpdatorCd(loginInfo.getTantoCd());
			inoutRecordDao.update(inout);
		}

	}

	/**
	 * 売上トランザクションデータの更新処理を行う
	 * @param frm 売上詳細(標準)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void update(final SalesDetailStandardForm frm,
			final LoginInfo loginInfo) throws NoDataException, Exception {

		// 返品以外の場合クリアする
		if (!SalesConst.CATEGOTRY_DIVISION_RETURNS.equals(frm
				.getCategoryDivision())
				&& !SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE.equals(frm
						.getCategoryDivision())) {
			frm.setHousingLocationCd(null); // 入庫ロケーション
			frm.setPackageDirectionNo(null); // 包装指図番号
			frm.setProductLotno(null); // 製品ロット番号
		}

		// 区分が売上である場合
		if (frm.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_SALES)
				|| frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE)) {

			// 以前の区分が売上である場合
			if (frm.getBeforeCategoryDivision().equals(
				SalesConst.CATEGOTRY_DIVISION_SALES)
					|| frm.getBeforeCategoryDivision().equals(
						SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE)) {

				// 以前と現在の受払番号が異なる場合
				if (!frm.getInoutNo().equals(frm.getBeforeInoutNo())) {
					// 受払履歴更新処理(オーダ番号クリア）
					InoutRecord inoutBefore = inoutRecordDao.getEntity(frm
							.getBeforeInoutNo());
					if (inoutBefore != null) {
						inoutBefore.setOderNo(null);
						inoutBefore.setUpdatorCd(loginInfo.getTantoCd());
						inoutRecordDao.update(inoutBefore);
					}

					// 受払履歴更新処理(オーダ番号を仕入番号で更新）
					InoutRecord inoutNow = inoutRecordDao.getEntity(frm
							.getInoutNo());
					if (inoutNow != null) {
						inoutNow.setOderNo(frm.getSalesNo());
						inoutNow.setUpdatorCd(loginInfo.getTantoCd());
						inoutRecordDao.update(inoutNow);
					}

				}

			} else { // 以前の区分が返品以外の場合
				// 受払履歴更新処理(オーダ番号を仕入番号で更新）
				InoutRecord inoutNow = inoutRecordDao.getEntity(frm
						.getInoutNo());
				if (inoutNow != null) {
					inoutNow.setOderNo(frm.getSalesNo());
					inoutNow.setUpdatorCd(loginInfo.getTantoCd());
					inoutRecordDao.update(inoutNow);
				}

			}

		} else { // 区分が返品以外
			// 以前の区分が売上の場合
			if (frm.getBeforeCategoryDivision().equals(
				SalesConst.CATEGOTRY_DIVISION_SALES)
					|| frm.getBeforeCategoryDivision().equals(
						SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE)) {
				// 受払履歴更新処理(オーダ番号クリア）
				InoutRecord inout = inoutRecordDao.getEntity(frm
						.getBeforeInoutNo());
				if (inout != null) {
					inout.setOderNo(null);
					inout.setUpdatorCd(loginInfo.getTantoCd());
					inoutRecordDao.update(inout);
				}
			}
		}

		// 売上トランザクション更新
		super.update(frm, loginInfo);
	}

	/**
	 * 削除処理を行う.
	 * @param frm 売上詳細(標準)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void delete(final SalesDetailStandardForm frm,
			final LoginInfo loginInfo) throws NoDataException, Exception {

		// 以前の区分が売上である場合
		if (frm.getBeforeCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_SALES)
				|| frm.getBeforeCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE)) {

			// 受払履歴更新処理
			InoutRecord inout = inoutRecordDao
					.getEntity(frm.getBeforeInoutNo());
			if (inout != null) {
				inout.setOderNo(null);
				inout.setUpdatorCd(loginInfo.getTantoCd());
				inoutRecordDao.update(inout);
			}
		}

		// 売上トランザクション削除
		super.delete(frm, loginInfo);
	}

	/**
	 * 売上取消処理を行う.
	 * @param frm 売上詳細(標準)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void cancel(final SalesDetailStandardForm frm,
			final LoginInfo loginInfo) throws NoDataException, Exception {
		// 売上取消処理を行う
		super.cancel(frm, loginInfo);
	}

	/**
	 * 取引先マスタチェック処理
	 * @param venderCd 取引先コード
	 * @return boolean チェック結果 true:OK false:NG
	 */
	public boolean isExistsVender(final String venderCd) {
		// 取引先マスタ
		Vender venderBean = venderDao.getEntity(venderCd,
			SalesConst.VENDER_DIVISION_TS);
		if (venderBean == null) {
			return false;
		}
		return true;
	}

	/**
	 * パラメータチェック
	 * @param param パラメータ
	 */
	private void checkParams(final String param) {

		if (param == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 売上詳細(標準)画面用売上トランザクションテーブル用Daoを設定する
	 * @param salesDetailStandardEntityDao 売上詳細(標準)画面用売上トランザクションテーブル用Dao
	 */
	public void setSalesDetailStandardEntityDao(
			final SalesDetailStandardEntityDao salesDetailStandardEntityDao) {
		this.salesDetailStandardEntityDao = salesDetailStandardEntityDao;
	}

	/**
	 * 取引先マスタ用Daoを設定する
	 * @param venderDao 取引先マスタ用Dao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * 受払履歴更新用Daoを設定します。
	 * @param inoutRecordDao 受払履歴更新Dao
	 */
	public void setInoutRecordDao(final InoutRecordDao inoutRecordDao) {
		this.inoutRecordDao = inoutRecordDao;
	}

	/**
	 * 受払履歴データ取得用Daoを設定します。
	 * @param salesGetInoutDataDao 受払履歴データ取得Dao
	 */
	public void setSalesGetInoutDataDao(
			final SalesGetInoutDataDao salesGetInoutDataDao) {
		this.salesGetInoutDataDao = salesGetInoutDataDao;
	}
}
