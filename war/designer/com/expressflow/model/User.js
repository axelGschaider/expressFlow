Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.User',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'String'},
        {name: 'googleServicesActivated', type: 'int'},
        {name: 'email', type: 'String'},
        {name: 'nickname', type: 'String'},
        {name: 'appid', type: 'String'}
    ],
    statics: {
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.User');
    		for(var i=0; i < xml.childNodes.length; i++){
    			switch(xml.childNodes[i].nodeName){
    			case "id":
    				element.data.id = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "googleServicesActivated":
    				element.data.googleServicesActivated = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "email":
    				element.data.email = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "nickname":
    				element.data.nickname = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "appId":
    				element.data.appId = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			default: 
    				console.log('[User model fromXML]: Unrecognized element.');
    				break;
    			}
    		}
    		return element;
    	}
    },
    proxy:{
        type: 'rest',
        url: '/api/user',
        reader: {
        	type: 'json',
        	root: 'user'
        },
        writer: {
        	type: 'json',
        	root: 'user'
        }
    },
    getXML: function(){
    	var _xml = Ext.String.format("<user id='{0}' googleServicesActivated='{1}' email='{2}' nickname='{3}' appId='{4}' />", this.get('id'), this.get('googleServicesActivated'), this.get('email'), this.get('nickname'), this.get('appId'));
    	return _xml;
    },
    getModel: function(){
    	var _model = Ext.String.format('<user><id>{0}</id><googleServicesActivated>{1}</googleServicesActivated><email>{2}</email><nickname>{3}</nickname><appId>{4}</appId></user>', this.get('id'), this.get('googleServicesActivated'), this.get('email'), this.get('nickname'), this.get('appId'));
    	return _model;
    }
});