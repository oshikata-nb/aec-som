/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.credit.Credit;
import com.asahikaseieng.dao.entity.credit.CreditDao;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCredit;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCreditDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.companybank.CompanyBankListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.companybank.CompanyBankListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetailDao;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetailDao;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.FunGetSlipNoCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * DepositDetailCategoryLogicクラス．入金入力（詳細)入金分類変更イベントロジック
 * @author tosco
 */
public class DepositDetailLogicImpl implements DepositDetailLogic {

	/** 取引先マスタ検索dao */
	private VenderDetailDao venderDetailDao;

	/** プロシージャ呼び出しDAO */
	private ProcedureCallDao procedureCallDao;

	/** 入金トランザクション更新DAO */
	private CreditDao creditDao;

	/** 入金トランザクション検索DAO */
	private DepositCreditDao depositCreditDao;

	/** 勘定科目マスタ検索dao */
	private AccountsDetailDao accountsDetailDao;

	private CompanyBankListForComboboxesDao companyBankListForComboboxesDao;

	private NamesDetailDao namesDetailDao;

	private ClassificationDetailDao classificationDetailDao;

	private ClassificationListForComboboxesDao classificationListForComboboxesDao;

	/**
	 * コンストラクタ
	 */
	public DepositDetailLogicImpl() {
	}

	/**
	 * 入金トランザクション検索
	 * @param creditNo creditNo
	 * @param rowNo rowNo
	 * @return Credit
	 * @throws NoDataException NoDataException
	 */
	public Credit getEntity(final String creditNo, final BigDecimal rowNo)
			throws NoDataException {
		if (StringUtils.isEmpty(creditNo)) {
			throw new IllegalArgumentException("creditNo is empty");
		}

		Credit bean = creditDao.getEntity(creditNo, rowNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 検索処理を行う.入金入力検索処理
	 * @param venderCd 取引先コード
	 * @param venderDivision 取引先分類
	 * @return VenderDetail
	 * @throws NoDataException 検索結果が存在しない場合発生
	 */
	public VenderDetail getVenderEntity(final String venderCd,
			final String venderDivision) throws NoDataException {
		VenderDetail vender = venderDetailDao.getEntity(venderDivision,
			venderCd);
		if (vender == null) {
			throw new NoDataException();
		}
		return vender;
	}

	/**
	 * 検索処理を行う.請求額＋売上額
	 * @param venderCd 取引先コード
	 * @param venderDivision 取引先分類
	 * @return VenderDetail
	 * @throws NoDataException 検索結果が存在しない場合発生
	 */
	public DepositCredit getClaimSalesAmount(final String organizationCd,
			final String venderCd,
			final String creditDate) throws NoDataException {
		DepositCredit depositCredit = depositCreditDao.getClaimSalesAmount(organizationCd, venderCd, creditDate);

		return depositCredit;
	}
	
	/**
	 * 入金テーブルにデータを挿入する。
	 * @param list 更新明細データ
	 * @return エラーメッセージ（正常時null)
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public String insert(final List<DepositCredit> list)
			throws IllegalAccessException, InvocationTargetException {
		String res = null;
		// 更新者コード
		String updatorCode = list.get(0).getUpdatorCd();
		// 入金番号をFunctionから取得
		try {
			Credit bean = new Credit();
			String creditNo = getCreditNo(updatorCode);
			for (DepositCredit credit : list) {
				// 入金番号を設定
				credit.setCreditNo(creditNo);
				try {
					/* 値を追加用Beanにコピる */
					IgnoreCaseBeanUtils.copyProperties(bean, credit);

					bean.setInputDate(bean.getCurrentTimestamp());

					// 挿入
					int count = creditDao.insert(bean);
					if (count != 1) {
						// 排他エラー
						throw new OptimisticLockRuntimeException();
					}
				} catch (SQLRuntimeException e) {
					throw new DuplicateRuntimeException(0);
				}
			}
		} catch (NoDataException e) {
			// 入金番号が取得できなかった場合
			res = "入金番号取得エラー";
		}
		return res;
	}

	/**
	 * 入金番号を取得する。
	 * @param updatotCode 更新者ＩＤ
	 * @return 入金番号
	 * @throws NoDataException データが取得できなかった場合
	 */
	private String getCreditNo(final String updatorCode) throws NoDataException {
		String creditNo = null;

		FunGetSlipNoCallDto dto = new FunGetSlipNoCallDto();
		dto.setPStrUpdatorCd(updatorCode);

		// 入金番号取得
		procedureCallDao.funGetCreditNo(dto);
		creditNo = dto.getPStrSlipNo();
		if (StringUtils.isEmpty(creditNo)) {
			throw new NoDataException();
		}
		return creditNo;
	}

	/**
	 * 入金番号で入金トランザクションテーブルからデータを取得
	 * @param creditNo 入金番号
	 * @return 入金明細データ
	 * @throws NoDataException データが存在しない場合発生
	 */
	public List<DepositCredit> findByCreditNo(final String creditNo)
			throws NoDataException {
		List<DepositCredit> list = depositCreditDao.findByCreditNo(creditNo);
		if (list.isEmpty()) {
			// 検索結果なし
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 入金テーブルにデータを更新する。（実際はDeleteInsert）
	 * @param list 更新明細データ
	 * @return エラーメッセージ（正常時null)
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public String update(final List<DepositCredit> list)
			throws IllegalAccessException, InvocationTargetException {
		String res = null;

		// 入金番号
		DepositCredit deleteCredit = list.get(0);

		// 入金番号に該当するデータ全て削除
		delete(deleteCredit.getCreditNo());

		Credit bean = new Credit();

		for (DepositCredit credit : list) {
			try {
				/* 値を更新用Beanにコピる */
				IgnoreCaseBeanUtils.copyProperties(bean, credit);

				// 挿入
				int count = creditDao.insert(bean);
				if (count != 1) {
					// 排他エラー
					throw new OptimisticLockRuntimeException();
				}
			} catch (SQLRuntimeException e) {
				// SqlLogRegistry registry =
				// SqlLogRegistryLocator.getInstance();
				// if (registry != null) {
				// // 存在しなければ設定がOFFになっている
				// SqlLog sqlLog = registry.getLast();
				// if (sqlLog != null) {
				// // 存在しなければ直近でSQLが発行されていない
				// String completeSql = sqlLog.getCompleteSql();
				// System.out.println(completeSql);
				// }
				// }
				throw new DuplicateRuntimeException(0);
			}
		}
		return res;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Credit bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			creditDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 入金テーブルから入金番号に合致するデータを全て削除する
	 * @param creditNo 入金番号
	 * @return 削除件数
	 */
	public int delete(final String creditNo) {
		int delCount = 0;
		try {
			// 入金番号に該当するデータ全て削除
			delCount = depositCreditDao.deleteCreditNo(creditNo);
			if (delCount <= 0) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
		return delCount;
	}

	/**
	 * 入金テーブルから入金番号に合致するデータを全て削除する
	 * @param credit 削除データ
	 * @return 削除件数
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	// public int delete(final DepositCredit credit)
	// throws IllegalAccessException, InvocationTargetException {
	// int delCount = 0;
	// Credit bean = new Credit();
	//
	// try {
	// /* 値を削除用Beanにコピる */
	// IgnoreCaseBeanUtils.copyProperties(bean, credit);
	//
	// // 入金番号に該当するデータ全て削除
	// delCount = creditDao.delete(bean);
	// if (delCount <= 0) {
	// // 排他エラー
	// throw new OptimisticLockRuntimeException();
	// }
	// } catch (SQLRuntimeException e) {
	// throw new DuplicateRuntimeException(0);
	// }
	// return delCount;
	// }
	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	public void statusUpdate(final DepositDetailForm frm,
			final BigDecimal status, final String tantoCd)
			throws NoDataException {
		for (int i = 0; i < frm.getDetailList().size(); i++) {
			Credit bean = getEntity(frm.getCreditNo(), new BigDecimal(i + 1));

			bean.setApprovalStatus(status);

			if (status.equals(new BigDecimal("3"))) {
				bean.setApprovedby(tantoCd);
				bean.setApprovaldate(bean.getCurrentTimestamp());
			} else {
				bean.setApprovedby(null);
				bean.setApprovaldate(null);
			}

			bean.setUpdatorCd(tantoCd);

			/* 登録処理 */
			update(bean);
		}
	}

	/**
	 * 入金勘定科目一覧検索
	 * @return List<DepositCredit>
	 * @throws NoDataException NoDataException
	 */
	public List<DepositCredit> getClassificationAccountsList()
			throws NoDataException {
		/* 入金勘定科目検索 */
		List<DepositCredit> list = depositCreditDao
				.getClassificationAccountsList();

		if (list.isEmpty()) {
			// 検索結果が存在しない場合
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 勘定科目検索
	 * @param accountsCd 勘定科目コード
	 * @return AccountsDetail
	 */
	public AccountsDetail getAccountsEntity(final String accountsCd) {
		AccountsDetail bean = accountsDetailDao.getEntity(accountsCd);
		return bean;
	}

	/**
	 * 銀行リスト取得
	 * @return List<CompanyBankListForComboboxes>
	 */
	public List<CompanyBankListForComboboxes> getBankList() {
		List<CompanyBankListForComboboxes> list = companyBankListForComboboxesDao
				.getListForComboboxes();
		return list;
	}

	/**
	 * 名称マスタ検索
	 * @return NamesDetail
	 */
	public NamesDetail getNamesEntity() {
		NamesDetail bean = namesDetailDao.getEntity("BMN", "1");
		return bean;
	}

	/**
	 * 分類マスタ検索
	 * @param dataType データ種別
	 * @param categoryDivision 分類コード
	 * @return ClassificationDetail
	 */
	public ClassificationDetail getClassificationEntity(
			final BigDecimal dataType, final String categoryDivision) {
		ClassificationDetail bean = classificationDetailDao.getEntity(dataType,
			categoryDivision, null);
		return bean;
	}

	/**
	 * 分類リスト取得
	 * @param dataType サイトデータ種別
	 * @param arDivision 売掛対象区分
	 * @return List<ClassificationListForComboboxes>
	 */
	public List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType, final BigDecimal arDivision) {
		List<ClassificationListForComboboxes> list = classificationListForComboboxesDao
				.getListForComboboxes(dataType, arDivision);
		return list;
	}

	// setter------------------------------------------------------------------------
	/**
	 * プロシージャ呼び出しDAOを設定します。
	 * @param procedureCallDao プロシージャ呼び出しDAO
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * venderDetailDaoを設定します。
	 * @param venderDetailDao venderDetailDao
	 */
	public void setVenderDetailDao(final VenderDetailDao venderDetailDao) {
		this.venderDetailDao = venderDetailDao;
	}

	/**
	 * depositCreditDaoを設定します。
	 * @param depositCreditDao depositCreditDao
	 */
	public void setDepositCreditDao(final DepositCreditDao depositCreditDao) {
		this.depositCreditDao = depositCreditDao;
	}

	/**
	 * creditDaoを設定します。
	 * @param creditDao creditDao
	 */
	public void setCreditDao(final CreditDao creditDao) {
		this.creditDao = creditDao;
	}

	/**
	 * accountsDetailDaoを設定します。
	 * @param accountsDetailDao accountsDetailDao
	 */
	public void setAccountsDetailDao(final AccountsDetailDao accountsDetailDao) {
		this.accountsDetailDao = accountsDetailDao;
	}

	/**
	 * companyBankListForComboboxesDaoを設定します。
	 * @param companyBankListForComboboxesDao companyBankListForComboboxesDao
	 */
	public void setCompanyBankListForComboboxesDao(
			final CompanyBankListForComboboxesDao companyBankListForComboboxesDao) {
		this.companyBankListForComboboxesDao = companyBankListForComboboxesDao;
	}

	/**
	 * namesDetailDaoを設定します。
	 * @param namesDetailDao namesDetailDao
	 */
	public void setNamesDetailDao(final NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
	}

	/**
	 * classificationDetailDaoを設定します。
	 * @param classificationDetailDao classificationDetailDao
	 */
	public void setClassificationDetailDao(
			final ClassificationDetailDao classificationDetailDao) {
		this.classificationDetailDao = classificationDetailDao;
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
