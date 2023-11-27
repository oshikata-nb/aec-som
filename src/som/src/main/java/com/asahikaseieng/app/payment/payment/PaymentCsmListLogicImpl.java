/*
 * Created on 2008/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPayment;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentDao;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListForReportDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * PaymentCsmListLogicImplクラス．支払入力（リスト)ロジック実装クラス(カスタマイズ)
 * @author tosco
 */
public class PaymentCsmListLogicImpl implements PaymentCsmListLogic {
	/** 支払トランザクションテーブル検索DAO */
	private AltPaymentDao paymentDao;

	private PaymentListForReportDao paymentListForReportDao;

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
	public PaymentCsmListLogicImpl() {
	}

	/**
	 * 検索処理を行う.支払入力検索処理
	 * @param condition condition
	 * @return List 検索結果
	 * @throws NoDataException 検索結果が存在しない場合発生
	 */
	public List<AltPayment> getSearchList(
			final AltPaymentPagerCondition condition) throws NoDataException {
		// 支払トランザクションテーブルを検索条件で検索
		List<AltPayment> result = paymentDao.getSearchList(condition);

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
	public void statusUpdate(final PaymentCsmListForm frm,
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
						paymentDao.updateApprovalStatus(status, approvedby,
							approvaldate, frm.getSearchList().get(i)
									.getSlipNo());
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
	private boolean isReadOnly(final AltPayment bean) {
		boolean res = false;

		if (isDone(bean.getDepositUpdateDivision())
				|| isDone(bean.getClaimUpdateDivision())
				|| isDone(bean.getPayableUpdateDivision())
				|| isDone(bean.getPaymentUpdateDivision())
				|| isDone(bean.getEraserCompleteDivision())
				|| bean.getTransmissionDate() != null) {
			/* 売り掛け更新、請求更新、買掛更新、支払い更新フラグ、消込完了フラグ、データ転送日時 */
			/* 何れかが立っていた場合は、更新付加->照会モード */
			res = true;
		}
		return res;
	}

	/**
	 * 処理済みかチェックする。
	 * @param dec フラグ
	 * @return [true:処理済][false:未処理]
	 */
	private boolean isDone(final BigDecimal dec) {
		boolean res = false;

		if (dec != null) {
			if (dec.intValue() != 0) {
				/* 0:未処理以外は処理済とする */
				res = true;
			}
		}
		return res;
	}

	/**
	 * 支払一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<PaymentListForReport>
	 */
	public List<PaymentListForReport> getListForReport(
			final PaymentListConditionForReport condition) {
		List<PaymentListForReport> list = paymentListForReportDao
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
				.getListForComboboxes(dataType, null);
		return list;
	}

	/**
	 * 伝票発行フラグ更新
	 * @param slipNoList 更新対象支払番号一覧
	 * @param tantoCd 担当者コード
	 */
	public void updateIssuedDivision(final ArrayList<String> slipNoList,
			final String tantoCd) {
		try {
			for (int i = 0; i < slipNoList.size(); i++) {
				/* 更新処理 */
				paymentDao.updateIssuedDivision(ISSUED, tantoCd, slipNoList
						.get(i));
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	// setter------------------------------------------------------
	/**
	 * 支払トランザクションテーブル検索DAOを設定します。
	 * @param paymentDao 支払トランザクションテーブル検索DAO
	 */
	public void setPaymentDao(final AltPaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	/**
	 * paymentListForReportDaoを設定します。
	 * @param paymentListForReportDao paymentListForReportDao
	 */
	public void setPaymentListForReportDao(
			final PaymentListForReportDao paymentListForReportDao) {
		this.paymentListForReportDao = paymentListForReportDao;
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

}
