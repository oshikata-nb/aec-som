/*
 * Created on 2008/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.payment.Payment;
import com.asahikaseieng.dao.entity.payment.PaymentDao;
import com.asahikaseieng.dao.entity.paymentheader.PaymentHeader;
import com.asahikaseieng.dao.entity.paymentheader.PaymentHeaderDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetailDao;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPayment;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentDao;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentVender;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentVenderDao;
import com.asahikaseieng.dao.nonentity.payment.paymentheaderdetail.PaymentHeaderDetail;
import com.asahikaseieng.dao.nonentity.payment.paymentheaderdetail.PaymentHeaderDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.FunGetSlipNoCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProGetStockReductionCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * PaymentCsmDetailLogicImplクラス．支払入力（詳細)ロジック(カスタマイズ)
 * @author tosco
 */
public class PaymentCsmDetailLogicImpl implements PaymentCsmDetailLogic {

	/** 取引先マスタ検索dao */
	private VenderDao venderDao;

	/** プロシージャ呼び出しDAO */
	private ProcedureCallDao procedureCallDao;

	/** 支払トランザクション更新DAO */
	private AltPaymentDao paymentDao;

	/** 支払トランザクション更新DAO */
	private PaymentDao paymentUpdateDao;

	/** 支払入力用取引先検索DAO */
	private AltPaymentVenderDao paymentVenderDao;

	private NamesDetailDao namesDetailDao;

	private PaymentHeaderDao paymentHeaderDao;

	private PaymentHeaderDetailDao paymentHeaderDetailDao;

	private AltPaymentDao altPaymentDao;

	private ClassificationDetailDao classificationDetailDao;

	private ClassificationListForComboboxesDao classificationListForComboboxesDao;

	/* 月の最終日 */
	// private static final int LAST_DAY = 31;
	/**
	 * コンストラクタ
	 */
	public PaymentCsmDetailLogicImpl() {
	}

	/**
	 * 検索処理を行う.支払入力検索処理
	 * @param venderCd 取引先コード
	 * @param venderDivision 取引先分類
	 * @return Vender
	 * @throws NoDataException 検索結果が存在しない場合発生
	 */
	public Vender getVenderEntity(final String venderCd,
			final String venderDivision) throws NoDataException {

		Vender vender = venderDao.getEntity(venderCd, venderDivision);
		if (vender == null) {
			throw new NoDataException();
		}
		return vender;
	}

	/**
	 * 支払テーブルにデータを挿入する。
	 * @param list 更新明細データ
	 * @return エラーメッセージ（正常時null)
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public String insert(final List<AltPayment> list)
			throws IllegalAccessException, InvocationTargetException {
		String res = null;
		// 更新者コード
		String updatorCode = list.get(0).getUpdatorCd();
		// 支払番号をFunctionから取得
		try {
			String slipNo = getSlipNo(updatorCode);
			for (AltPayment payment : list) {
				// 支払番号を設定
				payment.setSlipNo(slipNo);
				try {
					// 挿入
					int count = paymentDao.insert(payment);
					if (count != 1) {
						// 排他エラー
						throw new OptimisticLockRuntimeException();
					}
				} catch (SQLRuntimeException e) {
					throw new DuplicateRuntimeException(0);
				}
			}
		} catch (NoDataException e) {
			// 支払番号が取得できなかった場合
			res = "支払番号取得エラー";
		}

		/* 支払トランザクションの登録が成功した場合 */
		if (StringUtils.isEmpty(res)) {
			// Timestamp t = getDateTimestamp(list.get(0).getPaymentDate());
			//
			// String y = AecDateUtils.dateFormat(t, "yyyy"); /* 年 */
			// String m = AecDateUtils.dateFormat(t, "MM"); /* 月 */
			//
			// Calendar cal = Calendar.getInstance();
			//
			// /* 月の開始日 */
			// cal.set(AecNumberUtils.convertBigDecimal(y).intValue(),
			// AecNumberUtils.convertBigDecimal(m).intValue(), 1);
			//
			// int month = cal.get(Calendar.MONTH) + 1;
			// int todayMonth = 0;
			//
			// /* 月の最終日取得 */
			// for (int i = 0; i < LAST_DAY; i++) {
			// todayMonth = cal.get(Calendar.MONTH);
			//
			// if (month != todayMonth) {
			// break;
			// }
			//
			// cal.add(Calendar.DATE, 1);
			// }
			//
			// cal.add(Calendar.DATE, -1);
			//
			// Timestamp fromDate = AecDateUtils.getTimestampYmdFormat(y + "/"
			// + AecNumberUtils.decimalFormat(new BigDecimal(m), "00")
			// + "/01");
			// Timestamp toDate = AecDateUtils.getTimestampYmdFormat(y
			// + "/"
			// + AecNumberUtils.decimalFormat(new BigDecimal(m), "00")
			// + "/"
			// + AecNumberUtils.decimalFormat(new BigDecimal(cal
			// .get(Calendar.DAY_OF_MONTH)), "00"));
			//
			// /* 仕入割引額の登録 */
			// List<PaymentHeaderDetail> listHeader = paymentHeaderDetailDao
			// .getEntity(list.get(0).getOrganizationCd(), list.get(0)
			// .getSupplierCd(), fromDate, toDate);
			/* Date ---> Timestamp */
			Timestamp paymentDate = getDateTimestamp(list.get(0)
					.getPaymentDate());

			/* 仕入割引額＆振込手数料の登録 */
			List<PaymentHeaderDetail> listHeader = paymentHeaderDetailDao
					.getEntity(list.get(0).getOrganizationCd(), list.get(0)
							.getSupplierCd(), paymentDate);

			for (int i = 0; i < listHeader.size(); i++) {
				PaymentHeader bean = new PaymentHeader();

				/* BeanをFormにコピーする */
				IgnoreCaseBeanUtils.copyProperties(bean, listHeader.get(i));

				bean.setStockReduction(list.get(0).getStockReduction()); /* 仕入割引額 */
				bean.setTransferFee(list.get(0).getTransferFree()); /* 振込手数料 */
				bean.setUpdatorCd(updatorCode); /* 更新者コード */

				paymentHeaderDao.update(bean);
			}
		}

		return res;
	}

	/**
	 * 支払番号を取得する。
	 * @param updatotCode 更新者ＩＤ
	 * @return 支払番号
	 * @throws NoDataException データが取得できなかった場合
	 */
	private String getSlipNo(final String updatorCode) throws NoDataException {
		String slipNo = null;

		FunGetSlipNoCallDto dto = new FunGetSlipNoCallDto();
		dto.setPStrUpdatorCd(updatorCode);

		// 支払番号取得
		procedureCallDao.funGetPaymentNo(dto);
		slipNo = dto.getPStrSlipNo();
		if (StringUtils.isEmpty(slipNo)) {
			throw new NoDataException();
		}
		return slipNo;
	}

	/**
	 * 支払番号で支払トランザクションテーブルからデータを取得
	 * @param paymentNo 支払番号
	 * @return 支払明細データ
	 * @throws NoDataException データが存在しない場合発生
	 */
	public List<AltPayment> findBySlipNo(final String paymentNo)
			throws NoDataException {
		List<AltPayment> list = paymentDao.findBySlipNo(paymentNo);
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
		if (list.isEmpty()) {
			// 検索結果なし
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 支払テーブルにデータを更新する。（実際はDeleteInsert）
	 * @param list 更新明細データ
	 * @return エラーメッセージ（正常時null)
	 */
	public String update(final List<AltPayment> list) {
		String res = null;

		// 支払番号
		AltPayment deletePayment = list.get(0);

		// 支払番号に該当するデータ全て削除
		delete(deletePayment);

		for (AltPayment payment : list) {
			try {
				// 挿入
				int count = paymentDao.insert(payment);
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
	public void update(final Payment bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			paymentUpdateDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 支払テーブルから支払番号に合致するデータを全て削除する
	 * @param slipNo 支払番号
	 * @return 削除件数
	 */
	public int delete(final String slipNo) {
		int delCount = 0;
		try {
			// 支払番号に該当するデータ全て削除
			delCount = paymentDao.delete(slipNo);
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
	 * 支払テーブルから支払番号に合致するデータを全て削除する
	 * @param payment 削除データ
	 * @return 削除件数
	 */
	public int delete(final AltPayment payment) {
		int delCount = 0;
		try {
			// 支払番号に該当するデータ全て削除
			delCount = paymentDao.delete(payment.getSlipNo());
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
	 * 支払先AutoComplete用一覧取得
	 * @param venderDivision 取引先区分==TS:得意先 SI:仕入先
	 * @param venderCd 取引先コード
	 * @return 支払先AutoComplete用一覧
	 * @throws NoDataException データが存在しない場合
	 */
	// public List<AltPaymentVender> getVenderList(final String venderDivision,
	// final String venderCd) throws NoDataException {
	// List<AltPaymentVender> list = paymentVenderDao.getSupplierAutoComplete(
	// venderDivision, venderCd);
	// if (list.isEmpty()) {
	// // 検索結果なし
	// throw new NoDataException();
	// }
	// return list;
	// }
	/**
	 * 支払先取得
	 * @param venderDivision 取引先区分==TS:得意先 SI:仕入先
	 * @param venderCd 取引先コード
	 * @return 支払先データ
	 * @throws NoDataException データが存在しない場合
	 */
	public AltPaymentVender getVender(final String venderDivision,
			final String venderCd) throws NoDataException {
		AltPaymentVender result = paymentVenderDao.getSupplier(venderDivision,
			venderCd);
		if (result == null) {
			// 検索結果なし
			throw new NoDataException();
		}
		return result;
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
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	public void statusUpdate(final PaymentCsmDetailForm frm,
			final BigDecimal status, final String tantoCd)
			throws NoDataException {
		for (int i = 0; i < frm.getDetailList().size(); i++) {
			Payment bean = getEntity(frm.getSlipNo(), new BigDecimal(i + 1));

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
	 * 支払トランザクション検索
	 * @param slipNo slipNo
	 * @param rowNo rowNo
	 * @return Payment
	 * @throws NoDataException NoDataException
	 */
	public Payment getEntity(final String slipNo, final BigDecimal rowNo)
			throws NoDataException {
		if (StringUtils.isEmpty(slipNo)) {
			throw new IllegalArgumentException("slipNo is empty");
		}

		Payment bean = paymentUpdateDao.getEntity(rowNo, slipNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * DateオブジェクトをTimestampに変換する
	 * @param d Dateオブジェクト
	 * @return Timestamp
	 */
	public Timestamp getDateTimestamp(final Date d) {
		String s = null;

		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			s = sdf.format(d);
		}

		Timestamp t = AecDateUtils.getTimestampYmdFormat(s);

		return t;
	}

	/**
	 * 支払予定取得
	 * @param venderCd 仕入先コード
	 * @param payableAmount 支払残高合計
	 * @return 支払予定
	 */
	public List<AltPayment> getPaymentHeaderList(final String venderCd,
			final BigDecimal payableAmount) {
		/* 支払予定検索 */
		List<AltPayment> list = altPaymentDao.getPaymentHeaderList(venderCd,
			payableAmount);

		return list;
	}

	/**
	 * サイト情報取得
	 * @param venderCd 仕入先コード
	 * @param paymentScheduledAmount 支払予定額
	 * @return サイト情報
	 */
	public ProGetStockReductionCallDto getStockReduction(final String venderCd,
			final BigDecimal paymentScheduledAmount) {
		ProGetStockReductionCallDto dto = new ProGetStockReductionCallDto();

		dto.setPStrVenderCd(venderCd);
		dto.setPNumPayableAmount(paymentScheduledAmount);

		try {
			procedureCallDao.proGetStockReduction(dto);

			if (dto.getPStrErrorReturnCd() != null) {
				// throw new LogicExceptionEx(dto.getPStrErrorReturnMsg());
				return null;
			}
		} catch (LogicExceptionEx e) {
			// throw new LogicExceptionEx(dto.getPStrErrorReturnMsg());
			return null;
		}

		return dto;
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
	 * @return List<ClassificationListForComboboxes>
	 */
	public List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType) {
		List<ClassificationListForComboboxes> list = classificationListForComboboxesDao
				.getListForComboboxes(dataType, null);
		return list;
	}

	/**
	 * 支払済金額取得
	 * @param supplierCd 仕入先コード
	 * @param strPaymentDate 支払日付
	 * @return AltPayment
	 */
	public AltPayment getPaidEntity(final String supplierCd,
			final String strPaymentDate) {
		AltPayment bean = altPaymentDao.getPaidEntity(supplierCd,
			strPaymentDate);
		return bean;
	}

	/**
	 * 支払残高取得
	 * @param supplierCd 仕入先コード
	 * @param strPaymentDate 支払日付
	 * @return AltPayment
	 */
	public AltPayment getBalanceEntity(final String supplierCd,
			final String strPaymentDate) {
		AltPayment bean = altPaymentDao.getBalanceEntity(supplierCd,
			strPaymentDate);
		return bean;
	}

	/**
	 * 支払登録チェック
	 * @param paymentDate 支払日付
	 * @param supplierCd 支払先コード
	 * @param organizationCd 部署コード
	 * @return List<AltPayment>
	 */
	public List<AltPayment> checkEntity(final Timestamp paymentDate,
			final String supplierCd, final String organizationCd) {
		List<AltPayment> list = altPaymentDao.checkEntity(paymentDate,
			supplierCd, organizationCd);
		return list;
	}

	/**
	 * 支払予定 相殺検索
	 * @param strOffsetDate 相殺日
	 * @param venderCd 支払先コード
	 * @return AltPayment
	 */
	public AltPayment getOffsetAmount(final String strOffsetDate,
			final String venderCd) {
		Timestamp offsetDate = AecDateUtils
				.getTimestampYmdFormat(strOffsetDate);

		AltPayment bean = altPaymentDao.getOffsetAmount(offsetDate, venderCd);
		return bean;
	}

	/**
	 * 支払額合計検索
	 * @param supplierCd 支払先コード
	 * @return AltPayment
	 */
	public AltPayment getTotalPaymentAmount(final String supplierCd) {
		AltPayment bean = altPaymentDao.getTotalPaymentAmount(supplierCd);
		return bean;
	}

	// setter------------------------------------------------------------------------
	/**
	 * 取引先マスタ検索daoを設定します。
	 * @param venderDao 取引先マスタ検索dao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * プロシージャ呼び出しDAOを設定します。
	 * @param procedureCallDao プロシージャ呼び出しDAO
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * 支払トランザクション更新DAOを設定します。
	 * @param paymentDao 支払トランザクション更新DAO
	 */
	public void setPaymentDao(final AltPaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	/**
	 * 支払入力用取引先検索DAOを設定します。
	 * @param paymentVenderDao 支払入力用取引先検索DAO
	 */
	public void setPaymentVenderDao(final AltPaymentVenderDao paymentVenderDao) {
		this.paymentVenderDao = paymentVenderDao;
	}

	/**
	 * namesDetailDaoを設定します。
	 * @param namesDetailDao namesDetailDao
	 */
	public void setNamesDetailDao(final NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
	}

	/**
	 * paymentUpdateDaoを設定します。
	 * @param paymentUpdateDao paymentUpdateDao
	 */
	public void setPaymentUpdateDao(final PaymentDao paymentUpdateDao) {
		this.paymentUpdateDao = paymentUpdateDao;
	}

	/**
	 * paymentHeaderDaoを設定します。
	 * @param paymentHeaderDao paymentHeaderDao
	 */
	public void setPaymentHeaderDao(final PaymentHeaderDao paymentHeaderDao) {
		this.paymentHeaderDao = paymentHeaderDao;
	}

	/**
	 * paymentHeaderDetailDaoを設定します。
	 * @param paymentHeaderDetailDao paymentHeaderDetailDao
	 */
	public void setPaymentHeaderDetailDao(
			final PaymentHeaderDetailDao paymentHeaderDetailDao) {
		this.paymentHeaderDetailDao = paymentHeaderDetailDao;
	}

	/**
	 * altPaymentDaoを設定します。
	 * @param altPaymentDao altPaymentDao
	 */
	public void setAltPaymentDao(final AltPaymentDao altPaymentDao) {
		this.altPaymentDao = altPaymentDao;
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
