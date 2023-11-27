/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.shipping.Shipping;
import com.asahikaseieng.dao.entity.shipping.ShippingDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingListDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingListPagerCondition;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingDetailListForReport;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListForReport;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListForReportDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 出荷指図詳細 ロジック実装クラス
 * @author tosco
 */
public class ShippingListLogicImpl implements ShippingListLogic {

	private ShippingListDao shippingListDao;

	/** 出荷指図テーブル更新用Dao */
	private ShippingDao shippingDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 出荷指図帳票Excel用Dao(ヘッダ) */
	private ShippingListForReportDao shippingListForReportDao;

	/** 出荷指図帳票Excel用Dao(詳細) */
	private ShippingDetailListForReportDao shippingDetailListForReportDao;

	/**
	 * コンストラクタ.出荷指図
	 */
	public ShippingListLogicImpl() {
	}

	/**
	 * 出荷指図一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingList> 検索結果リスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	public List<ShippingList> getSearchList(
			final ShippingListPagerCondition condition) throws NoDataException {
		checkParams(condition);
		List<ShippingList> list = shippingListDao.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		// 指図量のフォーマット
		for (ShippingList bean : list) {
			bean.setStrShippingInstruction(checker.format(bean
					.getUnitDivision(), ShippingConst.VENDER_DIVISION_TS, bean
					.getVenderCd(), bean.getShippingInstruction()));
		}
		return list;
	}

	/**
	 * 出荷指図一覧検索処理(ヘッダ)
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingList> 検索結果リスト
	 * 
	 */
	public List<ShippingListForReport> getHeaderList(
			final ShippingListConditionForReport condition) {
		List<ShippingListForReport> list = shippingListForReportDao
				.getHeaderListForReport(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 出荷指図一覧検索処理(詳細)
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingDetailListForReport> 検索結果リスト
	 * 
	 */
	public List<ShippingDetailListForReport> getDetailList(
			final ShippingListConditionForReport condition) {

		List<ShippingDetailListForReport> list = shippingDetailListForReportDao
				.getDetailListForReport(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 確定取消処理を行う
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	public void cancelFix(final List<ShippingList> searchList,
			final String tantoCd) throws NoDataException, Exception {
		int updateNum;

		if (searchList == null || searchList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}

		try {
			for (ShippingList bean : searchList) {
				if (!bean.isCheckFlg()) {
					// チェック無
					continue;
				}

				Shipping updBean = shippingDao.getEntity(bean.getShippingNo());
				if (updBean == null) {
					throw new NoDataException();
				}

				// 出荷ステータス(1:出荷指図未確定)
				updBean
						.setShippingStatus(ShippingConst.SHIPPING_STATUS_NOT_FIX);
				// 更新時刻
				updBean.setUpdateDate(bean.getUpdateDate());
				// 更新者
				updBean.setUpdatorCd(tantoCd);
				// 更新処理
				updateNum = shippingDao.update(updBean);
				if (updateNum == 0) {
					// 更新対象無しエラー
					throw new NoDataException();
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final ShippingListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * shippingListDaoを設定します。
	 * 
	 * @param shippingListDao shippingListDao
	 */
	public void setShippingListDao(final ShippingListDao shippingListDao) {
		this.shippingListDao = shippingListDao;

	}

	/**
	 * 出荷指図テーブル更新用Daoを設定します。
	 * @param shippingDao 出荷指図テーブル更新用Dao
	 */
	public void setShippingDao(final ShippingDao shippingDao) {
		this.shippingDao = shippingDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * shippingListForReportDaoを設定します。
	 * @param shippingListForReportDao shippingListForReportDao
	 */
	public void setShippingListForReportDao(
			final ShippingListForReportDao shippingListForReportDao) {
		this.shippingListForReportDao = shippingListForReportDao;
	}

	/**
	 * shippingDetailListForReportDaoを設定します。
	 * @param shippingDetailListForReportDao shippingDetailListForReportDao
	 */
	public void setShippingDetailListForReportDao(
			final ShippingDetailListForReportDao shippingDetailListForReportDao) {
		this.shippingDetailListForReportDao = shippingDetailListForReportDao;
	}
}
