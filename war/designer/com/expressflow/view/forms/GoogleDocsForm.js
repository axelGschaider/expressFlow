Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.GoogleDocsForm', {
    extend: 'Ext.form.Panel',
    title: 'Google Documents Element',
    width: '100%',
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
    items: [{
        xtype: 'hidden',
        name: 'modelIndex'
    },
    {
        xtype: 'textfield',
        name: 'link',
        fieldLabel: 'Link',
        allowblank: false
    },
    {
        xtype: 'combobox',
        name: 'action',
        store: Ext.create('Ext.data.Store', {
            fields: ['action', 'name'],
            data : [
                {"action":"publish", "name":"publish"},
                {"action":"delete", "name":"delete"}
            ]
        }),
        queryMode: 'local',
        displayField: 'name',
        valueField: 'action',
        fieldLabel: 'Action',
        allowblank: false,
        listeners: {
        	'select': function(combo, record, options){
        		var form = this.up('form').getForm();
                var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
                var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
                model.set('action', combo.value);
        	}
        }
    },
    {
        xtype: 'textfield',
        name: 'email',
        fieldLabel: 'Email',
        allowblank: false
    }],
    buttons: [{
        text: 'Save',
        handler: function(event) {
            var form = this.up('form').getForm();
            var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
            var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
            model.set('link', form.findField('link').getValue());
            model.set('action', form.findField('action').getValue());
            model.set('email', form.findField('email').getValue());
        }
    }, {
        text: 'Reset',
        handler: function() {
            var form = this.up('form').getForm().reset();
        }
    }]
});