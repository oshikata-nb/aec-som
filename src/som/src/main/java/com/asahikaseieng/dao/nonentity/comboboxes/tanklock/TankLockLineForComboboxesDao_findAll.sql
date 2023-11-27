/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/

/**
 * 調合タンク底弁インターロック解除/再設定－生産ライン一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=TankLockLineForComboboxes
 * packageName=tanklock
 * methodName=findAll
 *
 */
SELECT
	 PRODUCTION_LINE AS			PRODUCTION_LINE
	,PRODUCTION_LINE_NAME AS	PRODUCTION_LINE_NAME
FROM
    LINE
ORDER BY
    PRODUCTION_LINE
