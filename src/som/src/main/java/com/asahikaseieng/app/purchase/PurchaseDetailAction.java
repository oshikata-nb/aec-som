/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchase;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseDetail;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 発注詳細 Actionクラス.
 * @author tosco
 */
public final class PurchaseDetailAction extends AbstractAction {

	private static final String UNIT_DIVITION_KG = "1";

	/** 発注詳細ロジッククラス */
	private PurchaseDetailLogic purchaseDetailLogic;

	/** 発番処理 ロジッククラス */
	private GetNumberLogic getNumberLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 種別 1:原料 */
	public static final int TYPE1_DIVISION = 1;

	/** 種別 2:包材 */
	public static final int TYPE2_DIVISION = 2;

	/** 種別 4:仕入直送品 */
	public static final int TYPE4_DIVISION = 4;

	/** 種別 5：仕入在庫品 */
	public static final int TYPE5_DIVISION = 5;

	/** 種別 6：外注品(直送） */
	public static final int TYPE6_DIVISION = 6;

	/** 種別 7：外注品(非直送） */
	public static final int TYPE7_DIVISION = 7;

	/** スポット区分 1:通常 */
	public static final BigDecimal SPOT1_DIVISION = new BigDecimal(1);

	/** スポット区分 2:スポット */
	public static final BigDecimal SPOT2_DIVISION = new BigDecimal(2);

	/** オーダー区分 1:原材料 */
	public static final BigDecimal ORDER1_DIVISION = new BigDecimal(1);

	/** オーダー区分 1:原材料 int */
	public static final int ORDER1_DIVISION_INT = 1;

	/** オーダー区分 2：外注製品（直送） */
	public static final BigDecimal ORDER2_DIVISION = new BigDecimal(2);

	/** オーダー区分 3：外注製品（非直送） */
	public static final BigDecimal ORDER3_DIVISION = new BigDecimal(3);

	/** オーダー区分 4：スポット品 */
	public static final BigDecimal ORDER4_DIVISION = new BigDecimal(4);

	/** オーダー区分 5：仕入直送品 */
	public static final BigDecimal ORDER5_DIVISION = new BigDecimal(5);

	/** オーダー区分 6：仕入在庫品 */
	public static final BigDecimal ORDER6_DIVISION = new BigDecimal(6);

	/** 外注原材料区分 1：外注 */
	public static final BigDecimal MATERIAL1_DIVISION = new BigDecimal(1);

	/** 単価区分 1:個あたり */
	public static final String UNIT_KO = UNIT_DIVITION_KG;

	/** 単価区分 2:kgあたり */
	public static final String UNIT_KG = "2";

	/** 新規入力フラグ 1:新規 */
	public static final String MODE_INSERT = UNIT_DIVITION_KG;

	/** 新規入力フラグ 0:変更 */
	public static final String MODE_CHANGE = "0";

	/**
	 * コンストラクタ.
	 */
	public PurchaseDetailAction() {
		super();
	}

	/**
	 * 検索処理(一覧画面のXXXXXXXリンク押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PURCHASE,
			Constants.TAB_ID_PURCHASE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 新規用切替フラグを変更(0)に設定
		frm.setInsertFlg(MODE_CHANGE);
		// 画面の変更フラグをクリア
		frm.setDirtyFlg("false");

		try {
			/* 初期検索 */
			PurchaseDetail bean = purchaseDetailLogic.getEntity(frm
					.getPurchaseNo(), getLoginInfo(request).getTantoCd());

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, bean);

			// 仕入れ直送品の場合削除ボタンを表示させない為オーダー区分をＪＳＰに渡す
			if (bean.getOrderDivision() != null) {
				frm.setStrOrderDivision(bean.getOrderDivision().toString());
			}
			// javascriptでの桁数丸め用に必要となる値取得
			getCheckDigit(frm, bean);

			// 不足量
			frm.setStrCheckQuantity(checker.format("SONOTA", "SI", bean
					.getVenderCd(), bean.getCheckQuantity()));
			// 購買ステータス(コンボボックス用)初期値設定
			if (PurchaseStatus.isAbleChange(new BigDecimal(frm.getStatus()))) {

				// 購買ステータスコンボボックスは、購買ステータスが2,3,4の時のみ表示
				frm.setCboStatus(frm.getStatus());
			}

			// 詳細Beanを保持
			frm.setDetailBean(bean);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		return mapping.findForward("success");
	}

	/**
	 * 新規登録画面表示処理(一覧画面の新規ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}
		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PURCHASE,
			Constants.TAB_ID_PURCHASE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 新規用切替フラグを新規(1)に設定
		frm.setInsertFlg(MODE_INSERT);
		// 画面の変更フラグをクリア
		frm.setDirtyFlg("false");

		// ***********************
		// 初期表示
		// ***********************
		// 発注日
		frm.setStrOrderDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
		// 担当者コード
		frm.setTantoCd(getLoginInfo(request).getTantoCd());
		// 担当者名称
		frm.setTantoNm(getLoginInfo(request).getTantoNm());
		// 部署コード
		frm.setOrganizationCd(getLoginInfo(request).getOrganizationCd());
		// 部署名称
		frm.setSectionName(getLoginInfo(request).getOrganizationName());

		return mapping.findForward("success");
	}

	/**
	 * 更新処理(詳細画面の登録ボタン押下時(更新))
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;
		// 保持していた詳細Beanを取得
		PurchaseDetail detailBean = frm.getDetailBean();

		// 品目連動項目取得処理
		getItemRelatedDataForInsUpd(mapping, form, request, response);

		// 入力された各コードのマスタ存在チェック
		// 1つでもマスタに存在しない値が入力されていたら
		if (!isMasterCheck(frm, request)) {
			return mapping.findForward("success");
		}

		/* 更新対象データを作成する */
		PurchaseSubcontract newBean = new PurchaseSubcontract();

		/* 値を更新用Beanにコピる */
		// 画面の内容を詳細Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(detailBean, frm);
		// 日付・数値項目の編集
		// 発注日・納品希望日時・数量・重量・単価・金額
		convert(detailBean, frm);
		// 詳細Beanの内容を更新用Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(newBean, detailBean);

		// *****************************************
		// copyPropertiesでBeanへコピーされない値をセットする
		// *****************************************
		newBean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 担当者
		// スポット品以外の場合、品目名称をクリアする。(購買外注オーダーテーブルに書き込まない)
		if (newBean.getOrderDivision().compareTo(ORDER4_DIVISION) != 0) {
			newBean.setItemName(null);
		} else {
			newBean.setItemName(frm.getItemName());
		}
		// 発注書備考（入荷以降）
		newBean.setOrderSheetRemark2(newBean.getOrderSheetRemark());
		// 備考（入荷以降）
		newBean.setRemark2(newBean.getRemark());

		try {
			/* 更新処理を実行 */
			purchaseDetailLogic.update(newBean, frm.getOrderLocation());
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (LogicExceptionEx e) {
			// 在庫更新に失敗
			if ("errors.purchase.stock.update".equals(e.getMessage())) {
				saveError(request, e.getMessage());
			} else {
				throw e;
			}
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理(詳細画面の登録ボタン押下時(新規))
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}
		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		// 品目連動項目取得処理
		getItemRelatedDataForInsUpd(mapping, form, request, response);

		// 入力された各コードのマスタ存在チェック
		// 1つでもマスタに存在しない値が入力されていたら
		if (!isMasterCheck(frm, request)) {
			return mapping.findForward("success");
		}

		/* 更新対象データを作成する */
		PurchaseSubcontract newBean = new PurchaseSubcontract();

		PurchaseDetail detailBean = new PurchaseDetail();

		/* 値を更新用Beanにコピる */
		// 画面の内容を詳細Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(detailBean, frm);
		// 日付・数値項目の編集
		// 発注日・納品希望日時・数量・重量・単価・金額
		convert(detailBean, frm);
		// 詳細Beanの内容を更新用Beanへコピー
		IgnoreCaseBeanUtils.copyProperties(newBean, detailBean);

		// *****************************************
		// copyPropertiesでBeanへコピーされない値をセットする
		// *****************************************
		// 発注番号発番
		String buySubcontractOrderNo = getNumberLogic
				.getBuySubcontractOrderNo();
		newBean.setBuySubcontractOrderNo(buySubcontractOrderNo); // 発注番号
		newBean.setInputorCd(getLoginInfo(request).getTantoCd()); // 登録者
		newBean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 担当者
		// スポット品以外の場合、品目名称をクリアする。(購買外注オーダーテーブルに書き込まない)
		if (newBean.getOrderDivision() == null
				|| newBean.getOrderDivision().compareTo(ORDER4_DIVISION) != 0) {
			newBean.setItemName(null);
		} else {
			newBean.setItemName(frm.getItemName());
		}
		// 発注書備考（入荷以降）
		newBean.setOrderSheetRemark2(newBean.getOrderSheetRemark());
		// 備考（入荷以降）
		newBean.setRemark2(newBean.getRemark());

		try {
			/* 更新処理を実行 */
			purchaseDetailLogic.insert(newBean);
		} catch (LogicExceptionEx e) {
			// 在庫更新に失敗
			if ("errors.purchase.stock.update".equals(e.getMessage())) {
				saveError(request, e.getMessage());
			} else {
				throw e;
			}
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.insert");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理(詳細画面の削除ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}
		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		// 購買NOをキーに更新対象データを取得する
		PurchaseDetail bean = frm.getDetailBean();

		/* 更新対象データを作成する */
		PurchaseSubcontract newBean = new PurchaseSubcontract();

		// 画面の内容をnewBeanへコピー
		IgnoreCaseBeanUtils.copyProperties(newBean, bean);

		try {
			/* 更新処理を実行 */
			purchaseDetailLogic.delete(newBean);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (LogicExceptionEx e) {
			// 在庫更新に失敗
			if ("errors.purchase.stock.update".equals(e.getMessage())) {
				saveError(request, e.getMessage());
			} else {
				throw e;
			}
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 戻る処理(詳細画面または新規登録画面の戻るボタン押下時)
	 *
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");

	}

	/**
	 * マスタ存在チェック.
	 * @param frm マスタチェック対象フォーム
	 * @param response resource取得用のresponse
	 * @return boolean 全ての項目がマスタにある:true<br>
	 *         マスタにないものがある:false
	 */
	private boolean isMasterCheck(final PurchaseDetailForm frm,
			final HttpServletRequest request) {

		// ActionMessagesの作成
		ActionMessages messages = new ActionMessages();
		// メッセージのキー取得のため、resource取得
		MessageResources resource = getResources(request);

		// 品目マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getItemCd())) {
			Item itemBean = purchaseDetailLogic.getItem(frm.getItemCd());
			if (itemBean == null) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.purchase.item.cd")));
			} else {
				// 新規入力画面のとき、
				if (frm.getInsertFlg().equals(MODE_INSERT)) {
					// 4:仕入直送品の場合、エラーとする。
					if (itemBean.getTypeDivision().intValue() == TYPE4_DIVISION) {
						messages.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage("errors.purchase.type.division"));
					}
					// 購入品区分が0:該当なしの場合、エラーとする。
					if (itemBean.getPurchaseDivision().compareTo(
						BigDecimal.ZERO) == 0) {
						messages.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage(
									"errors.purchase.purchase.division"));
					}
				}
			}
		}

		// 仕入先マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getVenderCd())) {
			if (0 == purchaseDetailLogic.getCountVender(frm.getVenderCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.purchase.vender.cd")));
			}
		}

		// 担当部署マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getChargeOrganizationCd())) {
			if (0 == purchaseDetailLogic.getCountOrganization(frm
					.getChargeOrganizationCd())) {
				messages
						.add(
							ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage(
									"errors.nodata.master",
									resource
											.getMessage("item.purchase.charge.organization.cd")));
			}
		}

		// 納入先マスタ存在チェック
		// オーダー区分によりチェックするマスタが異なる
		if (StringUtils.isNotEmpty(frm.getLocationCd())) {
			// オーダー区分により、納入先＝ロケーションマスタでチェック
			if (isLocation(frm.getOrderDivision())) {
				Location locationBean = purchaseDetailLogic
						.getCountLocation(frm.getLocationCd());
				if (locationBean == null) {
					messages.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.nodata.master", resource
								.getMessage("item.purchase.location.cd")));
				}
			}
			// オーダー区分により、納入先＝納入先マスタでチェック
			if (isDelivery(frm.getOrderDivision())) {
				if (0 == purchaseDetailLogic.getCountDelivery(frm
						.getLocationCd())) {
					// スポット品の場合のみ再度、ロケーションマスタでチェックする
					if (frm.getOrderDivision().compareTo(ORDER4_DIVISION) == 0) {
						Location locationBean = purchaseDetailLogic
								.getCountLocation(frm.getLocationCd());
						if (locationBean == null) {
							messages
									.add(
										ActionMessages.GLOBAL_MESSAGE,
										new ActionMessage(
												"errors.nodata.master",
												resource
														.getMessage("item.purchase.location.cd")));
						}
					} else {
						messages.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage("errors.nodata.master", resource
									.getMessage("item.purchase.location.cd")));
					}
				}
			}
		}

		// 部署マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getOrganizationCd())) {
			if (0 == purchaseDetailLogic.getCountOrganization(frm
					.getOrganizationCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.purchase.organization.cd")));
			}
		}
		// 担当者マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getTantoCd())) {
			if (0 == purchaseDetailLogic.getCountLogin(frm.getTantoCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.purchase.tanto.cd")));
			}
		}
		// 所属マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getOrganizationCd())
				&& StringUtils.isNotEmpty(frm.getTantoCd())) {
			if (0 == purchaseDetailLogic.getCountBelong(
				frm.getOrganizationCd(), frm.getTantoCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.purchase.belong"));
			}
		}
		// 仕入先別単価マスタ存在チェック
		// 存在チェックなしに変更
		// if (StringUtils.isNotEmpty(frm.getItemCd()) &&
		// StringUtils.isNotEmpty(frm.getVenderCd())) {
		// if (0 == purchaseDetailLogic.getCountUnitprice(frm.getItemCd(),
		// frm.getVenderCd())) {
		// messages.add(ActionMessages.GLOBAL_MESSAGE,
		// new ActionMessage("errors.purchase.unit.price"));
		// }
		// }

		// 1つでもマスタに存在しないものがあれば
		if (!messages.isEmpty()) {
			// エラーメッセージの登録
			saveErrors(request, messages);
			return false;
		}

		return true;
	}

	/**
	 * 日付・数値項目の編集 DBへ書き込む前に行う 日付：スラッシュ除去 数値：カンマ除去
	 * @param detailBean
	 * @param frm
	 * @throws Exception
	 */
	private void convert(final PurchaseDetail detailBean,
			final PurchaseDetailForm frm) throws Exception {

		// 発注日
		detailBean.setOrderDate(AecDateUtils.getTimestampYmdFormat(frm
				.getStrOrderDate()));
		// 納品希望日時
		String sugDate = frm.getStrSuggestedDeliverlimitDate() + " ";
		String tmptime = frm.getStrSuggestedDeliverlimitTime();
		if (StringUtils.isEmpty(tmptime)) {
			tmptime = "00:00";
		}
		sugDate = sugDate + tmptime;
		detailBean.setSuggestedDeliverlimitDate(AecDateUtils
				.getTimestampYmdHmFormat(sugDate, "yyyy/MM/dd HH:mm"));

		// 数量 画面のカンマあり数量からカンマを除去
		detailBean.setOrderQuantity(AecNumberUtils.convertBigDecimal(frm
				.getStrOrderQuantity()));
		// 単価 画面のカンマあり単価からカンマを除去
		detailBean.setOrderUnitprice(AecNumberUtils.convertBigDecimal(frm
				.getStrOrderUnitprice()));
		// *************************************
		// 重量 再計算後セットする
		// *************************************
		// KG換算係数(在庫)、カンマ除去
		BigDecimal kg = AecNumberUtils.convertBigDecimal(frm
				.getKgOfFractionManagement());
		if (kg != null) {
			// 重量計算して小数点以下の処理後セット (数量*Kg換算係数)
			detailBean
					.setOrderConvertQuantity(checker.round("1", "SI", frm
							.getVenderCd(), detailBean.getOrderQuantity()
							.multiply(kg)));
		}
		// *************************************
		// 金額 再計算後セットする
		// *************************************
		if (StringUtils.isEmpty(frm.getUnitpriceDefineunit())) {
			// 取引先単価マスタが存在しない場合は、個あたりと同じ計算とする
			detailBean.setSupplierOrdAmount(checker.round("SIKINGAKU", "SI",
				frm.getVenderCd(), detailBean.getOrderQuantity().multiply(
					detailBean.getOrderUnitprice())));
		} else {
			// 個あたり
			if (frm.getUnitpriceDefineunit().equals(UNIT_KO)) {
				// 金額計算して小数点以下の処理後セット (数量*単価)
				detailBean.setSupplierOrdAmount(checker.round("SIKINGAKU",
					"SI", frm.getVenderCd(), detailBean.getOrderQuantity()
							.multiply(detailBean.getOrderUnitprice())));
			}
			// Kgあたり
			if (kg != null) {
				if (frm.getUnitpriceDefineunit().equals(UNIT_KG)) {
					// 金額計算して小数点以下の処理後セット (数量*単価*Kg換算係数)
					detailBean.setSupplierOrdAmount(checker.round("SIKINGAKU",
						"SI", frm.getVenderCd(), detailBean.getOrderQuantity()
								.multiply(detailBean.getOrderUnitprice())
								.multiply(kg)));
				}
			}
		}
	}

	/**
	 * オーダー区分、条件選択
	 * @param newBean
	 * @param frm
	 * @throws Exception
	 */
	private BigDecimal getOrderDivision(final int type, final BigDecimal spot)
			throws Exception {

		BigDecimal orderDivision = new BigDecimal(0);

		if (spot.compareTo(SPOT2_DIVISION) == 0) {
			orderDivision = ORDER4_DIVISION; // オーダー区分 4：スポット品
		} else if (spot.compareTo(SPOT1_DIVISION) == 0) {
			switch (type) {
			case TYPE1_DIVISION:
				orderDivision = ORDER1_DIVISION; // オーダー区分 1:原材料
				break;
			case TYPE2_DIVISION:
				orderDivision = ORDER1_DIVISION; // オーダー区分 1:原材料
				break;
			case TYPE4_DIVISION:
				orderDivision = ORDER5_DIVISION; // オーダー区分 5：仕入直送品
				break;
			case TYPE5_DIVISION:
				orderDivision = ORDER6_DIVISION; // オーダー区分 6：仕入在庫品
				break;
			case TYPE6_DIVISION:
				orderDivision = ORDER2_DIVISION; // オーダー区分 2：外注製品（直送）
				break;
			case TYPE7_DIVISION:
				orderDivision = ORDER3_DIVISION; // オーダー区分 3：外注製品（非直送）
				break;
			default:
				break;
			}
		}
		return orderDivision;
	}

	/**
	 * 備考検索処理(詳細画面の備考取得ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getRemarkList(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getRemarkList.");
		}
		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		try {
			// 備考の検索
			frm.setRemarkList(purchaseDetailLogic.getRemarkList(frm
					.getVenderCd(), frm.getLocationCd(), frm.getItemCd()));
		} catch (NoDataException ex) {
			// 画面上部にメッセージセット
			saveMessage(request, "message.purchase.getremark.nodata");
			return mapping.findForward("success");
		}

		// *******************************
		// 取得した備考を発注書用備考と備考にセット
		// *******************************
		StringBuffer sbfOrderSheetRemark = new StringBuffer();
		StringBuffer sbfRemark = new StringBuffer();
		if (frm.getOrderSheetRemark().length() != 0) {
			sbfOrderSheetRemark.append(frm.getOrderSheetRemark());
		}
		if (frm.getRemark().length() != 0) {
			sbfRemark.append(frm.getRemark());
		}
		// 取得した備考を全てセット、区切りとして改行を追加
		for (int i = 0; i < frm.getRemarkList().size(); i++) {
			if (frm.getRemarkList().get(i).getRemark13() != null) {
				if (sbfOrderSheetRemark.length() != 0) {
					sbfOrderSheetRemark.append(System
							.getProperty("line.separator"));
				}
				sbfOrderSheetRemark.append(frm.getRemarkList().get(i)
						.getRemark13());
			}
			if (frm.getRemarkList().get(i).getRemark12() != null) {
				if (sbfRemark.length() != 0) {
					sbfRemark.append(System.getProperty("line.separator"));
				}
				sbfRemark.append(frm.getRemarkList().get(i).getRemark12());
			}
		}

		// 画面表示項目にセット
		frm.setOrderSheetRemark(sbfOrderSheetRemark.toString());
		frm.setRemark(sbfRemark.toString());
		// 画面上部にメッセージセット
		saveMessage(request, "message.purchase.getremark.found");

		return mapping.findForward("success");
	}

	/**
	 * ステータス変更処理(詳細画面のステータス変更ボタン押下時(更新))
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward updateStatus(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("updateStatus.");
		}

		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		PurchaseSubcontract detailBean = new PurchaseSubcontract();

		// 更新したい項目と、更新キーのみ値をセットする
		detailBean.setPurchaseNo(frm.getPurchaseNo()); // キー
		detailBean.setUpdateDate(Timestamp.valueOf(frm.getUpdateDate())); // キー
		detailBean.setStatus(PurchaseStatus.NOT_ISSUE); // 購買ステータスを7：未発行へ
		detailBean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 担当者

		// 在庫更新で必要なのでセット
		detailBean.setOrderDivision(frm.getOrderDivision()); // オーダー区分

		try {
			/* 更新処理を実行 */
			purchaseDetailLogic.updateStatus(detailBean);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.update");
			return mapping.getInputForward();
		} catch (LogicExceptionEx e) {
			// 在庫更新に失敗
			if ("errors.purchase.stock.update".equals(e.getMessage())) {
				saveError(request, e.getMessage());
			} else {
				throw e;
			}
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		// 画面を詳細初期表示
		init(mapping, form, request, response);

		return mapping.findForward("success");
	}

	/**
	 * 品目コード入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getItemRelatedData(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getItemRelatedData.");
		}
		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		PurchaseDetail getBean = new PurchaseDetail();

		frm.setItemName(null);
		frm.setOtherCompanyCd(null);
		frm.setVenderCd(null);
		frm.setStrMultiSupplierDivision(null);
		frm.setStrSuppliedDoodsDivision(null);
		frm.setStyleOfPacking(null);
		frm.setUnitOfOperationManagement(null);
		frm.setOrderUnit(null);
		frm.setKgOfFractionManagement(null);
		frm.setTypeDivision(null);
		frm.setSpotDivision(null);
		frm.setLorryDivision(null);
		frm.setOrderDivision(null);
		frm.setLocationCd(null);
		frm.setDeliveryName(null);

		frm.setSupplierName(null);
		frm.setUnitpriceDefineunit(null);
		frm.setUnitPriceUnit(null);
		frm.setChargeOrganizationCd(null);
		frm.setTantoSectionNm(null);

		frm.setStrOrderUnitprice(null);
		frm.setStrSupplierOrdAmount(null);

		frm.setOrderLocation(null);
		frm.setOrderLocationName(null);

		// 品目入力時に連動して変更する項目を検索
		// 品目コードによりDBから値取得
		getBean = purchaseDetailLogic.getItemRelatedData(frm.getItemCd());

		if (getBean == null) {
			return mapping.findForward("success");
		}

		// 品目名称
		frm.setItemName(getBean.getItemName());
		// 他社コード
		frm.setOtherCompanyCd(getBean.getOtherCompanyCd());
		// 仕入先コード
		frm.setVenderCd(getBean.getVenderCd());
		// 複数社購買
		frm.setStrMultiSupplierDivision(getBean.getStrMultiSupplierDivision());
		// 支給品区分
		frm.setStrSuppliedDoodsDivision(getBean.getStrSuppliedDoodsDivision());
		// 荷姿
		frm.setStyleOfPacking(getBean.getStyleOfPacking());
		// 運用管理単位
		frm
				.setUnitOfOperationManagement(getBean
						.getUnitOfOperationManagement());
		// 発注数量の単位の名称
		frm.setOrderUnit(getBean.getOrderUnit());
		// Kg換算係数(在庫)
		frm.setKgOfFractionManagement(getBean.getKgOfFractionManagement());
		// 種別
		frm.setTypeDivision(getBean.getTypeDivision());
		// スポット区分
		if (getBean.getSpotDivision() != null) {
			frm.setSpotDivision(getBean.getSpotDivision().toString());
		}
		// ローリー区分
		frm.setLorryDivision(getBean.getLorryDivision());

		// オーダー区分の決定(品目マスタの種別とスポット区分より)
		frm.setOrderDivision(getOrderDivision(getBean.getTypeDivision()
				.intValue(), getBean.getSpotDivision()));

		// 発注まとめ場所
		frm.setOrderLocation(getBean.getOrderLocation());
		// 発注まとめ場所名称
		frm.setOrderLocationName(getBean.getOrderLocationName());

		// 納入先に基準保管場所をセット
		frm.setLocationCd(getBean.getDefaultLocation());
		// オーダー区分により、ロケーションマスタの名称セット
		if (isLocation(frm.getOrderDivision())) {
			frm.setDeliveryName(getBean.getLocationName());
		}
		// オーダー区分により、納入先マスタの名称セット
		if (isDelivery(frm.getOrderDivision())) {
			frm.setDeliveryName(getBean.getDeliveryName());
			// スポット品の場合
			if (frm.getOrderDivision().compareTo(ORDER4_DIVISION) == 0) {
				if (getBean.getDeliveryName() == null) {
					frm.setDeliveryName(getBean.getLocationName());
				}
			}
		}
		// javascriptでの桁数丸め用に必要となる値取得
		// formに保存
		getCheckDigit(frm, getBean);

		// ****************************************
		// 仕入先入力時に連動して変更する項目を検索
		// ****************************************
		if (getVenderRelatedDataFunc(mapping, form, request, response)) {
			// ****************************************
			// 発注数量入力時に連動して変更する項目を検索
			// ****************************************
			getOrderQuantityRelatedDataFunc(mapping, form, request, response,
				false);
		}
		return mapping.findForward("success");
	}

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getVenderRelatedData(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getVenderRelatedData.");
		}

		// javascriptでの桁数丸め用に必要となる値取得
		// formに保存
		PurchaseDetailForm frm = (PurchaseDetailForm) form;
		getCheckDigit(frm);

		// ****************************************
		// 仕入先入力時に連動して変更する項目を検索
		// ****************************************
		if (getVenderRelatedDataFunc(mapping, form, request, response)) {
			// ****************************************
			// 発注数量入力時に連動して変更する項目を検索
			// ****************************************
			getOrderQuantityRelatedDataFunc(mapping, form, request, response,
				false);
		}
		return mapping.findForward("success");
	}

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public Boolean getVenderRelatedDataFunc(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		PurchaseDetail getBean = new PurchaseDetail();

		frm.setSupplierName(null);
		frm.setUnitpriceDefineunit(null);
		frm.setUnitPriceUnit(null);
		frm.setChargeOrganizationCd(null);
		frm.setTantoSectionNm(null);

		frm.setStrOrderUnitprice(null);
		frm.setStrSupplierOrdAmount(null);

		// 仕入先入力時に連動して変更する項目を検索
		// 品目コード、仕入先コード、発注数量によりDBから値取得
		// 発注数量は、画面表示項目のカンマ編集を解除した値を渡す
		getBean = purchaseDetailLogic.getVenderRelatedData(frm.getItemCd(), frm
				.getVenderCd(), AecNumberUtils.convertBigDecimal(frm
				.getStrOrderQuantity()));

		if (getBean == null) {
			return false;
		}

		// 画面表示項目にセット
		// 仕入先名称
		frm.setSupplierName(getBean.getSupplierName());
		// 単価区分
		if (getBean.getUnitpriceDefineunit() != null) {
			frm.setUnitpriceDefineunit(getBean.getUnitpriceDefineunit()
					.toString());
		}
		// 単価名称
		frm.setUnitPriceUnit(getBean.getUnitpriceUnit());
		// 担当部署コード
		frm.setChargeOrganizationCd(getBean.getChargeOrganizationCd());
		// 担当部署名称
		frm.setTantoSectionNm(getBean.getTantoSectionNm());
		// 免税計算対象フラグ
		frm.setReducedTaxTargetFlg(getBean.getReducedTaxTargetFlg());

		return true;
	}

	/**
	 * 発注数量入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getOrderQuantityRelatedData(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getOrderQuantityRelatedData.");
		}

		getOrderQuantityRelatedDataFunc(mapping, form, request, response, false);

		return mapping.findForward("success");
	}

	/**
	 * 発注数量入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param isUpdate 更新時識別
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public Boolean getOrderQuantityRelatedDataFunc(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response, final boolean isUpdate)
			throws Exception {

		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		PurchaseDetail getBean = new PurchaseDetail();

		// 発注数量が未入力の場合は検索しないで終了。
		if (StringUtils.isEmpty(frm.getStrOrderQuantity())) {
			return false;
		}

		// *************************************
		// 発注数量フォーマット
		// *************************************
		// 発注数量(画面入力した時のままなので、フォーマットなし。だからフォーマットする)(運用管理単位を渡す)
		frm.setStrOrderQuantity(checker.format(frm
				.getUnitOfOperationManagement(), "SI", frm.getVenderCd(),
			AecNumberUtils.convertBigDecimal(frm.getStrOrderQuantity())));

		// DBから取得する箇所をクリアする
		// 単価
		if (!isUpdate) {
			frm.setStrOrderUnitprice(null);
		}
		frm.setStrSupplierOrdAmount(null);

		// *************************************
		// 発注数量入力時に連動して変更する項目を検索
		// 品目コード、仕入先コード、発注数量によりDBから値取得
		// 発注数量は、画面表示項目のカンマ編集を解除した値を渡す
		// *************************************
		getBean = purchaseDetailLogic.getOrderQuantityRelatedData(frm
				.getItemCd(), frm.getVenderCd(), AecNumberUtils
				.convertBigDecimal(frm.getStrOrderQuantity()));

		if (getBean == null) {
			return false;
		}

		if (!isUpdate) {
			// 画面表示項目にセット
			// 発注単価(DBから取得した値を数値フォーマット編集後、画面にセット)
			frm.setStrOrderUnitprice(checker.format("SITANKA", "SI", frm
					.getVenderCd(), getBean.getOrderUnitprice()));
		} else {
			frm.setStrOrderUnitprice(checker.format("SITANKA", "SI", frm
					.getVenderCd(), AecNumberUtils.convertBigDecimal(frm
					.getStrOrderUnitprice())));
		}

		// *************************************
		// 重量・金額の計算で必要な値の取得
		// *************************************
		// 発注数量、カンマ除去
		BigDecimal decQuantity = AecNumberUtils.convertBigDecimal(frm
				.getStrOrderQuantity());
		// 単価、カンマ除去
		BigDecimal decPrice = AecNumberUtils.convertBigDecimal(frm
				.getStrOrderUnitprice());
		// KG換算係数(在庫)、カンマ除去
		BigDecimal decKgOfFraction = AecNumberUtils.convertBigDecimal(frm
				.getKgOfFractionManagement());

		// 単価またはKG換算係数(在庫)が未取得の場合は計算しないで終了。
		if (decPrice == null || decKgOfFraction == null) {
			return false;
		}

		// *************************************
		// 重量計算し、画面にセット
		// *************************************
		// 重量(画面post時の値が入っているので、再計算する)
		frm.setStrOrderConvertQuantity(checker.format(UNIT_DIVITION_KG, "SI",
			frm.getVenderCd(), decQuantity.multiply(decKgOfFraction)));
		// *************************************
		// 金額計算し、画面にセット
		// *************************************
		if (StringUtils.isEmpty(frm.getUnitpriceDefineunit())) {
			// 取引先単価マスタが存在しない場合は、個あたりと同じ計算とする
			// 発注金額
			frm.setStrSupplierOrdAmount(checker.format("SIKINGAKU", "SI", frm
					.getVenderCd(), decQuantity.multiply(decPrice)));
		} else {
			// 個あたり
			if (frm.getUnitpriceDefineunit().equals(UNIT_KO)) {
				// 発注金額
				frm.setStrSupplierOrdAmount(checker.format("SIKINGAKU", "SI",
					frm.getVenderCd(), decQuantity.multiply(decPrice)));
			}
			// kgあたり
			if (frm.getUnitpriceDefineunit().equals(UNIT_KG)) {
				// 発注金額
				frm.setStrSupplierOrdAmount(checker.format("SIKINGAKU", "SI",
					frm.getVenderCd(), decQuantity.multiply(decPrice).multiply(
						decKgOfFraction)));
			}
		}

		return true;
	}

	/**
	 * 登録または更新時、品目コード連動項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getItemRelatedDataForInsUpd(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getItemRelatedDataForInsUpd.");
		}
		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		PurchaseDetail getBean = new PurchaseDetail();

		// frm.setItemName(null);
		frm.setOtherCompanyCd(null);
		frm.setStrMultiSupplierDivision(null);
		frm.setStrSuppliedDoodsDivision(null);
		frm.setStyleOfPacking(null);
		frm.setUnitOfOperationManagement(null);
		frm.setOrderUnit(null);
		frm.setKgOfFractionManagement(null);
		frm.setTypeDivision(null);
		frm.setSpotDivision(null);
		frm.setLorryDivision(null);
		frm.setOrderDivision(null);

		frm.setUnitpriceDefineunit(null);
		frm.setUnitPriceUnit(null);

		frm.setOrderLocation(null);
		frm.setOrderLocationName(null);

		// 品目入力時に連動して変更する項目を検索
		// 品目コードによりDBから値取得
		getBean = purchaseDetailLogic.getItemRelatedData(frm.getItemCd());

		if (getBean == null) {
			return mapping.findForward("success");
		}

		// 品目名称
		if (StringUtils.isEmpty(frm.getItemName())
				|| (getBean.getSpotDivision().compareTo(new BigDecimal(2)) != 0)) {
			frm.setItemName(getBean.getItemName());
		}
		// 他社コード
		frm.setOtherCompanyCd(getBean.getOtherCompanyCd());
		// 複数社購買
		frm.setStrMultiSupplierDivision(getBean.getStrMultiSupplierDivision());
		// 支給品区分
		frm.setStrSuppliedDoodsDivision(getBean.getStrSuppliedDoodsDivision());
		// 荷姿
		frm.setStyleOfPacking(getBean.getStyleOfPacking());
		// 運用管理単位
		frm
				.setUnitOfOperationManagement(getBean
						.getUnitOfOperationManagement());
		// 発注数量の単位の名称
		frm.setOrderUnit(getBean.getOrderUnit());
		// Kg換算係数(在庫)
		frm.setKgOfFractionManagement(getBean.getKgOfFractionManagement());
		// 種別
		frm.setTypeDivision(getBean.getTypeDivision());
		// スポット区分
		if (getBean.getSpotDivision() != null) {
			frm.setSpotDivision(getBean.getSpotDivision().toString());
		}
		// ローリー区分
		frm.setLorryDivision(getBean.getLorryDivision());

		// オーダー区分の決定(品目マスタの種別とスポット区分より)
		frm.setOrderDivision(getOrderDivision(getBean.getTypeDivision()
				.intValue(), getBean.getSpotDivision()));

		// 発注まとめ場所
		frm.setOrderLocation(getBean.getOrderLocation());
		// 発注まとめ場所名称
		frm.setOrderLocationName(getBean.getOrderLocationName());

		// javascriptでの桁数丸め用に必要となる値取得
		// formに保存
		getCheckDigit(frm, getBean);

		// ****************************************
		// 仕入先入力時に連動して変更する項目を検索
		// ****************************************
		if (getVenderRelatedDataFuncForInsUpd(mapping, form, request, response)) {
			// ****************************************
			// 発注数量入力時に連動して変更する項目を検索
			// ****************************************
			getOrderQuantityRelatedDataFunc(mapping, form, request, response,
				true);
		}
		return mapping.findForward("success");
	}

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public Boolean getVenderRelatedDataFuncForInsUpd(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		/* formをPurchaseDetailFormにキャスト */
		PurchaseDetailForm frm = (PurchaseDetailForm) form;

		PurchaseDetail getBean = new PurchaseDetail();

		frm.setSupplierName(null);
		frm.setUnitpriceDefineunit(null);
		frm.setUnitPriceUnit(null);

		// 仕入先入力時に連動して変更する項目を検索
		// 品目コード、仕入先コード、発注数量によりDBから値取得
		// 発注数量は、画面表示項目のカンマ編集を解除した値を渡す
		getBean = purchaseDetailLogic.getVenderRelatedData(frm.getItemCd(), frm
				.getVenderCd(), AecNumberUtils.convertBigDecimal(frm
				.getStrOrderQuantity()));

		if (getBean == null) {
			return false;
		}

		// 画面表示項目にセット
		// 仕入先名称
		frm.setSupplierName(getBean.getSupplierName());
		// 単価区分
		if (getBean.getUnitpriceDefineunit() != null) {
			frm.setUnitpriceDefineunit(getBean.getUnitpriceDefineunit()
					.toString());
		}
		// 単価名称
		frm.setUnitPriceUnit(getBean.getUnitpriceUnit());

		return true;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param frm 発注画面Form
	 * @param bean 発注Bean
	 */
	private void getCheckDigit(final PurchaseDetailForm frm,
			final PurchaseDetail bean) {

		// *******************************
		// 数値桁数チェックマスタメンテから設定を取得
		// *******************************
		NumberChkDisitDetail checkDetail;

		// 発注数量
		checkDetail = checker.getCheckDigit(
			bean.getUnitOfOperationManagement(), "SI", bean.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrOrderQuantityDecimalPoint(checkDetail.getSmallnumLength()
					.toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrOrderQuantityRound(checkDetail.getRoundDivision()
					.toString());
		}

		// 重量
		checkDetail = checker.getCheckDigit(UNIT_DIVITION_KG, "SI", bean
				.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrOrderConvertQuantityDecimalPoint(checkDetail
					.getSmallnumLength().toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrOrderConvertQuantityRound(checkDetail.getRoundDivision()
					.toString());
		}

		// 発注単価
		checkDetail = checker
				.getCheckDigit("SITANKA", "SI", bean.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrOrderUnitpriceDecimalPoint(checkDetail
					.getSmallnumLength().toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrOrderUnitpriceRound(checkDetail.getRoundDivision()
					.toString());
		}

		// 発注金額
		checkDetail = checker.getCheckDigit("SIKINGAKU", "SI", bean
				.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrSupplierOrdAmountDecimalPoint(checkDetail
					.getSmallnumLength().toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrSupplierOrdAmountRound(checkDetail.getRoundDivision()
					.toString());
		}

	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得 （仕入先コード入力時）
	 * @param frm 発注画面Form
	 */
	private void getCheckDigit(final PurchaseDetailForm frm) {

		// *******************************
		// 数値桁数チェックマスタメンテから設定を取得
		// *******************************
		NumberChkDisitDetail checkDetail;

		// 発注数量
		checkDetail = checker.getCheckDigit(frm.getUnitOfOperationManagement(),
			"SI", frm.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrOrderQuantityDecimalPoint(checkDetail.getSmallnumLength()
					.toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrOrderQuantityRound(checkDetail.getRoundDivision()
					.toString());
		}

		// 重量
		checkDetail = checker.getCheckDigit(UNIT_DIVITION_KG, "SI", frm
				.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrOrderConvertQuantityDecimalPoint(checkDetail
					.getSmallnumLength().toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrOrderConvertQuantityRound(checkDetail.getRoundDivision()
					.toString());
		}

		// 発注単価
		checkDetail = checker.getCheckDigit("SITANKA", "SI", frm.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrOrderUnitpriceDecimalPoint(checkDetail
					.getSmallnumLength().toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrOrderUnitpriceRound(checkDetail.getRoundDivision()
					.toString());
		}

		// 発注金額
		checkDetail = checker.getCheckDigit("SIKINGAKU", "SI", frm
				.getVenderCd());
		if (checkDetail.getSmallnumLength() != null) { // 小数点以下桁数
			frm.setStrSupplierOrdAmountDecimalPoint(checkDetail
					.getSmallnumLength().toString());
		}
		if (checkDetail.getRoundDivision() != null) { // 端数区分
			frm.setStrSupplierOrdAmountRound(checkDetail.getRoundDivision()
					.toString());
		}

	}

	/**
	 * オーダー区分より、納入先にロケーションを使用するかどうかを判断する
	 * @param orderDivision オーダー区分
	 * @return Boolean true:ロケーションを使用
	 */
	private Boolean isLocation(final BigDecimal orderDivision) {
		if (orderDivision == null) {
			return false;
		}
		if (orderDivision.compareTo(ORDER1_DIVISION) == 0
				|| orderDivision.compareTo(ORDER2_DIVISION) == 0
				|| orderDivision.compareTo(ORDER3_DIVISION) == 0
				|| orderDivision.compareTo(ORDER6_DIVISION) == 0) {
			return true;
		}

		return false;
	}

	/**
	 * オーダー区分より、納入先に納入先を使用するかどうかを判断する
	 * @param orderDivision オーダー区分
	 * @return Boolean true:納入先を使用
	 */
	private Boolean isDelivery(final BigDecimal orderDivision) {
		if (orderDivision == null) {
			return true;
		}
		if (orderDivision.compareTo(ORDER4_DIVISION) == 0
				|| orderDivision.compareTo(ORDER5_DIVISION) == 0) {
			return true;
		}

		return false;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 発注詳細ロジッククラスを設定します。
	 * @param purchaseDetailLogic 発注詳細ロジッククラス
	 */
	public void setPurchaseDetailLogic(
			final PurchaseDetailLogic purchaseDetailLogic) {
		this.purchaseDetailLogic = purchaseDetailLogic;
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
}
