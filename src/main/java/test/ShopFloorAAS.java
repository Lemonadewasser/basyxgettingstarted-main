package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.eclipse.basyx.aas.metamodel.api.parts.asset.AssetKind;
import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.descriptor.AASDescriptor;
import org.eclipse.basyx.aas.metamodel.map.descriptor.CustomId;
import org.eclipse.basyx.aas.metamodel.map.descriptor.ModelUrn;
import org.eclipse.basyx.aas.metamodel.map.parts.Asset;
import org.eclipse.basyx.aas.registration.api.IAASRegistry;
import org.eclipse.basyx.aas.registration.memory.InMemoryRegistry;
import org.eclipse.basyx.aas.registration.proxy.AASRegistryProxy;
import org.eclipse.basyx.aas.registration.restapi.AASRegistryModelProvider;
import org.eclipse.basyx.aas.restapi.AASModelProvider;
import org.eclipse.basyx.aas.restapi.MultiSubmodelProvider;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.restapi.SubmodelProvider;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;
import org.eclipse.basyx.vab.protocol.http.server.BaSyxContext;
import org.eclipse.basyx.vab.protocol.http.server.BaSyxHTTPServer;
import org.eclipse.basyx.vab.protocol.http.server.VABHTTPInterface;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import AASExamples.ProductAAS;

public class ShopFloorAAS {

    private static final Logger logger = LoggerFactory.getLogger(ShopFloorAAS.class);

    // required objects for the properties
    public double value;

    public ShopFloorAAS(double value){

    }

    public Submodel createTechnicalData() {
        // submodel with id short
        Submodel technicalDataSubmodel = new Submodel();
        technicalDataSubmodel.setIdShort("TechnicalDataSubmodel");
        technicalDataSubmodel.setIdentification(null);//identification need to be written

        // SMC general information
        SubmodelElementCollection GeneralInformation = new SubmodelElementCollection("Manufacturer");

        Property ManufacturerName = new Property();
        GeneralInformation.addSubmodelElement(ManufacturerName);

        // SMC product classification


        // SMC techinical properties


        // SMC further Information


        return technicalDataSubmodel;
    }
    
}
