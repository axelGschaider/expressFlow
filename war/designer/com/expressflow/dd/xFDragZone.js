Ext.ns('com.expressflow.dd');

Ext.define('com.expressflow.dd.xFDragZone',{
    extend: 'Ext.dd.DragZone',
    ddGroup: 'group',
    scroll: false,
    ddel: document.createElement('img'),
    getDragData: function(e) {
        var target = Ext.get(e.getTarget());
        return {
            ddel:this.ddel, 
            item:target};
    } ,
    onInitDrag: function(e) {
        this.ddel.src = this.dragData.item.dom.src;
        this.ddel.model = com.expressflow.dd.DragElementHelper.getDDel(this.ddel.src);
        this.proxy.update(this.ddel);
    },
    getRepairXY: function(e, data) {
        return data.item.getXY();
    }
});