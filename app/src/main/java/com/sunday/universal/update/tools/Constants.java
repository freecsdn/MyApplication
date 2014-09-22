package com.sunday.universal.update.tools;

public class Constants {

	protected static final String APP_UPDATE_SERVER_URL = "http://61.147.72.236/mobile/api/api.php?act=getVersion&type=android&uin=30009120&pid=13&sessionkey=nMG3n2UX4zvuVsaFkuSE6yA9YYtaJtX7&sign=b7b820c4001d30a2b854ac92527de2cc";
	
	// json {"url":"http://192.168.1.115:8080/xxx.apk","versionCode":2,"updateMessage":"版本更新信息"}
	//我这里服务器返回的json数据是这样的，可以根据实际情况修改下面参数的名称
	public static final String APK_DOWNLOAD_URL = "url";
	public static final String APK_UPDATE_CONTENT = "updateMessage";
	public static final String APK_VERSION_CODE = "versionCode";

    //UpdateChecker.checkForDialog(MainActivity.this);弹框更新提示

     //UpdateChecker.checkForNotification(MainActivity.this);通知栏更新提示

}
