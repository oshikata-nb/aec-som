//二度押し対策用変数
var g_lock = false;

//右クリック対応(押下時にメニューを表示しない場合は下記のコメント
//をはずす事)
//window.document.oncontextmenu=function() { return false; }

//キー押下時
window.document.onkeydown=onKeyDown;

/**
 * BackSpaceキー押下防止
 * (入力項目にはname・idを必ずつける)
 * http://chaichan.web.infoseek.co.jp/qa3500/qa3642.htm
 */
function onKeyDown(e) {

    if (navigator.appName == "Microsoft Internet Explorer") {

        //ALT＋←ダメ
        if( event.keyCode == 0x25 && event.altKey == true ) {
            return false ;
        }
        //テキストボックス、パスワードボックスは許す
/**        for (i = 0; i < document.all.tags("INPUT").length; i++) {
			if (window.event.srcElement.name != null && window.event.srcElement.name != "") {
            if (document.all.tags("INPUT")(i).name == window.event.srcElement.name &&
                (document.all.tags("INPUT")(i).type == "text" || document.all.tags("INPUT")(i).type == "password") &&
                 document.all.tags("INPUT")(i).readOnly == false){
                return true;
            }
            }
        }
        //テキストエリアは許す
        for (i = 0; i < document.all.tags("TEXTAREA").length; i++) {
			if (window.event.srcElement.name != null && window.event.srcElement.name != "") {
            if (document.all.tags("TEXTAREA")(i).name == window.event.srcElement.name &&
                document.all.tags("TEXTAREA")(i).readOnly == false){
                return true;
            }
            }
        }
*/
		// 入力高速化
		//テキストボックス、パスワードボックスは許す
		if (event.srcElement.tagName =="INPUT") {
			return true;
		}

		//テキストエリアは許す
		if (event.srcElement.tagName =="TEXTAREA") {
			return true;
		}

        //BackSpaceダメ
        if( event.keyCode == 8 ) {
            return false ;
        }
    } else {

//　      if (navigator.appName == "Netscape") {
//　          if (e.which == 8) {
//　　            return false;
//			}
//　　    }

    }
}

// 検索ポップアップの受け取り用関数(各画面でオーバーライドする)
function setValues() {
	alert("ポップアップからの値を受け取るには業務側で\r\n"
	    + "setValues()関数を実装してください。");
}

/**
 * form変数を設定して、submitする
 * @param name	parameter
 * @param value	parameterにセットする値
 */
function form_submit(name, value) {

	var len = arguments.length;
	if (len < 2 || 0 < (len % 2)) {
		alert("The number of arguments is inaccurate. Please call an argument by the number piece of times of 2.");
		return false;
	}

    if (!g_lock) {
		for (var i = 0; i < len; i += 2) {
			eval("$('mainForm')." + arguments[i]).value = arguments[i + 1];
		}

		$('mainForm').submit();
		g_lock = true;
	}

	if ("_blank" == $('mainForm').target) {
		g_lock = false;
	}

	return false;
}

/**
 * 編集チェックをしてからsubmitするメソッド
 * @param name1  parameter
 * @param value1 parameterにセットする値
 */
function check_form_submit(name, value) {

	if(!g_lock) {

		/* 編集チェック */
		if (!continueConfirm()) {
			return false;
		}

		/* submit */
		return form_submit.apply(null, arguments);

	}
	return false;
}

/**
 * locationする(ページ遷移専用)
 * @param pageNum ページ番号
 **/
function change_page(pageNum) {
  if (!g_lock) {
    if (continueConfirm()) {
    	/* モーダルダイアログでhrefが動かない為、ローカルFORMを生成してsubmitする */
		var f = document.createElement('form');
		document.getElementsByTagName('body')[0].appendChild(f);
		f.method = 'POST';
		f.action = document.forms[0].action + "?op=changePage&page=" + pageNum;
		f.submit();
	    g_lock = true;
	}
  }
}

/**
 * locationする(変更チェックなしページ遷移専用)
 * @param pageNum ページ番号
 **/
function noncheck_change_page(pageNum) {
   	/* モーダルダイアログでhrefが動かない為、ローカルFORMを生成してsubmitする */
	var f = document.createElement('form');
	document.getElementsByTagName('body')[0].appendChild(f);
	f.method = 'POST';
	f.action = document.forms[0].action + "?op=changePage&page=" + pageNum;
	f.submit();
    g_lock = true;
}

/**
 * e.innerTextに値を取得する
 * firefoxはinnerTextを利用できないのでe.textContentを利用する
 */
function getInnerText(e) {
        if (navigator.userAgent.indexOf("Firefox") > -1) {
                return $(e).textContent;
        }else{
                return $(e).innerText;
        }
}

/**
 * e.innerTextに値を設定する。
 * firefoxはinnerTextを利用できないのでe.textContentを利用する。
 */
function setInnerText(e, value) {
        if (navigator.userAgent.indexOf("Firefox") > -1) {
                $(e).textContent = value;
        }else{
                $(e).innerText = value;
        }
}

/**
 * ラベルをリフレッシュする
 * labelのidが、hiddenのid + 'lbl'
 * (例)lblFoo <--> foo
 * ならばこのデフォルトの実装が機能する
 */
function refreshLabel() {
	$A(Form.getInputs('mainForm', 'hidden')).each(function(e) {
		// fooBar -> lblFooBar
		var i = 'lbl' + e.id.substring(0, 1).toUpperCase() + e.id.substring(1);
		if ($(i) != null) {
			setInnerText($(i), e.value);
		}
	});
}

/**
 * 上記関数のhiddenをthisとしてkeypress時にクリアする為のeventhandler用
 */
function clearLabel(event) {
  // いたちごっごになるので(controls.jsと同じ方針に..)
  if(event.keyCode==Event.KEY_TAB || event.keyCode==Event.KEY_RETURN ||
   (navigator.appVersion.indexOf('AppleWebKit') > 0 && event.keyCode == 0)) return;

  this.value = '';
  $('lbl' + (this.id).capitalize()).innerText = '';
}

/**
 * メイン画面ポップアップ
 */
function open_main_popup() {

	var len 	= arguments.length;
	var width 	= arguments[0];
	var height 	= arguments[1];
	var winName = arguments[2];
	var winUrl 	= arguments[3];
	/* 引数を移す */
	var param = $A(new Array());
	var pos = 4;
	for (var i = pos; i < len;) {
		param[i - pos] = arguments[i];
		i = i + 1;
	}

	/* 右・下の余白(要調整？)*/
	var WIDTH_MARGIN = 10
	var HEIGHT_MARGIN = 50

	/* オプション値の設定 */
	var strOption = "left=0, top=0"
				  + ", width=" + (screen.availWidth - WIDTH_MARGIN)
				  + ", height=" + (screen.availHeight - HEIGHT_MARGIN)
				  + ", toolbar=no, location= no,"
				  + "status=yes, menubar=no, scrollbars=yes, resizable=yes";

	var args = $A(new Array());

	args.push(winName);
	args.push(strOption);
	args.push(winUrl);

	return _open_popup_impl.apply(null, args.concat($A(param)));
}

/**
 * ポップアップをオープン
 */
function _open_popup_impl() {

	/* 引数チェック */
	/* [0]ウィンドウ名 */
	/* [1]ウィンドウプロパティ */
	/* [2]遷移先URL */
	/* [3](遷移先URL以降の)パラメータ */
	var len = arguments.length;
	if (4 < (len)) {
		if (0 < (len % 2)) {
			/* 引数の数が不正です。 */
			alert("The number of arguments is inaccurate. ");
			return false;
		}
	}

	/* 配列から引数文字列を作成する */
	var param = "";
	for (var i = 4; i < len; i++) {
		if (0 == (i % 2)) {
			param = param + "&" + arguments[i];
		} else {
			param = param + "=" + encodeURI(arguments[i]);
		}
	}
	param = param + "&dummy=" + encodeURI("" + new Date());

	return window.open(arguments[2], arguments[0], arguments[1]);
}

/**
 * FORMの状態を初期化する.
 */
function initializeFormState() {

  if (document.forms) {

    /* 先頭の入力フィールドにフォーカスを当てる */
    if (document.forms.length > 0) {

      /**
       * iprototype.jsのfocusFirstElementでは、
       * input/select等を別々に集めてからフォーカスを当てる為
       * 正確に画面の最初の項目にならない。
       * したがって
       * 1. フォーカスを当てるべき物の存在チェック
       * 2. 存在すれば深さ優先でnode treeをトラバース
       * 3. 最初に見つかった物に、focus()している。
       */
      var es = Form.getElements(document.forms[0]).select(function(e) { return e.type != 'hidden' && !e.disabled});
      if (es.length > 0) {

          /* トラバース用関数 */
	      function _func(n) {

	        for (var i = 0; i < n.childNodes.length; i++) {
	          var e = _func(n.childNodes.item(i));
	          if (e != null) {
	            return e;
	          }
	        }
			/* 　select にフォーカスを当てるとマウススクロールがややこしいので外した 20090129 ETO */
	        if (n.type != 'hidden' && !n.disabled && n.tagName &&
	              ['input',  'textarea'].include(n.tagName.toLowerCase())) {
	          return n;
	        }

	        return null;
	      }

	      /* 必ずnullでは無い物が返ってくるはずなので、null checkしません */
	      /* nullが返ってくるのでチェックします。20090129 ETO */
	      
	      var ob = _func(document.forms[0]);
	      if ( ob != null) {
	      	ob.focus();
	      }
      }
    }
  }
}

/* プルダウンと入力フィールド(値を同期させる関連フィールド)の表示用 */
function getPullDownClickSize() {
	s = 18;
	if (isIeVersionSeven()) {
		/* IE7はサイズが違う */
		s = 24;
	}
	return s;
}

/* ブラウザバージョンチェック(IE7) */
function isIeVersionSeven() {
	var version_ = navigator.appVersion; // include "MSIE 6.0" or "MSIE 7.0"
	var start = version_.indexOf("MSIE", 0);
	var end = version_.indexOf(";", start);
	start = start + 5; // "MSIE" + space
	if (start < end) {
		var n_varsion = version_.substring(start, end);
		if (isNaN(n_varsion) == false) {
			n_varsion = n_varsion - 0;
			if (n_varsion > 6) {
				return true;
			}
		}
	}
	return false;
}


// 標準の除外ID(for initValue)
var _IGNORE_ID = new Array(1);
_IGNORE_ID[0] = "op";

/**
 * initValue設定
 * outerArguments 呼出元からの無形パラメータ
 * (このメソッドは画面の全項目を対象とするが、
 * パラメータで対象外プロパティを指定出来る)
 */
function storeInitValues() {

	var outerArguments = $A(arguments);

	// 文字列の場合は正規表現に変換
	outerArguments.each( function(e, index) {
		if (!(e instanceof RegExp)) {
			outerArguments[index] = new RegExp("^" + e + "$");
		}
	});

	$A(Form.getElements('mainForm')).each(function(e) {

		if (typeof e.id != 'undefined') {

			// 標準の除外IDかどうか判定
			var len = _IGNORE_ID.length;
			for (var i = 0; i < len; i++) {
				// hiddenの場合、idが取れないのでnameも見る(現状opのみ)
				if (e.id == _IGNORE_ID[i]) {
					return;
				}
			}

			// 呼び出し元から指定された対象外プロパティ
			var lenOuter = outerArguments.length;
			for (var i = 0; i < lenOuter; i++) {
				// 正規表現で渡される
				if ((e.id).match(outerArguments[i])) {
					return;
				}
			}

   			e.initValue = $F(e);
//			switch (e.type) {
//				case "checkbox":
//					if(!e.disabled){
//		    			e.initValue = e.checked;
//		    		}
//		    		
//					break;
//				default:
//	    			e.initValue = $F(e);
//					break;
//			}
	    }
	});
}

/**
 * initValue設定済みプロパティの値変更チェック
 *（storeInitValuesと対）
 */
function isEdited() {

	// initValueが存在して、変わっている物が一つでもあればtrue
	// (↓の!で判断を反転させているので注意！)
	return !$A(Form.getElements('mainForm')).all(function(e) {

		if ((typeof e.initValue != 'undefined')) {

			var editValue =  $F(e);
//			switch (e.type) {
//				case "checkbox":
//	    			editValue = e.checked;
//					break;
//				default:
//					break;
//			}
			// (空白とnull)
			if (isEmpty(e.initValue) && isEmpty($F(e))) {
				return true;
			}
			if (e.initValue != $F(e)) {
				return false;
			}
		}

		return true;
	});
}

/* 値が空白またはnullかどうかを戻す */
function isEmpty(val) {
	if (val == null || val == "") {
		return true;
	}
	return false;
}

/* Divをエフェクト表示する */
function createEffect(id) {
	return new Effect.Opacity(id,
			{ duration: 10.0,
				transition: Effect.Transitions.sinoidal,
				from: 1.0, to: 1.0
			});
}

/**
 * 日付チェック関数.
 * @param  org チェックする文字列日付
 * @return true:正しい日付
 */
function checkDate(org) {
	if (org == null) {
		return false;
	}

	if(!org.match(/^(-?)[0-9]{6,8}$/)){
		return false;
	}

	var tmp = org;

	if (tmp.length == 6) {
		tmp = "20" + tmp;
	}

	if (tmp.length == 8) {
		var year  = parseInt(tmp.substring(0, 4), 10);
		var month = parseInt(tmp.substring(4, 6), 10) - 1;
		var day   = parseInt(tmp.substring(6, 8), 10);

		if (isNaN(year) || isNaN(month) || isNaN(day)) {
			return false;
		}

		var d = new Date(year, month, day);

		if (month != d.getMonth()) {
			return false;
		}

		if (day != d.getDate()) {
			return false;
		}

		return true;
	}

	return false;
}

/* ---------- 必須入力項目 ---------- */
function defineAsRequiredField(id) {
	var o = $(id);
	Element.addClassName(o, "cssRequired");

}
/* ---------- キー項目 ---------- */
function defineAsKeyField(id) {
	var o = $(id);
	Event.observe(o, 'blur', _keyBlurListener.bind(o), false);

	if (o.style.imeMode) {
		o.style.imeMode = 'disabled';
	}

	//o.style.textAlign = 'right';
}
/* キー項目 */
function _keyBlurListener() {
	var o = this.value;
	this.value =o.replace(/^\s+|\s+$/g, "");
}


/* ---------- 年月型 ---------- */
var g_yearsElements = new Array();

/* 年月型 ON_FOCUSの動作を設定 */
function _yearsFocusListener() {
	this.value = unformatDate(this.value);
	Field.select(this);
}

/* 年月型 OFF_FOCUSの動作を設定 */
function _yearsBlurListener() {
	this.value = formatYears(this.value);
}

/**
 * idのテキストフィールドを年月型として定義する.
 * @param id id
 */
function defineAsYearsField(id) {

	var o = $(id);
	Event.observe(o, 'focus', _yearsFocusListener.bind(o), false);
	Event.observe(o, 'blur', _yearsBlurListener.bind(o), false);

	if (o.style.imeMode) {
		o.style.imeMode = 'disabled';
	}
	o.style.textAlign = 'left';

	g_yearsElements[o.id] = o;
}

/* ---------- 日付型 ---------- */
var g_dateElements = new Array();

/* 日付型(yyyy/MM/dd) ON_FOCUSの動作を設定 */
function _dateFocusListener() {
	this.value = unformatDate(this.value);
	Field.select(this);
}

/* 日付型(yyyy/MM/dd) OFF_FOCUSの動作を設定 */
function _dateBlurListener() {
	this.value = formatDate(this.value);
}

/* 日付型(yyyy/MM) OFF_FOCUSの動作を設定 */
function _dateYMBlurListener() {
	this.value = formatDateYM(this.value);
}

/**
 * idのテキストフィールドを日付型(yyyy/MM/dd)として定義する.
 * @param id id
 */
function defineAsDateField(id) {

	var o = $(id);
	Event.observe(o, 'focus', _dateFocusListener.bind(o), false);
	Event.observe(o, 'blur', _dateBlurListener.bind(o), false);

	if (o.style.imeMode) {
		o.style.imeMode = 'disabled';
	}
	o.style.textAlign = 'left';

	g_dateElements[o.id] = o;
}

/**
 * idのテキストフィールドを日付(yyyy/MM)型として定義する.
 * @param id id
 */
function defineAsDateYMField(id) {

	var o = $(id);
	Event.observe(o, 'focus', _dateFocusListener.bind(o), false);
	Event.observe(o, 'blur', _dateYMBlurListener.bind(o), false);

	if (o.style.imeMode) {
		o.style.imeMode = 'disabled';
	}
	o.style.textAlign = 'left';

	g_dateElements[o.id] = o;
}

/* ---------- 年月型 ---------- */
var g_yearsElements = new Array();

/* 年月型 ON_FOCUSの動作を設定 */
function _yearsFocusListener() {
	this.value = unformatDate(this.value);
	Field.select(this);
}

/* 年月型 OFF_FOCUSの動作を設定 */
function _yearsBlurListener() {
	this.value = formatYears(this.value);
}

/**
 * idのテキストフィールドを年月型として定義する.
 * @param id id
 */
function defineAsYearsField(id) {

	var o = $(id);
	Event.observe(o, 'focus', _yearsFocusListener.bind(o), false);
	Event.observe(o, 'blur', _yearsBlurListener.bind(o), false);

	if (o.style.imeMode) {
		o.style.imeMode = 'disabled';
	}
	o.style.textAlign = 'left';

	g_yearsElements[o.id] = o;
}

/* ---------- 時刻型 ---------- */
var g_timeElements = new Array();

/* 時刻型 ON_FOCUSの動作を設定 */
function _timeFocusListener() {
	this.value = unformatTime(this.value);
	Field.select(this);
}

/* 時刻型 OFF_FOCUSの動作を設定 */
function _timeBlurListener() {
	this.value = formatTime(this.value);
}

/**
 * idのテキストフィールドを時刻型として定義する.
 * @param id id
 */
function defineAsTimeField(id) {

	var o = $(id);
	Event.observe(o, 'focus', _timeFocusListener.bind(o), false);
	Event.observe(o, 'blur', _timeBlurListener.bind(o), false);

	if (o.style.imeMode) {
		o.style.imeMode = 'disabled';
	}
	o.style.textAlign = 'left';

	g_timeElements[o.id] = o;
}

/**
 * idのテキストフィールドを時刻型として定義する.
 * @param id id
 */
function defineAsTimeField_DaylyrRport(id) {

	var o = $(id);
	Event.observe(o, 'focus', _timeFocusListener.bind(o), false);
	Event.observe(o, 'blur', _timeBlurListener_DaylyrRport.bind(o), false);

	if (o.style.imeMode) {
		o.style.imeMode = 'disabled';
	}
	o.style.textAlign = 'left';

	g_timeElements[o.id] = o;
}


/* 時刻型 OFF_FOCUSの動作を設定 */
function _timeBlurListener_DaylyrRport() {
	this.value = formatTime_DaylyrRport(this.value);
}

/**
 * 時刻のコロン編集用文字列操作関数.
 * @param  org 元の文字列
 * @return 編集後の文字列
 */
function formatTime_DaylyrRport(org) {

	if (org == null) {
		return null;
	}
	var tmp = org;
	
	if (tmp.length >= 4) {
		if(!org.match(/^(-?)[0-9]{4,}$/)){
			return org;
		}
		var strLen = tmp.length;
		var hour = parseInt(tmp.substring(0, strLen -2 ), 10);
		var minute = parseInt(tmp.substring(strLen - 2, strLen), 10);

		if (isNaN(hour) || isNaN(minute)) {
			return org;
		}

		return tmp.substring(0, strLen -2) + ":" + tmp.substring(strLen - 2, strLen);
	}
	return org;
}
/* ---------- 数値型 ---------- */
var g_numberElements = new Array();

/* 数値型 ON_FOCUSの動作を設定 */
function _numberFocusListener() {
	this.value = unformatNumber(this.value);
	Field.select(this);
}

/* 数値型 OFF_FOCUSの動作を設定 */
function _numberBlurListener() {
	this.value = formatNumber(this.value);
}

/**
 * idのテキストフィールドを数値型として定義する.
 * @param id id
 */
function defineAsNumberField(id) {

	var o = $(id);
	Event.observe(o, 'focus', _numberFocusListener.bind(o), false);
	Event.observe(o, 'blur', _numberBlurListener.bind(o), false);

	if (o.style.imeMode) {
		o.style.imeMode = 'disabled';
	}

	o.style.textAlign = 'right';

	g_numberElements[o.id] = o;
}

/* ---------- フォーマット関数 ---------- */

/**
 * 日付(yyyy/MM/dd)のスラッシュ編集用文字列操作関数.
 * @param  org 元の文字列
 * @return 編集後の文字列
 */
function formatDate(org) {

	if (org == null) {
		return null;
	}

	if(!org.match(/^(-?)[0-9]{6,8}$/)){
		return org;
	}

	var tmp = org;

	if (tmp.length == 6) {
		tmp = "20" + tmp;
	}

	if (tmp.length == 8) {

		var year  = parseInt(tmp.substring(0, 4), 10);
		var month = parseInt(tmp.substring(4, 6), 10) - 1;
		var day   = parseInt(tmp.substring(6, 8), 10);

		if (isNaN(year) || isNaN(month) || isNaN(day)) {
			return org;
		}

		var d = new Date(year, month, day);

		if (month != d.getMonth()) {
			return org;
		}

		if (day != d.getDate()) {
			return org;
		}

		return tmp.substring(0, 4) + "/" + tmp.substring(4, 6) + "/" + tmp.substring(6, 8);
	}

	return org;
}

/**
 * 日付(yyyy/MM)のスラッシュ編集用文字列操作関数.
 * @param  org 元の文字列
 * @return 編集後の文字列
 */
function formatDateYM(org) {

	if (org == null) {
		return null;
	}

	if(!org.match(/^(-?)[0-9]{4,6}$/)){
		return org;
	}

	var tmp = org;
	
	if (tmp.length == 4) {
		tmp = "20" + tmp;
	}

	if (tmp.length == 6) {

		var year  = parseInt(tmp.substring(0, 4), 10);
		var month = parseInt(tmp.substring(4, 6), 10) - 1;
		var day   = parseInt('01', 10);

		if (isNaN(year) || isNaN(month)) {
			return org;
		}
			
		var d = new Date(year, month, day);

		if (month != d.getMonth()) {
			return org;
		}
			
		if (day != d.getDate()) {
			return org;
		}

		return tmp.substring(0, 4) + "/" + tmp.substring(4, 6);
	}

	return org;
}

/**
 * 年月のスラッシュ編集用文字列操作関数.
 * @param  org 元の文字列
 * @return 編集後の文字列
 */
function formatYears(org) {

	if (org == null) {
		return null;
	}

	if(!org.match(/^(-?)[0-9]{6,8}$/)){
		return org;
	}

	var tmp = org;

	if (tmp.length == 4) {
		tmp = "20" + tmp;
	}

	if (tmp.length == 6) {

		var year  = parseInt(tmp.substring(0, 4), 10);
		var month = parseInt(tmp.substring(4, 6), 10) - 1;

		if (isNaN(year) || isNaN(month)) {
			return org;
		}

		var d = new Date(year, month, 1);

		if (month != d.getMonth()) {
			return org;
		}

		return tmp.substring(0, 4) + "/" + tmp.substring(4, 6);
	}

	return org;
}

/**
 * 時刻のコロン編集用文字列操作関数.
 * @param  org 元の文字列
 * @return 編集後の文字列
 */
function formatTime(org) {

	if (org == null) {
		return null;
	}

	var tmp = org;

	if (tmp.length == 4) {
		if(!org.match(/^(-?)[0-9]{4}$/)){
			return org;
		}

		var hour = parseInt(tmp.substring(0, 2), 10);
		var minute = parseInt(tmp.substring(2, 4), 10);

		if (isNaN(hour) || isNaN(minute)) {
			return org;
		}

		var c = new Date();
		var d = new Date(c.getYear(), c.getMonth(), c.getDay(), hour, minute);

		if (hour != d.getHours()) {
			return org;
		}

		if (minute != d.getMinutes()) {
			return org;
		}

		return tmp.substring(0, 2) + ":" + tmp.substring(2, 4);
	} else if (tmp.length == 6) {
		if(!org.match(/^(-?)[0-9]{6}$/)){
			return org;
		}

		var hour = parseInt(tmp.substring(0, 2), 10);
		var minute = parseInt(tmp.substring(2, 4), 10);
		var second = parseInt(tmp.substring(4, 6), 10);

		if (isNaN(hour) || isNaN(minute) || isNaN(second)) {
			return org;
		}

		var c = new Date();
		var d = new Date(c.getYear(), c.getMonth(), c.getDay(), hour, minute, second);

		if (hour != d.getHours()) {
			return org;
		}

		if (minute != d.getMinutes()) {
			return org;
		}

		if (second != d.getSeconds()) {
			return org;
		}

		return tmp.substring(0, 2) + ":" + tmp.substring(2, 4) + ":" + tmp.substring(4, 6);
	}

	return org;
}

/**
 * 日付のスラッシュ編集戻し用文字列操作関数.
 * @param  org 元の文字列
 * @return 編集後の文字列
 */
function unformatDate(org) {
	if (org == null) {
		return null;
	}
	return org.replace(/\//g, "");
}

/**
 * 時刻のコロン編集戻し用文字列操作関数.
 * @param  org 元の文字列
 * @return 編集後の文字列
 */
function unformatTime(org) {
	if (org == null) {
		return null;
	}
	return org.replace(/:/g, "");
}

/**
 * 数値のカンマ編集用文字列操作関数.
 * @param  org 元の文字列
 * @return 編集後の文字列
 */
function formatNumber(org) {
	if (org == null) {
		return null;
	}

	if (org.match(/^(-?)[0-9]*(\.?)[0-9]*$/)) {
		var dest = org;
		var tmp = "";
		while (dest != (tmp= dest.replace(/^([+-]?\d+)(\d\d\d)/,"$1,$2"))) {
			dest = tmp;
		}
		return dest;
	}
	return org;
}

/**
 * 数値のカンマ編集戻し用文字列操作関数.
 * @param  org 元の文字列
 * @return 編集後の文字列
 */
function unformatNumber(org) {
	if (org == null) {
		return null;
	}
	return org.replace(/,/g, "");
}

/**
 * Textフィールドをthisとしてkeypress時にクリアする為のeventhandler用
 */
function clearText(event) {
  // いたちごっごになるので(controls.jsと同じ方針に..)
  if(event.keyCode==Event.KEY_TAB || event.keyCode==Event.KEY_RETURN ||
   (navigator.appVersion.indexOf('AppleWebKit') > 0 && event.keyCode == 0)) return;

  this.value = '';
}
/**
 * ラベル(div要素内)をkeypress時にクリアする為のeventhandler用
 */
function clearDivLabel(event) {
  // いたちごっごになるので(controls.jsと同じ方針に..)
  if(event.keyCode==Event.KEY_TAB || event.keyCode==Event.KEY_RETURN ||
   (navigator.appVersion.indexOf('AppleWebKit') > 0 && event.keyCode == 0)) return;

  $((this.id).capitalize()).innerText = '';
}

/**
 * チェックボックスをkeypress時にクリアする為のeventhandler用
 */
function clearCheckbox(event) {
  // いたちごっごになるので(controls.jsと同じ方針に..)
  if(event.keyCode==Event.KEY_TAB || event.keyCode==Event.KEY_RETURN ||
   (navigator.appVersion.indexOf('AppleWebKit') > 0 && event.keyCode == 0)) return;

  $((this.id).capitalize()).checked = false;
}

