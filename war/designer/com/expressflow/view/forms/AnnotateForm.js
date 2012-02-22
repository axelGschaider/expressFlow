Ext.namespace('com.expressflow.view.forms')

Ext.define('com.expressflow.view.forms.AnnotateForm', {
    extend: 'Ext.form.Panel',
    title: 'Annotate',
    width: '100%',
    _model: 'Empty',
    setModel: function(model) {
        this._model = model;
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
        name: 'variable',
        fieldLabel: 'Variable',
        allowblank: false
    },
    {
        xtype: 'textfield',
        name: 'expr',
        fieldLabel: 'Expression',
        allowblank: false
    }],
    buttons: [{
        text: 'Save',
        handler: function() {
            var form = this.up('form').getForm();
            var mobileHumanTasksStore = Ext.getStore('com.expressflow.store.ElementsStore');
            var model = mobileHumanTasksStore.data.items[form.findField('modelIndex').getValue()];
            
            model.set('name', form.findField('name').getValue());
            model.set('variable', form.findField('variable').getValue());
            model.set('expr', form.findField('expr').getValue());
        }
    }, {
        text: 'Reset',
        handler: function() {
            var form = this.up('form').getForm().reset();
        }
    }]
});