Ext.ns('com.expressflow.dd');

Ext.define('com.expressflow.dd.xFDropTarget', {
	id: 'xFDropTarget',
    extend: 'Ext.dd.DropTarget',
    ddGroup: 'group',
    designArea: 'init',
    getDesignArea: function(){
    	return this.designArea;
    },
    designer: 'init',
    _connections: new Array(),
    _elements: new Array(),
    scroll: false,
    constructor: function(name) {
        this.designArea = Raphael(name, document.getElementById(name).width, document.getElementById(name).height);
        // Init Variables
        var variables = this.designArea.text(40, 40, "Variables: ");
        com.expressflow.designer.Designer.setDesignArea(this.designArea);
        this.superclass.constructor.call(this, name);
    },
    notifyDrop: function(dd, e, data) {
        // UI part
    	var x = e.xy[0] - 255;
        var y = e.xy[1] - 80;
        var imgSource = data.item.dom.src;
        var image = this.designArea.image(imgSource, x, y, document.getElementById(data.item.id).width, document.getElementById(data.item.id).height);
        // Adding the new element to the store
        var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
        data.ddel.model.data.image = image;
        data.ddel.model.data.image.element = data.ddel.model;
        elementsStore.add(data.ddel.model);
        var elementIndex = elementsStore.data.length - 1;
        
        // Updating status bar
        var sb = Ext.getCmp('processStatus');
        sb.showBusy('Saving draft...');
        
        // Dispatch event to parent component
        var designArea = Ext.getCmp('designerArea1');
        designArea.fireEvent('saveProcess');
        
        // Hook in the DropDispatcher
        com.expressflow.dd.DragElementHelper.dropTargetDispatcher(data.ddel.model);
        
        // Setting up listeners on design area
        var start = function() {
                // storing original coordinates
                this.ox = this.attr("x");
                this.oy = this.attr("y");
                this.attr({
                    opacity: .5
                });
            },
            move = function(dx, dy) {
                // move will be called with dx and dy
                this.attr({
                    x: this.ox + dx,
                    y: this.oy + dy
                });
                this.element.data.x = this.ox + dx;
                this.element.data.y = this.oy + dy;
                var conArray = com.expressflow.designer.Designer.getConnections();
                for (var i = conArray.length; i--;) {
                   com.expressflow.designer.Designer.connection(conArray[i]);
                }
                com.expressflow.designer.Designer.getDesignArea().safari();
            },
            up = function() {
                // restoring state
                this.attr({
                    opacity: 1
                });
            };
        image.drag(move, start, up);
        var designerIndex = com.expressflow.designer.Designer.addElement(image);
        
        image.click(function(event){
            var clickHandler = Ext.create('com.expressflow.dd.ClickHandler');
            clickHandler.handle(elementIndex);
        });
        
        // Define the simple context menu ...
        var contextMenu = new Ext.menu.Menu({
            clickedElement: 'init',
            items:[
                {
                    text: 'Edit'
                },
                {
                    text: 'Delete',
                    listeners:{
                        click: function(event){
                            this.parentMenu.clickedElement.remove(); 
                            com.expressflow.designer.Designer.removeElement(this.parentMenu.clickedElement);
                            this.parentMenu.remove();
                        }
                    }
                }
                ]
        }); 
        // ... and register the right-click mouse event to it 
        image.mouseup(function(event) {
            var rightclick;
            if (!event) var e = window.event;
    	    if (event.which) rightclick = (event.which == 3);
	        else if (event.button) rightclick = (event.button == 2);
            if(rightclick){
                contextMenu.showAt(event.clientX, event.clientY);
                contextMenu.clickedElement = this;
            }
        });
        
        return true;
    }
});