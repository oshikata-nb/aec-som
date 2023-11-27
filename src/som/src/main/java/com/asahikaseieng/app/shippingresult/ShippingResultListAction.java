/*
 * Created on 2009/03/18
 *
 * $copyright$
 * 出荷実績
 *
 */
package com.asahikaseieng.app.shippingresult;

import java.util.ArrayList;

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
import com.asahikaseieng.app.shipping.ShippingLogicException;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultList;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultListPagerCondition;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListConditionForReport;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 出荷実績一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class ShippingResultListAction extends AbstractSearchAction {

	/** 出荷実績一覧ロジッククラス */
	private ShippingResultListLogic shippingResultListLogic;

	/** 出荷実績帳票Excel用 */
	private ShippingResultListExcelDecorator shippingResultListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultListAction() {
		super();
	}

	/**
	 * 初期処理(メニューから遷移時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		ShippingResultListForm listForm = (ShippingResultListForm) form;

		// 運送会社コンボボックス
		listForm
				.setCarryCombo(shippingResultListLogic.createCarryAllCombobox());

		// 初期検索無し
		return mapping.findForward("success");
	}

	/**
	 * 出荷実績検索処理(検索ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		ShippingResultListForm frm = (ShippingResultListForm) form;

		// クリア
		frm.setSearchList(new ArrayList<ShippingResultList>());

		// 検索条件を取得
		ShippingResultListPagerCondition condition = (ShippingResultListPagerCondition) frm
				.getPager().getPagerCondition();

		// 検索条件をセット
		condition.setSrhShippingNo(frm.getSrhShippingNo());
		condition.setSrhScheduledShippingDateFrom(frm
				.getSrhScheduledShippingDateFrom());
		condition.setSrhScheduledShippingDateTo(frm
				.getSrhScheduledShippingDateTo());
		condition.setSrhOrderNoFrom(frm.getSrhOrderNoFrom());
		condition.setSrhOrderNoTo(frm.getSrhOrderNoTo());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhDeliveryCd(frm.getSrhDeliveryCd());
		condition.setSrhShippingStatus(frm.getSrhShippingStatus());
		condition.setSrhCarryCd(frm.getSrhCarryCd());
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		condition.setSrhShippingResultDateFrom(frm
				.getSrhShippingResultDateFrom());
		condition.setSrhShippingResultDateTo(frm.getSrhShippingResultDateTo());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		/* 帳票(Excel)用に検索条件を保持 */
		ShippingResultListConditionForReport reportCondition = new ShippingResultListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(shippingResultListLogic.getSearchList(condition));
		return mapping.findForward("success");
	}

	/**
	 * 完了登録処理(検索画面の完了登録ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward complete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("complete.");
		}
		ShippingResultListForm frm = (ShippingResultListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 完了登録処理を実行
			shippingResultListLogic.complete(frm, tantoCd);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (LogicExceptionEx e) {
			shippingResultListLogic.outPutErrorLog(frm.getErrorCd(), frm
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
				shippingResultListLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.shippingresult.complete");

		return mapping.findForward("reSearch");

	}

	/**
	 * 帳票処理(検索画面の帳票ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}
		ShippingResultListForm frm = (ShippingResultListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = shippingResultListExcelDecorator
				.createReport(frm.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");

	}

	/**
	 * フォームに表示されている項目のクリア処理.
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		ShippingResultListForm frm = (ShippingResultListForm) form;

		// クリア
		frm.clear();

		return mapping.findForward("success");
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
	 * 出荷実績一覧ロジッククラスを設定します。
	 * @param shippingResultListLogic 出荷実績一覧ロジッククラス
	 */
	public void setShippingResultListLogic(
			final ShippingResultListLogic shippingResultListLogic) {
		this.shippingResultListLogic = shippingResultListLogic;
	}

	/**
	 * 帳票Excel用
	 * @param shippingResultListExcelDecorator 帳票Excel用
	 */
	public void setShippingResultListExcelDecorator(
			final ShippingResultListExcelDecorator shippingResultListExcelDecorator) {
		this.shippingResultListExcelDecorator = shippingResultListExcelDecorator;
	}

}
