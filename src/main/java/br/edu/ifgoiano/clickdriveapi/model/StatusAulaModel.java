package br.edu.ifgoiano.clickdriveapi.model;

/**
 * Enumeração para representar os possíveis estados de uma aula
 * no sistema clickDrive.
 */
public enum StatusAulaModel {

    AGENDADA,      // Aula marcada, aguardando a data
    CONCLUIDA,     // Aula realizada com sucesso
    CANCELADA,     // Aula desmarcada
    ALUNO_AUSENTE, // O aluno não compareceu
    INSTRUTOR_AUSENTE // O instrutor não compareceu
}