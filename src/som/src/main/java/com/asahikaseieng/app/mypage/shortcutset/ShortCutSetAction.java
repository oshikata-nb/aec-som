/*
 * Created on 2008/06/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.shortcutset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.assistance.authority.AuthorityLogic;
import com.asahikaseieng.dao.entity.menu.Menu;
import com.asahikaseieng.dao.nonentity.mypage.shortcutset.ShortCutSet;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * ショートカット設定 Actionクラス.
 * @author tosco
 */
public final class ShortCutSetAction extends AbstractAction {

	/* 権限のアシスタントロジック */
	private AuthorityLogic authorityLogic;

	private ShortCutSetLogic shortCutSetLogic;

	// メニューID桁数
	private static final Integer MENU_ID_LENGTH = 10;

	/**
	 * コンストラクタ.
	 */
	public ShortCutSetAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		ShortCutSetForm frm = (ShortCutSetForm) form;

		/* ユーザー情報をセッションから取り出す */
		String tantoCd = getLoginInfo(request).getTantoCd();
		frm.setTantoCd(tantoCd);

		// 検索処理
		List<ShortCutSet> shortCutSetList = shortCutSetLogic.getEntity(tantoCd);

		// ロールコンボボックス設定
		setComboBox(frm, shortCutSetList);

		return mapping.findForward("success");
	}

	/**
	 * 登録処理.update.ロケーションマスタ
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
		ShortCutSetForm frm = (ShortCutSetForm) form;

		/* 削除対象データを作成する */
		ShortCutSet bean = new ShortCutSet();

		/* 値を削除用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(bean, frm);

		/* 削除処理を実行 */
		shortCutSetLogic.delete(bean);

		/* 更新対象データを取得する */
		String[] menuArray = frm.getMenuIdSaki();
		if (menuArray != null) {
			for (int i = 0; i < menuArray.length; i++) {
				/* 更新対象データを作成する */
				ShortCutSet newBean = new ShortCutSet();

				BigDecimal menuValue = new BigDecimal(menuArray[i].substring(0,
					MENU_ID_LENGTH));
				newBean.setMenuId(menuValue);
				newBean.setTantoCd(frm.getTantoCd());
				BigDecimal seqNoValue = new BigDecimal(i);
				newBean.setSeqNo(seqNoValue);

				/* 更新処理を実行 */
				shortCutSetLogic.insert(newBean);
			}
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");

	}

	/**
	 * 戻る処理.
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
	 * ロールのコンボボックス設定(変更画面)
	 * 
	 * @param frm 詳細画面Form
	 * @param strings 検索結果リスト
	 * @return List<ComboBoxItems> ロールのコンボボックス
	 * @throws NoDataException データ無しの例外
	 */
	private void setComboBox(final ShortCutSetForm frm,
			final List<ShortCutSet> list) throws NoDataException {

		// 複数ロール格納
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				String menuId = list.get(i).getMenuId().toString();
				map.put(menuId, menuId);
			}
		}

		/* 担当者・所属に紐づくロールを取得 */
		String[] tantoRoleIds = authorityLogic.getTantoRoleList(frm
				.getTantoCd(), null, null);
		String[] belongRoleIds = authorityLogic.getBelongRoleList(frm
				.getTantoCd(), null, null);

		// ロール一覧取得
		List<Menu> menuList = shortCutSetLogic.getMenus(tantoRoleIds,
			belongRoleIds);

		// 選択元コンボ
		List<ComboBoxItems> menuListMoto = new ArrayList<ComboBoxItems>();

		// 選択先コンボ
		List<ComboBoxItems> menuListSaki = new ArrayList<ComboBoxItems>();

		for (int i = 0; i < menuList.size(); i++) {
			Menu menu = menuList.get(i);

			// フォルダは除く
			if (menu.getMenuTypeId().equals("1")) {
				continue;
			}

			String menuId = menu.getMenuId().toString();
			String menuName = menu.getMenuName();

			ComboBoxItems combo = new ComboBoxItems();

			// メニューID(ゼロサプレス)＋ロール名称設定(Value値)
			StringBuffer zero = new StringBuffer();
			for (int j = 0; j < MENU_ID_LENGTH - menuId.length(); j++) {
				zero.append("0");
			}
			combo.setValues(zero + menuId + ":" + menuName);

			// メニューID＋ロール名称設定(ラベル)
			combo.setLabales(menuId + ":" + menuName);

			if (map.get(menuId) != null) {
				// 選択先コンボ
				menuListSaki.add(combo);
			} else {
				// 選択元コンボ
				menuListMoto.add(combo);
			}
		}
		frm.setMenuMoto(menuListMoto);
		frm.setMenu(menuListSaki);
	}

	/* -------------------- setter -------------------- */

	/**
	 * authorityLogicを設定します。
	 * 
	 * @param authorityLogic AuthorityLogic
	 */
	public void setAuthorityLogic(final AuthorityLogic authorityLogic) {
		this.authorityLogic = authorityLogic;
	}

	/**
	 * 
	 * 
	 * @param shortCutSetLogic shortCutSetLogic
	 */
	public void setShortCutSetLogic(final ShortCutSetLogic shortCutSetLogic) {
		this.shortCutSetLogic = shortCutSetLogic;
	}

}
