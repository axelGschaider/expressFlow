Ext.namespace('com.expressflow.view');

Ext.define('com.expressflow.view.Annotate',{
    extend: 'Ext.Img',
    requires: ['com.expressflow.model.Annotate'],
    model: 'init',
    config:{
        model: Ext.create('com.expressflow.model.Annotate')
    }
});