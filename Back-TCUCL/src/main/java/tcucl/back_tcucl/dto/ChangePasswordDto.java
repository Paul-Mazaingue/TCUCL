package tcucl.back_tcucl.dto;

public class ChangePasswordDto {

    public ChangePasswordDto(String email, String ancienMdp, String nouveauMdp) {
        this.email = email;
        this.ancienMdp = ancienMdp;
        this.nouveauMdp = nouveauMdp;
    }

    private String email;
    private String ancienMdp;
    private String nouveauMdp;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAncienMdp() {
        return ancienMdp;
    }

    public void setAncienMdp(String ancienMdp) {
        this.ancienMdp = ancienMdp;
    }

    public String getNouveauMdp() {
        return nouveauMdp;
    }

    public void setNouveauMdp(String nouveauMdp) {
        this.nouveauMdp = nouveauMdp;
    }
}
