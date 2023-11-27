/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchaseorder;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForPurchase;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderList;
import com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderListDao;
import com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderPagerCondition;
import com.asahikaseieng.dao.nonentity.purchaseorderforreport.PurchaseOrderListConditionForRepor;
import com.asahikaseieng.dao.nonentity.purchaseorderforreport.PurchaseOrderListForRepor;
import com.asahikaseieng.dao.nonentity.purchaseorderforreport.PurchaseOrderListForReporDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 発注書一覧 ロジック実装クラス
 * @author tosco
 */
public class PurchaseOrderListLogicImpl implements PurchaseOrderListLogic {

	private static final String UNIT_DIVISION_KG = "1";

	/** 発注書発行フラグ 1:発行 */
	public static final BigDecimal ORDER_SHEET_FLG = new BigDecimal(1);

	/** オーダー区分 1:原材料 */
	public static final BigDecimal ORDER1_DIVISION = new BigDecimal(1);

	/** オーダー区分 4：スポット品 */
	public static final BigDecimal ORDER4_DIVISION = new BigDecimal(4);

	/** 発注書一覧画面用Dao */
	private PurchaseOrderListDao purchaseOrderListDao;

	/** 購買オーダーテーブル更新用Dao */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/** 発番処理 ロジッククラス */
	private GetNumberLogic getNumberLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 帳票Excel用Dao * */
	private PurchaseOrderListForReporDao purchaseOrderListForReporDao;

	/** 仕入先Dao */
	private VenderDao venderDao;

	/**
	 * コンストラクタ
	 */
	public PurchaseOrderListLogicImpl() {
	}

	/**
	 * 一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<PurchaseOrderList>発注書一覧 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PurchaseOrderList> getSearchList(
			final PurchaseOrderPagerCondition condition) throws NoDataException {
		checkParams(condition);
		// 発注書一覧の検索を行い、listに格納する。
		List<PurchaseOrderList> list = purchaseOrderListDao
				.getSearchList(condition);

		// 検索結果がない場合
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 一覧画面フォーマット
		for (PurchaseOrderList bean : list) {
			bean.setStrOrderQuantity(checker.format(bean.getUnitDiv(), "SI",
				bean.getVenderCd(), bean.getOrderQuantity())); // 発注数量

			bean.setStrOrderConvertQuantity(checker.format(UNIT_DIVISION_KG,
				"SI", bean.getVenderCd(), bean.getOrderConvertQuantity())); // 重量
		}
		return list;
	}

	/**
	 * 帳票Excel用一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<PurchaseOrderListForRepor>発注書一覧 検索結果リスト
	 */
	public List<PurchaseOrderListForRepor> getReportList(
			final PurchaseOrderListConditionForRepor condition) {
		// 発注書一覧の検索を行い、listに格納する。
		List<PurchaseOrderListForRepor> list = purchaseOrderListForReporDao
				.getReportList(condition);

		// 検索結果がない場合
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 更新処理(発注書ボタン押下時)
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 * @param organizationCd 部署コード
	 * @return String[] 発注書No
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	public String[] updata(final List<PurchaseOrderList> searchList,
			final String tantoCd, final String organizationCd)
			throws NoDataException, Exception {
		TreeMap<String, String> tMap = new TreeMap<String, String>();
		String[] orderSheetNoArray = null;

		if (searchList == null || searchList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}

		try {
			Timestamp orderDate = null; // 発注日
			String supplier = null; // 仕入先コード
			String delivery = null; // まとめ処理区分
			String orderNo = null; // 発番処理用

			String deliveryCd = null; // 受注納入先コード
			String orderOrderNo = null; // 受注番号

			StockinoutForPurchase stock = new StockinoutForPurchase(zaiCtrlDao);
			String errMsg = "errors.purchase.stock.update";
			String sheetNo = null;

			for (PurchaseOrderList data : searchList) {
				if (!data.isOrderCheckBox()) {
					// チェックなし
					continue;
				}

				// 更新用データを作成する
				PurchaseSubcontract upData = new PurchaseSubcontract();

				// **********************************************
				// 更新したい項目のみupDataにセットする
				// **********************************************
				upData.setPurchaseNo(data.getPurchaseNo()); // 購買No(更新時のキー)
				int statu = data.getStatus().intValue(); // 購買ステータス
				if (statu == PurchaseStatus.NOT_ISSUE.intValue()) {
					upData.setStatus(PurchaseStatus.ISSUED); // 発注書発行済(2)
				}
				upData.setOrderSheeFlag(ORDER_SHEET_FLG); // 発注書発行フラグ(1)
				upData.setOrderSheeDate(AecDateUtils.getCurrentTimestamp()); // 発行書発効日
				upData.setOrderSheelTantoCd(tantoCd); // 発行書発行者
				upData.setUpdatorCd(tantoCd); // 更新者
				upData.setUpdateDate(data.getUpdateDate()); // 更新日時(更新時のキー)
				// 発注者コードと部署コードがNULLの場合、ログインしているユーザーの情報をセットする
				if (StringUtils.isEmpty(data.getTantoCd())) {
					upData.setTantoCd(tantoCd);
				}
				if (StringUtils.isEmpty(data.getOrganizationCd())) {
					upData.setOrganizationCd(organizationCd);
				}

				/* 発番処理 */
				if (StringUtils.isEmpty(data.getOrderSheetNo())) {

					// 受注番号がある場合(仕入直送品の場合)集約単位を変更
					if (data.getOrderNo() == null
							|| data.getOrderNo().equals("")) {

						// 集約単位ごとに発注書番号を取得
						if (!AecDateUtils.dateFormat(data.getOrderDate(),
							"yyyy/MM/dd").equals(
							AecDateUtils.dateFormat(orderDate, "yyyy/MM/dd"))
								|| !data.getVenderCd().equals(supplier)
								|| !data.getLocation().equals(delivery)) {
							// 発注書No発番
							orderNo = getNumberLogic.getOrderSheetNo();
							// 発注書NOをセット
							upData.setOrderSheetNo(orderNo);
						} else {
							// 発注書NOをセット
							upData.setOrderSheetNo(orderNo);
						}
					} else {
						// 集約単位ごとに発注書番号を取得
						if (!AecDateUtils.dateFormat(data.getOrderDate(),
							"yyyy/MM/dd").equals(
							AecDateUtils.dateFormat(orderDate, "yyyy/MM/dd"))
								|| !data.getVenderCd().equals(supplier)
								|| !data.getOrderNo().equals(orderOrderNo)
								|| !data.getDeliveryCd().equals(deliveryCd)) {
							// 発注書No発番
							orderNo = getNumberLogic.getOrderSheetNo();
							// 発注書NOをセット
							upData.setOrderSheetNo(orderNo);
						} else {
							// 発注書NOをセット
							upData.setOrderSheetNo(orderNo);
						}
					}
				}

				// 原材料(外注)の場合、外注原材料区分を1:外注に、
				// それ以外は、0:自社に更新する
				if (ORDER1_DIVISION.equals(data.getOrderDivision())) {
					if (StringUtils.isNotEmpty(data.getLocationCd())) {
						// ロケーションの先頭１文字で判断する
						if (data.getLocationCd().charAt(0) == 'E') {
							upData.setMaterialDivision(BigDecimal.ONE);
						} else {
							upData.setMaterialDivision(BigDecimal.ZERO);
						}
					}
				}

				/* 更新処理 */
				purchaseSubcontractDao.updateUnlessNull(upData);
				orderDate = data.getOrderDate(); // 発注日
				supplier = data.getVenderCd(); // 仕入先コード
				delivery = data.getLocation(); // まとめ処理区分

				deliveryCd = data.getDeliveryCd(); // 受注納入先コード
				orderOrderNo = data.getOrderNo(); // 受注番号

				// 購買ステータスが7:未発行のものを対象とする
				if (statu == 7) {
					try {
						/* 在庫更新－発注確定 */
						if (!stock.fixPurchase(data.getPurchaseNo(), tantoCd)) {
							throw new LogicExceptionEx(errMsg);
						}
					} catch (LogicExceptionEx e) {
						throw new LogicExceptionEx(errMsg);
					}
				}
				sheetNo = upData.getOrderSheetNo();
				if (StringUtils.isEmpty(sheetNo)) {
					sheetNo = data.getOrderSheetNo();
				}
				tMap.put(sheetNo, sheetNo);
			}

			// 発注書Noをスタックする
			if (!tMap.isEmpty()) {
				int idx = 0;
				orderSheetNoArray = new String[tMap.size()];
				Iterator ite = tMap.keySet().iterator();
				while (ite.hasNext()) {
					String key = (String) ite.next();
					orderSheetNoArray[idx] = key;
					idx++;
				}
			}
			return orderSheetNoArray;
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 更新エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final PurchaseOrderPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 仕入先コードチェック
	 * @param searchList .
	 * @return errors .
	 */
	public ActionMessages checkForOrder(final List<PurchaseOrderList> searchList) {
		ActionErrors errors = new ActionErrors();
		boolean flg = false;
		for (PurchaseOrderList data : searchList) {
			if (!data.isOrderCheckBox()) {
				// チェックなし
				continue;
			}
			flg = true;
			if (data.getVenderCd() == null || data.getVenderCd().equals("")) {
				errors.add("", new ActionMessage("errors.purchase.vender.row",
						data.getBuySubcontractOrderNo()));

			} else {
				Vender bean = venderDao.getEntity(data.getVenderCd(), "SI");
				if (bean == null) {
					errors.add("", new ActionMessage(
							"errors.purchase.vender.row", data
									.getBuySubcontractOrderNo()));
				}
			}
		}
		if (!flg) {
			errors.add("", new ActionMessage(
					"errors.purchase.selected.checkbox"));
		}

		return errors;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 発注書一覧画面用Daoを設定します。
	 * 
	 * @param purchaseOrderListDao 発注書一覧画面用Dao
	 */
	public void setPurchaseOrderListDao(
			final PurchaseOrderListDao purchaseOrderListDao) {
		this.purchaseOrderListDao = purchaseOrderListDao;
	}

	/**
	 * 購買オーダーテーブル更新用Daoを設定します。
	 * @param purchaseSubcontractDao 購買オーダーテーブル更新用Dao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * 発番処理用ロジッククラスを設定します。
	 * @param getNumberLogic 発番処理用ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * purchaseOrderListForReporDaoを設定します。
	 * @param purchaseOrderListForReporDao purchaseOrderListForReporDao
	 */
	public void setPurchaseOrderListForReporDao(
			final PurchaseOrderListForReporDao purchaseOrderListForReporDao) {
		this.purchaseOrderListForReporDao = purchaseOrderListForReporDao;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}
}
