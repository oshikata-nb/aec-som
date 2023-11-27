/*
 * entityName=RepOrderSlipDetailForReport
 * packageName=orderSlipforreport
 * methodName=getDetailListForReport
 */
WITH TMP AS 
(
	SELECT
	    REP_SLIP_ORDER_IMPORT_DETAIL.PK_NO
	FROM REP_SLIP_ORDER_IMPORT_DETAIL
	WHERE
	    REP_SLIP_ORDER_IMPORT_DETAIL.PK_NO IS NOT NULL
	    --受注グループ番号From
	    /*IF (condition.srhFrstOrderNoFrom != null) && (condition.srhFrstOrderNoFrom != "")*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.PK_NO >= /*condition.srhFrstOrderNoFrom*/'GR00000000' 
	    /*END*/
	    --受注グループ番号To
	    /*IF (condition.srhFrstOrderNoTo!= null) && (condition.srhFrstOrderNoTo != "")*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.PK_NO <= /*condition.srhFrstOrderNoTo*/'LR99999999' 
	    /*END*/
	    --客先注文番号
	    /*IF (condition.srhCtmOrderNo != null) && (condition.srhCtmOrderNo != "")*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.CUSTOMER_ORDER_NO LIKE /*condition.srhCtmOrderNo*/'00000000'
	    /*END*/
	    --得意先グループコード
	    /*IF ( condition.srhVenderGroupCd != "0" ) && (condition.srhVenderGroupCd != "99999")*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_GROUP_CD = /*condition.srhVenderGroupCd*/'%'
	    /*END*/
	    /*IF (condition.srhVenderGroupCd == "99999")*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_GROUP_CD IS NULL
	    /*END*/
	    --取込日From
	    /*IF (condition.srhImportDateFrom != null) && (condition.srhImportDateFrom != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.IMPORT_DATE, 'YYYY/MM/DD') >= /*condition.srhImportDateFrom*/'1900/01/01'
	    /*END*/
	    --取込日To
	    /*IF (condition.srhImportDateTo!= null) && (condition.srhImportDateTo != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.IMPORT_DATE, 'YYYY/MM/DD') <= /*condition.srhImportDateTo*/'2199/01/01'
	    /*END*/
	    --受注番号From
	    /*IF (condition.srhOrderNoFrom != null) && (condition.srhOrderNoFrom != "")*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.FIXED_ORDER_NO >= /*condition.srhOrderNoFrom*/'JU00000000' 
	    /*END*/
	    --受注番号To
	    /*IF (condition.srhOrderNoTo!= null) && (condition.srhOrderNoTo != "")*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.FIXED_ORDER_NO <= /*condition.srhOrderNoTo*/'JU99999999' 
	    /*END*/
	    -- 受注区分
	    /*IF (condition.srhOrderDivision != 0)*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_DIVISION = /*condition.srhOrderDivision*/3
	    /*END*/
	    --受注ステータス
	    -- 受注ステータスが先付受注の場合
	    /*IF (condition.srhOrderStatus == 90)*/
	        AND REP_SLIP_ORDER_IMPORT_DETAIL.FIXED_ORDER_NO IS NULL
	    /*END*/
	    -- ステータスが受注登録の場合
	    /*IF (condition.srhOrderStatus == 99)*/
	        AND REP_SLIP_ORDER_IMPORT_DETAIL.FIXED_ORDER_NO IS NOT NULL
	    /*END*/
	                
	    --受注区分が全ての場合
	    /*IF (condition.srhOrderDivision == 0)*/
	        -- ステータスが全てでは無い場合
	        /*IF ((condition.srhOrderStatus != 0) && (condition.srhOrderStatus != 99) && (condition.srhOrderStatus != 90))*/
	            -- 出荷ステータス検索
	            AND (REP_SLIP_ORDER_IMPORT_DETAIL.SHIPPING_STATUS_CD = /*condition.srhOrderStatus*/3
	            
	            -- ステータスが出荷未確定の場合
	            /*IF (condition.srhOrderStatus == 1) */
	            OR REP_SLIP_ORDER_IMPORT_DETAIL.PURCHASE_STATUS_CD = 7
	            /*END*/
	            
	            -- ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
	            /*IF (condition.srhOrderStatus > 1) && (condition.srhOrderStatus < 5)*/
	            OR REP_SLIP_ORDER_IMPORT_DETAIL.PURCHASE_STATUS_CD  > 1 AND REP_SLIP_ORDER_IMPORT_DETAIL.PURCHASE_STATUS_CD  < 6
	            /*END*/
	            
	            -- ステータスが出荷未確定の場合
	            /*IF (condition.srhOrderStatus == 5)*/
	            OR REP_SLIP_ORDER_IMPORT_DETAIL.PURCHASE_STATUS_CD  = 6
	            /*END*/
	            )
	        /*END*/
	        
	    /*END*/
	
	    
	    -- 受注区分が全てではなく　かつ　仕入直送品では無い場合
	    /*IF (condition.srhOrderDivision != 0) && (condition.srhOrderDivision != 3)*/
	        -- ステータスが全てでは無い場合
	        /*IF ((condition.srhOrderStatus != 0) && (condition.srhOrderStatus != 90) && (condition.srhOrderStatus != 99))*/
	            AND REP_SLIP_ORDER_IMPORT_DETAIL.SHIPPING_STATUS_CD = /*condition.srhOrderStatus*/3
	        /*END*/
	    /*END*/
	
	    -- 受注区分が仕入直送品の場合
	    /*IF ((condition.srhOrderDivision == 3) && (condition.srhOrderStatus != 90) && (condition.srhOrderStatus != 99))*/
	        
	        -- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
	        /*IF (condition.srhOrderStatus == 1) */
	        AND REP_SLIP_ORDER_IMPORT_DETAIL.PURCHASE_STATUS_CD  = 7
	        /*END*/
	    
	        -- 受注区分が仕入直送品　かつ　ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
	        /*IF (condition.srhOrderStatus > 1) && (condition.srhOrderStatus < 5)*/
	        AND REP_SLIP_ORDER_IMPORT_DETAIL.PURCHASE_STATUS_CD > 1 AND REP_SLIP_ORDER_IMPORT_DETAIL.PURCHASE_STATUS_CD < 6
	        /*END*/
	        
	        -- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
	        /*IF (condition.srhOrderStatus == 5)*/
	        AND REP_SLIP_ORDER_IMPORT_DETAIL.PURCHASE_STATUS_CD = 6
	        /*END*/
	    
	    /*END*/
	    --受注日From
	    /*IF (condition.srhOrderDateFrom != null) && (condition.srhOrderDateFrom != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_DATE, 'YYYY/MM/DD') >= /*condition.srhOrderDateFrom*/'1900/01/01'
	    /*END*/
	    --受注日To
	    /*IF (condition.srhOrderDateTo!= null) && (condition.srhOrderDateTo != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_DATE, 'YYYY/MM/DD') <= /*condition.srhOrderDateTo*/'2199/01/01'
	    /*END*/
	    --納入先
	    /*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
	    AND	(REP_SLIP_ORDER_IMPORT_DETAIL.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%'
	        OR REP_SLIP_ORDER_IMPORT_DETAIL.SEARCH_KANA LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%')
	        OR REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_CD_01 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_CD_02 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_CD_03 LIKE /*condition.srhDeliveryCd*/'%' 
	        OR REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_NAME_01 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_NAME_02 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_NAME_03 LIKE /*condition.srhDeliveryCd*/'%'
	    )
	    /*END*/
	    --住所
	    /*IF (condition.srhAddress != null) && (condition.srhAddress != "")*/
	    AND (REP_SLIP_ORDER_IMPORT_DETAIL.ADDRESS1 || REP_SLIP_ORDER_IMPORT_DETAIL.ADDRESS2 || REP_SLIP_ORDER_IMPORT_DETAIL.ADDRESS3 LIKE /*condition.srhAddress*/'%'
	        OR REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_ADDRESS_01 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_ADDRESS_02 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_ADDRESS_03 LIKE /*condition.srhAddress*/'%')
	    /*END*/
	    --納入先電話番号
	    /*IF (condition.srhDeliveryTelNo != null) && (condition.srhDeliveryTelNo != "")*/
	    AND (REP_SLIP_ORDER_IMPORT_DETAIL.DELIVERY_TEL_NO LIKE /*condition.srhDeliveryTelNo*/'%'
	        OR REP_SLIP_ORDER_IMPORT_DETAIL.CTM_DELIVERY_TEL_NO LIKE /*condition.srhDeliveryTelNo*/'%')
	    /*END*/
	    --客先返信
	    /*IF (condition.srhSlipPublishComp == "1")*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.DEL_DATE_SEND_DATE IS NULL
	    /*END*/
	    /*IF (condition.srhSlipPublishComp == "2")*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.DEL_DATE_SEND_DATE IS NOT NULL
	    /*END*/
	    --出荷予定日From
	    /*IF (condition.srhScheduledShippingDateFrom != null) && (condition.srhScheduledShippingDateFrom != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') >= /*condition.srhScheduledShippingDateFrom*/'1900/01/01'
	    /*END*/
	    --出荷予定日To
	    /*IF (condition.srhScheduledShippingDateTo!= null) && (condition.srhScheduledShippingDateTo != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') <= /*condition.srhScheduledShippingDateTo*/'2199/01/01'
	    /*END*/
	    --得意先
	    /*IF (condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
	    AND (REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_CD LIKE /*condition.srhVenderCd*/'%' 
	        OR REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%')
	        --2次店以下で検索
	        OR FNC_GET_UPPER_VENDER(REP_SLIP_ORDER_IMPORT_DETAIL.BALANCE_CD ,2) = REPLACE (/*condition.srhVenderCd*/'','%','')
	        OR FNC_GET_UPPER_VENDER(REP_SLIP_ORDER_IMPORT_DETAIL.BALANCE_CD ,3) = REPLACE (/*condition.srhVenderCd*/'','%','')
	        OR FNC_GET_UPPER_VENDER(REP_SLIP_ORDER_IMPORT_DETAIL.BALANCE_CD ,4) = REPLACE (/*condition.srhVenderCd*/'','%','')
	        OR FNC_GET_UPPER_VENDER(REP_SLIP_ORDER_IMPORT_DETAIL.BALANCE_CD ,5) = REPLACE (/*condition.srhVenderCd*/'','%','')
	        --客先得意先で検索
	        OR REP_SLIP_ORDER_IMPORT_DETAIL.CTM_VENDER_CD_01 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_VENDER_CD_02 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_VENDER_CD_03 LIKE /*condition.srhVenderCd*/'%' 
	        OR REP_SLIP_ORDER_IMPORT_DETAIL.CTM_VENDER_NAME_01 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_VENDER_NAME_02 || REP_SLIP_ORDER_IMPORT_DETAIL.CTM_VENDER_NAME_03 LIKE /*condition.srhVenderCd*/'%'
	    )
	    /*END*/
	    --担当部署
	    /*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
	    AND	(REP_SLIP_ORDER_IMPORT_DETAIL.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR REP_SLIP_ORDER_IMPORT_DETAIL.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
	    /*END*/
	    --仮単価FLG
	    /*IF (condition.srhTmpUnitpriceFlg != "9")*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.TMP_UNITPRICE_FLG = /*condition.srhTmpUnitpriceFlg*/0
	    /*END*/
	    --営業担当者
	    /*IF (condition.srhSalesTantoCd != null) && (condition.srhSalesTantoCd != "")*/
	    AND	(REP_SLIP_ORDER_IMPORT_DETAIL.SALES_TANTO_CD LIKE /*condition.srhSalesTantoCd*/'%' OR REP_SLIP_ORDER_IMPORT_DETAIL.SALES_TANTO_NAME LIKE /*condition.srhSalesTantoCd*/'%')
	    /*END*/
	    --入力担当者
	    /*IF (condition.srhInputTantoCd != null) && (condition.srhInputTantoCd != "")*/
	    AND	(REP_SLIP_ORDER_IMPORT_DETAIL.INPUT_TANTO_CD LIKE /*condition.srhInputTantoCd*/'%' OR REP_SLIP_ORDER_IMPORT_DETAIL.INPUT_TANTO_NAME LIKE /*condition.srhInputTantoCd*/'%')
	    /*END*/
	    --出荷BC
	    /*IF (condition.srhCarryBc != null) && (condition.srhCarryBc != "")*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.CARRY_BC LIKE /*condition.srhCarryBc*/'%'
	    /*END*/
	    --品目
	    /*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
	    AND	(REP_SLIP_ORDER_IMPORT_DETAIL.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR REP_SLIP_ORDER_IMPORT_DETAIL.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
	    /*END*/
	    --他社コード1
	    /*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
	    /*END*/
	    --希望納期From
	    /*IF (condition.srhSuggestedDeliverlimitFrom != null) && (condition.srhSuggestedDeliverlimitFrom != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.SUGGESTED_DELIVERLIMIT, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitFrom*/'1900/01/01'
	    /*END*/
	    --希望納期To
	    /*IF (condition.srhSuggestedDeliverlimitTo != null) && (condition.srhSuggestedDeliverlimitTo != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.SUGGESTED_DELIVERLIMIT, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitTo*/'2199/01/01'
	    /*END*/
	    --運送会社
	    /*IF (condition.srhCarryCd != null) && (condition.srhCarryCd != "")*/
	        /*IF ( condition.srhCarryCd != "0" )*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.CARRY_CD LIKE /*condition.srhCarryCd*/'%'
	        /*END*/
	    /*END*/
	    --エラー状態
	    /*IF ( condition.srhErrorStatus == "1" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.ERROR_FLG = '1'
	    /*END*/
	    /*IF ( condition.srhErrorStatus == "2" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.ERROR_FLG = '0'
	    /*END*/
	
	    --データ取込区分
	    /*IF ( condition.srhInputDivision == "1" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_IMP_NO IS NOT NULL  
	    /*END*/
	    /*IF ( condition.srhInputDivision == "2" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_IMP_NO IS NULL  
	    /*END*/
	
	    --納品予定日From
	    /*IF (condition.srhDeliveryExpectedDateFrom != null) && (condition.srhDeliveryExpectedDateFrom != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.DELIVERY_EXPECTED_DATE, 'YYYY/MM/DD') >= /*condition.srhDeliveryExpectedDateFrom*/'1900/01/01'
	    /*END*/
	    --納品予定日To
	    /*IF (condition.srhDeliveryExpectedDateTo != null) && (condition.srhDeliveryExpectedDateTo != "")*/
	    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_DETAIL.DELIVERY_EXPECTED_DATE, 'YYYY/MM/DD') <= /*condition.srhDeliveryExpectedDateTo*/'2199/01/01'
	    /*END*/
	    --取込番号From
	   	/*IF (condition.srhTempNoFrom != null) && (condition.srhTempNoFrom != "")*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_IMP_NO >= /*condition.srhTempNoFrom*/'OI000000001' 
	    /*END*/
	    --取込番号To
	    /*IF (condition.srhTempNoTo != null) && (condition.srhTempNoTo != "")*/
	    AND	REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_IMP_NO <= /*condition.srhTempNoTo*/'OI999999999' 
	    /*END*/
	    --入力チェック
	    /*IF ( condition.srhOrderInputCheck == "1" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.INPUT_APPROVAL_DATE IS NULL
	    /*END*/
	    /*IF ( condition.srhOrderInputCheck == "2" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.INPUT_APPROVAL_DATE IS NOT NULL
	    /*END*/
	    --納期連絡
	    /*IF ( condition.srhDelDateSend == "1" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.DEL_DATE_SEND_DATE IS NULL
	    /*END*/
	    /*IF ( condition.srhDelDateSend == "2" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.DEL_DATE_SEND_DATE IS NOT NULL
	    /*END*/
	    --削除・キャンセル
	    /*IF ( condition.srhDeleteCancel == "0" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.DEL_FLG = 0
	    /*END*/
	    /*IF ( condition.srhDeleteCancel == "1" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.DEL_FLG = 1
	    /*END*/
	    /*IF ( condition.srhDeleteCancel == "2" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.CANCEL_FLG = 1
	    /*END*/
	    /*IF ( condition.srhDeleteCancel == "3" )*/
	    AND ( REP_SLIP_ORDER_IMPORT_DETAIL.DEL_FLG = 1 OR REP_SLIP_ORDER_IMPORT_DETAIL.CANCEL_FLG = 1 )
	    /*END*/
	    /*IF ( condition.srhDeleteCancel == "4" )*/
	    AND ( REP_SLIP_ORDER_IMPORT_DETAIL.DEL_FLG <> 1 AND REP_SLIP_ORDER_IMPORT_DETAIL.CANCEL_FLG <> 1 )
	    /*END*/

	    )
SELECT
    REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_NO
,   REP_SLIP_ORDER_IMPORT_DETAIL.ROW_NO
,   REP_SLIP_ORDER_IMPORT_DETAIL.PK_NO
,   REP_SLIP_ORDER_IMPORT_DETAIL.PK_ROW
,   REP_SLIP_ORDER_IMPORT_DETAIL.SHIPPING_NO
,   REP_SLIP_ORDER_IMPORT_DETAIL.ITEM_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.ITEM_NAME
,   REP_SLIP_ORDER_IMPORT_DETAIL.STYLE_OF_PACKING
,   REP_SLIP_ORDER_IMPORT_DETAIL.OTHER_COMPANY_CD1
,   REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_QTY
,   REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_UNITPRICE
,   REP_SLIP_ORDER_IMPORT_DETAIL.STANDARD_UNITPRICE
,   REP_SLIP_ORDER_IMPORT_DETAIL.STANDARD_DISCOUNT
,   REP_SLIP_ORDER_IMPORT_DETAIL.SPECIAL_DISCOUNT
,   REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_AMOUNT
,   REP_SLIP_ORDER_IMPORT_DETAIL.TMP_UNITPRICE_FLG
,   REP_SLIP_ORDER_IMPORT_DETAIL.TMP_UNITPRICE_STRING
,   REP_SLIP_ORDER_IMPORT_DETAIL.MATSS
,   REP_SLIP_ORDER_IMPORT_DETAIL.ESTIMATE_STANDARD_AMOUNT
,   REP_SLIP_ORDER_IMPORT_DETAIL.ESTIMATE_MATSS
,   REP_SLIP_ORDER_IMPORT_DETAIL.INPUT_DATE
,   REP_SLIP_ORDER_IMPORT_DETAIL.INPUTOR_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.STATUS_NAME
,   REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_DIVISION
,   REP_SLIP_ORDER_IMPORT_DETAIL.INPUT_TANTO_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.INPUT_TANTO_NAME
,   REP_SLIP_ORDER_IMPORT_DETAIL.SALES_TANTO_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.SALES_TANTO_NAME
,   REP_SLIP_ORDER_IMPORT_DETAIL.DELIVERY_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.DELIVERY_NAME
,   REP_SLIP_ORDER_IMPORT_DETAIL.DELIVERY_ADDRESS_ALL
,   REP_SLIP_ORDER_IMPORT_DETAIL.DELIVERY_TEL_NO
,   REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_NAME
,   REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_CD2
,   REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_CD3
,   REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_CD4
,   REP_SLIP_ORDER_IMPORT_DETAIL.VENDER_CD5
,   REP_SLIP_ORDER_IMPORT_DETAIL.ORGANIZATION_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.ORGANIZATION_NAME
,   REP_SLIP_ORDER_IMPORT_DETAIL.SHIPPING_STATUS_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.PURCHASE_STATUS_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.SCHEDULED_SHIPPING_DATE
,   REP_SLIP_ORDER_IMPORT_DETAIL.ORDER_DATE
,   REP_SLIP_ORDER_IMPORT_DETAIL.INPUTOR_NAME
,   REP_SLIP_ORDER_IMPORT_DETAIL.UPDATE_DATE
,   REP_SLIP_ORDER_IMPORT_DETAIL.UPDATOR_CD
,   REP_SLIP_ORDER_IMPORT_DETAIL.UPDATOR_NAME
,   REP_SLIP_ORDER_IMPORT_DETAIL.CARRY_CD
FROM REP_SLIP_ORDER_IMPORT_DETAIL
WHERE
	REP_SLIP_ORDER_IMPORT_DETAIL.PK_NO IN ( SELECT TMP.PK_NO FROM TMP )
-- 受注番号と行番号でソート
	    --削除・キャンセル
	    /*IF ( condition.srhDeleteCancel == "0" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.DEL_FLG = 0
	    /*END*/
	    /*IF ( condition.srhDeleteCancel == "1" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.DEL_FLG = 1
	    /*END*/
	    /*IF ( condition.srhDeleteCancel == "2" )*/
	    AND REP_SLIP_ORDER_IMPORT_DETAIL.CANCEL_FLG = 1
	    /*END*/
	    /*IF ( condition.srhDeleteCancel == "3" )*/
	    AND ( REP_SLIP_ORDER_IMPORT_DETAIL.DEL_FLG = 1 OR REP_SLIP_ORDER_IMPORT_DETAIL.CANCEL_FLG = 1 )
	    /*END*/
	    /*IF ( condition.srhDeleteCancel == "4" )*/
	    AND ( REP_SLIP_ORDER_IMPORT_DETAIL.DEL_FLG <> 1 AND REP_SLIP_ORDER_IMPORT_DETAIL.CANCEL_FLG <> 1 )
	    /*END*/
ORDER BY 
 	REP_SLIP_ORDER_IMPORT_DETAIL.PK_NO
,   REP_SLIP_ORDER_IMPORT_DETAIL.PK_ROW ASC