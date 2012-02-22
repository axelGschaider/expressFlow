Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.Advice',{
    extend: 'Ext.data.Model',
    fields: [
             {name: 'id', type: 'String'},
             {name: 'title', type: 'String'},
             {name: 'message', type: 'String'}
             ]
});