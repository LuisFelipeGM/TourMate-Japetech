package com.japetech.tourmate.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.japetech.tourmate.dtos.ViagemDto;
import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.models.ViagemModel;
import com.japetech.tourmate.repositories.UsuarioRepository;
import com.japetech.tourmate.repositories.ViagemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
public class ViagemService extends EntityService<ViagemModel> {

    private static final Logger log = LoggerFactory.getLogger(ViagemService.class);

    private final ViagemRepository viagemRepository;
    private final UsuarioRepository usuarioRepository;


    ViagemService(JpaRepository<ViagemModel, Long> repository, ViagemRepository viagemRepository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.viagemRepository = viagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ViagemModel adicionaViagem(ViagemDto viagemDto){
        try {
            Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(viagemDto.idUsuario());
            if (usuarioOptional.isPresent()){
                UsuarioModel usu = usuarioOptional.get();
                ViagemModel viagem = new ViagemModel();
                BeanUtils.copyProperties(viagemDto, viagem);
                viagem.setUsuario(usu);

                log.info("Acessando o GPT");
                String resposta = chatGPT();

                log.info("Salvando o retorno do GPT");
                viagem.setResumo(resposta);

                log.info("Salvando a Viagem");
                return repository.save(viagem);
            } else {
                throw new Exception("Usuário não encontrado");
            }
        } catch (Exception e) {
            log.error("Erro na resposta do GPT " + e.getMessage());
            throw new RuntimeException("Erro ao salvar o nova viagem.");
        }
    }

    public String chatGPT() {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";
        String syst_mess = "You are a TRAVEL GUIDE ASSISTANT. you respond ONLY in a JSON object. the json will look like this:{city : [travel summary(one to two lines), value of the travel(BRAZILIAN CURRENCY), [first day, second day, third day...]]}. the key will be cities close to the city passed by the user. the first item of the list will be a quick travel summary, the second will be the estimate value of the travel (only one estimate number), consider every kind of costs, including travel, stay and food. the third will be a list of a thing to do in each day.";

        String message = "Give me travel recomendations in São Paulo state in winter with romantic porposes for 3 days in 3 diferent cities. (TRANSLATE RESPONSE TO BRAZILIAN PORTUGUESE)";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            String body = String.format("{\"model\": \"%s\", \"messages\": [{\"role\": \"system\", \"content\": \"%s\"}, {\"role\": \"user\", \"content\": \"%s\"}]}",
                    model, syst_mess, message);
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

            String content = jsonObject.getAsJsonArray("choices")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content")
                    .getAsString();
            //log.info(content);
            return content;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
