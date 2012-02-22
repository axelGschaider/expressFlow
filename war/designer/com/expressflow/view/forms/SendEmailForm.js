Ext.namespace('com.expressflow.view.forms');

Ext.define('com.expressflow.view.forms.SendEmailForm', {
    extend: 'Ext.form.Panel',
    title: 'Send Email Element',
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
    /* {
    	 xtype: 'combobox',
         fieldLabel: 'State',
         name: 'state',
         store: this._variables,
         valueField: 'name',
         displayField: 'state',
         typeAhead: true,
         queryMode: 'local',
         emptyText: 'Select a state...'
    }, */
    {
        xtype: 'textfield',
        name: 'respond_to',
        fieldLabel: 'Respond to',
        allowblank: true
    },
    {
    	xtype: 'textfield',
        name: 'subject',
        fieldLabel: 'Subject',
        allowblank: false
    },
    {
        xtype: 'textfield',
        name: 'body',
        fieldLabel: 'Message',
        allowblank: false
    }],
    buttons: [{
        text: 'Save',
        handler: function(event) {
            var form = this.up('form').getForm();
            var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
            var userStore = Ext.getStore('com.expressflow.store.UserStore');
            var currentUser = userStore.data.items[0];
            var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
            model.set('to', form.findField('to').getValue());
            model.set('respond_to', form.findField('respond_to').getValue());
            model.set('subject', form.findField('subject').getValue());
            model.set('body', form.findField('body').getValue());
            model.set('from', 'notify@' + currentUser.data.appId + '.appspotmail.com');
            model.set('nickname', 'expressFlow Notifier');
        }
    }, {
        text: 'Reset',
        handler: function() {
            var form = this.up('form').getForm().reset();
        }
    }]
});