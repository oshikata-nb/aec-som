/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.label.Label;
import com.asahikaseieng.dao.entity.master.label.LabelDao;
import com.asahikaseieng.dao.nonentity.master.commondetail.CommonDetail;
import com.asahikaseieng.dao.nonentity.master.commondetail.CommonDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeaderDao;
import com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail.ItemTechLabelDetail;
import com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail.ItemTechLabelDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 技術情報ロジック 実装クラス.
 * @author t0011036
 */
public class ItemTechLogicImpl implements ItemTechLogic {

	private ItemQueueHeaderDao itemQueueHeaderDao;

	private ItemTechLabelDetailDao itemTechLabelDetailDao;

	private LabelDao labelDao;

	private CommonDetailDao commonDetailDao;

	/** ファイル名の年月日時分秒部分の桁数 */
	private static final int START_FILE_NAME = 14;

	/** リンク情報ラベル種類 */
	public static final String LINK_CD = "ITEM_LINK";

	/** 技術情報ラベル種類 */
	public static final String TECH_CD = "ITEM_TECH";

	/**
	 * コンストラクタ.
	 */
	public ItemTechLogicImpl() {
	}

	/**
	 * 品目検索（ヘッダー用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	public ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version) {
		ItemQueueHeader bean = itemQueueHeaderDao.getEntity(itemCd, version);
		return bean;
	}

	/**
	 * 登録登録
	 * @param frm 登録対象データ
	 * @param tantoCd 担当者コード
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	public void regist(final ItemTechForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		String outFile = null;
		CommonDetail bean = new CommonDetail();

		/* 品目リンク情報名称取得 */
		bean = commonDetailDao.getEntity(LINK_CD);

		if (bean != null) {
			frm.setLinkCommonName(bean.getCommonName());
		}

		/* 品目技術情報名称取得 */
		bean = commonDetailDao.getEntity(TECH_CD);

		if (bean != null) {
			frm.setTechCommonName(bean.getCommonName());
		}

		/* アップロードしたファイル名 */
		outFile = upload(frm, LINK_CD);

		if (!StringUtils.isEmpty(outFile)) {
			frm.setLinkLabelPath(outFile);

			/* リンク情報登録 */
			if (frm.getLinkUpdateDate() == null) {
				/* 追加処理を実行 */
				insert(insertLabel(frm, LINK_CD, tantoCd));
			} else {
				/* 更新処理を実行 */
				update(updateLabel(frm, LINK_CD, tantoCd));
			}

			frm.setDispLinkLabelPath(getOriginalLabelPath(frm
					.getLinkLabelPath()));
		}

		/* アップロードしたファイル名 */
		outFile = upload(frm, TECH_CD);

		if (!StringUtils.isEmpty(outFile)) {
			frm.setTechLabelPath(outFile);

			/* 技術情報登録 */
			if (frm.getTechUpdateDate() == null) {
				/* 追加処理を実行 */
				insert(insertLabel(frm, TECH_CD, tantoCd));
			} else {
				/* 更新処理を実行 */
				update(updateLabel(frm, TECH_CD, tantoCd));
			}

			frm.setDispTechLabelPath(getOriginalLabelPath(frm
					.getTechLabelPath()));
		}

		if (!StringUtils.isEmpty(outFile)) {
			frm.setLinkUpdateDate(AecDateUtils.getCurrentTimestamp());
		}

		if (!StringUtils.isEmpty(outFile)) {
			frm.setTechUpdateDate(AecDateUtils.getCurrentTimestamp());
		}
	}

	/**
	 * 元のファイル名を取得
	 * @param lblPath 登録されているファイル名
	 * @return 元のファイル名
	 */
	public String getOriginalLabelPath(final String lblPath) {
		String path = null;

		if (!StringUtils.isEmpty(lblPath)) {
			/* 年月日時分秒を取除く */
			path = lblPath.substring(START_FILE_NAME);
		}

		return path;
	}

	/**
	 * 更新処理用のLabelを作成する.
	 * @param frm 画面データ
	 * @param commonCd ラベル種類
	 * @param tantoCd 担当者コード
	 * @return Label
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Label updateLabel(final ItemTechForm frm, final String commonCd,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Label newBean = new Label();

		try {
			newBean = getEntity(frm.getHeadItemCd(), commonCd);
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		String labelName = null;
		String labelPath = null;

		if (commonCd.equals(LINK_CD)) {
			labelName = frm.getLinkCommonName();
			labelPath = frm.getLinkLabelPath();
		} else if (commonCd.equals(TECH_CD)) {
			labelName = frm.getTechCommonName();
			labelPath = frm.getTechLabelPath();
		}

		/* コピーしきれなかった分は手で */
		newBean.setLabelName(labelName);
		newBean.setLabelPath(labelPath);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のLabel
	 * @param frm 画面データ
	 * @param commonCd ラベル種類
	 * @param tantoCd 担当者コード
	 * @return Label
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Label insertLabel(final ItemTechForm frm, final String commonCd,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Label newBean = new Label();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		String labelName = null;
		String labelPath = null;

		if (commonCd.equals(LINK_CD)) {
			labelName = frm.getLinkCommonName();
			labelPath = frm.getLinkLabelPath();
		} else if (commonCd.equals(TECH_CD)) {
			labelName = frm.getTechCommonName();
			labelPath = frm.getTechLabelPath();
		}

		/* コピーしきれなかった分は手で */
		newBean.setLabelCd(frm.getHeadItemCd());
		newBean.setLabelName(labelName);
		newBean.setLabelPath(labelPath);
		newBean.setCommonCd(commonCd);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * ラベル検索（登録用）
	 * @param labelCd ラベルコード
	 * @param commonCd ラベル種類
	 * @return Label
	 * @throws NoDataException NoDataException
	 */
	public Label getEntity(final String labelCd, final String commonCd)
			throws NoDataException {
		if (StringUtils.isEmpty(labelCd)) {
			throw new IllegalArgumentException("labelCd is empty");
		}

		if (StringUtils.isEmpty(commonCd)) {
			throw new IllegalArgumentException("commonCd is empty");
		}

		Label bean = labelDao.getEntity(commonCd, labelCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Label bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			labelDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Label bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			labelDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * ファイル情報取得
	 * @param labelCd ラベルコード
	 * @param commonCd ラベル種類
	 * @return ItemTechLabeLDetail
	 */
	public ItemTechLabelDetail getLabelEntity(final String labelCd,
			final String commonCd) {
		if (StringUtils.isEmpty(labelCd)) {
			throw new IllegalArgumentException("labelCd is empty");
		}

		if (StringUtils.isEmpty(commonCd)) {
			throw new IllegalArgumentException("commonCd is empty");
		}

		ItemTechLabelDetail bean = itemTechLabelDetailDao.getEntity(labelCd,
			commonCd);

		return bean;
	}

	/**
	 * ダウンロードパス取得
	 * @param commonCd ラベル種類
	 * @return String ダウンロードパス
	 */
	public String getDownloadPath(final String commonCd) {

		CommonDetail bean = commonDetailDao.getEntity(commonCd);

		if (bean == null) {
			return null;
		} else {
			return bean.getCommonValue();
		}
	}

	/**
	 * ファイルアップロード
	 * @param frm 画面データ
	 * @param commonCd ラベル種類
	 * @return アップロードファイル名
	 */
	private String upload(final ItemTechForm frm, final String commonCd) {
		String outFile = null;

		CommonDetail bean = commonDetailDao.getEntity(commonCd);

		if (bean == null) {
			return outFile;
		}

		/* アップロード日時 */
		String uploadDatetime = AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyyMMddHHmmss");

		/* アップロード先のディレクトリ */
		String uploadDir = bean.getCommonValue();

		BufferedInputStream inBuffer = null;
		BufferedOutputStream outBuffer = null;

		FormFile uploadFile = null;

		try {
			/* ディレクトリが存在するか調べてなければ作成する */
			File dir = new File(uploadDir);

			if (!dir.exists()) {
				if (!dir.mkdir()) {
					throw new RuntimeException("Can't create directory. : ["
							+ uploadDir + "]");
				}
			}

			String labelPath = null;

			if (commonCd.equals(LINK_CD)) {
				labelPath = frm.getLinkLabelPath();
				uploadFile = frm.getLinkUploadFile();
			} else if (commonCd.equals(TECH_CD)) {
				labelPath = frm.getTechLabelPath();
				uploadFile = frm.getTechUploadFile();
			}

			/* 以前アップロードしたファイルがある場合は削除しておく.上書 */
			if (!StringUtils.isEmpty(labelPath)) {
				File oldFile = new File(uploadDir + labelPath);

				if (oldFile.exists()) {
					oldFile.delete();
				}
			}

			/* アクセスメソッドを使用してFormFileオブジェクトの取得 */
			if (StringUtils.isEmpty(uploadFile.getFileName())) {
				throw new IOException("ファイルがありません");
			}

			/* getInputStreamメソッドを使用し、入力ストリームを取得 */
			/* 入力ストリームをバッファリング */
			inBuffer = new BufferedInputStream(uploadFile.getInputStream());

			/* ファイルのアップロード先を指定して、出力ストリームを生成 */
			/* 出力ストリームをバッファリング */
			outFile = uploadDatetime + uploadFile.getFileName();
			outBuffer = new BufferedOutputStream(new FileOutputStream(uploadDir
					+ outFile));

			/* コピー */
			IOUtils.copy(inBuffer, outBuffer);
		} catch (IOException e) {
			outFile = null;
		} finally {
			/* クローズ */
			IOUtils.closeQuietly(outBuffer);
			IOUtils.closeQuietly(inBuffer);

			/* 一時領域のアップロードデータを削除 */
			if (uploadFile != null) {
				uploadFile.destroy();
			}
		}

		return outFile;
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemTechLabelDetailDaoを設定します。
	 * @param itemTechLabelDetailDao itemTechLabelDetailDao
	 */
	public void setItemTechLabelDetailDao(
			final ItemTechLabelDetailDao itemTechLabelDetailDao) {
		this.itemTechLabelDetailDao = itemTechLabelDetailDao;
	}

	/**
	 * labelDaoを設定します。
	 * @param labelDao labelDao
	 */
	public void setLabelDao(final LabelDao labelDao) {
		this.labelDao = labelDao;
	}

	/**
	 * itemQueueHeaderDaoを設定します。
	 * @param itemQueueHeaderDao itemQueueHeaderDao
	 */
	public void setItemQueueHeaderDao(
			final ItemQueueHeaderDao itemQueueHeaderDao) {
		this.itemQueueHeaderDao = itemQueueHeaderDao;
	}

	/**
	 * commonDetailDaoを設定します。
	 * @param commonDetailDao commonDetailDao
	 */
	public void setCommonDetailDao(final CommonDetailDao commonDetailDao) {
		this.commonDetailDao = commonDetailDao;
	}
}
