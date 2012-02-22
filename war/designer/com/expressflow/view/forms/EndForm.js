Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.EndForm', {
    extend: 'Ext.form.Panel',
    title: 'Process End',
    width: '100%',
    _model: 'Empty',
    setModel: function(model) {
        this._model = model;
    },
    items: [{
        xtype: 'hidden',
        name: 'modelIndex'
    }]
});