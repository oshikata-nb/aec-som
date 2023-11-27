/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.billissue;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.claimheader.ClaimHeader;
import com.asahikaseieng.dao.entity.claimheader.ClaimHeaderDao;
import com.asahikaseieng.dao.entity.temporaryclaimheader.TemporaryClaimHeader;
import com.asahikaseieng.dao.entity.temporaryclaimheader.TemporaryClaimHeaderDao;
import com.asahikaseieng.dao.nonentity.claim.billissue.BillIssueList;
import com.asahikaseieng.dao.nonentity.claim.billissue.BillIssueListDao;
import com.asahikaseieng.dao.nonentity.claim.billissue.BillIssuePagerCondition;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListForReport;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListForReportDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 
 * BillIssueListLogicImplクラス.請求書発行
 * @author tosco
 */
public class BillIssueListLogicImpl implements BillIssueListLogic {

	private BillIssueListDao billIssueListDao;

	private BillIssueListForReportDao billIssueListForReportDao;

	private ClaimHeaderDao claimHeaderDao;

	private TemporaryClaimHeaderDao temporaryClaimHeaderDao;

	/* 対象区分 通常処理分 */
	private static final BigDecimal NORMAL = new BigDecimal("1");

	/* 対象区分 仮締め処理分 */
	private static final BigDecimal TEMP = new BigDecimal("2");

	/**
	 * コンストラクタ.請求書発行ロジック
	 */
	public BillIssueListLogicImpl() {
	}

	/**
	 * 検索処理を行う.請求書発行
	 * @param condition condition
	 * @return BillIssueList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<BillIssueList> getSearchList(
			final BillIssuePagerCondition condition) throws NoDataException {

		checkParams(condition);

		final List<BillIssueList> bean = billIssueListDao
				.getSearchList(condition);

		if (bean.isEmpty()) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final BillIssuePagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 請求書発行フラグ更新
	 * @param frm 画面データ
	 * @param status 請求書発行フラグ
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	public void updateFlg(final BillIssueListForm frm, final BigDecimal status,
			final String tantoCd) throws NoDataException {
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			if (frm.getSearchList().get(i).isBillIssueFlg()) {
				if (frm.getSearchList().get(i).getKbn().equals(NORMAL)) {
					/* 請求ヘッダー検索 */
					ClaimHeader bean = getClaimHeaderEntity(frm.getSearchList()
							.get(i).getClaimNo());

					bean.setBillDivision(status);
					bean.setUpdatorCd(tantoCd);

					/* 登録処理 */
					updateClaimHeader(bean);
				}

				if (frm.getSearchList().get(i).getKbn().equals(TEMP)) {
					/* 仮締め請求ヘッダー検索 */
					TemporaryClaimHeader bean = getTemporaryClaimHeaderEntity(frm
							.getSearchList().get(i).getClaimNo());

					bean.setBillDivision(status);
					bean.setUpdatorCd(tantoCd);

					/* 登録処理 */
					updateTemporaryClaimHeader(bean);
				}
			}
		}
	}

	/**
	 * 請求ヘッダー検索
	 * @param claimNo 請求番号
	 * @return ClaimHeader
	 * @throws NoDataException NoDataException
	 */
	public ClaimHeader getClaimHeaderEntity(final String claimNo)
			throws NoDataException {
		if (StringUtils.isEmpty(claimNo)) {
			throw new IllegalArgumentException("claimNo is empty");
		}

		ClaimHeader bean = claimHeaderDao.getEntity(claimNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 仮締め請求ヘッダー検索
	 * @param claimNo 請求番号
	 * @return TempraryClaimHeader
	 * @throws NoDataException NoDataException
	 */
	public TemporaryClaimHeader getTemporaryClaimHeaderEntity(
			final String claimNo) throws NoDataException {
		if (StringUtils.isEmpty(claimNo)) {
			throw new IllegalArgumentException("claimNo is empty");
		}

		TemporaryClaimHeader bean = temporaryClaimHeaderDao.getEntity(claimNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 請求ヘッダー更新登録
	 * @param bean 登録データ
	 */
	public void updateClaimHeader(final ClaimHeader bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			claimHeaderDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 仮締め請求ヘッダー更新登録
	 * @param bean 登録データ
	 */
	public void updateTemporaryClaimHeader(final TemporaryClaimHeader bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			temporaryClaimHeaderDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 請求書一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<BillIssueListForReport>
	 */
	public List<BillIssueListForReport> getListForReport(
			final BillIssueListConditionForReport condition) {
		List<BillIssueListForReport> list = billIssueListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * BillIssueListDaoを設定します。
	 * 
	 * @param billIssueListDao billIssueListDao
	 */
	public void setBillIssueListDao(final BillIssueListDao billIssueListDao) {
		this.billIssueListDao = billIssueListDao;
	}

	/**
	 * claimHeaderDaoを設定します。
	 * @param claimHeaderDao claimHeaderDao
	 */
	public void setClaimHeaderDao(final ClaimHeaderDao claimHeaderDao) {
		this.claimHeaderDao = claimHeaderDao;
	}

	/**
	 * temporaryClaimHeaderDaoを設定します。
	 * @param temporaryClaimHeaderDao temporaryClaimHeaderDao
	 */
	public void setTemporaryClaimHeaderDao(
			final TemporaryClaimHeaderDao temporaryClaimHeaderDao) {
		this.temporaryClaimHeaderDao = temporaryClaimHeaderDao;
	}

	/**
	 * billIssueListForReportDaoを設定します。
	 * @param billIssueListForReportDao billIssueListForReportDao
	 */
	public void setBillIssueListForReportDao(
			final BillIssueListForReportDao billIssueListForReportDao) {
		this.billIssueListForReportDao = billIssueListForReportDao;
	}
}
