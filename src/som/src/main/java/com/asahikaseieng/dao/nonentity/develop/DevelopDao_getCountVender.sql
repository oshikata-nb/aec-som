/*
 * 依頼元チェックSQL文��
 *
 * entityName=DevelopDto
 * packageName=develop
 * methodName=getCountVender
 */
 SELECT COUNT(VENDER_CD) CT
 FROM	VENDER
 WHERE	VENDER_CD = /*venderCd*/'Txxxx99'