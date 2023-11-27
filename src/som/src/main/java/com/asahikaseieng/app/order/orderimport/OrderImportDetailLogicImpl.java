/*
 * Created on 2020/09/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.extension.jdbc.SqlLogRegistryLocator;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.checkdigit.NumberChkDigitCtl;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.app.order.OrderDetailForm;
import com.asahikaseieng.app.unitprice.GetValidUnitpriceLogic;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetail;
import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetailDao;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHeadDao;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventoryDao;
import com.asahikaseieng.dao.entity.master.companysetting.CompanySetting;
import com.asahikaseieng.dao.entity.master.companysetting.CompanySettingDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.orderdetail.OrderDetail;
import com.asahikaseieng.dao.entity.orderdetail.OrderDetailDao;
import com.asahikaseieng.dao.entity.orderimportfilelog.OrderImportFileLog;
import com.asahikaseieng.dao.entity.orderimportfilelog.OrderImportFileLogDao;
import com.asahikaseieng.dao.entity.temporarycarryfarecalc.TemporaryCarryFareCalc;
import com.asahikaseieng.dao.entity.temporarycarryfarecalc.TemporaryCarryFareCalcDao;
import com.asahikaseieng.dao.nonentity.comboboxes.order.OrderCarryListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.order.OrderCarryListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.vendergroup.VenderGroupForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.vendergroup.VenderGroupForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.namesnolist.NamesNoListDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitprice;
import com.asahikaseieng.dao.nonentity.orderdetailvenderdetail.OrderDetailVenderDetail;
import com.asahikaseieng.dao.nonentity.orderdetailvenderdetail.OrderDetailVenderDetailDao;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderList;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderListDao;
import com.asahikaseieng.dao.nonentity.orderimportdataentity.OrderImportDataEntity;
import com.asahikaseieng.dao.nonentity.orderimportdataentity.OrderImportDataEntityDao;
import com.asahikaseieng.dao.nonentity.orderimportdetailentity.OrderImportDetailHeaderEntity;
import com.asahikaseieng.dao.nonentity.orderimportdetailentity.OrderImportDetailEntityDao;
import com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailList;
import com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailListDao;
import com.asahikaseieng.dao.nonentity.orderimportsamelist.OrderImportSameList;
import com.asahikaseieng.dao.nonentity.orderimportsamelist.OrderImportSameListDao;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesList;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesListDao;
import com.asahikaseieng.dao.nonentity.ordernogoodqty.OrderNoGoodQty;
import com.asahikaseieng.dao.nonentity.ordernogoodqty.OrderNoGoodQtyDao;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.FunGetCarryFareCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCalcDateFromCalendarCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 受注取込詳細ロジック 実装クラス.
 * @author
 */
public class OrderImportDetailLogicImpl implements OrderImportDetailLogic {

	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	private GetValidUnitpriceLogic getValidUnitpriceLogic;

	private OrderImportDetailEntityDao orderImportDetailEntityDao;

	private OrderImportDetailListDao orderImportDetailListDao;

	private OrderCarryListForComboboxesDao orderCarryListForComboboxesDao;

	private OrderDetailVenderListDao orderDetailVenderListDao;

	private OrderDetailVenderDetailDao orderDetailVenderDetailDao;

	private ItemDao itemDao;


	private LoginDao loginDao;


	private OrderDetailDao orderDetailDao;

	private OrderImportSameListDao orderImportSameListDao;


	private FrstOrderHeadDao frstOrderHeadDao;

	private FrstOrderDetailDao frstOrderDetailDao;

	private OrderImportDataEntityDao orderImportDataEntityDao;

	private ItemInventoryDao itemInventoryDao;

	private OrderNoGoodQtyDao orderNoGoodQtyDao;

	private OrderRemarkListDao orderRemarkListDao;

	private GetNumberLogic getNumberLogic;

	private OrderNamesListDao orderNamesListDao;

	private NamesNoListDao namesNoListDao;

	private TemporaryCarryFareCalcDao temporaryCarryFareCalcDao;


	private OrderImportFileLogDao orderImportFileLogDao;

	private CompanySettingDao companySettingDao;

	private ProcedureCallDao procedureCallDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	/** 得意先グループコンボボックス用DAO */
	private VenderGroupForComboboxesDao venderGroupForComboboxesDao;

	private OrderImportCommonLogic orderImportCommonLogic;
	

	//処理状態保持　後で移動
	private boolean publicCheckEndFlg = false;
	private boolean publicCheckApprovalFlg = false;
	
	/**
	 * コンストラクタ.
	 */
	public OrderImportDetailLogicImpl() {
	}

	/**
	 * 受注取込のヘッダと詳細データを取得する
	 * 
	 * @param frm 画面データ
	 * @param languageId 多言語文字
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void getOrderImportData(final OrderImportDetailForm frm,
			final String tantoCd , NumberChkDigitCtl numberChkDigitCtl) throws NoDataException, IllegalAccessException, InvocationTargetException {
		// 受注取込ヘッダデータを取得する
		OrderImportDetailHeaderEntity bean = null;
		
		// 競合による再取得
		if(AecStringUtils.isNotNullAndEmpty(frm.getConflictFrstOrderNo())){
			bean = orderImportDetailEntityDao
					.getHeaderEntity(frm.getConflictFrstOrderNo());
		}else{
			bean = orderImportDetailEntityDao
			.getHeaderEntity(frm.getFrstOrderNo());
		}
		

		OrderImportDataEntity importDataEntity = this.orderImportDataEntityDao
				.getImportDataEntity(frm.getFrstOrderNo());

		// 受注取込データをフォームにコピー ignoreはわけがわからなくなるので個別に記載したほうが後々の生産性は上がる
		// 並びはできる限りテーブル順
		frm.setOrderNo(bean.getOrderNo()); // 受注番号
		frm.setOrderDivision(AecNumberUtils
				.convertStringNullToOneFromBigDecimal(bean.getOrderDivision())); // 受注区分
		frm.setFrstOrderNo(bean.getFrstOrderNo()); // 受注グループ番号
		if (bean.getVenderGroupCd() != null) {
			frm.setVenderGroupCd(bean.getVenderGroupCd()); // 得意先グループ
		} else {
			frm.setVenderGroupCd("99999"); // nullのときは「設定なし」がコンボボックスに表示されるように、99999を設定
		}
		
		// 受注ヘッダ情報の取得
		FrstOrderHead frstHeadBean = this.frstOrderHeadDao
				.getEntity(bean.getFrstOrderNo());
		frm.setFrstOrderHeadBean(frstHeadBean);
		
		frm.setImportStatus(bean.getImportStatus());
		
		frm.setOrderDate(AecDateUtils.formatStringFromTimestamp(
			bean.getOrderDate(), "yyyy/MM/dd")); // 受注日
		frm.setCtmOrderDate(importDataEntity.getCtmOrderDate()); // 客先注文日
		frm.setImportDate(importDataEntity.getImportDate()); // データ取込日
		frm.setDeliveryCd(bean.getDeliveryCd()); // 納入先
		frm.setDeliveryName(bean.getDeliveryName()); // 納入先名
		frm.setCtmDeliveryCd(importDataEntity.getCtmDeliveryCd()); // 客先納入先
		frm.setCtmDeliveryName(importDataEntity.getCtmDeliveryName()); // 客先納入先名
		frm.setAddress(bean.getAddress()); // 納入先住所
		frm.setCtmAddress(importDataEntity.getCtmAddress()); // 客先納入先住所
		frm.setDeliveryAddress(bean.getDeliveryAddress()); // 納入先宛先
		frm.setCtmDeliveryAddress(importDataEntity.getCtmDeliveryAddress()); // 客先納入先宛先
		frm.setDeliveryTelNo(importDataEntity.getCtmDeliveryTelNo()); // 納入先電話
		frm.setCtmDeliveryTelNo(importDataEntity.getCtmDeliveryTelNo()); // 客先納入先電話
		frm.setOrderDate(AecDateUtils.formatStringFromTimestamp(
			bean.getOrderDate(), "yyyy/MM/dd")); // 出荷予定日
		frm.setLeadTime(AecNumberUtils.convertString(bean.getLeadTime())); // リードタイム
		frm.setCarryCd(bean.getCarryCd()); // 運送会社
		frm.setDeliveryExpectedDate(AecDateUtils.formatStringFromTimestamp(
			bean.getDeliveryExpectedDate(), "yyyy/MM/dd")); // 納入予定日
		frm.setDeliveryExpectedTime(bean.getDeliveryExpectedTime()); // 納入時刻
		frm.setSuggestedDeliverlimit(AecDateUtils.formatStringFromTimestamp(
			bean.getSuggestedDeliverlimit(), "yyyy/MM/dd")); // 客先希望納期
		frm.setScheduledShippingDate(AecDateUtils.formatStringFromTimestamp(
			bean.getScheduledShippingDate(), "yyyy/MM/dd")); // 出荷予定日
		frm.setTotalOrderAmount(AecNumberUtils
				.convertStringNullToZeroFromBigDecimal(bean.getOrderAmount())); // 合計金額
		frm.setBalanceCd(bean.getBalanceCd()); // 帳合コード
		frm.setOrganizationCd(bean.getOrganizationCd()); // 担当部署
		frm.setOrganizationName(bean.getOrganizationName()); // 担当部署名
		frm.setCtmVenderName(importDataEntity.getCtmVenderName()); // 客先得意先
		frm.setCarryFare(AecNumberUtils.convertString(bean.getCarryFare())); // 運賃
		frm.setCalcCarryFare(AecNumberUtils.convertString(bean.getCarryFare())); // 自動計算運賃
		frm.setCarryInvoiceFlag(bean.getCarryInvoiceFlag()); // 運賃請求フラグ
		frm.setPrintSummery(bean.getPrintSummery()); // 備考(印字用)
		frm.setDeliverySlipSummery(bean.getDeliverySlipSummery()); // 自動表示備考
		frm.setOrderPicture(bean.getOrderPicture()); // 印字文字数チェック用
		frm.setOrderSummery(bean.getOrderSummery()); // 参照
		// 20210906 Asclab Saita 納期連絡表専用備考追加対応
		frm.setDeliverydateContactSummery(bean.getDeliverydateContactSummery()); // 納期連絡表専用備考
		frm.setSalesTantoCd(bean.getSalesTantoCd()); // 営業担当者名
		//20210526 add S.Fujimaki担当者名表示
		frm.setSalesTantoName(loginDao.getEntity(bean.getSalesTantoCd()).getTantoNm()); // 営業担当者名
		//20210526 add S.Fujimaki担当者名表示
		frm.setVenderCd(bean.getVenderCd()); // 得意先コード
		frm.setHeadCtmOrderNo(bean.getCustomerOrderNo()); // 得意先コード
		frm.setDeliveryTelNo(bean.getDeliveryTelNo()); // 納入先電話

		
		// 画面を開いたときにDBから取得した帳合コードと得意先コードを保存
		frm.setDbBalanceCd(bean.getBalanceCd());
		frm.setDbVenderCd(bean.getVenderCd());

		// 受注番号登録済みなら確定
		if( AecStringUtils.isNotNullAndEmpty(bean.getOrderNo()) ){
			frm.setFixedFlg(1);
		}

		// 運賃請求フラグ
		// ファイル取込データでnullが来た時用の対策null除け
		if (bean.getCarryInvoiceFlag() != null) {
			// 1:true その他:false
			if (bean.getCarryInvoiceFlag().equals(BigDecimal.ONE)) {
				frm.setBlnCarryInvoiceFlag(Boolean.TRUE);
			} else {
				frm.setBlnCarryInvoiceFlag(Boolean.FALSE);
			}
		}

		// 数値桁数設定
		frm.setTotalOrderAmount(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.URKINGAKU,
			Constants.VENDER_DIVISION_TS, frm.getVenderCd()), AecNumberUtils
					.convertBigDecimalNullToZeroFromString(frm
							.getTotalOrderAmount()))); // 合計金額
		if (frm.getCarryFare() != null) {
			frm.setCarryFare(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.URKINGAKU,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd()),
				AecNumberUtils.convertBigDecimal(frm.getCarryFare()))); // 運賃
		}

		// 合計金額のJSPで行う数値桁数の設定を標準機能から取得する(同じ値)
		NumberChkDisitDetail checkData = checker.getCheckDigit(
			Constants.URKINGAKU, Constants.VENDER_DIVISION_TS,
			frm.getVenderCd());

		frm.setSmallnumLengthUrKingaku(AecNumberUtils
				.convertStringNullToZeroFromBigDecimal(checkData
						.getSmallnumLength())); // 小数点桁数(合計金額)
		frm.setRoundDivisionUrKingaku(AecNumberUtils
				.convertStringNullToZeroFromBigDecimal(checkData
						.getRoundDivision())); // 端数区分(合計金額)

		// 個数のJSPで行う数値桁数の設定を標準機能から取得する
		checkData = checker.getCheckDigit(Constants.PIECE,
			Constants.VENDER_DIVISION_TS, frm.getVenderCd());
		frm.setSmallnumLengthTotalNum(AecNumberUtils
				.convertStringNullToZeroFromBigDecimal(checkData
						.getSmallnumLength())); // 小数点桁数(合計個数)
		frm.setRoundDivisionTotalNum(AecNumberUtils
				.convertStringNullToZeroFromBigDecimal(checkData
						.getRoundDivision())); // 端数区分(合計個数)

		// 重量のJSPで行う数値桁数の設定を標準機能から取得する
		checkData = checker.getCheckDigit(Constants.KG,
			Constants.VENDER_DIVISION_TS, frm.getVenderCd());
		frm.setSmallnumLengthTotalWeight(AecNumberUtils
				.convertStringNullToZeroFromBigDecimal(checkData
						.getSmallnumLength())); // 小数点桁数(合計重量)
		frm.setRoundDivisionTotalWeight(AecNumberUtils
				.convertStringNullToZeroFromBigDecimal(checkData
						.getRoundDivision())); // 端数区分(合計重量)

		// 受注詳細データを取得する
		frm.setOrderImportDetailList(orderImportDetailListDao.getList(frm
				.getFrstOrderNo()));
		frm.setOrderImportDetailListCount(frm.getOrderImportDetailList().size());

		
		// 合計個数・重量計算用
		BigDecimal totalQty = BigDecimal.ZERO;
		BigDecimal totalWeight = BigDecimal.ZERO;

		String ctmOrderNo = "";

		for (OrderImportDetailList detailBean : frm.getOrderImportDetailList()) {
			// 数値桁数チェックを実施
			detailBean.setOrderQty(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,detailBean
					.getUnitOfOperationManagement(),
				Constants.VENDER_DIVISION_TS, frm.getVenderCd()), AecNumberUtils
						.convertBigDecimalNullToZeroFromString(detailBean
								.getOrderQty()))); // 受注数量
			detailBean.setCtmOrderQty(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,detailBean
					.getUnitOfOperationManagement(),
				Constants.VENDER_DIVISION_TS, frm.getVenderCd()), AecNumberUtils
						.convertBigDecimalFromString(detailBean
								.getCtmOrderQty()))); // 客先数量

			// 受注数量のJSPで行う数値桁数の設定を標準機能から取得する
			checkData = checker.getCheckDigit(
				detailBean.getUnitOfOperationManagement(), null, null);

			detailBean.setSmallnumLengthNum(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData
							.getSmallnumLength())); // 小数点位置
			detailBean.setRoundDivisionNum(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData
							.getRoundDivision())); // 丸め区分

			detailBean.setStandardUnitprice(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.TANKA,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd()), AecNumberUtils
						.convertBigDecimalFromString(detailBean
								.getStandardUnitprice()))); // AP標準単価
			detailBean.setStandardDiscount(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.TANKA,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd()), AecNumberUtils
						.convertBigDecimalFromString(detailBean
								.getStandardDiscount()))); // AP標準値引
			detailBean.setSpecialDiscount(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.TANKA,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd()), AecNumberUtils
						.convertBigDecimalFromString(detailBean
								.getSpecialDiscount()))); // AP特別値引
			detailBean.setCtmOrderUnitprice(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.TANKA,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd()), AecNumberUtils
						.convertBigDecimalFromString(detailBean
								.getCtmOrderUnitprice()))); // 客先単価

			// 標準単価と標準値引のJSPで行う数値桁数の設定を標準機能から取得する(同じ値)
			checkData = checker.getCheckDigit(Constants.TANKA,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd());

			detailBean.setSmallnumLengthUnitprice(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData
							.getSmallnumLength())); // 小数点桁数(単価)
			detailBean.setRoundDivisionUnitprice(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData
							.getRoundDivision())); // 端数区分(単価)

			detailBean.setOrderAmount(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.URKINGAKU,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd()), AecNumberUtils
						.convertBigDecimalFromString(detailBean
								.getOrderAmount()))); // 金額

			checkData = checker.getCheckDigit(Constants.URKINGAKU,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd());

			detailBean.setSmallnumLengthOrderAmount(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData
							.getSmallnumLength())); // 小数点桁数(金額)
			detailBean.setRoundDivisionOrderAmount(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData
							.getRoundDivision())); // 端数区分(金額)

			// 重量のJSPで行う数値桁数の設定を標準機能から取得する
			checkData = checker.getCheckDigit(Constants.KG,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd());
			detailBean.setSmallnumLengthWeight(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData
							.getSmallnumLength())); // 小数点桁数(合計重量)
			detailBean.setRoundDivisionWeight(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData
							.getRoundDivision())); // 端数区分(合計重量)
			
			if( detailBean.getDelFlg().intValue() == 0 && detailBean.getCancelFlg().intValue() == 0 ){
				// 合計個数・重量計算
				// 個数
				totalQty = totalQty.add(new BigDecimal(detailBean.getQty()));
				// 重量
				totalWeight = totalWeight
						.add(new BigDecimal(detailBean.getWeight()));
			}
			

			// 客先備考取得
			frm.setCtmRemark(detailBean.getCtmRemark());
			detailBean.setValidLine(true);
			detailBean.setInsertFlg(0);

			/* 有効単価取得(設定金額超過時の警告表示用) */
			VaridUnitprice unitBean = getValidUnitpriceLogic.getValidUnitprice(
				bean.getBalanceCd(),
				detailBean.getItemCd(),
				AecDateUtils.formatStringFromTimestamp(
					bean.getScheduledShippingDate(), "yyyy/MM/dd"));
			// 有効単価が取得できたら受注単価をセット
			if (unitBean != null) {
				detailBean.setTmpOrderUnitprice(unitBean.getUnitprice());
			}

			// 客先注文番号
			// 空だったらそのまま客先注文番号をいれる
			if ( !AecStringUtils.isNotNullAndEmpty(ctmOrderNo) && detailBean.getCtmOrderNo() != null) {
				ctmOrderNo = detailBean.getCtmOrderNo();
			} else if (AecStringUtils.isNotNullAndEmpty(ctmOrderNo) 
					&& detailBean.getCtmOrderNo() != null) {
				// 空じゃなかったら確認後客先注文番号と区切りの/を後ろにつける
				ctmOrderNo = ctmOrderNo + "/" + detailBean.getCtmOrderNo();
			}

			// 数量と金額警告用に保持
			detailBean.setBeforeOrderQty(AecNumberUtils
					.convertBigDecimalNullToZeroFromString(detailBean
							.getOrderQty()));
			detailBean.setBeforeMatss(AecNumberUtils
					.convertBigDecimalNullToZeroFromString(detailBean
							.getMatss()));

			if( AecStringUtils.isNotNullAndEmpty( detailBean.getErrorMessage() )) {
				frm.setErrorStatus("RED"); // エラーメッセージがある場合、エラー状態を設定
			}
			

			// 出荷情報か購買情報が存在する場合、受注情報を変更させない。
			if( detailBean.getShippingStatus() != null || ( detailBean.getPurchaseStatus() != null && detailBean.getPurchaseStatus().intValue() != 7 ) ){
				frm.setImportStatus("90");
			}
			
		}

		// 合計個数・重量のセット
		frm.setTotalQty(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.PIECE,
			Constants.VENDER_DIVISION_TS, frm.getVenderCd()), totalQty)); // 合計個数
		frm.setTotalWeight(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.KG,
			Constants.VENDER_DIVISION_TS, frm.getVenderCd()), totalWeight)); // 合計重量

		// 受注データの登録時に比較するデータを保持する
		frm.setBeforeOrderImportDetailList(new ArrayList< OrderImportDetailList >(frm.getOrderImportDetailList()));
		frm.setBeforeOrderImportDetailListCount(frm
				.getBeforeOrderImportDetailList().size());
		//ArrayList<FrstOrderHead> frst = new ArrayList<FrstOrderHead>();
		FrstOrderHead frstn  = new FrstOrderHead();
		BeanUtils.copyProperties(frstn, frm.getFrstOrderHeadBean());
		//frst.add(frstn);
		frm.setBeforeFrstOrderHead(frstn);

		// 自社マスタから確定時に警告をだす数量と金額を取得
		CompanySetting comSetting = companySettingDao.getEntity("000001");

		// 数量と金額の警告を出す閾値を取得
		frm.setCheckOrderAmount(comSetting.getOrderAmountCheck());
		frm.setCheckQty(comSetting.getQtyCheck());

		// 受注グループ番号が記録されているときに同じグループの確定済受注の客先注文番号を取得
		String sameCtmOrderNo = "";
		if (frm.getFrstOrderNo() != null) {
			List<OrderImportSameList> sameList = orderImportSameListDao
					.getSameOrderImportList("NOTNULL", frm.getFrstOrderNo());
			for (OrderImportSameList sameBean : sameList) {
				// 客先注文番号
				// 空だったらそのまま客先注文番号をいれる
				if (!AecStringUtils.isNotNullAndEmpty(sameCtmOrderNo)
						&& sameBean.getCustomerOrderDetailNo() != null) {
					sameCtmOrderNo = sameBean.getCustomerOrderDetailNo();
				} else if (AecStringUtils.isNotNullAndEmpty(sameCtmOrderNo)
						&& sameBean.getCustomerOrderDetailNo() != null) {
					// 空じゃなかったら客先注文番号と区切りの/を後ろにつける
					sameCtmOrderNo = sameCtmOrderNo + "/"
							+ sameBean.getCustomerOrderDetailNo();
				}
			}
		}
		frm.setSameCtmOrderNo(sameCtmOrderNo);

		if (frm.getCalcCarryFare() != null) {
			// 運賃計算
			BigDecimal calcCarryFare = this.calcCarryFare( frm.getDeliveryCd(), frm, null , tantoCd);
			frm.setCalcCarryFare(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.URKINGAKU,
				Constants.VENDER_DIVISION_TS, frm.getVenderCd()),  calcCarryFare )); // 自動計算運賃
		}

	}
	
	public List<OrderImportDetailList> getOrderImportDetailList( String frstOrderNo ){
		return orderImportDetailListDao.getList(frstOrderNo);
	}

	/**
	 * 登録更新処理を行う
	 * @param frm 更新対象画面フォーム
	 * @param tantoCd　ログインユーザ
	 * @throws Exception 例外
	 */
	public ActionMessages regist(final OrderImportDetailForm frm,
			final String tantoCd , final boolean isFrstData , final FrstOrderHead frstHeadBean) throws Exception {
		ActionMessages errorMsg = new ActionMessages();

		List<OrderImportDetailList> updateList = new ArrayList<OrderImportDetailList>();

		// 更新対象リスト取得
		updateList = frm.getOrderImportDetailList();

		// 詳細画面に遷移してきたときの登録フラグを保管
		int tempInsertFlg = frm.getInsertFlg();

		List<OrderImportSameList> sameList = new ArrayList<OrderImportSameList>();

		// 新規登録時の運賃計算用
		String frstOrderNo = "";

		// 運賃計算
		BigDecimal calcCarryFare = calcCarryFare(frm.getDeliveryCd(), frm,
			sameList, tantoCd);

		// ****************************************
		// 登録処理
		// ****************************************
		Boolean allCancelFlg = true;
		for (OrderImportDetailList formData : updateList) {
			if( formData.getDelFlg().intValue() != 1 && formData.getCancelFlg().intValue() == 0 ){
				allCancelFlg = false;
			}
		}
		
		
		// ヘッダ情報の整備
		FrstOrderHead updateHeadBean = new FrstOrderHead();
		Timestamp updateDate = null;
		//      20210924 承認チェック処理に移動
		//boolean delDateChanged = false;
		
		if (updateList.size() > 0
				&& AecStringUtils.isNotNullAndEmpty(updateList.get(0)
						.getFrstOrderNo())) {
			frstOrderNo = frm.getFrstOrderNo();
			updateHeadBean = frm.getFrstOrderHeadBean();
			updateDate = updateHeadBean.getUpdateDate();
			frstOrderNo = updateList.get(0).getFrstOrderNo();

//          20210924 承認チェック処理に移動
//			if( !frm.getDeliveryExpectedDate().equals( new SimpleDateFormat("yyyy/MM/dd").format( updateHeadBean.getDeliveryExpectedDate() ) ) ){
//				delDateChanged = true;
//			}
			
			// 登録フラグを0:更新にセット
			frm.setInsertFlg(0);
		} else {
			updateHeadBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日
			updateHeadBean.setInputorCd(tantoCd); // 登録者
			updateDate = AecDateUtils.getCurrentTimestamp();
			// 登録フラグを1:新規にセット
			frm.setInsertFlg(1);
		}

		// ヘッダ情報の作成
		createFrstOrderHeader(frm, tantoCd, calcCarryFare, updateHeadBean,
			frstOrderNo , updateDate);

		// 重複した受注情報を削除
		deleteConflictOrder(frm, tempInsertFlg);

		
		if(isFrstData)
		{
			// ヘッダの登録先の確認
			OrderImportDetailList concliftOrder =  orderImportDetailListDao.getConcliftFrstOrderNo(updateHeadBean.getOrderDivision()
				 , updateHeadBean.getVenderCd()
				 , updateHeadBean.getDeliveryCd()
				 , updateHeadBean.getBalanceCd()
				 , updateHeadBean.getScheduledShippingDate()
				 , updateHeadBean.getDeliveryExpectedDate()
				 , updateHeadBean.getCarryCd()
				 , updateHeadBean.getFrstOrderNo()
				 , updateHeadBean.getDeliveryAddress()
				 , updateHeadBean.getDeliveryTelNo());
			
			// 重複するヘッダがあった場合、再読み込み
			if( concliftOrder != null ){
				
				// 現在表示中のデータリストを再表示用に保持
				frm.setConflictOrderImportDetailList(frm.getOrderImportDetailList());
				
				// 重複したデータのヘッダリストを設定
				List<FrstOrderHead> conflistHeadList = frm.getConflictOrderHeadList();
				if(conflistHeadList == null){
					conflistHeadList = new ArrayList<FrstOrderHead>();
				}
				conflistHeadList.add(updateHeadBean);
				frm.setConflictOrderHeadList( conflistHeadList );
	
				errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.orderimport.order.conflict"));
				frm.setConflictFrstOrderNo(concliftOrder.getFrstOrderNo());
				frm.setReloadFlg(1);
				return errorMsg;
			}else{
				frm.setConflictFrstOrderNo("");
				frm.setConflictOrderHeadList(null);
				frm.setConflictOrderImportDetailList(null);
			}
		}else{
			frm.setConflictFrstOrderNo("");
			frm.setConflictOrderHeadList(null);
			frm.setConflictOrderImportDetailList(null);
		}
		
		if( allCancelFlg ){
			updateHeadBean.setCancelFlg(BigDecimal.ONE);
		}else{
			updateHeadBean.setCancelFlg(BigDecimal.ZERO);
		}
		
		// 追加処理
		if (frm.getInsertFlg() == 1) {
			// 新規の場合、
			frstOrderNo = getNumberLogic.getFrstOrderNo();
			updateHeadBean.setFrstOrderNo(frstOrderNo);

			frstOrderHeadDao.insert(updateHeadBean);
		} else {
			// 更新の場合、
			updateHeadBean.updateDate = frstHeadBean.updateDate;
			frstOrderHeadDao.update(updateHeadBean);
		}
		
		int rowNum = 0;
		
		for (OrderImportDetailList formData : updateList) {

			// 新規登録時のみ品目コードが空でもスキップ
			if (formData.getItemCd().isEmpty() && frm.getInsertFlg() == 1) {
				continue;
			}
			rowNum += 1;
			
			FrstOrderDetail updateBean = new FrstOrderDetail();

			// ***********************************
			// 更新時、受注取込データを検索
			// ***********************************
			if ( formData.getInsertFlg() == 0 ) {
				// 取込番号のデータを取得する
				updateBean = frstOrderDetailDao.getEntity(
					formData.getFrstOrderNo(), formData.getFrstOrderRowNo());

				// 更新のため、更新者情報のみを登録する
				for (OrderImportDetailList detailBean : frm
						.getBeforeOrderImportDetailList()) {
					// 画面を開いたときの情報からPK_NOとPK_ROWが同じ情報を取得
					if (StringUtils.defaultString(detailBean.getFrstOrderNo())
							.equals(updateBean.getFrstOrderNo())
							&& detailBean.getFrstOrderRowNo().compareTo(
								updateBean.getFrstOrderRowNo()) == 0) {
						updateBean.setUpdateDate(detailBean.getUpdateDate()); // 更新日は画面を開いた時に取得した更新日付を設定しこれで排他制御を行う
//20210922 承認ステータスの改定	
						// 品目、数量、増し付け数が変わった場合、未承認に戻す。
						/*if( ( !updateBean.getItemCd().equals( detailBean.getItemCd() )
								|| !updateBean.getOrderQty().toString().equals(detailBean.getOrderQty())
								|| !updateBean.getMatss().toString().equals(detailBean.getMatss()) 
								|| updateBean.getCancelFlg().intValue() != detailBean.getCancelFlg().intValue()
								|| delDateChanged)
						){
							updateBean.setInputApprovalDate(null);
							updateBean.setInputApproverCd(null);
							updateBean.setDelDateSendDate(null);
							updateBean.setDelDateSenderCd(null);
						}*/
						
						break;
					}
				}

				updateBean.setViewSeq(formData.getViewSeq()); // 表示順序
				updateBean.setUpdatorCd(tantoCd); // 更新者

				// 登録フラグを0:更新にセット
				frm.setInsertFlg(0);
			} else { // 新規登録時(行追加された行の場合もこちらへ分岐)
				// 取込番号が無い場合、初回ループ時のみ取込番号を取得する　以降のループにも同じ取込番号セット
				if(frm.getInsertFlg() == 1 ){
					updateBean.setFrstOrderRowNo(BigDecimal.valueOf(rowNum)); // 取込行
					updateBean.setViewSeq(BigDecimal.valueOf(rowNum)); // 取込行
				}else{
					updateBean.setFrstOrderRowNo(formData.getFrstOrderRowNo()); // 取込行
					updateBean.setViewSeq(formData.getViewSeq()); // 表示順序
				}

				frm.setFrstOrderNo(frstOrderNo);
				
				updateBean.setFrstOrderNo(frstOrderNo); // 取込番号
				updateBean.setInputDivision(new BigDecimal(2)); // データ発生区分:	// 2:画面から新規追加
				updateBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日
				updateBean.setInputorCd(tantoCd); // 登録者

				// 登録フラグを1:登録にセット
				frm.setInsertFlg(1);
			}
//20210924 add 
			//承認チェックを行う。
			if (formData.getInsertFlg() != 1) {
				checkApprovalUpdateFrstOrderDetail(frm,updateBean);
			}
//20210924 end
			
			// 画面のデータを設定する
			createFrstOrderDetail(formData, updateBean,tantoCd,isFrstData);

			try {
				// 追加処理
				if (formData.getInsertFlg() == 1) {
					// 新規の場合、
					frstOrderDetailDao.insert(updateBean);
				} else {
					// 更新の場合、
					frstOrderDetailDao.update(updateBean);
				}

			} catch (NotSingleRowUpdatedRuntimeException e) {
				System.out.println(SqlLogRegistryLocator.getInstance().getLast().getCompleteSql());
				// 排他エラー
				e.printStackTrace();
				// 登録終了後の画面の遷移に影響するため、登録フラグを元に戻す
				frm.setInsertFlg(tempInsertFlg);
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				e.printStackTrace();
				// 登録終了後の画面の遷移に影響するため、登録フラグを元に戻す
				frm.setInsertFlg(tempInsertFlg);
				throw new DuplicateRuntimeException(0);
			}
		}

		publicCheckEndFlg=false;
		publicCheckApprovalFlg=false;
		// 登録終了後の画面の遷移に影響するため、登録フラグを元に戻す
		frm.setInsertFlg(tempInsertFlg);

		// 受注データの登録時に比較するデータを更新する
		frm.setOrderImportDetailList(orderImportDetailListDao.getList(frm
				.getFrstOrderNo()));
		frm.setOrderImportDetailListCount(frm.getOrderImportDetailList().size());
		frm.setBeforeOrderImportDetailList( new ArrayList<OrderImportDetailList>( frm.getOrderImportDetailList()));
		

		// 受注ヘッダ情報の取得
		frm.setFrstOrderHeadBean(this.frstOrderHeadDao
			.getEntity(frm.getFrstOrderNo()));
		
		
		frm.setConflictOrderImportDetailList(null);
		return errorMsg;

	}

	/**
	 * 重複した再表示データを削除扱いとする。
	 * @param frm
	 * @param tempInsertFlg
	 */
	private void deleteConflictOrder(final OrderImportDetailForm frm,
			int tempInsertFlg) {
		
		// 対象がない場合、処理しない
		if( frm.getConflictOrderHeadList() == null 
				|| frm.getConflictOrderImportDetailList() == null ){
			return;
		}
		
		// 重複した再表示データを削除扱いとする。
		for (FrstOrderHead conflictOrderHeader : frm.getConflictOrderHeadList()) {
			if( conflictOrderHeader.getFrstOrderNo() == null || conflictOrderHeader.getFrstOrderNo().trim().length() == 0 ) { 
				continue;
			}
			
			try {
					conflictOrderHeader.setDelFlg(BigDecimal.ONE);
					// 更新の場合、
					frstOrderHeadDao.update(conflictOrderHeader);
			
					
					List<FrstOrderDetail> detailList = frstOrderDetailDao.getList(conflictOrderHeader.getFrstOrderNo());
					
					for( FrstOrderDetail detail : detailList ){
						detail.setDelFlg(BigDecimal.ONE);
						// 更新の場合、
						frstOrderDetailDao.update(detail);
					}
					
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 排他エラー
				e.printStackTrace();
				// 登録終了後の画面の遷移に影響するため、登録フラグを元に戻す
				frm.setInsertFlg(tempInsertFlg);
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				e.printStackTrace();
				// 登録終了後の画面の遷移に影響するため、登録フラグを元に戻す
				frm.setInsertFlg(tempInsertFlg);
				throw new DuplicateRuntimeException(0);
			}
		}
	}
	/**
	 * 先付け受注明細をチェックします。 20210924
	 * 下記がFormの初期状態から変更されていれば先付け受注明細を未承認にします。
	 * 全体未承認:受注区分,納入先,納入先宛先,出荷予定日,客先希望納期,納入予定日,納入先電話,納入時刻,運賃,運賃請求フラグ
	 * 個別未承認:品目,（行追加）,（行キャンセル）,数量,AP増付数,客先注文番号
	 * 全体確認は一回のみ実行され、二回目以降はスキップされます。
	 * @param frm 変更前のデータを活用する。
	 * @param updateBean 更新前のデータを確認する。
	 * @return
	 */
	private void checkApprovalUpdateFrstOrderDetail(final OrderImportDetailForm frm,
			FrstOrderDetail updateBean){
		// 一度でも全体承認項目チェックを実行したときその状態を保持して処理する。
		if(publicCheckEndFlg){
			//全体承認チェック済みのため未承認化もしくは個別チェック処理のみ実行する。
			if(publicCheckApprovalFlg){
				updateBean.setInputApprovalDate(null);
				updateBean.setInputApproverCd(null);
				updateBean.setDelDateSendDate(null);
				updateBean.setDelDateSenderCd(null);
				return;
			}
			//個別のみ確認する。
		}else{
			// 全体承認チェック項目のチェックを行う
			FrstOrderHead headBean = frm.getBeforeFrstOrderHead();
			//受注区分チェック
			if(!frm.getOrderDivision().equals(headBean.getOrderDivision().toString())){
				publicCheckApprovalFlg = true;
			}
			//納入先チェック
			if(!frm.getDeliveryCd().equals(headBean.getDeliveryCd())){
				publicCheckApprovalFlg = true;
			}
			//納入先宛先チェック
			if( !( AecStringUtils.isNotNullAndEmpty(frm.getDeliveryAddress()) == false && headBean.getDeliveryAddress() == null)){
				//片方がNULLか空白の場合
				if((!AecStringUtils.isNotNullAndEmpty(frm.getDeliveryAddress()) || headBean.getDeliveryAddress() == null)
						|| (!frm.getDeliveryAddress().equals(headBean.getDeliveryAddress()))){
					publicCheckApprovalFlg = true;
				}
			}
			//出荷予定日チェック
			if(!frm.getScheduledShippingDate().equals(new SimpleDateFormat("yyyy/MM/dd").format(headBean.getScheduledShippingDate()))){
				publicCheckApprovalFlg = true;
			}
			//客先希望納期チェック　null空白チェック  
			if( !( AecStringUtils.isNotNullAndEmpty(frm.getSuggestedDeliverlimit()) == false && headBean.getSuggestedDeliverlimit() == null)){
				//片方がNULLか空白の場合
				if((!AecStringUtils.isNotNullAndEmpty(frm.getSuggestedDeliverlimit()) || headBean.getSuggestedDeliverlimit() == null)
					|| (!frm.getSuggestedDeliverlimit().equals(new SimpleDateFormat("yyyy/MM/dd").format(headBean.getSuggestedDeliverlimit())))	){
					publicCheckApprovalFlg = true;
				}
			}
			//納入予定日チェック
			if( !frm.getDeliveryExpectedDate().equals(new SimpleDateFormat("yyyy/MM/dd").format(headBean.getDeliveryExpectedDate()))){
				publicCheckApprovalFlg = true;
			}
			//納入先電話チェック
			if( !( AecStringUtils.isNotNullAndEmpty(frm.getDeliveryTelNo()) == false && headBean.getDeliveryTelNo() == null)){
				if((!AecStringUtils.isNotNullAndEmpty(frm.getDeliveryTelNo()) || headBean.getDeliveryTelNo() == null)
						||(!frm.getDeliveryTelNo().equals(headBean.getDeliveryTelNo()))){
					publicCheckApprovalFlg = true;
				}
			}
			//納入時刻チェック
			if( !( AecStringUtils.isNotNullAndEmpty(frm.getDeliveryExpectedTime()) == false && headBean.getDeliveryExpectedTime() == null)){
				if((!AecStringUtils.isNotNullAndEmpty(frm.getDeliveryExpectedTime()) || headBean.getDeliveryExpectedTime() == null)
						||(!frm.getDeliveryExpectedTime().equals(headBean.getDeliveryExpectedTime()))){
					publicCheckApprovalFlg = true;
				}
			}
			//客先注文番号 
			if( !( AecStringUtils.isNotNullAndEmpty(frm.getFrstOrderHeadBean().getCustomerOrderNo()) == false && headBean.getCustomerOrderNo() == null)){
				if((!AecStringUtils.isNotNullAndEmpty(frm.getFrstOrderHeadBean().getCustomerOrderNo()) || headBean.getCustomerOrderNo() == null)
						||(!frm.getFrstOrderHeadBean().getCustomerOrderNo().equals(headBean.getCustomerOrderNo()))){
					publicCheckApprovalFlg = true;
				}
			}
			//運賃チェック
			System.out.println(frm.getCarryFare());
			if( !( AecStringUtils.isNotNullAndEmpty(frm.getCarryFare()) == false && headBean.getCarryFare() == null)){
				if((!AecStringUtils.isNotNullAndEmpty(frm.getCarryFare()) || headBean.getCarryFare() == null)
						||!AecNumberUtils.convertBigDecimal(frm.getCarryFare()).equals(headBean.getCarryFare())){
					publicCheckApprovalFlg = true;
				}
			}
			//運賃請求フラグチェック
			if(!frm.getCarryInvoiceFlag().equals(headBean.getCarryInvoiceFlag())){
				publicCheckApprovalFlg = true;
			}
			//全体承認チェック項目完了
			publicCheckEndFlg = true;
			if(publicCheckApprovalFlg){
				updateBean.setInputApprovalDate(null);
				updateBean.setInputApproverCd(null);
				updateBean.setDelDateSendDate(null);
				updateBean.setDelDateSenderCd(null);
				return;
			}
		}
// 個別承認項目のチェックを行う
		for (OrderImportDetailList detailBean : frm
				.getBeforeOrderImportDetailList()) {
			// 画面を開いたときの情報からPK_NOとPK_ROWが同じ情報を取得
			if (StringUtils.defaultString(detailBean.getFrstOrderNo())
					.equals(updateBean.getFrstOrderNo())
					&& detailBean.getFrstOrderRowNo().compareTo(
						updateBean.getFrstOrderRowNo()) == 0) {
				updateBean.setUpdateDate(detailBean.getUpdateDate()); // 更新日は画面を開いた時に取得した更新日付を設定しこれで排他制御を行う
				// 品目、数量、増し付け数,客先注文番号が変わった、キャンセルになった場合、未承認に戻す。　（追加は設定されないため明示的には行わない）
				if( ( !updateBean.getItemCd().equals( detailBean.getItemCd() )
						|| !updateBean.getOrderQty().toString().equals(detailBean.getOrderQty())
						|| !updateBean.getMatss().toString().equals(detailBean.getMatss()) 
						|| updateBean.getCancelFlg().intValue() != detailBean.getCancelFlg().intValue()
//						|| !updateBean.getCustomerOrderDetailNo().equals(detailBean.getCtmOrderNo())
					) 
				){
					updateBean.setInputApprovalDate(null);
					updateBean.setInputApproverCd(null);
					updateBean.setDelDateSendDate(null);
					updateBean.setDelDateSenderCd(null);
				}
				break;
			}
		}
	}

	/**
	 * 先付け受注明細を作成します。
	 * @param formData
	 * @param updateBean
	 * @return
	 */
	private void createFrstOrderDetail(OrderImportDetailList formData,
			FrstOrderDetail updateBean , final String tantoCd , final boolean isNewData) {
		updateBean.setErrorFlg(BigDecimal.ZERO); // エラー状態:エラー無
		updateBean.setCustomerOrderDetailNo(formData.getCtmOrderNo()); // 客先注文番号
		updateBean.setItemCd(formData.getItemCd()); // 品目コード
		updateBean.setOrderQty(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData.getOrderQty())); // AP数量
		updateBean.setOrderUnitprice(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData
						.getOrderUnitprice())); // AP金額
		updateBean.setStandardUnitprice(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData
						.getStandardUnitprice())); // AP標準単価
		updateBean.setStandardDiscount(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData
						.getStandardDiscount())); // AP標準値引
		updateBean.setSpecialDiscount(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData
						.getSpecialDiscount())); // AP特別値引
		updateBean.setTmpUnitpriceFlg(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData
						.getTmpUnitpriceFlg())); // AP仮単価
		updateBean.setMatss(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData.getMatss())); // AP増付数
		updateBean.setEstimateStandardAmount(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData
						.getEstimateStandardAmount())); // 見積基準数量
		updateBean.setEstimateMatss(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData
						.getEstimateMatss())); // 見積増付数
		BigDecimal orderSum = AecNumberUtils
				.convertBigDecimalNullToZeroFromString(formData.getOrderQty())
				.add(
					AecNumberUtils
							.convertBigDecimalNullToZeroFromString(formData
									.getMatss()));
		// DBに品目重量を持たせるように修正。品目重量×品目数※NULLの場合はゼロで計算を行う。
		updateBean.setWeight(AecNumberUtils
				.convertBigDecimalNullToZeroFromBigDecimal(
					formData.getAllUpWeight()).multiply(orderSum));
		updateBean.setUpdatorCd(tantoCd); // 更新者
		
		updateBean.setDelFlg(formData.getDelFlg());
		if( formData.getDelFlg().intValue() == 1 ){
			updateBean.setDeleteDate(AecDateUtils.getCurrentTimestamp());
		}
		
		updateBean.setCancelFlg(formData.getCancelFlg());
		if( formData.getCancelFlg().intValue() == 1 ){
			updateBean.setCancelDate(AecDateUtils.getCurrentTimestamp());
		}
//20210922 承認ステータスの改定 start
		// 受注更新時のレコード追加は承認済みにする。
		/*if(!isNewData && !AecStringUtils.isNotNullAndEmpty( updateBean.getInputApproverCd() )){
			updateBean.setInputApproverCd(tantoCd);
			updateBean.setInputApprovalDate(AecDateUtils.getCurrentTimestamp());
		}*/
//20210922 承認ステータスの改定 start
		// 客先注番を登録
		updateBean.setCustomerOrderDetailNo(formData.getCtmOrderNo());

		
	}

	/**
	 * 先付け受注ヘッダを作成します。
	 * @param frm
	 * @param tantoCd
	 * @param calcCarryFare
	 * @param updateHeadBean
	 * @param frstOrderNo
	 */
	private void createFrstOrderHeader(final OrderImportDetailForm frm,
			final String tantoCd, BigDecimal calcCarryFare,
			FrstOrderHead updateHeadBean, String frstOrderNo , Timestamp updateDate) {

		updateHeadBean.setFrstOrderNo(frstOrderNo);
		updateHeadBean.setBalanceCd(frm.getBalanceCd()); // 帳合コード
		updateHeadBean.setCancelFlg(BigDecimal.ZERO);
		updateHeadBean.setCarryCd(frm.getCarryCd()); // 運送会社
		if (!AecStringUtils.isNotNullAndEmpty(frm.getCarryFare())) {
			updateHeadBean.setCarryFare(calcCarryFare); // 運賃
		} else {
			updateHeadBean.setCarryFare(AecNumberUtils.convertBigDecimal(frm
					.getCarryFare())); // 運賃
		}
		// 運賃請求フラグ
		if (frm.getBlnCarryInvoiceFlag()) {
			// チェック時1
			updateHeadBean.setCarryInvoiceFlag(BigDecimal.ONE);
		} else {
			// 未チェック時0
			updateHeadBean.setCarryInvoiceFlag(BigDecimal.ZERO);
		}
		updateHeadBean.setCustomerOrderNo(frm.getHeadCtmOrderNo()); // 客先注文番号
		updateHeadBean.setDelFlg(BigDecimal.ZERO);
		updateHeadBean.setDeliveryAddress(frm.getDeliveryAddress()); // 納入先宛先
		updateHeadBean.setDeliveryCd(frm.getDeliveryCd()); // 納入先コード
		updateHeadBean.setDeliveryExpectedDate(AecDateUtils
				.convertYmdTimestampFromString(frm.getDeliveryExpectedDate())); // 納入予定日
		updateHeadBean.setDeliveryExpectedTime(frm.getDeliveryExpectedTime()); // 納入時刻
		updateHeadBean.setDeliverySlipSummery(frm.getDeliverySlipSummery()); // 自動表示備考
		updateHeadBean.setDeliveryTelNo(frm.getDeliveryTelNo()); // 納入先電話番号
		updateHeadBean.setFrstOrderNo(frstOrderNo);
		updateHeadBean.setInputTantoCd(tantoCd); // 入力担当者コード
		updateHeadBean.setLeadTime(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(frm.getLeadTime())); // リードタイム
		updateHeadBean.setOrderAmount( AecNumberUtils.convertBigDecimal( frm.getTotalOrderAmount() )); // 合計金額
		updateHeadBean.setOrderDate(AecDateUtils
				.convertYmdTimestampFromString(frm.getOrderDate())); // 受注日
		updateHeadBean.setOrderDivision(AecNumberUtils
				.convertBigDecimalNullToZeroFromString(frm.getOrderDivision())); // 受注区分
		
		if( AecStringUtils.isNotNullAndEmpty(  frm.getOrderNo() ) ){
			updateHeadBean.setOrderNo(frm.getOrderNo());
		
		}else{
			updateHeadBean.setOrderNo("");
		}
		updateHeadBean.setOrderPicture("");
		updateHeadBean.setOrderSummery(frm.getOrderSummery()); // 参照
		// 20210906 Asclab Saita 納期連絡表専用備考追加対応
		updateHeadBean.setDeliverydateContactSummery(frm.getDeliverydateContactSummery()); //  納期連絡表専用備考
		updateHeadBean.setOrganizationCd(frm.getOrganizationCd()); // 部署コード
		updateHeadBean.setPrintSummery(frm.getPrintSummery()); // 備考(印字用)
		updateHeadBean.setSalesTantoCd(frm.getSalesTantoCd()); // 営業担当者コード
		updateHeadBean.setScheduledShippingDate(AecDateUtils
				.convertYmdTimestampFromString(frm.getScheduledShippingDate())); // 出荷予定日
		updateHeadBean.setStatus(BigDecimal.ONE);
		updateHeadBean.setSuggestedDeliverlimit(AecDateUtils
				.convertYmdTimestampFromString(frm.getSuggestedDeliverlimit())); // 希望納期
		updateHeadBean.setUpdatorCd(tantoCd); // 更新者
		updateHeadBean.setTotalWeight(AecNumberUtils.convertBigDecimal( frm.getTotalWeight() )); // 合計重量
		updateHeadBean.setVenderCd(frm.getOrderDetailVenderList().get(0)
				.getVenderCd()); // 得意先コード
		updateHeadBean.setUpdateDate(updateDate);
		if (!frm.getVenderGroupCd().equals("99999")) { // 99999のときは得意先グループコードは設定しない
			updateHeadBean.setVenderGroupCd(frm.getVenderGroupCd()); // 得意先グループコード
		}
	}

	/**
	 * 受注取込テーブル確定処理(在庫引当)を行う
	 * 
	 * @param frm
	 * @param tantoCd
	 * @return ActionMessages
	 * @throws NoDataException
	 * @throws Exception
	 */
	public ActionMessages fix(final OrderImportDetailForm frm,
			final String tantoCd, final String tantoOrgCd, boolean isNewData , FrstOrderHead frstHeadBean)
			throws NoDataException, Exception {
		ActionMessages errorMsg = new ActionMessages();

		// 受注取込更新
		List<OrderImportDetailList> updateDetailList = new ArrayList<OrderImportDetailList>();
		frm.setInsertFlg(0); // エラー時にinsertFlgの値が消失するときがあるのでセット

		// 更新対象リスト取得
		updateDetailList = frm.getOrderImportDetailList();
		
		// 検索時の詳細リスト
		List<OrderImportDetailList> beforeOrderImportDetailList = frm.getBeforeOrderImportDetailList();

		// 受注番号の取得
		String orderNo = "";
		
		if( isNewData ){
			orderNo = getNumberLogic.getSupplierOrderNo();
		}else{
			orderNo = frm.getOrderNo();
		}
		
		if( isNewData ){
			
			// ヘッダの登録先の確認（同一集約条件の別受注がないかチェック）
			OrderImportDetailList concliftOrder =  orderImportDetailListDao.getConcliftFrstOrderNo(frstHeadBean.getOrderDivision()
				 , frstHeadBean.getVenderCd()
				 , frstHeadBean.getDeliveryCd()
				 , frstHeadBean.getBalanceCd()
				 , frstHeadBean.getScheduledShippingDate()
				 , frstHeadBean.getDeliveryExpectedDate()
				 , frstHeadBean.getCarryCd()
				 , frstHeadBean.getFrstOrderNo()
				 , frstHeadBean.getDeliveryAddress()
				 , frstHeadBean.getDeliveryTelNo());
			
			// 重複するヘッダがあった場合、再読み込み
			if( concliftOrder != null ){
				//同一集約条件の別受注が存在した場合、
				// 現在表示中のデータリストを再表示用に保持
				frm.setConflictOrderImportDetailList(frm.getOrderImportDetailList());
				
				// 重複したデータのヘッダリストを設定
				List<FrstOrderHead> conflistHeadList = frm.getConflictOrderHeadList();
				if(conflistHeadList == null){
					conflistHeadList = new ArrayList<FrstOrderHead>();
				}
				conflistHeadList.add(frstHeadBean);
				frm.setConflictOrderHeadList( conflistHeadList );
	
				// 表示メッセージ：errors.orderimport.order.conflict=既に登録されている受注と合わせて表示を行います。
				errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.order.conflict"));
				frm.setConflictFrstOrderNo(concliftOrder.getFrstOrderNo());
				frm.setReloadFlg(1);
				
				// Action層にメッセージを返し、メッセージがある場合、同一集約条件の別受注の品目明細と合わせて表示している。
				return errorMsg;
			}
		
		}
		
		// データベースの更新処理
		orderImportCommonLogic.fixUpdate(tantoCd, tantoOrgCd, updateDetailList,	beforeOrderImportDetailList, orderNo, frstHeadBean);
		
		return errorMsg;
	}

	/**
	 * 受注取込テーブル入力承認処理を行う
	 * 
	 * @param frm
	 * @param tantoCd
	 * @return ActionMessages
	 * @throws NoDataException
	 * @throws Exception
	 */
	public ActionMessages approval(final OrderImportDetailForm frm,
			final String tantoCd, final String tantoOrgCd)
			throws NoDataException, Exception {
		ActionMessages errorMsg = new ActionMessages();
		
		// 運賃計算
		List<String> frstOrderNoList = new ArrayList<String>();
		
		
		// **********************************
		// 承認登録処理
		// *********************************
		for (OrderImportDetailList detailBean : frm.getOrderImportDetailList()) {
			
			FrstOrderDetail updateBean = frstOrderDetailDao.getEntity(
			detailBean.getFrstOrderNo(), detailBean.getFrstOrderRowNo());
			
			// 承認済みのレコードは何もしない
			if( AecStringUtils.isNotNullAndEmpty( updateBean.getInputApproverCd() ) ){
				continue;
			}
			
			updateBean.setInputApproverCd(tantoCd);
			updateBean.setInputApprovalDate(AecDateUtils.getCurrentTimestamp());
			
			try {
				// 更新の場合、
				frstOrderDetailDao.update(updateBean);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 排他エラー
				e.printStackTrace();
				// 登録終了後の画面の遷移に影響するため、登録フラグを元に戻す
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				e.printStackTrace();
				// 登録終了後の画面の遷移に影響するため、登録フラグを元に戻す
				throw new DuplicateRuntimeException(0);
			}
			
			if( !frstOrderNoList.contains(detailBean.getFrstOrderNo()) ){
				frstOrderNoList.add(detailBean.getFrstOrderNo());
			}
		}

		// 承認履歴情報の登録
		this.orderImportCommonLogic.inserFrstOrdertLog(frstOrderNoList , tantoCd , "10");
		
		return errorMsg;
	}
	
	

	/**
	 * 受注取込ヘッド・詳細テーブル確定取消処理を行う
	 * 
	 * @param frm
	 * @param tantoCd
	 * @return ActionMessages
	 * @throws NoDataException
	 * @throws Exception
	 */
	public ActionMessages cancel(final OrderImportDetailForm frm,
			final String tantoCd) throws NoDataException, Exception {
		ActionMessages errorMsg = new ActionMessages();
		try {
			frm.setInsertFlg(0);

			// 受注ヘッダ取得
			String orderNo = frm.getOrderNo();
			String orderDivision = frm.getOrderDivision();
			String frstOrderNo = frm.getFrstOrderNo();
			//20220512 add S.Fujimaki受注詳細　排他追加
			List<OrderImportDetailList> detailList = frm.getBeforeOrderImportDetailList();
			//20220512 add S.Fujimaki受注詳細　排他追加

			orderImportCommonLogic.deleteOrder(tantoCd, orderNo, orderDivision, frstOrderNo ,detailList);
			
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 更新エラー OptimisticLockRuntimeExceptionをthrowする
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}

		return errorMsg;
	}

	/**
	 * 削除処理を行う
	 * @param frm 削除対象が画面ビーン
	 * @param tantoCd ログインユーザー
	 * @throws Exception Exception
	 */
	public ActionMessages delete(final OrderImportDetailForm frm,
			final String tantoCd) throws Exception {
		ActionMessages errorMsg = new ActionMessages();

		// 先付け受注情報の削除処理
		String frstOrderNo = frm.getFrstOrderNo();
		orderImportCommonLogic.deleteFrstOrder(tantoCd, frstOrderNo,frm.getFrstOrderHeadBean());

		return errorMsg;
	}

	/**
	 * 非表示処理を行う
	 * @param frm 非表示対象が画面ビーン
	 * @param tantoCd ログインユーザー
	 * @throws Exception Exception
	 */
	public ActionMessages invisible(final OrderImportDetailForm frm,
			final String tantoCd) throws Exception {
		ActionMessages errorMsg = new ActionMessages();

		
		// 受注取込更新
		FrstOrderHead frstHeadUpdateBean = frm.getFrstOrderHeadBean();
		// 受注番号、行番号(受注)取込ステータス、更新者情報、価格情報を登録する
		frstHeadUpdateBean.setInvisibleFlg(BigDecimal.ONE);
		frstHeadUpdateBean.setUpdatorCd(tantoCd); // 登録者
		try {
			// 更新
			frstOrderHeadDao.update(frstHeadUpdateBean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			e.printStackTrace();
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			e.printStackTrace();
			throw new DuplicateRuntimeException(0);
		}

		return errorMsg;
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
	 * 品目重量を取得する
	 * @param itemCd 品目コード
	 * @throws NoDataException NoDataException
	 * @return allUpWeight BigDecimal
	 */
	public BigDecimal getAllUpWeight(final String itemCd)
			throws NoDataException {

		Item bean = itemDao.getEntity(itemCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean.getAllUpWeight();
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
	 * 備考マスタ検索処理
	 * 
	 * @param frm OrderImportDetailForm
	 * @return List<OrderRemarkList>備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<OrderRemarkList> getRemarkList(final OrderImportDetailForm frm)
			throws NoDataException {

		String venderCd = null;
		String deliveryCd = null;
		List<OrderImportDetailList> itemList = null;

		if (frm.getOrderDetailVenderList().size() != 0) {
			venderCd = frm.getOrderDetailVenderList().get(0).getVenderCd(); // 得意先list
		}
		deliveryCd = frm.getDeliveryCd(); // 納入先
		itemList = frm.getOrderImportDetailList(); // 品目リスト

		// 得意先と納入先に紐付く備考を検索。
		List<OrderRemarkList> remarkList = orderRemarkListDao
				.getRemarkListNoItem(venderCd, deliveryCd);

		for (OrderImportDetailList bean : itemList) {
			List<OrderRemarkList> list = orderRemarkListDao
					.getRemarkListWithItem(venderCd, deliveryCd,
						bean.getItemCd());

			remarkList.addAll(list);
		}

		if (remarkList.isEmpty()) {
			throw new NoDataException();
		}
		return remarkList;
	}

	/**
	 * 運賃計算処理
	 * @param frm OrderImportDetailForm
	 * @param sameList OrderImportSameList
	 * @param tantoCd String
	 * @return BigDecimal calcCarryFare
	 * @throws throws NoDataException データが存在しない例外
	 */
	public BigDecimal calcCarryFare(final String deliveryCd,
			final OrderImportDetailForm frm,
			final List<OrderImportSameList> sameList, final String tantoCd)
			throws NoDataException {
		BigDecimal carryFare = new BigDecimal("9999999");

		if( !AecStringUtils.isNotNullAndEmpty(deliveryCd) ){
			return carryFare;
		}
		
		// 運賃計算フラグがONの時のみ、運賃計算を行う。
		if( frm.getCarryInvoiceFlag() != null && frm.getBlnCarryInvoiceFlag() == false ){
			return BigDecimal.ZERO;
		}

		
		// 運賃計算仮テーブルに登録された項目数カウント
		int count = 0;

		// シーケンス番号取得
		BigDecimal sequenceNo = namesNoListDao.getNo("CF");
		if (frm != null) {

			// 運賃自動計算用仮テーブルへの登録
			for (OrderImportDetailList detailBean : frm
					.getOrderImportDetailList()) {
				TemporaryCarryFareCalc bean = new TemporaryCarryFareCalc();
				if ( detailBean.getItemCd() == null || detailBean.getItemCd().isEmpty()
						|| detailBean.getOrderQty() == null || detailBean.getOrderQty().isEmpty()) {
					continue;
				}
				
				if( detailBean.getDelFlg() != null && ( detailBean.getDelFlg().intValue() == 1 || detailBean.getCancelFlg().intValue() == 1 ) ){
					continue;
				}

				bean.setSequenceNo(sequenceNo); //
				bean.setItemCd(detailBean.getItemCd()); //
				bean.setDeliveryCd(deliveryCd); //
				bean.setRowNo(new BigDecimal(count + 1));
				bean.setOrderQty(AecNumberUtils
						.convertBigDecimalNullToZeroFromString(
							detailBean.getOrderQty())
						.add(
							AecNumberUtils
									.convertBigDecimalNullToZeroFromString(detailBean
											.getMatss())));
				bean.setInputDate(AecDateUtils.getCurrentTimestamp());
				bean.setInputorCd(tantoCd);

				// 運賃自動計算用仮テーブルに登録
				try {
					temporaryCarryFareCalcDao.insert(bean);
					count++;
				} catch (NotSingleRowUpdatedRuntimeException e) {
					// 排他エラー
					e.printStackTrace();
					throw new OptimisticLockRuntimeException();
				} catch (SQLRuntimeException e) {
					e.printStackTrace();
					throw new DuplicateRuntimeException(0);
				}
			}
		}

		// 同じ受注リストがあればそちらも運賃自動計算仮テーブルに追加
		if (sameList != null && !sameList.isEmpty()) {

			for (OrderImportSameList sameBean : sameList) {

				TemporaryCarryFareCalc bean = new TemporaryCarryFareCalc();

				bean.setSequenceNo(sequenceNo); //
				bean.setItemCd(sameBean.getItemCd()); //
				bean.setDeliveryCd(deliveryCd); //
				bean.setRowNo(new BigDecimal(count + 1));
				bean.setOrderQty((sameBean.getOrderQty().add(sameBean
						.getMatss())));
				bean.setInputDate(AecDateUtils.getCurrentTimestamp());
				bean.setInputorCd(tantoCd);

				// 運賃自動計算用仮テーブルに登録
				try {
					temporaryCarryFareCalcDao.insert(bean);
					count++;
				} catch (NotSingleRowUpdatedRuntimeException e) {
					// 排他エラー
					e.printStackTrace();
					throw new OptimisticLockRuntimeException();
				} catch (SQLRuntimeException e) {
					e.printStackTrace();
					throw new DuplicateRuntimeException(0);
				}
			}
		}

		// 運賃計算仮テーブルに項目が1個以上登録されていればファンクション実行
		if (count >= 1) {
			// 運賃取得ファンクション呼出用設定
			FunGetCarryFareCallDto dto = new FunGetCarryFareCallDto();
			dto.setpParamSequenceNo(sequenceNo);

			// ファンクション実行
			procedureCallDao.funGetCarryFare(dto);

			carryFare = dto.getpCarryFare();
		}
		return carryFare;
	}

	/**
	 * 仮単価化処理
	 * @param frm OrderImportDetailForm
	 * @param tantoCd String
	 * @throws throws NoDataException データが存在しない例外
	 */
	public ActionMessages changeTmpUnitprice(final OrderImportDetailForm frm,
			final String tantoCd,
			NumberChkDigitCtl numberChkDigitCtl) throws NoDataException {
		ActionMessages errorMsg = new ActionMessages();

		// DB登録データの取得
		OrderImportDetailList importDetailBean = frm.getOrderImportDetailList().get(Integer.parseInt(frm.getLine()));
		
		if( importDetailBean.getTmpUnitpriceFlg().equals("0") ){
			// 仮単価化処理
			importDetailBean.setOrderUnitprice("0");
			importDetailBean.setStandardUnitprice("0");
			importDetailBean.setStandardDiscount("0");
			importDetailBean.setSpecialDiscount("0");
			importDetailBean.setEstimateStandardAmount("0");
			importDetailBean.setEstimateMatss("0");
			importDetailBean.setTmpUnitpriceFlg("1");
		}else{
			// 単価の設定
			setUnitprice(frm, importDetailBean , numberChkDigitCtl);
		}
		
		importDetailBean.setValidLine(true);
		
		// 各行の合計を再計算
		this.calcOrderAmout(frm , numberChkDigitCtl);
		// 品目リスト合計計算
		this.calcTotalOrderAmount(frm, numberChkDigitCtl);

		return errorMsg;
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
	 * 得意先グループ一覧取得
	 * @return List<VenderGroupForComboboxes>
	 */
	public List<VenderGroupForComboboxes> getVenderGroupList() {
		return venderGroupForComboboxesDao.getListForComboboxes();
	}

	/**
	 * 得意先グループコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createVenderGroupCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 得意先グループ検索
		List<VenderGroupForComboboxes> venderGroupList = getVenderGroupList();
		for (VenderGroupForComboboxes bean : venderGroupList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getVenderGroupCd());
			// 得意先グループ名称取得
			StringBuffer nameBuf = new StringBuffer("");
			nameBuf.append(bean.getVenderGroupName());
			item.setLabales(nameBuf.toString());
			res.add(item);
		}
		// 「99999:設定なし」を追加
		ComboBoxItems noData = new ComboBoxItems();
		noData.setLabales("設定なし");
		noData.setValues("99999");
		res.add(noData);

		return res;
	}

	/**
	 * 休日を考慮した出荷予定日などの日付を計算
	 * @param strDate 日付(文字列)
	 * @param leadTime リードタイム
	 * @param calCd カレンダーコード
	 * @return ProCalcDateFromCalendarCallDto 処理結果
	 */
	public ProCalcDateFromCalendarCallDto callProCalcDateFromCalendar(
			final String strDate, final BigDecimal leadTime, final String calCd) {
		ProCalcDateFromCalendarCallDto dto = new ProCalcDateFromCalendarCallDto();
		dto.setpStrDate(strDate);
		dto.setpLeadTime(leadTime); // nullの時はプロシージャ内でデフォルト値にセットされる
		dto.setpStrCalCd(calCd); // nullの時はプロシージャ内でデフォルト値にセットされる

		procedureCallDao.proCalcDateFromCalendar(dto);

		return dto;
	}

	/**
	 * 
	 * ファイル取込時のエラーログ出力
	 * @param pkNo
	 * @param pkRow
	 * @return errorMsg ActionMessages
	 * @throws Exception
	 */
	public ActionMessages getImportFileErrorLog(final String pkNo,
			final BigDecimal pkRow) throws Exception {
		ActionMessages errorMsg = new ActionMessages();

		List<OrderImportFileLog> logList = new ArrayList<OrderImportFileLog>();

		logList = orderImportFileLogDao.getList(pkNo, pkRow, BigDecimal.ONE);

		if (!logList.isEmpty()) {
			for (OrderImportFileLog logBean : logList) {

				// ログのエラーメッセージをActionMessagesに入れる
				errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.detail", logBean.getMsg()));
			}
		}

		return errorMsg;
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
	 * 同じ受注番号の詳細に受注からのデータと受注取込からのデータが混在しているかチェックする.<br>
	 * @param orderNo 受注番号
	 * @return Boolean true:受注取込からのデータしか存在しない false:受注からのデータと受注取込のデータが混在している
	 */
	public Boolean checkOrderDetailListData(final String orderNo) {

		// 受注詳細リスト取得
		List<OrderDetail> orderDetailList = orderDetailDao.getList(orderNo);

		// 受注詳細リストの中に受注から登録されたデータがあるかを確認する
		for (OrderDetail bean : orderDetailList) {
			// データ作成区分 1:受注から新規追加 があればfalseを返す
			if (bean.getInputDivision().compareTo(BigDecimal.ONE) == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 単価の取得
	 * @param frm
	 * @param detailBean
	 */
	public void setUnitprice(OrderImportDetailForm frm,
			OrderImportDetailList detailBean,
			NumberChkDigitCtl numberChkDigitCtl) {
		//仮単価を設定
		VaridUnitprice bean = getValidUnitpriceLogic
				.getValidUnitprice(frm.getBalanceCd(),
					detailBean.getItemCd(), frm.getScheduledShippingDate());
		
		if( bean != null &&  !bean.getTmpUnitpriceFlg().equals("1")) {
			/* 単価、値引き、増付数を0 */
			detailBean.setOrderUnitprice(bean.getUnitprice().toString());
			detailBean.setStandardUnitprice(bean.getStandardUnitPrice().toString());
			detailBean.setStandardDiscount(bean.getStandardDiscount().toString());
			detailBean.setSpecialDiscount(bean.getSpecialDiscount().toString());
			detailBean.setMatss("0");
			// 増付数
			detailBean.setEstimateMatss(bean.getMatss().toString());
			// 基準数量
			detailBean.setEstimateStandardAmount(bean.getStandardAmount().toString());
			/* 仮単価フラグON */
			detailBean.setTmpUnitpriceFlg("0");
			detailBean.setValidLine(false);

			
		}else{
			/* 単価、値引き、増付数を0 */
			detailBean.setOrderUnitprice("0");
			detailBean.setStandardUnitprice("0");
			detailBean.setStandardDiscount("0");
			detailBean.setSpecialDiscount("0");
			detailBean.setMatss("0");
			// 増付数
			detailBean.setEstimateMatss("0");
			// 基準数量
			detailBean.setEstimateStandardAmount("0");
			/* 仮単価フラグON */
			detailBean.setTmpUnitpriceFlg("1");
			detailBean.setValidLine(false);
		}
		

		
		// 数値桁数チェック
		NumberChkDisitDetail checkDigit = numberChkDigitCtl.getCheckDigitDetail( checker, Constants.TANKA, Constants.VENDER_DIVISION_TS, frm.getVenderCd() );
		
		detailBean.setStandardUnitprice(checker.format( checkDigit ,
			AecNumberUtils.convertBigDecimalFromString(detailBean.getStandardUnitprice()))); // AP標準単価
		detailBean.setStandardDiscount(checker.format( checkDigit ,
			AecNumberUtils.convertBigDecimalFromString(detailBean.getStandardDiscount()))); // AP標準値引
		detailBean.setSpecialDiscount(checker.format( checkDigit ,
			AecNumberUtils.convertBigDecimalFromString(detailBean.getSpecialDiscount()))); // AP特別値引
		
		// 標準単価と標準値引きのJSPで行う数値桁数の設定を標準機能から取得する(同じ値)
		detailBean.setSmallnumLengthUnitprice(
			AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkDigit.getSmallnumLength())); // 
		detailBean.setRoundDivisionUnitprice(AecNumberUtils
				.convertStringNullToZeroFromBigDecimal(checkDigit.getRoundDivision())); // 
	}
	

	/**
	 * 品目リスト各行の合計金額・個数・重量を計算
	 * @param OrderImportDetailForm
	 * @return OrderImportDetailForm
	 */
	public OrderImportDetailForm calcOrderAmout(final OrderImportDetailForm frm,NumberChkDigitCtl numberChkDigitCtl)
			throws NoDataException {
		List<OrderImportDetailList> detailList = frm.getOrderImportDetailList();

		for (OrderImportDetailList bean : detailList) {

			// 合計金額計算
			BigDecimal sumAmount = BigDecimal.ZERO;

			// 単価と数量が存在する場合は金額計算それ以外は金額0
			if (bean.getOrderUnitprice() != null && bean.getOrderQty() != null) {
				sumAmount = AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getOrderUnitprice())
						.multiply(AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getOrderQty()));
			}

			bean.setOrderAmount(AecNumberUtils.convertStringNullToOneFromBigDecimal(sumAmount)); // 合計金額
			
			// 合計個数計算
			BigDecimal sumQty = BigDecimal.ZERO;
			
			//数量が存在する場合は計算それ以外は0
			if (AecStringUtils.isNotNullAndEmpty(bean.getOrderQty())){
				sumQty = AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getOrderQty());
				// 数量に増付数を加算
				sumQty.add(AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getMatss()));
			}
			
			bean.setQty(AecNumberUtils.convertStringNullToZeroFromBigDecimal(sumQty)); // 合計個数
			
			//合計重量計算
			BigDecimal sumWeight = BigDecimal.ZERO;
			
			// 数量が存在する場合は計算それ以外は0
			if(AecStringUtils.isNotNullAndEmpty(bean.getOrderQty())){
				sumWeight = AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(bean.getAllUpWeight()).multiply(sumQty);
			}
			
			bean.setWeight(AecNumberUtils.convertStringNullToZeroFromBigDecimal(sumWeight));
			
 			if(AecStringUtils.isNotNullAndEmpty(bean.getOrderQty())){
				// 数値桁数チェックを実施
				if( AecStringUtils.isNotNullAndEmpty(frm.getVenderCd())){
					bean.setOrderQty(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,bean.getUnitOfOperationManagement(), Constants.VENDER_DIVISION_TS, frm.getVenderCd() ),
						AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getOrderQty()))); // 受注数量
				} else{
					bean.setOrderQty("0");
				}
			}
			// 受注数量のJSPで行う数値桁数の設定を標準機能から取得する
			NumberChkDisitDetail checkData = numberChkDigitCtl.getCheckDigitDetail(checker,
					bean.getUnitOfOperationManagement(), null, null);

			bean.setSmallnumLengthNum(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData.getSmallnumLength())); // 小数点位置
			bean.setRoundDivisionNum(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData.getRoundDivision())); // 丸め区分
		
			bean.setStandardUnitprice(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.TANKA, Constants.VENDER_DIVISION_TS, frm.getVenderCd()),
					AecNumberUtils.convertBigDecimalFromString(bean.getStandardUnitprice()))); // AP標準単価
			bean.setStandardDiscount(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.TANKA, Constants.VENDER_DIVISION_TS, frm.getVenderCd()),
					AecNumberUtils.convertBigDecimalFromString(bean.getStandardDiscount()))); // AP標準値引
			bean.setSpecialDiscount(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.TANKA, Constants.VENDER_DIVISION_TS, frm.getVenderCd()),
				AecNumberUtils.convertBigDecimalFromString(bean.getSpecialDiscount()))); // AP特別値引
	
			// 標準単価と標準値引きのJSPで行う数値桁数の設定を標準機能から取得する(同じ値)
			checkData = numberChkDigitCtl.getCheckDigitDetail(checker,Constants.TANKA, Constants.VENDER_DIVISION_TS, frm.getVenderCd());
			
			bean.setSmallnumLengthUnitprice(
				AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkData.getSmallnumLength())); // 小数点桁数(単価)
			bean.setRoundDivisionUnitprice(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData.getRoundDivision())); // 端数区分(単価)
			
			// 受注金額のJSPで行う数値桁数の設定を標準機能から取得する
			checkData = numberChkDigitCtl.getCheckDigitDetail(checker,Constants.URKINGAKU, Constants.VENDER_DIVISION_TS, frm.getVenderCd());
			
			bean.setOrderAmount(checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.URKINGAKU, Constants.VENDER_DIVISION_TS, frm.getVenderCd()),
				AecNumberUtils.convertBigDecimalFromString(bean.getOrderAmount()))); // 金額
			
			bean.setSmallnumLengthOrderAmount(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData.getSmallnumLength())); // 小数点桁数(金額)
			bean.setRoundDivisionOrderAmount(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData.getRoundDivision())); // 端数区分(金額)
			
			// 重量のJSPで行う数値桁数の設定を標準機能から取得する
			checkData = numberChkDigitCtl.getCheckDigitDetail(checker,Constants.KG, Constants.VENDER_DIVISION_TS, frm.getVenderCd());
			bean.setSmallnumLengthWeight(
				AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkData.getSmallnumLength())); // 小数点桁数(合計重量)
			bean.setRoundDivisionWeight(
				AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkData.getRoundDivision())); // 端数区分(合計重量)
		}

		frm.setOrderImportDetailList(detailList);
		return frm;
	}

	/**
	 * 品目リスト全体の合計金額、個数、重量を計算、設定
	 * @param OrderImportDetailForm
	 * @return OrderImportDetailForm
	 */
	public OrderImportDetailForm calcTotalOrderAmount(final OrderImportDetailForm frm,NumberChkDigitCtl numberChkDigitCtl)
			throws NoDataException {

		BigDecimal totalAmount = BigDecimal.ZERO; // 合計金額
		BigDecimal totalQty = BigDecimal.ZERO; // 合計個数
		BigDecimal totalWeight = BigDecimal.ZERO; // 合計重量

		List<OrderImportDetailList> detailList = frm.getOrderImportDetailList();

		for (OrderImportDetailList bean : detailList) {

			// 削除かキャンセルの場合、合計を計算しない。
			if( bean.getDelFlg().intValue() == 1 || bean.getCancelFlg().intValue() == 1 ){
				continue;
			}
			
			// 単価と数量が存在する場合金額計算
			if (bean.getOrderUnitprice() != null && bean.getOrderQty() != null) {
				BigDecimal sumAmount = AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getOrderUnitprice())
						.multiply(AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getOrderQty()));
				totalAmount = totalAmount.add(sumAmount);
			}
			
			// 数量が存在する場合個数と重量計算
			if(bean.getOrderQty() != null){
				totalQty = totalQty.add(AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getQty()));
				totalWeight = totalWeight.add(AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getWeight()));
			}
		}

		// 数値桁数チェック用
		String orderAmount = "";
		String qty = "";
		String weight = "";

		// 合計金額桁数チェック
		orderAmount = checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.URKINGAKU, Constants.VENDER_DIVISION_TS, frm.getVenderCd()), totalAmount);
		frm.setTotalOrderAmount(orderAmount);
		
		// 合計個数チェック
		qty = checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.PIECE, Constants.VENDER_DIVISION_TS, frm.getVenderCd()), totalQty); // 8=個
		frm.setTotalQty(qty);
		
		// 合計重量桁数チェック
		weight = checker.format( numberChkDigitCtl.getCheckDigitDetail(checker,Constants.KG, Constants.VENDER_DIVISION_TS, frm.getVenderCd()), totalWeight); // 1=kg
		frm.setTotalWeight(weight);
		
		//桁数情報取得
		try{
			NumberChkDisitDetail checkData = null;
			// 金額
			if(orderAmount != null){
				checkData = numberChkDigitCtl.getCheckDigitDetail(checker,Constants.URKINGAKU, Constants.VENDER_DIVISION_TS, frm.getVenderCd());
				frm.setSmallnumLengthUrKingaku(
					AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkData.getSmallnumLength())); // 小数点桁数(金額)
				frm.setRoundDivisionUrKingaku(
					AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkData.getRoundDivision())); // 端数区分(金額)
			}
			
			// 合計個数
			if(qty != null){
				checkData = numberChkDigitCtl.getCheckDigitDetail(checker,Constants.PIECE, Constants.VENDER_DIVISION_TS, frm.getVenderCd());
				frm.setSmallnumLengthTotalNum(
					AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkData.getSmallnumLength())); // 小数点桁数(合計個数)
				frm.setRoundDivisionTotalNum(
					AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkData.getRoundDivision())); // 端数区分(合計個数)
			}
			
			// 合計重量
			if(weight != null){
				checkData = numberChkDigitCtl.getCheckDigitDetail(checker,Constants.KG, Constants.VENDER_DIVISION_TS, frm.getVenderCd());
				frm.setSmallnumLengthTotalWeight(
					AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkData.getSmallnumLength())); // 小数点桁数(合計重量)
				frm.setRoundDivisionTotalWeight(
					AecNumberUtils.convertStringNullToZeroFromBigDecimal(checkData.getRoundDivision())); // 端数区分(合計重量)
			}
			
		} catch (Exception e) {
		}
		
		return frm;
	}
	
	
	/* -------------------- setter -------------------- */

	/**
	 * orderImportDetailEntityDaoを設定します。
	 * @param orderImportDetailEntityDao orderImportDetailEntityDao
	 */
	public void setOrderImportDetailEntityDao(
			OrderImportDetailEntityDao orderImportDetailEntityDao) {
		this.orderImportDetailEntityDao = orderImportDetailEntityDao;
	}

	/**
	 * getValidUnitpriceLogicを設定します。
	 * @param getValidUnitpriceLogic getValidUnitpriceLogic
	 */
	public void setGetValidUnitpriceLogic(
			GetValidUnitpriceLogic getValidUnitpriceLogic) {
		this.getValidUnitpriceLogic = getValidUnitpriceLogic;
	}

	/**
	 * orderImportDetailListDaoを設定します。
	 * @param orderImportDetailListDao orderImportDetailListDao
	 */
	public void setOrderImportDetailListDao(
			OrderImportDetailListDao orderImportDetailListDao) {
		this.orderImportDetailListDao = orderImportDetailListDao;
	}

	/**
	 * orderCarryListForComboboxesDaoを設定します。
	 * @param orderCarryListForComboboxesDao orderCarryListForComboboxesDao
	 */
	public void setOrderCarryListForComboboxesDao(
			OrderCarryListForComboboxesDao orderCarryListForComboboxesDao) {
		this.orderCarryListForComboboxesDao = orderCarryListForComboboxesDao;
	}

	/**
	 * orderDetailVenderListDaoを設定します。
	 * @param orderDetailVenderListDao orderDetailVenderListDao
	 */
	public void setOrderDetailVenderListDao(
			OrderDetailVenderListDao orderDetailVenderListDao) {
		this.orderDetailVenderListDao = orderDetailVenderListDao;
	}

	/**
	 * orderDetailVenderDetailDaoを設定します。
	 * @param orderDetailVenderDetailDao orderDetailVenderDetailDao
	 */
	public void setOrderDetailVenderDetailDao(
			OrderDetailVenderDetailDao orderDetailVenderDetailDao) {
		this.orderDetailVenderDetailDao = orderDetailVenderDetailDao;
	}

	/**
	 * itemDaoを設定します。
	 * @param itemDao itemDao
	 */
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * loginDaoを設定します。
	 * @param loginDao loginDao
	 */
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * orderDetailDaoを設定します。
	 * @param orderDetailDao orderDetailDao
	 */
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	/**
	 * orderImportSameListDaoを設定します。
	 * @param orderImportSameListDao orderImportSameListDao
	 */
	public void setOrderImportSameListDao(
			OrderImportSameListDao orderImportSameListDao) {
		this.orderImportSameListDao = orderImportSameListDao;
	}

	/**
	 * itemInventoryDaoを設定します。
	 * @param itemInventoryDao itemInventoryDao
	 */
	public void setItemInventoryDao(ItemInventoryDao itemInventoryDao) {
		this.itemInventoryDao = itemInventoryDao;
	}

	/**
	 * orderNoGoodQtyDaoを設定します。
	 * @param orderNoGoodQtyDao orderNoGoodQtyDao
	 */
	public void setOrderNoGoodQtyDao(OrderNoGoodQtyDao orderNoGoodQtyDao) {
		this.orderNoGoodQtyDao = orderNoGoodQtyDao;
	}

	/**
	 * orderRemarkListDaoを設定します。
	 * @param orderRemarkListDao orderRemarkListDao
	 */
	public void setOrderRemarkListDao(OrderRemarkListDao orderRemarkListDao) {
		this.orderRemarkListDao = orderRemarkListDao;
	}

	/**
	 * getNumberLogicを設定します。
	 * @param getNumberLogic getNumberLogic
	 */
	public void setGetNumberLogic(GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * orderNamesListDaoを設定します。
	 * @param orderNamesListDao orderNamesListDao
	 */
	public void setOrderNamesListDao(OrderNamesListDao orderNamesListDao) {
		this.orderNamesListDao = orderNamesListDao;
	}

	/**
	 * namesNoListDaoを設定します。
	 * @param namesNoListDao namesNoListDao
	 */
	public void setNamesNoListDao(NamesNoListDao namesNoListDao) {
		this.namesNoListDao = namesNoListDao;
	}

	/**
	 * temporaryCarryFareCalcDaoを設定します。
	 * @param temporaryCarryFareCalcDao temporaryCarryFareCalcDao
	 */
	public void setTemporaryCarryFareCalcDao(
			TemporaryCarryFareCalcDao temporaryCarryFareCalcDao) {
		this.temporaryCarryFareCalcDao = temporaryCarryFareCalcDao;
	}

	/**
	 * orderImportFileLogDaoを設定します。
	 * @param orderImportFileLogDao orderImportFileLogDao
	 */
	public void setOrderImportFileLogDao(
			OrderImportFileLogDao orderImportFileLogDao) {
		this.orderImportFileLogDao = orderImportFileLogDao;
	}

	/**
	 * companySettingDaoを設定します。
	 * @param companySettingDao companySettingDao
	 */
	public void setCompanySettingDao(CompanySettingDao companySettingDao) {
		this.companySettingDao = companySettingDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * checkerを設定します。
	 * @param checker checker
	 */
	public void setChecker(CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * errorLogDaoを設定します。
	 * @param errorLogDao errorLogDao
	 */
	public void setErrorLogDao(ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * venderGroupForComboboxesDaoを設定します。
	 * @param venderGroupForComboboxesDao venderGroupForComboboxesDao
	 */
	public void setVenderGroupForComboboxesDao(
			VenderGroupForComboboxesDao venderGroupForComboboxesDao) {
		this.venderGroupForComboboxesDao = venderGroupForComboboxesDao;
	}

	/**
	 * orderImportDataEntityDaoを取得します。
	 * @return orderImportDataEntityDao
	 */
	public OrderImportDataEntityDao getOrderImportDataEntityDao() {
		return orderImportDataEntityDao;
	}

	/**
	 * orderImportDataEntityDaoを設定します。
	 * @param orderImportDataEntityDao orderImportDataEntityDao
	 */
	public void setOrderImportDataEntityDao(
			OrderImportDataEntityDao orderImportDataEntityDao) {
		this.orderImportDataEntityDao = orderImportDataEntityDao;
	}

	/**
	 * frstOrderHeadDaoを取得します。
	 * @return frstOrderHeadDao
	 */
	public FrstOrderHeadDao getFrstOrderHeadDao() {
		return frstOrderHeadDao;
	}

	/**
	 * frstOrderHeadDaoを設定します。
	 * @param frstOrderHeadDao frstOrderHeadDao
	 */
	public void setFrstOrderHeadDao(FrstOrderHeadDao frstOrderHeadDao) {
		this.frstOrderHeadDao = frstOrderHeadDao;
	}

	/**
	 * frstOrderDetailDaoを取得します。
	 * @return frstOrderDetailDao
	 */
	public FrstOrderDetailDao getFrstOrderDetailDao() {
		return frstOrderDetailDao;
	}

	/**
	 * frstOrderDetailDaoを設定します。
	 * @param frstOrderDetailDao frstOrderDetailDao
	 */
	public void setFrstOrderDetailDao(FrstOrderDetailDao frstOrderDetailDao) {
		this.frstOrderDetailDao = frstOrderDetailDao;
	}

	/**
	 * orderImportCommonLogicを取得します。
	 * @return orderImportCommonLogic
	 */
	public OrderImportCommonLogic getOrderImportCommonLogic() {
		return orderImportCommonLogic;
	}

	/**
	 * orderImportCommonLogicを設定します。
	 * @param orderImportCommonLogic orderImportCommonLogic
	 */
	public void setOrderImportCommonLogic(OrderImportCommonLogic orderImportCommonLogic) {
		this.orderImportCommonLogic = orderImportCommonLogic;
	}
	
	

}