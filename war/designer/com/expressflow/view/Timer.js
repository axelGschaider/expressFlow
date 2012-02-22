Ext.namespace('com.expressflow.view');

Ext.define('com.expressflow.view.Timer',{
    extend: 'Ext.Img',
    requires: ['com.expressflow.model.Timer'],
    model: 'init',
    config:{
        model: Ext.create('com.expressflow.model.Timer')
    }
});