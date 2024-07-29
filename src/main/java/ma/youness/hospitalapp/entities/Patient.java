package ma.youness.hospitalapp.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.*;


import lombok.*;




import java.util.Date;

@Entity
@Table(name = "PATIENTS")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty @Size(min = 3, max = 50)
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Min(100)
    private int score;
    private boolean mlade;


}
