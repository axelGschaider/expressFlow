Ext.ns('com.expressflow.dd');

Ext.define('com.expressflow.dd.xFDDProxy',{
    extend: 'Ext.dd.DDProxy',
    startDrag: function(x,y){
        console.log('Drag started!');
    },
    endDrag: function(x,y){
        console.log('Drag ended!');
    }
});