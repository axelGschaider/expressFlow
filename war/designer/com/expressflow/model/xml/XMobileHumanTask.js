Ext.namespace('com.expressflow.model.xml');

Ext.define('com.expressflow.model.xml.XMobileHumanTask', {
    extend: 'com.expressflow.model.xml.XElement',
    fields: ['name', 'x', 'y'],
    belongsTo: 'com.expressflow.model.xml.XProcess'
});