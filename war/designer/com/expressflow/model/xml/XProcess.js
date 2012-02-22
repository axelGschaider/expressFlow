Ext.namespace('com.expressflow.model.xml');
Ext.define('com.expressflow.model.xml.XProcess', {
extend: 'Ext.data.Model',
hasMany: 'com.expressflow.model.xml.XElement'
});
