/*
 * Created on 2017/11/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.pdfReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate;
import com.asahikaseieng.dao.nonentity.common.pdfreportdecorator.PdfReportDecoratorDao;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * Decorator実装クラス.
 *
 * @author ssv_ogata
 */
public class PdfReportDecoratorImpl implements PdfReportDecorator{

	protected ResourceBundle rb = Constants.RB_REPORT_PROPERTIES;

	/**
	 * 個別ロジックのパラメータリスト
	 */
	protected List<String> params;

	/** ビルダー */
    private ReportPdfBuilder pdfBuilder = new ReportPdfBuilder();

	/**
	 * コンストラクタ
	 */
	public PdfReportDecoratorImpl() {
		super();
	}
	
	/**
	 * XLS -> PDF変換
	 *
	 * @param frm
	 *            　出力データ
	 * @param tantoCd
	 *            　担当者コード
	 * 20220516　S.Fujimaki seq付与機能を追加
	 */
	public FileDownloadInfo convertPdfForExcel(final String fileNameKey, final String tantoCd, final String fileName, final FileDownloadInfo info, final MailTemplate mailTemplate, BigDecimal seq) throws IOException{

		/* ダウンロード時の名称キーをセット */
		pdfBuilder.setFileKey(fileNameKey);
		
		//PDFシーケンスを付与 20220516 add S.Fujimaki
		/* 帳票ファイルの作成。一時領域に作成したファイル名を取得 */
		String tempFileName = pdfBuilder.pdfConvert(tantoCd, info, seq);
		//PDFシーケンスを付与 20220516 add S.Fujimaki

		// 該当場所に生成
		if(!tempFileName.isEmpty()){
			File oldFile = new File(tempFileName);

			// 作成ディレクトリを取得
			String parentDir = rb.getString("mailsavedirectory");

			// ディレクトリがなければ作成
			File savDir = new File(parentDir);
			if(!savDir.exists()){
				savDir.mkdirs();
			}

			// path入り名称
            //20220516 add S.Fujimaki
			String newName = parentDir + fileName + "_" + seq + rb.getString("pdf.extension");
            //20220516 add S.Fujimaki
			File newFile = new File(newName);

			// ファイルコピー
			if(oldFile.exists()){

				FileInputStream fileIn = new FileInputStream(oldFile);
				FileOutputStream fileOut = new FileOutputStream(newFile);

				byte[] buf = new byte[256];

				while(fileIn.read(buf) != -1){
					fileOut.write(buf);
				}

				fileOut.flush();
				fileOut.close();
				fileIn.close();

				tempFileName = newName;

				// 同じファイルをコピー(name+_cpy)
                //20220516 add S.Fujimaki
				newName = parentDir + fileName + "_" + seq + rb.getString("pdf.copy.name") + rb.getString("pdf.extension");
                //20220516 add S.Fujimaki
				File newSavFile = new File(newName);

				fileIn = new FileInputStream(oldFile);
				FileOutputStream fileSavOut = new FileOutputStream(newSavFile);

				buf = new byte[256];

				while(fileIn.read(buf) != -1){
					fileSavOut.write(buf);
				}

				fileSavOut.flush();
				fileSavOut.close();
				fileIn.close();
//PDF保管用　start				
				//PDF保管場所に、コピーして保存する
				String copyFilePath = mailTemplate.getPDFPath();
				// ディレクトリがなければ作成
				File copyDir = new File(copyFilePath);
				if(!copyDir.exists()){
					copyDir.mkdirs();
				}
				
				//ファイル名が重複しないように現在日時を取得し、元のファイル名に結合する		
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHmmss");
				String now = sdf.format(new Date());
				//20220516 S.Fujimaki Add
				copyFilePath = copyFilePath + fileName + "_" + now + "_" + seq + rb.getString("pdf.extension");
				//20220516 S.Fujimaki Add end
				
				fileIn = new FileInputStream(oldFile);
				fileSavOut = new FileOutputStream(copyFilePath);

				buf = new byte[256];

				while(fileIn.read(buf) != -1){
					fileSavOut.write(buf);
				}

				fileSavOut.flush();
				fileSavOut.close();
				fileIn.close();
//PDF保管用　end
				

			}

		}

		/* ダウンロード用ファイル名を取得 */ 
//		String responseFileName = pdfBuilder.getResponseFileName(tantoCd, tm); PDF作成はダウンロードしない。tempFileName以外は使用していないため、コメントアウト

		/* ダウンロード情報を作成して返却 */
//		return new FileDownloadInfo(responseFileName, tempFileName);
		return new FileDownloadInfo("", tempFileName);
	}

	/**
	 * @param pdfBuilder セットする pdfBuilder
	 */
	public void setPdfBuilder(ReportPdfBuilder pdfBuilder) {
		this.pdfBuilder = pdfBuilder;
	}
}
