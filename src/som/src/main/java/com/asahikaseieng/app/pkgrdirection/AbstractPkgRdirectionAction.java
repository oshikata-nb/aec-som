/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionHeaderDetail;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装実績-タブの抽象親クラス Actionクラス.
 * @author tosco
 */
public abstract class AbstractPkgRdirectionAction extends AbstractAction {

	/** 包装実績共通ロジッククラス */
	protected PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic;

	/**
	 * コンストラクタ.
	 */
	public AbstractPkgRdirectionAction() {
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

		AbstractPkgRdirectionForm commonForm = (AbstractPkgRdirectionForm) form;
		//前処理
		beforeInit(mapping, commonForm, request, response);

		//クリアするので、渡された指図区分、包装指図番号を退避
		String directionDivision = commonForm.getDirectionDivision();
		String pkgDirectionNo = commonForm.getDirectionNo();

		//クリア
		commonForm.clear();
		commonForm.setDirectionDivision(directionDivision);
		commonForm.setDirectionNo(pkgDirectionNo);

		//タブIDを設定
		commonForm.setTabId(getTabId());

		//共通情報検索処理
		if (directionDivision != null && pkgDirectionNo != null) {
			PkgRdirectionDirectionHeaderDetail header = null;
			header = pkgRdirectionCommonsLogic.getEntity(directionDivision, pkgDirectionNo);
			setCommonHeaderInfo(commonForm, header, request);
		}

		//各子クラスの初期表示処理
		return initProcess(mapping, commonForm, request, response);
	}
	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	protected abstract String getTabId();
	/**
	 * 各子クラスの画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected abstract ActionForward initProcess(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	/**
	 *
	 * 画面表示初期処理が行われる前に,行う処理
	 * 子クラスで必要ならオーバーライドする
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception Exception
	 */
	protected void beforeInit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("beforeInit.");
		}
		//画面表示初期処理が行われる前に,何か処理を行いたい場合はオーバーライドすること
		//initメソッドでformのクリアを行うので、値を退避するときなどに使用する
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

	/**
	 * 共通ヘッダー情報を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param header 共通ヘッダー情報
	 * @param request request
	 * @return AbstractPkgDirectionForm
	 */
	protected AbstractPkgRdirectionForm setCommonHeaderInfo(final AbstractPkgRdirectionForm form,
			final PkgRdirectionDirectionHeaderDetail header, final HttpServletRequest request) {

		//数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);

		form.setProductionLine(header.getProductionLine());
		form.setProductionLineName(header.getProductionLineName());
		form.setPackageLine(header.getPackageLine());
		form.setItemCd(header.getItemCd());
		form.setItemName(header.getItemName());
		form.setUnitOfOperationManagement(header.getUnitOfOperationManagement());
		form.setOtherCompanyCd1(header.getOtherCompanyCd1());
		form.setParentItemCd(header.getParentItemCd());
		if (header.getRecipeId() != null) {
			form.setRecipeId(header.getRecipeId().toString());
		} else {
			form.setRecipeId(null);
		}
		form.setRecipeCd(header.getRecipeCd());
		form.setRecipeCdVersion(header.getRecipeCdVersion());
		form.setRecipeName(header.getRecipeName());
		form.setRecipeVersion(header.getRecipeVersion());
		String plandQty = checker.format(PkgRdirectionConst.UNIT_DIVISION_HAIGO, header.getPlanedQty());
		form.setPlanedQty(plandQty);
		form.setUnitName(header.getUnitName());
		form.setStyleOfPacking(header.getStyleOfPacking());
		form.setPlanedSdate(AecDateUtils.dateFormat(header.getPlanedSdate(), "yyyy/MM/dd HH:mm"));
		form.setPlanedEdate(AecDateUtils.dateFormat(header.getPlanedEdate(), "yyyy/MM/dd HH:mm"));
		form.setLotNo(header.getLotNo());
		if (header.getDirectionStatus() != null) {
			form.setDirectionStatus(header.getDirectionStatus().toString());
		}
		form.setDirectionStatusName(header.getStatusName());
		form.setPackageLineName(header.getPackageLineName());
		form.setFilltankNo(header.getFilltank());
		form.setFilltankName(header.getFilltankName());
		form.setJissekiFlag(header.getJissekiFlag());
		form.setHeaderUpdateDate(header.getUpdateDate());
		return form;
	}

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	protected void addError(final HttpServletRequest request, final String key, final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	protected String getMessageResource(final HttpServletRequest request, final String key) {
		 MessageResources resource = getResources(request);
		 return resource.getMessage(key);
	}

	/**
	 * BigDecimalの文字列表現を取得する
	 * BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	protected String getBigDecimalString(final BigDecimal dec) {
		String res = null;
		if (dec != null) {
			res = dec.toString();
		}
		return res;
	}

/* -------------------- setter -------------------- */

	/**
	 * 包装実績共通ロジッククラスを設定します。
	 * @param pkgDirectionCommonsLogic 基本処方共通ロジッククラス
	 */
	public void setPkgRdirectionCommonsLogic(final PkgRdirectionCommonsLogic pkgDirectionCommonsLogic) {
		this.pkgRdirectionCommonsLogic = pkgDirectionCommonsLogic;
	}
}
