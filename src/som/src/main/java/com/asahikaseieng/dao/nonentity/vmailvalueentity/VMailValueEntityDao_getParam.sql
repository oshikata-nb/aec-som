/*
 * メールパラメータ取得用SQL 
 *
 * entityName=VMailValueEntity
 * packageName=vmailvalueentity
 * methodName=getParam
 *
 */
SELECT 
	VALS
FROM (
SELECT
    TO_NCHAR("0")       AS  I_1
,   TO_NCHAR("1")       AS  I_2
,   TO_NCHAR("2")       AS  I_3
,   TO_NCHAR("3")       AS  I_4
,   TO_NCHAR("4")       AS  I_5
,   TO_NCHAR("5")       AS  I_6
,   TO_NCHAR("6")       AS  I_7
,   TO_NCHAR("7")       AS  I_8
,   TO_NCHAR("8")       AS  I_9
,   TO_NCHAR("9")       AS  I_10
,   TO_NCHAR("10")      AS  I_11
,   TO_NCHAR("11")      AS  I_12
,   TO_NCHAR("12")      AS  I_13
,   TO_NCHAR("13")      AS  I_14
,   TO_NCHAR("14")      AS  I_15
,   TO_NCHAR("15")      AS  I_16
,   TO_NCHAR("16")      AS  I_17
,   TO_NCHAR("17")      AS  I_18
,   TO_NCHAR("18")      AS  I_19
,   TO_NCHAR("19")      AS  I_20
,   TO_NCHAR("20")      AS  I_21
,   TO_NCHAR("21")      AS  I_22
,   TO_NCHAR("22")      AS  I_23
,   TO_NCHAR("23")      AS  I_24
,   TO_NCHAR("24")      AS  I_25
,   TO_NCHAR("25")      AS  I_26
,   TO_NCHAR("26")      AS  I_27
,   TO_NCHAR("27")      AS  I_28
,   TO_NCHAR("28")      AS  I_29
,   TO_NCHAR("29")      AS  I_30
,   TO_NCHAR("30")      AS  I_31
,   TO_NCHAR("31")      AS  I_32
,   TO_NCHAR("32")      AS  I_33
,   TO_NCHAR("33")      AS  I_34
,   TO_NCHAR("34")      AS  I_35
,   TO_NCHAR("35")      AS  I_36
,   TO_NCHAR("36")      AS  I_37
,   TO_NCHAR("37")      AS  I_38
,   TO_NCHAR("38")      AS  I_39
,   TO_NCHAR("39")      AS  I_40
,   TO_NCHAR("40")      AS  I_41
,   TO_NCHAR("41")      AS  I_42
,   TO_NCHAR("42")      AS  I_43
,   TO_NCHAR("43")      AS  I_44
,   TO_NCHAR("44")      AS  I_45
,   TO_NCHAR("45")      AS  I_46
,   TO_NCHAR("46")      AS  I_47
,   TO_NCHAR("47")      AS  I_48
,   TO_NCHAR("48")      AS  I_49
,   TO_NCHAR("49")      AS  I_50
,   TO_NCHAR("50")      AS  I_51
,   TO_NCHAR("51")      AS  I_52
,   TO_NCHAR("52")      AS  I_53
,   TO_NCHAR("53")      AS  I_54
,   TO_NCHAR("54")      AS  I_55
,   TO_NCHAR("55")      AS  I_56
,   TO_NCHAR("56")      AS  I_57
,   TO_NCHAR("57")      AS  I_58
,   TO_NCHAR("58")      AS  I_59
,   TO_NCHAR("59")      AS  I_60
,   TO_NCHAR("60")      AS  I_61
,   TO_NCHAR("61")      AS  I_62
,   TO_NCHAR("62")      AS  I_63
,   TO_NCHAR("63")      AS  I_64
,   TO_NCHAR("64")      AS  I_65
,   TO_NCHAR("65")      AS  I_66
,   TO_NCHAR("66")      AS  I_67
,   TO_NCHAR("67")      AS  I_68
,   TO_NCHAR("68")      AS  I_69
,   TO_NCHAR("69")      AS  I_70
,   TO_NCHAR("70")      AS  I_71
--メールフォーマットID:O_10001
/*IF (mailFormatId == "O_10001") */
FROM V_MAIL_VALUE_O_10001 VMV
WHERE
    VMV.PK_NO IS NOT NULL
AND VMV.PK_NO = /*item1*/'PK000000001'
AND VMV.PK_ROW = /*item2*/1
/*END*/
--メールフォーマットID:O_20001
/*IF (mailFormatId == "O_20001") */
FROM V_MAIL_VALUE_O_20001 VMV
WHERE
    VMV.PK_NO IS NOT NULL
AND VMV.PK_NO = /*item1*/'PK000000001'
AND VMV.PK_ROW = /*item2*/1
/*END*/
--メールフォーマットID:S_10001
/*IF (mailFormatId == "S_10001") */
FROM V_MAIL_VALUE_S_10001 VMV
WHERE
    VMV.SALES_SLIP_NO IS NOT NULL
AND VMV.SALES_SLIP_NO = /*item1*/'000000001'
/*END*/
--メールフォーマットID:S_20001
/*IF (mailFormatId == "S_20001") */
FROM V_MAIL_VALUE_S_20001 VMV
WHERE
    VMV.SALES_SLIP_NO IS NOT NULL
AND VMV.SALES_SLIP_NO = /*item1*/'000000001'
/*END*/

--メールフォーマットID:O_30001 注文ファイル返信
/*IF (mailFormatId == "O_30001") */
FROM V_MAIL_VALUE_O_30001 VMV
WHERE
    VMV.PK_NO IS NOT NULL
AND VMV.PK_NO = /*item1*/'PK000000001'
AND VMV.PK_ROW = /*item2*/1
/*END*/

)
UNPIVOT INCLUDE NULLS (
    VALS FOR COLS IN (I_1,I_2,I_3,I_4,I_5,I_6,I_7,I_8,I_9,I_10,I_11,I_12,I_13,I_14,I_15,I_16,I_17,I_18,I_19,I_20,I_21,I_22,I_23,I_24,I_25,I_26,I_27,I_28,I_29,I_30,I_31,I_32,I_33,I_34,I_35,I_36,I_37,I_38,I_39,I_40,I_41,I_42,I_43,I_44,I_45,I_46,I_47,I_48,I_49,I_50,I_51,I_52,I_53,I_54,I_55,I_56,I_57,I_58,I_59,I_60,I_61,I_62,I_63,I_64,I_65,I_66,I_67,I_68,I_69,I_70,I_71 )
)