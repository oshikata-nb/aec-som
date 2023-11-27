/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.arrival;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalList;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalListPagerCondition;
import com.asahikaseieng.dao.nonentity.arrivalforreport.ArrivalListConditionForReport;
import com.asahikaseieng.exception.LogicException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 入荷一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class ArrivalListAction extends AbstractSearchAction {

	/** 入荷一覧ロジッククラス */
	private ArrivalListLogic arrivalListLogic;

	/** 製品・原材料ラベルＥＸＣＥＬファイル作成ロジッククラス */
	private ArrivalListExcelDecorator arrivalListExcelDecorator;

	/**  */
	private ArrivalReportListExcelDecorator arrivalReportListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public ArrivalListAction() {
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

		ArrivalListForm frm = (ArrivalListForm) form;
		// 納品希望日にシステム日付を設定
		Timestamp sysdate = AecDateUtils.getCurrentTimestamp();
		frm.setSrhSuggestedDeliverlimitDateFrom(AecDateUtils.dateFormat(
			sysdate, "yyyy/MM/dd"));
		frm.setSrhSuggestedDeliverlimitDateTo(AecDateUtils.dateFormat(sysdate,
			"yyyy/MM/dd"));

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * 検索処理(検索ボタン押下時)
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

		ArrivalListForm frm = (ArrivalListForm) form;

		/* 検索条件をセット */
		ArrivalListPagerCondition condition = searchSet(frm);

		/* 帳票(Excel)用に検索条件を保持 */
		ArrivalListConditionForReport reportCondition = new ArrivalListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		return mapping.findForward("success");
	}

	/**
	 * 更新処理(詳細画面の登録ボタン押下時)
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

		ArrivalListForm frm = (ArrivalListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {

			/* 更新処理を実行 */
			arrivalListLogic.update(frm.getSearchList(), tantoCd);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (LogicException e) {
			// 発番に失敗した場合
			saveError(request, "errors.numbering");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("reSearch");

	}

	/**
	 * ラベル発行処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward issue(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("issue.");
		}

		ArrivalListForm frm = (ArrivalListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		String forward = "success";

		TreeMap<String, String> tMap = new TreeMap<String, String>();

		try {
			for (ArrivalList bean : frm.getSearchList()) {
				// チェック無
				if (!bean.isCheckFlg()) {
					continue;
				}
				if (StringUtils.isEmpty(bean.getLotNo())) {
					// ロット管理しないものは、対象外
					continue;
				}

				/* ラベル発行更新 */
				arrivalListLogic.issue(bean, tantoCd);

				/* 計装IFテーブル更新 */
				arrivalListLogic.insertIfTable(bean);

				if (bean.getLabelCount() != null) {
					// ロット番号とラベル枚数を保持する
					tMap.put(bean.getLotNo(), bean.getLabelCount());
				}
			}

			if (!tMap.isEmpty()) {
				// 製品ラベル作成
				createLabel(frm, request, tMap);
			}

			/* 検索条件をセット */
			searchSet(frm);

		} catch (ArrivalLogicException e) {
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
			frm.setErrMsg(getErrorMessage(request, e.getKey(), replacements));
		}
		return mapping.findForward(forward);
	}

	/**
	 * 製品ラベル作成
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 */
	private void createLabel(final ArrivalListForm frm,
			final HttpServletRequest request, final TreeMap<String, String> tMap) {
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		// 製品ラベルを作成
		info = arrivalListExcelDecorator.createReport(tMap, tantoNm,
			AecDateUtils.getCurrentTimestamp(), request.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(true);
	}

	/**
	 * 帳票処理(検索画面の帳票(Excel)ボタン押下時)
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

		ArrivalListForm frm = (ArrivalListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = arrivalReportListExcelDecorator
				.createReport(frm.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * 検索条件をセット
	 * @param frm ArrivalListForm
	 * @throws NoDataException NoDataException
	 */
	private ArrivalListPagerCondition searchSet(final ArrivalListForm frm)
			throws NoDataException {

		/* クリア */
		frm.setSearchList(new ArrayList<ArrivalList>());

		/* 検索条件を取得 */
		ArrivalListPagerCondition condition = (ArrivalListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition
				.setSrhBuySubcontractOrderNo(frm.getSrhBuySubcontractOrderNo()); // 発注番号
		condition.setSrhStatus(frm.getSrhStatus()); // 購買ステータス
		condition.setSrhItemCd(frm.getSrhItemCd()); // 品目コード
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1()); // 他社コード１
		condition.setSrhVenderCd(frm.getSrhVenderCd()); // 仕入先コード
		condition.setSrhLocationCd(frm.getSrhLocationCd()); // 納入先コード
		condition.setSrhOrderDateFrom(frm.getSrhOrderDateFrom()); // 発注日FROM
		condition.setSrhOrderDateTo(frm.getSrhOrderDateTo()); // 発注日TO
		condition.setSrhSuggestedDeliverlimitDateFrom(frm
				.getSrhSuggestedDeliverlimitDateFrom()); // 納品希望日FROM
		condition.setSrhSuggestedDeliverlimitDateTo(frm
				.getSrhSuggestedDeliverlimitDateTo()); // 納品希望日TO
		condition.setSrhOrderDivision(frm.getSrhOrderDivision()); // オーダー区分
		condition.setSrhChargeOrganizationCd(frm.getSrhChargeOrganizationCd()); // 担当部署コード
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd()); // 部署コード
		condition.setSrhTantoCd(frm.getSrhTantoCd()); // 発注者コード
		condition.setSrhOrderSheetNo(frm.getSrhOrderSheetNo()); // 発注書No

		/* 検索 */
		frm.setSearchList(arrivalListLogic.getSearchList(condition));

		return condition;
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
	 * 置換エラーメッセージを取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param replacements 置換パラメータ
	 */
	private String getErrorMessage(final HttpServletRequest request,
			final String key, final String[] replacements) {
		String errMsg;
		errMsg = getMessageResource(request, key);
		if (replacements != null) {
			int len = replacements.length;
			for (int i = 0; i < len; i++) {
				String repl = "{" + String.valueOf(i) + "}";
				errMsg = StringUtils.replace(errMsg, repl, replacements[i]);
			}
		}
		return errMsg;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 入荷一覧ロジッククラスを設定します。
	 * @param arrivalListLogic 入荷一覧ロジッククラス
	 */
	public void setArrivalListLogic(final ArrivalListLogic arrivalListLogic) {
		this.arrivalListLogic = arrivalListLogic;
	}

	/**
	 * 製品・原材料ラベルＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param arrivalListExcelDecorator 製品・原材料ラベルＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setBeforehandMeltLblListExcelDecorator(
			final ArrivalListExcelDecorator arrivalListExcelDecorator) {
		this.arrivalListExcelDecorator = arrivalListExcelDecorator;
	}

	/**
	 * 帳票ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param arrivalReportListExcelDecorator 帳票ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setArrivalReportListExcelDecorator(
			final ArrivalReportListExcelDecorator arrivalReportListExcelDecorator) {
		this.arrivalReportListExcelDecorator = arrivalReportListExcelDecorator;
	}
}
