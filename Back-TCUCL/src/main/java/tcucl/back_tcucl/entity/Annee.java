package tcucl.back_tcucl.entity;

import jakarta.persistence.*;
import tcucl.back_tcucl.config.AnneeConfig;
import tcucl.back_tcucl.entity.onglet.*;
import tcucl.back_tcucl.entity.onglet.achat.AchatOnglet;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentImmobilisationMobilierOnglet;
import tcucl.back_tcucl.entity.onglet.dechet.DechetOnglet;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.EmissionFugitiveOnglet;
import tcucl.back_tcucl.entity.onglet.energie.EnergieOnglet;
import tcucl.back_tcucl.entity.onglet.mobInternationale.MobInternationalOnglet;
import tcucl.back_tcucl.entity.onglet.numerique.NumeriqueOnglet;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirieOnglet;
import tcucl.back_tcucl.entity.onglet.vehicule.VehiculeOnglet;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "annee")
public class Annee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int anneeValeur;

    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Onglet> onglets;

    @ManyToOne
    @JoinColumn(name = "entite_id")
    private Entite entite;

    public Annee() {
        this.anneeValeur = AnneeConfig.getAnneeCourante();
        // ne pas retirer le ArrayList autour du list.of sinon l'objet devient immutable et ça coince avec spring jpa
        // ¯\_(ツ)_/¯
        this.onglets = new ArrayList<>(List.of(
                new AchatOnglet(),
                new AutreImmobilisationOnglet(),
                new AutreMobFrOnglet(),
                new BatimentImmobilisationMobilierOnglet(),
                new DechetOnglet(),
                new EmissionFugitiveOnglet(),
                new EnergieOnglet(),
                new GeneralOnglet(),
                new MobInternationalOnglet(),
                new MobiliteDomicileTravailOnglet(),
                new NumeriqueOnglet(),
                new ParkingVoirieOnglet(),
                new VehiculeOnglet()
        ));

        this.onglets.forEach(o -> o.setAnnee(this));
    }

    public Annee(int anneeValeur) {
        this.anneeValeur = anneeValeur;

        // ne pas retirer le ArrayList autour du list.of sinon l'objet devient immutable et ça coince avec spring jpa
        // ¯\_(ツ)_/¯
        this.onglets = new ArrayList<>(List.of(
                new GeneralOnglet(),
                new EnergieOnglet(),
                new AchatOnglet(),
                new AutreImmobilisationOnglet(),
                new AutreMobFrOnglet(),
                new BatimentImmobilisationMobilierOnglet(),
                new DechetOnglet(),
                new EmissionFugitiveOnglet(),
                new MobiliteDomicileTravailOnglet(),
                new MobInternationalOnglet(),
                new NumeriqueOnglet(),
                new ParkingVoirieOnglet(),
                new VehiculeOnglet()
        ));

        this.onglets.forEach(o -> o.setAnnee(this));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getAnneeValeur() {
        return anneeValeur;
    }

    public void setAnneeValeur(int annee) {
        this.anneeValeur = annee;
    }

    public List<Onglet> getOnglets(){
        return this.onglets;
    }

    public GeneralOnglet getGeneralOnglet() {
        return getOngletByType(GeneralOnglet.class);
    }

    public EnergieOnglet getEnergieOnglet() {
        return getOngletByType(EnergieOnglet.class);
    }

    public AchatOnglet getAchatOnglet() {
        return getOngletByType(AchatOnglet.class);
    }

    public AutreImmobilisationOnglet getAutreImmobilisationOnglet() {
        return getOngletByType(AutreImmobilisationOnglet.class);
    }

    public AutreMobFrOnglet getAutreMobFrOnglet() {
        return getOngletByType(AutreMobFrOnglet.class);
    }

    public BatimentImmobilisationMobilierOnglet getBatimentImmobilisationMobilierOnglet() {
        return getOngletByType(BatimentImmobilisationMobilierOnglet.class);
    }

    public DechetOnglet getDechetOnglet() {
        return getOngletByType(DechetOnglet.class);
    }

    public EmissionFugitiveOnglet getEmissionFugitiveOnglet() {
        return getOngletByType(EmissionFugitiveOnglet.class);
    }

    public MobiliteDomicileTravailOnglet getMobiliteDomicileTravailOnglet() {
        return getOngletByType(MobiliteDomicileTravailOnglet.class);
    }

    public MobInternationalOnglet getMobInternationalOnglet() {
        return getOngletByType(MobInternationalOnglet.class);
    }

    public NumeriqueOnglet getNumeriqueOnglet() {
        return getOngletByType(NumeriqueOnglet.class);
    }

    public ParkingVoirieOnglet getParkingVoirieOnglet() {
        return getOngletByType(ParkingVoirieOnglet.class);
    }

    public VehiculeOnglet getVehiculeOnglet() {
        return getOngletByType(VehiculeOnglet.class);
    }


    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    @SuppressWarnings("unchecked")
    public <T extends Onglet> T getOngletByType(Class<T> clazz) {
        return (T) onglets.stream()
                .filter(clazz::isInstance)
                .findFirst()
                .orElse(null);
    }


}
