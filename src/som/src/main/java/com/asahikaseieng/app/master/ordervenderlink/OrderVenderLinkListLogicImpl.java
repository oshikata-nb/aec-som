/*
 * Created on 2020/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.ordervenderlink;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.shipping.ShippingConst;
import com.asahikaseieng.dao.entity.master.common.CommonDao;
import com.asahikaseieng.dao.entity.orderrecieve.ordervenderdelivery.OrderVenderDelivery;
import com.asahikaseieng.dao.entity.orderrecieve.ordervenderdelivery.OrderVenderDeliveryDao;
import com.asahikaseieng.dao.entity.orderrecieve.ordervenderitem.OrderVenderItem;
import com.asahikaseieng.dao.entity.orderrecieve.ordervenderitem.OrderVenderItemDao;
import com.asahikaseieng.dao.entity.orderrecieve.ordervendermaster.OrderVenderMasterDao;
import com.asahikaseieng.dao.nonentity.comboboxes.vendergroup.VenderGroupForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.vendergroup.VenderGroupForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport.OrderVenderLinkListForReport;
import com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport.OrderVenderLinkListForReportDao;
import com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterList;
import com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterListDao;
import com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterListPagerCondition;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecDateUtils;
/**
 * 得意先グループ一覧ロジック 実装クラス.
 * @author
 */
public class OrderVenderLinkListLogicImpl implements OrderVenderLinkListLogic {
	
	/** 納入先シート番号*/
	private static final String DELIVERY_SHEET_NO = "order_vender_delivery";
	
	/** 品目シート番号*/
	private static final String ITEM_SHEET_NO = "order_vender_item";
	
	/** 得意先グループコードセル行番号*/
	private static final Integer VENDER_GROUP_CD_ROW = 1;

	/** 得意先グループコードセル列番号*/
	private static final short VENDER_GROUP_CD_COLUMN = 2;
	
	/** 納入先連携データ開始行番号*/
	private static final Integer DELIVERY_LINK_DATA_ROW = 7;
	
	/** セッツ納入先コード開始列番号*/
	private static final short DELIVERY_LINK_DATA_SOM_COLUMN = 1;
	
	/** 得意先納入先コード開始列番号*/
	private static final short DELIVERY_LINK_DATA_CTM_COLUMN = 8;
	
	/** 品目連携データ開始行番号*/
	private static final Integer ITEM_LINK_DATA_ROW = 7;
	
	/** セッツ品目コード開始列番号*/
	private static final short ITEM_LINK_DATA_SOM_COLUMN = 1;
	
	/** 得意先品目コード開始列番号*/
	private static final short ITEM_LINK_DATA_CTM_COLUMN = 8;

	/** 得意先品目コード開始列番号*/
	private static final short MAX_ROW = 30000;

	private static final Integer DELIVERY_LENGTH = 50;
	private static final Integer ITEM_LENGTH = 50;

	private CommonDao commonDao;
	
	private OrderVenderMasterDao orderVenderMasterDao;
	
	private OrderVenderDeliveryDao orderVenderDeliveryDao;
	
	private OrderVenderItemDao orderVenderItemDao;
	
	private OrderVenderMasterListDao orderVenderMasterListDao;

	private OrderVenderLinkListForReportDao orderVenderLinkListForReportDao;
	
	private VenderGroupForComboboxesDao venderGroupForComboboxesDao;

	/**
	 * コンストラクタ.
	 */
	public OrderVenderLinkListLogicImpl() {
	}

	/**
	 * EXCELからデータを読み込む.
	 * @param srhFilePath
	 * @param checker
	 * @return
	 */
	public ActionMessages getDataFromUploadFile(final String srhFilePath,
			final CheckDigitUtilsLogic checker, final String tantoCd) throws IOException,Exception{
		ActionMessages errorMessages = new ActionMessages();
		
		POIFSFileSystem filein = new POIFSFileSystem(new FileInputStream(srhFilePath));
		HSSFWorkbook wb = new HSSFWorkbook(filein);

		// =================================================================================================================
		// 納入先シートの処理を記載
		// =================================================================================================================

		// ブックの1番目(ordervenderdelivery)シートを指定
		HSSFSheet sheet = wb.getSheet(DELIVERY_SHEET_NO);
		
		List<OrderVenderDelivery> uploadDeliveryList = new ArrayList<OrderVenderDelivery>();
		
		//得意先グループコードが入力されている行取得
		HSSFRow row = sheet.getRow(VENDER_GROUP_CD_ROW);
		
		//得意先グループコードを取得
		String venderGroupCd = getString(row.getCell(VENDER_GROUP_CD_COLUMN));

		
		//得意先グループコードが空白の場合エラー
		if(venderGroupCd == null){
			//ordervenderdeliveryシート:得意先グループコードが未入力です。
			errorMessages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("errors.ordervenderlink.insert.ordervendergroup.null",
								Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderdelivery")));
			return errorMessages;
		}
		
		//得意先グループコードがマスタに登録されていなかったらエラーメッセージ
		if(!venderGroupCd.equals("99999")&& orderVenderMasterDao.getEntity(venderGroupCd, null) == null){
			//ordervenderdeliveryシート:指定の得意先グループコード{venderGroupCd}は存在していません。
			errorMessages.add(ActionMessages.GLOBAL_MESSAGE, 
								new ActionMessage("errors.ordervenderlink.insert.ordervendergroup",
								Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderdelivery"), venderGroupCd));
			return errorMessages;
		}
		
		//データ開始行に行番号をセット
		int rowNo = DELIVERY_LINK_DATA_ROW;
		
		// チェック用の納入先リストの取得
		List<OrderVenderLinkListForReport> deliveryList = this.getDeliveryList(venderGroupCd);

		// =================================================================================================================
		// 納入先のファイル読込処理
		// =================================================================================================================
		for(;;rowNo++){
			
			//得意先グループコードが99999のときアップロード対象外
			if(venderGroupCd.compareTo("99999") == 0){
				break;
			}
			
			//読み込んだデータ格納
			OrderVenderDelivery deliveryBean = new OrderVenderDelivery();
			
			if(rowNo > MAX_ROW){
				errorMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.ordervenderlink.insert.record.num"));
				return errorMessages;
			}
			
			//行の取得
			row = sheet.getRow(rowNo);
//			if(row == null){
//				// 行の取得ができなかった場合、その行は飛ばす。
//				continue;
//			}
			
			String somDeliveryCd = null;
			String ctmDeliveryCd1 = null;
			String ctmDeliveryCd2 = null;
			String ctmDeliveryCd3 = null;
			
			//指定のセルからそれぞれの値取得
			somDeliveryCd = getString(row.getCell(DELIVERY_LINK_DATA_SOM_COLUMN));
			
			// 最終行の場合、処理終了
			if(somDeliveryCd.equals("EOF")){
				break;
			}

			OrderVenderLinkListForReport deliveryData = getExistDeliveryInSalesTerms(deliveryList, somDeliveryCd);
			
			//販売条件マスタの存在チェック
			if( deliveryData == null){
				// 販売条件マスタにデータがない場合
				errorMessages.add(ActionMessages.GLOBAL_MESSAGE, 
					new ActionMessage("errors.ordervenderlink.insert.deliverycd.salesterms", 
									Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderdelivery")
									, rowNo+1
									, somDeliveryCd));

			}else{
				//販売条件マスタにデータがある場合、納入先コード存在チェック
				if( deliveryData.getDeliveryCd1() == null || deliveryData.getDeliveryCd1().trim().length() == 0 ){
					//納入先コード:{somDeliveryCd} は存在していません。
					errorMessages.add(ActionMessages.GLOBAL_MESSAGE, 
						new ActionMessage("errors.ordervenderlink.insert.deliverycd", 
						Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderdelivery"), rowNo+1 , somDeliveryCd));
					
				}
			}

			
			//データ取得
			ctmDeliveryCd1 = getString(row.getCell(DELIVERY_LINK_DATA_CTM_COLUMN));
			ctmDeliveryCd2 = getString(row.getCell((short)(DELIVERY_LINK_DATA_CTM_COLUMN + 1)));
			ctmDeliveryCd3 = getString(row.getCell((short)(DELIVERY_LINK_DATA_CTM_COLUMN + 2)));
			
			String ctmDeliveryCd = ctmDeliveryCd1 +"_" + ctmDeliveryCd2 +"_" +ctmDeliveryCd3;
			
			// 客先の納入先コードの長さが規定を超えている場合、エラーとする
			if(ctmDeliveryCd1.length() > DELIVERY_LENGTH || ctmDeliveryCd2.length() > DELIVERY_LENGTH || ctmDeliveryCd3.length() > DELIVERY_LENGTH){
				errorMessages.add(ActionMessages.GLOBAL_MESSAGE, 
					new ActionMessage("errors.ordervenderlink.insert.delivery.length", 
									Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderdelivery")
									, rowNo+1
									, ctmDeliveryCd.replace("__", ""),DELIVERY_LENGTH));
				
			}
			
			// 未入力判定
			if (StringUtils.isEmpty(somDeliveryCd) && StringUtils.isEmpty(ctmDeliveryCd1) && StringUtils.isEmpty(ctmDeliveryCd2) && StringUtils.isEmpty(ctmDeliveryCd3)) {
				break;
			}
			
			//beanにセット
			deliveryBean.setVenderGroupCd(venderGroupCd);
			deliveryBean.setSomDeliveryCd(somDeliveryCd);;
			deliveryBean.setCtmDeliveryCd1(ctmDeliveryCd1);
			deliveryBean.setCtmDeliveryCd2(ctmDeliveryCd2);
			deliveryBean.setCtmDeliveryCd3(ctmDeliveryCd3);
			
			//アップロードリストにadd
			uploadDeliveryList.add(deliveryBean);
			
		}
		
		////////////納入先処理完了
		
		// =================================================================================================================
		// 品目シートの処理を記載
		// =================================================================================================================
		// ブックの2番目(ordervenderlinklistitem)シートを指定
		sheet = wb.getSheet(ITEM_SHEET_NO);
		
		List<OrderVenderItem> uploadItemList = new ArrayList<OrderVenderItem>();
		
		//得意先グループコードが記入されている行を取得
		row = sheet.getRow(VENDER_GROUP_CD_ROW);
		
		//得意先グループコードを取得
		venderGroupCd = getString(row.getCell(VENDER_GROUP_CD_COLUMN));
		
		
		//得意先グループコードが空白の場合エラーメッセージ
		if(venderGroupCd == null){
			//ordervenderitemシート:得意先グループコードが未入力です。
			errorMessages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("errors.ordervenderlink.insert.ordervendergroup.null",
								Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderitem")));
			return errorMessages;
		}
		
		//得意先グループコードがマスタに登録されていなかったらエラーメッセージ
		if(!venderGroupCd.equals("99999")&& orderVenderMasterDao.getEntity(venderGroupCd, null) == null){
			//ordervenderitemシート:指定の得意先グループコード{venderGroupCd}は存在していません。
			errorMessages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("errors.ordervenderlink.insert.ordervendergroup",
								Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderitem"), venderGroupCd));
			return errorMessages;
		}
		
		// チェック用の品目リストの取得
		List<OrderVenderLinkListForReport> itemList = this.getItemList(venderGroupCd);

		//データ開始行に行番号をセット
		rowNo = ITEM_LINK_DATA_ROW;
		
		// =================================================================================================================
		// 品目のファイル読込処理
		// =================================================================================================================
		for(;;rowNo++){
			
			//得意先グループコードが99999のときアップロード対象外
			if(venderGroupCd.compareTo("99999") == 0){
				break;
			}
			
			//読み込んだデータ格納
			OrderVenderItem itemBean = new OrderVenderItem();
			if(rowNo > MAX_ROW){
				errorMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.ordervenderlink.insert.record.num"));
				return errorMessages;
			}
			//行の取得
			row = sheet.getRow(rowNo);
//			if(row == null){
//				// 行の取得ができなかった場合、その行は飛ばす。
//				continue;
//			}
			
			String somItemCd = null;
			String ctmItemCd1 = null;
			String ctmItemCd2 = null;
			String ctmItemCd3 = null;
			
			//指定のセルからそれぞれの値取得
			somItemCd = getString(row.getCell(ITEM_LINK_DATA_SOM_COLUMN));
			// 最終行の場合、処理終了
			if(somItemCd.equals("EOF")){
				break;
			}
			
			OrderVenderLinkListForReport itemData = this.getExistItemInSalesTerms(itemList, somItemCd);
					
			//販売条件マスタの存在チェック
			if(itemData == null){
				// 販売条件マスタにデータがない場合
				errorMessages.add(ActionMessages.GLOBAL_MESSAGE, 
					new ActionMessage("errors.ordervenderlink.insert.itemcd.salesterms", 
										Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderitem")
										, rowNo+1
										, somItemCd));
			}else{
				// 販売条件マスタにデータがある場合、
				//品目コード存在チェック
				if( itemData.getItemName() == null || itemData.getItemName().trim().length() == 0){
					//品目コード:{somItemCd} は存在していません。
					errorMessages.add(ActionMessages.GLOBAL_MESSAGE, 
						new ActionMessage("errors.ordervenderlink.insert.itemcd", 
										Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderitem")
										, rowNo+1 
										, somItemCd));
				}

			}
			
			ctmItemCd1 = getString(row.getCell(ITEM_LINK_DATA_CTM_COLUMN));
			ctmItemCd2 = getString(row.getCell((short)(ITEM_LINK_DATA_CTM_COLUMN + 1)));
			ctmItemCd3 = getString(row.getCell((short)(ITEM_LINK_DATA_CTM_COLUMN + 2)));
			String ctmItemCd = ctmItemCd1 + "_" + ctmItemCd2 + "_" + ctmItemCd3;
			
			// 客先の納入先コードの長さが規定を超えている場合、エラーとする
			if(ctmItemCd1.length() > ITEM_LENGTH || ctmItemCd2.length() > ITEM_LENGTH || ctmItemCd3.length() > ITEM_LENGTH){
				errorMessages.add(ActionMessages.GLOBAL_MESSAGE, 
					new ActionMessage("errors.ordervenderlink.insert.item.length", 
									Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderdelivery")
									, rowNo+1
									, ctmItemCd.replace("__", ""),ITEM_LENGTH));
				
			}

			// 未入力判定
			if (StringUtils.isEmpty(somItemCd) && StringUtils.isEmpty(ctmItemCd1) && StringUtils.isEmpty(ctmItemCd2) && StringUtils.isEmpty(ctmItemCd3)) {
				break;
			}
			
			//beanにセット
			itemBean.setVenderGroupCd(venderGroupCd);
			itemBean.setSomItemCd(somItemCd);;
			itemBean.setCtmItemCd1(ctmItemCd1);
			itemBean.setCtmItemCd2(ctmItemCd2);
			itemBean.setCtmItemCd3(ctmItemCd3);
			
			//アップロードリストにadd
			uploadItemList.add(itemBean);
			
		}
		

		// ===============================================================================================
		// 重複チェック処理
		// ===============================================================================================
		ActionMessages messages = new ActionMessages();
		
		//アップロードリストが空でなければ
		if(!uploadDeliveryList.isEmpty()){

			// 客先情報の重複チェック
			checkDeliveryDouble(uploadDeliveryList,errorMessages);
		}
		//アップロードリストが空でなければ
		if(!uploadItemList.isEmpty()){
			
			// 客先情報の重複チェック
			checkItemDouble(uploadItemList,errorMessages);
		}
		// エラーがある場合、メッセージを返す
		if(errorMessages.size() >0){
			return errorMessages;
		}

		// ===============================================================================================
		// 登録処理
		// ===============================================================================================
		if(!uploadDeliveryList.isEmpty()){
			//現在納入先連携マスタに登録されている情報を削除
			List<OrderVenderDelivery> deleteDeliveryList = orderVenderDeliveryDao.getList(venderGroupCd);
			for(OrderVenderDelivery deleteDeliveryBean : deleteDeliveryList){
				int deleteNum = orderVenderDeliveryDao.delete(deleteDeliveryBean);
				if (deleteNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}

			//データアップロードのDB更新処理
			messages = uploadDeliveryBean(uploadDeliveryList, tantoCd);
			//エラーが存在した場合メッセージに追加
			if(!messages.isEmpty()){
				errorMessages.add(messages);
				throw new Exception();
			}
		}

		//アップロードリストが空でなければ
		if(!uploadItemList.isEmpty()){
			
			//現在納入先連携マスタに登録されている情報を削除
			List<OrderVenderItem> deleteItemList = orderVenderItemDao.getList(venderGroupCd);
			for(OrderVenderItem deleteBean : deleteItemList){
				int deleteNum = orderVenderItemDao.delete(deleteBean);
				if (deleteNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}

			//データアップロードのデータ更新処理
			messages = uploadItemBean(uploadItemList, tantoCd);
			//エラーが存在した場合メッセージに追加
			if(!messages.isEmpty()){
				errorMessages.add(messages);
				throw new Exception();
			}
		}
		
		return errorMessages;
	}

	/**
	 * 得意先グループ一覧検索
	 * @param condition 検索条件
	 * @return List<OrderVenderMasterList>
	 * @throws NoDataException NoDataException
	 */
	public List<OrderVenderMasterList> getList(final OrderVenderMasterListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);
		
		List<OrderVenderMasterList> list = orderVenderMasterListDao.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}
	
	/**
	 * 納入先連携データ存在チェック.
	 * @param venderGroupCd String
	 * @param somDeliveryCd String
	 * @return boolean true:存在している false:存在していない
	 */
	public boolean checkDeliveryExist(final String venderGroupCd, final String somDeliveryCd){
		
		if(orderVenderDeliveryDao.getEntity(venderGroupCd, somDeliveryCd) != null){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 品目連携データ存在チェック.
	 * @param venderGroupCd String
	 * @param somDeliveryCd String
	 * @return boolean true:存在している false:存在していない
	 */
	public boolean checkItemExist(final String venderGroupCd, final String somItemCd){
		
		if(orderVenderItemDao.getEntity(venderGroupCd, somItemCd) != null){
			return true;
		}
		
		return false;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final OrderVenderMasterListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}
	
	/**
	 * 得意先グループ一覧検索（帳票用）
	 * @param venderGroupCd 得意先グループ番号
	 * @return List<OrderVenderLinkListForReport>
	 */
	public List<OrderVenderLinkListForReport> getDeliveryListForReport(
			final String venderGroupCd) {
		List<OrderVenderLinkListForReport> deliveryList = orderVenderLinkListForReportDao
				.getDeliveryListForReport(venderGroupCd);

		if (deliveryList.isEmpty()) {
			return null;
		}

		return deliveryList;
	}
	
	/**
	 * 得意先グループ一覧検索（品目用）
	 * @param venderGroupCd 得意先グループ番号
	 * @return List<OrderVenderLinkListForReport>
	 */
	public List<OrderVenderLinkListForReport> getItemListForReport(
			final String venderGroupCd) {
		List<OrderVenderLinkListForReport> itemList = orderVenderLinkListForReportDao
				.getItemListForReport(venderGroupCd);

		if (itemList.isEmpty()) {
			return null;
		}

		return itemList;
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
	 * 得意先グループ一覧取得
	 * @return List<VenderGroupForComboboxes>
	 */
	public List<VenderGroupForComboboxes> getVenderGroupList() {
		return venderGroupForComboboxesDao.getListForComboboxes();
	}

	/**
	 * 得意先グループコンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createVenderGroupAllCombobox() {
		// 得意先グループマスタからステータスコンボボックス用配列を取得
		List<ComboBoxItems> res = createVenderGroupCombobox();
		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(ShippingConst.COMBO_ALL_VALUE);
		allItem.setLabales(ShippingConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	
	/**
	 * HSSFRichTextStringの返却(NULL/空文字判定)
	 * @return
	 */
	private String getString(HSSFCell hssfCell) {
		String ret = "";
		try {
			if(null != hssfCell && null != hssfCell.getRichStringCellValue() && hssfCell.getRichStringCellValue().getString().compareTo("")!=0){
				ret = hssfCell.getRichStringCellValue().getString();
			}
		} catch(Exception e) {
			try {
				if(null != hssfCell && 0 != hssfCell.getNumericCellValue()){
					ret = String.valueOf((int)hssfCell.getNumericCellValue());
				}
			} catch(Exception ex) {
				return ret;
			}
		}
		
		return ret;
	}
	
	/**
	 * 納入先連携データ追加登録
	 * @param beanList 登録データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 * @return errorMessages ActionMessages
	 */
	private ActionMessages uploadDeliveryBean(final List<OrderVenderDelivery> beanList,
			final String tantoCd) {
		ActionMessages errorMessages = new ActionMessages();
		// 更新処理
		for (OrderVenderDelivery bean : beanList) {
			bean.setUpdatorCd(tantoCd); // 更新者コード
			bean.setUpdateDate(AecDateUtils.getCurrentTimestamp()); // 更新日時
			
			if(!checkDeliveryExist(bean.getVenderGroupCd(), bean.getSomDeliveryCd())){
				bean.setInputorCd(tantoCd); // 登録者コード
				bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				
				int insertNum = orderVenderDeliveryDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}else {
				// 更新処理
				int updateNum = orderVenderDeliveryDao.update(bean);
				if (updateNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}
		}
		return errorMessages;
	}

	/**
	 * 納入先の客先情報重複チェック
	 * @param beanList 登録データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 * @return errorMessages ActionMessages
	 */
	private void checkDeliveryDouble(final List<OrderVenderDelivery> beanList,ActionMessages errorMessages) {
		
		// 客先情報ループ
		for(int i = 0;i<beanList.size();i++){
			OrderVenderDelivery source = beanList.get(i);
			
			// 重複チェックループ
			for(int j =i+1;j<beanList.size();j++){
				OrderVenderDelivery target = beanList.get(j);
				
				// 未設定ではなく、客先のコードが一致する場合、メッセージを設定。
				if(source.getCtmDeliveryCd1()!= null && !source.getCtmDeliveryCd1().equals("")){
					if(source.getCtmDeliveryCd1().equals(target.getCtmDeliveryCd1())){
						errorMessages.add(ActionMessages.GLOBAL_MESSAGE, 
							new ActionMessage("errors.ordervenderlink.insert.double.deliverycd", 
												Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderdelivery")
												, DELIVERY_LINK_DATA_ROW + i + 1
												, source.getCtmDeliveryCd1()
												, DELIVERY_LINK_DATA_ROW + j + 1
									
									));
						
						break;
					}
					
				}
			}			
		}
	}

	/**
	 * 納入先の客先情報重複チェック
	 * @param beanList 登録データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 * @return errorMessages ActionMessages
	 */
	private void checkItemDouble(final List<OrderVenderItem> beanList,ActionMessages errorMessages) {
		
		// 客先情報ループ
		for(int i = 0;i<beanList.size();i++){
			OrderVenderItem source = beanList.get(i);
			
			// 重複チェックループ
			for(int j =i+1;j<beanList.size();j++){
				OrderVenderItem target = beanList.get(j);
				
				// 未設定ではなく、客先のコードが一致する場合、メッセージを設定。
				if(source.getCtmItemCd1()!= null && !source.getCtmItemCd1().equals("")){
					if(source.getCtmItemCd1().equals(target.getCtmItemCd1())){
						errorMessages.add(ActionMessages.GLOBAL_MESSAGE, 
							new ActionMessage("errors.ordervenderlink.insert.double.itemcd", 
												Constants.RB_APPLICATION_PROPERTIES.getString("ordervenderlink.sheet.ordervenderitem")
												, ITEM_LINK_DATA_ROW + i + 1
												, source.getCtmItemCd1()
												, ITEM_LINK_DATA_ROW + j + 1
									));
						break;
					}
				}
			}			
		}
	}

	/**
	 * 品目連携データ追加登録
	 * @param beanList 登録データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 * @return errorMessages ActionMessages
	 */
	private ActionMessages uploadItemBean(final List<OrderVenderItem> beanList,
			final String tantoCd) {
		ActionMessages errorMessages = new ActionMessages();
		// 更新処理
		for (OrderVenderItem bean : beanList) {
			bean.setUpdatorCd(tantoCd); // 更新者コード
			bean.setUpdateDate(AecDateUtils.getCurrentTimestamp()); // 更新日時
			
			if(!checkItemExist(bean.getVenderGroupCd(), bean.getSomItemCd())){
				bean.setInputorCd(tantoCd); // 登録者コード
				bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				
				int insertNum = orderVenderItemDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}else {
				// 更新処理
				int updateNum = orderVenderItemDao.update(bean);
				if (updateNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}
		}
		
		return errorMessages;
	}
	
	/**
	 * 販売条件マスタ存在チェック(納入先)
	 * @param venderGroupCd String
	 * @param somDeliveryCd String
	 * @return Boolean true:存在している false:存在していない
	 */
	private OrderVenderLinkListForReport getExistDeliveryInSalesTerms(List<OrderVenderLinkListForReport> deliveryList , String somDeliveryCd){
	
		//リストのコードと一致していたらtrueを返す
		for(OrderVenderLinkListForReport deliveryBean : deliveryList){
			if(deliveryBean.getSomDeliveryCd().equals(somDeliveryCd)){
				return deliveryBean;
			}
		}
		
		//合致なしでfalseを返す
		return null;
	}
	
	/**
	 * 販売条件マスタ一覧を取得
	 * @param venderGroupCd
	 * @return
	 */
	private List<OrderVenderLinkListForReport> getDeliveryList(String venderGroupCd){
		//販売条件マスタに存在している納入先リストを取得
		List<OrderVenderLinkListForReport> deliveryList = orderVenderLinkListForReportDao.getDeliveryListInSalesTerms(venderGroupCd);
		return deliveryList;
	}

	
	/**
	 * 販売条件マスタ存在チェック(品目)
	 * @param venderGroupCd String
	 * @param somItemCd String
	 * @return Boolean true:存在している false:存在していない
	 */
	private OrderVenderLinkListForReport getExistItemInSalesTerms(List<OrderVenderLinkListForReport> itemList , String somItemCd){
		
		//リストのコードと一致していたらitemBeanを返す
		for(OrderVenderLinkListForReport itemBean : itemList){
			if(itemBean.getSomItemCd().equals(somItemCd)){
				return itemBean;
			}
		}
		
		//合致なしでnullを返す
		return null;
	}

	/**
	 * 販売条件マスタ一覧の取得
	 * @param venderGroupCd
	 * @return
	 */
	public List<OrderVenderLinkListForReport> getItemList(String venderGroupCd) {
		//販売条件マスタに存在している納入先リストを取得
		List<OrderVenderLinkListForReport> itemList = orderVenderLinkListForReportDao.getItemListInSalesTerms(venderGroupCd);
		return itemList;
	}
	
	
	/**
	 * TMPファイルパス取得
	 */
	public String getTmpFilePath(){
		
		String filePath = commonDao.getEntity("ORDER_READ").getCommonValue();
		
		return filePath;
	}
	/* -------------------- setter -------------------- */
	
	/**
	 * commonDaoを設定します。
	 * @param commonDao commonDao
	 */
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	/**
	 * orderVenderMasterDaoを設定します。
	 * @param orderVenderMasterDao orderVenderMasterDao
	 */
	public void setOrderVenderMasterDao(OrderVenderMasterDao orderVenderMasterDao) {
		this.orderVenderMasterDao = orderVenderMasterDao;
	}
	
	/**
	 * orderVenderDeliveryDaoを設定します。
	 * @param orderVenderDeliveryDao orderVenderDeliveryDao
	 */
	public void setOrderVenderDeliveryDao(
			OrderVenderDeliveryDao orderVenderDeliveryDao) {
		this.orderVenderDeliveryDao = orderVenderDeliveryDao;
	}

	/**
	 * orderVenderItemDaoを設定します。
	 * @param orderVenderItemDao orderVenderItemDao
	 */
	public void setOrderVenderItemDao(OrderVenderItemDao orderVenderItemDao) {
		this.orderVenderItemDao = orderVenderItemDao;
	}
	
	/**
	 * orderVenderMasterListDaoを設定します。
	 * @param orderVenderMasterListDao orderVenderMasterListDao
	 */
	public void setOrderVenderMasterListDao(final OrderVenderMasterListDao orderVenderMasterListDao) {
		this.orderVenderMasterListDao = orderVenderMasterListDao;
	}
	
	/**
	 * orderVenderLinkListForReportDaoを設定します。
	 * @param orderVenderLinkListForReportDao orderVenderLinkListForReportDao
	 */
	public void setOrderVenderLinkListForReportDao(
			final OrderVenderLinkListForReportDao orderVenderLinkListForReportDao) {
		this.orderVenderLinkListForReportDao = orderVenderLinkListForReportDao;
	}
	
	/**
	 * venderGroupForComboboxesDaoを設定します。
	 * @param venderGroupForComboboxesDao venderGroupForComboboxesDao
	 */
	public void setVenderGroupForComboboxesDao(final VenderGroupForComboboxesDao venderGroupForComboboxesDao) {
		this.venderGroupForComboboxesDao = venderGroupForComboboxesDao;
	}

}
