/*
 * Created on 2008/08/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCredit;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCreditDao;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCreditListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListForReport;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListForReportDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * DepositListLogicImplクラス．入金入力（リスト)ロジック実装クラス
 * @author tosco
 */
public class DepositListLogicImpl implements DepositListLogic {
	/** 入金トランザクションテーブル検索DAO */
	private DepositCreditDao creditDao;

	private DepositCreditListForReportDao depositCreditListForReportDao;

	private ClassificationListForComboboxesDao classificationListForComboboxesDao;

	/* 入力中 */
	private static final BigDecimal APPROVAL_INPUT = new BigDecimal("1");

	/* 承認依頼 */
	private static final BigDecimal APPROVAL_REQUEST = new BigDecimal("2");

	/* 承認 */
	private static final BigDecimal APPROVAL = new BigDecimal("3");

	/* 伝票発行済 */
	private static final BigDecimal ISSUED = BigDecimal.ONE;

	/**
	 * コンストラクタ
	 */
	public DepositListLogicImpl() {
	}

	/**
	 * 検索処理を行う.入金入力検索処理
	 * @param condition condition
	 * @return List 検索結果
	 * @throws NoDataException 検索結果が存在しない場合発生
	 */
	public List<DepositCredit> getSearchList(
			final DepositCreditListPagerCondition condition)
			throws NoDataException {
		// 入金トランザクションテーブルを検索条件で検索
		List<DepositCredit> result = creditDao.getSearchList(condition);

		// SqlLogRegistry registry = SqlLogRegistryLocator.getInstance();
		// if (registry != null) {
		// // 存在しなければ設定がOFFになっている
		// SqlLog sqlLog = registry.getLast();
		// if (sqlLog != null) {
		// // 存在しなければ直近でSQLが発行されていない
		// String completeSql = sqlLog.getCompleteSql();
		// System.out.println(completeSql);
		// }
		// }

		if (result.isEmpty()) {
			// 検索結果が存在しない場合
			throw new NoDataException();
		}

		return result;
	}

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param statusMode ステータス モード
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	public void statusUpdate(final DepositListForm frm,
			final BigDecimal status, final BigDecimal statusMode,
			final String tantoCd) throws NoDataException {
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* チェックが付いている行のみ対象 */
			if (frm.getSearchList().get(i).getChecked().equals(Boolean.TRUE)) {
				/* 更新可能行のみ対象 */
				if (!isReadOnly(frm.getSearchList().get(i))) {
					Boolean approvalOk = Boolean.FALSE; /* 承認処理許可 */

					/* 承認依頼は入力中のみ受け付ける */
					if (status.equals(APPROVAL_REQUEST)) {
						if (statusMode.equals(new BigDecimal("1"))) {
							if (frm.getSearchList().get(i).getApprovalStatus()
									.equals("1")) {
								approvalOk = Boolean.TRUE;
							}
						}
					}

					/* 承認は承認依頼中のみ受け付ける */
					if (status.equals(APPROVAL)) {
						if (statusMode.equals(new BigDecimal("2"))) {
							if (frm.getSearchList().get(i).getApprovalStatus()
									.equals("2")) {
								approvalOk = Boolean.TRUE;
							}
						}
					}

					/* 否認は承認依頼中のみ受け付ける */
					if (status.equals(APPROVAL_INPUT)) {
						if (statusMode.equals(new BigDecimal("3"))) {
							if (frm.getSearchList().get(i).getApprovalStatus()
									.equals("2")) {
								approvalOk = Boolean.TRUE;
							}
						}
					}

					/* 承認取消は承認中のみ受け付ける */
					if (status.equals(APPROVAL_INPUT)) {
						if (statusMode.equals(new BigDecimal("4"))) {
							if (frm.getSearchList().get(i).getApprovalStatus()
									.equals("3")) {
								approvalOk = Boolean.TRUE;
							}
						}
					}

					if (approvalOk.equals(Boolean.TRUE)) {
						String approvedby = null; /* 承認者 */
						Timestamp approvaldate = null; /* 承認日時 */

						/* 承認時のみ承認者情報を登録する */
						if (status.equals(new BigDecimal("3"))) {
							approvedby = tantoCd;
							approvaldate = AecDateUtils.getCurrentTimestamp();
						}

						/* 承認ステータス更新 */
						creditDao.updateApprovalStatus(status, approvedby,
							approvaldate, frm.getSearchList().get(i)
									.getCreditNo());
					}
				}
			}
		}
	}

	/**
	 * 参照のみかチェックする。
	 * @param bean 明細Bean
	 * @return [true:更新不可][false:更新可能]
	 */
	private boolean isReadOnly(final DepositCredit bean) {
		boolean res = false;

		// 入金承認操作可能チェック
		List<DepositCredit> list = creditDao.checkApproval(bean.getCreditNo());

		if (list != null) {
			if (0 < list.size()) {
				/* 売り掛け更新、請求更新、買掛更新、支払い更新フラグ、消込完了フラグ、データ転送日時 、消込 */
				/* 何れかが立っていた場合は、更新付加->照会モード */
				res = true;
			}
		}

		return res;
	}

	/**
	 * 入金消込一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<DepositCreditListForReport>
	 */
	public List<DepositCreditListForReport> getListForReport(
			final DepositCreditListConditionForReport condition) {
		List<DepositCreditListForReport> list = depositCreditListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 分類リスト取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	public List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType) {
		List<ClassificationListForComboboxes> list = classificationListForComboboxesDao
				.getListForComboboxes(dataType, BigDecimal.ONE);
		return list;
	}

	/**
	 * 伝票発行フラグ更新
	 * @param creditNoList 更新対象入金番号一覧
	 * @param tantoCd 担当者コード
	 */
	public void updateIssuedDivision(final ArrayList<String> creditNoList,
			final String tantoCd) {
		try {
			for (int i = 0; i < creditNoList.size(); i++) {
				/* 更新処理 */
				creditDao.updateIssuedDivision(ISSUED, tantoCd, creditNoList
						.get(i));
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	// setter------------------------------------------------------

	/**
	 * depositCreditListForReportDaoを設定します。
	 * @param depositCreditListForReportDao depositCreditListForReportDao
	 */
	public void setDepositCreditListForReportDao(
			final DepositCreditListForReportDao depositCreditListForReportDao) {
		this.depositCreditListForReportDao = depositCreditListForReportDao;
	}

	/**
	 * classificationListForComboboxesDaoを設定します。
	 * @param classificationListForComboboxesDao
	 *            classificationListForComboboxesDao
	 */
	public void setClassificationListForComboboxesDao(
			final ClassificationListForComboboxesDao classificationListForComboboxesDao) {
		this.classificationListForComboboxesDao = classificationListForComboboxesDao;
	}

	/**
	 * creditDaoを設定します。
	 * @param creditDao creditDao
	 */
	public void setCreditDao(final DepositCreditDao creditDao) {
		this.creditDao = creditDao;
	}
}
