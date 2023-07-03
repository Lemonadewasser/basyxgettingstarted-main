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

import AASExamples.ProductAAS;
import AASExamples.PushAAStoServer;

public class ShopFloorAAS_Process {

    private static final Logger logger = LoggerFactory.getLogger(ShopFloorAAS_Process.class);

    // required objects for the properties
    public double value;

    public List<Integer> processes_number;
    public List<String> processes_name;
    public List<String> processes_description;
    public List<String> processes_resource;
    public List<String> processes_constraints;
    public List<String> processes_capability;

    public List<List<Integer>> tasks_number;
    public List<List<String>> tasks_names;
    public List<List<String>> tasks_position;
    public List<List<String>> tasks_orientation;
    public List<List<Double>> tasks_velocity;
    public List<List<Boolean>> tasks_inputParameter;
    public List<List<Boolean>> tasks_outputParameter;
    public List<List<String>> tasks_resource;
    public List<List<String>> tasks_constraints;


    public ShopFloorAAS_Process(double value, List<Integer> processes_number, List<String> processes_name, List<String> processes_description, List<String> processes_resource,
    List<String> processes_constraints, List<String> processes_capability, List<List<Integer>> tasks_number, List<List<String>> tasks_names, List<List<String>> tasks_position,
    List<List<String>> tasks_orientation, List<List<Double>> tasks_velocity, List<List<Boolean>> tasks_inputParameter, List<List<Boolean>> tasks_outputParameter,
    List<List<String>> tasks_resource, List<List<String>> tasks_constraints){
        this.value = value;

        this.processes_number = processes_number;
        this.processes_name = processes_name;
        this.processes_description = processes_description;
        this.processes_resource = processes_resource;
        this.processes_constraints = processes_constraints;
        this.processes_capability = processes_capability;

        this.tasks_number = tasks_number;
        this.tasks_names = tasks_names;
        this.tasks_position = tasks_position;
        this.tasks_orientation = tasks_orientation;
        this.tasks_velocity = tasks_velocity;
        this.tasks_inputParameter = tasks_inputParameter;
        this.tasks_outputParameter = tasks_outputParameter;
        this.tasks_resource = tasks_resource;
        this.tasks_constraints = tasks_constraints;
    }

    Submodel createProcess(){
        // submodel with id short
        Submodel processSubmodel = new Submodel();
        processSubmodel.setIdShort("ProcessSubmodel");
        processSubmodel.setIdentification(new ModelUrn("ProcessSubmodel"));//TODO: identification need to be written


        // build the process submodel in detail
        for (int process : this.processes_number) {
            // set the step number
            int counter = process+1;
            // initialize the SMC ProcessStep1
            SubmodelElementCollection process_step = new SubmodelElementCollection("ProcessStep"+counter);

            // 1.initialize the property process name and set the value
            Property process_name = new Property();
            process_name.setIdShort("ProcessName");
            process_name.setValue(this.processes_name.get(process));
            process_step.addSubmodelElement(process_name);

            // 2.process id
            Property process_id = new Property();
            process_id.setIdShort("ProcessID");
            process_id.setValue(counter);
            process_step.addSubmodelElement(process_id);

            // 3.process description
            Property process_description = new Property();
            process_description.setIdShort("ProcessDescription");
            process_description.setValue(this.processes_description.get(process));
            process_step.addSubmodelElement(process_description);

            // 4.process resource
            Property process_resource = new Property();
            process_resource.setIdShort("ProcessResource");
            process_resource.setValue(this.processes_resource.get(process));
            process_step.addSubmodelElement(process_resource);

            // 5.process constraints
            Property process_constraints = new Property();
            process_constraints.setIdShort("ProcessConstraints");
            process_constraints.setValue(this.processes_constraints.get(process));
            process_step.addSubmodelElement(process_constraints);

            // 6.process capability
            Property process_capability = new Property();
            process_capability.setIdShort("ProcessCapability");
            process_capability.setValue(this.processes_capability.get(process));
            process_step.addSubmodelElement(process_capability);

            // 7.process task
            for (int task : this.tasks_number.get(process)) {
                SubmodelElementCollection task_step = new SubmodelElementCollection(this.tasks_names.get(process).get(task));

                // 7.1 task position
                Property task_position = new Property();
                task_position.setIdShort("TaskPosition");
                task_position.setValue(this.tasks_position.get(process).get(task));
                task_step.addSubmodelElement(task_position);

                //7.2 task orientation
                Property task_orientation = new Property();
                task_orientation.setIdShort("TaskOrientation");
                task_orientation.setValue(this.tasks_orientation.get(process).get(task));
                task_step.addSubmodelElement(task_orientation);

                // 7.3 task velocity
                Property task_velocity = new Property();
                task_velocity.setIdShort("TaskVelocity");
                task_velocity.setValue(this.tasks_velocity.get(process).get(task));
                task_step.addSubmodelElement(task_velocity);

                // 7.4 task input parameter
                Property task_inputParameter = new Property();
                task_inputParameter.setIdShort("TaskInputParameter");
                task_inputParameter.setValue(this.tasks_inputParameter.get(process).get(task));
                task_step.addSubmodelElement(task_inputParameter);

                // 7.5 task output parameter
                Property task_outputerParameter = new Property();
                task_outputerParameter.setIdShort("TaskOutputParameter");
                task_outputerParameter.setValue(this.tasks_outputParameter.get(process).get(task));
                task_step.addSubmodelElement(task_outputerParameter);

                // 7.6 task resource
                Property task_resource = new Property();
                task_resource.setIdShort("TaskResource");
                task_resource.setValue(this.tasks_resource.get(process).get(task));
                task_step.addSubmodelElement(task_resource);

                // 7.7 task constraints
                Property task_constraints = new Property();
                task_constraints.setIdShort("TaskConstraints");
                task_constraints.setValue(this.tasks_constraints.get(process).get(task));
                task_step.addSubmodelElement(task_constraints);

                process_step.addSubmodelElement(task_step);
            }


            processSubmodel.addSubmodelElement(process_step);
        }


        return processSubmodel;
    }

	public static Asset createAsset(String processIdentifier) {
		Asset asset = new Asset(processIdentifier, new CustomId(processIdentifier), AssetKind.INSTANCE);
		return asset;
	}

	public static AssetAdministrationShell createAAS(Asset processAsset, String AASIdentifier, String description) {
		// create product asset and set aas
		AssetAdministrationShell processShell = new AssetAdministrationShell(AASIdentifier, new CustomId(AASIdentifier),
				processAsset);
		// create description for product shell
		LangStrings descriptionProcess = new LangStrings("english", description);
		processShell.setDescription(descriptionProcess);
		return processShell;
	}
    
	public static List<Submodel> generateAndRegisterSubmodels(AssetAdministrationShell processShell,
			ShopFloorAAS_Process generator) {
		// create Geometry, Costing and Process Submodel
		Submodel processSubmodel = generator.createProcess();
		// Submodel costingSubmodel = generator.createCostingSubmodel();
		// Submodel processSubmodel = generator.createProcessSubmodel();

		// add submodels to AAS
		processShell.addSubmodel(processSubmodel);
		// productShell.addSubmodel(costingSubmodel);
		// productShell.addSubmodel(processSubmodel);

		// return List.of(geometrySubmodel, costingSubmodel, processSubmodel);
        return List.of(processSubmodel);
	}

	public static MultiSubmodelProvider getFullProvier(AssetAdministrationShell processShell,
			List<Submodel> submodels) {
		// create aas model provider for all submodels
		MultiSubmodelProvider fullProvider = new MultiSubmodelProvider();

		AASModelProvider aasProvider = new AASModelProvider(processShell);
		fullProvider.setAssetAdministrationShell(aasProvider);

		for (Submodel submodel : submodels) {
			SubmodelProvider submodelProvider = new SubmodelProvider(submodel);
			fullProvider.addSubmodel(submodelProvider);
		}
		return fullProvider;

	}

	public static void startServerWithInMemoryRegistry(MultiSubmodelProvider fullProvider,
		AssetAdministrationShell processShell) {
		// create aas server
		HttpServlet aasServlet = new VABHTTPInterface<IModelProvider>(fullProvider);
		logger.info("Created a servlet for the model");

		// create registry with provider and servlet
		IAASRegistry registry = new InMemoryRegistry();
		IModelProvider registryProvider = new AASRegistryModelProvider(registry);
		HttpServlet registryServlet = new VABHTTPInterface<IModelProvider>(registryProvider);
		logger.info("Created a registry servlet for the model");

		// Register the VAB model at the directory
		AASDescriptor aasDescriptor = new AASDescriptor(processShell, "http://localhost:4001/aasserver/shells/TestProductAAS/aas");
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
			AssetAdministrationShell processshell) {
		String host = "localhost";
		// String host = "193.196.37.23";

		String registryPath = "http://" + host + ":8082/registry/api/v1/registry";
		AASRegistryProxy registryProxy = new AASRegistryProxy(registryPath);
		AASDescriptor aasDescriptor = new AASDescriptor(processshell, "http://localhost:4001/aasserver/shells/TestProductAAS/aas");

		registryProxy.register(aasDescriptor);
	}

	public static void UseExternalServerAndRegistry(AssetAdministrationShell processShell, List<Submodel> submodels) {


		Map<AssetAdministrationShell, List<Submodel>> map = new HashMap<>();
		map.put(processShell, submodels);

		String host = "localhost";
		// String host = "193.196.37.23";

		PushAAStoServer.pushAAS(map, "http://" + host + ":8081/aasServer",
				"http://" + host + ":8082/registry/api/v1/registry");
	}

	public static void main(String[] args) throws Exception {

		// create product asset and set aas
		Asset processAsset = createAsset("TestProcess");
		AssetAdministrationShell processShell = createAAS(processAsset, "TestProcessAAS",
				"This is a test process AAS.");

		// create SimpleAAS object for easy storage of the product data for all
		// submodels
		ShopFloorAAS_Process generator = new ShopFloorAAS_Process(0.0, 
        List.of(0,1), 
        List.of("Deforming1", "Deforming2"), 
        List.of("Deforming a sheet1", "Deforming a sheet2"),
        List.of("Robot", "Gripper"),
        List.of("Done in 2 mins1", "Done in 3 mins2"),
        List.of("change the shape to meet the specification1", "change the shape to meet the specification2"),
        List.of(List.of(0,1), List.of(0,1)), 
        List.of(List.of("Gripping", "Moving"), List.of("Heating","Pressing")),
        List.of(List.of("(1,2,3)","(2,3,4)"), List.of("(3,4,5)","(4,5,6)")),
        List.of(List.of("(1,2,3)","(2,3,4)"), List.of("(3,4,5)","(4,5,6)")),
        List.of(List.of(4.0, 5.0), List.of(6.0,7.0)),
        List.of(List.of(true, false), List.of(false,true)),
        List.of(List.of(true, false), List.of(false,true)),
        List.of(List.of("Gripper", "Mover"), List.of("Heater","Presser")),
        List.of(List.of("Done firstly", "Done secondly"), List.of("Done thirdly","Done finally")));

        // ShopFloorAAS_Process generator = new ShopFloorAAS_Process(0.0, List.of(0,1), List.of("Deforming1", "Deforming2"), 
        // List.of("Deforming a sheet1", "Deforming a sheet2"), List.of("Robot", "Gripper"), List.of("Done in 2 mins1", "Done in 3 mins2"), 
        // List.of("change the shape to meet the specification1", "change the shape to meet the specification2"), List.of(List.of(0,1), List.of(0,1)), 
        // List.of(List.of("Gripping", "Moving"), List.of("Heating","Pressing")), 
        // List.of(List.of(List.of(1.1,2.2,3.3),List.of(1.1,2.2,3.3)), List.of(List.of(1.1,2.2,3.3),List.of(1.1,2.2,3.3))), 
        // List.of(List.of(List.of(1.1,2.2,3.3),List.of(1.1,2.2,3.3)), List.of(List.of(1.1,2.2,3.3),List.of(1.1,2.2,3.3))), 
        // List.of(List.of(4.0, 5.0), List.of(6.0,7.0)), List.of(List.of(true, false), List.of(false,true)), 
        // List.of(List.of(true, false), List.of(false,true)), List.of(List.of("Gripper", "Mover"), List.of("Heater","Presser")), 
        // List.of(List.of("Done firstly", "Done secondly"), List.of("Done thirdly","Done finally")));

		List<Submodel> submodels = generateAndRegisterSubmodels(processShell, generator);

		MultiSubmodelProvider fullProvider = getFullProvier(processShell, submodels);

		startServerWithInMemoryRegistry(fullProvider, processShell);

		startServerAndConnectToExternalRegistry(fullProvider, processShell);

		UseExternalServerAndRegistry(processShell, submodels);

	}

}
