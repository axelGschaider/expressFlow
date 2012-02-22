Ext.namespace('com.expressflow.designer');
Ext.define('com.expressflow.parser.Parser', {
    statics: {
    	parseXML: function(xmlStr, designArea){
    		var xml;
    		if (window.ActiveXObject) {         //IE
    		    xml = new ActiveXObject("Microsoft.XMLDOM");
    		    xml.async = "false";
    		    xml.loadXML(xmlStr);
    		} else {                             //Mozilla
    		    xml = new DOMParser().parseFromString(xmlStr, "text/xml");
    		}
    		for(var i=0; i<xml.childNodes.length; i++){
    			switch(xml.childNodes[i].nodeName){
    				case "process":
    					var parent = xml.childNodes[i];
    					for(var pc = 0; pc < parent.childNodes.length; pc++){
    						var child = this.parseNode(parent.childNodes[pc], designArea)
    					}
    					break;
    				default:
    					break;
    			}
    		}
    	},
    	parseNode: function(node, designArea){
    		var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
    		var element;
    		switch(node.nodeName){
    			case "endEvent":
    				element = com.expressflow.model.End.fromXML(node);
    				elementsStore.add(element);
    				var endImg = designArea.image(element.data.image, element.data.x, element.data.y, com.expressflow.model.End.imageWidth(), com.expressflow.model.End.imageHeight());
    				endImg.element = element;
    				this.addImage(endImg);
    				break;
    			case "gDoc":
    				element = com.expressflow.model.GoogleDocs.fromXML(node);
    				elementsStore.add(element);
    				var gDocImage = designArea.image(element.data.image, element.data.x, element.data.y, com.expressflow.model.GoogleDocs.imageWidth(), com.expressflow.model.GoogleDocs.imageHeight());
    				gDocImage.element = element;
    				this.addImage(gDocImage);
    				break;
    			case "mobileForm":
    				element = com.expressflow.model.MobileForm.fromXML(node);
    				elementsStore.add(element);
    				var mHTImg = designArea.image(element.data.image, element.data.x, element.data.y, com.expressflow.model.MobileForm.imageWidth(), com.expressflow.model.MobileForm.imageHeight());
    				mHTImg.element = element;
    				this.addImage(mHTImg);
    				if(node.childNodes.length > 0){
    					for(var pc = 0; pc < node.childNodes.length; pc++){
    						var child = this.parseNode(node.childNodes[pc], designArea)
    					}
    				}
    				break;
    			case "sendEmail":
    				element = com.expressflow.model.SendEmail.fromXML(node);
    				elementsStore.add(element);
    				var startImg = designArea.image(element.data.image, element.data.x, element.data.y, com.expressflow.model.SendEmail.imageWidth(), com.expressflow.model.SendEmail.imageHeight());
    				startImg.element = element;
    				this.addImage(startImg);
    				break;
    			case "sendSMS":
    				element = com.expressflow.model.SendSMS.fromXML(node);
    				elementsStore.add(element);
    				var startImg = designArea.image(element.data.image, element.data.x, element.data.y, com.expressflow.model.SendSMS.imageWidth(), com.expressflow.model.SendSMS.imageHeight());
    				startImg.element = element;
    				this.addImage(startImg);
    				break;
    			case "startEvent":
    				element = com.expressflow.model.Start.fromXML(node);
    				elementsStore.add(element);
    				var startImg = designArea.image(element.data.image, element.data.x, element.data.y, com.expressflow.model.Start.imageWidth(), com.expressflow.model.Start.imageHeight());
    				startImg.element = element;
    				this.addImage(startImg);
    				break;
    			case "timer":
    				element = com.expressflow.model.Timer.fromXML(node);
    				elementsStore.add(element);
    				var startImg = designArea.image(element.data.image, element.data.x, element.data.y, com.expressflow.model.Timer.imageWidth(), com.expressflow.model.Timer.imageHeight());
    				startImg.element = element;
    				this.addImage(startImg);
    				break;
    			case "urlShortener":
    				element = com.expressflow.model.UrlShortener.fromXML(node);
    				elementsStore.add(element);
    				var startImg = designArea.image(element.data.image, element.data.x, element.data.y, com.expressflow.model.UrlShortener.imageWidth(), com.expressflow.model.UrlShortener.imageHeight());
    				startImg.element = element;
    				this.addImage(startImg);
    				break;
    			case "variable":
    				element = com.expressflow.model.Variable.fromXML(node);
//    				var rect = designArea.rect(element.data.x, 10, 100, 60, 10);
//        			var text =  "Form\n\nVariable: " + element.data.name + "\nType: " + element.data.type + "\nValue: " + element.data.value;
//        			var text = designArea.text(element.data.x + 40, 40, text);
        			var varStore = Ext.getStore('com.expressflow.store.VariablesStore');
        			varStore.add(element);
    				break;
    			default:
    				console.log("Unrecognized element: " + node.nodeName);
    				break;
    		}
    		return element;
    	},
    	parseBase: function(element, xml){
    		for(var i=0; i < xml.childNodes.length; i++){
    			switch(xml.childNodes[i].nodeName){
    				case "x":
    					element.data.x = parseInt(xml.childNodes[i].firstChild.nodeValue);
    					break;
    				case "y":
    					element.data.y = parseInt(xml.childNodes[i].firstChild.nodeValue);
    					break;
    				case "name":
    					if(xml.childNodes[i].firstChild)
    						element.data.name = xml.childNodes[i].firstChild.nodeValue;
    					break;
    				default:
    					console.log("Unrecognized attribute: " + xml.childNodes[i].nodeName);
    					break;
    			}
    		}
    		element.data.xml = xml;
    		return element;
    	},
    	addImage: function(image){
    		var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
    		
            var elementIndex = elementsStore.data.length - 1;
            
            // Updating status bar
            var sb = Ext.getCmp('processStatus');
            sb.showBusy('Saving draft...');

            
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
    	}
    }
});