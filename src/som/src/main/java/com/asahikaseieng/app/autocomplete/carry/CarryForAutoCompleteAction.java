/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.carry;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.carry.CarryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 運送会社のAuto Complete用アクション
 * @author t0011036
 */
public class CarryForAutoCompleteAction extends AbstractAction {

	/** 運送会社のAuto Complete用ロジック */
	private CarryForAutoCompleteLogic carryAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public CarryForAutoCompleteAction() {
	}

	/**
	 * 検索画面用運送会社検索 運送会社名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchCarry(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchCarry.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 運送会社を運送会社コードまたは運送会社名称で検索 */
		try {
			List<CarryForAutoComplete> result = carryAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<CarryForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("運送会社検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<CarryForAutoCompleteBean> getAutoCompleteBean(
			final List<CarryForAutoComplete> result) {
		List<CarryForAutoCompleteBean> list = new ArrayList<CarryForAutoCompleteBean>();

		for (CarryForAutoComplete bean : result) {
			CarryForAutoCompleteBean item = new CarryForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 運送会社データをオートコンプリート用Beanに移送する
	 * @param source運送会社データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private CarryForAutoCompleteBean transferEntityData(
			final CarryForAutoComplete source,
			final CarryForAutoCompleteBean dest) {
		dest.setCode(source.getCarryCd());
		dest.setName(source.getCarryName1());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * carryAutoCompleteLogicを設定します。
	 * @param carryAutoCompleteLogic carryAutoCompleteLogic
	 */
	public void setCarryAutoCompleteLogic(
			final CarryForAutoCompleteLogic carryAutoCompleteLogic) {
		this.carryAutoCompleteLogic = carryAutoCompleteLogic;
	}
}
