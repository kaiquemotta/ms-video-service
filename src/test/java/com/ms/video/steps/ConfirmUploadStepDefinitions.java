package com.ms.video.steps;

import com.ms.video.application.ConfirmUploadUseCase;
import com.ms.video.domain.model.Video;
import com.ms.video.port.output.SqsPublisher;
import com.ms.video.port.output.VideoRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.mockito.Mockito.*;

public class ConfirmUploadStepDefinitions {

    private String title, key, clientId;
    private int secondsPartition;
    private ConfirmUploadUseCase useCase;

    private VideoRepository videoRepository;
    private SqsPublisher sqsPublisher;

    private Video resultVideo;

    @Given("o título do vídeo é {string}")
    public void o_titulo_do_video_e(String titulo) {
        this.title = titulo;
    }

    @Given("o nome do arquivo é {string}")
    public void o_nome_do_arquivo_e(String key) {
        this.key = key;
    }

    @Given("o clientId é {string}")
    public void o_clientId_e(String clientId) {
        this.clientId = clientId;
    }

    @Given("o tempo de divisão é {int} segundos")
    public void o_tempo_de_divisao_e_segundos(Integer segundos) {
        this.secondsPartition = segundos;
        this.videoRepository = mock(VideoRepository.class);
        this.sqsPublisher = mock(SqsPublisher.class);

        useCase = new ConfirmUploadUseCase(videoRepository, sqsPublisher, "meu-bucket");
    }

    @When("eu confirmar o upload")
    public void eu_confirmar_o_upload() {
        resultVideo = useCase.execute(title, key, clientId, secondsPartition);
    }

    @Then("o vídeo deve ser salvo no repositório")
    public void o_video_deve_ser_salvo_no_repositorio() {
        verify(videoRepository, times(1)).save(resultVideo);
    }

    @Then("o evento deve ser publicado na fila")
    public void o_evento_deve_ser_publicado_na_fila() {
        verify(sqsPublisher, times(1)).publish(resultVideo);
    }
}
