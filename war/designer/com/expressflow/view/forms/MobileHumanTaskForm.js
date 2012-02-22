Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.MobileHumanTaskForm', {
    extend: 'Ext.form.Panel',
    title: 'Mobile Human Task',
    width: '100%',
    height: '100%',
    statics:{
    	_forms: new Array(),
    	addForm: function(form){
    		this._forms.push(form);
    	},
		getForm: function(index){
			return this._forms[index];
		},
		getFormsLength: function(){
			return this._forms.length;
		},
		popForm: function(){
			if(this._forms.length > 0){
				return this._forms.pop();
			}
		}
    },
    _model: 'Empty',
    forms: new Array(),
    setModel: function(model) {
        this._model = model;
    },
    getModel: function(){
    	return this._model;
    },
    items: [{
        xtype: 'hidden',
        name: 'modelIndex'
    },
    {
        xtype: 'textfield',
        name: 'name',
        fieldLabel: 'Name',
        allowblank: false
    },
    {
    	xtype: 'textfield',
    	name: 'description',
    	fieldLabel: 'Description',
    	allowblank: false
    },
    {
    	xtype: 'textfield',
    	name: 'recipient',
    	fieldLabel: 'Recipient'
    },
    {
    	xtype: 'textfield',
    	name: 'creator',
    	fieldLabel: 'Creator'
    },
    {
        xtype: 'combobox',
        name: 'visibility',
        store: Ext.create('Ext.data.Store', {
            fields: ['name', 'value'],
            data : [
                {"name":"Public", "value":"public"},
                {"name":"Private", "value":"private"}
            ]
        }),
        queryMode: 'local',
        displayField: 'name',
        valueField: 'value',
        fieldLabel: 'Visibility',
        allowblank: false,
        listeners: {
        	'select': function(combo, record, options){
        		var form = this.up('form').getForm();
                var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
                var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
                model.set('visibility', combo.value);
        	}
        }
    },
    {
    	xtype: 'button',
    	text: 'Save',
    	handler: function(){
    		 var form = this.up('form').getForm();
             var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
             var processStore = Ext.getStore('com.expressflow.store.ProcessStore');
             var currentProcess = processStore.data.items[0];
             
             var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
             
             var mht = Ext.create('com.expressflow.model.MobileHumanTask');
             mht.data.name = form.findField('name').getValue();
             mht.data.description = form.findField('description').getValue();
             mht.data.recipient = form.findField('recipient').getValue();
             mht.data.creator = form.findField('creator').getValue();
             mht.data.id = 123;
             mht.data.processId = currentProcess.data.id;
             mht.data.visibility = form.findField('visibility').getValue();
            
             
             // Init the process model
             mht.setProxy({
                 type: 'rest',
                 url: '/api/task'
             });
             
             mht.save({
            	 success: function(m){
            		 var modelHT = elementsStore.getAt(form.findField('modelIndex').getValue());
            		 modelHT.set('description', m.raw.task.description);
            		 modelHT.set('id', m.raw.task.id);
            		 modelHT.set('name', m.raw.task.name);
            		 modelHT.set('publicAppKey', m.raw.task.publicAppKey);
            		 modelHT.set('privateAppKey', m.raw.task.privateAppKey);
            		 modelHT.set('url', m.raw.task.url);
            		 modelHT.set('namespace', m.raw.task.namespace);
            		 modelHT.set('processId', m.raw.task.processId);
            		 modelHT.set('creator', m.raw.task.creator);
            		 modelHT.set('recipient', m.raw.task.recipient);
            		 modelHT.set('visibility', m.raw.task.visibility);
            		 // Variables Store
            		 var variablesStore = Ext.getStore('com.expressflow.store.VariablesStore');
                     variablesStore.each(function(variable){
                    	 modelHT.variables().add(variable);
                     });
            	 }
             });
             
    	}
    },
    {
    	xtype: 'button',
    	text: 'Reset',
    	handler: function(){
    		 var form = this.up('form').getForm().reset();
    	}
    }]
});