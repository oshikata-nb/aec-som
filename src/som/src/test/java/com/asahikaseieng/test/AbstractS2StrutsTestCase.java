/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.asahikaseieng.Constants;
import com.asahikaseieng.struts.ApplicationServlet;
import com.asahikaseieng.utils.AecStackTrace;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.impl.S2ContainerImpl;
import org.seasar.framework.exception.NoSuchMethodRuntimeException;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.MethodUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.StringUtil;

import servletunit.struts.MockStrutsTestCase;

/**
 * S2StrutsTestCaseのスーパークラス.
 * @author jbd
 */
public abstract class AbstractS2StrutsTestCase extends MockStrutsTestCase {

	private String requestPathInfo;

	private S2Container container;

	private List<Field> bindedFields;

	/** Log */
	private static Log log = LogFactory.getLog(AbstractS2StrutsTestCase.class);

	/**
	 * コンストラクタ.
	 * 
	 * @param testName テスト名
	 * @param requestPathInfo テスト対象*.do名(例 "/ActLogin.do")
	 */
	public AbstractS2StrutsTestCase(final String testName,
			final String requestPathInfo) {
		super(testName);
		this.requestPathInfo = requestPathInfo;
	}

	/**
	 * <p>
	 * テストケースが保有するS2コンテナを取得します。
	 * </p>
	 * 
	 * @return S2コンテナ
	 */
	public S2Container getContainer() {
		return container;
	}

	/**
	 * <p>
	 * コンポーネントを取得します。
	 * </p>
	 * 
	 * @param componentName コンポーネント名
	 * @return コンポーネント
	 */
	public Object getComponent(final String componentName) {
		return container.getComponent(componentName);
	}

	/**
	 * <p>
	 * コンポーネントを取得します。
	 * </p>
	 * 
	 * @param componentClass コンポーネントのクラス
	 * @return コンポーネント
	 */
	public Object getComponent(final Class componentClass) {
		return container.getComponent(componentClass);
	}

	/**
	 * <p>
	 * コンポーネントDefを取得します。
	 * </p>
	 * 
	 * @param componentName コンポーネント名
	 * @return コンポーネントDef
	 */
	public ComponentDef getComponentDef(final String componentName) {
		return container.getComponentDef(componentName);
	}

	/**
	 * <p>
	 * コンポーネントDefを取得します。
	 * </p>
	 * 
	 * @param componentClass コンポーネントのクラス
	 * @return コンポーネントDef
	 */
	public ComponentDef getComponentDef(final Class componentClass) {
		return container.getComponentDef(componentClass);
	}

	/**
	 * {@inheritDoc}
	 */
	public void runBare() throws Throwable {
		setUpContainer();
		SingletonS2ContainerFactory.setContainer(this.container);
		setUp();
		try {
			setUpForEachTestMethod();
			try {
				this.container.init();
				try {
					bindFields();
					setUpAfterBindFields();
					try {
						// トランザクションがいらないのでここでテスト実行
						runTest();
					} finally {
						unbindFields();
					}
					tearDownBeforeContainerDestroy();
				} finally {
					container.destroy();
					SingletonS2ContainerFactory.setContainer(null);
				}
			} finally {
				tearDownForEachTestMethod();
			}
		} finally {
			tearDown();
		}
	}

	/**
	 * <p>
	 * コンテナをセットアップするメソッドです。
	 * </p>
	 * <p>
	 * 必要な場合にオーバーライドしてください。
	 * </p>
	 * 
	 * @throws Throwable Throwable
	 */
	protected void setUpContainer() throws Throwable {
		container = new S2ContainerImpl();
	}

	/**
	 * <p>
	 * フィールドが設定された後に実行されるセットアップメソッドです。
	 * </p>
	 * <p>
	 * 必要な場合にオーバーライドしてください。
	 * </p>
	 * 
	 * @throws Throwable Throwable
	 */
	protected void setUpAfterBindFields() throws Throwable {
	}

	/**
	 * <p>
	 * コンテナ終了処理前に実行される終了処理メソッドです。
	 * </p>
	 * <p>
	 * 必要な場合にオーバーライドしてください。
	 * </p>
	 * 
	 * @throws Throwable Throwable
	 */
	protected void tearDownBeforeContainerDestroy() throws Throwable {
	}

	/**
	 * <p>
	 * setup() 後に実行されるテストメソッドごとのセットアップ メソッドです。
	 * </p>
	 * <p>
	 * testXxx() というメソッドの場合、setUpXxx() という名前で セットアップメソッドを作成しておくと、自動的に実行されます。
	 * </p>
	 * 
	 * @throws Throwable Throwable
	 */
	protected void setUpForEachTestMethod() throws Throwable {
		invoke("setUp" + getTargetName());
	}

	/**
	 * <p>
	 * tearDown() 前に実行されるテストメソッドごとの終了処理メソッド です。
	 * </p>
	 * <p>
	 * testXxx() というメソッドの場合、tearDownXxx() という名前で 終了処理メソッドを作成しておくと、自動的に実行されます。
	 * </p>
	 * 
	 * @throws Throwable Throwable
	 */
	protected void tearDownForEachTestMethod() throws Throwable {
		invoke("tearDown" + getTargetName());
	}

	private String getTargetName() {
		return getName().substring("test".length());
	}

	private void invoke(final String methodName) throws Throwable {
		try {
			Method method = ClassUtil.getMethod(getClass(), methodName, null);
			MethodUtil.invoke(method, this, null);
		} catch (NoSuchMethodRuntimeException ignore) {
			ignore.toString();
		}
	}

	private void bindFields() throws Throwable {
		this.bindedFields = new ArrayList<Field>();
		for (Class clazz = getClass(); clazz != S2TestCase.class
				&& clazz != null; clazz = clazz.getSuperclass()) {

			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; ++i) {
				bindField(fields[i]);
			}
		}
	}

	private void bindField(final Field field) {
		if (isAutoBindable(field)) {
			field.setAccessible(true);
			if (FieldUtil.get(field, this) != null) {
				return;
			}
			String name = normalizeName(field.getName());
			Object component = null;
			if (getContainer().hasComponentDef(name)) {
				Class componentClass = getComponentDef(name)
						.getComponentClass();
				if (componentClass == null) {
					component = getComponent(name);
					if (component != null) {
						componentClass = component.getClass();
					}
				}
				if (componentClass != null
						&& field.getType().isAssignableFrom(componentClass)) {
					if (component == null) {
						component = getComponent(name);
					}
				} else {
					component = null;
				}
			}
			if (component == null
					&& getContainer().hasComponentDef(field.getType())) {
				component = getComponent(field.getType());
			}
			if (component != null) {
				FieldUtil.set(field, this, component);
				this.bindedFields.add(field);
			}
		}
	}

	private void unbindFields() {
		for (int i = 0; i < bindedFields.size(); ++i) {
			Field field = bindedFields.get(i);
			try {
				field.set(this, null);
			} catch (IllegalArgumentException e) {
				log.error(AecStackTrace.getStackTrace(e));
			} catch (IllegalAccessException e) {
				log.error(AecStackTrace.getStackTrace(e));
			}
		}
	}

	private boolean isAutoBindable(final Field field) {
		int modifiers = field.getModifiers();
		return !Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers)
				&& !field.getType().isPrimitive();
	}

	private String normalizeName(final String name) {
		return StringUtil.replace(name, "_", "");
	}

	/**
	 * <p>
	 * 設定ファイルのパスを指定して子コンテナをincludeします。
	 * </p>
	 * <p>
	 * パスはCLASSPATHで指定されているディレクトリをルートとする 設定ファイルの絶対パスか、ファイル名のみを指定します。
	 * </p>
	 * <p>
	 * ファイル名のみの場合、テストケースと同じパッケージにあるもの とします。
	 * </p>
	 * 
	 * @param path 子コンテナの設定ファイルのパス
	 */
	public void include(final String path) {
		S2ContainerFactory.include(container, convertPath(path));
	}

	private String convertPath(final String path) {
		if (ResourceUtil.getResourceNoException(path) != null) {
			return path;
		}
		String prefix = getClass().getPackage().getName().replace('.', '/');
		return prefix + "/" + path;
	}

	//
	// ここから上はS2TestCaseをコピーしてカスタマイズしています。
	//

	/**
	 * コンストラクタで渡したrequestPathInfoを取得する
	 * 
	 * @return コンストラクタで渡したrequestPathInfo
	 */
	public final String getRequestPathInfo() {
		return requestPathInfo;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setRequestPathInfo(final String moduleName,
			final String pathInfo) {
		this.requestPathInfo = pathInfo;
		super.setRequestPathInfo(moduleName, pathInfo);
	}

	/**
	 * URL変数、エラー、トークンをリセットする
	 * 
	 */
	protected final void reset() {
		clearRequestParameters();
		getRequestWrapper().setAttribute(Globals.ERROR_KEY, null);
		resetToken();
	}

	/**
	 * トランザクショントークンを設定する
	 */
	protected final void setToken() {
		addRequestParameter(org.apache.struts.taglib.html.Constants.TOKEN_KEY,
			"test_token");
		getSession().setAttribute(Globals.TRANSACTION_TOKEN_KEY, "test_token");
	}

	/**
	 * トランザクショントークンをリセットする
	 */
	protected final void resetToken() {
		getSession().setAttribute(Globals.TRANSACTION_TOKEN_KEY, null);
	}

	private String getConfigFile() throws Exception {

		// // web.xml解析
		// Digester digester = new Digester(SAXParserFactory.newInstance()
		// .newSAXParser());
		// digester.addObjectCreate("web-app", WebApp.class);
		// digester.addObjectCreate("web-app/servlet", Servlet.class);
		// digester.addSetNext("web-app/servlet", "addChild");
		// digester.addRule("web-app/servlet/servlet-name",
		// new BeanPropertySetterRule("servletName"));
		// digester.addObjectCreate("web-app/servlet/init-param",
		// InitParam.class);
		// digester.addSetNext("web-app/servlet/init-param", "addChild");
		// digester.addRule("web-app/servlet/init-param/param-name",
		// new BeanPropertySetterRule("name"));
		// digester.addRule("web-app/servlet/init-param/param-value",
		// new BeanPropertySetterRule("value"));
		// // ↓ for maven
		// // WebApp webapp = (WebApp) digester.parse("webapp/WEB-INF/web.xml");
		// WebApp webapp = (WebApp) digester
		// .parse("src/main/webapp/WEB-INF/web.xml");
		// // ↑
		//
		// // configを抽出
		// Servlet servlet = (Servlet) webapp.getServlets().get("action");
		// if (servlet != null) {
		// InitParam initParam = (InitParam) servlet.getInitParams().get(
		// "config");
		// if (initParam != null) {
		// return initParam.getValue();
		// }
		// }
		//
		// return "/WEB-INF/struts-config.xml";

		return "/WEB-INF/struts-config.xml,/WEB-INF/struts-config-demo.xml";
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setUp() throws Exception {

		super.setUp();

		// s2struts.diconはjar内にも存在する為、mavenからの実行では
		// どうしてものっとれない(childDelegationでは無理)
		// よって別名にする
		include("mys2struts.dicon");

		setActionServlet(new ApplicationServlet());

		// ↓ for maven
		// setContextDirectory(new File("webapp"));
		setContextDirectory(new File("src/main/webapp"));
		// ↑
		setServletConfigFile("/WEB-INF/web.xml");
		// 本物のweb.xmlのconfig設定を読み込む
		// setConfigFile("/WEB-INF/struts-config.xml");
		setConfigFile(getConfigFile());

		setRequestPathInfo(requestPathInfo);

		include("actiontest.dicon");

		setUpImpl();
	}

	/**
	 * 独自の初期化内容を記述する(必要があれば継承先でカスタマイズして下さい)
	 * 
	 * @throws Exception 例外
	 */
	protected void setUpImpl() throws Exception {
	}

	/**
	 * ActionErrorが含まれているかどうか検証する <br>
	 * 細かく検証が必要かどうかする為に必要
	 * 
	 * @param key エラーキー
	 * @return true 含んでいる false 含んでいない
	 */
	protected final boolean containsActionError(final String key) {
		return constainsActionErrorImpl(new ActionMessage(key));
	}

	/**
	 * ActionErrorが含まれているかどうか検証する <br>
	 * 細かく検証が必要かどうかする為に必要
	 * 
	 * @param key エラーキー
	 * @param arg0 arg0
	 * @return true 含んでいる false 含んでいない
	 */
	protected final boolean containsActionError(final String key,
			final String arg0) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		return constainsActionErrorImpl(new ActionMessage(key, rb
				.getString(arg0)));
	}

	/**
	 * ActionErrorが含まれているかどうか検証する <br>
	 * 細かく検証が必要かどうかする為に必要
	 * 
	 * @param key エラーキー
	 * @param arg0 arg0
	 * @param arg1 arg1
	 * @return true 含んでいる false 含んでいない
	 */
	protected final boolean containsActionError(final String key,
			final String arg0, final String arg1) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		return constainsActionErrorImpl(new ActionMessage(key, rb
				.getString(arg0), rb.getString(arg1)));
	}

	/**
	 * ActionErrorが含まれているかどうか検証する <br>
	 * 細かく検証が必要かどうかする為に必要
	 * 
	 * @param key エラーキー
	 * @param arg0 arg0
	 * @param arg1 arg1
	 * @param arg2 arg2
	 * @return true 含んでいる false 含んでいない
	 */
	protected final boolean containsActionError(final String key,
			final String arg0, final String arg1, final String arg2) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		return constainsActionErrorImpl(new ActionMessage(key, rb
				.getString(arg0), rb.getString(arg1), rb.getString(arg2)));
	}

	/**
	 * ActionErrorが含まれているかどうか検証する <br>
	 * 細かく検証が必要かどうかする為に必要
	 * 
	 * @param key エラーキー
	 * @param arg0 arg0
	 * @param arg1 arg1
	 * @param arg2 arg2
	 * @param arg3 arg3
	 * @return true 含んでいる false 含んでいない
	 */
	protected final boolean containsActionError(final String key,
			final String arg0, final String arg1, final String arg2,
			final String arg3) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		return constainsActionErrorImpl(new ActionMessage(key, rb
				.getString(arg0), rb.getString(arg1), rb.getString(arg2), rb
				.getString(arg3)));
	}

	/**
	 * ActionErrorが含まれているかどうか検証する <br>
	 * 細かく検証が必要かどうかする為に必要
	 * 
	 * @param key エラーキー
	 * @param args args
	 * @return true 含んでいる false 含んでいない
	 */
	protected final boolean containsActionError(final String key,
			final String[] args) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		String[] items = new String[args.length];
		for (int i = 0; i < items.length; i++) {
			items[i] = rb.getString(args[i]);
		}
		return constainsActionErrorImpl(new ActionMessage(key, items));
	}

	private boolean constainsActionErrorImpl(final ActionMessage ae) {
		ActionErrors aes = (ActionErrors) getRequestWrapper().getAttribute(
			Globals.ERROR_KEY);
		for (Iterator ite = aes.get(); ite.hasNext();) {
			ActionMessage a = (ActionMessage) ite.next();
			if (a.getKey().equals(ae.getKey())) {
				Object[] aeArray = ae.getValues();
				Object[] array = a.getValues();
				if (aeArray != null && array != null) {
					for (int i = 0; i < array.length; i++) {
						for (int j = 0; j < aeArray.length; j++) {
							if (array[i] != null
									&& !array[i].equals(aeArray[j])) {
								return false;
							}
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 人に優しいエラー表示
	 */
	protected final void printErrors() {
		log.error("=== ActionErrors containts ===");
		ActionErrors aes = (ActionErrors) getRequestWrapper().getAttribute(
			Globals.ERROR_KEY);
		if (aes != null) {
			for (Iterator ite = aes.get(); ite.hasNext();) {
				ActionMessage ae = (ActionMessage) ite.next();
				log.error(ae.getKey() + ":");
				if (ae.getValues() == null) {
					for (int i = 0; i < ae.getValues().length; i++) {
						log.error(ae.getValues()[i] + " ");
					}
				}
				log.error("");
			}
		}
		log.error("==============================");
	}
}
