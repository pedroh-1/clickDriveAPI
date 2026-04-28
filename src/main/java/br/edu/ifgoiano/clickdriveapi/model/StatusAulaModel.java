package br.edu.ifgoiano.clickdriveapi.model;

/**
 * Enumeração para representar os possíveis estados de uma aula
 * no sistema clickDrive.
 */
public enum StatusAulaModel {

    SOLICITADA,      // Aula marcada, aguardando a data
    CONFIRMADA,     // Aula realizada com sucesso
    CONCLUIDA,     // Aula desmarcada
    CANCELADA, // O aluno não compareceu
}