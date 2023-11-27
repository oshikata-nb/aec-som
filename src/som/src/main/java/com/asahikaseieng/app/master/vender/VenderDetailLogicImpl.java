/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.vender;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisit;
import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisitDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.companybank.CompanyBankListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.companybank.CompanyBankListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetailDao;
import com.asahikaseieng.dao.nonentity.master.areadetail.AreaDetail;
import com.asahikaseieng.dao.nonentity.master.areadetail.AreaDetailDao;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetail;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetailDao;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetailDao;
import com.asahikaseieng.dao.nonentity.master.caldetail.CalDetail;
import com.asahikaseieng.dao.nonentity.master.caldetail.CalDetailDao;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetailDao;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetailDao;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetailDao;
import com.asahikaseieng.dao.nonentity.master.venderclassdetail.VenderClassDetail;
import com.asahikaseieng.dao.nonentity.master.venderclassdetail.VenderClassDetailDao;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 取引先詳細ロジック 実装クラス.
 * @author t0011036
 */
public class VenderDetailLogicImpl implements VenderDetailLogic {

	private VenderDao venderDao;

	private VenderDetailDao venderDetailDao;

	private OrganizationDetailDao organizationDetailDao;

	private LoginDetailDao loginDetailDao;

	private AreaDetailDao areaDetailDao;

	private BumonDetailDao bumonDetailDao;

	private AccountsDetailDao accountsDetailDao;

	private CalDetailDao calDetailDao;

	private BankDetailDao bankDetailDao;

	private CompanyDetailDao companyDetailDao;

	private NumberChkdisitDao numberChkdisitDao;

	private CompanyBankListForComboboxesDao companyBankListForComboboxesDao;

	private ClassificationListForComboboxesDao classificationListForComboboxesDao;

	private VenderClassDetailDao venderClassDetailDao;

	private NamesListForComboboxesDao namesListForComboboxesDao;

	/**
	 * コンストラクタ.
	 */
	public VenderDetailLogicImpl() {
	}

	/**
	 * 取引先検索（登録用）
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return Vender
	 * @throws NoDataException NoDataException
	 */
	public Vender getEntity(final String venderDivision, final String venderCd)
			throws NoDataException {
		if (StringUtils.isEmpty(venderCd)) {
			throw new IllegalArgumentException("venderCd is empty");
		}

		Vender bean = venderDao.getEntity(venderCd, venderDivision);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 取引先検索（詳細用）
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return VenderDetail
	 */
	public VenderDetail getDetailEntity(final String venderDivision,
			final String venderCd) {
		VenderDetail bean = venderDetailDao.getEntity(venderDivision, venderCd);
		return bean;
	}

	/**
	 * 担当部署検索
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	public OrganizationDetail getOrganizationEntity(final String organizationCd) {
		OrganizationDetail bean = organizationDetailDao
				.getEntity(organizationCd);
		return bean;
	}

	/**
	 * メール送信元部署検索
	 * @param mailOrganizationCd メール送信元部署コード
	 * @return OrganizationDetail
	 */
	public OrganizationDetail getMailOrganizationEntity(final String mailOrganizationCd) {
		OrganizationDetail bean = organizationDetailDao
				.getEntity(mailOrganizationCd);
		return bean;
	}

	/**
	 * 営業担当者検索
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	public LoginDetail getLoginEntity(final String tantoCd) {
		LoginDetail bean = loginDetailDao.getEntity(tantoCd);
		return bean;
	}

	/**
	 * 地区検索
	 * @param areaCd 地区コード
	 * @return AreaDetail
	 */
	public AreaDetail getAreaEntity(final String areaCd) {
		AreaDetail bean = areaDetailDao.getEntity(areaCd);
		return bean;
	}

	/**
	 * 会計部門検索
	 * @param sectionCd 会計部門コード
	 * @return BumonDetail
	 */
	public BumonDetail getBumonEntity(final String sectionCd) {
		BumonDetail bean = bumonDetailDao.getEntity(sectionCd);
		return bean;
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
	 * カレンダー検索
	 * @param calCd カレンダーコード
	 * @return CalDetail
	 */
	public CalDetail getCalEntity(final String calCd) {
		CalDetail bean = calDetailDao.getEntity(calCd);
		return bean;
	}

	/**
	 * 銀行検索
	 * @param bankMasterCd 銀行マスターコード
	 * @return BankDetail
	 */
	public BankDetail getBankEntity(final String bankMasterCd) {
		BankDetail bean = bankDetailDao.getEntity(bankMasterCd);
		return bean;
	}

	/**
	 * 自社検索
	 * @param companyCd 自社コード
	 * @return CompanyDetail
	 */
	public CompanyDetail getCompanyEntity(final String companyCd) {
		CompanyDetail bean = companyDetailDao.getEntity(companyCd);
		return bean;
	}

	/**
	 * 数値チェック検索
	 * @param unitDivision 区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return NumberChkdisit
	 */
	public NumberChkdisit getNumberEntity(final String unitDivision,
			final String venderDivision, final String venderCd) {
		NumberChkdisit bean = numberChkdisitDao.getEntity(unitDivision,
			venderCd, venderDivision);
		return bean;
	}

	/**
	 * 取引先更新登録
	 * @param bean 登録データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	public void update(final Vender bean) throws NoDataException,
			IllegalAccessException, InvocationTargetException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			venderDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}

		try {
			/* 数値チェック登録 */
			registNumberChkdisit(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 数値チェック登録
	 * @param bean 登録データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	public void registNumberChkdisit(final Vender bean) throws NoDataException,
			IllegalAccessException, InvocationTargetException {
		String unitDivision = null;

		unitDivision = "TAX_AMOUNT";

		if (bean.getTaxRoundup().equals(new BigDecimal("4"))
				|| bean.getTaxRoundupUnit().equals(new BigDecimal("8"))) {
			/* 自社マスタ選択 */
			NumberChkdisit beanNumber = numberChkdisitDao.getEntity(
				unitDivision, bean.getVenderCd(), bean.getVenderDivision());

			if (beanNumber != null) {
				deleteNumberChkdisit(beanNumber);
			}
		} else {
			/* 消費税数値チェック更新 */
			setNumberChkdisit(unitDivision, bean.getTaxRoundup(), bean
					.getTaxRoundupUnit(), bean.getVenderDivision(), bean
					.getVenderCd(), bean.getUpdatorCd(), bean.getUpdateDate());
		}

		unitDivision = "SONOTA";

		if (bean.getRoundup().equals(new BigDecimal("4"))
				|| bean.getRoundupUnit().equals(new BigDecimal("8"))) {
			/* 自社マスタ選択 */
			NumberChkdisit beanNumber = numberChkdisitDao.getEntity(
				unitDivision, bean.getVenderCd(), bean.getVenderDivision());

			if (beanNumber != null) {
				deleteNumberChkdisit(beanNumber);
			}
		} else {
			/* 端数数値チェック更新 */
			setNumberChkdisit(unitDivision, bean.getRoundup(), bean
					.getRoundupUnit(), bean.getVenderDivision(), bean
					.getVenderCd(), bean.getUpdatorCd(), bean.getUpdateDate());
		}

		if (bean.getVenderDivision().equals("TS")) {
			unitDivision = "URKINGAKU";
		} else {
			unitDivision = "SIKINGAKU";
		}

		if (bean.getSalesPurchaseRoundup().equals(new BigDecimal("4"))
				|| bean.getSalesPurchaseRoundupUnit().equals(
					new BigDecimal("8"))) {
			/* 自社マスタ選択 */
			NumberChkdisit beanNumber = numberChkdisitDao.getEntity(
				unitDivision, bean.getVenderCd(), bean.getVenderDivision());

			if (beanNumber != null) {
				deleteNumberChkdisit(beanNumber);
			}
		} else {
			/* 売上(仕入)金額数値チェック更新 */
			setNumberChkdisit(unitDivision, bean.getSalesPurchaseRoundup(),
				bean.getSalesPurchaseRoundupUnit(), bean.getVenderDivision(),
				bean.getVenderCd(), bean.getUpdatorCd(), bean.getUpdateDate());
		}

		if (bean.getVenderDivision().equals("TS")) {
			unitDivision = "URTANKA";
		} else {
			unitDivision = "SITANKA";
		}

		if (bean.getUnitpriceRoundup().equals(new BigDecimal("4"))
				|| bean.getUnitpriceRoundupUnit().equals(new BigDecimal("8"))) {
			/* 自社マスタ選択 */
			NumberChkdisit beanNumber = numberChkdisitDao.getEntity(
				unitDivision, bean.getVenderCd(), bean.getVenderDivision());

			if (beanNumber != null) {
				deleteNumberChkdisit(beanNumber);
			}
		} else {
			/* 売上(仕入)単価数値チェック更新 */
			setNumberChkdisit(unitDivision, bean.getUnitpriceRoundup(), bean
					.getUnitpriceRoundupUnit(), bean.getVenderDivision(), bean
					.getVenderCd(), bean.getUpdatorCd(), bean.getUpdateDate());
		}

		unitDivision = "TANKA";

		if (bean.getUnitpriceRoundup().equals(new BigDecimal("4"))
				|| bean.getUnitpriceRoundupUnit().equals(new BigDecimal("8"))) {
			/* 自社マスタ選択 */
			NumberChkdisit beanNumber = numberChkdisitDao.getEntity(
				unitDivision, bean.getVenderCd(), bean.getVenderDivision());

			if (beanNumber != null) {
				deleteNumberChkdisit(beanNumber);
			}
		} else {
			/* 単価数値チェック更新 */
			setNumberChkdisit(unitDivision, bean.getUnitpriceRoundup(), bean
					.getUnitpriceRoundupUnit(), bean.getVenderDivision(), bean
					.getVenderCd(), bean.getUpdatorCd(), bean.getUpdateDate());
		}
	}

	/**
	 * 数値チェック更新登録
	 * @param bean 登録データ
	 */
	public void updateNumberChkdisit(final NumberChkdisit bean) {
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
	 * 数値チェック登録
	 * @param unitDivision 区分
	 * @param roundup 端数区分
	 * @param roundupUnit 端数単位
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @param tantoCd 担当者コード
	 * @param updateDate 更新日時
	 * @throws NoDataException NoDataException
	 */
	public void setNumberChkdisit(final String unitDivision,
			final BigDecimal roundup, final BigDecimal roundupUnit,
			final String venderDivision, final String venderCd,
			final String tantoCd, final Timestamp updateDate)
			throws NoDataException {
		BigDecimal integerLength = null;
		BigDecimal smallnumLength = null;
		BigDecimal upperLimit = null;
		String strUpperLimit = "";

		/* 消費税数値チェック更新 */
		if (roundup.equals(new BigDecimal("4"))) {
			/* 自社マスタ選択 */
			NumberChkdisit beanNumber = new NumberChkdisit();

			beanNumber.setUnitDivision(unitDivision);
			beanNumber.setVenderDivision(venderDivision);
			beanNumber.setVenderCd(venderCd);
			beanNumber.setUpdateDate(updateDate);

			deleteNumberChkdisit(beanNumber);
		} else {
			if (roundupUnit.equals(new BigDecimal("1"))) {
				integerLength = new BigDecimal("22");
				smallnumLength = new BigDecimal("0");
			} else {
				integerLength = new BigDecimal("22").subtract(roundupUnit);
				smallnumLength = roundupUnit.subtract(new BigDecimal("1"));
			}

			if (smallnumLength.equals(new BigDecimal("0"))) {
				for (int i = 0; i < integerLength.intValue(); i++) {
					strUpperLimit = strUpperLimit + "9";
				}
			} else {
				for (int i = 0; i < integerLength.intValue(); i++) {
					strUpperLimit = strUpperLimit + "9";
				}

				strUpperLimit = strUpperLimit + ".";

				for (int i = 0; i < smallnumLength.intValue(); i++) {
					strUpperLimit = strUpperLimit + "9";
				}
			}

			upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

			NumberChkdisit beanNumber = getNumberEntity(unitDivision,
				venderDivision, venderCd);

			if (beanNumber == null) {
				NumberChkdisit beanInsert = new NumberChkdisit();

				beanInsert.setUnitDivision(unitDivision);
				beanInsert.setVenderDivision(venderDivision);
				beanInsert.setVenderCd(venderCd);
				beanInsert.setMaxLength(new BigDecimal("22"));
				beanInsert.setIntegerLength(integerLength);
				beanInsert.setSmallnumLength(smallnumLength);
				beanInsert.setRoundDivision(roundup);
				beanInsert.setLowerLimit(new BigDecimal("0"));
				beanInsert.setUpperLimit(upperLimit);
				beanInsert.setInputDate(beanInsert.getCurrentTimestamp());
				beanInsert.setInputorCd(tantoCd);
				beanInsert.setUpdatorCd(tantoCd);

				/* 追加処理 */
				insertNumberChkdisit(beanInsert);
			} else {
				beanNumber.setMaxLength(new BigDecimal("22"));
				beanNumber.setIntegerLength(integerLength);
				beanNumber.setSmallnumLength(smallnumLength);
				beanNumber.setRoundDivision(roundup);
				beanNumber.setLowerLimit(new BigDecimal("0"));
				beanNumber.setUpperLimit(upperLimit);
				beanNumber.setUpdatorCd(tantoCd);

				/* 更新処理 */
				updateNumberChkdisit(beanNumber);
			}
		}
	}

	/**
	 * 取引先追加登録
	 * @param bean 登録データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	public void insert(final Vender bean) throws NoDataException,
			IllegalAccessException, InvocationTargetException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			venderDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}

		try {
			/* 数値チェック登録 */
			registNumberChkdisit(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 数値チェック追加登録
	 * @param bean 登録データ
	 */
	public void insertNumberChkdisit(final NumberChkdisit bean) {
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

	/**
	 * 取引先削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	public void delete(final Vender bean) throws NoDataException,
			IllegalAccessException, InvocationTargetException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			venderDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}

		bean.setTaxRoundup(new BigDecimal("4"));
		bean.setRoundup(new BigDecimal("4"));
		bean.setSalesPurchaseRoundup(new BigDecimal("4"));
		bean.setUnitpriceRoundup(new BigDecimal("4"));

		/* 数値チェック削除 */
		registNumberChkdisit(bean);
	}

	/**
	 * 数値チェック削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void deleteNumberChkdisit(final NumberChkdisit bean)
			throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			numberChkdisitDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
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

	/**
	 * 手形区分検索
	 * @param dataType データ種別
	 * @param categoryDivision 区分
	 * @return VenderClass
	 */
	public VenderClassDetail getVenderClassEntity(final BigDecimal dataType,
			final String categoryDivision) {
		VenderClassDetail bean = venderClassDetailDao.getEntity(dataType,
			categoryDivision);
		return bean;
	}

	/**
	 * 売上インボイスパターンリスト取得
	 * @return List<NamesListForComboboxes>
	 */
	public List<NamesListForComboboxes> getTsInvoicePtnList() {
		List<NamesListForComboboxes> list = namesListForComboboxesDao.getListForComboboxes("TSIV");
		return list;
	}

	/**
	 * 仕入インボイスパターンリスト取得
	 * @return List<NamesListForComboboxes>
	 */
	public List<NamesListForComboboxes> getSiInvoicePtnList() {
		List<NamesListForComboboxes> list = namesListForComboboxesDao.getListForComboboxes("SIIV");
		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * venderDetailDaoを設定します。
	 * @param venderDetailDao venderDetailDao
	 */
	public void setVenderDetailDao(final VenderDetailDao venderDetailDao) {
		this.venderDetailDao = venderDetailDao;
	}

	/**
	 * accountsDetailDaoを設定します。
	 * @param accountsDetailDao accountsDetailDao
	 */
	public void setAccountsDetailDao(final AccountsDetailDao accountsDetailDao) {
		this.accountsDetailDao = accountsDetailDao;
	}

	/**
	 * areaDetailDaoを設定します。
	 * @param areaDetailDao areaDetailDao
	 */
	public void setAreaDetailDao(final AreaDetailDao areaDetailDao) {
		this.areaDetailDao = areaDetailDao;
	}

	/**
	 * bumonDetailDaoを設定します。
	 * @param bumonDetailDao bumonDetailDao
	 */
	public void setBumonDetailDao(final BumonDetailDao bumonDetailDao) {
		this.bumonDetailDao = bumonDetailDao;
	}

	/**
	 * calDetailDaoを設定します。
	 * @param calDetailDao calDetailDao
	 */
	public void setCalDetailDao(final CalDetailDao calDetailDao) {
		this.calDetailDao = calDetailDao;
	}

	/**
	 * loginDetailDaoを設定します。
	 * @param loginDetailDao loginDetailDao
	 */
	public void setLoginDetailDao(final LoginDetailDao loginDetailDao) {
		this.loginDetailDao = loginDetailDao;
	}

	/**
	 * organizationDetailDaoを設定します。
	 * @param organizationDetailDao organizationDetailDao
	 */
	public void setOrganizationDetailDao(
			final OrganizationDetailDao organizationDetailDao) {
		this.organizationDetailDao = organizationDetailDao;
	}

	/**
	 * bankDetailDaoを設定します。
	 * @param bankDetailDao bankDetailDao
	 */
	public void setBankDetailDao(final BankDetailDao bankDetailDao) {
		this.bankDetailDao = bankDetailDao;
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
	 * companyBankListForComboboxesDaoを設定します。
	 * @param companyBankListForComboboxesDao companyBankListForComboboxesDao
	 */
	public void setCompanyBankListForComboboxesDao(
			final CompanyBankListForComboboxesDao companyBankListForComboboxesDao) {
		this.companyBankListForComboboxesDao = companyBankListForComboboxesDao;
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
	 * venderClassDetailDaoを設定します。
	 * @param venderClassDetailDao venderClassDetailDao
	 */
	public void setVenderClassDetailDao(
			final VenderClassDetailDao venderClassDetailDao) {
		this.venderClassDetailDao = venderClassDetailDao;
	}

	/**
	 * namesListForComboboxesDaoを設定します。
	 * @param namesListForComboboxesDao namesListForComboboxesDao
	 */
	public void setNamesListForComboboxesDao(
			NamesListForComboboxesDao namesListForComboboxesDao) {
		this.namesListForComboboxesDao = namesListForComboboxesDao;
	}
}
