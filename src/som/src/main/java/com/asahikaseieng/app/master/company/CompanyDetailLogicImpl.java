/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.entity.master.company.CompanyDao;
import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisit;
import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisitDao;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetail;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetailDao;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 自社詳細ロジック 実装クラス.
 * @author t0011036
 */
public class CompanyDetailLogicImpl implements CompanyDetailLogic {

	private CompanyDao companyDao;

	private CompanyDetailDao companyDetailDao;

	private NumberChkdisitDao numberChkdisitDao;

	private BankDetailDao bankDetailDao;

	/**
	 * コンストラクタ.
	 */
	public CompanyDetailLogicImpl() {
	}

	/**
	 * 自社検索（登録用）
	 * @param companyCd 自社コード
	 * @return Company
	 * @throws NoDataException NoDataException
	 */
	public Company getEntity(final String companyCd) throws NoDataException {
		if (StringUtils.isEmpty(companyCd)) {
			throw new IllegalArgumentException("companyCd is empty");
		}

		Company bean = companyDao.getEntity(companyCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 自社検索（詳細用）
	 * @param companyCd 自社コード
	 * @return CompanyDetail
	 */
	public CompanyDetail getDetailEntity(final String companyCd) {
		CompanyDetail bean = companyDetailDao.getEntity(companyCd);
		return bean;
	}

	/**
	 * 銀行検索
	 * @param bankMasterCd 銀行マスタコード
	 * @return BankDetail
	 */
	public BankDetail getBankEntity(final String bankMasterCd) {
		BankDetail bean = bankDetailDao.getEntity(bankMasterCd);
		return bean;
	}

	/**
	 * 数値チェック検索（登録用）
	 * @param unitDivision 区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return NumberChkdisit
	 */
	public NumberChkdisit getNumberEntity(final String unitDivision,
			final String venderDivision, final String venderCd) {
		if (StringUtils.isEmpty(unitDivision)) {
			throw new IllegalArgumentException("unitDivision is empty");
		}

		if (StringUtils.isEmpty(venderDivision)) {
			throw new IllegalArgumentException("venderDivision is empty");
		}

		if (StringUtils.isEmpty(venderCd)) {
			throw new IllegalArgumentException("venderCd is empty");
		}

		NumberChkdisit bean = numberChkdisitDao.getEntity(unitDivision,
			venderDivision, venderCd);

		return bean;
	}

	/**
	 * 登録処理
	 * @param bean 登録データ
	 * @param beanTax 追加対象データ(消費税)
	 * @param beanRoundup 追加対象データ(端数)
	 * @param beanSales 追加対象データ(売上金額)
	 * @param beanPurchase 追加対象データ(仕入金額)
	 * @param beanSalesUnitprice 追加対象データ(売上単価)
	 * @param beanPurchaseUnitprice 追加対象データ(仕入単価)
	 * @param beanUnitprice 追加対象データ(単価)
	 * @param beanBlendQty 追加対象データ(配合量)
	 * @param beanMixRate 追加対象データ(配合率)
	 * @param beanAdj 追加対象データ(配合調整)
	 */
	public void regist(final Company bean, final NumberChkdisit beanTax,
			final NumberChkdisit beanRoundup, final NumberChkdisit beanSales,
			final NumberChkdisit beanPurchase,
			final NumberChkdisit beanSalesUnitprice,
			final NumberChkdisit beanPurchaseUnitprice,
			final NumberChkdisit beanUnitprice,
			final NumberChkdisit beanBlendQty,
			final NumberChkdisit beanMixRate, final NumberChkdisit beanAdj) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		/* 自社マスタ */
		if (bean.getUpdateDate() == null) {
			/* 追加処理 */
			insert(bean);
		} else {
			/* 更新処理 */
			update(bean);
		}

		/* 消費税 */
		if (beanTax != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanTax);
			} else {
				/* 更新処理 */
				updateNumber(beanTax);
			}
		}

		/* 端数 */
		if (beanRoundup != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanRoundup);
			} else {
				/* 更新処理 */
				updateNumber(beanRoundup);
			}
		}

		/* 売上金額 */
		if (beanSales != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanSales);
			} else {
				/* 更新処理 */
				updateNumber(beanSales);
			}
		}

		/* 仕入金額 */
		if (beanPurchase != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanPurchase);
			} else {
				/* 更新処理 */
				updateNumber(beanPurchase);
			}
		}

		/* 売上単価 */
		if (beanSalesUnitprice != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanSalesUnitprice);
			} else {
				/* 更新処理 */
				updateNumber(beanSalesUnitprice);
			}
		}

		/* 仕入単価 */
		if (beanPurchaseUnitprice != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanPurchaseUnitprice);
			} else {
				/* 更新処理 */
				updateNumber(beanPurchaseUnitprice);
			}
		}

		/* 単価 */
		if (beanUnitprice != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanUnitprice);
			} else {
				/* 更新処理 */
				updateNumber(beanUnitprice);
			}
		}

		/* 配合量 */
		if (beanBlendQty != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanBlendQty);
			} else {
				/* 更新処理 */
				updateNumber(beanBlendQty);
			}
		}

		/* 配合率 */
		if (beanMixRate != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanMixRate);
			} else {
				/* 更新処理 */
				updateNumber(beanMixRate);
			}
		}

		/* 配合調整 */
		if (beanAdj != null) {
			if (beanTax.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanAdj);
			} else {
				/* 更新処理 */
				updateNumber(beanAdj);
			}
		}
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Company bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			companyDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 更新登録(数値チェック)
	 * @param bean 登録データ
	 */
	public void updateNumber(final NumberChkdisit bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			numberChkdisitDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Company bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			companyDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 追加登録(数値チェック)
	 * @param bean 登録データ
	 */
	public void insertNumber(final NumberChkdisit bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			numberChkdisitDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * companyDaoを設定します。
	 * @param companyDao companyDao
	 */
	public void setCompanyDao(final CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * companyDetailDaoを設定します。
	 * @param companyDetailDao companyDetailDao
	 */
	public void setCompanyDetailDao(final CompanyDetailDao companyDetailDao) {
		this.companyDetailDao = companyDetailDao;
	}

	/**
	 * numberChkdisitDaoを設定します。
	 * @param numberChkdisitDao numberChkdisitDao
	 */
	public void setNumberChkdisitDao(final NumberChkdisitDao numberChkdisitDao) {
		this.numberChkdisitDao = numberChkdisitDao;
	}

	/**
	 * bankDetailDaoを設定します。
	 * @param bankDetailDao bankDetailDao
	 */
	public void setBankDetailDao(final BankDetailDao bankDetailDao) {
		this.bankDetailDao = bankDetailDao;
	}
}
