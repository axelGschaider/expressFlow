Ext.namespace('com.expressflow.controller');
Ext.define('com.expressflow.controller.ManageProcessesController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'#manageProcessesItem' : {
				click : this.manageProcesses
			}
		});
	},
	manageProcesses : function() {
		
		Ext.create('Ext.data.Store', {
			storeId : 'manageProcessStore',
			autoLoad: true,
			fields : [ 'id', 'creationDateView', 'state', 'accessDateView', 'creator', 'xml', 'name' ],
			proxy : {
				type : 'rest',
				url: '/api/process/',
				reader : {
					type : 'json',
					root : 'processList'
				}
			}
		});

		var window = Ext.create('Ext.Window', {
			title : 'Manage Your Processes',
			width : 600,
			height : 400,
			constrain : true,
			maximizable : true,
			layout : 'fit',
			items : [ Ext.create('Ext.grid.Panel', {
				id: 'manageProcessesGrid',
				title : 'My processes',
				store : Ext.data.StoreManager.lookup('manageProcessStore'),
				columns : [
				{
					header: 'Name',
					dataIndex: 'name',
					width: 130
				},
				{
					header : 'Creation date',
					dataIndex : 'creationDateView',
					width: 100
				}, {
					header : 'Status',
					dataIndex : 'state',
					width: 100
				}, {
					header : 'Access',
					dataIndex : 'accessDateView',
					width: 100
				},
				{
					header: 'Creator',
					dataIndex: 'creator',
					width: 120
				}],
				renderTo : Ext.getBody()
			}),{
				xtype: 'button',
				
			} ]
		});
		window.show();
	}
});