/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.credit.Credit;
import com.asahikaseieng.dao.entity.credit.CreditDao;
import com.asahikaseieng.dao.entity.erasercsm.EraserCsm;
import com.asahikaseieng.dao.entity.erasercsm.EraserCsmDao;
import com.asahikaseieng.dao.entity.eraserheaderdetail.EraserHeaderDetail;
import com.asahikaseieng.dao.entity.eraserheaderdetail.EraserHeaderDetailDao;
import com.asahikaseieng.dao.entity.offsetgroupdata.OffsetGroupData;
import com.asahikaseieng.dao.entity.offsetgroupdata.OffsetGroupDataDao;
import com.asahikaseieng.dao.entity.sales.Sales;
import com.asahikaseieng.dao.entity.sales.SalesDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsm;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmDetailDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetailDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserSales;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserSalesCsmDao;
import com.asahikaseieng.dao.nonentity.procedurecall.FunGetSlipNoCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 消込入力詳細 ロジック実装クラス(カスタマイズ)
 * @author tosco
 */
public class EraserCsmDetailLogicImpl implements EraserCsmDetailLogic {

	/** データ種別(売上) */
	private static final BigDecimal DATA_TYPE_SALES = new BigDecimal(1);

	/** データ種別(入金) */
	private static final BigDecimal DATA_TYPE_CREDIT = new BigDecimal(2);

	/** データ種別(支払) */
	private static final BigDecimal DATA_TYPE_PAYMENT = new BigDecimal(4);

	/** データ種別(相殺) */
	private static final BigDecimal DATA_TYPE_OFFSET = new BigDecimal(5);

	/** 消込完了フラグ(未完了) */
	private static final BigDecimal DIVISION_NOT_COMPLETE = new BigDecimal(0);

	/** 消込完了フラグ(完了) */
	private static final BigDecimal DIVISION_COMPLETE = new BigDecimal(1);

	/** 消込トラン(Csm)用 消込完了フラグ(未完了) */
	private static final BigDecimal CSM_DIVISION_NOT_COMPLETE = new BigDecimal(
			0);

	/** 消込トラン(Csm)用 消込完了フラグ(処理中) */
	private static final BigDecimal CSM_DIVISION_PROCESSING = new BigDecimal(1);

	/** 消込トラン(Csm)用 消込完了フラグ(完了) */
	private static final BigDecimal CSM_DIVISION_COMPLETE = new BigDecimal(2);

	/** 承認ステータス(入力中) */
	private static final BigDecimal APPROVAL_STATUS_INPUT = new BigDecimal(1);

	/** 得意先区分 */
	private static final String TS = "TS";

	/** 承認ステータス(承認依頼中) */
	// private static final BigDecimal APPROVAL_STATUS_REQUEST = new
	// BigDecimal(2);
	/** 承認ステータス(承認済) */
	// private static final BigDecimal APPROVAL_STATUS_APPROVED = new
	// BigDecimal(3);
	private ClaimEraserDetailDao eraserDetailDao;

	private ClaimEraserCsmDetailDao eraserCsmDetailDao;

	private ClaimEraserCsmDao eraserCsmDao;

	private ClaimEraserSalesCsmDao eraserSalesCsmDao;

	private EraserCsmDao eraserCsmEntityDao;

	private EraserHeaderDetailDao eraserHeaderDetailDao;

	private ProcedureCallDao procedureCallDao;

	private SalesDao salesDao;

	private CreditDao creditDao;

	private OffsetGroupDataDao offsetGroupDataDao;

	/**
	 * コンストラクタ.消込入力詳細ロジック
	 */
	public EraserCsmDetailLogicImpl() {
	}

	/**
	 * 消込(カスタマイズ)・入金データ検索処理を行う.消込入力詳細
	 * 
	 * @param organizationCd 部署コード
	 * @param organizationName 部署名称
	 * @param venderCd 請求先コード
	 * @param tantoCd 担当者コード
	 * @param eraserCompleteDateFrom 消込完了日付FROM
	 * @param eraserCompleteDateTo 消込完了日付TO
	 * @param kbn 区分
	 * @param approvalStatus 承認ステータス
	 * @param claimNo 請求番号
	 * @return List<EraserDetail> 消込入力詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<ClaimEraserDetail> getDetailData(final String organizationCd,
			final String organizationName, final String venderCd,
			final String tantoCd, final Date eraserCompleteDateFrom,
			final Date eraserCompleteDateTo, final String kbn,
			final BigDecimal approvalStatus, final String claimNo)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(venderCd);

		/* 消込詳細(上段･中段)検索 */
		List<ClaimEraserDetail> beanList = eraserCsmDetailDao
				.getDetailCreditData(organizationCd, organizationName,
					venderCd, tantoCd, eraserCompleteDateFrom,
					eraserCompleteDateTo, kbn, approvalStatus, claimNo);

		if (beanList.isEmpty()) {
			throw new NoDataException();
		}

		return beanList;
	}

	/**
	 * 消込トラン(カスタマイズ)明細データ検索処理を行う.消込入力詳細
	 * 
	 * @param organizationCd 部署コード
	 * @param organizationName 部署名称
	 * @param venderCd 請求先コード
	 * @param tantoCd 担当者コード
	 * @param eraserCompleteDateFrom 消込完了日付FROM
	 * @param eraserCompleteDateTo 消込完了日付TO
	 * @param kbn 区分
	 * @param approvalStatus 承認ステータス
	 * @param claimNo 請求番号
	 * @return List<EraserCsm> 消込入力詳細(下段) 消込トラン(カスタマイズ)明細データ
	 * @throws NoDataException NoDataException
	 */
	public List<ClaimEraserCsm> getEraserCsmData(final String organizationCd,
			final String organizationName, final String venderCd,
			final String tantoCd, final Date eraserCompleteDateFrom,
			final Date eraserCompleteDateTo, final String kbn,
			final BigDecimal approvalStatus, final String claimNo)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(venderCd);

		/* 消込詳細(下段)検索 */
		List<ClaimEraserCsm> beanList = eraserCsmDao.getEraserCsmData(
			organizationCd, organizationName, venderCd, tantoCd,
			eraserCompleteDateFrom, eraserCompleteDateTo, kbn, approvalStatus,
			claimNo);

		if (beanList.isEmpty()) {
			throw new NoDataException();
		}

		return beanList;
	}

	/**
	 * 更新処理を行う.
	 * @param eraserBean 消込トラン(Csm)データ
	 * @param detailList 消込トラン(Csm)・入金データリスト(上段・中段)
	 * @param eraserCsmList 消込トラン(Csm)データリスト(下段)
	 * @throws NoDataException データ無し例外
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws SQLException 入金データ無し例外
	 */
	public void update(final ClaimEraserCsm eraserBean,
			final List<ClaimEraserDetail> detailList,
			final List<ClaimEraserCsm> eraserCsmList) throws NoDataException,
			IllegalAccessException, InvocationTargetException, SQLException {

		BigDecimal lastEraserAmount = new BigDecimal(0); // 前回消込金額計

		if (eraserBean == null || detailList == null || eraserCsmList == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 消込前の状態に戻す
			// 消込トラン(Csm)データループ
			for (int i = 0; i < eraserCsmList.size(); i++) {

				ClaimEraserCsm eraserCsmBean = eraserCsmList.get(i);

				if (eraserCsmBean.getLastEraserAmount() == null
						|| eraserCsmBean.getLastEraserAmount().compareTo(
							new BigDecimal(0)) == 0) {
					continue;
				}

				// 前回消込金額合計
				lastEraserAmount = lastEraserAmount.add(eraserCsmBean
						.getLastEraserAmount());

				// 消込トラン(Csm)更新処理
				updateEraserCsmNotComp(eraserBean, eraserCsmBean);

				if (DATA_TYPE_SALES.equals(eraserCsmBean.getDataType())) {
					// 売上トランザクション更新処理
					updateSalesNotComplete(eraserBean, eraserCsmBean);
				} else if (DATA_TYPE_CREDIT.equals(eraserCsmBean.getDataType())) {
					// 入金トランザクション更新処理
					updateCreditNotComplete(eraserBean, eraserCsmBean);
				} else if (DATA_TYPE_PAYMENT
						.equals(eraserCsmBean.getDataType())) {
					// 支払トランザクション更新処理
					updatePaymentNotComplete(eraserBean, eraserCsmBean);
				} else if (DATA_TYPE_OFFSET.equals(eraserCsmBean.getDataType())) {
					// グループ間相殺トランザクション更新処理
					updateOffsetGroupNotComplete(eraserBean, eraserCsmBean);
				}

			}

			List<EraserCsmBean> beanList = new ArrayList<EraserCsmBean>();

			// 今回消込金額合計
			BigDecimal eraserAmount = detailList.get(0).getEraserAmount();
			BigDecimal balanceAmount;
			// 今回と前回の消込金額を比較
			if (eraserAmount.compareTo(lastEraserAmount) > 0) {
				// 今回＞前回の場合、入金データから差分を消込
				balanceAmount = eraserAmount.subtract(lastEraserAmount);
				updateCreditSubtract(eraserBean, detailList, beanList,
					balanceAmount);
			} else if (eraserAmount.compareTo(lastEraserAmount) < 0) {
				// 今回＜前回の場合、入金データへ差分を戻す
				balanceAmount = lastEraserAmount.subtract(eraserAmount);
				updateCreditAdd(eraserBean, detailList, beanList, balanceAmount);
			}

			// 消込トラン(Csm)データループ
			for (int i = 0; i < eraserCsmList.size(); i++) {

				ClaimEraserCsm eraserCsmBean = eraserCsmList.get(i);

				// if (!eraserCsmBean.isCheckFlg()
				// || (eraserCsmBean.isCheckFlg() && eraserCsmBean
				// .getCheckKbn().equals("2"))) {
				// // チェック無 または チェック有かつdisabledチェックボックス
				// continue;
				// }

				// 消込トラン(Csm)更新処理
				updateEraserCsm(eraserBean, eraserCsmBean);

				if (DATA_TYPE_SALES.equals(eraserCsmBean.getDataType())) {
					// 売上トランザクション更新処理
					updateSales(eraserBean, eraserCsmBean);
				} else if (DATA_TYPE_CREDIT.equals(eraserCsmBean.getDataType())) {
					// 入金トランザクション更新処理
					updateCredit(eraserBean, eraserCsmBean);
				} else if (DATA_TYPE_PAYMENT
						.equals(eraserCsmBean.getDataType())) {
					// 支払トランザクション更新処理
					updatePayment(eraserBean, eraserCsmBean);
				} else if (DATA_TYPE_OFFSET.equals(eraserCsmBean.getDataType())) {
					// グループ間相殺トランザクション更新処理
					updateOffsetGroup(eraserBean, eraserCsmBean);
				}

				for (int j = 0; j < beanList.size(); j++) {
					/* 消込ログ追加登録 */
					insertEraserHeaderDetail(insertEraserHeaderDetail(
						eraserBean, beanList.get(j), eraserCsmBean.getSlipNo()));
				}
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * パラメータチェック.
	 * @param cd 検索条件
	 * @throws IllegalArgumentException
	 */
	private void checkParams(final String cd) throws IllegalArgumentException {

		if (StringUtils.isEmpty(cd)) {
			throw new IllegalArgumentException(
					"EraserCsmDetailLogic IllegalArgumentException : Paramater is empty checkParams(cd).");
		}
	}

	/**
	 * 消込トランザクション(Csm)更新処理
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updateEraserCsm(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {

		ClaimEraserCsm newBean = new ClaimEraserCsm();
		newBean.setDataType(eraserCsmBean.getDataType()); // ﾃﾞｰﾀ種別
		newBean.setSlipNo(eraserCsmBean.getSlipNo()); // 伝票番号
		newBean.setInvoiceCd(eraserCsmBean.getInvoiceCd()); // 請求先コード

		// 請求金額＝消込金額
		if (eraserCsmBean.getStrEraserObjectAmount().equals(
			eraserCsmBean.getStrEraserAmount())) {
			newBean.setEraserAmount(eraserCsmBean.getEraserObjectAmount()); // 消込額
			newBean.setEraserBalanceAmount(new BigDecimal(0)); // 消込残
			newBean.setEraserCompleteDivision(CSM_DIVISION_COMPLETE); // 消込完了フラグ(処理済)
			newBean.setEraserCompleteDate(eraserBean.getEraserCompleteDate()); // 消込完了日
			newBean.setApprovalStatus(APPROVAL_STATUS_INPUT); // 承認ステータス(入力中)
		} else {
			BigDecimal eraserObjectAmount = AecNumberUtils
					.convertBigDecimal(eraserCsmBean.getStrEraserObjectAmount());
			BigDecimal eraserAmount = AecNumberUtils
					.convertBigDecimal(eraserCsmBean.getStrEraserAmount());

			/* null ---> zero */
			eraserObjectAmount = AecNumberUtils
					.convertNullToZero(eraserObjectAmount); /* 消込残 */
			eraserAmount = AecNumberUtils.convertNullToZero(eraserAmount); /* 消込額 */

			newBean.setEraserAmount(eraserAmount); // 消込額
			newBean.setEraserBalanceAmount(eraserObjectAmount
					.subtract(eraserAmount)); // 消込残
			newBean.setEraserCompleteDivision(CSM_DIVISION_PROCESSING); // 消込完了フラグ(処理中)
			newBean.setEraserCompleteDate(null); // 消込完了日
			newBean.setApprovalStatus(APPROVAL_STATUS_INPUT); // 承認ステータス(入力中)
		}

		newBean.setEraserDate(eraserBean.getEraserDate()); // 消込日付
		newBean.setEraserUpdateDate(eraserBean.getUpdateDate()); // 消込更新日時
		newBean.setEraserorCd(eraserBean.getUpdatorCd()); // 消込担当者ＩＤ
		newBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		newBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		int insertNum = eraserCsmDao.update(newBean);
		if (insertNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 消込トランザクション(Csm)更新処理 消込前の状態に戻す
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updateEraserCsmNotComp(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {

		ClaimEraserCsm newBean = new ClaimEraserCsm();
		newBean.setDataType(eraserCsmBean.getDataType()); // ﾃﾞｰﾀ種別
		newBean.setSlipNo(eraserCsmBean.getSlipNo()); // 伝票番号
		newBean.setInvoiceCd(eraserCsmBean.getInvoiceCd()); // 請求先コード
		newBean.setEraserAmount(new BigDecimal(0)); // 消込額
		// 消込残＝消込対象額
		newBean.setEraserBalanceAmount(eraserCsmBean.getEraserObjectAmount()); // 消込残
		newBean.setEraserCompleteDivision(CSM_DIVISION_NOT_COMPLETE); // 消込完了フラグ(未処理)
		newBean.setEraserCompleteDate(null); // 消込完了日
		newBean.setApprovalStatus(APPROVAL_STATUS_INPUT); // 承認ステータス(入力中)
		newBean.setEraserDate(eraserBean.getEraserDate()); // 消込日付
		newBean.setEraserUpdateDate(null); // 消込更新日時
		newBean.setEraserorCd(null); // 消込担当者ＩＤ
		newBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		newBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		int insertNum = eraserCsmDao.update(newBean);
		if (insertNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 入金トランザクション(入金)更新処理
	 * 
	 * @param newCreditBean 入金トランBean
	 * @throws NoDataException
	 */
	private void updateCredit(final ClaimEraserDetail newCreditBean)
			throws NoDataException {
		// 入金トランザクション(入金)更新処理
		int updateNum = eraserDetailDao.updateCredit(newCreditBean);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 入金トランザクション(相殺)更新処理
	 * 
	 * @param newCreditBean 入金トランBean
	 * @throws NoDataException
	 */
	private void updateOffsetGroupData(final ClaimEraserDetail newCreditBean)
			throws NoDataException {
		// 入金トランザクション(相殺)更新処理
		int updateNum = eraserDetailDao.updateOffsetGroupData(newCreditBean);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 売上トランザクション更新処理
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updateSales(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {
		// 売上トランザクション更新データ作成
		ClaimEraserSales sales = new ClaimEraserSales();
		sales.setSlipNo(eraserCsmBean.getSlipNo()); // 伝票番号
		if (eraserCsmBean.getStrEraserObjectAmount().equals(
			eraserCsmBean.getStrEraserAmount())) {
			// 請求金額＝消込金額の場合
			sales.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了フラグ(処理済)
			sales.setEraserCompleteDate(eraserBean.getEraserCompleteDate()); // 消込完了日
		} else {
			sales.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ(未処理)
			sales.setEraserCompleteDate(null); // 消込完了日
		}
		sales.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		sales.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		// 売上トランザクション更新処理
		int updateNum = eraserSalesCsmDao.update(sales);
		if (updateNum == 0) {
			throw new NoDataException();
		}
	}

	/**
	 * 売上トランザクション更新処理 (消込完了→未完了)
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updateSalesNotComplete(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {
		// 売上トランザクション更新データ作成
		ClaimEraserSales sales = new ClaimEraserSales();
		sales.setSlipNo(eraserCsmBean.getSlipNo()); // 伝票番号
		sales.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ
		sales.setEraserCompleteDate(null); // 消込完了日
		sales.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		sales.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		// 売上トランザクション更新処理
		int updateNum = eraserSalesCsmDao.update(sales);
		if (updateNum == 0) {
			throw new NoDataException();
		}
	}

	/**
	 * グループ間相殺トランザクション更新処理
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updateOffsetGroup(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {
		// グループ間相殺トランザクション更新データ作成
		ClaimEraserDetail offsetBean = new ClaimEraserDetail();
		offsetBean.setOffsetNo(eraserCsmBean.getSlipNo()); // 相殺番号
		offsetBean.setVenderCd(eraserCsmBean.getInvoiceCd()); // 請求先コード
		if (eraserCsmBean.getStrEraserObjectAmount().equals(
			eraserCsmBean.getStrEraserAmount())) {
			// 請求金額＝消込金額の場合
			offsetBean.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了フラグ(処理済)
			offsetBean
					.setEraserCompleteDate(eraserBean.getEraserCompleteDate()); // 消込完了日
		} else {
			offsetBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ(未処理)
			offsetBean.setEraserCompleteDate(null); // 消込完了日
		}
		offsetBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		offsetBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		// グループ間相殺トランザクション更新処理
		int updateNum = eraserCsmDetailDao.updateOffsetGroup(offsetBean);
		if (updateNum == 0) {
			throw new NoDataException();
		}
	}

	/**
	 * グループ間相殺トランザクション更新処理 (消込完了→未完了)
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updateOffsetGroupNotComplete(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {
		// グループ間相殺トランザクション更新データ作成
		ClaimEraserDetail offsetBean = new ClaimEraserDetail();
		offsetBean.setOffsetNo(eraserCsmBean.getSlipNo()); // 相殺番号
		offsetBean.setVenderCd(eraserCsmBean.getInvoiceCd()); // 請求先コード
		offsetBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ
		offsetBean.setEraserCompleteDate(null); // 消込完了日
		offsetBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		offsetBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		// グループ間相殺トランザクション更新処理
		int updateNum = eraserCsmDetailDao.updateOffsetGroup(offsetBean);
		if (updateNum == 0) {
			throw new NoDataException();
		}
	}

	/**
	 * 支払トランザクション更新処理
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updatePayment(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {
		// 支払トランザクション更新データ作成
		ClaimEraserDetail payBean = new ClaimEraserDetail();
		payBean.setPaySlipNo(eraserCsmBean.getSlipNo()); // 伝票番号
		if (eraserCsmBean.getStrEraserObjectAmount().equals(
			eraserCsmBean.getStrEraserAmount())) {
			// 請求金額＝消込金額の場合
			payBean.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了フラグ(処理済)
			payBean.setEraserCompleteDate(eraserBean.getEraserCompleteDate()); // 消込完了日
		} else {
			payBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ(未処理)
			payBean.setEraserCompleteDate(null); // 消込完了日
		}
		payBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		payBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		// 支払トランザクション更新処理
		int updateNum = eraserCsmDetailDao.updatePayment(payBean);
		if (updateNum == 0) {
			throw new NoDataException();
		}
	}

	/**
	 * 支払トランザクション更新処理 (消込完了→未完了)
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updatePaymentNotComplete(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {
		// 支払トランザクション更新データ作成
		ClaimEraserDetail payBean = new ClaimEraserDetail();
		payBean.setPaySlipNo(eraserCsmBean.getSlipNo()); // 伝票番号
		payBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ
		payBean.setEraserCompleteDate(null); // 消込完了日
		payBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		payBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		// 支払トランザクション更新処理
		int updateNum = eraserCsmDetailDao.updatePayment(payBean);
		if (updateNum == 0) {
			throw new NoDataException();
		}
	}

	/**
	 * 入金トランザクション更新処理(相殺データ)
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updateCredit(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {
		// 入金トランザクション更新データ作成
		ClaimEraserDetail creditBean = new ClaimEraserDetail();
		creditBean.setCreditNo(eraserCsmBean.getSlipNo()); // 伝票番号(入金番号)
		if (eraserCsmBean.getStrEraserObjectAmount().equals(
			eraserCsmBean.getStrEraserAmount())) {
			// 請求金額＝消込金額の場合
			creditBean.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了フラグ(処理済)
			creditBean
					.setEraserCompleteDate(eraserBean.getEraserCompleteDate()); // 消込完了日
		} else {
			creditBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ(未処理)
			creditBean.setEraserCompleteDate(null); // 消込完了日
		}
		creditBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		creditBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		// 入金トランザクション更新処理
		int updateNum = eraserCsmDetailDao.updateCreditOffset(creditBean);
		if (updateNum == 0) {
			throw new NoDataException();
		}
	}

	/**
	 * 入金トランザクション更新処理(相殺データ) (消込完了→未完了)
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param eraserCsmBean (下段明細)消込トラン(Csm)Bean
	 * @throws NoDataException
	 */
	private void updateCreditNotComplete(final ClaimEraserCsm eraserBean,
			final ClaimEraserCsm eraserCsmBean) throws NoDataException {
		// 入金トランザクション更新データ作成
		ClaimEraserDetail creditBean = new ClaimEraserDetail();
		creditBean.setCreditNo(eraserCsmBean.getSlipNo()); // 伝票番号(入金番号)
		creditBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ
		creditBean.setEraserCompleteDate(null); // 消込完了日
		creditBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
		creditBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

		// 入金トランザクション更新処理
		int updateNum = eraserCsmDetailDao.updateCreditOffset(creditBean);
		if (updateNum == 0) {
			throw new NoDataException();
		}
	}

	/**
	 * 入金トランザクション更新処理(戻し) (消込額・入金消込残→消込前の額、消込完了→未完了)
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param detailList 消込トラン(Csm)・入金データリスト(上段・中段)
	 * @param beanList 消込用 bean
	 * @param lastEraserAmount 前回消込金額(差分)
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws NoDataException
	 */
	private void updateCreditAdd(final ClaimEraserCsm eraserBean,
			final List<ClaimEraserDetail> detailList,
			final List<EraserCsmBean> beanList,
			final BigDecimal lastEraserAmount) throws NoDataException,
			IllegalAccessException, InvocationTargetException, SQLException {

		BigDecimal balanceAmount = lastEraserAmount;

		// 消込途中、消込済の入金データ検索(前回消込金額(差分)を戻すためのデータ)
		List<ClaimEraserDetail> creditList = eraserCsmDetailDao.getCreditData(
			eraserBean.getOrganizationCd(), eraserBean.getInvoiceCd(),
			balanceAmount);
		if (!creditList.isEmpty()) {
			// 入金データへ前回消込金額の合計値を戻す
			// 入金データループ
			for (int j = 0; j < creditList.size(); j++) {
				ClaimEraserDetail creditBean = creditList.get(j);

				if (creditBean.getCrEraserAmount() == null
						|| creditBean.getCrEraserAmount().compareTo(
							new BigDecimal(0)) == 0) {
					// 消込額＝０の場合は次レコード
					continue;
				}

				if (creditBean.getDataType().equals(DATA_TYPE_CREDIT)) {
					// 入金トランザクション(入金)更新データ作成
					ClaimEraserDetail newCreditBean = new ClaimEraserDetail();
					newCreditBean.setCreditNo(creditBean.getCreditNo()); // 入金番号
					newCreditBean.setRowNo(creditBean.getRowNo()); // 行番号
					newCreditBean
							.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込未完了
					newCreditBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
					newCreditBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

					BigDecimal crEraserAmount = new BigDecimal(0); // 消込額
					BigDecimal creditEraserAmount = new BigDecimal(0); // 入金消込残

					EraserCsmBean beanEraser = new EraserCsmBean();
					beanEraser.setBeforeEraserAmount(creditBean
							.getCrEraserAmount());

					// 消込額>=前回消込金額合計
					if (creditBean.getCrEraserAmount().compareTo(balanceAmount) >= 0) {
						// 消込額＝入金トラン.消込額－前回消込金額合計
						crEraserAmount = creditBean.getCrEraserAmount()
								.subtract(balanceAmount);
						// 入金消込残＝入金トラン.入金消込残＋前回消込金額合計
						creditEraserAmount = creditBean.getCreditEraserAmount()
								.add(balanceAmount);

						// 計算結果を設定
						newCreditBean.setCrEraserAmount(crEraserAmount);
						newCreditBean.setCreditEraserAmount(creditEraserAmount);

						// 入金トランザクション(入金)更新処理
						updateCredit(newCreditBean);

						beanEraser.setEraserAmount(crEraserAmount);
						beanEraser.setDataType(creditBean.getDataType());
						beanEraser.setCreditNo(creditBean.getCreditNo());
						beanEraser.setRowNo(creditBean.getRowNo());
						beanEraser.setVenderDivision(TS);
						beanEraser.setVenderCd(creditBean.getVenderCd());
						beanList.add(beanEraser);

						balanceAmount = BigDecimal.ZERO;

						break;
					} else {
						// 戻す必要のある前回消込金額＝戻す前回消込金額－消込残
						balanceAmount = balanceAmount.subtract(creditBean
								.getCrEraserAmount());

						// 消込額＝０
						crEraserAmount = new BigDecimal(0);
						// 入金消込残＝消込額＋現入金消込残
						creditEraserAmount = creditBean.getCrEraserAmount()
								.add(creditBean.getCreditEraserAmount());

						// 計算結果を設定
						newCreditBean.setCrEraserAmount(crEraserAmount);
						newCreditBean.setCreditEraserAmount(creditEraserAmount);

						// 入金トランザクション(入金)更新処理
						updateCredit(newCreditBean);

						beanEraser.setEraserAmount(crEraserAmount);
						beanEraser.setDataType(creditBean.getDataType());
						beanEraser.setCreditNo(creditBean.getCreditNo());
						beanEraser.setRowNo(creditBean.getRowNo());
						beanEraser.setVenderDivision(TS);
						beanEraser.setVenderCd(creditBean.getVenderCd());
						beanList.add(beanEraser);
					}
				}
			}
		}

		/* 全部消込されていなければ相殺からも消し込む */
		if (!balanceAmount.equals(BigDecimal.ZERO)) {
			// 消込途中、消込済の相殺データ検索(前回消込金額(差分)を戻すためのデータ)
			List<ClaimEraserDetail> offsetList = eraserCsmDetailDao
					.getOffsetGroupData(eraserBean.getOrganizationCd(), TS,
						eraserBean.getInvoiceCd(), balanceAmount);
			if (!offsetList.isEmpty()) {
				BigDecimal saveTotalEraserAmount = BigDecimal.ZERO; /* 登録値消込積算 */

				for (int j = 0; j < offsetList.size(); j++) {
					saveTotalEraserAmount = saveTotalEraserAmount
							.add(offsetList.get(j).getCrEraserAmount());
				}

				/* 消込額より前回消込額の方が多かった場合は消込出来ない */
				if (saveTotalEraserAmount.compareTo(balanceAmount) < 0) {
					throw new SQLException();
				}

				// 相殺データへ前回消込金額の合計値を戻す
				// 相殺データループ
				for (int j = 0; j < offsetList.size(); j++) {
					ClaimEraserDetail creditBean = offsetList.get(j);

					if (creditBean.getCrEraserAmount() == null
							|| creditBean.getCrEraserAmount().compareTo(
								new BigDecimal(0)) == 0) {
						// 消込額＝０の場合は次レコード
						continue;
					}

					if (creditBean.getDataType().equals(DATA_TYPE_OFFSET)) {
						// 入金トランザクション(相殺)更新データ作成
						ClaimEraserDetail newCreditBean = new ClaimEraserDetail();
						newCreditBean.setCreditNo(creditBean.getCreditNo()); // 相殺番号
						newCreditBean.setVenderDivision(TS); // 取引先区分
						newCreditBean.setVenderCd(creditBean.getVenderCd()); // 請求先コード
						newCreditBean
								.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込未完了
						newCreditBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
						newCreditBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

						BigDecimal crEraserAmount = new BigDecimal(0); // 消込額
						BigDecimal creditEraserAmount = new BigDecimal(0); // 入金消込残

						EraserCsmBean beanEraser = new EraserCsmBean();
						beanEraser.setBeforeEraserAmount(creditBean
								.getCrEraserAmount());

						// 消込額>=前回消込金額合計
						if (creditBean.getCrEraserAmount().compareTo(
							balanceAmount) >= 0) {
							// 消込額＝入金トラン.消込額－前回消込金額合計
							crEraserAmount = creditBean.getCrEraserAmount()
									.subtract(balanceAmount);
							// 入金消込残＝入金トラン.入金消込残＋前回消込金額合計
							creditEraserAmount = creditBean
									.getCreditEraserAmount().add(balanceAmount);

							// 計算結果を設定
							newCreditBean.setCrEraserAmount(crEraserAmount);
							newCreditBean
									.setCreditEraserAmount(creditEraserAmount);

							// 入金トランザクション(相殺)更新処理
							updateOffsetGroupData(newCreditBean);

							beanEraser.setEraserAmount(crEraserAmount);
							beanEraser.setDataType(creditBean.getDataType());
							beanEraser.setCreditNo(creditBean.getCreditNo());
							beanEraser.setRowNo(creditBean.getRowNo());
							beanEraser.setVenderDivision(TS);
							beanEraser.setVenderCd(creditBean.getVenderCd());
							beanList.add(beanEraser);

							break;
						} else {
							// 戻す必要のある前回消込金額＝戻す前回消込金額－消込残
							balanceAmount = balanceAmount.subtract(creditBean
									.getCrEraserAmount());

							// 消込額＝０
							crEraserAmount = new BigDecimal(0);
							// 入金消込残＝消込額＋現入金消込残
							creditEraserAmount = creditBean.getCrEraserAmount()
									.add(creditBean.getCreditEraserAmount());

							// 計算結果を設定
							newCreditBean.setCrEraserAmount(crEraserAmount);
							newCreditBean
									.setCreditEraserAmount(creditEraserAmount);

							// 入金トランザクション(相殺)更新処理
							updateOffsetGroupData(newCreditBean);

							beanEraser.setEraserAmount(crEraserAmount);
							beanEraser.setDataType(creditBean.getDataType());
							beanEraser.setCreditNo(creditBean.getCreditNo());
							beanEraser.setRowNo(creditBean.getRowNo());
							beanEraser.setVenderDivision(TS);
							beanEraser.setVenderCd(creditBean.getVenderCd());
							beanList.add(beanEraser);
						}
					}
				}
			}

			if (creditList.isEmpty() && offsetList.isEmpty()) {
				throw new SQLException();
			}
		}
	}

	/**
	 * 入金トランザクション更新処理(消込) (消込額＋今回消込金額(差分)、入金消込残－今回消込金額(差分))
	 * 
	 * @param eraserBean 消込トラン(Csm)Bean
	 * @param detailList 消込トラン(Csm)・入金データリスト(上段・中段)
	 * @param beanList 消込用 bean
	 * @param lastEraserAmount 今回消込金額(差分)
	 * @throws NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private void updateCreditSubtract(final ClaimEraserCsm eraserBean,
			final List<ClaimEraserDetail> detailList,
			final List<EraserCsmBean> beanList,
			final BigDecimal lastEraserAmount) throws NoDataException,
			IllegalAccessException, InvocationTargetException {

		BigDecimal balanceAmount = lastEraserAmount;

		// 消込完了済判定
		BigDecimal sumEraserAmount = new BigDecimal(0); // 消込額合計
		ClaimEraserDetail bean = detailList.get(0);
		BigDecimal defaultEraserAmount = bean.getDefaultEraserAmount(); // 消込額(DB取得初期値)
		sumEraserAmount = sumEraserAmount.add(defaultEraserAmount);

		// 入金データループ
		for (int j = 0; j < detailList.size(); j++) {
			ClaimEraserDetail creditBean = detailList.get(j);

			if (creditBean.getCreditEraserAmount() == null
					|| creditBean.getCreditEraserAmount().compareTo(
						new BigDecimal(0)) == 0) {
				// 入金消込残＝０の場合は次レコード
				continue;
			}

			if (creditBean.getDataType().equals(DATA_TYPE_CREDIT)) {
				// 入金トランザクション(入金)更新データ作成
				ClaimEraserDetail newCreditBean = new ClaimEraserDetail();
				newCreditBean.setCreditNo(creditBean.getCreditNo()); // 入金番号
				newCreditBean.setRowNo(creditBean.getRowNo()); // 行番号
				newCreditBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了ﾌﾗｸﾞ
				newCreditBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
				newCreditBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

				BigDecimal crEraserAmount = new BigDecimal(0); // 消込額
				BigDecimal balance = new BigDecimal(0); // 入金消込残

				EraserCsmBean beanEraser = new EraserCsmBean();
				beanEraser
						.setBeforeEraserAmount(creditBean.getCrEraserAmount());

				// 入金消込残>=消し込みの必要な額(画面請求金額)
				if (creditBean.getCreditEraserAmount().compareTo(balanceAmount) >= 0) {
					// 消込額＝現消込額＋消し込みの必要な額
					crEraserAmount = creditBean.getCrEraserAmount().add(
						balanceAmount);
					newCreditBean.setCrEraserAmount(crEraserAmount);
					creditBean.setCrEraserAmount(crEraserAmount);
					// 入金消込残＝現入金消込残－消し込みの必要な額
					balance = creditBean.getCreditEraserAmount().subtract(
						balanceAmount);
					creditBean.setCreditEraserAmount(balance);
					newCreditBean.setCreditEraserAmount(balance);

					if (balance.compareTo(new BigDecimal(0)) == 0) {
						newCreditBean
								.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了ﾌﾗｸﾞ
						newCreditBean.setEraserCompleteDate(eraserBean
								.getEraserCompleteDate()); // 消込完了日
					}

					// 入金トランザクション(入金)更新処理
					updateCredit(newCreditBean);

					beanEraser.setEraserAmount(crEraserAmount);
					beanEraser.setDataType(creditBean.getDataType());
					beanEraser.setCreditNo(creditBean.getCreditNo());
					beanEraser.setRowNo(creditBean.getRowNo());
					beanEraser.setVenderDivision(TS);
					beanEraser.setVenderCd(creditBean.getVenderCd());
					beanList.add(beanEraser);

					// 消し込みの必要な額＝０
					balanceAmount = new BigDecimal(0);

					break;
				} else {
					// 消し込みの必要な額＝消し込みの必要な額－現入金消込残
					balanceAmount = balanceAmount.subtract(creditBean
							.getCreditEraserAmount());
					// 消込額＝現消込額＋現入金消込残
					crEraserAmount = creditBean.getCrEraserAmount().add(
						creditBean.getCreditEraserAmount());

					newCreditBean.setCrEraserAmount(crEraserAmount);
					creditBean.setCrEraserAmount(crEraserAmount);
					// 入金消込残＝０
					creditBean.setCreditEraserAmount(balance);
					newCreditBean.setCreditEraserAmount(balance);

					newCreditBean.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了ﾌﾗｸﾞ
					newCreditBean.setEraserCompleteDate(eraserBean
							.getEraserCompleteDate()); // 消込完了日

					// 入金トランザクション(入金)更新処理
					updateCredit(newCreditBean);

					beanEraser.setEraserAmount(crEraserAmount);
					beanEraser.setDataType(creditBean.getDataType());
					beanEraser.setCreditNo(creditBean.getCreditNo());
					beanEraser.setRowNo(creditBean.getRowNo());
					beanEraser.setVenderDivision(TS);
					beanEraser.setVenderCd(creditBean.getVenderCd());
					beanList.add(beanEraser);
				}
			} else if (creditBean.getDataType().equals(DATA_TYPE_OFFSET)) {
				// 入金トランザクション(相殺)更新データ作成
				ClaimEraserDetail newCreditBean = new ClaimEraserDetail();
				newCreditBean.setCreditNo(creditBean.getCreditNo()); // 相殺番号
				newCreditBean.setVenderDivision(TS); // 取引先区分
				newCreditBean.setVenderCd(creditBean.getVenderCd()); // 請求先コード
				newCreditBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了ﾌﾗｸﾞ
				newCreditBean.setUpdateDate(eraserBean.getUpdateDate()); // 更新日時
				newCreditBean.setUpdatorCd(eraserBean.getUpdatorCd()); // 更新者ＩＤ

				BigDecimal crEraserAmount = new BigDecimal(0); // 消込額
				BigDecimal balance = new BigDecimal(0); // 入金消込残

				EraserCsmBean beanEraser = new EraserCsmBean();
				beanEraser
						.setBeforeEraserAmount(creditBean.getCrEraserAmount());

				// 入金消込残>=消し込みの必要な額(画面請求金額)
				if (creditBean.getCreditEraserAmount().compareTo(balanceAmount) >= 0) {
					// 消込額＝現消込額＋消し込みの必要な額
					crEraserAmount = creditBean.getCrEraserAmount().add(
						balanceAmount);
					newCreditBean.setCrEraserAmount(crEraserAmount);
					creditBean.setCrEraserAmount(crEraserAmount);
					// 入金消込残＝現入金消込残－消し込みの必要な額
					balance = creditBean.getCreditEraserAmount().subtract(
						balanceAmount);
					creditBean.setCreditEraserAmount(balance);
					newCreditBean.setCreditEraserAmount(balance);

					if (balance.compareTo(new BigDecimal(0)) == 0) {
						newCreditBean
								.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了ﾌﾗｸﾞ
						newCreditBean.setEraserCompleteDate(eraserBean
								.getEraserCompleteDate()); // 消込完了日
					}

					// 入金トランザクション(相殺)更新処理
					updateOffsetGroupData(newCreditBean);

					beanEraser.setEraserAmount(crEraserAmount);
					beanEraser.setDataType(creditBean.getDataType());
					beanEraser.setCreditNo(creditBean.getCreditNo());
					beanEraser.setRowNo(BigDecimal.ONE);
					beanEraser
							.setVenderDivision(creditBean.getVenderDivision());
					beanEraser.setVenderCd(creditBean.getVenderCd());
					beanList.add(beanEraser);

					// 消し込みの必要な額＝０
					balanceAmount = new BigDecimal(0);

					break;
				} else {
					// 消し込みの必要な額＝消し込みの必要な額－現入金消込残
					balanceAmount = balanceAmount.subtract(creditBean
							.getCreditEraserAmount());
					// 消込額＝現消込額＋現入金消込残
					crEraserAmount = creditBean.getCrEraserAmount().add(
						creditBean.getCreditEraserAmount());

					newCreditBean.setCrEraserAmount(crEraserAmount);
					creditBean.setCrEraserAmount(crEraserAmount);
					// 入金消込残＝０
					creditBean.setCreditEraserAmount(balance);
					newCreditBean.setCreditEraserAmount(balance);

					newCreditBean.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了ﾌﾗｸﾞ
					newCreditBean.setEraserCompleteDate(eraserBean
							.getEraserCompleteDate()); // 消込完了日

					// 入金トランザクション(相殺)更新処理
					updateOffsetGroupData(newCreditBean);

					beanEraser.setEraserAmount(crEraserAmount);
					beanEraser.setDataType(creditBean.getDataType());
					beanEraser.setCreditNo(creditBean.getCreditNo());
					beanEraser.setRowNo(BigDecimal.ONE);
					beanEraser
							.setVenderDivision(creditBean.getVenderDivision());
					beanEraser.setVenderCd(creditBean.getVenderCd());
					beanList.add(beanEraser);
				}
			}
		}

	}

	/**
	 * 追加処理用のEraserHeaderDetailを作成する.
	 * @param beanEraser 消込データ
	 * @param bean 消込対象データ
	 * @param slipNo 伝票番号
	 * @return EraserHeaderDetail
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private EraserHeaderDetail insertEraserHeaderDetail(
			final ClaimEraserCsm beanEraser, final EraserCsmBean bean,
			final String slipNo) throws NoDataException,
			IllegalAccessException, InvocationTargetException {
		EraserHeaderDetail newBean = new EraserHeaderDetail();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, beanEraser);

		String eraserNo = null;
		BigDecimal rowNo = BigDecimal.ONE;
		BigDecimal eraserAmount = BigDecimal.ZERO;
		BigDecimal salesEraserDivision = BigDecimal.ZERO;
		BigDecimal creditEraserDivision = BigDecimal.ZERO;

		/* 消込番号取得 */
		eraserNo = getEraserNo(beanEraser.getUpdatorCd());

		/* 消込額 = 処理後消込額 - 処理前消込額 */
		eraserAmount = bean.getEraserAmount().subtract(
			bean.getBeforeEraserAmount());

		Sales beanSales = salesDao.getEntity(slipNo);

		if (beanSales != null) {
			salesEraserDivision = beanSales.getEraserCompleteDivision();
		}

		if (bean.getDataType().equals(DATA_TYPE_CREDIT)) {
			/* 入金 */
			Credit beanCredit = creditDao.getEntity(bean.getCreditNo(), bean
					.getRowNo());

			if (beanCredit != null) {
				creditEraserDivision = beanCredit.getEraserCompleteDivision();
			}

			rowNo = bean.getRowNo();
		} else if (bean.getDataType().equals(DATA_TYPE_OFFSET)) {
			/* 相殺 */
			OffsetGroupData beanOffset = offsetGroupDataDao.getEntity(bean
					.getCreditNo(), bean.getVenderCd(), bean
					.getVenderDivision());

			if (beanOffset != null) {
				creditEraserDivision = beanOffset.getEraserCompleteDivision();
			}
		}

		/* コピーしきれなかった分は手で */
		newBean.setEraserNo(eraserNo);
		newBean.setSlipNo(slipNo);
		newBean.setCreditNo(bean.getCreditNo());
		newBean.setRowNo(rowNo);
		newBean.setEraserAmount(eraserAmount);
		newBean.setSalesEraserDivision(salesEraserDivision);
		newBean.setCreditEraserDivision(creditEraserDivision);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(beanEraser.getUpdatorCd());
		newBean.setUpdatorCd(beanEraser.getUpdatorCd());

		return newBean;
	}

	/**
	 * 消込ログ追加登録
	 * @param bean 登録データ
	 */
	public void insertEraserHeaderDetail(final EraserHeaderDetail bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			eraserHeaderDetailDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 消込番号取得
	 * @param updatorCd 更新者ID
	 * @return String 消込番号
	 * @throws NoDataException データ無し例外
	 */
	private String getEraserNo(final String updatorCd) throws NoDataException {
		FunGetSlipNoCallDto dto = new FunGetSlipNoCallDto();
		dto.setPStrUpdatorCd(updatorCd);

		/* 消込番号取得ファンクション呼出 */
		procedureCallDao.funGetEraserNo(dto);

		if (StringUtils.isEmpty(dto.getPStrSlipNo())) {
			// 消込番号取得エラー
			throw new NoDataException();
		}

		return dto.getPStrSlipNo();
	}

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	public void statusUpdate(final EraserCsmDetailForm frm,
			final BigDecimal status, final String tantoCd)
			throws NoDataException {
		for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
			EraserCsm bean = getEntity(frm.getEraserCsmList().get(i)
					.getDataType(), frm.getEraserCsmList().get(i).getSlipNo());

			if (StringUtils.isEmpty(bean.getEraserorCd())) {
				bean.setEraserorCd(tantoCd);
			}

			bean.setApprovalStatus(status);
			bean.setUpdatorCd(tantoCd);

			if (status.equals(new BigDecimal("3"))) {
				bean.setApprovaldate(AecDateUtils.getCurrentTimestamp());
				bean.setApprovedby(tantoCd);
			} else {
				bean.setApprovaldate(null);
				bean.setApprovedby(null);
			}

			/* 登録処理 */
			try {
				/* 更新処理 */
				eraserCsmEntityDao.update(bean);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}
		}
	}

	/**
	 * 消込トランザクション検索（登録用）
	 * @param dataType dataType
	 * @param slipNo slipNo
	 * @return EraserCsm
	 * @throws NoDataException NoDataException
	 */
	public EraserCsm getEntity(final BigDecimal dataType, final String slipNo)
			throws NoDataException {
		if (StringUtils.isEmpty(slipNo)) {
			throw new IllegalArgumentException("slipNo is empty");
		}

		EraserCsm bean = eraserCsmEntityDao.getEntity(dataType, slipNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * eraserDetailDaoを設定します。
	 * @param eraserDetailDao EraserDetailDao
	 */
	public void setEraserDetailDao(final ClaimEraserDetailDao eraserDetailDao) {
		this.eraserDetailDao = eraserDetailDao;
	}

	/**
	 * eraserCsmDetailDaoを設定します。
	 * @param eraserCsmDetailDao EraserCsmDetailDao
	 */
	public void setEraserCsmDetailDao(
			final ClaimEraserCsmDetailDao eraserCsmDetailDao) {
		this.eraserCsmDetailDao = eraserCsmDetailDao;
	}

	/**
	 * eraserCsmDaoを設定します。
	 * @param eraserCsmDao EraserCsmDao
	 */
	public void setEraserCsmDao(final ClaimEraserCsmDao eraserCsmDao) {
		this.eraserCsmDao = eraserCsmDao;
	}

	/**
	 * eraserSalesCsmDaoを設定します。
	 * @param eraserSalesCsmDao EraserSalesCsmDao
	 */
	public final void setEraserSalesCsmDao(
			final ClaimEraserSalesCsmDao eraserSalesCsmDao) {
		this.eraserSalesCsmDao = eraserSalesCsmDao;
	}

	/**
	 * eraserCsmEntityDaoを設定します。
	 * @param eraserCsmEntityDao eraserCsmEntityDao
	 */
	public void setEraserCsmEntityDao(final EraserCsmDao eraserCsmEntityDao) {
		this.eraserCsmEntityDao = eraserCsmEntityDao;
	}

	/**
	 * eraserHeaderDetailDaoを設定します。
	 * @param eraserHeaderDetailDao eraserHeaderDetailDao
	 */
	public void setEraserHeaderDetailDao(
			final EraserHeaderDetailDao eraserHeaderDetailDao) {
		this.eraserHeaderDetailDao = eraserHeaderDetailDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * salesDaoを設定します。
	 * @param salesDao salesDao
	 */
	public void setSalesDao(final SalesDao salesDao) {
		this.salesDao = salesDao;
	}

	/**
	 * creditDaoを設定します。
	 * @param creditDao creditDao
	 */
	public void setCreditDao(final CreditDao creditDao) {
		this.creditDao = creditDao;
	}

	/**
	 * offsetGroupDataDaoを設定します。
	 * @param offsetGroupDataDao offsetGroupDataDao
	 */
	public void setOffsetGroupDataDao(
			final OffsetGroupDataDao offsetGroupDataDao) {
		this.offsetGroupDataDao = offsetGroupDataDao;
	}
}
