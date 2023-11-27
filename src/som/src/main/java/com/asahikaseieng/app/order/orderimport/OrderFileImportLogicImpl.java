/*
 * Created on 2020/11/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.master.common.CommonDao;
import com.asahikaseieng.dao.entity.orderimporttemp.OrderImportTemp;
import com.asahikaseieng.dao.entity.orderimporttemp.OrderImportTempDao;
import com.asahikaseieng.dao.nonentity.comboboxes.vendergroup.VenderGroupForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.vendergroup.VenderGroupForComboboxesDao;
import com.asahikaseieng.dao.nonentity.orderimportfileloglist.OrderImportFileLogList;
import com.asahikaseieng.dao.nonentity.orderimportfileloglist.OrderImportFileLogListDao;
import com.asahikaseieng.dao.nonentity.orderimportfilelogupdate.OrderImportFileLogUpdateDao;
import com.asahikaseieng.dao.nonentity.orderimporttemplist.OrderImportTempList;
import com.asahikaseieng.dao.nonentity.orderimporttemplist.OrderImportTempListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.pakorderfileimport.PakOrderFileImportDao;
import com.asahikaseieng.dao.nonentity.procedurecall.pakorderfileimport.ConvertFrstOrderDto;
import com.asahikaseieng.dao.nonentity.procedurecall.pakorderfileimport.ConvertOrderBaseDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 受注取込詳細ロジック 実装クラス.
 * @author 
 */
public class OrderFileImportLogicImpl implements OrderFileImportLogic {
	
	private InputStream stream;
	private List<List<String>> csvData;
	
	/** 文字コード UTF-8 */
	private String encode = "UTF-8";
	
	/** CSV区切り文字 (カンマ) */
	private static final String separator = ",";
	
	/** ダブルクォート */
	private static final String enclose = "\"";
	
	private GetNumberLogic getNumberLogic;
	
	private OrderImportTempDao orderImportTempDao;
	
	private OrderImportFileLogListDao orderImportFileLogListDao;
	
	private OrderImportFileLogUpdateDao orderImportFileLogUpdateDao;
	
	private OrderImportTempListDao orderImportTempListDao;
	
	private ProcedureCallDao procedureCallDao;

	/** 得意先グループコンボボックス用DAO*/
	private VenderGroupForComboboxesDao venderGroupForComboboxesDao;
	
	private CommonDao commonDao;

	private PakOrderFileImportDao pakOrderFileImportDao;
	
	
	/**
	 * pakOrderFileImportDaoを設定します。
	 * @param pakOrderFileImportDao pakOrderFileImportDao
	 */
	public void setPakOrderFileImportDao(PakOrderFileImportDao pakOrderFileImportDao) {
		this.pakOrderFileImportDao = pakOrderFileImportDao;
	}

	/**
	 * コンストラクタ.
	 */
	public OrderFileImportLogicImpl() {
	}
	
	/**
	 * <pre>
	 * CSV読み込み時の文字コードを指定
	 * 設定されない場合、UTF-8で読み込み
	 * </pre>
	 * @param encode
	 */
	private void setEncode(String encode){
		this.encode = encode;
	}
	
	/**
	 * CSVデータをバッファリングします。
	 * @param stream
	 * @return
	 * @throws Exception 
	 */
	private BufferedReader getBufferedReader() throws Exception{
		return new BufferedReader(new InputStreamReader(this.stream, this.encode));
	}
	
	/**
	 * CSVデータを取得します。
	 * @return CSVデータ
	 * @throws Exception 
	 */
	
	public List<List<String>> getRows(final String fileName, final String encode) throws Exception{
	
		if(encode != null){
			this.setEncode(encode);
		}
		stream = new FileInputStream(fileName);
	
		this.readCsvData();
	
		return csvData;
	}
	
	/**
	 * CSVデータを読み込みcsvDataにリストとして格納
	 * 
	 * @throws IOException 
	 */
	private void readCsvData() throws Exception{
		
		csvData = new ArrayList<List<String>>();
		
		BufferedReader buffer = null;
		try{
			buffer = getBufferedReader();
			
			String line;
			while ((line = buffer.readLine()) != null) {
				csvData.add(getCsvLineList(line));
			}
		}finally{
			if (buffer != null){
				buffer.close(); 
			}
		}
	}
	
	/**
	 * CSVデータの１行分の文字列を解析しリストに格納して返す
	 * @param csvLine １行分の文字列
	 * @return １行分のデータの解析結果を格納したList
	 */
	private List<String> getCsvLineList(String csvLine){
	
		List<String> list = new ArrayList<String>();
	
		int charCnt = 0;
		int charIdx = 0;
	
		StringBuilder builder = new StringBuilder();
		while (charCnt < csvLine.length()) 
		{
			
			boolean isDoubleQuote = 
					csvLine.substring(charCnt, charCnt+1).equals(enclose);
			
			if (isDoubleQuote) ++charCnt;
			
			charIdx = csvLine.indexOf(isDoubleQuote ? enclose : separator , charCnt);
			
			if (charIdx < 0) {
				charIdx = csvLine.length();
			}
			
			String workStr = csvLine.substring(charCnt, charIdx);
			
			if (isDoubleQuote 
					&& charIdx < csvLine.length() - 1 
					&& csvLine.substring(charIdx + 1, charIdx + 2).equals(enclose)){
				// 文字列内のクォートと判断
				builder.append(workStr);
				builder.append(enclose);
				charCnt = charIdx + 1;
				
			}else{
				list.add(builder.toString() + workStr);
				builder.setLength(0);
				charCnt = charIdx + (isDoubleQuote ? 2 : 1);
			}
	
			// 最後の文字が"separator"
			if (csvLine.length() == charCnt) {
				String lastStr = csvLine.substring(charCnt - 1, charCnt);
				if (lastStr.equals(separator)) {
					list.add("");
				}
				
			}
		}
		
		return list;
	}
	
	/**
	 * リストに格納したCSVデータを一時取込テーブルに登録
	 * @param csvData List<List<String>>
	 * @return 
	 */
	public ActionMessages setTempTable(final List<List<String>> csvData, final OrderFileImportForm frm, final String tantoCd) throws Exception{
		ActionMessages messages = new ActionMessages();
		
		//行番号
		int rowNum = 1;
		//列番号
		int colNum = 1;
		
		String tempNo = "";
		
		try{
			//一時取込番号と取込番号をシーケンスから取得
			tempNo = getNumberLogic.getTempNo();
		} catch(Exception e){
			//エラーメッセージを追加
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nodata.sequence"));
			return messages;
		}
		
		
		OrderImportTemp bean = new OrderImportTemp();
		
		//行頭項目名保存用配列
		String[] firstColumn = new String[csvData.get(0).size()];
		
		// 行を取り出す
		for(List<String> csvRow : csvData){
			//列番号を1にリセット
			colNum = 1;
			//要素を1つずつ取り出す
			for(String data : csvRow){
				//1行目の項目名を格納
				if(rowNum == 1){
					firstColumn[colNum - 1] = data;
				}
				//データをセット
				bean.setTempNo(tempNo); // 一時取込番号
				bean.setVenderGroupCd(frm.getVenderGroupCd()); // 得意先グループコード
				bean.setOrderFileName(frm.getUploadFileName()); // 受注ファイル名
				bean.setMd5Checksum(frm.getMd5()); // md5チェックサム値
				bean.setImportDate(AecDateUtils.getCurrentTimestamp()); // ファイル取込日
				bean.setImportTantoCd(tantoCd); // ファイル取込担当者
				bean.setCellRowNumber(AecNumberUtils.convertBigDecimalFromString(String.valueOf(rowNum))); // セル行番号
				bean.setCellColumnNumber(AecNumberUtils.convertBigDecimalFromString(String.valueOf(colNum))); // セル列番号
				bean.setOrderFileColumnName(firstColumn[colNum - 1]); // 受注ファイル項目名
				bean.setCellData(data); // セルデータ
				bean.setUpdatorCd(tantoCd); // 更新者ID
				
				/* 一時取込テーブルへの登録処理 */
				try{
					orderImportTempDao.insert(bean);
				} catch (NotSingleRowUpdatedRuntimeException e) {
					// 排他エラー
					e.printStackTrace();
					throw new OptimisticLockRuntimeException();
				} catch (SQLRuntimeException e) {
					e.printStackTrace();
					throw new DuplicateRuntimeException(0);
				}
				
				colNum++;
			}
			
			rowNum++;
		}

		try{
			BigDecimal returnCd = BigDecimal.ZERO;
			//一時取込テーブルから受注取込テーブルへの登録処理
			returnCd = convertOrderBase(frm.getVenderGroupCd(), tempNo, tantoCd);
			// 1:正常終了
			if(returnCd.compareTo(new BigDecimal("9")) == 0 || returnCd.compareTo(new BigDecimal("10")) == 0){ // 9:異常終了
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.file.error.commonconvert"));
				return messages;

			}
			
			returnCd = convertFrstOrder(tempNo, tantoCd);
			if(returnCd.compareTo(new BigDecimal("9")) == 0 || returnCd.compareTo(new BigDecimal("10")) == 0){ // 9:異常終了
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.file.error.orderconvert"));
				return messages;
			}
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		
		return messages;
	}
	
	/**
	 * 一時取込テーブルから受注取込テーブルへの登録処理
	 * @param frm OrderFileImport
	 * @param rowNum int
	 * @return messages ActionMessages
	 */
	private BigDecimal convertOrderBase(String venderGroupCd , String tempNo, String tantoCd) throws Exception{
		
		
		// プロシージャを呼び出すためのデータをDtoにセット
		ConvertOrderBaseDto dto = new ConvertOrderBaseDto();
		dto.setpTempNo(tempNo);
		dto.setpVenderGroupCd(venderGroupCd);
		dto.setpTantoCd(tantoCd);
		BigDecimal returnCd = BigDecimal.ZERO;
		//受注取込テーブルへの登録プロシージャ呼び出し
		try{
			pakOrderFileImportDao.convertOrderBase(dto);
		} catch(Exception e){
			e.printStackTrace();
			returnCd = new BigDecimal("10");
			return returnCd;
		}
		returnCd = dto.getPNumRet(); //戻り値 1:正常終了 2:未登録 9:異常終了
		
		return returnCd;
	}
	
	/**
	 * 一時取込テーブルから受注取込テーブルへの登録処理
	 * @param frm OrderFileImport
	 * @param rowNum int
	 * @return messages ActionMessages
	 */
	private BigDecimal convertFrstOrder( String tempNo , String tantoCd) throws Exception{

		// プロシージャを呼び出すためのデータをDtoにセット
		ConvertFrstOrderDto  dto = new ConvertFrstOrderDto();
		dto.setpTempNo(tempNo);
		dto.setpTantoCd(tantoCd);
		BigDecimal returnCd = BigDecimal.ZERO;
		//受注取込テーブルへの登録プロシージャ呼び出し
		try{
			pakOrderFileImportDao.convertFrstOrder(dto);
		} catch(Exception e){
			e.printStackTrace();
			returnCd = new BigDecimal("10");
			return returnCd;
		}
		returnCd = dto.getPNumRet(); //戻り値 1:正常終了 2:未登録 9:異常終了
		
		return returnCd;
	}
	
	/**
	 * 
	 * 過去1か月間の取り込まれたファイルをmd5チェックサム値で検索する
	 * @param frm OrderFileImportForm
	 * @return OrderImportTempList
	 * @throws Exception
	 */
	public OrderImportTempList getMd5Checksum(OrderFileImportForm frm) throws Exception{
		
		OrderImportTempList checksum = orderImportTempListDao.getMd5Check(frm.getMd5());
		
		return checksum;
	}
	
	
	/**
	 * 得意先グループ一覧取得
	 * @return List<VenderGroupForComboboxes>
	 */
	public List<VenderGroupForComboboxes> getVenderGroupList() {
		return venderGroupForComboboxesDao.getListForComboboxes();
	}
	
	/**
	 * 得意先グループコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createVenderGroupCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 得意先グループ検索
		List<VenderGroupForComboboxes> venderGroupList = getVenderGroupList();
		for (VenderGroupForComboboxes bean : venderGroupList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getVenderGroupCd());
			// 得意先グループ名称は得意先グループ名称1_得意先グループ名称2とする
			StringBuffer nameBuf = new StringBuffer("");
			nameBuf.append(bean.getVenderGroupName());
			item.setLabales(nameBuf.toString());
			res.add(item);
		}
		return res;
	}
	
	/**
	 * プロシージャ実行ログ取得
	 * @return List<OrderImportFileLogList>
	 */
	public List<OrderImportFileLogList> getImportLog(final String tantoCd){
		
		List<OrderImportFileLogList> logList = orderImportFileLogListDao.getViewList(tantoCd);
		
		return logList;
	}
	
	public ActionMessages updateCheckedFlg(String tantoCd) throws Exception{
		ActionMessages errorMsg = new ActionMessages();
		
		orderImportFileLogUpdateDao.updateCheckedFlg(tantoCd);
		
		return errorMsg;
	}
	
	/**
	 * TMPファイルパス取得
	 */
	public String getTmpFilePath(){
		
		String filePath = commonDao.getEntity("ORDER_IMPORT").getCommonValue();
		
		return filePath;
	}
	
	/* -------------------- setter -------------------- */

	/**
	 * getNumberLogicを設定します。
	 * @param getNumberLogic getNumberLogic
	 */
	public void setGetNumberLogic(GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * orderImportTempDaoを設定します。
	 * @param orderImportTempDao orderImportTempDao
	 */
	public void setOrderImportTempDao(OrderImportTempDao orderImportTempDao) {
		this.orderImportTempDao = orderImportTempDao;
	}

	/**
	 * orderImportFileLogListDaoを取得します。
	 * @return orderImportFileLogListDao
	 */
	public OrderImportFileLogListDao getOrderImportFileLogListDao() {
		return orderImportFileLogListDao;
	}

	/**
	 * orderImportFileLogListDaoを設定します。
	 * @param orderImportFileLogListDao orderImportFileLogListDao
	 */
	public void setOrderImportFileLogListDao(
			OrderImportFileLogListDao orderImportFileLogListDao) {
		this.orderImportFileLogListDao = orderImportFileLogListDao;
	}

	/**
	 * orderImportFileLogUpdateDaoを設定します。
	 * @param orderImportFileLogUpdateDao orderImportFileLogUpdateDao
	 */
	public void setOrderImportFileLogUpdateDao(
			OrderImportFileLogUpdateDao orderImportFileLogUpdateDao) {
		this.orderImportFileLogUpdateDao = orderImportFileLogUpdateDao;
	}

	/**
	 * orderImportTempListDaoを設定します。
	 * @param orderImportTempListDao orderImportTempListDao
	 */
	public void setOrderImportTempListDao(
			OrderImportTempListDao orderImportTempListDao) {
		this.orderImportTempListDao = orderImportTempListDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * venderGroupForComboboxesDaoを設定します。
	 * @param venderGroupForComboboxesDao venderGroupForComboboxesDao
	 */
	public void setVenderGroupForComboboxesDao(
			VenderGroupForComboboxesDao venderGroupForComboboxesDao) {
		this.venderGroupForComboboxesDao = venderGroupForComboboxesDao;
	}

	/**
	 * commonDaoを設定します。
	 * @param commonDao commonDao
	 */
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
}
