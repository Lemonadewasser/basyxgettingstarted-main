package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import AASExamples.ProductAAS;
import AASExamples.PushAAStoServer;

//import AASExamples.ProductAAS;

public class ShopFloorAAS_Product {

    private static final Logger logger = LoggerFactory.getLogger(ShopFloorAAS_Product.class);

    // required objects for the properties
    public double value;
    public List<String> classificationSystems;
    public List<Double> classificationSystemVersions;
    public List<String> classIds;

    public String material;
    public double meltingpoint;
    public double density;
    public double deformability;
    public List<Double> dimensions;
    public String pathOfModel;
    public List<Integer> numberOfCapability;
    public List<String> name;
    public List<String> description;
    // public double inputParameters;
    // public double outputParameters;
    // public String supportedTask;



    public ShopFloorAAS_Product(double value, List<String> classificationSystems, 
    List<Double> classificationSystemVersions, List<String> classIds, 
    String material, double meltingpoint, double density, double deformability, List<Double> dimensions, 
    String pathOfModel, List<Integer> numberOfCapability, List<String> name, List<String> description){
        this.value = value;
        this.classificationSystems = classificationSystems;
        this.classificationSystemVersions = classificationSystemVersions;
        this.classIds = classIds;
        this.material = material;
        this.meltingpoint = meltingpoint;
        this.density = density;
        this.deformability = deformability;
        this.dimensions = dimensions;
        this.pathOfModel = pathOfModel;
        this.numberOfCapability = numberOfCapability;
        this.name = name;
        this.description = description;
        // this.inputParameters = inputParameters;
        // this.outputParameters = outputParameters;
        // this.supportedTask = supportedTask;
    }

    public Submodel createProduct() {
        // submodel with id short
        Submodel productSubmodel = new Submodel();
        productSubmodel.setIdShort("ProductSubmodel");
        productSubmodel.setIdentification(new ModelUrn("ProductSubmodel"));//TODO: identification need to be written


        // 1.SMC product classification
        SubmodelElementCollection productClassificationSubmodelCollection = new SubmodelElementCollection("ProductClassification");

        // all properties in product classification

        //1.1 SMC product classification item 1
        SubmodelElementCollection productClassificationItem1 = new SubmodelElementCollection("ProductClassificationItem1");
        // 1.1.1 product classification system 1
        Property productClassificationSystem1 = new Property();
        productClassificationSystem1.setIdShort("ProductClassificationSystem1");
        productClassificationSystem1.setValue(this.classificationSystems.get(0));
        productClassificationItem1.addSubmodelElement(productClassificationSystem1);
        // 1.1.2 classification system version 1
        Property productClassificationSystemVersion1 = new Property();
        productClassificationSystemVersion1.setIdShort("ProductClassificationSystemVersion1");
        productClassificationSystemVersion1.setValue(this.classificationSystemVersions.get(0));
        productClassificationItem1.addSubmodelElement(productClassificationSystemVersion1);
        // 1.1.3 product class id 1
        Property productClassID1 = new Property();
        productClassID1.setIdShort("ProductClassID1");
        productClassID1.setValue(this.classIds.get(0));
        productClassificationItem1.addSubmodelElement(productClassID1);

        productClassificationSubmodelCollection.addSubmodelElement(productClassificationItem1);

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
        productClassificationSystemVersion2.setValue(this.classificationSystemVersions.get(1));
        productClassificationItem2.addSubmodelElement(productClassificationSystemVersion2);
        //          1.2.2.2.3 Property product class id 2
        Property productClassID2 = new Property();
        productClassID2.setIdShort("ProductClassID2");
        productClassID2.setValue(this.classIds.get(1));
        productClassificationItem2.addSubmodelElement(productClassID2);

        productClassificationSubmodelCollection.addSubmodelElement(productClassificationItem2);

        // 2.SMC technical properties
        SubmodelElementCollection technicalPropertiesSubmodelCollection = new SubmodelElementCollection("TechnicalProperties");

        // 2.1 Material
        Property materialProperty = new Property();
        materialProperty.setIdShort("Material");
        materialProperty.setValue(this.material);
        technicalPropertiesSubmodelCollection.addSubmodelElement(materialProperty);
        // 2.2 Melting Point
        Property meltingpointProperty = new Property();
        meltingpointProperty.setIdShort("Melting Point");
        meltingpointProperty.setValue(this.meltingpoint + " ℃"); 
        technicalPropertiesSubmodelCollection.addSubmodelElement(meltingpointProperty);
        // 2.3 Density
        Property densityProperty = new Property();
        densityProperty.setIdShort("Density");
        densityProperty.setValue(this.density + " g/cm");
        technicalPropertiesSubmodelCollection.addSubmodelElement(densityProperty);
        // 2.4 Deformability
        Property deformabilityProperty = new Property();
        deformabilityProperty.setIdShort("Deformability");
        deformabilityProperty.setValue(this.deformability + " Mpa");
        technicalPropertiesSubmodelCollection.addSubmodelElement(deformabilityProperty);

        // 3.SMC geometry information
        SubmodelElementCollection geometryInformationSubmodelCollection = new SubmodelElementCollection("GeometryInformation");
        // 3.1 Height
        Property heightProperty = new Property();
        heightProperty.setIdShort("Height");
        heightProperty.setValue(this.dimensions.get(0)+ " mm");
        geometryInformationSubmodelCollection.addSubmodelElement(heightProperty);
        // 3.2 Width
        Property widthProperty = new Property();
        widthProperty.setIdShort("Width");
        widthProperty.setValue(this.dimensions.get(1).toString()+ " mm");
        geometryInformationSubmodelCollection.addSubmodelElement(widthProperty);
        // 3.3 Length
        Property lengthProperty = new Property();
        lengthProperty.setIdShort("Length");
        lengthProperty.setValue(this.dimensions.get(2).toString()+ " mm");
        geometryInformationSubmodelCollection.addSubmodelElement(lengthProperty);
        // 3.4 Weight
        Property weightProperty = new Property();
        weightProperty.setIdShort("Weight");
        weightProperty.setValue(this.dimensions.get(3).toString()+ " g");
        geometryInformationSubmodelCollection.addSubmodelElement(weightProperty);
        // 3.5 3D Model
        Property threeDModelProperty = new Property();
        threeDModelProperty.setIdShort("3DModel");
        threeDModelProperty.setValue(this.pathOfModel);// TODO: read the file from this path, file in form of .STEP
        geometryInformationSubmodelCollection.addSubmodelElement(threeDModelProperty);

        // 4.SMC capability
        SubmodelElementCollection capabilitySubmodelElementCollection = new SubmodelElementCollection("NecessaryCapability");

        // number of capability
        // 修改加的单位的数据类型
        // 读取path
        for(int count : this.numberOfCapability){
            int number = count+1;
            SubmodelElementCollection capability = new SubmodelElementCollection("Capability" + Integer.toString(number));

            // 4.1 Name
            Property nameProperty = new Property();
            nameProperty.setIdShort("Name");
            nameProperty.setValue(this.name.get(count));
            capability.addSubmodelElement(nameProperty);

            // 4.2 Description
            Property descriptionProperty = new Property();
            descriptionProperty.setIdShort("Description");
            descriptionProperty.setValue(this.description.get(count));
            capability.addSubmodelElement(descriptionProperty);

            capabilitySubmodelElementCollection.addSubmodelElement(capability);
        }
        
        // // 4.1 Name
        // Property nameProperty = new Property();
        // nameProperty.setIdShort("Name");
        // nameProperty.setValue(this.name);
        // capabilitySubmodelElementCollection.addSubmodelElement(nameProperty);

        // // 4.2 Description
        // Property descriptionProperty = new Property();
        // descriptionProperty.setIdShort("Description");
        // descriptionProperty.setValue(this.description);
        // capabilitySubmodelElementCollection.addSubmodelElement(descriptionProperty);

        // // 4.3 Input parameters
        // Property inputParametersProperty = new Property();
        // inputParametersProperty.setIdShort("InputParameters");
        // inputParametersProperty.setValue(this.inputParameters);
        // capabilitySubmodelElementCollection.addSubmodelElement(inputParametersProperty);

        // // 4.4 Output parameters
        // Property outputParametersProperty = new Property();
        // outputParametersProperty.setIdShort("OutputParameters");
        // outputParametersProperty.setValue(this.outputParameters);
        // capabilitySubmodelElementCollection.addSubmodelElement(outputParametersProperty);

        // // 4.5 supported process
        // Property supportedProcessProperty = new Property();
        // supportedProcessProperty.setIdShort("SupportedProcess");
        // supportedProcessProperty.setValue(this.supportedTask);
        // capabilitySubmodelElementCollection.addSubmodelElement(supportedProcessProperty);

        // add all the SMC to the product submodel
        productSubmodel.addSubmodelElement(productClassificationSubmodelCollection);
        productSubmodel.addSubmodelElement(technicalPropertiesSubmodelCollection);
        productSubmodel.addSubmodelElement(geometryInformationSubmodelCollection);
        productSubmodel.addSubmodelElement(capabilitySubmodelElementCollection);

        return productSubmodel;
    }


	public static Asset createAsset(String productIdentifier) {
		Asset asset = new Asset(productIdentifier, new CustomId(productIdentifier), AssetKind.INSTANCE);
		return asset;
	}

	public static AssetAdministrationShell createAAS(Asset productAsset, String AASIdentifier, String description) {
		// create product asset and set aas
		AssetAdministrationShell productShell = new AssetAdministrationShell(AASIdentifier, new CustomId(AASIdentifier),
				productAsset);
		// create description for product shell
		LangStrings descriptionProduct = new LangStrings("english", description);
		productShell.setDescription(descriptionProduct);
		return productShell;
	}

	public static List<Submodel> generateAndRegisterSubmodels(AssetAdministrationShell productShell,
			ShopFloorAAS_Product generator) {
		// create Geometry, Costing and Process Submodel
		Submodel productSubmodel = generator.createProduct();
		// Submodel costingSubmodel = generator.createCostingSubmodel();
		// Submodel processSubmodel = generator.createProcessSubmodel();

		// add submodels to AAS
		productShell.addSubmodel(productSubmodel);
		// productShell.addSubmodel(costingSubmodel);
		// productShell.addSubmodel(processSubmodel);

		// return List.of(geometrySubmodel, costingSubmodel, processSubmodel);
        return List.of(productSubmodel);
	}


	public static MultiSubmodelProvider getFullProvier(AssetAdministrationShell productShell,
			List<Submodel> submodels) {
		// create aas model provider for all submodels
		MultiSubmodelProvider fullProvider = new MultiSubmodelProvider();

		AASModelProvider aasProvider = new AASModelProvider(productShell);
		fullProvider.setAssetAdministrationShell(aasProvider);

		for (Submodel submodel : submodels) {
			SubmodelProvider submodelProvider = new SubmodelProvider(submodel);
			fullProvider.addSubmodel(submodelProvider);
		}
		return fullProvider;

	}

	public static void startServerWithInMemoryRegistry(MultiSubmodelProvider fullProvider,
		AssetAdministrationShell productShell) {
		// create aas server
		HttpServlet aasServlet = new VABHTTPInterface<IModelProvider>(fullProvider);
		logger.info("Created a servlet for the model");

		// create registry with provider and servlet
		IAASRegistry registry = new InMemoryRegistry();
		IModelProvider registryProvider = new AASRegistryModelProvider(registry);
		HttpServlet registryServlet = new VABHTTPInterface<IModelProvider>(registryProvider);
		logger.info("Created a registry servlet for the model");

		// Register the VAB model at the directory
		AASDescriptor aasDescriptor = new AASDescriptor(productShell, "http://localhost:4001/aasserver/shells/TestProductAAS/aas");
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
			AssetAdministrationShell productshell) {
		String host = "localhost";
		// String host = "193.196.37.23";

		String registryPath = "http://" + host + ":8082/registry/api/v1/registry";
		AASRegistryProxy registryProxy = new AASRegistryProxy(registryPath);
		AASDescriptor aasDescriptor = new AASDescriptor(productshell, "http://localhost:4001/aasserver/shells/TestProductAAS/aas");

		registryProxy.register(aasDescriptor);
	}

	public static void UseExternalServerAndRegistry(AssetAdministrationShell productShell, List<Submodel> submodels) {


		Map<AssetAdministrationShell, List<Submodel>> map = new HashMap<>();
		map.put(productShell, submodels);

		String host = "localhost";
		// String host = "193.196.37.23";

		PushAAStoServer.pushAAS(map, "http://" + host + ":8081/aasServer",
				"http://" + host + ":8082/registry/api/v1/registry");
	}

	public static void main(String[] args) throws Exception {

		// create product asset and set aas
		Asset productAsset = createAsset("ShopFloorAAS_Product");
		AssetAdministrationShell productShell = createAAS(productAsset, "ShopFloorAAS",
				"This is a product AAS.");

		// create SimpleAAS object for easy storage of the product data for all
		// submodels
		// ShopFloorAAS_Product generator = new ShopFloorAAS_Product(0, 
        // List.of("ECLASS1","ECLASS2"), List.of(9.0,10.0), 
        // List.of("07-01", "07-02"), "PA6/UD", 
        // 222.0, 1.12, 10.0, List.of(1.0, 300.0, 400.0,400.0), 
        // "path of database, which stores a STEP document", 4, List.of("Pick and place", "Heating", "Moving", "Deformation"),
        // List.of("Description of pick and place", "Description of heating", "Description of moving", "Description of Deformation"));
        ShopFloorAAS_Product generator = new ShopFloorAAS_Product(0.0, List.of("ECLASS1","ECLASS2"), 
        List.of(9.0,10.0), List.of("07-01", "07-02"),
        "PA6/UD", 222.0, 1.12, 10.0, List.of(1.0, 300.0, 400.0,400.0), 
        "path of database, which stores a STEP document", List.of(0,1,2,3), List.of("Pick and place", "Heating", "Moving", "Deformation"), 
        List.of("Description of pick and place", "Description of heating", "Description of moving", "Description of Deformation"));

		List<Submodel> submodels = generateAndRegisterSubmodels(productShell, generator);

		MultiSubmodelProvider fullProvider = getFullProvier(productShell, submodels);

		startServerWithInMemoryRegistry(fullProvider, productShell);

		startServerAndConnectToExternalRegistry(fullProvider, productShell);

		UseExternalServerAndRegistry(productShell, submodels);

	}


    
}
