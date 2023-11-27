/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureList;

/**
 * 包装実績－工程一覧画面 Formクラス
 * @author tosco
 */
public class PkgRdirectionProcedureListForm extends AbstractPkgRdirectionForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 検索結果リスト */
	private List<PkgRdirectionDirectionProcedureList> searchProcList;

	/** 工程検索行番号 */
	private String line;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionProcedureListForm() {
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
		} else if ("addlist".equals(getOp())) {
			clearCheck();
		} else if ("dellist".equals(getOp())) {
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
		if ("regist".equals(getOp())) {
			/* Validatorによる判定 */
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
		if (!"reInit".equals(getOp())) {
			// 検索結果リスト
			setSearchProcList(new ArrayList<PkgRdirectionDirectionProcedureList>());
		}
		// 工程検索行番号
		setLine(null);
	}

	/**
	 * 検索結果リスト取得
	 * @return List<PkgRdirectionDirectionProcedureList> 検索結果リスト
	 */
	public List<PkgRdirectionDirectionProcedureList> getSearchProcList() {
		return searchProcList;
	}

	/**
	 * 検索結果リスト設定
	 * @param searchProcList 検索結果リスト
	 */
	public void setSearchProcList(final List<PkgRdirectionDirectionProcedureList> searchProcList) {
		this.searchProcList = searchProcList;
	}

	/**
	 * 工程検索行番号取得
	 * @return String 工程検索行番号
	 */
	public String getLine() {
		return line;
	}

	/**
	 * 工程検索行番号設定
	 * @param line 工程検索行番号
	 */
	public void setLine(final String line) {
		this.line = line;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchProcList() != null) {
			for (PkgRdirectionDirectionProcedureList bean : getSearchProcList()) {
				bean.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	/**
	 * 検索結果の件数を取得する
	 * @return 件数
	 */
	public int getCount() {
		int count = 0;
		if (searchProcList != null) {
			count = searchProcList.size();
		}
		return count;
	}
}
