package br.edu.ifsul.cstsi.tads_cleber.infra.exception;

public class TokenInvalidoException extends RuntimeException {
    public TokenInvalidoException(String mensagem) {
        super(mensagem);
    }
}
