package model;

import java.util.ArrayList;

public class LotSizingMethods {

	public ArrayList<Integer> sistemaLotePorLote(ArrayList<Integer> articulosSemanales) {
		return articulosSemanales;
	}
	
	public ArrayList<Integer> sistemaCantidadDeOrdenEconomica(ArrayList<Integer> articulosSemanales, double costoArticulo, double costoPreparacion, double costoMantenimiento){
		int EOQ = (int) calcularCantidadDeOrdenEconomica(articulosSemanales, costoArticulo, costoPreparacion, costoMantenimiento);
		ArrayList<Integer> retorno = new ArrayList<>();
		int cantidad = 0;
		retorno.add(EOQ);
		for(int i = 0; i < articulosSemanales.size(); i++) {
			cantidad += articulosSemanales.get(i);
			if(cantidad >= EOQ) {
				retorno.add(EOQ);
				cantidad = articulosSemanales.get(i);
			}else if(i != 0){
				retorno.add(0);
			}
		}
		return retorno;
	}

	public double calcularCantidadDeOrdenEconomica(ArrayList<Integer> articulosSemanales, double costoArticulo, double costoPreparacion, double costoMantenimiento) {

		// Pasamos de semanas a años
		int totalArticulos = 0;
		for (int i = 0; i < articulosSemanales.size(); i++) {
			totalArticulos += articulosSemanales.get(i);
		}
		double D = ((totalArticulos * 1.0) / articulosSemanales.size()) * (52);

		// encontramos el costo anual de mantemiento
		double H = costoMantenimiento * costoArticulo * 52;
		double EOQ = Math.sqrt((2 * D * costoPreparacion) / H);

		return EOQ;
	}

	public ArrayList<Integer> sistemaPeriodoDeSuministro(int t, ArrayList<Integer> articulosSemanales) {

		ArrayList<Integer> pedidos = new ArrayList<Integer>();
		int contador = 0;
		int sumaProductos = 0;
		for (int i = 0; i < articulosSemanales.size(); i++) {
			contador++;
			sumaProductos = sumaProductos + articulosSemanales.get(i);
			if (contador == t) {
				contador = 0;
				pedidos.add(sumaProductos);
				sumaProductos = 0;
			} else if (i == articulosSemanales.size() - 1) {
				pedidos.add(sumaProductos);
			}
		}
		return pedidos;
	}

	public ArrayList<Integer> sistemaCantidadDeOrdenDePeriodo(ArrayList<Integer> articulosSemanales, double costoArticulo, double costoPreparacion, double costoMantenimiento) {
		ArrayList<Integer> retornoReves = new ArrayList<Integer>();
		int demanda = 0;
		for (int i = 0; i < articulosSemanales.size(); i++) {
			demanda += articulosSemanales.get(i);
		}
		double frecienciaPedido = (demanda * 1.0) / calcularCantidadDeOrdenEconomica(articulosSemanales, costoArticulo, costoPreparacion, costoMantenimiento);
		int periodoOptimoPedido = (int) ((articulosSemanales.size() * 1.0) / frecienciaPedido);
		int cantidad = 0;
		System.out.println(articulosSemanales.size() - 1);
		for (int j = articulosSemanales.size() - 1; j >= 0; j--) {
			cantidad += articulosSemanales.get(j);
			if (j % periodoOptimoPedido == 0) {
				retornoReves.add(cantidad);
				cantidad = 0;
			} else {
				retornoReves.add(0);
			}
		}
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		for (int i = retornoReves.size() - 1; i >= 0; i--) {
			retorno.add(retornoReves.get(i));
		}
		return retorno;
	}
	
	public ArrayList<Integer> sistemaPorMenorCostoUnitario(ArrayList<Integer> articulosSemanales, double costoPreparacion, double costoMantenimiento) {
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		int cantidad = 0;
		int vecesAlmacenada = 0;
		int unidadesAlmacenadas = 0;
		int ultimaVez = 0;
		for(int i = 0; i < articulosSemanales.size(); i++) {
			cantidad += articulosSemanales.get(i);
			unidadesAlmacenadas += articulosSemanales.get(i) * vecesAlmacenada;
			double costoUnitario = (costoPreparacion + (costoMantenimiento * (unidadesAlmacenadas*1.0)))/(cantidad*1.0);
			if(i != articulosSemanales.size()-1) {
				int unidadesAlmacenadasNext = unidadesAlmacenadas + (articulosSemanales.get(i+1)*(vecesAlmacenada+1)); 
				double costoUnitarioNext = (costoPreparacion + (costoMantenimiento * (unidadesAlmacenadasNext*1.0)))/((cantidad+articulosSemanales.get(i+1))*1.0);
				if(costoUnitarioNext > costoUnitario) {
					auxActualizarLista(retorno, ultimaVez, i, cantidad);
					cantidad = 0;
					unidadesAlmacenadas = 0;
					vecesAlmacenada = 0;
					ultimaVez = i+1;
				}else {
					vecesAlmacenada++;
				}
			}else {
				auxActualizarLista(retorno, ultimaVez, i, cantidad);
			}
		}
		return retorno;
	}

	public ArrayList<Integer> sistemaPorCostoTotalMinimo(ArrayList<Integer> articulosSemanales, double costoPreparacion, double costoMantenimiento) {
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		int cantidad = 0;
		int vecesAlmacenada = 0;
		int unidadesAlmacenadas = 0;
		int ultimaVez = 0;
		for(int i = 0; i < articulosSemanales.size(); i++) {
			cantidad += articulosSemanales.get(i);
			unidadesAlmacenadas += articulosSemanales.get(i) * vecesAlmacenada;
			double diferencia = Math.abs(costoPreparacion - (costoMantenimiento * (unidadesAlmacenadas*1.0)));
			if(i != articulosSemanales.size()-1) {
				int unidadesAlmacenadasNext = unidadesAlmacenadas + (articulosSemanales.get(i+1)*(vecesAlmacenada+1)); 
				double diferenciaNext = Math.abs(costoPreparacion - (costoMantenimiento * (unidadesAlmacenadasNext*1.0)));
				if(diferenciaNext > diferencia) {
					auxActualizarLista(retorno, ultimaVez, i, cantidad);
					cantidad = 0;
					unidadesAlmacenadas = 0;
					vecesAlmacenada = 0;
					ultimaVez = i+1;
				}else {
					vecesAlmacenada++;
				}
			}else {
				auxActualizarLista(retorno, ultimaVez, i, cantidad);
			}
		}
		return retorno;
	}
	
	public void auxActualizarLista(ArrayList<Integer> lista, int j, int i, int cantidad) {
		lista.add(cantidad);
		int k = j;
		while(k < i) {
			lista.add(0);
			k++;
		}
	}
	
}
