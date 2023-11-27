/*
 * Created on 2013/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.List;

import com.asahikaseieng.exception.NoDataException;


/**
 * ロジッククラス interface. コンボボックス用ロジック
 * @author atts
 */
public interface ComboboxesLogic {

	/**
	 * コンボボックス用データ取得（「すべて」を追加しないコンボボックス）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	ComboboxesBean getComboboxNomal(
			String nameDivision,
			String languageId) throws NoDataException;
	
	/**
	 * コンボボックス用データ取得（「すべて」を追加するコンボボックス）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	ComboboxesBean getComboboxAll(
			String nameDivision,
			String languageId) throws NoDataException;

	/**
	 * コンボボックス用データ取得（（パラメータ名称を追加するコンボボックス）
	 * @param itemName コンボ名称
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	ComboboxesBean getComboboxOther(
			String itemName,
			String nameDivision,
			String languageId) throws NoDataException;
	
	/**
	 * コンボボックス用データ取得（「すべて」を追加しないコンボボックス）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @param nameCd 名称コード
	 * @param name01 名称1
	 * @param name02 名称2
	 * @param name03 名称3  
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	ComboboxesBean getComboboxNomal(
			String nameDivision,
			String languageId,
			String nameCd,
			String name01,
			String name02,
			String name03			
			) throws NoDataException;
	
	/**
	 * コンボボックス用データ取得（「すべて」を追加するコンボボックス）
	 * @param nameDivision 名称区分
	 * @param languageId 言語コード
	 * @param nameCd 名称コード
	 * @param name01 名称1
	 * @param name02 名称2
	 * @param name03 名称3  
	 * @return ComboboxesBean 結果格納用Bean
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	ComboboxesBean getComboboxAll(
			String nameDivision,
			String languageId,
			String nameCd,
			String name01,
			String name02,
			String name03			
			) throws NoDataException;

	/**
	 * コンボボックス用データ取得（（パラメータ名称を追加するコンボボックス）
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
	ComboboxesBean getComboboxOther(
			String itemName,
			String nameDivision,
			String languageId,
			String nameCd,
			String name01,
			String name02,
			String name03			
			) throws NoDataException;	

	/**
	 * コンボボックス用データをSeqで昇順ソートして取得（「すべて」を追加しないコンボボックス）
	 * @param nameDivision 名称区分
	 * @param nameCd 名称コード
	 * @param name01  名称1
	 * @param name02  名称2
	 * @param name03  名称3
	 * @param languageId 言語コード
	 * @return  ComboboxesBean 結果格納用Bean
	 * @throws NoDataException
	 */
	ComboboxesBean getComboboxNomalOrderbySeq(
			String nameDivision,
			String nameCd,
			String name01,
			String name02,
			String name03,
			String languageId) throws NoDataException;
	
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
	ComboboxesBean getComboboxNomalByList(
			String nameDivision,
			String languageId,
			List<String> nameCd,
			List<String> name01,
			List<String> name02,
			List<String> name03) throws NoDataException;
	
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
	ComboboxesBean getComboboxAllByList(
			String nameDivision,
			String languageId,
			List<String> nameCd,
			List<String> name01,
			List<String> name02,
			List<String> name03) throws NoDataException;
	
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
	ComboboxesBean getComboboxOtherByList(
			String itemName,
			String nameDivision,
			String languageId,
			List<String> nameCd,
			List<String> name01,
			List<String> name02,
			List<String> name03	) throws NoDataException;
	
	
}
