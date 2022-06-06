package com.example.convidados.constants;

public class DataBaseConstants {

    private DataBaseConstants(){}

    public static class GUEST{

        //NOME DA TABELA DENTRO DO SQLITE
        public static final String TABLE_NAME = "Guest";

        //AS TRÊS COLUNAS PARA SALVAR OS DADOS NO SQ LITE
        public static class COLUMNS{
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String PRESENCE = "presence";
        }
    }
}
