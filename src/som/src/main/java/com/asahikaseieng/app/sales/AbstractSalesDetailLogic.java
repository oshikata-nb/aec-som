/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.ComboboxesBean;
import com.asahikaseieng.app.common.CommonLogic;
import com.asahikaseieng.app.common.stockinout.Stockinout;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.app.shipping.ShippingLogicException;
import com.asahikaseieng.app.tax.GetTaxRatioLogic;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueue;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.bumon.Bumon;
import com.asahikaseieng.dao.entity.master.bumon.BumonDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.reason.Reason;
import com.asahikaseieng.dao.entity.master.reason.ReasonDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.sales.Sales;
import com.asahikaseieng.dao.entity.sales.SalesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster.TaxMasterListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster.TaxMasterListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.sales.SalesHousingLocationForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.sales.SalesHousingLocationForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.sales.SalesAccountYears;
import com.asahikaseieng.dao.nonentity.sales.SalesAccountYearsDao;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailEntity;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailEntityDao;
import com.asahikaseieng.dao.nonentity.salescancelcheckdeliveryupdate.SalesCancelCheckDeliveryUpdate;
import com.asahikaseieng.dao.nonentity.salescancelcheckdeliveryupdate.SalesCancelCheckDeliveryUpdateDao;
import com.asahikaseieng.dao.nonentity.salescancelcheckinvoiceupdate.SalesCancelCheckInvoiceUpdate;
import com.asahikaseieng.dao.nonentity.salescancelcheckinvoiceupdate.SalesCancelCheckInvoiceUpdateDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 売上詳細画面共通処理抽象クラス
 * @author tosco
 */
public abstract class AbstractSalesDetailLogic {
	/** 算出区分 1:明細単位 */
	protected static final BigDecimal CALC_DIVISION_MEISAI = BigDecimal.ONE;

	/** 算出区分 4:自社ﾏｽﾀ */
	public static final BigDecimal CALC_DIVISION_COMPANY = new BigDecimal(4);

	/** 消費税課税区分 1:外税 */
	public static final BigDecimal TAX_DIVISION_OUT = new BigDecimal(1);

	/** 消費税課税区分 2:内税 */
	public static final BigDecimal TAX_DIVISION_IN = new BigDecimal(2);

	/** 仮単価FLG 0:通常 */
	protected static final BigDecimal TMP_UNITPRICE_FLG_NORMAL = BigDecimal.ZERO;

	/** ﾃﾞｰﾀ種別 1:売上 */
	protected static final BigDecimal DATA_TYPE_SALES = BigDecimal.ONE;

	/** 入力区分 0:売上 */
	protected static final BigDecimal INPUT_DIVISION_SALES = BigDecimal.ZERO;
	
	/** 売上伝票 0:未発行*/
	protected static final BigDecimal SLIP_UNPUBLISH = BigDecimal.ZERO;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 売上－入庫ロケーションコンボボックス用DAO */
	private SalesHousingLocationForComboboxesDao salesHousingLocationDao;

	/** 品目マスタ用Dao */
	private ItemDao itemDao;

	/** 品目マスタ用Dao(販売品) */
	private ArticleAttributeQueueDao articleAttributeQueueDao;

	/** 会計部門マスタ用Dao */
	private BumonDao bumonDao;

	/** 売上トランザクショ用Dao */
	private SalesDao salesDao;

	/** 売上詳細画面_売上トランザクション用Dao */
	private SalesDetailEntityDao salesDetailEntityDao;

	/** 理由マスタ用Dao */
	private ReasonDao reasonDao;

	/** 発番処理用ロジッククラス */
	private GetNumberLogic getNumberLogic;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 売上勘定年月取得用Dao */
	private SalesAccountYearsDao salesAccountYearsDao;

	private VenderDao chargeOrganizationDao;

	/** 取り消しとき売掛締め日チェック用 */
	private SalesCancelCheckDeliveryUpdateDao salesCancelCheckDeliveryUpdateDao;

	/** 取り消しとき請求締め日チェック用 */
	private SalesCancelCheckInvoiceUpdateDao salesCancelCheckInvoiceUpdateDao;

	// 2014/1/28 新消費税対応 -->
	private GetTaxRatioLogic getTaxRatioLogic;

	// 2014/1/28 新消費税対応 <--
	private TaxMasterListForComboboxesDao taxMasterListForComboboxesDao;
	
	private CommonLogic commonLogic;
	
	
	/**
	 * 通常用入庫ロケーションコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createHousingLocationComboboxForStandard() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 入庫ロケーション検索
		List<SalesHousingLocationForComboboxes> lineList = salesHousingLocationDao
				.getHousingLocationForStandardList();
		for (SalesHousingLocationForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			StringBuffer labelBuf = new StringBuffer("");
			labelBuf.append(bean.getLocationCd());
			labelBuf.append(":").append(bean.getLocationName());
			item.setValues(bean.getLocationCd());
			item.setLabales(labelBuf.toString());
			res.add(item);
		}
		return res;
	}

	/**
	 * 預り品用入庫ロケーションコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createHousingLocationComboboxForKeep() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 入庫ロケーション検索
		List<SalesHousingLocationForComboboxes> lineList = salesHousingLocationDao
				.getHousingLocationForKeepList();
		for (SalesHousingLocationForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			StringBuffer labelBuf = new StringBuffer("");
			labelBuf.append(bean.getLocationCd());
			labelBuf.append(":").append(bean.getLocationName());
			item.setValues(bean.getLocationCd());
			item.setLabales(labelBuf.toString());
			res.add(item);
		}
		return res;
	}

	/**
	 * 売上データの更新日時を取得
	 * @param salesNo 売上番号
	 * @return Timestamp
	 * @throws NoDataException データ無し例外
	 */
	public Timestamp getTimestampBySaleNo(final String salesNo)
			throws NoDataException {
		Sales bean = salesDao.getEntity(salesNo);
		if (bean == null) {
			// 対象データ無し
			throw new NoDataException();
		}
		return bean.getUpdateDate();
	}

	/**
	 * 数値チェック情報設定.
	 * @param frm 売上詳細共通画面FORM
	 */
	public void setCheckDigit(final AbstractSalesDetailForm frm) {
		NumberChkDisitDetail detail = null;

		// 数量用
		detail = checker.getCheckDigit(frm.getUnitDivision(),
			SalesConst.VENDER_DIVISION_TS, frm.getVenderCd());
		// 小数点桁数
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPoint(detail.getSmallnumLength().toString());
		}
		// 端数区分
		if (detail.getRoundDivision() != null) {
			frm.setRound(detail.getRoundDivision().toString());
		}

		// 単価用
		detail = checker.getCheckDigit(SalesConst.UNIT_DIVISION_URTANKA,
			SalesConst.VENDER_DIVISION_TS, frm.getVenderCd());
		// 小数点桁数
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPointUrTanka(detail.getSmallnumLength().toString());
		}
		// 端数区分
		if (detail.getRoundDivision() != null) {
			frm.setRoundUrTanka(detail.getRoundDivision().toString());
		}

		// 金額用
		detail = checker.getCheckDigit(SalesConst.UNIT_DIVISION_URKINGAKU,
			SalesConst.VENDER_DIVISION_TS, frm.getVenderCd());
		// 小数点桁数
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPointUrKingaku(detail.getSmallnumLength().toString());
		}
		// 端数区分
		if (detail.getRoundDivision() != null) {
			frm.setRoundUrKingaku(detail.getRoundDivision().toString());
		}
	}

	/**
	 * 削除処理を行う.
	 * @param frm 売上詳細画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void delete(final AbstractSalesDetailForm frm,
			final LoginInfo loginInfo) throws NoDataException, Exception {
		try {
			if (Integer.parseInt(frm.getCategoryDivision()) < 0) {
				// 取消データの場合、取消元データの売上-取消 売上番号をクリアする
				Sales orginBean = salesDao.getEntity(frm.getCancelSalesNo());
				if (orginBean == null) {
					// 対象データ無し
					throw new NoDataException();
				}
				orginBean.setCancelSalesNo(null);
				orginBean.setUpdatorCd(loginInfo.getTantoCd()); // 更新者
				orginBean.setUpdateDate(frm.getUpdateDateOrigin()); // 更新日時
				salesDao.update(orginBean);

				// 返品取消の場合
				if (frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_RETURNS_CANCEL)
						|| frm
								.getCategoryDivision()
								.equals(
									SalesConst.CATEGOTRY_DIVISION_RETURNS_CANCEL_ADVANCE)) {
					String errMsg = "errors.sales.stock.update";
					try {
						// 在庫更新処理
						Stockinout stock = new Stockinout(zaiCtrlDao);
						// 在庫更新－返品入庫
						if (!stock.returnDelivery(frm.getCancelSalesNo(),
							loginInfo.getTantoCd())) {
							ShippingLogicException ex = new ShippingLogicException(
									errMsg, "");
							throw ex;
						}
					} catch (LogicExceptionEx e) {
						SalesLogicException ex = new SalesLogicException(
								errMsg, "");
						ex.setModuleCd("Stockinout");
						ex.setInsideErrCd(frm.getCancelSalesNo());
						ex.setInsideErrMsg(e.getMessage());
						throw ex;
					}
				}
			} else {

				// 返品の場合
				if (frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_RETURNS)
						|| frm.getCategoryDivision().equals(
							SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE)) {
					String errMsg = "errors.sales.stock.update";
					try {
						// 在庫更新処理
						Stockinout stock = new Stockinout(zaiCtrlDao);
						// 在庫更新－返品入庫取消
						if (!stock.deReturnDelivery(frm.getSalesNo(), loginInfo
								.getTantoCd())) {
							ShippingLogicException ex = new ShippingLogicException(
									errMsg, "");
							throw ex;
						}
					} catch (LogicExceptionEx e) {
						SalesLogicException ex = new SalesLogicException(
								errMsg, "");
						ex.setModuleCd("Stockinout");
						ex.setInsideErrCd(frm.getSalesNo());
						ex.setInsideErrMsg(e.getMessage());
						throw ex;
					}
				}
			}

			// 削除対象の売上トランザクション取得
			Sales delBean = salesDao.getEntity(frm.getSalesNo());
			if (delBean == null) {
				// 対象データ無し
				throw new NoDataException();
			}
			delBean.setUpdateDate(frm.getUpdateDate());
			// 売上トランザクション削除
			int delNum = salesDao.delete(delBean);
			if (delNum == 0) {
				// 対象データ無し
				throw new NoDataException();
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 預り品区分を取得する
	 * @param itemCd 品目コード
	 * @return BigDecimal 売上番号
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public BigDecimal getKeepDivision(final String itemCd)
			throws NoDataException, Exception {

		// 預り品フラグ設定処理（新規の場合、預り品では無いとは限らない為）
		// 品目マスタからデータを取得
		Item item = itemDao.getEntity(itemCd);

		if (item != null) {

			ArticleAttributeQueue artItem = articleAttributeQueueDao.getEntity(
				item.getItemCd(), item.getVersion());

			if (artItem != null) {

				// 預り品フラグ
				return artItem.getKeepDivision();
			}
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 取消処理を行う.
	 * @param frm 売上詳細画面FORM
	 * @param loginInfo ログイン情報
	 * @return String 売上番号
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public String cancel(final AbstractSalesDetailForm frm,
			final LoginInfo loginInfo) throws NoDataException, Exception {
		try {
			// 取消元の売上トランザクションを取得
			Sales originBean = salesDao.getEntity(frm.getSalesNo());
			if (originBean == null) {
				// 対象データ無し
				throw new NoDataException();
			}

			// 返品の場合
			if (originBean.getCategoryDivision().equals(
				SalesConst.CATEGOTRY_DIVISION_RETURNS)
					|| originBean.getCategoryDivision().equals(
						SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE)) {
				String errMsg = "errors.sales.stock.update";
				try {
					// 在庫更新処理
					Stockinout stock = new Stockinout(zaiCtrlDao);
					// 在庫更新－返品入庫取消
					if (!stock.deReturnDelivery(originBean.getSalesNo(),
						loginInfo.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(originBean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}

			// 取消元売上トランザクションデータセット
			Sales cancelBean = setCancelData(originBean, frm, loginInfo);

			// 取消データ登録
			int insertNum = salesDao.insert(cancelBean);
			if (insertNum != 1) {
				// 一意制約エラー
				throw new DuplicateRuntimeException(0);
			}

			// 取消元データを更新
			originBean.setCancelSalesNo(cancelBean.getSalesNo()); // 売上-取消
			// 売上番号
			originBean.setUpdateDate(frm.getUpdateDate()); // 更新日時
			originBean.setUpdatorCd(loginInfo.getTantoCd()); // 更新者ID

			salesDao.update(originBean);

			return cancelBean.getSalesNo();
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 一意制約エラー
			throw new DuplicateRuntimeException(0);
		}

	}

	/**
	 * 品目関連数値チェックエラーを行う.<br>
	 * @param frm 売上詳細画面FORM
	 * @param errors エラー内容
	 * @return boolean チェック結果 true:OK false:NG
	 */
	public boolean checkDigitForItem(final AbstractSalesDetailForm frm,
			final ActionMessages errors) {
		boolean res = true;

		// 数値チェック(autocompleteで取得していない場合の対応)
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		String name = null;
		if (frm instanceof SalesDetailStandardForm) {
			name = rb.getString("item.sales.sales.quantity");
		} else {
			name = rb.getString("item.sales.keep.sales.quantity");
		}
		// 数量
		if (!checkDigitNotZero(frm.getStrSalesQuantity(),
			frm.getUnitDivision(), frm.getVenderCd(), name, errors)) {
			res = false;
		}
		// 標準単価
		if (!this.checkDigit(frm.getStrStandardUnitprice(), frm
				.getUnitDivisionUrTanka(), frm.getVenderCd(), rb
				.getString("item.sales.standard.unitprice"), errors)) {
			res = false;
		}
		// 標準値引
		if (!this.checkDigit(frm.getStrStandardDiscount(), frm
				.getUnitDivisionUrTanka(), frm.getVenderCd(), rb
				.getString("item.sales.standard.discount"), errors)) {
			res = false;
		}
		// 特別値引
		if (!this.checkDigit(frm.getStrSpecialDiscount(), frm
				.getUnitDivisionUrTanka(), frm.getVenderCd(), rb
				.getString("item.sales.special.discount"), errors)) {
			res = false;
		}

		return res;
	}

	/**
	 * 得意先関連情報を取得する
	 * @param venderCd 得意先コード
	 * @return SalesDetailEntity
	 * @throws NoDataException データが存在しない例外
	 */
	public SalesDetailEntity getSalesByVender(final String venderCd)
			throws NoDataException {

		// 得意先コードより会計データを取得
		SalesDetailEntity bean = salesDetailEntityDao
				.getSalesByVender(venderCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 勘定年月を取得する
	 * @param venderCd 得意先コード
	 * @param salesDate 売上日
	 * @return String
	 * @throws NoDataException データが存在しない例外
	 */
	public String getAccountYears(final String venderCd, final String salesDate)
			throws NoDataException {

		// 得意先コードより会計データを取得
		SalesAccountYears bean = salesAccountYearsDao.getAccountYearsData(null,
			venderCd, salesDate);

		String accountYears = bean.getStrAccountYears();

		return accountYears;
	}

	/**
	 * 登録時の品目マスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param frm 売上詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkItemForRegist(final AbstractSalesDetailForm frm,
			final ActionMessages errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 品目マスタ検索
		Item bean = itemDao.getEntity(frm.getItemCd());
		if (bean == null) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.nodata.master", rb
					.getString("item.sales.item.cd")));
		} else {
			// 販売品区分が0:該当なしの場合、エラーとする
			if (BigDecimal.ZERO.compareTo(bean.getArticleDivision()) == 0) {
				errors.add("", new ActionMessage(
						"errors.sales.article.division"));
			}
			// 単位区分をセット(autocompleteで取得していない場合の対応)
			frm.setUnitDivision(bean.getUnitOfOperationManagement());
		}
		return errors;
	}

	/**
	 * 登録時の会計関連マスタチェックを行う.<br>
	 * 会計部門マスタ、科目マスタにデータがない場合はエラーとする。
	 * @param frm 売上詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkAccountsForRegist(
			final AbstractSalesDetailForm frm, final ActionMessages errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 会計部門マスタ検索(借方)
		Bumon bumonDebitBean = bumonDao.getEntity(frm
				.getAccountDebitSectionCd());
		if (bumonDebitBean == null) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.nodata.master", rb
					.getString("item.sales.account.debit.section.cd")));
		}

		// 会計部門マスタ検索(貸方)
		Bumon bumonCreditBean = bumonDao.getEntity(frm
				.getAccountCreditSectionCd());
		if (bumonCreditBean == null) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.nodata.master", rb
					.getString("item.sales.account.credit.section.cd")));
		}

		// 科目マスタ検索(借方)
		int debitCnt = salesDetailEntityDao.getCountAccounts(frm
				.getDebitTitleCd());
		if (debitCnt == 0) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.nodata.master", rb
					.getString("item.sales.debit.title.name")));
		}

		// 科目マスタ検索(貸方)
		int creditCnt = salesDetailEntityDao.getCountAccounts(frm
				.getCreditTitleCd());
		if (creditCnt == 0) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.nodata.master", rb
					.getString("item.sales.credit.title.name")));
		}

		return errors;
	}

	/**
	 * 登録時の理由マスタチェックを行う.<br>
	 * autocompleteで取得していない場合の対応
	 * @param frm 売上詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkReasonForRegist(
			final AbstractSalesDetailForm frm, final ActionMessages errors) {
		if (StringUtils.isNotEmpty(frm.getRyCd())) {
			ResourceBundle rb = ResourceBundle
					.getBundle(Constants.APPLICATION_PROPERTIES);

			// 理由マスタ検索
			Reason bean = reasonDao.getEntity(frm.getRyCd());
			if (bean == null) {
				// データが存在しない場合
				errors.add("", new ActionMessage("errors.nodata.master", rb
						.getString("item.sales.ry.cd")));
			}
		}

		return errors;
	}

	/**
	 * 取消データをセットする.
	 * @param originBean 取消元売上トランザクションデータ
	 * @param frm 売上詳細画面FORM
	 * @param loginInfo ログイン情報
	 * @throws Exception 例外
	 */
	private Sales setCancelData(final Sales originBean,
			final AbstractSalesDetailForm frm, final LoginInfo loginInfo)
			throws Exception {
		Sales cancelBean = new Sales();
		// 取消元のデータを取消データにコピーする
		IgnoreCaseBeanUtils.copyProperties(cancelBean, originBean);

		// 売上番号を発番
		String salesNo = getNumberLogic.getUrSlipNo();
		cancelBean.setSalesDate(AecDateUtils.getTimestampYmdHmFormat(frm
				.getStrSalesDate(), "yyyy/MM/dd")); // 売上日
		cancelBean.setSalesNo(salesNo); // 売上番号
		cancelBean.setCancelSalesNo(frm.getSalesNo()); // 売上-取消 売上番号
		cancelBean.setRemark(frm.getRemark()); // 備考
		cancelBean.setDeliveryUpdateDate(null); // 売掛締め日
		cancelBean.setInvoiceUpdateDate(null); // 請求締め日
		cancelBean.setInputDivision(INPUT_DIVISION_SALES); // 入力区分 0:売上
		cancelBean.setSalesSlipNo(null); // 売上伝票番号
		cancelBean.setSalesSlipRowNo(null); // 売上伝票行番号
		cancelBean.setSlipPublishComp(BigDecimal.ZERO); // 伝票発行済区分 0:未発行
		cancelBean.setSlipPublishDate(null); // 伝票発行日
		cancelBean.setTransmissionDate(null); // データ転送日時
		cancelBean.setLedgerPostDate(null); // 元帳転記日時
		cancelBean.setLedgerPostNo(null); // 元帳転記番号
		// 売上区分をマイナスとする
		String categoryDivision = Integer.toString(convertBigDecimal(
			frm.getCategoryDivision()).intValue()
				* (-1));
		cancelBean.setDataTotalDivision(salesDetailEntityDao
				.getDataTotalDivision(categoryDivision)); // データ集計区分
		cancelBean.setCategoryDivision(categoryDivision); // 分類コード
		cancelBean.setAccountYears(AecDateUtils.dateFormat(AecDateUtils
				.getTimestampYmdHmFormat(frm.getStrAccountYears(), "yyyy/MM"),
			"yyyyMM")); // 勘定年月
		// 仕訳の貸方と借方を逆にしてセット
		cancelBean.setAccountDebitSectionCd(originBean
				.getAccountCreditSectionCd());
		cancelBean.setDebitTitleCd(originBean.getCreditTitleCd());
		cancelBean.setDebitSubTitleCd(originBean.getCreditSubTitleCd());
		cancelBean.setAccountCreditSectionCd(originBean
				.getAccountDebitSectionCd());
		cancelBean.setCreditTitleCd(originBean.getDebitTitleCd());
		cancelBean.setCreditSubTitleCd(originBean.getDebitSubTitleCd());
		cancelBean.setDepositTargetDivision(BigDecimal.ZERO); // 売掛対象
		cancelBean.setClaimTargetDivision(BigDecimal.ZERO); // 請求対象
		cancelBean.setSummaryCd(null); // 適用コード
		cancelBean.setSummary(null); // 適用
		cancelBean.setDepositUpdateDivision(BigDecimal.ZERO); // 売掛更新フラグ
		cancelBean.setDepositNo(null); // 売掛番号
		cancelBean.setClaimUpdateDivision(BigDecimal.ZERO); // 請求更新フラグ
		cancelBean.setClaimNo(null); // 請求番号
		cancelBean.setEraserCompleteDivision(BigDecimal.ZERO); // 消込完了フラグ
		cancelBean.setEraserCompleteDate(null); // 消込完了日
		cancelBean.setApprovalStatus(BigDecimal.ZERO); // 承認ステータス
		cancelBean.setApprovedby(null); // 承認者
		cancelBean.setApprovaldate(null); // 承認日
		cancelBean.setRyCd(frm.getRyCd()); // 理由コード
		cancelBean.setInputorCd(loginInfo.getTantoCd()); // 登録者
		cancelBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		cancelBean.setUpdatorCd(loginInfo.getTantoCd()); // 更新者

		cancelBean.setSummary(frm.getSummary()); // 適用

		return cancelBean;
	}

	/**
	 * 数量(最小値が０より大きい数値)の検証
	 * @param inputValue 入力した値
	 * @param unitDivision 単位区分
	 * @param venderCd 取引先コード
	 * @param name 項目名
	 * @param errors 検証エラー内容
	 * @return boolean チェック結果 true:OK false:エラー
	 */
	private boolean checkDigitNotZero(final String inputValue,
			final String unitDivision, final String venderCd,
			final String name, final ActionMessages errors) {
		String value = StringUtils.replace(inputValue, ",", "");
		ActionMessage message = checker.checkDigitMessage(unitDivision,
			SalesConst.VENDER_DIVISION_TS, venderCd, value, name);
		if (message != null) {
			if ("errors.rang".equals(message.getKey())) {
				// 範囲エラーの場合、売上固有のメッセージにする
				NumberChkDisitDetail checkDetail = checker.getCheckDigit(
					unitDivision, SalesConst.VENDER_DIVISION_TS, venderCd);
				message = new ActionMessage("errors.sales.rang.quantity",
						checker.format(unitDivision,
							SalesConst.VENDER_DIVISION_TS, venderCd,
							SalesConst.QUANTITY_MIN), name, checkDetail
								.getUpperLimit().toString());
			}
			errors.add("", message);
			return false;
		}
		return true;
	}

	/**
	 * 数値チェックを行う.<br>
	 * 標準の数値チェックで可能な項目のチェックを行う
	 * @param inputValue 入力値
	 * @param unitDivision 単位区分
	 * @param venderCd 1取引先コード
	 * @param name 項目名
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean checkDigit(final String inputValue,
			final String unitDivision, final String venderCd,
			final String name, final ActionMessages errors) {
		// 数値チェック
		if (!StringUtils.isEmpty(inputValue)) {
			String value = StringUtils.replace(inputValue, ",", "");
			ActionMessage message = checker.checkDigitMessage(unitDivision,
				SalesConst.VENDER_DIVISION_TS, venderCd, value, name);
			if (message != null) {
				errors.add("", message);
			}
		}
		return true;
	}

	/**
	 * 売上詳細データのフォーマットを行う
	 * @param frm 売上詳細画面FORM
	 * @return AbstractSalesDetailForm 売上詳細画面FORM
	 */
	protected AbstractSalesDetailForm formatDetail(
			final AbstractSalesDetailForm frm) {

		String venderCd = frm.getVenderCd();
		frm.setStrSalesQuantity(checker.format(frm.getUnitDivision(),
			SalesConst.VENDER_DIVISION_TS, venderCd, AecNumberUtils
					.convertBigDecimal(frm.getStrSalesQuantity()))); // 数量
		frm.setStrStandardUnitprice(checker.format(
			frm.getUnitDivisionUrTanka(), SalesConst.VENDER_DIVISION_TS,
			venderCd, AecNumberUtils.convertBigDecimal(frm
					.getStrStandardUnitprice()))); // 標準単価
		frm.setStrStandardDiscount(checker.format(frm.getUnitDivisionUrTanka(),
			SalesConst.VENDER_DIVISION_TS, venderCd, AecNumberUtils
					.convertBigDecimal(frm.getStrStandardDiscount()))); // 標準値引
		frm.setStrSpecialDiscount(checker.format(frm.getUnitDivisionUrTanka(),
			SalesConst.VENDER_DIVISION_TS, venderCd, AecNumberUtils
					.convertBigDecimal(frm.getStrSpecialDiscount()))); // 特別値引
		return frm;
	}

	/**
	 * 売上データの登録処理を行う
	 * @param frm 売上詳細画面FORM
	 * @param loginInfo ログイン情報
	 * @return String 売上番号
	 * @throws Exception 例外
	 */
	protected String insert(final AbstractSalesDetailForm frm,
			final LoginInfo loginInfo) throws Exception {
		String salesNo = null;
		try {
			Sales bean = new Sales();

			// 画面の内容をBeanにセット
			setSalesBean(frm, loginInfo, bean, true);

			// 売上トランザクション登録処理
			int insertNum = salesDao.insert(bean);
			if (insertNum != 1) {
				// 一意制約エラー
				throw new DuplicateRuntimeException(0);
			}

			// 返品の場合
			if (bean.getCategoryDivision().equals(
				SalesConst.CATEGOTRY_DIVISION_RETURNS)
					|| bean.getCategoryDivision().equals(
						SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE)) {
				String errMsg = "errors.sales.stock.update";
				try {
					// 在庫更新処理
					Stockinout stock = new Stockinout(zaiCtrlDao);
					// 在庫更新－返品入庫
					if (!stock.returnDelivery(bean.getSalesNo(), loginInfo
							.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}
			salesNo = bean.getSalesNo();

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 一意制約エラー
			throw new DuplicateRuntimeException(0);
		}
		return salesNo;
	}

	/**
	 * 売上データの更新処理を行う
	 * @param frm 売上詳細画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	protected void update(final AbstractSalesDetailForm frm,
			final LoginInfo loginInfo) throws NoDataException, Exception {

		try {
			Sales bean = salesDao.getEntity(frm.getSalesNo());
			if (bean == null) {
				// 対象データ無し
				throw new NoDataException();
			}

			// 画面の内容をBeanにセット
			setSalesBean(frm, loginInfo, bean, false);
			bean.setSalesNo(bean.getSalesNo()); // 売上番号
			bean.setUpdateDate(frm.getUpdateDate());
			
			// 返品の場合
			if (bean.getCategoryDivision().equals(
				SalesConst.CATEGOTRY_DIVISION_RETURNS)
					|| bean.getCategoryDivision().equals(
						SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE)) {
				String errMsg = "errors.sales.stock.update";
				try {
					// 在庫更新処理
					Stockinout stock = new Stockinout(zaiCtrlDao);
					// 在庫更新－返品入庫取消
					if (!stock.deReturnDelivery(bean.getSalesNo(), loginInfo
							.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}

			// 売上トランザクション更新
			salesDao.update(bean);

			// 返品の場合
			if (bean.getCategoryDivision().equals(
				SalesConst.CATEGOTRY_DIVISION_RETURNS)
					|| bean.getCategoryDivision().equals(
						SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE)) {
				String errMsg = "errors.sales.stock.update";
				try {
					// 在庫更新処理
					Stockinout stock = new Stockinout(zaiCtrlDao);
					// 在庫更新－返品入庫
					if (!stock.returnDelivery(bean.getSalesNo(), loginInfo
							.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 売上データの登録処理を行う
	 * @param frm 売上詳細画面FORM
	 * @param loginInfo ログイン情報
	 * @param newFlag 新規フラグ true:新規 false:更新
	 * @return bean 売上トランザクションBean
	 * @throws Exception 例外
	 */
	private Sales setSalesBean(final AbstractSalesDetailForm frm,
			final LoginInfo loginInfo, final Sales bean, final boolean newFlag)
			throws Exception {
		// 数値フォーマット
		formatDetail(frm);

		// 会社コード取得
		String companyCd = salesDetailEntityDao.getCompanyCd();
		// 売上トラン登録用データ取得(関連データ)
		SalesDetailEntity salesBean = salesDetailEntityDao.getSalesForRegist(
			loginInfo.getTantoCd(), companyCd, frm.getDeliveryCd(), frm
					.getCategoryDivision(), frm.getVenderCd(), frm.getItemCd());
		if (salesBean == null) {
			// 対象データなし
			throw new LogicException();
		}

		// 売上番号取得
		if (newFlag) {
			String salesNo = getNumberLogic.getUrSlipNo();
			bean.setSalesNo(salesNo); // 売上番号
			frm.setSalesNo(salesNo);
		}
		bean.setSalesDate(AecDateUtils.getTimestampYmdHmFormat(frm
				.getStrSalesDate(), "yyyy/MM/dd")); // 売上日
		bean.setItemCd(frm.getItemCd()); // 品目コード
		bean.setOrganizationCd(loginInfo.getOrganizationCd()); // 部署コード
		bean.setVenderCd(frm.getVenderCd()); // 得意先コード
		bean.setBalanceCd(frm.getBalanceCd()); // 帳合コード
		bean.setInputTantoCd(loginInfo.getTantoCd()); // 入力担当者コード
		bean.setSalesTantoCd(salesBean.getSalesTantoCd()); // 営業担当コード
		bean.setSalesQuantity(convertBigDecimal(frm.getStrSalesQuantity())); // 売上数量
		bean.setStandardUnitprice(convertBigDecimal(frm
				.getStrStandardUnitprice())); // 標準単価
		bean
				.setStandardDiscount(convertBigDecimal(frm
						.getStrStandardDiscount())); // 標準値引
		bean.setSpecialDiscount(convertBigDecimal(frm.getStrSpecialDiscount())); // 特別値引

		// 売上単価 = 標準単価 - 標準値引 - 特別値引
		BigDecimal unitPrice = bean.getStandardUnitprice().subtract(
			bean.getStandardDiscount()).subtract(bean.getSpecialDiscount());
		bean.setSalesUnitprice(unitPrice);
		// 仮単価FLG
		if (frm.getTmpUnitpriceFlg()) {
			bean.setTmpUnitpriceFlg(SalesConst.TMP_UNITPRICE_FLG_TMP);
			bean.setSlipPublishComp(SLIP_UNPUBLISH);
			bean.setSlipPublishDate(null);
		} else {
			bean.setTmpUnitpriceFlg(TMP_UNITPRICE_FLG_NORMAL);
		}
		bean.setRemark(frm.getRemark()); // 備考
		bean.setSummary(frm.getSummary()); // 適用
		bean.setInputDivision(bean.getInputDivision()); // 入力区分
		bean.setDeliveryCd(frm.getDeliveryCd()); // 納入先コード
		
		bean.setTaxCd(frm.getTaxCd());

		// 売上金額 = 売上単価 × 数量
		BigDecimal salesAmount = bean.getSalesUnitprice().multiply(
			bean.getSalesQuantity());
		bean.setSalesAmount(salesAmount);
		// 丸め処理
		BigDecimal salesAmountRd = checker.round(
			SalesConst.UNIT_DIVISION_URKINGAKU, SalesConst.VENDER_DIVISION_TS,
			frm.getVenderCd(), salesAmount);
		bean.setSalesAmount(salesAmountRd);
		// 2014/1/29 ->新消費税対応
		// bean.setTaxDivision(salesBean.getTaxDivision()); // 消費税課税区分
		// 画面のコンボボックスから取得
		bean.setTaxDivision(new BigDecimal(frm.getStrTaxDivision()));
		// 2014/1/29 ->新消費税対応
		// 消費税額設定
		BigDecimal calcDiv = salesBean.getCalcDivision(); // 取引先ﾏｽﾀ.算出区分
		BigDecimal compCalcDiv = salesBean.getCompCalcDivision(); // 自社ﾏｽﾀ.消費税算出区分
		// 取引先ﾏｽﾀ.算出区分＝1(明細)の場合
		// 取引先ﾏｽﾀ.算出区分＝4(自社ﾏｽﾀ) かつ 自社ﾏｽﾀ.消費税算出区分＝1(明細)の場合
		if (CALC_DIVISION_MEISAI.equals(calcDiv)
				|| (CALC_DIVISION_COMPANY.equals(calcDiv) && CALC_DIVISION_MEISAI
						.equals(compCalcDiv))) {

			// 消費税課税区分=1:外税または2:内税
			// 2014/2/3 消費税対応 ->
			// if (TAX_DIVISION_OUT.equals(salesBean.getTaxDivision())) {
			if (SalesConst.TAX_DIVISION_OUT.equals(frm.getStrTaxDivision())) {
				// 外税処理
				// 2014/2/3 消費税対応 <-

				// 消費税率は％のため100で割る

				// 2014/1/29 ->新消費税対応
				// BigDecimal taxRatio = salesBean.getTaxRatio().divide(
				// new BigDecimal(100));
				BigDecimal taxRatio;
				if (frm.getStrTaxRatio() == null
						|| frm.getStrTaxRatio().equals("")) {
					taxRatio = BigDecimal.ZERO;
				} else {
					taxRatio = new BigDecimal(frm.getStrTaxRatio())
							.divide(new BigDecimal(100));
				}
				// 2014/1/29 ->新消費税対応

				// 消費税額＝売上金額×消費税率
				BigDecimal taxAmount = salesAmountRd.multiply(taxRatio);
				// 丸め処理
				taxAmount = checker
						.round(SalesConst.UNIT_DIVISION_TAX_AMOUNT,
							SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(),
							taxAmount);
				bean.setTaxAmount(taxAmount);
				// 消費税課税区分=2:内税
				// 2014/2/3 消費税対応 ->
				// } else if
				// (TAX_DIVISION_IN.equals(salesBean.getTaxDivision())) {
			} else if (SalesConst.TAX_DIVISION_IN.equals(frm.getStrTaxDivision())) {
				// 内税処理
				// 2014/2/3 消費税対応 <-

				double dblSalesAmount = 0;
				double dblTaxRate = 0;
				double dblTalAmount = 0;

				if (salesAmount != null) {
					dblSalesAmount = salesAmount.doubleValue();
				} else {
					dblSalesAmount = 0;
				}
				// 2014/1/29 ->新消費税対応
				// if (salesBean.getTaxRatio() != null) {
				// dblTaxRate = salesBean.getTaxRatio().doubleValue();
				// } else {
				// dblTaxRate = 0;
				// }
				if (frm.getStrTaxRatio() == null
						|| frm.getStrTaxRatio().equals("")) {
					dblTaxRate = 0;
				} else {
					dblTaxRate = new BigDecimal(frm.getStrTaxRatio())
							.doubleValue();
				}
				// 2014/1/29 ->新消費税対応

				// 内税計算 = 金額 * 消費税率 /( 100 + 消費税率)
				dblTalAmount = dblSalesAmount * dblTaxRate / (100 + dblTaxRate);

				BigDecimal taxAmount = new BigDecimal(dblTalAmount);

				// 丸め処理
				taxAmount = checker
						.round(SalesConst.UNIT_DIVISION_TAX_AMOUNT,
							SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(),
							taxAmount);

				if (taxAmount != null) {
					dblSalesAmount = dblSalesAmount - taxAmount.doubleValue();
				}
				bean.setTaxAmount(taxAmount);
				bean.setSalesAmount(new BigDecimal(dblSalesAmount));

			} else {
				// 非課税処理
				bean.setTaxAmount(BigDecimal.ZERO);
				bean.setTaxCd("00");
			}
		} else {
			bean.setTaxAmount(BigDecimal.ZERO);
		}
		bean.setInvoiceCd(salesBean.getInvoiceCd()); // 請求先コード
		// 2014/1/29 ->新消費税対応
		// bean.setTaxRatio(salesBean.getTaxRatio()); // 消費税率(丸めない)
		bean.setTaxRatio(new BigDecimal(frm.getStrTaxRatio()));
		// 2014/1/29 <-新消費税対応

		bean.setDataType(DATA_TYPE_SALES); // データ種別
		bean.setAccountYears(AecDateUtils.dateFormat(AecDateUtils
				.getTimestampYmdHmFormat(frm.getStrAccountYears(), "yyyy/MM"),
			"yyyyMM")); // 勘定年月
		bean.setAccountDebitSectionCd(frm.getAccountDebitSectionCd()); // 会計部門借方コード
		bean.setAccountCreditSectionCd(frm.getAccountCreditSectionCd()); // 会計部門貸方コード
		bean.setDebitTitleCd(frm.getDebitTitleCd()); // 借方科目コード
		bean.setCreditTitleCd(frm.getCreditTitleCd()); // 貸方科目コード
		bean.setChargeOrganizationCd(frm.getChargeOrganizationCd()); // 担当部署コード
		bean.setRyCd(frm.getRyCd()); // 理由コード
		bean.setHousingLocationCd(frm.getHousingLocationCd()); // 入庫ロケーション
		bean.setPackageDirectionNo(frm.getPackageDirectionNo()); // 包装指図番号
		bean.setProductLotno(frm.getProductLotno()); // 製品ロット番号
		bean.setUpdatorCd(loginInfo.getTantoCd()); // 更新者

		// 新規の場合のみセットする項目
		if (newFlag) {
			bean.setDataTotalDivision(salesBean.getDataTotalDivision()); // データ集計区分
			bean.setCategoryDivision(frm.getCategoryDivision()); // 分類コード
			bean.setInputDivision(INPUT_DIVISION_SALES); // 入力区分
			bean.setKeepFlag(frm.getKeepFlag()); // 預り品フラグ
			bean.setInputorCd(loginInfo.getTantoCd()); // 登録者
			bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 録日時
		}

		// 担当部署を再取得する（オートコンプリート未選択時対応
		Vender venderBean = chargeOrganizationDao.getEntity(frm.getVenderCd(),
			SalesConst.VENDER_DIVISION_TS);
		if (venderBean != null) {
			bean.setChargeOrganizationCd(venderBean.getOrganizationCd());
		}

		bean.setTaxAmount(AecNumberUtils.convertBigDecimal(frm.getStrTaxAmount()));
		bean.setTaxRatio(new BigDecimal(commonLogic.getTaxRatio(bean.getTaxCd(), null, null, null, null)));

		return bean;
	}
	
	/**
	 * 売上の仮単価フラグの更新処理を行う 
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void tmpUnitflagUpdate(final SalesDetailKeepForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception {
		
		Sales bean = salesDao.getEntity(frm.getSalesNo());
		if (bean == null) {
			// 対象データ無し
			throw new NoDataException();
		}
		
		// 更新日時、更新者、仮単価フラグセット
		bean.setTmpUnitpriceFlg(BigDecimal.ONE);
		bean.setUpdateDate(frm.getUpdateDate());
		bean.setUpdatorCd(loginInfo.getTantoCd());
	
		try {
			
		// 売上仮単価更新
		salesDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 数値データを数値換算して返す
	 * @param inputData 入力数量
	 * @param unitOfOperationManagement 運用管理単位
	 * @param venderCd 得意先コード
	 * @return String 受払数量
	 */
	public String getExchangeQty(final BigDecimal inputData,
			final String unitOfOperationManagement, final String venderCd) {

		return checker.format(unitOfOperationManagement,
			SalesConst.VENDER_DIVISION_TS, venderCd, inputData);

	}

	/**
	 * 請求先で最大の売掛締め日取得
	 * @param invoiceCd invoiceCd
	 * @param salesDate salesDate
	 * @return SalesCancelCheckDeliveryUpdate
	 */
	public SalesCancelCheckDeliveryUpdate getMaxDeliveryUpdateDate(
			final String invoiceCd, final String salesDate) {

		SalesCancelCheckDeliveryUpdate bean = salesCancelCheckDeliveryUpdateDao
				.getMaxDeliveryUpdateDate(invoiceCd, salesDate);

		return bean;

	}

	/**
	 * 請求先で最大の請求締め日取得
	 * @param invoiceCd invoiceCd
	 * @param salesDate salesDate
	 * @return SalesCancelCheckInvoiceUpdate
	 */
	public SalesCancelCheckInvoiceUpdate getMaxInvoiceUpdateDate(
			final String invoiceCd, final String salesDate) {

		SalesCancelCheckInvoiceUpdate bean = salesCancelCheckInvoiceUpdateDao
				.getMaxInvoiceUpdateDate(invoiceCd, salesDate);

		return bean;

	}

	// 2014/1/29 ->新消費税対応
	/**
	 * 消費税額を取得する
	 * @param date 消費税取得日
	 * @return 消費税率
	 */
/*	public String getTaxRatio(final String date) {
		return getTaxRatioLogic.getTaxRatio(date);
	}*/
	//軽減税率対応
	public String getTaxRatio(final String taxcd) {
		return getTaxRatioLogic.getTaxRatio(taxcd);
	}
	
	// 2014/1/29 <-新消費税対応

	/**
	 * 売上ﾄﾗﾝｻﾞｸｼｮﾝデータ取得
	 * @param salesNo salesNo
	 * @return Sales
	 */
	public Sales getSalesData(final String salesNo) {

		return salesDao.getEntity(salesNo);

	}

	/**
	 * StringからBigDecimalへ型変換を行う
	 * @param strVal String値
	 * @return BigDecimal BigDecimal型に変換した値
	 */
	protected BigDecimal convertBigDecimal(final String strVal) {
		BigDecimal val = AecNumberUtils.convertNullToZero(AecNumberUtils
				.convertBigDecimal(strVal));
		return val;
	}

	/**
	 * 数値桁数チェック ロジッククラス取得
	 * @return CheckDigitUtilsLogic 数値桁数チェック ロジッククラス
	 */
	protected CheckDigitUtilsLogic getChecker() {
		return this.checker;
	}
	
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
	public ComboboxesBean getSalesTaxCombobox() throws NoDataException {

		ComboboxesBean bean = new ComboboxesBean();

		// コンボボックスデータを取得
		List<TaxMasterListForComboboxes> list = taxMasterListForComboboxesDao
				.getSalesTaxListForComboboxes();
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
	 * 売上－入庫ロケーションコンボボックス用Daoを設定する
	 * @param salesHousingLocationDao 売上－入庫ロケーションコンボボックス用Dao
	 */
	public void setSalesHousingLocationDao(
			final SalesHousingLocationForComboboxesDao salesHousingLocationDao) {
		this.salesHousingLocationDao = salesHousingLocationDao;
	}

	/**
	 * 会計部門マスタDaoを設定します。
	 * @param bumonDao 会計部門マスタDao
	 */
	public void setBumonDao(final BumonDao bumonDao) {
		this.bumonDao = bumonDao;
	}

	/**
	 * 品目マスタDaoを設定します。
	 * @param itemDao 品目マスタDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 理由マスタ用Daoを設定する
	 * @param reasonDao 理由マスタ用Dao
	 */
	public void setReasonDao(final ReasonDao reasonDao) {
		this.reasonDao = reasonDao;
	}

	/**
	 * 売上トランザクション用Daoを設定する
	 * @param salesDao 売上トランザクション用Dao
	 */
	public void setSalesDao(final SalesDao salesDao) {
		this.salesDao = salesDao;
	}

	/**
	 * 売上詳細画面_売上トランザクション用Daoを設定します。
	 * @param salesDetailEntityDao 売上詳細画面_売上トランザクション用Dao
	 */
	public void setSalesDetailEntityDao(
			final SalesDetailEntityDao salesDetailEntityDao) {
		this.salesDetailEntityDao = salesDetailEntityDao;
	}

	/**
	 * 発番処理用ロジッククラスoを設定します。
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
	 * salesAccountYearsDaoを設定します。
	 * @param salesAccountYearsDao salesAccountYearsDao
	 */
	public void setSalesAccountYearsDao(
			final SalesAccountYearsDao salesAccountYearsDao) {
		this.salesAccountYearsDao = salesAccountYearsDao;
	}

	/**
	 * articleAttributeQueueDaoを設定します。
	 * @param articleAttributeQueueDao articleAttributeQueueDao
	 */
	public void setArticleAttributeQueueDao(
			final ArticleAttributeQueueDao articleAttributeQueueDao) {
		this.articleAttributeQueueDao = articleAttributeQueueDao;
	}

	/**
	 * chargeOrganizationDaoを設定します。
	 * @param chargeOrganizationDao chargeOrganizationDao
	 */
	public void setChargeOrganizationDao(final VenderDao chargeOrganizationDao) {
		this.chargeOrganizationDao = chargeOrganizationDao;
	}

	/**
	 * salesCancelCheckDeliveryUpdateDaoを設定します。
	 * @param salesCancelCheckDeliveryUpdateDao
	 *            salesCancelCheckDeliveryUpdateDao
	 */
	public void setSalesCancelCheckDeliveryUpdateDao(
			final SalesCancelCheckDeliveryUpdateDao salesCancelCheckDeliveryUpdateDao) {
		this.salesCancelCheckDeliveryUpdateDao = salesCancelCheckDeliveryUpdateDao;
	}

	/**
	 * salesCancelCheckInvoiceUpdateDaoを設定します。
	 * @param salesCancelCheckInvoiceUpdateDao salesCancelCheckInvoiceUpdateDao
	 */
	public void setSalesCancelCheckInvoiceUpdateDao(
			final SalesCancelCheckInvoiceUpdateDao salesCancelCheckInvoiceUpdateDao) {
		this.salesCancelCheckInvoiceUpdateDao = salesCancelCheckInvoiceUpdateDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param getTaxRatioLogic getTaxRatioLogic
	 */
	public void setGetTaxRatioLogic(final GetTaxRatioLogic getTaxRatioLogic) {
		this.getTaxRatioLogic = getTaxRatioLogic;
	}

	/**
	 * taxMasterListForComboboxesDaoを設定します。
	 * @param taxMasterListForComboboxesDao taxMasterListForComboboxesDao
	 */
	public void setTaxMasterListForComboboxesDao(
			TaxMasterListForComboboxesDao taxMasterListForComboboxesDao) {
		this.taxMasterListForComboboxesDao = taxMasterListForComboboxesDao;
	}

	/**
	 * commonLogicを設定します。
	 * @param commonLogic commonLogic
	 */
	public void setCommonLogic(CommonLogic commonLogic) {
		this.commonLogic = commonLogic;
	}
}
