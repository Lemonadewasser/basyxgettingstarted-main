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

    public List<String> smc_name; //number3

    public List<Integer> document_number;
    public List<List<String>> documentsID; //n*3, here is 2*3
    public List<List<String>> documentsClassID; //n*3, here is 2*3
    public List<List<String>> documentsClassName; //n*3, here is 2*3
    public List<List<Boolean>> documentsIsPrimary; //n*3, here is 2*3
    public List<List<String>> documentsClassificationSystem; //n*3, here is 2*3
    public List<List<String>> languages; //n*3, here is 2*3
    public List<List<String>> documentsVersionID; //n*3, here is 2*3
    public List<List<String>> titles; //n*3, here is 2*3

    public List<String> manufacturersName; //number 3
    public List<String> manufacturersLogo; //number 3
    public List<String> manufacturersProductDesignation; //number 3
    public List<String> manufacturersOrderCode; //number 3
    public List<String> manufacturersImage; //number 3

    public List<List<String>> classificationSystems; // size 2*3
    public List<List<String>> classificationSystemVersions; //size 2*3
    public List<List<String>> classIds; // size 2*3

    public List<Double> speed; //number 3
    public List<String> material_composition;//number 3
    public List<Double> acceleration;//number 3
    public List<String> pathOfLinkStructures;//number 3
    public List<Double> precision;//number 3
    public List<List<String>> constraints;//size 7*3

    public List<String> pathOfModel; // number 3
    public List<List<Double>> dimensions; // size 3*3
    public List<Double> weights; // number 3
    public List<Double> freedom; // number 3
    public List<String> positions; // number 3
    public List<String> orientations; // number 3

    public List<Integer> capability_number;
    public List<List<String>> capabilityNames;
    public List<List<String>> descriptions;

    public ShopFloorAAS_Resource(double value,
    List<String> smc_name,

    List<Integer> document_number,
    List<List<String>> documentsID, 
    List<List<String>> documentsClassID, 
    List<List<String>> documentsClassName, 
    List<List<Boolean>> documentsIsPrimary, 
    List<List<String>> documentsClassificationSystem,
    List<List<String>> languages, 
    List<List<String>> documentsVersionID, 
    List<List<String>> titles, 

    List<String> manufacturersName, 
    List<String> manufacturersLogo, 
    List<String> manufacturersProductDesignation,
    List<String> manufacturersOrderCode, 
    List<String> manufacturersImage, 

    List<List<String>> classificationSystems, 
    List<List<String>> classificationSystemVersions, 
    List<List<String>> classIds, 

    List<Double> speed,
    List<String> material_composition,
    List<Double> acceleration,
    List<String> pathOfLinkStructures,
    List<Double> precision,
    List<List<String>> constraints,

    List<String> pathOfModel, 
    List<List<Double>> dimensions, 
    List<Double> weights,
    List<Double> freedom,
    List<String> positions, 
    List<String> orientations, 
    
    List<Integer> capability_number,
    List<List<String>> capabilityNames, 
    List<List<String>> descriptions){
        this.value = value;
        this.smc_name = smc_name;

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

        this.speed = speed;
        this.material_composition = material_composition;
        this.acceleration = acceleration;
        this.pathOfLinkStructures = pathOfLinkStructures;
        this.precision = precision;
        this.constraints = constraints;

        this.pathOfModel = pathOfModel;
        this.dimensions = dimensions;
        this.weights = weights;
        this.freedom = freedom;
        this.positions = positions;
        this.orientations = orientations;

        this.capability_number = capability_number;
        this.capabilityNames = capabilityNames;
        this.descriptions = descriptions;
    }

    Submodel createResource(){
        // submodel with id short
        Submodel resourceSubmodel = new Submodel();
        resourceSubmodel.setIdShort("ResourceSubmodel");
        resourceSubmodel.setIdentification(new ModelUrn("ResourceSubmodel"));

        for(int smc_count:List.of(0,1,2)){
            // 1. SMC robot
            SubmodelElementCollection robot = new SubmodelElementCollection(smc_name.get(smc_count));

            //1.1 SMC Documentation
            SubmodelElementCollection documentation = new SubmodelElementCollection("Documentation");
            //  for different documents,from 01 to n
            
            for(int document_count : this.document_number){
                // set the document number
                int document_count1 = document_count+1;
                // 1.1.1 SMC document n
                SubmodelElementCollection documentNumber = new SubmodelElementCollection("Document"+document_count1);
                //    1.1.1.1 Property document id
                Property documentId = new Property();
                documentId.setIdShort("DocumentID");
                documentId.setValue(this.documentsID.get(smc_count).get(document_count));
                documentNumber.addSubmodelElement(documentId);
                //    1.1.1.2 Property document class id
                Property documentClassId = new Property();
                documentClassId.setIdShort("DocumentClassID");
                documentClassId.setValue(this.documentsClassID.get(smc_count).get(document_count));
                documentNumber.addSubmodelElement(documentClassId);
                //    1.1.1.3 Property document class name
                Property documentClassName = new Property();
                documentClassName.setIdShort("DocumentClassName");
                documentClassName.setValue(this.documentsClassName.get(smc_count).get(document_count));
                documentNumber.addSubmodelElement(documentClassName);
                //    1.1.1.4 Property is primary
                Property isPrimary = new Property();
                isPrimary.setIdShort("IsPrimary");
                isPrimary.setValue(this.documentsIsPrimary.get(smc_count).get(document_count));
                documentNumber.addSubmodelElement(isPrimary);
                //    1.1.1.5 Property document classification system
                Property documentClassificationSystem = new Property();
                documentClassificationSystem.setIdShort("DocumentClassificationSystem");
                documentClassificationSystem.setValue(this.documentsClassificationSystem.get(smc_count).get(document_count));
                documentNumber.addSubmodelElement(documentClassificationSystem);
                //    1.1.1.6 Property document version
                SubmodelElementCollection documentVersion = new SubmodelElementCollection("DocumentVersion");
                //      1.1.1.6.1 Property language
                Property language = new Property();
                language.setIdShort("Language");
                language.setValue(this.languages.get(smc_count).get(document_count));
                documentVersion.addSubmodelElement(language);
                //      1.1.1.6.2 Property document version id
                Property documentVersionId = new Property();
                documentVersionId.setIdShort("DocumentVersionId");
                documentVersionId.setValue(this.documentsVersionID.get(smc_count).get(document_count));
                documentVersion.addSubmodelElement(documentVersionId);
                //      1.1.1.6.3 Property title
                Property title = new Property();
                title.setIdShort("Title");
                title.setValue(this.titles.get(smc_count).get(document_count));
                documentVersion.addSubmodelElement(title);


                documentNumber.addSubmodelElement(documentVersion);

                documentation.addSubmodelElement(documentNumber);
            }

            robot.addSubmodelElement(documentation);

            //==================================================================================================

            //  1.2 SMC Technical information
            SubmodelElementCollection technicalInformation = new SubmodelElementCollection("TechnicalInforamtion");

            //    1.2.1 SMC General information
            SubmodelElementCollection generalInformation = new SubmodelElementCollection("GeneralInformation");
            //      1.2.1.1 Property manufacturer name
            Property manufacuturerName = new Property();
            manufacuturerName.setIdShort("ManufacturerName");
            manufacuturerName.setValue(this.manufacturersName.get(smc_count));
            generalInformation.addSubmodelElement(manufacuturerName);
            //      1.2.1.2 Property manufacturer Logo
            Property manufacuturerLogo = new Property();
            manufacuturerLogo.setIdShort("ManufacturerLogo");
            manufacuturerLogo.setValue(this.manufacturersLogo.get(smc_count));
            generalInformation.addSubmodelElement(manufacuturerLogo);
            //      1.2.1.3 Property manufacturer product designation
            Property manufacuturerProductDesignation = new Property();
            manufacuturerProductDesignation.setIdShort("ManufacturerProductDesignation");
            manufacuturerProductDesignation.setValue(this.manufacturersProductDesignation.get(smc_count));
            generalInformation.addSubmodelElement(manufacuturerProductDesignation);
            //      1.2.1.4 Property manufacturer order code
            Property manufacuturerOrderCode = new Property();
            manufacuturerOrderCode.setIdShort("ManufacturerOrderCode");
            manufacuturerOrderCode.setValue(this.manufacturersOrderCode.get(smc_count));
            generalInformation.addSubmodelElement(manufacuturerOrderCode);
            //      1.2.1.5 Property manufacturer image
            Property manufacuturerImage = new Property();
            manufacuturerImage.setIdShort("ManufacturerImage");
            manufacuturerImage.setValue(this.manufacturersImage.get(smc_count));
            generalInformation.addSubmodelElement(manufacuturerImage);

            technicalInformation.addSubmodelElement(generalInformation);

            //    1.2.2 SMC product classification
            SubmodelElementCollection resourceClassification = new SubmodelElementCollection("ResourceClassification");

            //      1.2.2.1 SMC product classification item 1
            SubmodelElementCollection resourceClassificationItem1 = new SubmodelElementCollection("ResourceClassificationItem1");
            //          1.2.2.1.1 Property product classification system 1
            Property resourceClassificationSystem1 = new Property();
            resourceClassificationSystem1.setIdShort("ProductClassificationSystem1");
            resourceClassificationSystem1.setValue(this.classificationSystems.get(smc_count).get(0));
            resourceClassificationItem1.addSubmodelElement(resourceClassificationSystem1);
            //          1.2.2.1.2 Property classification system version 1
            Property productClassificationSystemVersion1 = new Property();
            productClassificationSystemVersion1.setIdShort("ProductClassificationSystemVersion1");
            productClassificationSystemVersion1.setValue(this.classificationSystems.get(smc_count).get(0));
            resourceClassificationItem1.addSubmodelElement(productClassificationSystemVersion1);
            //          1.2.2.1.3 Property product class id 1
            Property productClassID1 = new Property();
            productClassID1.setIdShort("ProductClassID1");
            productClassID1.setValue(this.classificationSystems.get(smc_count).get(0));
            resourceClassificationItem1.addSubmodelElement(productClassID1);

            resourceClassification.addSubmodelElement(resourceClassificationItem1);

            //      1.2.2.2 SMC product classification item 2
            SubmodelElementCollection productClassificationItem2 = new SubmodelElementCollection("ProductClassificationItem2");
            //          1.2.2.2.1 Property product classification system 2
            Property productClassificationSystem2 = new Property();
            productClassificationSystem2.setIdShort("ProductClassificationSystem2");
            productClassificationSystem2.setValue(this.classificationSystems.get(smc_count).get(1));
            productClassificationItem2.addSubmodelElement(productClassificationSystem2);
            //          1.2.2.2.2 Property classification system version 2
            Property productClassificationSystemVersion2 = new Property();
            productClassificationSystemVersion2.setIdShort("ProductClassificationSystemVersion2");
            productClassificationSystemVersion2.setValue(this.classificationSystems.get(smc_count).get(1));
            productClassificationItem2.addSubmodelElement(productClassificationSystemVersion2);
            //          1.2.2.2.3 Property product class id 2
            Property productClassID2 = new Property();
            productClassID2.setIdShort("ProductClassID2");
            productClassID2.setValue(this.classificationSystems.get(smc_count).get(1));
            productClassificationItem2.addSubmodelElement(productClassID2);

            resourceClassification.addSubmodelElement(productClassificationItem2);


            technicalInformation.addSubmodelElement(resourceClassification);

            //    1.2.3 SMC technical properties
            SubmodelElementCollection technicalPro = new SubmodelElementCollection("TechnicalProperties");

            // 1.2.3.1 property speed
            Property Speed = new Property();
            Speed.setIdShort("Speed");
            Speed.setValue(this.speed.get(smc_count).toString()+"m/s");
            technicalPro.addSubmodelElement(Speed);
            // 1.2.3.2 property material composition
            Property materialComposition = new Property();
            materialComposition.setIdShort("MaterialComposition");
            materialComposition.setValue(this.material_composition.get(smc_count));
            technicalPro.addSubmodelElement(materialComposition);
            // 1.2.3.3 property acceleration
            Property Acceleration = new Property();
            Acceleration.setIdShort("Acceleration");
            Acceleration.setValue(this.acceleration.get(smc_count).toString()+"m/s^2");
            technicalPro.addSubmodelElement(Acceleration);
            // 1.2.3.4 property link structure
            Property linkstructure = new Property();
            linkstructure.setIdShort("Linkstructure");
            linkstructure.setValue(this.pathOfLinkStructures.get(smc_count));
            technicalPro.addSubmodelElement(linkstructure);
            // 1.2.3.5 property precision
            Property Precision = new Property();
            Precision.setIdShort("Precision");
            Precision.setValue(this.precision.get(smc_count).toString()+"mm");
            technicalPro.addSubmodelElement(Precision);

            // 1.2.3.6 smc constraints
            SubmodelElementCollection Constraints = new SubmodelElementCollection("Constraints");
            // 1.2.3.6.1 property power range
            Property powerRange = new Property();
            powerRange.setIdShort("PowerRange");
            powerRange.setValue(this.constraints.get(smc_count).get(0));
            Constraints.addSubmodelElement(powerRange);
            // 1.2.3.6.2 property speed range
            Property speedRange = new Property();
            speedRange.setIdShort("SpeedRange");
            speedRange.setValue(this.constraints.get(smc_count).get(1));
            Constraints.addSubmodelElement(speedRange);
            // 1.2.3.6.3 property reach
            Property reach = new Property();
            reach.setIdShort("Reach");
            reach.setValue(this.constraints.get(smc_count).get(2));
            Constraints.addSubmodelElement(reach);
            // 1.2.3.6.4 property applied force range
            Property forceRange = new Property();
            forceRange.setIdShort("AppliedForceRange");
            forceRange.setValue(this.constraints.get(smc_count).get(3));
            Constraints.addSubmodelElement(forceRange);
            // 1.2.3.6.5 property open range
            Property openRange = new Property();
            openRange.setIdShort("OpenRange");
            openRange.setValue(this.constraints.get(smc_count).get(4));
            Constraints.addSubmodelElement(openRange);
            // 1.2.3.6.6 property temperature range
            Property temperature = new Property();
            temperature.setIdShort("Temperature");
            temperature.setValue(this.constraints.get(smc_count).get(5));
            Constraints.addSubmodelElement(temperature);
            // 1.2.3.6.7 property load-bearing range
            Property loadRange = new Property();
            loadRange.setIdShort("LoadBearingRange");
            loadRange.setValue(this.constraints.get(smc_count).get(6));
            Constraints.addSubmodelElement(loadRange);
        
            technicalPro.addSubmodelElement(Constraints);
            technicalInformation.addSubmodelElement(technicalPro);

            robot.addSubmodelElement(technicalInformation);

            //  1.3 SMC Geometry information
            SubmodelElementCollection geometryInformation = new SubmodelElementCollection("GeometryInformation");
            //    1.3.1 Property 3d model
            Property robot3DModel = new Property();
            robot3DModel.setIdShort("threeDModel");
            robot3DModel.setValue(this.pathOfModel.get(smc_count));
            geometryInformation.addSubmodelElement(robot3DModel);
            //      1.3.2.1 Property height
            Property height = new Property();
            height.setIdShort("Height");
            height.setValue(this.dimensions.get(smc_count).get(0).toString()+"m");
            geometryInformation.addSubmodelElement(height);
            //      1.3.2.2 Property width
            Property width = new Property();
            width.setIdShort("Width");
            width.setValue(this.dimensions.get(smc_count).get(1).toString()+"m");
            geometryInformation.addSubmodelElement(width);
            //      1.3.2.3 Property length
            Property length = new Property();
            length.setIdShort("Length");
            length.setValue(this.dimensions.get(smc_count).get(2).toString()+"m");
            geometryInformation.addSubmodelElement(length);
            //      1.3.2.4 Property weight
            Property weight = new Property();
            weight.setIdShort("Weight");
            weight.setValue(this.weights.get(smc_count).toString()+"kg");
            geometryInformation.addSubmodelElement(weight);
            //      1.3.2.5 Property degrees of freedom
            Property degreesOfFreedom = new Property();
            degreesOfFreedom.setIdShort("DegreesOfFreedom");
            degreesOfFreedom.setValue(this.freedom.get(smc_count));
            geometryInformation.addSubmodelElement(degreesOfFreedom);
            // 1.3.2.6 installation position
            Property intallationPosition = new Property();
            intallationPosition.setIdShort("InstallationPosition");
            intallationPosition.setValue(this.positions.get(smc_count));
            geometryInformation.addSubmodelElement(intallationPosition);
            // 1.3.2.7 installation orientation
            Property intallationOrientation = new Property();
            intallationOrientation.setIdShort("InstallationOrientation");
            intallationOrientation.setValue(this.orientations.get(smc_count));
            geometryInformation.addSubmodelElement(intallationOrientation);

            robot.addSubmodelElement(geometryInformation);


            //  1.4 SMC Capability
            SubmodelElementCollection capability = new SubmodelElementCollection("Capability");
    
            for (int capability_count : this.capability_number) {
                int capability_count1 = capability_count + 1;

                SubmodelElementCollection capabilityCollection = new SubmodelElementCollection("Capability"+Integer.toString(capability_count1));

                // 1.4.1 Property Name
                Property capabilityName = new Property();
                capabilityName.setIdShort("Name");
                capabilityName.setValue(this.capabilityNames.get(smc_count).get(capability_count));
                capabilityCollection.addSubmodelElement(capabilityName);
                //    1.4.2 Property Description
                Property description = new Property();
                description.setIdShort("Description");
                description.setValue(this.descriptions.get(smc_count).get(capability_count));
                capabilityCollection.addSubmodelElement(description);

                capability.addSubmodelElement(capabilityCollection);
            }
           

            robot.addSubmodelElement(capability);


            //add all the SMC123 to the resource submodel
            resourceSubmodel.addSubmodelElement(robot);
        }


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
		Asset resourceAsset = createAsset("ShopFloorAAS_Resource");
		AssetAdministrationShell resourceShell = createAAS(resourceAsset, "ShopFloorAAS",
				"This is a resource AAS.");


        ShopFloorAAS_Resource generator = new ShopFloorAAS_Resource(0.0, 
        List.of("EndEffector","Robot", "Gripper"), 
        List.of(0,1), 
        List.of(List.of("11231","11232"),List.of("21231","21232"),List.of("31231","31232")),
        List.of(List.of("03-07","03-08"),List.of("04-07","04-08"),List.of("05-07","05-08")), 
        List.of(List.of("Operation manual","Operation manual"),List.of("Operation manual","Operation manual"),List.of("Operation manual","Operation manual")), 
        List.of(List.of(true,true),List.of(false,false),List.of(false,false)), 
        List.of(List.of("VDI2271","VDI2272"),List.of("VDI2273","VDI2274"),List.of("VDI2275","VDI2276")), 
        List.of(List.of("English","English"),List.of("Chinese","Chinese"),List.of("German","German")), 
        List.of(List.of("V1.3","V1.4"),List.of("V2.5","V2.6"),List.of("V3.8","V3.9")), 
        List.of(List.of("Robot operation manual","Robot operation manual"),List.of("Robot operation manual","Robot operation manual"),List.of("Robot operation manual","Robot operation manual")), 
        List.of("Bosch", "Bosch","Bosch"), 
        List.of("Link to a Logo image","Link to a logo image","Link to a Logo image"), 
        List.of("Industry Robot","Industry Robot","Industry Robot"), 
        List.of("ssdg-adfs01","ssdg-adfs01","ssdg-adfs01"), 
        List.of("Link to a robot image","Link to a robot image","Link to a robot image"), 
        List.of(List.of("ISO8373","ISO8373"),List.of("ISO8373","ISO8373"),List.of("ISO8373","ISO8373")), 
        List.of(List.of("V2023","V2023"),List.of("V2023","V2023"),List.of("V2023","V2023")), 
        List.of(List.of("C1","C1"),List.of("C1","C1"),List.of("C1","C1")), 
        List.of(0.5,2.3,0.8), 
        List.of("Aluminium alloys,steel","Aluminium alloys,steel","Aluminium alloys,steel"), 
        List.of(2.0,2.5,3.0), 
        List.of("link to a structure image","link to a structure image","link to a structure image"), 
        List.of(1.0,1.1,1.2), 
        List.of(List.of("100W to 150W","0m/s to 1m/s", "a link to a image show the work space", "0N to 50N","5 to 30cm","-5 to 50\u00B0C", "10 kg"),
        List.of("100W to 150W","0m/s to 1m/s", "a link to a image show the work space", "NA","NA","-5\u00B0C to 50\u00B0C", "10 kg"),
        List.of("100W to 150W","0m/s to 1m/s", "a link to a image show the work space", "0 to 50N","5 to 30cm","-5\u00B0C to 50\u00B0C", "10 kg")), 
        List.of("Link to STEP.document","Link to STEP.document","Link to STEP.document"), 
        List.of(List.of(1.0,2.0,3.0),List.of(2.1,3.2,4.3),List.of(1.5,1.2,1.3)), 
        List.of(400.0,300.0,450.0), 
        List.of(6.0,5.0,7.0), 
        List.of("Position A","Position A","Position A"), 
        List.of("Orientation A","Orientation A","Orientation A"), 
        List.of(0,1), 
        List.of(List.of("Pick and Place aa","Pick and Place ab"),List.of("Pick and Place ba","Pick and Place bb"),List.of("Pick and Place ca","Pick and Place cb")), 
        List.of(List.of("Description of pick and place","Description of pick and place"),List.of("Description of pick and place","Description of pick and place"),List.of("Description of pick and place","Description of pick and place")));

        // List.of(List.of(),List.of(),List.of())
		List<Submodel> submodels = generateAndRegisterSubmodels(resourceShell, generator);

		MultiSubmodelProvider fullProvider = getFullProvier(resourceShell, submodels);

		startServerWithInMemoryRegistry(fullProvider, resourceShell);

		startServerAndConnectToExternalRegistry(fullProvider, resourceShell);

		UseExternalServerAndRegistry(resourceShell, submodels);

	}

}
