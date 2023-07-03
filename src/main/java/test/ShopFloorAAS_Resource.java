package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopFloorAAS_Resource {

    private static final Logger logger = LoggerFactory.getLogger(ShopFloorAAS_Resource.class);

    // required objects for the properties
    public double value;

    public List<String> documentID;
    public List<String>  documentClassID;
    public List<String> documentClassName;
    public List<Boolean> isPrimary;
    public List<String> documentClassificationSystem;
    public List<String> language;
    public List<String> documentVersionID;
    public List<String> title;
    public List<String> manufactureName;
    public List<String> manufactureLogo;
    public List<String> manufactureProductDesignation;
    public List<String> manufactureOrderCode;
    public List<String> manufactureImage;

    public List<String> classificationSystem;
    public List<String> classificationSystemVersion;
    public List<String> classId;

    public List<Double> maxSpeed;
    public List<String> controlSystem;
    public List<Double> acceleration;
    public List<String> actuatorType;
    public List<Double> torque;
    public List<Double> force;
    public List<Double> powerRequirement;
    public List<Double> precision;

    public List<String> pathOfModel;
    public List<List<Double>> dimension;
    public List<String> position;
    public List<String> orientation;
    public List<String> pathOfLinkStructure;
    public List<String> jointType;
    public List<String> axisOfRotation;
    public List<Double> gripperSize;
    public List<Double> gripperOpeningWidth;
    public List<Double> grippingForce;
    public List<Double> fingerLength;
    public List<Integer> numberOfFingers;

    public List<String> name;
    public List<String> description;
    public List<String> inputParameters;
    public List<String> outputParameters;
    public List<String> supportedProcess;

    public ShopFloorAAS_Resource(double value, List<String> documentID, List<String>  documentClassID, List<String> documentClassName, List<Boolean> isPrimary, List<String> documentClassificationSystem,
    List<String> language, List<String> documentVersionID,List<String> title, List<String> manufactureName, List<String> manufactureLogo, List<String> manufactureProductDesignation,
    List<String> manufactureOrderCode, List<String> manufactureImage, List<String> classificationSystem, List<String> classificationSystemVersion, List<String> classId, List<Double> maxSpeed,
    List<String> controlSystem, List<Double> acceleration, List<String> actuatorType, List<Double> torque, List<Double> force, List<Double> powerRequirement,
    List<Double> precision, List<String> pathOfModel, List<List<Double>> dimension, List<String> position, List<String> orientation, List<String> pathOfLinkStructure, List<String> jointType,
    List<String> axisOfRotation, List<Double> gripperSize, List<Double> gripperOpeningWidth, List<Double> grippingForce, List<Double> fingerLength, List<Integer> numberOfFingers, List<String> name,
    List<String> description, List<String> inputParameters, List<String> outputParameters, List<String> supportedProcess){
        this.value = value;

        this.documentID = documentID;
        this.documentClassID = documentClassID;
        this.documentClassName = documentClassName;
        this.isPrimary = isPrimary;
        this.documentClassificationSystem = documentClassificationSystem;
        this.language = language;
        this.documentVersionID = documentVersionID;
        this.title = title;
        this.manufactureName = manufactureName;
        this.manufactureLogo = manufactureLogo;
        this.manufactureProductDesignation = manufactureProductDesignation;
        this.manufactureOrderCode = manufactureOrderCode;
        this.manufactureImage = manufactureImage;

        this.classificationSystem = classificationSystem;
        this.classificationSystemVersion = classificationSystemVersion;
        this.classId = classId;

        this.maxSpeed = maxSpeed;
        this.controlSystem = controlSystem;
        this.acceleration = acceleration;
        this.actuatorType = actuatorType;
        this.torque = torque;
        this.force = force;
        this.powerRequirement = powerRequirement;
        this.precision = precision;

        this.pathOfModel = pathOfModel;
        this.dimension = dimension;
        this.position = position;
        this.orientation = orientation;
        this.pathOfLinkStructure = pathOfLinkStructure;
        this.jointType = jointType;
        this.axisOfRotation = axisOfRotation;
        this.gripperSize = gripperSize;
        this.gripperOpeningWidth = gripperOpeningWidth;
        this.grippingForce = grippingForce;
        this.fingerLength = fingerLength;
        this.numberOfFingers = numberOfFingers;

        this.name = name;
        this.description = description;
        this.inputParameters = inputParameters;
        this.outputParameters = outputParameters;
        this.supportedProcess = supportedProcess;
    }

    Submodel createResource(){
        // submodel with id short
        Submodel resourceSubmodel = new Submodel();
        resourceSubmodel.setIdShort("ResourceSubmodel");
        resourceSubmodel.setIdentification(new ModelUrn("ResourceSubmodel"));


        SubmodelElementCollection robot


        return resourceSubmodel;
    }
    
}
