/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造指図ヘッダー画面
 * @author tosco
 */
public class RdirectionHeaderAction extends AbstractRdirectionAction {

	/** 日時フォーマット */
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";

	/** 日付フォーマット */
	private static final String DAY_FORMAT = "yyyy/MM/dd";

	/** 時刻フォーマット */
	private static final String TIME_FORMAT = "HH:mm";

	/**
	 * コンストラクタ
	 */
	public RdirectionHeaderAction() {
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return RdirectionConst.HEADER;
	}

	/**
	 * 各子クラスの画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	@Override
	protected ActionForward initProcess(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		RdirectionHeaderForm frm = (RdirectionHeaderForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_RDIRECTION,
			Constants.TAB_ID_RDIRECTION_HEADER);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");
	}

	/**
	 * 共通ヘッダー情報を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param header 共通ヘッダー情報
	 * @param request request
	 * @return AbstractMgrecipeForm
	 */
	@Override
	protected AbstractRdirectionForm setCommonHeaderInfo(
			final AbstractRdirectionForm form,
			final RdirectionDirectionHeaderList header,
			final HttpServletRequest request) {
		super.setCommonHeaderInfo(form, header, request);

		RdirectionHeaderForm headerForm = (RdirectionHeaderForm) form;
		headerForm.setLotNo(header.getLotNo());
		headerForm.setRemark(header.getRemark());
		headerForm.setNotes(header.getNotes());
		headerForm.setInputDirectionStatus(getBigDecimalString(header
				.getDirectionStatus()));

		headerForm.setInputResultSdateDay(AecDateUtils.dateFormat(header
				.getResultSdate(), DAY_FORMAT));
		headerForm.setInputResultSdateTime(AecDateUtils.dateFormat(header
				.getResultSdate(), TIME_FORMAT));
		headerForm.setInputSteritSdateDay(AecDateUtils.dateFormat(header
				.getSteritSdate(), DAY_FORMAT));
		headerForm.setInputSteritSdateTime(AecDateUtils.dateFormat(header
				.getSteritSdate(), TIME_FORMAT));
		headerForm.setInputResultEdateDay(AecDateUtils.dateFormat(header
				.getResultEdate(), DAY_FORMAT));
		headerForm.setInputResultEdateTime(AecDateUtils.dateFormat(header
				.getResultEdate(), TIME_FORMAT));
		headerForm.setInputSteritEdateDay(AecDateUtils.dateFormat(header
				.getSteritEdate(), DAY_FORMAT));
		headerForm.setInputSteritEdateTime(AecDateUtils.dateFormat(header
				.getSteritEdate(), TIME_FORMAT));

		return headerForm;
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
		RdirectionHeaderForm headerForm = (RdirectionHeaderForm) form;
		// ステータスチェック処理
		if (checkStatus(headerForm.getDirectionStatus(), headerForm
				.getInputDirectionStatus(), request)) {
			// 更新用データ作成
			RdirectionDirectionHeaderList header = setDirectionHeader(
				headerForm, request);
			try {
				// 処方ヘッダ更新
				rdirectionCommonsLogic.updateDirectionHeader(header);

			} catch (LogicExceptionEx e) {
				if ("errors.rdirection.stock.update".equals(e.getMessage())) {
					// 在庫更新に失敗
					saveError(request, e.getMessage());
					return mapping.getInputForward();
				} else {
					throw e;
				}
			}
			// 処理成功メッセージ
			saveMessage(request, "message.complete.update");
			// 再検索処理
			init(mapping, form, request, response);
			headerForm.setDirtyFlg(null);
		}
		return mapping.findForward("success");
	}

	/**
	 * ステータスの変更チェックを行う。
	 * @param currentStatus 現在のステータス
	 * @param inputStatus 入力したステータス
	 * @param request HttpServletRequest
	 * @return [true:OK][false:NG]
	 */
	private boolean checkStatus(final String currentStatus,
			final String inputStatus, final HttpServletRequest request) {
		boolean res = false;
		BigDecimal current = new BigDecimal(currentStatus);
		BigDecimal input = new BigDecimal(inputStatus);
		if ((RdirectionConst.DIRECTION_STATUS_OUTPUTED.equals(current) && RdirectionConst.DIRECTION_STATUS_COMPLETED
				.equals(input))
				|| current.equals(input)) {
			// 7(製造記録出力済)->8(完了)以外のステータス変更は不可
			res = true;
		} else {
			addError(request, "errors.rdirection.header.not.regist");
		}
		return res;
	}

	/**
	 * 更新用データを作成する
	 * @param headerForm ActionForm
	 * @param request HttpServletRequest
	 * @return DirectionDirectionHeaderList
	 */
	private RdirectionDirectionHeaderList setDirectionHeader(
			final RdirectionHeaderForm headerForm,
			final HttpServletRequest request) {
		RdirectionDirectionHeaderList header = new RdirectionDirectionHeaderList();
		try {
			// 更新用beanに検索時の値をコピー
			IgnoreCaseBeanUtils.copyProperties(header, headerForm
					.getHeaderBean());
			header.setDirectionStatus(new BigDecimal(headerForm
					.getInputDirectionStatus()));
			header.setLotNo(headerForm.getLotNo());
			header.setRemark(headerForm.getRemark());
			header.setNotes(headerForm.getNotes());
			header.setResultSdate(getTimestamp(headerForm
					.getInputResultSdateDay(), headerForm
					.getInputResultSdateTime()));
			header.setResultEdate(getTimestamp(headerForm
					.getInputResultEdateDay(), headerForm
					.getInputResultEdateTime()));
			header.setSteritSdate(getTimestamp(headerForm
					.getInputSteritSdateDay(), headerForm
					.getInputSteritSdateTime()));
			header.setSteritEdate(getTimestamp(headerForm
					.getInputSteritEdateDay(), headerForm
					.getInputSteritEdateTime()));
			Timestamp now = AecDateUtils.getCurrentTimestamp();
			header.setDirectionDate(now);
			String tantoCd = getLoginInfo(request).getTantoCd(); // 担当者コード
			header.setUpdatorCd(tantoCd);

			// 6:FA受信済or7:製造記録出力済の場合→6:FA受信済に更新
			// 製造記録発行済情報をクリア
			BigDecimal status = null;
			if (StringUtils.isNotEmpty(headerForm.getInputDirectionStatus())) {
				status = new BigDecimal(headerForm.getInputDirectionStatus());
			}
			if (!RdirectionConst.DIRECTION_STATUS_COMPLETED.equals(status)) {
				status = null;
				if (StringUtils.isNotEmpty(headerForm.getDirectionStatus())) {
					status = new BigDecimal(headerForm.getDirectionStatus());
				}
				if (RdirectionConst.DIRECTION_STATUS_FA_RECEIVE.equals(status)
						|| RdirectionConst.DIRECTION_STATUS_OUTPUTED
								.equals(status)) {
					header
							.setDirectionStatus(RdirectionConst.DIRECTION_STATUS_FA_RECEIVE);
					header
							.setProductRecordFlag(RdirectionConst.PRODUCT_RECORD_FLAG_UN_ISSUED);
					header.setProductRecordDate(null);
					header.setProductRecordTantoCd(null);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return header;
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
			DATE_FORMAT);
		return stamp;
	}
	// setter-----------------------------------------------------------------------------

}
