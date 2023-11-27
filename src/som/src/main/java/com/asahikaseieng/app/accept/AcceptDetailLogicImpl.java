/*
 * Created on 2009/02/20
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.accept;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.stockinout.StockinoutForPurchase;
import com.asahikaseieng.app.convinventory.ConvInventoryLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryResult;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.app.tax.GetTaxRatioLogic;
import com.asahikaseieng.dao.entity.codenyukalot.CodeNyukalot;
import com.asahikaseieng.dao.entity.codenyukalot.CodeNyukalotDao;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.lotinventory.LotInventory;
import com.asahikaseieng.dao.entity.lotinventory.LotInventoryDao;
import com.asahikaseieng.dao.entity.master.delivery.Delivery;
import com.asahikaseieng.dao.entity.master.delivery.DeliveryDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.entity.master.location.LocationDao;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueue;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.orderhead.OrderHead;
import com.asahikaseieng.dao.entity.orderhead.OrderHeadDao;
import com.asahikaseieng.dao.entity.performancelog.PerformanceLog;
import com.asahikaseieng.dao.entity.performancelog.PerformanceLogDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.entity.sales.Sales;
import com.asahikaseieng.dao.entity.sales.SalesDao;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailList;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailListDao;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailSales;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailSalesCarry;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailSalesCarryDao;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailSalesDao;
import com.asahikaseieng.dao.nonentity.accept.AcceptOrderNum;
import com.asahikaseieng.dao.nonentity.accept.AcceptOrderNumDao;
import com.asahikaseieng.dao.nonentity.comboboxes.accept.AcceptStockingDivisionComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.accept.AcceptStockingDivisionComboboxesDao;
import com.asahikaseieng.dao.nonentity.procedurecall.FncGetTaxCdNewCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProIfMaterialImportResultCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.sales.SalesAccountYears;
import com.asahikaseieng.dao.nonentity.sales.SalesAccountYearsDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 受入入力詳細 ロジック実装クラス
 * @author tosco
 */
public class AcceptDetailLogicImpl implements AcceptDetailLogic {

	/** 区分 その他 */
	public static final String UNIT_DIVISION_KG = "1";

	/** 区分 売上単価 */
	public static final String UNIT_DIVISION_URTANKA = "URTANKA";

	/** 区分 売上金額 */
	public static final String UNIT_DIVISION_URKINGAKU = "URKINGAKU";

	/** 区分 消費税額 */
	public static final String UNIT_DIVISION_TAX_AMOUNT = "TAX_AMOUNT";

	/** 区分 消費税率 */
	public static final String UNIT_DIVISION_TAX = "TAX";

	/** ﾃﾞｰﾀ種別 1:売上 */
	public static final BigDecimal DATA_TYPE_URI = new BigDecimal(1);

	/** ﾃﾞｰﾀ種別 3:仕入 */
	public static final BigDecimal DATA_TYPE_SIIRE = new BigDecimal(3);

	/** 分類コード 1:売上 */
	public static final String CATEGORY_DIVISION_URI = "1";

	/** 取引先区分 仕入先 */
	public static final String VENDER_DIV_SI = "SI";

	/** 取引先区分 得意先 */
	public static final String VENDER_DIV_TS = "TS";

	/** オーダー区分 1:原材料 */
	public static final BigDecimal ORDER_DIVISION1 = new BigDecimal(1);

	/** オーダー区分 2:外注製品(直送) */
	public static final BigDecimal ORDER_DIVISION2 = new BigDecimal(2);

	/** オーダー区分 3:外注製品(非直送) */
	public static final BigDecimal ORDER_DIVISION3 = new BigDecimal(3);

	/** オーダー区分 4:スポット品 */
	public static final BigDecimal ORDER_DIVISION4 = new BigDecimal(4);

	/** オーダー区分 5:仕入直送品 */
	public static final BigDecimal ORDER_DIVISION5 = new BigDecimal(5);

	/** オーダー区分 6:仕入在庫品 */
	public static final BigDecimal ORDER_DIVISION6 = new BigDecimal(6);

	/** 入力区分 2:仕入入力 */
	public static final BigDecimal INPUT_DIVISION_SIIRE = new BigDecimal(2);

	/** 算出区分 1:明細単位 */
	public static final BigDecimal CALC_DIVISION_MEISAI = new BigDecimal(1);

	/** 算出区分 4:自社ﾏｽﾀ */
	public static final BigDecimal CALC_DIVISION_COMPANY = new BigDecimal(4);

	/** 消費税課税区分 1:外税 */
	public static final BigDecimal TAX_DIVISION_OUT = new BigDecimal(1);

	/** 消費税課税区分 2:内税 */
	public static final BigDecimal TAX_DIVISION_IN = new BigDecimal(2);

	/** 在庫管理区分 3:更新除外 */
	public static final BigDecimal STOCK_DIVISION_NOT_UPDATE = new BigDecimal(3);

	/** ロット管理区分 1:しない */
	public static final BigDecimal LOT_DIVISION_NOT_MANAGE = new BigDecimal(1);

	/** 納入先コードが油脂である運賃品目 */
	public static final String CONST_DIVISION_YUSI_ITEM = "39999999";

	/** 納入先コードが化成品である運賃品目 */
	public static final String CONST_DIVISION_KASEI_ITEM = "09999999";

	/** エラーログ出力用モジュールコード */
	public static final String PRO_IF_MATERIAL_IMPORT_RESULT = "PRO_IF_MATERIAL_IMPORT_RESULT";

	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	/** ローリー区分 2:ローリー */
	private static final BigDecimal LORRY_DIVISION_LORRY = new BigDecimal(2);

	/** 仕入在庫品 */
	private static final BigDecimal ITEM_DIVISION_5 = new BigDecimal(5);

	/** 外注製品(直送) */
	private static final BigDecimal ITEM_DIVISION_6 = new BigDecimal(6);

	/** 外注製品(非直送) */
	private static final BigDecimal ITEM_DIVISION_7 = new BigDecimal(7);

	/** 支給区分区分 2:無償 */
	private static final String MUSYO_SIKYU = "2";

	/** 発番処理用ロジッククラス */
	private GetNumberLogic getNumberLogic;

	/** 在庫数量換算ロジッククラス */
	private ConvInventoryLogic convInventoryLogic;

	/** 受入入力 詳細画面用Dao */
	private AcceptDetailListDao acceptDetailListDao;

	/** 受入入力 詳細画面用Dao */
	private AcceptDetailSalesCarryDao acceptDetailSalesCarryDao;

	/** 受入入力 売上トラン登録用データ取得Dao */
	private AcceptDetailSalesDao acceptDetailSalesDao;

	/** 購買外注オーダ更新用Dao */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/** 売上トラン更新用Dao */
	private SalesDao salesDao;

	/** 仕入区分コンボボックス用DAO */
	private AcceptStockingDivisionComboboxesDao acceptStockingDivisionComboboxesDao;

	/** 部署マスタ用Dao */
	private OrganizationDao organizationDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 品目マスタ用Dao * */
	private ItemDao itemDao;

	/** プロシージャコール用Dao */
	private ProcedureCallDao procedureCallDao;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	/** 購入品扱い属性キュー用Dao */
	private PurchaseAttributeQueueDao purchaseAttributeQueueDao;

	/** ロケーションマスタ用Dao */
	private LocationDao locationDao;

	/** 受注ヘッダ用Dao */
	private OrderHeadDao orderHeadDao;

	/** 納入先マスタ用Dao */
	private DeliveryDao deliveryDao;

	/** 入荷ロット用Dao */
	private CodeNyukalotDao codeNyukalotDao;

	/** 売上勘定年月取得用Dao */
	private SalesAccountYearsDao salesAccountYearsDao;

	/** 運賃計上処理用DAO */
	private AcceptOrderNumDao acceptOrderNumDao;

	/** エラーログ出力用Dao */
	private PerformanceLogDao performanceLogDao;

	/** ロット在庫用Dao */
	private LotInventoryDao lotInventoryDao;

	/** 得意先用DAO */
	private VenderDao venderDao;

	/** 分類コード 前受金 */
	public static final String CATEGORY_DIVISION_ADVANCE = "11";

	/** 分類コード 売掛金 */
	public static final String CATEGORY_DIVISION_DEPOSIT = "1";

	/** 消費税取得用ロジッククラス */
	private GetTaxRatioLogic getTaxRatioLogic;

	/**
	 * コンストラクタ.
	 */
	public AcceptDetailLogicImpl() {
	}

	/**
	 * 仕入区分コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createAcceptStockingDivisionCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 仕入区分検索
		List<AcceptStockingDivisionComboboxes> list = acceptStockingDivisionComboboxesDao
				.findStockingDivision();
		if (list != null) {
			for (AcceptStockingDivisionComboboxes bean : list) {
				ComboBoxItems combo = new ComboBoxItems();
				combo.setValues(bean.getCategoryDivision().toString());
				combo.setLabales(bean.getCategoryDivision().toString() + ":"
						+ bean.getCategoryName().toString());
				res.add(combo);
			}
		}

		return res;
	}

	/**
	 * 購買データ検索処理を行なう.
	 * @param purchaseNo 購買NO
	 * @param check 数値項目用表示ロジッククラス
	 * @param kbn 区分(A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 *            ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 * @return List<ArrivalDetailList> 詳細データ
	 * @throws NoDataException データが存在しない例外
	 */
	public List<AcceptDetailList> getEntity(final String purchaseNo,
			final CheckDigitUtilsLogic check, final String kbn)
			throws NoDataException {
		String strOrderQuantity = null;
		String strOrderConvertQuantity = null;
		String strAcceptQuantity = null;
		String strIncreaseQuantity = null;
		String strArrivalQuantity = null;
		String strAcceptDate = AecDateUtils.dateFormat(
			AecDateUtils.getCurrentTimestamp(), "yyyy/MM/dd");

		List<AcceptDetailList> list = acceptDetailListDao.getEntity(purchaseNo,
			kbn);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		int i = 0;
		for (AcceptDetailList bean : list) {

			// 受入登録済みの場合(入庫ロケ、受入数量、受入日を購買トランザクションとする）
			if (PurchaseStatus.ACCEPTED.equals(bean.getStatus())) {

				// 購買トランザクションのデータを取得
				PurchaseSubcontract purBean = purchaseSubcontractDao
						.getEntity(purchaseNo);
				bean.setHousingLocationCd(purBean.getHousingLocationCd());
				bean.setAcceptQuantity(purBean.getAcceptQuantity());
				bean.setAcceptDate(purBean.getAcceptDate());

			}

			// 数量
			strOrderQuantity = check.format(bean.getUnitDiv(), VENDER_DIV_SI,
				bean.getVenderCd(), bean.getOrderQuantity());
			bean.setStrOrderQuantity(strOrderQuantity);

			// 重量
			strOrderConvertQuantity = check.format(UNIT_DIVISION_KG,
				VENDER_DIV_SI, bean.getVenderCd(),
				bean.getOrderConvertQuantity());
			bean.setStrOrderConvertQuantity(strOrderConvertQuantity);

			// 受入数量
			strAcceptQuantity = check.format(bean.getUnitDiv(), VENDER_DIV_SI,
				bean.getVenderCd(), bean.getAcceptQuantity());
			bean.setStrAcceptQuantity(strAcceptQuantity);

			// 増付数量
			strIncreaseQuantity = check.format(bean.getUnitDiv(),
				VENDER_DIV_SI, bean.getVenderCd(), bean.getIncreaseQuantity());
			bean.setStrIncreaseQuantity(strIncreaseQuantity);

			// 区分
			bean.setKbn(kbn);

			// 現在日付を初期値として設定
			if (bean.getAcceptDate() == null) {
				bean.setStrAcceptDate(strAcceptDate);
			} else {
				bean.setStrAcceptDate(AecDateUtils.dateFormat(
					bean.getAcceptDate(), "yyyy/MM/dd"));
			}

			// 入荷数量
			strArrivalQuantity = check.format(bean.getUnitDiv(), VENDER_DIV_SI,
				bean.getVenderCd(), bean.getArrivalQuantity());
			bean.setStrArrivalQuantity(strArrivalQuantity);
			if (i > 0) {
				bean.setPurchaseNo(null);
			}
			i++;
		}

		return list;
	}

	/**
	 * 発注番号にひもづくデータの受入数量の合計値を取得する.
	 * @param buySubcontractOrderNo 発注番号
	 * @param slipNo 仕入番号
	 * @return BigDecimal 受入数量の合計値
	 */
	public BigDecimal getSumAcceptQty(final String buySubcontractOrderNo,
			final String slipNo) {
		return acceptDetailListDao.getSumAcceptQty(buySubcontractOrderNo,
			slipNo);
	}

	/**
	 * 未受入データ件数を取得する.
	 * @param buySubcontractOrderNo 発注番号
	 * @return BigDecimal 未受入データ件数
	 */
	public BigDecimal getCountNotAccept(final String buySubcontractOrderNo) {
		return acceptDetailListDao.getCountNotAccept(buySubcontractOrderNo);
	}

	/**
	 * 品目が無償支給か否かを取得する
	 * @param itemCd 品目コード
	 * @return True:無償支給品
	 */
	public boolean isMusyoSikyu(final String itemCd) {
		boolean flg = false;
		Item itemBean = itemDao.getEntity(itemCd);
		if (itemBean != null) {
			PurchaseAttributeQueue itemAttr = purchaseAttributeQueueDao
					.getEntity(itemBean.getItemCd(), itemBean.getVersion());
			if (itemAttr != null) {
				// ローリー原料の場合
				if (MUSYO_SIKYU.compareTo(itemAttr.getSuppliedGoodsDivision()) == 0) {
					flg = true;
				}
			}
		}
		return flg;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 部署マスタ、ロケーションマスタにデータがない場合はエラーとする。
	 * @param frm 受入入力画面FORM
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final AcceptDetailForm frm) {
		ActionMessages errors = new ActionMessages();
		String checkLorryItemCd = null;
		boolean isLorry = false;

		// 部署マスタを検索
		Organization orgBean = organizationDao.getEntity(frm
				.getOrganizationCd());
		if (orgBean == null) {
			// データが存在しない場合
			errors = addError(errors, "errors.accept.no.organization");
		}

		Item itemBean = itemDao.getEntity(frm.getItemCd());
		if (itemBean == null) {
			// データが存在しない場合
			errors = addError(errors, "errors.accept.no.item.cd");
		} else {
			PurchaseAttributeQueue itemAttr = purchaseAttributeQueueDao
					.getEntity(itemBean.getItemCd(), itemBean.getVersion());
			if (itemAttr != null) {
				// ローリー原料の場合
				if (LORRY_DIVISION_LORRY.compareTo(itemAttr.getLorryDivision()) == 0) {
					checkLorryItemCd = itemBean.getParentItemCd(); // 親品目
					isLorry = true;
				}
			}
		}
		int index = 0;
		for (AcceptDetailList bean : frm.getDetailList()) {
			// ロケーションマスタを検索
			Location locationBean = locationDao.getEntity(bean
					.getHousingLocationCd());
			if (locationBean == null) {
				if (!bean.getKbn().equals("F") && !bean.getKbn().equals("G")) {
					// データが存在しない場合
					errors = addError(errors, "errors.accept.no.location.row",
						index + 1);
				}
			} else {
				if (locationBean.getAvailableFlg().equals(BigDecimal.ZERO)) {
					errors = addError(errors,
						"errors.accept.no.location.stock.row", index + 1);
				} else {
					// ローリー原料品目コードチェック
					if (StringUtils.isNotEmpty(checkLorryItemCd)) {
						if (!checkLorryItemCd.equals(locationBean.getItemCd())) {
							errors = addError(errors,
								"errors.accept.no.location.item.cd.row",
								index + 1);
						}
					} else {
						if (isLorry) {
							errors = addError(errors,
								"errors.accept.no.location.item.cd.row",
								index + 1);
						} else {
							if (StringUtils
									.isNotEmpty(locationBean.getItemCd())
									&& !locationBean.getItemCd().equals(
										bean.getItemCd())) {
								errors = addError(errors,
									"errors.accept.no.location.item.cd.row",
									index + 1);
							}
						}
					}
				}
			}
			index++;
		}

		return errors;
	}

	/**
	 * 購買外注データを購買NO(KEY)で全項目取得する.
	 * @param purchaseNo 購買NO
	 * @return PurchaseSubcontract 購買外注データBean
	 * @throws NoDataException 対象データ無しエラー
	 */
	public PurchaseSubcontract getEntity(final String purchaseNo)
			throws NoDataException {
		PurchaseSubcontract bean = purchaseSubcontractDao.getEntity(purchaseNo);
		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}

	/**
	 * 購買オーダーテーブル更新処理を行う.
	 * 
	 * @param frm 受入入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @param bean 購買外注オーダテーブルBean
	 * @param check 数値項目用表示ロジッククラス
	 * @return PurchaseSubcontract 購買外注オーダテーブルBean
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public PurchaseSubcontract update(final AcceptDetailForm frm,
			final String tantoCd, final PurchaseSubcontract bean,
			final CheckDigitUtilsLogic check) throws NoDataException, Exception {

		try {
			PerformanceLog log = new PerformanceLog();

			PurchaseSubcontract updBean = new PurchaseSubcontract();

			// 値を更新用Beanにコピー
			IgnoreCaseBeanUtils.copyProperties(updBean, bean);

			// 共通項目セット
			setCommonInfo(updBean, frm, tantoCd);
			boolean firstFlg = true;
			int index = 0;
			for (AcceptDetailList listBean : frm.getDetailList()) {

				// 在庫更新処理
				StockinoutForPurchase stock = new StockinoutForPurchase(
						zaiCtrlDao);

				// ロット分割データは必ず１件ではない
				updBean.setSupplierLotno(listBean.getSupplierLotno()); // ﾒｰｶｰﾛｯﾄ番号
				updBean.setHousingLocationCd(listBean.getHousingLocationCd()); // 入庫ﾛｹｰｼｮﾝ
				updBean.setAcceptQuantity(listBean.getAcceptQuantity()); // 受入数量
				updBean.setRowNo(listBean.getRowNo());
				updBean.setPurchaseNo(listBean.getPurchaseNo());
				updBean.setInputDate(listBean.getInputDate());
				updBean.setInputorCd(listBean.getInputorCd());
				updBean.setUpdateDate(listBean.getUpdateDate());
				// 受入重量
				ConvInventoryResult result = convInventoryLogic.packToWeight(
					updBean.getItemCd(), updBean.getAcceptQuantity(),
					BigDecimal.ZERO);
				if (result.getCode().equals(new BigDecimal(1))) { // 品目未登録
					AcceptLogicException ex = new AcceptLogicException(
							"errors.conv.inventory.calc", "");
					ex.setModuleCd("AcceptDetailLogicImpl.update");
					ex.setInsideErrCd(updBean.getItemCd());
					ex.setInsideErrMsg("convInventoryLogic :品目が登録されていません。");
					throw ex;
				}
				BigDecimal convQty = check.round(UNIT_DIVISION_KG,
					VENDER_DIV_SI, updBean.getVenderCd(),
					result.getInventoryQty());
				updBean.setAcceptConvertQuantity(convQty);

				updBean.setIncreaseQuantity(listBean.getIncreaseQuantity()); // 増付数量
				updBean.setAcceptDate(listBean.getAcceptDate()); // 受入日
				updBean.setTaxCd(getTaxCd(listBean.getStrAcceptDate(),listBean.getItemCd(),Constants.VENDER_DIVISION_SI
					,listBean.getVenderCd(),Constants.STOCKING_CATEGORY,null,listBean.getReducedTaxTargetFlg()).toString());

				// ステータスが受入登録済みの場合（更新の場合）
				if (PurchaseStatus.ACCEPTED.toString().equals(frm.getStatus())) {

					// 在庫が存在するかチェック
					if (isStock(frm.getPurchaseNo(), index)) {
						// 在庫が正常に存在する場合 戻しを行う
						stock.dereceivePurchase(updBean.getPurchaseNo(),
							tantoCd);
					} else {
						// 在庫が既に仕様済みの場合 エラーメッセージを表示する
						AcceptLogicException ex = new AcceptLogicException(
								"errors.accept.lot.use", new BigDecimal(
										index + 1).toString());
						throw ex;

					}

				}

				// 購買外注オーダ更新処理
				if (StringUtils.isEmpty(updBean.getPurchaseNo()) || !firstFlg) {
					updBean.setPurchaseNo(null);
					updBean.setInputorCd(tantoCd);
					updBean.setInputDate(AecDateUtils.getCurrentTimestamp());
					purchaseSubcontractDao.insert(updBean);
					// 登録した購買Noを取得する
					AcceptDetailList insBean = acceptDetailListDao
							.getPurchaseNo(bean.getBuySubcontractOrderNo(),
								updBean.getOrderDivideNo(), updBean.getRowNo()
										.toString());
					updBean.setPurchaseNo(insBean.getPurchaseNo());
				} else {
					purchaseSubcontractDao.update(updBean);
				}

				log.setModuleCd("ACCEPT_TEST");
				log.setClient(tantoCd);
				log.setErrorDate(AecDateUtils.getCurrentTimestamp());

				log.setErrorDate(AecDateUtils.getCurrentTimestamp());
				log.setSqlStr("在庫更新処理 開始");
				log.setErrorMes("6");
				performanceLogDao.insert(log);

				/* 在庫更新－受入入力 */
				stock.receivePurchase(updBean.getPurchaseNo(),
					updBean.getUpdatorCd());
				// 完納の場合は、発注完了を行う
				if (updBean.getDeliveryComp().compareTo(BigDecimal.ONE) == 0) {
					/* 在庫更新－発注完了 */
					stock.completePurchase(bean.getBuySubcontractOrderNo(),
						updBean.getUpdatorCd());
				}
				log.setErrorDate(AecDateUtils.getCurrentTimestamp());
				log.setErrorMes("7");
				log.setSqlStr("在庫更新処理 終了");
				performanceLogDao.insert(log);
				index++;
			}
			return updBean;
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}
	
	/**
	 * 税コードを取得
	 * 軽減税率対応　20190823
	 * @param taxCd 税コード
	 * @return TaCd 税コード
	 */
	public String getTaxCd(final String date,final String itemCd,final String venderDivision
			,final String venderCd,final String category,final String taxRatio,final String taxFreeFlg) {

		FncGetTaxCdNewCallDto dto = new FncGetTaxCdNewCallDto();
		dto.setpParamDate(date);
		dto.setpParamItemCd(itemCd);
		dto.setpParamVenderDivision(venderDivision);
		dto.setpParamVenderCd(venderCd);
		dto.setpParamCategory(category);
		dto.setpParamTaxRatio(taxRatio);
		dto.setpParam1(null);
		dto.setpParam2(null);
		dto.setpParam3(null);
		dto.setpParamTaxFreeFlg(taxFreeFlg);


		// 出荷指図自動作成プロシージャ実行
		procedureCallDao.funGetTaxCdNew(dto);

		return dto.getpTaxCd();
	}

	/**
	 * 購買オーダーテーブル削除処理を行う.
	 * 
	 * @param frm 受入入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @param bean 購買外注オーダテーブルBean
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void delete(final AcceptDetailForm frm, final String tantoCd,
			final PurchaseSubcontract bean) throws NoDataException, Exception {

		try {
			PurchaseSubcontract delBean = new PurchaseSubcontract();
			// 値を更新用Beanにコピー
			IgnoreCaseBeanUtils.copyProperties(delBean, frm);

			delBean.setUpdateDate(bean.getUpdateDate());

			int index = 0;

			for (AcceptDetailList listBean : frm.getDetailList()) {

				IgnoreCaseBeanUtils.copyProperties(delBean, listBean);

				// 在庫更新処理
				StockinoutForPurchase stock = new StockinoutForPurchase(
						zaiCtrlDao);

				// 在庫が存在するかチェック
				if (isStock(frm.getPurchaseNo(), index)) {
					/* 在庫更新 */
					stock.dereceivePurchase(delBean.getPurchaseNo(), tantoCd);
				} else {
					// 在庫が既に仕様済みの場合 エラーメッセージを表示する
					AcceptLogicException ex = new AcceptLogicException(
							"errors.accept.lot.use",
							new BigDecimal(index + 1).toString());
					throw ex;

				}

				purchaseSubcontractDao.delete(delBean);

				index++;
			}

			return;
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 更新、削除時の在庫をチェックする処理
	 * 
	 * @param purchaseNo 購買番号
	 * @param index 行番号
	 * @return 結果
	 */
	private boolean isStock(final String purchaseNo, final int index) {

		// 購買データ取得
		PurchaseSubcontract purBean = purchaseSubcontractDao
				.getEntity(purchaseNo);

		// 品目マスタからロット管理区分を取得
		Item itemBean = itemDao.getEntity(purBean.getItemCd());

		// ロット在庫の数量を取得
		LotInventory lotBean = lotInventoryDao.getEntity(purBean.getItemCd(),
			purBean.getHousingLocationCd(), purBean.getLotNo());

		BigDecimal purQty = BigDecimal.ZERO;

		// 品目の区分によって分岐
		if (itemBean.getTypeDivision().equals(ITEM_DIVISION_5)
				|| itemBean.getTypeDivision().equals(ITEM_DIVISION_6)
				|| itemBean.getTypeDivision().equals(ITEM_DIVISION_7)) {
			// 仕入在庫品,外注製品(直送),外注製品(非直送)の場合
			// 受入数量でチェックする
			purQty = purBean.getAcceptQuantity();
		} else {
			// 仕入在庫品,外注製品(直送),外注製品(非直送) 以外の場合
			// 受入重量でチェックする
			purQty = purBean.getAcceptConvertQuantity();
		}

		// ロット在庫が無い場合エラー
		if (lotBean != null) {

			// ロット管理しない場合
			if (itemBean.getLotDivision().equals(BigDecimal.ONE)) {

				// 在庫量より受入数量が多い場合はエラー
				if (lotBean.getInventoryQty().doubleValue() < purQty
						.doubleValue()) {
					return false;

				}

			} else {
				// ロット管理する場合
				// ロット在庫の数量と受入数量を比較
				if (!lotBean.getInventoryQty().equals(purQty)) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 計装IF入荷ロットコード対応にデータを書き込む
	 * 
	 * @param frm 受入入力画面FORM
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void setCodeNyukalot(final AcceptDetailForm frm)
			throws NoDataException, Exception {

		try {

			for (AcceptDetailList listBean : frm.getDetailList()) {

				boolean isLorry = false;

				Item itemBean = itemDao.getEntity(listBean.getItemCd());
				if (itemBean == null) {
					continue;
				} else {
					PurchaseAttributeQueue itemAttr = purchaseAttributeQueueDao
							.getEntity(itemBean.getItemCd(),
								itemBean.getVersion());
					if (itemAttr != null) {
						// ローリー原料の場合
						if (LORRY_DIVISION_LORRY.compareTo(itemAttr
								.getLorryDivision()) == 0) {
							isLorry = true;
						}
					}
				}

				if (isLorry) {

					CodeNyukalot insertBean = new CodeNyukalot();

					if (codeNyukalotDao.getEntity(listBean.getLotNo()) == null) {

						insertBean.setNyukalot(AecStringUtils
								.editSpecifiedSize(listBean.getLotNo(), 11)); // 入荷ロット
						insertBean.setGenzaicode(AecStringUtils
								.editSpecifiedSize(listBean.getItemCd(), 8)); // 原材料コード

						int insertNum = codeNyukalotDao.insert(insertBean);
						if (insertNum != 1) {
							// 更新エラー
							throw new SQLRuntimeException(null);
						}

					}
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 一意制約エラー
			throw new DuplicateRuntimeException(0);
		}

	}

	/**
	 * 購買オーダーテーブル更新処理(DELETE/INSERT)を行う. 発注残数量＞０で次回納品希望日が指定されている場合、分納データを新規作成する。
	 * 
	 * @param frm 受入入力画面FORM
	 * @param organizationCd ログイン者の部署コード
	 * @param tantoCd ログイン者コード
	 * @param bean 登録前検索購買データBean
	 * @param check 数値項目用表示ロジッククラス
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void insert(final AcceptDetailForm frm, final String organizationCd,
			final String tantoCd, final PurchaseSubcontract bean,
			final CheckDigitUtilsLogic check) throws NoDataException, Exception {

		String bunnoFlg = "0"; // 分納フラグ(0:分納無 1:分納有)
		String orderDivideNo = null; // 発注番号分納枝番
		String orderDivideNoNew = null; // 発注番号分納枝番(新規分納データ用)

		if (frm == null) {
			throw new IllegalArgumentException("form == null");
		}

		try {
			// 分納有無判定
			BigDecimal blanceOrderQty = AecNumberUtils.convertBigDecimal(frm
					.getBalanceOrderQuantity());
			// 発注残数量＞０の場合
			if (blanceOrderQty != null
					&& blanceOrderQty.compareTo(new BigDecimal(0)) > 0) {
				// 次回納品希望日が指定されている場合
				if (StringUtils.isNotEmpty(frm.getNextDeliverlimitDate())) {
					bunnoFlg = "1"; // 分納フラグ

					// 発注番号分納枝番 設定
					if (frm.getOrderDivideNo() == null) {
						orderDivideNo = "001";
						orderDivideNoNew = "002";
					} else {
						orderDivideNo = frm.getOrderDivideNo();
						int divNo = Integer.parseInt(orderDivideNo);
						divNo++;
						// ゼロパディング
						orderDivideNoNew = StringUtils.leftPad(
							Integer.toString(divNo), 3, "0");
					}
				}
			}

			// ロット分割先頭データ(登録済)をいったん削除
			purchaseSubcontractDao.delete(bean);

			// 仕入番号取得
			String slipNo = getNumberLogic.getSiSlipNo();

			// 在庫更新処理
			String errMsg = "errors.accept.stock.update";
			StockinoutForPurchase stock = new StockinoutForPurchase(zaiCtrlDao);
			BigDecimal totalqty = BigDecimal.ZERO;

			Timestamp acceptDate = frm.getDetailList().get(0).getAcceptDate(); // 売上運賃計上の売上日用

			for (AcceptDetailList listBean : frm.getDetailList()) {

				PurchaseSubcontract updBean = new PurchaseSubcontract();
				// 値を更新用Beanにコピー
				IgnoreCaseBeanUtils.copyProperties(updBean, bean);

				updBean.setPurchaseNo(null); // 購買NO
				if ("1".equals(bunnoFlg)) {
					updBean.setOrderDivideNo(orderDivideNo); // 発注番号分納枝番
				}
				updBean.setSlipNo(slipNo); // 仕入番号
				updBean.setRowNo(listBean.getRowNo());

				// 共通項目セット
				setCommonInfo(updBean, frm, tantoCd);

				// 次回納品希望日
				String nextDelDate = frm.getNextDeliverlimitDate() + " ";
				if (StringUtils.isNotEmpty(frm.getNextDeliverlimitDateTime())) {
					nextDelDate = nextDelDate
							+ frm.getNextDeliverlimitDateTime();
				} else {
					nextDelDate = nextDelDate + "00:00";
				}
				updBean.setNextDeliverlimitDate(AecDateUtils
						.getTimestampYmdHmFormat(nextDelDate,
							"yyyy/MM/dd HH:mm"));

				// ロット分割の明細内容
				updBean.setSupplierLotno(listBean.getSupplierLotno()); // ﾒｰｶｰﾛｯﾄ番号
				getLotNo(updBean); // 入荷ロット番号
				listBean.setLotNo(updBean.getLotNo());
				listBean.setItemCd(updBean.getItemCd());

				updBean.setHousingLocationCd(listBean.getHousingLocationCd()); // 入庫ﾛｹｰｼｮﾝ(NULL)
				updBean.setAcceptQuantity(listBean.getAcceptQuantity()); // 受入数量
				totalqty = totalqty.add(listBean.getAcceptQuantity());
				// 受入重量
				ConvInventoryResult result = convInventoryLogic.packToWeight(
					updBean.getItemCd(), updBean.getAcceptQuantity(),
					BigDecimal.ZERO);
				if (result.getCode().equals(new BigDecimal(1))) { // 品目未登録
					throw new LogicExceptionEx("errors.conv.inventory.calc");
				}
				BigDecimal convQty = check.round(UNIT_DIVISION_KG,
					VENDER_DIV_SI, updBean.getVenderCd(),
					result.getInventoryQty());
				updBean.setAcceptConvertQuantity(convQty);

				updBean.setIncreaseQuantity(listBean.getIncreaseQuantity()); // 増付数量
				updBean.setAcceptDate(listBean.getAcceptDate()); // 受入日
				updBean.setTaxCd(getTaxCd(listBean.getStrAcceptDate(),listBean.getItemCd(),Constants.VENDER_DIVISION_SI
					,listBean.getVenderCd(),Constants.STOCKING_CATEGORY,null,listBean.getReducedTaxTargetFlg()).toString());

				// ロット分割データ登録処理
				purchaseSubcontractDao.insert(updBean);

				String strRowNo = null;
				if (updBean.getRowNo() != null) {
					strRowNo = updBean.getRowNo().toString();
				}
				// 登録した購買Noを取得する
				AcceptDetailList insBean = acceptDetailListDao.getPurchaseNo(
					bean.getBuySubcontractOrderNo(),
					updBean.getOrderDivideNo(), strRowNo);
				/* 在庫更新－受入入力 */
				// try {
				// if (!stock.receivePurchase(insBean.getPurchaseNo(), updBean
				// .getUpdatorCd())) {
				// throw new LogicExceptionEx(errMsg);
				// }
				// } catch (LogicExceptionEx e) {
				// throw new LogicExceptionEx(errMsg);
				// }
				stock.receivePurchase(insBean.getPurchaseNo(),
					updBean.getUpdatorCd());
			}

			// 仕入直送品の場合
			if (ORDER_DIVISION5.equals(bean.getOrderDivision())) {
				AcceptDetailList listBean = frm.getDetailList().get(0);
				listBean.setAcceptQuantity(totalqty);
				// 売上データ登録処理
				insertSales(listBean, organizationCd, tantoCd, bean, check);
			}

			// 完納の場合は、発注完了を行う
			if (frm.getDeliveryComp()) {
				try {
					/* 在庫更新－発注完了 */
					if (!stock.completePurchase(
						bean.getBuySubcontractOrderNo(), tantoCd)) {
						throw new LogicExceptionEx(errMsg);
					}
				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
				}
			}

			// 分納有の場合
			if ("1".equals(bunnoFlg)) {
				// 分納データ作成
				insertBunnoData(frm, orderDivideNoNew, tantoCd, bean);
			}
			// 仕入直送品の場合の運賃計上処理
			if (ORDER_DIVISION5.equals(bean.getOrderDivision())) {
				// 売上データ登録処理
				insertCarrySales(acceptDate, organizationCd, tantoCd, bean,
					check);
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 一意制約エラー
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * PRO_IF_MATERIAL_IMPORT_RESULTを実行する
	 * @param frm 購買外注オーダテーブル
	 * @param tantoCd 担当者コード
	 * @throws AcceptLogicException プロシージャ実行時エラー
	 */
	public void callProIf(final AcceptDetailForm frm, final String tantoCd)
			throws AcceptLogicException {
		String errKey = "errors.accept.update.if.table.detail";
		String errDetailKey = null;
		ProIfMaterialImportResultCallDto dto = new ProIfMaterialImportResultCallDto();
		AcceptDetailList bean = frm.getDetailList().get(0);
		dto.setPStrLotNo(bean.getLotNo());
		dto.setPNumQty(bean.getAcceptQuantity());
		dto.setPStrLocation(bean.getHousingLocationCd());
		dto.setPNumOrderDivision(bean.getOrderDivision());
		dto.setPStrItemCd(bean.getItemCd());
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proIfMaterialImportResult(dto);
			// 異常終了の場合
			int intRet = dto.getPNumRet().intValue();
			if (intRet != 0) {
				switch (intRet) {
				case -1:
					errDetailKey = "errors.accept.pro.if.lot.no";
					break;
				case -2:
					errDetailKey = "errors.accept.pro.if.stock.update";
					break;
				case -3:
					errDetailKey = "errors.accept.pro.if.item.cd";
					break;
				case -4:
					errDetailKey = "errors.accept.pro.if.accept.qty";
					break;
				default:
					errDetailKey = "errors.accept.pro.if";
					break;
				}
				AcceptLogicException ex = new AcceptLogicException(errKey,
						errDetailKey);
				ex.setModuleCd(PRO_IF_MATERIAL_IMPORT_RESULT);
				ex.setInsideErrCd(dto.getPStrErrorCd());
				ex.setInsideErrMsg(dto.getPStrErrorMsg());
				throw ex;
			}
		} catch (LogicExceptionEx e) {
			AcceptLogicException ex = new AcceptLogicException(errKey,
					"errors.accept.pro.if");
			ex.setModuleCd(PRO_IF_MATERIAL_IMPORT_RESULT);
			ex.setInsideErrCd(dto.getPStrErrorCd());
			ex.setInsideErrMsg(dto.getPStrErrorMsg());
			throw ex;
		}

	}

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void outPutErrorLog(final String strModuleCd,
			final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception {

		ErrorLog log = new ErrorLog();

		log.setModuleCd(strModuleCd);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		log.setErrorMes(strErrorCd);
		String cutMsg = strErrorMsg;
		if (StringUtils.isNotEmpty(strErrorMsg)
				&& strErrorMsg.length() > ERROR_LOG_SQL_STR_MAX_LEN) {
			cutMsg = StringUtils.substring(strErrorMsg, 0,
				ERROR_LOG_SQL_STR_MAX_LEN);
		}
		log.setSqlStr(cutMsg);

		try {
			errorLogDao.insert(log);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 受入入力画面の共通項目(ロット分割以外)の値設定
	 * @param updBean 購買外注オーダ更新Bean
	 * @param frm 受入入力画面Form
	 * @param tantoCd 担当者コード
	 */
	private void setCommonInfo(final PurchaseSubcontract updBean,
			final AcceptDetailForm frm, final String tantoCd) {
		updBean.setOrganizationCd(frm.getOrganizationCd()); // 部署コード
		updBean.setStatus(PurchaseStatus.ACCEPTED); // 6:受入登録済
		if (isMusyoSikyu(updBean.getItemCd())) {
			updBean.setStatus2(new BigDecimal(3)); // 無償支給品なら 3:仕入承認済み
		} else {
			updBean.setStatus2(new BigDecimal(5)); // 5:未登録
		}
		if (frm.getDeliveryComp()) {
			updBean.setDeliveryComp(BigDecimal.ONE); // 完納フラグ(1:完納)
		} else {
			updBean.setDeliveryComp(BigDecimal.ZERO); // 完納フラグ(0:未完納)
		}

		// 分類マスタ検索
		BigDecimal dataTotalDivision = acceptDetailListDao
				.getDataTotalDivision(frm.getCategoryDivision());
		updBean.setDataType(DATA_TYPE_SIIRE); // データ種別(3:仕入)
		updBean.setCategoryDivision(frm.getCategoryDivision()); // 仕入区分(分類コード)
		updBean.setDataTotalDivision(dataTotalDivision); // 分類コード

		updBean.setOrderSheetRemark2(frm.getOrderSheetRemark2()); // 発注書備考
		updBean.setRemark2(frm.getRemark2()); // 備考
		updBean.setUpdatorCd(tantoCd); // 更新者
	}

	/**
	 * 入荷ロット番号取得
	 * @param updBean 購買外注オーダBean
	 * @throws LogicException 発番エラー
	 */
	private void getLotNo(final PurchaseSubcontract updBean)
			throws LogicException {
		String lotNo = null;
		BigDecimal orderDiv = updBean.getOrderDivision();

		Item itemBean = itemDao.getEntity(updBean.getItemCd());
		if (itemBean != null) {
			// 在庫管理区分3:更新除外またはロット管理区分1:しない場合は発番なし
			if ((STOCK_DIVISION_NOT_UPDATE.equals(itemBean.getStockDivision()))
					|| (LOT_DIVISION_NOT_MANAGE.equals(itemBean
							.getLotDivision()))) {
				updBean.setLotNo(Constants.LOTNO_WITHOUT_LOT);
				return;
			} else {
				if (orderDiv.equals(ORDER_DIVISION1)
						|| orderDiv.equals(ORDER_DIVISION4)) {
					// 原材料(自社ローリー・外注)の場合
					// スポット品の場合
					lotNo = getNumberLogic.getNLotNo(null); // 'N'+西暦6桁+連番4桁
				} else if (orderDiv.equals(ORDER_DIVISION2)
						|| orderDiv.equals(ORDER_DIVISION3)
						|| orderDiv.equals(ORDER_DIVISION5)
						|| orderDiv.equals(ORDER_DIVISION6)) {
					// 外注製品(直送)の場合
					// 外注製品(非直送)、仕入直送品
					// 仕入在庫品は入荷対象のためとおらない
					lotNo = getNumberLogic.getPLotNo(null); // 'P'+西暦6桁+連番4桁
				}
				// 以外のオーダー区分は、発番エラーとする
			}
		}
		if (lotNo == null) {
			// 発番に失敗した場合
			throw new LogicException();
		}
		updBean.setLotNo(lotNo);
	}

	/**
	 * 仕入直送品の場合、売上データを登録する。
	 * 
	 * @param listBean ロット分割の１明細データ
	 * @param organizationCd ログイン者の部署コード
	 * @param tantoCd 担当者コード
	 * @param bean 登録前検索購買データ
	 * @param check 数値項目用表示ロジッククラス
	 * @param NoDataException 対象データなしエラー
	 * @param Exception 例外
	 */
	private void insertSales(final AcceptDetailList listBean,
			final String organizationCd, final String tantoCd,
			final PurchaseSubcontract bean, final CheckDigitUtilsLogic check)
			throws NoDataException, Exception {
		// 会社コード取得
		String companyCd = acceptDetailSalesDao.getCompanyCd();

		// 売上トラン登録用データ取得(受注関連データ)
		AcceptDetailSales salesBean = acceptDetailSalesDao.getSalesData(
			tantoCd, companyCd, bean.getOrderNo(), bean.getOrderRowNo());
		if (salesBean == null) {
			// 対象データなし
			throw new LogicException();
		}

		// 売上トランデータ設定
		Sales insBean = setSalesData(listBean, organizationCd, tantoCd,
			salesBean, check);

		// 売上勘定年月算出
		SalesAccountYears accountYearBean = salesAccountYearsDao
				.getAccountYearsData(insBean.getOrderNo(),
					insBean.getVenderCd(), null);

		String accountYears = accountYearBean.getAccountYears();

		if (accountYears != null) {
			insBean.setAccountYears(accountYears);
		}

		// 分類マスタからデータ取得（前受金対応)
		getClassificationData(insBean);
		
		// 売上トラン登録処理
		salesDao.insert(insBean);

	}

	/**
	 * 分類マスタのデータを取得する（前受け金対応)
	 * @param insBean insBean
	 */
	private void getClassificationData(final Sales insBean) {

		Vender vender = venderDao.getEntity(insBean.getVenderCd(),
			VENDER_DIV_TS);

		if (vender != null) {

			// 分類コード
			if (vender.getAdvanceDivision().equals(BigDecimal.ONE)) {
				// 売掛金
				insBean.setCategoryDivision(CATEGORY_DIVISION_DEPOSIT);
			} else {
				// 前受金
				insBean.setCategoryDivision(CATEGORY_DIVISION_ADVANCE);
			}

			// ﾃﾞｰﾀ集計区分取得
			insBean.setDataTotalDivision(acceptDetailListDao
					.getDataTotalDivisionSales(insBean.getCategoryDivision()));
		}
	}

	/**
	 * 仕入直送品の場合、売上運賃を登録する。
	 * 
	 * @param organizationCd ログイン者の部署コード
	 * @param tantoCd 担当者コード
	 * @param bean 登録前検索購買データ
	 * @param check 数値項目用表示ロジッククラス
	 * @param NoDataException 対象データなしエラー
	 * @param Exception 例外
	 */
	private void insertCarrySales(final Timestamp acceptDate,
			final String organizationCd, final String tantoCd,
			final PurchaseSubcontract bean, final CheckDigitUtilsLogic check)
			throws NoDataException, Exception {
		// 会社コード取得
		String companyCd = acceptDetailSalesDao.getCompanyCd();

		// 売上トラン登録用データ取得(受注関連データ)
		AcceptDetailSales salesBean = acceptDetailSalesDao.getSalesData(
			tantoCd, companyCd, bean.getOrderNo(), bean.getOrderRowNo());
		if (salesBean == null) {
			// 対象データなし
			throw new LogicException();
		}

		// 売上勘定年月算出
		SalesAccountYears accountYearBean = salesAccountYearsDao
				.getAccountYearsData(salesBean.getOrderNo(),
					salesBean.getVenderCd(), null);

		String accountYears = accountYearBean.getAccountYears();

		OrderHead order = orderHeadDao.getEntity(salesBean.getOrderNo());

		// 受注運賃が有る場合売上運賃作成
		if (!order.getCarryFare().equals(BigDecimal.ZERO)) {

			// 同一受注番号のデータの集計
			AcceptOrderNum orderSum = acceptOrderNumDao.getOrderNumData(
				salesBean.getOrderNo(), null, null);

			// 同一受注番号のデータの集計
			AcceptOrderNum orderNum = acceptOrderNumDao.getOrderNumData(
				salesBean.getOrderNo(), null, 6);

			// 数量が同一の場合運賃計上
			if (orderSum.getOrderNum().intValue() == orderNum.getOrderNum()
					.intValue()) {
				Delivery delivery = deliveryDao.getEntity(salesBean
						.getDeliveryCd());
				String itemCd = null;

				if (delivery.getDivision().equals(BigDecimal.ONE)) {
					itemCd = CONST_DIVISION_YUSI_ITEM;
				} else {
					itemCd = CONST_DIVISION_KASEI_ITEM;
				}

				AcceptDetailSalesCarry salesCarryBean = acceptDetailSalesCarryDao
						.getSalesData(companyCd, bean.getOrderNo(), bean
								.getOrderRowNo().toString(), itemCd);

				Sales insCarryBean = new Sales();

				// 売上トランデータ設定
				insCarryBean = setSalesCarryData(organizationCd, tantoCd,
					salesCarryBean, check, order.getCarryFare(),acceptDate);

//				insCarryBean.setSalesDate(acceptDate);
				insCarryBean.setSalesQuantity(BigDecimal.ONE);
				insCarryBean.setSalesUnitprice(order.getCarryFare());
				insCarryBean.setStandardUnitprice(order.getCarryFare());
				insCarryBean.setStandardDiscount(BigDecimal.ZERO);
				insCarryBean.setSpecialDiscount(BigDecimal.ZERO);

				if (accountYears != null) {
					insCarryBean.setAccountYears(accountYears);
				}
				getClassificationData(insCarryBean);

				// 売上トラン登録処理
				salesDao.insert(insCarryBean);
			}
		}
	}

	/**
	 * 仕入直送品の場合、売上運賃データを登録する。
	 * 
	 * @param listBean ロット分割の１明細データ
	 * @param organizationCd ログイン者の部署コード
	 * @param tantoCd 担当者コード
	 * @param salesBean 受注関連データ
	 * @param check 数値項目用表示ロジッククラス
	 * @return Sales 売上トラン登録用データ
	 * @throws NoDataException 対象データなしエラー
	 */
	private Sales setSalesCarryData(final String organizationCd,
			final String tantoCd, final AcceptDetailSalesCarry salesBean,
			final CheckDigitUtilsLogic check, final BigDecimal salesAmountCarry,final Timestamp acceptDate)
			throws NoDataException {

		// 売上番号取得
		String salesNo = getNumberLogic.getUrSlipNo();

		// 売上トランデータ設定
		Sales insBean = new Sales();

		String salesDate = AecDateUtils.dateFormat(acceptDate, "yyyy/MM/dd");
		insBean.setSalesDate(AecDateUtils.getTimestampYmdFormat(salesDate)); // 売上日付

		// 2014/2/5 新消費税対応 ->
		// 消費税率
		// insBean.setTaxRatio(salesBean.getTaxRatio());
		/*
		 * salesBean.setTaxRatio(AecNumberUtils.convertNullToZero(AecNumberUtils
		 * .convertBigDecimal(getTaxRatioLogic.getTaxRatio(AecDateUtils
		 * .dateFormat(insBean.getSalesDate(), "yyyy/MM/dd")))));
		 */
		// 軽減税率対応　消費税率を税コードから取得　20190823
		String taxCd = getTaxRatioLogic.getTaxCd(salesDate,
			salesBean.getItemCd(), Constants.VENDER_DIVISION_TS,
			salesBean.getVenderCd(), Constants.SALES_CATEGORY, null, null);
		insBean.setTaxRatio(AecNumberUtils.convertNullToZero(AecNumberUtils
				.convertBigDecimal(getTaxRatioLogic.getTaxRatio(taxCd))));
		insBean.setTaxDivision(AecNumberUtils
				.convertNullToZero(AecNumberUtils
						.convertBigDecimal(getTaxRatioLogic
								.getTaxDivisionFromItem(salesBean.getItemCd(),
									"1"))));
		
		insBean.setTaxCd(taxCd);		
		
		// 2014/2/5 新消費税対応 <-

		insBean.setSalesNo(salesNo); // 売上番号
		insBean.setItemCd(salesBean.getItemCd()); // 品目コード
		insBean.setOrganizationCd(organizationCd); // 部署コード
		insBean.setVenderCd(salesBean.getVenderCd()); // 得意先コード
		insBean.setInputTantoCd(tantoCd); // 入力担当者コード
		insBean.setSalesTantoCd(salesBean.getSalesTantoCd()); // 営業担当者コード
		insBean.setOrderNo(salesBean.getOrderNo()); // 受注番号
		insBean.setOrderRowNo(salesBean.getOrderRowNo()); // 受注行番号
		insBean.setBalanceCd(salesBean.getBalanceCd()); // 帳合コード 2009/5/27追加

		// 2018/5/29 仕入れ直送品の場合受注の備考を継承しないように修正(売上側は対応していたが運賃側は対応していなかった。)
		// insBean.setRemark(salesBean.getRemark()); // 摘要
		insBean.setInputDivision(INPUT_DIVISION_SIIRE); // 入力区分(2:仕入入力)
		insBean.setDeliveryCd(salesBean.getDeliveryCd()); // 納入先コード
		insBean.setTaxDivision(salesBean.getTaxDivision()); // 消費税課税区分
		insBean.setInvoiceCd(salesBean.getInvoiceCd()); // 請求先コード

		// 運賃の売上金額＝受注の売上金額
		BigDecimal salesAmount = salesAmountCarry;
		// 丸め処理
		salesAmount = check.round(UNIT_DIVISION_URKINGAKU, VENDER_DIV_TS,salesBean.getVenderCd(), salesAmount);
		insBean.setSalesAmount(salesAmount);

		// 消費税額設定
		BigDecimal calcDiv = salesBean.getCalcDivision(); // 取引先ﾏｽﾀ.算出区分
		BigDecimal compCalcDiv = salesBean.getCompCalcDivision(); // 自社ﾏｽﾀ.消費税算出区分
		// 取引先ﾏｽﾀ.算出区分＝1(明細)の場合
		// 取引先ﾏｽﾀ.算出区分＝4(自社ﾏｽﾀ) かつ 自社ﾏｽﾀ.消費税算出区分＝1(明細)の場合
		if (CALC_DIVISION_MEISAI.equals(calcDiv)
				|| (CALC_DIVISION_COMPANY.equals(calcDiv) && CALC_DIVISION_MEISAI
						.equals(compCalcDiv))) {

			// 消費税課税区分=1:外税
			if (TAX_DIVISION_OUT.equals(salesBean.getTaxDivision())) {
				// 消費税率は％のため100で割る
				BigDecimal taxRatio = insBean.getTaxRatio().divide(new BigDecimal(100));
				// 消費税額＝売上金額×消費税率
				BigDecimal taxAmount = salesAmount.multiply(taxRatio);
				// 丸め処理
				taxAmount = check.round(UNIT_DIVISION_TAX_AMOUNT,
					VENDER_DIV_TS, salesBean.getVenderCd(), taxAmount);
				insBean.setTaxAmount(taxAmount);
				// 消費税課税区分=2:内税
			} else if (TAX_DIVISION_IN.equals(salesBean.getTaxDivision())) {

				double dblSalesAmount = 0;
				double dblTaxRate = 0;
				double dblTalAmount = 0;

				if (salesAmount != null) {
					dblSalesAmount = salesAmount.doubleValue();
				} else {
					dblSalesAmount = 0;
				}

				if (insBean.getTaxRatio() != null) {
					dblTaxRate = insBean.getTaxRatio().doubleValue();
				} else {
					dblTaxRate = 0;
				}

				// 内税計算 = 金額 * 消費税率 /( 100 + 消費税率)
				dblTalAmount = dblSalesAmount * dblTaxRate / (100 + dblTaxRate);

				BigDecimal taxAmount = new BigDecimal(dblTalAmount);

				// 丸め処理
				taxAmount = check.round(UNIT_DIVISION_TAX_AMOUNT,
					VENDER_DIV_TS, salesBean.getVenderCd(), taxAmount);
				insBean.setTaxAmount(taxAmount);

				// 内税の場合 計算した金額から内税を引く
				insBean.setSalesAmount(insBean.getSalesAmount().subtract(
					taxAmount));

			} else {
				insBean.setTaxAmount(BigDecimal.ZERO);
			}
		} else {
			insBean.setTaxAmount(BigDecimal.ZERO);
		}

		insBean.setDataType(DATA_TYPE_URI); // ﾃﾞｰﾀ種別(1:売上)
		insBean.setDataTotalDivision(salesBean.getDataTotalDivision()); // ﾃﾞｰﾀ集計区分
		insBean.setCategoryDivision(CATEGORY_DIVISION_URI); // 分類コード(1:売上)
		insBean.setAccountYears(salesBean.getAccountYears()); // 勘定年月
		insBean.setAccountDebitSectionCd(salesBean.getAccountDebitSectionCd()); // 会計部門借方コード
		insBean.setAccountCreditSectionCd(salesBean.getAccountCreditSectionCd()); // 会計部門貸方コード
		insBean.setDebitTitleCd(salesBean.getDebitTitleCd()); // 借方科目コード
		insBean.setCreditTitleCd(salesBean.getCreditTitleCd()); // 貸方科目コード
		insBean.setChargeOrganizationCd(salesBean.getChargeOrganizationCd()); // 担当部署
		insBean.setKeepFlag(BigDecimal.ONE); // 預り品フラグ(1:標準)

		insBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		insBean.setInputorCd(tantoCd); // 登録者
		insBean.setUpdatorCd(tantoCd); // 更新者

		return insBean;
	}

	/**
	 * 仕入直送品の場合、売上データを登録する。
	 * 
	 * @param listBean ロット分割の１明細データ
	 * @param organizationCd ログイン者の部署コード
	 * @param tantoCd 担当者コード
	 * @param salesBean 受注関連データ
	 * @param check 数値項目用表示ロジッククラス
	 * @return Sales 売上トラン登録用データ
	 * @throws NoDataException 対象データなしエラー
	 */
	private Sales setSalesData(final AcceptDetailList listBean,
			final String organizationCd, final String tantoCd,
			final AcceptDetailSales salesBean, final CheckDigitUtilsLogic check)
			throws NoDataException {
		// 売上番号取得
		String salesNo = getNumberLogic.getUrSlipNo();

		// 売上トランデータ設定
		Sales insBean = new Sales();
		insBean.setSalesDate(listBean.getAcceptDate()); // 売上日付

		// 2014/2/5 新消費税対応 ->
		// 消費税率
		/*
		 * salesBean.setTaxRatio(AecNumberUtils.convertNullToZero(AecNumberUtils
		 * .convertBigDecimal(getTaxRatioLogic.getTaxRatio(AecDateUtils
		 * .dateFormat(insBean.getSalesDate(), "yyyy/MM/dd")))));
		 */
		// 軽減税率対応　消費税率を税コードから取得　20190823
		String taxCd = getTaxRatioLogic.getTaxCd(AecDateUtils.dateFormat(listBean.getAcceptDate(),"yyyy/MM/dd"),
			listBean.getItemCd(), Constants.VENDER_DIVISION_TS,
			listBean.getVenderCd(), Constants.SALES_CATEGORY, null, null);
		salesBean.setTaxRatio(AecNumberUtils.convertNullToZero(AecNumberUtils
				.convertBigDecimal(getTaxRatioLogic.getTaxRatio(taxCd))));
		salesBean.setTaxDivision(AecNumberUtils
				.convertNullToZero(AecNumberUtils
						.convertBigDecimal(getTaxRatioLogic
								.getTaxDivisionFromItem(salesBean.getItemCd(),
									"1"))));
		// 2014/2/5 新消費税対応 <-
		insBean.setTaxRatio(salesBean.getTaxRatio());
		insBean.setTaxCd(taxCd);

		insBean.setSalesNo(salesNo); // 売上番号
		insBean.setItemCd(salesBean.getItemCd()); // 品目コード
		insBean.setOrganizationCd(organizationCd); // 部署コード
		insBean.setVenderCd(salesBean.getVenderCd()); // 得意先コード
		insBean.setInputTantoCd(tantoCd); // 入力担当者コード
		insBean.setSalesTantoCd(salesBean.getSalesTantoCd()); // 営業担当者コード
		insBean.setProductLotno(listBean.getSupplierLotno()); // 製品ロット番号
		insBean.setOrderNo(salesBean.getOrderNo()); // 受注番号
		insBean.setOrderRowNo(salesBean.getOrderRowNo()); // 受注行番号
		insBean.setBalanceCd(salesBean.getBalanceCd()); // 帳合コード 2009/5/27追加

		// 売上数量(丸め処理)
		BigDecimal salesQty = check.round(salesBean.getUnitDiv(),
			VENDER_DIV_TS, salesBean.getVenderCd(),
			listBean.getAcceptQuantity());
		insBean.setSalesQuantity(salesQty);

		// 売上単価(丸め処理)
		BigDecimal salesUnitPrice = check.round(UNIT_DIVISION_URTANKA,
			VENDER_DIV_TS, salesBean.getVenderCd(),
			salesBean.getSalesUnitprice());
		insBean.setSalesUnitprice(salesUnitPrice);

		// 標準単価(丸め処理)
		BigDecimal standardUnitprice = check.round(UNIT_DIVISION_URTANKA,
			VENDER_DIV_TS, salesBean.getVenderCd(),
			salesBean.getStandardUnitprice());
		insBean.setStandardUnitprice(standardUnitprice);

		// 標準値引(丸め処理)
		BigDecimal standardDiscount = check.round(UNIT_DIVISION_URTANKA,
			VENDER_DIV_TS, salesBean.getVenderCd(),
			salesBean.getStandardDiscount());
		insBean.setStandardDiscount(standardDiscount);

		// 特別値引(丸め処理)
		BigDecimal specialDiscount = check.round(UNIT_DIVISION_URTANKA,
			VENDER_DIV_TS, salesBean.getVenderCd(),
			salesBean.getSpecialDiscount());
		insBean.setSpecialDiscount(specialDiscount);

		insBean.setTmpUnitpriceFlg(salesBean.getTmpUnitpriceFlg()); // 仮単価FLG

		// 2015/5/27 仕入れ直送品の場合受注の備考を継承しないように修正
		// insBean.setRemark(salesBean.getRemark()); // 摘要
		insBean.setInputDivision(INPUT_DIVISION_SIIRE); // 入力区分(2:仕入入力)
		insBean.setDeliveryCd(salesBean.getDeliveryCd()); // 納入先コード
		insBean.setTaxDivision(salesBean.getTaxDivision()); // 消費税課税区分
		insBean.setInvoiceCd(salesBean.getInvoiceCd()); // 請求先コード

		// 売上金額＝売上数量(受入数量)×売上単価(受注単価)
		BigDecimal salesAmount = new BigDecimal(0);
		if (salesQty != null && salesUnitPrice != null) {
			salesAmount = listBean.getAcceptQuantity().multiply(
				salesBean.getSalesUnitprice());
		}
		// 丸め処理
		salesAmount = check.round(UNIT_DIVISION_URKINGAKU, VENDER_DIV_TS,
			salesBean.getVenderCd(), salesAmount);
		insBean.setSalesAmount(salesAmount);

		// 消費税額設定
		BigDecimal calcDiv = salesBean.getCalcDivision(); // 取引先ﾏｽﾀ.算出区分
		BigDecimal compCalcDiv = salesBean.getCompCalcDivision(); // 自社ﾏｽﾀ.消費税算出区分
		// 取引先ﾏｽﾀ.算出区分＝1(明細)の場合
		// 取引先ﾏｽﾀ.算出区分＝4(自社ﾏｽﾀ) かつ 自社ﾏｽﾀ.消費税算出区分＝1(明細)の場合
		if (CALC_DIVISION_MEISAI.equals(calcDiv)
				|| (CALC_DIVISION_COMPANY.equals(calcDiv) && CALC_DIVISION_MEISAI
						.equals(compCalcDiv))) {

			// 消費税課税区分=1:外税
			if (TAX_DIVISION_OUT.equals(salesBean.getTaxDivision())) {
				// 消費税率は％のため100で割る
				BigDecimal taxRatio = salesBean.getTaxRatio().divide(
					new BigDecimal(100));
				// 消費税額＝売上金額×消費税率
				BigDecimal taxAmount = salesAmount.multiply(taxRatio);
				// 丸め処理
				taxAmount = check.round(UNIT_DIVISION_TAX_AMOUNT,
					VENDER_DIV_TS, salesBean.getVenderCd(), taxAmount);
				insBean.setTaxAmount(taxAmount);

				// 消費税課税区分=2:内税
			} else if (TAX_DIVISION_IN.equals(salesBean.getTaxDivision())) {

				double dblSalesAmount = 0;
				double dblTaxRate = 0;
				double dblTalAmount = 0;

				if (salesAmount != null) {
					dblSalesAmount = salesAmount.doubleValue();
				} else {
					dblSalesAmount = 0;
				}

				if (salesBean.getTaxRatio() != null) {
					dblTaxRate = salesBean.getTaxRatio().doubleValue();
				} else {
					dblTaxRate = 0;
				}

				// 内税計算 = 金額 * 消費税率 /( 100 + 消費税率)
				dblTalAmount = dblSalesAmount * dblTaxRate / (100 + dblTaxRate);

				BigDecimal taxAmount = new BigDecimal(dblTalAmount);

				// 丸め処理
				taxAmount = check.round(UNIT_DIVISION_TAX_AMOUNT,
					VENDER_DIV_TS, salesBean.getVenderCd(), taxAmount);
				insBean.setTaxAmount(taxAmount);

				// 内税の場合 計算した金額から内税を引く
				insBean.setSalesAmount(insBean.getSalesAmount().subtract(
					taxAmount));

			} else {
				insBean.setTaxAmount(BigDecimal.ZERO);
			}
		} else {
			insBean.setTaxAmount(BigDecimal.ZERO);
		}

		insBean.setDataType(DATA_TYPE_URI); // ﾃﾞｰﾀ種別(1:売上)
		insBean.setDataTotalDivision(salesBean.getDataTotalDivision()); // ﾃﾞｰﾀ集計区分
		insBean.setCategoryDivision(CATEGORY_DIVISION_URI); // 分類コード(1:売上)
		insBean.setAccountYears(salesBean.getAccountYears()); // 勘定年月
		insBean.setAccountDebitSectionCd(salesBean.getAccountDebitSectionCd()); // 会計部門借方コード
		insBean.setAccountCreditSectionCd(salesBean.getAccountCreditSectionCd()); // 会計部門貸方コード
		insBean.setDebitTitleCd(salesBean.getDebitTitleCd()); // 借方科目コード
		insBean.setCreditTitleCd(salesBean.getCreditTitleCd()); // 貸方科目コード
		insBean.setChargeOrganizationCd(salesBean.getChargeOrganizationCd()); // 担当部署
		insBean.setKeepFlag(BigDecimal.ONE); // 預り品フラグ(1:標準)

		insBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		insBean.setInputorCd(tantoCd); // 登録者
		insBean.setUpdatorCd(tantoCd); // 更新者

		return insBean;
	}

	/**
	 * 分納データ新規登録処理
	 * 
	 * @param frm 入荷入力画面FORM
	 * @param orderDivideNoNew 発注番号分納枝番
	 * @param tantoCd 担当者コード
	 * @param bean 購買オーダデータBean
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws OptimisticLockRuntimeException
	 */
	private void insertBunnoData(final AcceptDetailForm frm,
			final String orderDivideNoNew, final String tantoCd,
			final PurchaseSubcontract bean) throws IllegalAccessException,
			InvocationTargetException, SQLRuntimeException {

		PurchaseSubcontract newBean = new PurchaseSubcontract();
		// １件目Beanを分納データ用Beanにコピー
		IgnoreCaseBeanUtils.copyProperties(newBean, bean);

		newBean.setPurchaseNo(null); // 購買NO
		newBean.setOrderDivideNo(orderDivideNoNew); // 発注番号分納枝番
		newBean.setStatus(PurchaseStatus.FIXED); // 購買ステータス(4:納期確定)
		newBean.setSlipNo(null); // 仕入番号
		newBean.setRowNo(null); // 行番号
		// 納品希望日
		String nextDelDate = frm.getNextDeliverlimitDate() + " ";
		if (StringUtils.isNotEmpty(frm.getNextDeliverlimitDateTime())) {
			nextDelDate = nextDelDate + frm.getNextDeliverlimitDateTime();
		} else {
			nextDelDate = nextDelDate + "00:00";
		}
		newBean.setSuggestedDeliverlimitDate(AecDateUtils
				.getTimestampYmdHmFormat(nextDelDate, "yyyy/MM/dd HH:mm"));

		newBean.setSupplierLotno(null); // メーカーロット番号
		newBean.setLotNo(null); // 入荷ロット番号
		newBean.setHousingLocationCd(null); // 入庫ロケーションコード
		newBean.setAcceptQuantity(null); // 受入数量
		newBean.setIncreaseQuantity(null); // 増付数量
		newBean.setAcceptDate(null); // 受入日

		newBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		newBean.setInputorCd(tantoCd); // 登録者
		newBean.setUpdatorCd(tantoCd); // 更新者

		// 分納データ登録処理
		purchaseSubcontractDao.insert(newBean);

	}

	/**
	 * メッセージを追加する
	 * 
	 * @param errors ActionMessages
	 * @param key リソースのキー
	 * @param objects オブジェクト
	 * @return ActionMessages メッセージ
	 */
	private ActionMessages addError(final ActionMessages errors,
			final String key, final Object... objects) {
		ActionMessages tmpMsg = errors;
		if (tmpMsg == null) {
			tmpMsg = new ActionMessages();
		}
		tmpMsg.add("", new ActionMessage(key, objects));
		return tmpMsg;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 発番処理用ロジッククラスを設定します。
	 * @param getNumberLogic 発番処理用ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
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
	 * 受入入力詳細画面用Daoを設定します。
	 * @param acceptDetailListDao 受入入力詳細画面用Dao
	 */
	public void setAcceptDetailListDao(
			final AcceptDetailListDao acceptDetailListDao) {
		this.acceptDetailListDao = acceptDetailListDao;
	}

	/**
	 * 受入入力 売上トラン登録用データ取得Daoを設定します。
	 * @param acceptDetailSalesDao 受入入力 売上トラン登録用データ取得Dao
	 */
	public void setAcceptDetailSalesDao(
			final AcceptDetailSalesDao acceptDetailSalesDao) {
		this.acceptDetailSalesDao = acceptDetailSalesDao;
	}

	/**
	 * 購買外注オーダ更新用Daoを設定します。
	 * @param purchaseSubcontractDao 購買外注オーダ更新用Dao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * 売上トラン更新用Daoを設定します。
	 * @param salesDao 売上トラン更新用Dao
	 */
	public void setSalesDao(final SalesDao salesDao) {
		this.salesDao = salesDao;
	}

	/**
	 * 仕入区分コンボボックス用DAOを設定します。
	 * @param acceptStockingDivisionComboboxesDao 仕入区分コンボボックス用DAO
	 */
	public void setAcceptStockingDivisionComboboxesDao(
			final AcceptStockingDivisionComboboxesDao acceptStockingDivisionComboboxesDao) {
		this.acceptStockingDivisionComboboxesDao = acceptStockingDivisionComboboxesDao;
	}

	/**
	 * 部署マスタ用Daoを設定します。
	 * @param organizationDao 部署マスタ用Dao
	 */
	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * 品目マスタ用Daoを設定します。
	 * @param itemDao 品目マスタ用Dao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * プロシージャコール用daoを設定します。
	 * @param procedureCallDao プロシージャコール用dao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * エラーログ出力用daoを設定します。
	 * @param errorLogDao エラーログ出力用dao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * 購入品扱い属性キュー用Daoを設定します。
	 * @param purchaseAttributeQueueDao 購入品扱い属性キュー用Dao
	 */
	public void setPurchaseAttributeQueueDao(
			final PurchaseAttributeQueueDao purchaseAttributeQueueDao) {
		this.purchaseAttributeQueueDao = purchaseAttributeQueueDao;
	}

	/**
	 * ロケーションマスタDaoを設定します。
	 * @param locationDao ロケーションマスタ用Dao
	 */
	public void setLocationDao(final LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	/**
	 * 入荷ロットDaoを設定します。
	 * @param codeNyukalotDao 入荷ロット用Dao
	 */
	public void setCodeNyukalotDao(final CodeNyukalotDao codeNyukalotDao) {
		this.codeNyukalotDao = codeNyukalotDao;
	}

	/**
	 * orderHeadDaoを設定します。
	 * @param orderHeadDao orderHeadDao
	 */
	public void setOrderHeadDao(final OrderHeadDao orderHeadDao) {
		this.orderHeadDao = orderHeadDao;
	}

	/**
	 * deliveryDaoを設定します。
	 * @param deliveryDao deliveryDao
	 */
	public void setDeliveryDao(final DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

	/**
	 * acceptDetailSalesCarryDaoを設定します。
	 * @param acceptDetailSalesCarryDao acceptDetailSalesCarryDao
	 */
	public void setAcceptDetailSalesCarryDao(
			final AcceptDetailSalesCarryDao acceptDetailSalesCarryDao) {
		this.acceptDetailSalesCarryDao = acceptDetailSalesCarryDao;
	}

	/**
	 * salesAccountYearsDaoを設定します。
	 * @param salesAccountYearsDao salesAccountYearsDao
	 */
	public void setSalesAccountYearsDao(
			final SalesAccountYearsDao salesAccountYearsDao) {
		this.salesAccountYearsDao = salesAccountYearsDao;
	}

	/**
	 * acceptOrderNumDaoを設定します。
	 * @param acceptOrderNumDao acceptOrderNumDao
	 */
	public void setAcceptOrderNumDao(final AcceptOrderNumDao acceptOrderNumDao) {
		this.acceptOrderNumDao = acceptOrderNumDao;
	}

	/**
	 * performanceLogDaoを設定します。
	 * @param performanceLogDao performanceLogDao
	 */
	public void setPerformanceLogDao(final PerformanceLogDao performanceLogDao) {
		this.performanceLogDao = performanceLogDao;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * lotInventoryDaoを設定します。
	 * @param lotInventoryDao lotInventoryDao
	 */
	public void setLotInventoryDao(final LotInventoryDao lotInventoryDao) {
		this.lotInventoryDao = lotInventoryDao;
	}

	/**
	 * getTaxRatioLogicを設定します。
	 * @param getTaxRatioLogic getTaxRatioLogic
	 */
	public void setGetTaxRatioLogic(final GetTaxRatioLogic getTaxRatioLogic) {
		this.getTaxRatioLogic = getTaxRatioLogic;
	}
	
}
