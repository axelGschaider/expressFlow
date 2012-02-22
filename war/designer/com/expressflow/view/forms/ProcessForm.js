Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.ProcessForm', {
    extend: 'Ext.form.Panel',
    title: 'Process',
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
        fieldLabel: 'Process Name'
    }],
    buttons: [{
        text: 'Save',
        handler: function(event) {
            var form = this.up('form').getForm();
            var processStore = Ext.getStore('com.expressflow.store.ProcessStore');
            var process = 'Empty';
            if(processStore.getAt(0)){
            	process = processStore.getAt(0);
            }
            process.set('name', form.findField('name').getValue());
        }
    }, {
        text: 'Reset',
        handler: function() {
            var form = this.up('form').getForm().reset();
        }
    }]
});