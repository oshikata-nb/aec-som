/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/

/**
 * 包装指図－生産ラインコンボボックス一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=PkgDirectionLineForComboboxes
 * packageName=pkgdirection
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
