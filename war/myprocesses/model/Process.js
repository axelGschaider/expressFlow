Ext.namespace('myprocesses.model');

Ext.define('myprocesses.model.Process',{
	extend: 'Ext.data.Model',
	fields: [
	         {name: 'name', type: 'String'},
	         {name: 'description', type: 'String'},
	         {name: 'url', type: 'String'}]
});