Ext.namespace('com.expressflow.designer');
Ext.define('com.expressflow.designer.DesignerArea', {
	extend: 'Ext.Component',
	onRender: function() {
		this.autoEl = Ext.apply({}, this.initialConfig, this.autoEl);
		this.callParent(arguments);
		paper.setup(this.autoEl.id);
		this.el.on('load', this.onLoad, this);
	},
	onLoad: function() {
		this.fireEvent('load', this);
	},
	addPath: function(){
		var myPath = new Path();
		myPath.strokeColor = 'black';
		myPath.add(new Point(0, 0));
		myPath.add(new Point(100, 50));
	}
});