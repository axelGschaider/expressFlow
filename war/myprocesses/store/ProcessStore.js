Ext.namespace('myprocesses.store');

Ext.define('myprocesses.store.ProcessStore',{
    extend: 'Ext.data.Store',
    requires: 'myprocesses.model.Process',
    model: 'myprocesses.model.Process',
    proxy:{
    	type : 'rest',
    	url: '/api/process/deploy/',
		reader : {
			type : 'json',
			root : 'jsonNode'
		}
    },
    autoLoad: true
});