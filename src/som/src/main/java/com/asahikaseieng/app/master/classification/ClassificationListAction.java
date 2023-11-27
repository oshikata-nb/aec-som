/*
 * Created on 2007/08/08
 *
 * $copyright$
 */
package com.asahikaseieng.app.master.classification;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.dao.nonentity.master.classification.ClassificationList;
import com.asahikaseieng.dao.nonentity.master.classification.ClassificationPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 分類マスタ一覧 Actionクラス.
 * @author tosco
 */
public final class ClassificationListAction extends AbstractSearchAction {

	private ClassificationListLogic classificationListLogic;

	/**
	 * コンストラクタ.分類マスタ一覧
	 */
	public ClassificationListAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		ClassificationListForm frm = (ClassificationListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<ClassificationList>());

		/* 検索条件を取得 */
		ClassificationPagerCondition condition = (ClassificationPagerCondition)
								frm.getPager().getPagerCondition();

		frm.setHidennDataTotalDivision(frm.getSrhDataTotalDivision());

		/* 検索条件をセット */
		BigDecimal numData = null;
		BigDecimal numDataTotalDivision = null;
		if ((frm.getSrhDataType() != null) && (!frm.getSrhDataType().equals(""))) {
			numData = new BigDecimal(frm.getSrhDataType());
		} else {
			frm.setSrhDataType(null);
		}
		if ((frm.getSrhDataTotalDivision()) != null && (!frm.getSrhDataTotalDivision().equals(""))) {
			numDataTotalDivision = new BigDecimal(frm.getSrhDataTotalDivision());
		} else {
			frm.setSrhDataTotalDivision(null);
		}
		condition.setSrhDataType(numData);
		condition.setSrhDataTotalDivision(numDataTotalDivision);
		condition.setSrhCategoryDivision(frm.getSrhCategoryDivision());
		condition.setSrhCategoryName(frm.getSrhCategoryName());

		/* 検索実行 */
		frm.setSearchList(classificationListLogic.getSearchList(condition));

		return mapping.findForward("success");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
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

		ClassificationListForm frm = (ClassificationListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * classificationListLogicを設定します。
	 * @param classificationListLogic classificationListLogic
	 */
	public void setClassificationListLogic(final ClassificationListLogic classificationListLogic) {
		this.classificationListLogic = classificationListLogic;

	}

}
