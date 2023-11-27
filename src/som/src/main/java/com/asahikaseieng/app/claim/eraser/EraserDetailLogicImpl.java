/*
 * Created on 2008/07/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraser;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetailDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserHeader;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserHeaderDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserHeaderDetail;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserHeaderDetailDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserSales;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserSalesDao;
import com.asahikaseieng.dao.nonentity.procedurecall.FunGetSlipNoCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

/**
 * カレンダーマスタ詳細 ロジック実装クラス
 * @author tosco
 */
public class EraserDetailLogicImpl implements EraserDetailLogic {

	/** データ種別(売上) */
	private static final BigDecimal DATA_TYPE_SALES = new BigDecimal(1);

	/** データ種別(入金) */
	private static final BigDecimal DATA_TYPE_CREDIT = new BigDecimal(2);

	/** データ種別(支払) */
	private static final BigDecimal DATA_TYPE_PAYMENT = new BigDecimal(4);

	/** データ種別(相殺) */
	private static final BigDecimal DATA_TYPE_OFFSET = new BigDecimal(5);

	/** 消込完了フラグ(完了) */
	private static final BigDecimal DIVISION_COMPLETE = new BigDecimal(1);

	/** 消込完了フラグ(未完了) */
	private static final BigDecimal DIVISION_NOT_COMPLETE = new BigDecimal(0);

	private ClaimEraserDetailDao eraserDetailDao;

	private ClaimEraserSalesDao eraserSalesDao;

	private ClaimEraserHeaderDao eraserHeaderDao;

	private ClaimEraserHeaderDetailDao eraserHeaderDetailDao;

	private ClaimEraserDao eraserDao;

	/** 伝票番号取得FUNCTION DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ.カレンダーマスタ詳細ロジック
	 */
	public EraserDetailLogicImpl() {
	}

	/**
	 * 消込・請求・入金データ検索処理を行う.消込入力詳細
	 * 
	 * @param eraserNo 消込番号
	 * @param sectionCd 部門コード
	 * @param venderCd 請求先コード
	 * @param tantoCd 担当者コード
	 * @param tantoNm 担当者名称
	 * @return List<EraserDetail> 消込入力詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<ClaimEraserDetail> getDetailData(final String eraserNo,
			final String sectionCd, final String venderCd,
			final String tantoCd, final String tantoNm) throws NoDataException {

		checkParams(venderCd);

		List<ClaimEraserDetail> beanList = eraserDetailDao.getDetailData(
			eraserNo, sectionCd, venderCd, tantoCd, tantoNm);

		if (beanList.isEmpty()) {
			throw new NoDataException();
		}

		return beanList;
	}

	/**
	 * 請求明細データ検索処理を行う.消込入力詳細
	 * 
	 * @param eraserNo 消込番号
	 * @param sectionCd 部門コード
	 * @param venderCd 請求先コード
	 * @param tantoCd 担当者コード
	 * @param tantoNm 担当者名称
	 * @return List<EraserSales> 消込入力詳細(下段) 請求明細データ
	 * @throws NoDataException NoDataException
	 */
	public List<ClaimEraserSales> getSalesData(final String eraserNo,
			final String sectionCd, final String venderCd,
			final String tantoCd, final String tantoNm) throws NoDataException {

		checkParams(venderCd);

		List<ClaimEraserSales> beanList = eraserSalesDao.getSalesData(eraserNo,
			sectionCd, venderCd, tantoCd, tantoNm);

		if (beanList.isEmpty()) {
			throw new NoDataException();
		}

		return beanList;
	}

	/**
	 * 登録処理を行う.
	 * @param headerBean 消込ヘッダーBean
	 * @param detailList 請求ヘッダ・入金データリスト
	 * @param salesList 請求データリスト
	 * @throws NoDataException NoDataException
	 */
	public void insert(final ClaimEraserHeader headerBean,
			final List<ClaimEraserDetail> detailList,
			final List<ClaimEraserSales> salesList) throws NoDataException {
		int insertNum;

		if (headerBean == null || detailList == null || salesList == null) {
			throw new IllegalArgumentException("bean == null");
		}

		// 消込番号取得(FUNCTION呼出)
		headerBean.setEraserNo(callFunction(headerBean.getInputorCd()));

		try {

			// 消込ヘッダー登録
			insertNum = eraserHeaderDao.insert(headerBean);
			if (insertNum != 1) {
				throw new NoDataException();
			}

			// 請求ヘッダー更新、消込トラン登録、入金トラン更新処理
			insertUpdateData(headerBean, detailList, salesList);

		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			throw new NoDataException();
		}

	}

	/**
	 * 更新処理を行う.
	 * @param headerBean 消込ヘッダーBean
	 * @param detailList 請求ヘッダ・入金データリスト
	 * @param salesList 請求データリスト
	 * @throws NoDataException データ無し例外
	 */
	public void update(final ClaimEraserHeader headerBean,
			final List<ClaimEraserDetail> detailList,
			final List<ClaimEraserSales> salesList) throws NoDataException {
		int updateNum;

		if (headerBean == null || detailList == null || salesList == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {

			// 消込ヘッダー内訳検索(前回内訳データ取得)
			List<ClaimEraserHeaderDetail> eraserDetailList = eraserHeaderDetailDao
					.getEntity(headerBean.getEraserNo());
			if (eraserDetailList != null) {
				// 入金トランザクション更新処理
				updateCreditNotComplete(headerBean, eraserDetailList,
					detailList);
			}

			// 消込トラン検索(前回消込データ取得)
			List<ClaimEraser> eraserList = eraserDao.getEntity(headerBean
					.getEraserNo());
			if (eraserList.isEmpty()) {
				throw new NoDataException();
			}

			// 入金トラン、売上トランを消し込む前の状態に戻す
			for (int k = 0; k < eraserList.size(); k++) {
				ClaimEraser eraserBean = eraserList.get(k);

				if (DATA_TYPE_SALES.equals(eraserBean.getDataType())) {
					// 売上トランザクション更新処理
					updateSalesNotComplete(headerBean, eraserBean);
				} else if (DATA_TYPE_CREDIT.equals(eraserBean.getDataType())) {
					// 入金トランザクション更新処理
					updateCreditNotComplete(headerBean, eraserBean);
				} else if (DATA_TYPE_PAYMENT.equals(eraserBean.getDataType())) {
					// 支払トランザクション更新処理
					updatePaymentNotComplete(headerBean, eraserBean);
				} else if (DATA_TYPE_OFFSET.equals(eraserBean.getDataType())) {
					// グループ間相殺トランザクション更新処理
					updateOffsetGroupNotComplete(headerBean, eraserBean);
				}

			}

			// 消込トランザクション削除処理
			updateNum = eraserDao.delete(headerBean.getEraserNo());
			if (updateNum == 0) {
				throw new NoDataException();
			}

			// 消込ヘッダー内訳削除処理
			if (eraserDetailList != null) {
				for (int i = 0; i < eraserDetailList.size(); i++) {
					ClaimEraserHeaderDetail detailBean = eraserDetailList
							.get(i);
					updateNum = eraserHeaderDetailDao.delete(detailBean);
				}
			}

			// 消込ヘッダー更新処理
			updateNum = eraserHeaderDao.update(headerBean);
			if (updateNum != 1) {
				throw new NoDataException();
			}

			// insertと同じ処理
			// 請求ヘッダー更新、消込トラン登録、入金トラン更新処理
			insertUpdateData(headerBean, detailList, salesList);

		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 削除処理を行う.
	 * @param headerBean 消込ヘッダーBean
	 * @param detailList 請求ヘッダ・入金データリスト
	 * @param salesList 請求データリスト
	 * @throws NoDataException データ無し例外
	 */
	public void delete(final ClaimEraserHeader headerBean,
			final List<ClaimEraserDetail> detailList,
			final List<ClaimEraserSales> salesList) throws NoDataException {
		int updateNum;

		if (headerBean == null || detailList == null || salesList == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 消込ヘッダー内訳検索(前回内訳データ取得)
			List<ClaimEraserHeaderDetail> eraserDetailList = eraserHeaderDetailDao
					.getEntity(headerBean.getEraserNo());

			if (eraserDetailList != null) {
				// 入金トランザクション更新処理
				updateCreditNotComplete(headerBean, eraserDetailList,
					detailList);
			}

			// 消込トラン検索(前回消込データ取得)
			List<ClaimEraser> eraserList = eraserDao.getEntity(headerBean
					.getEraserNo());
			if (eraserList.isEmpty()) {
				throw new NoDataException();
			}

			// 入金トラン、売上トランを消し込む前の状態に戻す
			for (int k = 0; k < eraserList.size(); k++) {
				ClaimEraser eraserBean = eraserList.get(k);

				if (DATA_TYPE_SALES.equals(eraserBean.getDataType())) {
					// 売上トランザクション更新処理
					updateSalesNotComplete(headerBean, eraserBean);
				} else if (DATA_TYPE_CREDIT.equals(eraserBean.getDataType())) {
					// 入金トランザクション更新処理
					updateCreditNotComplete(headerBean, eraserBean);
				} else if (DATA_TYPE_PAYMENT.equals(eraserBean.getDataType())) {
					// 支払トランザクション更新処理
					updatePaymentNotComplete(headerBean, eraserBean);
				} else if (DATA_TYPE_OFFSET.equals(eraserBean.getDataType())) {
					// グループ間相殺トランザクション更新処理
					updateOffsetGroupNotComplete(headerBean, eraserBean);
				}

			}

			// 消込ヘッダー削除処理
			updateNum = eraserHeaderDao.delete(headerBean);
			if (updateNum != 1) {
				throw new NoDataException();
			}

			// 消込ヘッダー内訳削除処理
			if (eraserDetailList != null) {
				for (int i = 0; i < eraserDetailList.size(); i++) {
					ClaimEraserHeaderDetail detailBean = eraserDetailList
							.get(i);
					updateNum = eraserHeaderDetailDao.delete(detailBean);
				}
			}

			// 消込トランザクション削除処理
			updateNum = eraserDao.delete(headerBean.getEraserNo());
			if (updateNum == 0) {
				throw new NoDataException();
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 削除エラー */
			throw new NoDataException();
		}
	}

	/**
	 * 消込番号取得
	 * @param updatorCd 更新者ID
	 * @return String 消込番号
	 * @throws NoDataException データ無し例外
	 */
	private String callFunction(final String updatorCd) throws NoDataException {
		FunGetSlipNoCallDto funDto = new FunGetSlipNoCallDto();
		funDto.setPStrUpdatorCd(updatorCd);

		// 消込番号取得FUNCTION呼出
		procedureCallDao.funGetEraserNo(funDto);

		if (StringUtils.isEmpty(funDto.getPStrSlipNo())) {
			// 消込番号取得エラー
			throw new NoDataException();
		}

		return funDto.getPStrSlipNo();
	}

	/**
	 * パラメータチェック.
	 * @param cd 検索条件
	 * @throws IllegalArgumentException
	 */
	private void checkParams(final String cd) throws IllegalArgumentException {

		if (StringUtils.isEmpty(cd)) {
			throw new IllegalArgumentException(
					"CalendarDetailLogic IllegalArgumentException : Paramater is empty checkParams(cd).");
		}
	}

	/**
	 * 登録／更新処理を行う. (ループ1)入金データ ①消込ヘッダー内訳登録 ②入金トランザクション更新 (ループ2)明細データ
	 * ③消込トランザクション登録 ④売上or入金(相殺)or支払(相殺)or相殺トラン更新
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param detailList 請求ヘッダ・入金データリスト
	 * @param salesList 請求データリスト
	 * @throws NoDataException NoDataException
	 */
	private void insertUpdateData(final ClaimEraserHeader headerBean,
			final List<ClaimEraserDetail> detailList,
			final List<ClaimEraserSales> salesList) throws NoDataException {
		int eraserRowNo = 1;

		// 消込完了済判定
		BigDecimal sumEraserAmount = new BigDecimal(0); // 消込額合計
		ClaimEraserDetail bean = detailList.get(0);
		BigDecimal defaultEraserAmount = bean.getDefaultEraserAmount(); // 消込額(DB取得初期値)
		sumEraserAmount = sumEraserAmount.add(defaultEraserAmount);

		try {
			BigDecimal eraserAmount = bean.getEraserAmount(); // 消込合計

			// 入金データループ
			for (int j = 0; j < detailList.size(); j++) {
				ClaimEraserDetail creditBean = detailList.get(j);

				if (creditBean.getCreditEraserAmount() == null
						|| creditBean.getCreditEraserAmount().compareTo(
							new BigDecimal(0)) == 0) {
					// 入金消込残＝０の場合は次レコード
					continue;
				}

				// 消込ヘッダー内訳データ作成
				ClaimEraserHeaderDetail detailBean = new ClaimEraserHeaderDetail();
				detailBean.setEraserNo(headerBean.getEraserNo()); // 消込番号
				detailBean.setCreditNo(creditBean.getCreditNo()); // 入金番号
				detailBean.setRowNo(creditBean.getRowNo()); // 行番号
				detailBean.setInputDate(headerBean.getUpdateDate()); // 登録日時
				detailBean.setInputorCd(headerBean.getUpdatorCd()); // 登録者ＩＤ
				detailBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
				detailBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

				// 入金トランザクション更新データ作成
				ClaimEraserDetail newCreditBean = new ClaimEraserDetail();
				newCreditBean.setCreditNo(creditBean.getCreditNo()); // 入金番号
				newCreditBean.setRowNo(creditBean.getRowNo()); // 行番号
				newCreditBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了ﾌﾗｸﾞ
				newCreditBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
				newCreditBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

				BigDecimal crEraserAmount = new BigDecimal(0); // 消込額
				BigDecimal balance = new BigDecimal(0); // 入金消込残

				// 入金消込残>=消し込みの必要な額(画面請求金額)
				if (creditBean.getCreditEraserAmount().compareTo(eraserAmount) >= 0) {
					detailBean.setEraserAmount(eraserAmount); // 消込額
					// 消込ヘッダー内訳登録処理
					insertEraserHeaderDetail(detailBean);

					// 消込額＝現消込額＋消し込みの必要な額
					crEraserAmount = creditBean.getCrEraserAmount().add(
						eraserAmount);
					newCreditBean.setCrEraserAmount(crEraserAmount);
					creditBean.setCrEraserAmount(crEraserAmount);
					// 入金消込残＝現入金消込残－消し込みの必要な額
					balance = creditBean.getCreditEraserAmount().subtract(
						eraserAmount);
					creditBean.setCreditEraserAmount(balance);
					newCreditBean.setCreditEraserAmount(balance);

					if (balance.compareTo(new BigDecimal(0)) == 0) {
						newCreditBean
								.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了ﾌﾗｸﾞ
						newCreditBean.setEraserCompleteDate(headerBean
								.getEraserDate()); // 消込完了日
					}

					// 入金トランザクション更新処理
					updateCredit(newCreditBean);

					// 消し込みの必要な額＝０
					eraserAmount = new BigDecimal(0);

					break;
				} else {
					// 消し込みの必要な額＝消し込みの必要な額－現入金消込残
					eraserAmount = eraserAmount.subtract(creditBean
							.getCreditEraserAmount());
					// 消込額＝現消込額＋現入金消込残
					crEraserAmount = creditBean.getCrEraserAmount().add(
						creditBean.getCreditEraserAmount());

					detailBean.setEraserAmount(crEraserAmount); // 消込額
					// 消込ヘッダー内訳登録処理
					insertEraserHeaderDetail(detailBean);

					newCreditBean.setCrEraserAmount(crEraserAmount);
					creditBean.setCrEraserAmount(crEraserAmount);
					// 入金消込残＝０
					creditBean.setCreditEraserAmount(balance);
					newCreditBean.setCreditEraserAmount(balance);

					newCreditBean.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了ﾌﾗｸﾞ
					newCreditBean.setEraserCompleteDate(headerBean
							.getEraserDate()); // 消込完了日

					// 入金トランザクション更新処理
					updateCredit(newCreditBean);
				}
			}

			// 請求データループ
			for (int i = 0; i < salesList.size(); i++) {

				ClaimEraserSales salesBean = salesList.get(i);

				if (!salesBean.isCheckFlg()
						|| (salesBean.isCheckFlg() && salesBean.getCheckKbn()
								.equals("2"))) {
					// チェック無 または チェック有かつdisabledチェックボックス
					continue;
				}

				// 消込トランデータ作成
				ClaimEraser eraserBean = makeEraserData(headerBean, salesBean,
					eraserRowNo);
				// 消込トランザクション登録処理
				insertEraser(eraserBean);

				if (DATA_TYPE_SALES.equals(salesBean.getDataType())) {
					// 売上トランザクション更新処理
					updateSales(headerBean, salesBean);
				} else if (DATA_TYPE_CREDIT.equals(eraserBean.getDataType())) {
					// 入金トランザクション更新処理
					updateCredit(headerBean, salesBean);
				} else if (DATA_TYPE_PAYMENT.equals(salesBean.getDataType())) {
					// 支払トランザクション更新処理
					updatePayment(headerBean, salesBean);
				} else if (DATA_TYPE_OFFSET.equals(salesBean.getDataType())) {
					// グループ間相殺トランザクション更新処理
					updateOffsetGroup(headerBean, salesBean);
				}

				eraserRowNo = eraserRowNo + 1;
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			throw new NoDataException();
		}
	}

	/**
	 * 
	 * 消込トランデータ作成
	 * @param headerBean 消込ヘッダーBean
	 * @param salesBean 請求データBean
	 * @param eraserRowNo 行番号
	 * @return Eraser 消込トランデータ
	 */
	private ClaimEraser makeEraserData(final ClaimEraserHeader headerBean,
			final ClaimEraserSales salesBean, final int eraserRowNo) {
		// 消込トランデータ作成
		ClaimEraser eraserBean = new ClaimEraser();
		eraserBean.setEraserNo(headerBean.getEraserNo()); // 消込番号
		eraserBean.setRowNo(new BigDecimal(eraserRowNo)); // 行番号
		eraserBean.setBranchNo(new BigDecimal(1)); // 枝番
		eraserBean.setDataType(salesBean.getDataType()); // データ種別
		eraserBean.setSlipNo(salesBean.getSlipNo()); // 伝票番号(相殺番号)
		if (DATA_TYPE_OFFSET.equals(salesBean.getDataType())) {
			// グループ間相殺
			eraserBean.setVenderCd(salesBean.getCustomerCd()); // 請求先/支払先コード
		} else {
			// 売上、支払、入金
			eraserBean.setSlipRowNo(salesBean.getRowNo()); // 伝票番号行番号
		}
		eraserBean.setEraserDate(headerBean.getEraserDate()); // 消込日付
		eraserBean.setEraserAmount(salesBean.getTotalSalesAmount()); // 消込額
		eraserBean.setApprovalStatus(new BigDecimal(1)); // 承認ステータス(入力中)
		eraserBean.setInputDate(headerBean.getInputDate()); // 登録日時
		eraserBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
		eraserBean.setInputorCd(headerBean.getInputorCd()); // 登録者ＩＤ
		eraserBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

		return eraserBean;
	}

	/**
	 * 消込トランザクション登録処理
	 * 
	 * @param eraserBean 消込トランBean
	 * @throws NoDataException
	 */
	private void insertEraser(final ClaimEraser eraserBean)
			throws NoDataException {
		int insertNum = eraserDao.insert(eraserBean);
		if (insertNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 消込ヘッダー内訳登録処理
	 * 
	 * @param bean 消込ヘッダー内訳Bean
	 * @throws NoDataException
	 */
	private void insertEraserHeaderDetail(final ClaimEraserHeaderDetail bean)
			throws NoDataException {
		int insertNum = eraserHeaderDetailDao.insert(bean);
		if (insertNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 入金トランザクション更新処理
	 * 
	 * @param newCreditBean 入金トランBean
	 * @throws NoDataException
	 */
	private void updateCredit(final ClaimEraserDetail newCreditBean)
			throws NoDataException {
		// 入金トランザクション更新処理
		int updateNum = eraserDetailDao.updateCredit(newCreditBean);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 売上トランザクション更新処理
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param salesBean 請求データBean
	 * @throws NoDataException
	 */
	private void updateSales(final ClaimEraserHeader headerBean,
			final ClaimEraserSales salesBean) throws NoDataException {
		// 売上トランザクション更新データ作成
		ClaimEraserSales sales = new ClaimEraserSales();
		sales.setSlipNo(salesBean.getSlipNo()); // 伝票番号
		sales.setRowNo(salesBean.getRowNo()); // 行番号
		sales.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了フラグ
		sales.setEraserCompleteDate(headerBean.getEraserDate()); // 消込完了日
		sales.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
		sales.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

		// 売上トランザクション更新処理
		int updateNum = eraserSalesDao.update(sales);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 売上トランザクション更新処理 (消込完了→未完了)
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param eraserBean 消込データBean
	 * @throws NoDataException
	 */
	private void updateSalesNotComplete(final ClaimEraserHeader headerBean,
			final ClaimEraser eraserBean) throws NoDataException {
		// 売上トランザクション更新データ作成
		ClaimEraserSales sales = new ClaimEraserSales();
		sales.setSlipNo(eraserBean.getSlipNo()); // 伝票番号
		sales.setRowNo(eraserBean.getSlipRowNo()); // 伝票番号行番号
		sales.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ
		sales.setEraserCompleteDate(null); // 消込完了日
		sales.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
		sales.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

		// 売上トランザクション更新処理
		int updateNum = eraserSalesDao.update(sales);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * グループ間相殺トランザクション更新処理
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param salesBean 請求データBean
	 * @throws NoDataException
	 */
	private void updateOffsetGroup(final ClaimEraserHeader headerBean,
			final ClaimEraserSales salesBean) throws NoDataException {
		// グループ間相殺トランザクション更新データ作成
		ClaimEraserDetail offsetBean = new ClaimEraserDetail();
		offsetBean.setOffsetNo(salesBean.getSlipNo()); // 相殺番号
		offsetBean.setVenderCd(salesBean.getCustomerCd()); // 請求先/支払先コード
		offsetBean.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了フラグ
		offsetBean.setEraserCompleteDate(headerBean.getEraserDate()); // 消込完了日
		offsetBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
		offsetBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

		// グループ間相殺トランザクション更新処理
		int updateNum = eraserDetailDao.updateOffsetGroup(offsetBean);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * グループ間相殺トランザクション更新処理 (消込完了→未完了)
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param eraserBean 消込データBean
	 * @throws NoDataException
	 */
	private void updateOffsetGroupNotComplete(
			final ClaimEraserHeader headerBean, final ClaimEraser eraserBean)
			throws NoDataException {
		// グループ間相殺トランザクション更新データ作成
		ClaimEraserDetail offsetBean = new ClaimEraserDetail();
		offsetBean.setOffsetNo(eraserBean.getSlipNo()); // 相殺番号
		offsetBean.setVenderCd(eraserBean.getVenderCd()); // 請求先/支払先コード
		offsetBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ
		offsetBean.setEraserCompleteDate(null); // 消込完了日
		offsetBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
		offsetBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

		// グループ間相殺トランザクション更新処理
		int updateNum = eraserDetailDao.updateOffsetGroup(offsetBean);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 支払トランザクション更新処理
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param salesBean 請求データBean
	 * @throws NoDataException
	 */
	private void updatePayment(final ClaimEraserHeader headerBean,
			final ClaimEraserSales salesBean) throws NoDataException {
		// 支払トランザクション更新データ作成
		ClaimEraserDetail payBean = new ClaimEraserDetail();
		payBean.setPaySlipNo(salesBean.getSlipNo()); // 伝票番号
		payBean.setPayRowNo(salesBean.getRowNo()); // 行番号
		payBean.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了フラグ
		payBean.setEraserCompleteDate(headerBean.getEraserDate()); // 消込完了日
		payBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
		payBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

		// 支払トランザクション更新処理
		int updateNum = eraserDetailDao.updatePayment(payBean);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 支払トランザクション更新処理 (消込完了→未完了)
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param eraserBean 消込データBean
	 * @throws NoDataException
	 */
	private void updatePaymentNotComplete(final ClaimEraserHeader headerBean,
			final ClaimEraser eraserBean) throws NoDataException {
		// 支払トランザクション更新データ作成
		ClaimEraserDetail payBean = new ClaimEraserDetail();
		payBean.setPaySlipNo(eraserBean.getSlipNo()); // 伝票番号
		payBean.setPayRowNo(eraserBean.getSlipRowNo()); // 行番号
		payBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ
		payBean.setEraserCompleteDate(null); // 消込完了日
		payBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
		payBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

		// 支払トランザクション更新処理
		int updateNum = eraserDetailDao.updatePayment(payBean);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 入金トランザクション更新処理(相殺データ)
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param salesBean 請求データBean
	 * @throws NoDataException
	 */
	private void updateCredit(final ClaimEraserHeader headerBean,
			final ClaimEraserSales salesBean) throws NoDataException {
		// 入金トランザクション更新データ作成
		ClaimEraserDetail creditBean = new ClaimEraserDetail();
		creditBean.setCreditNo(salesBean.getSlipNo()); // 伝票番号(入金番号)
		creditBean.setRowNo(salesBean.getRowNo()); // 行番号
		creditBean.setEraserCompleteDivision(DIVISION_COMPLETE); // 消込完了フラグ
		creditBean.setEraserCompleteDate(headerBean.getEraserDate()); // 消込完了日
		creditBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
		creditBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

		// 入金トランザクション更新処理
		int updateNum = eraserDetailDao.updateCreditOffset(creditBean);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 入金トランザクション更新処理(相殺データ) (消込完了→未完了)
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param eraserBean 消込データBean
	 * @throws NoDataException
	 */
	private void updateCreditNotComplete(final ClaimEraserHeader headerBean,
			final ClaimEraser eraserBean) throws NoDataException {
		// 入金トランザクション更新データ作成
		ClaimEraserDetail creditBean = new ClaimEraserDetail();
		creditBean.setCreditNo(eraserBean.getSlipNo()); // 伝票番号(入金番号)
		creditBean.setRowNo(eraserBean.getSlipRowNo()); // 行番号
		creditBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ
		creditBean.setEraserCompleteDate(null); // 消込完了日
		creditBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
		creditBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

		// 入金トランザクション更新処理
		int updateNum = eraserDetailDao.updateCreditOffset(creditBean);
		if (updateNum != 1) {
			throw new NoDataException();
		}
	}

	/**
	 * 入金トランザクション更新処理 (消込額・入金消込残→消込前の額、消込完了→未完了)
	 * 
	 * @param headerBean 消込ヘッダーBean
	 * @param detailBeanList 消込ヘッダー内訳Beanリスト
	 * @param detailList 入金データ(詳細上段・中段)リスト
	 * @throws NoDataException
	 */
	private void updateCreditNotComplete(final ClaimEraserHeader headerBean,
			final List<ClaimEraserHeaderDetail> detailBeanList,
			final List<ClaimEraserDetail> detailList) throws NoDataException {

		BigDecimal crEraserAmount = new BigDecimal(0);
		BigDecimal creditEraserAmount = new BigDecimal(0);

		// 消込ヘッダー内訳データループ
		for (int j = 0; j < detailBeanList.size(); j++) {
			ClaimEraserHeaderDetail detailBean = detailBeanList.get(j);

			// 入金データループ
			for (int i = 0; i < detailList.size(); i++) {
				ClaimEraserDetail creditBean = detailList.get(i);

				// 消込データに紐づく入金データの場合(入金番号、行番号が同じ)
				if (detailBean.getCreditNo().equals(creditBean.getCreditNo())
						&& detailBean.getRowNo().equals(creditBean.getRowNo())) {

					// 消込額＝入金トラン.消込額－消込ヘッダー内訳.消込額
					crEraserAmount = creditBean.getCrEraserAmount().subtract(
						detailBean.getEraserAmount());
					// 入金消込残＝入金トラン.入金消込残＋消込ヘッダー内訳.消込額
					creditEraserAmount = creditBean.getCreditEraserAmount()
							.add(detailBean.getEraserAmount());

					// 計算結果を再設定
					creditBean.setCrEraserAmount(crEraserAmount);
					creditBean.setCreditEraserAmount(creditEraserAmount);

					break;
				}
			}

			// 入金トランザクション更新データ作成
			ClaimEraserDetail newCreditBean = new ClaimEraserDetail();
			newCreditBean.setCreditNo(detailBean.getCreditNo()); // 入金番号
			newCreditBean.setRowNo(detailBean.getRowNo()); // 入金番号行番号
			newCreditBean.setCrEraserAmount(crEraserAmount); // 消込額
			newCreditBean.setCreditEraserAmount(creditEraserAmount); // 入金消込残
			newCreditBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); // 消込完了フラグ
			newCreditBean.setEraserCompleteDate(null); // 消込完了日
			newCreditBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
			newCreditBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ

			// 入金トランザクション更新処理
			int updateNum = eraserDetailDao.updateCredit(newCreditBean);
			if (updateNum != 1) {
				throw new NoDataException();
			}
		}
	}

	// /**
	// * 入金トランザクション更新処理
	// * (消込額・入金消込残→消込前の額、消込完了→未完了)
	// *
	// * @param headerBean 消込ヘッダーBean
	// * @param eraserBean 消込データ
	// * @throws NoDataException
	// */
	// private void updateCreditNotComplete(final EraserHeader headerBean
	// , final Eraser eraserBean
	// , final List<EraserDetail> detailList)
	// throws NoDataException {
	//
	// // 消込データ.入金番号、行番号がNULLの場合
	// if (StringUtils.isEmpty(eraserBean.getCreditNo())
	// || eraserBean.getCreditRowNo() == null) {
	// return;
	// }
	//
	// BigDecimal crEraserAmount = new BigDecimal(0);
	// BigDecimal creditEraserAmount = new BigDecimal(0);
	//
	// for (int i = 0; i < detailList.size(); i++) {
	// EraserDetail creditBean = detailList.get(i);
	//
	// // 消込データに紐づく入金データの場合(入金番号、行番号が同じ)
	// if (eraserBean.getCreditNo().equals(creditBean.getCreditNo())
	// && eraserBean.getCreditRowNo().equals(creditBean.getRowNo())) {
	//
	// // 消込額＝入金トラン.消込額－消込トラン.消込額
	// crEraserAmount
	// = creditBean.getCrEraserAmount().subtract(eraserBean.getEraserAmount());
	// // 入金消込残＝入金トラン.入金消込残＋消込トラン.消込額
	// creditEraserAmount
	// = creditBean.getCreditEraserAmount().add(eraserBean.getEraserAmount());
	//
	// // 計算結果を再設定
	// creditBean.setCrEraserAmount(crEraserAmount);
	// creditBean.setCreditEraserAmount(creditEraserAmount);
	//
	// break;
	// }
	// }
	//
	// // 入金トランザクション更新データ作成
	// EraserDetail newCreditBean = new EraserDetail();
	// newCreditBean.setCreditNo(eraserBean.getCreditNo()); // 入金番号
	// newCreditBean.setRowNo(eraserBean.getCreditRowNo()); // 入金番号行番号
	// newCreditBean.setCrEraserAmount(crEraserAmount); // 消込額
	// newCreditBean.setCreditEraserAmount(creditEraserAmount); // 入金消込残
	// newCreditBean.setEraserCompleteDivision(DIVISION_NOT_COMPLETE); //
	// 消込完了フラグ
	// newCreditBean.setEraserCompleteDate(null); // 消込完了日
	// newCreditBean.setUpdateDate(headerBean.getUpdateDate()); // 更新日時
	// newCreditBean.setUpdatorCd(headerBean.getUpdatorCd()); // 更新者ＩＤ
	//
	// // 入金トランザクション更新処理
	// int updateNum = eraserDetailDao.updateCredit(newCreditBean);
	// if (updateNum != 1) {
	// throw new NoDataException();
	// }
	// }

	/* -------------------- setter -------------------- */

	/**
	 * eraserDetailDaoを設定します。
	 * @param eraserDetailDao EraserDetailDao
	 */
	public void setEraserDetailDao(final ClaimEraserDetailDao eraserDetailDao) {
		this.eraserDetailDao = eraserDetailDao;
	}

	/**
	 * eraserSalesDaoを設定します。
	 * @param eraserSalesDao EraserSalesDao
	 */
	public void setEraserSalesDao(final ClaimEraserSalesDao eraserSalesDao) {
		this.eraserSalesDao = eraserSalesDao;
	}

	/**
	 * eraserDaoを設定します。
	 * @param eraserDao EraserDao
	 */
	public void setEraserDao(final ClaimEraserDao eraserDao) {
		this.eraserDao = eraserDao;
	}

	/**
	 * eraserHeaderDaoを設定します。
	 * @param eraserHeaderDao EraserHeaderDao
	 */
	public void setEraserHeaderDao(final ClaimEraserHeaderDao eraserHeaderDao) {
		this.eraserHeaderDao = eraserHeaderDao;
	}

	/**
	 * eraserHeaderDetailDaoを設定します。
	 * @param eraserHeaderDetailDao EraserHeaderDetailDao
	 */
	public void setEraserHeaderDetailDao(
			final ClaimEraserHeaderDetailDao eraserHeaderDetailDao) {
		this.eraserHeaderDetailDao = eraserHeaderDetailDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

}
