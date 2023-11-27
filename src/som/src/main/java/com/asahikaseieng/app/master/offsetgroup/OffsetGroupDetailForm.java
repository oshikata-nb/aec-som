/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.offsetgroup;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 相殺グループマスタ詳細 Formクラス
 * @author tosco
 */
public final class OffsetGroupDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド
	//

	/** 相殺グループコード */
	private String offsetGroupCd;

	/** 相殺グループ名称 */
	private String offsetGroupName;

	/** 請求先/支払先名称 */
	private String venderName;

	/** 選択先 請求先/支払先コード */
	private String[] venderCdSaki;

	/** 選択元 請求先/支払先コード */
	private String[] venderCdMoto;

	/** 選択先 請求先/支払先コードリスト */
	private List<ComboBoxItems> venderSakiList;

	/** 選択元 請求先/支払先コードリスト */
	private List<ComboBoxItems> venderMotoList;

	/** 登録担当者ID */
	private String registTantoCd;

	/** 登録日時 */
	private Timestamp registDate;

	// 新規用切替フラグ
	private int insertFlg;

	/**
	 * コンストラクタ.相殺グループマスタ詳細
	 */
	public OffsetGroupDetailForm() {
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
		if ("initNew".equals(getOp())) {
			clear();
		}
		if ("search".equals(getOp())) {
			// 選択元
			setVenderCdMoto(null);
			// 選択先
			setVenderCdSaki(null);
			// 選択元 コンボ
			setVenderMotoList(null);
			// 選択先 コンボ
			setVenderSakiList(null);
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
			/* コンボボックス再設定 */
			resetComboBox();
		}
		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			/* コンボボックス再設定 */
			resetComboBox();
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		// 相殺グループコード
		setOffsetGroupCd(null);
		// 相殺グループ名称
		setOffsetGroupName(null);
		// 選択元
		setVenderCdMoto(null);
		// 選択先
		setVenderCdSaki(null);
		// 選択元 コンボ
		setVenderMotoList(null);
		// 選択先 コンボ
		setVenderSakiList(null);
		setVenderName(null);

		// 登録日時
		setRegistDate(null);
		// 登録担当者ID
		setRegistTantoCd(null);

		// 新規更新切替フラグ
		setInsertFlg(0);
	}

	/**
	 * 選択元コンボ、選択先コンボを画面で選択された内容に再設定
	 */
	private void resetComboBox() {
		// 選択元
		List<ComboBoxItems> venderListMoto = new ArrayList<ComboBoxItems>();

		if (getVenderCdMoto() != null) {
			for (int i = 0; i < getVenderCdMoto().length; i++) {
				// 再設定
				ComboBoxItems combo = new ComboBoxItems();
				combo.setValues(getVenderCdMoto()[i]);
				combo.setLabales(getVenderCdMoto()[i]);

				venderListMoto.add(combo);
			}
		}

		setVenderMotoList(venderListMoto);

		// 選択先
		List<ComboBoxItems> venderListSaki = new ArrayList<ComboBoxItems>();

		if (getVenderCdSaki() != null) {
			for (int i = 0; i < getVenderCdSaki().length; i++) {
				// 再設定
				ComboBoxItems combo = new ComboBoxItems();
				combo.setValues(getVenderCdSaki()[i]);
				combo.setLabales(getVenderCdSaki()[i]);

				venderListSaki.add(combo);
			}
		}

		setVenderSakiList(venderListSaki);
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
	 * insertFlgを取得します。
	 * 
	 * @return insertFlg
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * insertFlgを設定します。
	 * 
	 * @param insertFlg insertFlg
	 */
	public void setInsertFlg(final int insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * 銀行コードを取得します。
	 * @return offsetGroupCd
	 */
	public String getOffsetGroupCd() {
		return offsetGroupCd;
	}

	/**
	 * 銀行コードを設定します。
	 * @param offsetGroupCd 銀行コード
	 */
	public void setOffsetGroupCd(final String offsetGroupCd) {
		this.offsetGroupCd = offsetGroupCd;
	}

	/**
	 * 銀行名称を取得します。
	 * @return offsetGroupName
	 */
	public String getOffsetGroupName() {
		return offsetGroupName;
	}

	/**
	 * 銀行名称を設定します。
	 * @param offsetGroupName 銀行名称
	 */
	public void setOffsetGroupName(final String offsetGroupName) {
		this.offsetGroupName = offsetGroupName;
	}

	/**
	 * 請求先/支払先リストを取得します。
	 * @return venderCd
	 */
	public String[] getVenderCdSaki() {
		return venderCdSaki;
	}

	/**
	 * 請求先/支払先リストを設定します。
	 * @param venderCdSaki 請求先/支払先リスト
	 */
	public void setVenderCdSaki(final String[] venderCdSaki) {
		this.venderCdSaki = venderCdSaki;
	}

	/**
	 * venderCdMotoを取得します。
	 * @return venderCdMoto
	 */
	public String[] getVenderCdMoto() {
		return venderCdMoto;
	}

	/**
	 * venderCdMotoを設定します。
	 * @param venderCdMoto venderCdMoto
	 */
	public void setVenderCdMoto(final String[] venderCdMoto) {
		this.venderCdMoto = venderCdMoto;
	}

	/**
	 * venderMotoを取得します。
	 * @return venderMoto
	 */
	public List<ComboBoxItems> getVenderMotoList() {
		return venderMotoList;
	}

	/**
	 * venderMotoを設定します。
	 * @param venderMoto venderMoto
	 */
	public void setVenderMotoList(final List<ComboBoxItems> venderMoto) {
		this.venderMotoList = venderMoto;
	}

	/**
	 * venderSakiを取得します。
	 * @return venderSaki
	 */
	public List<ComboBoxItems> getVenderSakiList() {
		return venderSakiList;
	}

	/**
	 * venderSakiを設定します。
	 * @param venderSaki venderSaki
	 */
	public void setVenderSakiList(final List<ComboBoxItems> venderSaki) {
		this.venderSakiList = venderSaki;
	}

	/**
	 * registDateを取得します。
	 * @return registDate
	 */
	public Timestamp getRegistDate() {
		return registDate;
	}

	/**
	 * registDateを設定します。
	 * @param registDate registDate
	 */
	public void setRegistDate(final Timestamp registDate) {
		this.registDate = registDate;
	}

	/**
	 * registTantoCdを取得します。
	 * @return registTantoCd
	 */
	public String getRegistTantoCd() {
		return registTantoCd;
	}

	/**
	 * registTantoCdを設定します。
	 * @param registTantoCd registTantoCd
	 */
	public void setRegistTantoCd(final String registTantoCd) {
		this.registTantoCd = registTantoCd;
	}

	/**
	 * 請求先/支払先名称を取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 請求先/支払先名称を設定します。
	 * @param venderName 請求先/支払先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}
}
