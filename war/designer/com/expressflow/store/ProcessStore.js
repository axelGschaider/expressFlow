Ext.namespace('com.expressflow.store');

Ext.define('com.expressflow.store.ProcessStore',{
    extend: 'Ext.data.Store',
    requires: 'com.expressflow.model.Process',
    model: 'com.expressflow.model.Process'
});