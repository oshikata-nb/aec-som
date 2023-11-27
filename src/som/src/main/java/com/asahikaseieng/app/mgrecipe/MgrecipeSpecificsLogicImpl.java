/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeCommonDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeCommonDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelList;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;



/**
 * 基本処方－詳細タブ ロジック実装クラス
 * @author tosco
 */
public class MgrecipeSpecificsLogicImpl implements MgrecipeSpecificsLogic {

	/** 帳票・ラベルマスタ用Dao */
	private MgrecipeLabelListDao mgrecipeLabelListDao;
	/** テンプレート保存先マスタ共通用Dao */
	private MgrecipeCommonDetailDao mgrecipeCommonDetailDao;

	/** テンプレート区分 GRECIPE_DETAIL1 */
	public static final String COMMON_CD_GR1 = "GRECIPE_DETAIL1";
	/** テンプレート区分 GRECIPE_DETAIL2 */
	public static final String COMMON_CD_GR2 = "GRECIPE_DETAIL2";
	/** テンプレート区分 GRECIPE_DETAIL3 */
	public static final String COMMON_CD_GR3 = "GRECIPE_DETAIL3";
	/** テンプレート区分 GRECIPE_DETAIL4 */
	public static final String COMMON_CD_GR4 = "GRECIPE_DETAIL4";

	/** テンプレート区分 MRECIPE_DETAIL1 */
	public static final String COMMON_CD_MR1 = "MRECIPE_DETAIL1";
	/** テンプレート区分 MRECIPE_DETAIL2 */
	public static final String COMMON_CD_MR2 = "MRECIPE_DETAIL2";
	/** テンプレート区分 MRECIPE_DETAIL3 */
	public static final String COMMON_CD_MR3 = "MRECIPE_DETAIL3";
	/** テンプレート区分 MRECIPE_DETAIL4 */
	public static final String COMMON_CD_MR4 = "MRECIPE_DETAIL4";

	/** 必要行数 */
	public static final int numLine = 4;

	/**
	 * コンストラクタ.基本処方－詳細タブ
	 */
	public MgrecipeSpecificsLogicImpl() {
	}

	/**
	 * 詳細タブ初期画面用－原処方データ検索
	 *
	 * @param labelCd ラベルコード
	 * @return List<MgrecipeLabelList>
	 */
	public List<MgrecipeLabelList> getGrSearch(final String labelCd) {

		checkParams(labelCd);

		// 検索
		List<MgrecipeLabelList> list = mgrecipeLabelListDao.getSearchList(labelCd);

		List<MgrecipeLabelList> newList = new ArrayList<MgrecipeLabelList>(numLine);
		for (int i = 0; i < numLine; i++) {
			MgrecipeLabelList newBean = new MgrecipeLabelList();
			// 新規登録時用－固定データ
			newBean.setLabelCd(labelCd);
			newBean.setLabelName("原処方");
			newBean.setCommonCd(MgrecipeConst.COMMON_CD_GR + Integer.toString(i + 1));
			newList.add(i, newBean);
		}

		// 検索結果の移し変え
		for (MgrecipeLabelList bean : list) {
			String commonCd = bean.getCommonCd();
			if (commonCd.equals(COMMON_CD_GR1)) {
				newList.remove(0);
				newList.add(0, bean);
			} else if (commonCd.equals(COMMON_CD_GR2)) {
				newList.remove(1);
				newList.add(1, bean);
			} else if (commonCd.equals(COMMON_CD_GR3)) {
				newList.remove(2);
				newList.add(2, bean);
			} else if (commonCd.equals(COMMON_CD_GR4)) {
				newList.remove(3);
				newList.add(3, bean);
			}
		}

		return newList;
	}

	/**
	 * 詳細タブ初期画面用－基本処方データ検索
	 *
	 * @param labelCd ラベルコード
	 * @return List<MgrecipeLabelList>
	 */
	public List<MgrecipeLabelList> getMrSearch(final String labelCd) {

		checkParams(labelCd);

		// 検索
		List<MgrecipeLabelList> list = mgrecipeLabelListDao.getSearchList(labelCd);

		// 画面用リスト再設定
		List<MgrecipeLabelList> newList = new ArrayList<MgrecipeLabelList>(numLine);
		for (int i = 0; i < numLine; i++) {
			MgrecipeLabelList newBean = new MgrecipeLabelList();
			// 新規登録時用－固定データ
			newBean.setLabelCd(labelCd);
			newBean.setLabelName("基本処方");
			newBean.setCommonCd(MgrecipeConst.COMMON_CD_MR + Integer.toString(i + 1));
			newList.add(i, newBean);
		}

		// 検索結果の移し変え
		for (MgrecipeLabelList bean : list) {
			String commonCd = bean.getCommonCd();
			if (commonCd.equals(COMMON_CD_MR1)) {
				newList.remove(0);
				newList.add(0, bean);
			} else if (commonCd.equals(COMMON_CD_MR2)) {
				newList.remove(1);
				newList.add(1, bean);
			} else if (commonCd.equals(COMMON_CD_MR3)) {
				newList.remove(2);
				newList.add(2, bean);
			} else if (commonCd.equals(COMMON_CD_MR4)) {
				newList.remove(3);
				newList.add(3, bean);
			}
		}

		return newList;
	}

	/**
	 * 登録処理
	 *
	 * @param frm MgrecipeSpecificsForm
	 * @param tantoCd 更新者
	 * @throws Exception 例外エラー
	 */
	public void regist(final MgrecipeSpecificsForm frm, final String tantoCd) throws Exception {

		List<MgrecipeLabelList> uplordList = new ArrayList<MgrecipeLabelList>(8);

		// 原処方リスト
		if (frm.getSearchGrList() == null) {
			throw new IllegalArgumentException("searchGrList == null");
		}
		uplordList.addAll(frm.getSearchGrList());

		// 基本処方リスト
		if (frm.getSearchMrList() == null) {
			throw new IllegalArgumentException("searchMrList == null");
		}
		uplordList.addAll(frm.getSearchMrList());

		// リスト分処理を繰り返す
		for (MgrecipeLabelList bean : uplordList) {
			int updateNum;

			if (bean.getUploadFile() == null
					|| bean.getUploadFile().getFileName() == null
					|| bean.getUploadFile().getFileName().equals("")) {
				// アップロードファイルが入力されてない場合スキップ
				continue;
			}

			// アップロードしたファイル名
			String outFile = upload(bean);
			if (StringUtils.isEmpty(outFile)) {
				throw new IllegalArgumentException("outFile is empty");
			}
			bean.setLabelPath(outFile);

			/* 基本情報入力 */
			bean.setUpdatorCd(tantoCd);

			try {

				if (bean.getInputorCd() == null || bean.getInputorCd().equals("")) {

					/* 基本情報入力 */
					bean.setInputDate(AecDateUtils.getCurrentTimestamp());
					bean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
					bean.setInputorCd(tantoCd);

					/* 追加処理 */
					updateNum = mgrecipeLabelListDao.insert(bean);
					if (updateNum != 1) {
						/* 排他エラー */
						throw new OptimisticLockRuntimeException();
					}
				} else {

					/* 更新処理 */
					updateNum = mgrecipeLabelListDao.update(bean);
					if (updateNum != 1) {
						/* 排他エラー */
						throw new OptimisticLockRuntimeException();
					}
				}

			} catch (NotSingleRowUpdatedRuntimeException e) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				throw new DuplicateRuntimeException(0);
			}
		}

	}

	/**
	 * ダウンロードパス取得
	 *
	 * @param commonId 共通コード
	 * @return String ダウンロードパス
	 */
	public String getDownloadPath(final String commonId) {

		MgrecipeCommonDetail bean = mgrecipeCommonDetailDao.findByCommonCd(commonId);

		if (bean == null) {
			return null;
		} else {
			return bean.getCommonValue();
		}
	}

	/**
	 * パラメータチェック
	 * @param labelCd ラベルコード
	 */
	private void checkParams(final String recipeId) {

		if (recipeId == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * ファイルアップロード
	 * @param frm 画面データ
	 * @return アップロードファイル名
	 */
	private String upload(final MgrecipeLabelList lblBean) {

		String outFile = null;

		MgrecipeCommonDetail cmnBean = mgrecipeCommonDetailDao.findByCommonCd(lblBean.getCommonCd());
		if (cmnBean == null) {
			return outFile;
		}

		// アップロード日時
		String uploadDatetime = AecDateUtils.dateFormat
			(AecDateUtils.getCurrentTimestamp(), "yyyyMMddHHmmss");

		// アップロード先のディレクトリ
		String uploaddir = cmnBean.getCommonValue();

		FormFile uploadFile = null;
		BufferedInputStream inBuffer = null;
		BufferedOutputStream outBuffer = null;


		try {
			// ディレクトリが存在するか調べてなければ作成する
			File dir = new File(uploaddir);
			if (!dir.exists()) {
				if (!dir.mkdir()) {
					throw new RuntimeException(
							"Can't create directory. : [" + uploaddir + "]");
				}
			}

			// 以前アップロードしたファイルがある場合は削除しておく.上書
			if (lblBean.getLabelPath() != null && !lblBean.getLabelPath().equals("")) {
				File oldFile = new File(uploaddir + lblBean.getLabelPath());
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}

			// アクセスメソッドを使用してFormFileオブジェクトの取得
			if (lblBean.getUploadFile().getFileName().equals("")
					|| lblBean.getUploadFile().getFileName() == null) {
				throw new IOException("ファイルがありません");
			}

			uploadFile = lblBean.getUploadFile();
			// getInputStreamメソッドを使用し、入力ストリームを取得
			// 入力ストリームをバッファリング
			inBuffer = new BufferedInputStream(uploadFile.getInputStream());
			// ファイルのアップロード先を指定して、出力ストリームを生成
			// 出力ストリームをバッファリング
			outFile = uploadDatetime + uploadFile.getFileName();
			outBuffer = new BufferedOutputStream(new FileOutputStream(uploaddir + outFile));

			// コピー
			IOUtils.copy(inBuffer, outBuffer);
		} catch (IOException e) {
			outFile = null;
		} finally {
			// クローズ
			IOUtils.closeQuietly(outBuffer);
			IOUtils.closeQuietly(inBuffer);

			// 一時領域のアップロードデータを削除
			if (null != uploadFile) {
				uploadFile.destroy();
			}
		}

		return outFile;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 帳票・ラベルマスタ用Daoを設定します。
	 * @param mgrecipeLabelListDao 帳票・ラベルマスタ用Dao
	 */
	public void setMgrecipeLabelListDao(final MgrecipeLabelListDao mgrecipeLabelListDao) {
		this.mgrecipeLabelListDao = mgrecipeLabelListDao;
	}

	/**
	 * テンプレート保存先マスタ共通用Daoを設定します。
	 * @param mgrecipeCommonDetailDao テンプレート保存先マスタ共通用Dao
	 */
	public void setMgrecipeCommonDetailDao(final MgrecipeCommonDetailDao mgrecipeCommonDetailDao) {
		this.mgrecipeCommonDetailDao = mgrecipeCommonDetailDao;
	}


}
