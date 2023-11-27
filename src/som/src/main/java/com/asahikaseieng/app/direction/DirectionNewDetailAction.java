/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

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
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.mgrecipe.MgrecipeConst;
import com.asahikaseieng.dao.entity.recipeheader.RecipeHeader;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 製造指図-新規入力画面Action
 * @author tosco
 */
public class DirectionNewDetailAction extends AbstractAction {
	/** 新規入力画面ロジッククラス */
	private DirectionNewDetailLogic directionNewDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public DirectionNewDetailAction() {
		super();
	}

	/**
	 * 画面初期表示処理
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

		DirectionNewDetailForm newForm = (DirectionNewDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, newForm, Constants.MENU_ID_DIRECTION,
			Constants.TAB_ID_DIRECTION_HEADER);

		if (!newForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		// 数値フォーマットデータの設定
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			DirectionConst.UNIT_DIVISION_HAIGO, null, null);

		newForm.setDecimalPoint(DirectionUtil.getBigDecimalString(checkDetail
				.getSmallnumLength()));
		newForm.setRoundDivision(DirectionUtil.getBigDecimalString(checkDetail
				.getRoundDivision()));

		return mapping.findForward("success");
	}

	/**
	 * 登録処理
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
		DirectionNewDetailForm newForm = (DirectionNewDetailForm) form;
		String forward = "back";
		// マスタ存在チェック
		if (isCheckMaster(newForm, request)
				&& isCheckRecipeHeader(newForm, request)) {
			// 登録処理
			// 登録データ作成
			DirectionDirectionHeaderList bean = createDirectionHeaderData(
				newForm, request);
			try {
				// 指図ヘッダ登録処理
				directionNewDetailLogic.update(bean, newForm
						.getUnitOfOperationManagement());
			} catch (LogicExceptionEx e) {
				String errMsg = "errors.direction.stock.update";
				if (errMsg.equals(e.getMessage())) {
					// 在庫更新に失敗
					saveError(request, errMsg);
					return mapping.getInputForward();
				} else {
					throw e;
				}
			}
			// 登録完了メッセージ
			saveMessage(request, "message.complete.insert");
		} else {
			// 入力チェックエラー時
			forward = "success";
		}
		return mapping.findForward(forward);
	}

	/**
	 * 各タンクマスタ存在チェック
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	private boolean isCheckMaster(final DirectionNewDetailForm form,
			final HttpServletRequest request) {
		boolean res = true;
		// 調合タンク存在チェック
		try {
			String compoundTankName = directionNewDetailLogic
					.checkExistsCompoundTank(form.getRecipeId(), form
							.getCompoundTankNo());
			form.setCompoundTankName(compoundTankName);
		} catch (NoDataException e) {
			// 処方ASPROVAに存在しなかった場合
			addError(request, "errors.nodata.master",
				"item.direction.compound.tank.no");
			form.setCompoundTankName(null);
			res = false;
		}
		// ホールドタンク存在チェック
		if (StringUtils.isNotEmpty(form.getHoldTankNo())) {
			try {
				String holdTankName = directionNewDetailLogic
						.checkExistsHoldTank(form.getCompoundTankNo(), form
								.getHoldTankNo());
				form.setHoldTankName(holdTankName);
			} catch (NoDataException e) {
				// 前後設備紐付けマスタに存在しなかった場合
				addError(request, "errors.nodata.master",
					"item.direction.hold.tank.no");
				form.setHoldTankName(null);
				res = false;
			}
		}
		// 予備溶解タンク存在チェック
		if (StringUtils.isNotEmpty(form.getDissolutionTankNo())) {
			try {
				String dissolutionTankName = directionNewDetailLogic
						.checkExistsDissolutionTank(form.getProductionLine(),
							form.getDissolutionTankNo());
				form.setDissolutionTankName(dissolutionTankName);
			} catch (NoDataException e) {
				// 設備マスタに存在しなかった場合
				addError(request, "errors.nodata.master",
					"item.direction.dissolution.tank.no");
				form.setDissolutionTankName(null);
				res = false;
			}
		}
		return res;
	}

	/**
	 * 処方ヘッダのデータと仕込み数量のチェックを行う。
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	private boolean isCheckRecipeHeader(final DirectionNewDetailForm form,
			final HttpServletRequest request) {
		boolean res = true;
		// 処方ヘッダ存在チェック
		try {
			RecipeHeader head = directionNewDetailLogic.getRecipeHeader(form
					.getRecipeId());
			// 承認のチェック
			BigDecimal status = new BigDecimal(
					MgrecipeConst.APPROVAL_STATUS_APPROVAL);
			if (head.getApprovalStatus().compareTo(status) != 0) {
				addError(request, "errors.direction.no.recipe.approval");
				return false;
			}
			// ステータスのチェック
			String key = "errors.direction.no.recipe.status";
			String object = null;
			switch (head.getRecipeStatus().intValue()) {
			case 1:
				object = "ラボ使用";
				break;
			case 2:
			case 3:
				break;
			case 4:
				object = "廃棄";
				break;
			case 5:
				object = "改訂中";
				break;
			case 6:
				object = "保留中";
				break;
			default:
				object = "不正";
				break;

			}
			if (StringUtils.isNotEmpty(object)) {
				ActionMessages messages = new ActionMessages();
				messages.add("", new ActionMessage(key, object));
				addErrors(request, messages);
				return false;
			}
			// 有効期限のチェック
			Timestamp sdate = getTimestamp(form.getPlanedSdateDate(), form
					.getPlanedSdateTime());
			if (sdate == null || sdate.compareTo(head.getStartDate()) < 0
					|| sdate.compareTo(head.getEndDate()) > 0) {
				addError(request, "errors.direction.no.recipe.priod");
				return false;
			}
			BigDecimal plandQty = AecNumberUtils.convertBigDecimal(form
					.getPlanedQty());
			// 数量範囲チェック
			if ((plandQty.compareTo(head.getMinQty()) < 0)
					|| (plandQty.compareTo(head.getMaxQty()) > 0)) {
				// 処方ヘッダの最小生産量≦予定数量≦最大生産量範囲エラー
				addError(request, "errors.direction.pland.qty.scope");
				res = false;
			}

			// 単位生産量がゼロでない場合、チェックを行う
			if (head.getUnitQty().compareTo(BigDecimal.ZERO) != 0) {
				// 単位数量チェック(仕込数量÷単位生産量の余り==0でないとエラー)
				BigDecimal[] dividers = plandQty.divideAndRemainder(head
						.getUnitQty());
				// 余り
				if (dividers[1].compareTo(new BigDecimal(0)) != 0) {
					// 余りがあるのでエラー
					addError(request, "errors.direction.pland.qty.unit");
					res = false;
				}
			}
		} catch (NoDataException e) {
			// 処方ヘッダに存在しなかった場合
			addError(request, "errors.nodata.master", "item.mgrecipe.recipe.cd");
			res = false;
		}

		return res;
	}

	/**
	 * 指図ヘッダー登録情報作成
	 * @param form この画面のForm
	 * @param request HttpServletRequest
	 * @return DirectionDirectionHeaderList
	 */
	private DirectionDirectionHeaderList createDirectionHeaderData(
			final DirectionNewDetailForm form, final HttpServletRequest request) {
		Timestamp now = AecDateUtils.getCurrentTimestamp();

		DirectionDirectionHeaderList bean = new DirectionDirectionHeaderList();
		bean.setDirectionDivision(DirectionConst.DIRECTION_DIVISION);
		// DirectionNoはlogic内で発番部品から取得するのこ、ここでは設定しない
		bean.setDirectionDate(now);
		bean.setDirectionStatus(DirectionConst.DIRECTION_STATUS_UN_CONFIRMED);
		bean.setRecipeId(new BigDecimal(form.getRecipeId()));
		bean.setRecipeCd(form.getRecipeCd());
		bean.setRecipeVersion(new BigDecimal(form.getRecipeVersion()));
		bean.setProductionLine(form.getProductionLine());
		bean.setCompoundTankNo(form.getCompoundTankNo());
		bean.setHoldTankNo(form.getHoldTankNo());
		bean.setDissolutionTankNo(form.getDissolutionTankNo());
		bean.setItemCd(form.getItemCd());
		bean
				.setPlanedQty(AecNumberUtils.convertBigDecimal(form
						.getPlanedQty()));
		bean.setPlanedSdate(getTimestamp(form.getPlanedSdateDate(), form
				.getPlanedSdateTime()));
		bean.setPlanedEdate(getTimestamp(form.getPlanedEdateDate(), form
				.getPlanedEdateTime()));
		bean.setStampFlag(DirectionConst.STAMP_FLAG_UN_ISSUANCE);
		bean.setProductLabelFlag(DirectionConst.PRODUCT_LABEL_FLAG_UN_ISSUANCE);
		bean
				.setProductRecordFlag(DirectionConst.PRODUCT_RECORD_FLAG_UN_ISSUANCE);
		bean
				.setStockdissLabelFlag(DirectionConst.STOCKDISS_LABEL_FLAG_UN_ISSUANCE);
		bean.setRemark(form.getRemark());
		bean.setNotes(form.getNotes());
		String tanto = getLoginInfo(request).getTantoCd();
		bean.setInputDate(now);
		bean.setInputorCd(tanto);
		bean.setUpdateDate(now);
		bean.setUpdatorCd(tanto);
		return bean;
	}

	/**
	 * 日付・時刻文字列からTimestampを取得する
	 * @param day 日付文字列
	 * @param time 時刻文字列
	 * @return Timestamp
	 */
	private Timestamp getTimestamp(final String day, final String time) {
		String date = day + " " + time;
		Timestamp stamp = AecDateUtils.getTimestampYmdHmFormat(date,
			"yyyy/MM/dd HH:mm");
		return stamp;
	}

	/**
	 * 戻る処理(戻るボタン押下時)
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

	// ----------------------------------------------------
	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	private void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		int len = objects.length;
		for (int i = 0; i < len; i++) {
			String itemKey = (String) objects[i];
			String value = getMessageResource(request, itemKey);
			if (StringUtils.isNotEmpty(value)) {
				objects[i] = value;
			}
		}
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
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

	/* -------------------- setter -------------------- */

	/**
	 * 新規入力画面ロジッククラスを設定します。
	 * @param directionNewDetailLogic 新規入力画面ロジッククラス
	 */
	public void setDirectionNewDetailLogic(
			final DirectionNewDetailLogic directionNewDetailLogic) {
		this.directionNewDetailLogic = directionNewDetailLogic;
	}

}
