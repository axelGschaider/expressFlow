Ext.namespace('com.expressflow.store');

Ext.define('com.expressflow.store.MobileFormsStore',{
    extend: 'Ext.data.Store', 
    requires: 'com.expressflow.model.MobileForm',
    model: 'com.expressflow.model.MobileForm'
});