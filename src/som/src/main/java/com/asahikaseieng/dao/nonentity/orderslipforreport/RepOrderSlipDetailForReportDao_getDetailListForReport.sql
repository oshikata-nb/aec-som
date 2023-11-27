/*
 * entityName=RepOrderSlipDetailForReport
 * packageName=orderSlipforreport
 * methodName=getDetailListForReport
 * 20191225 PG修正 バインド変数From S.Tanaka
 */
SELECT
	*
FROM REP_SLIP_ORDER_DETAIL
WHERE REP_SLIP_ORDER_DETAIL.ORDER_NO IS NOT NULL

-- 受注番号From
/*IF (condition.srhOrderNoFrom != null) && (condition.srhOrderNoFrom != "")*/
AND	REP_SLIP_ORDER_DETAIL.ORDER_NO >= /*condition.srhOrderNoFrom*/'JU00000001' 
/*END*/

-- 受注番号To
/*IF (condition.srhOrderNoTo != null) && (condition.srhOrderNoTo != "")*/
AND 	REP_SLIP_ORDER_DETAIL.ORDER_NO <= /*condition.srhOrderNoTo*/'JU999999999' 
/*END*/

-- 受注区分が全てではない場合
/*IF (condition.srhOrderDivision != 0)*/
AND 	REP_SLIP_ORDER_DETAIL.ORDER_DIVISION = /*condition.srhOrderDivision*/3
/*END*/

-- 受注区分が全ての場合
/*IF (condition.srhOrderDivision == 0)*/
	-- ステータスが全てでは無い場合
	/*IF (condition.srhStatus !=0)*/

		-- ステータスが受注登録の場合
		/*IF (condition.srhStatus == 99)*/
			AND ((REP_SLIP_ORDER_DETAIL.SHIPPING_STATUS_CD = 99 AND REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD = 99) OR REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD = 7)
		/*END*/
		
		/*IF (condition.srhStatus != 99)*/
			-- 出荷ステータス検索
			AND 	(REP_SLIP_ORDER_DETAIL.SHIPPING_STATUS_CD = /*condition.srhStatus*/3 
	
			-- ステータスが出荷未確定の場合
			/*IF (condition.srhStatus == 1) */
			OR 	REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD = 7
			/*END*/
		
			-- ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
			/*IF (condition.srhStatus > 1) && (condition.srhStatus < 5)*/
			OR 	REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD > 1 AND REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD < 6
			/*END*/
			
			-- ステータスが出荷未確定の場合
			/*IF (condition.srhStatus == 5)*/
			OR 	REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD = 6
			/*END*/
			
			)
		/*END*/
	/*END*/
	
/*END*/

-- 受注区分が全てではなく　かつ　仕入直送品では無い場合
/*IF (condition.srhOrderDivision != 0) && (condition.srhOrderDivision != 3)*/
	-- ステータスが全てでは無い場合
	/*IF (condition.srhStatus !=0)*/
		AND 	REP_SLIP_ORDER_DETAIL.SHIPPING_STATUS_CD = /*condition.srhStatus*/3
	/*END*/
/*END*/

-- 受注区分が仕入直送品の場合
/*IF (condition.srhOrderDivision == 3)*/
	
	-- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
	/*IF (condition.srhStatus == 1) */
	AND 	REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD = 7
	/*END*/

	-- 受注区分が仕入直送品　かつ　ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
	/*IF (condition.srhStatus > 1) && (condition.srhStatus < 5)*/
	AND 	REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD > 1 AND REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD < 6
	/*END*/
	
	-- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
	/*IF (condition.srhStatus == 5)*/
	AND 	REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD = 6
	/*END*/
	
	-- 受注区分が仕入直送品　かつ　ステータスが受注登録の場合
	/*IF (condition.srhStatus == 99)*/
	AND 	REP_SLIP_ORDER_DETAIL.PURCHASE_STATUS_CD = 7
	/*END*/
	
/*END*/

-- 受注日From
/*IF(condition.srhOrderDateFrom != null) && (condition.srhOrderDateFrom != "")*/
AND 	TO_CHAR(REP_SLIP_ORDER_DETAIL.ORDER_DATE, 'YYYY/MM/DD') >= /*condition.srhOrderDateFrom*/'2000/01/01'
/*END*/

-- 受注日To
/*IF(condition.srhOrderDateTo != null) && (condition.srhOrderDateTo != "")*/
AND 	TO_CHAR(REP_SLIP_ORDER_DETAIL.ORDER_DATE, 'YYYY/MM/DD') <= /*condition.srhOrderDateTo*/'2010/12/31'
/*END*/

-- 出荷予定日From
/*IF(condition.srhScheduledShippingDateFrom != null) && (condition.srhScheduledShippingDateFrom != "")*/
AND 	TO_CHAR(REP_SLIP_ORDER_DETAIL.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') >= /*condition.srhScheduledShippingDateFrom*/'2000/01/01'
/*END*/

-- 出荷予定日To
/*IF(condition.srhScheduledShippingDateTo != null) && (condition.srhScheduledShippingDateTo != "")*/
AND 	TO_CHAR(REP_SLIP_ORDER_DETAIL.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') <= /*condition.srhScheduledShippingDateTo*/'2010/12/31'
/*END*/

-- 納入先
/*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
AND	(REP_SLIP_ORDER_DETAIL.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%' OR REP_SLIP_ORDER_DETAIL.DELIVERY_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%'))
/*END*/

-- 納入先住所
/*IF (condition.srhDeliveryAddress != null) && (condition.srhDeliveryAddress != "")*/
AND	REP_SLIP_ORDER_DETAIL.DELIVERY_ADDRESS_ALL LIKE /*condition.srhDeliveryAddress*/'%'
/*END*/

-- 納入先電話番号
/*IF (condition.srhDeliveryTelNo != null) && (condition.srhDeliveryTelNo != "")*/
AND	REP_SLIP_ORDER_DETAIL.DELIVERY_TEL_NO LIKE /*condition.srhDeliveryTelNo*/'%'
/*END*/

-- 得意先
/*IF (condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
AND	(REP_SLIP_ORDER_DETAIL.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR REP_SLIP_ORDER_DETAIL.VENDER_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%')
	OR REP_SLIP_ORDER_DETAIL.VENDER_CD2 = REPLACE (/*condition.srhVenderCd*/,'%','')
	OR REP_SLIP_ORDER_DETAIL.VENDER_CD3 = REPLACE (/*condition.srhVenderCd*/,'%','')
	OR REP_SLIP_ORDER_DETAIL.VENDER_CD4 = REPLACE (/*condition.srhVenderCd*/,'%','')
	OR REP_SLIP_ORDER_DETAIL.VENDER_CD5 = REPLACE (/*condition.srhVenderCd*/,'%','')
)
/*END*/

-- 担当部署
/*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
AND	(REP_SLIP_ORDER_DETAIL.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR REP_SLIP_ORDER_DETAIL.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

-- 営業担当者
/*IF (condition.srhSalesTantoCd != null) && (condition.srhSalesTantoCd != "")*/
AND	(REP_SLIP_ORDER_DETAIL.SALES_TANTO_CD LIKE /*condition.srhSalesTantoCd*/'%' OR REP_SLIP_ORDER_DETAIL.SALES_TANTO_NAME LIKE /*condition.srhSalesTantoCd*/'%')
/*END*/

-- 入力担当者
/*IF (condition.srhInputTantoCd != null) && (condition.srhInputTantoCd != "")*/
AND	(REP_SLIP_ORDER_DETAIL.INPUT_TANTO_CD LIKE /*condition.srhInputTantoCd*/'%' OR REP_SLIP_ORDER_DETAIL.INPUT_TANTO_NAME LIKE /*condition.srhInputTantoCd*/'%')
/*END*/

-- 品目コード
/*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND	(REP_SLIP_ORDER_DETAIL.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR REP_SLIP_ORDER_DETAIL.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

-- 他社コード1
/*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
AND	REP_SLIP_ORDER_DETAIL.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

-- 運送会社
/*IF (condition.srhCarryCd != null) && (condition.srhCarryCd != "")*/
	/*IF ( condition.srhCarryCd != "0" )*/
AND	REP_SLIP_ORDER_DETAIL.CARRY_CD LIKE /*condition.srhCarryCd*/'%'
	/*END*/
/*END*/

-- 受注番号と行番号でソート
ORDER BY REP_SLIP_ORDER_DETAIL.ORDER_NO,REP_SLIP_ORDER_DETAIL.ROW_NO ASC



