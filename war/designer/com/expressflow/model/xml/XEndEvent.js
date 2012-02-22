Ext.namespace('com.expressflow.model.xml');

Ext.define('com.expressflow.model.xml.XEndEvent', {
    extend: 'com.expressflow.model.xml.XElement',
    fields: ['x', 'y'],
    belongsTo: 'com.expressflow.model.xml.XProcess'
});