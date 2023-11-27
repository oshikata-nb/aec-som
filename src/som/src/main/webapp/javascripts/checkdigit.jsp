<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
	数値桁数チェックマスタを用いて指定小数点桁数で端数区分による丸め処理を行う。
--%>

//端数区分値-なし
var ROUND_NOT = 0;
//端数区分値-切り捨て
var ROUND_DOWN = 1;
//端数区分値-四捨五入
var ROUND_HALF_UP = 2;
//端数区分値-切り上げ
var ROUND_UP = 3;

<%--
 * 小数点桁数、端数区分により数値を丸めた数値文字列(カンマ編集)を返す。
 * 対象数値文字列が数値でない場合は、""（空白）を返す。
 * @param decimalPoint 小数点位置
 * @param round 端数区分
 * @param value 対象数値文字列(カンマ編集可)
 * @return 丸めた数値文字列
--%>
function digitStringFormat(decimalPoint,round,value){
	var buf = parseFloat(value.replace(/,/g, ""));
	if( !isNaN(buf) ){
		return digitFormat(decimalPoint,round,buf);
	}else{
		return "";
	}
}
<%--
 * 小数点桁数、端数区分により数値を丸めた数値文字列(カンマ編集)を返す。
 * @param decimalPoint 小数点位置
 * @param round 端数区分
 * @param value 対象数値
 * @return 丸めた数値文字列
--%>
function digitFormat(decimalPoint,round,value){
	var num = digitRound2(decimalPoint,round,value);
	var fmt = formatNumber(String(num));
	var pos = fmt.lastIndexOf(".");
	if(pos >= 0){
		var len = fmt.length;
		var decNum = fmt.substring(pos+1,len).length;
		if(decNum < decimalPoint){
			for(var i = 0 ; i < (decimalPoint - decNum) ; i++){
				fmt = fmt + "0";
			}
		}
	}else{
		if(decimalPoint > 0){
			fmt = fmt + "."
			for(var i = 0 ; i < decimalPoint ; i++){
				fmt = fmt + "0";
			}
		}
	}
	return fmt;
}
<%--
 * 小数点桁数、端数区分により数値を丸めた数値を返す　8.20->8.19になる
 * @param decimalPoint 小数点位置 2
 * @param round 端数区分          1
 * @param value 対象数値          8.2
 * @return 丸めた数値             8.19
--%>
function digitRound(decimalPoint,round,value){
	var res = value;
	if(value != null){
		point = parseInt(decimalPoint);
		var weight = parseFloat("1");
		if(point > 0){
			weight = Math.pow(10,point);
		}
		var buf = value * weight;
		switch(parseInt(round)){
			case ROUND_DOWN:	//切り捨て
				buf = Math.floor(buf);
				break;
			case ROUND_HALF_UP:	//四捨五入
				buf = Math.round(buf);
				break;
			case ROUND_UP:		//切り上げ
				buf = Math.ceil(buf);
				break;
			default:
				buf = Math.floor(buf);	//切り捨て
				break;
		}
		res = buf / weight;
	}
	return res;
}
<%--
 * 小数点桁数、端数区分により数値を丸めた数値を返す不具合解消版
 * @param decimalPoint 小数点位置 2
 * @param round 端数区分          1
 * @param value 対象数値          8.2
 * @return 丸めた数値             8.2
--%>
function digitRound2(decimalPoint,round,value){
	var res = value + "";
	if(value != null){
		if((res.split(".")[1]) == null){
			res = value +".0000000000000";
		}else{
			res = value +"0000000000000";
		}
		point = parseInt(decimalPoint);
		var weight = parseFloat("1");
		if(point > 0){
			res = res.split(".")[0]+(res.split(".")[1]).substring(0,point) + "." + (res.split(".")[1]).substring(point+1,point+2);
			weight = Math.pow(10,point);
		}
		var buf = parseFloat(res);
		switch(parseInt(round)){
			case ROUND_DOWN:	//切り捨て
				buf = Math.floor(buf);
				break;
			case ROUND_HALF_UP:	//四捨五入
				buf = Math.round(buf);
				break;
			case ROUND_UP:		//切り上げ
				buf = Math.ceil(buf);
				break;
			default:
				buf = Math.floor(buf);	//切り捨て
				break;
		}
		res = buf / weight;
	}
	return res;
}

<%--
 * 小数点桁数、端数区分により数値を丸めた数値文字列(カンマ編集)を返す。
 * @param decimalPoint 小数点位置のID属性名
 * @param round 端数区分のID属性名
 * @param value 対象数値のID属性名
 * @return 丸めた数値
--%>
function digitFormatId(decimalPoint,round,value){
	return digitFormat($F(decimalPoint),$F(round),$F(value));
}

<%--
 *  空白チェック（null,全てスペースも空白と判断する)
--%>
function blank(s){
	var reg = new RegExp(/^\s*$/);
	return reg.test(s);
}

<%--
 * 数値桁数チェックマスタの設定により数値を丸めた後、文字列にフォーマットする。
 * @param decimalPoint 小数点位置
 * @param round 端数区分
 * @param value 対象数値
 * @param method 非同期処理終了後呼ばれるメソッド
--%>
function digitRequest(unitDivision,venderDivision,venderCd,value,method){
	var param = "op=format" + "&unitDivision=" + unitDivision +
				"&venderDivision=" + venderDivision +
				"&venderCd=" + venderCd +
				"&value=" + value;
	var url = "<%= request.getContextPath() %>/CheckDigitAction.do";
	var result = new Ajax.Request(
			url,
			{
				method: 'post',
				parameters: param,
				onComplete: method
			});
}
<%--
 * 数値桁数チェックマスタの設定により数値を丸めた後、文字列にフォーマットする。
 * @param decimalPoint 小数点位置のID属性名
 * @param round 端数区分のID属性名
 * @param value 対象数値のID属性名
 * @param method 非同期処理終了後呼ばれるメソッド
--%>
function digitRequestId(unitDivision,venderDivision,venderCd,value,method){
	digitRequest($F(decimalPoint), $F(round), $F(value), method);
}
<%--
 * JSON文字列をオブジェクトに変換
 * @param jsonString JSON文字列
--%>
function evalJSON(jsonString){
	return eval('(' + jsonString + ')');
}
<%-- <<<<< オートコンプリート用 >>>>> --%>
<%--
 * 選択されたオートコンプリートから、指定されたスタイルシート名のhiddenの値を取得する
 * @param li 選択された<li></li>ブロック
 * @param cssName スタイルシートのclass名
--%>
function getHiddenValue(li,cssName){
	var hiddens = li.getElementsByTagName("input");
	var len = hiddens.length;
	for(var i = 0 ; i < len ; i++){
		if(hiddens[i].className == cssName){
			var res = hiddens[i].value;
			break;
		}
	}
	return res;
}

function multiFloat(a,b,point){
	var t = a.replace(/,/g,"");
	var tk = parseFloat(t.replace(/\./g,""));
	var weight = parseFloat("1");
	if(point > 0){
		weight = Math.pow(10,point);
	}
	var sum = b * tk / weight;
	return sum;
	
}

function addFloat(a,b,point){
	var ta = a.replace(/,/g,"");
	var tka = parseFloat(ta.replace(/\./g,""));
	var tb = b.replace(/,/g,"");
	var tkb = parseFloat(tb.replace(/\./g,""));
	var weight = parseFloat("1");
	if(point > 0){
		weight = Math.pow(10,point);
	}
	var sum = (tka + tkb) / weight;
	return sum;
	
}

