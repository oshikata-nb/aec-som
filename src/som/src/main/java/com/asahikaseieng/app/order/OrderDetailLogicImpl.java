/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.stockinout.StockinoutForOrder;
import com.asahikaseieng.app.common.stockinout.StockinoutForPurchase;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.aspproduction.AspProduction;
import com.asahikaseieng.dao.entity.aspproduction.AspProductionDao;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventoryDao;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueue;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.entity.master.carry.Carry;
import com.asahikaseieng.dao.entity.master.carry.CarryDao;
import com.asahikaseieng.dao.entity.master.common.Common;
import com.asahikaseieng.dao.entity.master.common.CommonDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueue;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.orderdetail.OrderDetail;
import com.asahikaseieng.dao.entity.orderdetail.OrderDetailDao;
import com.asahikaseieng.dao.entity.orderhead.OrderHead;
import com.asahikaseieng.dao.entity.orderhead.OrderHeadDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.comboboxes.order.OrderCarryListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.order.OrderCarryListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.orderdetailaspproduction.OrderDetailAspProduction;
import com.asahikaseieng.dao.nonentity.orderdetailaspproduction.OrderDetailAspProductionDao;
import com.asahikaseieng.dao.nonentity.orderdetailentity.OrderDetailEntity;
import com.asahikaseieng.dao.nonentity.orderdetailentity.OrderDetailEntityDao;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailList;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailListDao;
import com.asahikaseieng.dao.nonentity.orderdetailpurchasesubcontract.OrderDetailPurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.orderdetailpurchasesubcontract.OrderDetailPurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.orderdetailsalesterms.OrderDetailSalesTerms;
import com.asahikaseieng.dao.nonentity.orderdetailsalesterms.OrderDetailSalesTermsDao;
import com.asahikaseieng.dao.nonentity.orderdetailunitprice.OrderDetailUnitprice;
import com.asahikaseieng.dao.nonentity.orderdetailunitprice.OrderDetailUnitpriceDao;
import com.asahikaseieng.dao.nonentity.orderdetailvenderdetail.OrderDetailVenderDetail;
import com.asahikaseieng.dao.nonentity.orderdetailvenderdetail.OrderDetailVenderDetailDao;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderList;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderListDao;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesList;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesListDao;
import com.asahikaseieng.dao.nonentity.ordernogoodqty.OrderNoGoodQty;
import com.asahikaseieng.dao.nonentity.ordernogoodqty.OrderNoGoodQtyDao;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCalcDateFromCalendarCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 受注詳細ロジック 実装クラス.
 * @author t1344224
 */
public class OrderDetailLogicImpl implements OrderDetailLogic {

	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	private OrderDetailEntityDao orderDetailEntityDao;

	private OrderDetailListDao orderDetailListDao;

	private OrderCarryListForComboboxesDao orderCarryListForComboboxesDao;

	private OrderDetailVenderListDao orderDetailVenderListDao;

	private OrderDetailVenderDetailDao orderDetailVenderDetailDao;

	private OrderRemarkListDao orderRemarkListDao;

	private ItemInventoryDao itemInventoryDao;

	private ItemDao itemDao;

	private BalanceDao balanceDao;

	private OrderDetailSalesTermsDao orderDetailSalesTermsDao;

	private OrderHeadDao orderHeadDao;

	private OrderDetailDao orderDetailDao;

	private CommonDao commonDao;

	private GetNumberLogic getNumberLogic;

	private AspProductionDao aspProductionDao;

	private OrderDetailAspProductionDao orderDetailAspProductionDao;

	private VenderDao venderDao;

	private PurchaseAttributeQueueDao purchaseAttributeQueueDao;

	private PurchaseSubcontractDao purchaseSubcontractDao;

	private OrderDetailUnitpriceDao orderDetailUnitpriceDao;

	private OrderDetailPurchaseSubcontractDao orderDetailPurchaseSubcontractDao;

	private CarryDao carryDao;

	private ArticleAttributeQueueDao articleAttributeQueueDao;

	private OrderNoGoodQtyDao orderNoGoodQtyDao;

	private OrderNamesListDao orderNamesListDao;

	private LoginDao loginDao;

	private ProcedureCallDao procedureCallDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	/**
	 * コンストラクタ.
	 */
	public OrderDetailLogicImpl() {
	}

	/**
	 * 更新処理を行う
	 * @param frm 更新対象画面フォーム
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザ部署コード
	 * @throws Exception 例外
	 */
	public void update(final OrderDetailForm frm, final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {
		String errMsg = "errors.order.stock.update";
		try {

			// *********************************************************************
			// OrderHeadの登録処理
			// *********************************************************************
			// 登録対象データを作成する
			OrderHead newOrderHeadBean = orderHeadDao.getEntity(frm
					.getOrderNo());

			// コピーできるものはコピー。
			IgnoreCaseBeanUtils.copyProperties(newOrderHeadBean, frm);

			// 受注区分が３仕入直送品以外の場合
			if (!frm.getOrderDivision().equals("3")) {
				try {
					// 在庫更新処理
					StockinoutForOrder stock = new StockinoutForOrder(
							zaiCtrlDao);
					// 在庫更新－受注取消(全）
					if (!stock.cancelOrder(newOrderHeadBean.getOrderNo(),
						loginUserId)) {
						OrderLogicException ex = new OrderLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					OrderLogicException ex = new OrderLogicException(errMsg, "");
					ex.setModuleCd("StockinoutForOrder");
					ex.setInsideErrCd(newOrderHeadBean.getOrderNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}

			newOrderHeadBean.setInputTantoCd(loginUserId); // 入力担当者コードをセット
			newOrderHeadBean.setSalesTantoCd(frm.getEigyoTantoCd()); // 営業担当者コードをセット

			// 得意先コードのセット
			List<OrderDetailVenderList> venderList = frm
					.getOrderDetailVenderList();
			if (venderList != null && venderList.size() != 0) {
				newOrderHeadBean.setVenderCd(venderList.get(0).getVenderCd());
			}
			// 合計金額をセット
			newOrderHeadBean.setOrderAmount(frm.getOrderAmount());

			// ステータスをセット 新規時は1:受注登録をセット
			// newOrderHeadBean.setStatus(new BigDecimal(1));

			// 運賃請求フラグ
			if (frm.getCarryInvoiceFlag() == Boolean.TRUE) {
				newOrderHeadBean.setCarryInvoiceFlag(new BigDecimal(1));
			} else {
				newOrderHeadBean.setCarryInvoiceFlag(new BigDecimal(0));
			}

			// ファイルのアップロード
			String outFile = upload(frm); // ファイル名が帰ってくる
			newOrderHeadBean.setOrderPicture(outFile); // ファイル名セット

			// 更新者セット
			newOrderHeadBean.setUpdateDate(frm.getUpdateDate());
			newOrderHeadBean.setUpdatorCd(loginUserId);

			// 2015/08/17 納入先電話番号を更新する様修正
			newOrderHeadBean.setDeliveryTelNo(frm.getTelNo());

			// 受注ヘッダテーブルを更新
			orderHeadDao.update(newOrderHeadBean);

			// *********************************************************************
			// OrderDetailの登録処理
			// *********************************************************************
			BigDecimal maxRow = orderDetailListDao.getOrderDetailMaxRow(frm
					.getOrderNo());
			int rowNo = maxRow.intValue() + 1;
			for (OrderDetailList detailListBean : frm.getOrderDetailList()) {
				// RowNoが入っているときは更新処理
				if (detailListBean.getRowNo() != null
						&& !detailListBean.getRowNo().equals("")) {
					OrderDetail orderDetailBean = orderDetailDao.getEntity(frm
							.getOrderNo(), new BigDecimal(detailListBean
							.getRowNo()));

					IgnoreCaseBeanUtils.copyProperties(orderDetailBean,
						detailListBean);

					// 更新者セット
					newOrderHeadBean.setUpdateDate(detailListBean
							.getUpdateDate());
					newOrderHeadBean.setUpdatorCd(loginUserId);

					orderDetailDao.update(orderDetailBean);

				} else {
					// 登録対象データを作成する
					OrderDetail newOrderDetailBean = new OrderDetail();
					// コピーできるものはコピー。
					IgnoreCaseBeanUtils.copyProperties(newOrderDetailBean,
						detailListBean);

					// 受注番号設定
					newOrderDetailBean
							.setOrderNo(newOrderHeadBean.getOrderNo());
					// 行番号設定
					newOrderDetailBean.setRowNo(new BigDecimal(rowNo));
					detailListBean.setRowNo(Integer.toString(rowNo));

					// 今を取得
					Timestamp now = AecDateUtils.getCurrentTimestamp();
					// 登録者セット
					newOrderDetailBean.setInputDate(now);
					newOrderDetailBean.setInputorCd(loginUserId);
					// 更新者セット
					newOrderDetailBean.setUpdateDate(now);
					newOrderDetailBean.setUpdatorCd(loginUserId);

					// 受注詳細テーブル登録
					orderDetailDao.insert(newOrderDetailBean);

					rowNo++;
				}
			}

			// 削除対象テーブルにデータがあれば消す
			if (frm.getDelFormList() != null) {
				for (OrderDetailList deleteBean : frm.getDelFormList()) {
					if (deleteBean.getRowNo() != null
							&& !deleteBean.getRowNo().equals("")) {

						OrderDetail deleteOrderDetailBean = orderDetailDao
								.getEntity(frm.getOrderNo(), new BigDecimal(
										deleteBean.getRowNo()));

						IgnoreCaseBeanUtils.copyProperties(
							deleteOrderDetailBean, deleteBean);

						// 更新者セット
						newOrderHeadBean.setUpdateDate(deleteBean
								.getUpdateDate());
						newOrderHeadBean.setUpdatorCd(loginUserId);

						int delNum = orderDetailDao
								.delete(deleteOrderDetailBean);
						if (delNum == 0) {
							// 対象データ無し
							throw new NoDataException();
						}
					}
				}
			}

			// *********************************************************************
			// 仕入直送品の場合の購買トランの更新処理
			// *********************************************************************
			// 受注区分が３仕入直送品の場合
			if (frm.getOrderDivision().equals("3")) {

				// 在庫更新処理
				StockinoutForPurchase stock = new StockinoutForPurchase(
						zaiCtrlDao);

				for (OrderDetailList detailListBean : frm.getOrderDetailList()) {
					// (行番号は全て設定されている
					PurchaseSubcontract newPurSubBean = new PurchaseSubcontract();

					// PURCHASE_SUBCONTRACTからORDER_NOとROW_NOでデータを取得
					OrderDetailPurchaseSubcontract orDePurSubBean = orderDetailPurchaseSubcontractDao
							.getEntity(frm.getOrderNo(), detailListBean
									.getRowNo());

					// 取得できたら
					if (orDePurSubBean != null) {
						try {
							// 在庫更新－発注入力取消
							if (!stock.canselPurchase(orDePurSubBean
									.getPurchaseNo(), loginUserId)) {
								OrderLogicException ex = new OrderLogicException(
										errMsg, "");
								throw ex;
							}
						} catch (LogicExceptionEx e) {
							OrderLogicException ex = new OrderLogicException(
									errMsg, "");
							ex.setModuleCd("StockinoutForPurchase");
							ex.setInsideErrCd(frm.getOrderNo());
							ex.setInsideErrMsg(e.getMessage());
							throw ex;
						}

						// 取得したデータを更新用データにコピー
						IgnoreCaseBeanUtils.copyProperties(newPurSubBean,
							orDePurSubBean);

						purchaseSubcontractDao.delete(newPurSubBean);

						// 諸々データのコピー
						setPurchaseSubcontractBean(frm, detailListBean,
							newPurSubBean, loginUserId, loginUserOrganizationCd);

						// 更新者セット
						newPurSubBean.setUpdatorCd(loginUserId);

						purchaseSubcontractDao.insert(newPurSubBean);
					} else {
						// 発注番号
						String buySubcontractOrderNo = getNumberLogic
								.getBuySubcontractOrderNo();
						newPurSubBean
								.setBuySubcontractOrderNo(buySubcontractOrderNo);
						// 受注番号
						newPurSubBean.setOrderNo(frm.getOrderNo());
						// 受注行番号
						newPurSubBean.setOrderRowNo(new BigDecimal(
								detailListBean.getRowNo()));

						// 諸々データのコピー
						setPurchaseSubcontractBean(frm, detailListBean,
							newPurSubBean, loginUserId, loginUserOrganizationCd);

						// 今を取得
						Timestamp now = AecDateUtils.getCurrentTimestamp();
						// 登録者セット
						newPurSubBean.setInputDate(now);
						newPurSubBean.setInputorCd(loginUserId);
						// 更新者セット
						newPurSubBean.setUpdateDate(now);
						newPurSubBean.setUpdatorCd(loginUserId);

						purchaseSubcontractDao.insert(newPurSubBean);
					}
					try {
						// 購買Noを取得
						OrderDetailPurchaseSubcontract bean = orderDetailPurchaseSubcontractDao
								.getPurchaseNo(newPurSubBean
										.getBuySubcontractOrderNo());
						// 在庫更新－発注入力
						if (!stock.entryPurchase(bean.getPurchaseNo(),
							loginUserId)) {
							OrderLogicException ex = new OrderLogicException(
									errMsg, "");
							throw ex;
						}
					} catch (LogicExceptionEx e) {
						OrderLogicException ex = new OrderLogicException(
								errMsg, "");
						ex.setModuleCd("StockinoutForPurchase");
						ex.setInsideErrCd(frm.getOrderNo());
						ex.setInsideErrMsg(e.getMessage());
						throw ex;
					}
				}
				// 削除対象テーブルにデータがORDER_PRODUCTIONも消す
				if (frm.getDelFormList() != null) {
					for (OrderDetailList deleteBean : frm.getDelFormList()) {
						if (deleteBean.getRowNo() != null
								&& !deleteBean.getRowNo().equals("")) {

							// 更新用データ
							PurchaseSubcontract newPurSubBean = new PurchaseSubcontract();

							// PURCHASE_SUBCONTRACTからORDER_NOとROW_NOでデータを取得
							OrderDetailPurchaseSubcontract orDePurSubBean = orderDetailPurchaseSubcontractDao
									.getEntity(frm.getOrderNo(), deleteBean
											.getRowNo());

							if (orDePurSubBean != null) {
								// 取得したデータを更新用データにコピー
								IgnoreCaseBeanUtils.copyProperties(
									newPurSubBean, orDePurSubBean);

								// 更新者セット
								newPurSubBean.setUpdatorCd(loginUserId);
								int delNum = purchaseSubcontractDao
										.delete(newPurSubBean);
								if (delNum == 0) {
									// 対象データ無し
									throw new NoDataException();
								}
							}
						}
					}
				}
			} else {
				try {
					// 在庫更新処理
					StockinoutForOrder stock = new StockinoutForOrder(
							zaiCtrlDao);
					// 在庫更新－受注入力(全）
					if (!stock.entryOrder(newOrderHeadBean.getOrderNo(),
						loginUserId)) {
						OrderLogicException ex = new OrderLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					OrderLogicException ex = new OrderLogicException(errMsg, "");
					ex.setModuleCd("StockinoutForOrder");
					ex.setInsideErrCd(newOrderHeadBean.getOrderNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}

			// *********************************************************************
			// 受注生産の場合の生産要求ASPの登録処理
			// *********************************************************************
			// 受注区分が２受注生産の場合
			if (frm.getOrderDivision().equals("2")) {
				for (OrderDetailList detailListBean : frm.getOrderDetailList()) {
					// (行番号は全て設定されている

					// 更新用データ
					AspProduction aspProBean = new AspProduction();

					// ASP_PRODUCTIONからORDER_NOとROW_NOでデータを取得
					OrderDetailAspProduction orDeAspProBean = orderDetailAspProductionDao
							.getEntity(frm.getOrderNo(), detailListBean
									.getRowNo());
					// 取得できたら
					if (orDeAspProBean != null) {
						// 取得できた場合は更新処理
						// 主キーが変更される場合もあるため、削除してから、インサートする

						// 取得したデータを更新用データにコピー
						IgnoreCaseBeanUtils.copyProperties(aspProBean,
							orDeAspProBean);
						aspProductionDao.delete(aspProBean);

						// 品目コード
						aspProBean.setItemCd(detailListBean.getItemCd());
						// 納期をセット
						aspProBean.setOrderLet(frm.getScheduledShippingDate());
						// 受注数量セット
						aspProBean.setOrderAcceptQty(detailListBean
								.getOrderQty());

						// 更新者セット
						aspProBean.setUpdatorCd(loginUserId);

						aspProductionDao.insert(aspProBean);
					} else {
						// 取得できない場合は新規追加処理
						AspProduction newAspProBean = new AspProduction();

						// オーダーコード
						Calendar cal1 = Calendar.getInstance(); // システム日時を取得
						cal1.add(Calendar.DAY_OF_MONTH, -1); // 日付-1
						SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
						String date = df.format(cal1.getTime());

						String orderCd = "O_" + frm.getOrderNo() + "_" + date;
						newAspProBean.setOrderCd(orderCd);

						// 品目コード
						newAspProBean.setItemCd(detailListBean.getItemCd());
						// 納期
						newAspProBean.setOrderLet(frm
								.getScheduledShippingDate());
						// 受注数量
						newAspProBean.setOrderAcceptQty(detailListBean
								.getOrderQty());
						// 受注番号
						newAspProBean.setOrderNo(frm.getOrderNo());
						// 受注行番号
						newAspProBean.setOrderRowNo(new BigDecimal(
								detailListBean.getRowNo()));

						// 今を取得
						Timestamp now = AecDateUtils.getCurrentTimestamp();
						// 登録者セット
						newAspProBean.setInputDate(now);
						newAspProBean.setInputorCd(loginUserId);
						// 更新者セット
						newAspProBean.setUpdateDate(now);
						newAspProBean.setUpdatorCd(loginUserId);

						aspProductionDao.insert(newAspProBean);
					}
				}
				// 削除対象テーブルにデータがORDER_PRODUCTIONも消す
				if (frm.getDelFormList() != null) {
					for (OrderDetailList deleteBean : frm.getDelFormList()) {
						if (deleteBean.getRowNo() != null
								&& !deleteBean.getRowNo().equals("")) {

							// 更新用データ
							AspProduction aspProBean = new AspProduction();

							// ASP_PRODUCTIONからORDER_NOとROW_NOでデータを取得
							OrderDetailAspProduction orDeAspProBean = orderDetailAspProductionDao
									.getEntity(frm.getOrderNo(), deleteBean
											.getRowNo());

							if (orDeAspProBean != null) {
								// 取得したデータを更新用データにコピー
								IgnoreCaseBeanUtils.copyProperties(aspProBean,
									orDeAspProBean);

								// 更新者セット
								aspProBean.setUpdatorCd(loginUserId);

								int delNum = aspProductionDao
										.delete(aspProBean);
								if (delNum == 0) {
									// 対象データ無し
									throw new NoDataException();
								}
							}
						}
					}
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 新規登録処理を行う
	 * @param frm 登録対象画面フォーム
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザ部署コード
	 * @throws Exception 例外
	 */
	public void insert(final OrderDetailForm frm, final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {
		String errMsg = "errors.order.stock.update";

		// *********************************************************************
		// OrderHeadの登録処理
		// *********************************************************************
		// 登録対象データを作成する
		OrderHead newOrderHeadBean = new OrderHead();

		// 発番
		frm.setOrderNo(getNumberLogic.getSupplierOrderNo());

		// コピーできるものはコピー。
		IgnoreCaseBeanUtils.copyProperties(newOrderHeadBean, frm);

		newOrderHeadBean.setInputTantoCd(loginUserId); // 入力担当者コードをセット
		newOrderHeadBean.setSalesTantoCd(frm.getEigyoTantoCd()); // 営業担当者コードをセット

		// 得意先コードのセット
		List<OrderDetailVenderList> venderList = frm.getOrderDetailVenderList();
		if (venderList != null && venderList.size() != 0) {
			newOrderHeadBean.setVenderCd(venderList.get(0).getVenderCd());
		}
		// 合計金額をセット
		newOrderHeadBean.setOrderAmount(frm.getOrderAmount());

		// ステータスをセット 新規時は1:受注登録をセット
		newOrderHeadBean.setStatus(new BigDecimal(1));

		// 運賃請求フラグ
		if (frm.getCarryInvoiceFlag() != null) {
			if (frm.getCarryInvoiceFlag() == Boolean.TRUE) {
				newOrderHeadBean.setCarryInvoiceFlag(new BigDecimal(1));
			} else {
				newOrderHeadBean.setCarryInvoiceFlag(new BigDecimal(0));
			}
		}

		// ファイルのアップロード
		String outFile = upload(frm); // ファイル名が帰ってくる
		newOrderHeadBean.setOrderPicture(outFile); // ファイル名セット

		// 今を取得
		Timestamp now = AecDateUtils.getCurrentTimestamp();
		// 登録者セット
		newOrderHeadBean.setInputDate(now);
		newOrderHeadBean.setInputorCd(loginUserId);
		// 更新者セット
		newOrderHeadBean.setUpdateDate(now);
		newOrderHeadBean.setUpdatorCd(loginUserId);

		// 2015/08/17 納入先電話番号を更新する様修正
		newOrderHeadBean.setDeliveryTelNo(frm.getTelNo());

		// 受注ヘッダテーブルに登録
		orderHeadDao.insert(newOrderHeadBean);

		// *********************************************************************
		// OrderDetailの登録処理
		// *********************************************************************
		BigDecimal maxRow = orderDetailListDao.getOrderDetailMaxRow(frm
				.getOrderNo());
		int rowNo = maxRow.intValue() + 1;
		for (OrderDetailList detailListBean : frm.getOrderDetailList()) {
			// 登録対象データを作成する
			OrderDetail newOrderDetailBean = new OrderDetail();
			// コピーできるものはコピー。
			IgnoreCaseBeanUtils.copyProperties(newOrderDetailBean,
				detailListBean);

			// 受注番号設定
			newOrderDetailBean.setOrderNo(newOrderHeadBean.getOrderNo());
			// 行番号設定
			newOrderDetailBean.setRowNo(new BigDecimal(rowNo));
			detailListBean.setRowNo(Integer.toString(rowNo));

			// 登録者セット
			newOrderDetailBean.setInputDate(now);
			newOrderDetailBean.setInputorCd(loginUserId);
			// 更新者セット
			newOrderDetailBean.setUpdateDate(now);
			newOrderDetailBean.setUpdatorCd(loginUserId);

			// 受注詳細テーブル登録
			orderDetailDao.insert(newOrderDetailBean);

			rowNo++;
		}

		// *********************************************************************
		// 仕入直送品の場合の購買トランの登録処理
		// *********************************************************************
		// 受注区分が３仕入直送品の場合
		if (frm.getOrderDivision().equals("3")) {
			for (OrderDetailList detailListBean : frm.getOrderDetailList()) {
				PurchaseSubcontract newPurSubBean = new PurchaseSubcontract();

				// 発注番号
				String buySubcontractOrderNo = getNumberLogic
						.getBuySubcontractOrderNo();
				newPurSubBean.setBuySubcontractOrderNo(buySubcontractOrderNo);
				// 受注番号
				newPurSubBean.setOrderNo(frm.getOrderNo());
				// 受注行番号
				newPurSubBean.setOrderRowNo(new BigDecimal(detailListBean
						.getRowNo()));

				// 諸々データのコピー
				setPurchaseSubcontractBean(frm, detailListBean, newPurSubBean,
					loginUserId, loginUserOrganizationCd);

				// 登録者セット
				newPurSubBean.setInputDate(now);
				newPurSubBean.setInputorCd(loginUserId);
				// 更新者セット
				newPurSubBean.setUpdateDate(now);
				newPurSubBean.setUpdatorCd(loginUserId);

				purchaseSubcontractDao.insert(newPurSubBean);

				try {
					// 在庫更新処理
					StockinoutForPurchase stock = new StockinoutForPurchase(
							zaiCtrlDao);
					// 購買Noを取得
					OrderDetailPurchaseSubcontract bean = orderDetailPurchaseSubcontractDao
							.getPurchaseNo(newPurSubBean
									.getBuySubcontractOrderNo());
					// 在庫更新－発注入力
					if (!stock.entryPurchase(bean.getPurchaseNo(), loginUserId)) {
						OrderLogicException ex = new OrderLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					OrderLogicException ex = new OrderLogicException(errMsg, "");
					ex.setModuleCd("StockinoutForPurchase");
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}
		} else {
			try {
				// 在庫更新処理
				StockinoutForOrder stock = new StockinoutForOrder(zaiCtrlDao);
				// 在庫更新－受注入力(全）
				if (!stock.entryOrder(newOrderHeadBean.getOrderNo(),
					loginUserId)) {
					OrderLogicException ex = new OrderLogicException(errMsg, "");
					throw ex;
				}
			} catch (LogicExceptionEx e) {
				OrderLogicException ex = new OrderLogicException(errMsg, "");
				ex.setModuleCd("StockinoutForOrder");
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}
		}

		// *********************************************************************
		// 受注生産の場合の生産要求ASPの登録処理
		// *********************************************************************
		// 受注区分が２受注生産の場合
		if (frm.getOrderDivision().equals("2")) {
			for (OrderDetailList detailListBean : frm.getOrderDetailList()) {
				AspProduction newAspProBean = new AspProduction();

				// オーダーコード
				Calendar cal1 = Calendar.getInstance(); // システム日時を取得
				cal1.add(Calendar.DAY_OF_MONTH, -1); // 日付-1
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String date = df.format(cal1.getTime());

				String orderCd = "O_" + frm.getOrderNo() + "_" + date;
				newAspProBean.setOrderCd(orderCd);

				// 品目コード
				newAspProBean.setItemCd(detailListBean.getItemCd());
				// 納期
				newAspProBean.setOrderLet(frm.getScheduledShippingDate());
				// 受注数量
				newAspProBean.setOrderAcceptQty(detailListBean.getOrderQty());
				// 受注番号
				newAspProBean.setOrderNo(frm.getOrderNo());
				// 受注行番号
				newAspProBean.setOrderRowNo(new BigDecimal(detailListBean
						.getRowNo()));

				// 登録者セット
				newAspProBean.setInputDate(now);
				newAspProBean.setInputorCd(loginUserId);
				// 更新者セット
				newAspProBean.setUpdateDate(now);
				newAspProBean.setUpdatorCd(loginUserId);

				aspProductionDao.insert(newAspProBean);
			}
		}
	}

	/**
	 * 削除処理を行う
	 * @param frm 削除対象が画面ビーン
	 * @param loginUserId ログインユーザー
	 * @throws Exception Exception
	 */
	public void delete(final OrderDetailForm frm, final String loginUserId)
			throws Exception {
		String errMsg = "errors.order.stock.update";
		try {
			// *********************************************************************
			// OrderHeadの削除処理
			// *********************************************************************
			// 削除対象データを作成する
			OrderHead delOrderHeadBean = orderHeadDao.getEntity(frm
					.getOrderNo());

			// コピーできるものはコピー。
			IgnoreCaseBeanUtils.copyProperties(delOrderHeadBean, frm);
			delOrderHeadBean.setUpdateDate(frm.getUpdateDate());
			delOrderHeadBean.setUpdatorCd(loginUserId);

			// 受注区分が３仕入直送品以外の場合
			if (!frm.getOrderDivision().equals("3")) {
				try {
					// 在庫更新処理
					StockinoutForOrder stock = new StockinoutForOrder(
							zaiCtrlDao);
					// 在庫更新－受注取消(全）
					if (!stock.cancelOrder(delOrderHeadBean.getOrderNo(),
						loginUserId)) {
						OrderLogicException ex = new OrderLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					OrderLogicException ex = new OrderLogicException(errMsg, "");
					ex.setModuleCd("StockinoutForOrder");
					ex.setInsideErrCd(delOrderHeadBean.getOrderNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}

			// ファイルの削除
			frm.setUploadFile(null);
			upload(frm);

			// 削除
			orderHeadDao.delete(delOrderHeadBean);

			// *********************************************************************
			// OrderDetailの削除処理
			// *********************************************************************
			orderDetailListDao.deleteByOrderNo(frm.getOrderNo());

			// *********************************************************************
			// 受注生産の場合の生産要求ASPの削除
			// *********************************************************************
			// 受注区分が２受注生産の場合
			if (frm.getOrderDivision().equals("2")) {
				orderDetailAspProductionDao.deleteByOrderNo(frm.getOrderNo());
			}

			// *********************************************************************
			// 仕入直送品の場合の購買トランの登録処理
			// *********************************************************************
			// 受注区分が３仕入直送品の場合
			if (frm.getOrderDivision().equals("3")) {
				try {
					// 在庫更新処理
					StockinoutForPurchase stock = new StockinoutForPurchase(
							zaiCtrlDao);
					List<OrderDetailPurchaseSubcontract> list;
					list = orderDetailPurchaseSubcontractDao
							.getPurchaseNoList(frm.getOrderNo());
					if (list != null && list.size() > 0) {
						for (OrderDetailPurchaseSubcontract bean : list) {
							// 在庫更新－発注入力取消
							if (!stock.canselPurchase(bean.getPurchaseNo(),
								loginUserId)) {
								OrderLogicException ex = new OrderLogicException(
										errMsg, "");
								throw ex;
							}
						}
					}
				} catch (LogicExceptionEx e) {
					OrderLogicException ex = new OrderLogicException(errMsg, "");
					ex.setModuleCd("StockinoutForPurchase");
					ex.setInsideErrCd(frm.getOrderNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
				orderDetailPurchaseSubcontractDao.deleteByOrderNo(frm
						.getOrderNo());
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 更新エラー OptimisticLockRuntimeExceptionをthrowする
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 購買テーブル情報設定
	 */
	private void setPurchaseSubcontractBean(final OrderDetailForm frm,
			final OrderDetailList detailListBean,
			final PurchaseSubcontract newPurSubBean, final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {

		// 発注日
		newPurSubBean.setOrderDate(frm.getOrderDate());

		// 納入先コード
		newPurSubBean.setLocationCd(frm.getDeliveryCd());
		// 2015/2/5 品目マスタのVerを取得する ->
		// // 仕入先コード 、担当部署コード
		// PurchaseAttributeQueue purAttQueBean = purchaseAttributeQueueDao
		// .getEntity(detailListBean.getItemCd(), detailListBean
		// .getVersion());
		Item itemBeanVersion = itemDao.getEntity(detailListBean.getItemCd());

		// 仕入先コード 、担当部署コード
		PurchaseAttributeQueue purAttQueBean = purchaseAttributeQueueDao
				.getEntity(itemBeanVersion.getItemCd(), itemBeanVersion
						.getVersion());
		// 2015/2/5 品目マスタのVerを取得する <-

		if (purAttQueBean != null) {
			newPurSubBean.setVenderCd(purAttQueBean.getVenderCd());

			if (StringUtils.isNotEmpty(purAttQueBean.getVenderCd())) {
				// 担当部署コード
				Vender venderBean = venderDao.getEntity(purAttQueBean
						.getVenderCd(), "SI");
				if (venderBean != null) {
					newPurSubBean.setChargeOrganizationCd(venderBean
							.getOrganizationCd());
				}
			} else {
				newPurSubBean.setChargeOrganizationCd(null);
			}
		} else {
			newPurSubBean.setVenderCd(null);
		}

		// 品目コード
		newPurSubBean.setItemCd(detailListBean.getItemCd());

		// 品目名称
		newPurSubBean.setItemName(detailListBean.getItemName());

		// 受注数量
		newPurSubBean.setOrderQuantity(detailListBean.getOrderQty());

		// 重量
		Item itemBean = itemDao.getEntity(detailListBean.getItemCd());
		if (itemBean != null) {
			newPurSubBean.setOrderConvertQuantity(detailListBean.getOrderQty()
					.multiply(itemBean.getKgOfFractionManagement()));
		}

		// 希望納期
		newPurSubBean.setSuggestedDeliverlimitDate(frm
				.getSuggestedDeliverlimit());

		// 発注書備考、備考(仕入先コードで備考を取得する)
		String venderCd = null;
		// if (frm.getOrderDetailVenderList().size() != 0) {
		// venderCd = frm.getOrderDetailVenderList().get(0).getVenderCd();
		// }
		venderCd = newPurSubBean.getVenderCd();

		List<OrderRemarkList> orRemarkList = orderRemarkListDao.getRemarkList(
			venderCd, frm.getDeliveryCd(), detailListBean.getItemCd());

		StringBuffer sbfRemark12 = new StringBuffer();
		StringBuffer sbfRemark13 = new StringBuffer();
		int lngRemark12 = 0;
		int lngRemark13 = 0;

		// 発注時備考に納入予定時間をセット
		if (frm.getDeliveryExpectedTime() != null
				&& !frm.getDeliveryExpectedTime().equals("")) {
			sbfRemark12.append(frm.getDeliveryExpectedTime());
			lngRemark12++;
		}
		if (orRemarkList != null) {

			for (int i = 0; i < orRemarkList.size(); i++) {
				if (orRemarkList.get(i).getRemark12() != null) {
					if (lngRemark12 != 0) {
						sbfRemark12
								.append(System.getProperty("line.separator"));
					}
					sbfRemark12.append(orRemarkList.get(i).getRemark12());
					lngRemark12++;
				}
				if (orRemarkList.get(i).getRemark13() != null) {
					if (lngRemark13 != 0) {
						sbfRemark13
								.append(System.getProperty("line.separator"));
					}
					sbfRemark13.append(orRemarkList.get(i).getRemark13());
					lngRemark13++;
				}
			}
		}
		newPurSubBean.setOrderSheetRemark(sbfRemark13.toString());
		newPurSubBean.setRemark(sbfRemark12.toString());

		// 発注単価
		OrderDetailUnitprice orderUnitprice = orderDetailUnitpriceDao
				.getOrderQuantityRelatedData(detailListBean.getItemCd(),
					newPurSubBean.getVenderCd(), detailListBean.getOrderQty());
		if (orderUnitprice != null) {
			newPurSubBean.setOrderUnitprice(orderUnitprice.getUnitprice());
		}

		// 単価決定単位区分
		if (orderUnitprice != null) {
			newPurSubBean.setUnitpriceDefineunit(new BigDecimal(orderUnitprice
					.getUnitpriceDivision()));
		}

		// 金額設定
		BigDecimal orderAmount = calcOrderAmount(orderUnitprice,
			detailListBean, itemBean);
		newPurSubBean.setSupplierOrdAmount(orderAmount);

		// 部署コード
		newPurSubBean.setOrganizationCd(loginUserOrganizationCd);
		// 発注担当者コード
		newPurSubBean.setTantoCd(loginUserId);

		newPurSubBean.setMaterialDivision(new BigDecimal(1));
		newPurSubBean.setOrderDivision(new BigDecimal(5));
		newPurSubBean.setStatus(PurchaseStatus.NOT_ISSUE);
		newPurSubBean.setOrderSheeFlag(new BigDecimal(0));
		newPurSubBean.setGoodHousingSum(new BigDecimal(0));
		newPurSubBean.setReplyContentsDivision(new BigDecimal(0));
		newPurSubBean.setDeliveryComp(new BigDecimal(0));
		newPurSubBean.setDataType(new BigDecimal(0));
		newPurSubBean.setDataTotalDivision(new BigDecimal(0));
		newPurSubBean.setRowNo(new BigDecimal(0));
		newPurSubBean.setCancelRowNo(new BigDecimal(0));
		newPurSubBean.setHousingUnitprice(new BigDecimal(0));
		newPurSubBean.setStockingAmount(new BigDecimal(0));

		newPurSubBean.setTaxDivision(new BigDecimal(0));
		newPurSubBean.setTaxAmount(new BigDecimal(0));

		newPurSubBean.setPayableTargetDivision(new BigDecimal(0));
		newPurSubBean.setPaymentTargetDivision(new BigDecimal(0));
		newPurSubBean.setPayableUpdateDivision(new BigDecimal(0));
		newPurSubBean.setPaymentUpdateDivision(new BigDecimal(0));

		newPurSubBean.setLabelFlag(new BigDecimal(0));

		newPurSubBean.setTmpUnitpriceFlg(new BigDecimal(0));
		newPurSubBean.setInspectMethod(new BigDecimal(0));
		newPurSubBean.setMortgageDetailFlg(new BigDecimal(0));
		newPurSubBean.setInspreceiptWaitQuantity(new BigDecimal(0));
		newPurSubBean.setBadQuantity(new BigDecimal(0));
		newPurSubBean.setCostEntry(new BigDecimal(0));
		newPurSubBean.setAdvPurNoticeDecideDivision(new BigDecimal(0));
		newPurSubBean.setOrderMortgagedQuantity(new BigDecimal(0));
		newPurSubBean.setSumHousingConvertQuant(new BigDecimal(0));
		newPurSubBean.setInspectWaitConvertQuantity(new BigDecimal(0));
		newPurSubBean.setExtractionsQuantity(new BigDecimal(0));
		newPurSubBean.setDefectiveQuantity(new BigDecimal(0));
		newPurSubBean.setLaboratoryMethod(new BigDecimal(0));
		newPurSubBean.setProvisionDivision(new BigDecimal(0));
		newPurSubBean.setTaxRatio(new BigDecimal(0));
		newPurSubBean.setCheckQuantity(new BigDecimal(0));
		newPurSubBean.setSlipIssueDivision(new BigDecimal(0));
		newPurSubBean.setApprovalStatus(new BigDecimal(0));
	}

	/**
	 * 購買テーブル書き込み時の金額計算
	 */
	private BigDecimal calcOrderAmount(
			final OrderDetailUnitprice orderUnitprice,
			final OrderDetailList detailListBean, final Item itemBean)
			throws Exception {

		String unitPriceDivision = null;
		String unitOfOpeManage = null;
		if (orderUnitprice == null || detailListBean == null
				|| itemBean == null) {
			return new BigDecimal(0);
		}
		unitPriceDivision = orderUnitprice.getUnitpriceDivision();
		unitOfOpeManage = detailListBean.getUnitOfOperationManagement();
		if (unitOfOpeManage == null || unitPriceDivision == null) {
			return new BigDecimal(0);
		}

		BigDecimal qty = detailListBean.getOrderQty();
		BigDecimal tanka = orderUnitprice.getUnitprice();
		BigDecimal kgOffrac = itemBean.getKgOfFractionManagement();
		if (qty == null || tanka == null || kgOffrac == null) {
			return new BigDecimal(0);
		}

		BigDecimal orderAmount = null;

		// 運用管理単位がKg以外 かつ 単価決定単位区分が 個
		if (!unitOfOpeManage.equals("1") && unitPriceDivision.equals("1")) {
			orderAmount = tanka.multiply(qty);
			// 運用管理単位がKg以外 かつ 単価決定単位区分が Kg
		} else if (!unitOfOpeManage.equals("1")
				&& unitPriceDivision.equals("2")) {
			orderAmount = kgOffrac.multiply(tanka.multiply(qty));
			// 運用管理単位がKg かつ 単価決定単位区分が Kg
		} else if (unitOfOpeManage.equals("1") && unitPriceDivision.equals("2")) {
			orderAmount = tanka.multiply(qty);
			// 運用管理単位がKg かつ 単価決定単位区分が 個
		} else if (unitOfOpeManage.equals("1") && unitPriceDivision.equals("1")) {
			return new BigDecimal(0);
		}
		return orderAmount;
	}

	/**
	 * 受注検索（詳細用）
	 * @param orderCd 受注コード
	 * @throws NoDataException NoDataException
	 * @return OrderDetail
	 */
	public OrderDetailEntity getDetailEntity(final String orderCd)
			throws NoDataException {
		OrderDetailEntity bean = orderDetailEntityDao.getEntity(orderCd);
		return bean;
	}

	/**
	 * 受注品目リスト検索（詳細用）
	 * @param orderCd 受注コード
	 * @throws NoDataException NoDataException
	 * @return OrderDetail
	 */
	public List<OrderDetailList> getDetailList(final String orderCd)
			throws NoDataException {
		List<OrderDetailList> list = orderDetailListDao.getDetailList(orderCd);

		for (OrderDetailList bean : list) {
			bean.setCheckline(Boolean.FALSE);
		}

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 得意先リスト検索
	 * @param balanceCd 帳合コード
	 * @throws NoDataException NoDataException
	 * @return OrderDetailVenderList
	 */
	public List<OrderDetailVenderList> getVenderList(final String balanceCd)
			throws NoDataException {
		List<OrderDetailVenderList> list = orderDetailVenderListDao
				.getList(balanceCd);
		return list;
	}

	/**
	 * 取引先詳細検索
	 * @param venderCd 取引先コード
	 * @throws NoDataException NoDataException
	 * @return OrderDetailVenderDetail
	 */
	public OrderDetailVenderDetail getVenderDetail(final String venderCd)
			throws NoDataException {
		OrderDetailVenderDetail entity = orderDetailVenderDetailDao
				.getEntity(venderCd);

		if (entity == null) {
			throw new NoDataException();
		}
		return entity;
	}

	/**
	 * 品目在庫を取得する
	 * @param itemCd 品目コード
	 * @throws NoDataException NoDataException
	 * @return ItemInventory
	 */
	public ItemInventory getItemInventoryEntity(final String itemCd)
			throws NoDataException {
		ItemInventory entity = itemInventoryDao.getEntity(itemCd);

		if (entity == null) {
			throw new NoDataException();
		}
		return entity;
	}

	/**
	 * 品目在庫を取得する
	 * @param itemCd 品目コード
	 * @throws NoDataException NoDataException
	 * @return ItemInventory
	 */
	public BigDecimal getNoGoodQty(final String itemCd) throws NoDataException {
		OrderNoGoodQty bean = orderNoGoodQtyDao.getNoGoodQty(itemCd);

		if (bean != null) {
			return bean.getQty();
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 受注区分を全件取得する
	 * @return List<NamesList>
	 */
	public List<OrderNamesList> getAllLines() {
		List<OrderNamesList> list = orderNamesListDao.getList("ORDR");
		return list;
	}

	/**
	 * 受注区分コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> getCreateOrderDivisionCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 生産ライン検索
		List<OrderNamesList> lineList = getAllLines();

		for (OrderNamesList bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getNameCd());
			item.setLabales(bean.getName01());
			res.add(item);
		}
		return res;
	}

	/**
	 * 運送会社を全件取得する
	 * @return List<OrderCarryListForComboboxes>
	 */
	public List<OrderCarryListForComboboxes> getAllCarry() {
		List<OrderCarryListForComboboxes> list = orderCarryListForComboboxesDao
				.getListForComboboxes();
		return list;
	}

	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> getCreateCarryCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 運送会社全件検索
		List<OrderCarryListForComboboxes> lineList = getAllCarry();

		for (OrderCarryListForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();

			if (bean.getCarryName1() != null) {
				if (bean.getCarryName2() != null) {
					item.setLabales(bean.getCarryName1() + " "
							+ bean.getCarryName2());
					item.setValues(bean.getCarryCd());
				} else {
					item.setLabales(bean.getCarryName1());
					item.setValues(bean.getCarryCd());
				}
			}
			res.add(item);
		}
		return res;
	}

	/**
	 * 備考マスタ検索処理
	 *
	 * @param frm OrderDetailForm
	 * @return List<OrderRemarkList>備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<OrderRemarkList> getRemarkList(final OrderDetailForm frm)
			throws NoDataException {

		String venderCd = null;
		String deliveryCd = null;
		List<OrderDetailList> itemList = null;

		if (frm.getOrderDetailVenderList().size() != 0) {
			venderCd = frm.getOrderDetailVenderList().get(0).getVenderCd(); // 得意先list
		}
		deliveryCd = frm.getDeliveryCd(); // 納入先
		itemList = frm.getOrderDetailList(); // 品目リスト

		// 得意先と納入先に紐付く備考を検索。
		List<OrderRemarkList> remarkList = orderRemarkListDao
				.getRemarkListNoItem(venderCd, deliveryCd);

		for (OrderDetailList bean : itemList) {
			List<OrderRemarkList> list = orderRemarkListDao
					.getRemarkListWithItem(venderCd, deliveryCd, bean
							.getItemCd());

			remarkList.addAll(list);
		}

		if (remarkList.isEmpty()) {
			throw new NoDataException();
		}
		return remarkList;
	}

	/**
	 * 納入先のみで備考マスタ検索処理
	 *
	 * @param frm OrderDetailForm
	 * @return OrderRemarkList備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public OrderRemarkList getRemarkDeliveryOnly(final OrderDetailForm frm)
			throws NoDataException {

		OrderRemarkList remarkList = orderRemarkListDao
				.getRemarkDeliveryOnly(frm.getDeliveryCd());
		return remarkList;
	}

	/**
	 * ファイルアップロード
	 * @param frm 画面データ
	 * @return アップロードファイル名
	 */
	private String upload(final OrderDetailForm frm) {

		String outFile = null;

		Common bean = commonDao.getEntity("ORDER_PICTURE");
		if (bean == null) {
			return outFile;
		}

		// アップロード日時
		String uploadDatetime = AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyyMMddHHmmss");

		// アップロード先のディレクトリ
		String uploaddir = bean.getCommonValue();

		FormFile uploadFile = null;
		BufferedInputStream inBuffer = null;
		BufferedOutputStream outBuffer = null;

		if (frm.getOrderPicture() == null || frm.getOrderPicture().equals("")
				|| frm.getUploadFile() == null
				|| !frm.getUploadFile().getFileName().equals("")) {

			try {
				// ディレクトリが存在するか調べてなければ作成する
				File dir = new File(uploaddir);
				if (!dir.exists()) {
					if (!dir.mkdir()) {
						throw new RuntimeException(
								"Can't create directory. : [" + uploaddir + "]");
					}
				}

				// 以前アップロードしたファイルがある場合は削除しておく.上書
				if (frm.getOrderPicture() != null
						&& !frm.getOrderPicture().equals("")) {
					File oldFile = new File(uploaddir + frm.getOrderPicture());
					if (oldFile.exists()) {
						oldFile.delete();
					}
				}

				// アクセスメソッドを使用してFormFileオブジェクトの取得
				if (frm.getUploadFile() == null
						|| frm.getUploadFile().getFileName().equals("")
						|| frm.getUploadFile().getFileName() == null) {
					throw new IOException("ファイルがありません");
				}

				uploadFile = frm.getUploadFile();
				// getInputStreamメソッドを使用し、入力ストリームを取得
				// 入力ストリームをバッファリング
				inBuffer = new BufferedInputStream(uploadFile.getInputStream());
				// ファイルのアップロード先を指定して、出力ストリームを生成
				// 出力ストリームをバッファリング
				outFile = uploadDatetime + uploadFile.getFileName();
				outBuffer = new BufferedOutputStream(new FileOutputStream(
						uploaddir + outFile));

				// コピー
				IOUtils.copy(inBuffer, outBuffer);
			} catch (IOException e) {
				outFile = null;
			} finally {
				// クローズ
				IOUtils.closeQuietly(outBuffer);
				IOUtils.closeQuietly(inBuffer);

				// 一時領域のアップロードデータを削除
				if (null != uploadFile) {
					uploadFile.destroy();
				}
			}
		} else {
			outFile = frm.getOrderPicture();
		}

		return outFile;
	}

	/**
	 * ダウンロードパス取得
	 * @return String
	 */
	public String getDownloadPath() {

		Common bean = commonDao.getEntity("ORDER_PICTURE");

		if (bean == null) {
			return null;
		} else {
			return bean.getCommonValue();
		}
	}

	/**
	 * 登録時の品目マスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkItem(final OrderDetailForm frm,
			final ActionMessages errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		int index = 1;
		List<OrderDetailList> detailList = frm.getOrderDetailList();

		for (OrderDetailList bean : detailList) {
			// 品目マスタ検索
			Item itemBean = itemDao.getEntity(bean.getItemCd());
			if (itemBean == null) {
				// データが存在しない場合
				errors.add("", new ActionMessage("errors.nodata.master.row", rb
						.getString("item.order.item.cd"), Integer
						.toString(index)));
			} else {

				// 販売品区分が0:該当なしの場合エラーとする
				if (BigDecimal.ZERO.compareTo(itemBean.getArticleDivision()) == 0) {
					errors.add("", new ActionMessage(
							"errors.order.article.division.row", Integer
									.toString(index)));
				} else {

					ArticleAttributeQueue artBean = articleAttributeQueueDao
							.getEntity(itemBean.getItemCd(), itemBean
									.getVersion());

					if (artBean == null) {
						// データが存在しない場合
						errors.add("", new ActionMessage(
								"errors.nodata.master.row", rb
										.getString("item.order.item.cd"),
								Integer.toString(index)));
					} else {
						// 預り品品目の場合エラー
						if (artBean.getKeepDivision().equals(new BigDecimal(2))) {
							errors.add("", new ActionMessage(
									"errors.order.article.keep.division.row",
									Integer.toString(index)));

						}
					}

				}
			}
			index++;
		}
		return errors;
	}

	/**
	 * 品目が全て仕入直送品どうかをかチェックを行う.<br>
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkItemTypeDivision(final OrderDetailForm frm,
			final ActionMessages errors) {
		List<OrderDetailList> detailList = frm.getOrderDetailList();
		boolean checkFlag = false; // チェックOKはfalse

		if (frm.getOrderDivision().equals("3")) {
			// 品目が全て仕入直送品かをチェックする
			for (OrderDetailList bean : detailList) {
				// 品目マスタ検索
				Item itemBean = itemDao.getEntity(bean.getItemCd());
				if (itemBean != null) {
					// 4:仕入直送品でなければ
					if (!itemBean.getTypeDivision().equals(new BigDecimal("4"))) {
						checkFlag = true;
					}
				}
			}

			if (checkFlag) {
				// 仕入直送品以外の品目があった場合
				errors.add("", new ActionMessage(
						"errors.order.item.not.shiiretyokusou"));
			}
		} else {
			// 品目が全て仕入直送品以外かをチェックする
			for (OrderDetailList bean : detailList) {
				Item itemBean = itemDao.getEntity(bean.getItemCd());
				if (itemBean != null) {
					// 4:仕入直送品であれば
					if (itemBean.getTypeDivision().equals(new BigDecimal("4"))) {
						checkFlag = true;
					}
				}
			}

			if (checkFlag) {
				// 仕入直送品の品目があった場合
				errors.add("", new ActionMessage(
						"errors.order.item.shiiretyokusou"));
			}
		}

		return errors;
	}

	/**
	 * 登録時の販売条件マスタチェックを行う.<br>
	 * 販売条件マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkSalesTerms(final OrderDetailForm frm,
			final ActionMessages errors) {
		int index = 1;
		List<OrderDetailList> detailList = frm.getOrderDetailList();

		for (OrderDetailList bean : detailList) {
			String itemCd = bean.getItemCd();
			String balanceCd = frm.getBalanceCd();
			String deliveryCd = frm.getDeliveryCd();

			// 販売条件マスタ検索
			OrderDetailSalesTerms salesTermsBean = orderDetailSalesTermsDao
					.getEntity(deliveryCd, balanceCd, itemCd);

			if (salesTermsBean == null) {
				// データが存在しない場合
				errors.add("", new ActionMessage(
						"errors.order.nodata.sales.terms.row", Integer
								.toString(index)));
			}
			index++;
		}
		return errors;
	}

	/**
	 * 登録時の帳合コードマスタチェックを行う.<br>
	 * 帳合マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkBalance(final OrderDetailForm frm,
			final ActionMessages errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		Balance balanceBean = balanceDao.getEntity(frm.getBalanceCd());
		if (balanceBean == null) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.nodata.master", rb
					.getString("item.order.balance.cd")));
		}

		return errors;
	}

	/**
	 * 担当者コードから担当者名称を取得する
	 * @param tantoCd tantoCd
	 * @return tantoName 担当者名称
	 */
	public String getLoginName(final String tantoCd) {

		// 担当者コードが無い場合処理しない
		if (tantoCd == null || tantoCd.equals("")) {
			return null;
		}

		Login bean = loginDao.getEntity(tantoCd);

		if (bean != null) {
			return bean.getTantoNm();
		} else {
			return null;
		}

	}

	/**
	 * 運送会社コードマスタチェックを行う.<br>
	 * 運送会社マスタにデータがない場合はエラーとする。
	 * @param carryCd 運送会社コード
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkCarry(final String carryCd,
			final ActionMessages errors) {
		Carry carryBean = carryDao.getEntity(carryCd);
		if (carryBean == null || carryCd == null) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.order.carry.not.exist"));
		}

		return errors;
	}

	/**
	 * 登録時の取引先コードマスタチェックを行う.<br>
	 * 取引先マスタにデータがない場合はエラーとする。
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkVender(final OrderDetailForm frm,
			final ActionMessages errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		Vender venderBean = venderDao.getEntity(frm.getVenderCd(), "TS");
		if (venderBean == null) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.nodata.master", rb
					.getString("item.order.vender.cd")));
		}

		return errors;
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
	 * 休日を考慮した出荷予定日などの日付を計算
	 * @param strDate     日付(文字列)
	 * @param leadTime    リードタイム
	 * @param calCd       カレンダーコード
	 * @return ProCalcDateFromCalendarCallDto 処理結果
	 */
	public ProCalcDateFromCalendarCallDto callProCalcDateFromCalendar(final String strDate, final BigDecimal leadTime, final String calCd) {
		ProCalcDateFromCalendarCallDto dto = new ProCalcDateFromCalendarCallDto();
		dto.setpStrDate(strDate);
		dto.setpLeadTime(leadTime);  //nullの時はプロシージャ内でデフォルト値にセットされる
		dto.setpStrCalCd(calCd);     //nullの時はプロシージャ内でデフォルト値にセットされる

		procedureCallDao.proCalcDateFromCalendar(dto);

		return dto;
	}

	/* -------------------- setter -------------------- */

	/**
	 * orderDetailEntityDaoを設定します。
	 * @param orderDetailEntityDao orderDetailEntityDao
	 */
	public void setOrderDetailEntityDao(
			final OrderDetailEntityDao orderDetailEntityDao) {
		this.orderDetailEntityDao = orderDetailEntityDao;
	}

	/**
	 * orderDetailListDaoを設定します。
	 * @param orderDetailListDao orderDetailListDao
	 */
	public void setOrderDetailListDao(
			final OrderDetailListDao orderDetailListDao) {
		this.orderDetailListDao = orderDetailListDao;
	}

	/**
	 * orderCarryListForComboboxesDaoを設定します。
	 * @param orderCarryListForComboboxesDao orderCarryListForComboboxesDao
	 */
	public void setOrderCarryListForComboboxesDao(
			final OrderCarryListForComboboxesDao orderCarryListForComboboxesDao) {
		this.orderCarryListForComboboxesDao = orderCarryListForComboboxesDao;
	}

	/**
	 * orderDetailVenderListDaoを設定します。
	 * @param orderDetailVenderListDao orderDetailVenderListDao
	 */
	public void setOrderDetailVenderListDao(
			final OrderDetailVenderListDao orderDetailVenderListDao) {
		this.orderDetailVenderListDao = orderDetailVenderListDao;
	}

	/**
	 * orderDetailVenderDetailDaoを設定します。
	 * @param orderDetailVenderDetailDao orderDetailVenderDetailDao
	 */
	public void setOrderDetailVenderDetailDao(
			final OrderDetailVenderDetailDao orderDetailVenderDetailDao) {
		this.orderDetailVenderDetailDao = orderDetailVenderDetailDao;
	}

	/**
	 * orderRemarkListDaoを設定します。
	 * @param orderRemarkListDao orderRemarkListDao
	 */
	public void setOrderRemarkListDao(
			final OrderRemarkListDao orderRemarkListDao) {
		this.orderRemarkListDao = orderRemarkListDao;
	}

	/**
	 * itemInventoryDaoを設定します。
	 * @param itemInventoryDao itemInventoryDao
	 */
	public void setItemInventoryDao(final ItemInventoryDao itemInventoryDao) {
		this.itemInventoryDao = itemInventoryDao;
	}

	/**
	 * itemDaoを設定します。
	 * @param itemDao itemDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * balanceDaoを設定します。
	 * @param balanceDao balanceDao
	 */
	public void setBalanceDao(final BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	/**
	 * orderDetailSalesTermsDaoを設定します。
	 * @param orderDetailSalesTermsDao orderDetailSalesTermsDao
	 */
	public void setOrderDetailSalesTermsDao(
			final OrderDetailSalesTermsDao orderDetailSalesTermsDao) {
		this.orderDetailSalesTermsDao = orderDetailSalesTermsDao;
	}

	/**
	 * orderDetailDaoを設定します。
	 * @param orderDetailDao orderDetailDao
	 */
	public void setOrderDetailDao(final OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	/**
	 * orderHeadDaoを設定します。
	 * @param orderHeadDao orderHeadDao
	 */
	public void setOrderHeadDao(final OrderHeadDao orderHeadDao) {
		this.orderHeadDao = orderHeadDao;
	}

	/**
	 * commonDaoを設定します。
	 * @param commonDao commonDao
	 */
	public void setCommonDao(final CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	/**
	 * 発番処理 ロジッククラスを設定します。
	 * @param getNumberLogic 発番処理ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * aspProductionDaoを設定します。
	 * @param aspProductionDao aspProductionDao
	 */
	public void setAspProductionDao(final AspProductionDao aspProductionDao) {
		this.aspProductionDao = aspProductionDao;
	}

	/**
	 * orderDetailAspProductionDaoを設定します。
	 * @param orderDetailAspProductionDao orderDetailAspProductionDao
	 */
	public void setOrderDetailAspProductionDao(
			final OrderDetailAspProductionDao orderDetailAspProductionDao) {
		this.orderDetailAspProductionDao = orderDetailAspProductionDao;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * purchaseAttributeQueueDaoを設定します。
	 * @param purchaseAttributeQueueDao purchaseAttributeQueueDao
	 */
	public void setPurchaseAttributeQueueDao(
			final PurchaseAttributeQueueDao purchaseAttributeQueueDao) {
		this.purchaseAttributeQueueDao = purchaseAttributeQueueDao;
	}

	/**
	 * purchaseSubcontractDaoを設定します。
	 * @param purchaseSubcontractDao purchaseSubcontractDao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * orderDetailUnitpriceDaoを設定します。
	 * @param orderDetailUnitpriceDao orderDetailUnitpriceDao
	 */
	public void setOrderDetailUnitpriceDao(
			final OrderDetailUnitpriceDao orderDetailUnitpriceDao) {
		this.orderDetailUnitpriceDao = orderDetailUnitpriceDao;
	}

	/**
	 * orderDetailPurchaseSubcontractDaoを設定します。
	 * @param orderDetailPurchaseSubcontractDao
	 *            orderDetailPurchaseSubcontractDao
	 */
	public void setOrderDetailPurchaseSubcontractDao(
			final OrderDetailPurchaseSubcontractDao orderDetailPurchaseSubcontractDao) {
		this.orderDetailPurchaseSubcontractDao = orderDetailPurchaseSubcontractDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * エラーログ出力用daoを設定します。
	 * @param errorLogDao エラーログ出力用dao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * 運送会社マスタdaoを設定します。
	 * @param carryDao エラーログ出力用dao
	 */
	public void setCarryDao(final CarryDao carryDao) {
		this.carryDao = carryDao;
	}

	/**
	 * 販売品属性用daoを設定します。
	 * @param articleAttributeQueueDao 販売品属性用dao
	 */
	public void setArticleAttributeQueueDao(
			final ArticleAttributeQueueDao articleAttributeQueueDao) {
		this.articleAttributeQueueDao = articleAttributeQueueDao;
	}

	/**
	 * 返品在庫数取得用daoを設定します。
	 * @param orderNoGoodQtyDao 返品在庫数取得用dao
	 */
	public void setOrderNoGoodQtyDao(final OrderNoGoodQtyDao orderNoGoodQtyDao) {
		this.orderNoGoodQtyDao = orderNoGoodQtyDao;
	}

	/**
	 * orderNamesListDaoを設定します。
	 * @param orderNamesListDao orderNamesListDao
	 */
	public void setOrderNamesListDao(final OrderNamesListDao orderNamesListDao) {
		this.orderNamesListDao = orderNamesListDao;
	}

	/**
	 * loginDaoを設定します。
	 * @param loginDao loginDao
	 */
	public void setLoginDao(final LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}
}
