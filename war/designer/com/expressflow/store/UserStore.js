Ext.namespace('com.expressflow.store');

Ext.define('com.expressflow.store.UserStore',{
    extend: 'Ext.data.Store',
    requires: 'com.expressflow.model.User',
    model: 'com.expressflow.model.User'
});