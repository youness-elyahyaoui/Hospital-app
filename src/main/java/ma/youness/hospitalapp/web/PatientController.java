package ma.youness.hospitalapp.web;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.youness.hospitalapp.entities.Patient;
import ma.youness.hospitalapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository ;
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size" ,defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw) {
        Page<Patient> pagePatient = patientRepository.findByNomContains(kw,PageRequest.of(page,size));
        model.addAttribute("patients", pagePatient);
        model.addAttribute("currentPage",page);
        model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
        model.addAttribute("keyword",kw);
        return "Patient";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id,String keyword,int page) {
        patientRepository.deleteById(id);

     return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

    @PostMapping("/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "formPatients";
        }

        patientRepository.save(patient);
        return "formPatients";
    }

    @GetMapping("/editPatient")
    public String editPatient(@RequestParam(name = "id")Long id,Model model){
        model.addAttribute("patient",patientRepository.findById(id).get());

        return "editPatients";
    }


}
