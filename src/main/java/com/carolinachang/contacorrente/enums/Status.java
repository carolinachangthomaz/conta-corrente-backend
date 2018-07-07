package com.carolinachang.contacorrente.enums;


public enum Status {
	
	PAGO,
	PENDENTE;
	
	public static Status getStatus(String status) {
		switch (status) {
		case "PAGO": return PAGO;
		case "PENDENTE": return PENDENTE;
		default : return PENDENTE;
			
	    }
    }
}
