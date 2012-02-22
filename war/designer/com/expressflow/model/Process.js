Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.Process',{
	extend: 'Ext.data.Model',
    hasMany: { model: 'com.expressflow.model.Element', name: 'elements' } ,
    fields: [
        {name: 'id', type: 'String'},
        {name: 'access', type: 'String'},
        {name: 'accessDateView', type:'String'},
        {name: 'creator', type: 'String'},
        {name: 'creationDateView', type: 'String'},
        {name: 'description', type:'String'},
        {name: 'state', type: 'String'},
        {name: 'timesExecuted', type: 'int'},
        {name: 'xml', type: 'String'},
        {name: 'execXml', type: 'String'},
        {name: 'name', type: 'String'}
        ],
    proxy:{
        type: 'rest',
        url: '/api/process',
        reader: {
        	type: 'json',
        	root: 'entity'
        },
        writer: {
        	type: 'json',
        	root: 'entity'
        }
    },
    getXml: function(){
        var _xml = Ext.String.format('<process name="{0}" access="{1}" timesExecuted="{2}">', this.get('name'), this.get('access'), this.get('timesExecuted'));
        return _xml;
    }
}); 