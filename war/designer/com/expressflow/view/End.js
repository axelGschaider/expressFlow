Ext.namespace('com.expressflow.view');

Ext.define('com.expressflow.view.End',{
    extend: 'Ext.Img',
    requires: ['com.expressflow.model.End'],
    model: 'init',
    config:{
        model: Ext.create('com.expressflow.model.End')
    }
});