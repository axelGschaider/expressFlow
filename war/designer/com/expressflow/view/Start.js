Ext.namespace('com.expressflow.view');

Ext.define('com.expressflow.view.Start',{
    extend: 'Ext.Img',
    requires: ['com.expressflow.model.Start'],
    model: 'init',
    config:{
        model: Ext.create('com.expressflow.model.Start')
    }
});