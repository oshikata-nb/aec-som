/*
 * 受注検索画面用SQL
 *
 * entityName=OrderDetailListForReport
 * packageName=orderlistforreport
 * methodName=getDetailListForReport
 *
 */
SELECT 
	ORDER_DETAIL.ORDER_NO
,	ORDER_DETAIL.ROW_NO
,	ORDER_DETAIL.SHIPPING_NO
,	ORDER_DETAIL.ITEM_CD
,	ORDER_LIST.ITEM_NAME
,	ORDER_LIST.STYLE_OF_PACKING
,	ORDER_LIST.OTHER_COMPANY_CD1
,	ORDER_DETAIL.ORDER_QTY
,	ORDER_DETAIL.ORDER_UNITPRICE
,	ORDER_DETAIL.STANDARD_UNITPRICE
,	ORDER_DETAIL.STANDARD_DISCOUNT
,	ORDER_DETAIL.SPECIAL_DISCOUNT
,	ORDER_DETAIL.TMP_UNITPRICE_FLG
,	ORDER_DETAIL.MATSS
,	ORDER_DETAIL.ESTIMATE_STANDARD_AMOUNT
,	ORDER_DETAIL.ESTIMATE_MATSS
,	ORDER_DETAIL.INPUT_DATE
,	ORDER_DETAIL.INPUTOR_CD
,	NVL(CASE ORDER_LIST.ORDER_DIVISION WHEN 3 THEN ORDER_LIST.PURCHASE_STATUS_NAME ELSE ORDER_LIST.SHIPPING_STATUS_NAME END,'受注登録') AS STATUS_NAME
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	ORDER_DETAIL.UPDATE_DATE
,	ORDER_DETAIL.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
FROM
	(SELECT 
		ORDER_HEAD.ORDER_NO
	,	ORDER_DETAIL.ROW_NO
	,	ORDER_HEAD.ORDER_DIVISION
	,	ORDER_DIVISION.DIVISION_NAME
	,	DELIVERY.DELIVERY_CD
	,	DELIVERY.DELIVERY_NAME1 || DELIVERY_NAME2 AS DELIVERY_NAME
	,	DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3 AS DELIVERY_ADDRESS
	,	(CASE WHEN ORDER_HEAD.DELIVERY_TEL_NO IS NULL
		THEN 
			DELIVERY.TEL_NO
		ELSE 
			ORDER_HEAD.DELIVERY_TEL_NO
	END) AS DELIVERY_TEL_NO
	,	VENDER.VENDER_CD
	,	VENDER.VENDER_NAME1 || VENDER.VENDER_NAME2 AS VENDER_NAME
	,	FNC_GET_UPPER_VENDER(order_head.BALANCE_CD ,2) as vender_cd2
	,	FNC_GET_UPPER_VENDER(order_head.BALANCE_CD ,3) as vender_cd3
	,	FNC_GET_UPPER_VENDER(order_head.BALANCE_CD ,4) as vender_cd4
	,	FNC_GET_UPPER_VENDER(order_head.BALANCE_CD ,5) as vender_cd5
	,	VENDER.ORGANIZATION_CD
	,	ORGANIZATION.ORGANIZATION_NAME
	,	ORDER_HEAD.SALES_TANTO_CD
--	,	SALES_TANTO.SALES_TANTO_NAME
	,	(SELECT LOGIN.TANTO_NM FROM LOGIN WHERE LOGIN.TANTO_CD = ORDER_HEAD.SALES_TANTO_CD) AS SALES_TANTO_NAME
	,	ORDER_HEAD.INPUT_TANTO_CD
--	,	INPUT_TANTO.INPUT_TANTO_NAME
	,	(SELECT LOGIN.TANTO_NM FROM LOGIN WHERE LOGIN.TANTO_CD = ORDER_HEAD.INPUT_TANTO_CD) AS INPUT_TANTO_NAME
	,	ITEM_QUEUE.ITEM_CD
	,	ITEM_QUEUE.ITEM_NAME
	,	ITEM_QUEUE.STYLE_OF_PACKING
	,	ITEM_QUEUE.OTHER_COMPANY_CD1
	,	ORDER_HEAD.ORDER_DATE
	,	ORDER_HEAD.SCHEDULED_SHIPPING_DATE
	,	NVL(SHIPPING_STATUS.STATUS_CD,'99') AS SHIPPING_STATUS_CD
	,	NVL(SHIPPING_STATUS.SHIPPING_STATUS_NAME,'受注登録') AS SHIPPING_STATUS_NAME
	,	NVL(PURCHASE_STATUS.STATUS_CD,'99') AS PURCHASE_STATUS_CD
	,	NVL(PURCHASE_STATUS.PURCHASE_STATUS_NAME,'受注登録') AS PURCHASE_STATUS_NAME
	,	ORDER_HEAD.CARRY_CD
	FROM 
		ORDER_HEAD
	,	ORDER_DETAIL
	,	DELIVERY
	,	VENDER
	,   VALID_ITEM_QUEUE ITEM_QUEUE_MAX
	,	VALID_ITEM_QUEUE ITEM_QUEUE
	,	SHIPPING
	,	(SELECT ORDER_NO,ORDER_ROW_NO,STATUS FROM PURCHASE_SUBCONTRACT WHERE ORDER_NO IS NOT NULL GROUP BY ORDER_NO,ORDER_ROW_NO,STATUS) PURCHASE_SUBCONTRACT
	,	ORGANIZATION
--	,	(SELECT ORDER_HEAD.ORDER_NO AS ORDER_NO,LOGIN.TANTO_CD AS TANTO_CD,LOGIN.TANTO_NM AS SALES_TANTO_NAME FROM ORDER_HEAD,LOGIN WHERE ORDER_HEAD.SALES_TANTO_CD = LOGIN.TANTO_CD) SALES_TANTO
--	,	(SELECT ORDER_HEAD.ORDER_NO AS ORDER_NO,LOGIN.TANTO_CD AS TANTO_CD,LOGIN.TANTO_NM AS INPUT_TANTO_NAME FROM ORDER_HEAD,LOGIN WHERE ORDER_HEAD.INPUT_TANTO_CD = LOGIN.TANTO_CD) INPUT_TANTO
	,	(SELECT NAMES.NAME_CD AS DIVISION_CD ,NAMES.NAME01 AS DIVISION_NAME FROM NAMES WHERE NAMES.NAME_DIVISION = 'ORDR') ORDER_DIVISION
	,	(SELECT NAMES.NAME_CD AS STATUS_CD ,NAMES.NAME01 AS SHIPPING_STATUS_NAME FROM NAMES WHERE NAMES.NAME_DIVISION = 'ORST') SHIPPING_STATUS
	,	(SELECT NAMES.NAME_CD AS STATUS_CD ,NAMES.NAME01 AS PURCHASE_STATUS_NAME FROM NAMES WHERE NAMES.NAME_DIVISION = 'PUST') PURCHASE_STATUS
	WHERE 
		ORDER_HEAD.ORDER_NO = ORDER_DETAIL.ORDER_NO
	AND	ORDER_HEAD.ORDER_DIVISION = ORDER_DIVISION.DIVISION_CD
	AND	ORDER_HEAD.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
	AND	ORDER_HEAD.VENDER_CD = VENDER.VENDER_CD(+) AND VENDER.VENDER_DIVISION(+) = 'TS'
	
	AND 	ITEM_QUEUE_MAX.ITEM_CD(+) = ITEM_QUEUE.ITEM_CD
	AND 	ITEM_QUEUE_MAX.VERSION(+) = ITEM_QUEUE.VERSION
	
	AND	ORDER_DETAIL.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND	ORDER_HEAD.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
--	AND	ORDER_HEAD.ORDER_NO = SALES_TANTO.ORDER_NO(+)
--	AND	ORDER_HEAD.ORDER_NO = INPUT_TANTO.ORDER_NO(+)

	AND	ORDER_DETAIL.SHIPPING_NO = SHIPPING.SHIPPING_NO(+)
	AND	SHIPPING.SHIPPING_STATUS = SHIPPING_STATUS.STATUS_CD(+)

	AND	ORDER_DETAIL.ORDER_NO = PURCHASE_SUBCONTRACT.ORDER_NO(+) 
	AND	ORDER_DETAIL.ROW_NO = PURCHASE_SUBCONTRACT.ORDER_ROW_NO(+) 
	AND	PURCHASE_SUBCONTRACT.STATUS = PURCHASE_STATUS.STATUS_CD(+)
	) ORDER_LIST
,	ORDER_DETAIL
,	LOGIN INPUTOR
,	LOGIN UPDATOR

WHERE ORDER_LIST.ORDER_NO IS NOT NULL
AND	ORDER_LIST.ORDER_NO = ORDER_DETAIL.ORDER_NO
AND	ORDER_LIST.ROW_NO = ORDER_DETAIL.ROW_NO
AND	ORDER_DETAIL.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	ORDER_DETAIL.UPDATOR_CD = UPDATOR.TANTO_CD(+)

-- 受注番号From
/*IF (condition.srhOrderNoFrom != null) && (condition.srhOrderNoFrom != "")*/
AND	ORDER_LIST.ORDER_NO >= /*condition.srhOrderNoFrom*/'JU00000001' 
/*END*/

-- 受注番号To
/*IF (condition.srhOrderNoTo != null) && (condition.srhOrderNoTo != "")*/
AND 	ORDER_LIST.ORDER_NO <= /*condition.srhOrderNoTo*/'JU999999999' 
/*END*/

-- 受注区分が全てではない場合
/*IF (condition.srhOrderDivision != 0)*/
AND 	ORDER_LIST.ORDER_DIVISION = /*condition.srhOrderDivision*/3
/*END*/

-- 受注区分が全ての場合
/*IF (condition.srhOrderDivision == 0)*/
	-- ステータスが全てでは無い場合
	/*IF (condition.srhStatus !=0)*/

		-- ステータスが受注登録の場合
		/*IF (condition.srhStatus == 99)*/
			AND ((ORDER_LIST.SHIPPING_STATUS_CD = 99 AND ORDER_LIST.PURCHASE_STATUS_CD = 99) OR ORDER_LIST.PURCHASE_STATUS_CD = 7)
		/*END*/
		
		/*IF (condition.srhStatus != 99)*/
			-- 出荷ステータス検索
			AND 	(ORDER_LIST.SHIPPING_STATUS_CD = /*condition.srhStatus*/3 
	
			-- ステータスが出荷未確定の場合
			/*IF (condition.srhStatus == 1) */
			OR 	ORDER_LIST.PURCHASE_STATUS_CD = 7
			/*END*/
		
			-- ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
			/*IF (condition.srhStatus > 1) && (condition.srhStatus < 5)*/
			OR 	ORDER_LIST.PURCHASE_STATUS_CD > 1 AND ORDER_LIST.PURCHASE_STATUS_CD < 6
			/*END*/
			
			-- ステータスが出荷未確定の場合
			/*IF (condition.srhStatus == 5)*/
			OR 	ORDER_LIST.PURCHASE_STATUS_CD = 6
			/*END*/
			
			)
		/*END*/
	/*END*/
	
/*END*/

-- 受注区分が全てではなく　かつ　仕入直送品では無い場合
/*IF (condition.srhOrderDivision != 0) && (condition.srhOrderDivision != 3)*/
	-- ステータスが全てでは無い場合
	/*IF (condition.srhStatus !=0)*/
		AND 	ORDER_LIST.SHIPPING_STATUS_CD = /*condition.srhStatus*/3
	/*END*/
/*END*/

-- 受注区分が仕入直送品の場合
/*IF (condition.srhOrderDivision == 3)*/
	
	-- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
	/*IF (condition.srhStatus == 1) */
	AND 	ORDER_LIST.PURCHASE_STATUS_CD = 7
	/*END*/

	-- 受注区分が仕入直送品　かつ　ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
	/*IF (condition.srhStatus > 1) && (condition.srhStatus < 5)*/
	AND 	ORDER_LIST.PURCHASE_STATUS_CD > 1 AND ORDER_LIST.PURCHASE_STATUS_CD < 6
	/*END*/
	
	-- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
	/*IF (condition.srhStatus == 5)*/
	AND 	ORDER_LIST.PURCHASE_STATUS_CD = 6
	/*END*/
	
	-- 受注区分が仕入直送品　かつ　ステータスが受注登録の場合
	/*IF (condition.srhStatus == 99)*/
	AND 	ORDER_LIST.PURCHASE_STATUS_CD = 7
	/*END*/
	
/*END*/

-- 受注日From
/*IF(condition.srhOrderDateFrom != null) && (condition.srhOrderDateFrom != "")*/
AND 	TO_CHAR(ORDER_LIST.ORDER_DATE, 'YYYY/MM/DD') >= /*condition.srhOrderDateFrom*/'2000/01/01'
/*END*/

-- 受注日To
/*IF(condition.srhOrderDateTo != null) && (condition.srhOrderDateTo != "")*/
AND 	TO_CHAR(ORDER_LIST.ORDER_DATE, 'YYYY/MM/DD') <= /*condition.srhOrderDateTo*/'2010/12/31'
/*END*/

-- 出荷予定日From
/*IF(condition.srhScheduledShippingDateFrom != null) && (condition.srhScheduledShippingDateFrom != "")*/
AND 	TO_CHAR(ORDER_LIST.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') >= /*condition.srhScheduledShippingDateFrom*/'2000/01/01'
/*END*/

-- 出荷予定日To
/*IF(condition.srhScheduledShippingDateTo != null) && (condition.srhScheduledShippingDateTo != "")*/
AND 	TO_CHAR(ORDER_LIST.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') <= /*condition.srhScheduledShippingDateTo*/'2010/12/31'
/*END*/

-- 納入先
/*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
AND	(ORDER_LIST.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%' OR ORDER_LIST.DELIVERY_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%'))
/*END*/

-- 納入先住所
/*IF (condition.srhDeliveryAddress != null) && (condition.srhDeliveryAddress != "")*/
AND	ORDER_LIST.DELIVERY_ADDRESS LIKE /*condition.srhDeliveryAddress*/'%'
/*END*/

-- 納入先電話番号
/*IF (condition.srhDeliveryTelNo != null) && (condition.srhDeliveryTelNo != "")*/
AND	ORDER_LIST.DELIVERY_TEL_NO LIKE /*condition.srhDeliveryTelNo*/'%'
/*END*/

-- 得意先
/*IF (condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
AND	(ORDER_LIST.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR ORDER_LIST.VENDER_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%')
	OR ORDER_LIST.VENDER_CD2 = REPLACE (/*condition.srhVenderCd*/,'%','')
	OR ORDER_LIST.VENDER_CD3 = REPLACE (/*condition.srhVenderCd*/,'%','')
	OR ORDER_LIST.VENDER_CD4 = REPLACE (/*condition.srhVenderCd*/,'%','')
	OR ORDER_LIST.VENDER_CD5 = REPLACE (/*condition.srhVenderCd*/,'%','')
)
/*END*/

-- 担当部署
/*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
AND	(ORDER_LIST.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORDER_LIST.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

-- 営業担当者
/*IF (condition.srhSalesTantoCd != null) && (condition.srhSalesTantoCd != "")*/
AND	(ORDER_LIST.SALES_TANTO_CD LIKE /*condition.srhSalesTantoCd*/'%' OR ORDER_LIST.SALES_TANTO_NAME LIKE /*condition.srhSalesTantoCd*/'%')
/*END*/

-- 入力担当者
/*IF (condition.srhInputTantoCd != null) && (condition.srhInputTantoCd != "")*/
AND	(ORDER_LIST.INPUT_TANTO_CD LIKE /*condition.srhInputTantoCd*/'%' OR ORDER_LIST.INPUT_TANTO_NAME LIKE /*condition.srhInputTantoCd*/'%')
/*END*/

-- 品目コード
/*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND	(ORDER_LIST.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR ORDER_LIST.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

-- 他社コード1
/*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
AND	ORDER_LIST.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

-- 運送会社
/*IF (condition.srhCarryCd != null) && (condition.srhCarryCd != "")*/
	/*IF ( condition.srhCarryCd != "0" )*/
AND	ORDER_LIST.CARRY_CD LIKE /*condition.srhCarryCd*/'%'
	/*END*/
/*END*/

-- 受注番号と行番号でソート
ORDER BY ORDER_DETAIL.ORDER_NO,ORDER_DETAIL.ROW_NO ASC



