/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.offset;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.offsetgroupdata.OffsetGroupData;
import com.asahikaseieng.dao.entity.offsetgroupdata.OffsetGroupDataDao;
import com.asahikaseieng.dao.entity.offsetgroupheader.OffsetGroupHeader;
import com.asahikaseieng.dao.entity.offsetgroupheader.OffsetGroupHeaderDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetailDao;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetailDao;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetDeposit;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetDepositDao;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetDetail;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetDetailDao;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetPayable;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetPayableDao;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetTranData;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetTranDataDao;
import com.asahikaseieng.dao.nonentity.procedurecall.FunGetSlipNoCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 
 * OffsetListLogicImplクラス.グループ間相殺入力一覧
 * @author tosco
 */
public class OffsetDetailLogicImpl implements OffsetDetailLogic {

	private OffsetDetailDao offsetDetailDao;

	private OffsetDepositDao offsetDepositDao;

	private OffsetPayableDao offsetPayableDao;

	private OffsetTranDataDao offsetTranDataDao;

	private ClassificationDetailDao classificationDetailDao;

	private OrganizationDetailDao organizationDetailDao;

	private OffsetGroupHeaderDao offsetGroupHeaderDao;

	private OffsetGroupDataDao offsetGroupDataDao;

	private NamesDetailDao namesDetailDao;

	private ClassificationListForComboboxesDao classificationListForComboboxesDao;

	/** プロシージャ呼び出しDAO */
	private ProcedureCallDao procedureCallDao;

	/** データ種別['5'：グループ間相殺] */
	private static final BigDecimal DATA_TYPE = new BigDecimal(5);

	/** データ集計区分['1'：グループ間相殺] */
	private static final BigDecimal DATA_TYPE_DIVI = BigDecimal.ONE;

	/** 対象フラグ['0'：未処理]、更新フラグ['0'：未処理] */
	private static final BigDecimal UPDATE_MI = BigDecimal.ZERO;

	/** 取引先区分 得意先 'TS' */
	private static final String VENDER_DIVISION_TS = "TS";

	/** 取引先区分 仕入先 'SI' */
	private static final String VENDER_DIVISION_SI = "SI";

	/**
	 * コンストラクタ.グループ間相殺入力一覧
	 */
	public OffsetDetailLogicImpl() {
	}

	/**
	 * 検索処理を行う.グループ間相殺入力
	 * 
	 * @param offsetNo offsetNo
	 * @return List<OffsetDetail> 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public OffsetDetail getOffsetHeader(final String offsetNo)
			throws NoDataException {

		final OffsetDetail bean = offsetDetailDao.getOffsetHeader(offsetNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 検索処理を行う.グループ間相殺入力一覧
	 * @param offsetNo 相殺番号
	 * @return List<OffsetTranData> 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<OffsetTranData> getDetailData(final String offsetNo)
			throws NoDataException {

		List<OffsetTranData> list = offsetTranDataDao.getDetailData(offsetNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 更新処理を行う.
	 * @param hedBean 相殺ヘッダービーン
	 * @param payableList 買掛リスト
	 * @param depositList 売掛リスト
	 * @throws NoDataException データ無し例外
	 */
	public void update(final OffsetDetail hedBean,
			final List<OffsetPayable> payableList,
			final List<OffsetDeposit> depositList) throws NoDataException {

		if (hedBean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 相殺ヘッダー更新 */
			int updateNum = offsetDetailDao.update(hedBean);

			if (updateNum != 1) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}

			OffsetTranData bean = null;
			NamesDetail beanName = null;

			/* 買掛データから相殺トランザクションに更新 */
			for (OffsetPayable payableBean : payableList) {
				// 相殺トランザクションを検索
				OffsetTranData updateBean = offsetTranDataDao.getEntity(hedBean
						.getOffsetNo(), VENDER_DIVISION_SI, payableBean
						.getSupplierCd());

				if (updateBean == null) {
					/* データは削除されています。 */
					throw new NoDataException();
				}

				/* 変更部分の設定 */
				updateBean.setOffsetDate(hedBean.getOffsetDate()); // 相殺日付
				// 買掛相殺金額
				updateBean.setPayableOffsetAmount(AecNumberUtils
						.convertBigDecimal(payableBean.getStrPayableOffset()));
				updateBean.setUpdateDate(hedBean.getUpdateDate()); // 更新日付
				updateBean.setUpdatorCd(hedBean.getUpdatorCd()); // 更新者

				/* 取引先部署取得 */
				bean = getVenderOrganization(VENDER_DIVISION_SI, payableBean
						.getSupplierCd());

				if (bean != null) {
					if (!StringUtils.isEmpty(bean.getOrganizationCd())) {
						updateBean.setOrganizationCd(bean.getOrganizationCd());
					}

					updateBean.setDebitSectionCd(bean.getSectionCd());

					/* 名称マスタから全社共通部門コードを取得 */
					beanName = getNamesEntity();

					if (beanName == null) {
						updateBean.setCreditSectionCd(null);
					} else {
						updateBean.setCreditSectionCd(beanName.getName01());
					}
				}

				updateNum = offsetTranDataDao.update(updateBean);

				if (updateNum != 1) {
					/* 排他エラー */
					throw new OptimisticLockRuntimeException();
				}

			}

			/* 売掛データから相殺トランザクションに更新 */
			for (OffsetDeposit depositBean : depositList) {
				// 相殺トランザクションを検索
				OffsetTranData updateBean = offsetTranDataDao.getEntity(hedBean
						.getOffsetNo(), VENDER_DIVISION_TS, depositBean
						.getCustomerCd());

				if (updateBean == null) {
					/* データは削除されています。 */
					throw new NoDataException();
				}

				/* 変更部分の設定 */
				updateBean.setOffsetDate(hedBean.getOffsetDate()); // 相殺日付
				// 売掛相殺金額
				updateBean.setDepositOffsetAmount(AecNumberUtils
						.convertBigDecimal(depositBean.getStrDepositOffset()));
				updateBean.setUpdateDate(hedBean.getUpdateDate()); // 更新日付
				updateBean.setUpdatorCd(hedBean.getUpdatorCd()); // 更新者

				// 入金消込残（売掛相殺金額を設定する）
				updateBean.setCreditEraserAmount(AecNumberUtils
						.convertBigDecimal(depositBean.getStrDepositOffset()));

				/* 取引先部署取得 */
				bean = getVenderOrganization(VENDER_DIVISION_TS, depositBean
						.getCustomerCd());

				if (bean != null) {
					if (!StringUtils.isEmpty(bean.getOrganizationCd())) {
						updateBean.setOrganizationCd(bean.getOrganizationCd());
					}

					/* 名称マスタから全社共通部門コードを取得 */
					beanName = getNamesEntity();

					if (beanName == null) {
						updateBean.setDebitSectionCd(null);
					} else {
						updateBean.setDebitSectionCd(beanName.getName01());
					}

					updateBean.setCreditSectionCd(bean.getSectionCd());
				}

				updateNum = offsetTranDataDao.update(updateBean);

				if (updateNum != 1) {
					/* 排他エラー */
					throw new OptimisticLockRuntimeException();
				}

			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 更新エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 追加処理を行う.
	 * @param categoryDivision 分類区分
	 * @param hedBean 相殺ヘッダービーン
	 * @param payableList 買掛リスト
	 * @param depositList 売掛リスト
	 * @throws NoDataException データなし例外
	 */
	public void insert(final String categoryDivision,
			final OffsetDetail hedBean, final List<OffsetPayable> payableList,
			final List<OffsetDeposit> depositList) throws NoDataException {
		int insertNum;

		if (hedBean == null) {
			throw new IllegalArgumentException("hedBean == null");
		}

		try {
			// 部署データ取得
			OrganizationDetail orgData = organizationDetailDao
					.getEntity(hedBean.getOrganizationCd());

			// 分類データ取得
			ClassificationDetail classificationData = classificationDetailDao
					.getEntity(DATA_TYPE, categoryDivision, null);

			// 相殺番号取得(FUNCTION呼出)
			// String offsetNo = callFunction(hedBean.getInputorCd());
			hedBean.setOffsetNo(hedBean.getOffsetNo());

			/* 相殺ヘッダー追加処理 */
			insertNum = offsetDetailDao.insert(hedBean);

			if (insertNum != 1) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}

			List<OffsetTranData> offsetTranList = new ArrayList<OffsetTranData>();

			OffsetTranData bean = null;
			NamesDetail beanName = null;

			/* 買掛データを相殺トランザクション用に設定 */
			for (OffsetPayable payableBean : payableList) {
				/* 買掛、売掛共通の設定を行う */
				OffsetTranData newTranBean = getKoteiData(hedBean, orgData,
					classificationData);

				// 入金消込残
				newTranBean.setCreditEraserAmount(BigDecimal.ZERO);
				// 取引先区分
				newTranBean.setVenderDivision(VENDER_DIVISION_SI);
				// 請求先/支払先コード
				newTranBean.setVenderCd(payableBean.getSupplierCd());
				// 買掛相殺金額
				newTranBean.setPayableOffsetAmount(AecNumberUtils
						.convertBigDecimal(payableBean.getStrPayableOffset()));
				// 売掛相殺金額
				newTranBean.setDepositOffsetAmount(BigDecimal.ZERO);

				/* 取引先部署取得 */
				bean = getVenderOrganization(VENDER_DIVISION_SI, payableBean
						.getSupplierCd());

				if (bean != null) {
					if (!StringUtils.isEmpty(bean.getOrganizationCd())) {
						newTranBean.setOrganizationCd(bean.getOrganizationCd());
					}

					newTranBean.setDebitSectionCd(bean.getSectionCd());

					/* 名称マスタから全社共通部門コードを取得 */
					beanName = getNamesEntity();

					if (beanName == null) {
						newTranBean.setCreditSectionCd(null);
					} else {
						newTranBean.setCreditSectionCd(beanName.getName01());
					}
				}

				offsetTranList.add(newTranBean);
			}

			/* 売掛データを相殺トランザクション用に設定 */
			for (OffsetDeposit depositBean : depositList) {
				/* 買掛、売掛共通の設定を行う */
				OffsetTranData newTranBean = getKoteiData(hedBean, orgData,
					classificationData);

				// 入金消込残（売掛相殺金額を設定する）
				newTranBean.setCreditEraserAmount(AecNumberUtils
						.convertBigDecimal(depositBean.getStrDepositOffset()));
				// 取引先区分
				newTranBean.setVenderDivision(VENDER_DIVISION_TS);
				// 請求先/支払先コード
				newTranBean.setVenderCd(depositBean.getCustomerCd());
				// 買掛相殺金額
				newTranBean.setPayableOffsetAmount(BigDecimal.ZERO);
				// 売掛相殺金額
				newTranBean.setDepositOffsetAmount(AecNumberUtils
						.convertBigDecimal(depositBean.getStrDepositOffset()));

				/* 取引先部署取得 */
				bean = getVenderOrganization(VENDER_DIVISION_TS, depositBean
						.getCustomerCd());

				if (bean != null) {
					if (!StringUtils.isEmpty(bean.getOrganizationCd())) {
						newTranBean.setOrganizationCd(bean.getOrganizationCd());
					}

					/* 名称マスタから全社共通部門コードを取得 */
					beanName = getNamesEntity();

					if (beanName == null) {
						newTranBean.setDebitSectionCd(null);
					} else {
						newTranBean.setDebitSectionCd(beanName.getName01());
					}

					newTranBean.setCreditSectionCd(bean.getSectionCd());
				}

				offsetTranList.add(newTranBean);
			}

			/* 作成したトランザクションデータ分insertしていく */
			for (OffsetTranData offsetTranBean : offsetTranList) {
				/* 追加処理 */
				insertNum = offsetTranDataDao.insert(offsetTranBean);

				if (insertNum != 1) {
					/* 排他エラー */
					throw new OptimisticLockRuntimeException();
				}
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 更新エラー */
			throw new NoDataException();
		}

	}

	/**
	 * 削除処理を行う.
	 * @param hedBean 相殺ヘッダービーン
	 * @param payableList 買掛リスト
	 * @param depositList 売掛リスト
	 * @throws NoDataException データ無し例外
	 */
	public void delete(final OffsetDetail hedBean,
			final List<OffsetPayable> payableList,
			final List<OffsetDeposit> depositList) throws NoDataException {

		if (hedBean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		/* 削除処理 */
		int deleteNum = offsetDetailDao.delete(hedBean);

		if (deleteNum != 1) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}

		/* 買掛データから相殺トランザクションを削除 */
		for (OffsetPayable payableBean : payableList) {

			// 相殺トランザクションを検索
			OffsetTranData updateBean = offsetTranDataDao.getEntity(hedBean
					.getOffsetNo(), VENDER_DIVISION_SI, payableBean
					.getSupplierCd());

			deleteNum = offsetTranDataDao.delete(updateBean);

			if (deleteNum != 1) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}
		}

		/* 売掛データから相殺トランザクションを削除 */
		for (OffsetDeposit depositBean : depositList) {
			// 相殺トランザクションを検索
			OffsetTranData updateBean = offsetTranDataDao.getEntity(hedBean
					.getOffsetNo(), VENDER_DIVISION_TS, depositBean
					.getCustomerCd());

			deleteNum = offsetTranDataDao.delete(updateBean);

			if (deleteNum != 1) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}
		}
	}

	/**
	 * 売掛リスト検索処理を行う.グループ間相殺入力
	 * 
	 * @param sectionCd 部門コード
	 * @param offsetGroupCd 相殺グループコード
	 * @param creditFlg 売上科目区分
	 * @return List<OffsetDeposit> 買掛リスト
	 * @throws NoDataException NoDataException
	 */
	public List<OffsetDeposit> getDepositNew(final String sectionCd,
			final String offsetGroupCd, final BigDecimal creditFlg)
			throws NoDataException {

		final List<OffsetDeposit> bean = offsetDepositDao.getDepositNew(
			sectionCd, offsetGroupCd, creditFlg);

		return bean;
	}

	/**
	 * 売掛リスト検索処理を行う.グループ間相殺入力一覧
	 * 
	 * @param organizationCd 部署コード
	 * @param offsetGroupCd 相殺グループコード
	 * @param payableFlg 仕入科目区分
	 * @return List<OffsetPayable> 売掛リスト
	 * @throws NoDataException NoDataException
	 */
	public List<OffsetPayable> getPayableNew(final String organizationCd,
			final String offsetGroupCd, final BigDecimal payableFlg)
			throws NoDataException {

		final List<OffsetPayable> bean = offsetPayableDao.getPayableNew(
			organizationCd, offsetGroupCd, payableFlg);

		return bean;
	}

	/**
	 * 売掛リスト検索処理を行う.グループ間相殺入力
	 * @param organizationCd 部署コード
	 * @param venderCd 請求先コード
	 * @param depositDate 売掛締め日
	 * @param creditFlg creditFlg
	 * @return OffsetDeposit 売掛リスト
	 * @throws NoDataException NoDataException
	 */
	public OffsetDeposit getDeposit(final String organizationCd,
			final String venderCd, final Date depositDate,
			final BigDecimal creditFlg) throws NoDataException {

		OffsetDeposit bean = offsetDepositDao.getDeposit(organizationCd,
			venderCd, depositDate, creditFlg);

		return bean;
	}

	/**
	 * 買掛データ検索処理を行う.グループ間相殺入力
	 * @param sectionCd 部門コード
	 * @param venderCd 支払先コード
	 * @param payableDate 買掛締め日
	 * @param payableFlg payableFlg
	 * @return OffsetPayable 買掛データ
	 * @throws NoDataException NoDataException
	 */
	public OffsetPayable getPayable(final String sectionCd,
			final String venderCd, final Date payableDate,
			final BigDecimal payableFlg) throws NoDataException {

		OffsetPayable bean = offsetPayableDao.getPayable(sectionCd, venderCd,
			payableDate, payableFlg);

		return bean;
	}

	/**
	 * 相殺トランザクションデータ（固定データ）設定
	 * 
	 * @param hedBean 相殺ヘッダー
	 * @param orgData 部署データ
	 * @param classificationData 分類データ
	 * @param newTranBean 新起用に設定した
	 */
	private OffsetTranData getKoteiData(final OffsetDetail hedBean,
			final OrganizationDetail orgData,
			final ClassificationDetail classificationData) {
		OffsetTranData newTranBean = new OffsetTranData();

		// ﾃﾞｰﾀ種別
		newTranBean.setDataType(DATA_TYPE);
		// 分類コード
		newTranBean.setCategoryDivi(classificationData.getCategoryDivision());
		// データ集計区分
		newTranBean.setDataTypeDivi(DATA_TYPE_DIVI);

		// 対象フラグ（'0'：未処理）
		newTranBean.setDepositUpdateDivi(UPDATE_MI);
		newTranBean.setClaimUpdateDivi(UPDATE_MI);
		newTranBean.setPayableUpdateDivi(UPDATE_MI);
		newTranBean.setPaymentUpdateDivi(UPDATE_MI);

		// 相殺番号（ヘッダーと同じ）
		newTranBean.setOffsetNo(hedBean.getOffsetNo());
		// 部署コード
		newTranBean.setOrganizationCd(hedBean.getOrganizationCd());
		// 部門コード
		// newTranBean.setDebitSectionCd(hedBean.getOrganizationCd());
		// newTranBean.setCreditSectionCd(hedBean.getOrganizationCd());

		// 相殺グループコード
		newTranBean.setOffsetGroupCd(hedBean.getOffsetGroupCd());
		// 相殺締め日
		newTranBean.setOffsetDate(hedBean.getOffsetDate());

		// 登録日
		newTranBean.setInputDate(hedBean.getInputDate());
		// 登録者
		newTranBean.setInputorCd(hedBean.getInputorCd());
		// 更新日
		newTranBean.setUpdateDate(hedBean.getUpdateDate());
		// 更新者
		newTranBean.setUpdatorCd(hedBean.getUpdatorCd());

		// 借方科目コード
		newTranBean.setDebitTitleCd(classificationData.getDebitAccountsCd());
		// 借方補助科目コード
		newTranBean.setDebitSubTitleCd(classificationData
				.getDebitAccountsSubCd());
		// 貸方科目コード
		newTranBean.setCreditTitleCd(classificationData.getCreditAccountsCd());
		// 貸方補助科目コード
		newTranBean.setCreditSubTitleCd(classificationData
				.getCreditAccountsSubCd());

		// 更新フラグ（'0'：未処理）
		newTranBean.setDepositTargetDivi(UPDATE_MI);
		newTranBean.setClaimTargetDivi(UPDATE_MI);
		newTranBean.setPayableTargetDivi(UPDATE_MI);
		newTranBean.setPaymentTargetDivi(UPDATE_MI);

		// 最後のほう
		newTranBean.setEraserCompleteDivi(UPDATE_MI);
		newTranBean.setApprovalStatus(BigDecimal.ONE);
		newTranBean.setEraserAmount(BigDecimal.ZERO);

		return newTranBean;
	}

	/**
	 * 相殺番号取得
	 * 
	 * @param updatorCd 更新者ID
	 * @return String 相殺番号
	 * @throws NoDataException データ無し例外
	 */
	public String callFunction(final String updatorCd) throws NoDataException {
		FunGetSlipNoCallDto funDto = new FunGetSlipNoCallDto();
		funDto.setPStrUpdatorCd(updatorCd);

		// 相殺番号取得FUNCTION呼出
		procedureCallDao.funGetOffsetNo(funDto);

		if (StringUtils.isEmpty(funDto.getPStrSlipNo())) {
			// 相殺番号取得エラー
			throw new NoDataException();
		}
		return funDto.getPStrSlipNo();
	}

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	public void statusUpdate(final OffsetDetailForm frm,
			final BigDecimal status, final String tantoCd)
			throws NoDataException {
		/* 相殺グループヘッダー更新 */
		OffsetGroupHeader beanHeader = getOffsetGroupHeaderEntity(frm
				.getOffsetNo());

		beanHeader.setApprovalStatus(status);
		beanHeader.setUpdatorCd(tantoCd);

		if (status.equals(new BigDecimal("3"))) {
			beanHeader.setApprovaldate(AecDateUtils.getCurrentTimestamp());
			beanHeader.setApprovedby(tantoCd);
		} else {
			beanHeader.setApprovaldate(null);
			beanHeader.setApprovedby(null);
		}

		/* 登録処理 */
		try {
			/* 更新処理 */
			offsetGroupHeaderDao.update(beanHeader);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}

		/* 相殺トランザクション更新 */
		for (int i = 0; i < frm.getPayableList().size(); i++) {
			OffsetGroupData beanData = getOffsetGroupDataEntity(frm
					.getOffsetNo(), VENDER_DIVISION_SI, frm.getPayableList()
					.get(i).getSupplierCd());

			beanData.setApprovalStatus(status);
			beanData.setUpdatorCd(tantoCd);

			if (status.equals(new BigDecimal("3"))) {
				beanData.setApprovaldate(AecDateUtils.getCurrentTimestamp());
				beanData.setApprovedby(tantoCd);
			} else {
				beanData.setApprovaldate(null);
				beanData.setApprovedby(null);
			}

			/* 登録処理 */
			try {
				/* 更新処理 */
				offsetGroupDataDao.update(beanData);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}
		}

		for (int i = 0; i < frm.getDepositList().size(); i++) {
			OffsetGroupData beanData = getOffsetGroupDataEntity(frm
					.getOffsetNo(), VENDER_DIVISION_TS, frm.getDepositList()
					.get(i).getCustomerCd());

			beanData.setApprovalStatus(status);
			beanData.setUpdatorCd(tantoCd);

			if (status.equals(new BigDecimal("3"))) {
				beanData.setApprovaldate(AecDateUtils.getCurrentTimestamp());
				beanData.setApprovedby(tantoCd);
			} else {
				beanData.setApprovaldate(null);
				beanData.setApprovedby(null);
			}

			/* 登録処理 */
			try {
				/* 更新処理 */
				offsetGroupDataDao.update(beanData);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}
		}
	}

	/**
	 * 相殺グループヘッダー検索（登録用）
	 * @param offsetNo 相殺番号
	 * @return OffsetGroupHeader
	 * @throws NoDataException NoDataException
	 */
	public OffsetGroupHeader getOffsetGroupHeaderEntity(final String offsetNo)
			throws NoDataException {
		if (StringUtils.isEmpty(offsetNo)) {
			throw new IllegalArgumentException("offsetNo is empty");
		}

		OffsetGroupHeader bean = offsetGroupHeaderDao.getEntity(offsetNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 相殺トランザクション検索（登録用）
	 * @param offsetNo 相殺番号
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return OffsetGroupData
	 * @throws NoDataException NoDataException
	 */
	public OffsetGroupData getOffsetGroupDataEntity(final String offsetNo,
			final String venderDivision, final String venderCd)
			throws NoDataException {
		if (StringUtils.isEmpty(offsetNo)) {
			throw new IllegalArgumentException("offsetNo is empty");
		}

		if (StringUtils.isEmpty(venderDivision)) {
			throw new IllegalArgumentException("venderDivision is empty");
		}

		if (StringUtils.isEmpty(venderCd)) {
			throw new IllegalArgumentException("venderCd is empty");
		}

		OffsetGroupData bean = offsetGroupDataDao.getEntity(offsetNo, venderCd,
			venderDivision);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 取引先部署取得
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return OffsetTranData
	 */
	public OffsetTranData getVenderOrganization(final String venderDivision,
			final String venderCd) {
		OffsetTranData bean = offsetTranDataDao.getVenderOrganization(
			venderDivision, venderCd);
		return bean;
	}

	/**
	 * 名称マスタ検索
	 * @return NamesDetail
	 */
	private NamesDetail getNamesEntity() {
		NamesDetail bean = namesDetailDao.getEntity("BMN", "1");
		return bean;
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

	/* -------------------- setter -------------------- */

	/**
	 * OffsetDetailDaoを設定します。
	 * @param offsetDetailDao offsetDetailDao
	 */
	public void setOffsetDetailDao(final OffsetDetailDao offsetDetailDao) {
		this.offsetDetailDao = offsetDetailDao;
	}

	/**
	 * OffsetDepositDaoを設定します。
	 * @param offsetDepositDao offsetDepositDao
	 */
	public void setOffsetDepositDao(final OffsetDepositDao offsetDepositDao) {
		this.offsetDepositDao = offsetDepositDao;
	}

	/**
	 * OffsetPayableDaoを設定します。
	 * @param offsetPayableDao offsetPayableDao
	 */
	public void setOffsetPayableDao(final OffsetPayableDao offsetPayableDao) {
		this.offsetPayableDao = offsetPayableDao;
	}

	/**
	 * OffsetTranDataDaoを設定します。
	 * @param offsetTranDataDao offsetTranDataDao
	 */
	public void setOffsetTranDataDao(final OffsetTranDataDao offsetTranDataDao) {
		this.offsetTranDataDao = offsetTranDataDao;
	}

	/**
	 * ProcedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * ClassificationDetailDaoを設定します。
	 * @param classificationDetailDao classificationDetailDao
	 */
	public void setClassificationDetailDao(
			final ClassificationDetailDao classificationDetailDao) {
		this.classificationDetailDao = classificationDetailDao;
	}

	/**
	 * OffsetTranDataDaoを設定します。
	 * @param organizationDetailDao organizationDetailDao
	 */
	public void setOrganizationDetailDao(
			final OrganizationDetailDao organizationDetailDao) {
		this.organizationDetailDao = organizationDetailDao;
	}

	/**
	 * offsetGroupDataDaoを設定します。
	 * @param offsetGroupDataDao offsetGroupDataDao
	 */
	public void setOffsetGroupDataDao(
			final OffsetGroupDataDao offsetGroupDataDao) {
		this.offsetGroupDataDao = offsetGroupDataDao;
	}

	/**
	 * offsetGroupHeaderDaoを設定します。
	 * @param offsetGroupHeaderDao offsetGroupHeaderDao
	 */
	public void setOffsetGroupHeaderDao(
			final OffsetGroupHeaderDao offsetGroupHeaderDao) {
		this.offsetGroupHeaderDao = offsetGroupHeaderDao;
	}

	/**
	 * namesDetailDaoを設定します。
	 * @param namesDetailDao namesDetailDao
	 */
	public void setNamesDetailDao(final NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
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
