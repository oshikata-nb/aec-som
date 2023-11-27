/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.buyingapproval;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.convinventory.ConvInventoryLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryResult;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalList;
import com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalListDao;
import com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalPagerCondition;
import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListConditionForReport;
import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListForReport;
import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListForReportDao;
import com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxesDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 仕入承認画面 ロジック実装クラス
 * @author tosco
 */
public class BuyingApprovalListLogicImpl implements BuyingApprovalListLogic {

	private static final String UNIT_DIVITION_KG = "1";

	/** 仕入承認画面用のDao * */
	private BuyingApprovalListDao buyingApprovalListDao;

	/** 帳票Excel用Dao * */
	private BuyingApprovalListForReportDao buyingApprovalListForReportDao;

	/** 購買外注オーダーファイルテーブル用のDao * */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/** 仕入区分コンボボックス用DAO */
	private BuyingStockingDivisionComboboxesDao buyingStockingDivisionComboboxesDao;

	/** 在庫数量換算ロジッククラス */
	private ConvInventoryLogic convInventoryLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 仕入ステータス定数 承認は'3' */
	public static final int APPROVAL = 3;

	/** 仕入ステータス定数 否認は'4' */
	public static final int DENY = 4;

	/**
	 * コンストラクタ.
	 */
	public BuyingApprovalListLogicImpl() {
	}

	/**
	 * 仕入承認検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<BuyingApprovalList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<BuyingApprovalList> getSearchList(
			final BuyingApprovalPagerCondition condition)
			throws NoDataException {
		checkParams(condition);

		// conditionを条件に検索をおこなう
		List<BuyingApprovalList> list = buyingApprovalListDao
				.getSearchList(condition);

		// フォーマット編集
		for (BuyingApprovalList bean : list) {
			// 受入数量 ← 仕入数量
			bean.setStrStockingQuantity(checker.format(bean
					.getUnitOfOperationManagement(), "SI", bean.getVenderCd(),
				bean.getStockingQuantity()));

			// 単価
			bean.setStrHousingUnitprice(checker.format("SITANKA", "SI", bean
					.getVenderCd(), bean.getHousingUnitprice()));

			// 金額
			bean.setStrStockingAmount(checker.format("SIKINGAKU", "SI", bean
					.getVenderCd(), bean.getStockingAmount()));

			// 発注数量
			bean.setStrOrderQuantity(checker.format(bean
					.getUnitOfOperationManagement(), "SI", bean.getVenderCd(),
				bean.getOrderQuantity()));

			// 重量
			bean.setStrOrderConvertQuantity(checker.format(UNIT_DIVITION_KG,
				"SI", bean.getVenderCd(), bean.getOrderConvertQuantity()));

			ConvInventoryResult result = convInventoryLogic.packToWeight(bean
					.getItemCd(), bean.getStockingQuantity(), BigDecimal.ZERO);
			if (result.getCode().equals(new BigDecimal(1))) { // 品目未登録
				throw new LogicExceptionEx("errors.conv.inventory.calc");
			}
			bean.setStrOrderConvertQuantity(checker.format(UNIT_DIVITION_KG,
				"SI", bean.getVenderCd(), result.getInventoryQty()));
		}

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 仕入承認検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<BuyingApprovalListForReport> 検索結果リスト
	 */
	public List<BuyingApprovalListForReport> getReportList(
			final BuyingApprovalListConditionForReport condition) {

		// conditionを条件に検索をおこなう
		List<BuyingApprovalListForReport> list = buyingApprovalListForReportDao
				.getReportList(condition);

		// フォーマット編集
		for (BuyingApprovalListForReport bean : list) {
			// 重量
			ConvInventoryResult result = convInventoryLogic.packToWeight(bean
					.getItemCd(), bean.getStockingQuantity(), BigDecimal.ZERO);
			if (result.getCode().equals(new BigDecimal(1))) { // 品目未登録
				throw new LogicExceptionEx("errors.conv.inventory.calc");
			}
			bean.setOrderConvertQuantity(AecNumberUtils
					.convertBigDecimal(checker.format(UNIT_DIVITION_KG, "SI",
						bean.getVenderCd(), result.getInventoryQty())));
		}

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 承認処理を行う
	 * @param searchList 一覧検索結果
	 * @param loginUserId ログインユーザ
	 * @throws Exception Exception
	 */
	public void updateApproval(final List<BuyingApprovalList> searchList,
			final String loginUserId) throws Exception {

		// 検索結果分ループ
		for (BuyingApprovalList searchBean : searchList) {
			// チェックボックスにチェックが入っていたら
			if (searchBean.isApprovalCheckBox()) {

				try {

					// 承認対象データを作成する
					List<PurchaseSubcontract> list = purchaseSubcontractDao
							.getListBySlipNo(searchBean.getSlipNo());

					// 検索結果分ループ
					for (PurchaseSubcontract bean : list) {
						// newBeanのデータで購買外注オーダーファイルテーブルの更新を行う
						PurchaseSubcontract newBean = new PurchaseSubcontract();

						// 購買NO
						newBean.setPurchaseNo(bean.getPurchaseNo());

						// 支払先コードの設定
						newBean.setPayeeCd(searchBean.getPaymentInvoiceCd());

						// 仕入ステータス承認'3'(固定)セット
						newBean.setStatus2(new BigDecimal(APPROVAL));

						// 更新者(ログインユーザー)セット
						newBean.setUpdatorCd(loginUserId);

						// 更新日時セット
						newBean.setUpdateDate(bean.getUpdateDate());

						// ２回目：
						purchaseSubcontractDao.updateUnlessNull(newBean);

					}
				} catch (NotSingleRowUpdatedRuntimeException e) {
					// 更新エラー OptimisticLockRuntimeExceptionをthrowする
					throw new OptimisticLockRuntimeException();
				}

			}
		}
	}

	/**
	 * 否認処理を行う
	 * @param searchList 一覧検索結果
	 * @param loginUserId ログインユーザ
	 * @throws Exception Exception
	 */
	public void updateDeny(final List<BuyingApprovalList> searchList,
			final String loginUserId) throws Exception {

		// 検索結果分ループ
		for (BuyingApprovalList searchBean : searchList) {
			// チェックボックスにチェックが入っていたら
			if (searchBean.isApprovalCheckBox()) {

				try {
					// 承認対象データを作成する
					List<PurchaseSubcontract> list = purchaseSubcontractDao
							.getListBySlipNo(searchBean.getSlipNo());

					// 検索結果分ループ
					for (PurchaseSubcontract bean : list) {
						// newBeanのデータで購買外注オーダーファイルテーブルの更新を行う
						PurchaseSubcontract newBean = new PurchaseSubcontract();

						// 購買NO
						newBean.setPurchaseNo(bean.getPurchaseNo());

						// 仕入ステータス否認'4'(固定)セット
						newBean.setStatus2(new BigDecimal(DENY));

						// 更新者(ログインユーザー)セット
						newBean.setUpdatorCd(loginUserId);

						// 更新日時セット
						newBean.setUpdateDate(bean.getUpdateDate());

						// ２回目：
						purchaseSubcontractDao.updateUnlessNull(newBean);

					}
				} catch (NotSingleRowUpdatedRuntimeException e) {
					// 更新エラー OptimisticLockRuntimeExceptionをthrowする
					throw new OptimisticLockRuntimeException();
				}

			}
		}

	}

	/**
	 * 仕入区分コンボボックスを取得する
	 * @return List<BuyingStockingDivisionComboboxes>
	 */
	public List<BuyingStockingDivisionComboboxes> getBuyingStockingDivisionComboboxes() {
		// 仕入れ区分コンボボックスを取得(検索用のため、取消の区分はなし)
		return buyingStockingDivisionComboboxesDao.findStockingDivision(false);
	}

	/**
	 * 仕入区分コンボボックス作成
	 * @param zero すべての設定可否(true:すべてを設定する false:すべてを設定しない)
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createBuyingApprovalStockingDivisionCombobox(
			final boolean zero) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("すべて");
			res.add(item);
		}
		// 仕入区分検索
		List<BuyingStockingDivisionComboboxes> list = getBuyingStockingDivisionComboboxes();
		if (list != null) {
			for (BuyingStockingDivisionComboboxes bean : list) {
				ComboBoxItems item = new ComboBoxItems();
				item.setValues(bean.getCategoryDivision().toString());
				item.setLabales(bean.getCategoryName().toString());
				res.add(item);
			}
		}

		return res;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final BuyingApprovalPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 仕入承認画面用のDaoを設定します。
	 * @param buyingApprovalListDao 仕入承認画面用のDao
	 */
	public void setBuyingApprovalListDao(
			final BuyingApprovalListDao buyingApprovalListDao) {
		this.buyingApprovalListDao = buyingApprovalListDao;

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
	 * 在庫数量換算ロジッククラスを設定します。
	 * @param convInventoryLogic 在庫数量換算ロジッククラス
	 */
	public void setConvInventoryLogic(
			final ConvInventoryLogic convInventoryLogic) {
		this.convInventoryLogic = convInventoryLogic;
	}

	/**
	 * 帳票Excelクラスを設定します。
	 * @param buyingApprovalListForReportDao 帳票Excelクラス
	 */
	public void setBuyingApprovalListForReportDao(
			final BuyingApprovalListForReportDao buyingApprovalListForReportDao) {
		this.buyingApprovalListForReportDao = buyingApprovalListForReportDao;
	}
}
