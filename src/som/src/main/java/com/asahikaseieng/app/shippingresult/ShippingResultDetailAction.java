/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shippingresult;

import java.math.BigDecimal;
import java.util.List;

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
import com.asahikaseieng.app.shipping.ShippingLogicException;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailEntity;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailList;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 出荷実績詳細 Actionクラス.
 * @author tosco
 */
public final class ShippingResultDetailAction extends AbstractAction {

	/** 出荷実績共通ロジッククラス */
	private ShippingResultCommonsLogic shippingResultCommonsLogic;

	/** 出荷実績詳細ロジッククラス */
	private ShippingResultDetailLogic shippingResultDetailLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultDetailAction() {
		super();
	}

	/**
	 * 出荷実績検索処理(一覧画面の出荷番号リンク押下時)
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

		ShippingResultDetailForm frm = (ShippingResultDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SHIPPING_RESULT,
			Constants.TAB_ID_SHIPPING_RESULT_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		try {
			// 初期検索
			ShippingResultDetailEntity bean = shippingResultDetailLogic
					.getEntity(frm.getShippingNo());
			// BeanをFormにコピーする
			setShippingData(frm, bean);

			// 出荷実績詳細情報を取得する
			List<ShippingResultDetailList> list = shippingResultDetailLogic
					.getDetailList(frm.getShippingNo(), bean.getUnitDivision(),
						ShippingResultConst.VENDER_DIVISION_TS, frm
								.getVenderCd());
			frm.setDetailList(list);
			frm.setDirtyFlg(null);

		} catch (NoDataException e) {
			// エラーメッセージ
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");

	}

	/**
	 * 行追加処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("addRow.");
		}

		ShippingResultDetailForm frm = (ShippingResultDetailForm) form;

		List<ShippingResultDetailList> detailList = frm.getDetailList();
		int len = detailList.size();
		boolean addFlg = false;
		for (int i = 0; i < len; i++) {
			ShippingResultDetailList bean = detailList.get(i);
			if (bean.isCheckFlg()) {
				// チェックされている場合、その行の上に追加
				detailList.add(i, getNewDetailLine());
				addFlg = true;
				break;
			}
		}
		// 行が追加されていない場合、最後に追加
		if (!addFlg) {
			detailList.add(getNewDetailLine());
		}

		// チェックボックスクリア
		clearCheckFlg(frm);

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delRow.");
		}

		ShippingResultDetailForm frm = (ShippingResultDetailForm) form;

		List<ShippingResultDetailList> detailList = frm.getDetailList();
		int len = detailList.size();
		for (int i = len - 1; i >= 0; i--) {
			ShippingResultDetailList bean = detailList.get(i);
			if (bean.isCheckFlg()) {
				// 削除対象行
				detailList.remove(i);
			}
		}

		// チェックボックスクリア
		clearCheckFlg(frm);

		return mapping.findForward("success");
	}

	/**
	 * 出荷実績更新処理(詳細画面の更新ボタン押下時)
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
		ShippingResultDetailForm frm = (ShippingResultDetailForm) form;

		// ロケーションコードとロット番号の重複チェック
		if (!isOtherLotLocation(frm, request)) {
			return mapping.findForward("success");
		}

		// 出荷完了以外かつ受注番号ありの場合は、出荷実績量累計チェック
		if (!ShippingResultConst.SHIPPING_STATUS_COMPLETE.toString().equals(
			frm.getShippingStatus())
				&& StringUtils.isNotEmpty(frm.getOrderNo())) {
			if (!isShippingResultQuantityCheck(frm, request)) {
				return mapping.findForward("success");
			}
		}

		// ログイン担当者コード取得
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 更新処理を実行
			shippingResultDetailLogic.update(frm, tantoCd);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (LogicExceptionEx e) {
			shippingResultDetailLogic.outPutErrorLog(frm.getErrorCd(), frm
					.getErrorMsg(), getLoginInfo(request).getTantoCd());
			String errMsg = "errors.shipping.make.sales.error";
			String errMsg2 = "errors.shipping.make.sales.shipping.no";
			String errMsg3 = "errors.shipping.make.sales.order.no";
			if (errMsg.equals(e.getMessage())) {
				saveError(request, errMsg);
				return mapping.getInputForward();
			} else if (errMsg2.equals(e.getMessage())) {
				saveError(request, errMsg2);
				return mapping.getInputForward();
			} else if (errMsg3.equals(e.getMessage())) {
				saveError(request, errMsg3);
				return mapping.getInputForward();

			} else {
				throw e;
			}
		} catch (ShippingLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			if (StringUtils.isNotEmpty(e.getModuleCd())) {
				// エラーログに出力する
				shippingResultDetailLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			return mapping.getInputForward();

		}
		// メッセージ
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");
	}

	/**
	 * 重複ロケ、ロットがないかチェック
	 * @param frm チェック対象フォーム
	 * @param response resource取得用のresponse
	 * @return boolean 全ての項目がマスタにある:true<br>
	 *         マスタにないものがある:false
	 */
	private boolean isOtherLotLocation(final ShippingResultDetailForm frm,
			final HttpServletRequest request) {
		boolean res = true;

		// 品目ループ
		// for (ShippingDetailCompanyList itemList : frm.getDetailList()) {
		int intLineNo = 0;

		// ロケーションループ
		for (ShippingResultDetailList detailList : frm.getDetailList()) {
			intLineNo++;
			if (intLineNo > 1
					&& !isOtherLotLocationOne(detailList, frm.getDetailList())) {
				ActionMessages messages = new ActionMessages();
				messages.add("", new ActionMessage(
						"errors.shipping.same.lot.and.location", Integer
								.toString(intLineNo)));
				addErrors(request, messages);
				res = false;
				return res;

			}
		}

		// }
		return res;

	}

	/**
	 * 重複ロケ、ロットがないかチェック
	 * @param detail 比較対象レコード
	 * @param detailList 出荷明細
	 * @return ShippingDetailOtherForm
	 */
	private boolean isOtherLotLocationOne(
			final ShippingResultDetailList detail,
			final List<ShippingResultDetailList> detailList) {

		BigDecimal count = new BigDecimal(0);
		// ロケーションループ
		for (ShippingResultDetailList ref : detailList) {
			// 同一ロット、同一ロケーションを検索
			if (ref.getLocationCd().equals(detail.getLocationCd())
					&& ref.getLotNo().equals(detail.getLotNo())) {
				count = count.add(BigDecimal.ONE);

				// 複数件ある場合
				if (count.intValue() > 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 戻る処理(詳細画面の戻るボタン押下時)
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
	 * 出荷実績検索結果を画面のFormに設定する。
	 * @param form 出荷実績詳細画面のForm
	 * @param bean 出荷実績検索結果
	 * @return ShippingDetailCompanyForm
	 */
	private ShippingResultDetailForm setShippingData(
			final ShippingResultDetailForm form,
			final ShippingResultDetailEntity bean) {
		form.setShippingNo(bean.getShippingNo()); // 出荷番号
		form.setOrderNo(bean.getOrderNo()); // 受注番号
		form.setOrderRowNo(getBigDecimalString(bean.getOrderRowNo())); // 行番号(受注)
		form.setShippingStatus(getBigDecimalString(bean.getShippingStatus())); // 出荷ステータス
		form.setShippingStatusName(bean.getStrShippingStatus()); // 出荷ステータス名称
		form.setStrShippingInstructDate(bean.getStrShippingInstructDate()); // 出荷指図日
		form.setDeliveryCd(bean.getDeliveryCd()); // 納入先コード
		form.setDeliveryName1(bean.getDeliveryName()); // 納入先名称
		form.setSearchKana(bean.getSearchKana()); // 納入先略称
		form.setDeliveryAddress(bean.getDeliveryAddress()); // 納入先宛先
		form.setItemCd(bean.getItemCd()); // 品目コード
		form.setItemName(bean.getItemName()); // 品目名称
		form.setStyleOfPacking(bean.getStyleOfPacking()); // 荷姿
		form.setOtherCompanyCd1(bean.getOtherCompanyCd1()); // 他社コード1
		form.setBalanceCd(bean.getBalanceCd()); // 帳合コード
		form.setStrSuggestedDeliverlimit(bean.getStrSuggestedDeliverlimit()); // 希望納期
		form.setStrDeliveryExpectedDate(bean.getStrDeliveryExpectedDate()); // 納入予定日

		form.setStrScheduledShippingDate(bean.getStrScheduledShippingDate()); // 出荷予定日
		form.setCarryCd(bean.getCarryCd()); // 運送会社コード
		form.setCarryName(shippingResultCommonsLogic.getCarryName(bean
				.getCarryName1(), bean.getCarryName2())); // 運送会社名称
		form.setStrCarryFare(checker.format(
			ShippingResultConst.UNIT_DIVISION_UNTIN,
			ShippingResultConst.VENDER_DIVISION_TS, bean.getVenderCd(), bean
					.getCarryFare())); // 運賃
		form.setStrShippingResultDate(bean.getStrShippingResultDate()); // 出荷完了日
		form.setStrOrderQty(checker.format(bean.getUnitDivision(),
			ShippingResultConst.VENDER_DIVISION_TS, bean.getVenderCd(), bean
					.getOrderQty())); // 受注量
		form.setStrMatss(checker.format(bean.getUnitDivision(),
			ShippingResultConst.VENDER_DIVISION_TS, bean.getVenderCd(), bean
					.getMatss())); // 増付量
		form.setVenderCd(bean.getVenderCd()); // 取引先コード
		form.setUnitDivision(bean.getUnitDivision()); // 単位
		form.setUpdateDate(bean.getUpdateDate()); // 更新日付
		form.setDeliveryAllAddress(bean.getDeliveryAllAddress());
		setCheckDigit(form);

		return form;
	}

	/**
	 * BigDecimalの文字列表現を取得する BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	private String getBigDecimalString(final BigDecimal dec) {
		String res = null;
		if (dec != null) {
			res = dec.toString();
		}
		return res;
	}

	/**
	 * 出荷実績量チェック
	 * @param frm マスタチェック対象フォーム
	 * @param request HttpServletRequest
	 * @return boolean 出荷実績量の合計 ≠ 受注量 ＋ 増付数 の場合:false
	 */
	private boolean isShippingResultQuantityCheck(
			final ShippingResultDetailForm frm, final HttpServletRequest request) {
		// ActionMessages messages = new ActionMessages();

		// 出荷実績量の累計を算出
		BigDecimal sum = new BigDecimal(0);
		for (ShippingResultDetailList detail : frm.getDetailList()) {
			sum = sum.add(convertBigDecimal(detail
					.getStrShippingResultQuantity()));
		}
		// 受注量 ＝ 受注量 ＋ 増付数
		BigDecimal order = new BigDecimal(0);
		order = order.add(convertBigDecimal(frm.getStrOrderQty())).add(
			convertBigDecimal(frm.getStrMatss()));

		// // 出荷実績量の合計 ＞ 受注量 ＋ 増付数 の場合、エラーとする
		// if (sum.intValue() > order.intValue()) {
		// messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		// "errors.shippingresult.shipping.result.quantity"));
		// saveErrors(request, messages);
		// return false;
		// }

		return true;
	}

	/**
	 * 新規明細行を取得する
	 * @return ShippingShippingDetail
	 */
	private ShippingResultDetailList getNewDetailLine() throws Exception {
		ShippingResultDetailList data = new ShippingResultDetailList();
		data.setCheckFlg(false); // チェックフラグ
		data.setShippingNo(null); // 出荷伝票番号
		data.setShippingRowNo(new BigDecimal(0)); // 行番号(出荷)
		data.setRowNo(null); // 行番号(未使用)
		data.setLocationCd(null); // ロケーションコード
		data.setLocationName(null); // ロケーション名称
		data.setLotNo(null); // ロット番号
		data.setShippingInstruction(null); // 指図量
		return data;
	}

	/**
	 * チェックボックスクリア処理
	 * @param form 出荷実績詳細画面のForm
	 * @return ShippingDetailCompanyForm 画面のForm
	 */
	private ShippingResultDetailForm clearCheckFlg(
			final ShippingResultDetailForm form) {
		// チェックボックスをクリアする
		for (ShippingResultDetailList detail : form.getDetailList()) {
			detail.setCheckFlg(Boolean.FALSE);
		}

		return form;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param form 出荷実績詳細画面のForm
	 */
	private void setCheckDigit(final ShippingResultDetailForm form) {
		String venderDivision = null;
		String venderCd = null;
		if (StringUtils.isNotEmpty(form.getOrderNo())) {
			venderDivision = ShippingResultConst.VENDER_DIVISION_TS;
			venderCd = form.getVenderCd();
		}
		NumberChkDisitDetail detail = checker.getCheckDigit(form
				.getUnitDivision(), venderDivision, venderCd);
		// 小数点桁数
		if (detail.getSmallnumLength() != null) {
			form.setDecimalPoint(detail.getSmallnumLength().toString());
		}
		// 端数区分
		if (detail.getRoundDivision() != null) {
			form.setRound(detail.getRoundDivision().toString());
		}

		// 運賃
		detail = checker.getCheckDigit(ShippingResultConst.UNIT_DIVISION_UNTIN,
			venderDivision, venderCd);
		// 単位区分(運賃)
		form.setUnitDivisionUntin(ShippingResultConst.UNIT_DIVISION_UNTIN);
		// 小数点桁数(運賃)
		if (detail.getSmallnumLength() != null) {
			form.setDecimalPointUntin(detail.getSmallnumLength().toString());
		}
		// 端数区分(運賃)
		if (detail.getRoundDivision() != null) {
			form.setRoundUntin(detail.getRoundDivision().toString());
		}
	}

	/**
	 * StringからBigDecimalへ型変換を行う
	 * @param strVal String値
	 * @return BigDecimal BigDecimal型に変換した値
	 */
	private BigDecimal convertBigDecimal(final String strVal) {
		BigDecimal val = AecNumberUtils.convertNullToZero(AecNumberUtils
				.convertBigDecimal(strVal));
		return val;
	}

	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	private String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	private void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/* -------------------- setter -------------------- */
	/**
	 * 出荷実績共通ロジッククラスを設定します。
	 * @param shippingResultCommonsLogic 出荷実績共通ロジッククラス
	 */
	public void setShippingResultCommonLogic(
			final ShippingResultCommonsLogic shippingResultCommonsLogic) {
		this.shippingResultCommonsLogic = shippingResultCommonsLogic;
	}

	/**
	 * 出荷実績詳細ロジッククラスを設定します。
	 * @param shippingResultDetailLogic 出荷実績詳細ロジッククラス
	 */
	public void setShippingResultDetailLogic(
			final ShippingResultDetailLogic shippingResultDetailLogic) {
		this.shippingResultDetailLogic = shippingResultDetailLogic;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

}
