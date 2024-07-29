package ma.youness.hospitalapp;

import lombok.Builder;
import ma.youness.hospitalapp.entities.Patient;
import ma.youness.hospitalapp.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HospitalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {

            Patient patient1 = Patient.builder()
                    .nom("Youness")
                    .dateNaissance(new Date())
                    .prenom("El yahyaoui")
                    .mlade(true)
                    .score(200)
                    .build();

            Patient patient2 = Patient.builder()
                    .nom("anas")
                    .dateNaissance(new Date())
                    .prenom("El yahyaoui")
                    .mlade(true)
                    .score(300)
                    .build();

            Patient patient3 = Patient.builder()
                    .nom("Mohamed")
                    .dateNaissance(new Date())
                    .prenom("El yahyaoui")
                    .mlade(false)
                    .score(400)
                    .build();


            patientRepository.save(patient1);
            patientRepository.save(patient2);
            patientRepository.save(patient3);

            List<Patient> patients = patientRepository.findAll();

            System.out.println(patients.get(0).toString());
            System.out.println(patients.get(1).toString());
            System.out.println(patients.get(2).toString());

         };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
