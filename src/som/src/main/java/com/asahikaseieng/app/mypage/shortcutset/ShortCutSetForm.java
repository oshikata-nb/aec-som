/*
 * Created on 2008/06/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.shortcutset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.struts.AbstractForm;

/**
 * ショートカット設定 Formクラス.
 * @author tosco
 */
public final class ShortCutSetForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//

	/** 担当者コード */
	private String tantoCd;

	/** 変更フラグ */
	private String dirtyFlg;

	/** メニューID */
	private String[] menuIdSaki;

	/** 選択元メニューID */
	private String[] menuIdMoto;

	/** メニューリスト */
	private List<ComboBoxItems> menu;

	/** 選択元メニューリスト */
	private List<ComboBoxItems> menuMoto;

	/**
	 * コンストラクタ.
	 */
	public ShortCutSetForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			// コンボボックス再設定
			resetComboBox();

		}
		return errors;
	}

	/**
	 * 選択元コンボ、選択先コンボを画面で選択された内容に再設定
	 */
	private void resetComboBox() {
		// 選択元
		List<ComboBoxItems> menuListMoto = new ArrayList<ComboBoxItems>();

		if (getMenuIdMoto() != null) {
			for (int i = 0; i < getMenuIdMoto().length; i++) {
				String[] moto = getMenuIdMoto()[i].split(":", 0);
				String value = moto[0];
				BigDecimal decValue = new BigDecimal(value);
				String label = moto[1];

				ComboBoxItems combo = new ComboBoxItems();
				combo.setValues(getMenuIdMoto()[i]);
				combo.setLabales(decValue.toString() + ":" + label);

				menuListMoto.add(combo);
			}
		}
		setMenuMoto(menuListMoto);

		// 選択先
		List<ComboBoxItems> menuList = new ArrayList<ComboBoxItems>();

		if (getMenuIdSaki() != null) {
			for (int i = 0; i < getMenuIdSaki().length; i++) {
				String[] saki = getMenuIdSaki()[i].split(":", 0);
				String value = saki[0];
				BigDecimal decValue = new BigDecimal(value);
				String label = saki[1];

				ComboBoxItems combo = new ComboBoxItems();
				combo.setValues(getMenuIdSaki()[i]);
				combo.setLabales(decValue.toString() + ":" + label);

				menuList.add(combo);
			}
		}
		setMenu(menuList);

	}

	/**
	 * 値をクリアする.
	 */
	private void clear() {
		/** 担当者コード */
		setTantoCd(null);

		/** メニューID */
		setMenuIdSaki(null);

		/** 選択元メニューID */
		setMenuIdMoto(null);

		/** メニューリスト */
		setMenu(null);

		/** 選択元メニューリスト */
		setMenuMoto(null);

	}

	/**
	 * 担当者コード取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * 担当者コード設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * メニューIDを取得します。
	 * @return String[] メニューID
	 */
	public String[] getMenuIdSaki() {
		return menuIdSaki;
	}

	/**
	 * メニューIDを設定します。
	 * @param menuIdSaki メニューID
	 */
	public void setMenuIdSaki(final String[] menuIdSaki) {
		this.menuIdSaki = menuIdSaki;
	}

	/**
	 * 選択元メニューIDを取得します。
	 * @return String[] 選択元メニューID
	 */
	public String[] getMenuIdMoto() {
		return menuIdMoto;
	}

	/**
	 * 選択元メニューIDを設定します。
	 * @param menuIdMoto 選択元メニューID
	 */
	public void setMenuIdMoto(final String[] menuIdMoto) {
		this.menuIdMoto = menuIdMoto;
	}

	/**
	 * メニューリストを取得します。
	 * @return List<ComboBoxItems> メニューリスト
	 */
	public List<ComboBoxItems> getMenu() {
		return menu;
	}

	/**
	 * メニューリストを設定します。
	 * @param menu メニューリスト
	 */
	public void setMenu(final List<ComboBoxItems> menu) {
		this.menu = menu;
	}

	/**
	 * 選択元メニューリストを取得します。
	 * @return List<ComboBoxItems> 選択元メニューリスト
	 */
	public List<ComboBoxItems> getMenuMoto() {
		return menuMoto;
	}

	/**
	 * 選択元メニューリストを設定します。
	 * @param menuMoto 選択元メニューリスト
	 */
	public void setMenuMoto(final List<ComboBoxItems> menuMoto) {
		this.menuMoto = menuMoto;
	}

}
