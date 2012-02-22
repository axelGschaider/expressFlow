Ext.namespace('com.expressflow.store');

Ext.define('com.expressflow.store.ElementsStore',{
    extend: 'Ext.data.Store',
    requires: 'com.expressflow.model.Element',
    model: 'com.expressflow.model.Element'
});