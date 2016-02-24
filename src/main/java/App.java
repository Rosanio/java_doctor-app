import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object>model= new HashMap<String, Object>();
      model.put("doctors", Doctor.all());
      // model.put("patients", request.session().attribute("patients"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/doctors", (request, response) -> {
      HashMap<String, Object>model= new HashMap<String, Object>();
      String doctorName = request.queryParams("doctorName");
      String specialty = request.queryParams("specialty");
      Doctor newDoctor = new Doctor(doctorName, specialty);
      newDoctor.save();
      // Doctor.order();
      model.put("doctors", Doctor.all());
      model.put("template", "templates/doctors.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors", (request, response) -> {
      HashMap<String, Object>model= new HashMap<String, Object>();
      model.put("doctors", Doctor.all());
      model.put("template", "templates/doctors.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors/:id" , (request, response) -> {
      HashMap<String, Object>model = new HashMap<String, Object>();
      Doctor doctor = Doctor.find(Integer.parseInt(request.params(":id")));
      model.put("doctor", doctor);
      model.put("patients", doctor.viewPatients());
      model.put("template", "templates/doctor.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/patients", (request, response) -> {
      HashMap<String, Object>model= new HashMap<String, Object>();
      String patientName = request.queryParams("patientName");
      String birthday = request.queryParams("birthday");
      int doctorId = Integer.parseInt(request.queryParams("doctorId"));
      Patient newPatient = new Patient(patientName, birthday, doctorId);
      newPatient.save();
      model.put("patients", Patient.all());
      model.put("template", "templates/patients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
