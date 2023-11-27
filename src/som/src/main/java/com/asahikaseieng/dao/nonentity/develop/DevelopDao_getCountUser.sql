/*
 * 担当チェックSQL文��
 *
 * entityName=DevelopDto
 * packageName=develop
 * methodName=getCountUser
 */
 SELECT COUNT(TANTO_CD) CT
 FROM	LOGIN
 WHERE	TANTO_CD = /*tantoCd*/'kaihatsu'