package service;
/*
 * *Diseña una clase, que encapsule a cualquier objeto Java. Tendrá los métodos
getData y setData para acceder y establecer el valor del objeto
 */
public class Capsula<T> {
	private T valor;
    public Capsula(T valor) {
        super();
        this.valor = valor;
    }
    public T getValor() {
        return valor;
    }
    public void setValor(T valor) {
        this.valor = valor;
    }
}
