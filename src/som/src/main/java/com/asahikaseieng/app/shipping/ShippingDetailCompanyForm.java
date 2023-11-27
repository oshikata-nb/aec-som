/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyEntity;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailList;

/**
 * 出荷指図詳細 Formクラス.
 * @author tosco
 */
public final class ShippingDetailCompanyForm extends AbstractShippingDetailForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド

	/** 納入先宛先 */
	private String deliveryAddress;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 指図量累計(文字列) */
	private String strShippingInstructionSum;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位区分 */
	private String unitDivision;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String round;

	/** 帳合コード */
	private String balanceCd;

	/** 納入予定日 */
	private String strDeliveryExpectedDate;

	/** 受注ヘッダ更新日時 */
	private String strUpdateDateOrderHead;

	/** 受注詳細更新日時 */
	private String strUpdateDateOrderDetail;

	/** 前回入力した受注番号 */
	private String orderNoPrev;

	/** 前回入力した行番号(受注) */
	private String orderRowNoPrev;

	/** 前回入力した受注番号の受注ヘッダ更新日時 */
	private String strUpdateDateOrderHeadPrev;

	/** 前回入力した受注番号の受注詳細更新日時 */
	private String strUpdateDateOrderDetailPrev;

	/** 受注数量(文字列) */
	private String strOrderQty;

	/** 増付数(文字列) */
	private String strMatss;

	/** 希望納期(文字列) */
	private String strSuggestedDeliverlimit;

	/** 出荷予定日(文字列) */
	private String strScheduledShippingDate;

	/** 出荷指図ヘッダ検索結果 */
	private ShippingDetailCompanyEntity shippingBean;

	/** 出荷指図詳細リスト */
	private List<ShippingDetailCompanyList> detailList;

	/** 検索入力：受注番号 */
	private String srhOrderNo;

	/** 検索入力：受注区分 */
	private String srhOrderDivision;

	/** 検索入力：出荷予定日FROM */
	private String srhScheduledShippingDateFrom;

	/** 検索入力：出荷予定日TO */
	private String srhScheduledShippingDateTo;

	/** 検索入力：納入先コード */
	private String srhDeliveryCd;

	/** 検索入力：納入先名称 */
	private String srhDeliveryName1;

	/** 検索入力：得意先コード */
	private String srhVenderCd;

	/** 検索入力：取引先名称 */
	private String srhVenderName1;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：他社コード1 */
	private String srhOtherCompanyCd1;

	/** 検索入力：荷姿 */
	private String srhStyleOfPacking;

	/**
	 * コンストラクタ.出荷指図詳細（花王・その他）
	 */
	public ShippingDetailCompanyForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
			// } else if ("initNew".equals(getOp())) {
			// clear();
		} else if ("addRow".equals(getOp())) {
			clearCheck();
		} else if ("delRow".equals(getOp())) {
			clearCheck();
		} else if ("insert".equals(getOp())) {
			clearCheck();
		} else if ("update".equals(getOp())) {
			clearCheck();
		}

	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 明細のチェック
			validateDetail(errors, request);
		} else if ("addRow".equals(getOp())) {
			// 行追加時のチェック
			errors = new ActionErrors();
			this.validateAddRow(errors, request);
		} else if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 明細のチェック
			validateDetail(errors, request);
		} else if ("initNew".equals(getOp())) {
			/* イレギュラーだけど、ここでフォームをクリア */
			this.clear();
		} else if ("initContinue".equals(getOp())) {
			/* イレギュラーだけど、ここでフォームをクリア */
			this.clearNew();
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	protected void clearNew() {
		super.clear();
		/** 納入先宛先 */
		setDeliveryAddress(null);

		/** 他社コード1 */
		setOtherCompanyCd1(null);

		/** 指図量累計(文字列) */
		setStrShippingInstructionSum(null);

		/** 荷姿 */
		setStyleOfPacking(null);

		/** 単位区分 */
		setUnitDivision(null);

		/** 小数点桁数 */
		setDecimalPoint(null);

		/** 端数区分 */
		setRound(null);

		/** 帳合コード */
		setBalanceCd(null);

		/** 受注数量(文字列) */
		setStrOrderQty(null);

		/** 増付数(文字列) */
		setStrMatss(null);

		/** 希望納期(文字列) */
		setStrSuggestedDeliverlimit(null);

		/** 出荷予定日(文字列) */
		setStrScheduledShippingDate(null);

		/** 受注ヘッダ更新日時 */
		setStrUpdateDateOrderHead(null);

		/** 受注詳細更新日時 */
		setStrUpdateDateOrderDetail(null);

		/** 前回入力した受注番号 */
		setOrderNoPrev(null);

		/** 前回入力した行番号(受注) */
		setOrderRowNoPrev(null);

		/** 前回入力した受注番号の受注ヘッダ更新日時 */
		setStrUpdateDateOrderHeadPrev(null);

		/** 前回入力した受注番号の受注詳細日時 */
		setStrUpdateDateOrderDetailPrev(null);

		/** 出荷指図ヘッダ検索結果 */
		setShippingBean(new ShippingDetailCompanyEntity());

		/** 詳細情報リストのクリア */
		setDetailList(new ArrayList<ShippingDetailCompanyList>());

		// 納入予定日
		setStrDeliveryExpectedDate(null);

	}

	/**
	 * 入力データ（詳細情報リスト）の検証（更新時）
	 * 
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	private void validateDetail(final ActionErrors errors,
			final HttpServletRequest request) {
		// 明細リスト
		List<ShippingDetailCompanyList> detailList = getDetailList();

		if (detailList == null || detailList.size() == 0) {
			// 明細が1件も存在しない場合、エラーとする
			errors.add("", new ActionMessage(
					"errors.shipping.required.detail.row", ""));
		} else {
			// 明細数分チェック
			int detailLineNo = 0;
			for (ShippingDetailList detailBean : detailList) {
				detailLineNo++;
				// ロケーションコード
				super.validateLocationCd(detailBean.getLocationCd(),
					detailLineNo, errors);
				// 指図量
				super.validateShippingInstruction(detailBean,
					getUnitDivision(), ShippingConst.VENDER_DIVISION_TS,
					getVenderCd(), getErrorDetailInfo(detailLineNo), request,
					errors);
			}
		}
	}

	/**
	 * エラーメッセージ詳細情報取得（行番号あり）
	 * @param lineNo 行番号
	 */
	private String getErrorDetailInfo(final int lineNo) {
		StringBuffer msgBuf = new StringBuffer();
		msgBuf.append(lineNo).append("行目");
		return msgBuf.toString();
	}

	/**
	 * 行追加時入力データの検証
	 * 
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	private void validateAddRow(final ActionErrors errors,
			final HttpServletRequest request) {
		// 受注コードの必須チェック
		if (StringUtils.isEmpty(getOrderNo())) {
			errors.add("", new ActionMessage("errors.shipping.required.order"));
		}
	}

	/**
	 * クリア処理.
	 */
	protected void clear() {
		super.clear();

		/** 納入先宛先 */
		setDeliveryAddress(null);

		/** 他社コード1 */
		setOtherCompanyCd1(null);

		/** 指図量累計(文字列) */
		setStrShippingInstructionSum(null);

		/** 荷姿 */
		setStyleOfPacking(null);

		/** 単位区分 */
		setUnitDivision(null);

		/** 小数点桁数 */
		setDecimalPoint(null);

		/** 端数区分 */
		setRound(null);

		/** 帳合コード */
		setBalanceCd(null);

		/** 受注数量(文字列) */
		setStrOrderQty(null);

		/** 増付数(文字列) */
		setStrMatss(null);

		/** 希望納期(文字列) */
		setStrSuggestedDeliverlimit(null);

		// 納入予定日
		setStrDeliveryExpectedDate(null);

		/** 出荷予定日(文字列) */
		setStrScheduledShippingDate(null);

		/** 受注ヘッダ更新日時 */
		setStrUpdateDateOrderHead(null);

		/** 受注詳細更新日時 */
		setStrUpdateDateOrderDetail(null);

		/** 前回入力した受注番号 */
		setOrderNoPrev(null);

		/** 前回入力した行番号(受注) */
		setOrderRowNoPrev(null);

		/** 前回入力した受注番号の受注ヘッダ更新日時 */
		setStrUpdateDateOrderHeadPrev(null);

		/** 前回入力した受注番号の受注詳細日時 */
		setStrUpdateDateOrderDetailPrev(null);

		/** 出荷指図ヘッダ検索結果 */
		setShippingBean(new ShippingDetailCompanyEntity());

		/** 詳細情報リストのクリア */
		setDetailList(new ArrayList<ShippingDetailCompanyList>());

		/** 検索入力：受注番号 */
		setSrhOrderNo(null);
		/** 検索入力：受注区分 */
		setSrhOrderDivision("0");
		/** 検索入力：出荷予定日FROM */
		setSrhScheduledShippingDateFrom(null);
		/** 検索入力：出荷予定日TO */
		setSrhScheduledShippingDateTo(null);
		/** 検索入力：取引先コード */
		setSrhVenderCd(null);
		/** 検索入力：取引先名称 */
		setSrhVenderName1(null);
		/** 検索入力：納入先コード */
		setSrhDeliveryCd(null);
		/** 検索入力：納入先名称 */
		setSrhDeliveryName1(null);
		/** 検索入力：品目コード */
		setSrhItemCd(null);
		/** 検索入力：品目名称 */
		setSrhItemName(null);
		/** 検索入力：他社コード1 */
		setSrhOtherCompanyCd1(null);
		/** 検索入力：荷姿 */
		setSrhStyleOfPacking(null);

	}

	//
	// インスタンスメソッド
	//

	/**
	 * 納入先宛先取得
	 * @return String 納入先宛先
	 */
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * 納入先宛先設定
	 * @param deliveryAddress 納入先宛先
	 */
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 単位区分
	 * @return String 単位区分
	 */
	public String getUnitDivision() {
		return this.unitDivision;
	}

	/**
	 * 単位区分
	 * @param unitDivision 単位区分
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * 小数点桁数取得
	 * @return String 小数点桁数
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数設定
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分取得
	 * @return String 端数区分
	 */
	public String getRound() {
		return round;
	}

	/**
	 * 端数区分設定
	 * @param round 端数区分
	 */
	public void setRound(final String round) {
		this.round = round;
	}

	/**
	 * 指図量累計(文字列)取得
	 * @return BigDecimal 指図量累計(文字列)
	 */
	public String getStrShippingInstructionSum() {
		return this.strShippingInstructionSum;
	}

	/**
	 * 指図量累計(文字列)設定
	 * @param strShippingInstructionSum 指図量累計(文字列)
	 */
	public void setStrShippingInstructionSum(
			final String strShippingInstructionSum) {
		this.strShippingInstructionSum = strShippingInstructionSum;
	}

	/**
	 * 帳合コード取得
	 * @return String 帳合コード
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * 帳合コード設定
	 * @param balanceCd 帳合コード
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * 受注ヘッダ更新日時(文字列)を取得します。
	 * @return String 受注ヘッダ更新日時(文字列)
	 */
	public String getStrUpdateDateOrderHead() {
		return strUpdateDateOrderHead;
	}

	/**
	 * 受注ヘッダ更新日時(文字列)を設定します。
	 * @param strUpdateDateOrderHead 受注ヘッダ更新日時(文字列)
	 */
	public void setStrUpdateDateOrderHead(final String strUpdateDateOrderHead) {
		this.strUpdateDateOrderHead = strUpdateDateOrderHead;
	}

	/**
	 * 受注詳細更新日時(文字列)取得
	 * @return String 受注詳細更新日時(文字列)
	 */
	public String getStrUpdateDateOrderDetail() {
		return this.strUpdateDateOrderDetail;
	}

	/**
	 * 受注詳細更新日時(文字列)設定
	 * @param strUpdateDateOrderDetail 受注詳細更新日時(文字列)
	 */
	public void setStrUpdateDateOrderDetail(
			final String strUpdateDateOrderDetail) {
		this.strUpdateDateOrderDetail = strUpdateDateOrderDetail;
	}

	/**
	 * 前回入力した受注番号取得
	 * @return String 前回入力した受注番号
	 */
	public String getOrderNoPrev() {
		return this.orderNoPrev;
	}

	/**
	 * 前回入力した受注番号設定
	 * @param orderNoPrev 前回入力した受注番号
	 */
	public void setOrderNoPrev(final String orderNoPrev) {
		this.orderNoPrev = orderNoPrev;
	}

	/**
	 * 前回入力した行番号(受注)取得
	 * @return String 前回入力した行番号(受注)
	 */
	public String getOrderRowNoPrev() {
		return this.orderRowNoPrev;
	}

	/**
	 * 前回入力した行番号(受注)設定
	 * @param orderRowNoPrev 前回入力した行番号(受注)
	 */
	public void setOrderRowNoPrev(final String orderRowNoPrev) {
		this.orderRowNoPrev = orderRowNoPrev;
	}

	/**
	 * 前回入力した受注番号の受注ヘッダ更新日時取得
	 * @return String 前回入力した受注番号の受注ヘッダ更新日時
	 */
	public String getStrUpdateDateOrderHeadPrev() {
		return this.strUpdateDateOrderHeadPrev;
	}

	/**
	 * 前回入力した受注番号の受注ヘッダ更新日時設定
	 * @param strUpdateDateOrderHeadPrev 前回入力した受注番号の受注ヘッダ更新日時
	 */
	public void setStrUpdateDateOrderHeadPrev(
			final String strUpdateDateOrderHeadPrev) {
		this.strUpdateDateOrderHeadPrev = strUpdateDateOrderHeadPrev;
	}

	/**
	 * 前回入力した受注番号の受注詳細更新日時取得
	 * @return Timestamp 前回入力した受注番号の受注詳細更新日時
	 */
	public String getStrUpdateDateOrderDetailPrev() {
		return this.strUpdateDateOrderDetailPrev;
	}

	/**
	 * 前回入力した受注番号の受注ヘッダー更新日時設定
	 * @param strUpdateDateOrderDetailPrev 前回入力した受注番号の受注詳細更新日時
	 */
	public void setStrUpdateDateOrderDetailPrev(
			final String strUpdateDateOrderDetailPrev) {
		this.strUpdateDateOrderDetailPrev = strUpdateDateOrderDetailPrev;
	}

	/**
	 * 希望納期(文字列)を取得します。
	 * @return String 希望納期(文字列)
	 */
	public String getStrSuggestedDeliverlimit() {
		return strSuggestedDeliverlimit;
	}

	/**
	 * 希望納期(文字列)を設定します。
	 * @param strSuggestedDeliverlimit 希望納期(文字列)
	 */
	public void setStrSuggestedDeliverlimit(
			final String strSuggestedDeliverlimit) {
		this.strSuggestedDeliverlimit = strSuggestedDeliverlimit;
	}

	/**
	 * 出荷予定日(文字列)を取得します。
	 * @return String 出荷予定日(文字列)
	 */
	public String getStrScheduledShippingDate() {
		return strScheduledShippingDate;
	}

	/**
	 * 出荷予定日(文字列)を設定します。
	 * @param strScheduledShippingDate 出荷予定日(文字列)
	 */
	public void setStrScheduledShippingDate(
			final String strScheduledShippingDate) {
		this.strScheduledShippingDate = strScheduledShippingDate;
	}

	/**
	 * 受注数量(文字列)を取得します。
	 * @return String 受注数量(文字列)
	 */
	public String getStrOrderQty() {
		return strOrderQty;
	}

	/**
	 * 受注数量(文字列)を設定します。
	 * @param strOrderQty 受注数量(文字列)
	 */
	public void setStrOrderQty(final String strOrderQty) {
		this.strOrderQty = strOrderQty;
	}

	/**
	 * 増付数(文字列)を取得します。
	 * @return String 増付数(文字列)
	 */
	public String getStrMatss() {
		return strMatss;
	}

	/**
	 * 増付数(文字列)を設定します。
	 * @param strMatss 増付数(文字列)
	 */
	public void setStrMatss(final String strMatss) {
		this.strMatss = strMatss;
	}

	/**
	 * 出荷指図ヘッダ検索結果取得.
	 * @return String 出荷指図ヘッダ検索結果
	 */
	public ShippingDetailCompanyEntity getShippingBean() {
		return this.shippingBean;
	}

	/**
	 * 出荷指図ヘッダ検索結果設定.
	 * @param shippingBean 出荷指図ヘッダ検索結果
	 */
	public void setShippingBean(final ShippingDetailCompanyEntity shippingBean) {
		this.shippingBean = shippingBean;
	}

	/**
	 * 出荷指図詳細（自社ロット）：detailListを取得します。
	 * @return itemList
	 */
	public List<ShippingDetailCompanyList> getDetailList() {
		return detailList;
	}

	/**
	 * 出荷指図詳細（自社ロット）：detailListを設定します。
	 * 
	 * @param detailList detailList
	 */
	public void setDetailList(final List<ShippingDetailCompanyList> detailList) {
		this.detailList = detailList;
	}

	/**
	 * 検索入力：受注番号
	 * @return String 受注番号
	 */
	public String getSrhOrderNo() {
		return this.srhOrderNo;
	}

	/**
	 * 検索入力：受注番号
	 * @param srhOrderNo 受注番号
	 */
	public void setSrhOrderNo(final String srhOrderNo) {
		this.srhOrderNo = srhOrderNo;
	}

	/**
	 * 検索入力：受注区分取得.
	 * @return String 受注区分
	 */
	public String getSrhOrderDivision() {
		return this.srhOrderDivision;
	}

	/**
	 * 検索入力：受注区分設定.
	 * @param srhOrderDivision 受注区分
	 */
	public void setSrhOrderDivision(final String srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * 検索入力：出荷予定日FROM取得.
	 * @return String 出荷予定日FROM
	 */
	public String getSrhScheduledShippingDateFrom() {
		return this.srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力：出荷予定日FROM設定.
	 * @param srhScheduledShippingDateFrom 出荷予定日FROM
	 */
	public void setSrhScheduledShippingDateFrom(
			final String srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力：出荷予定日TO取得.
	 * @return String 出荷予定日TO
	 */
	public String getSrhScheduledShippingDateTo() {
		return this.srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力：出荷予定日TO設定.
	 * @param srhScheduledShippingDateTo 出荷予定日TO
	 */
	public void setSrhScheduledShippingDateTo(
			final String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力：得意先コード取得.
	 * @return String 得意先コード
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力：取引先名称取得.
	 * @return String 取引先名称
	 */
	public String getSrhVenderName1() {
		return this.srhVenderName1;
	}

	/**
	 * 検索入力：取引先名称設定.
	 * @param srhVenderName1 取引先名称
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = srhVenderName1;
	}

	/**
	 * 検索入力：納入先コード取得.
	 * @return String 納入先コード
	 */
	public String getSrhDeliveryCd() {
		return this.srhDeliveryCd;
	}

	/**
	 * 検索入力：納入先コード設定.
	 * @param srhDeliveryCd 納入先コード
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = srhDeliveryCd;
	}

	/**
	 * 検索入力：納入先名称取得.
	 * @return String 納入先名称
	 */
	public String getSrhDeliveryName1() {
		return this.srhDeliveryName1;
	}

	/**
	 * 検索入力：納入先名称設定.
	 * @param srhDeliveryName1 納入先名称
	 */
	public void setSrhDeliveryName1(final String srhDeliveryName1) {
		this.srhDeliveryName1 = srhDeliveryName1;
	}

	/**
	 * 検索入力：得意先コード設定.
	 * @param srhVenderCd 得意先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 品目コード設定
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return this.srhItemName;
	}

	/**
	 * 品目名称設定
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param srhOtherCompanyCd1 他社コード1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getSrhStyleOfPacking() {
		return this.srhStyleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param srhStyleOfPacking 荷姿
	 */
	public void setSrhStyleOfPacking(final String srhStyleOfPacking) {
		this.srhStyleOfPacking = srhStyleOfPacking;
	}

	/**
	 * 詳細データ数を取得する。
	 * @return 詳細データ数
	 */
	public int getDetailCount() {
		int count = 0;
		if (detailList != null) {
			count = detailList.size();
		}
		return count;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getDetailList() != null) {
			for (ShippingDetailList detail : getDetailList()) {
				detail.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	/**
	 * 納入予定日取得
	 * @return String 納入予定日
	 */
	public String getStrDeliveryExpectedDate() {
		return strDeliveryExpectedDate;
	}

	/**
	 * 納入予定日設定
	 * @param strDeliveryExpectedDate 納入予定日
	 */
	public void setStrDeliveryExpectedDate(final String strDeliveryExpectedDate) {
		this.strDeliveryExpectedDate = strDeliveryExpectedDate;
	}
}
