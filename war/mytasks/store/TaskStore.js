Ext.namespace('mytasks.store');

Ext.define('mytasks.store.TaskStore',{
    extend: 'Ext.data.Store',
    requires: 'mytasks.model.Task',
    model: 'mytasks.model.Task',
    proxy:{
    	type : 'rest',
    	url: '/api/task/',
		reader : {
			type : 'json',
			root : 'jsonNode'
		}
    },
    autoLoad: true
});