package com.expressflow.servlets;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

public class JavaScriptFormatter {
	public static String ExtSetup(String modelName) {
		String result = "Ext.setup({" + "icon: \'icon.png\',"
				+ "tabletStartupScreen: 'sencha/tablet_startup.png',"
				+ "phoneStartupScreen: 'sencha/phone_startup.png',"
				+ "glossOnIcon: false," + "onReady: function() {" + "var form;"
				+ "Ext.regModel('" + modelName + "', {" + "fields: [";
		return result;
	}
	
	public static String ExtModelField(String name, String type){
		String result = "{name: '" + name + "',     type: '" + type + "'}";
		return result;
	}
	
	public static String ExtModelField(String nameAttribute, String name, String valueAttribute, String value){
		String result = "{" + nameAttribute + ": '" + name + "',     " + valueAttribute + ": '" + value + "'}";
		return result;
	}

	public static String ExtFormBase(String catchUpUrl, String title) {
		String result = "]" + "});" + "var formBase = {"
				+ "scroll: 'vertical'," + "url   : '" + catchUpUrl + "',"
				+ "standardSubmit : false," + "items: [" + "{"
				+ "xtype: 'fieldset'," + "title: '" + title + "',"
				+ "instructions: 'Please enter the information above.',"
				+ "defaults: {" + "required: true," + "labelAlign: 'left',"
				+ "labelWidth: '40%'" + "}," + "items: [";
		return result;
	}
	
	public static String ExtGroupingBase(String storeModelName, String firstModelField, String secondModelField){
		String result = "var overlayTb = new Ext.Toolbar({"
            + "dock: 'top'"
            + "});" 
			+ "var overlay = new Ext.Panel({"
            + "floating: true,"
            + "modal: true,"
            + "centered: false,"
            + "width: 260,"
            + "height: 220,"
            + "styleHtmlContent: true,"
            + "dockedItems: overlayTb,"
            + "scroll: 'vertical',"
            + "cls: 'htmlcontent'" 
            + "});"
			+ "var groupingBase = {"
            + "itemTpl: '<div class=\"contact2\"><strong>{" + firstModelField + "}</strong></div>',"
            + "selModel: { "
            + "  mode: 'SINGLE',"
            + "  allowDeselect: true "
            + "},"
            + "grouped: true,"
            + "indexBar: true,"
            + "onItemDisclosure: {"
            + "   scope: 'test',"
            + "   handler: function(record, btn, index) {"
            + "   overlay.setCentered(true);"
            + "   overlayTb.setTitle(record.get('name'));"
            + "	  overlay.html = 'Process ready for execution.';"
            + "	  var startProcessBtn = new Ext.Button({" +
            		"text: 'Start Process'," +
            		"dock: 'bottom'," +
            		"listeners: {" +
            		"'tap': function(){" +
            		"	window.location = record.get('url');" +
            		"}" +
            		"}" +
            		"});" 
            + "	  overlay.add(startProcessBtn);" 
			// + "   overlay.html = \"Process ready for execution. <br /><a href='\" + record.get('url') + \"'>Start</a>\"; "
            + "   overlay.show();"
            + "   }"
//            + "   handler: function(record, btn, index) {"
//            + "      alert('Mobile Form deployed at: ' + record.get('" + secondModelField + "'));" 
//            + "   }"
            + "},"
            + "store: new Ext.data.Store({"
            + " model: '" + storeModelName + "',"
            + " sorters: '" + firstModelField + "',"
            + " getGroupString : function(record) {"
            + "  return record.get('" + firstModelField + "')[0];"
            + "   },"
            + " data: [";
		return result;
	}

	public static String ExtTextField(String name) {
		String result = "{xtype: 'textfield'," + "name : '" + name + "',"
				+ "label: '" + name + "'," + "useClearIcon: true,"
				+ "autoCapitalize : false}";
		return result;
	}

	public static String ExtPasswordField(String name) {
		String result = "{xtype: 'passwordfielrdfield'," + "name : '" + name + "',"
				+ "label: '" + name + "'," + "useClearIcon: false}";
		return result;
	}

	public static String ExtEmailField(String name, String placeHolder) {
		String result = "{xtype: 'emailfield'," + "name : '" + name + "',"
				+ "label: '" + name + "'," + "placeHolder: '" + placeHolder
				+ "'," + "useClearIcon: true}";
		return result;
	}

	public static String ExtCheckBoxField(String name) {
		String result = "{" + "xtype: 'checkboxfield'," + "name : '" + name
				+ "'," + "label: '" + name + "'," + "value: true}";
		return result;
	}

	public static String ExtTextAreaField(String name) {
		String result = "{xtype : 'textareafield'," + "name  : '" + name + "',"
				+ "label : '" + name + "'," + "maxLength : 60,"
				+ "maxRows : 10}";
		return result;
	}

	public static String ExtDockItems() {
		String result = "]" + "}" + "]," + "listeners : {"
				+ "submit : function(form, result){"
				+ "console.log('success', Ext.toArray(arguments));" + "},"
				+ "exception : function(form, result){"
				+ "console.log('failure', Ext.toArray(arguments));" + "}"
				+ "}," + "dockedItems: [" + "{" + "xtype: 'toolbar',"
				+ "dock: 'bottom'," + "items: [" + "{" + "text: 'Load Model',"
				+ "ui: 'round'," + "handler: function() {"
				+ "formBase.user = Ext.ModelMgr.create({";
		return result;
	}
	
	public static String ExtModelManagerItem(String name, String value){
		String result = "'"+ name + "'    : '" + value + "'";
		return result;
	}

	public static String ExtLoadModel(String url) {
		String result = "}, 'User');" + "form.loadModel(formBase.user);" + "}"
				+ "}," + "{xtype: 'spacer'}," + "{" + "text: 'Reset',"
				+ "handler: function() {" + "form.reset();" + "}" + "}," + "{"
				+ "text: 'Execute'," + "ui: 'confirm'," + "handler: function() {"
				+ "if(formBase.user){"
				+ "form.updateRecord(formBase.user, true);" + "}"
				+ "form.submit({"
				+ "url: '" + url + "',"
				+ "method: 'POST',"
				+ "success:function(form, result){"
				+ "Ext.Msg.alert('Status', 'Executed successfully!', function(btn, text){"
				+ "	   if (btn == 'ok'){"
			    + "                    var redirect = '/myprocesses';" 
			    + "                   window.location = redirect;"
	            + "                      }"
				+ "       });"
				+ "},"
				+ "failure:function(form, result){"
				+ "Ext.Msg.alert('Status', 'Executed successfully!', function(btn, text){"
				+ "	   if (btn == 'ok'){"
			    + "                    var redirect = '/myprocesses';" 
			    + "                   window.location = redirect;"
	            + "                      }"
				+ "       });"
				+ "},"
				+ "waitMsg : {message:'Executing', cls : 'demos-loading'}"
				+ "});" + "}" + "}" + "]" + "}" + "]" + "};"
				+ "if (Ext.is.Phone) {" + "formBase.fullscreen = true;"
				+ "} else {" + "Ext.apply(formBase, {" + "autoRender: true,"
				+ "floating: true," + "modal: true," + "centered: true,"
				+ "hideOnMaskTap: false," + "height: 385," + "width: 480"
				+ "});" + "}" + "form = new Ext.form.FormPanel(formBase);"
				+ "form.show();" + "}" + "});";
		return result;
	}
	
	public static String ExtLoadGroupingBase(){
		String result = "if (!Ext.is.Phone) {"
            + "new Ext.List(Ext.apply(groupingBase, {"
            + "  floating: true,"
            + "  width: 350,"
            + "  height: 370,"
            + "  centered: true,"
            + "  modal: true,"
            + "  hideOnMaskTap: false"
            + "})).show();"
            + "}"
            + "else {"
            + "new Ext.List(Ext.apply(groupingBase, {"
            + "  fullscreen: true"
            + "}));"
            + "}";
		return result;
	}
}
