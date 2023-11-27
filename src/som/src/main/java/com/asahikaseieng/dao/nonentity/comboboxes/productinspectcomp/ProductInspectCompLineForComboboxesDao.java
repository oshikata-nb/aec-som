/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.productinspectcomp;

import java.util.List;

/**
 * 製品検査完了入力－生産ラインDaoインターフェース.
 *
 * @author tosco
 */
public interface ProductInspectCompLineForComboboxesDao {

	/** BEANアノテーション */
	Class<ProductInspectCompLineForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.productinspectcomp.ProductInspectCompLineForComboboxes.class;

    /**
	 * 製品検査完了入－生産ライン一覧取得	 * @return List<ProductInspectCompLineForComboboxes> 検索結果リスト	 */
	List<ProductInspectCompLineForComboboxes> findAll();

}
