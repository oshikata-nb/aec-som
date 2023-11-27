/*
 * Created on 2009/04/06
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.production;

import java.util.List;

/**
 * 生産ラインDaoインターフェース.
 *
 * @author tosco
 */
public interface ProductionLineForComboboxesDao {

	/** BEANアノテーション */
	Class<ProductionLineForComboboxes> BEAN = ProductionLineForComboboxes.class;

	/**
	 * 全件取得.
	 * @return Mgrecipeline
	 */
	List<ProductionLineForComboboxes> findAll();

}
