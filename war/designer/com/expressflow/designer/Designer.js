Ext.namespace('com.expressflow.designer');
Ext.define('com.expressflow.designer.Designer', {
    statics: {
        _connections: new Array(),
        _elements: new Array(),
        _variables: new Array(),
        _designArea: 'init',
        setDesignArea: function(area) {
            _designArea = area;
        },
        getDesignArea: function(){
        	return this._designArea;
        },
        getElement: function(elementIndex){
            return this._elements[elementIndex];  
        },
        setElementModel: function(elementIndex, model){
          this._elements[elementIndex].model = model;  
        },
        getDesignArea: function() {
            return _designArea;
        }, 
        getConnections: function() {
            return this._connections;
        }, 
        resetConnections: function(){
        	this._connections = new Array();
        },
        resetElements: function(){
        	this._elements = new Array();
        },
        addElement: function(element) {
            this._elements.push(element);
            if (this._elements.length > 1) {
                var l = this._elements.length;
                this._connections.push(this.connection(this._elements[l - 2], this._elements[l - 1], "#000"));
            }
            return this._elements.length - 1;
        },
        addElements: function(element){
        	this._elements.push(element);
        	if(this._elements.length > 1){
        		// Draw connections to more than one elemtens (like in condition etc.)
        	}
        },
        removeElement: function(element) {
            var index = Ext.Array.indexOf(this._elements, element);
            // Ext.Array.remove(this._connections, this.connection(this._elements[index-1], this._elements[index], "#000"));
            Ext.Array.remove(this._elements, element);
            // Manage Connections and Redraw!!!
        },
        addVariable: function(variable){
        	this._variables.push(variable);
        },
        removeVariable: function(variable){
        	var index = Ext.Array.indexOf(this._variables, variable);
        	Ext.Array.remove(this._variables, variable);
        },
        resetVariables: function() {
        	Ext.each(this._variables, function(variable, index){
        		this.removeVariable(variable);
        		variable.remove();
        	});
        },
        getElementsXml: function() {
            var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
            var _xml = '<process>';
            for (var index = 0; index < elementsStore.data.length; index++){
                var el = elementsStore.data.items[index];
                _xml += el.getXml();
            }
            _xml += '</process>';
            _xml = Ext.String.htmlEncode(_xml);
            return _xml;
        },
        drawVariables: function(){
        	var variablesStore = Ext.getStore('com.expressflow.store.VariablesStore');
        	var index = 1;
        	variablesStore.data.each(function(variable){
        		// Draw a variable on the Design Area
    			var designArea = com.expressflow.designer.Designer.getDesignArea();
    			var rect = designArea.rect(index * 100, 10, 100, 60, 10);
    			var text =  "Variable: " + variable.data.name + "\nType: " + variable.data.type;
    			var text = designArea.text((index * 100) + 40, 40, text);
    			index++;
        	});
        },
        connection: function(obj1, obj2, line, bg) {
            if (obj1.line && obj1.from && obj1.to) {
                line = obj1;
                obj1 = line.from;
                obj2 = line.to;
            }
            var bb1 = obj1.getBBox(),
                bb2 = obj2.getBBox(),
                p = [{
                    x: bb1.x + bb1.width / 2,
                    y: bb1.y - 1
                }, {
                    x: bb1.x + bb1.width / 2,
                    y: bb1.y + bb1.height + 1
                }, {
                    x: bb1.x - 1,
                    y: bb1.y + bb1.height / 2
                }, {
                    x: bb1.x + bb1.width + 1,
                    y: bb1.y + bb1.height / 2
                }, {
                    x: bb2.x + bb2.width / 2,
                    y: bb2.y - 1
                }, {
                    x: bb2.x + bb2.width / 2,
                    y: bb2.y + bb2.height + 1
                }, {
                    x: bb2.x - 1,
                    y: bb2.y + bb2.height / 2
                }, {
                    x: bb2.x + bb2.width + 1,
                    y: bb2.y + bb2.height / 2
                }],
                d = {},
                dis = [];
            for (var i = 0; i < 4; i++) {
                for (var j = 4; j < 8; j++) {
                    var dx = Math.abs(p[i].x - p[j].x),
                        dy = Math.abs(p[i].y - p[j].y);
                    if ((i == j - 4) || (((i != 3 && j != 6) || p[i].x < p[j].x) && ((i != 2 && j != 7) || p[i].x > p[j].x) && ((i != 0 && j != 5) || p[i].y > p[j].y) && ((i != 1 && j != 4) || p[i].y < p[j].y))) {
                        dis.push(dx + dy);
                        d[dis[dis.length - 1]] = [i, j];
                    }
                }
            }
            if (dis.length == 0) {
                var res = [0, 4];
            }
            else {
                res = d[Math.min.apply(Math, dis)];
            }
            var x1 = p[res[0]].x,
                y1 = p[res[0]].y,
                x4 = p[res[1]].x,
                y4 = p[res[1]].y;
            dx = Math.max(Math.abs(x1 - x4) / 2, 10);
            dy = Math.max(Math.abs(y1 - y4) / 2, 10);
            var x2 = [x1, x1, x1 - dx, x1 + dx][res[0]].toFixed(3),
                y2 = [y1 - dy, y1 + dy, y1, y1][res[0]].toFixed(3),
                x3 = [0, 0, 0, 0, x4, x4, x4 - dx, x4 + dx][res[1]].toFixed(3),
                y3 = [0, 0, 0, 0, y1 + dy, y1 - dy, y4, y4][res[1]].toFixed(3);
            var path = ["M", x1.toFixed(3), y1.toFixed(3), "C", x2, y2, x3, y3, x4.toFixed(3), y4.toFixed(3)].join(",");
            if (line && line.line) {
                line.bg && line.bg.attr({
                    path: path
                });
                line.line.attr({
                    path: path
                });
            }
            else {
                var color = typeof line == "string" ? line : "#000";
                return {
                    bg: bg && bg.split && this.path(path).attr({
                        stroke: bg.split("|")[0],
                        fill: "none",
                        "stroke-width": bg.split("|")[1] || 3
                    }),
                    line: _designArea.path(path).attr({
                        stroke: color,
                        fill: "none"
                    }),
                    from: obj1,
                    to: obj2
                };
            }
        } 
    }
});