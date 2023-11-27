/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
*/

/**
 * 売上詳細(標準)SQL
 *
 * @author tosco
 *
 * entityName=SalesDetailStandardEntity
 * packageName=sales
 * methodName=getEntity
 *
 */
SELECT
    SALES.SALES_NO
,   SALES.SALES_DATE
,   SALES.CANCEL_SALES_NO
,   SALES.ITEM_CD
,   SALES.ORGANIZATION_CD
,   SALES.VENDER_CD
,   SALES.INPUT_TANTO_CD
,   SALES.SALES_TANTO_CD
,   SALES.PRODUCT_LOTNO
,   SALES.ORDER_NO
,   SALES.ORDER_ROW_NO
,   SALES.SALES_QUANTITY
,   SALES.SALES_UNITPRICE
,   SALES.STANDARD_UNITPRICE
,   SALES.STANDARD_DISCOUNT
,   SALES.SPECIAL_DISCOUNT
,   SALES.TMP_UNITPRICE_FLG
,   SALES.REMARK
,	SALES.SUMMARY
,   SALES.INPUT_DIVISION
,   SALES.DELIVERY_CD
,   SALES.TAX_DIVISION
,   SALES.TAX_AMOUNT
,	SALES.TAX_CD
,   SALES.INVOICE_CD
,   SALES.SALES_AMOUNT
,   SALES.SALES_BASIC
,   SALES.TAX_RATIO
,   SALES.SLIP_PUBLISH_COMP
,   SALES.DATA_TYPE
,   SALES.DATA_TOTAL_DIVISION
,   SALES.CATEGORY_DIVISION
,   SALES.ACCOUNT_YEARS
,   SALES.ACCOUNT_DEBIT_SECTION_CD
,   SALES.ACCOUNT_CREDIT_SECTION_CD
,   SALES.DEBIT_TITLE_CD
,   SALES.CREDIT_TITLE_CD
,   SALES.DEPOSIT_UPDATE_DIVISION
,   SALES.CLAIM_UPDATE_DIVISION
,   SALES.ERASER_COMPLETE_DIVISION
,   SALES.CHARGE_ORGANIZATION_CD
,   SALES.KEEP_FLAG
,   SALES.RY_CD
,   SALES.HOUSING_LOCATION_CD
,   SALES.PACKAGE_DIRECTION_NO
,   SALES.INPUT_DATE
,   SALES.INPUTOR_CD
,   SALES.UPDATE_DATE
,   SALES.UPDATOR_CD
,   ITEM.ITEM_NAME
,   ITEM.OTHER_COMPANY_CD1
,   ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIVISION
,   ITEM.STYLE_OF_PACKING
,   DELIVERY.DELIVERY_NAME1
,   DELIVERY.SEARCH_KANA
,   (DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3 ) AS ADDRESS
,   DELIVERY.TEL_NO
,   VENDER.VENDER_NAME1
,   VENDER.VENDER_SHORTED_NAME
,   ORGANIZATION.ORGANIZATION_NAME AS CHARGE_ORGANIZATION_NAME
,   DBUMON.SECTION_NAME            AS ACCOUNTS_DEBIT_SECTION_NAME
,   DACCOUNTS.ACCOUNTS_NAME        AS DEBIT_TITLE_NAME
,   CBUMON.SECTION_NAME            AS ACCOUNTS_CREDIT_SECTION_NAME
,   CACCOUNTS.ACCOUNTS_NAME        AS CREDIT_TITLE_NAME
,   REASON.RY_DESCRIPTION
,   (SELECT CLASSIFICATION.CATEGORY_NAME
     FROM   CLASSIFICATION
     WHERE  CLASSIFICATION.DATA_TYPE(+) = '1'
       AND CLASSIFICATION.CATEGORY_DIVISION = SALES.CATEGORY_DIVISION
       AND ROWNUM <= 1
    ) AS CATEGORY_NAME
FROM
    SALES
,   ITEM
,   DELIVERY
,   VENDER
,   ORGANIZATION
,   BUMON DBUMON
,   (SELECT ACCOUNTS_CD, ACCOUNTS_NAME
     FROM ACCOUNTS
     GROUP BY ACCOUNTS_CD, ACCOUNTS_NAME
    ) DACCOUNTS
,   BUMON CBUMON
,   (SELECT ACCOUNTS_CD, ACCOUNTS_NAME
     FROM ACCOUNTS
     GROUP BY ACCOUNTS_CD, ACCOUNTS_NAME
    ) CACCOUNTS
,   REASON
WHERE
	SALES.SALES_NO = /*salesNo*/
AND SALES.ITEM_CD = ITEM.ITEM_CD(+)
AND	SALES.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND	SALES.VENDER_CD = VENDER.VENDER_CD(+)
AND	VENDER.VENDER_DIVISION(+) = 'TS'
AND SALES.CHARGE_ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND SALES.ACCOUNT_DEBIT_SECTION_CD = DBUMON.SECTION_CD(+)
AND SALES.DEBIT_TITLE_CD = DACCOUNTS.ACCOUNTS_CD(+)
AND SALES.ACCOUNT_CREDIT_SECTION_CD = CBUMON.SECTION_CD(+)
AND SALES.CREDIT_TITLE_CD = CACCOUNTS.ACCOUNTS_CD(+)
AND SALES.RY_CD = REASON.RY_CD(+)