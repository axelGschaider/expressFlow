Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.MobileFormContainer', {
	extend: 'Ext.form.Panel',
	name: 'Mobile Form',
	layout: 'hbox',
	autoscroll: true,
	items:[{
			xtype:'fieldset',
			id: 'mobileForm',
			columnWidth: 0.5,
			title: this.name,
			defaultType: 'textfield',
			defaults: {anchor: '100%'},
			layout: 'anchor',
			items :[{
		        		xtype: 'hidden',
		        		name: 'modelIndex'
		    		},
			        {
						fieldLabel: 'Name',
						name: 'variableName'
					}, 
					{
				        xtype: 'combobox',
				        name: 'variableType',
				        store: Ext.create('Ext.data.Store', {
				            fields: ['type', 'name'],
				            data : [
				                {"type":"string", "name":"String"},
				                {"type":"int", "name":"Integer"},
				                {"type":"date", "name":"Date"}
				            ]
				        }),
				        queryMode: 'local',
				        displayField: 'name',
				        valueField: 'type',
				        fieldLabel: 'Type',
				        allowblank: false,
				        listeners: {
				        	'select': function(combo, record, options){
				        		var form = this.up('form').getForm();
				                var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
				                var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
				                model.set('type', combo.value);
				        	}
				        }
				    }
					,
					{
			            xtype:'textfield',
			            fieldLabel: 'Default Value',
			            name: 'variableValue',
			            allowBlank: false
					},{
						xtype: 'button',
						text: 'Save',
						handler: function(){
							var form = this.up('form').getForm();
							var varName = form.findField('variableName').getValue();
							var varType = form.findField('variableType').getValue();
							var varValue = form.findField('variableValue').getValue();
							// Propagate the Variable to the MobileForm Model and save it in the store!
							var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
				            var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
				            var variableModel = Ext.create('com.expressflow.model.Variable');
				            variableModel.set('name', varName);
							variableModel.set('type', varType);
							variableModel.set('value', varValue);
							// var x = (model.variables().data.length + 1) * 100;
							variableModel.set('x', 100);
							variableModel.set('y', 10);
							model.variables().add(variableModel);
						}
					}]
			}
	       ]
})