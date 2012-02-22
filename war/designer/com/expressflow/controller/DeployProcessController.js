Ext.namespace('com.expressflow.controller');
Ext.define('com.expressflow.controller.DeployProcessController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'#deployProcessItem' : {
				click : this.deployWizard
			}
		});
	},
	deployWizard : function() {
		var window = Ext.create('Ext.Window', {
			title : 'Deploy Process Wizard',
			width : 250,
			height : 130,
			constrain : true,
			maximizable : false,
			resizable: false,
			layout : 'fit',
			items : [{
				xtype: 'panel',
				width: '100%',
				items: [{
						xtype     : 'textareafield',
						id: 'descriptionArea',
			        	grow      : true,
			        	name      : 'description',
			        	fieldLabel: 'Process Description',
			        	value: 'Describe what actions your flow performs...',
			        	width: '100%'
					}],
				buttons:[
			    	{
			    		xtype: 'button',
			    		id: 'saveMhtButton',
			    		text: 'Save',
			    		handler: function() {
			    			var textarea = Ext.getCmp('descriptionArea');
			    			var processStore = Ext.getStore('com.expressflow.store.ProcessStore');
			    			var process = 'Empty';
			    	        if(processStore.getAt(0)){
			    	        	process = processStore.getAt(0);
			    	        	process.data.description = textarea.getValue();
			    	        	var designArea = Ext.getCmp('designerArea1');
			    	            designArea.fireEvent('saveProcess');
			    	            designArea.fireEvent('deployProcess');
			    	        }
			    		}
					},
					{
			    		xtype: 'button',
			    		text: 'Reset',
			    		handler: function(){
			    			var textarea = Ext.getCmp('descriptionArea');
			    			textarea.setValue('');
			    		}
				}]
			}]
		});
		window.show();
	}
});