Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.ConditionForm', {
    extend: 'Ext.form.Panel',
    title: 'Process Start',
    width: '100%',
    _model: 'Empty',
    setModel: function(model) {
        this._model = model;
    },
    items: [{
        xtype: 'hidden',
        name: 'modelIndex'
    },{
        xtype: 'textfield',
        name: 'name',
        fieldLabel: 'Condition Name'
    },
    {
    	xtype: 'textfield',
    	name: 'expression',
    	fieldLabel: 'Expression',
    	allowblank: false
    }],
    buttons: [{
        text: 'Save',
        handler: function(event) {
            var form = this.up('form').getForm();
            var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
            var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
            model.set('name', form.findField('name').getValue());
            model.set('expression', form.findField('expression').getValue());
        }
    }, {
        text: 'Reset',
        handler: function() {
            var form = this.up('form').getForm().reset();
        }
    }]
});