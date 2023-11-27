/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.organization;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.organization.OrganizationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 部署マスタのAuto Complete用アクション
 * @author tosco
 */
public class OrganizationForAutoCompleteAction extends AbstractAction {

	/** 部署マスタのAuto Complete用ロジック */
	private OrganizationForAutoCompleteLogic organizationForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public OrganizationForAutoCompleteAction() {
	}
	//部署コード・部署名称で検索-----------------------------------------------
	/**
	 * 検索画面用部署マスタ検索
	 * 部署名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchOrganization(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchOrganization.");
		}
		GeneralParameterForm frm = (GeneralParameterForm) form;
		//部署マスタを部署コードまたは部署名称で検索
		try {
			List<OrganizationForAutoComplete> result =
				organizationForAutoCompleteLogic.getSearchList(frm.getCode());
			//Formに設定する
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("部署マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(final List<OrganizationForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (OrganizationForAutoComplete bean : result) {
			GeneralParameterBean organization = new GeneralParameterBean();
			transferEntityData(bean, organization);
			list.add(organization);
		}
		return list;
	}

	/**
	 * 部署マスタデータをオートコンプリート用Beanに移送する
	 * @param source 部署マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final OrganizationForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getOrganizationCd());
		dest.setName(source.getOrganizationName());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * 部署マスタのAuto Complete用ロジックを設定します。
	 * @param organizationForAutoCompleteLogic 部署マスタのAuto Complete用ロジック
	 */
	public void setOrganizationForAutoCompleteLogic(
			final OrganizationForAutoCompleteLogic organizationForAutoCompleteLogic) {
		this.organizationForAutoCompleteLogic = organizationForAutoCompleteLogic;
	}

}
