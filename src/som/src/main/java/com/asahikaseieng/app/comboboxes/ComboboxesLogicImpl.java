/*
 * Created on 2013/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.List;
import java.util.ResourceBundle;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.names.NamesSearch;
import com.asahikaseieng.dao.nonentity.master.names.NamesSearchDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * コンボボックス用ロジック
 * @author atts
 */
public class ComboboxesLogicImpl implements ComboboxesLogic {

	/* 名称操作DAO */
	private NamesSearchDao namesSearchDao;

	/**
	 * コンストラクタ
	 */
	public ComboboxesLogicImpl() {
	}

	/**
	 * コンボボックス用データ取得（「すべて」を追加しないコンボボックス）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxNomal(
			String nameDivision,
			String languageId) {
		
		ComboboxesBean bean = new ComboboxesBean();
		
		/* コンボボックスデータを取得 */
		List<NamesSearch> list = namesSearchDao.getNamesList(nameDivision, languageId);
		if(list == null) return bean;
		
		String[] values;
		String[] labels;
		
		/* すべてを追加しない */
		labels = new String[list.size()];
		values = new String[list.size()];
		
		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {	
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}
		
		bean.setLabels(labels);
		bean.setValues(values);
		return bean;
		
	}

	/**
	 * コンボボックス用データ取得（（「すべて」を追加するコンボボックス）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxAll(
			String nameDivision,
			String languageId) {

		ComboboxesBean bean = new ComboboxesBean();
		
		/* すべてを取得する */
		ResourceBundle bundle = null;
		bundle = Constants.RB_APPLICATION_PROPERTIES;
		String itemName = bundle.getString("I00041");
		
		/* コンボボックスデータを取得 */
		List<NamesSearch> list = namesSearchDao.getNamesList(nameDivision, languageId);
		if(list == null) return bean;
		
		String[] values;
		String[] labels;
		
		/* すべてを追加する */
		labels = new String[list.size()+1];
		values = new String[list.size()+1];
		
		/* コンボボックスアイテムに「0:すべて」を追加 */
		labels[0] = itemName;
		values[0] = Constants.COMB_ALL_CD;
		
		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {	
			labels[i+1] = list.get(i).getName01();
			values[i+1] = list.get(i).getNameCd();
		}
		
		bean.setLabels(labels);
		bean.setValues(values);
		return bean;
	}

	/**
	 * コンボボックス用データ取得（パラメータ名称を追加するコンボボックス）
	 * @param itemName コンボ名称
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxOther(
			String itemName,
			String nameDivision,
			String languageId) {

		ComboboxesBean bean = new ComboboxesBean();
		
		/* コンボボックスデータを取得 */
		List<NamesSearch> list = namesSearchDao.getNamesList(nameDivision, languageId);
		if(list == null) return bean;
		
		String[] values;
		String[] labels;
		
		/* パラメータ名称を追加する */
		labels = new String[list.size()+1];
		values = new String[list.size()+1];
		
		/* コンボボックスアイテムに「0:パラメータ名称」を追加 */
		labels[0] = itemName;
		values[0] = Constants.COMB_ALL_CD;
		
		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {	
			labels[i+1] = list.get(i).getName01();
			values[i+1] = list.get(i).getNameCd();
		}
		
		bean.setLabels(labels);
		bean.setValues(values);
		return bean;
	}
	
	
	/**
	 * コンボボックス用データ取得（「すべて」を追加しないコンボボックス） （絞り込み条件付き）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @param nameCd 名称コード
	 * @param name01 名称1
	 * @param name02 名称2
	 * @param name03 名称3 
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxNomal(
			String nameDivision,
			String languageId,
			String nameCd,
			String name01,
			String name02,
			String name03) {
		
		ComboboxesBean bean = new ComboboxesBean();
		
		/* コンボボックスデータを取得 */
		List<NamesSearch> list = namesSearchDao.getNamesListWithCondition
									(nameDivision, languageId, nameCd, name01, name02, name03);
		if(list == null) return bean;
		
		String[] values;
		String[] labels;
		
		/* すべてを追加しない */
		labels = new String[list.size()];
		values = new String[list.size()];
		
		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {	
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}
		
		bean.setLabels(labels);
		bean.setValues(values);
		return bean;
		
	}

	/**
	 * コンボボックス用データ取得（（「すべて」を追加するコンボボックス）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @param nameCd 名称コード
	 * @param name01 名称1
	 * @param name02 名称2
	 * @param name03 名称3  
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxAll(
			String nameDivision,
			String languageId,
			String nameCd,
			String name01,
			String name02,
			String name03) {

		ComboboxesBean bean = new ComboboxesBean();
		
		/* すべてを取得する */
		ResourceBundle bundle = null;
		bundle = Constants.RB_APPLICATION_PROPERTIES;
		String itemName = bundle.getString("I00041");
		
		/* コンボボックスデータを取得 */
		List<NamesSearch> list = namesSearchDao.getNamesListWithCondition
									(nameDivision, languageId, nameCd, name01, name02, name03);
		if(list == null) return bean;
		
		String[] values;
		String[] labels;
		
		/* すべてを追加する */
		labels = new String[list.size()+1];
		values = new String[list.size()+1];
		
		/* コンボボックスアイテムに「0:すべて」を追加 */
		labels[0] = itemName;
		values[0] = Constants.COMB_ALL_CD;
		
		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {	
			labels[i+1] = list.get(i).getName01();
			values[i+1] = list.get(i).getNameCd();
		}
		
		bean.setLabels(labels);
		bean.setValues(values);
		return bean;
	}

	/**
	 * コンボボックス用データ取得（パラメータ名称を追加するコンボボックス）
	 * @param itemName コンボ名称
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @param nameCd 名称コード
	 * @param name01 名称1
	 * @param name02 名称2
	 * @param name03 名称3   
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxOther(
			String itemName,
			String nameDivision,
			String languageId,
			String nameCd,
			String name01,
			String name02,
			String name03			
			) {

		ComboboxesBean bean = new ComboboxesBean();
		
		/* コンボボックスデータを取得 */
		List<NamesSearch> list = namesSearchDao.getNamesListWithCondition
									(nameDivision, languageId, nameCd, name01, name02, name03);
		if(list == null) return bean;
		
		String[] values;
		String[] labels;
		
		/* パラメータ名称を追加する */
		labels = new String[list.size()+1];
		values = new String[list.size()+1];
		
		/* コンボボックスアイテムに「0:パラメータ名称」を追加 */
		labels[0] = itemName;
		values[0] = Constants.COMB_ALL_CD;
		
		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {	
			labels[i+1] = list.get(i).getName01();
			values[i+1] = list.get(i).getNameCd();
		}
		
		bean.setLabels(labels);
		bean.setValues(values);
		return bean;
	}	
	
	/**
	 * コンボボックス用データをSeqの昇順ソートで取得（「すべて」を追加しないコンボボックス）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxNomalOrderbySeq(
			String nameDivision,
			String nameCd,
			String name01,
			String name02,
			String name03,
			String languageId) {
		
		ComboboxesBean bean = new ComboboxesBean();
		
		/* コンボボックスデータを取得 */
		List<NamesSearch> list = namesSearchDao.getNamesListOrderbySeq(nameDivision, nameCd,
																		name01, name02, name03,languageId);
		if(list == null) return bean;
		
		String[] values;
		String[] labels;
		String[] invisibility;
		
		/* すべてを追加しない */
		labels = new String[list.size()];
		values = new String[list.size()];
		invisibility = new String[list.size()];
				
		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {	
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
			if (list.get(i).getInvisibility() != null) {
				invisibility[i] = list.get(i).getInvisibility().toString();
			} else {
				invisibility[i] = "0";	// 未設定であれば、削除しないように設定する
			}
		}
		
		bean.setLabels(labels);
		bean.setValues(values);
		bean.setInvisibility(invisibility);
		
		return bean;
		
	}
	
	
	/**
	 * コンボボックス用データ取得（「すべて」を追加しないコンボボックス） （絞り込み条件付き in句）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @param nameCd 名称コード
	 * @param name01 名称1
	 * @param name02 名称2
	 * @param name03 名称3 
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxNomalByList(
			String nameDivision,
			String languageId,
			List<String> nameCd,
			List<String> name01,
			List<String> name02,
			List<String> name03) {	
		
		return getComboboxBase(null, nameDivision, languageId, 
				nameCd, name01, name02, name03, 1);
	}
	
	/**
	 * コンボボックス用データ取得（「すべて」を追加しないコンボボックス） （絞り込み条件付き in句）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @param nameCd 名称コード
	 * @param name01 名称1
	 * @param name02 名称2
	 * @param name03 名称3 
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxAllByList(
			String nameDivision,
			String languageId,
			List<String> nameCd,
			List<String> name01,
			List<String> name02,
			List<String> name03) {	
			
		return getComboboxBase(null, nameDivision, languageId, 
				nameCd, name01, name02, name03, 2);
	}	
	

	/**
	 * コンボボックス用データ取得（パラメータ名称を追加するコンボボックス）
	 * @param itemName コンボ名称
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @param nameCd 名称コード
	 * @param name01 名称1
	 * @param name02 名称2
	 * @param name03 名称3   
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public ComboboxesBean getComboboxOtherByList(
			String itemName,
			String nameDivision,
			String languageId,
			List<String> nameCd,
			List<String> name01,
			List<String> name02,
			List<String> name03			
			) {
		
		return getComboboxBase(itemName, languageId, nameDivision, 
				nameCd, name01, name02, name03, 3);
	}
	
	
	/**
	 * コンボボックス用データ取得
	 * @param itemName コンボ名称
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @param nameCd 名称コード
	 * @param name01 名称1
	 * @param name02 名称2
	 * @param name03 名称3   
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	private  ComboboxesBean getComboboxBase	(
			String itemName,
			String nameDivision,
			String languageId,
			List<String> nameCd,
			List<String> name01,
			List<String> name02,
			List<String> name03,
			int mode
			) {
	
		ComboboxesBean bean = new ComboboxesBean();
	
		/* コンボボックスデータを取得 */
		List<NamesSearch> list = namesSearchDao.getNamesListWithConditionList
									(nameDivision, languageId, nameCd, name01, name02, name03);		
		
		if(list == null) return bean;
		
		String[] values;
		String[] labels;

		if (mode == 2 ) {
			// すべを追加
			labels = new String[list.size()+1];
			values = new String[list.size()+1];
			
			/* すべてを取得する */
			ResourceBundle bundle = null;
			bundle = Constants.RB_APPLICATION_PROPERTIES;
			String allValue = bundle.getString("I00041");			
			
			/* コンボボックスアイテムに「0:すべて」を追加 */
			labels[0] = allValue;
			values[0] = Constants.COMB_ALL_CD;			
			
		} else if (mode == 3) {
			// その他項目を追加
			labels = new String[list.size()+1];
			values = new String[list.size()+1];
			
			/* コンボボックスアイテムに「0:パラメータ名称」を追加 */
			labels[0] = itemName;
			values[0] = Constants.COMB_ALL_CD;			
		} else {
			labels = new String[list.size()];
			values = new String[list.size()];				
		}
		
		/* コンボボックスアイテム設定処理 */
		if (mode == 1) {			
			for (int i = 0; i < list.size(); i++) {	
				labels[i] = list.get(i).getName01();
				values[i] = list.get(i).getNameCd();
			}			
		} else {
			for (int i = 0; i < list.size(); i++) {	
				labels[i+1] = list.get(i).getName01();
				values[i+1] = list.get(i).getNameCd();
			}
		}

		
		bean.setLabels(labels);
		bean.setValues(values);
		
		return bean;
	}
	
	// setter--------------------------------------------------------------------------

	/**
	 * namesSearchDaoを設定します。
	 * @param namesSearchDao
	 *            namesSearchDao
	 */
	public void setNamesSearchDao(
			final NamesSearchDao NamesSearchDao) {
		this.namesSearchDao = NamesSearchDao;
	}
}
