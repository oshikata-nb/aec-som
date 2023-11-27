/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/

/**
 * 検査待ち在庫計上－生産ライン一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=StockBookingLineForComboboxes
 * packageName=stockbooking
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
