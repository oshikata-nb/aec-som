/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.error.ErrorAction;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailList;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 受注詳細 Formクラス.
 * @author t1344224
 */
public final class OrderDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 受注番号 */
	private String orderNo;

	/** 受注区分 */
	private String orderDivision;

	/** 受注日 */
	private java.sql.Timestamp orderDate;

	/** 納入先コード */
	private String deliveryCd;
	
	/**　納入先区分*/
	private String deliveryDivision;

	/** 営業担当者コード */
	private String eigyoTantoCd;

	/** 営業担当者名 */
	private String eigyoTantoName;

	/** 納入先名称１ */
	private String deliveryName1;

	/** 納入先あて先 */
	private String deliveryAddress;

	/** 納入先住所(１＋２＋３) */
	private String address;

	/** 納入先電話番号 */
	private String telNo;

	/** 全体合計金額 */
	private java.math.BigDecimal orderAmount;

	/** 全体合計金額(String) */
	private String strSumOrderAmount;

	/** 担当部署コード */
	private String organizationCd;

	/** 担当部署名称 */
	private String organizationName;

	/** 帳合コード */
	private String balanceCd;

	/** 希望納期 */
	private java.sql.Timestamp suggestedDeliverlimit;

	/** 出荷予定日 */
	private java.sql.Timestamp scheduledShippingDate;

	/** リードタイム */
	private java.math.BigDecimal leadTime;

	/** リードタイム(Strinｇ) */
	private String strLeadTime;

	/** 運賃 */
	private java.math.BigDecimal carryFare;

	/** 運賃(string) */
	private String strCarryFare;

	/** 運賃請求フラグ */
	private Boolean carryInvoiceFlag;

	/** 運送会社コード */
	private String carryCd;

	/** 運送会社コード */
	private String tempCarryCd;

	/** 納入予定日 */
	private java.sql.Timestamp deliveryExpectedDate;

	/** 納入予定時刻 */
	private String deliveryExpectedTime;

	/** 注文番号 */
	private String customerOrderNo;

	/** vederCd */
	private String venderCd;

	/** 納入書備考 */
	private String deliverySlipSummery;
	
	/** 納入書備考(表示用) */
	private String deliverySlipSummeryDisp;

	/** 自動表示備考 */
	private String orderSummery;
	
	/** 新規備考 */
	private String printSummery;

	/** 注文書画像 */
	private FormFile uploadFile;

	/** 注文書画像のファイル名 */
	private String orderPicture;

	/** ダウンロードフラグ */
	private Boolean downloadFlg;

	/** 更新日時 */
	private java.sql.Timestamp updateDate;

	/** 受注日(String) */
	private String strOrderDate;

	/** 出荷予定日(String) */
	private String strScheduledShippingDate;

	/** 希望納期(String) */
	private String strSuggestedDeliverlimit;

	/** 納入予定日(String) */
	private String strDeliveryExpectedDate;

	/** 小数点桁数(運賃) */
	private String decimalPointUntin;

	/** 端数区分(運賃) */
	private String roundUntin;

	/** 区分(運賃) */
	private String unitDivisionUntin;

	/** 小数点桁数(URKINGAKU) */
	private String decimalPointUrKingaku;

	/** 端数区分(URKINGAKU) */
	private String roundUrKingaku;

	/** 小数点桁数(SONOTA) */
	private String decimalPointSonota;

	/** 端数区分(SONOTA) */
	private String roundSonota;

	/** 品目情報リスト件数 */
	private int orderDetailListCount;

	/**  */
	private int varidUnitpriceRow;

	/** 品目情報リスト */
	private List<OrderDetailList> orderDetailList;

	/** 品目情報リスト 表示時のデータを保持 */
	private List<OrderDetailList> orderDetailListOld;

	/** 行削除リスト */
	private List<OrderDetailList> delFormList;

	/** 得意先リスト */
	private List<OrderDetailVenderList> orderDetailVenderList;

	/** 変更フラグ */
	private Boolean dirtyFlg;

	/** 新規フラグ */
	private int newFlg;

	/** 変更不可 0:変更可 1:変更不可 */
	private int updateFlg;

	/** 受注区分コンボボックス */
	private List<ComboBoxItems> orderDivisionCombo;

	/** 運送会社コンボボックス */
	private List<ComboBoxItems> carryCombo;

	/** カーソル位置 */
	private String cursor;

	/** カーソル位置 */
	private String cursorIndex;

	/** 小数点桁数(URTANKA) */
	private String decimalPointUrTanka;

	private static Log log = LogFactory.getLog(ErrorAction.class);

	/**
	 * コンストラクタ.
	 */
	public OrderDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		log.error("CHECK_OP:  " + getOp());
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}

		// // 新規ボタン
		// if ("newPage".equals(getOp())) {
		// clear();
		// }

		// 更新ボタン
		if ("update".equals(getOp())) {
			// チェックボックスをクリア
			this.setCarryInvoiceFlag(Boolean.FALSE);
		}

		// 行削除ボタン
		if ("dellist".equals(getOp())) {
			clearCheck();
		}
		// チェックボックスをクリア
		this.setCarryInvoiceFlag(Boolean.FALSE);
		/* ダウンロードフラグを倒す */
		setDownloadFlg(false);

		setDirtyFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		log.error("CHECK_OP:  " + getOp());
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			// 受注数量のチェック
			validateOrderQty(request, errors);

			// validateメソッドによる入力チェック
			validateInsertList(request, errors);
		}
		if ("insert".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);

			// 受注数量のチェック
			validateOrderQty(request, errors);

			// validateメソッドによる入力チェック
			validateInsertList(request, errors);
		}
		if ("initNew".equals(getOp())) {
			/* イレギュラーだけど、ここでフォームをクリア */
			this.clear();
		}

		return errors;
	}

	/**
	 * 更新処理の入力チェック (品目の重複チェック)
	 * @param request HttpServletRequest
	 * @param errors エラー内容
	 */
	private void validateOrderQty(final HttpServletRequest request,
			final ActionErrors errors) {
		List<OrderDetailList> detailList = getOrderDetailList();

		if (detailList != null) {
			for (int i = 0; i < detailList.size(); i++) {
				OrderDetailList detailBean = detailList.get(i);

				// 受注数量
				BigDecimal orderQty = AecNumberUtils
						.convertBigDecimal(detailBean.getStrOrderQty());

				// 増付数量
				BigDecimal matss = AecNumberUtils.convertBigDecimal(detailBean
						.getStrMatss());

				// 数量項目が入力されていてかつ数値では無い場合エラーメッセージ
				if (orderQty == null && detailBean.getStrOrderQty() != null) {
					errors.add("", new ActionMessage(
							"errors.order.qty.not.exist.row", Integer
									.toString(i + 1)));
				} else {

					// 受注数量が０以上かチェック
					if (orderQty.intValue() < 1) {
						errors.add("",
							new ActionMessage("errors.order.qty.row", Integer
									.toString(i + 1)));

					}
				}
				// 増付項目が入力されていてかつ数値では無い場合エラーメッセージ
				if (matss == null && detailBean.getStrMatss() != null) {
					errors.add("", new ActionMessage(
							"errors.order.matss.not.exist.row", Integer
									.toString(i + 1)));
				}

			}
		}

	}

	/**
	 * 更新処理の入力チェック (品目の重複チェック)
	 * @param request HttpServletRequest
	 * @param errors エラー内容
	 */
	private void validateInsertList(final HttpServletRequest request,
			final ActionErrors errors) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		List<OrderDetailList> detailList = getOrderDetailList();
		HashMap<String, String> directionMap = new HashMap<String, String>();
		boolean errorFlag = false;

		if (detailList != null) {
			for (int i = 0; i < detailList.size(); i++) {
				OrderDetailList detailBean = detailList.get(i);
				if (detailBean.getItemCd() != null
						&& !detailBean.getItemCd().equals("")) {
					if (directionMap.get(detailBean.getItemCd()) != null) {
						errorFlag = true;
					} else {
						directionMap.put(detailBean.getItemCd(), detailBean
								.getItemCd());
					}
				}
			}
		}

		if (errorFlag) {
			errors.add("", new ActionMessage("errors.order.duplication", rb
					.getString("item.order.item.cd")));
		}

	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getOrderDetailList() != null) {
			for (OrderDetailList bean : getOrderDetailList()) {
				bean.setCheckline(Boolean.FALSE);
			}
		}
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		/* 受注番号 */
		setOrderNo(null);
		/* 受注区分 */
		setOrderDivision(null);
		/* 受注日 */
		setOrderDate(null);
		/* 納入先コード */
		setDeliveryCd(null);
		/* 納入先区分 */
		setDeliveryDivision(null);
		/* 営業担当者コード */
		setEigyoTantoCd(null);
		/* 営業担当者コード */
		setEigyoTantoName(null);
		/* 納入先名称１ */
		setDeliveryName1(null);
		/* 納入先あて先 */
		setDeliveryAddress(null);
		/* 納入先住所(１＋２＋３) */
		setAddress(null);
		/* 納入先電話番号 */
		setTelNo(null);
		/* 合計金額 */
		setOrderAmount(null);
		/* 合計金額(String) */
		setStrSumOrderAmount(null);
		/* 担当部署コード */
		setOrganizationCd(null);
		/* 担当部署名称 */
		setOrganizationName(null);
		/* 帳合コード */
		setBalanceCd(null);
		/* 希望納期 */
		setSuggestedDeliverlimit(null);
		/* 出荷予定日 */
		setScheduledShippingDate(null);
		/* リードタイム */
		setLeadTime(null);
		/* リードタイム(String) */
		setStrLeadTime(null);
		/* 運賃 */
		setCarryFare(null);
		/* 運賃(String) */
		setStrCarryFare(null);
		/* 運賃請求フラグ */
		setCarryInvoiceFlag(Boolean.FALSE);
		/* 運送会社コード */
		setCarryCd(null);
		/* 納入予定日 */
		setDeliveryExpectedDate(null);
		/* 納入予定時刻 */
		setDeliveryExpectedTime(null);
		/* 注文番号 */
		setCustomerOrderNo(null);
		/* 備考(印字用) */
		setPrintSummery(null);
		/* 納入書備考 */
		setDeliverySlipSummery(null);
		/* 備考 */
		setOrderSummery(null);
		/* 注文書画像 */
		setUploadFile(null);
		/* 注文書画像ファイル名 */
		setOrderPicture(null);
		/* 更新日時 */
		setUpdateDate(null);
		/* 受注日(String) */
		setStrOrderDate(null);
		/* 出荷予定日(String) */
		setStrScheduledShippingDate(null);
		/* 希望納期(String) */
		setStrSuggestedDeliverlimit(null);
		/* 納入予定日(String) */
		setStrDeliveryExpectedDate(null);
		/* 品目情報リスト */
		setOrderDetailList(new ArrayList<OrderDetailList>());

		setOrderDetailListOld(new ArrayList<OrderDetailList>());

		setDelFormList(new ArrayList<OrderDetailList>());
		/* 得意先リスト */
		setOrderDetailVenderList(new ArrayList<OrderDetailVenderList>());
		setCursor(null);
		setCursorIndex(null);
		setDecimalPointUrTanka(null);
		
		setDeliverySlipSummeryDisp(null);

	}

	//
	// インスタンスメソッド
	//

	/**
	 * orderNo取得.
	 * @return orderNo
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * orderNo設定.
	 * @param orderNo orderNo
	 */
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * orderDivision取得.
	 * @return orderDivision
	 */
	public String getOrderDivision() {
		return this.orderDivision;
	}

	/**
	 * orderDivision設定.
	 * @param orderDivision orderDivision
	 */
	public void setOrderDivision(final String orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * orderDate取得.
	 * @return orderDate
	 */
	public java.sql.Timestamp getOrderDate() {
		return this.orderDate;
	}

	/**
	 * orderDate設定.
	 * @param orderDate orderDate
	 */
	public void setOrderDate(final java.sql.Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * deliveryCd取得.
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * deliveryCd設定.
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}
	
	/**
	 * 検索入力：区分取得.
	 * @return String 区分
	 */
	public String getDeliveryDivision() {
		return this.deliveryDivision;
	}

	/**
	 * 検索入力：区分設定.
	 * @param deliveryDivision 区分
	 */
	public void setDeliveryDivision(final String deliveryDivision) {
		this.deliveryDivision = deliveryDivision;
	}

	/**
	 * deliveryName1取得.
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * deliveryName1設定.
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * deliveryAddress取得.
	 * @return deliveryAddress
	 */
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * deliveryAddress設定.
	 * @param deliveryAddress deliveryAddress
	 */
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * adress取得.
	 * @return adress
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * address設定.
	 * @param address address
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * telNo取得.
	 * @return telNo
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * telNo設定.
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * orderAmount取得.
	 * @return orderAmount
	 */
	public java.math.BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	/**
	 * orderAmount設定.
	 * @param orderAmount orderAmount
	 */
	public void setOrderAmount(final java.math.BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * 合計金額(String) を取得します。
	 * @return strSumOrderAmount
	 */
	public String getStrSumOrderAmount() {
		return strSumOrderAmount;
	}

	/**
	 * 合計金額(String) を設定します。
	 * @param strSumOrderAmount 合計金額(String)
	 */
	public void setStrSumOrderAmount(final String strSumOrderAmount) {
		this.strSumOrderAmount = strSumOrderAmount;
	}

	/**
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationName取得.
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * balanceCd取得.
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * balanceCd設定.
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * suggestedDeliverlimit取得.
	 * @return suggestedDeliverlimit
	 */
	public java.sql.Timestamp getSuggestedDeliverlimit() {
		return this.suggestedDeliverlimit;
	}

	/**
	 * suggestedDeliverlimit設定.
	 * @param suggestedDeliverlimit suggestedDeliverlimit
	 */
	public void setSuggestedDeliverlimit(
			final java.sql.Timestamp suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
	}

	/**
	 * scheduledShippingDate取得.
	 * @return scheduledShippingDate
	 */
	public java.sql.Timestamp getScheduledShippingDate() {
		return this.scheduledShippingDate;
	}

	/**
	 * scheduledShippingDate設定.
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(
			final java.sql.Timestamp scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
	}

	/**
	 * leadTime取得.
	 * @return leadTime
	 */
	public java.math.BigDecimal getLeadTime() {
		return this.leadTime;
	}

	/**
	 * leadTime設定.
	 * @param leadTime leadTime
	 */
	public void setLeadTime(final java.math.BigDecimal leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * carryFare取得.
	 * @return carryFare
	 */
	public java.math.BigDecimal getCarryFare() {
		return this.carryFare;
	}

	/**
	 * carryFare設定.
	 * @param carryFare carryFare
	 */
	public void setCarryFare(final java.math.BigDecimal carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * carryInvoiceFlag取得.
	 * @return carryInvoiceFlag
	 */
	public Boolean getCarryInvoiceFlag() {
		return this.carryInvoiceFlag;
	}

	/**
	 * carryInvoiceFlag設定.
	 * @param carryInvoiceFlag carryInvoiceFlag
	 */
	public void setCarryInvoiceFlag(final Boolean carryInvoiceFlag) {
		this.carryInvoiceFlag = carryInvoiceFlag;
	}

	/**
	 * carryCd取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * carryCd設定.
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * deliveryExpectedDate取得.
	 * @return deliveryExpectedDate
	 */
	public java.sql.Timestamp getDeliveryExpectedDate() {
		return this.deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDate設定.
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(
			final java.sql.Timestamp deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedTime取得.
	 * @return deliveryExpectedTime
	 */
	public String getDeliveryExpectedTime() {
		return this.deliveryExpectedTime;
	}

	/**
	 * deliveryExpectedTime設定.
	 * @param deliveryExpectedTime deliveryExpectedTime
	 */
	public void setDeliveryExpectedTime(final String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * customerOrderNo取得.
	 * @return customerOrderNo
	 */
	public String getCustomerOrderNo() {
		return this.customerOrderNo;
	}

	/**
	 * customerOrderNo設定.
	 * @param customerOrderNo customerOrderNo
	 */
	public void setCustomerOrderNo(final String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	/**
	 * deliverySlipSummery取得.
	 * @return deliverySlipSummery
	 */
	public String getDeliverySlipSummery() {
		return this.deliverySlipSummery;
	}

	/**
	 * deliverySlipSummery設定.
	 * @param deliverySlipSummery deliverySlipSummery
	 */
	public void setDeliverySlipSummery(final String deliverySlipSummery) {
		this.deliverySlipSummery = deliverySlipSummery;
	}

	/**
	 * orderSummery取得.
	 * @return orderSummery
	 */
	public String getOrderSummery() {
		return this.orderSummery;
	}

	/**
	 * orderSummery設定.
	 * @param orderSummery orderSummery
	 */
	public void setOrderSummery(final String orderSummery) {
		this.orderSummery = orderSummery;
	}

	/**
	 * アップロード ファイル名称を取得します。
	 * @return uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}

	/**
	 * アップロード ファイル名称を設定します。
	 * @param uploadFile アップロード ファイル名称
	 */
	public void setUploadFile(final FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * dirtyFlg取得.
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlg設定.
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * newFlg取得.
	 * @return newFlg
	 */
	public int getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlg設定.
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final int newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * strDeliveryExpectedDate取得.
	 * @return strDeliveryExpectedDate
	 */
	public String getStrDeliveryExpectedDate() {
		return strDeliveryExpectedDate;
	}

	/**
	 * strDeliveryExpectedDate設定.
	 * @param strDeliveryExpectedDate strDeliveryExpectedDate
	 */
	public void setStrDeliveryExpectedDate(final String strDeliveryExpectedDate) {
		this.strDeliveryExpectedDate = strDeliveryExpectedDate;
	}

	/**
	 * strOrderDate取得.
	 * @return strOrderDate
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * strOrderDate設定.
	 * @param strOrderDate strOrderDate
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * strScheduledShippingDate取得.
	 * @return strScheduledShippingDate
	 */
	public String getStrScheduledShippingDate() {
		return strScheduledShippingDate;
	}

	/**
	 * strScheduledShippingDate設定.
	 * @param strScheduledShippingDate strScheduledShippingDate
	 */
	public void setStrScheduledShippingDate(
			final String strScheduledShippingDate) {
		this.strScheduledShippingDate = strScheduledShippingDate;
	}

	/**
	 * strSuggestedDeliverlimit取得.
	 * @return strSuggestedDeliverlimit
	 */
	public String getStrSuggestedDeliverlimit() {
		return strSuggestedDeliverlimit;
	}

	/**
	 * strSuggestedDeliverlimit設定.
	 * @param strSuggestedDeliverlimit strSuggestedDeliverlimit
	 */
	public void setStrSuggestedDeliverlimit(
			final String strSuggestedDeliverlimit) {
		this.strSuggestedDeliverlimit = strSuggestedDeliverlimit;
	}

	/**
	 * orderDetailList取得.
	 * @return orderDetailList
	 */
	public List<OrderDetailList> getOrderDetailList() {
		return orderDetailList;
	}

	/**
	 * orderDetailList設定.
	 * @param orderDetailList orderDetailList
	 */
	public void setOrderDetailList(final List<OrderDetailList> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	/**
	 * orderDetailListOld取得.
	 * @return orderDetailListOld
	 */
	public List<OrderDetailList> getOrderDetailListOld() {
		return orderDetailListOld;
	}

	/**
	 * orderDetailListOld設定.
	 * @param orderDetailListOld orderDetailListOld
	 */
	public void setOrderDetailListOld(
			final List<OrderDetailList> orderDetailListOld) {
		this.orderDetailListOld = orderDetailListOld;
	}

	/**
	 * orderDetailListCount取得.
	 * @return orderDetailListCount
	 */
	public int getOrderDetailListCount() {
		return orderDetailListCount;
	}

	/**
	 * orderDetailListCount設定.
	 * @param orderDetailListCount orderDetailListCount
	 */
	public void setOrderDetailListCount(final int orderDetailListCount) {
		this.orderDetailListCount = orderDetailListCount;
	}

	/**
	 * 得意先リストを取得します。
	 * @return orderDetailVenderList
	 */
	public List<OrderDetailVenderList> getOrderDetailVenderList() {
		return orderDetailVenderList;
	}

	/**
	 * 得意先リストを設定します。
	 * @param orderDetailVenderList 得意先リスト
	 */
	public void setOrderDetailVenderList(
			final List<OrderDetailVenderList> orderDetailVenderList) {
		this.orderDetailVenderList = orderDetailVenderList;
	}

	/**
	 * 運送会社コンボボックスを取得します。
	 * @return carryCombo
	 */
	public List<ComboBoxItems> getCarryCombo() {
		return carryCombo;
	}

	/**
	 * 運送会社コンボボックスを設定します。
	 * @param carryCombo 運送会社コンボボックス
	 */
	public void setCarryCombo(final List<ComboBoxItems> carryCombo) {
		this.carryCombo = carryCombo;
	}

	/**
	 * 得意先リスト件数を取得します。
	 * @return Length
	 */
	public int getOrderDetailVenderListCount() {
		int i = 0;

		if (orderDetailVenderList != null) {
			i = orderDetailVenderList.size();
		}
		return i;
	}

	/**
	 * getVaridUnitpriceRow取得.
	 * @return getVaridUnitpriceRow
	 */
	public int getVaridUnitpriceRow() {
		return varidUnitpriceRow;
	}

	/**
	 * getVaridUnitpriceRow設定.
	 * @param getVaridUnitpriceRow getVaridUnitpriceRow
	 */
	public void setVaridUnitpriceRow(final int getVaridUnitpriceRow) {
		this.varidUnitpriceRow = getVaridUnitpriceRow;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * 受注区分コンボボックスを取得します。
	 * @return orderDivisionCombo
	 */
	public List<ComboBoxItems> getOrderDivisionCombo() {
		return orderDivisionCombo;
	}

	/**
	 * 受注区分コンボボックスを設定します。
	 * @param orderDivisionCombo 受注区分コンボボックス
	 */
	public void setOrderDivisionCombo(
			final List<ComboBoxItems> orderDivisionCombo) {
		this.orderDivisionCombo = orderDivisionCombo;
	}

	/**
	 * リードタイム(Strin)を取得します。
	 * @return strLeadTime
	 */
	public String getStrLeadTime() {
		return strLeadTime;
	}

	/**
	 * リードタイム(Strin)を設定します。
	 * @param strLeadTime リードタイム(Strin)
	 */
	public void setStrLeadTime(final String strLeadTime) {
		this.strLeadTime = strLeadTime;
	}

	/**
	 * 運賃(string)を取得します。
	 * @return strCarryFare
	 */
	public String getStrCarryFare() {
		return strCarryFare;
	}

	/**
	 * 運賃(string)を設定します。
	 * @param strCarryFare 運賃(string)
	 */
	public void setStrCarryFare(final String strCarryFare) {
		this.strCarryFare = strCarryFare;
	}

	/**
	 * 小数点桁数(運賃)取得
	 * @return String 小数点桁数(運賃)
	 */
	public String getDecimalPointUntin() {
		return decimalPointUntin;
	}

	/**
	 * 小数点桁数(運賃)設定
	 * @param decimalPointUntin 小数点桁数(運賃)
	 */
	public void setDecimalPointUntin(final String decimalPointUntin) {
		this.decimalPointUntin = decimalPointUntin;
	}

	/**
	 * 端数区分(運賃)取得
	 * @return String 端数区分(運賃)
	 */
	public String getRoundUntin() {
		return roundUntin;
	}

	/**
	 * 端数区分(運賃)設定
	 * @param roundUntin 端数区分(運賃)
	 */
	public void setRoundUntin(final String roundUntin) {
		this.roundUntin = roundUntin;
	}

	/**
	 * 区分(運賃)を取得します。
	 * @return unitDivisionUntin
	 */
	public String getUnitDivisionUntin() {
		return unitDivisionUntin;
	}

	/**
	 * 区分(運賃)を設定します。
	 * @param unitDivisionUntin 区分(運賃)
	 */
	public void setUnitDivisionUntin(final String unitDivisionUntin) {
		this.unitDivisionUntin = unitDivisionUntin;
	}

	/**
	 * 小数点桁数(URKINGAKU)取得
	 * @return String 小数点桁数(URKINGAKU)
	 */
	public String getDecimalPointUrKingaku() {
		return decimalPointUrKingaku;
	}

	/**
	 * 小数点桁数(URKINGAKU)設定
	 * @param decimalPointUrKingaku 小数点桁数(URKINGAKU)
	 */
	public void setDecimalPointUrKingaku(final String decimalPointUrKingaku) {
		this.decimalPointUrKingaku = decimalPointUrKingaku;
	}

	/**
	 * 端数区分(URKINGAKU)取得
	 * @return String 端数区分(URKINGAKU)
	 */
	public String getRoundUrKingaku() {
		return roundUrKingaku;
	}

	/**
	 * 端数区分(URKINGAKU)設定
	 * @param roundUrKingaku 端数区分(URKINGAKU)
	 */
	public void setRoundUrKingaku(final String roundUrKingaku) {
		this.roundUrKingaku = roundUrKingaku;
	}

	/**
	 * 変更不可 0:変更可 1:変更不可を取得します。
	 * @return updateFlg
	 */
	public int getUpdateFlg() {
		return updateFlg;
	}

	/**
	 * 変更不可 0:変更可 1:変更不可を設定します。
	 * @param updateFlg 変更不可 0:変更可 1:変更不可
	 */
	public void setUpdateFlg(final int updateFlg) {
		this.updateFlg = updateFlg;
	}

	/**
	 * 営業担当者コードを取得します。
	 * @return eigyoTantoCd
	 */
	public String getEigyoTantoCd() {
		return eigyoTantoCd;
	}

	/**
	 * 営業担当者コードを設定します。
	 * @param eigyoTantoCd 営業担当者コード
	 */
	public void setEigyoTantoCd(final String eigyoTantoCd) {
		this.eigyoTantoCd = eigyoTantoCd;
	}

	/**
	 * 営業担当者名を取得します。
	 * @return eigyoTantoName
	 */
	public String getEigyoTantoName() {
		return eigyoTantoName;
	}

	/**
	 * 営業担当者名を設定します。
	 * @param eigyoTantoName 営業担当者名
	 */
	public void setEigyoTantoName(final String eigyoTantoName) {
		this.eigyoTantoName = eigyoTantoName;
	}

	/**
	 * 注文書画像のファイル名を取得します。
	 * @return orderPicture
	 */
	public String getOrderPicture() {
		return orderPicture;
	}

	/**
	 * 注文書画像のファイル名を設定します。
	 * @param orderPicture 注文書画像のファイル名
	 */
	public void setOrderPicture(final String orderPicture) {
		this.orderPicture = orderPicture;
	}

	/**
	 * 行削除リストを取得します。
	 * @return delFormList
	 */
	public List<OrderDetailList> getDelFormList() {
		return delFormList;
	}

	/**
	 * 行削除リストを設定します。
	 * @param delFormList 行削除リスト
	 */
	public void setDelFormList(final List<OrderDetailList> delFormList) {
		this.delFormList = delFormList;
	}

	/**
	 * downloadFlgを取得します。
	 * @return downloadFlg
	 */
	public Boolean getDownloadFlg() {
		return downloadFlg;
	}

	/**
	 * downloadFlgを設定します。
	 * @param downloadFlg downloadFlg
	 */
	public void setDownloadFlg(final Boolean downloadFlg) {
		this.downloadFlg = downloadFlg;
	}

	/**
	 * 小数点桁数(SONOTA)を取得します。
	 * @return decimalPointSonota
	 */
	public String getDecimalPointSonota() {
		return decimalPointSonota;
	}

	/**
	 * 小数点桁数(SONOTA)を設定します。
	 * @param decimalPointSonota 小数点桁数(SONOTA)
	 */
	public void setDecimalPointSonota(final String decimalPointSonota) {
		this.decimalPointSonota = decimalPointSonota;
	}

	/**
	 * 端数区分(SONOTA)を取得します。
	 * @return roundSonota
	 */
	public String getRoundSonota() {
		return roundSonota;
	}

	/**
	 * 端数区分(SONOTA)を設定します。
	 * @param roundSonota 端数区分(SONOTA)
	 */
	public void setRoundSonota(final String roundSonota) {
		this.roundSonota = roundSonota;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * tempCarryCdを取得します。
	 * @return tempCarryCd
	 */
	public String getTempCarryCd() {
		return tempCarryCd;
	}

	/**
	 * tempCarryCdを設定します。
	 * @param tempCarryCd tempCarryCd
	 */
	public void setTempCarryCd(final String tempCarryCd) {
		this.tempCarryCd = tempCarryCd;
	}

	/**
	 * cursorを取得します。
	 * @return cursor
	 */
	public String getCursor() {
		return cursor;
	}

	/**
	 * cursorを設定します。
	 * @param cursor cursor
	 */
	public void setCursor(final String cursor) {
		this.cursor = cursor;
	}

	/**
	 * cursorIndexを取得します。
	 * @return cursorIndex
	 */
	public String getCursorIndex() {
		return cursorIndex;
	}

	/**
	 * cursorIndexを設定します。
	 * @param cursorIndex cursorIndex
	 */
	public void setCursorIndex(final String cursorIndex) {
		this.cursorIndex = cursorIndex;
	}

	/**
	 * decimalPointUrTankaを取得します。
	 * @return decimalPointUrTanka
	 */
	public String getDecimalPointUrTanka() {
		return decimalPointUrTanka;
	}

	/**
	 * decimalPointUrTankaを設定します。
	 * @param decimalPointUrTanka decimalPointUrTanka
	 */
	public void setDecimalPointUrTanka(final String decimalPointUrTanka) {
		this.decimalPointUrTanka = decimalPointUrTanka;
	}

	/**
	 * deliverySlipSummeryDispを取得します。
	 * @return deliverySlipSummeryDisp
	 */
	public String getDeliverySlipSummeryDisp() {
		return deliverySlipSummeryDisp;
	}

	/**
	 * deliverySlipSummeryDispを設定します。
	 * @param deliverySlipSummeryDisp deliverySlipSummeryDisp
	 */
	public void setDeliverySlipSummeryDisp(final String deliverySlipSummeryDisp) {
		this.deliverySlipSummeryDisp = deliverySlipSummeryDisp;
	}

	/**
	 * printSummeryを取得します。
	 * @return printSummery
	 */
	public String getPrintSummery() {
		return printSummery;
	}

	/**
	 * printSummeryを設定します。
	 * @param printSummery printSummery
	 */
	public void setPrintSummery(String printSummery) {
		this.printSummery = printSummery;
	}
}
