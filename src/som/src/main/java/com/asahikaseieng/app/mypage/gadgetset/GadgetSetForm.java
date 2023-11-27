/*
 * Created on 2008/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.gadgetset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.struts.AbstractForm;

/**
 * ガジェット設定 Formクラス
 * @author tosco
 */
public final class GadgetSetForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド

	//

	// ガジェットID(１列目)
	private String[] gadgetId1;

	// ガジェットID(２列目)
	private String[] gadgetId2;

	// 選択元ガジェットID
	private String[] gadgetIdMoto;

	// ガジェットリスト(１列目)
	private List<ComboBoxItems> gadget1;

	// ガジェットリスト(２列目)
	private List<ComboBoxItems> gadget2;

	// 選択元ガジェットリスト
	private List<ComboBoxItems> gadgetMoto;

	/**
	 * コンストラクタ.ロケーションマスタ詳細
	 */
	public GadgetSetForm() {
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

		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		/** ガジェットID(１列目) */
		setGadgetId1(null);

		/** ガジェットID(２列目) */
		setGadgetId2(null);

		/** 選択元ガジェットID */
		setGadgetIdMoto(null);

		/** ガジェットリスト(１列目) */
		setGadget1(null);

		/** ガジェットリスト(２列目) */
		setGadget2(null);

		/** 選択元ガジェットリスト */
		setGadgetMoto(null);

	}

	//	
	// インスタンスメソッド
	//		
	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * ガジェットID(１列目)を取得します。
	 * 
	 * @return String[] ガジェットID(１列目)
	 */
	public String[] getGadgetId1() {
		return gadgetId1;
	}

	/**
	 * ガジェットID(１列目)を設定します。
	 * 
	 * @param gadgetId1 ガジェットID(１列目)
	 */
	public void setGadgetId1(final String[] gadgetId1) {
		this.gadgetId1 = gadgetId1;
	}

	/**
	 * ガジェットID(２列目)を取得します。
	 * 
	 * @return String[] ガジェットID(２列目)
	 */
	public String[] getGadgetId2() {
		return gadgetId2;
	}

	/**
	 * ガジェットID(２列目)を設定します。
	 * 
	 * @param gadgetId2 ガジェットID(２列目)
	 */
	public void setGadgetId2(final String[] gadgetId2) {
		this.gadgetId2 = gadgetId2;
	}

	/**
	 * 選択元ガジェットIDを取得します。
	 * 
	 * @return String[] 選択元ガジェットID
	 */
	public String[] getGadgetIdMoto() {
		return gadgetIdMoto;
	}

	/**
	 * 選択元ガジェットIDを設定します。
	 * 
	 * @param gadgetIdMoto 選択元ガジェットID
	 */
	public void setGadgetIdMoto(final String[] gadgetIdMoto) {
		this.gadgetIdMoto = gadgetIdMoto;
	}

	/**
	 * ガジェットリスト(１列目)を取得します。
	 * 
	 * @return List<ComboBoxItems> ガジェットリスト(１列目)
	 */
	public List<ComboBoxItems> getGadget1() {
		return gadget1;
	}

	/**
	 * ガジェットリスト(１列目)を設定します。
	 * 
	 * @param gadget1 ガジェットリスト(１列目)
	 */
	public void setGadget1(final List<ComboBoxItems> gadget1) {
		this.gadget1 = gadget1;
	}

	/**
	 * ガジェットリスト(２列目)を取得します。
	 * 
	 * @return List<ComboBoxItems> ガジェットリスト(２列目)
	 */
	public List<ComboBoxItems> getGadget2() {
		return gadget2;
	}

	/**
	 * ガジェットリスト(２列目)を設定します。
	 * 
	 * @param gadget2 ガジェットリスト(２列目)
	 */
	public void setGadget2(final List<ComboBoxItems> gadget2) {
		this.gadget2 = gadget2;
	}

	/**
	 * 選択元ガジェットリストを取得します。
	 * 
	 * @return List<ComboBoxItems> 選択元ガジェットリスト
	 * 
	 */
	public List<ComboBoxItems> getGadgetMoto() {
		return gadgetMoto;
	}

	/**
	 * 選択元ガジェットリストを設定します。
	 * 
	 * @param gadgetMoto 選択元ガジェットリスト
	 * 
	 */
	public void setGadgetMoto(final List<ComboBoxItems> gadgetMoto) {
		this.gadgetMoto = gadgetMoto;
	}

}
