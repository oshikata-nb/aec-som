/*
 * Created on 2020/09/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

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
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderList;
import com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 受注取込詳細 Formクラス.
 * @author
 */
public final class OrderImportDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;
	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);
		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	/** 受注取込詳細リスト これはよく使う為一番上にあると便利 */
	private List<OrderImportDetailList> orderImportDetailList;

	/** 詳細画面表示時の受注取込テーブルデータ これを元に修正前との比較をする*/
	private List<OrderImportDetailList> beforeOrderImportDetailList;
	
	/** 詳細画面表示時の受注取込テーブルデータ これを元に修正前との比較をする*/
	private FrstOrderHead beforeFrstOrderHead;

	/** 競合した受注詳細画面のデータ*/
	private List<FrstOrderHead> conflictOrderHeadList;

	/** 競合した受注詳細画面のデータ*/
	private List<OrderImportDetailList> conflictOrderImportDetailList;

	/** 競合した受注取込番号 */
	private String conflictFrstOrderNo;

	/**
	 * conflictFrstOrderNoを取得します。
	 * @return conflictFrstOrderNo
	 */
	public String getConflictFrstOrderNo() {
		return conflictFrstOrderNo;
	}

	/**
	 * conflictFrstOrderNoを設定します。
	 * @param conflictFrstOrderNo conflictFrstOrderNo
	 */
	public void setConflictFrstOrderNo(String conflictFrstOrderNo) {
		this.conflictFrstOrderNo = conflictFrstOrderNo;
	}

	/** 受注取込番号 */
	private String frstOrderNo;

	/** 受注取込番号連番 */
	private BigDecimal frstOrderRow;

	/** 画面の並び順*/
	private String seq;

	/** 受注番号 */
	private String orderNo;

	/** 受注区分 */
	private String orderDivision;

	/** 得意先グループコード */
	private String venderGroupCd;

	/** 得意先グループ名 */
	private String venderGroupName;

	/** エラー状態*/
	private String errorStatus;

	/** 受注日 */
	private String orderDate;

	/** 客先の注文日 */
	private String ctmOrderDate;

	/** 取込日 */
	private String importDate;

	/** 納入先コード */
	private String deliveryCd;

	/** 納入先名 */
	private String deliveryName;

	/**　納入先区分*/
	private String deliveryDivision;

	/** 客先納入先コード */
	private String ctmDeliveryCd;

	/** 客先納入先名 */
	private String ctmDeliveryName;

	/** 納入先住所(1+2+3) */
	private String address;

	/** 客先納入先住所(1+2+3) */
	private String ctmAddress;

	/** 納入先宛先(〇〇様等) */
	private String deliveryAddress;

	/** 客先納入先宛先(〇〇様等) */
	private String ctmDeliveryAddress;

	/** 納入先電話番号 */
	private String deliveryTelNo;

	/** 客先納入先電話番号 */
	private String ctmDeliveryTelNo;

	/** 出荷予定日 */
	private String scheduledShippingDate;

	/** リードタイム */
	private String leadTime;

	/** 客先備考01 */
	private String ctmRemark01;

	/** 客先備考02 */
	private String ctmRemark02;

	/** 客先備考03 */
	private String ctmRemark03;

	/** 運送会社コード */
	private String carryCd;

	/** 納入予定日 */
	private String deliveryExpectedDate;

	/** 納入予定時刻 */
	private String deliveryExpectedTime;

	/** 希望納期 */
	private String suggestedDeliverlimit;

	/** 品目コード*/
	private String itemCd;

	/** 合計金額 */
	private String totalOrderAmount;

	/** 合計個数 */
	private String totalQty;

	/** 合計重量 */
	private String totalWeight;

	/** 帳合コード */
	private String balanceCd;

	/** 担当部署コード */
	private String organizationCd;

	/** 担当部署名称 */
	private String organizationName;

	/** 得意先コード*/
	private String venderCd;

	/** 得意先名称 */
	private String venderName;

	/** 客先得意先名称 */
	private String ctmVenderName;

	/** 営業担当者コード */
	private String salesTantoCd;
	//20210526 add 担当者名表示追加 S.Fujimaki
	/** 営業担当者名 */
	private String salesTantoName;
	//20210526 add 担当者名表示追加 S.Fujimaki

	/** 運賃 */
	private String carryFare;

	/** 計算運賃 */
	private String calcCarryFare;

	/** 運賃請求フラグ */
	private BigDecimal carryInvoiceFlag;

	/** 運賃請求フラグ */
	private Boolean blnCarryInvoiceFlag;

	/** 新規備考 */
	private String printSummery;

	/** 自動表示備考 */
	private String deliverySlipSummery;

	/** 注文書画像のファイル名 */
	private String orderPicture;

	/** 自動表示備考 */
	private String orderSummery;

	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	/** 納期連絡表専用備考 */
	private String deliverydateContactSummery;

	/** 客先備考 */
	private String ctmRemark;

	/** 注文書画像 */
	private FormFile uploadFile;

	/** 品目情報リスト件数 */
	private int orderImportDetailListCount;

	/** 品目情報リスト件数(詳細画面遷移時)*/
	private int beforeOrderImportDetailListCount;

	/** 得意先リスト */
	private List<OrderDetailVenderList> orderDetailVenderList;

	/** 得意先リスト件数 */
	private int orderDetailVenderListCount;

	/** ダウンロードフラグ */
	private Boolean downloadFlg;

	/** 変更フラグ */
	private Boolean dirtyFlg;

	/** 登録フラグ 0:更新 1:新規 */
	private int insertFlg;

	/** 受注区分コンボボックス */
	private List<ComboBoxItems> orderDivisionCombo;

	/** 運送会社コンボボックス */
	private List<ComboBoxItems> carryCombo;

	/** 運送会社コンボボックス */
	private List<ComboBoxItems> venderGroupCombo;

	/** カーソル位置 */
	private String cursor;

	/** 仮単価化ボタン選択行保存用*/
	private String line;

	/** 受注キャンセル変更可フラグ 1:変更可 0:変更不可 */
	private int orderCancelButtonEnableFlg;

	/** 受注非表示変更可フラグ 1:変更可 0:変更不可 */
	private int orderInvisibleButtonEnableFlg;


	/** 確定時に既存受注の運賃が変更されたことを確認するフラグ　0:変更無　1:変更有*/
	private int difCarryFareFlg;

	/** 確定時に既存受注の備考が変更されたことを確認するフラグ　0:変更無　1:変更有*/
	private int difRemarkFlg;

	/** DB帳合コード(確定時に変更されているか比較用)*/
	private String dbBalanceCd;

	/** DB得意先コード(確定時に変更されているか比較用)*/
	private String dbVenderCd;

	/** 同じ受注グループの確定済受注の客先注文番号 */
	private String sameCtmOrderNo;

	/** 小数点桁数(URTANKA) */
	private String smallnumLengthUrTanka;

	/** 端数区分(URTANKA) */
	private String roundDivisionUrTanka;

	/** 小数点桁数(運賃) */
	private String smallnumLengthCarryFare;

	/** 端数区分(運賃) */
	private String roundDivisionCarryFare;

	/** 区分(運賃) */
	private String unitDivisionCarryFare;

	/** 小数点桁数(URKINGAKU) */
	private String smallnumLengthUrKingaku;

	/** 端数区分(URKINGAKU) */
	private String roundDivisionUrKingaku;

	/** 小数点桁数(合計個数) */
	private String smallnumLengthTotalNum;

	/** 端数区分(合計個数) */
	private String roundDivisionTotalNum;

	/** 小数点桁数(合計重量) */
	private String smallnumLengthTotalWeight;

	/** 端数区分(合計重量) */
	private String roundDivisionTotalWeight;

	/** 小数点桁数(SONOTA) */
	private String smallnumLengthSonota;

	/** 端数区分(SONOTA) */
	private String roundDivisionSonota;

	/**  */
	private int varidUnitpriceRow;

	/** 警告表示数量 */
	private BigDecimal checkQty;

	/** 警告表示金額 */
	private BigDecimal checkOrderAmount;

	/** 削除フラグ　0:有効　1:削除済み*/
	private int delFlg;

	/** キャンセルフラグ　0:有効　1:キャンセル済み*/
	private int cancelFlg;

	/** 確定フラグ　0:先付け　1:確定済み*/
	private int fixedFlg;

	/** 取込ステータス */
	private String importStatus;

	/** 客先注文番号 */
	private String headCtmOrderNo;

	/** 再読み込みフラグ */
	private int reloadFlg;

	/** キャンセルフラグ　0:有効　1:キャンセル済み*/
	private boolean fareCalcFlg;

	/** スクロールフラグ */
	private int scrollFlg;

	private static Log log = LogFactory.getLog(ErrorAction.class);

	/** 先付け受注ヘッダ情報 */
	private FrstOrderHead frstOrderHeadBean;

	/**
	 * コンストラクタ.
	 */
	public OrderImportDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * 仕入れ直送品の場合、客先希望納期のチェックを行い、それ以外の場合チェックをしないため固定値を返す
	 * @return
	 */
	public String getCtmDeliveryLimitDirect(){
		if(this.getOrderDivision().equals("3")){
			return this.getSuggestedDeliverlimit();
		}else{
			return "2020/01/01";
		}
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

		// 新規ボタン
		if ("newPage".equals(getOp())) {
			clear();
		}

		// 行削除ボタン
		if ("dellist".equals(getOp()) || "unspecified".equals(getOp())) {
			clearCheck();
		}

		setDirtyFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		log.error("CHECK_OP:  " + getOp());
		ActionErrors errors = null;

		try{
			/* Validatorによる判定 */
			if ("regist".equals(getOp()) || "fix".equals(getOp()) || "fixedOrderUpdate".equals(getOp())) {
				errors = super.validate(mapping, request);

				// validateメソッドによる入力チェック
				validateInsertList(request, errors);
			}

		}catch( IllegalArgumentException ex ){
			throw ex;
		}

		return errors;
	}

	/**
	 * 更新処理の入力チェック (品目の重複チェック)
	 * @param request HttpServletRequest
	 * @param errors エラー内容
	 */
	private void validateInsertList(final HttpServletRequest request, final ActionErrors errors) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		List<OrderImportDetailList> detailList = getOrderImportDetailList();
		HashMap<String, String> directionMap = new HashMap<String, String>();
		boolean errorFlag = false;

		if (detailList != null) {
			for (int i = 0; i < detailList.size(); i++) {
				OrderImportDetailList detailBean = detailList.get(i);

				// キャンセルか削除の場合、チェック対象としない。
				if( detailBean.getDelFlg().intValue() == 1 || detailBean.getCancelFlg().intValue() == 1 ){
					continue;
				}

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
			errors.add("", new ActionMessage("errors.order.duplication", rb.getString("item.order.item.cd")));
		}

	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		/** 受注取込明細リスト*/
		setOrderImportDetailList(new ArrayList<OrderImportDetailList>());
		setOrderImportDetailListCount(0);
		/** 受注取込番号 */
		setFrstOrderNo(null);
		/** 受注取込番号連番 */
		setFrstOrderRow(null);
		/** 画面の並び順*/
		setSeq(null);
		/** 受注番号 */
		setOrderNo(null);
		/** 受注区分 */
		setOrderDivision(null);
		/** 受注グループ番号 */
		setFrstOrderNo(null);
		/** 得意先グループコード 99999:設定なし*/
		setVenderGroupCd("99999");
		/** 得意先グループ名 */
		setVenderGroupName(null);
		/** エラー状態*/
		setErrorStatus(null);
		/** 受注日 */
		setOrderDate(null);
		/** 客先の注文日 */
		setCtmOrderDate(null);
		/** 取込日 */
		setImportDate(null);
		/** 納入先コード */
		setDeliveryCd(null);
		/** 納入先名 */
		setDeliveryName(null);
		/** 客先納入先コード */
		setCtmDeliveryCd(null);
		//20210526 add S.Fujimaki　営業担当者名表示
		/** 営業担当者氏名 */
		setSalesTantoName(null);
		//20210526 add S.Fujimaki　営業担当者名表示
		/** 客先納入先名 */
		setCtmDeliveryName(null);
		/** 納入先住所(１＋２＋３) */
		setAddress(null);
		/** 客先納入先住所 */
		setCtmAddress(null);
		/** 納入先宛先(〇〇様等) */
		setDeliveryAddress(null);
		/** 客先納入先宛先(〇〇様等) */
		setCtmDeliveryAddress(null);
		/** 納入先電話番号 */
		setDeliveryTelNo(null);
		/** 客先納入先電話番号 */
		setCtmDeliveryTelNo(null);
		/** 出荷予定日 */
		setScheduledShippingDate(null);
		/** リードタイム */
		setLeadTime(null);
		/** 備考01 */
		setCtmRemark01(null);
		/** 備考02 */
		setCtmRemark02(null);
		/** 備考03 */
		setCtmRemark03(null);
		/** 運送会社コード */
		setCarryCd(null);
		/** 納入予定日 */
		setDeliveryExpectedDate(null);
		/** 納入予定時刻 */
		setDeliveryExpectedTime(null);
		/** 希望納期 */
		setSuggestedDeliverlimit(null);
		/** 品目コード*/
		setItemCd(null);
		/** 全体合計金額 */
		setTotalOrderAmount(null);
		/** 合計個数 */
		setTotalQty(null);
		/** 合計重量 */
		setTotalWeight(null);
		/** 帳合コード */
		setBalanceCd(null);
		/** 担当部署コード */
		setOrganizationCd(null);
		/** 担当部署名称 */
		setOrganizationName(null);
		/** 得意先コード */
		setVenderCd(null);
		/** 得意先名称 */
		setVenderName(null);
		/** 客先得意先名称 */
		setCtmVenderName(null);
		/** 運賃 */
		setCarryFare(null);
		/** 運賃請求フラグ */
		setCarryInvoiceFlag(BigDecimal.ZERO);
		/** 運賃請求フラグ */
		setBlnCarryInvoiceFlag(false);
		/** 新規備考 */
		setPrintSummery(null);
		/** 自動表示備考 */
		setDeliverySlipSummery(null);
		/** 注文書画像のファイル名 */
		setOrderPicture(null);
		/** 自動表示備考 */
		setOrderSummery(null);
		// 20210906 Asclab Saita 納期連絡表専用備考追加対応
		/** 納期連絡表専用備考 */
		setDeliverydateContactSummery(null);
		/** 注文書画像 */
		setUploadFile(null);
		/** 客先備考 */
		setCtmRemark(null);
		/** ダウンロードフラグ */
		setDownloadFlg(false);
		/** 削除フラグ*/
		this.setDelFlg(0);
		/** 削除フラグ*/
		this.setCancelFlg(0);
		/** 得意先リスト */
		setOrderDetailVenderList(new ArrayList<OrderDetailVenderList>());
		/** カーソル位置 */
		setCursor(null);
		/** 仮単価ボタン選択行*/
		setLine(null);
		/** 変更可フラグ*/
		setOrderCancelButtonEnableFlg(0);
		/** 非表示可フラグ*/
		setOrderInvisibleButtonEnableFlg(0);
		/** 確定時運賃変更確認フラグ*/
		setDifCarryFareFlg(0);
		/** 確定時備考変更確認フラグ*/
		setDifRemarkFlg(0);
		/** DB得意先コード*/
		setDbVenderCd(null);
		/** DB帳合コード*/
		setDbBalanceCd(null);
		/** 同じ受注グループの確定済受注の客先注文番号 */
		setSameCtmOrderNo(null);
		/** 小数点桁数(URTANKA) */
		setSmallnumLengthUrTanka(null);
		/** 確定フラグ */
		this.setFixedFlg(0);
		/** 小数点桁数(URTANKA) */
		this.setImportStatus(null);
		this.setHeadCtmOrderNo(null);
		this.setDelFlg(0);
		this.setCancelFlg(0);
		this.setFareCalcFlg(false);
		// スクロールフラグ:0
		this.setScrollFlg(0);

		if( this.getReloadFlg() == 1 )
		{
			this.setReloadFlg(0);
		}else{
			this.setConflictOrderHeadList( new ArrayList<FrstOrderHead>() );
			this.setConflictOrderImportDetailList(new ArrayList<OrderImportDetailList>());
			this.setConflictFrstOrderNo(null);
		}
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getOrderImportDetailList() != null) {
			for (OrderImportDetailList bean : getOrderImportDetailList()) {
				bean.setCheckline(Boolean.FALSE);
			}
		}
	}

	/**
	 * コンフリクトモード
	 * @return
	 */
	public boolean getConflicted(){
		if( AecStringUtils.isNotNullAndEmpty(this.conflictFrstOrderNo) ){
			return true;
		}else{
			return false;
		}
	}

	//
	// インスタンスメソッド
	//

	/**
	 * orderImportDetailListを取得します。
	 * @return orderImportDetailList
	 */
	public List<OrderImportDetailList> getOrderImportDetailList() {
		return orderImportDetailList;
	}

	/**
	 * orderImportDetailListを設定します。
	 * @param orderImportDetailList orderImportDetailList
	 */
	public void setOrderImportDetailList(
			List<OrderImportDetailList> orderImportDetailList) {
		this.orderImportDetailList = orderImportDetailList;
	}

	/**
	 * beforeOrderImportDetailListを取得します。
	 * @return beforeOrderImportDetailList
	 */
	public List<OrderImportDetailList> getBeforeOrderImportDetailList() {
		return beforeOrderImportDetailList;
	}

	/**
	 * beforeOrderImportDetailListを設定します。
	 * @param beforeOrderImportDetailList beforeOrderImportDetailList
	 */
	public void setBeforeOrderImportDetailList(
			List<OrderImportDetailList> beforeOrderImportDetailList) {
		this.beforeOrderImportDetailList = beforeOrderImportDetailList;
	}


	
	/**
	 * beforeOrderImportDetailListを取得します。
	 * @return beforeOrderImportDetailList
	 */
	public FrstOrderHead getBeforeFrstOrderHead() {
		return beforeFrstOrderHead;
	}
	
	/**
	 * beforeOrderImportDetailListを設定します。
	 * @param beforeOrderImportDetailList beforeOrderImportDetailList
	 */
	public void setBeforeFrstOrderHead(
			FrstOrderHead beforeFrstOrderHead) {
		this.beforeFrstOrderHead = beforeFrstOrderHead;
	}
	/**
	 * seqを取得します。
	 * @return seq
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
	 * orderNoを取得します。
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * orderNoを設定します。
	 * @param orderNo orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * orderDivisionを取得します。
	 * @return orderDivision
	 */
	public String getOrderDivision() {
		return orderDivision;
	}

	/**
	 * orderDivisionを設定します。
	 * @param orderDivision orderDivision
	 */
	public void setOrderDivision(String orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * FrstOrderNoを取得します。
	 * @return FrstOrderNo
	 */
	public String getFrstOrderNo() {
		return frstOrderNo;
	}

	/**
	 * FrstOrderNoを設定します。
	 * @param FrstOrderNo FrstOrderNo
	 */
	public void setFrstOrderNo(String FrstOrderNo) {
		this.frstOrderNo = FrstOrderNo;
	}

	/**
	 * venderGroupCdを取得します。
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return venderGroupCd;
	}

	/**
	 * venderGroupCdを設定します。
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
	}

	/**
	 * venderGroupNameを取得します。
	 * @return venderGroupName
	 */
	public String getVenderGroupName() {
		return venderGroupName;
	}

	/**
	 * venderGroupNameを設定します。
	 * @param venderGroupName venderGroupName
	 */
	public void setVenderGroupName(String venderGroupName) {
		this.venderGroupName = venderGroupName;
	}

	/**
	 * errorStatusを取得します。
	 * @return errorStatus
	 */
	public String getErrorStatus() {
		return errorStatus;
	}

	/**
	 * errorStatusを設定します。
	 * @param errorStatus errorStatus
	 */
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	/**
	 * orderDateを取得します。
	 * @return orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * orderDateを設定します。
	 * @param orderDate orderDate
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * ctmOrderDateを取得します。
	 * @return ctmOrderDate
	 */
	public String getCtmOrderDate() {
		return ctmOrderDate;
	}

	/**
	 * ctmOrderDateを設定します。
	 * @param ctmOrderDate ctmOrderDate
	 */
	public void setCtmOrderDate(String ctmOrderDate) {
		this.ctmOrderDate = ctmOrderDate;
	}

	/**
	 * importDateを取得します。
	 * @return importDate
	 */
	public String getImportDate() {
		return importDate;
	}

	/**
	 * importDateを設定します。
	 * @param importDate importDate
	 */
	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	/**
	 * deliveryCdを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * deliveryCdを設定します。
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * deliveryNameを取得します。
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * deliveryNameを設定します。
	 * @param deliveryName deliveryName
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * deliveryDivisionを取得します。
	 * @return deliveryDivision
	 */
	public String getDeliveryDivision() {
		return deliveryDivision;
	}

	/**
	 * deliveryDivisionを設定します。
	 * @param deliveryDivision deliveryDivision
	 */
	public void setDeliveryDivision(String deliveryDivision) {
		this.deliveryDivision = deliveryDivision;
	}

	/**
	 * ctmDeliveryCdを取得します。
	 * @return ctmDeliveryCd
	 */
	public String getCtmDeliveryCd() {
		return ctmDeliveryCd;
	}

	/**
	 * ctmDeliveryCdを設定します。
	 * @param ctmDeliveryCd ctmDeliveryCd
	 */
	public void setCtmDeliveryCd(String ctmDeliveryCd) {
		this.ctmDeliveryCd = ctmDeliveryCd;
	}

	/**
	 * ctmDeliveryNameを取得します。
	 * @return ctmDeliveryName
	 */
	public String getCtmDeliveryName() {
		return ctmDeliveryName;
	}

	/**
	 * ctmDeliveryNameを設定します。
	 * @param ctmDeliveryName ctmDeliveryName
	 */
	public void setCtmDeliveryName(String ctmDeliveryName) {
		this.ctmDeliveryName = ctmDeliveryName;
	}

	/**
	 * addressを取得します。
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * addressを設定します。
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * ctmAddressを取得します。
	 * @return ctmAddress
	 */
	public String getCtmAddress() {
		return ctmAddress;
	}

	/**
	 * ctmAddressを設定します。
	 * @param ctmAddress ctmAddress
	 */
	public void setCtmAddress(String ctmAddress) {
		this.ctmAddress = ctmAddress;
	}

	/**
	 * deliveryAddressを取得します。
	 * @return deliveryAddress
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * deliveryAddressを設定します。
	 * @param deliveryAddress deliveryAddress
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * ctmDeliveryAddressを取得します。
	 * @return ctmDeliveryAddress
	 */
	public String getCtmDeliveryAddress() {
		return ctmDeliveryAddress;
	}

	/**
	 * ctmDeliveryAddressを設定します。
	 * @param ctmDeliveryAddress ctmDeliveryAddress
	 */
	public void setCtmDeliveryAddress(String ctmDeliveryAddress) {
		this.ctmDeliveryAddress = ctmDeliveryAddress;
	}

	/**
	 * deliveryTelNoを取得します。
	 * @return deliveryTelNo
	 */
	public String getDeliveryTelNo() {
		return deliveryTelNo;
	}

	/**
	 * deliveryTelNoを設定します。
	 * @param deliveryTelNo deliveryTelNo
	 */
	public void setDeliveryTelNo(String deliveryTelNo) {
		this.deliveryTelNo = deliveryTelNo;
	}

	/**
	 * ctmDeliveryTelNoを取得します。
	 * @return ctmDeliveryTelNo
	 */
	public String getCtmDeliveryTelNo() {
		return ctmDeliveryTelNo;
	}

	/**
	 * ctmDeliveryTelNoを設定します。
	 * @param ctmDeliveryTelNo ctmDeliveryTelNo
	 */
	public void setCtmDeliveryTelNo(String ctmDeliveryTelNo) {
		this.ctmDeliveryTelNo = ctmDeliveryTelNo;
	}

	/**
	 * scheduledShippingDateを取得します。
	 * @return scheduledShippingDate
	 */
	public String getScheduledShippingDate() {
		return scheduledShippingDate;
	}

	/**
	 * scheduledShippingDateを設定します。
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(String scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
	}

	/**
	 * leadTimeを取得します。
	 * @return leadTime
	 */
	public String getLeadTime() {
		return leadTime;
	}

	/**
	 * leadTimeを設定します。
	 * @param leadTime leadTime
	 */
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * ctmRemark01を取得します。
	 * @return ctmRemark01
	 */
	public String getCtmRemark01() {
		return ctmRemark01;
	}

	/**
	 * ctmRemark01を設定します。
	 * @param ctmRemark01 ctmRemark01
	 */
	public void setCtmRemark01(String ctmRemark01) {
		this.ctmRemark01 = ctmRemark01;
	}

	/**
	 * ctmRemark02を取得します。
	 * @return ctmRemark02
	 */
	public String getCtmRemark02() {
		return ctmRemark02;
	}

	/**
	 * ctmRemark02を設定します。
	 * @param ctmRemark02 ctmRemark02
	 */
	public void setCtmRemark02(String ctmRemark02) {
		this.ctmRemark02 = ctmRemark02;
	}

	/**
	 * ctmRemark03を取得します。
	 * @return ctmRemark03
	 */
	public String getCtmRemark03() {
		return ctmRemark03;
	}

	/**
	 * ctmRemark03を設定します。
	 * @param ctmRemark03 ctmRemark03
	 */
	public void setCtmRemark03(String ctmRemark03) {
		this.ctmRemark03 = ctmRemark03;
	}

	/**
	 * carryCdを取得します。
	 * @return carryCd
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * carryCdを設定します。
	 * @param carryCd carryCd
	 */
	public void setCarryCd(String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * deliveryExpectedDateを取得します。
	 * @return deliveryExpectedDate
	 */
	public String getDeliveryExpectedDate() {
		return deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDateを設定します。
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(String deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedTimeを取得します。
	 * @return deliveryExpectedTime
	 */
	public String getDeliveryExpectedTime() {
		return deliveryExpectedTime;
	}

	/**
	 * deliveryExpectedTimeを設定します。
	 * @param deliveryExpectedTime deliveryExpectedTime
	 */
	public void setDeliveryExpectedTime(String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * suggestedDeliverlimitを取得します。
	 * @return suggestedDeliverlimit
	 */
	public String getSuggestedDeliverlimit() {
		return suggestedDeliverlimit;
	}

	/**
	 * suggestedDeliverlimitを設定します。
	 * @param suggestedDeliverlimit suggestedDeliverlimit
	 */
	public void setSuggestedDeliverlimit(String suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
	}

	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * totalOrderAmountを取得します。
	 * @return totalOrderAmount
	 */
	public String getTotalOrderAmount() {
		return totalOrderAmount;
	}

	/**
	 * totalOrderAmountを設定します。
	 * @param totalOrderAmount totalOrderAmount
	 */
	public void setTotalOrderAmount(String totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	/**
	 * totalQtyを取得します。
	 * @return totalQty
	 */
	public String getTotalQty() {
		return totalQty;
	}

	/**
	 * totalQtyを設定します。
	 * @param totalQty totalQty
	 */
	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}

	/**
	 * totalWeightを取得します。
	 * @return totalWeight
	 */
	public String getTotalWeight() {
		return totalWeight;
	}

	/**
	 * totalWeightを設定します。
	 * @param totalWeight totalWeight
	 */
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}

	/**
	 * balanceCdを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * balanceCdを設定します。
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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
	public void setVenderCd(String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderNameを取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * venderNameを設定します。
	 * @param venderName venderName
	 */
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	/**
	 * ctmVenderNameを取得します。
	 * @return ctmVenderName
	 */
	public String getCtmVenderName() {
		return ctmVenderName;
	}

	/**
	 * ctmVenderNameを設定します。
	 * @param ctmVenderName ctmVenderName
	 */
	public void setCtmVenderName(String ctmVenderName) {
		this.ctmVenderName = ctmVenderName;
	}

	/**
	 * salesTantoCdを取得します。
	 * @return salesTantoCd
	 */
	public String getSalesTantoCd() {
		return salesTantoCd;
	}

	/**
	 * salesTantoCdを設定します。
	 * @param salesTantoCd salesTantoCd
	 */
	public void setSalesTantoCd(String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}
	//20210526 add 担当者名表示追加 S.Fujimaki
	/**
	* salesTantoNameを設定します。
	* @param salesTantoName salesTantoName
	*/
	public void setSalesTantoName(String salesTantoName) {
		this.salesTantoName = salesTantoName;
	}

	/**
	* salesTantoNameを取得します。
	* @return salesTantoName
	*/
	public String getSalesTantoName() {
		return salesTantoName;
	}
	//20210526 add 担当者名表示追加 S.Fujimaki

	/**
	 * carryFareを取得します。
	 * @return carryFare
	 */
	public String getCarryFare() {
		return carryFare;
	}

	/**
	 * carryFareを設定します。
	 * @param carryFare carryFare
	 */
	public void setCarryFare(String carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * calcCarryFareを取得します。
	 * @return calcCarryFare
	 */
	public String getCalcCarryFare() {
		return calcCarryFare;
	}

	/**
	 * calcCarryFareを設定します。
	 * @param calcCarryFare calcCarryFare
	 */
	public void setCalcCarryFare(String calcCarryFare) {
		this.calcCarryFare = calcCarryFare;
	}

	/**
	 * carryInvoiceFlagを取得します。
	 * @return carryInvoiceFlag
	 */
	public BigDecimal getCarryInvoiceFlag() {
		return carryInvoiceFlag;
	}

	/**
	 * carryInvoiceFlagを設定します。
	 * @param carryInvoiceFlag carryInvoiceFlag
	 */
	public void setCarryInvoiceFlag(BigDecimal carryInvoiceFlag) {
		this.carryInvoiceFlag = carryInvoiceFlag;
	}

	/**
	 * blnCarryInvoiceFlagを取得します。
	 * @return blnCarryInvoiceFlag
	 */
	public Boolean getBlnCarryInvoiceFlag() {
		return blnCarryInvoiceFlag;
	}

	/**
	 * blnCarryInvoiceFlagを設定します。
	 * @param blnCarryInvoiceFlag blnCarryInvoiceFlag
	 */
	public void setBlnCarryInvoiceFlag(Boolean blnCarryInvoiceFlag) {
		this.blnCarryInvoiceFlag = blnCarryInvoiceFlag;
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

	/**
	 * deliverySlipSummeryを取得します。
	 * @return deliverySlipSummery
	 */
	public String getDeliverySlipSummery() {
		return deliverySlipSummery;
	}

	/**
	 * deliverySlipSummeryを設定します。
	 * @param deliverySlipSummery deliverySlipSummery
	 */
	public void setDeliverySlipSummery(String deliverySlipSummery) {
		this.deliverySlipSummery = deliverySlipSummery;
	}


	/**
	 * orderPictureを取得します。
	 * @return orderPicture
	 */
	public String getOrderPicture() {
		return orderPicture;
	}

	/**
	 * orderPictureを設定します。
	 * @param orderPicture orderPicture
	 */
	public void setOrderPicture(String orderPicture) {
		this.orderPicture = orderPicture;
	}

	/**
	 * orderSummeryを取得します。
	 * @return orderSummery
	 */
	public String getOrderSummery() {
		return orderSummery;
	}

	/**
	 * orderSummeryを設定します。
	 * @param orderSummery orderSummery
	 */
	public void setOrderSummery(String orderSummery) {
		this.orderSummery = orderSummery;
	}

	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	/**
	 * deliverydateContactSummeryを取得します。
	 * @return orderSummery
	 */
	public String getDeliverydateContactSummery() {
		return deliverydateContactSummery;
	}

	/**
	 * deliverydateContactSummeryを設定します。
	 * @param deliverydateContactSummery deliverydateContactSummery
	 */
	public void setDeliverydateContactSummery(String deliverydateContactSummery) {
		this.deliverydateContactSummery = deliverydateContactSummery;
	}

	/**
	 * ctmRemarkを取得します。
	 * @return ctmRemark
	 */
	public String getCtmRemark() {
		return ctmRemark;
	}

	/**
	 * ctmRemarkを設定します。
	 * @param ctmRemark ctmRemark
	 */
	public void setCtmRemark(String ctmRemark) {
		this.ctmRemark = ctmRemark;
	}

	/**
	 * uploadFileを取得します。
	 * @return uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}

	/**
	 * uploadFileを設定します。
	 * @param uploadFile uploadFile
	 */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
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
	public void setDownloadFlg(Boolean downloadFlg) {
		this.downloadFlg = downloadFlg;
	}

	/**
	 * orderImportDetailListCountを取得します。
	 * @return orderImportDetailListCount
	 */
	public int getOrderImportDetailListCount() {
		return orderImportDetailListCount;
	}

	/**
	 * orderImportDetailListCountを設定します。
	 * @param orderImportDetailListCount orderImportDetailListCount
	 */
	public void setOrderImportDetailListCount(int orderImportDetailListCount) {
		this.orderImportDetailListCount = orderImportDetailListCount;
	}

	/**
	 * beforeOrderImportDetailListCountを取得します。
	 * @return beforeOrderImportDetailListCount
	 */
	public int getBeforeOrderImportDetailListCount() {
		return beforeOrderImportDetailListCount;
	}

	/**
	 * beforeOrderImportDetailListCountを設定します。
	 * @param beforeOrderImportDetailListCount beforeOrderImportDetailListCount
	 */
	public void setBeforeOrderImportDetailListCount(
			int beforeOrderImportDetailListCount) {
		this.beforeOrderImportDetailListCount = beforeOrderImportDetailListCount;
	}

	/**
	 * orderDetailVenderListを取得します。
	 * @return orderDetailVenderList
	 */
	public List<OrderDetailVenderList> getOrderDetailVenderList() {
		return orderDetailVenderList;
	}

	/**
	 * orderDetailVenderListを設定します。
	 * @param orderDetailVenderList orderDetailVenderList
	 */
	public void setOrderDetailVenderList(
			List<OrderDetailVenderList> orderDetailVenderList) {
		this.orderDetailVenderList = orderDetailVenderList;
	}

	/**
	 * orderDetailVenderListCountを取得します。
	 * @return orderDetailVenderListCount
	 */
	public int getOrderDetailVenderListCount() {
		return orderDetailVenderListCount;
	}

	/**
	 * orderDetailVenderListCountを設定します。
	 * @param orderDetailVenderListCount orderDetailVenderListCount
	 */
	public void setOrderDetailVenderListCount(int orderDetailVenderListCount) {
		this.orderDetailVenderListCount = orderDetailVenderListCount;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * insertFlgを取得します。
	 * @return insertFlg
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * insertFlgを設定します。
	 * @param insertFlg insertFlg
	 */
	public void setInsertFlg(int insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * orderDivisionComboを取得します。
	 * @return orderDivisionCombo
	 */
	public List<ComboBoxItems> getOrderDivisionCombo() {
		return orderDivisionCombo;
	}

	/**
	 * orderDivisionComboを設定します。
	 * @param orderDivisionCombo orderDivisionCombo
	 */
	public void setOrderDivisionCombo(List<ComboBoxItems> orderDivisionCombo) {
		this.orderDivisionCombo = orderDivisionCombo;
	}

	/**
	 * carryComboを取得します。
	 * @return carryCombo
	 */
	public List<ComboBoxItems> getCarryCombo() {
		return carryCombo;
	}

	/**
	 * carryComboを設定します。
	 * @param carryCombo carryCombo
	 */
	public void setCarryCombo(List<ComboBoxItems> carryCombo) {
		this.carryCombo = carryCombo;
	}

	/**
	 * venderGroupComboを取得します。
	 * @return venderGroupCombo
	 */
	public List<ComboBoxItems> getVenderGroupCombo() {
		return venderGroupCombo;
	}

	/**
	 * venderGroupComboを設定します。
	 * @param venderGroupCombo venderGroupCombo
	 */
	public void setVenderGroupCombo(List<ComboBoxItems> venderGroupCombo) {
		this.venderGroupCombo = venderGroupCombo;
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
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	/**
	 * lineを取得します。
	 * @return line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * lineを設定します。
	 * @param line line
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * orderCancelButtonEnableFlgを取得します。
	 * @return orderCancelButtonEnableFlg
	 */
	public int getOrderCancelButtonEnableFlg() {
		return orderCancelButtonEnableFlg;
	}

	/**
	 * orderCancelButtonEnableFlgを設定します。
	 * @param orderCancelButtonEnableFlg orderCancelButtonEnableFlg
	 */
	public void setOrderCancelButtonEnableFlg(int orderCancelButtonEnableFlg) {
		this.orderCancelButtonEnableFlg = orderCancelButtonEnableFlg;
	}

	/**
	 * difCarryFareFlgを取得します。
	 * @return difCarryFareFlg
	 */
	public int getDifCarryFareFlg() {
		return difCarryFareFlg;
	}

	/**
	 * difCarryFareFlgを設定します。
	 * @param difCarryFareFlg difCarryFareFlg
	 */
	public void setDifCarryFareFlg(int difCarryFareFlg) {
		this.difCarryFareFlg = difCarryFareFlg;
	}

	/**
	 * difRemarkFlgを取得します。
	 * @return difRemarkFlg
	 */
	public int getDifRemarkFlg() {
		return difRemarkFlg;
	}

	/**
	 * difRemarkFlgを設定します。
	 * @param difRemarkFlg difRemarkFlg
	 */
	public void setDifRemarkFlg(int difRemarkFlg) {
		this.difRemarkFlg = difRemarkFlg;
	}

	/**
	 * dbBalanceCdを取得します。
	 * @return dbBalanceCd
	 */
	public String getDbBalanceCd() {
		return dbBalanceCd;
	}

	/**
	 * dbBalanceCdを設定します。
	 * @param dbBalanceCd dbBalanceCd
	 */
	public void setDbBalanceCd(String dbBalanceCd) {
		this.dbBalanceCd = dbBalanceCd;
	}

	/**
	 * dbVenderCdを取得します。
	 * @return dbVenderCd
	 */
	public String getDbVenderCd() {
		return dbVenderCd;
	}

	/**
	 * dbVenderCdを設定します。
	 * @param dbVenderCd dbVenderCd
	 */
	public void setDbVenderCd(String dbVenderCd) {
		this.dbVenderCd = dbVenderCd;
	}

	/**
	 * sameCtmOrderNoを取得します。
	 * @return sameCtmOrderNo
	 */
	public String getSameCtmOrderNo() {
		return sameCtmOrderNo;
	}

	/**
	 * sameCtmOrderNoを設定します。
	 * @param sameCtmOrderNo sameCtmOrderNo
	 */
	public void setSameCtmOrderNo(String sameCtmOrderNo) {
		this.sameCtmOrderNo = sameCtmOrderNo;
	}

	/**
	 * smallnumLengthUrTankaを取得します。
	 * @return smallnumLengthUrTanka
	 */
	public String getSmallnumLengthUrTanka() {
		return smallnumLengthUrTanka;
	}

	/**
	 * smallnumLengthUrTankaを設定します。
	 * @param smallnumLengthUrTanka smallnumLengthUrTanka
	 */
	public void setSmallnumLengthUrTanka(String smallnumLengthUrTanka) {
		this.smallnumLengthUrTanka = smallnumLengthUrTanka;
	}

	/**
	 * roundDivisionUrTankaを取得します。
	 * @return roundDivisionUrTanka
	 */
	public String getRoundDivisionUrTanka() {
		return roundDivisionUrTanka;
	}

	/**
	 * roundDivisionUrTankaを設定します。
	 * @param roundDivisionUrTanka roundDivisionUrTanka
	 */
	public void setRoundDivisionUrTanka(String roundDivisionUrTanka) {
		this.roundDivisionUrTanka = roundDivisionUrTanka;
	}

	/**
	 * smallnumLengthCarryFareを取得します。
	 * @return smallnumLengthCarryFare
	 */
	public String getSmallnumLengthCarryFare() {
		return smallnumLengthCarryFare;
	}

	/**
	 * smallnumLengthCarryFareを設定します。
	 * @param smallnumLengthCarryFare smallnumLengthCarryFare
	 */
	public void setSmallnumLengthCarryFare(String smallnumLengthCarryFare) {
		this.smallnumLengthCarryFare = smallnumLengthCarryFare;
	}

	/**
	 * roundDivisionCarryFareを取得します。
	 * @return roundDivisionCarryFare
	 */
	public String getRoundDivisionCarryFare() {
		return roundDivisionCarryFare;
	}

	/**
	 * roundDivisionCarryFareを設定します。
	 * @param roundDivisionCarryFare roundDivisionCarryFare
	 */
	public void setRoundDivisionCarryFare(String roundDivisionCarryFare) {
		this.roundDivisionCarryFare = roundDivisionCarryFare;
	}

	/**
	 * unitDivisionCarryFareを取得します。
	 * @return unitDivisionCarryFare
	 */
	public String getUnitDivisionCarryFare() {
		return unitDivisionCarryFare;
	}

	/**
	 * unitDivisionCarryFareを設定します。
	 * @param unitDivisionCarryFare unitDivisionCarryFare
	 */
	public void setUnitDivisionCarryFare(String unitDivisionCarryFare) {
		this.unitDivisionCarryFare = unitDivisionCarryFare;
	}

	/**
	 * smallnumLengthUrKingakuを取得します。
	 * @return smallnumLengthUrKingaku
	 */
	public String getSmallnumLengthUrKingaku() {
		return smallnumLengthUrKingaku;
	}

	/**
	 * smallnumLengthUrKingakuを設定します。
	 * @param smallnumLengthUrKingaku smallnumLengthUrKingaku
	 */
	public void setSmallnumLengthUrKingaku(String smallnumLengthUrKingaku) {
		this.smallnumLengthUrKingaku = smallnumLengthUrKingaku;
	}

	/**
	 * roundDivisionUrKingakuを取得します。
	 * @return roundDivisionUrKingaku
	 */
	public String getRoundDivisionUrKingaku() {
		return roundDivisionUrKingaku;
	}

	/**
	 * roundDivisionUrKingakuを設定します。
	 * @param roundDivisionUrKingaku roundDivisionUrKingaku
	 */
	public void setRoundDivisionUrKingaku(String roundDivisionUrKingaku) {
		this.roundDivisionUrKingaku = roundDivisionUrKingaku;
	}

	/**
	 * smallnumLengthTotalNumを取得します。
	 * @return smallnumLengthTotalNum
	 */
	public String getSmallnumLengthTotalNum() {
		return smallnumLengthTotalNum;
	}

	/**
	 * smallnumLengthTotalNumを設定します。
	 * @param smallnumLengthTotalNum smallnumLengthTotalNum
	 */
	public void setSmallnumLengthTotalNum(String smallnumLengthTotalNum) {
		this.smallnumLengthTotalNum = smallnumLengthTotalNum;
	}

	/**
	 * roundDivisionTotalNumを取得します。
	 * @return roundDivisionTotalNum
	 */
	public String getRoundDivisionTotalNum() {
		return roundDivisionTotalNum;
	}

	/**
	 * roundDivisionTotalNumを設定します。
	 * @param roundDivisionTotalNum roundDivisionTotalNum
	 */
	public void setRoundDivisionTotalNum(String roundDivisionTotalNum) {
		this.roundDivisionTotalNum = roundDivisionTotalNum;
	}

	/**
	 * smallnumLengthTotalWeightを取得します。
	 * @return smallnumLengthTotalWeight
	 */
	public String getSmallnumLengthTotalWeight() {
		return smallnumLengthTotalWeight;
	}

	/**
	 * smallnumLengthTotalWeightを設定します。
	 * @param smallnumLengthTotalWeight smallnumLengthTotalWeight
	 */
	public void setSmallnumLengthTotalWeight(String smallnumLengthTotalWeight) {
		this.smallnumLengthTotalWeight = smallnumLengthTotalWeight;
	}

	/**
	 * roundDivisionTotalWeightを取得します。
	 * @return roundDivisionTotalWeight
	 */
	public String getRoundDivisionTotalWeight() {
		return roundDivisionTotalWeight;
	}

	/**
	 * roundDivisionTotalWeightを設定します。
	 * @param roundDivisionTotalWeight roundDivisionTotalWeight
	 */
	public void setRoundDivisionTotalWeight(String roundDivisionTotalWeight) {
		this.roundDivisionTotalWeight = roundDivisionTotalWeight;
	}

	/**
	 * smallnumLengthSonotaを取得します。
	 * @return smallnumLengthSonota
	 */
	public String getSmallnumLengthSonota() {
		return smallnumLengthSonota;
	}

	/**
	 * smallnumLengthSonotaを設定します。
	 * @param smallnumLengthSonota smallnumLengthSonota
	 */
	public void setSmallnumLengthSonota(String smallnumLengthSonota) {
		this.smallnumLengthSonota = smallnumLengthSonota;
	}

	/**
	 * roundDivisionSonotaを取得します。
	 * @return roundDivisionSonota
	 */
	public String getRoundDivisionSonota() {
		return roundDivisionSonota;
	}

	/**
	 * roundDivisionSonotaを設定します。
	 * @param roundDivisionSonota roundDivisionSonota
	 */
	public void setRoundDivisionSonota(String roundDivisionSonota) {
		this.roundDivisionSonota = roundDivisionSonota;
	}

	/**
	 * varidUnitpriceRowを取得します。
	 * @return varidUnitpriceRow
	 */
	public int getVaridUnitpriceRow() {
		return varidUnitpriceRow;
	}

	/**
	 * varidUnitpriceRowを設定します。
	 * @param varidUnitpriceRow varidUnitpriceRow
	 */
	public void setVaridUnitpriceRow(int varidUnitpriceRow) {
		this.varidUnitpriceRow = varidUnitpriceRow;
	}

	/**
	 * checkQtyを取得します。
	 * @return checkQty
	 */
	public BigDecimal getCheckQty() {
		return checkQty;
	}

	/**
	 * checkQtyを設定します。
	 * @param checkQty checkQty
	 */
	public void setCheckQty(BigDecimal checkQty) {
		this.checkQty = checkQty;
	}

	/**
	 * checkOrderAmountを取得します。
	 * @return checkOrderAmount
	 */
	public BigDecimal getCheckOrderAmount() {
		return checkOrderAmount;
	}

	/**
	 * checkOrderAmountを設定します。
	 * @param checkOrderAmount checkOrderAmount
	 */
	public void setCheckOrderAmount(BigDecimal checkOrderAmount) {
		this.checkOrderAmount = checkOrderAmount;
	}

	/**
	 * frstOrderRowを取得します。
	 * @return frstOrderRow
	 */
	public BigDecimal getFrstOrderRow() {
		return frstOrderRow;
	}

	/**
	 * frstOrderRowを設定します。
	 * @param frstOrderRow frstOrderRow
	 */
	public void setFrstOrderRow(BigDecimal frstOrderRow) {
		this.frstOrderRow = frstOrderRow;
	}

	/**
	 * cancelFlgを取得します。
	 * @return cancelFlg
	 */
	public int getCancelFlg() {
		return cancelFlg;
	}

	/**
	 * cancelFlgを設定します。
	 * @param cancelFlg cancelFlg
	 */
	public void setCancelFlg(int cancelFlg) {
		this.cancelFlg = cancelFlg;
	}

	/**
	 * delFlgを取得します。
	 * @return delFlg
	 */
	public int getDelFlg() {
		return delFlg;
	}

	/**
	 * delFlgを設定します。
	 * @param delFlg delFlg
	 */
	public void setDelFlg(int delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * fixedFlgを取得します。
	 * @return fixedFlg
	 */
	public int getFixedFlg() {
		return fixedFlg;
	}

	/**
	 * importStatusを取得します。
	 * @return importStatus
	 */
	public String getImportStatus() {
		return importStatus;
	}

	/**
	 * importStatusを設定します。
	 * @param importStatus importStatus
	 */
	public void setImportStatus(String importStatus) {
		this.importStatus = importStatus;
	}

	/**
	 * fixedFlgを設定します。
	 * @param fixedFlg fixedFlg
	 */
	public void setFixedFlg(int fixedFlg) {
		this.fixedFlg = fixedFlg;
	}

	/**
	 * headCtmOrderNoを取得します。
	 * @return headCtmOrderNo
	 */
	public String getHeadCtmOrderNo() {
		return headCtmOrderNo;
	}

	/**
	 * headCtmOrderNoを設定します。
	 * @param headCtmOrderNo headCtmOrderNo
	 */
	public void setHeadCtmOrderNo(String headCtmOrderNo) {
		this.headCtmOrderNo = headCtmOrderNo;
	}

	/**
	 * conflictOrderImportDetailListを取得します。
	 * @return conflictOrderImportDetailList
	 */
	public List<OrderImportDetailList> getConflictOrderImportDetailList() {
		return conflictOrderImportDetailList;
	}

	/**
	 * conflictOrderImportDetailListを設定します。
	 * @param conflictOrderImportDetailList conflictOrderImportDetailList
	 */
	public void setConflictOrderImportDetailList(
			List<OrderImportDetailList> conflictOrderImportDetailList) {
		this.conflictOrderImportDetailList = conflictOrderImportDetailList;
	}


	/**
	 * reloadFlgを取得します。
	 * @return reloadFlg
	 */
	public int getReloadFlg() {
		return reloadFlg;
	}

	/**
	 * reloadFlgを設定します。
	 * @param reloadFlg reloadFlg
	 */
	public void setReloadFlg(int reloadFlg) {
		this.reloadFlg = reloadFlg;
	}
	/**
	 * conflictOrderHeadListを取得します。
	 * @return conflictOrderHeadList
	 */
	public List<FrstOrderHead> getConflictOrderHeadList() {
		return conflictOrderHeadList;
	}

	/**
	 * conflictOrderHeadListを設定します。
	 * @param conflictOrderHeadList conflictOrderHeadList
	 */
	public void setConflictOrderHeadList(List<FrstOrderHead> conflictOrderHeadList) {
		this.conflictOrderHeadList = conflictOrderHeadList;
	}


	/**
	 * scrollFlgを取得します。
	 * @return scrollFlg
	 */
	public int getScrollFlg() {
		return scrollFlg;
	}

	/**
	 * scrollFlgを設定します。
	 * @param scrollFlg scrollFlg
	 */
	public void setScrollFlg(int scrollFlg) {
		this.scrollFlg = scrollFlg;
	}

	/**
	 * fareCalcFlgを取得します。
	 * @return fareCalcFlg
	 */
	public boolean isFareCalcFlg() {
		return fareCalcFlg;
	}

	/**
	 * fareCalcFlgを設定します。
	 * @param fareCalcFlg fareCalcFlg
	 */
	public void setFareCalcFlg(boolean fareCalcFlg) {
		this.fareCalcFlg = fareCalcFlg;
	}

	/**
	 * orderInvisibleButtonEnableFlgを取得します。
	 * @return orderInvisibleButtonEnableFlg
	 */
	public int getOrderInvisibleButtonEnableFlg() {
		return orderInvisibleButtonEnableFlg;
	}

	/**
	 * orderInvisibleButtonEnableFlgを設定します。
	 * @param orderInvisibleButtonEnableFlg orderInvisibleButtonEnableFlg
	 */
	public void setOrderInvisibleButtonEnableFlg(int orderInvisibleButtonEnableFlg) {
		this.orderInvisibleButtonEnableFlg = orderInvisibleButtonEnableFlg;
	}

	/**
	 * frstOrderHeadBeanを取得します。
	 * @return frstOrderHeadBean
	 */
	public FrstOrderHead getFrstOrderHeadBean() {
		return frstOrderHeadBean;
	}

	/**
	 * frstOrderHeadBeanを設定します。
	 * @param frstOrderHeadBean frstOrderHeadBean
	 */
	public void setFrstOrderHeadBean(FrstOrderHead frstOrderHeadBean) {
		this.frstOrderHeadBean = frstOrderHeadBean;
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

}
