package jmri.jmrit.logixng.actions.configurexml;

import java.util.List;

import jmri.InstanceManager;
import jmri.Light;
import jmri.LightManager;
import jmri.NamedBeanHandle;
import jmri.jmrit.logixng.DigitalActionManager;
import jmri.jmrit.logixng.SymbolTable;
import jmri.jmrit.logixng.actions.ActionListenOnBeans;
import jmri.jmrit.logixng.actions.ActionListenOnBeans.NamedBeanReference;
import jmri.jmrit.logixng.actions.ActionListenOnBeans.NamedBeanType;

import org.jdom2.Element;

/**
 * Handle XML configuration for ActionLightXml objects.
 *
 * @author Bob Jacobsen Copyright: Copyright (c) 2004, 2008, 2010
 * @author Daniel Bergqvist Copyright (C) 2019
 */
public class ActionListenOnBeansXml extends jmri.managers.configurexml.AbstractNamedBeanManagerConfigXML {

    public ActionListenOnBeansXml() {
    }
    
    /**
     * Default implementation for storing the contents of a SE8cSignalHead
     *
     * @param o Object to store, of type TripleLightSignalHead
     * @return Element containing the complete info
     */
    @Override
    public Element store(Object o) {
        ActionListenOnBeans p = (ActionListenOnBeans) o;

        Element element = new Element("action-listen-on-beans");
        element.setAttribute("class", this.getClass().getName());
        element.addContent(new Element("systemName").addContent(p.getSystemName()));
        
        storeCommon(p, element);
        
        Element parameters = new Element("references");
        for (NamedBeanReference ref : p.getReferences()) {
            Element elementParameter = new Element("reference");
            elementParameter.addContent(new Element("name").addContent(ref.getName()));
            elementParameter.addContent(new Element("type").addContent(ref.getType().name()));
            parameters.addContent(elementParameter);
        }
        element.addContent(parameters);
        
        return element;
    }
    
    @Override
    public boolean load(Element shared, Element perNode) {
        String sys = getSystemName(shared);
        String uname = getUserName(shared);
        ActionListenOnBeans h = new ActionListenOnBeans(sys, uname);

        loadCommon(h, shared);
        
        List<Element> parameterList = shared.getChild("references").getChildren();  // NOI18N
        log.debug("Found " + parameterList.size() + " references");  // NOI18N
        
        for (Element e : parameterList) {
            Element elementName = e.getChild("name");
            
            NamedBeanType type = null;
            Element elementType = e.getChild("type");
            if (elementType != null) {
                type = NamedBeanType.valueOf(elementType.getTextTrim());
            }
            
            if (elementName == null) throw new IllegalArgumentException("Element 'name' does not exists");
            if (type == null) throw new IllegalArgumentException("Element 'type' does not exists");
            
            h.addReference(new NamedBeanReference(elementName.getTextTrim(), type));
        }
        
        InstanceManager.getDefault(DigitalActionManager.class).registerAction(h);
        return true;
    }
    
    private final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ActionListenOnBeansXml.class);
}
