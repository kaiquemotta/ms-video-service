Feature: Confirmar upload de vídeo

  Scenario: Confirmar envio de vídeo e publicar evento
    Given o título do vídeo é "Aula de Java"
    And o nome do arquivo é "aula.mp4"
    And o clientId é "cliente-123"
    And o tempo de divisão é 10 segundos
    When eu confirmar o upload
    Then o vídeo deve ser salvo no repositório
    And o evento deve ser publicado na fila
