Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.SendSMSForm', {
    extend: 'Ext.form.Panel',
    title: 'Twilio Send SMS Element',
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
        name: 'to',
        fieldLabel: 'To',
        allowblank: false
    },
    {
        xtype: 'textfield',
        name: 'message',
        fieldLabel: 'Message',
        allowblank: false
    }],
    buttons: [{
        text: 'Save',
        handler: function(event) {
            var form = this.up('form').getForm();
            var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
            var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
            model.set('to', form.findField('to').getValue());
            model.set('message', form.findField('message').getValue());
        }
    }, {
        text: 'Reset',
        handler: function() {
            var form = this.up('form').getForm().reset();
        }
    }]
});