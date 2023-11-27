/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.buying;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.ComboboxesBean;
import com.asahikaseieng.dao.entity.inoutrecord.InoutRecord;
import com.asahikaseieng.dao.entity.inoutrecord.InoutRecordDao;
import com.asahikaseieng.dao.entity.master.bumon.Bumon;
import com.asahikaseieng.dao.entity.master.bumon.BumonDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.entity.master.location.LocationDao;
import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.buying.BuyingDetail;
import com.asahikaseieng.dao.nonentity.buying.BuyingDetailDao;
import com.asahikaseieng.dao.nonentity.buyinginout.BuyingGetInoutData;
import com.asahikaseieng.dao.nonentity.buyinginout.BuyingGetInoutDataDao;
import com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster.TaxMasterListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster.TaxMasterListForComboboxesDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 仕入詳細 ロジック実装クラス
 * @author tosco
 */
public class BuyingDetailLogicImpl implements BuyingDetailLogic {

	/** 仕入詳細画面用Dao * */
	private BuyingDetailDao buyingDetailDao;

	/** 購買外注オーダーファイルテーブル用のDao * */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/** 仕入区分コンボボックス用DAO */
	private BuyingStockingDivisionComboboxesDao buyingStockingDivisionComboboxesDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 品目マスタ検索用Dao * */
	private ItemDao itemDao;

	/** 仕入先マスタ検索用Dao * */
	private VenderDao venderDao;

	/** 会計部門マスタ検索用Dao * */
	private BumonDao bumonDao;

	/** 組織マスタ検索用Dao * */
	private OrganizationDao organizationDao;

	/** ロケーションマスタ検索用Dao * */
	private LocationDao locationDao;

	/** ログインマスタ検索用Dao * */
	private LoginDao loginDao;

	/** 受払履歴更新用Dao * */
	private InoutRecordDao inoutRecordDao;

	/** 受払履歴データ取得用Dao * */
	private BuyingGetInoutDataDao buyingGetInoutDataDao;
	
	// 軽減税率対応
	private TaxMasterListForComboboxesDao taxMasterListForComboboxesDao;

	/** 算出区分 1:明細単位 */
	public static final BigDecimal CALC_DIVISION_MEISAI = new BigDecimal(1);

	/** 算出区分 4:自社ﾏｽﾀ */
	public static final BigDecimal CALC_DIVISION_COMPANY = new BigDecimal(4);

	/** 消費税課税区分 1:外税 */
	public static final BigDecimal TAX_DIVISION_OUT = new BigDecimal(1);

	/** 消費税課税区分 2:内税 */
	public static final BigDecimal TAX_DIVISION_IN = new BigDecimal(2);

	/** 仕入区分[2:返品] */
	public static final String STOCKING_DIVISION_RETURN = "2";

	/**
	 * コンストラクタ.
	 */
	public BuyingDetailLogicImpl() {
	}

	/**
	 * 仕入れ番号から受払データを取得する
	 * @param slipNo 仕入番号
	 * @return BuyingGetInoutData
	 */
	public BuyingGetInoutData getInoutData(final String slipNo) {

		return buyingGetInoutDataDao.getEntity(slipNo);

	}

	/**
	 * 受払数量を数値換算して取得
	 * @param inoutQty 受払数量
	 * @param unitOfOperationManagement 運用管理単位
	 * @param venderCd 仕入先コード
	 * @return String 受払数量
	 */
	public String getInoutQty(final BigDecimal inoutQty,
			final String unitOfOperationManagement, final String venderCd) {

		return checker.format(unitOfOperationManagement, "SI", venderCd,
			inoutQty);

	}

	/**
	 * 検索処理を行なう.
	 * @param slipNo 仕入番号
	 * @return BuyingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public BuyingDetail getEntity(final String slipNo) throws NoDataException {

		// slipNoのチェックを行う
		checkParams(slipNo);

		// 会社コード取得
		String companyCd = buyingDetailDao.getCompanyCd();

		// 仕入詳細データを検索し、Beanに格納する
		BuyingDetail bean = buyingDetailDao.getEntity(slipNo, companyCd);

		calcStockAmount(bean);

		// 購入数量
		bean.setStrStockingQuantity(checker.format(bean
				.getUnitOfOperationManagement(), "SI", bean.getVenderCd(), bean
				.getStockingQuantity()));

		// 単価
		bean.setStrHousingUnitprice(checker.format("SITANKA", "SI", bean
				.getVenderCd(), bean.getHousingUnitprice()));

		// 金額
		bean.setStrStockingAmount(checker.format("SIKINGAKU", "SI", bean
				.getVenderCd(), bean.getStockingAmount()));

		// 仕入詳細の検索結果がない場合
		if (bean == null) {
			// NoDataExceptionをthrowする
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 消費税額算出
	 * @param updBean 購買外注オーダ更新Bean
	 * @param frm 仕入入力画面Form
	 * @param check 数値項目用表示ロジッククラス
	 */
	private void calcStockAmount(final BuyingDetail bean) {

		BigDecimal calcDiv = bean.getCalcDivision(); // 取引先ﾏｽﾀ.算出区分
		BigDecimal compCalcDiv = bean.getCompCalcDivision(); // 自社ﾏｽﾀ.消費税算出区分

		// 取引先ﾏｽﾀ.算出区分＝1(明細)の場合
		// 取引先ﾏｽﾀ.算出区分＝4(自社ﾏｽﾀ) かつ 自社ﾏｽﾀ.消費税算出区分＝1(明細)の場合
		if (CALC_DIVISION_MEISAI.equals(calcDiv)
				|| (CALC_DIVISION_COMPANY.equals(calcDiv) && CALC_DIVISION_MEISAI
						.equals(compCalcDiv))) {

			// 消費税課税区分=1:外税
			if (TAX_DIVISION_IN.equals(bean.getTaxDivision())) {
				BigDecimal stockingAmount = bean.getStockingAmount(); // 仕入金額
				BigDecimal taxRatio = bean.getTaxAmount(); // 消費税率
				if (stockingAmount != null && taxRatio != null) {
					stockingAmount = stockingAmount.add(taxRatio);
				}
				bean.setStockingAmount(stockingAmount);
			}
		}
	}

	/**
	 * 更新処理を行う
	 * @param bean 更新対象ビーン
	 * @param frm 画面情報
	 * @throws NoDataException データ無し例外
	 */
	public void update(final PurchaseSubcontract bean,
			final BuyingDetailForm frm) throws NoDataException {

		try {
			// beanのデータで購買外注オーダーファイルテーブルの更新を行う
			purchaseSubcontractDao.update(bean);

			// 区分が返品である場合
			if (frm.getCategoryDivision().equals(STOCKING_DIVISION_RETURN)) {

				// 以前の区分が返品である場合
				if (frm.getBeforeCategoryDivision().equals(
					STOCKING_DIVISION_RETURN)) {

					// 以前と現在の受払番号が異なる場合
					if (!frm.getInoutNo().equals(frm.getBeforeInoutNo())) {
						// 受払履歴更新処理(オーダ番号クリア）
						InoutRecord inoutBefore = inoutRecordDao.getEntity(frm
								.getBeforeInoutNo());
						if (inoutBefore != null) {
							inoutBefore.setOderNo(null);
							inoutBefore.setUpdatorCd(bean.getUpdatorCd());
							inoutRecordDao.update(inoutBefore);
						}

						// 受払履歴更新処理(オーダ番号を仕入番号で更新）
						InoutRecord inoutNow = inoutRecordDao.getEntity(frm
								.getInoutNo());
						if (inoutNow != null) {
							inoutNow.setOderNo(frm.getSlipNo());
							inoutNow.setUpdatorCd(bean.getUpdatorCd());
							inoutRecordDao.update(inoutNow);
						}

					}

				} else { // 以前の区分が返品以外の場合
					// 受払履歴更新処理(オーダ番号を仕入番号で更新）
					InoutRecord inoutNow = inoutRecordDao.getEntity(frm
							.getInoutNo());
					if (inoutNow != null) {
						inoutNow.setOderNo(frm.getSlipNo());
						inoutNow.setUpdatorCd(bean.getUpdatorCd());
						inoutRecordDao.update(inoutNow);
					}

				}

			} else { // 区分が返品以外
				// 以前の区分が返品の場合
				if (frm.getBeforeCategoryDivision().equals(
					STOCKING_DIVISION_RETURN)) {
					// 受払履歴更新処理(オーダ番号クリア）
					InoutRecord inout = inoutRecordDao.getEntity(frm
							.getBeforeInoutNo());
					if (inout != null) {
						inout.setOderNo(null);
						inout.setUpdatorCd(bean.getUpdatorCd());
						inoutRecordDao.update(inout);
					}
				}
			}

			// 更新時に、すでに更新されていた場合
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 更新エラー OptimisticLockRuntimeExceptionをthrowする
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 承認依頼の更新処理を行う
	 * @param paramBean 更新対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	public void updateApprovalRequest(final PurchaseSubcontract paramBean)
			throws NoDataException {

		// ****************************************
		// 同じ仕入番号をもつレコードを全て更新する
		// ****************************************
		try {

			// 承認対象データを検索する。仕入番号で検索。
			List<PurchaseSubcontract> list = purchaseSubcontractDao
					.getListBySlipNo(paramBean.getSlipNo());

			// 検索結果分ループ
			// 更新は、キー（購買No）で行う。
			for (PurchaseSubcontract bean : list) {
				// newBeanのデータで購買外注オーダーファイルテーブルの更新を行う
				PurchaseSubcontract newBean = new PurchaseSubcontract();

				// 購買NO（更新キー）
				newBean.setPurchaseNo(bean.getPurchaseNo());

				// 仕入ステータス承認依頼'2'(固定)セット
				newBean.setStatus2(paramBean.getStatus2());

				// 更新者(ログインユーザー)セット
				newBean.setUpdatorCd(paramBean.getUpdatorCd());

				// 更新日時セット（更新キー）
				newBean.setUpdateDate(bean.getUpdateDate());

				purchaseSubcontractDao.updateUnlessNull(newBean);

			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 更新エラー OptimisticLockRuntimeExceptionをthrowする
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 更新処理を行う（値がある項目のみ更新）
	 * @param bean 更新対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	public void updateUnlessNull(final PurchaseSubcontract bean)
			throws NoDataException {

		try {
			// beanのデータで購買外注オーダーファイルテーブルの更新を行う
			purchaseSubcontractDao.updateUnlessNull(bean);

			// 更新時に、すでに更新されていた場合
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 更新エラー OptimisticLockRuntimeExceptionをthrowする
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 登録処理を行う
	 * @param bean 登録対象ビーン
	 * @param inoutNo 受払番号
	 */
	public void insert(final PurchaseSubcontract bean, final String inoutNo) {

		try {
			// beanのデータを購買外注オーダーファイルテーブルに登録を行う
			purchaseSubcontractDao.insert(bean);
			if (bean.getCategoryDivision().equals(STOCKING_DIVISION_RETURN)
					&& inoutNo != null && !inoutNo.equals("")) {

				// 受払履歴更新処理
				InoutRecord inout = inoutRecordDao.getEntity(inoutNo);
				inout.setOderNo(bean.getSlipNo());
				inout.setUpdatorCd(bean.getUpdatorCd());
				inoutRecordDao.update(inout);
			}
		} catch (SQLRuntimeException e) {
			// 登録エラー DuplicateRuntimeExceptionをthrowする
			throw new DuplicateRuntimeException(0);
		}

	}

	/**
	 * 削除処理を行う
	 * @param deleteBean 削除対象ビーン
	 * @param loginUserId ログインユーザー
	 * @param frm 画面情報
	 * @throws NoDataException データ無し例外
	 */
	public void delete(final PurchaseSubcontract deleteBean,
			final String loginUserId, final BuyingDetailForm frm)
			throws NoDataException {

		// ****************************************
		// 取消データの削除の場合、取消元データの更新もする
		// ****************************************
		if (!(StringUtils.isEmpty(deleteBean.getCategoryDivision()))) {
			if (deleteBean.getCategoryDivision().substring(0, 1).equals("-")) {
				// 削除対象のデータが取消データの場合
				// 取消元データの更新と、取消データ自体の削除を行う
				// ****************************************
				// 同じ仕入番号をもつレコードを全て更新する
				// ****************************************
				try {
					// 更新対象データを検索する。仕入番号で検索。
					List<PurchaseSubcontract> list = purchaseSubcontractDao
							.getListBySlipNo(deleteBean.getCancelSlipNo());

					// 更新は、キー（購買No）で行う。
					for (PurchaseSubcontract bean : list) {

						// 取消元データの仕入取消－仕入番号と仕入取消－行番号をNULLにする。
						bean.setCancelSlipNo(null);
						bean.setCancelRowNo(null);
						// 更新者(ログインユーザー)セット
						bean.setUpdatorCd(loginUserId);

						purchaseSubcontractDao.update(bean);
					}
				} catch (NotSingleRowUpdatedRuntimeException e) {
					// 更新エラー OptimisticLockRuntimeExceptionをthrowする
					throw new OptimisticLockRuntimeException();
				}
			}
		}

		try {
			// beanのデータを購買外注オーダーファイルテーブルから削除を行う
			purchaseSubcontractDao.delete(deleteBean);

			// 以前の区分が返品である場合
			if (frm.getBeforeCategoryDivision()
					.equals(STOCKING_DIVISION_RETURN)) {

				// 受払履歴更新処理
				InoutRecord inout = inoutRecordDao.getEntity(frm
						.getBeforeInoutNo());
				if (inout != null) {
					inout.setOderNo(null);
					inout.setUpdatorCd(frm.getUpdatorCd());
					inoutRecordDao.update(inout);
				}
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 削除エラー OptimisticLockRuntimeExceptionをthrow */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 取消元データの更新処理を行う
	 * @param updateSlipNo 更新対象の仕入番号
	 * @param slipNo 取消データの仕入番号
	 * @param rowNo 取消データの行番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データ無し例外
	 */
	public void updateCancel(final String updateSlipNo, final String slipNo,
			final BigDecimal rowNo, final String loginUserId)
			throws NoDataException {

		// ****************************************
		// 同じ仕入番号をもつレコードを全て更新する
		// ****************************************
		try {

			// 更新対象データを検索する。仕入番号で検索。
			List<PurchaseSubcontract> list = purchaseSubcontractDao
					.getListBySlipNo(updateSlipNo);

			// 検索結果分ループ
			// 更新は、キー（購買No）で行う。
			for (PurchaseSubcontract bean : list) {

				// ******************************************
				// 取消元データの更新
				// ******************************************
				// newBeanのデータで購買外注オーダーファイルテーブルの更新を行う
				PurchaseSubcontract newBean = new PurchaseSubcontract();

				// 仕入取消－仕入番号（取消先の仕入番号）
				newBean.setCancelSlipNo(slipNo);
				// 仕入取消－行番号（取消先の行番号）
				newBean.setCancelRowNo(rowNo);
				// 更新者(ログインユーザー)セット
				newBean.setUpdatorCd(loginUserId);

				// 更新キーの設定
				// 購買No
				newBean.setPurchaseNo(bean.getPurchaseNo());
				// 更新日付
				newBean.setUpdateDate(bean.getUpdateDate());

				purchaseSubcontractDao.updateUnlessNull(newBean);
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 更新エラー OptimisticLockRuntimeExceptionをthrowする
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * データ集計区分取得
	 * @param categoryDivision 仕入先コード
	 * @return String
	 */
	public String getDataTotalDivision(final String categoryDivision) {

		String dataTotalDivision = buyingDetailDao
				.getDataTotalDivision(categoryDivision);

		return dataTotalDivision;
	}

	/**
	 * 取引先マスタ存在チェック
	 * @param venderCd 仕入先コード
	 * @return 取得件数
	 */
	public int getCountVender(final String venderCd) {

		// 引数の仕入先コードを使用し、取引先マスタの検索を行い
		// 検索結果件数を取得する。
		checkParams(venderCd);
		Vender bean = venderDao.getEntity(venderCd, "SI");
		if (bean == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * 品目マスタ存在チェック用
	 * @param itemCd 品目コード
	 * @return PurchaseDetail
	 */
	public int getCountItem(final String itemCd) {

		checkParams(itemCd);
		Item bean = itemDao.getEntity(itemCd);
		if (bean == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * 会計部門マスタ存在チェック用
	 * @param bumonCd 会計部門コード
	 * @return ある・無し値
	 */
	public int getCountBumon(final String bumonCd) {

		// 引数の部署コードを使用し、組織マスタの検索を行う
		checkParams(bumonCd);
		Bumon bean = bumonDao.getEntity(bumonCd);
		if (bean == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * 科目マスタ存在チェック用
	 * @param accountsCd 科目コード
	 * @return ある・無し値
	 */
	public int getCountAccounts(final String accountsCd) {

		// 引数の科目コードを使用し、科目マスタの検索を行い
		// 検索結果件数を取得する
		int count = buyingDetailDao.getCountAccounts(accountsCd);

		return count;
	}

	/**
	 * 組織マスタ存在チェック用
	 * @param organizationCd 部署コード
	 * @return ある・無し値
	 */
	public int getCountOrganization(final String organizationCd) {

		// 引数の部署コードを使用し、組織マスタの検索を行う
		checkParams(organizationCd);
		Organization bean = organizationDao.getEntity(organizationCd);
		if (bean == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * ログインユーザ定義マスタ存在チェック用
	 * @param tantoCd 担当者コード
	 * @return ある・無し値
	 */
	public int getCountLogin(final String tantoCd) {

		// 引数のログインユコードを使用し、ログインマスタの検索を行い
		checkParams(tantoCd);
		Login bean = loginDao.getEntity(tantoCd);
		if (bean == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * ロケーションマスタ存在チェック用
	 * @param locationCd ロケーションコード
	 * @return ある・無し値
	 */
	public Location getCountLocation(final String locationCd) {

		// 引数のロケーションコードを使用し、組織マスタの検索を行い
		checkParams(locationCd);
		Location bean = locationDao.getEntity(locationCd);
		return bean;
	}

	/**
	 * 仕入先別単価マスタ存在チェック用
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return ある・無し値
	 */
	public int getCountUnitprice(final String itemCd, final String venderCd) {

		// 引数の品目コード、仕入先コードを使用し、仕入先別単価マスタの検索を行い
		// 検索結果件数を取得する
		int count = buyingDetailDao.getCountUnitprice(itemCd, venderCd);

		return count;
	}

	/**
	 * 仕入区分コンボボックスを取得する
	 * @param cancelFlg 取消の区分を表示するかしないかのフラグ
	 * @return List<BuyingStockingDivisionComboboxes>
	 */
	public List<BuyingStockingDivisionComboboxes> getBuyingStockingDivisionComboboxes(
			final boolean cancelFlg) {
		// 仕入れ区分コンボボックスを取得(引数は、取消の区分を取得する／しないのフラグ)
		return buyingStockingDivisionComboboxesDao
				.findStockingDivision(cancelFlg);
	}

	/**
	 * 仕入区分コンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @param frm 仕入入力画面フォーム
	 */
	public void createBuyingStockingDivisionCombobox(final boolean zero,
			final BuyingDetailForm frm) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();
		String[] categoryDivisionList = null;
		String[] reversalFlgList = null;

		// 0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("すべて");
			res.add(item);
		}
		// 仕入区分検索（false:取消の区分は取得しない）
		List<BuyingStockingDivisionComboboxes> list = getBuyingStockingDivisionComboboxes(false);

		if (list != null) {
			categoryDivisionList = new String[list.size()];
			reversalFlgList = new String[list.size()];
			int index = 0;
			for (BuyingStockingDivisionComboboxes bean : list) {
				// 仕入区分コンボボックス
				ComboBoxItems item = new ComboBoxItems();
				item.setValues(bean.getCategoryDivision().toString());
				item.setLabales(bean.getCategoryName().toString());
				res.add(item);

				// 分類コードリスト
				categoryDivisionList[index] = bean.getCategoryDivision();
				// 仕分反転フラグリスト
				if (BuyingDetailAction.DATA_TOTAL_DIVISION_HENPIN.equals(bean
						.getDataTotalDivision())
						|| BuyingDetailAction.DATA_TOTAL_DIVISION_NEBIKI
								.equals(bean.getDataTotalDivision())) {
					// ﾃﾞｰﾀ集計区分=2:返品,3:値引の場合
					reversalFlgList[index] = "1";
				} else {
					reversalFlgList[index] = "0";
				}

				index++;
			}
		}

		frm.setStockinDivisionCombo(res);
		frm.setCategoryDivisionList(categoryDivisionList);
		frm.setReversalFlgList(reversalFlgList);
	}

	/**
	 * 品目コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @return BuyingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public BuyingDetail getItemRelatedData(final String itemCd)
			throws NoDataException {

		BuyingDetail bean = buyingDetailDao.getItemRelatedData(itemCd);

		return bean;
	}

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return BuyingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public BuyingDetail getVenderRelatedData(final String itemCd,
			final String venderCd) throws NoDataException {

		BuyingDetail bean = buyingDetailDao.getVenderRelatedData(itemCd,
			venderCd);

		return bean;
	}

	/**
	 * 発注数量入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param stockingQuantity 発注数量
	 * @return BuyingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public BuyingDetail getStockingQuantityRelatedData(final String itemCd,
			final String venderCd, final BigDecimal stockingQuantity)
			throws NoDataException {

		BuyingDetail bean = buyingDetailDao.getStockingQuantityRelatedData(
			itemCd, venderCd, stockingQuantity);

		if (bean != null) {
			// 仕入単価
			bean.setStrHousingUnitprice(checker.format("SITANKA", "SI", bean
					.getVenderCd(), bean.getHousingUnitprice()));
		}

		return bean;
	}

	/**
	 * 消費税関連項目を取得する。
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return BuyingDetail 消費税関連項目
	 */
	public BuyingDetail getTaxRelatedData(final String itemCd,
			final String venderCd) {

		// 会社コード取得
		String companyCd = buyingDetailDao.getCompanyCd();

		// 消費税関連項目取得処理
		BuyingDetail bean = buyingDetailDao.getTaxRelatedData(companyCd,
			itemCd, venderCd);

		if (bean == null) {
			bean = buyingDetailDao.getTaxData(companyCd, itemCd);
		}
		return bean;
	}

	/**
	 * パラメータチェック.Stringバージョン
	 * @param cd 検索条件:LOCATION
	 * @throws IllegalArgumentException
	 */
	private void checkParams(final String cd) throws IllegalArgumentException {
		// cdが未入力もしくは、nullであれば
		if (StringUtils.isEmpty(cd)) {
			// IllegalArgumentExceptionをthrowする
			throw new IllegalArgumentException(
					"BuyingDetailLogic IllegalArgumentException : Paramater is empty checkParams(cd).");
		}
	}

	/**
	 * 支払先コードを取得する
	 * @param venderCd 仕入先コード
	 * @return 支払先コード
	 */
	public String getPayeeCd(final String venderCd) {
		Vender bean = venderDao.getEntity(venderCd, "SI");
		if (bean == null) {
			return null;
		} else {
			return bean.getPaymentInvoiceCd();
		}
	}
	
	// 軽減税率対応
	/**
	 * 消費税率コンボボックス用データ取得
	 *
	 * @param locale
	 *            言語コード
	 * @return bean
	 *            コンボボックス
	 * @throws Exception
	 *            NoDataException
	 */
	public ComboboxesBean getPurchaseTaxCombobox() throws NoDataException {

		ComboboxesBean bean = new ComboboxesBean();

		// コンボボックスデータを取得
		List<TaxMasterListForComboboxes> list = taxMasterListForComboboxesDao
				.getPurchaseTaxListForComboboxes();
		if (list == null) { throw new NoDataException(); }

		String[] values = new String[list.size()];
		String[] labels = new String[list.size()];
		String[] keys   = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getComboboxName();
			values[i] = list.get(i).getTaxCd();
			keys[i]   = list.get(i).getTaxRatio();
		}

		bean.setLabels(labels);
		bean.setValues(values);
		bean.setInvisibility(keys);
		return bean;
	}


	/* -------------------- setter -------------------- */
	/**
	 * 仕入詳細画面用Daoを設定します。
	 * @param buyingDetailDao 仕入詳細画面用Dao
	 */
	public void setBuyingDetailDao(final BuyingDetailDao buyingDetailDao) {
		this.buyingDetailDao = buyingDetailDao;
	}

	/**
	 * 購買外注オーダーファイルテーブル用Daoを設定します。
	 * @param updPurchaseSubcontractDao 購買外注オーダーファイルテーブル用Dao
	 */
	public void setUpdPurchaseSubcontractDao(
			final PurchaseSubcontractDao updPurchaseSubcontractDao) {
		this.purchaseSubcontractDao = updPurchaseSubcontractDao;
	}

	/**
	 * 仕入区分コンボボックス用DAOを設定します。
	 * @param buyingStockingDivisionComboboxesDao 仕入区分コンボボックス用DAO
	 */
	public void setBuyingStockingDivisionComboboxesDao(
			final BuyingStockingDivisionComboboxesDao buyingStockingDivisionComboboxesDao) {
		this.buyingStockingDivisionComboboxesDao = buyingStockingDivisionComboboxesDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * 品目用Daoを設定します。
	 * @param itemDao 品目取得用Dao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 仕入先用Daoを設定します。
	 * @param venderDao 仕入先取得用Dao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * 会計部門取得用Daoを設定します。
	 * @param bumonDao 部署取得用Dao
	 */
	public void setBumonDao(final BumonDao bumonDao) {
		this.bumonDao = bumonDao;
	}

	/**
	 * 部署取得用Daoを設定します。
	 * @param organizationDao 部署取得用Dao
	 */
	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/**
	 * ロケーション取得用Daoを設定します。
	 * @param locationDao ロケーション取得用Dao
	 */
	public void setLocationDao(final LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	/**
	 * 担当者取得用Daoを設定します。
	 * @param loginDao 担当者取得用Dao
	 */
	public void setLoginDao(final LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * 受払履歴更新用Daoを設定します。
	 * @param inoutRecordDao 受払履歴更新Dao
	 */
	public void setInoutRecordDao(final InoutRecordDao inoutRecordDao) {
		this.inoutRecordDao = inoutRecordDao;
	}

	/**
	 * 受払履歴データ取得用Daoを設定します。
	 * @param buyingGetInoutDataDao 受払履歴データ取得Dao
	 */
	public void setBuyingGetInoutDataDao(
			final BuyingGetInoutDataDao buyingGetInoutDataDao) {
		this.buyingGetInoutDataDao = buyingGetInoutDataDao;
	}

	/**
	 * taxMasterListForComboboxesDaoを設定します。
	 * @param taxMasterListForComboboxesDao taxMasterListForComboboxesDao
	 */
	public void setTaxMasterListForComboboxesDao(
			TaxMasterListForComboboxesDao taxMasterListForComboboxesDao) {
		this.taxMasterListForComboboxesDao = taxMasterListForComboboxesDao;
	}
}
