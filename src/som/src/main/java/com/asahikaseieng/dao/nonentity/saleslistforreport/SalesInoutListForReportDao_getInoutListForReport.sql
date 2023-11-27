/*
 * entityName=SalesInoutListForReport
 * packageName=saleslistforreport
 * methodName=getInoutListForReport
 *
 */
SELECT
	SALES_INOUT_RECORD.SALES_NO
,	SALES_INOUT_RECORD.SALES_ROW_NO
,	SALES_INOUT_RECORD.LOCATION_CD
,	LOCATION.LOCATION_NAME
,	SALES_INOUT_RECORD.LOT_NO
,	SALES_INOUT_RECORD.QTY
,	SALES_INOUT_RECORD.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	SALES_INOUT_RECORD.INPUT_DATE
,	SALES_INOUT_RECORD.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
,	SALES_INOUT_RECORD.UPDATE_DATE

FROM	SALES
,	SALES_INOUT_RECORD
,   (SELECT
           CATEGORY_DIVISION
         , MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
      FROM CLASSIFICATION
     WHERE DATA_TYPE = '1'
     GROUP BY CATEGORY_DIVISION
    ) CLASSIFICATION_MIN
,   CLASSIFICATION
,   VENDER
,	LOCATION
,   VALID_ITEM_QUEUE MAX_ITEM
,   VALID_ITEM_QUEUE ITEM
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,   (SELECT ORDER_DETAIL.ORDER_NO,ORDER_DETAIL.ROW_NO,ORDER_HEAD.CUSTOMER_ORDER_NO FROM ORDER_HEAD,ORDER_DETAIL WHERE ORDER_HEAD.ORDER_NO = ORDER_DETAIL.ORDER_NO) ORDER_DATA
,	DELIVERY

WHERE	SALES.SALES_NO IS NOT NULL
AND	SALES.SALES_NO = SALES_INOUT_RECORD.SALES_NO
AND	SALES_INOUT_RECORD.LOCATION_CD = LOCATION.LOCATION_CD
AND SALES.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND SALES.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND CLASSIFICATION_MIN.CATEGORY_DIVISION(+) = SALES.CATEGORY_DIVISION
AND CLASSIFICATION.DATA_TYPE(+) = '1'
AND CLASSIFICATION.CATEGORY_DIVISION(+)   = CLASSIFICATION_MIN.CATEGORY_DIVISION
AND CLASSIFICATION.DATA_TOTAL_DIVISION(+) = CLASSIFICATION_MIN.DATA_TOTAL_DIVISION
AND VENDER.VENDER_DIVISION(+) = 'TS'
AND VENDER.VENDER_CD(+) = SALES.VENDER_CD
AND MAX_ITEM.ITEM_CD(+) = SALES.ITEM_CD
AND ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
AND ITEM.VERSION(+) = MAX_ITEM.VERSION
AND SALES.ORDER_NO = ORDER_DATA.ORDER_NO(+)
AND SALES.ORDER_ROW_NO = ORDER_DATA.ROW_NO(+)
AND DELIVERY.DELIVERY_CD(+) = SALES.DELIVERY_CD


/*IF (( condition.srhSalesNo != null ) && ( condition.srhSalesNo != "" ))*/
	AND	SALES.SALES_NO LIKE /*condition.srhSalesNo*/''
/*END*/

/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "" ))*/
	/*IF (condition.srhCategoryDivision == "0")*/
		/*IF (condition.srhCancelFlg)*/
			AND	SALES.CATEGORY_DIVISION < 0 
		/*END*/
		/*IF (!condition.srhCancelFlg)*/
			AND	SALES.CATEGORY_DIVISION <> 0 
		/*END*/
	/*END*/
	/*IF (condition.srhCategoryDivision != "0")*/
		/*IF (condition.srhCancelFlg)*/
			/*IF (condition.srhCategoryDivision == "1")*/
				AND	(SALES.CATEGORY_DIVISION = -1 OR SALES.CATEGORY_DIVISION = -11)
			/*END*/
			/*IF (condition.srhCategoryDivision == "2")*/
				AND	(SALES.CATEGORY_DIVISION = -2 OR SALES.CATEGORY_DIVISION = -12)
			/*END*/
			/*IF (condition.srhCategoryDivision == "3")*/
				AND	(SALES.CATEGORY_DIVISION = -3 OR SALES.CATEGORY_DIVISION = -13)
			/*END*/
			/*IF (condition.srhCategoryDivision == "4")*/
				AND	(SALES.CATEGORY_DIVISION = -4 OR SALES.CATEGORY_DIVISION = -14)
			/*END*/
			/*IF (condition.srhCategoryDivision == "9")*/
				AND	(SALES.CATEGORY_DIVISION = -9 OR SALES.CATEGORY_DIVISION = -19)
			/*END*/
		/*END*/
		/*IF (!condition.srhCancelFlg)*/
			/*IF (condition.srhCategoryDivision == "1")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 1 OR ABS(SALES.CATEGORY_DIVISION) = 11)
			/*END*/
			/*IF (condition.srhCategoryDivision == "2")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 2 OR ABS(SALES.CATEGORY_DIVISION) = 12)
			/*END*/
			/*IF (condition.srhCategoryDivision == "3")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 3 OR ABS(SALES.CATEGORY_DIVISION) = 13)
			/*END*/
			/*IF (condition.srhCategoryDivision == "4")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 4 OR ABS(SALES.CATEGORY_DIVISION) = 14)
			/*END*/
			/*IF (condition.srhCategoryDivision == "9")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 9 OR ABS(SALES.CATEGORY_DIVISION) = 19)
			/*END*/
		/*END*/
	/*END*/
/*END*/

/*IF (( condition.srhOrderNoFrom != null ) && ( condition.srhOrderNoFrom != "" ))*/
	AND	SALES.ORDER_NO >= /*condition.srhOrderNoFrom*/''
/*END*/

/*IF (( condition.srhOrderNoTo != null ) && ( condition.srhOrderNoTo != "" ))*/
	AND	SALES.ORDER_NO <= /*condition.srhOrderNoTo*/''
/*END*/

/*IF (( condition.srhSalesDateFrom != null ) && ( condition.srhSalesDateFrom != "" ))*/
	AND	TO_CHAR(SALES.SALES_DATE, 'YYYY/MM/DD') >= /*condition.srhSalesDateFrom*/''
/*END*/

/*IF (( condition.srhSalesDateTo != null ) && ( condition.srhSalesDateTo != "" ))*/
	AND	TO_CHAR(SALES.SALES_DATE, 'YYYY/MM/DD') <= /*condition.srhSalesDateTo*/''
/*END*/

/*IF (( condition.srhAccountYears != null ) && ( condition.srhAccountYears != "" ))*/
	AND	SALES.ACCOUNT_YEARS = /*condition.srhAccountYears*/''
/*END*/

/*IF (condition.srhTmpUnitpriceFlg)*/
	AND	SALES.TMP_UNITPRICE_FLG = 1
/*END*/

/*IF (condition.srhKeepFlag)*/
	AND	SALES.KEEP_FLAG = 2
/*END*/

/*IF (condition.srhMonthlyUpdateFlag)*/
	AND	(SALES.DEPOSIT_UPDATE_DIVISION = 1 OR SALES.CLAIM_UPDATE_DIVISION = 1)
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	(SALES.VENDER_CD LIKE /*condition.srhVenderCd*/'' OR VENDER.VENDER_SHORTED_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
	AND	SALES.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	SALES.ITEM_CD LIKE /*condition.srhItemCd*/''
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
    AND SALES.ITEM_CD IN (SELECT ITEM_CD
                          FROM   VALID_ITEM_QUEUE
                          WHERE  OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'')
/*END*/

/*IF (( condition.srhDeliveryCd != null ) && ( condition.srhDeliveryCd != "" ))*/
	AND	SALES.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/
/*END*/

/*IF (( condition.srhCustomerOrderNo != null ) && ( condition.srhCustomerOrderNo != "" ))*/
	AND	ORDER_DATA.CUSTOMER_ORDER_NO = /*condition.srhCustomerOrderNo*/
/*END*/

ORDER BY SALES_INOUT_RECORD.SALES_NO,SALES_INOUT_RECORD.SALES_ROW_NO




