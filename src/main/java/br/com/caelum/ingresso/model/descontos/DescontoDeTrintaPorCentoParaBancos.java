package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoDeTrintaPorCentoParaBancos implements Desconto{
	private BigDecimal percentualDeDsconto =new BigDecimal("0.3");
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal){
		return precoOriginal.subtract(trintaPorCentoSobre(precoOriginal));
	}
	private BigDecimal trintaPorCentoSobre(BigDecimal precoOriginal) {
		// TODO Auto-generated method stub
		return precoOriginal.multiply(percentualDeDsconto);
	}
}
