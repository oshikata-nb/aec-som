/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
*/

/**
 * 包装実績－生産ラインコンボボックス一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=PkgRdirectionLineForComboboxes
 * packageName=pkgrdirection
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
