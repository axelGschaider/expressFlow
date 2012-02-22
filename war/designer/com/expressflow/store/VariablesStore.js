Ext.namespace('com.expressflow.store');

Ext.define('com.expressflow.store.VariablesStore',{
    extend: 'Ext.data.Store',
    requires: 'com.expressflow.model.Variable',
    model: 'com.expressflow.model.Variable'
});