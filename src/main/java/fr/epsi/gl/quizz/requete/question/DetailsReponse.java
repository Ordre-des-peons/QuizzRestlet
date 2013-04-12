package fr.epsi.gl.quizz.requete.question;

public class DetailsReponse {

    public String getLibellé() {
        return libellé;
    }

    public void setLibellé(String libellé) {
        this.libellé = libellé;
    }

    public boolean isCorrecte() {
        return correcte;
    }

    public void setCorrecte(boolean correcte) {
        this.correcte = correcte;
    }

    private boolean correcte;
    private String libellé;
}
