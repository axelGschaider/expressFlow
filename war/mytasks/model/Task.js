Ext.namespace('mytasks.model');

Ext.define('mytasks.model.Task',{
	extend: 'Ext.data.Model',
	fields: [
	         {name: 'name', type: 'String'},
	         {name: 'description', type: 'String'},
	         {name: 'url', type: 'String'}]
});