package br.vrbeneficios.cidadaos.service;

public enum Regiao {
    //REGIAO NORTE
    AC(1), AP(1), AM(1), PA(1), RO(1), RR(1), TO(1),
    //REGIAO NORDESTE
    AL(2), BA(2), CE(2), MA(2), PB(2), PI(2), PE(2), RN(2), SE(2),
    //REGIAO CENTRO-OESTE
    DF(3), GO(3), MT(3), MS(3),
    //REGIAO SUDESTE
    ES(4), MG(4), RJ(4), SP(4),
    //REGIAO SUL
    PR(5), RS(5), SC(5);

    public Integer regiaoId;

    Regiao(Integer regiaoId){
        this.regiaoId = regiaoId;
    }

}
