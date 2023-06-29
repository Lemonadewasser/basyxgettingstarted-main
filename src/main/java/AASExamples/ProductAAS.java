package AASExamples;

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

public class ProductAAS {

	private static final Logger logger = LoggerFactory.getLogger(ProductAAS.class);

	// required objects for the properties
	public double value;
	public double raw_material_cost;
	public double weight;
	public List<Double> dimensions;
	public List<String> required_processes;

	public ProductAAS(double value, double raw_material_cost, double weight, List<Double> dimensions,
			List<String> required_processes) {
		this.value = value;
		this.raw_material_cost = raw_material_cost;
		this.weight = weight;
		this.dimensions = dimensions;
		this.required_processes = required_processes;
	}

	public Submodel createGeometrySubmodel() {
		// submodel with id short
		Submodel geometrySubModel = new Submodel();
		geometrySubModel.setIdShort("GeometrySubmodel");
		geometrySubModel.setIdentification(new ModelUrn("GeometrySubmodel"));

		Property geometryProperty = new Property();
		geometryProperty.setIdShort("Geometry");
		geometryProperty.setValue(this.dimensions.toString());
		geometrySubModel.addSubmodelElement(geometryProperty);

		Property weightProperty = new Property();
		weightProperty.setIdShort("weight");
		weightProperty.setValue(this.weight);
		geometrySubModel.addSubmodelElement(weightProperty);

		return geometrySubModel;
	}

	public Submodel createCostingSubmodel() {
		// submodel with id short
		Submodel costingSubModel = new Submodel();
		costingSubModel.setIdShort("CostingSubmodel");
		costingSubModel.setIdentification(new ModelUrn("CostingSubmodel"));

		Property valueProperty = new Property();
		valueProperty.setIdShort("Value");
		valueProperty.setValue(this.value);
		costingSubModel.addSubmodelElement(valueProperty);

		Property rawMaterialCostProperty = new Property();
		rawMaterialCostProperty.setIdShort("RawMaterialCost");
		rawMaterialCostProperty.setValue(this.value);
		costingSubModel.addSubmodelElement(rawMaterialCostProperty);

		return costingSubModel;
	}

	public Submodel createProcessSubmodel() {
		// submodel with id short
		Submodel processSubModel = new Submodel();
		processSubModel.setIdShort("ProcessSubmodel");
		processSubModel.setIdentification(new ModelUrn("ProcessSubmodel"));

		SubmodelElementCollection processSubmodelCollection = new SubmodelElementCollection("Processes");
		int counter = 0;
		for (String process : this.required_processes) {
			Property processProprty = new Property("Processs" + counter, process);
			processSubmodelCollection.addSubmodelElement(processProprty);

			counter++;

		}

		processSubModel.addSubmodelElement(processSubmodelCollection);

		return processSubModel;
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
			ProductAAS generator) {
		// create Geometry, Costing and Process Submodel
		Submodel geometrySubmodel = generator.createGeometrySubmodel();
		Submodel costingSubmodel = generator.createCostingSubmodel();
		Submodel processSubmodel = generator.createProcessSubmodel();

		// add submodels to AAS
		productShell.addSubmodel(geometrySubmodel);
		productShell.addSubmodel(costingSubmodel);
		productShell.addSubmodel(processSubmodel);

		return List.of(geometrySubmodel, costingSubmodel, processSubmodel);
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
		Asset productAsset = createAsset("TestProduct");
		AssetAdministrationShell productShell = createAAS(productAsset, "TestProductAAS",
				"This is a test product AAS.");

		// create SimpleAAS object for easy storage of the product data for all
		// submodels
		ProductAAS generator = new ProductAAS(25.0, 20.2, 37.2, List.of(20.0, 10.4, 80.9),
				List.of("Press", "Milling", "Welding"));

		List<Submodel> submodels = generateAndRegisterSubmodels(productShell, generator);

		MultiSubmodelProvider fullProvider = getFullProvier(productShell, submodels);

		startServerWithInMemoryRegistry(fullProvider, productShell);

		startServerAndConnectToExternalRegistry(fullProvider, productShell);

		UseExternalServerAndRegistry(productShell, submodels);

	}

}
