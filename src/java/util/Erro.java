package util;
public class Erro {
    private String tipo;
    private String mens;

    public Erro(String tipo, String mens) {
        this.tipo = tipo;
        this.mens = mens;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMens() {
        return mens;
    }

    public void setMens(String mens) {
        this.mens = mens;
    }
    
    
}
