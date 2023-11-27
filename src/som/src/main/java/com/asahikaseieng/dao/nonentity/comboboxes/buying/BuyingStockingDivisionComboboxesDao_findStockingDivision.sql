/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
*/

/**
 * 仕入－仕入区分取得用SQL
 *
 * @author tosco
 *
 * entityName=BuyingStockingDivisionComboboxes
 * packageName=buying
 * methodName=findAll
 *
 */
SELECT
	UNI.CATEGORY_DIVISION ,
	UNI.DATA_TOTAL_DIVISION ,
	UNI.CATEGORY_NAME
FROM
	(SELECT
		'1' AS KIND,
		PLUS.CATEGORY_DIVISION ,
		PLUS.DATA_TOTAL_DIVISION ,
		PLUS.CATEGORY_NAME
	FROM
	        (SELECT
	            CATEGORY_DIVISION ,
	            DATA_TOTAL_DIVISION ,
	            CATEGORY_NAME
	        FROM
	            CLASSIFICATION
	        WHERE
	            DATA_TYPE = '3'              AND
	            CATEGORY_DIVISION NOT LIKE '-%'
	        ) PLUS
/*IF (cancel)*/
	UNION ALL 
	SELECT
		'2' AS KIND,
		NEGA.CATEGORY_DIVISION ,
		NEGA.DATA_TOTAL_DIVISION ,
		NEGA.CATEGORY_NAME
	FROM
		(SELECT
			CATEGORY_DIVISION ,
			DATA_TOTAL_DIVISION ,
			CATEGORY_NAME
		FROM
			CLASSIFICATION
		WHERE
			DATA_TYPE = '3'              AND
			CATEGORY_DIVISION LIKE '-%'
		) NEGA
/*END*/
	) UNI
ORDER BY
	KIND,
	CATEGORY_DIVISION