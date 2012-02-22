Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.Element',{
    extend: 'Ext.data.Model',
    belongsTo: 'com.expressflow.model.Process',
    fields: [
        {name: 'id', type: 'String'},
        {name: 'name', type: 'String'},
        {name: 'x', type: 'int'},
        {name: 'y', type: 'int'},
        {name: 'image', type: 'Object'},
        {name: 'xml', type: 'String'}
        ]
});