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

    // property name in stage abc
    public List<String> processes_name; //number 3
    public List<String> processes_id; //number 3
    public List<String> processes_description; //number 3

    // property name only in stage a
    public String target_shape;
    public String task_description;
    public String production_resource;
    public double ambient_temperature;
    public double ambient_humidity;
    public String installation_location;
    public String task_sequence;
    public double task_duration;
    public List<String> processes_resource; //number 2

    // property name only in pick and place(aa,ab,ac,ad)
    public List<String> sheet_position; // number 4
    public List<String> sheet_orientation; // number 4 
    public List<String> target_position; // number 4
    public List<String> target_orientation; // number 4
    public List<Double> grip_force; // number 4
    public List<String> gripping_angel; // number 4
    public List<String> carry_velocity; // number 4
    public List<String> carry_path; // number 4
    

    // property name in smc (aa,ab,ac,ad,heatingsfp,finger deformation, heatingfp, stamping deformation)
    public List<String> task_resource; //number 8
    public List<String> input_parameter;//number 8
    public List<String> output_parameter;//number 8
    public List<List<String>> task_constraints;//number 8*8

    // property name in smc heatingsfp and heatingfp
    public List<Double> target_temperature; //number 2
    public List<Double> heating_speed; //number 2
    public List<String> realtime_temperature; //number 2

    // property name in smc finger deformation and stamping deformation
    public List<Double> stroke; //number 2
    public List<Double> velocity; //number 2
    public List<Double> applied_force; //number 2
    public List<Double> pressure_value; //number 2
    public List<String> fingermoving_path; //number 2
    public List<Double> finger_temperature; //number 2
    public List<Double> deformation_duration; //number 2
    public List<Double> gripper_force; //number 2
    public List<Double> gripper_angel; //number 2
    
    // property name in capability bc
    public List<Integer> capabilityb_number; //number 
    public List<String> capabilityb_name; //number same as description b
    public List<String> capabilityb_description;
    public List<Integer> capabilityc_number;
    public List<String> capabilityc_name; // number same as description c
    public List<String> capabilityc_description;


    public ShopFloorAAS_Process(double value, 
    List<String> processes_name, 
    List<String> processes_id, 
    List<String> processes_description, 
    String target_shape,
    String task_description,
    String production_resource,
    double ambient_temperature,
    double ambient_humidity,
    String installation_location,
    String task_sequence,
    double task_duration,
    List<String> processes_resource,

    List<String> sheet_position, // number 4
    List<String> sheet_orientation, // number 4 
    List<String> target_position, // number 4
    List<String> target_orientation, // number 4
    List<Double> grip_force, // number 4
    List<String> gripping_angel, // number 4
    List<String> carry_velocity, // number 4
    List<String> carry_path, // number 4

    List<String> task_resource, //number 8
    List<String> input_parameter,//number 8
    List<String> output_parameter,//number 8
    List<List<String>> task_constraints,//number 8*8
 
    List<Double> target_temperature, //number 2
    List<Double> heating_speed, //number 2
    List<String> realtime_temperature, //number 2

    List<Double> stroke, //number 2
    List<Double> velocity, //number 2
    List<Double> applied_force, //number 2
    List<Double> pressure_value, //number 2
    List<String> fingermoving_path, //number 2
    List<Double> finger_temperature, //number 2
    List<Double> deformation_duration, //number 2
    List<Double> gripper_force, //number 2
    List<Double> gripper_angel, //number 2

    List<Integer> capabilityb_number,
    List<String> capabilityb_name, 
    List<String> capabilityb_description,
    List<Integer> capabilityc_number, 
    List<String> capabilityc_name, 
    List<String> capabilityc_description){

        this.value = value;

        this.processes_name = processes_name;
        this.processes_id = processes_id;
        this.processes_description = processes_description;
        this.target_shape = target_shape;
        this.task_description = task_description;
        this.production_resource = production_resource;
        this.ambient_temperature = ambient_temperature;
        this.ambient_humidity = ambient_humidity;
        this.installation_location = installation_location;
        this.task_sequence = task_sequence;
        this.task_duration = task_duration;
        this.processes_resource = processes_resource;

        this.sheet_position = sheet_position;
        this.sheet_orientation = sheet_orientation;
        this.target_position = target_position;
        this.target_orientation = target_orientation;
        this.grip_force = grip_force;
        this.gripping_angel = gripping_angel;
        this.carry_velocity = carry_velocity;
        this.carry_path = carry_path;

        this.task_resource = task_resource;
        this.input_parameter = input_parameter;
        this.output_parameter = output_parameter;
        this.task_constraints = task_constraints;
        
        this.target_temperature = target_temperature;
        this.heating_speed = heating_speed;
        this.realtime_temperature = realtime_temperature;

        this.stroke = stroke;
        this.velocity = velocity;
        this.applied_force = applied_force;
        this.pressure_value = pressure_value;
        this.fingermoving_path = fingermoving_path;
        this.finger_temperature = finger_temperature;
        this.deformation_duration = deformation_duration;
        this.gripper_force = gripper_force;
        this.gripper_angel = gripper_angel;

        this.capabilityb_number = capabilityb_number;
        this.capabilityb_name = capabilityb_name;
        this.capabilityb_description = capabilityb_description;
        this.capabilityc_number = capabilityc_number;
        this.capabilityc_name = capabilityc_name;
        this.capabilityc_description = capabilityc_description;
    }

    Submodel createProcess(){
        // submodel with id short
        Submodel processSubmodel = new Submodel();
        processSubmodel.setIdShort("ProcessSubmodel");
        processSubmodel.setIdentification(new ModelUrn("ProcessSubmodel"));//TODO: identification need to be written

        // smc process stage a
        SubmodelElementCollection processStageA = new SubmodelElementCollection("ProcessStageA");

        // a.property process name
        Property processNamea = new Property();
        processNamea.setIdShort("StageAProcessName");
        processNamea.setValue(this.processes_name.get(0));
        processStageA.addSubmodelElement(processNamea);
        // a.property process id
        Property processida = new Property();
        processida.setIdShort("StageAProcessID");
        processida.setValue(this.processes_id.get(0));
        processStageA.addSubmodelElement(processida);
        // a.property process description
        Property processdescriptiona = new Property();
        processdescriptiona.setIdShort("StageAProcessDescription");
        processdescriptiona.setValue(this.processes_description.get(0));
        processStageA.addSubmodelElement(processdescriptiona);
        // a.property target shape
        Property targetShape = new Property();
        targetShape.setIdShort("TargetShape");
        targetShape.setValue(this.target_shape);
        processStageA.addSubmodelElement(targetShape);
        // a.property task description document
        Property taskDescription = new Property();
        taskDescription.setIdShort("TaskDescriptionDocument");
        taskDescription.setValue(this.task_description);
        processStageA.addSubmodelElement(taskDescription);
        // a.property resource for production
        Property productionResource = new Property();
        productionResource.setIdShort("ResourceForProduction");
        productionResource.setValue(this.production_resource);
        processStageA.addSubmodelElement(productionResource);
        // a.property ambient temperature
        Property ambientTemperature = new Property();
        ambientTemperature.setIdShort("AmbientTemperature");
        ambientTemperature.setValue(this.ambient_temperature);
        processStageA.addSubmodelElement(ambientTemperature);
        // a.property ambient humidity
        Property ambientHumidity = new Property();
        ambientHumidity.setIdShort("AmbientHumidity");
        ambientHumidity.setValue(this.ambient_humidity);
        processStageA.addSubmodelElement(ambientHumidity);
        // a.property resource installation location
        Property installationLocation = new Property();
        installationLocation.setIdShort("ResourceInstallationLocation");
        installationLocation.setValue(this.installation_location);
        processStageA.addSubmodelElement(installationLocation);
        // a.property sequence of task
        Property taskSequence = new Property();
        taskSequence.setIdShort("SequenceOfTask");
        taskSequence.setValue(this.task_sequence);
        processStageA.addSubmodelElement(taskSequence);
        // a.property task duration
        Property taskDuration = new Property();
        taskDuration.setIdShort("TaskDuration");
        taskDuration.setValue(Double.toString(task_duration) + " Day");
        processStageA.addSubmodelElement(taskDuration);



        // smc process stage b
        SubmodelElementCollection processStageB = new SubmodelElementCollection("ProcessStageB");
        // b.property process name
        Property processNameb = new Property();
        processNameb.setIdShort("StageBProcessName");
        processNameb.setValue(this.processes_name.get(1));
        processStageB.addSubmodelElement(processNameb);
        // b.property process id
        Property processidb = new Property();
        processidb.setIdShort("StageBProcessID");
        processidb.setValue(this.processes_id.get(1));
        processStageB.addSubmodelElement(processidb);
        // b.property process description
        Property processdescriptionb = new Property();
        processdescriptionb.setIdShort("StageBProcessDescription");
        processdescriptionb.setValue(this.processes_description.get(1));
        processStageB.addSubmodelElement(processdescriptionb);
        // b.property process resource
        Property processResourceb = new Property();
        processResourceb.setIdShort("StageBProcessResource");
        processResourceb.setValue(processes_resource.get(0));
        processStageB.addSubmodelElement(processResourceb);

        // b.smc picka and placea
        SubmodelElementCollection pickAplaceA = new SubmodelElementCollection("PickandPlaceA");
        // b1. property sheet position
        Property sheetPositionaa = new Property();
        sheetPositionaa.setIdShort("SheetPositionA");
        sheetPositionaa.setValue(sheet_position.get(0));
        pickAplaceA.addSubmodelElement(sheetPositionaa);
        // b1. property sheet orientation
        Property sheetOrientationaa = new Property();
        sheetOrientationaa.setIdShort("SheetOrientationA");
        sheetOrientationaa.setValue(sheet_orientation.get(0));
        pickAplaceA.addSubmodelElement(sheetOrientationaa);
        // b1. property target position
        Property targetPositionaa = new Property();
        targetPositionaa.setIdShort("TargetPositionA");
        targetPositionaa.setValue(target_position.get(0));
        pickAplaceA.addSubmodelElement(targetPositionaa);
        // b1. property target orentation
        Property targetOrientationaa = new Property();
        targetOrientationaa.setIdShort("TargetOrientationA");
        targetOrientationaa.setValue(target_orientation.get(0));
        pickAplaceA.addSubmodelElement(targetOrientationaa);
        // b1. property actual grip force of ent effector
        Property gripForceaa = new Property();
        gripForceaa.setIdShort("ActualGripForceOfEndEffectorA");
        gripForceaa.setValue(grip_force.get(0).toString()+"N");
        pickAplaceA.addSubmodelElement(gripForceaa);
        // b1. property gripping angel of end effector
        Property gripAngelaa = new Property();
        gripAngelaa.setIdShort("GrippingAngelOfEndEffectorA");
        gripAngelaa.setValue(gripping_angel.get(0));
        pickAplaceA.addSubmodelElement(gripAngelaa);
        // b1. property carrying velocity
        Property carryVelosityaa = new Property();
        carryVelosityaa.setIdShort("CarryingVelocityA");
        carryVelosityaa.setValue(carry_velocity.get(0));
        pickAplaceA.addSubmodelElement(carryVelosityaa);
        // b1. property carrying path
        Property carryPathaa = new Property();
        carryPathaa.setIdShort("CarryingPathA");
        carryPathaa.setValue(carry_path.get(0));
        pickAplaceA.addSubmodelElement(carryPathaa);
        // b1. property task resource 1
        Property taskResourceaa = new Property();
        taskResourceaa.setIdShort("TaskResourceA");
        taskResourceaa.setValue(task_resource.get(0));
        pickAplaceA.addSubmodelElement(taskResourceaa);
        // b1. property input parameter 1
        Property inputParameteraa = new Property();
        inputParameteraa.setIdShort("InputParameterA");
        inputParameteraa.setValue(input_parameter.get(0));
        pickAplaceA.addSubmodelElement(inputParameteraa);
        // b1. property output parameter 1
        Property outputParameteraa = new Property();
        outputParameteraa.setIdShort("OutputParameterA");
        outputParameteraa.setValue(output_parameter.get(0));
        pickAplaceA.addSubmodelElement(outputParameteraa);

        // b1. smc task constraints 1
        SubmodelElementCollection taskConstraintaa = new SubmodelElementCollection("TaskConstrainA");
        // b1.1 property speed range
        Property speedRangeaa = new Property();
        speedRangeaa.setIdShort("SpeedRangeA");
        speedRangeaa.setValue(task_constraints.get(0).get(0));
        taskConstraintaa.addSubmodelElement(speedRangeaa);
        // b1.1 property reach
        Property reachRangeaa = new Property();
        reachRangeaa.setIdShort("ReachRangeA");
        reachRangeaa.setValue(task_constraints.get(0).get(1));
        taskConstraintaa.addSubmodelElement(reachRangeaa);
        // b1.1 property finger force range
        Property fingerForceRangeaa = new Property();
        fingerForceRangeaa.setIdShort("FingerForceRangeA");
        fingerForceRangeaa.setValue(task_constraints.get(0).get(2));
        taskConstraintaa.addSubmodelElement(fingerForceRangeaa);
        // b1.1 property gripper force range
        Property gripperForceRangeaa = new Property();
        gripperForceRangeaa.setIdShort("GripperForceRangeA");
        gripperForceRangeaa.setValue(task_constraints.get(0).get(3));
        taskConstraintaa.addSubmodelElement(gripperForceRangeaa);
        // b1.1 property temperature range
        Property temperatureRangeaa = new Property();
        temperatureRangeaa.setIdShort("TemperatureRangeA");
        temperatureRangeaa.setValue(task_constraints.get(0).get(4));
        taskConstraintaa.addSubmodelElement(temperatureRangeaa);
        // b1.1 property finger open range
        Property fingerOpenRangeaa = new Property();
        fingerOpenRangeaa.setIdShort("FingerOpenRangeA");
        fingerOpenRangeaa.setValue(task_constraints.get(0).get(5));
        taskConstraintaa.addSubmodelElement(fingerOpenRangeaa);
        // b1.1 property gripper open range
        Property gripperOpenRangeaa = new Property();
        gripperOpenRangeaa.setIdShort("GripperOpenRangeA");
        gripperOpenRangeaa.setValue(task_constraints.get(0).get(6));
        taskConstraintaa.addSubmodelElement(gripperOpenRangeaa);
        // b1.1 property deformation range
        Property deformationRangeaa = new Property();
        deformationRangeaa.setIdShort("DeformationRangeA");
        deformationRangeaa.setValue(task_constraints.get(0).get(7));
        taskConstraintaa.addSubmodelElement(deformationRangeaa);

        pickAplaceA.addSubmodelElement(taskConstraintaa);
        processStageB.addSubmodelElement(pickAplaceA);

        // b. smc heating sfp
        SubmodelElementCollection heatingsfp = new SubmodelElementCollection("HeatingSFP");
        // b2. property target product temperature
        Property targetTemperaturesfp = new Property();
        targetTemperaturesfp.setIdShort("TargetProductTemperatureSFP");
        targetTemperaturesfp.setValue(target_temperature.get(0).toString()+"\u00B0C");
        heatingsfp.addSubmodelElement(targetTemperaturesfp);
        // b2. property heating speed
        Property heatingSpeedsfp = new Property();
        heatingSpeedsfp.setIdShort("HeatingSpeedSFP");
        heatingSpeedsfp.setValue(heating_speed.get(0).toString()+"°C/s");
        heatingsfp.addSubmodelElement(heatingSpeedsfp);
        // b2. property real time product temperature
        Property realtimeTemperaturesfp = new Property();
        realtimeTemperaturesfp.setIdShort("RealTimeProductTemperatureSFP");
        realtimeTemperaturesfp.setValue(realtime_temperature.get(0));
        heatingsfp.addSubmodelElement(realtimeTemperaturesfp);
        // b2. property task resource2
        Property taskResourcesfp = new Property();
        taskResourcesfp.setIdShort("TaskResourceSFP");
        taskResourcesfp.setValue(task_resource.get(4));
        heatingsfp.addSubmodelElement(taskResourcesfp);
        // b2. property input parameter 2
        Property inputParametersfp = new Property();
        inputParametersfp.setIdShort("InputParameterSFP");
        inputParametersfp.setValue(input_parameter.get(4));
        heatingsfp.addSubmodelElement(inputParametersfp);
        // b2. property output parameter 2
        Property outputParametersfp = new Property();
        outputParametersfp.setIdShort("TaskResourceSFP");
        outputParametersfp.setValue(output_parameter.get(4));
        heatingsfp.addSubmodelElement(outputParametersfp);

        // b2. smc task constraints sfp
        SubmodelElementCollection taskConstraintsfp = new SubmodelElementCollection("TaskConstraintSFP");
        // b2.1 property speed range
        Property speedRangesfp = new Property();
        speedRangesfp.setIdShort("SpeedRangeSFP");
        speedRangesfp.setValue(task_constraints.get(4).get(0));
        taskConstraintsfp.addSubmodelElement(speedRangesfp);
        // b2.1 property reach
        Property reachRangesfp = new Property();
        reachRangesfp.setIdShort("ReachRangeSFP");
        reachRangesfp.setValue(task_constraints.get(4).get(1));
        taskConstraintsfp.addSubmodelElement(reachRangesfp);
        // b2.1 property finger force range
        Property fingerForceRangesfp = new Property();
        fingerForceRangesfp.setIdShort("FingerForceRangeSFP");
        fingerForceRangesfp.setValue(task_constraints.get(4).get(2));
        taskConstraintsfp.addSubmodelElement(fingerForceRangesfp);
        // b2.1 property gripper force range
        Property gripperForceRangesfp = new Property();
        gripperForceRangesfp.setIdShort("GripperForceRangeSFP");
        gripperForceRangesfp.setValue(task_constraints.get(4).get(3));
        taskConstraintsfp.addSubmodelElement(gripperForceRangesfp);
        // b2.1 property temperature range
        Property temperatureRangesfp = new Property();
        temperatureRangesfp.setIdShort("TemperatureRangeSFP");
        temperatureRangesfp.setValue(task_constraints.get(4).get(4).toString()+"\u00B0C");
        taskConstraintsfp.addSubmodelElement(temperatureRangesfp);
        // b2.1 property finger open range
        Property fingerOpenRangesfp = new Property();
        fingerOpenRangesfp.setIdShort("FingerOpenRangeSFP");
        fingerOpenRangesfp.setValue(task_constraints.get(4).get(5));
        taskConstraintsfp.addSubmodelElement(fingerOpenRangesfp);
        // b2.1 property gripper open range
        Property gripperOpenRangesfp = new Property();
        gripperOpenRangesfp.setIdShort("GripperOpenRangeSFP");
        gripperOpenRangesfp.setValue(task_constraints.get(4).get(6));
        taskConstraintsfp.addSubmodelElement(gripperOpenRangesfp);
        // b2.1 property deformation range
        Property deformationRangesfp = new Property();
        deformationRangesfp.setIdShort("DeformationRangeSFP");
        deformationRangesfp.setValue(task_constraints.get(4).get(7));
        taskConstraintsfp.addSubmodelElement(deformationRangesfp);

        heatingsfp.addSubmodelElement(taskConstraintsfp);

        processStageB.addSubmodelElement(heatingsfp);

        // b. smc finger deformation
        SubmodelElementCollection fingerDeformation = new SubmodelElementCollection("FingerDeformation");
        // b3. property stroke
        Property strokefd = new Property();
        strokefd.setIdShort("StrokeFD");
        strokefd.setValue(stroke.get(0).toString()+"");
        fingerDeformation.addSubmodelElement(strokefd);
        // b3. property velocity
        Property velocityfd = new Property();
        velocityfd.setIdShort("StrokeFD"+"");
        velocityfd.setValue(velocity.get(0).toString()+"");
        fingerDeformation.addSubmodelElement(velocityfd);
        // b3. property applied force
        Property appliedForcefd = new Property();
        appliedForcefd.setIdShort("AppliedForceFD");
        appliedForcefd.setValue(applied_force.get(0).toString()+"");
        fingerDeformation.addSubmodelElement(appliedForcefd);
        // b3. property pressure value
        Property pressureValuefd = new Property();
        pressureValuefd.setIdShort("PressureValueFD");
        pressureValuefd.setValue(pressure_value.get(0).toString()+"");
        fingerDeformation.addSubmodelElement(pressureValuefd);
        // b3. property finger moving path
        Property movingPathfd = new Property();
        movingPathfd.setIdShort("FingerMovingPathFD");
        movingPathfd.setValue(fingermoving_path.get(0));
        fingerDeformation.addSubmodelElement(movingPathfd);
        // b3. property finger temperature
        Property fingerTemperaturefd = new Property();
        fingerTemperaturefd.setIdShort("FingerTemperatureFD");
        fingerTemperaturefd.setValue(finger_temperature.get(0).toString()+"");
        fingerDeformation.addSubmodelElement(fingerTemperaturefd);
        // b3. property deformation duration
        Property deformationDurationfd = new Property();
        deformationDurationfd.setIdShort("DeformationDurationFD");
        deformationDurationfd.setValue(deformation_duration.get(0).toString()+"");
        fingerDeformation.addSubmodelElement(deformationDurationfd);
        // b3. property gripper force
        Property gripperForcefd = new Property();
        gripperForcefd.setIdShort("GripperForceFD");
        gripperForcefd.setValue(gripper_force.get(0).toString()+"");
        fingerDeformation.addSubmodelElement(gripperForcefd);
        // b3. property task resource fd
        Property taskResoucefd = new Property();
        taskResoucefd.setIdShort("TaskresorceFD");
        taskResoucefd.setValue(task_resource.get(5));
        fingerDeformation.addSubmodelElement(taskResoucefd);
        // b3. property input parameter fd
        Property inputParameterfd = new Property();
        inputParameterfd.setIdShort("InputParameterFD");
        inputParameterfd.setValue(input_parameter.get(5));
        fingerDeformation.addSubmodelElement(inputParameterfd);
        // b3. property output parameter fd
        Property outputParameterfd = new Property();
        outputParameterfd.setIdShort("OutputParameterFD");
        outputParameterfd.setValue(input_parameter.get(5));
        fingerDeformation.addSubmodelElement(outputParameterfd);

        // b3. smc task constraints fd
        SubmodelElementCollection taskConstraintfd = new SubmodelElementCollection("TaskConstraintFD");
        // b3.1 property speed range
        Property speedRangefd = new Property();
        speedRangefd.setIdShort("SpeedRangeFD");
        speedRangefd.setValue(task_constraints.get(5).get(0));
        taskConstraintfd.addSubmodelElement(speedRangefd);
        // b3.1 property reach
        Property reachRangefd = new Property();
        reachRangefd.setIdShort("ReachRangeFD");
        reachRangefd.setValue(task_constraints.get(5).get(1));
        taskConstraintfd.addSubmodelElement(reachRangefd);
        // b3.1 property finger force range
        Property fingerForceRangefd = new Property();
        fingerForceRangefd.setIdShort("FingerForceRangeFD");
        fingerForceRangefd.setValue(task_constraints.get(5).get(2));
        taskConstraintfd.addSubmodelElement(fingerForceRangefd);
        // b3.1 property gripper force range
        Property gripperForceRangefd = new Property();
        gripperForceRangefd.setIdShort("GripperForceRangeFD");
        gripperForceRangefd.setValue(task_constraints.get(5).get(3));
        taskConstraintfd.addSubmodelElement(gripperForceRangefd);
        // b3.1 property temperature range
        Property temperatureRangefd = new Property();
        temperatureRangefd.setIdShort("TemperatureRangeFD");
        temperatureRangefd.setValue(task_constraints.get(5).get(4));
        taskConstraintfd.addSubmodelElement(temperatureRangefd);
        // b3.1 property finger open range
        Property fingerOpenRangefd = new Property();
        fingerOpenRangefd.setIdShort("FingerOpenRangeFD");
        fingerOpenRangefd.setValue(task_constraints.get(5).get(5));
        taskConstraintfd.addSubmodelElement(fingerOpenRangefd);
        // b3.1 property gripper open range
        Property gripperOpenRangefd = new Property();
        gripperOpenRangefd.setIdShort("GripperOpenRangeFD");
        gripperOpenRangefd.setValue(task_constraints.get(5).get(6));
        taskConstraintfd.addSubmodelElement(gripperOpenRangefd);
        // b3.1 property deformation range
        Property deformationRangefd = new Property();
        deformationRangefd.setIdShort("DeformationRangeFD");
        deformationRangefd.setValue(task_constraints.get(5).get(7));
        taskConstraintfd.addSubmodelElement(deformationRangefd);

        fingerDeformation.addSubmodelElement(taskConstraintfd);


        processStageB.addSubmodelElement(fingerDeformation);

        // b. smc picka and placeb
        SubmodelElementCollection pickAplaceB = new SubmodelElementCollection("PickandPlaceB");
        // b4. property sheet position
        Property sheetPositionab = new Property();
        sheetPositionab.setIdShort("SheetPositionB");
        sheetPositionab.setValue(sheet_position.get(1));
        pickAplaceB.addSubmodelElement(sheetPositionab);
        // b4. property sheet orientation
        Property sheetOrientationab = new Property();
        sheetOrientationab.setIdShort("SheetOrientationB");
        sheetOrientationab.setValue(sheet_orientation.get(1));
        pickAplaceB.addSubmodelElement(sheetOrientationab);
        // b4. property target position
        Property targetPositionab = new Property();
        targetPositionab.setIdShort("TargetPositionB");
        targetPositionab.setValue(target_position.get(1));
        pickAplaceB.addSubmodelElement(targetPositionab);
        // b4. property target orentation
        Property targetOrientationab = new Property();
        targetOrientationab.setIdShort("TargetOrientationB");
        targetOrientationab.setValue(target_orientation.get(1));
        pickAplaceB.addSubmodelElement(targetOrientationab);
        // b4. property actual grip force of ent effector
        Property gripForceab = new Property();
        gripForceab.setIdShort("ActualGripForceOfEndEffectorB");
        gripForceab.setValue(grip_force.get(1));
        pickAplaceB.addSubmodelElement(gripForceab);
        // b4. property gripping angel of end effector
        Property gripAngelab = new Property();
        gripAngelab.setIdShort("GrippingAngelOfEndEffectorB");
        gripAngelab.setValue(gripping_angel.get(1));
        pickAplaceB.addSubmodelElement(gripAngelab);
        // b4. property carrying velocity
        Property carryVelosityab = new Property();
        carryVelosityab.setIdShort("CarryingVelocityB");
        carryVelosityab.setValue(carry_velocity.get(1));
        pickAplaceB.addSubmodelElement(carryVelosityab);
        // b4. property carrying path
        Property carryPathab = new Property();
        carryPathab.setIdShort("CarryingPathB");
        carryPathab.setValue(carry_path.get(1));
        pickAplaceB.addSubmodelElement(carryPathab);
        // b4. property task resource 1
        Property taskResourceab = new Property();
        taskResourceab.setIdShort("TaskResourceB");
        taskResourceab.setValue(task_resource.get(1));
        pickAplaceB.addSubmodelElement(taskResourceab);
        // b4. property input parameter 1
        Property inputParameterab = new Property();
        inputParameterab.setIdShort("InputParameterB");
        inputParameterab.setValue(input_parameter.get(1));
        pickAplaceB.addSubmodelElement(inputParameterab);
        // b4. property output parameter 1
        Property outputParameterab = new Property();
        outputParameterab.setIdShort("OutputParameterB");
        outputParameterab.setValue(output_parameter.get(1));
        pickAplaceB.addSubmodelElement(outputParameterab);

        // b1. smc task constraints 1
        SubmodelElementCollection taskConstrainab = new SubmodelElementCollection("TaskConstrainB");
        // b4.1 property speed range
        Property speedRangeab = new Property();
        speedRangeab.setIdShort("SpeedRangeAB");
        speedRangeab.setValue(task_constraints.get(1).get(0));
        taskConstrainab.addSubmodelElement(speedRangeab);
        // b1.1 property reach
        Property reachRangeab = new Property();
        reachRangeab.setIdShort("ReachRangeB");
        reachRangeab.setValue(task_constraints.get(1).get(1));
        taskConstrainab.addSubmodelElement(reachRangeab);
        // b4.1 property finger force range
        Property fingerForceRangeab = new Property();
        fingerForceRangeab.setIdShort("FingerForceRangeB");
        fingerForceRangeab.setValue(task_constraints.get(1).get(2));
        taskConstrainab.addSubmodelElement(fingerForceRangeab);
        // b4.1 property gripper force range
        Property gripperForceRangeab = new Property();
        gripperForceRangeab.setIdShort("GripperForceRangeB");
        gripperForceRangeab.setValue(task_constraints.get(1).get(3));
        taskConstrainab.addSubmodelElement(gripperForceRangeab);
        // b4.1 property temperature range
        Property temperatureRangeab = new Property();
        temperatureRangeab.setIdShort("TemperatureRangeB");
        temperatureRangeab.setValue(task_constraints.get(1).get(4));
        taskConstrainab.addSubmodelElement(temperatureRangeab);
        // b4.1 property finger open range
        Property fingerOpenRangeab = new Property();
        fingerOpenRangeab.setIdShort("FingerOpenRangeB");
        fingerOpenRangeab.setValue(task_constraints.get(1).get(5));
        taskConstrainab.addSubmodelElement(fingerOpenRangeab);
        // b4.1 property gripper open range
        Property gripperOpenRangeab = new Property();
        gripperOpenRangeab.setIdShort("GripperOpenRangeB");
        gripperOpenRangeab.setValue(task_constraints.get(1).get(6));
        taskConstrainab.addSubmodelElement(gripperOpenRangeab);
        // b4.1 property deformation range
        Property deformationRangeab = new Property();
        deformationRangeab.setIdShort("DeformationRangeB");
        deformationRangeab.setValue(task_constraints.get(1).get(7));
        taskConstrainab.addSubmodelElement(deformationRangeab);

        pickAplaceB.addSubmodelElement(taskConstrainab);
        processStageB.addSubmodelElement(pickAplaceB);
        

        // b. smc necessary capability 1 (for loop)
        SubmodelElementCollection necessaryCapabilityb = new SubmodelElementCollection("BNecessaryCapability");

        for(int count : this.capabilityb_number){
            int number = count +1;
            SubmodelElementCollection capability = new SubmodelElementCollection("BCapability"+ Integer.toString(number));
            // capability name
            Property capabilityName = new Property();
            capabilityName.setIdShort("Name");
            capabilityName.setValue(capabilityb_name.get(count));
            capability.addSubmodelElement(capabilityName);

            // capability description
            Property capabilityDescription = new Property();
            capabilityDescription.setIdShort("Description");
            capabilityDescription.setValue(capabilityb_description.get(count));
            capability.addSubmodelElement(capabilityDescription);

            necessaryCapabilityb.addSubmodelElement(capability);
        }
        processStageB.addSubmodelElement(necessaryCapabilityb);





        // smc process stage c
        SubmodelElementCollection processStageC = new SubmodelElementCollection("ProcessStageC");

        // c.property process name
        Property processNamec = new Property();
        processNamec.setIdShort("StageCProcessName");
        processNamec.setValue(this.processes_name.get(2));
        processStageC.addSubmodelElement(processNamec);
        // c.property process id
        Property processidc = new Property();
        processidc.setIdShort("StageCProcessID");
        processidc.setValue(this.processes_id.get(2));
        processStageC.addSubmodelElement(processidc);
        // c.property process description
        Property processdescriptionc = new Property();
        processdescriptionc.setIdShort("StageCProcessDescription");
        processdescriptionc.setValue(this.processes_description.get(2));
        processStageC.addSubmodelElement(processdescriptionc);
        // c.property process resource
        Property processResourcec = new Property();
        processResourcec.setIdShort("StageCProcessResource");
        processResourcec.setValue(processes_resource.get(1));
        processStageC.addSubmodelElement(processResourcec);

        // c.smc picka and placec
        SubmodelElementCollection pickAplaceC = new SubmodelElementCollection("PickandPlaceC");
        // c1. property sheet position
        Property sheetPositionac = new Property();
        sheetPositionac.setIdShort("SheetPositionC");
        sheetPositionac.setValue(sheet_position.get(2));
        pickAplaceC.addSubmodelElement(sheetPositionac);
        // c1. property sheet orientation
        Property sheetOrientationac = new Property();
        sheetOrientationac.setIdShort("SheetOrientationC");
        sheetOrientationac.setValue(sheet_orientation.get(2));
        pickAplaceC.addSubmodelElement(sheetOrientationac);
        // c1. property target position
        Property targetPositionac = new Property();
        targetPositionac.setIdShort("TargetPositionC");
        targetPositionac.setValue(target_position.get(2));
        pickAplaceC.addSubmodelElement(targetPositionac);
        // c1. property target orentation
        Property targetOrientationac = new Property();
        targetOrientationac.setIdShort("TargetOrientationC");
        targetOrientationac.setValue(target_orientation.get(2));
        pickAplaceC.addSubmodelElement(targetOrientationac);
        // c1. property actual grip force of ent effector
        Property gripForceac = new Property();
        gripForceac.setIdShort("ActualGripForceOfEndEffectorC");
        gripForceac.setValue(grip_force.get(2));
        pickAplaceC.addSubmodelElement(gripForceac);
        // c1. property gripping angel of end effector
        Property gripAngelac = new Property();
        gripAngelac.setIdShort("GrippingAngelOfEndEffectorC");
        gripAngelac.setValue(gripping_angel.get(2));
        pickAplaceC.addSubmodelElement(gripAngelac);
        // c1. property carrying velocity
        Property carryVelosityac = new Property();
        carryVelosityac.setIdShort("CarryingVelocityC");
        carryVelosityac.setValue(carry_velocity.get(2));
        pickAplaceC.addSubmodelElement(carryVelosityac);
        // c1. property carrying path
        Property carryPathac = new Property();
        carryPathac.setIdShort("CarryingPathC");
        carryPathac.setValue(carry_path.get(2));
        pickAplaceC.addSubmodelElement(carryPathac);
        // b1. property task resource 1
        Property taskResourceac = new Property();
        taskResourceac.setIdShort("TaskResourceC");
        taskResourceac.setValue(task_resource.get(2));
        pickAplaceC.addSubmodelElement(taskResourceac);
        // b1. property input parameter 1
        Property inputParameterac = new Property();
        inputParameterac.setIdShort("InputParameterC");
        inputParameterac.setValue(input_parameter.get(2));
        pickAplaceC.addSubmodelElement(inputParameterac);
        // b1. property output parameter 1
        Property outputParameterac = new Property();
        outputParameterac.setIdShort("OutputParameterC");
        outputParameterac.setValue(output_parameter.get(2));
        pickAplaceC.addSubmodelElement(outputParameterac);

        // c1. smc task constraints 3
        SubmodelElementCollection taskConstraintac = new SubmodelElementCollection("TaskConstrainC");
        // c1.1 property speed range
        Property speedRangeac = new Property();
        speedRangeac.setIdShort("SpeedRangeC");
        speedRangeac.setValue(task_constraints.get(2).get(0));
        taskConstraintac.addSubmodelElement(speedRangeac);
        // c1.1 property reach
        Property reachRangeac = new Property();
        reachRangeac.setIdShort("ReachRangeC");
        reachRangeac.setValue(task_constraints.get(2).get(1));
        taskConstraintac.addSubmodelElement(reachRangeac);
        // c1.1 property finger force range
        Property fingerForceRangeac = new Property();
        fingerForceRangeac.setIdShort("FingerForceRangeC");
        fingerForceRangeac.setValue(task_constraints.get(2).get(2));
        taskConstraintac.addSubmodelElement(fingerForceRangeac);
        // c1.1 property gripper force range
        Property gripperForceRangeac = new Property();
        gripperForceRangeac.setIdShort("GripperForceRangeC");
        gripperForceRangeac.setValue(task_constraints.get(2).get(3));
        taskConstraintac.addSubmodelElement(gripperForceRangeac);
        // c1.1 property temperature range
        Property temperatureRangeac = new Property();
        temperatureRangeac.setIdShort("TemperatureRangeC");
        temperatureRangeac.setValue(task_constraints.get(2).get(4));
        taskConstraintac.addSubmodelElement(temperatureRangeac);
        // c1.1 property finger open range
        Property fingerOpenRangeac = new Property();
        fingerOpenRangeac.setIdShort("FingerOpenRangeC");
        fingerOpenRangeac.setValue(task_constraints.get(2).get(5));
        taskConstraintac.addSubmodelElement(fingerOpenRangeac);
        // c1.1 property gripper open range
        Property gripperOpenRangeac = new Property();
        gripperOpenRangeac.setIdShort("GripperOpenRangeC");
        gripperOpenRangeac.setValue(task_constraints.get(2).get(6));
        taskConstraintac.addSubmodelElement(gripperOpenRangeac);
        // c1.1 property deformation range
        Property deformationRangeac = new Property();
        deformationRangeac.setIdShort("DeformationRangeC");
        deformationRangeac.setValue(task_constraints.get(2).get(7));
        taskConstraintac.addSubmodelElement(deformationRangeac);

        pickAplaceC.addSubmodelElement(taskConstraintac);
        processStageC.addSubmodelElement(pickAplaceC);


        // c. smc heating fp
        SubmodelElementCollection heatingfp = new SubmodelElementCollection("HeatingFP");
        // c2. property target product temperature
        Property targetTemperaturefp = new Property();
        targetTemperaturefp.setIdShort("TargetProductTemperatureFP");
        targetTemperaturefp.setValue(target_temperature.get(1));
        heatingfp.addSubmodelElement(targetTemperaturefp);
        // c2. property heating speed
        Property heatingSpeedfp = new Property();
        heatingSpeedfp.setIdShort("HeatingSpeedFP");
        heatingSpeedfp.setValue(heating_speed.get(1));
        heatingfp.addSubmodelElement(heatingSpeedfp);
        // c2. property real time product temperature
        Property realtimeTemperaturefp = new Property();
        realtimeTemperaturefp.setIdShort("RealTimeProductTemperatureFP");
        realtimeTemperaturefp.setValue(realtime_temperature.get(1));
        heatingfp.addSubmodelElement(realtimeTemperaturefp);
        // c2. property task resource2
        Property taskResourcefp = new Property();
        taskResourcefp.setIdShort("TaskResourceFP");
        taskResourcefp.setValue(task_resource.get(6));
        heatingfp.addSubmodelElement(taskResourcefp);
        // c2. property input parameter 2
        Property inputParameterfp = new Property();
        inputParameterfp.setIdShort("InputParameterFP");
        inputParameterfp.setValue(input_parameter.get(6));
        heatingfp.addSubmodelElement(inputParameterfp);
        // c2. property output parameter 2
        Property outputParameterfp = new Property();
        outputParameterfp.setIdShort("TaskResourceFP");
        outputParameterfp.setValue(output_parameter.get(6));
        heatingfp.addSubmodelElement(outputParameterfp);

        // c2. smc task constraints fp
        SubmodelElementCollection taskConstraintfp = new SubmodelElementCollection("TaskConstraintFP");
        // c2.1 property speed range
        Property speedRangefp = new Property();
        speedRangefp.setIdShort("SpeedRangeFP");
        speedRangefp.setValue(task_constraints.get(6).get(0));
        taskConstraintsfp.addSubmodelElement(speedRangesfp);
        // c2.1 property reach
        Property reachRangefp = new Property();
        reachRangefp.setIdShort("ReachRangeFP");
        reachRangefp.setValue(task_constraints.get(6).get(1));
        taskConstraintfp.addSubmodelElement(reachRangefp);
        // c2.1 property finger force range
        Property fingerForceRangefp = new Property();
        fingerForceRangefp.setIdShort("FingerForceRangeFP");
        fingerForceRangefp.setValue(task_constraints.get(6).get(2));
        taskConstraintfp.addSubmodelElement(fingerForceRangefp);
        // c2.1 property gripper force range
        Property gripperForceRangefp = new Property();
        gripperForceRangefp.setIdShort("GripperForceRangeFP");
        gripperForceRangefp.setValue(task_constraints.get(6).get(3));
        taskConstraintfp.addSubmodelElement(gripperForceRangefp);
        // c2.1 property temperature range
        Property temperatureRangefp = new Property();
        temperatureRangefp.setIdShort("TemperatureRangeFP");
        temperatureRangefp.setValue(task_constraints.get(6).get(4));
        taskConstraintfp.addSubmodelElement(temperatureRangefp);
        // c2.1 property finger open range
        Property fingerOpenRangefp = new Property();
        fingerOpenRangefp.setIdShort("FingerOpenRangeFP");
        fingerOpenRangefp.setValue(task_constraints.get(6).get(5));
        taskConstraintfp.addSubmodelElement(fingerOpenRangefp);
        // c2.1 property gripper open range
        Property gripperOpenRangefp = new Property();
        gripperOpenRangefp.setIdShort("GripperOpenRangeFP");
        gripperOpenRangefp.setValue(task_constraints.get(6).get(6));
        taskConstraintfp.addSubmodelElement(gripperOpenRangefp);
        // c2.1 property deformation range
        Property deformationRangefp = new Property();
        deformationRangefp.setIdShort("DeformationRangeFP");
        deformationRangefp.setValue(task_constraints.get(6).get(7));
        taskConstraintfp.addSubmodelElement(deformationRangefp);

        heatingfp.addSubmodelElement(taskConstraintfp);

        processStageC.addSubmodelElement(heatingfp);


        // c. smc stamping deformation
        SubmodelElementCollection stampingDeformation = new SubmodelElementCollection("StampingrDeformation");
        // c3. property stroke
        Property strokesd = new Property();
        strokesd.setIdShort("StrokeSD");
        strokesd.setValue(stroke.get(1).toString()+"");
        stampingDeformation.addSubmodelElement(strokesd);
        // c3. property velocity
        Property velocitysd = new Property();
        velocitysd.setIdShort("StrokeFD"+"");
        velocitysd.setValue(velocity.get(1).toString()+"");
        stampingDeformation.addSubmodelElement(velocitysd);
        // c3. property applied force
        Property appliedForcesd = new Property();
        appliedForcesd.setIdShort("AppliedForceSD");
        appliedForcesd.setValue(applied_force.get(1).toString()+"");
        stampingDeformation.addSubmodelElement(appliedForcesd);
        // c3. property pressure value
        Property pressureValuesd = new Property();
        pressureValuesd.setIdShort("PressureValueSD");
        pressureValuesd.setValue(pressure_value.get(1).toString()+"");
        stampingDeformation.addSubmodelElement(pressureValuesd);
        // c3. property finger moving path
        Property movingPathsd = new Property();
        movingPathsd.setIdShort("FingerMovingPathSD");
        movingPathsd.setValue(fingermoving_path.get(1));
        stampingDeformation.addSubmodelElement(movingPathsd);
        // c3. property finger temperature
        Property fingerTemperaturesd = new Property();
        fingerTemperaturesd.setIdShort("FingerTemperatureSD");
        fingerTemperaturesd.setValue(finger_temperature.get(1).toString()+"");
        stampingDeformation.addSubmodelElement(fingerTemperaturesd);
        // c3. property deformation duration
        Property deformationDurationsd = new Property();
        deformationDurationsd.setIdShort("DeformationDurationSD");
        deformationDurationsd.setValue(deformation_duration.get(1).toString()+"");
        stampingDeformation.addSubmodelElement(deformationDurationsd);
        // c3. property gripper force
        Property gripperForcesd = new Property();
        gripperForcesd.setIdShort("GripperForceSD");
        gripperForcesd.setValue(gripper_force.get(1).toString()+"");
        stampingDeformation.addSubmodelElement(gripperForcesd);
        // c3. property task resource fd
        Property taskResoucesd = new Property();
        taskResoucesd.setIdShort("TaskresorceSD");
        taskResoucesd.setValue(task_resource.get(7));
        stampingDeformation.addSubmodelElement(taskResoucesd);
        // c3. property input parameter fd
        Property inputParametersd = new Property();
        inputParametersd.setIdShort("InputParameterSD");
        inputParametersd.setValue(input_parameter.get(7));
        stampingDeformation.addSubmodelElement(inputParametersd);
        // c3. property output parameter fd
        Property outputParametersd = new Property();
        outputParametersd.setIdShort("OutputParameterSD");
        outputParametersd.setValue(input_parameter.get(7));
        stampingDeformation.addSubmodelElement(outputParametersd);

        // c3. smc task constraints fd
        SubmodelElementCollection taskConstraintsd = new SubmodelElementCollection("TaskConstraintSD");
        // c3.1 property speed range
        Property speedRangesd = new Property();
        speedRangesd.setIdShort("SpeedRangeSD");
        speedRangesd.setValue(task_constraints.get(7).get(0));
        taskConstraintsd.addSubmodelElement(speedRangesd);
        // c3.1 property reach
        Property reachRangesd = new Property();
        reachRangesd.setIdShort("ReachRangeSD");
        reachRangesd.setValue(task_constraints.get(7).get(1));
        taskConstraintsd.addSubmodelElement(reachRangesd);
        // c3.1 property finger force range
        Property fingerForceRangesd = new Property();
        fingerForceRangesd.setIdShort("FingerForceRangeFD");
        fingerForceRangesd.setValue(task_constraints.get(7).get(2));
        taskConstraintsd.addSubmodelElement(fingerForceRangesd);
        // c3.1 property gripper force range
        Property gripperForceRangesd = new Property();
        gripperForceRangesd.setIdShort("GripperForceRangeSD");
        gripperForceRangesd.setValue(task_constraints.get(7).get(3));
        taskConstraintsd.addSubmodelElement(gripperForceRangesd);
        // c3.1 property temperature range
        Property temperatureRangesd = new Property();
        temperatureRangesd.setIdShort("TemperatureRangeSD");
        temperatureRangesd.setValue(task_constraints.get(7).get(4));
        taskConstraintsd.addSubmodelElement(temperatureRangesd);
        // c3.1 property finger open range
        Property fingerOpenRangesd = new Property();
        fingerOpenRangesd.setIdShort("FingerOpenRangeSD");
        fingerOpenRangesd.setValue(task_constraints.get(7).get(5));
        taskConstraintsd.addSubmodelElement(fingerOpenRangesd);
        // c3.1 property gripper open range
        Property gripperOpenRangesd = new Property();
        gripperOpenRangesd.setIdShort("GripperOpenRangeSD");
        gripperOpenRangesd.setValue(task_constraints.get(7).get(6));
        taskConstraintsd.addSubmodelElement(gripperOpenRangesd);
        // c3.1 property deformation range
        Property deformationRangesd = new Property();
        deformationRangesd.setIdShort("DeformationRangeSD");
        deformationRangesd.setValue(task_constraints.get(7).get(7));
        taskConstraintsd.addSubmodelElement(deformationRangesd);

        stampingDeformation.addSubmodelElement(taskConstraintsd);


        processStageB.addSubmodelElement(stampingDeformation);

        // c. smc picka and placed
        SubmodelElementCollection pickAplaceD = new SubmodelElementCollection("PickandPlaceD");
        // c4. property sheet position
        Property sheetPositionad = new Property();
        sheetPositionad.setIdShort("SheetPositionD");
        sheetPositionad.setValue(sheet_position.get(3));
        pickAplaceD.addSubmodelElement(sheetPositionad);
        // c4. property sheet orientation
        Property sheetOrientationad = new Property();
        sheetOrientationad.setIdShort("SheetOrientationD");
        sheetOrientationad.setValue(sheet_orientation.get(3));
        pickAplaceD.addSubmodelElement(sheetOrientationad);
        // c4. property target position
        Property targetPositionad = new Property();
        targetPositionad.setIdShort("TargetPositionAD");
        targetPositionad.setValue(target_position.get(3));
        pickAplaceD.addSubmodelElement(targetPositionad);
        // c4. property target orentation
        Property targetOrientationad = new Property();
        targetOrientationad.setIdShort("TargetOrientationD");
        targetOrientationad.setValue(target_orientation.get(3));
        pickAplaceD.addSubmodelElement(targetOrientationad);
        // c4. property actual grip force of ent effector
        Property gripForcead = new Property();
        gripForcead.setIdShort("ActualGripForceOfEndEffectorD");
        gripForcead.setValue(grip_force.get(3));
        pickAplaceD.addSubmodelElement(gripForcead);
        // c4. property gripping angel of end effector
        Property gripAngelad = new Property();
        gripAngelad.setIdShort("GrippingAngelOfEndEffectorD");
        gripAngelad.setValue(gripping_angel.get(3));
        pickAplaceD.addSubmodelElement(gripAngelad);
        // c4. property carrying velocity
        Property carryVelosityad = new Property();
        carryVelosityad.setIdShort("CarryingVelocityD");
        carryVelosityad.setValue(carry_velocity.get(3));
        pickAplaceD.addSubmodelElement(carryVelosityad);
        // c4. property carrying path
        Property carryPathad = new Property();
        carryPathad.setIdShort("CarryingPathD");
        carryPathad.setValue(carry_path.get(3));
        pickAplaceD.addSubmodelElement(carryPathad);
        // c4. property task resource 1
        Property taskResourcead = new Property();
        taskResourcead.setIdShort("TaskResourceD");
        taskResourcead.setValue(task_resource.get(3));
        pickAplaceD.addSubmodelElement(taskResourcead);
        // c4. property input parameter 1
        Property inputParameterad = new Property();
        inputParameterad.setIdShort("InputParameterD");
        inputParameterad.setValue(input_parameter.get(3));
        pickAplaceD.addSubmodelElement(inputParameterad);
        // c4. property output parameter 1
        Property outputParameterad = new Property();
        outputParameterad.setIdShort("OutputParameterD");
        outputParameterad.setValue(output_parameter.get(3));
        pickAplaceD.addSubmodelElement(outputParameterad);

        // c4. smc task constraints 1
        SubmodelElementCollection taskConstrainad = new SubmodelElementCollection("TaskConstrainD");
        // c4.1 property speed range
        Property speedRangead = new Property();
        speedRangead.setIdShort("SpeedRangeD");
        speedRangead.setValue(task_constraints.get(3).get(0));
        taskConstrainad.addSubmodelElement(speedRangead);
        // c4.1 property reach
        Property reachRangead = new Property();
        reachRangead.setIdShort("ReachRangeD");
        reachRangead.setValue(task_constraints.get(3).get(1));
        taskConstrainad.addSubmodelElement(reachRangead);
        // c4.1 property finger force range
        Property fingerForceRangead = new Property();
        fingerForceRangead.setIdShort("FingerForceRangeD");
        fingerForceRangead.setValue(task_constraints.get(3).get(2));
        taskConstrainad.addSubmodelElement(fingerForceRangead);
        // c4.1 property gripper force range
        Property gripperForceRangead = new Property();
        gripperForceRangead.setIdShort("GripperForceRangeD");
        gripperForceRangead.setValue(task_constraints.get(3).get(3));
        taskConstrainad.addSubmodelElement(gripperForceRangead);
        // c4.1 property temperature range
        Property temperatureRangead = new Property();
        temperatureRangead.setIdShort("TemperatureRangeD");
        temperatureRangead.setValue(task_constraints.get(3).get(4));
        taskConstrainad.addSubmodelElement(temperatureRangead);
        // c4.1 property finger open range
        Property fingerOpenRangead = new Property();
        fingerOpenRangead.setIdShort("FingerOpenRangeD");
        fingerOpenRangead.setValue(task_constraints.get(3).get(5));
        taskConstrainad.addSubmodelElement(fingerOpenRangead);
        // c4.1 property gripper open range
        Property gripperOpenRangead = new Property();
        gripperOpenRangead.setIdShort("GripperOpenRangeD");
        gripperOpenRangead.setValue(task_constraints.get(3).get(6));
        taskConstrainad.addSubmodelElement(gripperOpenRangead);
        // c4.1 property deformation range
        Property deformationRangead = new Property();
        deformationRangead.setIdShort("DeformationRangeD");
        deformationRangead.setValue(task_constraints.get(3).get(7));
        taskConstrainad.addSubmodelElement(deformationRangead);

        pickAplaceD.addSubmodelElement(taskConstrainad);
        processStageC.addSubmodelElement(pickAplaceD);




        // c. smc necessary capability 2 (for loop)
        SubmodelElementCollection necessaryCapabilityc = new SubmodelElementCollection("CNecessaryCapability");

        for(int count : this.capabilityc_number){
            int number = count +1;
            SubmodelElementCollection capability = new SubmodelElementCollection("CCapability"+ Integer.toString(number));
            // capability name
            Property capabilityName = new Property();
            capabilityName.setIdShort("Name");
            capabilityName.setValue(capabilityc_name.get(count));
            capability.addSubmodelElement(capabilityName);

            // capability description
            Property capabilityDescription = new Property();
            capabilityDescription.setIdShort("Description");
            capabilityDescription.setValue(capabilityc_description.get(count));
            capability.addSubmodelElement(capabilityDescription);

            necessaryCapabilityc.addSubmodelElement(capability);
        }
        processStageC.addSubmodelElement(necessaryCapabilityc);


        // add all submodel collection in the submodel
        processSubmodel.addSubmodelElement(processStageA);
        processSubmodel.addSubmodelElement(processStageB);
        processSubmodel.addSubmodelElement(processStageC);


        return processSubmodel;
    }

	public static Asset createAsset(String processIdentifier) {
		Asset asset = new Asset(processIdentifier, new CustomId(processIdentifier), AssetKind.INSTANCE);
		return asset;
	}

	public static AssetAdministrationShell createAAS(Asset processAsset, String AASIdentifier, String description) {
		// create process asset and set aas
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
		Asset processAsset = createAsset("ShopFloorAAS_Process");
		AssetAdministrationShell processShell = createAAS(processAsset, "ShopFloorAAS",
				"This is a product AAS.");


        ShopFloorAAS_Process generator = new ShopFloorAAS_Process(0.0, 
        List.of("preparatory", "SFPP", "FPP"), 
        List.of("A", "B", "C"), 
        List.of("Description of preparatory", "Description of SFPP", "Description of FPP"), 
        "a link to the STEP.document", 
        "a link to the document", 
        "no", 
        25.0, 
        15.0, 
        "a link to the installation document", 
        "a link to the flowchat document", 
        1.0, 
        List.of("Robot, End-effector, Gripper", "Robot, End-effector, Gripper"), 
        List.of("(0,0,0)", "(1,1,1)", "(2,2,2)", "(3,3,3)"), 
        List.of("(0,0,0)", "(1,1,1)", "(2,2,2)", "(3,3,3)"), 
        List.of("(0,0,0)", "(1,1,1)", "(2,2,2)", "(3,3,3)"), 
        List.of("(0,0,0)", "(1,1,1)", "(2,2,2)", "(3,3,3)"), 
        List.of(10.0,20.0,30.0,40.0), 
        List.of("(0,1,2)", "(1,2,3)","(2,3,4)","(3,4,5)"), 
        List.of("a link to a curve of speed relative to time", "a link to a curve of speed relative to time","a link to a curve of speed relative to time","a link to a curve of speed relative to time"), 
        List.of("a link to a diagramma", "a link to a diagramma","a link to a diagramma","a link to a diagramma"), 
        List.of("Robot, End-effector", "Robot, End-effector", "Robot, End-effector","Robot, End-effector","Gripper", "Robot, End-effector, Gripper", "Gripper", "Gripper"), 
        List.of("a link to a curve of position relative to time", "a link to a curve of position relative to time", "a link to a curve of position relative to time", "a link to a curve of position relative to time", "a link to a curve of position relative to time",
        "a link to a curve of position relative to time", "a link to a curve of position relative to time", "a link to a curve of position relative to time"), 
        List.of("a link to a curve of position relative to time", "a link to a curve of position relative to time", "a link to a curve of position relative to time", "a link to a curve of position relative to time", "a link to a curve of position relative to time",
        "a link to a curve of position relative to time", "a link to a curve of position relative to time", "a link to a curve of position relative to time"), 
        List.of(List.of("0 to 0.5m/s", "1.7m", "15N to 30N", "NA", "NA", "0 to 20cm", "NA", "NA"),
        List.of("0 to 0.5m/s", "1.7m", "15N to 30N", "NA", "NA", "0 to 20cm", "NA", "NA"),
        List.of("0 to 0.5m/s", "1.7m", "15N to 30N", "NA", "NA", "0 to 20cm", "NA", "NA"),
        List.of("0 to 0.5m/s", "1.7m", "15N to 30N", "NA", "NA", "0 to 20cm", "NA", "NA"),
        List.of("NA", "NA", "NA", "NA", "220 to 260", "NA", "NA", "NA"),
        List.of("0 to 0.02m/s", "NA", "NA", "20N to 30N", "220 to 260", "NA", "10 to 20cm", "a link to a image show the work region"),
        List.of("NA", "NA", "NA", "NA", "220 to 260", "NA", "NA", "NA"),
        List.of("0 to 0.02m/s", "NA", "NA", "20N to 30N", "NA", "NA", "10 to 20cm", "a link to a image show the work region")), 
        List.of(260.0, 250.0), 
        List.of(15.0, 15.5), 
        List.of("a curve of Temperature relative to time", "a curve of Temperature relative to time"), 
        List.of(5.0, 6.0), 
        List.of(5.0, 1.0), 
        List.of(20.0,100.0), 
        List.of(2.0, 1000.0), 
        List.of("a link to a path diagrama, stored in an extra database", "a link to a path diagrama, stored in an extra database"), 
        List.of(260.0, -1.0), 
        List.of(60.0, 5.0), 
        List.of(50.0, 100.0), 
        List.of(100.0,100.0), 
        List.of(0,1,2,3), 
        List.of("Pick and Place", "Heating", "Moving", "Deformation"), 
        List.of("Description of pick and place", "Description of heating", "Description of moving", "Description of Deformation"), 
        List.of(0,1,2,3), 
        List.of("Pick and Place", "Heating", "Moving", "Deformation"), 
        List.of("Description of pick and place", "Description of heating", "Description of moving", "Description of Deformation"));


		List<Submodel> submodels = generateAndRegisterSubmodels(processShell, generator);

		MultiSubmodelProvider fullProvider = getFullProvier(processShell, submodels);

		startServerWithInMemoryRegistry(fullProvider, processShell);

		startServerAndConnectToExternalRegistry(fullProvider, processShell);

		UseExternalServerAndRegistry(processShell, submodels);

	}

}
