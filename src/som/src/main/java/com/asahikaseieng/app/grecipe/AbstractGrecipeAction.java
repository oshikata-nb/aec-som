/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 原処方-タブの抽象親クラス(ヘッダー情報タブは除く) Actionクラス.
 * @author tosco
 */
public abstract class AbstractGrecipeAction extends AbstractAction {

	/** 原処方共通ロジッククラス */
	protected GrecipeCommonsLogic grecipeCommonsLogic;

	/**
	 * コンストラクタ.
	 */
	public AbstractGrecipeAction() {
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

		AbstractGrecipeForm commonForm = (AbstractGrecipeForm) form;
		// 前処理
		beforeInit(mapping, commonForm, request, response);
		// クリアするので、渡されたレシピインデックスを退避
		String recipeId = commonForm.getRecipeId();
		// クリア
		commonForm.clear();
		commonForm.setRecipeId(recipeId);

		// タブIDを設定
		commonForm.setTabId(getTabId());

		// 共通情報検索処理
		GrecipeRecipeHeaderList header = grecipeCommonsLogic
				.getCommonHeader(recipeId);
		setCommonHeaderInfo(commonForm, header);
		// 各子クラスの初期表示処理
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
	 * 画面表示初期処理が行われる前に,行う処理 子クラスで必要ならオーバーライドする
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
		// 画面表示初期処理が行われる前に,何か処理を行いたい場合はオーバーライドすること
		// initメソッドでformのクリアを行うので、値を退避するときなどに使用する
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
	 * @return AbstractGrecipeForm
	 */
	protected AbstractGrecipeForm setCommonHeaderInfo(
			final AbstractGrecipeForm form, final GrecipeRecipeHeaderList header) {
		form.setRecipeCd(header.getRecipeCd());
		form.setRecipeVersion(getBigDecimalString(header.getRecipeVersion()));
		form.setRecipeName(header.getRecipeName());
		form.setRecipeUse(getBigDecimalString(header.getRecipeUse()));
		form.setRecipeUseName(SelectRecipeUse.getName(form.getRecipeUse()));
		form.setProduct(header.getProduct());
		form.setItemName(header.getItemName());
		form.setRecipeStatus(header.getRecipeStatus().toString());
		form.setApprovalStatus(header.getApprovalStatus().toString());
		form.setApprovalStatusName(header.getApprovalStatusName());
		form.setOriginalRecipeId(getBigDecimalString(header
				.getOriginalRecipeId()));
		return form;
	}

	// ----------------------------------------------------
	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	protected void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
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
	protected String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}

	/**
	 * BigDecimalの文字列表現を取得する BigDecimal=null時はnullを返す
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
	 * 原処方共通ロジッククラスを設定します。
	 * @param grecipeCommonsLogic 原処方共通ロジッククラス
	 */
	public void setGrecipeCommonsLogic(
			final GrecipeCommonsLogic grecipeCommonsLogic) {
		this.grecipeCommonsLogic = grecipeCommonsLogic;
	}

}
