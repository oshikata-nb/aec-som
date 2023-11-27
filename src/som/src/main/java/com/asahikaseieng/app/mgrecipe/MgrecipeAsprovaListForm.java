/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;

/**
 * Asprova一覧タブ Formクラス.
 * @author tosco
 */
public final class MgrecipeAsprovaListForm extends AbstractMgrecipeForm {

	private static final long serialVersionUID = 1L;

	/** 検索状態 未検索 */
	public static final String SEARCH_DIVISION_INIT = "0";

	/** 検索状態 検索済み 表示データ RECIPE_ASPROVA */
	public static final String SEARCH_DIVISION_ASPROVA = "1";

	/** 検索状態 検索済み 表示データ RECIPE_RESOUCE */
	public static final String SEARCH_DIVISION_RESOUCE = "2";

	/** 存在フラグ 下段リスト[0:存在しない、1:存在する] */
	private String dispLowerListFlg;

	/** 上段 工程グループコード */
	private String upperOpeGrpCd;

	/** 上段 工程グループ名称 */
	private String upperOpeGrpNm;

	/** 上段 設備グループコンボボックス */
	private List<ComboBoxItems> upperResGrpCombo;

	/** 上段 設備グループコード */
	private String upperResGrpCd;

	/** 上段 TEMP 設備グループコード */
	private String tempUpperResGrpCd;

	/** 上段 要員数 */
	private String upperYouinsu;

	/** 上段 検索フラグ[0:未検索、1:RECIPE_ASPROVA、2:RECIPE_RESOUCE] */
	private String upperSearchDiv;

	/** 上段 検索結果リスト */
	private List<RecipeAsprovaList> upperSearchList;

	/** 下段 工程グループコード */
	private String lowerOpeGrpCd;

	/** 下段 工程グループ名称 */
	private String lowerOpeGrpNm;

	/** 下段 設備グループコンボボックス */
	private List<ComboBoxItems> lowerResGrpCombo;

	/** 下段 設備グループコード */
	private String lowerResGrpCd;

	/** 下段 TEMP 設備グループコード */
	private String tempLowerResGrpCd;

	/** 下段 要員数 */
	private String lowerYouinsu;

	/** 下段 検索フラグ[0:未検索、1:RECIPE_ASPROVA、2:RECIPE_RESOUCE] */
	private String lowerSearchDiv;

	/** 下段 検索結果リスト */
	private List<RecipeAsprovaList> lowerSearchList;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeAsprovaListForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);
		if ("init".equals(getOp())) {
			clear();
		}

		if ("regist".equals(getOp())) {
			/* チェックボックスクリア処理 */
			clearCheck();
		}
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("searchUpperList".equals(getOp())) {
			// 上段リストの初期化
			setUpperSearchList(new ArrayList<RecipeAsprovaList>());
			/* 上段 要員数 */
			setUpperYouinsu(null);
		}
		if ("searchLowerList".equals(getOp())) {
			// 下段リストの初期化
			setLowerSearchList(new ArrayList<RecipeAsprovaList>());
			/* 下段 要員数 */
			setLowerYouinsu(null);
		}
		if ("regist".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();
		/* 下段リスト存在フラグ */
		setDispLowerListFlg(null);

		/* 上段 工程グループコード */
		setUpperOpeGrpCd(null);
		/* 上段 工程グループ名称 */
		setUpperOpeGrpNm(null);
		/* 上段 設備グループコンボボックス */
		setUpperResGrpCombo(null);
		/* 上段 設備グループコード */
		setUpperResGrpCd(null);
		/* 上段 Temp設備グループコード */
		setTempUpperResGrpCd(null);
		/* 上段 要員数 */
		setUpperYouinsu(null);
		/* 上段 検索フラグ */
		setUpperSearchDiv(SEARCH_DIVISION_INIT);
		/* 上段 検索結果リスト */
		setUpperSearchList(null);

		/* 下段 工程グループコード */
		setLowerOpeGrpCd(null);
		/* 下段 工程グループ名称 */
		setLowerOpeGrpNm(null);
		/* 下段 設備グループコンボボックス */
		setLowerResGrpCombo(null);
		/* 下段 設備グループコード */
		setLowerResGrpCd(null);
		/* 下段 Temp設備グループコード */
		setTempLowerResGrpCd(null);
		/* 下段 要員数 */
		setLowerYouinsu(null);
		/* 下段 検索結果リスト */
		setLowerSearchDiv(SEARCH_DIVISION_INIT);
		/* 下段 検索結果リスト */
		setLowerSearchList(null);
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getUpperSearchList() != null) {
			for (RecipeAsprovaList bean : getUpperSearchList()) {
				bean.setCheckFlg(Boolean.FALSE);
			}
		}

		if (getLowerSearchList() != null) {
			for (RecipeAsprovaList bean : getLowerSearchList()) {
				bean.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	// getter,setter

	/**
	 * 下段リスト存在フラグを取得します。
	 * @return dispLowerListFlg
	 */
	public String getDispLowerListFlg() {
		return dispLowerListFlg;
	}

	/**
	 * 下段リスト存在フラグ[0:存在しない、1:存在する]を設定します。
	 * @param dispLowerListFlg 下段リスト存在フラグ
	 */
	public void setDispLowerListFlg(final String dispLowerListFlg) {
		this.dispLowerListFlg = dispLowerListFlg;
	}

	/**
	 * 上段 工程グループコードを取得します。
	 * @return upperOpeGrpCd
	 */
	public String getUpperOpeGrpCd() {
		return upperOpeGrpCd;
	}

	/**
	 * 上段 工程グループコードを設定します。
	 * @param upperOpeGrpCd 上段 工程グループコード
	 */
	public void setUpperOpeGrpCd(final String upperOpeGrpCd) {
		this.upperOpeGrpCd = upperOpeGrpCd;
	}

	/**
	 * 上段 工程グループ名称を取得します。
	 * @return upperOpeGrpNm
	 */
	public String getUpperOpeGrpNm() {
		return upperOpeGrpNm;
	}

	/**
	 * 上段 工程グループ名称を設定します。
	 * @param upperOpeGrpNm 上段 工程グループ名称
	 */
	public void setUpperOpeGrpNm(final String upperOpeGrpNm) {
		this.upperOpeGrpNm = upperOpeGrpNm;
	}

	/**
	 * 上段 設備グループコードを取得します。
	 * @return upperResGrpCd
	 */
	public String getUpperResGrpCd() {
		return upperResGrpCd;
	}

	/**
	 * 上段 設備グループコードを設定します。
	 * @param upperResGrpCd 上段 設備グループコード
	 */
	public void setUpperResGrpCd(final String upperResGrpCd) {
		this.upperResGrpCd = upperResGrpCd;
	}

	/**
	 * 上段 設備グループコンボボックスを取得します。
	 * @return upperResGrpCombo
	 */
	public List<ComboBoxItems> getUpperResGrpCombo() {
		return upperResGrpCombo;
	}

	/**
	 * 上段 設備グループコンボボックスを設定します。
	 * @param upperResGrpCombo 上段 設備グループコンボボックス
	 */
	public void setUpperResGrpCombo(final List<ComboBoxItems> upperResGrpCombo) {
		this.upperResGrpCombo = upperResGrpCombo;
	}

	/**
	 * 上段 Temp設備グループコードを取得します。
	 * @return tempUpperResGrpCd
	 */
	public String getTempUpperResGrpCd() {
		return tempUpperResGrpCd;
	}

	/**
	 * 上段 Temp設備グループコードを設定します。
	 * @param tempUpperResGrpCd 上段 Temp設備グループコード
	 */
	public void setTempUpperResGrpCd(final String tempUpperResGrpCd) {
		this.tempUpperResGrpCd = tempUpperResGrpCd;
	}

	/**
	 * 上段 要員数を取得します。
	 * @return upperYouinsu
	 */
	public String getUpperYouinsu() {
		return upperYouinsu;
	}

	/**
	 * 上段 要員数を設定します。
	 * @param upperYouinsu 上段 要員数
	 */
	public void setUpperYouinsu(final String upperYouinsu) {
		this.upperYouinsu = upperYouinsu;
	}

	/**
	 * 上段 検索結果リストを取得します。
	 * @return upperSearchList
	 */
	public List<RecipeAsprovaList> getUpperSearchList() {
		return upperSearchList;
	}

	/**
	 * 上段 検索結果リストを設定します。
	 * @param upperSearchList 上段 検索結果リスト
	 */
	public void setUpperSearchList(final List<RecipeAsprovaList> upperSearchList) {
		this.upperSearchList = upperSearchList;
	}

	/**
	 * 上段 検索フラグを取得します。
	 * @return upperSearchDiv
	 */
	public String getUpperSearchDiv() {
		return upperSearchDiv;
	}

	/**
	 * 上段 検索フラグ[0:未検索、1:RECIPE_ASPROVA、2:RECIPE_RESOUCE]を設定します。
	 * @param upperSearchDiv 上段 検索フラグ
	 */
	public void setUpperSearchDiv(final String upperSearchDiv) {
		this.upperSearchDiv = upperSearchDiv;
	}

	/**
	 * 上段 検索結果の件数を取得する
	 * @return 上段 件数
	 */
	public int getUpperCount() {
		int upperCount = 0;
		if (upperSearchList != null) {
			upperCount = upperSearchList.size();
		}
		return upperCount;
	}

	/**
	 * 下段 工程グループコードを取得します。
	 * @return lowerOpeGrpCd
	 */
	public String getLowerOpeGrpCd() {
		return lowerOpeGrpCd;
	}

	/**
	 * 下段 工程グループコードを設定します。
	 * @param lowerOpeGrpCd 下段 工程グループコード
	 */
	public void setLowerOpeGrpCd(final String lowerOpeGrpCd) {
		this.lowerOpeGrpCd = lowerOpeGrpCd;
	}

	/**
	 * 下段 工程グループ名称を取得します。
	 * @return lowerOpeGrpNm
	 */
	public String getLowerOpeGrpNm() {
		return lowerOpeGrpNm;
	}

	/**
	 * 下段 工程グループ名称を設定します。
	 * @param lowerOpeGrpNm 下段 工程グループ名称
	 */
	public void setLowerOpeGrpNm(final String lowerOpeGrpNm) {
		this.lowerOpeGrpNm = lowerOpeGrpNm;
	}

	/**
	 * 下段 設備グループコードを取得します。
	 * @return lowerResGrpCd
	 */
	public String getLowerResGrpCd() {
		return lowerResGrpCd;
	}

	/**
	 * 下段 設備グループコードを設定します。
	 * @param lowerResGrpCd 下段 設備グループコード
	 */
	public void setLowerResGrpCd(final String lowerResGrpCd) {
		this.lowerResGrpCd = lowerResGrpCd;
	}

	/**
	 * 下段 設備グループコンボボックスを取得します。
	 * @return lowerResGrpCombo
	 */
	public List<ComboBoxItems> getLowerResGrpCombo() {
		return lowerResGrpCombo;
	}

	/**
	 * 下段 設備グループコンボボックスを設定します。
	 * @param lowerResGrpCombo 下段 設備グループコンボボックス
	 */
	public void setLowerResGrpCombo(final List<ComboBoxItems> lowerResGrpCombo) {
		this.lowerResGrpCombo = lowerResGrpCombo;
	}

	/**
	 * 下段 検索フラグ[0:未検索、1:RECIPE_ASPROVA、2:RECIPE_RESOUCE]を取得します。
	 * @return lowerSearchDiv
	 */
	public String getLowerSearchDiv() {
		return lowerSearchDiv;
	}

	/**
	 * 下段 検索フラグ[0:未検索、1:RECIPE_ASPROVA、2:RECIPE_RESOUCE]を設定します。
	 * @param lowerSearchDiv 下段 検索フラグ[0:未検索、1:RECIPE_ASPROVA、2:RECIPE_RESOUCE]
	 */
	public void setLowerSearchDiv(final String lowerSearchDiv) {
		this.lowerSearchDiv = lowerSearchDiv;
	}

	/**
	 * 下段 検索結果リストを取得します。
	 * @return lowerSearchList
	 */
	public List<RecipeAsprovaList> getLowerSearchList() {
		return lowerSearchList;
	}

	/**
	 * 下段 検索結果リストを設定します。
	 * @param lowerSearchList 下段 検索結果リスト
	 */
	public void setLowerSearchList(final List<RecipeAsprovaList> lowerSearchList) {
		this.lowerSearchList = lowerSearchList;
	}

	/**
	 * 下段 TEMP 設備グループコードを取得します。
	 * @return tempLowerResGrpCd
	 */
	public String getTempLowerResGrpCd() {
		return tempLowerResGrpCd;
	}

	/**
	 * 下段 TEMP 設備グループコードを設定します。
	 * @param tempLowerResGrpCd 下段 TEMP 設備グループコード
	 */
	public void setTempLowerResGrpCd(final String tempLowerResGrpCd) {
		this.tempLowerResGrpCd = tempLowerResGrpCd;
	}

	/**
	 * 下段 要員数を取得します。
	 * @return lowerYouinsu
	 */
	public String getLowerYouinsu() {
		return lowerYouinsu;
	}

	/**
	 * 下段 要員数を設定します。
	 * @param lowerYouinsu 下段 要員数
	 */
	public void setLowerYouinsu(final String lowerYouinsu) {
		this.lowerYouinsu = lowerYouinsu;
	}

	/**
	 * 下段 検索結果の件数を取得する
	 * @return 件数
	 */
	public int getLowerCount() {
		int lowerCount = 0;
		if (lowerSearchList != null) {
			lowerCount = lowerSearchList.size();
		}
		return lowerCount;
	}

	/**
	 * 登録ボタン状態を取得する
	 * @return 件数
	 */
	public String getRegistFlg() {
		// 下段がある場合
		if (dispLowerListFlg.equals("1")) {
			if (upperSearchDiv.equals(SEARCH_DIVISION_INIT)
					&& lowerSearchDiv.equals(SEARCH_DIVISION_INIT)) {
				return SEARCH_DIVISION_INIT;
			} else if (upperSearchDiv.equals(SEARCH_DIVISION_RESOUCE)
					|| lowerSearchDiv.equals(SEARCH_DIVISION_RESOUCE)) {
				return SEARCH_DIVISION_RESOUCE;
			}
			return SEARCH_DIVISION_ASPROVA;
		} else {
			// 下段がない場合
			return upperSearchDiv;
		}
	}

	/**
	 * srhLinkを取得します。
	 * @return srhLink
	 */
	public String getSrhLink() {
		return srhLink;
	}

	/**
	 * srhLinkを設定します。
	 * @param srhLink srhLink
	 */
	public void setSrhLink(final String srhLink) {
		this.srhLink = srhLink;
	}
}
