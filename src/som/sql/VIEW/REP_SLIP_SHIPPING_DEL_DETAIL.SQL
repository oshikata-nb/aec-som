CREATE OR REPLACE VIEW REP_SLIP_SHIPPING_DEL_DETAIL
    (KEY,SHIPPING_NO,ITEM_CD,JAN_CD,ITEM_NAME,STYLE_OF_PACKING,MATSS,SALES_QUANTITY,
    MATSS_INS,QTY_INS,REMARK,ALL_UP_WEIGHT,SHIPPING_INSTRUCT_DATE,SCHEDULED_SHIPPING_DATE,
    TANTO_CD,ORDER_NO,ORDER_ROW_NO,VENDER_DIVISION,VENDER_CD,DELIVERY_CD,
    SHIPPING_STATUS,SUMMERY,DELIVERY_COMP,SHIPPING_RESULT_DATE,SHIPPING_SLIP_NO,
    SHIPPING_SLIP_ROW_NO,SLIP_PUBLISH_COMP,SLIP_PUBLISH_DATE,SLIP_SHIPPING_ORDER_COMP,
    SLIP_SHIPPING_ORDER_DATE,SLIP_SHIPPING_SCHEDULE_COMP,SLIP_SHIPPING_SCHEDULE_DATE,
    SLIP_SHIPPING_TAG_COMP,SLIP_SHIPPING_TAG_DATE,SLIP_SHIPPING_REQUEST_COMP,
    SLIP_SHIPPING_REQUEST_DATE,SLIP_SHIPPING_FARE_COMP,SLIP_SHIPPING_FARE_DATE,
    SLIP_SHIPPING_DELIVELY_COMP,SLIP_SHIPPING_DELIVELY_DATE,SLIP_SHIPPING_NEW_TAG_COMP,
    SLIP_SHIPPING_NEW_TAG_DATE,SLIP_SHIPPING_POSTAL_COMP,SLIP_SHIPPING_POSTAL_DATE,
    CARRY_CD,SUGGESTED_DELIVERLIMIT,CARRY_FARE,SENDING_OFF_NUMBER,INPUT_DATE,
    INPUTOR_CD,UPDATE_DATE,UPDATOR_CD,REPOTR_OUTPUT_NUM,ITEM_DIVISION)
AS
SELECT  SHIPPING.CARRY_CD || TO_CHAR(SHIPPING.SCHEDULED_SHIPPING_DATE,'YYYYMMDD') || SHIPPING_DETAIL_LOCATION.UPPER_LOCATION_CD || SHIPPING.ORDER_NO || ORDER_HEAD.CUSTOMER_ORDER_NO || SHIPPING.VENDER_CD || SHIPPING.DELIVERY_CD  || SHIPPING.CARRY_BC  AS KEY-- キー

        ,SHIPPING.SHIPPING_NO           -- 出荷番号
        ,SHIPPING.ITEM_CD               -- 商品コード
        ,ARTICLE_ATTRIBUTE_QUEUE.JAN_CD -- JANコード
        ,ITEM.ITEM_NAME                 -- 商品名
        ,ITEM.STYLE_OF_PACKING          -- 荷姿
         -- 増付け
  	    ,-- 運賃の場合は増付をつけない
		  CASE WHEN (SHIPPING.ITEM_CD = '39999999' OR SHIPPING.ITEM_CD = '09999999') 
				          THEN 
					            '0'
				          ELSE 
					            TO_CHAR(NVL(ORDER_DETAIL.MATSS,0))
		  END AS MATSS -- 添付数
	     -- 売上数量
  	    ,TO_CHAR(NVL(CASE WHEN SHIPPING.ORDER_NO IS NOT NULL THEN ORDER_DETAIL.ORDER_QTY ELSE SHIPPING_DETAIL_LOCATION.ORDER_QTY END,0))  AS SALES_QUANTITY -- 梱数
    	 -- 増付け入り数
  	    ,-- 運賃の場合は増付をつけない
		  CASE WHEN (SHIPPING.ITEM_CD = '39999999' OR SHIPPING.ITEM_CD = '09999999') 
				          THEN 
					            '0'
				          ELSE 
					            TO_CHAR(NVL(ORDER_DETAIL.MATSS * ITEM.NUMBER_OF_INSERTIONS,0))
		  END AS MATSS_INS -- 添付（ﾊﾞﾗ）
	     -- 入数
  	    ,TO_CHAR(NVL(CASE WHEN SHIPPING.ORDER_NO IS NOT NULL THEN ORDER_DETAIL.ORDER_QTY ELSE SHIPPING_DETAIL_LOCATION.ORDER_QTY END,0) * ITEM.NUMBER_OF_INSERTIONS) AS QTY_INS -- バラ数
        ,ORDER_HEAD.PRINT_SUMMERY AS REMARK -- 備考
        ,TO_CHAR(NVL(ITEM.ALL_UP_WEIGHT * (NVL(ORDER_DETAIL.MATSS,0) 
                        + (CASE WHEN SHIPPING.ORDER_NO IS NOT NULL THEN ORDER_DETAIL.ORDER_QTY ELSE SHIPPING_DETAIL_LOCATION.ORDER_QTY END)),0)) AS ALL_UP_WEIGHT -- kg
        
        ,TO_CHAR(SHIPPING.SHIPPING_INSTRUCT_DATE,'YYYY/MM/DD') AS SHIPPING_INSTRUCT_DATE
        ,TO_CHAR(SHIPPING.SCHEDULED_SHIPPING_DATE,'YYYY/MM/DD') AS SCHEDULED_SHIPPING_DATE
        ,SHIPPING.TANTO_CD
        ,SHIPPING.ORDER_NO
        ,TO_CHAR(SHIPPING.ORDER_ROW_NO)
        ,SHIPPING.VENDER_DIVISION
        ,SHIPPING.VENDER_CD
        ,SHIPPING.DELIVERY_CD
        ,SHIPPING.SHIPPING_STATUS
        ,SHIPPING.SUMMERY
        ,SHIPPING.DELIVERY_COMP
        ,TO_CHAR(SHIPPING.SHIPPING_RESULT_DATE,'YYYY/MM/DD') AS SHIPPING_RESULT_DATE
        ,SHIPPING.SHIPPING_SLIP_NO
        ,SHIPPING.SHIPPING_SLIP_ROW_NO
        ,SHIPPING.SLIP_PUBLISH_COMP
        ,TO_CHAR(SHIPPING.SLIP_PUBLISH_DATE,'YYYY/MM/DD') AS SLIP_PUBLISH_DATE
        ,SHIPPING.SLIP_SHIPPING_ORDER_COMP
        ,SHIPPING.SLIP_SHIPPING_ORDER_DATE
        ,SHIPPING.SLIP_SHIPPING_SCHEDULE_COMP
        ,TO_CHAR(SHIPPING.SLIP_SHIPPING_SCHEDULE_DATE,'YYYY/MM/DD') AS SLIP_SHIPPING_SCHEDULE_DATE
        ,SHIPPING.SLIP_SHIPPING_TAG_COMP
        ,TO_CHAR(SHIPPING.SLIP_SHIPPING_TAG_DATE,'YYYY/MM/DD') AS SLIP_SHIPPING_TAG_DATE
        ,SHIPPING.SLIP_SHIPPING_REQUEST_COMP
        ,TO_CHAR(SHIPPING.SLIP_SHIPPING_REQUEST_DATE,'YYYY/MM/DD') AS SLIP_SHIPPING_REQUEST_DATE
        ,SHIPPING.SLIP_SHIPPING_FARE_COMP
        ,TO_CHAR(SHIPPING.SLIP_SHIPPING_FARE_DATE,'YYYY/MM/DD') AS SLIP_SHIPPING_FARE_DATE
        ,SHIPPING.SLIP_SHIPPING_DELIVELY_COMP
        ,TO_CHAR(SHIPPING.SLIP_SHIPPING_DELIVELY_DATE,'YYYY/MM/DD') AS SLIP_SHIPPING_DELIVELY_DATE
        ,SHIPPING.SLIP_SHIPPING_NEW_TAG_COMP
        ,TO_CHAR(SHIPPING.SLIP_SHIPPING_NEW_TAG_DATE,'YYYY/MM/DD') AS SLIP_SHIPPING_NEW_TAG_DATE
        ,SHIPPING.SLIP_SHIPPING_POSTAL_COMP
        ,TO_CHAR(SHIPPING.SLIP_SHIPPING_POSTAL_DATE,'YYYY/MM/DD') AS SLIP_SHIPPING_POSTAL_DATE
        ,SHIPPING.CARRY_CD
        ,SHIPPING.SUGGESTED_DELIVERLIMIT
        ,NVL(SHIPPING.CARRY_FARE,0) AS CARRY_FARE
        ,SHIPPING.SENDING_OFF_NUMBER
        ,TO_CHAR(SHIPPING.INPUT_DATE,'YYYY/MM/DD') AS INPUT_DATE
        ,SHIPPING.INPUTOR_CD
        ,TO_CHAR(SHIPPING.UPDATE_DATE,'YYYY/MM/DD') AS UPDATE_DATE
        ,SHIPPING.UPDATOR_CD
        ,CARRY.REPOTR_OUTPUT_NUM AS REPOTR_OUTPUT_NUM
        ,ITEM.ITEM_DIVISION

FROM      SHIPPING

--LEFT JOIN	(SELECT SHIPPING_NO
--                    ,SUM(SHIPPING_INSTRUCTION) AS ORDER_QTY
--                    ,SUM(SHIPPING_RESULT_QUANTITY) AS RESULT_QTY 
--             FROM SHIPPING_DETAIL 
--             GROUP BY SHIPPING_NO) DETAIL
--ON         SHIPPING.SHIPPING_NO = DETAIL.SHIPPING_NO
     
LEFT JOIN ITEM
ON        SHIPPING.ITEM_CD = ITEM.ITEM_CD

LEFT JOIN ARTICLE_ATTRIBUTE_QUEUE
ON        SHIPPING.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD
AND       ITEM.VERSION     = ARTICLE_ATTRIBUTE_QUEUE.VERSION

LEFT JOIN ORDER_DETAIL
ON	      SHIPPING.ORDER_NO = ORDER_DETAIL.ORDER_NO
AND	      SHIPPING.ORDER_ROW_NO = ORDER_DETAIL.ROW_NO

LEFT JOIN ORDER_HEAD
ON        SHIPPING.ORDER_NO      = ORDER_HEAD.ORDER_NO

LEFT JOIN (SELECT
					SHIPPING_DETAIL.SHIPPING_NO
				,	MAX(UPPER_LOCATION.UPPER_LOCATION_CD) AS UPPER_LOCATION_CD
                ,   SUM(SHIPPING_DETAIL.SHIPPING_INSTRUCTION) AS ORDER_QTY
                ,   SUM(SHIPPING_DETAIL.SHIPPING_RESULT_QUANTITY) AS RESULT_QTY 
				FROM
					SHIPPING_DETAIL
				,	(SELECT
						LOCA1.LOCATION_CD
					,	NVL(LOCA2.UPPER_LOCATION_CD,LOCA2.LOCATION_CD) AS UPPER_LOCATION_CD
					FROM
						LOCATION LOCA1
					,	LOCATION LOCA2
					WHERE
						NVL(LOCA1.UPPER_LOCATION_CD,LOCA1.LOCATION_CD) = LOCA2.LOCATION_CD(+)
					AND	(LOCA1.LOCATION_LEVEL = '1' OR LOCA1.LOCATION_LEVEL = '4')
					AND	LOCA1.LOCATION_CD <> 'K'
					)UPPER_LOCATION
				
				WHERE
					SHIPPING_DETAIL.LOCATION_CD = UPPER_LOCATION.LOCATION_CD
				
				GROUP BY
					SHIPPING_DETAIL.SHIPPING_NO
				)SHIPPING_DETAIL_LOCATION
ON        SHIPPING_DETAIL_LOCATION.SHIPPING_NO = SHIPPING.SHIPPING_NO

LEFT JOIN CARRY
ON        SHIPPING.CARRY_CD = CARRY.CARRY_CD 

 
 
/
COMMENT ON TABLE REP_SLIP_SHIPPING_DEL_DETAIL IS 'REP_納品伝票明細'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SHIPPING_NO IS '出荷番号'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.ITEM_CD IS '品目コード'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.JAN_CD IS 'JANコード'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.ITEM_NAME IS '品目名称'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.STYLE_OF_PACKING IS '荷姿'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.MATSS IS '増付'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SALES_QUANTITY IS '売上数量'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.MATSS_INS IS '増付、納品数量（バラ）'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.QTY_INS IS '納品数量（バラ）'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.REMARK IS '備考'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.ALL_UP_WEIGHT IS '総重量'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SHIPPING_INSTRUCT_DATE IS '出荷指図日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SCHEDULED_SHIPPING_DATE IS '出荷予定日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.TANTO_CD IS '出荷担当者コード'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.ORDER_NO IS '受注番号'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.ORDER_ROW_NO IS '行番号(受注)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.VENDER_DIVISION IS '取引先区分(未使用)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.VENDER_CD IS '取引先コード'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.DELIVERY_CD IS '納入先コード'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SHIPPING_STATUS IS '出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SUMMERY IS '摘要(未使用)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.DELIVERY_COMP IS '完納区分'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SHIPPING_RESULT_DATE IS '出荷完了日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SHIPPING_SLIP_NO IS '出荷伝票番号'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SHIPPING_SLIP_ROW_NO IS '出荷伝票行番号'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_PUBLISH_COMP IS '出荷伝票発行済区分(0:未発行 1:発行済)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_PUBLISH_DATE IS '伝票発行日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_ORDER_COMP IS '出荷指図書発行済区分(0:未発行 1:発行済)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_ORDER_DATE IS '出荷指図書発行日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_SCHEDULE_COMP IS '出荷予定表発行済区分(0:未発行 1:発行済)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_SCHEDULE_DATE IS '出荷予定表発行日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_TAG_COMP IS '荷札,ペリカン発行済区分(0:未発行 1:発行済)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_TAG_DATE IS '荷札,ペリカン発行日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_REQUEST_COMP IS '出荷依頼書発行済区分(0:未発行 1:発行済)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_REQUEST_DATE IS '出荷依頼書発行日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_FARE_COMP IS '運賃表発行済区分(0:未発行 1:発行済)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_FARE_DATE IS '運賃表発行日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_DELIVELY_COMP IS '納品伝票発行済区分(0:未発行 1:発行済)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_DELIVELY_DATE IS '納品伝票発行日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_NEW_TAG_COMP IS '新荷札発行済区分(0:未発行 1:発行済)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_NEW_TAG_DATE IS '新荷札発行日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_POSTAL_COMP IS '新郵政発行済区分(0:未発行 1:発行済)'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SLIP_SHIPPING_POSTAL_DATE IS '新郵政発行日'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.CARRY_CD IS '運送会社コード'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SUGGESTED_DELIVERLIMIT IS '希望納期'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.CARRY_FARE IS '運賃'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.SENDING_OFF_NUMBER IS '積出ナンバー'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.INPUT_DATE IS '登録日時'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.INPUTOR_CD IS '登録者ID'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.UPDATE_DATE IS '更新日時'
/
COMMENT ON COLUMN REP_SLIP_SHIPPING_DEL_DETAIL.UPDATOR_CD IS '更新者ID'
/
