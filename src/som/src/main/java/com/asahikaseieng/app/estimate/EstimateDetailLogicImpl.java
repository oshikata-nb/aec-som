/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.estimate.Estimate;
import com.asahikaseieng.dao.entity.estimate.EstimateDao;
import com.asahikaseieng.dao.nonentity.estimatebalancelist.EstimateBalanceList;
import com.asahikaseieng.dao.nonentity.estimatebalancelist.EstimateBalanceListDao;
import com.asahikaseieng.dao.nonentity.estimatedelete.EstimateDeleteDao;
import com.asahikaseieng.dao.nonentity.estimatedetaillist.EstimateDetailList;
import com.asahikaseieng.dao.nonentity.estimatedetaillist.EstimateDetailListDao;
import com.asahikaseieng.dao.nonentity.estimateduplicatelist.EstimateDuplicateList;
import com.asahikaseieng.dao.nonentity.estimateduplicatelist.EstimateDuplicateListDao;
import com.asahikaseieng.dao.nonentity.estimatestatusupdate.EstimateStatusUpdateDao;
import com.asahikaseieng.dao.nonentity.estimateupdate.EstimateUpdateDao;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 見積/単価詳細ロジック 実装クラス.
 * @author t0011036
 */
public class EstimateDetailLogicImpl implements EstimateDetailLogic {

	private EstimateDao estimateDao;

	private EstimateDeleteDao estimateDeleteDao;

	private EstimateDetailListDao estimateDetailListDao;

	private EstimateBalanceListDao estimateBalanceListDao;

	private EstimateDuplicateListDao estimateDuplicateListDao;

	private EstimateUpdateDao estimateUpdateDao;

	private EstimateStatusUpdateDao estimateStatusUpdateDao;

	private BalanceDetailDao balanceDetailDao;

	private ItemDetailDao itemDetailDao;

	private GetNumberLogic getNumberLogic;

	/**
	 * コンストラクタ.
	 */
	public EstimateDetailLogicImpl() {
	}

	/**
	 * 見積/単価検索（登録用）
	 * @param estimateNo estimateNo
	 * @param consecutiveNo consecutiveNo
	 * @return Estimate
	 * @throws NoDataException NoDataException
	 */
	public Estimate getEntity(final String estimateNo,
			final BigDecimal consecutiveNo) throws NoDataException {
		if (StringUtils.isEmpty(estimateNo)) {
			throw new IllegalArgumentException("estimateNo is empty");
		}

		Estimate bean = estimateDao.getEntity(estimateNo, consecutiveNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 見積/単価検索（詳細用）
	 * @param estimateNo estimateNo
	 * @return List<EstimateDetailList>
	 */
	public List<EstimateDetailList> getList(final String estimateNo) {
		List<EstimateDetailList> bean = estimateDetailListDao
				.getList(estimateNo);
		return bean;
	}

	/**
	 * 帳合一覧検索
	 * @param balanceCd balanceCd
	 * @return List<EstimateBalanceList>
	 */
	public List<EstimateBalanceList> getBalanceList(final String balanceCd) {
		List<EstimateBalanceList> bean = estimateBalanceListDao
				.getList(balanceCd);
		return bean;
	}

	/**
	 * 帳合検索
	 * @param balanceCd 帳合コード
	 * @return BalanceDetail
	 */
	public BalanceDetail getBalanceEntity(final String balanceCd) {
		BalanceDetail bean = balanceDetailDao.getEntity(balanceCd);
		return bean;
	}

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	public ItemDetail getItemEntity(final String itemCd) {
		ItemDetail bean = itemDetailDao.getEntity(itemCd, null);
		return bean;
	}

	/**
	 * 帳合＆品目重複チェック
	 * @param estimateNo 見積番号
	 * @param balanceCd 帳合コード
	 * @param itemCd 品目コード
	 * @param strEstimateValidDateFrom 見積有効期限(FROM)
	 * @param strEstimateValidDateTo 見積有効期限(TO)
	 * @return List<EstimateDuplicate>
	 */
	public List<EstimateDuplicateList> getDuplicate(final String estimateNo,
			final String balanceCd, final String itemCd,
			final String strEstimateValidDateFrom,
			final String strEstimateValidDateTo) {
		List<EstimateDuplicateList> list = estimateDuplicateListDao
				.getDuplicateList(estimateNo, balanceCd, itemCd,
					strEstimateValidDateFrom, strEstimateValidDateTo);
		return list;
	}

	/**
	 * 見積番号取得
	 * @return EstimateNo
	 * @throws NoDataException NoDataException
	 */
	public String getEstimateNo() throws NoDataException {
		return getNumberLogic.getEstimateNo();
	}

	/**
	 * 見積/単価一括削除
	 * @param estimateNo estimateNo
	 * @param consecutiveNo consecutiveNo
	 * @return 削除件数
	 */
	public int deleteEstimateList(final String estimateNo,
			final BigDecimal consecutiveNo) {
		return estimateDeleteDao.deleteList(estimateNo, consecutiveNo);
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Estimate bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			estimateDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param frm 登録データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws NoDataException NoDataException
	 */
	public void insert(final EstimateDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException,
			NoDataException {
		Estimate bean = new Estimate();

		// try {
		// /* 一括削除処理 */
		// deleteEstimateList(frm.getEstimateNo());
		//
		// for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
		// /* 値を更新用Beanにコピる */
		// IgnoreCaseBeanUtils.copyProperties(bean, frm);
		//
		// /* コピーしきれなかった分は手で */
		// bean.setItemCd(frm.getSearchEstimateDetailList().get(i)
		// .getItemCd());
		// bean.setEstimateInputDate(AecDateUtils
		// .getTimestampYmdFormat(frm.getStrEstimateInputDate()));
		// bean.setStandardUnitPrice(frm.getSearchEstimateDetailList()
		// .get(i).getStandardUnitPrice());
		// bean.setStandardDiscount(frm.getSearchEstimateDetailList().get(
		// i).getStandardDiscount());
		// bean.setSpecialDiscount(frm.getSearchEstimateDetailList()
		// .get(i).getSpecialDiscount());
		// bean.setUnitprice(frm.getSearchEstimateDetailList().get(i)
		// .getUnitprice());
		// bean.setStandardAmount(frm.getSearchEstimateDetailList().get(i)
		// .getStandardAmount());
		// bean.setMatss(frm.getSearchEstimateDetailList().get(i)
		// .getMatss());
		//
		// bean.setConsecutiveNo(new BigDecimal(i + 1));
		// bean.setInputDate(bean.getCurrentTimestamp());
		// bean.setInputorCd(tantoCd);
		// bean.setUpdatorCd(tantoCd);
		//
		// /* 追加処理 */
		// estimateDao.insert(bean);
		// }
		// } catch (SQLRuntimeException e) {
		// if (frm.getNewFlg().equals("true")) {
		// frm.setEstimateStatus(null);
		// }
		//
		// /* 一意制約エラー */
		// throw new DuplicateRuntimeException(0);
		// }
		BigDecimal consecutiveNo = BigDecimal.ZERO;

		try {
			for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
				/* 値を更新用Beanにコピる */
				IgnoreCaseBeanUtils.copyProperties(bean, frm);

				/* コピーしきれなかった分は手で */
				bean.setItemCd(frm.getSearchEstimateDetailList().get(i)
						.getItemCd());
				bean.setEstimateInputDate(AecDateUtils
						.getTimestampYmdFormat(frm.getStrEstimateInputDate()));
				bean.setStandardUnitPrice(frm.getSearchEstimateDetailList()
						.get(i).getStandardUnitPrice());
				bean.setStandardDiscount(frm.getSearchEstimateDetailList().get(
					i).getStandardDiscount());
				bean.setSpecialDiscount(frm.getSearchEstimateDetailList()
						.get(i).getSpecialDiscount());
				bean.setUnitprice(frm.getSearchEstimateDetailList().get(i)
						.getUnitprice());
				bean.setStandardAmount(frm.getSearchEstimateDetailList().get(i)
						.getStandardAmount());
				bean.setMatss(frm.getSearchEstimateDetailList().get(i)
						.getMatss());

				bean.setConsecutiveNo(new BigDecimal(i + 1));
				bean.setInputDate(bean.getCurrentTimestamp());
				bean.setInputorCd(tantoCd);
				bean.setUpdatorCd(tantoCd);
				bean.setUpdateDate(bean.getCurrentTimestamp());

				/* 登録処理 */
				estimateUpdateDao.update(bean);
			}

			consecutiveNo = new BigDecimal(frm.getSearchEstimateDetailList()
					.size());
		} catch (SQLRuntimeException e) {
			if (frm.getNewFlg().equals("true")) {
				frm.setEstimateStatus(null);
			}

			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			if (frm.getNewFlg().equals("true")) {
				frm.setEstimateStatus(null);
			}

			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}

		try {
			/* 一括削除処理 */
			deleteEstimateList(frm.getEstimateNo(), consecutiveNo);
		} catch (SQLRuntimeException e) {
			if (frm.getNewFlg().equals("true")) {
				frm.setEstimateStatus(null);
			}

			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final Estimate bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			estimateDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	public void statusUpdate(final EstimateDetailForm frm,
			final BigDecimal status, final String tantoCd)
			throws NoDataException {
		// for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
		// Estimate bean = getEntity(frm.getEstimateNo(),
		// new BigDecimal(i + 1));
		//
		// bean.setEstimateStatus(status);
		// bean.setUpdatorCd(tantoCd);
		//
		// /* 登録処理 */
		// update(bean);
		// }
		try {
			/* 更新処理 */
			estimateStatusUpdateDao.updateStatus(status, AecDateUtils
					.getCurrentTimestamp(), tantoCd, frm.getEstimateNo());
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * balanceDetailDaoを設定します。
	 * @param balanceDetailDao balanceDetailDao
	 */
	public void setBalanceDetailDao(final BalanceDetailDao balanceDetailDao) {
		this.balanceDetailDao = balanceDetailDao;
	}

	/**
	 * estimateBalanceListDaoを設定します。
	 * @param estimateBalanceListDao estimateBalanceListDao
	 */
	public void setEstimateBalanceListDao(
			final EstimateBalanceListDao estimateBalanceListDao) {
		this.estimateBalanceListDao = estimateBalanceListDao;
	}

	/**
	 * estimateDaoを設定します。
	 * @param estimateDao estimateDao
	 */
	public void setEstimateDao(final EstimateDao estimateDao) {
		this.estimateDao = estimateDao;
	}

	/**
	 * estimateDeleteDaoを設定します。
	 * @param estimateDeleteDao estimateDeleteDao
	 */
	public void setEstimateDeleteDao(final EstimateDeleteDao estimateDeleteDao) {
		this.estimateDeleteDao = estimateDeleteDao;
	}

	/**
	 * estimateDetailListDaoを設定します。
	 * @param estimateDetailListDao estimateDetailListDao
	 */
	public void setEstimateDetailListDao(
			final EstimateDetailListDao estimateDetailListDao) {
		this.estimateDetailListDao = estimateDetailListDao;
	}

	/**
	 * itemDetailDaoを設定します。
	 * @param itemDetailDao itemDetailDao
	 */
	public void setItemDetailDao(final ItemDetailDao itemDetailDao) {
		this.itemDetailDao = itemDetailDao;
	}

	/**
	 * estimateDuplicateListDaoを設定します。
	 * @param estimateDuplicateListDao estimateDuplicateListDao
	 */
	public void setEstimateDuplicateListDao(
			final EstimateDuplicateListDao estimateDuplicateListDao) {
		this.estimateDuplicateListDao = estimateDuplicateListDao;
	}

	/**
	 * getNumberLogicを設定します。
	 * @param getNumberLogic getNumberLogic
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * estimateUpdateDaoを設定します。
	 * @param estimateUpdateDao estimateUpdateDao
	 */
	public void setEstimateUpdateDao(final EstimateUpdateDao estimateUpdateDao) {
		this.estimateUpdateDao = estimateUpdateDao;
	}

	/**
	 * estimateStatusUpdateDaoを設定します。
	 * @param estimateStatusUpdateDao estimateStatusUpdateDao
	 */
	public void setEstimateStatusUpdateDao(
			final EstimateStatusUpdateDao estimateStatusUpdateDao) {
		this.estimateStatusUpdateDao = estimateStatusUpdateDao;
	}
}
