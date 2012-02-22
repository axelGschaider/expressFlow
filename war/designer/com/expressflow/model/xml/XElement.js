Ext.namespace('com.expressflow.model.xml');

Ext.define('com.expressflow.model.xml.XElement', {
    extend: 'Ext.data.Model',
    belongsTo: 'com.expressflow.model.xml.XProcess',
    fields: ['name', 'x', 'y']
});