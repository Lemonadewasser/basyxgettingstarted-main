package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.commons.math3.analysis.function.Max;
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

import AASExamples.PushAAStoServer;

public class ShopFloorAAS_Resource {

    private static final Logger logger = LoggerFactory.getLogger(ShopFloorAAS_Resource.class);

    // required objects for the properties
    public double value;

    public List<Integer> document_number;
    public List<String> documentsID;
    public List<String> documentsClassID;
    public List<String> documentsClassName;
    public List<Boolean> documentsIsPrimary;
    public List<String> documentsClassificationSystem;
    public List<String> languages;
    public List<String> documentsVersionID;
    public List<String> titles;

    public String manufacturersName;
    public String manufacturersLogo;
    public String manufacturersProductDesignation;
    public String manufacturersOrderCode;
    public String manufacturersImage;

    public List<String> classificationSystems;
    public List<String> classificationSystemVersions;
    public List<String> classIds;

    public String actuatorTypes;
    public double torques;
    public double forces;
    public String controlSystems;
    public double powerRequirements;
    public double speeds;
    public double accelerations;
    public double precisions;

    public String pathOfModel;
    public List<Double> dimensions;
    public double weights;
    public double freedom;
    public List<Double> positions;
    public List<Double> orientations;

    public String pathOfLinkStructures;
    public String jointTypes;
    public List<Double> axisOfRotations;
    public double gripperSizes;
    public double gripperOpeningWidths;
    public double grippingForces;
    public double fingerLengths;
    public int numberOfFingers;

    public List<Integer> capability_number;
    public List<String> capabilityNames;
    public List<String> descriptions;
    public List<Double> inputParameters;
    public List<Double> outputParameters;
    public List<String> supportedProcesses;

    public ShopFloorAAS_Resource(double value, 
    List<Integer> document_number, 
    List<String> documentsID, 
    List<String> documentsClassID, 
    List<String> documentsClassName, 
    List<Boolean> documentsIsPrimary, 
    List<String> documentsClassificationSystem,
    List<String> languages, 
    List<String> documentsVersionID, 
    List<String> titles, 
    String manufacturersName, 
    String manufacturersLogo, 
    String manufacturersProductDesignation,
    String manufacturersOrderCode, 
    String manufacturersImage, 
    List<String> classificationSystems, 
    List<String> classificationSystemVersions, 
    List<String> classIds, 
    String controlSystems, 
    double accelerations, 
    String actuatorTypes, 
    double torques, 
    double forces, 
    double powerRequirements,
    double speed,
    double precisions, 
    String pathOfModel, 
    List<Double> dimensions, 
    double weights,
    double freedom,
    List<Double> positions, 
    List<Double> orientations, 
    String pathOfLinkStructures, 
    String jointTypes,
    List<Double> axisOfRotations, 
    double gripperSizes, 
    double gripperOpeningWidths, 
    double grippingForces, 
    double fingerLengths, 
    int numberOfFingers, 
    List<Integer> capability_number, 
    List<String> capabilityNames, 
    List<String> descriptions, 
    List<Double> inputParameters, 
    List<Double> outputParameters, 
    List<String> supportedProcesses){
        this.value = value;

        this.document_number = document_number;
        this.documentsID = documentsID;
        this.documentsClassID = documentsClassID;
        this.documentsClassName = documentsClassName;
        this.documentsIsPrimary = documentsIsPrimary;
        this.documentsClassificationSystem = documentsClassificationSystem;
        this.languages = languages;
        this.documentsVersionID = documentsVersionID;
        this.titles = titles;

        this.manufacturersName = manufacturersName;
        this.manufacturersLogo = manufacturersLogo;
        this.manufacturersProductDesignation = manufacturersProductDesignation;
        this.manufacturersOrderCode = manufacturersOrderCode;
        this.manufacturersImage = manufacturersImage;

        this.classificationSystems = classificationSystems;
        this.classificationSystemVersions = classificationSystemVersions;
        this.classIds = classIds;

        this.controlSystems = controlSystems;
        this.accelerations = accelerations;
        this.actuatorTypes = actuatorTypes;
        this.torques = torques;
        this.forces = forces;
        this.powerRequirements = powerRequirements;
        this.speeds = speed;
        this.precisions = precisions;
        
        this.pathOfModel = pathOfModel;
        this.dimensions = dimensions;
        this.weights = weights;
        this.freedom = freedom;
        this.positions = positions;
        this.orientations = orientations;
        this.pathOfLinkStructures = pathOfLinkStructures;
        this.jointTypes = jointTypes;
        this.axisOfRotations = axisOfRotations;
        this.gripperSizes = gripperSizes;
        this.gripperOpeningWidths = gripperOpeningWidths;
        this.grippingForces = grippingForces;
        this.fingerLengths = fingerLengths;
        this.numberOfFingers = numberOfFingers;

        this.capability_number = capability_number;
        this.capabilityNames = capabilityNames;
        this.descriptions = descriptions;
        this.inputParameters = inputParameters;
        this.outputParameters = outputParameters;
        this.supportedProcesses = supportedProcesses;
    }

    Submodel createResource(){
        // submodel with id short
        Submodel resourceSubmodel = new Submodel();
        resourceSubmodel.setIdShort("ResourceSubmodel");
        resourceSubmodel.setIdentification(new ModelUrn("ResourceSubmodel"));

        // 1. SMC robot
        SubmodelElementCollection robot = new SubmodelElementCollection("Robot");

        //  1.1 SMC Documentation
        SubmodelElementCollection documentation = new SubmodelElementCollection("Documentation");
        
        //  for different documents,from 01 to n
        for(int document : this.document_number){
            // set the document number
            int count = document+1;
            // 1.1.1 SMC document n
            SubmodelElementCollection documentNumber = new SubmodelElementCollection("Document"+count);

            //    1.1.1.1 Property document id
            Property documentId = new Property();
            documentId.setIdShort("DocumentID");
            documentId.setValue(this.documentsID.get(document));
            documentNumber.addSubmodelElement(documentId);

            //    1.1.1.2 Property document class id
            Property documentClassId = new Property();
            documentClassId.setIdShort("DocumentClassID");
            documentClassId.setValue(this.documentsClassID.get(document));
            documentNumber.addSubmodelElement(documentClassId);

            //    1.1.1.3 Property document class name
            Property documentClassName = new Property();
            documentClassName.setIdShort("DocumentClassName");
            documentClassName.setValue(this.documentsClassName.get(document));
            documentNumber.addSubmodelElement(documentClassName);

            //    1.1.1.4 Property is primary
            Property isPrimary = new Property();
            isPrimary.setIdShort("IsPrimary");
            isPrimary.setValue(this.documentsIsPrimary.get(document));
            documentNumber.addSubmodelElement(isPrimary);

            //    1.1.1.5 Property document classification system
            Property documentClassificationSystem = new Property();
            documentClassificationSystem.setIdShort("DocumentClassificationSystem");
            documentClassificationSystem.setValue(this.documentsClassificationSystem.get(document));
            documentNumber.addSubmodelElement(documentClassificationSystem);

            //    1.1.1.6 Property document version
            SubmodelElementCollection documentVersion = new SubmodelElementCollection("DocumentVersion");

            //      1.1.1.6.1 Property language
            Property language = new Property();
            language.setIdShort("Language");
            language.setValue(this.languages.get(document));
            documentVersion.addSubmodelElement(language);

            //      1.1.1.6.2 Property document version id
            Property documentVersionId = new Property();
            documentVersionId.setIdShort("DocumentVersionId");
            documentVersionId.setValue(this.documentsVersionID.get(document));
            documentVersion.addSubmodelElement(documentVersionId);

            //      1.1.1.6.3 Property title
            Property title = new Property();
            title.setIdShort("Title");
            title.setValue(this.titles.get(document));
            documentVersion.addSubmodelElement(title);


            documentNumber.addSubmodelElement(documentVersion);

            documentation.addSubmodelElement(documentNumber);
        }

        robot.addSubmodelElement(documentation);

        //  1.2 SMC Technical information
        SubmodelElementCollection technicalInformation = new SubmodelElementCollection("TechnicalInforamtion");

        //    1.2.1 SMC General information
        SubmodelElementCollection generalInformation = new SubmodelElementCollection("GeneralInformation");

        //      1.2.1.1 Property manufacturer name
        Property manufacuturerName = new Property();
        manufacuturerName.setIdShort("ManufacturerName");
        manufacuturerName.setValue(this.manufacturersName);
        generalInformation.addSubmodelElement(manufacuturerName);

        //      1.2.1.2 Property manufacturer Logo
        Property manufacuturerLogo = new Property();
        manufacuturerLogo.setIdShort("ManufacturerLogo");
        manufacuturerLogo.setValue(this.manufacturersLogo);
        generalInformation.addSubmodelElement(manufacuturerLogo);

        //      1.2.1.3 Property manufacturer product designation
        Property manufacuturerProductDesignation = new Property();
        manufacuturerProductDesignation.setIdShort("ManufacturerProductDesignation");
        manufacuturerProductDesignation.setValue(this.manufacturersProductDesignation);
        generalInformation.addSubmodelElement(manufacuturerProductDesignation);

        //      1.2.1.4 Property manufacturer order code
        Property manufacuturerOrderCode = new Property();
        manufacuturerOrderCode.setIdShort("ManufacturerOrderCode");
        manufacuturerOrderCode.setValue(this.manufacturersOrderCode);
        generalInformation.addSubmodelElement(manufacuturerOrderCode);

        //      1.2.1.5 Property manufacturer image
        Property manufacuturerImage = new Property();
        manufacuturerImage.setIdShort("ManufacturerImage");
        manufacuturerImage.setValue(this.manufacturersImage);
        generalInformation.addSubmodelElement(manufacuturerImage);


        technicalInformation.addSubmodelElement(generalInformation);

        //    1.2.2 SMC product classification
        SubmodelElementCollection productClassification = new SubmodelElementCollection("ProductClassification");

        //      1.2.2.1 SMC product classification item 1
        SubmodelElementCollection productClassificationItem1 = new SubmodelElementCollection("ProductClassificationItem1");

        //          1.2.2.1.1 Property product classification system 1
        Property productClassificationSystem1 = new Property();
        productClassificationSystem1.setIdShort("ProductClassificationSystem1");
        productClassificationSystem1.setValue(this.classificationSystems.get(0));
        productClassificationItem1.addSubmodelElement(productClassificationSystem1);

        //          1.2.2.1.2 Property classification system version 1
        Property productClassificationSystemVersion1 = new Property();
        productClassificationSystemVersion1.setIdShort("ProductClassificationSystemVersion1");
        productClassificationSystemVersion1.setValue(this.classificationSystems.get(0));
        productClassificationItem1.addSubmodelElement(productClassificationSystemVersion1);

        //          1.2.2.1.3 Property product class id 1
        Property productClassID1 = new Property();
        productClassID1.setIdShort("ProductClassID1");
        productClassID1.setValue(this.classificationSystems.get(0));
        productClassificationItem1.addSubmodelElement(productClassID1);

        productClassification.addSubmodelElement(productClassificationItem1);

        //      1.2.2.2 SMC product classification item 2
        SubmodelElementCollection productClassificationItem2 = new SubmodelElementCollection("ProductClassificationItem2");

        //          1.2.2.2.1 Property product classification system 2
        Property productClassificationSystem2 = new Property();
        productClassificationSystem2.setIdShort("ProductClassificationSystem2");
        productClassificationSystem2.setValue(this.classificationSystems.get(1));
        productClassificationItem2.addSubmodelElement(productClassificationSystem2);

        //          1.2.2.2.2 Property classification system version 2
        Property productClassificationSystemVersion2 = new Property();
        productClassificationSystemVersion2.setIdShort("ProductClassificationSystemVersion2");
        productClassificationSystemVersion2.setValue(this.classificationSystems.get(1));
        productClassificationItem2.addSubmodelElement(productClassificationSystemVersion2);
        
        //          1.2.2.2.3 Property product class id 2
        Property productClassID2 = new Property();
        productClassID2.setIdShort("ProductClassID2");
        productClassID2.setValue(this.classificationSystems.get(1));
        productClassificationItem2.addSubmodelElement(productClassID2);

        productClassification.addSubmodelElement(productClassificationItem2);


        technicalInformation.addSubmodelElement(productClassification);


        //    1.2.3 SMC technical properties
        SubmodelElementCollection technicalPro = new SubmodelElementCollection("TechnicalProperties");

        // //      1.2.3.1 SMC motion control
        // SubmodelElementCollection motionControl = new SubmodelElementCollection("MotionControl");
        // //          1.2.3.1.1 Property max speed
        // Property maxSpeed = new Property();
        // maxSpeed.setIdShort("MaxSpeed");
        // maxSpeed.setValue(this.maxSpeeds);
        // motionControl.addSubmodelElement(maxSpeed);
        // //          1.2.3.1.2 Property control system
        // Property controlSystem = new Property();
        // controlSystem.setIdShort("ControlSystem");
        // controlSystem.setValue(this.controlSystems);
        // motionControl.addSubmodelElement(controlSystem);
        //          1.2.3.1.3 Property acceleration
        // Property acceleration = new Property();
        // acceleration.setIdShort("Acceleration");
        // acceleration.setValue(this.accelerations);
        // motionControl.addSubmodelElement(acceleration);

        // technicalPro.addSubmodelElement(motionControl);

        //      1.2.3.2 SMC actuator
        SubmodelElementCollection actuator = new SubmodelElementCollection("Actuator");
        //          1.2.3.2.1 Property actuator
        Property actuatorType = new Property();
        actuatorType.setIdShort("ActuatorType");
        actuatorType.setValue(this.actuatorTypes);
        actuator.addSubmodelElement(actuatorType);
        //          1.2.3.2.2 Property torque and force
        Property torqueAndForce = new Property();
        torqueAndForce.setIdShort("TorqueAndForce");
        torqueAndForce.setValue(Math.max(this.torques, this.forces));
        actuator.addSubmodelElement(torqueAndForce);

        technicalPro.addSubmodelElement(actuator);

        //          1.2.3.1.2 Property control system
        Property controlSystem = new Property();
        controlSystem.setIdShort("ControlSystem");
        controlSystem.setValue(controlSystems);
        technicalPro.addSubmodelElement(controlSystem);

        //      1.2.3.4 Property power requirements
        Property powerRequirements = new Property();
        powerRequirements.setIdShort("PowerRequirements");
        powerRequirements.setValue(this.powerRequirements);
        technicalPro.addSubmodelElement(powerRequirements);

        //      1.2.3.5 Property speed and acceleration
        Property speedAndAcceleration = new Property();
        speedAndAcceleration.setIdShort("SpeedAndAcceleration");
        speedAndAcceleration.setValue(Math.max(this.speeds, this.accelerations));
        technicalPro.addSubmodelElement(speedAndAcceleration);

        //      1.2.3.6 Property precision
        Property precision = new Property();
        precision.setIdShort("Precision");
        precision.setValue(this.precisions);
        technicalPro.addSubmodelElement(precision);

        technicalInformation.addSubmodelElement(technicalPro);

        robot.addSubmodelElement(technicalInformation);

    
        //  1.3 SMC Geometry information
        SubmodelElementCollection geometryInformation = new SubmodelElementCollection("GeometryInformation");

        //    1.3.1 Property 3d model
        Property robot3DModel = new Property();
        robot3DModel.setIdShort("Robot3DModel");
        robot3DModel.setValue(this.pathOfModel);
        geometryInformation.addSubmodelElement(robot3DModel);

        //    1.3.2 SMC dimensions
        SubmodelElementCollection dimensions = new SubmodelElementCollection("Dimensions");

        //      1.3.2.1 Property height
        Property height = new Property();
        height.setIdShort("Height");
        height.setValue(this.dimensions.get(0));
        dimensions.addSubmodelElement(height);

        //      1.3.2.2 Property width
        Property width = new Property();
        width.setIdShort("Width");
        width.setValue(this.dimensions.get(1));
        dimensions.addSubmodelElement(width);

        //      1.3.2.3 Property length
        Property length = new Property();
        length.setIdShort("Length");
        length.setValue(this.dimensions.get(2));
        dimensions.addSubmodelElement(length);

        //      1.3.2.4 Property weight
        Property weight = new Property();
        weight.setIdShort("Weight");
        weight.setValue(this.weights);
        dimensions.addSubmodelElement(weight);

        //      1.3.2.5 Property degrees of freedom
        Property degreesOfFreedom = new Property();
        degreesOfFreedom.setIdShort("DegreesOfFreedom");
        degreesOfFreedom.setValue(this.freedom);
        dimensions.addSubmodelElement(degreesOfFreedom);


        geometryInformation.addSubmodelElement(dimensions);
        //    1.3.3 SMC position
        SubmodelElementCollection position = new SubmodelElementCollection("Position");
        //    set x axis
        Property position_x = new Property();
        position_x.setIdShort("Position_x");
        position_x.setValue(this.positions.get(0));
        position.addSubmodelElement(position_x);
        //    set y axis
        Property position_y = new Property();
        position_y.setIdShort("Position_y");
        position_y.setValue(this.positions.get(1));
        position.addSubmodelElement(position_y);
        //    set z axis
        Property position_z = new Property();
        position_z.setIdShort("Position_z");
        position_z.setValue(this.positions.get(2));
        position.addSubmodelElement(position_z);

        generalInformation.addSubmodelElement(position);
        
        //    1.3.4 SMC orientation
        SubmodelElementCollection orientation = new SubmodelElementCollection("Orientation");
        //    set x axis
        Property orientation_x = new Property();
        orientation_x.setIdShort("Orientation_x");
        orientation_x.setValue(this.orientations.get(0));
        orientation.addSubmodelElement(orientation_x);
        //    set y axis
        Property orientation_y = new Property();
        orientation_y.setIdShort("Orientation_y");
        orientation_y.setValue(this.orientations.get(1));
        orientation.addSubmodelElement(orientation_y);
        //    set z axis
        Property orientation_z = new Property();
        orientation_z.setIdShort("Orientation_z");
        orientation_z.setValue(this.orientations.get(2));
        orientation.addSubmodelElement(orientation_z);

        generalInformation.addSubmodelElement(orientation);
        
        //    1.3.5 SMC configuration
        SubmodelElementCollection configuration = new SubmodelElementCollection("Configuration");

        //      1.3.5.1 Property Link Structure
        Property linkstructure = new Property();
        linkstructure.setIdShort("Linkstructure");
        linkstructure.setValue(this.pathOfLinkStructures);
        configuration.addSubmodelElement(linkstructure);

        //      1.3.5.2 Property Joint Type
        Property jointType = new Property();
        jointType.setIdShort("JointType");
        jointType.setValue(this.jointTypes);
        configuration.addSubmodelElement(jointType);

        //      1.3.5.3 Property Axis of Rotation
        SubmodelElementCollection rotation = new SubmodelElementCollection("AxisOfRotation");

        // rotation x
        Property rotation_x = new Property();
        rotation_x.setIdShort("Rotation_x");
        rotation_x.setValue(this.axisOfRotations.get(0));
        rotation.addSubmodelElement(rotation_x);
        // rotation y
        Property rotation_y = new Property();
        rotation_y.setIdShort("Rotation_y");
        rotation_y.setValue(this.axisOfRotations.get(1));
        rotation.addSubmodelElement(rotation_y);
        // rotation z
        Property rotation_z = new Property();
        rotation_z.setIdShort("Rotation_z");
        rotation_z.setValue(this.axisOfRotations.get(2));
        rotation.addSubmodelElement(rotation_z);

        configuration.addSubmodelElement(rotation);

        //      1.3.5.4 Property Gripper size
        Property gripperSize = new Property();
        gripperSize.setIdShort("GripperSize");
        gripperSize.setValue(this.gripperSizes);
        configuration.addSubmodelElement(gripperSize);

        //      1.3.5.5 Property gripper opening width
        Property gripperOpeningWidth = new Property();
        gripperOpeningWidth.setIdShort("GripperOpeningWidth");
        gripperOpeningWidth.setValue(this.gripperOpeningWidths);
        configuration.addSubmodelElement(gripperOpeningWidth);

        //      1.3.5.6 Property gripping force
        Property grippingForce = new Property();
        grippingForce.setIdShort("GrippingForce");
        grippingForce.setValue(this.grippingForces);
        configuration.addSubmodelElement(grippingForce);

        //      1.3.5.7 Property finger length
        Property fingerLength = new Property();
        fingerLength.setIdShort("FingerLength");
        fingerLength.setValue(this.fingerLengths);
        configuration.addSubmodelElement(fingerLength);

        //      1.3.5.8 Property number of fingers
        Property numberOfFinger = new Property();
        numberOfFinger.setIdShort("NumberOfFingers");
        numberOfFinger.setValue(this.numberOfFingers);
        configuration.addSubmodelElement(numberOfFinger);

        geometryInformation.addSubmodelElement(configuration);

        robot.addSubmodelElement(geometryInformation);


        //  1.4 SMC Capability
        SubmodelElementCollection capability = new SubmodelElementCollection("Capability");

        for (int number : capability_number) {
            int count = number + 1;

            SubmodelElementCollection capabilityCollection = new SubmodelElementCollection("Capability"+count);

            // 1.4.1 Property Name
            Property capabilityName = new Property();
            capabilityName.setIdShort("Name");
            capabilityName.setValue(this.capabilityNames.get(number));
            capabilityCollection.addSubmodelElement(capabilityName);

            //    1.4.2 Property Description
            Property description = new Property();
            description.setIdShort("Description");
            description.setValue(this.descriptions.get(number));
            capabilityCollection.addSubmodelElement(description);

            //    1.4.3 Property Input Parameter
            Property inputParameter = new Property();
            inputParameter.setIdShort("InputParameter");
            inputParameter.setValue(this.inputParameters.get(number));
            capabilityCollection.addSubmodelElement(inputParameter);

            //    1.4.4 Property Output Parameter
            Property outputParameter = new Property();
            outputParameter.setIdShort("OutputParameter");
            outputParameter.setValue(this.outputParameters.get(number));
            capabilityCollection.addSubmodelElement(outputParameter);

            //    1.4.5 Property Supported Process
            Property supportedProcess = new Property();
            supportedProcess.setIdShort("SuppoertedProcess");
            supportedProcess.setValue(this.supportedProcesses.get(number));
            capabilityCollection.addSubmodelElement(supportedProcess);

            capability.addSubmodelElement(capabilityCollection);
        }
           

        robot.addSubmodelElement(capability);

        
        // 2. SMC EndEffector
        SubmodelElementCollection endEffector = new SubmodelElementCollection("EndEffector");

        // 3. SMC Gripper
        SubmodelElementCollection gripper = new SubmodelElementCollection("Gripper");

        // add all the SMC123 to the resource submodel
        resourceSubmodel.addSubmodelElement(robot);
        resourceSubmodel.addSubmodelElement(endEffector);
        resourceSubmodel.addSubmodelElement(gripper);

        return resourceSubmodel;
    }

    public static Asset createAsset(String resourceIdentifier) {
		Asset asset = new Asset(resourceIdentifier, new CustomId(resourceIdentifier), AssetKind.INSTANCE);
		return asset;
	}

    public static AssetAdministrationShell createAAS(Asset resourceAsset, String AASIdentifier, String description) {
		// create resource asset and set aas
		AssetAdministrationShell resourceShell = new AssetAdministrationShell(AASIdentifier, new CustomId(AASIdentifier),
				resourceAsset);
		// create description for resource shell
		LangStrings descriptionResource = new LangStrings("english", description);
		resourceShell.setDescription(descriptionResource);
		return resourceShell;
	}

    public static List<Submodel> generateAndRegisterSubmodels(AssetAdministrationShell resourceShell,
			ShopFloorAAS_Resource generator) {
		// create Geometry, Costing and Process Submodel
		Submodel resourceSubmodel = generator.createResource();
		// Submodel costingSubmodel = generator.createCostingSubmodel();
		// Submodel processSubmodel = generator.createProcessSubmodel();

		// add submodels to AAS
		resourceShell.addSubmodel(resourceSubmodel);
		// productShell.addSubmodel(costingSubmodel);
		// productShell.addSubmodel(processSubmodel);

		// return List.of(geometrySubmodel, costingSubmodel, processSubmodel);
        return List.of(resourceSubmodel);
	}

    public static MultiSubmodelProvider getFullProvier(AssetAdministrationShell resourceShell,
			List<Submodel> submodels) {
		// create aas model provider for all submodels
		MultiSubmodelProvider fullProvider = new MultiSubmodelProvider();

		AASModelProvider aasProvider = new AASModelProvider(resourceShell);
		fullProvider.setAssetAdministrationShell(aasProvider);

		for (Submodel submodel : submodels) {
			SubmodelProvider submodelProvider = new SubmodelProvider(submodel);
			fullProvider.addSubmodel(submodelProvider);
		}
		return fullProvider;

	}


    public static void startServerWithInMemoryRegistry(MultiSubmodelProvider fullProvider,
		AssetAdministrationShell resourceShell) {
		// create aas server
		HttpServlet aasServlet = new VABHTTPInterface<IModelProvider>(fullProvider);
		logger.info("Created a servlet for the model");

		// create registry with provider and servlet
		IAASRegistry registry = new InMemoryRegistry();
		IModelProvider registryProvider = new AASRegistryModelProvider(registry);
		HttpServlet registryServlet = new VABHTTPInterface<IModelProvider>(registryProvider);
		logger.info("Created a registry servlet for the model");

		// Register the VAB model at the directory
		AASDescriptor aasDescriptor = new AASDescriptor(resourceShell, "http://localhost:4001/aasserver/shells/TestProductAAS/aas");
		registry.register(aasDescriptor);

		// Deploy the AAS on a HTTP server
		BaSyxContext context = new BaSyxContext("/aasserver", "", "localhost", 4001);
		context.addServletMapping("/shells/TestProductAAS/*", aasServlet);
		context.addServletMapping("/registry/*", registryServlet);
		BaSyxHTTPServer httpServer = new BaSyxHTTPServer(context);

		httpServer.start();
		logger.info("HTTP server started");
	}

    public static void startServerAndConnectToExternalRegistry(MultiSubmodelProvider fullProvier,
			AssetAdministrationShell resourcehell) {
		String host = "localhost";
		// String host = "193.196.37.23";

		String registryPath = "http://" + host + ":8082/registry/api/v1/registry";
		AASRegistryProxy registryProxy = new AASRegistryProxy(registryPath);
		AASDescriptor aasDescriptor = new AASDescriptor(resourcehell, "http://localhost:4001/aasserver/shells/TestProductAAS/aas");

		registryProxy.register(aasDescriptor);
	}

    public static void UseExternalServerAndRegistry(AssetAdministrationShell resourceShell, List<Submodel> submodels) {


		Map<AssetAdministrationShell, List<Submodel>> map = new HashMap<>();
		map.put(resourceShell, submodels);

		String host = "localhost";
		// String host = "193.196.37.23";

		PushAAStoServer.pushAAS(map, "http://" + host + ":8081/aasServer",
				"http://" + host + ":8082/registry/api/v1/registry");
	}


    public static void main(String[] args) throws Exception {

		// create product asset and set aas
		Asset resourceAsset = createAsset("TestResource");
		AssetAdministrationShell resourceShell = createAAS(resourceAsset, "TestResourceAAS",
				"This is a test resource AAS.");

		// create SimpleAAS object for easy storage of the product data for all
		// submodels
		ShopFloorAAS_Resource generator = new ShopFloorAAS_Resource(0.0, 
        List.of(0,1),
        List.of("12345", "23456"),
        List.of("03-02", "04-03"),
        List.of("Operation1", "Operation2"),
        List.of(true, false),
        List.of("VDI2770", "VDI2771"),
        List.of("en", "de"),
        List.of("V1.3", "V3.5"),
        List.of("path of doc", "path of doc2"),
        "ABC Company",
        "path of png",
        "ABC Ronot",
        "dfsdf-sdg",
        "path of jpg",
        List.of("Class1", "Class2"),
        List.of("8.0", "10.2"),
        List.of("21-02", "52-05"),
        "Automated",
        3,
        "Electric Motor",
        5.3,
        8.9,
        20.6,
        98.6,
        0.01,
        "path of .STEP",
        List.of(1.0,2.0,3.0),
        4.0,
        6.0,
        List.of(1.0,2.0,3.0),
        List.of(5.0,6.0,7.0),
        "path of jpg",
        "revolute",
        List.of(1.1,2.2,3.3),
        10.0,
        9.0,
        10.0,
        15.0,
        5,
        List.of(0,1,2),
        List.of("Moving1","Moving2","Moving3"),
        List.of("Moving1","Moving2","Moving3"),
        List.of(1.1,2.2,3.3),
        List.of(2.5,3.5,4.5),
        List.of("Task,Moving1","Task,Moving2","Task,Moving3"));

        
		List<Submodel> submodels = generateAndRegisterSubmodels(resourceShell, generator);

		MultiSubmodelProvider fullProvider = getFullProvier(resourceShell, submodels);

		startServerWithInMemoryRegistry(fullProvider, resourceShell);

		startServerAndConnectToExternalRegistry(fullProvider, resourceShell);

		UseExternalServerAndRegistry(resourceShell, submodels);

	}

}
