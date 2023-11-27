/*
 * 
 *
 * entityName=RepDirectionMaterialUsed
 * packageName=directionmaterialusedforreport
 * methodName=getListForReport
 *
 */

SELECT
	DIVISION
,	CASE WHEN KBN='1' THEN FINANCIAL_CLASS_CD
		ELSE NULL
	END	AS FINANCIAL_CLASS_CD_DISP

,	CASE WHEN KBN='1' THEN ITEM_CD
		ELSE NULL
	END	AS ITEM_CD_DISP

,	CASE WHEN KBN='1' THEN ITEM_NAME
		ELSE NULL
	END	AS ITEM_NAME

,	STYLE_OF_PACKING
,	SUM(RESULT_QTY)		AS RESULT_QTY
,	M_ITEM_DIVISION
,	M_ITEM_CD
,	M_ITEM_NAME
,	M_STYLE_OF_PACKING
,	0			AS UNITPRICE
,	SUM(M_RESULT_QTY)	AS M_RESULT_QTY
,	0			AS AMOUNT

FROM
	REP_DIRECTION_MATERIAL_USED

WHERE
	TARGET_MONTH BETWEEN TO_CHAR(/*condition.srhDateFrom*/'200910')
			AND  TO_CHAR(/*condition.srhDateTo*/'200910')

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	(ITEM_CD LIKE /*condition.srhItemCd*/'%' OR ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

/*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
	AND OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

/*IF (condition.srhBalanceType == 2)*/
	AND SHIPPER_DIVISION IN (0,1,2)
/*END*/

/*IF (condition.srhBalanceType == 1)*/
	AND	SHIPPER_DIVISION = 3
/*END*/

/*IF (condition.srhDirectionDivision == 1)*/
	AND	TYPE_DIVISION = 3
/*END*/

/*IF (condition.srhDirectionDivision == 2)*/
	AND	TYPE_DIVISION = 0
/*END*/

/*IF (condition.srhDirectionDivision == 3)*/
	AND	TYPE_DIVISION IN (6,7)
/*END*/

GROUP BY
	DIVISION_CD
,	DIVISION
,	KBN
,	FINANCIAL_CLASS_CD
,	ITEM_CD
,	ITEM_NAME
,	OTHER_COMPANY_CD1
,	STYLE_OF_PACKING
,	SHIPPER_DIVISION
,	TYPE_DIVISION
,	M_ITEM_DIVISION
,	M_ITEM_CD
,	M_ITEM_NAME
,	M_STYLE_OF_PACKING

ORDER BY
	DIVISION_CD
,	FINANCIAL_CLASS_CD
,	ITEM_CD
,	KBN
,	M_ITEM_CD



